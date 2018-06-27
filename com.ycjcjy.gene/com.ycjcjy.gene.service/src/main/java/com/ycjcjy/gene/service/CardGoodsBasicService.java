package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CardGoodsBasic;

import java.util.List;

public interface CardGoodsBasicService extends IBaseBiz<CardGoodsBasic> {
    public List<CardGoodsBasic> findGoods(CardGoodsBasic cardGoodsBasic);
    public Integer pageCount(CardGoodsBasic cardGoodsBasic);
    Integer hasTicket (Integer target_id);
}