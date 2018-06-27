package com.ycjcjy.gene.dao;
import com.ycjcjy.gene.model.CardTicketMaster;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CardTicketSalve;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CardTicketSalveDao extends BaseDao<CardTicketSalve> {

    void bathInsert(List<CardTicketSalve> lc);

    /**
     * 发卡
     * @param saleId 销售
     * @param pid masterId
     * @param num 数量
     * @return
     */
    int giveTicketToSale(@Param("saleId") String saleId,@Param("pId") String pid,@Param("num") String num,@Param("tosaleTime") String tosaleTime);

    /**
     * 收卡
     * @return
     */
    int takeBackTicketToSale(Map<String,Object> map);

    /**
     * 查询可发的卡
     * @param saleId
     * @param pid
     * @return
     */
    CardTicketSalve queryCanSendCardByCondition(@Param("saleId") int saleId,@Param("pId") int pid);
    Map<String,Object> queryCardInfo(@Param("id") int id);

    /**
     * 更新过期卡状态，用于定时器
     * @param time
     * @return
     */
    int updateSaleStateGuoQi(@Param("nowTime")String time);

    int findLeftCountByMonth(@Param("caseId") int caseId,@Param("month") int month);

    /**
     * 点单
     * @param saleId 销售
     * @param num 数量
     * @return
     */
    int costGoodsToSale(@Param("saleId") String saleId,@Param("caseId") String caseId,@Param("num") String num,@Param("tosaleTime") String tosaleTime,@Param("month") String month);


    int getMaxMasterIdForGood(@Param("caseId") String caseId,@Param("month") String month);

    /**
     * 将子卡的状态更新为已分享
     * @param id
     */
    void upDateCardIsSended(@Param(value = "id")Long id,@Param(value = "state")String state);

    /**
     * 查看该卡是否被领取
     * @param id
     * @return
     */
    Integer getCountById(@Param(value = "id")Long id);

    /**
     * 根据id子卡信息
     */
    CardTicketSalve getById(@Param(value = "id")Long id);

}