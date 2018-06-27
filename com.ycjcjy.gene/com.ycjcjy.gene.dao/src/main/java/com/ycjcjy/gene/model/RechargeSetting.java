package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("recharge_setting")
public class RechargeSetting extends BaseModel{

	private Double price;
	@FiledName("price")
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price){
		 this.price = price;
	}

	private Double gift_price;
	@FiledName("gift_price")
	public Double getGift_price(){
		return this.gift_price;
	}
	public void setGift_price(Double gift_price){
		 this.gift_price = gift_price;
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