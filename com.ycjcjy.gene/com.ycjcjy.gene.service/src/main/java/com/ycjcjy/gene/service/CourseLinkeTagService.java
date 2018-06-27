package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CourseLinkeTag;


/**
* @author chenjie
* @description 课程标签关联表 service
* @date 2018-05-28 16:59:16
*/
public interface CourseLinkeTagService extends IBaseBiz<CourseLinkeTag> {


  void   deleteByCourseId(Long id);
}