package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.SendCardLog;
import com.ycjcjy.gene.service.SendCardLogService;
import com.ycjcjy.gene.dao.SendCardLogDao;

import java.util.List;

@Service
public class SendCardLogServiceImpl extends BaseBiz<SendCardLog, SendCardLogDao> implements SendCardLogService{
    public List<SendCardLog> getList(SendCardLog sendCardLog){
        return baseDao.getList(sendCardLog);
    }
    public Integer pageCount(SendCardLog sendCardLog){
        return baseDao.pageCount(sendCardLog);
    }

    public SendCardLog getOne(Integer id){
        return baseDao.getOne(id);
    }
}