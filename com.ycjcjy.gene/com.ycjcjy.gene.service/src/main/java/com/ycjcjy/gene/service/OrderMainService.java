package com.ycjcjy.gene.service;
import com.ycjcjy.gene.VO.*;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.OrderMain;
import net.onebean.core.IBaseBiz;

import java.util.List;
import java.util.Map;

public interface OrderMainService extends IBaseBiz<OrderMain> {

    double calPrice(Integer courseId,Integer ticketId,Integer courseNum) throws Exception;

    Integer getOrderCountByCourseId(String course_id);
    OrderMain addOrder(Integer courseId,Integer ticketId,Integer courseNum,CustomerUser user) throws Exception;

    /**
     * 支付回调（余额主动调）
     * @param payType  0 余额 1 支付宝 2 微信
     * @param orderNo
     * @param payPrice
     * @param user
     * @return
     */
    Map<String,Object> paySuccess(Integer payType, String orderNo, double payPrice, CustomerUser user);

    OrderMain getOrderByNo(String orderNo);

    void updateOrderStateByTime(String nowTime);

    List<OrderMain> findAllOrderMain(OrderMain orderMain);

    Integer getAllCount(OrderMain orderMain);

    //用户订单列表
    List<OrderMain> queryUserOrderList(String userId,String orderState,String base_offset,String base_pageSize);
    Integer queryUserOrderListCount(String userId,String orderState);

    //我的课程
    List<OrderMain> queryUserCourseOrderList(String userId,String courseType,String base_offset,String base_pageSize);
    Integer queryUserCourseOrderListCount(String userId,String courseType);

    //我的私教
    List<SiJiaoListVo> queryUserSiJiaoOrderList(String userId, String base_offset, String base_pageSize);
    Integer queryUserSiJiaoOrderListCount(String userId);

    //我的团体课
    List<TuanTiListVo> queryUserTuanTiOrderList(String userId, String nowTime, String base_offset, String base_pageSize);
    Integer queryUserTuanTiOrderListCount(String userId);
    //约课详情列表
    List<SiJiaoListVo> querySiJiaoOrderDetailVoList(String userId, String teacherId, String base_offset, String base_pageSize);
    Integer querySiJiaoOrderDetailVoListCount(String userId,String teacherId);
    //私教详情
    SiJiaoListVo querySiJiaoOrderDetailBySiJiaoId(String userId,String teacherId);

    //各种详情
    OrderMain queryUserOrderDetailById( String id);
    OrderMain queryCourseOrderDetailById( String id);
    SiJiaoListVo querySiJiaoOrderDetailById( String id);
    TuanTiListVo queryTuanTiOrderDetailById(String userId, String id,String nowTime);

    //私教端 团体课列表
    List<TuanTiListVo> querySiJiaoDuanTuanTiList(String teacherId,String nowTime, String base_offset,String base_pageSize);
    Integer querySiJiaoDuanTuanTiListCount(String teacherId);

    //私教端 团体课详情
    TuanTiListVo querySiJiaoDuanTuanTiDetailById(String courseId,String nowTime);
    List<TuanTiListVo> querySiJiaoDuanTuanTiDetailCustomerUserList(String courseId);

    //私教端历史记录列表
    List<Map<String,Object>> querySiJiaoDuanTuanRecordList(String teacherId,String nowTime, String base_offset,String base_pageSize);
    Integer querySiJiaoDuanTuanRecordListCount(String teacherId,String nowTime);

    //私教端私教课列表
    List<SiJiaoListVo> getAllCoachLessonForCoach(Integer teacherId,Integer base_offset,Integer base_pageSize);
    Integer getCountAllLessonForCoach(Integer teacherId);

    //详情列表页单卡
    SiJiaoListVo getsingleCoachLessonCustomer(Integer teacherId,Integer user_id);

    //获取私教已预约时间
    List<AppointmentTimeVO> getAppointmentTimeCoach(Integer teacher_id);
    List<BookingTimeVO> showAvailableTime(List<AppointmentTimeVO> timeLists,String str,String orderId);

    //获取目前第几节课
    Integer getCoachIndex(Integer order_id);

    //获取教练课下拉框
    List<CoachClassListVO> getCoachClassName(Integer user_id,Integer teacher_id);

    //约课详情列表
    List<SiJiaoListVo> queryCoachOrderDetailVoList(String userId, String teacherId, String base_offset, String base_pageSize);
    Integer queryCoachOrderDetailVoListCount(String userId,String teacherId);

    //教练上了多少私教课
    List<CoachLessonDoneVO> coachLessonDone();

    int updateOrderStateSiJiaoKeOut(String nowTime);
    int updateOrderStateSiJiaoKeFinish(String nowTime);

    Map<String,Object>findTotalMoney(String caseid);

    Integer findShareLessonByMonth(String case_id);
    /*首页数据折线图*/
    Map<String,Object> queryIndexForTu( Integer dateType,String startTime, String endTime,String caseId);

}