package com.ycjcjy.gene.service.impl;

import net.onebean.core.BaseBiz;
import net.onebean.core.Condition;
import com.ycjcjy.gene.dao.SysRoleUserDao;
import com.ycjcjy.gene.model.SysRoleUser;
import com.ycjcjy.gene.service.SysRoleUserService;
import net.onebean.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleUserServiceImpl extends BaseBiz<SysRoleUser, SysRoleUserDao> implements SysRoleUserService {

    @Override
    public void deleteByUserId(Long userId) {
        baseDao.deleteByUserId(userId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        baseDao.deleteByRoleId(roleId);
    }

    @Override
    public void save(SysRoleUser entity) {
        List<Condition> params = new ArrayList<>();
        Condition c1 = Condition.parseCondition("sys_role_id@int@eq$");
        c1.setValue(entity.getSys_role_id());
        Condition c2 = Condition.parseCondition("sys_user_id@int@eq$");
        c2.setValue(entity.getSys_user_id());
        params.add(c1);
        params.add(c2);
        if(CollectionUtil.isEmpty(this.find(null,params))){
            super.save(entity);
        }
    }
}