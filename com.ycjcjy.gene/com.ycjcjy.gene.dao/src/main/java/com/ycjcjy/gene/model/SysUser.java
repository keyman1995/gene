package com.ycjcjy.gene.model;

import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

import java.sql.Timestamp;
import java.util.List;

@TableName("sys_user")
public class SysUser extends BaseModel implements InterfaceBaseModel {

	private String username;
	@FiledName("username")
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		 this.username = username;
	}

	private String password;
	@FiledName("password")
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		 this.password = password;
	}
	
	private List<SysRole> roles;
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	private String is_field_contact;
	@FiledName("is_field_contact")
	public String getIs_field_contact() {
		return is_field_contact;
	}

	public void setIs_field_contact(String is_field_contact) {
		this.is_field_contact = is_field_contact;
	}

	private String icon;
	private String realname;
	private String email;
	private String number;
	private String mobile;
	private Timestamp creat_time;
	private String creat_time_str;
	private String is_lock;
	private String is_delete;
	private String user_type;

	private Integer can_send;
	private Integer can_send_card;
	@FiledName("can_send_card")
	public Integer getCan_send_card() {
		return can_send_card;
	}

	public void setCan_send_card(Integer can_send_card) {
		this.can_send_card = can_send_card;
	}

	@FiledName("can_send")
	public Integer getCan_send() {
		return can_send;
	}

	public void setCan_send(Integer can_send) {
		this.can_send = can_send;
	}

	@IgnoreColumn
	public String getCreat_time_str() {
		return creat_time_str;
	}

	public void setCreat_time_str(String creat_time_str) {
		this.creat_time_str = creat_time_str;
	}

	@FiledName("is_lock")
	public String getIs_lock() {
		is_lock = (null == is_lock)?"0":is_lock;
		return is_lock;
	}

	public void setIs_lock(String is_lock) {
		this.is_lock = is_lock;
	}
	@FiledName("is_delete")
	public String getIs_delete() {
		is_delete = (null == is_delete)?"0":is_delete;
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	@FiledName("user_type")
	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	@FiledName("icon")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	@FiledName("realname")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	@FiledName("email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@FiledName("number")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@FiledName("mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@FiledName("creat_time")
	public Timestamp getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(Timestamp creat_time) {
		this.creat_time = creat_time;
	}

	private Integer org_id;
	@FiledName("org_id")
	public Integer getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}
	private Long field_id;
	@FiledName("field_id")
	public Long getField_id() {
		return field_id;
	}

	public void setField_id(Long field_id) {
		this.field_id = field_id;
	}

	private String field_name;
	@FiledName("field_name")
	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	private String open_id;
	@FiledName("open_id")
	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	private Integer msg_state;
	@FiledName("msg_state")
	public Integer getMsg_state() {
		return msg_state;
	}

	public void setMsg_state(Integer msg_state) {
		this.msg_state = msg_state;
	}
}