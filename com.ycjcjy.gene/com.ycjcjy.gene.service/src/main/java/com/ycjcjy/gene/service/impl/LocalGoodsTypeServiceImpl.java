package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.LocalGoodsType;
import com.ycjcjy.gene.service.LocalGoodsTypeService;
import com.ycjcjy.gene.dao.LocalGoodsTypeDao;

import java.util.List;

/**
* @author Ken Hu
* @description 商品类型管理 serviceImpl
* @date 2018-06-11 15:43:08
*/
@Service
public class LocalGoodsTypeServiceImpl extends BaseBiz<LocalGoodsType, LocalGoodsTypeDao> implements LocalGoodsTypeService{
    @Override
    public List<LocalGoodsType> findAllType(Long caseId) {
        return baseDao.findAllType(caseId);
    }
    @Override
    public Integer hasGoods(String type,String case_id){return baseDao.hasGoods(type,case_id);}
}