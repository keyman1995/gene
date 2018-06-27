package com.ycjcjy.gene.web.action.cardTicket;

import com.ycjcjy.gene.VO.TicketCardVo;
import com.ycjcjy.gene.VO.TicketSalerVo;
import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.common.util.WebSorketSessionIdUtil;
import com.ycjcjy.gene.common.webSocket.WebSocketController;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.*;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.util.DateUtils;
import net.onebean.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 案场端
 * Created by szc on 2018/5/10.
 */
@Controller
@RequestMapping("/ticketTerminal")
public class TicketTerminalController {

    @Autowired
    private CardTicketMasterService cardTicketMasterService;
    @Autowired
    private CardTicketSalveService cardTicketSalveService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CustomerUserService customerUserService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SysCaseFieldAreaService sysCaseFieldAreaService;
    @Autowired
    private SysCaseFieldTableService sysCaseFieldTableService;
    @Autowired
    private GoodsOrderSubService goodsOrderSubService;
    @Autowired
    private LocalGoodsTypeService localGoodsTypeService;
    @Autowired
    private LocalGoodsService localGoodsService;
    @Autowired
    private GoodsOrderMainService goodsOrderMainService;

    private static final Logger logger = LoggerFactory.getLogger(TicketTerminalController.class);

    private static final String SHARED = "3";

    @RequestMapping("/getSysUserInfo")
    @ResponseBody
    public ResponseBean getSysUserInfo(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        try{
            Object o =  request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            Object userType =  request.getSession().getAttribute(BaseConstans.SESSION_ANCHANG_USERTYPE.getValue());
            if(o==null){
                responseBean.setError(3,"用户丢失");
            }else{
                SysUser sysUser = (SysUser)o;
                String type=sysUser.getUser_type();
                if(userType==null){
                    userType=type;
                }
                request.getSession().setAttribute(BaseConstans.SESSION_ANCHANG_USERTYPE.getValue(),type);
                SysUser curSysUser = sysUserService.findUserAndCaseById(Integer.valueOf(sysUser.getId().toString()));
                if(curSysUser.getUser_type().equals(userType.toString())){
//                    if(curSysUser.getUser_type().equals("manager")){//前端组件
//                        curSysUser.setCan_send(null);
//                        curSysUser.setCan_send_card(null);
//                    }
                    responseBean.setSuccess(curSysUser);
                }else{

                    if(curSysUser.getUser_type().equals("manager")){
                        responseBean.setError(2,"主管");
                    }else  if(curSysUser.getUser_type().equals("sale")){
                        responseBean.setError(1,"销售");
                    }


                    responseBean.setData(curSysUser);
                    request.getSession().setAttribute(BaseConstans.SESSION_SYSUSER.getValue(),curSysUser);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 分页
     * @param ticketType 有，就差tab分类
     * @param base_pageIndex
     * @param base_pageSize
     * @param request
     * @return
     */
    @RequestMapping("/masterPage")
    @ResponseBody
    public ResponseBean masterPage(String ticketType,String base_pageIndex, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            //直接判断当前用户类型，决定是那种查询
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_pageIndex)){
                base_offset = String.valueOf((Integer.valueOf(base_pageIndex)-1)*Integer.valueOf(base_pageSize));
            }


            List<TicketCardVo> list = cardTicketMasterService.queryCardListInCaseTerminal(ticketType, DateUtils.dateToString(new Date()),sysUser.getUser_type().equals("sale")?sysUser.getId().toString():""
            ,sysUser.getField_id().toString(),base_offset,base_pageSize);

            Map<String,Object> total = cardTicketMasterService.queryCardListInCaseTerminalCount(ticketType, DateUtils.dateToString(new Date()),sysUser.getUser_type().equals("sale")?sysUser.getId().toString():""
                    ,sysUser.getField_id().toString());

            int totalPage = responseBean.init(Integer.valueOf(total.get("totalCount").toString()),Integer.valueOf(base_pageSize));
            data.put("list",list);
            data.put("total",Integer.valueOf(total.get("totalCount").toString()));
            data.put("totalPrice",total.get("totalPrice").toString());
            data.put("totalPage",totalPage);
            data.put("base_pageIndex",base_pageIndex);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);


        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 发卡详情页
     * @param masterId
     * @param request
     * @return
     */
    @RequestMapping("/masterDetail")
    @ResponseBody
    public ResponseBean masterDetail(Integer masterId,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            List<TicketSalerVo> list = cardTicketMasterService.querySaleVoListByMasterId(masterId.toString(),sysUser.getField_id().toString());
            TicketCardVo cardVo = cardTicketMasterService.queryMasterDetailById(masterId.toString(),sysUser.getUser_type().equals("sale")?sysUser.getId().toString():"",DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
            data.put("list",list);
            data.put("cardVo",cardVo);
            responseBean.setSuccess(data);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     *
     * @param targetId 主卡id
     * @param slaveId  子卡id
     * @param request
     * @return
     */
    @RequestMapping("shareSlaveDetail")
    @ResponseBody
    public ResponseBean shareDetail(Long targetId,Long slaveId,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> result =new HashMap<String, Object>();
        CardTicketSalve cardTicketSalve = null;
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            if(sysUser!=null){
                result.put("userId",sysUser.getId());
                result.put("userType",sysUser.getUser_type());
            }else{
                CustomerUser customerUser = (CustomerUser) request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
                result.put("userId",customerUser.getId());
                result.put("userType","C");
                result.put("tel",customerUser.getTel()==null?"":customerUser.getTel());
            }
            //次卡如果为空则是从列表中查到一个没有分发的卡id给前台
            if(slaveId==null && sysUser!=null){
                cardTicketSalve=cardTicketSalveService.canSendSlave(Integer.valueOf(String.valueOf(sysUser.getId())),Integer.valueOf(String.valueOf(targetId)));
            }else{//子卡id不是空表示从分享详情中直接找一张卡
              cardTicketSalve = cardTicketSalveService.getBySlaveById(slaveId);
            }
            result.put("details",cardTicketMasterService.getShareDetail(targetId));
            result.put("slaveId",cardTicketSalve);
            responseBean.setSuccess(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseBean;
    }


    /**
     * 分享成功之后 进行回调表示卡已经被分享出去了
     * @param id
     * @return
     */
    @RequestMapping("upDateSlaveId")
    @ResponseBody
    public ResponseBean upDateSlaveId(Long id){
        ResponseBean responseBean = new ResponseBean();
        try{
            cardTicketSalveService.upDateCardIsSend(id,SHARED);
            responseBean.setSuccess("OK");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 客户领取优惠卡
     * @param slaveId
     * @param cardId
     * @param request
     * @return
     */
    @RequestMapping("acceptCard")
    @ResponseBody
    public ResponseBean acceptCard(Long slaveId,Long cardId,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            if(sysUser!=null){
                responseBean.setError("该用户是系统用户");
                return responseBean;
            }
            CustomerUser customerUser = (CustomerUser) request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            result = cardTicketSalveService.confirmTheCard(cardId,customerUser.getId(),slaveId);
            if((boolean)result.get("isAccepted")){
                responseBean.setError("该卡已被领取");
            }else{
                if((boolean)result.get("result")){
                    responseBean.setSuccess("领取成功");
                    return  responseBean;
                }else{
                    responseBean.setError("领取失败");
                    return  responseBean;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseBean;
    }






    /**
     * 销售派发记录
     * @param saleId
     * @param base_pageIndex
     * @param base_pageSize
     * @param request
     * @return
     */
    @RequestMapping("/saleRecord")
    @ResponseBody
    public ResponseBean saleRecord(String saleId,String ticketType,String base_pageIndex, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }
            if(StringUtils.isNotBlank(base_pageIndex)){
                base_offset = String.valueOf((Integer.valueOf(base_pageIndex)-1)*Integer.valueOf(base_pageSize));
            }

            if(StringUtils.isBlank(saleId)){
                saleId = sysUser.getId().toString();
            }
            SysUser sale = sysUserService.findById(saleId);
            List<TicketSalerVo> list = cardTicketMasterService.querySaleRecord(saleId,ticketType,sale.getField_id().toString(),base_offset,base_pageSize);
            Map<String,Object> total = cardTicketMasterService.querySaleRecordCount(saleId,ticketType,sale.getField_id().toString());
            Map<String,Object> totalTicket = cardTicketMasterService.querySaleRecordCount(saleId,"0",sale.getField_id().toString());
            Map<String,Object> totalGoods = cardTicketMasterService.querySaleRecordCount(saleId,"1",sale.getField_id().toString());

            for(int i=0;i<list.size();i++){
                if(StringUtils.isNotBlank(list.get(i).getTouserTime())){
                    list.get(i).setTouserTime(list.get(i).getTouserTime().replace("*","<br/>"));
                }

            }


            int totalPage = responseBean.init(Integer.valueOf(total.get("totalCount").toString()),Integer.valueOf(base_pageSize));
            data.put("sysUser",sysUserService.findUserAndCaseById(Integer.valueOf(saleId)));
            data.put("list",list);
            data.put("totalTicket",Integer.valueOf(totalTicket.get("totalCount").toString()));
            data.put("totalGoods",Integer.valueOf(totalGoods.get("totalCount").toString()));
            data.put("total",Integer.valueOf(total.get("totalCount").toString()));
            data.put("totalPrice",total.get("totalPrice").toString());
            data.put("totalPage",totalPage);
            data.put("base_pageIndex",base_pageIndex);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 主管下面所有销售发卡列表
     * @param request
     * @return
     */
    @RequestMapping("/saleListRecord")
    @ResponseBody
    public ResponseBean saleListRecord(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            List<TicketSalerVo> list= cardTicketMasterService.querySaleRecordFromZhuGuan(sysUser.getUser_type().equals("sale")?sysUser.getId().toString():"",sysUser.getField_id().toString());
            data.put("list",list);
            responseBean.setSuccess(data);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    //发卡收卡
    @RequestMapping(value = "/sendCardToSaler")
    @ResponseBody
    public ResponseBean sendCardToSaler(HttpServletRequest request,String json,Integer masterId){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{

            cardTicketSalveService.sendCardToSaler(json,masterId);

            responseBean.setSuccess(data,"请求成功");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    //发送短信给客户
    @RequestMapping("/sendSMSToUser")
    @ResponseBody
    public ResponseBean sendSMSToUser(HttpServletRequest request,String tel){
        ResponseBean responseBean = new ResponseBean();
        try{
            CustomerUser user =  customerUserService.findByTel(tel);
            SysUser telSysUser = sysUserService.findUserByPhone(tel);
            Teacher teacher = teacherService.findByTel(tel);
            if(telSysUser!=null||teacher!=null){
                responseBean.setError(1,"不允许发送给工作人员");
            }else{
                if(user==null){
                    responseBean.setError(2,"不是会员");
                }else{
                    Map<String,Object> map = SmsUtil.sendValidCode(tel);
                    if(("OK").equals(String.valueOf(map.get("result")))){
                        responseBean.setSuccess(tel);
                        customerUserService.addPinCode(map);
                    }else{
                        responseBean.setError(3,"发送验证码失败");
                        logger.error(String.valueOf(map.get("msg")));
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }




    //发送给客户
    @RequestMapping("/sendCardToUser")
    @ResponseBody
    public ResponseBean sendCardToUser(HttpServletRequest request,String tel,String code,String userName,int sex,int masterId,int age,int area){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            data.put("tel", tel);
            Map<String, Object> a = customerUserService.getPinCode(data);
            if (a == null || a.get("code") == null || !code.equals(String.valueOf(a.get("code")))) {
                responseBean.setError(1,"验证码错误");
                return responseBean;
            }

            CustomerUser user =  customerUserService.findByTel(tel);
            user.setSex(sex);
            user.setUsername(userName);
            user.setAge(age);
            user.setArea(area);

            //发卡
            //发卡
            int state = cardTicketSalveService.sendCardToUser(Integer.valueOf(sysUser.getId().toString()),masterId,user,1);
            if(state==2){
                responseBean.setError(2,"卡已经发完!");
                return  responseBean;
            }
            //更新用户


            data.put("user",user);

            responseBean.setSuccess(data);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 发送给客户详情页
     * @param masterId
     * @param request
     * @return
     */
    @RequestMapping("/showSendCustomerDetail")
    @ResponseBody
    public ResponseBean showSendCustomerDetail(Integer masterId,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            TicketCardVo cardVo = cardTicketMasterService.queryMasterDetailById(masterId.toString(),sysUser.getUser_type().equals("sale")?sysUser.getId().toString():"",DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
            data.put("cardVo",cardVo);
            data.put("sysUser",sysUser);
            data.put("sorketId", WebSorketSessionIdUtil.getSessionId(0,Integer.valueOf(sysUser.getId().toString())));
            responseBean.setSuccess(data);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }


    /**
     * 收回过期体验券，返回案场钱
     * @Scheduled(cron = "0 0/3 * * * ? ")
     */

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void orderStateTimer(){

        System.out.println(111111);

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar nowTime = Calendar.getInstance();


            cardTicketMasterService.updateCardTicketGuoQi(sdf.format(nowTime.getTime()));
            //cardTicketMasterService.updateCardTicketGuoQi("2018-06-07 10:55:00");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /** ====================================================渠道端================================================================ **/

    /**
     * 发卡详情页
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/channelDetail")
    @ResponseBody
    public ResponseBean channelDetail(Integer id,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            TicketCardVo cardVo = cardTicketMasterService.queryQuDaoCardById(id.toString(),DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
            data.put("cardVo",cardVo);
            responseBean.setSuccess(data);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }


    //发送短信给客户
    @RequestMapping("/sendSMSToChannelUser")
    @ResponseBody
    public ResponseBean sendSMSToChannelUser(HttpServletRequest request,String tel){
        ResponseBean responseBean = new ResponseBean();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            SysUser telSysUser = sysUserService.findUserByPhone(tel);
            Teacher teacher = teacherService.findByTel(tel);
            if(telSysUser!=null||teacher!=null){
                responseBean.setError(1,"不允许发送给工作人员");
            }else{
                if(user==null){
                    responseBean.setError(2,"用户丢失");
                }else{
                    Map<String,Object> map = SmsUtil.sendValidCode(tel);
                    if(map.get("code")!=null){
                        responseBean.setSuccess(tel);
                        customerUserService.addPinCode(map);

                    }else{
                        responseBean.setError(3,"验证码发送失败");
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    //发送给客户
    @RequestMapping("/sendChannelCardToUser")
    @ResponseBody
    public ResponseBean sendChannelCardToUser(HttpServletRequest request,String userName,String tel,String code,int masterId){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            data.put("tel", tel);
            Map<String, Object> a = customerUserService.getPinCode(data);
            if (a == null || a.get("code") == null || !code.equals(String.valueOf(a.get("code")))) {
                responseBean.setError(1,"验证码错误，请重试！");
                return responseBean;
            }
            user.setTel(tel);
            user.setUsername(userName);



            //发卡
            int state = cardTicketSalveService.sendCardToUser(0,masterId,user,0);
            if(state==0){
                responseBean.setError(2,"很抱歉，卡券被领完啦~");
                return  responseBean;
            }else if(state==2){
                responseBean.setError(3,"一个手机号仅能领取一次！");
                return  responseBean;
            }

            responseBean.setSuccess(data);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /** ====================================================自助点单================================================================ **/
    /**
     * 桌子列表
     * @return
     */
    @RequestMapping("/tableList")
    @ResponseBody
    public ResponseBean tableList(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            //直接判断当前用户类型，决定是那种查询
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            List<SysCaseFieldArea> areaList = sysCaseFieldAreaService.findAreaByCaseId(sysUser.getField_id());


            if(areaList!=null&&areaList.size()>0){


                for(int i=0;i<areaList.size();i++){
                    areaList.get(i).setTableList(sysCaseFieldTableService.queryAllTableByArea(areaList.get(i).getId()));
                }




            }
            data.put("list",areaList);

            Calendar calendar = Calendar.getInstance();
            int nowMonth = calendar.get(Calendar.MONTH )+1;
            //本月送出多少
            int monthGiveNum = goodsOrderSubService.getGiveCountBySalerId(Integer.valueOf(sysUser.getId().toString()),1,nowMonth);
            //一共送出多少
            int totalGiveNum = goodsOrderSubService.getGiveCountBySalerId(Integer.valueOf(sysUser.getId().toString()),0,nowMonth);
            data.put("monthGiveNum",monthGiveNum);
            data.put("totalGiveNum",totalGiveNum);
            responseBean.setSuccess(data);


        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }
    /**
     * 点单列表
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public ResponseBean goodsList(HttpServletRequest request,Integer tableId){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            SysCaseFieldTable table = sysCaseFieldTableService.queryAllTableById(Long.valueOf(tableId));
            data.put("table",table);
            List<LocalGoodsType> typeList = localGoodsTypeService.findAllType(sysUser.getField_id());
            List<LocalGoods> goodsList = localGoodsService.findAllGoods(sysUser.getField_id());



            for(int i = 0;i<typeList.size();i++){
                LocalGoodsType t = typeList.get(i);
                List<LocalGoods> list = new ArrayList<>();
                for (LocalGoods g:goodsList){
                    if(Integer.valueOf(g.getType())==Integer.valueOf(t.getId().toString())){
                        list.add(g);
                    }
                }
                typeList.get(i).setGoodsList(list);

            }
            data.put("typeList",typeList);
            data.put("goodsList",typeList);

            Calendar calendar = Calendar.getInstance();
            int nowMonth = calendar.get(Calendar.MONTH )+1;
            //本月送出多少
            int monthGiveNum = goodsOrderSubService.getGiveCountBySalerId(Integer.valueOf(sysUser.getId().toString()),1,nowMonth);
            //一共送出多少
            int totalGiveNum = goodsOrderSubService.getGiveCountBySalerId(Integer.valueOf(sysUser.getId().toString()),0,nowMonth);
            data.put("monthGiveNum",monthGiveNum);
            data.put("totalGiveNum",totalGiveNum);
            responseBean.setSuccess(data,"请求成功");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    //下单
    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public ResponseBean addOrder(HttpServletRequest request,String json,Integer tableId,String remark){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(BaseConstans.SESSION_SYSUSER.getValue());
            Map<String,Object> result= goodsOrderMainService.addOrder(json,tableId,Integer.valueOf(sysUser.getField_id().toString()),Integer.valueOf(sysUser.getId().toString()),remark);
            if(Integer.valueOf(result.get("success").toString())==0){
                responseBean.setError(1,"等待运营分配商品可购买次数");
            }else{
                responseBean.setSuccess(data,"请求成功");
                //开始推送
                List<SysUser> list = sysUserService.findCoffeeMaker(sysUser.getField_id());

                GoodsOrderMain orderJson = (GoodsOrderMain)result.get("order");
                for(SysUser s:list){
                    WebSocketController.sendMessageToUserJson("1",orderJson,WebSorketSessionIdUtil.getSessionId(0,Integer.parseInt(s.getId().toString())));
                }

            }





        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    //修改销售是否可以发生卡券
    @RequestMapping(value = "/updateSalerCanSend")
    @ResponseBody
    public ResponseBean updateSalerCanSend(HttpServletRequest request,Integer canSend,Integer salerId,Integer sendType){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{

            if(sendType==0) {
                sysUserService.updateSalerCanSend(canSend, salerId);
                SysUser user =  sysUserService.findById(salerId.toString());
                if(canSend==0){
                    SmsUtil.closeOrderGongNeng(user.getMobile(),user.getRealname());
                }else if(canSend==1){
                    SmsUtil.openOrderGongNeng(user.getMobile(),user.getRealname());
                }
            }else if(sendType==1){
                sysUserService.updateSalerCanSendCard(canSend,salerId);
            }

            responseBean.setSuccess("");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }




}
