package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.TeacherBadgeRela;
import com.ycjcjy.gene.service.TeacherBadgeRelaService;
import com.ycjcjy.gene.dao.TeacherBadgeRelaDao;

@Service
public class TeacherBadgeRelaServiceImpl extends BaseBiz<TeacherBadgeRela, TeacherBadgeRelaDao> implements TeacherBadgeRelaService{
    @Override
    public void deleteByTeacherId(int teacherId) {
        baseDao.deleteByTeacherId(teacherId);

    }


}