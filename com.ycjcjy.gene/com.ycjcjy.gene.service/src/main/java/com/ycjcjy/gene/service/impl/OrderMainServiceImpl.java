package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.VO.*;
import com.ycjcjy.gene.common.CDateUtil;
import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.common.util.NumberArithmeticUtils;
import com.ycjcjy.gene.dao.*;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.CustomerSpendLogService;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.service.OrderMainService;
import com.ycjcjy.gene.service.OrderSubService;
import net.onebean.core.BaseBiz;
import net.onebean.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderMainServiceImpl extends BaseBiz<OrderMain, OrderMainDao> implements OrderMainService{


    private static final Log log = LogFactory.getLog(OrderMainServiceImpl.class);

    @Autowired
    CourseManagerDao courseManagerDao;
    @Autowired
    SubCourseManagerDao subCourseManagerDao;
    @Autowired
    CardTicketSalveDao cardTicketSalveDao;
    @Autowired
    CardUserRelaDao cardUserRelaDao;
    @Autowired
    CardTicketMasterDao cardTicketMasterDao;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    CustomerUserService customerUserService;
    @Autowired
    CustomerSpendLogService customerSpendLogService;
    @Autowired
    OrderMainService orderMainService;

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public double calPrice(Integer courseId,Integer ticketId,Integer courseNum) throws Exception{
        //获取课程
        CourseManager courseManager =  courseManagerDao.findById(courseId.toString());
        //判断课程是否需要课时数
        double price = 0d;
        if(courseManager.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_SJ.getCode()))){
            price = courseManager.getCourseprice()*courseNum;
        }else{
            price = courseManager.getCourseprice();
        }
        //获取优惠券
        if(ticketId!=null){

            CardUserRela rela =  cardUserRelaDao.findById(ticketId.toString());

            if(rela!=null&&rela.getState()==0){

                if(rela.getStart_time().getTime()<System.currentTimeMillis()&&
                            rela.getEnd_time().getTime()>System.currentTimeMillis()
                            ){
                        price =0d;
                    }
            }


        }
        return price;
    }

    @Override
    public Integer getOrderCountByCourseId(String course_id) {
        return baseDao.getOrderCountByCourseId(course_id);
    }

    @Override
    public OrderMain addOrder(Integer courseId, Integer ticketId, Integer courseNum, CustomerUser user) throws Exception{
        //获取课程
        CourseManager courseManager =  courseManagerDao.findById(courseId.toString());


        double price = this.calPrice(courseId,ticketId,courseNum);//实际价格
        double ogPrice = price;//原价
        if(ticketId!=null){
            ogPrice = this.calPrice(courseId,null,courseNum);//原价
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        OrderMain orderMain = new OrderMain();
        orderMain.setActual_price(price);
        orderMain.setOrgin_price(ogPrice);
        orderMain.setTicket_id(ticketId);
        orderMain.setCourse_id(courseId);
        orderMain.setCourse_type(Integer.valueOf(courseManager.getSpecialclass()));
        orderMain.setCreate_time(timestamp);
        orderMain.setDiscount_price(NumberArithmeticUtils.sub(ogPrice,price).doubleValue());
        orderMain.setOrder_no(createOrderNo());
        orderMain.setOrder_state(OrderStateEnum.ORDERMAIN_DZF.getCode());
        orderMain.setUser_id(Integer.valueOf(user.getId().toString()));
        orderMain.setCase_field_id(courseManager.getCaseid());
        if(courseManager.getSpecialclass().equals(CourseEnum.COURSE_SJ.getCode().toString())) {//私教，不需要生成子订单
            orderMain.setCourse_num(courseNum);
        }else{


            orderMain.setCourse_num(courseManager.getCoursecount());
        }

        orderMain.setOrder_type(0);
        baseDao.add(orderMain);
        log.info("------order insert success----"+orderMain.getOrder_no());
        List<OrderSub> orderSubList = new ArrayList<>();
        // 800讲座 801预约课程 802系列课程 803 私教课程 804团体课程
        if(courseManager.getSpecialclass().equals(CourseEnum.COURSE_SJ.getCode().toString())){//私教，不需要生成子订单

        }else if(courseManager.getSpecialclass().equals(CourseEnum.COURSE_YY.getCode().toString())){//预约，需要生成一个子订单
            OrderSub sub = new OrderSub();
            sub.setOrder_id(Integer.valueOf(orderMain.getId().toString()));
            sub.setAppointment_start_time(new Timestamp(courseManager.getCoursestartline().getTime()));
            sub.setAppointment_end_time(new Timestamp(courseManager.getCourseendline().getTime()));
            sub.setCase_field_id(courseManager.getCaseid());
            sub.setDiscount_price(orderMain.getDiscount_price());
            sub.setOrder_state(OrderStateEnum.ORDERSUB_YSC.getCode());
            sub.setIs_delay(0);
            orderSubList.add(sub);
        }else{// 800讲座   802系列课程   804团体课程
            List<SubCourseManager> courseSubManagerList = subCourseManagerDao.findSubCourses(null,courseManager.getId());
            if(courseSubManagerList!=null){
                double countPrice = 0d;
                //所有子课添加进
                for(int i=0;i<courseSubManagerList.size();i++){
                    SubCourseManager subCourseManager = courseSubManagerList.get(i);

                    OrderSub sub = new OrderSub();
                    sub.setOrder_id(Integer.valueOf(orderMain.getId().toString()));
                    sub.setAppointment_start_time(subCourseManager.getSubstartline());
                    sub.setAppointment_end_time(subCourseManager.getSubendline());
                    sub.setCase_field_id(courseManager.getCaseid());
                    sub.setCourse_sub_id(Integer.valueOf(subCourseManager.getId().toString()));
                    sub.setIs_delay(0);
                    if(i<courseSubManagerList.size()-1){
                        sub.setDiscount_price(NumberArithmeticUtils.div(orderMain.getDiscount_price(),Double.valueOf(courseSubManagerList.size())).doubleValue());
                    }else{
                        sub.setDiscount_price(NumberArithmeticUtils.mul(orderMain.getDiscount_price(),countPrice).doubleValue());
                    }

                    NumberArithmeticUtils.add(sub.getDiscount_price(),countPrice);
                    sub.setOrder_state(OrderStateEnum.ORDERSUB_YSC.getCode());
                    orderSubList.add(sub);
                }
            }

        }

        log.info("------ordersub ready to insert----"+orderSubList.toString());
        orderSubService.saveBatch(orderSubList);
        log.info("------ordersub insert success----"+orderSubList.toString());


        //使用体验券
        if(ticketId!=null){
            log.info("------use CardTicketSalve----"+ticketId);
            CardUserRela rela =  cardUserRelaDao.findById(ticketId.toString());
            rela.setState(1);
            cardUserRelaDao.update(rela);

            log.info("------update CardTicketSalve success----"+ticketId);
        }

        return orderMain;
    }




    public String createOrderNo(){
        SimpleDateFormat sdfNo = new SimpleDateFormat("yyyyMMdd");
        String orderNo = "";
        Date date = new Date();
        orderNo  = "m"+sdfNo.format(date)+System.currentTimeMillis();
        return orderNo;
    }

    @Override
    public Map<String,Object> paySuccess(Integer payType, String orderNo, double payPrice, CustomerUser user) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("flag",0);
        resultMap.put("code",1);
        resultMap.put("message","");
        resultMap.put("orderMain",null);


        OrderMain orderMain = baseDao.getOrderMainByOrderNo(orderNo);
        log.info("------query order success----"+orderMain.getOrder_no());
        if(orderMain==null){
            resultMap.put("flag",0);
            resultMap.put("code",0);
            resultMap.put("message","订单不存在");
            resultMap.put("orderMain",null);
            return resultMap;
        }

        if(!orderMain.getOrder_state().equals(OrderStateEnum.ORDERMAIN_DZF.getCode())){
            resultMap.put("flag",1);
            resultMap.put("code",0);
            resultMap.put("message","订单已支付");
            resultMap.put("orderMain",orderMain);
            return resultMap;
        }

        //支付宝、微信回调需要验证金额是否正确
        if(payType!=0&&payPrice!=orderMain.getActual_price()){
            resultMap.put("flag",2);
            resultMap.put("code",0);
            resultMap.put("message","支付金额错误");
            resultMap.put("orderMain",orderMain);
            return resultMap;
        }

        //验证成功，判断支付方式 payType  0 余额 1 支付宝 2 微信

            //验证人员钱是否够

        user = customerUserService.findById(user.getId().toString());
        double allMoney = NumberArithmeticUtils.add(user.getActual_balance(),user.getGift_balance()).doubleValue();
        double price = orderMain.getActual_price();

        if(price>allMoney){
            resultMap.put("flag",3);
            resultMap.put("code",0);
            resultMap.put("message","请先到案场领取优惠券");
            resultMap.put("orderMain",orderMain);
            return resultMap;
        }



        int cut = customerUserService.cutMoney(orderMain.getActual_price(),user.getId());
        log.info("------cut money success----"+orderMain.getOrder_no());

        CourseManager courseManager = courseManagerDao.findById(orderMain.getCourse_id().toString());
        //修改订单状态

        baseDao.updateOrderState(orderMain.getOrder_no(),OrderStateEnum.ORDERMAIN_YZF.getCode(),sdf.format(new Date()));
        log.info("------update order state success----"+orderMain.getOrder_no());

        //发送通知
        SmsUtil.sendOrderSuccess(user.getTel(),courseManager.getCoursename());
        log.info("------send sms success----"+user.getTel()+"***"+courseManager.getCoursename());

        //新增一条消费记录
        CustomerSpendLog log = new CustomerSpendLog();
        log.setType(1);
        log.setPrice(orderMain.getActual_price());
        log.setCreate_time(new Timestamp(System.currentTimeMillis()));
        log.setQuantity("1");
        log.setGoods_id(orderMain.getId().toString());

        log.setCasefield_id(courseManager.getCaseid());
        log.setCustomer_id(orderMain.getUser_id());

        customerSpendLogService.save(log);

        resultMap.put("orderMain",orderMain);

        return resultMap;
    }

    @Override
    public OrderMain getOrderByNo(String orderNo) {

        return baseDao.getOrderMainByOrderNo(orderNo);
    }

    @Override
    public void updateOrderStateByTime(String nowTime) {
        List<OrderMain> orderMainList = baseDao.queryOrderByTime(nowTime);
        for (OrderMain orderMain:orderMainList){
            baseDao.updateOrderState(orderMain.getOrder_no(),OrderStateEnum.ORDERMAIN_YGB.getCode(),sdf.format(new Date()));

            Integer ticketId = orderMain.getTicket_id();
            if(ticketId!=null){
                CardUserRela rela =  cardUserRelaDao.findById(ticketId.toString());

                if(rela.getStart_time().getTime()<System.currentTimeMillis()&&
                        rela.getEnd_time().getTime()>System.currentTimeMillis()
                        ){
                    rela.setState(0);
                }else{
                    rela.setState(2);
                }
                cardUserRelaDao.update(rela);

            }

        }
    }

    @Override
    public List<OrderMain> findAllOrderMain(OrderMain orderMain) {
        return baseDao.findAllOrderMain(orderMain);
    }

    @Override
    public Integer getAllCount(OrderMain orderMain) {
        return baseDao.getAllCount(orderMain);
    }

    @Override
    public List<OrderMain> queryUserOrderList(String userId,String orderState, String base_offset, String base_pageSize) {
        return baseDao.queryUserOrderList(userId,orderState, base_offset, base_pageSize);
    }

    @Override
    public Integer queryUserOrderListCount(String userId,String orderState) {
        return baseDao.queryUserOrderListCount(userId,orderState);
    }

    @Override
    public List<OrderMain> queryUserCourseOrderList(String userId, String courseType, String base_offset, String base_pageSize) {
        return baseDao.queryUserCourseOrderList(userId, courseType, base_offset, base_pageSize);
    }

    @Override
    public Integer queryUserCourseOrderListCount(String userId, String courseType) {
        return baseDao.queryUserCourseOrderListCount(userId, courseType);
    }

    @Override
    public List<SiJiaoListVo> queryUserSiJiaoOrderList(String userId, String base_offset, String base_pageSize) {
        return baseDao.queryUserSiJiaoOrderList(userId, base_offset, base_pageSize);
    }

    @Override
    public Integer queryUserSiJiaoOrderListCount(String userId) {
        return baseDao.queryUserSiJiaoOrderListCount(userId);
    }

    @Override
    public List<TuanTiListVo> queryUserTuanTiOrderList(String userId, String nowTime, String base_offset, String base_pageSize) {
        return baseDao.queryUserTuanTiOrderList(userId, nowTime, base_offset, base_pageSize);
    }

    @Override
    public Integer queryUserTuanTiOrderListCount(String userId) {
        return baseDao.queryUserTuanTiOrderListCount(userId);
    }

    @Override
    public SiJiaoListVo querySiJiaoOrderDetailBySiJiaoId(String userId, String teacherId) {
        return baseDao.querySiJiaoOrderDetailBySiJiaoId(userId, teacherId);
    }

    @Override
    public OrderMain queryUserOrderDetailById(String id) {
        return baseDao.queryUserOrderDetailById(id);
    }

    @Override
    public OrderMain queryCourseOrderDetailById(String id) {
        return baseDao.queryCourseOrderDetailById(id);
    }

    @Override
    public SiJiaoListVo querySiJiaoOrderDetailById(String id) {
        return baseDao.querySiJiaoOrderDetailById(id);
    }

    @Override
    public TuanTiListVo queryTuanTiOrderDetailById(String userId, String id, String nowTime) {
        return baseDao.queryTuanTiOrderDetailById(userId, id, nowTime);
    }

    @Override
    public List<SiJiaoListVo> querySiJiaoOrderDetailVoList(String userId, String teacherId, String base_offset, String base_pageSize) {
        return baseDao.querySiJiaoOrderDetailVoList(userId, teacherId, base_offset, base_pageSize);
    }

    @Override
    public Integer querySiJiaoOrderDetailVoListCount(String userId, String teacherId) {
        return baseDao.querySiJiaoOrderDetailVoListCount(userId, teacherId);
    }

    @Override
    public List<TuanTiListVo> querySiJiaoDuanTuanTiList(String teacherId, String nowTime, String base_offset, String base_pageSize) {
        return baseDao.querySiJiaoDuanTuanTiList(teacherId, nowTime, base_offset, base_pageSize);
    }

    @Override
    public Integer querySiJiaoDuanTuanTiListCount(String teacherId) {
        return baseDao.querySiJiaoDuanTuanTiListCount(teacherId);
    }

    @Override
    public TuanTiListVo querySiJiaoDuanTuanTiDetailById(String courseId, String nowTime) {
        return baseDao.querySiJiaoDuanTuanTiDetailById(courseId, nowTime);
    }

    @Override
    public List<TuanTiListVo> querySiJiaoDuanTuanTiDetailCustomerUserList(String courseId) {
        return baseDao.querySiJiaoDuanTuanTiDetailCustomerUserList(courseId);
    }

    @Override
    public List<Map<String,Object>> querySiJiaoDuanTuanRecordList(String teacherId, String nowTime, String base_offset, String base_pageSize) {
        return baseDao.querySiJiaoDuanTuanRecordList(teacherId, nowTime, base_offset, base_pageSize);
    }

    @Override
    public Integer querySiJiaoDuanTuanRecordListCount(String teacherId, String nowTime) {
        return baseDao.querySiJiaoDuanTuanRecordListCount(teacherId, nowTime);
    }

    @Override
    public List<SiJiaoListVo> getAllCoachLessonForCoach(Integer teacherId,Integer base_offset,Integer base_pageSize){return baseDao.getAllCoachLessonForCoach(teacherId,base_offset,base_pageSize);}

    @Override
    public Integer getCountAllLessonForCoach(Integer teacherId){return baseDao.getCountAllLessonForCoach(teacherId);}

    @Override
    public SiJiaoListVo getsingleCoachLessonCustomer(Integer teacherId,Integer user_id){return baseDao.getsingleCoachLessonCustomer(teacherId,user_id);}

    @Override
    public List<AppointmentTimeVO> getAppointmentTimeCoach(Integer teacher_id){return baseDao.getAppointmentTimeCoach(teacher_id);}

    @Override
    public List<BookingTimeVO> showAvailableTime(List<AppointmentTimeVO> timeLists,String str,String orderId){
        List<BookingTimeVO>list = new ArrayList<>();
        List<Timestamp>start = new ArrayList<>();
        List<Timestamp> end=new ArrayList<>();
        OrderMain orderMain = orderMainService.findById(orderId);
        CourseManager courseManager = courseManagerDao.findById(orderMain.getCourse_id().toString());
        Timestamp courseStart = courseManager.getCoursestartline();
        Timestamp courseEnd = courseManager.getCourseendline();
        Timestamp nowtims = new Timestamp(System.currentTimeMillis());
        for(int i =0;i<timeLists.size();i++){
            start.add(timeLists.get(i).getAppointment_start_time());
            end.add(timeLists.get(i).getAppointment_end_time());
        }
        String timeList = "09:00,09:30,10:00,10:30,11:00,11:30,12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00";
        SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String nowlesson = hms.format(date);
        String nows = str+nowlesson;
        SimpleDateFormat hm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] ls = timeList.split(",");
        Timestamp nowTime = DateUtils.stringToTimeStamp(nows);
        for(int i = 0; i<ls.length;i++){
            BookingTimeVO bookingTimeVO=new BookingTimeVO();
            bookingTimeVO.setTime(ls[i]);
            String tims = str+ls[i];
            try{
                Date time = hm.parse(tims);
                Timestamp timestamp = new Timestamp(time.getTime());
                bookingTimeVO.setTimes(timestamp);
            }catch (Exception e){
                e.printStackTrace();
            }
            list.add(bookingTimeVO);
        }
        for (int i =0;i<start.size();i++){
            for(int j = 0;j<list.size();j++){
                if (start.get(i).after(list.get(j).getTimes()) && end.get(i).before(list.get(j).getTimes())){
                    list.get(j).setFlag("1");
                }else {
                    list.get(j).setFlag("0");
                }
            }
        }
        for (int i =0;i<list.size();i++){
            if (nowtims.after(list.get(i).getTimes())){
                list.get(i).setFlag("1");
            }else if(null!=courseStart && null!= courseEnd){
                if (courseStart.after(list.get(i).getTimes())){
                    list.get(i).setFlag("1");
                }else if(courseEnd.before(list.get(i).getTimes())){
                    list.get(i).setFlag("1");
                }

            }
        }
        return list;
    }
    @Override
    public Integer getCoachIndex(Integer order_id){
        return baseDao.getCoachIndex(order_id);
    }

    @Override
    public List<CoachClassListVO> getCoachClassName(Integer user_id,Integer teacher_id){
        return baseDao.getCoachClassName(user_id,teacher_id);
    }

    @Override
    public List<SiJiaoListVo> queryCoachOrderDetailVoList(String userId, String teacherId, String base_offset, String base_pageSize) {
        return baseDao.queryCoachOrderDetailVoList(userId, teacherId, base_offset, base_pageSize);
    }

    @Override
    public Integer queryCoachOrderDetailVoListCount(String userId, String teacherId) {
        return baseDao.queryCoachOrderDetailVoListCount(userId, teacherId);
    }

    @Override
    public List<CoachLessonDoneVO> coachLessonDone(){
        return baseDao.coachLessonDone();
    }

    @Override
    public int updateOrderStateSiJiaoKeOut(String nowTime) {
        return baseDao.updateOrderStateSiJiaoKeOut(nowTime);
    }

    @Override
    public int updateOrderStateSiJiaoKeFinish(String nowTime) {
        return baseDao.updateOrderStateSiJiaoKeFinish(nowTime);
    }
    @Override
    public Map<String, Object> findTotalMoney(String caseid) {
        return baseDao.findSumMoney(caseid);
    }
    @Override
    public Map<String, Object> queryIndexForTu(Integer dateType,String startTime, String endTime,String caseId) {
        SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
        if(dateType==0){
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.DATE,-1);

            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            Date s=cal.getTime();


            Date e=new Date();
            startTime = sdfSimple.format(s);
            endTime = sdfSimple.format(e);

        }else if(dateType==1){
            HashMap<String, String> map =  CDateUtil.getWeekByDate();
            startTime = map.get("Monday");
            endTime = map.get("SunDay");

        }else if(dateType==2){
            HashMap<String, String> map =  CDateUtil.getTimesMonth();
            startTime = map.get("Months");
            endTime = map.get("Monthe");

        }


        Map<String, Object> result = new HashMap<>();
        result.put("startTime",startTime);
        result.put("endTime",endTime);


        List<Map<String,Object>> list1 = baseDao.queryCourseNumForTu(startTime, endTime,caseId);
        List<Map<String,Object>> list2 = baseDao.queryOrderVerificationNumForTu(startTime, endTime,caseId);
        List<Map<String,Object>> list3 = baseDao.queryOrderCreateNumForTu(startTime, endTime,caseId);

        List<String> timeList = new ArrayList<>();
        List<Integer> cnList = new ArrayList<>();
        List<Integer> ovList = new ArrayList<>();
        List<Integer> ocList = new ArrayList<>();


        try{
            for(Map<String,Object> m:list1){
                timeList.add(m.get("date").toString());
                cnList.add(Integer.valueOf(m.get("number").toString()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for(Map<String,Object> m:list2){
            ovList.add(Integer.valueOf(m.get("number").toString()));
        }
        for(Map<String,Object> m:list3){
            ocList.add(Integer.valueOf(m.get("number").toString()));
        }

        result.put("timeList",timeList);
        result.put("cnList",cnList);
        result.put("ovList",ovList);
        result.put("ocList",ocList);


        return result;
    }

    @Override
    public Integer findShareLessonByMonth(String case_id){
        return baseDao.findShareLessonByMonth(case_id);
    }
}
