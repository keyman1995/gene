package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.VipCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VipCardDao extends BaseDao<VipCard> {
    List<VipCard> findAllCard(VipCard card);

    Integer getAllCount(VipCard card);

    VipCard getByCardNo(@Param(value = "cardNo") String cardNo);
}