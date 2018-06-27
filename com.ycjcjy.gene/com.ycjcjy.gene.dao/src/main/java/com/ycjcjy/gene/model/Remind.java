package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("remind")
public class Remind extends BaseModel{

	private Integer user_id;
	@FiledName("user_id")
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		this.user_id = user_id;
	}

	private Integer type;
	@FiledName("type")
	public Integer getType(){
		return this.type;
	}
	public void setType(Integer type){
		 this.type = type;
	}

	private String coursename;
	@FiledName("coursename")
	public String getCoursename(){
		return this.coursename;
	}
	public void setCoursename(String coursename){
		 this.coursename = coursename;
	}

	private String coursestarttime;
	@FiledName("coursestarttime")
	public String getCoursestarttime(){
		return this.coursestarttime;
	}
	public void setCoursestarttime(String coursestarttime){
		 this.coursestarttime = coursestarttime;
	}

	private Integer cardcount;
	@FiledName("cardcount")
	public Integer getCardcount(){
		return this.cardcount;
	}
	public void setCardcount(Integer cardcount){
		 this.cardcount = cardcount;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}
}