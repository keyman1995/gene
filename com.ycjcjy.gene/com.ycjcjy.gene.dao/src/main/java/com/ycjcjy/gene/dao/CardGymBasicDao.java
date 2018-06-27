package com.ycjcjy.gene.dao;
import com.ycjcjy.gene.model.CardTicketMaster;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CardGymBasic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardGymBasicDao extends BaseDao<CardGymBasic> {
    List<CardGymBasic> findList(CardGymBasic cardGymBasic);

    int getCount(CardGymBasic cardGymBasic);

    CardGymBasic getById(@Param(value = "target_id") Long target_id);

    Integer hasTicket (@Param("target_id") Integer target_id);
}