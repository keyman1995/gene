package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.TeacherBadgeRela;
import org.apache.ibatis.annotations.Param;

public interface TeacherBadgeRelaDao extends BaseDao<TeacherBadgeRela> {

    void deleteByTeacherId(@Param(value = "teacherId") int teacherId);
    void findByTeacherId(@Param(value = "teacherId") int teacherId);
}