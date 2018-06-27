package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.TeacherBadge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherBadgeService extends IBaseBiz<TeacherBadge> {
    List<TeacherBadge> selectbadgeByTeacherId(int teacherId);
}