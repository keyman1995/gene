package com.ycjcjy.gene.service.impl;

import com.ycjcjy.gene.VO.TicketCardVo;
import com.ycjcjy.gene.VO.TicketSalerVo;
import com.ycjcjy.gene.dao.CardTicketMasterDao;
import com.ycjcjy.gene.dao.CardTicketSalveDao;
import com.ycjcjy.gene.dao.CardUserRelaDao;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.*;
import net.onebean.core.BaseBiz;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CardTicketMasterServiceImpl extends BaseBiz<CardTicketMaster, CardTicketMasterDao> implements CardTicketMasterService {

    @Autowired
    private CardGoodsBasicService cardGoodsBasicService;
    @Autowired
    private CardTicketSalveService cardTicketSalveService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;
    @Autowired
    private CardTicketSalveDao cardTicketSalveDao;
    @Autowired
    private CardUserRelaDao cardUserRelaDao;
    @Autowired
    private SysUserService sysUserService;

    public void saveTicket(CardTicketMaster entity) {


        //创建时间
        Timestamp timestamp =new Timestamp(System.currentTimeMillis());
        entity.setCreate_time(timestamp);
        //添加主卡信息

        baseDao.add(entity);
        //添加副卡信息
        cardTicketSalveService.bathInsert(entity);
    }

    @Override
    public List<Map<String, Object>> cardList(String seachType) {
        List<Map<String, Object>> res = new ArrayList<>();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        switch (seachType) {
            case "0":
                List<CardCourdeBasic> bl = baseDao.getAllCourseCard(now);
                for (CardCourdeBasic b : bl) {
                    Map<String, Object> m = new HashedMap();
                    m.put("id", b.getId());
                    m.put("name", b.getName());
                    m.put("price",b.getPrice());
                    res.add(m);
                }
                break;
            case "1":
                List<CardGoodsBasic> al = baseDao.getAllGoodsCard(now);
                for (CardGoodsBasic a : al) {
                    Map<String, Object> m = new HashedMap();
                    m.put("id", a.getId());
                    m.put("name", a.getName());
                    m.put("price",a.getPrice());
                    res.add(m);
                }
                break;
            case "2":
                List<CardGymBasic> cl = baseDao.getAllGymCard(now);
                for (CardGymBasic c : cl) {
                    Map<String, Object> m = new HashedMap();
                    m.put("id", c.getId());
                    if ("491".equals(c.getCard_gym_type())) {
                        m.put("name", "(体验卡)" + c.getName());
                    } else if ("492".equals(c.getCard_gym_type())) {
                        m.put("name", "(季卡)" + c.getName());
                    } else if ("493".equals(c.getCard_gym_type())) {
                        m.put("name", "(年卡)" + c.getName());
                    } else if("494".equals(c.getCard_gym_type())){
                        m.put("name", "(月卡)" + c.getName());
                    }
                    m.put("price",c.getPrice());
                    res.add(m);
                }
                break;
            default:
                break;
        }

        return res;
    }

    @Transactional
    @Override
    public String purchase(CardTicketMaster cardTicketMaster, Long FieldId){
        String[] idsArr = cardTicketMaster.getIds().substring(0, cardTicketMaster.getIds().length() - 1).split(",");
        String[] numArr = cardTicketMaster.getNum().substring(0, cardTicketMaster.getNum().length() - 1).split(",");
        String[] price = cardTicketMaster.getPrices().substring(0, cardTicketMaster.getPrices().length() - 1).split(",");
        String[] ticketType = cardTicketMaster.getTicketTypes().substring(0, cardTicketMaster.getTicketTypes().length() - 1).split(",");
        List<CardTicketMaster> ls = new ArrayList<>();
        int len = idsArr.length;
        int total = 0;
        for (int i = 0; i < len; i++) {
            if (Integer.valueOf(numArr[i]) != 0) {
                CardTicketMaster c = new CardTicketMaster();
                c.setId(Long.valueOf(idsArr[i]));
                c.setNum(numArr[i]);
                c.setPrice(Double.valueOf(price[i]));
                total += Integer.valueOf(numArr[i]) * Integer.valueOf(price[i]);
                ls.add(c);
                List<SysUser>userList = sysUserService.findThisManger(FieldId);
                if(ticketType[i].equals("1")){
                    for (int j =0; j<userList.size();j++){
                        SysUser sysUser=userList.get(j);
                        sysUser.setMsg_state(0);
                        sysUserService.update(sysUser);
                    }
                }

            }
        }
        String flag = "成功";
        try {
            // 扣钱
            if (sysCaseFieldService.updateAvailById(-Double.valueOf(total), FieldId) > 0) {
                //挪动卡归属
                for (CardTicketMaster c : ls) {
                    Map<String, Object> param = new HashedMap();
                    param.put("pid", c.getId());
                    param.put("num", c.getNum());
                    Integer nums = baseDao.purchase(param);

                    /*if (nums != Integer.valueOf(c.getNum())) {
                        //挪动卡归属失败
                        flag = "挪动卡归属失败";
                    }*/
                }
            } else {
                //扣款失败
                flag = "扣款失败";
            }
        } catch (Exception e) {
            flag = "购买失败";
            e.printStackTrace();
        }

        return flag;
    }




    @Override
    public List<CardTicketMaster> findList(CardTicketMaster cardTicketMaster){
        return baseDao.findList(cardTicketMaster);
    }
    @Override
    public Integer getCount(CardTicketMaster cardTicketMaster){
        return baseDao.getCount(cardTicketMaster);
    }

    @Override
    public List<TicketCardVo> queryCardListInCaseTerminal(String ticketType,String nowTime, String saleId, String caseId, String base_offset, String base_pageSize) {
        return baseDao.queryCardListInCaseTerminal(ticketType,nowTime,saleId,caseId,base_offset,base_pageSize);
    }

    @Override
    public Map<String,Object> queryCardListInCaseTerminalCount(String ticketType, String nowTime, String saleId, String caseId) {
        return baseDao.queryCardListInCaseTerminalCount(ticketType,nowTime,saleId,caseId);
    }

    @Override
    public List<TicketSalerVo> querySaleVoListByMasterId(String pId, String caseId) {
        return baseDao.querySaleVoListByMasterId(pId, caseId);
    }

    @Override
    public List<TicketSalerVo> querySaleRecord(String saleId,String ticketType,String caseId, String base_offset, String base_pageSize) {
        return baseDao.querySaleRecord(saleId, ticketType,caseId,base_offset, base_pageSize);
    }

    @Override
    public Map<String,Object> querySaleRecordCount(String saleId,String ticketType,String caseId) {
        return baseDao.querySaleRecordCount(saleId,ticketType,caseId);
    }

    @Override
    public List<TicketSalerVo> querySaleRecordFromZhuGuan(String saleId, String caseId) {
        return baseDao.querySaleRecordFromZhuGuan(saleId, caseId);
    }

    @Override
    public TicketCardVo queryMasterDetailById(String id,String saleId,String nowTime) {
        return baseDao.queryMasterDetailById(id,saleId,nowTime);
    }

    @Override
    public int updateCardTicketGuoQi(String time) {

         List<Map<String,Object>> list = baseDao.getTicketGuoQiPriceGroupByCaseId(time);



        if(list!=null){
            //循环list，返回案场钱
            for(Map<String,Object> map:list){
                sysCaseFieldService.updateAvailById(Double.valueOf(map.get("totalPrice").toString()),Long.valueOf(map.get("org_id").toString()));
            }
        }
        cardTicketSalveDao.updateSaleStateGuoQi(time);
        cardUserRelaDao.updateSaleStateGuoQi(time);

        return 0;
    }

    @Override
    public int saleTicketCount(Integer saleId, Integer ticketId) {
        return baseDao.saleTicketCount(saleId, ticketId);
    }

    @Override
    public int updateSaleGoodsTicketState(String saleId, String ticketId, String num, String nowTime) {
        return baseDao.updateSaleGoodsTicketState(saleId, ticketId, num, nowTime);
    }

    @Override
    public TicketCardVo queryQuDaoCardById(String id, String nowTime) {
        return baseDao.queryQuDaoCardById(id,nowTime);
    }

    @Override
    public Map<String, Object> getShareDetail(Long id) {
        return baseDao.getShareDetail(id);
    }
}