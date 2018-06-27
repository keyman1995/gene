package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("teacher_certificate")
public class TeacherCertificate extends BaseModel{

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
		 this.img_url= img_url;
	}

	private Long teacher_id;
	@FiledName("teacher_id")
	public Long getTeacher_id(){
		return this.teacher_id;
	}
	public void setTeacher_id(Long teacher_id){
		 this.teacher_id = teacher_id;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Integer create_by;
	@FiledName("create_by")
	public Integer getCreate_by(){
		return this.create_by;
	}
	public void setCreate_by(Integer create_by){
		 this.create_by = create_by;
	}
}