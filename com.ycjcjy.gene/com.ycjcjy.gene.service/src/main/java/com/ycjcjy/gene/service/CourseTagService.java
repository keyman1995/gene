package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CourseTag;

import java.util.List;
import java.util.Map;


/**
* @author chenjie
* @description 课程标签 service
* @date 2018-05-28 13:31:09
*/
public interface CourseTagService extends IBaseBiz<CourseTag> {

    List<Map<String,Object>> getAllTage(Long id);

}