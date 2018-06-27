package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.dao.CardTicketSalveDao;
import com.ycjcjy.gene.model.CardTicketMaster;
import com.ycjcjy.gene.model.CardTicketSalve;
import com.ycjcjy.gene.model.CardUserRela;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.service.CardCourdeBasicService;
import com.ycjcjy.gene.service.CardTicketSalveService;
import com.ycjcjy.gene.service.CardUserRelaService;
import com.ycjcjy.gene.service.CustomerUserService;
import net.onebean.core.BaseBiz;
import net.onebean.util.DateUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class CardTicketSalveServiceImpl extends BaseBiz<CardTicketSalve, CardTicketSalveDao> implements CardTicketSalveService{

    @Autowired
    private CardUserRelaService cardUserRelaService;
    @Autowired
    private CustomerUserService customerUserService;
    @Autowired
    private CardCourdeBasicService cardCourdeBasicService;

    private static final String HAVEACCEPT = "6";
    private static final Integer userState = 0;
    private static final Integer COURSE_CARD_TYPE=0;
    private static final Integer COURSE_SOURCE_TYPE=0;
    /**
     * 生成体验券
     * @param entity
     */

    @Override
    public void bathInsert(CardTicketMaster entity) {
        int num = entity.getCount();
        List<CardTicketSalve> list = new ArrayList<>();
        for (int i=0;i<num;i++){
            CardTicketSalve cardTicketSalve = new CardTicketSalve();
            cardTicketSalve.setPid(entity.getId().intValue());
            cardTicketSalve.setState("0");
            list.add(cardTicketSalve);
        }
        baseDao.bathInsert(list);

    }

    @Override
    public int giveTicketToSale(String saleId, String pid, String num) {
        return baseDao.giveTicketToSale(saleId, pid, num,DateUtils.dateToString(new Date()));
    }



    @Override
    public int sendCardToUser(int saleId, int pid,CustomerUser user,int type) throws Exception{
        int result = 0;
        CardTicketSalve card = baseDao.queryCanSendCardByCondition(saleId, pid);
        if(card!=null){


            //找卡信息
            Map<String,Object> infoMap = baseDao.queryCardInfo(Integer.valueOf(card.getId().toString()));


            if(type==0){

                //一个人一个卡只能一次

                int count = cardUserRelaService.queryCardSendCount(String.valueOf(user.getId()),infoMap.get("id").toString());
                if(count>=1){
                    return 2;
                }
            }

            card.setTouser_time(new Date());
            card.setState("3");
            baseDao.update(card);

            //判断是否存在salveId
            CardUserRela relaExist = cardUserRelaService.getCardIsExist(Integer.valueOf(card.getId().toString()));

            if(relaExist!=null){
                return 3;
            }

            CardUserRela rela = new CardUserRela();
            rela.setCard_id(Integer.valueOf(infoMap.get("id").toString()));
            rela.setSalve_id(Integer.valueOf(card.getId().toString()));
            rela.setCreate_time(new Timestamp(new Date().getTime()));
            rela.setSource(saleId>0?0:2);
            rela.setState(0);
            rela.setUser_id(Integer.valueOf(user.getId().toString()));
            rela.setCard_type(Integer.valueOf(infoMap.get("ticket_type").toString()));
            if(infoMap.get("cardEndTime")!=null){
                rela.setEnd_time(DateUtils.dateToTimeStamp(DateUtils.stringToDate(infoMap.get("cardEndTime").toString())));
            }
            if(infoMap.get("cardStartTime")!=null){
                rela.setStart_time(DateUtils.dateToTimeStamp(DateUtils.stringToDate(infoMap.get("cardStartTime").toString())));
            }
            cardUserRelaService.save(rela);

            customerUserService.update(user);

            if(type==1){

                SmsUtil.sendTicketSuccess(user.getTel(),user.getUsername()+(user.getSex()==0?"先生":"女士"),infoMap.get("cardName").toString());
            }
            result = 1;

        }

        return result;
    }

    @Override
    public int sendCardToSaler(String json,int pid) {

        JSONArray array = JSONArray.fromObject(json); //id count
        Integer[] idArr = new Integer[array.size()];
        Integer[] numArr = new Integer[array.size()];

        for(int i=0;i<array.size();i++){
            idArr[i] = Integer.valueOf(array.getJSONObject(i).getString("saleId"));
            numArr[i] = Integer.valueOf(array.getJSONObject(i).getString("count"));
        }

        Map<String,Object> map = new HashMap<>();
        map.put("list",java.util.Arrays.asList(idArr));
        map.put("pid",pid);
        baseDao.takeBackTicketToSale(map);
        for(int i=0;i<idArr.length;i++){

            if(numArr[i]>0){
                this.giveTicketToSale(String.valueOf(idArr[i]),String.valueOf(pid),String.valueOf(numArr[i]));
            }

        }
        return 0;
    }

    @Override
    public CardTicketSalve canSendSlave(int saleId, int pid) {
        return baseDao.queryCanSendCardByCondition(saleId,pid);
    }

    @Override
    public void upDateCardIsSend(Long id,String state) {
        baseDao.upDateCardIsSended(id,state);
    }

    @Override
    public CardTicketSalve getBySlaveById(Long id) {
        return baseDao.getById(id);
    }

    /**
     * 用户已经领取记录,并更改状态
     * @param cardId
     * @param userId
     */
    @Override
    public Map<String,Object> confirmTheCard(Long cardId, Long userId,Long slaveId) {
        Map<String,Object> result = new HashMap<String, Object>();
        CardUserRela cardUserRela = new CardUserRela();
        try{
            Integer count = baseDao.getCountById(slaveId);
            if(count!=0){//表示该卡已经被领取
                result.put("isAccepted",true);
                return result;
            }else{
                result.put("isAccepted",false);
                baseDao.upDateCardIsSended(slaveId,HAVEACCEPT);
                cardUserRela.setState(userState);
                cardUserRela.setCard_id(Integer.valueOf(String.valueOf(cardId)));
                cardUserRela.setUser_id(Integer.valueOf(String.valueOf(userId)));
                cardUserRela.setCard_type(COURSE_CARD_TYPE);
                cardUserRela.setSource(COURSE_SOURCE_TYPE);
                cardUserRela.setCreate_time(DateUtils.dateToTimeStamp(new Date()));
                cardUserRela.setSalve_id(Integer.valueOf(String.valueOf(slaveId)));
                cardUserRelaService.save(cardUserRela);
                result.put("result",true);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("result",false);
        }
        return result;
    }
}