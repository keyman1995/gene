package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.Teacher;
import com.ycjcjy.gene.service.TeacherService;
import com.ycjcjy.gene.dao.TeacherDao;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl extends BaseBiz<Teacher, TeacherDao> implements TeacherService{

    @Override
    public List<Map<String, Object>> findAllByType(String type) {
        return baseDao.findCoachByType(type);
    }

    @Override
    public List<Teacher> findAllTeacher(Teacher teacher) {
        return baseDao.findAllTeacher(teacher);
    }

    @Override
    public Integer getAllCount(Teacher teacher) {
        return baseDao.getAllCount(teacher);
    }

    @Override
    public List<Teacher> findByField(String caseid) {
        return  baseDao.findByField(caseid);
    }

    @Override
    public Teacher findByTel(String tel) {
        return baseDao.findByTel(tel);
    }

    @Override
    public Teacher findByOpenId(String openId) {
        return baseDao.findByOpenId(openId);
    }

    @Override
    public List<Map<String,Object>> findByParam(Teacher teacher) {
        return baseDao.findTeacherByParam(teacher);
    }

    @Override
    public Teacher findForWeb(String id) {
        return baseDao.findByIdForWeb(id);
    }

    @Override
    public Integer getAllTeacherCount(Teacher teacher) {
        return baseDao.findTeacherCount(teacher);
    }
}