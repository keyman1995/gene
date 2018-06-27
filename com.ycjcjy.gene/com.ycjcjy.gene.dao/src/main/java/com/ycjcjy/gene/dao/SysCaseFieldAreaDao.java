package com.ycjcjy.gene.dao;

import com.ycjcjy.gene.model.SysCaseFieldArea;
import net.onebean.core.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Ken Hu
* @description 案场区域管理 Dao
* @date 2018-06-11 15:26:53
*/
public interface SysCaseFieldAreaDao extends BaseDao<SysCaseFieldArea> {
    List<SysCaseFieldArea> findAreaByCaseId(@Param("caseId") Long caseId);

    Integer hasTable(@Param("area") String area,@Param("case_id") String case_id);
}