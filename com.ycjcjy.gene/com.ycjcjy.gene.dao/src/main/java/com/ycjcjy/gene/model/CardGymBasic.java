package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@TableName("card_gym_basic")
public class CardGymBasic extends BaseModel{

	private String card_gym_type;
	@FiledName("card_gym_type")
	public String getCard_gym_type(){
		return this.card_gym_type;
	}
	public void setCard_gym_type(String card_gym_type){
		 this.card_gym_type = card_gym_type;
	}

	private String name;
	@FiledName("name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		 this.name = name;
	}

	private Date create_time;
	@FiledName("create_time")
	public Date getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Date create_time){
		 this.create_time = create_time;
	}

	private String remarks;
	@FiledName("remarks")
	public String getRemarks(){
		return this.remarks;
	}
	public void setRemarks(String remarks){
		 this.remarks = remarks;
	}

	private Double price;
	@FiledName("price")
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price){
		 this.price = price;
	}

	private String time_str;
	@IgnoreColumn
	public String getTime_str() {
		return time_str;
	}

	public void setTime_str(String time_str) {
		this.time_str = time_str;
	}


	public Timestamp start_time;
	public Timestamp end_time;

	@FiledName("start_time")
	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	@FiledName("end_time")
	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	private String startStr;
	private String endStr;
	@IgnoreColumn
	public String getStartStr() {
		return startStr;
	}

	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}
	@IgnoreColumn
	public String getEndStr() {
		return endStr;
	}

	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}
}