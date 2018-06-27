package com.ycjcjy.gene.service;

import com.ycjcjy.gene.VO.ExportExcelVO;
import com.ycjcjy.gene.VO.MonthOrderVO;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.GoodsOrderSub;

import java.util.List;


/**
* @author szcc
* @description 商品订单子订单 service
* @date 2018-06-11 18:11:14
*/
public interface GoodsOrderSubService extends IBaseBiz<GoodsOrderSub> {
    List<GoodsOrderSub> findAllGoodsOrderSub(GoodsOrderSub orderMain);

    Integer getAllCount(GoodsOrderSub orderMain);

    List<MonthOrderVO> getMonthlyOrder(Integer case_id);
    MonthOrderVO getThisMonthlyOrder(Integer case_id);

    Integer getMonthlyOrderCount(Integer case_id);

    List<ExportExcelVO> exportExcelList(Integer case_id, Integer month);

    Integer getGiveCountBySalerId(Integer salerId,Integer ismonth,Integer month);
}