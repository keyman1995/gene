package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao extends BaseDao<SysUser> {
	
    public SysUser findByUserName(@Param("username") String username);

    SysUser findUserById(Integer id);

    SysUser findUserAndCaseById(Integer id);

    SysUser findUserByPhone(@Param("phone") String phone);


    SysUser findByOpenId(@Param("openId")String openId);

    List<SysUser> findMangers();
    List<SysUser> findThisManger(@Param("field_id") Long field_id);
    List<SysUser> findCoffeeMakerByCaseId(@Param("caseId")Integer caseId);

    List<SysUser> findCoffeeMaker(@Param("field_id") Long field_id);

    int updateSalerCanSend(@Param("canSend") int canSend,@Param("id") int id);
    int updateSalerCanSendCard(@Param("canSend") int canSend,@Param("id") int id);
}