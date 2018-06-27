package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CodeDatabaseTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CodeDatabaseTableDao extends BaseDao<CodeDatabaseTable> {
    /**
     * 查询数据库所有表名
     * @param databaseName
     * @return
     */
    List<String> findDatabaseTableList(@Param("databaseName") String databaseName);

}