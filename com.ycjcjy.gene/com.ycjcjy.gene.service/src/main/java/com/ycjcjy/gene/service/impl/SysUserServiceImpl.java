package com.ycjcjy.gene.service.impl;

import com.ycjcjy.gene.dao.SysOrganizationDao;
import com.ycjcjy.gene.model.SysOrganization;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.dao.SysUserDao;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.service.SysUserService;
import net.onebean.core.Condition;
import net.onebean.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl extends BaseBiz<SysUser, SysUserDao> implements SysUserService{
	@Autowired
	private SysOrganizationDao sysOrganizationDao;

	@Override
	public SysUser findByUserName(String username) {
		// TODO Auto-generated method stub
		return baseDao.findByUserName(username);
	}

	@Override
	public SysUser findUserById(Integer id) {
		return baseDao.findUserById(id);
	}

	@Override
	public SysUser findUserByPhone(String phone) {
		return baseDao.findUserByPhone(phone);
	}

	@Override
	public SysUser findByOpenId(String openId) {
		return baseDao.findByOpenId(openId);
	}

	@Override
	public SysUser findUserAndCaseById(Integer id) {
		return baseDao.findUserAndCaseById(id);
	}

	@Override
	public List<SysUser> findUserByOrgID(Object ordId) {

		Condition condition =  Condition.parseCondition("org_id@int@eq$");
		condition.setValue(ordId);
		List<SysUser> users=new ArrayList<>();
		users.addAll(this.find(null,condition));

		return users;
	}

	@Override
	public List<SysUser> findMangers(){return baseDao.findMangers();}

	@Override
	public List<SysUser> findThisManger(Long field_id){return baseDao.findThisManger(field_id);}

	@Override
	public List<SysUser> findCoffeeMaker(Long field_id) {
		return baseDao.findCoffeeMaker(field_id);
	}

	@Override
	public int updateSalerCanSend(int canSend, int id) {
		return baseDao.updateSalerCanSend(canSend, id);
	}

	@Override
	public int updateSalerCanSendCard(int canSend, int id) {
		return baseDao.updateSalerCanSendCard(canSend, id);
	}
}