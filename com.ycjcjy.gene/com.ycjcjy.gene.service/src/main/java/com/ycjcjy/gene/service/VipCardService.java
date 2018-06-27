package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.VipCard;

import java.util.List;

public interface VipCardService extends IBaseBiz<VipCard> {
    List<VipCard> findAllCard(VipCard card);

    Integer getAllCount(VipCard card);

    VipCard getByCardNo(String cardNo);
}