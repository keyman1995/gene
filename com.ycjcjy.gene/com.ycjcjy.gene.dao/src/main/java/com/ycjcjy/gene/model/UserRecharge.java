package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("user_recharge")
public class UserRecharge extends BaseModel{

	private String type;
	@FiledName("type")
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		 this.type = type;
	}

	private String order_id;
	@FiledName("order_id")
	public String getOrder_id(){
		return this.order_id;
	}
	public void setOrder_id(String order_id){
		this.order_id = order_id;
	}

	private Integer case_field_id;
	@FiledName("case_field_id")
	public Integer getCase_field_id(){
		return this.case_field_id;
	}
	public void setCase_field_id(Integer case_field_id){
		 this.case_field_id = case_field_id;
	}

	private Integer user_id;
	@FiledName("user_id")
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		this.user_id = user_id;
	}

	private String identifyingcode;
	@FiledName("identifyingcode")
	public String getIdentifyingcode(){
		return this.identifyingcode;
	}
	public void setIdentifyingcode(String identifyingcode){
		this.identifyingcode = identifyingcode;
	}


	private Double actual;
	@FiledName("actual")
	public Double getActual(){
		return this.actual;
	}
	public void setActual(Double actual){
		 this.actual = actual;
	}

	private Double gift;
	@FiledName("gift")
	public Double getGift(){
		return this.gift;
	}
	public void setGift(Double gift){
		 this.gift = gift;
	}

	private String status;
	@FiledName("status")
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		 this.status = status;
	}

	private String trade_no;
	@FiledName("trade_no")
	public String getTrade_no(){
		return this.trade_no;
	}
	public void setTrade_no(String trade_no){
		this.trade_no = trade_no;
	}

//	private String create_id;
//	@FiledName("create_id")
//	public String getCreate_id(){
//		return this.create_id;
//	}
//	public void setCreate_id(String create_id){
//		 this.create_id = create_id;
//	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Timestamp update_time;
	@FiledName("update_time")
	public Timestamp getUpdate_time(){
		return this.update_time;
	}
	public void setUpdate_time(Timestamp update_time){
		this.update_time = update_time;
	}

	private String create_time_str;
	@IgnoreColumn
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}

	private String casefieldname;
	@IgnoreColumn
	public String getCasefieldname() {
		return casefieldname;
	}
	public void setCasefieldname(String casefieldname) {
		this.casefieldname = casefieldname;
	}

	private String username;
	@IgnoreColumn
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private String tel;
	@IgnoreColumn
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}