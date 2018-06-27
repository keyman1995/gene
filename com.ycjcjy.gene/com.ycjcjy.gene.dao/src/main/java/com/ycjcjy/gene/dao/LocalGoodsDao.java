package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.LocalGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocalGoodsDao extends BaseDao<LocalGoods> {
    LocalGoods findCardGoods(Long targetId);

    Integer hasCard(@Param("id") Long id);
    List<LocalGoods> findAllGoods(@Param("caseId") Long caseId);
}