package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.Information;

import java.util.List;


/**
* @author yibin
* @description 首页资讯 service
* @date 2018-05-28 10:04:00
*/
public interface InformationService extends IBaseBiz<Information> {

    Integer getAllCount(Information information);

    List<Information> findAllInfor(Information information);

    List<Information> findInforByTime();
}