package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.GoodsOrderMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author szcc
* @description 商品订单主表 Dao
* @date 2018-06-11 18:10:40
*/
public interface GoodsOrderMainDao extends BaseDao<GoodsOrderMain> {
    List<GoodsOrderMain> findNewGoodsOrderMain(GoodsOrderMain goodsOrderMain);

    GoodsOrderMain findNewGoodsOrderMainForTui(GoodsOrderMain goodsOrderMain);

    int updateNewGoodsOrderMain(List<GoodsOrderMain> list);

    int  updateOrder(GoodsOrderMain goodsOrderMain);
    int  updateOrderFlag(GoodsOrderMain goodsOrderMain);

    int toDayGoodsNum(@Param("caseId") String caseId,@Param("id") String id,@Param("nowTime")String nowTime);
    int toDayOrderCount(@Param("caseId") String caseId,@Param("id")String id,@Param("nowTime")String nowTime);
}