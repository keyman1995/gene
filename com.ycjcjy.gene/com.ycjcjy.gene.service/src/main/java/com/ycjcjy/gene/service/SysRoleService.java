package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SysRole;

import java.util.List;

public interface SysRoleService extends IBaseBiz<SysRole> {

    /**
     * 根据用户登录名查询用户所有角色
     * @author 0neBean
     * @param userId
     * @return List<SysRole>
     */
    public List<SysRole> findRolesByUserId(Long userId);
}