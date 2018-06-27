package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.dao.LockerKeyDao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LockerKeyServiceImpl extends BaseBiz<LockerKey, LockerKeyDao> implements LockerKeyService{

    @Autowired
    private CardUserRelaService cardUserRelaService;
    @Autowired
    private CardTicketSalveService cardTicketSalveService;
    @Autowired
    private OrderSubService orderSubService;

    public List<Integer> findByCaseFieldId (Integer case_field_id){
        List<Integer> list=baseDao.findByCaseFieldId(case_field_id);
        return list;
    }

    public boolean containsNum(Integer num, List<Integer> list){
        boolean istrue = false;
        for (Integer i :list){
            if (i == num){
                istrue = true;
                break;
            }
        }
        return istrue;
    }

    public boolean containsId(Long num, List<Long> list){
        boolean istrue = false;
        for (Long i :list){
            if (i == num){
                istrue = true;
                break;
            }
        }
        return istrue;
    }

    public Integer findUserId(Long case_field_id,Long userId){
        return baseDao.findUserId(case_field_id,userId);
    }

    public Integer keyBindCard(String id,String userId){
        try{
            String[]list = id.split("&");
            String cardId = list[0];
            String cardType = list[1];
            Calendar calendar = Calendar.getInstance();
            CardUserRela cardUserRela = cardUserRelaService.findById(cardId);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if(null==cardUserRela.getStart_time() || cardUserRela.getStart_time().equals("")){
                switch(cardType){
                    case "492":
                        calendar.add(Calendar.MONTH,3);
                        break;
                    case "493":
                        calendar.add(Calendar.YEAR,1);
                        break;
                    case "494":
                        calendar.add(Calendar.MONTH,1);
                        break;
                }
                cardUserRela.setStart_time(now);
                cardUserRela.setEnd_time(new Timestamp(calendar.getTimeInMillis()));
                cardUserRelaService.update(cardUserRela);

                return 0;
            }else if (cardType.equals("491")){
                Calendar calendar1=Calendar.getInstance();
                calendar1.add(Calendar.HOUR,-1);
                Timestamp startCard = new Timestamp(calendar1.getTimeInMillis());
                if(startCard.after(cardUserRela.getStart_time())){
                    cardUserRela.setState(1);
                    cardUserRelaService.update(cardUserRela);

                    return 0;
                }else {
                    return 2;
                }

            }else if (!cardType.equals("491") && null!=cardUserRela.getStart_time() || !cardUserRela.getStart_time().equals("")){
                return 0;
            }else {
                return 1;
            }


        }catch (Exception e){
            e.printStackTrace();
            return  1;
        }

    }

    public String keyBindCoach(String id,String userId,SysUser sysUser){
        String msg = "";
        Calendar StartC = Calendar.getInstance();
        StartC.add(Calendar.MINUTE,-30);
        Calendar endC = Calendar.getInstance();
        endC.add(Calendar.MINUTE,30);
        Timestamp start = new Timestamp(StartC.getTimeInMillis());
        Timestamp end = new Timestamp(endC.getTimeInMillis());
        OrderSub orderSub = orderSubService.findById(id.toString());
        Timestamp startTime = orderSub.getAppointment_start_time();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Integer org_id = sysUser.getField_id().intValue();
        if (org_id == orderSub.getCase_field_id()){
            if (startTime.before(end) && startTime.after(start)){
                orderSub.setOrder_state("3");
                orderSub.setVerification_time(now);
                orderSub.setVerification_case_field_id(org_id);
                orderSub.setVerification_user_id(sysUser.getId().intValue());
                orderSubService.update(orderSub);
                msg= "";
            }else {
                msg = "您预约的不是这个时间";
            }
        }else {
            msg = "您的课程不在此案场。";
        }


        return msg;
    }
}