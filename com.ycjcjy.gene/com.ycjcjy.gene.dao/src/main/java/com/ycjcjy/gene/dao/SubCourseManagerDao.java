package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.SubCourseManager;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SubCourseManagerDao extends BaseDao<SubCourseManager> {

    public List<SubCourseManager> findSubCourses(@Param(value = "date") Date date,@Param(value = "courseid")Long courseid);

    Map<String,Object>  findSubCourse(@Param(value = "id")Integer id);

    void deleteByCourseId(@Param(value = "id")Integer id);

    void updateBySubId(SubCourseManager subCourseManager);

    int findCountByCircle(@Param(value = "id")Integer id);

    int getCountFromNow(@Param(value = "nowDay")Date nowDate,@Param(value = "id")Integer id);

    Long saveSubCourse(SubCourseManager subCourseManager);

    void deleteByPid(@Param(value = "pcourseid")Integer pcourseid);

    List<SubCourseManager> getSubCourseById(@Param("id") Integer id);

    List<SubCourseManager> delayList(SubCourseManager subCourseManager);

    Integer delayListCount(SubCourseManager subCourseManager);

    Map<String,Object> selectSubCountByDay(@Param(value = "caseid")String caseid);

    Integer findCountByMonth(@Param("case_id")String case_id);

    List<Map<String,Object>> findAllNum(SubCourseManager subCourseManager);
}