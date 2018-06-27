package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CodeDatabaseField;

import java.util.List;

public interface CodeDatabaseFieldService extends IBaseBiz<CodeDatabaseField> {

    /**
     * 根据表名和数据库名查出所有字段
     * @param tablename
     * @return
     */
    List<CodeDatabaseField> findAllTableFieldbyTableName(String tablename);
}