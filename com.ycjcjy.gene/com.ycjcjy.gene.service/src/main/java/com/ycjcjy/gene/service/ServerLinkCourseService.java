package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.ServerLinkCourse;

import java.util.List;


/**
* @author chenjie
* @description 专题课程 service
* @date 2018-05-30 19:59:09
*/
public interface ServerLinkCourseService extends IBaseBiz<ServerLinkCourse> {

    List<ServerLinkCourse> getAllLinkCourses(Long id);

}