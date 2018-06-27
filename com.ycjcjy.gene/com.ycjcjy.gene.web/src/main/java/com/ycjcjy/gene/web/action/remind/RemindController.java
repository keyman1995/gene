package com.ycjcjy.gene.web.action.remind;

import com.ycjcjy.gene.VO.MonthOrderVO;
import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.*;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
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
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/remind")
public class RemindController {

    @Autowired
    private OrderSubService orderSubService;

    @Autowired
    private CardUserRelaService cardUserRelaService;

    @Autowired
    private RemindService remindService;

    @Autowired
    private GoodsOrderSubService goodsOrderSubService;

    @Autowired
    SysUserService sysUserService;

    private static final Logger logger = LoggerFactory.getLogger(RemindController.class);

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResponseBean getUserInfo(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

        try{
            HttpSession session = request.getSession();
            CustomerUser customerUser = (CustomerUser) session.getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            if(customerUser == null){
                responseBean.setError(1,"用户登录失效，请重新登录");
            }else{
                data.put("icon",customerUser.getIcon());
                data.put("user_id",customerUser.getId());
                data.put("username",customerUser.getUsername());
                data.put("tel",customerUser.getTel());
                responseBean.setSuccess(data,"请求成功");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }

    @Scheduled(cron = "0 0 */1 * * ?")
//    @Scheduled(cron = "0 09 21 ? * *")
    public void courseRemindTimer(){


        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            Calendar nowTime = Calendar.getInstance();
            String create_time = sdf.format(nowTime.getTime());

            nowTime.add(Calendar.HOUR_OF_DAY, 1);

            String nowHour = sdf.format(nowTime.getTime());
            nowTime.add(Calendar.HOUR_OF_DAY, 1);
            logger.info(sdf.format(nowTime.getTime()));
            String newTime = sdf.format(nowTime.getTime());
            List<OrderSub> courses = orderSubService.queryCoursesByTime(nowHour,newTime);
            if(courses.size() != 0){
                for(OrderSub course : courses){
                    Remind remind = new Remind();
                    remind.setType(0);
                    remind.setCreate_time(java.sql.Timestamp.valueOf(create_time));
                    remind.setUser_id(course.getUser_id());
//                    if(course.getCourse_sub_id() != null && course.getCoursename() != ""){
//                        remind.setCoursename(course.getCourseName());
//                    }else{
//                        remind.setCoursename(course.getCourseName());
//                    }
                    remind.setCoursename(course.getCourseName());
                    if(course.getIs_delay() == 0){
                        remind.setCoursestarttime(sdf2.format(course.getAppointment_start_time()));
                    }else{
                        remind.setCoursestarttime(sdf2.format(course.getDelay_start_time()));
                    }
                    remindService.save(remind);
                }


            }
        }catch (Exception e){
            logger.error("每小时变更课程提醒出现错误!");
            e.printStackTrace();
        }


    }

        @Scheduled(cron = "0 0 8 * * ?")
    public void xiTongRemindTimer(){


        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            Calendar nowTime = Calendar.getInstance();

            String nowHour = sdf.format(nowTime.getTime());

            nowTime.add(Calendar.DAY_OF_YEAR, 1);
            logger.info(sdf.format(nowTime.getTime()));
            String newTime = sdf.format(nowTime.getTime());
            List<CardUserRela> cards = cardUserRelaService.queryCardsByTime(nowHour,newTime);
            if(cards.size() != 0){
                for(CardUserRela card : cards){
                    Remind remind = new Remind();
                    remind.setCreate_time(java.sql.Timestamp.valueOf(nowHour));
                    remind.setType(1);
                    remind.setUser_id(card.getUser_id());
                    remind.setCardcount(card.getCardcount());
                    remindService.save(remind);
                }
            }
        }catch (Exception e){
            logger.error("每天变更系统通知出现错误!");
            e.printStackTrace();
        }

    }
    @Scheduled(cron = "0 0/5 * * * ?")
    public void MonthlyOrderReminder(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = sdf.format(new Date());
            List<SysUser>managers = sysUserService.findMangers();
            for(int i =0;i<managers.size();i++){
                MonthOrderVO monthly=goodsOrderSubService.getThisMonthlyOrder(managers.get(i).getField_id().intValue());
                if (monthly.getUsed()>=(monthly.getTotal()*0.5) && managers.get(i).getMsg_state()==0){
                    SmsUtil.drinkUsePercent(managers.get(i).getMobile(),now,50,monthly.getUsed(),monthly.getTotal());
                    managers.get(i).setMsg_state(1);
                }else if(monthly.getUsed()>=(monthly.getTotal()*0.6) && managers.get(i).getMsg_state()==1){
                    SmsUtil.drinkUsePercent(managers.get(i).getMobile(),now,60,monthly.getUsed(),monthly.getTotal());
                    managers.get(i).setMsg_state(2);
                }else if(monthly.getUsed()>=(monthly.getTotal()*0.7) && managers.get(i).getMsg_state()==2){
                    SmsUtil.drinkUsePercent(managers.get(i).getMobile(),now,70,monthly.getUsed(),monthly.getTotal());
                    managers.get(i).setMsg_state(3);
                }else if(monthly.getUsed()>=(monthly.getTotal()*0.8) && managers.get(i).getMsg_state()==3){
                    SmsUtil.drinkUsePercent(managers.get(i).getMobile(),now,80,monthly.getUsed(),monthly.getTotal());
                    managers.get(i).setMsg_state(4);
                }else if(monthly.getUsed()>=(monthly.getTotal()*0.9) && managers.get(i).getMsg_state()==4){
                    SmsUtil.drinkUsePercent(managers.get(i).getMobile(),now,90,monthly.getUsed(),monthly.getTotal());
                    managers.get(i).setMsg_state(5);
                }else if(monthly.getUsed()>=(monthly.getTotal()) && managers.get(i).getMsg_state()==5){
                    SmsUtil.drinkUsePercent(managers.get(i).getMobile(),now,100,monthly.getUsed(),monthly.getTotal());
                    managers.get(i).setMsg_state(6);
                }
                sysUserService.update(managers.get(i));
            }

        }catch (Exception e){
            logger.error("商品体验卡使用率发送提醒失败。");
        }
    }


        @Scheduled(cron = "0 0 20 * * ?")
        public void courseOverdueTimer(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar nowTime = Calendar.getInstance();
            String nowHour = sdf.format(nowTime.getTime());
            nowTime.add(Calendar.DAY_OF_YEAR, -1);
            logger.info(sdf.format(nowTime.getTime()));
            String newTime = sdf.format(nowTime.getTime());

            List<OrderSub> users = orderSubService.queryUsersByTime(newTime,nowHour);
            if(users.size() != 0){
                for(OrderSub user : users){
                    SmsUtil.sendKuangke(user.getTel());
                }
            }

        }catch (Exception e){
            logger.error("每天短信通知课程过期出现错误!");
            e.printStackTrace();
        }

        }

        @RequestMapping("/remind")
        @ResponseBody
        public ResponseBean remind(HttpServletRequest request,String type,String base_currentPage,String base_pageSize){
            ResponseBean responseBean = new ResponseBean();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);
            Map<String,Object> data = new HashedMap();
            List notice = new ArrayList();
            try{
                Map<String,Object> user = (Map<String,Object>)getUserInfo(request).getData();
                String base_offset = "0";
                if(StringUtils.isBlank(base_pageSize)){
                    base_pageSize = "10";
                }

                if(StringUtils.isNotBlank(base_currentPage)){
                    base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
                }

                Remind remind = new Remind();
                remind.setType(Integer.valueOf(type));
                remind.setUser_id(Integer.parseInt(String.valueOf(user.get("user_id"))));
                remind.setBase_offset(Integer.valueOf(base_offset));
                remind.setBase_pageSize(Integer.valueOf(base_pageSize));

                List<Remind> reminds = remindService.findReminds(remind);
                int total = remindService.findCounts(remind);
                int totalPage = responseBean.init(total,Integer.valueOf(base_pageSize));
                data.put("total",total);
                data.put("totalPage",totalPage);
                if(reminds.size() != 0){
                    for(Remind remind1 : reminds){
                        Map<String,Object> entry = new HashedMap();
                        if(("0").equals(type)){
                            entry.put("title","您有一节"+remind1.getCoursename()+"课将在"+remind1.getCoursestarttime()+"开始，请按时前往。");
                            entry.put("remindTime",sdf1.format(remind1.getCreate_time())+"，"+sdf2.format(remind1.getCreate_time()).substring(sdf2.format(remind1.getCreate_time()).indexOf(":")-2,sdf2.format(remind1.getCreate_time()).lastIndexOf(":"))+" "+sdf2.format(remind1.getCreate_time()).substring(remind1.getCreate_time().toString().length()-2));
                        }else{
                            entry.put("title","你有"+remind1.getCardcount()+"张卡券快到期，请尽快使用哦！");
                            entry.put("remindTime",sdf1.format(remind1.getCreate_time())+"，"+sdf2.format(remind1.getCreate_time()).substring(sdf2.format(remind1.getCreate_time()).indexOf(":")-2,sdf2.format(remind1.getCreate_time()).lastIndexOf(":"))+" "+sdf2.format(remind1.getCreate_time()).substring(remind1.getCreate_time().toString().length()-2));
//                            entry.put("remindTime",sdf1.format(remind1.getCreate_time())+"，"+sdf2.format(remind1.getCreate_time())+" "+remind1.getCreate_time().toString().substring(remind1.getCreate_time().toString().length()-2));
                        }
                        notice.add(entry);
                    }
                }
                data.put("notice",notice);
                responseBean.setSuccess(data,"请求成功");
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                responseBean.setError();
            }


            return  responseBean;
        }

}
