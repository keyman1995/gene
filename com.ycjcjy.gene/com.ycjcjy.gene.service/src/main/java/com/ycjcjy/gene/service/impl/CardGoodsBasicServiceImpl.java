package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CardGoodsBasic;
import com.ycjcjy.gene.service.CardGoodsBasicService;
import com.ycjcjy.gene.dao.CardGoodsBasicDao;

import java.util.List;

@Service
public class CardGoodsBasicServiceImpl extends BaseBiz<CardGoodsBasic, CardGoodsBasicDao> implements CardGoodsBasicService{
    public List<CardGoodsBasic> findGoods(CardGoodsBasic cardGoodsBasic){
        return baseDao.findGoods(cardGoodsBasic);
    }
    public Integer pageCount(CardGoodsBasic cardGoodsBasic){
        return baseDao.pageCount(cardGoodsBasic);
    }
    public Integer hasTicket (Integer target_id){
        return baseDao.hasTicket(target_id);
    }
}