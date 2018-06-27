package com.ycjcjy.gene.dao;

import com.ycjcjy.gene.VO.TicketCardVo;
import com.ycjcjy.gene.VO.TicketSalerVo;
import com.ycjcjy.gene.model.CardCourdeBasic;
import com.ycjcjy.gene.model.CardGoodsBasic;
import com.ycjcjy.gene.model.CardGymBasic;
import com.ycjcjy.gene.model.CardTicketMaster;
import net.onebean.core.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CardTicketMasterDao extends BaseDao<CardTicketMaster> {

    /**
     * 案场端大卡分页
     * @param nowTime  现在时间，判断是否过期
     * @param saleId  销售id（销售端）
     * @param caseId  案场id（必填）
     * @param base_offset
     * @param base_pageSize
     * @return
     */
    List<TicketCardVo> queryCardListInCaseTerminal(@Param("ticketType")String ticketType,@Param("nowTime") String nowTime,@Param("saleId") String saleId,@Param("caseId") String caseId,@Param("base_offset") String base_offset,@Param("base_pageSize") String base_pageSize);
    /**
     * 案场端大卡分页
     * @param nowTime  现在时间，判断是否过期
     * @param saleId  销售id（销售端）
     * @param caseId  案场id（必填）
     * @return
     */
    Map<String,Object> queryCardListInCaseTerminalCount(@Param("ticketType")String ticketType,@Param("nowTime") String nowTime,@Param("saleId") String saleId,@Param("caseId") String caseId);


    /**
     * 分发卡页面（大卡详情页）
     * @param pId masterId
     * @param caseId  案场id
     * @return
     */
    List<TicketSalerVo> querySaleVoListByMasterId(@Param("pId") String pId,@Param("caseId") String caseId);

    /**
     * 销售发卡记录分页
     * @param saleId
     * @param base_offset
     * @param base_pageSize
     * @return
     */
    List<TicketSalerVo> querySaleRecord(@Param("saleId") String saleId,@Param("ticketType")String ticketType,@Param("caseId") String caseId,@Param("base_offset") String base_offset,@Param("base_pageSize") String base_pageSize);
    /**
     * 销售发卡记录分页
     * @param saleId
     * @return
     */
    Map<String,Object> querySaleRecordCount(@Param("saleId") String saleId,@Param("ticketType")String ticketType,@Param("caseId") String caseId);

    /**
     * 销售发卡记录汇总（主管端）
     * @param saleId
     * @param caseId
     * @return
     */
    List<TicketSalerVo> querySaleRecordFromZhuGuan(@Param("saleId") String saleId,@Param("caseId") String caseId);

    /**
     * 查询卡面详情
     * @param id
     * @return
     */
    TicketCardVo queryMasterDetailById(@Param("id")String id,@Param("saleId") String saleId,@Param("nowTime") String nowTime);

    /**
     * 获取过期的卡，用于定时器
     * @param time
     * @return
     */
    List<Map<String,Object>> getTicketGuoQiPriceGroupByCaseId(@Param("nowTime")String nowTime);

    int saleTicketCount(@Param("saleId")Integer saleId,@Param("ticketId")Integer ticketId);
    int updateSaleGoodsTicketState(@Param("saleId")String saleId,@Param("ticketId")String ticketId,@Param("num")String num,@Param("nowTime")String nowTime);
    //获取列表页
    List<CardTicketMaster> findList(CardTicketMaster query);
    //获取健身卡
    List<CardGymBasic> getAllGymCard(@Param(value = "now") Timestamp now);
    //获取商品卡
    List<CardGoodsBasic> getAllGoodsCard(@Param(value = "now") Timestamp now);
    //获取课程卡
    List<CardCourdeBasic> getAllCourseCard(@Param(value = "now") Timestamp now);
    CardTicketMaster findById(@Param(value = "id") Integer id);

    CardTicketMaster findTicketManager(Integer id);
    //获取总条数
    Integer getCount(CardTicketMaster cardTicketMaster);

    int purchase(Map<String, Object> param);

    TicketCardVo queryQuDaoCardById(@Param("id")String id,@Param("nowTime") String nowTime);

    Map<String,Object> getShareDetail(@Param(value = "id")Long id);
}