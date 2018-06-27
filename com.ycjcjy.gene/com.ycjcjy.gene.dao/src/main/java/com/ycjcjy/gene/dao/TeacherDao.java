package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherDao extends BaseDao<Teacher> {

    List<Map<String,Object>>  findCoachByType(String type);

    List<Teacher> findAllTeacher(Teacher teacher);

    Integer getAllCount(Teacher teacher);

    List<Teacher> findByField(@Param("caseid")String caseid);
    Teacher findByTel(@Param("tel")String tel);
    Teacher findByOpenId(@Param("openId")String openId);

    List<Map<String,Object>> findTeacherByParam(Teacher teacher);

    Integer findTeacherCount(Teacher teacher);

    Teacher findByIdForWeb(@Param(value = "id")String id);


}