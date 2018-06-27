package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CmsPic;
import com.ycjcjy.gene.service.CmsPicService;
import com.ycjcjy.gene.dao.CmsPicDao;

import java.util.List;
import java.util.Map;

@Service
public class CmsPicServiceImpl extends BaseBiz<CmsPic, CmsPicDao> implements CmsPicService{

    public List<CmsPic> findAllPic(CmsPic pic){
        return  baseDao.findAllPic(pic);
    }

    public Integer getAllCount(CmsPic pic){
        return baseDao.getAllCount(pic);
    }

    @Override
    public List<Map<String,Object>> findByParam(Integer place) {
        return baseDao.findOneCmsByParam(place);
    }
    @Override
    public List<CmsPic> findPicByPlace(CmsPic cmsPic) {
        return baseDao.findPicByPlace(cmsPic);
    }

    @Override
    public Integer findPicCount(Integer place, Integer i) {
        return baseDao.findPicCount(place,i);
    }

    @Override
    public void updateCmsPic(CmsPic cmsPic) {
        baseDao.updateCms(cmsPic);
    }
}