package com.ycjcjy.gene.service.impl;

import net.onebean.core.BaseBiz;
import net.onebean.core.Condition;
import com.ycjcjy.gene.dao.CodeDatabaseTableDao;
import com.ycjcjy.gene.model.CodeDatabaseField;
import com.ycjcjy.gene.model.CodeDatabaseTable;
import com.ycjcjy.gene.service.CodeDatabaseFieldService;
import com.ycjcjy.gene.service.CodeDatabaseTableService;
import net.onebean.util.CollectionUtil;
import net.onebean.util.PropUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeDatabaseTableServiceImpl extends BaseBiz<CodeDatabaseTable, CodeDatabaseTableDao> implements CodeDatabaseTableService{

    @Autowired
    private CodeDatabaseFieldService codeDatabaseFieldService;

    @Override
    public List<String> findDatabaseTableList() {
        return baseDao.findDatabaseTableList(PropUtil.getConfig("spring.datasource.databaseName"));
    }

    @Override
    public void deleteChildList(Object id) {
        Condition condition = Condition.parseCondition("table_id@int@eq$");
        condition.setValue(id);
        List<CodeDatabaseField> childList = codeDatabaseFieldService.find(null,condition);
        if(CollectionUtil.isNotEmpty(childList)){
            List<Long> ids = new ArrayList<>();
            for (CodeDatabaseField codeDatabaseField : childList) {
                ids.add(codeDatabaseField.getId());
            }
            codeDatabaseFieldService.deleteByIds(ids);
        }
    }
}