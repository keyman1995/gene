package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.TeacherBadgeRela;

public interface TeacherBadgeRelaService extends IBaseBiz<TeacherBadgeRela> {
    void deleteByTeacherId(int teacherId);
}