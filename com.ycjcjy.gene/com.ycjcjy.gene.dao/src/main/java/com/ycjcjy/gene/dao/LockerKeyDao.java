package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.LockerKey;
import net.onebean.core.Condition;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface LockerKeyDao extends BaseDao<LockerKey> {

    List<Integer> findByCaseFieldId(Integer case_field_id);

    Integer findUserId(@Param(value = "case_field_id") Long case_field_id,@Param(value = "userId") Long userId);
}