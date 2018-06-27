package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.VipCard;
import com.ycjcjy.gene.service.VipCardService;
import com.ycjcjy.gene.dao.VipCardDao;

import java.util.List;

@Service
public class VipCardServiceImpl extends BaseBiz<VipCard, VipCardDao> implements VipCardService{
    public  List<VipCard> findAllCard(VipCard card){
            return  baseDao.findAllCard(card);
    }

    public Integer getAllCount(VipCard card){
        return  baseDao.getAllCount(card);
    }

    @Override
    public VipCard getByCardNo(String cardNo) {
        return baseDao.getByCardNo(cardNo);
    }
}