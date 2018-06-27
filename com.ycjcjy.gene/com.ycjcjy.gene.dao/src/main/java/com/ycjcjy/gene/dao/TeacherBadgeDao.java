package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.TeacherBadge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherBadgeDao extends BaseDao<TeacherBadge> {


    List<TeacherBadge> selectbadgeByTeacherId(@Param(value = "teacherId") int teacherId);
}