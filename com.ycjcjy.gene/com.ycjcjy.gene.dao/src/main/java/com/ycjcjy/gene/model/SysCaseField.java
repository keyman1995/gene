package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

import java.sql.Timestamp;

@TableName("sys_case_field")
public class SysCaseField extends BaseModel implements InterfaceBaseModel {



	private String casefieldname;
	@FiledName("casefieldname")
	public String getCasefieldname(){
		return this.casefieldname;
	}
	public void setCasefieldname(String casefieldname){
		 this.casefieldname = casefieldname;
	}

	private String address;
	@FiledName("address")
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		 this.address = address;
	}

	private String leading_official;
	@FiledName("leading_official")
	public String getLeading_official(){
		return this.leading_official;
	}
	public void setLeading_official(String leading_official){
		 this.leading_official = leading_official;
	}

	private String tel;
	@FiledName("tel")
	public String getTel(){
		return this.tel;
	}
	public void setTel(String tel){
		 this.tel = tel;
	}

	private Double avail;
	@FiledName("avail")
	public Double getAvail(){
		return this.avail;
	}
	public void setAvail(Double avail){
		 this.avail = avail;
	}

	private String lng;
	@FiledName("lng")
	public String getLng(){
		return this.lng;
	}
	public void setLng(String lng){
		this.lng = lng;
	}

	private String lat;
	@FiledName("lat")
	public String getLat(){
		return this.lat;
	}
	public void setLat(String lat){
		this.lat = lat;
	}

	private String icon;
	@FiledName("icon")
	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}

	private String create_id;
	@FiledName("create_id")
	public String getCreate_id(){
		return this.create_id;
	}
	public void setCreate_id(String create_id){
		this.create_id = create_id;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private String create_time_str;
	@IgnoreColumn
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}

}