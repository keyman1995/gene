package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.GoodsOrderMain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @author szcc
* @description 商品订单主表 service
* @date 2018-06-11 18:10:40
*/

public interface GoodsOrderMainService extends IBaseBiz<GoodsOrderMain> {
    List<GoodsOrderMain> findNewGoodsOrderMain(GoodsOrderMain goodsOrderMain);
    int  updateOrder(GoodsOrderMain goodsOrderMain);
    int  updateOrderFlag(GoodsOrderMain goodsOrderMain);

    int updateNewGoodsOrderMain(List<GoodsOrderMain> list);

    Map<String,Object> addOrder(String json,int tableId,int caseId,int salerId,String remark);

}