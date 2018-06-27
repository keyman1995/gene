package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CmsPic;

import java.util.List;
import java.util.Map;

public interface CmsPicService extends IBaseBiz<CmsPic> {
    List<CmsPic> findAllPic(CmsPic pic);

    Integer getAllCount(CmsPic pic);

    List<Map<String,Object>> findByParam(Integer place);

    List<CmsPic> findPicByPlace(CmsPic cmsPic);

    Integer findPicCount(Integer place, Integer i);

    void updateCmsPic(CmsPic cmsPic);
}