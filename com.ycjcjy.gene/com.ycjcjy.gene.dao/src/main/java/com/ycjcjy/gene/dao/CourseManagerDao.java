package com.ycjcjy.gene.dao;

import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.CourseManagerVo;
import net.onebean.core.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseManagerDao extends BaseDao<CourseManager> {

    public List<CourseManager> findAllCourse(CourseManager courseManager);

    public Integer findCount(CourseManager courseManager);

    public List<CourseManager> findYouXiaoCourse(CourseManager courseManager);

    public Integer findYouXiaoCount(CourseManager courseManager);

    public CourseManager findByCourseId(String id);

  /*  public List<Map<String,Object>> findAllByIsDate(String isDate);*/

    public String findCourseCircle(Integer id);

    void deleteByCourseId(@Param(value = "id") Long id);

    CourseManager saleDetail(@Param(value = "id") Long id);

    List<Map<String,Object>> findCourseForWeb(CourseManagerVo courseManager);

    Integer findAcountForWeb(CourseManagerVo courseManagerVo);

    void updateIsPublic(@Param(value = "id")Long id,@Param(value = "ispublic")String ispublic);

    CourseManagerVo findPrivateByCouseId(@Param(value = "id")Long id);

    Map<String,Object> findxilieCourseStartTimeAndEndTime(@Param(value = "id")Long id);

    List<Map<String,Object>>findAllCourseForWeb(CourseManagerVo courseManager);

    List<CourseManager> findCoursesByCourseType(Integer coursetype);

    List<CourseManager> findCoursesByCondition(@Param(value = "specialcourse")Integer specialcourse, @Param(value = "time")Integer time, @Param(value = "caseid")Integer caseid);

    List<CourseManagerVo> findCoursesForIndex(CourseManagerVo courseManager);

    List<Map<String,Object>> findForSel();
}