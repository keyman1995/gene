package com.ycjcjy.gene.dao;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SubCourseManager;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CourseMakeupManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMakeupManagerDao extends BaseDao<CourseMakeupManager> {

    List<SubCourseManager> findMakeupCourseList(SubCourseManager subCourseManager);
    List<CustomerUser> findMakeupCustomer(CustomerUser customerUser);
    CustomerUser findCustomerDetail(@Param("userId") Integer userId , @Param("courseId") Integer courseId);
    Integer pageCount (SubCourseManager subCourseManager);
}