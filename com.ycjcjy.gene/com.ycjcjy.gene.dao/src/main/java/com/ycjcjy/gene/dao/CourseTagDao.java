package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CourseTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author chenjie
* @description 课程标签 Dao
* @date 2018-05-28 13:31:09
*/
public interface CourseTagDao extends BaseDao<CourseTag> {

    List<Map<String,Object>> findCourseTags(@Param(value = "id")Long id);
}