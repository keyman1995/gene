package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.RechargeSetting;
import com.ycjcjy.gene.service.RechargeSettingService;
import com.ycjcjy.gene.dao.RechargeSettingDao;

import java.util.List;

@Service
public class RechargeSettingServiceImpl extends BaseBiz<RechargeSetting, RechargeSettingDao> implements RechargeSettingService{
    @Override
    public List<RechargeSetting> findAllSetting() {
        return baseDao.findAllSetting();
    }
}