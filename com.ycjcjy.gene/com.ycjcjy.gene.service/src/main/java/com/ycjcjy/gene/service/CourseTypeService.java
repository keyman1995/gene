package com.ycjcjy.gene.service;
import com.ycjcjy.gene.VO.CourseTypeTree;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CourseType;

import java.util.List;

public interface CourseTypeService extends IBaseBiz<CourseType> {

    List<CourseType> findChildSync(Long id);
    List<CourseType> findPicPlace();

    List<CourseTypeTree> courseToCourseTypeTree(List<CourseType> before,Long self_id);

    void deleteSelfAndChildById(Long id);

   Integer findChildOrderNextNum(Long parentId);

    List<CourseType> findByParentId(int i);

    List<String> getAllUnicode(Long id);
}