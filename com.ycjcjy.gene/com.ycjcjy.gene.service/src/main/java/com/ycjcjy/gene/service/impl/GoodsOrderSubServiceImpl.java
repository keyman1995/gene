package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.VO.ExportExcelVO;
import com.ycjcjy.gene.VO.MonthOrderVO;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.GoodsOrderSub;
import com.ycjcjy.gene.service.GoodsOrderSubService;
import com.ycjcjy.gene.dao.GoodsOrderSubDao;

import java.util.List;

/**
* @author szcc
* @description 商品订单子订单 serviceImpl
* @date 2018-06-11 18:11:14
*/
@Service
public class GoodsOrderSubServiceImpl extends BaseBiz<GoodsOrderSub, GoodsOrderSubDao> implements GoodsOrderSubService{
    @Override
    public List<GoodsOrderSub> findAllGoodsOrderSub(GoodsOrderSub orderMain) {
        return baseDao.findAllGoodsOrderSub(orderMain);
    }

    @Override
    public Integer getAllCount(GoodsOrderSub orderMain) {
        return baseDao.getAllCount(orderMain);
    }

    @Override
    public List<MonthOrderVO> getMonthlyOrder(Integer case_id){return baseDao.getMonthlyOrder(case_id);}

    @Override
    public MonthOrderVO getThisMonthlyOrder(Integer case_id){return baseDao.getThisMonthlyOrder(case_id);}

    @Override
    public Integer getMonthlyOrderCount(Integer case_id){return baseDao.getMonthlyOrderCount(case_id);}

    @Override
    public List<ExportExcelVO> exportExcelList(Integer case_id, Integer month){return baseDao.exportExcelList(case_id,month);}

    @Override
    public Integer getGiveCountBySalerId(Integer salerId, Integer ismonth, Integer month) {
        return baseDao.getGiveCountBySalerId(salerId, ismonth, month);
    }
}