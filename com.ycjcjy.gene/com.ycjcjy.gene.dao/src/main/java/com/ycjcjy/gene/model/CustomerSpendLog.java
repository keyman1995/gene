package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("customer_spend_log")
public class CustomerSpendLog extends BaseModel{
	private Integer type;

	@FiledName("type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	private Integer customer_id;
	@FiledName("customer_id")
	public Integer getCustomer_id(){
		return this.customer_id;
	}
	public void setCustomer_id(Integer customer_id){
		 this.customer_id = customer_id;
	}

	private String goods_id;
	@FiledName("goods_id")
	public String getGoods_id(){
		return this.goods_id;
	}
	public void setGoods_id(String goods_id){
		 this.goods_id = goods_id;
	}

	private Double price;
	@FiledName("price")
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price){
		 this.price = price;
	}

	private String quantity;
	@FiledName("quantity")
	public String getQuantity(){
		return this.quantity;
	}
	public void setQuantity(String quantity){
		 this.quantity = quantity;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Integer casefield_id;
	@FiledName("casefield_id")
	public Integer getCasefield_id(){
		return this.casefield_id;
	}
	public void setCasefield_id(Integer casefield_id){
		this.casefield_id = casefield_id;
	}

	private String casefield_name;
	@IgnoreColumn
	public String getCasefield_name() {
		return casefield_name;
	}
	public void setCasefield_name(String casefield_name) {
		this.casefield_name = casefield_name;
	}

	private String goods_name;
	@IgnoreColumn
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	private String coursename;
	@IgnoreColumn
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
}