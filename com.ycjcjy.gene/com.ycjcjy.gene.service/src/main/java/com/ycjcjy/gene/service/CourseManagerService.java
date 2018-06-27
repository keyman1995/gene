package com.ycjcjy.gene.service;

import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.CourseManagerVo;
import net.onebean.core.IBaseBiz;

import java.util.List;
import java.util.Map;

public interface CourseManagerService extends IBaseBiz<CourseManager> {

    public List<CourseManager> findCourses(CourseManager courseManager);

    public CourseManager findByCourseId(String id);

    public List<CourseManager> findYouXiaoCourse(CourseManager courseManager);

    public Integer findYouXiaoCount(CourseManager courseManager);

    public Integer getAllCount(CourseManager courseManager);

    public String getCourseCircle(Integer id);

    void deleteByCourseId(Long id);

    CourseManager saleDetail(Long id);

   List<Map<String,Object>> getCoursesForWeb(CourseManagerVo courseManager);

   Integer getAllCountForWeb(CourseManagerVo courseManagerVo);

   void updatePublic(Long id,String ispublic);

   CourseManagerVo getCourseMangerById(Long id);

   List<Map<String,Object>> getAllCourseForWeb(CourseManagerVo courseManager);


    List<CourseManager> findCoursesByCourseType(Integer coursetype);

    List<CourseManager> findCoursesByCondition(Integer specialcourse, Integer time, Integer caseid);

    List<CourseManagerVo> getCoursesForIndex(CourseManagerVo courseManager);

    List<Map<String,Object>> getAllForSel();
}