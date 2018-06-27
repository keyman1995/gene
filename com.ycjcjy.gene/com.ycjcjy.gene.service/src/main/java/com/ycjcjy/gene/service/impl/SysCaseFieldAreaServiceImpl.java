package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.SysCaseFieldArea;
import com.ycjcjy.gene.service.SysCaseFieldAreaService;
import com.ycjcjy.gene.dao.SysCaseFieldAreaDao;

import java.util.List;

/**
* @author Ken Hu
* @description 案场区域管理 serviceImpl
* @date 2018-06-11 15:26:53
*/
@Service
public class SysCaseFieldAreaServiceImpl extends BaseBiz<SysCaseFieldArea, SysCaseFieldAreaDao> implements SysCaseFieldAreaService{
    @Override
    public List<SysCaseFieldArea> findAreaByCaseId(Long caseId){
        return baseDao.findAreaByCaseId(caseId);
    }

    @Override
    public Integer hasTable(String area,String case_id){return baseDao.hasTable(area,case_id);}

}