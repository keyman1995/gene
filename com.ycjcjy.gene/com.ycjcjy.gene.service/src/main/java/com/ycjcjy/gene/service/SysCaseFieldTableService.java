package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SysCaseFieldTable;

import java.util.List;


/**
* @author Ken Hu
* @description 案场桌位管理 service
* @date 2018-06-11 15:35:49
*/
public interface SysCaseFieldTableService extends IBaseBiz<SysCaseFieldTable> {
    List<SysCaseFieldTable> queryAllTableByArea(Long areaId);
    SysCaseFieldTable queryAllTableById(Long id);
}