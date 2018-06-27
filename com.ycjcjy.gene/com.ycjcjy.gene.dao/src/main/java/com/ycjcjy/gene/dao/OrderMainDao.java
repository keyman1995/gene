package com.ycjcjy.gene.dao;
import com.ycjcjy.gene.VO.*;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.OrderMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMainDao extends BaseDao<OrderMain> {
    OrderMain getOrderMainByOrderNo(@Param(value = "orderNo")String orderNo);

    int updateOrderState(@Param(value = "orderNo")String orderNo,@Param(value = "orderState")String orderState,@Param(value = "time")String time);

    List<OrderMain> queryOrderByTime(@Param(value = "nowTime")String nowTime);

    List<OrderMain> findAllOrderMain(OrderMain orderMain);

    Integer getAllCount(OrderMain orderMain);
    Integer getOrderCountByCourseId(@Param(value = "course_id")String course_id);

    //用户订单列表
    List<OrderMain> queryUserOrderList(@Param("userId")String userId,@Param("orderState")String orderState,@Param("base_offset")String base_offset,@Param("base_pageSize")String base_pageSize);
    Integer queryUserOrderListCount(@Param("userId")String userId,@Param("orderState")String orderState);

    //我的课程
    List<OrderMain> queryUserCourseOrderList(@Param("userId")String userId,@Param("courseType")String courseType,@Param("base_offset")String base_offset,@Param("base_pageSize")String base_pageSize);
    Integer queryUserCourseOrderListCount(@Param("userId")String userId,@Param("courseType")String courseType);

    //我的私教
    List<SiJiaoListVo> queryUserSiJiaoOrderList(@Param("userId")String userId, @Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);
    Integer queryUserSiJiaoOrderListCount(@Param("userId")String userId);

    //我的团体课
    List<TuanTiListVo> queryUserTuanTiOrderList(@Param("userId")String userId,@Param("nowTime")String nowTime, @Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);
    Integer queryUserTuanTiOrderListCount(@Param("userId")String userId);

    //约课详情列表
    List<SiJiaoListVo> querySiJiaoOrderDetailVoList(@Param("userId")String userId,@Param("teacherId")String teacherId, @Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);
    Integer querySiJiaoOrderDetailVoListCount(@Param("userId")String userId,@Param("teacherId")String teacherId);
    //私教详情
    SiJiaoListVo querySiJiaoOrderDetailBySiJiaoId(@Param("userId")String userId,@Param("teacherId")String teacherId);

    //各种详情页
    OrderMain queryUserOrderDetailById(@Param("id")String id);
    OrderMain queryCourseOrderDetailById(@Param("id")String id);
    SiJiaoListVo querySiJiaoOrderDetailById(@Param("id")String id);
    TuanTiListVo queryTuanTiOrderDetailById(@Param("userId")String userId,@Param("id")String id,@Param("nowTime")String nowTime);

    //私教端 团体课列表
    List<TuanTiListVo> querySiJiaoDuanTuanTiList(@Param("teacherId")String teacherId,@Param("nowTime")String nowTime, @Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);
    Integer querySiJiaoDuanTuanTiListCount(@Param("teacherId")String teacherId);

    //私教端 团体课详情
    TuanTiListVo querySiJiaoDuanTuanTiDetailById(@Param("courseId")String courseId,@Param("nowTime")String nowTime);
    List<TuanTiListVo> querySiJiaoDuanTuanTiDetailCustomerUserList(@Param("courseId")String courseId);

    //私教端历史记录列表
    List<Map<String,Object>> querySiJiaoDuanTuanRecordList(@Param("teacherId")String teacherId,@Param("nowTime")String nowTime, @Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);
    Integer querySiJiaoDuanTuanRecordListCount(@Param("teacherId")String teacherId,@Param("nowTime")String nowTime);

    //私教端私教课列表
    List<SiJiaoListVo> getAllCoachLessonForCoach(@Param("teacherId") Integer teacherId,@Param("base_offset")Integer base_offset, @Param("base_pageSize")Integer base_pageSize);
    Integer getCountAllLessonForCoach(@Param("teacherId") Integer teacherId);

    //详情列表页单卡
    SiJiaoListVo getsingleCoachLessonCustomer(@Param("teacherId")Integer teacherId,@Param("user_id")Integer user_id);

    //获取私教已预约时间
    List<AppointmentTimeVO> getAppointmentTimeCoach(@Param("teacher_id")Integer teacher_id);

    //获取目前第几节课
    Integer getCoachIndex(@Param("order_id")Integer order_id);

    //获取教练课下拉框
    List<CoachClassListVO> getCoachClassName(@Param("user_id") Integer user_id, @Param("teacher_id") Integer teacher_id);

    //约课详情列表
    List<SiJiaoListVo> queryCoachOrderDetailVoList(@Param("userId")String userId,@Param("teacherId")String teacherId, @Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);
    Integer queryCoachOrderDetailVoListCount(@Param("userId")String userId,@Param("teacherId")String teacherId);

    List<CoachLessonDoneVO> coachLessonDone();

    int updateOrderStateSiJiaoKeOut(@Param("nowTime")String nowTime);
    int updateOrderStateSiJiaoKeFinish(@Param("nowTime")String nowTime);

    Map<String,Object> findSumMoney(@Param(value = "caseid")String caseid);

    Integer findShareLessonByMonth(@Param("case_id") String case_id);

    /*首页数据折线图*/
    List<Map<String,Object>> queryOrderCreateNumForTu(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("caseId")String caseId);
    List<Map<String,Object>> queryOrderVerificationNumForTu(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("caseId")String caseId);
    List<Map<String,Object>> queryCourseNumForTu(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("caseId")String caseId);
}