package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.CardSwimingFitness;
import com.ycjcjy.gene.model.SysUser;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CardSwimingFitnessBind;

public interface CardSwimingFitnessBindService extends IBaseBiz<CardSwimingFitnessBind> {
    /**
     * 充值游泳健身卡
     * @return
     */
    CardSwimingFitnessBind saves(CardSwimingFitnessBind entity, SysUser create_user);
}