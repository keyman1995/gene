package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.OrderSub;
import net.onebean.core.IBaseBiz;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderSubService extends IBaseBiz<OrderSub> {
    List<OrderSub> querySiJiaoOrderDetailList(String userId, String teacherId,String base_offset,String base_pageSize);

    Integer querySiJiaoOrderDetailListCount(String userId,String teacherId);

    List<OrderSub> getLessons(OrderSub orderSub);

    Integer getLessonsCount(OrderSub orderSub);

    List<OrderSub> getLesson(OrderSub orderSub);

    Integer getLessonCount(OrderSub orderSub);

    List<OrderSub> queryCoursesByTime(String nowHour,String newTime);

    List<OrderSub> queryUsersByTime(String newTime, String nowHour);

    List<OrderSub> findListBySubId(Integer course_sub_id);

    Integer getLessonDoneCount(Integer order_id);

    Map<String,Object> findLessonDoneByNow(String caseid);
    Integer findCheckInByMonth(String case_id);

}