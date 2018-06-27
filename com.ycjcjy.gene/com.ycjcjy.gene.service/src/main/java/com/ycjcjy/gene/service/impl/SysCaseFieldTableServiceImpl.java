package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.SysCaseFieldTable;
import com.ycjcjy.gene.service.SysCaseFieldTableService;
import com.ycjcjy.gene.dao.SysCaseFieldTableDao;

import java.util.List;

/**
* @author Ken Hu
* @description 案场桌位管理 serviceImpl
* @date 2018-06-11 15:35:49
*/
@Service
public class SysCaseFieldTableServiceImpl extends BaseBiz<SysCaseFieldTable, SysCaseFieldTableDao> implements SysCaseFieldTableService{
    @Override
    public List<SysCaseFieldTable> queryAllTableByArea(Long areaId) {
        return baseDao.queryAllTableByArea(areaId);
    }

    @Override
    public SysCaseFieldTable queryAllTableById(Long id) {
        return baseDao.queryAllTableById(id);
    }
}