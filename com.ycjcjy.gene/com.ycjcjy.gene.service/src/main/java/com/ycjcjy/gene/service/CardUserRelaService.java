package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.CardUserRela;
import net.onebean.core.IBaseBiz;

import java.util.List;

public interface CardUserRelaService extends IBaseBiz<CardUserRela> {
    List<CardUserRela> getAllCard(String user_id,String state,String card_type,String base_offset,String base_pageSize);
    CardUserRela getCardDetail(String id);
    CardUserRela getCardIsExist(Integer salveId);
    Integer getCount(String user_id,String state,String card_type);

    List<CardUserRela> queryCardsByTime(String nowHour,String newTime);

    List<CardUserRela> queryCardsByTimeAndUserId(String nowHour,String userId,String courseId);
    Integer queryCardsByTimeAndUserIdCount(String nowHour,String userId,String courseId);
    Integer queryCardSendCount(String userId,String courseId);
}