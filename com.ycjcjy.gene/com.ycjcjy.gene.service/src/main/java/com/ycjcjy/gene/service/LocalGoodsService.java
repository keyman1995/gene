package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.LocalGoods;

import java.util.List;

public interface LocalGoodsService extends IBaseBiz<LocalGoods> {
    public LocalGoods findCardGoods (Long targetId);

    Integer hasCard(Long id);

    List<LocalGoods> findAllGoods(Long caseId);
}