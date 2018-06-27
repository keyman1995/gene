package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SubCourseManager;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CourseMakeupManager;

import java.util.List;

public interface CourseMakeupManagerService extends IBaseBiz<CourseMakeupManager> {
    public List<SubCourseManager> findMakeupCourseList(SubCourseManager subCourseManager);
    public List<CustomerUser> findMakeupCustomer(CustomerUser customerUser);
    public  CustomerUser findCustomerDetail(Integer userId,Integer courseId);
    Integer pageCount(SubCourseManager subCourseManager);
}