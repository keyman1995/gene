package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.Information;
import com.ycjcjy.gene.service.InformationService;
import com.ycjcjy.gene.dao.InformationDao;

import java.util.List;

/**
* @author yibin
* @description 首页资讯 serviceImpl
* @date 2018-05-28 10:04:00
*/
@Service
public class InformationServiceImpl extends BaseBiz<Information, InformationDao> implements InformationService{

    @Override
    public Integer getAllCount(Information information) {
        return baseDao.getAllCount(information);
    }

    @Override
    public List<Information> findAllInfor(Information information) {
        return baseDao.findAllInfor(information);
    }

    @Override
    public List<Information> findInforByTime() {
        return baseDao.findInforByTime();
    }
}