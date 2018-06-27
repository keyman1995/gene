package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.CardTicketMaster;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CardGymBasic;

import java.util.List;

public interface CardGymBasicService extends IBaseBiz<CardGymBasic> {
    List<CardGymBasic> findList(CardGymBasic cardGymBasic);

    int getCount(CardGymBasic cardGymBasic);

    CardGymBasic getById(Long target_id);

    Integer hasTicket (Integer target_id);
}