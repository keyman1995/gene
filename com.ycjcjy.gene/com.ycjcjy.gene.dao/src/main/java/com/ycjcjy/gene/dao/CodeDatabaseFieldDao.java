package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CodeDatabaseField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CodeDatabaseFieldDao extends BaseDao<CodeDatabaseField> {
    /**
     * 根据表名和数据库名查出所有字段
     * @param databaseName
     * @param tablename
     * @return
     */
    List<CodeDatabaseField> findAllTableFieldbyTableName(@Param("databaseName") String databaseName, @Param("tablename") String tablename);
}