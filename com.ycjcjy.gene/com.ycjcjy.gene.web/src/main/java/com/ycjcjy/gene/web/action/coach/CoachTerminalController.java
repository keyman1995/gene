package com.ycjcjy.gene.web.action.coach;

import com.ycjcjy.gene.VO.*;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.OrderMainService;
import com.ycjcjy.gene.service.OrderSubService;
import com.ycjcjy.gene.service.SysCaseFieldService;
import com.ycjcjy.gene.service.TeacherService;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.util.DateUtils;
import net.onebean.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**私教端
 * Created by szc on 2018/5/15.
 */
@Controller
@RequestMapping("/coachTerminal")
public class CoachTerminalController {
    @Autowired
    private OrderMainService orderMainService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;
    @Autowired
    private OrderSubService orderSubService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/getTeacherInfo")
    @ResponseBody
    public ResponseBean getTeacherInfo(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        try{
            Teacher o =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            responseBean.setSuccess(o);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/siJiaoDuanTuanTiPage")
    @ResponseBody
    public ResponseBean siJiaoDuanTuanTiPage(String base_currentPage, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<TuanTiListVo> list = orderMainService.querySiJiaoDuanTuanTiList(user.getId().toString(), DateUtils.getNowyyyy_MM_dd_HH_mm_ss(),base_offset,base_pageSize);
            int total = orderMainService.querySiJiaoDuanTuanTiListCount(user.getId().toString());
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

    @RequestMapping("/siJiaoDuanTuanRecordListTiPage")
    @ResponseBody
    public ResponseBean siJiaoDuanTuanRecordListTiPage(String base_currentPage, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<Map<String,Object>> list = orderMainService.querySiJiaoDuanTuanRecordList(user.getId().toString(), DateUtils.getNowyyyy_MM_dd_HH_mm_ss(),base_offset,base_pageSize);
            int total = orderMainService.querySiJiaoDuanTuanRecordListCount(user.getId().toString(), DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
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

    @RequestMapping("/siJiaoDuanTuanTiDetail")
    @ResponseBody
    public ResponseBean siJiaoDuanTuanTiDetail(String courseId,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());

            TuanTiListVo detail = orderMainService.querySiJiaoDuanTuanTiDetailById(courseId, DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
            List<TuanTiListVo> list = orderMainService.querySiJiaoDuanTuanTiDetailCustomerUserList(courseId);

            data.put("list",list);

            data.put("detail",detail);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/getAllCoachLessonForCoach")
    @ResponseBody
    public ResponseBean getAllCoachLessonForCoach(String base_currentPage, String base_pageSize,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<SiJiaoListVo> list = orderMainService.getAllCoachLessonForCoach(user.getId().intValue(),Integer.parseInt(base_offset),Integer.parseInt(base_pageSize));
            int total = orderMainService.getCountAllLessonForCoach(user.getId().intValue());
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

    @RequestMapping("/getsingleCoachLessonCustomer")
    @ResponseBody
    public ResponseBean getsingleCoachLessonCustomer(String customerId,HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());

            SiJiaoListVo detail = orderMainService.getsingleCoachLessonCustomer(user.getId().intValue(), Integer.parseInt(customerId));

            data.put("detail",detail);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/getBookingTime")
    @ResponseBody
    public ResponseBean getBookingTime(HttpServletRequest request,String str,String orderId){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            List<AppointmentTimeVO> timeList = orderMainService.getAppointmentTimeCoach(user.getId().intValue());
            List<BookingTimeVO> list = orderMainService.showAvailableTime(timeList,str,orderId);
            data.put("list",list);
            responseBean.setSuccess(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/bookCoach")
    @ResponseBody
    public ResponseBean bookCoach(HttpServletRequest request,String order_id,String start,String end,String fieldId){
        ResponseBean responseBean = new ResponseBean();
        try {
            OrderSub orderSub=new OrderSub();
            orderSub.setOrder_id(Integer.parseInt(order_id));
            orderSub.setOrder_state("2");
            orderSub.setIs_delay(0);
            orderSub.setAppointment_start_time(DateUtils.stringToTimeStamp(start));
            orderSub.setAppointment_end_time(DateUtils.stringToTimeStamp(end));
            orderSub.setCase_field_id(Integer.parseInt(fieldId));
            if (null == orderMainService.getCoachIndex(Integer.parseInt(order_id))){
                orderSub.setSijiao_index(1);
            } else {
                orderSub.setSijiao_index(orderMainService.getCoachIndex(Integer.parseInt(order_id))+1);
            }
            orderSubService.save(orderSub);
            responseBean.setSuccess("success","成功预约");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/getAllCaseField")
    @ResponseBody
    public ResponseBean getAllCaseField(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            Teacher t =  teacherService.findById(user.getId().toString());
            String[] arr = t.getCaseids().split(",");
            List<SysCaseField> sysCaseFields = sysCaseFieldService.findByCaseids(arr);
            responseBean.setSuccess(sysCaseFields,"请求成功");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/getCoachClassName")
    @ResponseBody
    public ResponseBean getCoachClassName(HttpServletRequest request,String customerId){
        ResponseBean responseBean = new ResponseBean();
        try {
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            List<CoachClassListVO> list = orderMainService.getCoachClassName(Integer.parseInt(customerId),user.getId().intValue());
            responseBean.setSuccess(list,"请求成功");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError("请求错误");
        }

        return responseBean;
    }

    @RequestMapping("/querySiJiaoOrderDetailVoList")
    @ResponseBody
    public ResponseBean querySiJiaoOrderDetailVoList(String base_currentPage, String base_pageSize,HttpServletRequest request,String customerId){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            Teacher user =  (Teacher)request.getSession().getAttribute(BaseConstans.SESSION_TEACHER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }

            List<SiJiaoListVo> list = orderMainService.queryCoachOrderDetailVoList(customerId,user.getId().toString(),base_offset,base_pageSize);
            int total = orderMainService.queryCoachOrderDetailVoListCount(customerId,user.getId().toString());
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

    @RequestMapping("/cancelBooking")
    @ResponseBody
    public ResponseBean cancelBooking(String orderId){
        ResponseBean responseBean = new ResponseBean();
        try {
            OrderSub orderSub=orderSubService.findById(orderId);
            orderSub.setOrder_state("4");
            orderSubService.update(orderSub);
            responseBean.setSuccess("success","取消成功");
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError();
        }
        return responseBean;
    }





}
