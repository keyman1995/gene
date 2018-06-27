package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@TableName("card_ticket_salve")
public class CardTicketSalve extends BaseModel{

	private Integer pid;
	@FiledName("pid")
	public Integer getPid(){
		return this.pid;
	}
	public void setPid(Integer pid){
		 this.pid = pid;
	}



	private String state;
	@FiledName("state")
	public String getState(){
		return this.state;
	}
	public void setState(String state){
		 this.state = state;
	}

	private Integer user_id;
	@FiledName("user_id")
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		 this.user_id = user_id;
	}


	private Date tosale_time;
	private Date touser_time;

	@FiledName("tosale_time")
	public Date getTosale_time() {
		return tosale_time;
	}

	public void setTosale_time(Date tosale_time) {
		this.tosale_time = tosale_time;
	}

	@FiledName("touser_time")
	public Date getTouser_time() {
		return touser_time;
	}

	public void setTouser_time(Date touser_time) {
		this.touser_time = touser_time;
	}


}