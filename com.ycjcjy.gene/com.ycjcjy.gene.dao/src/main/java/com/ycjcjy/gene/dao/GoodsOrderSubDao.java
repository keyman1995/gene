package com.ycjcjy.gene.dao;

import com.ycjcjy.gene.VO.ExportExcelVO;
import com.ycjcjy.gene.VO.MonthOrderVO;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.GoodsOrderSub;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author szcc
* @description 商品订单子订单 Dao
* @date 2018-06-11 18:11:14
*/
public interface GoodsOrderSubDao extends BaseDao<GoodsOrderSub> {
    int  updateOrder(GoodsOrderSub goodsOrderMain);


    List<GoodsOrderSub> findAllGoodsOrderSub(GoodsOrderSub orderMain);

    Integer getAllCount(GoodsOrderSub orderMain);

    List<MonthOrderVO> getMonthlyOrder(@Param("case_id") Integer case_id);
    Integer getMonthlyOrderCount(@Param("case_id") Integer case_id);
    MonthOrderVO getThisMonthlyOrder(@Param("case_id") Integer case_id);

    List<ExportExcelVO> exportExcelList(@Param("case_id") Integer case_id,@Param("month") Integer month);



    Integer getGiveCountBySalerId(@Param("salerId")Integer salerId,@Param("ismonth")Integer ismonth,@Param("month")Integer month);
}