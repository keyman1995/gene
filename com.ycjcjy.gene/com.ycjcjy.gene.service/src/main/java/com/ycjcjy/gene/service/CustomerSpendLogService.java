package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CustomerSpendLog;

import java.util.List;

public interface CustomerSpendLogService extends IBaseBiz<CustomerSpendLog> {
    List<CustomerSpendLog> findByUserId(CustomerSpendLog customerSpendLog);

    Integer getAllCountByCustomerId(CustomerSpendLog customerSpendLog);
}