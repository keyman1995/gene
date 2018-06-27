package com.ycjcjy.gene.service;

import com.ycjcjy.gene.VO.TicketCardVo;
import com.ycjcjy.gene.VO.TicketSalerVo;
import com.ycjcjy.gene.model.CardTicketMaster;
import net.onebean.core.IBaseBiz;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CardTicketMasterService extends IBaseBiz<CardTicketMaster> {

    void saveTicket(CardTicketMaster entity);
    List<CardTicketMaster> findList(CardTicketMaster cardTicketMaster);
    Integer getCount(CardTicketMaster cardTicketMaster);
    List<Map<String,Object>> cardList(String seachType);
    String purchase(CardTicketMaster cardTicketMaster, Long FieldId);


    /**
     * 案场端大卡分页
     * @param nowTime  现在时间，判断是否过期
     * @param saleId  销售id（销售端）
     * @param caseId  案场id（必填）
     * @param base_offset
     * @param base_pageSize
     * @return
     */
    List<TicketCardVo> queryCardListInCaseTerminal(String ticketType,String nowTime, String saleId, String caseId, String base_offset, String base_pageSize);
    /**
     * 案场端大卡分页
     * @param nowTime  现在时间，判断是否过期
     * @param saleId  销售id（销售端）
     * @param caseId  案场id（必填）
     * @return
     */
    Map<String,Object> queryCardListInCaseTerminalCount(String ticketType, String nowTime, String saleId, String caseId);


    /**
     * 分发卡页面（大卡详情页）
     * @param pId masterId
     * @param caseId  案场id
     * @return
     */
    List<TicketSalerVo> querySaleVoListByMasterId(String pId, String caseId);

    /**
     * 销售发卡记录分页
     * @param saleId
     * @param base_offset
     * @param base_pageSize
     * @return
     */
    List<TicketSalerVo> querySaleRecord(String saleId,String ticketType,String caseId,String base_offset,String base_pageSize);
    /**
     * 销售发卡记录分页
     * @param saleId
     * @return
     */
    Map<String,Object> querySaleRecordCount(String saleId,String ticketType,String caseId);

    /**
     * 销售发卡记录汇总（主管端）
     * @param saleId
     * @param caseId
     * @return
     */
    List<TicketSalerVo> querySaleRecordFromZhuGuan(String saleId,String caseId);
    /**
     * 查询卡面详情
     * @param id
     * @return
     */
    TicketCardVo queryMasterDetailById( String id,String saleId,String nowTime);


    int updateCardTicketGuoQi(String time);

    int saleTicketCount(Integer saleId,Integer ticketId);

    int updateSaleGoodsTicketState(String saleId,String ticketId,String num,String nowTime);

    TicketCardVo queryQuDaoCardById(String id,String nowTime);

    Map<String,Object> getShareDetail(Long id);

}

