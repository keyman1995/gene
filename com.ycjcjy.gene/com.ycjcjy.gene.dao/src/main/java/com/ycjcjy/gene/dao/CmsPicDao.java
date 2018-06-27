package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CmsPic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CmsPicDao extends BaseDao<CmsPic> {
    List<CmsPic> findAllPic(CmsPic pic);

    Integer getAllCount(CmsPic pic);


    List<Map<String,Object>> findOneCmsByParam(@Param(value = "place") Integer place);

    List<CmsPic> findPicByPlace(CmsPic cmsPic);

    Integer findPicCount(@Param(value = "place")Integer place,@Param(value = "sec_place") Integer sec_place);

    void updateCms(CmsPic cmsPic);
}