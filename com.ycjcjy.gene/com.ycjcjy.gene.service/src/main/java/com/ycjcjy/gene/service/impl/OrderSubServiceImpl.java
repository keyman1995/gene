package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.dao.OrderSubDao;
import com.ycjcjy.gene.model.OrderSub;
import com.ycjcjy.gene.service.OrderSubService;
import net.onebean.core.BaseBiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderSubServiceImpl extends BaseBiz<OrderSub, OrderSubDao> implements OrderSubService{
    @Override
    public List<OrderSub> querySiJiaoOrderDetailList(String userId, String teacherId, String base_offset, String base_pageSize) {
        return baseDao.querySiJiaoOrderDetailList(userId, teacherId, base_offset, base_pageSize);
    }

    @Override
    public Integer querySiJiaoOrderDetailListCount(String userId, String teacherId) {
        return baseDao.querySiJiaoOrderDetailListCount(userId, teacherId);
    }

    @Override
    public List<OrderSub> getLessons(OrderSub orderSub){
        return baseDao.getLessons(orderSub);
    }

    @Override
    public Integer getLessonsCount(OrderSub orderSub){
        return baseDao.getLessonsCount(orderSub);
    }

    @Override
    public List<OrderSub> getLesson(OrderSub orderSub){
        return baseDao.getLesson(orderSub);
    }

    @Override
    public Integer getLessonCount(OrderSub orderSub){
        return baseDao.getLessonCount(orderSub);
    }

    @Override
    public List<OrderSub> queryCoursesByTime(String nowHour,String newTime) {
        return baseDao.queryCoursesByTime(nowHour,newTime);
    }

    @Override
    public List<OrderSub> queryUsersByTime(String newTime, String nowHour) {
        return baseDao.queryUsersByTime(newTime,nowHour);
    }

    @Override
    public List<OrderSub> findListBySubId(Integer course_sub_id){
        return baseDao.findListBySubId(course_sub_id);
    }

    @Override
    public Integer getLessonDoneCount(Integer order_id){
        return baseDao.getLessonDoneCount(order_id);
    }

    @Override
    public Map<String,Object> findLessonDoneByNow(String caseid) {
        return baseDao.getLessonDoneByNow(caseid);
    }

    @Override
    public Integer findCheckInByMonth(String case_id){return baseDao.findCheckInByMonth(case_id);}
}