package com.ycjcjy.gene.web.action.LockerKey;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.common.util.WebSorketSessionIdUtil;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.*;
import com.ycjcjy.gene.common.webSocket.WebSocketController;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/lockerkey")
public class LockerKeyController extends TlBaseController<LockerKey,LockerKeyService> {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CardTicketMasterService cardTicketMasterService;
    @Autowired
    private CustomerUserService customerUserService;


    /**
     * 删除
     * @param model
     * @return
     */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    public PageResult<LockerKey> delete(Model model, @PathVariable("id")Object id, PageResult<LockerKey> result) {
        baseService.deleteById(id);
        result.setFlag(true);
        return result;
    }
    @RequestMapping(value = "viewKey")
    @Description(value = "预览列表页面")
    public String preview(Model model, HttpServletRequest request) {
        SysUser currentUser = SpringSecurityUtil.getCurrentLoginUser(request);
        Long userFieldId =  currentUser.getField_id();
        model.addAttribute("userFieldId",userFieldId);
        return getView("keyList");
    }


    @RequestMapping("list")
    @ResponseBody
    public PageResult<LockerKey> list (Sort sort, Pagination page, PageResult<LockerKey> result, @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        /*dicCoverList(null,"date@create_time$");*/
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    @RequestMapping("addkeys")
    @ResponseBody
    public PageResult<LockerKey> addkeys(Model model, LockerKey entity, PageResult<LockerKey> result) {
        Integer fieldId= entity.getCase_field_id().intValue();
        List<Integer> lockList=baseService.findByCaseFieldId(fieldId);
        long count = entity.getEndNum() - entity.getStartNum()+1;
        int num=(int)count;
        if (baseService.containsNum(entity.getStartNum().intValue(),lockList) || baseService.containsNum(entity.getEndNum().intValue(),lockList)){
            result.setMsg("柜号重复了");
        }else {
            for (int i =0; i<num; i++){
                LockerKey lockerKey = new LockerKey();
                lockerKey.setCreate_time(new Timestamp(System.currentTimeMillis()));
                long startNum=entity.getStartNum();
                startNum=startNum+i;
                lockerKey.setLock_num(startNum);
                lockerKey.setCase_field_id(entity.getCase_field_id());
                lockerKey.setStatus(entity.getStatus());
                baseService.save(lockerKey);
                result.getData().add(lockerKey);

            }
            result.setMsg("新增成功");
        }

        /*if (entity.getId() != null) {
            baseService.update(entity);
        } else {
            baseService.save(entity);
        }*/

        return result;
    }

    @RequestMapping(value = "bind")
    @Description(value = "绑定钥匙")
    @ResponseBody
    public PageResult<LockerKey> bind( PageResult<LockerKey> result,String id,String userId,String orderId,Integer type,HttpServletRequest request) {
        try{
            LockerKey lockerKey = baseService.findById(id.toString());
           Integer currentUser = baseService.findUserId(lockerKey.getCase_field_id(),Long.valueOf(userId));
            SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
            if (currentUser==0){
                //教练二维码
                if (type==1008){
                    Teacher teacher = teacherService.findById(userId);
                    if (teacher.getType() == 0){
                        lockerKey.setCurrent_user_id(teacher.getId());
                        lockerKey.setCurrent_user_name(teacher.getName());
                        lockerKey.setStatus("0");
                        WebSocketController.sendMessageToUser("1","开锁成功",WebSorketSessionIdUtil.getSessionId(2,Integer.parseInt(userId)));
                    }else {
                        WebSocketController.sendMessageToUser("0","不是教练，开锁失败",WebSorketSessionIdUtil.getSessionId(2,Integer.parseInt(userId)));
                    }
                    //游泳健身卡二维码
                }else if (type==1009){
                    Integer flag=baseService.keyBindCard(orderId,userId);
                    if (flag == 0){
                        WebSocketController.sendMessageToUser("1","开锁成功",WebSorketSessionIdUtil.getSessionId(1,Integer.parseInt(userId)));
                        lockerKey.setCurrent_user_id(Long.valueOf(userId));
                        lockerKey.setCurrent_user_name(customerUserService.findById(userId.toString()).getUsername());
                        lockerKey.setStatus("0");
                    }else if(flag == 2){
                        result.setMsg("体验卡开始时间未到");
                    }else {
                        result.setMsg("系统错误");
                        WebSocketController.sendMessageToUser("0","系统错误",WebSorketSessionIdUtil.getSessionId(1,Integer.parseInt(userId)));
                    }

                }else if(type==1010){
                    String msg = baseService.keyBindCoach(orderId,userId,user);
                    if (StringUtils.isBlank(msg)){
                        lockerKey.setCurrent_user_id(Long.valueOf(userId));
                        lockerKey.setCurrent_user_name(customerUserService.findById(userId.toString()).getUsername());
                        lockerKey.setStatus("0");
                        WebSocketController.sendMessageToUser("1","开锁成功",WebSorketSessionIdUtil.getSessionId(1,Integer.parseInt(userId)));
                        result.setMsg("预约成功");
                    } else {
                        result.setMsg(msg);
                        WebSocketController.sendMessageToUser("0","开锁失败",WebSorketSessionIdUtil.getSessionId(1,Integer.parseInt(userId)));
                    }

                }
                baseService.update(lockerKey);
                result.getData().add(lockerKey);

            }else {
                result.setFlag(false);
                result.setMsg("该用户已绑定钥匙，开锁失败。");
            }


        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("系统错误");
        }
        return result;

    }

    @RequestMapping(value = "unbind/{id}")
    @Description(value = "解绑")
    @ResponseBody
    public PageResult<LockerKey> unbind(Model model, @PathVariable("id")Integer id, PageResult<LockerKey> result) {
        LockerKey lockerKey = baseService.findById(id.toString());
        lockerKey.setCurrent_user_name("");
        lockerKey.setCurrent_user_id(-1l);
        lockerKey.setStatus("1");
        baseService.update(lockerKey);
        result.getData().add(lockerKey);
        return result;
    }

    @RequestMapping(value = "bindCard")
    @Description(value = "核销绑定钥匙")
    @ResponseBody
    public PageResult<LockerKey> bindCard(Model model,@RequestParam("qrInfo") String qrInfo, PageResult<LockerKey> result) {
        try{
            JSONObject obj = JSON.parseObject(qrInfo);
            String userId = obj.get("userId").toString();
            String keyId = obj.get("keyId").toString();
            LockerKey lockerKey = baseService.findById(keyId);
            CustomerUser customerUser = customerUserService.findById(userId);
            Integer currentUser = baseService.findUserId(lockerKey.getCase_field_id(),Long.valueOf(userId));
            if (currentUser==0){
                lockerKey.setStatus("0");
                lockerKey.setCurrent_user_id(Long.parseLong(userId));
                lockerKey.setCurrent_user_name(customerUser.getUsername());
                baseService.update(lockerKey);
                result.setMsg("绑定成功");
            }else {
                result.setMsg("该用户已绑定钥匙，开锁失败。");
            }

        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("绑定失败");
        }
        return result;
    }



    



}
