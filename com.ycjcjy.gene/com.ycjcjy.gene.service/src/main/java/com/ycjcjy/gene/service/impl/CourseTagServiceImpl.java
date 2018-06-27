package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CourseTag;
import com.ycjcjy.gene.service.CourseTagService;
import com.ycjcjy.gene.dao.CourseTagDao;

import java.util.List;
import java.util.Map;

/**
* @author chenjie
* @description 课程标签 serviceImpl
* @date 2018-05-28 13:31:09
*/
@Service
public class CourseTagServiceImpl extends BaseBiz<CourseTag, CourseTagDao> implements CourseTagService{


    @Override
    public List<Map<String, Object>> getAllTage(Long id) {
        return baseDao.findCourseTags(id);
    }
}