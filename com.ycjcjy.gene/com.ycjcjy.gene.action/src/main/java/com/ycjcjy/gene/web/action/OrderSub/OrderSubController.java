package com.ycjcjy.gene.web.action.OrderSub;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.common.util.WebSorketSessionIdUtil;
import com.ycjcjy.gene.core.BaseController;
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
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/orderSub")
public class OrderSubController extends BaseController<OrderSub,OrderSubService> {
    @Autowired
    private OrderMainService orderMainService;
    @Autowired
    private LockerKeyService lockerKeyService;
    @Autowired
    private CustomerUserService customerUserService;

    @RequestMapping(value = "previewList")
    @Description(value = "预览列表页面")
    public String preview(Model model,@RequestParam("qrInfo") String qrInfo,HttpServletRequest request) {
//        String id, String belong_id, Integer type,Integer num
        SysUser currentUser = SpringSecurityUtil.getCurrentLoginUser(request);
        JSONObject obj = JSON.parseObject(qrInfo);
        model.addAttribute("id",obj.get("id"));
        model.addAttribute("belong_id",obj.get("belong_id"));
        model.addAttribute("type",obj.get("type"));
        model.addAttribute("keyId",obj.get("keyId"));
        model.addAttribute("case_id",currentUser.getField_id());
        return getView("list");
    }

    @RequestMapping(value = "verifyList")
    @Description(value = "课程核销列表")
    @ResponseBody
    public PageResult<OrderSub> verifyList(PageResult<OrderSub> result, String id, OrderSub orderSub, String userId, String type, HttpServletRequest request, Sort sort, Pagination page,@RequestParam(value = "conditionList",required = false) String conditionStr) {
        orderSub=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        orderSub.setOrder_id(Integer.parseInt(id));

        if (type.equals("1011") || type.equals("1013")){
            List<OrderSub> list = baseService.getLessons(orderSub);
            for(int i=0;i<list.size();i++){
                if (list.get(i).getEnd_time().before(new Timestamp(System.currentTimeMillis()))){
                    list.get(i).setFlag(false);
                }else {
                    list.get(i).setFlag(true);
                }
            }
            result.setData(list);
            page.init(baseService.getLessonsCount(orderSub),page.getPageSize());
            result.setPagination(page);
            result.setMsg(type);
        }else if("1012".equals(type)){
            List<OrderSub> list = baseService.getLesson(orderSub);
            for(int i=0;i<list.size();i++){
                if (list.get(i).getEnd_time().before(new Timestamp(System.currentTimeMillis()))){
                    list.get(i).setFlag(false);
                }else {
                    list.get(i).setFlag(true);
                }
            }
            result.setData(list);
            page.init(baseService.getLessonCount(orderSub),page.getPageSize());
            result.setPagination(page);
        }
        return result;
    }

    @RequestMapping(value = "verify")
    @Description(value = "核销课程")
    @ResponseBody
    public PageResult<OrderSub> verify(Model model, @RequestParam("qrInfo") String qrInfo, PageResult<OrderSub> result,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        JSONObject obj = JSON.parseObject(qrInfo);
        String id = (String) obj.get("id").toString();
        String type = (String) obj.get("type").toString();;
        OrderSub orderSub=baseService.findById(id);
        OrderMain orderMain = orderMainService.findById(orderSub.getOrder_id().toString());
        Integer lessonDone = baseService.getLessonDoneCount(orderMain.getId().intValue());
        Integer courseNum = orderMain.getCourse_num();
        Integer userId = orderMain.getUser_id();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.MINUTE,-60);
        endCalendar.add(Calendar.MINUTE,60);
        Timestamp start = new Timestamp(startCalendar.getTimeInMillis());
        Timestamp end = new Timestamp(endCalendar.getTimeInMillis());
        int sysUserId = user.getField_id().intValue();
        int cardFieldId = orderSub.getCase_field_id();
        try{
            if (sysUserId == cardFieldId){
                if(orderSub.getIs_delay().equals("1")){
                    if (null!=orderSub.getDelay_start_time() && orderSub.getDelay_start_time().before(end) && orderSub.getDelay_end_time().after(start)){
                        orderSub.setOrder_state("3");
                        orderSub.setVerification_user_id(user.getId().intValue());
                        orderSub.setVerification_case_field_id(user.getOrg_id());
                        orderSub.setVerification_time(new Timestamp(System.currentTimeMillis()));
                        baseService.update(orderSub);
                        if (type.equals("1012")){
                            orderMain.setOrder_state("2");
                        }else if(type.equals("1011") || type.equals("1013")){
                            if (lessonDone>=courseNum){
                                orderMain.setOrder_state("2");
                            }
                        }
                        orderMainService.update(orderMain);
                        baseService.update(orderSub);
                        WebSocketController.sendMessageToUser("1","核销成功",WebSorketSessionIdUtil.getSessionId(1,userId));
                        result.setMsg("核销成功");
                    }else {
                        result.setMsg("该课程暂未开课");
                        result.setFlag(false);
                    }
                }else {//if (null != orderSub.getAppointment_start_time() && orderSub.getAppointment_start_time().before(end) && orderSub.getAppointment_end_time().after(start)){
                    if (null != orderSub.getAppointment_start_time() && orderSub.getAppointment_start_time().before(end) && orderSub.getAppointment_end_time().after(start)){
                        orderSub.setOrder_state("3");
                        orderSub.setVerification_user_id(user.getId().intValue());
                        orderSub.setVerification_case_field_id(user.getOrg_id());
                        orderSub.setVerification_time(new Timestamp(System.currentTimeMillis()));
                        baseService.update(orderSub);
                        if (type.equals("1012")){
                            orderMain.setOrder_state("2");
                        }else if(type.equals("1011") || type.equals("1013")){
                            if (lessonDone>=courseNum){
                                orderMain.setOrder_state("2");
                            }
                        }
                        orderMainService.update(orderMain);
                        baseService.update(orderSub);
                        WebSocketController.sendMessageToUser("1","核销成功",WebSorketSessionIdUtil.getSessionId(1,userId));
                        result.setMsg("核销成功");
                    }else {
                        result.setMsg("该课程暂未开课");
                        result.setFlag(false);
                    }
                }
            }else {
                result.setMsg("您的课程不在此案场");
                result.setFlag(false);
            }


        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(false);

        }
        return result;
    }





}
