package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.dao.CourseManagerDao;
import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.CourseManagerVo;
import com.ycjcjy.gene.service.CourseManagerService;
import net.onebean.core.BaseBiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseManagerServiceImpl extends BaseBiz<CourseManager, CourseManagerDao> implements CourseManagerService{

    @Override
    public List<CourseManager> findCourses(CourseManager courseManager) {
        return baseDao.findAllCourse(courseManager);
    }

    @Override
    public CourseManager findByCourseId(String id) {
        return baseDao.findByCourseId(id);
    }

    @Override
    public List<CourseManager> findYouXiaoCourse(CourseManager courseManager) {
        return baseDao.findYouXiaoCourse(courseManager);
    }

    @Override
    public Integer findYouXiaoCount(CourseManager courseManager) {
        return baseDao.findYouXiaoCount(courseManager);
    }

    @Override
    public Integer getAllCount(CourseManager courseManager) {
        return baseDao.findCount(courseManager);
    }

    @Override
    public String getCourseCircle(Integer id) {
        return baseDao.findCourseCircle(id);
    }

    @Override
    public void deleteByCourseId(Long id) {
        baseDao.deleteByCourseId(id);
    }

    public CourseManager saleDetail(Long id){
        return baseDao.saleDetail(id);
    }

    @Override
    public List<Map<String, Object>> getCoursesForWeb(CourseManagerVo courseManager) {
        return baseDao.findCourseForWeb(courseManager);
    }

    @Override
    public void updatePublic(Long id, String ispublic) {
        baseDao.updateIsPublic(id,ispublic);
    }

    @Override
    public CourseManagerVo getCourseMangerById(Long id) {
        return baseDao.findPrivateByCouseId(id);
    }

    @Override
    public List<Map<String, Object>> getAllCourseForWeb(CourseManagerVo courseManager) {
        return baseDao.findAllCourseForWeb(courseManager);
    }

    @Override
    public Integer getAllCountForWeb(CourseManagerVo courseManagerVo) {
        return baseDao.findAcountForWeb(courseManagerVo);
    }
    @Override
    public List<CourseManager> findCoursesByCourseType(Integer coursetype) {
        return baseDao.findCoursesByCourseType(coursetype);
    }

    @Override
    public List<CourseManager> findCoursesByCondition(Integer specialcourse, Integer time, Integer caseid) {
        return baseDao.findCoursesByCondition(specialcourse,time,caseid);
    }

    @Override
    public List<CourseManagerVo> getCoursesForIndex(CourseManagerVo courseManager) {
        return baseDao.findCoursesForIndex(courseManager);
    }

    @Override
    public List<Map<String, Object>> getAllForSel() {
        return baseDao.findForSel();
    }
}