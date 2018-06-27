package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService extends IBaseBiz<Teacher> {

    List<Map<String,Object>> findAllByType(String type);

    List<Teacher> findAllTeacher(Teacher teacher);

    Integer getAllCount(Teacher teacher);

    List<Teacher> findByField(String caseid);
    Teacher findByTel(String tel);

    Teacher findByOpenId(String openId);

    List<Map<String,Object>> findByParam(Teacher teacher);

    Integer getAllTeacherCount(Teacher teacher);

    Teacher findForWeb(String id);

}