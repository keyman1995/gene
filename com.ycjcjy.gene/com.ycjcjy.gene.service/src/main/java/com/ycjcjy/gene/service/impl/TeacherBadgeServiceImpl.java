package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.TeacherBadge;
import com.ycjcjy.gene.service.TeacherBadgeService;
import com.ycjcjy.gene.dao.TeacherBadgeDao;

import java.util.List;

@Service
public class TeacherBadgeServiceImpl extends BaseBiz<TeacherBadge, TeacherBadgeDao> implements TeacherBadgeService{

    @Override
    public List<TeacherBadge> selectbadgeByTeacherId(int teacherId) {
        return baseDao.selectbadgeByTeacherId(teacherId);
    }
}