package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.LocalGoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Ken Hu
* @description 商品类型管理 Dao
* @date 2018-06-11 15:43:08
*/
public interface LocalGoodsTypeDao extends BaseDao<LocalGoodsType> {
    List<LocalGoodsType> findAllType(@Param("caseId") Long caseId);

    Integer hasGoods(@Param("type") String type, @Param("case_id") String case_id);
}