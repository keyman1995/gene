package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.SysCaseFieldTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Ken Hu
* @description 案场桌位管理 Dao
* @date 2018-06-11 15:35:49
*/
public interface SysCaseFieldTableDao extends BaseDao<SysCaseFieldTable> {

    List<SysCaseFieldTable> queryAllTableByArea(@Param("areaId") Long areaId);

    SysCaseFieldTable queryAllTableById(@Param("id") Long id);
}