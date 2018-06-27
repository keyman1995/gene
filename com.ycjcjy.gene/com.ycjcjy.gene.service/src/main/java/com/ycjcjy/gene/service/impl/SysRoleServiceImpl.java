package com.ycjcjy.gene.service.impl;

import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.dao.SysRoleDao;
import com.ycjcjy.gene.model.SysRole;
import com.ycjcjy.gene.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseBiz<SysRole, SysRoleDao> implements SysRoleService {
    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return baseDao.findRolesByUserId(userId);
    }
}