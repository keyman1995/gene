package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.Evaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluateDao extends BaseDao<Evaluate> {
    List<Evaluate> getAllEvaluate(Evaluate evaluate);

    Integer pageCount(Evaluate evaluate);

    Evaluate getDetail (@Param(value = "id") Integer id);

    List<Evaluate> getEvaluatesForWeb(Evaluate evaluate);

    Integer getAllCounts(Evaluate evaluate);
}