package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CardUserRela;
import com.ycjcjy.gene.service.CardUserRelaService;
import com.ycjcjy.gene.dao.CardUserRelaDao;

import java.util.List;

@Service
public class CardUserRelaServiceImpl extends BaseBiz<CardUserRela, CardUserRelaDao> implements  CardUserRelaService{
    @Override
    public List<CardUserRela> getAllCard(String user_id, String state,String card_type,String base_offset,String base_pageSize){
        return baseDao.getUserCard(user_id,state,card_type,base_offset,base_pageSize);
    }
    @Override
    public CardUserRela getCardDetail(String id){
        return baseDao.getCardDetail(id);
    }
    @Override
    public Integer getCount(String user_id,String state,String card_type){
        return baseDao.getCount(user_id,state,card_type);
    }

    @Override
    public List<CardUserRela> queryCardsByTime(String nowHour,String newTime) {
        return baseDao.queryCardsByTime(nowHour,newTime);
    }

    @Override
    public List<CardUserRela> queryCardsByTimeAndUserId(String nowHour, String userId, String courseId) {
        return baseDao.queryCardsByTimeAndUserId(nowHour,userId,courseId);
    }

    @Override
    public Integer queryCardsByTimeAndUserIdCount(String nowHour, String userId, String courseId) {
        return baseDao.queryCardsByTimeAndUserIdCount(nowHour,userId,courseId);
    }

    @Override
    public Integer queryCardSendCount(String userId, String courseId) {
        return baseDao.queryCardSendCount(userId, courseId);
    }

    @Override
    public CardUserRela getCardIsExist(Integer salveId) {
        return baseDao.getCardIsExist(salveId);
    }
}