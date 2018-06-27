package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.RechargeSetting;

import java.util.List;

public interface RechargeSettingDao extends BaseDao<RechargeSetting> {
    List<RechargeSetting> findAllSetting();
}