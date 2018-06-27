package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CardGoodsBasic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardGoodsBasicDao extends BaseDao<CardGoodsBasic> {
    List<CardGoodsBasic> findGoods(CardGoodsBasic cardGoodsBasic);
    Integer pageCount(CardGoodsBasic cardGoodsBasic);
    Integer hasTicket (@Param("target_id") Integer target_id);

}