package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CardUserRela;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardUserRelaDao extends BaseDao<CardUserRela> {
    /**
     * 更新过期卡状态，用于定时器
     * @param time
     * @return
     */
    int updateSaleStateGuoQi(@Param("nowTime")String time);
	List<CardUserRela> getUserCard (@Param(value = "user_id")String user_id,@Param(value = "state")String state,@Param(value = "card_type") String card_type,@Param("base_offset")String base_offset, @Param("base_pageSize")String base_pageSize);
    CardUserRela getCardDetail(@Param(value = "id")String id);
    Integer getCount(@Param(value = "user_id")String user_id,@Param(value = "state")String state,@Param(value = "card_type") String card_type);

    List<CardUserRela> queryCardsByTime(@Param(value = "nowHour")String nowHour,@Param(value = "newTime")String newTime);

    List<CardUserRela> queryCardsByTimeAndUserId(@Param(value = "nowTime")String nowHour,@Param(value = "userId")String userId,@Param(value = "courseId")String courseId);
    Integer queryCardsByTimeAndUserIdCount(@Param(value = "nowTime")String nowHour,@Param(value = "userId")String userId,@Param(value = "courseId")String courseId);
    Integer queryCardSendCount(@Param(value = "userId")String userId,@Param(value = "courseId")String courseId);

    CardUserRela getCardIsExist(@Param(value = "salveId")Integer salveId);
}