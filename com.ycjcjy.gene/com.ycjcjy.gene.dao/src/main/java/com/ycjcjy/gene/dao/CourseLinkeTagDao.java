package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CourseLinkeTag;
import org.apache.ibatis.annotations.Param;

/**
* @author chenjie
* @description 课程标签关联表 Dao
* @date 2018-05-28 16:59:16
*/
public interface CourseLinkeTagDao extends BaseDao<CourseLinkeTag> {
    void deleteByCourseId(@Param(value = "courseid")Long courseid);
}