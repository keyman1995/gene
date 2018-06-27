package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("hot_search_word")
public class HotSearchWord extends BaseModel{

	private String name;
	@FiledName("name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		 this.name = name;
	}

	private Integer weight;
	@FiledName("weight")
	public Integer getWeight(){
		return this.weight;
	}
	public void setWeight(Integer weight){
		 this.weight = weight;
	}

	private String url;
	@FiledName("url")
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
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