package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SysPermissionRole;

import java.util.List;

public interface SysPermissionRoleService extends IBaseBiz<SysPermissionRole> {
    /**
     * 获取角色菜单
     * @param roleId
     * @return
     */
    List<SysPermissionRole> getRolePremissionByRoleId(Long roleId);

    /**
     * 根据roleId删除数据
     * @param roleId
     */
    void deteleByRoleId(Long roleId);

    /**
     * 根据permissionId删除数据
     * @param permissionId
     */
    void deteleByPermissionId(Long permissionId);

    /**
     * 批量插入
     * @param pids
     * @param rid
     */
    void insertBatch(String pids, String rid);
}