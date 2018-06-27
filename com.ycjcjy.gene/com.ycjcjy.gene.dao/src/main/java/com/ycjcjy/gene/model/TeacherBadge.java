package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName(" teacher_badge")
public class TeacherBadge extends BaseModel{

	private String name;
	@FiledName("name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		 this.name = name;
	}

	private String img_url;
	@FiledName("img_url")
	public String getImg_url(){
		return this.img_url;
	}
	public void setImg_url(String img_url){
		 this.img_url = img_url;
	}
}