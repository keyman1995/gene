package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.ServerLinkCourse;
import com.ycjcjy.gene.service.ServerLinkCourseService;
import com.ycjcjy.gene.dao.ServerLinkCourseDao;

import java.util.List;

/**
* @author chenjie
* @description 专题课程 serviceImpl
* @date 2018-05-30 19:59:09
*/
@Service
public class ServerLinkCourseServiceImpl extends BaseBiz<ServerLinkCourse, ServerLinkCourseDao> implements ServerLinkCourseService{

    @Override
    public List<ServerLinkCourse> getAllLinkCourses(Long id) {
        return baseDao.selectLinkCourseByServerId(id);
    }
}