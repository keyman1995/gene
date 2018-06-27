package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.Information;

import java.util.List;

/**
* @author yibin
* @description 首页资讯 Dao
* @date 2018-05-28 10:04:00
*/
public interface InformationDao extends BaseDao<Information> {

    Integer getAllCount(Information information);

    List<Information> findAllInfor(Information information);

    List<Information> findInforByTime();
}