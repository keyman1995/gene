package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SubCourseManager;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CourseMakeupManager;
import com.ycjcjy.gene.service.CourseMakeupManagerService;
import com.ycjcjy.gene.dao.CourseMakeupManagerDao;

import java.util.List;

@Service
public class CourseMakeupManagerServiceImpl extends BaseBiz<CourseMakeupManager, CourseMakeupManagerDao> implements CourseMakeupManagerService{
    public List<SubCourseManager> findMakeupCourseList(SubCourseManager subCourseManager){
        return baseDao.findMakeupCourseList(subCourseManager);
    }
    public List<CustomerUser> findMakeupCustomer(CustomerUser customerUser){
        return  baseDao.findMakeupCustomer(customerUser);
    }
    public  CustomerUser findCustomerDetail(Integer userId,Integer courseId){return baseDao.findCustomerDetail(userId,courseId);}
    public Integer pageCount(SubCourseManager subCourseManager){return baseDao.pageCount(subCourseManager);}
}