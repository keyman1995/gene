package com.ycjcjy.gene.service.impl;

import com.ycjcjy.gene.dao.CustomerSpendLogDao;
import com.ycjcjy.gene.model.CustomerSpendLog;
import com.ycjcjy.gene.service.CustomerSpendLogService;
import net.onebean.core.BaseBiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSpendLogServiceImpl extends BaseBiz<CustomerSpendLog, CustomerSpendLogDao> implements CustomerSpendLogService{
    @Override
    public List<CustomerSpendLog> findByUserId(CustomerSpendLog customerSpendLog) {
        return baseDao.findByUserId(customerSpendLog);
    }

    @Override
    public Integer getAllCountByCustomerId(CustomerSpendLog customerSpendLog) {
        return baseDao.getAllCountByCustomerId(customerSpendLog);
    }
}