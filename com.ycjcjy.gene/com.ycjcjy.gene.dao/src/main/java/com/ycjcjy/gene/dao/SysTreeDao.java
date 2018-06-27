package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.SysTree;

import java.util.List;

public interface SysTreeDao extends BaseDao<SysTree> {

    public List<SysTree> findList(int parentid);
}