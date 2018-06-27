package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SysTree;

import java.util.List;

public interface SysTreeService extends IBaseBiz<SysTree> {
    public List<SysTree> findList(Integer parentid);
}