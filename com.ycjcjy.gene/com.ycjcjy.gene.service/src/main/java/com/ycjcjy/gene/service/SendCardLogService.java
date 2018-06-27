package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SendCardLog;

import java.util.List;

public interface SendCardLogService extends IBaseBiz<SendCardLog> {
    public List<SendCardLog> getList(SendCardLog sendCardLog);
    Integer pageCount(SendCardLog sendCardLog);
    SendCardLog getOne(Integer id);
}