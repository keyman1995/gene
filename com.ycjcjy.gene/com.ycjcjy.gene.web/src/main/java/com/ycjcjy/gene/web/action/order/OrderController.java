package com.ycjcjy.gene.web.action.order;

import com.ycjcjy.gene.VO.*;
import com.ycjcjy.gene.common.util.NumberArithmeticUtils;
import com.ycjcjy.gene.common.util.WebSorketSessionIdUtil;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.*;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.util.DateUtils;
import net.onebean.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by szc on 2018/5/7.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Log log = LogFactory.getLog(OrderController.class);
 
    @Autowired
    private CourseManagerService courseManagerService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;
    @Autowired
    private OrderMainService orderMainService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CardUserRelaService cardUserRelaService;
    @Autowired
    private CustomerUserService customerUserService;
    @Autowired
    private CardTicketSalveService cardTicketSalveService;


    @RequestMapping("/saleDetail")
    @ResponseBody
    public ResponseBean saleDetail(HttpServletRequest request, Long id){
        ResponseBean responseBean = new ResponseBean();
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
        Map<String,Object> data = new HashMap<>();
        try {
            CourseManager courseManager = courseManagerService.saleDetail(id);
            int ticketCount =  cardUserRelaService.queryCardsByTimeAndUserIdCount(DateUtils.getNowyyyy_MM_dd_HH_mm_ss(),user.getId().toString(),id.toString());
            data.put("courseManager",courseManager);
            data.put("ticketCount",ticketCount);

            responseBean.setSuccess(data,"请求成功");
            request.getSession().setAttribute("orderToken","1");
        }catch ( Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }


    @RequestMapping("/cardListForOrder")
    @ResponseBody
    public ResponseBean cardListForOrder(HttpServletRequest request, Long id){
        ResponseBean responseBean = new ResponseBean();
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
        try {
            List<CardUserRela> list =  cardUserRelaService.queryCardsByTimeAndUserId(DateUtils.getNowyyyy_MM_dd_HH_mm_ss(),user.getId().toString(),id.toString());
            responseBean.setSuccess(list,"请求成功");
        }catch ( Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/findAllCaseField")
    @ResponseBody
    public ResponseBean findAllCaseField(String coarchId){
        ResponseBean responseBean = new ResponseBean();
        try{
            Teacher t =  teacherService.findById(coarchId);
             String[] arr = t.getCaseids().split(",");
            List<SysCaseField> sysCaseFields = sysCaseFieldService.findByCaseids(arr);
            responseBean.setSuccess(sysCaseFields,"请求成功");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }


    @RequestMapping("/calPrice")
    @ResponseBody
    public ResponseBean calPrice(Integer courseId,Integer ticketId,Integer courseNum,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());

        try{
            if(user==null){
                throw new Exception();
            }
            double price =  orderMainService.calPrice(courseId,ticketId,courseNum);
            responseBean.setSuccess(price,"请求成功");
        }catch (Exception e){
            responseBean.setError();
            e.printStackTrace();
        }
        return responseBean;
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public ResponseBean addOrder(Integer courseId,Integer ticketId,Integer courseNum,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
        try{

            Object token = request.getSession().getAttribute("orderToken");




            if(StringUtils.isBlank(user.getTel())){
                responseBean.setError(5,"请绑定手机号!");
            }else  if(token!=null){

                double totalPrice = orderMainService.calPrice(courseId,ticketId,courseNum);
                double allMoney = NumberArithmeticUtils.add(user.getActual_balance(),user.getGift_balance()).doubleValue();
                if(totalPrice>allMoney){
                    responseBean.setError("请先到案场领取优惠券");
                   /* resultMap.put("flag",3);
                    resultMap.put("code",0);
                    resultMap.put("message","用户余额不足");
                    resultMap.put("orderMain",orderMain);*/
                    return responseBean;
                }
                int count = orderMainService.getOrderCountByCourseId(courseId.toString());
                CourseManager courseManager = courseManagerService.findByCourseId(courseId.toString());
                if(courseManager==null){
                    throw new Exception();
                }
                if(courseManager.getSpecialclass().equals(CourseEnum.COURSE_SJ.getCode().toString())||courseManager.getCoursenum()==null||count<=courseManager.getCoursenum()){


                    if(courseManager.getCourseendline().getTime()<new Date().getTime()){
                        responseBean.setError(3,"课程已结束!");
                    }else{
                        //判断体验券是否过期、已使用
                        boolean flag = true;
                        if(ticketId!=null){
                            CardUserRela rela = cardUserRelaService.findById(String.valueOf(ticketId));
                            if(rela.getState()!=0){
                                responseBean.setError(4,"卡券已使用或过期!");
                                flag = false;
                            }
                        }
                        if(flag){
                            OrderMain orderMain =  orderMainService.addOrder(courseId,ticketId,courseNum,user);
                            responseBean.setSuccess(orderMain);
                            request.getSession().removeAttribute("orderToken");
                        }
                    }
                }else{
                    responseBean.setError(2,"课程已售卖完!");
                }


            }else{
                responseBean.setError(1,"请勿重复提交!");
            }


        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 参数看具体的进行更改
     * @param payType
     * @param orderNo
     * @param payPrice
     * @param request
     * @return
     */
    @RequestMapping("/paySuccess")
    @ResponseBody
    public ResponseBean paySuccess(Integer payType, String orderNo, double payPrice,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
        try{
            Map<String,Object> map = orderMainService.paySuccess(payType,orderNo,payPrice,user);
            if(Integer.valueOf(map.get("code").toString())==1){
                responseBean.setSuccess(map.get("orderMain"));
            }else{
                responseBean.setError(Integer.valueOf(map.get("flag").toString()),map.get("message").toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/getOrderByNoForPay")
    @ResponseBody
    public ResponseBean getOrderByNoForPay( String orderNo, HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> map = new HashMap<>();
        try{
            OrderMain orderMain = orderMainService.getOrderByNo(orderNo);
            map.put("orderMain",orderMain);
            if(orderMain==null){
                responseBean.setError(2,"订单不存在");
            }else if(!orderMain.getOrder_state().equals(OrderStateEnum.ORDERMAIN_DZF.getCode())){
                responseBean.setError(1,"订单状态有误");
                responseBean.setData(map);
            }else{
                map.put("orderTime",orderMain.getCreate_time().getTime());
                map.put("currentTime",System.currentTimeMillis());
                responseBean.setSuccess(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }
    @RequestMapping("/updateOrderStateByNo")
    @ResponseBody
    public ResponseBean updateOrderStateByNo( String orderNo, HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> map = new HashMap<>();
        try{
            OrderMain orderMain = orderMainService.getOrderByNo(orderNo);
            map.put("orderMain",orderMain);
            if(orderMain==null){
                responseBean.setError(0,"订单不存在");
            }else if(!orderMain.getOrder_state().equals(OrderStateEnum.ORDERMAIN_DZF.getCode())){
                responseBean.setError(1,"订单状态有误");
                responseBean.setData(map);
            }else{
                orderMain.setOrder_state(OrderStateEnum.ORDERMAIN_YGB.getCode());
                orderMain.setClose_time(new Timestamp(new Date().getTime()));
                orderMainService.update(orderMain);
                //如果有体验券

                Integer ticketId = orderMain.getTicket_id();
                if(ticketId!=null){
                    CardUserRela rela =  cardUserRelaService.findById(ticketId.toString());

                    if(rela.getStart_time().getTime()<System.currentTimeMillis()&&
                            rela.getEnd_time().getTime()>System.currentTimeMillis()
                            ){
                        rela.setState(0);
                    }else{
                        rela.setState(2);
                    }
                    cardUserRelaService.update(rela);


                }


                responseBean.setSuccess(orderMain);
            }
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 未支付订单十五分钟改变状态
     */
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void orderStateTimer(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, -15);
            log.info(sdf.format(nowTime.getTime()));
            orderMainService.updateOrderStateByTime(sdf.format(nowTime.getTime()));



        }catch (Exception e){
            log.error("十五分钟订单状态改变接口错误!");
             e.printStackTrace();
        }
        try{

            orderMainService.updateOrderStateSiJiaoKeFinish(DateUtils.getNowyyyy_MM_dd_HH_mm_ss());



        }catch (Exception e){
            log.error("私教约课完成订单状态改变接口错误!");
            e.printStackTrace();
        }
        try{

            orderMainService.updateOrderStateSiJiaoKeOut(DateUtils.getNowyyyy_MM_dd_HH_mm_ss());



        }catch (Exception e){
            log.error("私教约课取消订单状态改变接口错误!");
            e.printStackTrace();
        }

    }

    @Scheduled(cron = "0 0/20 * * * ? ")
    public void coachLessonCountTimer(){
        try{
            List<CoachLessonDoneVO> list = orderMainService.coachLessonDone();
            if(list.size()>0){
                for(int i = 0; i<list.size();i++){
                    Teacher teacher = teacherService.findById(list.get(i).getTeacherids().toString());
                    teacher.setTeache_num(list.get(i).getLessonDone());
                    teacherService.update(teacher);
                }
            }


        }catch (Exception e){
            log.error("私教已上课时数改变接口错误!");
            e.printStackTrace();
        }

    }


    @RequestMapping("/orderPage")
    @ResponseBody
    public ResponseBean orderPage(String orderState,String base_currentPage, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<OrderMain> list = orderMainService.queryUserOrderList(user.getId().toString(),orderState,base_offset,base_pageSize);
            int total = orderMainService.queryUserOrderListCount(user.getId().toString(),orderState);
            int totalPage = responseBean.init(total,Integer.valueOf(base_pageSize));
            data.put("list",list);
            data.put("total",total);
            data.put("totalPage",totalPage);
            data.put("base_currentPage",base_currentPage);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/orderCoursePage")
    @ResponseBody
    public ResponseBean orderCoursePage(String courseType,String base_currentPage, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<OrderMain> list = orderMainService.queryUserCourseOrderList(user.getId().toString(),courseType,base_offset,base_pageSize);
            int total = orderMainService.queryUserCourseOrderListCount(user.getId().toString(),courseType);
            int totalPage = responseBean.init(total,Integer.valueOf(base_pageSize));
            data.put("list",list);
            data.put("total",total);
            data.put("totalPage",totalPage);
            data.put("base_currentPage",base_currentPage);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/orderSiJiaoPage")
    @ResponseBody
    public ResponseBean orderSiJiaoPage(String base_currentPage, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<SiJiaoListVo> list = orderMainService.queryUserSiJiaoOrderList(user.getId().toString(),base_offset,base_pageSize);
            int total = orderMainService.queryUserSiJiaoOrderListCount(user.getId().toString());
            int totalPage = responseBean.init(total,Integer.valueOf(base_pageSize));
            data.put("list",list);
            data.put("total",total);
            data.put("totalPage",totalPage);
            data.put("base_currentPage",base_currentPage);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/orderTuanTiPage")
    @ResponseBody
    public ResponseBean orderTuanTiPage(String base_currentPage, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<TuanTiListVo> list = orderMainService.queryUserTuanTiOrderList(user.getId().toString(), DateUtils.dateToString(new Date()),base_offset,base_pageSize);
            int total = orderMainService.queryUserTuanTiOrderListCount(user.getId().toString());
            int totalPage = responseBean.init(total,Integer.valueOf(base_pageSize));
            data.put("list",list);
            data.put("total",total);
            data.put("totalPage",totalPage);
            data.put("base_currentPage",base_currentPage);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);


        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/siJiaoDetail")
    @ResponseBody
    public ResponseBean siJiaoDetail(String teacherId,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());

            SiJiaoListVo detail = orderMainService.querySiJiaoOrderDetailBySiJiaoId(user.getId().toString(), teacherId);

            data.put("teacherDetail",detail);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/siJiaoOrderDetailPage")
    @ResponseBody
    public ResponseBean siJiaoOrderDetailPage(String teacherId,String base_currentPage, String base_pageSize,HttpServletRequest request){

        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<SiJiaoListVo> list = orderMainService.querySiJiaoOrderDetailVoList(user.getId().toString(), teacherId,base_offset,base_pageSize);
            int total = orderMainService.querySiJiaoOrderDetailVoListCount(user.getId().toString(), teacherId);
            int totalPage = responseBean.init(total,Integer.valueOf(base_pageSize));
            data.put("list",list);
            data.put("total",total);
            data.put("totalPage",totalPage);
            data.put("base_currentPage",base_currentPage);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/userOrderDetail")
    @ResponseBody
    public ResponseBean userOrderDetail(String id,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());

            OrderMain orderMain = orderMainService.queryUserOrderDetailById(id);
            data.put("orderDetail",orderMain);
            responseBean.setSuccess(data);


        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/courseOrderDetail")
    @ResponseBody
    public ResponseBean courseOrderDetail(String id,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());

            OrderMain orderMain = orderMainService.queryCourseOrderDetailById(id);
            data.put("sorketId", WebSorketSessionIdUtil.getSessionId(1,Integer.valueOf(user.getId().toString())));


            data.put("orderDetail",orderMain);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/siJiaoOrderDetail")
    @ResponseBody
    public ResponseBean siJiaoOrderDetail(String id,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());

            SiJiaoListVo orderMain = orderMainService.querySiJiaoOrderDetailById(id);
            data.put("sorketId", WebSorketSessionIdUtil.getSessionId(1,Integer.valueOf(user.getId().toString())));
            data.put("orderDetail",orderMain);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/tuanTiOrderDetail")
    @ResponseBody
    public ResponseBean tuanTiOrderDetail(String id,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());

            TuanTiListVo orderMain = orderMainService.queryTuanTiOrderDetailById(user.getId().toString(),id,DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
            data.put("sorketId", WebSorketSessionIdUtil.getSessionId(1,Integer.valueOf(user.getId().toString())));
            data.put("orderDetail",orderMain);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

}
