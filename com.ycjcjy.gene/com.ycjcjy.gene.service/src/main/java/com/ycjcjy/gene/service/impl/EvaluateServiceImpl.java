package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.Evaluate;
import com.ycjcjy.gene.service.EvaluateService;
import com.ycjcjy.gene.dao.EvaluateDao;

import java.util.List;

@Service
public class EvaluateServiceImpl extends BaseBiz<Evaluate, EvaluateDao> implements EvaluateService{
    @Override
    public List<Evaluate> getAllEvaluate(Evaluate evaluate){
        return baseDao.getAllEvaluate(evaluate);
    }
    @Override
    public Integer pageCount(Evaluate evaluate){
        return baseDao.pageCount(evaluate);
    }
    @Override
    public Evaluate getDetail (Integer id){
        return baseDao.getDetail(id);
    }

    @Override
    public List<Evaluate> getAllForWeb(Evaluate evaluate) {
        return baseDao.getEvaluatesForWeb(evaluate);
    }

    @Override
    public Integer getAllCount(Evaluate evaluate) {
        return baseDao.getAllCounts(evaluate);
    }
}