package com.ycjcjy.gene.service.impl;

import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.dao.SysPermissionRoleDao;
import com.ycjcjy.gene.model.SysPermissionRole;
import com.ycjcjy.gene.service.SysPermissionRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysPermissionRoleServiceImpl extends BaseBiz<SysPermissionRole, SysPermissionRoleDao> implements SysPermissionRoleService{
    @Override
    public List<SysPermissionRole> getRolePremissionByRoleId(Long roleId) {
        return baseDao.getRolePremissionByRoleId(roleId);
    }

    @Override
    public void deteleByRoleId(Long roleId) {
        baseDao.deteleByRoleId(roleId);
    }

    @Override
    public void deteleByPermissionId(Long permissionId) {
        baseDao.deteleByPermissionId(permissionId);
    }

    @Override
    public void insertBatch(String pids,String rid) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map;
        String[] arr = pids.split(",");
        for (String s : arr) {
            map = new HashMap<>();
            map.put("permission_id",s);
            map.put("role_id",rid);
            list.add(map);
        }
        baseDao.insertBatch(list);
    }
}