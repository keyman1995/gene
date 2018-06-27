package com.ycjcjy.gene.service.impl;

import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.dao.SysTreeDao;
import com.ycjcjy.gene.model.SysTree;
import com.ycjcjy.gene.service.SysTreeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysTreeServiceImpl extends BaseBiz<SysTree, SysTreeDao> implements SysTreeService {
    @Override
    public List<SysTree> findList(Integer parentid) {
        return baseDao.findList(parentid);
    }
}