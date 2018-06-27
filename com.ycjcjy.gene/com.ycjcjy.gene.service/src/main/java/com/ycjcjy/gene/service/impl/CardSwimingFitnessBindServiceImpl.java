package com.ycjcjy.gene.service.impl;

import com.ycjcjy.gene.dao.CardSwimingFitnessBindDao;
import com.ycjcjy.gene.model.CardSwimingFitness;
import com.ycjcjy.gene.model.CardSwimingFitnessBind;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.service.CardSwimingFitnessBindService;
import com.ycjcjy.gene.service.CardSwimingFitnessService;
import com.ycjcjy.gene.service.SysCaseFieldService;
import net.onebean.core.BaseBiz;
import net.onebean.core.form.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
public class CardSwimingFitnessBindServiceImpl extends BaseBiz<CardSwimingFitnessBind, CardSwimingFitnessBindDao> implements CardSwimingFitnessBindService{

    @Autowired
    private CardSwimingFitnessService cardSwimingFitnessService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;

    @Override
    public CardSwimingFitnessBind saves(CardSwimingFitnessBind entity, SysUser create_user) {
        if (entity.getId() == null) {
            Timestamp create_time = new Timestamp(System.currentTimeMillis());

            entity.setCreate_user_id(create_user.getId());
            entity.setCreate_user_name(create_user.getRealname());
            CardSwimingFitness card = cardSwimingFitnessService.findById(entity.getCard_id().toString());
            entity.setCreate_time(create_time);
            this.save(entity);
            Calendar calendar = Calendar.getInstance();// 得到一个Calendar的实例
            if(null == entity.getEnd_crad_time()){
                entity.setEnd_crad_time(entity.getCreate_time());
            }
            calendar.setTime(new Date(entity.getEnd_crad_time().getTime())); //设置时间为end_crad_time
            if (card.getTime_scope().equals("yk")){
                calendar.add(Calendar.MONTH, 1);//月份减+1
                entity.setEnd_crad_time(new Timestamp(calendar.getTimeInMillis()));
            }else if(card.getTime_scope().equals("nk")){
                calendar.add(Calendar.YEAR, 1); //年份+1
                entity.setEnd_crad_time(new Timestamp(calendar.getTimeInMillis()));
            }
            //扣钱
            sysCaseFieldService.cutMoney(Parse.toDouble(entity.getPrice(),2),create_user.getField_id());
        }
        this.save(entity);
        return entity;
    }
}