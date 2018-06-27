package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CourseDetail;
import com.ycjcjy.gene.service.CourseDetailService;
import com.ycjcjy.gene.dao.CourseDetailDao;

@Service
public class CourseDetailServiceImpl extends BaseBiz<CourseDetail, CourseDetailDao> implements CourseDetailService{
}