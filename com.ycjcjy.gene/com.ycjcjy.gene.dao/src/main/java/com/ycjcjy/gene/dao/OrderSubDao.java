package com.ycjcjy.gene.dao;
import com.ycjcjy.gene.model.OrderSub;
import net.onebean.core.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderSubDao extends BaseDao<OrderSub> {

    List<OrderSub> querySiJiaoOrderDetailList(@Param("userId")String userId,@Param("teacherId")String teacherId, @Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);

    Integer querySiJiaoOrderDetailListCount(@Param("userId")String userId,@Param("teacherId")String teacherId);

    List<OrderSub> getLessons(OrderSub orderSub);

    Integer getLessonsCount(OrderSub orderSub);

    List<OrderSub> getLesson(OrderSub orderSub);

    Integer getLessonCount(OrderSub orderSub);

    List<OrderSub> queryCoursesByTime(@Param(value ="nowHour")String nowHour,@Param(value ="newTime")String newTime);

    List<OrderSub> queryUsersByTime(@Param(value ="newTime")String newTime, @Param(value ="nowHour")String nowHour);

    List<OrderSub> findListBySubId(@Param(value = "course_sub_id")Integer course_sub_id);

    Integer getLessonDoneCount(@Param(value = "order_id")Integer order_id);

    Map<String,Object> getLessonDoneByNow(@Param(value = "caseid")String caseid);

    Integer findCheckInByMonth(@Param("case_id")String case_id);
}