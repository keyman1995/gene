package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;


@TableName("send_card_log")
public class SendCardLog extends BaseModel{

	private String sys_username;
	@IgnoreColumn
	public String getSys_username(){
		return this.sys_username;
	}
	public void setSys_username(String sys_username){
		 this.sys_username = sys_username;
	}

	private Long sys_user_id;
	@FiledName("sys_user_id")
	public Long getSys_user_id(){
		return this.sys_user_id;
	}
	public void setSys_user_id(Long sys_user_id){
		 this.sys_user_id = sys_user_id;
	}
	private Double price;
	@FiledName("price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	private String sys_customer_name;
	@IgnoreColumn
	public String getSys_customer_name(){
		return this.sys_customer_name;
	}
	public void setSys_customer_name(String sys_customer_name){
		 this.sys_customer_name = sys_customer_name;
	}

	private Long sys_customer_id;
	@FiledName("sys_customer_id")
	public Long getSys_customer_id(){
		return this.sys_customer_id;
	}
	public void setSys_customer_id(Long sys_customer_id){
		 this.sys_customer_id = sys_customer_id;
	}

	private String card_name;
	@IgnoreColumn
	public String getCard_name(){
		return this.card_name;
	}
	public void setCard_name(String card_name){
		 this.card_name = card_name;
	}

	private Long card_id;
	@FiledName("card_id")
	public Long getCard_id(){
		return this.card_id;
	}
	public void setCard_id(Long card_id){
		 this.card_id = card_id;
	}

	private String create_time;
	@FiledName("create_time")
	public String getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(String create_time){
		 this.create_time = create_time;
	}

	private String customer_tel;
	@FiledName("customer_tel")
	public String getCustomer_tel() {
		return customer_tel;
	}

	public void setCustomer_tel(String customer_tel) {
		this.customer_tel = customer_tel;
	}

	private String end_time;
	@IgnoreColumn
	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	private String field_name;
	@IgnoreColumn
	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	private Integer current_user_org_id;
	@IgnoreColumn
	public Integer getCurrent_user_org_id() {
		return current_user_org_id;
	}

	public void setCurrent_user_org_id(Integer current_user_org_id) {
		this.current_user_org_id = current_user_org_id;
	}
}