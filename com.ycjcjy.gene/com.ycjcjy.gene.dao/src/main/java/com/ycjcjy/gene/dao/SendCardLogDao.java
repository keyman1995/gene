package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.SendCardLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SendCardLogDao extends BaseDao<SendCardLog> {
    List<SendCardLog> getList(SendCardLog sendCardLog);
    Integer pageCount(SendCardLog sendCardLog);
    SendCardLog getOne(@Param("id") Integer id);
}