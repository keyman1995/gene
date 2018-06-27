package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.Evaluate;

import java.util.List;

public interface EvaluateService extends IBaseBiz<Evaluate> {
    List<Evaluate> getAllEvaluate(Evaluate evaluate);

    Integer pageCount(Evaluate evaluate);
    Evaluate getDetail (Integer id);

    List<Evaluate> getAllForWeb(Evaluate evaluate);

    Integer getAllCount(Evaluate evaluate);
}