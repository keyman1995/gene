package com.ycjcjy.gene.service.impl;

import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.dao.CodeDatabaseFieldDao;
import com.ycjcjy.gene.model.CodeDatabaseField;
import com.ycjcjy.gene.service.CodeDatabaseFieldService;
import net.onebean.util.PropUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeDatabaseFieldServiceImpl extends BaseBiz<CodeDatabaseField, CodeDatabaseFieldDao> implements CodeDatabaseFieldService{

    @Override
    public List<CodeDatabaseField> findAllTableFieldbyTableName(String tablename) {
        return baseDao.findAllTableFieldbyTableName(PropUtil.getConfig("spring.datasource.databaseName"),tablename);
    }
}