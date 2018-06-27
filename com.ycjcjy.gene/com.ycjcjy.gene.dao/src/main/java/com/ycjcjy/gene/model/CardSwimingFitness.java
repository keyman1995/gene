package com.ycjcjy.gene.model;

import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

@TableName("card_swiming_fitness")
public class CardSwimingFitness extends BaseModel{

	private String description;
	@FiledName("description")
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		 this.description = description;
	}

	private String time_scope;
	@FiledName("time_scope")
	public String getTime_scope(){
		return this.time_scope;
	}
	public void setTime_scope(String time_scope){
		 this.time_scope = time_scope;
	}

	private String type;
	@FiledName("type")
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		 this.type = type;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private BigDecimal price;
	@FiledName("price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private String create_time_str;
	@IgnoreColumn
	public String getCreate_time_str() {
		return create_time_str;
	}

	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}

	private Integer num;
	@FiledName("num")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}