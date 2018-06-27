package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.CardTicketMaster;
import com.ycjcjy.gene.model.CustomerUser;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CardTicketSalve;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CardTicketSalveService extends IBaseBiz<CardTicketSalve> {

    void bathInsert(CardTicketMaster entity);

    /**
     * 发卡
     * @param saleId 销售
     * @param pid masterId
     * @param num 数量
     * @return
     */
    int giveTicketToSale(String saleId, String pid, String num);


    /**
     * 查询可发的卡
     * @param saleId
     * @param pid
     * @return
     */
    int sendCardToUser(int saleId, int pid, CustomerUser user,int type) throws Exception;

    int sendCardToSaler(String json,int pid);

    CardTicketSalve canSendSlave(int saleId,int pid);

    void upDateCardIsSend(Long id,String state);

    Map<String,Object> confirmTheCard(Long cardId, Long userId,Long slaveId);

    CardTicketSalve getBySlaveById(Long id);


}