package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.RechargeSetting;

import java.util.List;

public interface RechargeSettingService extends IBaseBiz<RechargeSetting> {
    List<RechargeSetting> findAllSetting();
}