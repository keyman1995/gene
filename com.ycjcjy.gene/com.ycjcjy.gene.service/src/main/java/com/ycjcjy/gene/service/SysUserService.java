package com.ycjcjy.gene.service;

import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SysUser;

import java.util.List;

public interface SysUserService extends IBaseBiz<SysUser> {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    SysUser findByUserName(String username);

    SysUser findUserById(Integer userId);

    SysUser findUserByPhone(String phone);

    SysUser findByOpenId(String openId);

    /**
     * 根据机构ID查找用户
     * @param ordId
     * @return
     */
    List<SysUser> findUserByOrgID(Object ordId);
    /**
     * 用于案场端
     * @param id
     * @return
     */
    SysUser findUserAndCaseById(Integer id);

    List<SysUser> findMangers();
    List<SysUser> findCoffeeMaker(Long field_id);

    List<SysUser> findThisManger(Long field_id);

    int updateSalerCanSend(int canSend,int id);
    int updateSalerCanSendCard(int canSend,int id);
}