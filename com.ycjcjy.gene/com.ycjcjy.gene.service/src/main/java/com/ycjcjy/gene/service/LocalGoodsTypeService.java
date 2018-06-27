package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.LocalGoodsType;

import java.util.List;


/**
* @author Ken Hu
* @description 商品类型管理 service
* @date 2018-06-11 15:43:08
*/
public interface LocalGoodsTypeService extends IBaseBiz<LocalGoodsType> {
    List<LocalGoodsType> findAllType(Long caseId);

    Integer hasGoods(String type,String case_id);
}