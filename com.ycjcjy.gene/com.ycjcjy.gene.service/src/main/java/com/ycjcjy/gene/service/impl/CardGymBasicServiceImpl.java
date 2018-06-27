package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.model.CardTicketMaster;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CardGymBasic;
import com.ycjcjy.gene.service.CardGymBasicService;
import com.ycjcjy.gene.dao.CardGymBasicDao;

import java.util.List;

@Service
public class CardGymBasicServiceImpl extends BaseBiz<CardGymBasic, CardGymBasicDao> implements CardGymBasicService{

    @Autowired
    CardGymBasicDao cardGymBasicDao;

    @Override
    public List<CardGymBasic> findList(CardGymBasic cardGymBasic) {
        return cardGymBasicDao.findList(cardGymBasic);
    }

    @Override
    public int getCount(CardGymBasic cardGymBasic) {
        return cardGymBasicDao.getCount(cardGymBasic);
    }

    @Override
    public CardGymBasic getById(Long target_id) {
        return cardGymBasicDao.getById(target_id);
    }

    public Integer hasTicket (Integer target_id){
        return baseDao.hasTicket(target_id);
    }
}