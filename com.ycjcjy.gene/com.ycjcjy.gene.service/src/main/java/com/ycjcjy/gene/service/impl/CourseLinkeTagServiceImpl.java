package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CourseLinkeTag;
import com.ycjcjy.gene.service.CourseLinkeTagService;
import com.ycjcjy.gene.dao.CourseLinkeTagDao;

/**
* @author chenjie
* @description 课程标签关联表 serviceImpl
* @date 2018-05-28 16:59:16
*/
@Service
public class CourseLinkeTagServiceImpl extends BaseBiz<CourseLinkeTag, CourseLinkeTagDao> implements CourseLinkeTagService{


    @Override
    public void deleteByCourseId(Long id) {
        baseDao.deleteByCourseId(id);
    }
}