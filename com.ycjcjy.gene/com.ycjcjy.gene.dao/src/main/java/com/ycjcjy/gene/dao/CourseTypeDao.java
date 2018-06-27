package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CourseType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTypeDao extends BaseDao<CourseType> {

   List<CourseType> findChildSync(Long id);
   List<CourseType> findPicPlace();

   void deleteSelfAndChildById(Long id);

   String getParentCtype(@Param("childId") Long childId);

   Integer getChildByPId(Long parentid);

    List<CourseType> findByParentId(Integer i);

    List<String> findUnicode(@Param("id")Long id);
}