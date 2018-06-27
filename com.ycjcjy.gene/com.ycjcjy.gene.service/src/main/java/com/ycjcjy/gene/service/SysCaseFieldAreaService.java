package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SysCaseFieldArea;

import java.util.List;


/**
* @author Ken Hu
* @description 案场区域管理 service
* @date 2018-06-11 15:26:53
*/
public interface SysCaseFieldAreaService extends IBaseBiz<SysCaseFieldArea> {
    List<SysCaseFieldArea> findAreaByCaseId(Long caseId);

    Integer hasTable(String area,String case_id);

}