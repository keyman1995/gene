package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.LocalGoods;
import com.ycjcjy.gene.service.LocalGoodsService;
import com.ycjcjy.gene.dao.LocalGoodsDao;

import java.util.List;

@Service
public class LocalGoodsServiceImpl extends BaseBiz<LocalGoods, LocalGoodsDao> implements LocalGoodsService{
    public LocalGoods findCardGoods (Long targetId){
        return baseDao.findCardGoods(targetId);
    }

    public Integer hasCard(Long id){
        return baseDao.hasCard(id);
    }

    @Override
    public List<LocalGoods> findAllGoods(Long caseId) {
        return baseDao.findAllGoods(caseId);
    }
}