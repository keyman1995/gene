package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.model.CardSwimingFitnessBind;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.service.SysCaseFieldService;
import net.onebean.core.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CardSwimingFitness;
import com.ycjcjy.gene.service.CardSwimingFitnessService;
import com.ycjcjy.gene.dao.CardSwimingFitnessDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
public class CardSwimingFitnessServiceImpl extends BaseBiz<CardSwimingFitness, CardSwimingFitnessDao> implements CardSwimingFitnessService{
}