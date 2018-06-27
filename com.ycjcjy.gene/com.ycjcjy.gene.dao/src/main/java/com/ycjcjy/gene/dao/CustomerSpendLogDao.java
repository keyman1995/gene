package com.ycjcjy.gene.dao;
import com.ycjcjy.gene.model.CustomerSpendLog;
import net.onebean.core.BaseDao;

import java.util.List;

public interface CustomerSpendLogDao extends BaseDao<CustomerSpendLog> {
    List<CustomerSpendLog> findByUserId(CustomerSpendLog customerSpendLog);

    Integer getAllCountByCustomerId(CustomerSpendLog customerSpendLog);
}