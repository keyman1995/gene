package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("teacher_badge_rela")
public class TeacherBadgeRela extends BaseModel{

	private Integer teacher_id;
	@FiledName("teacher_id")
	public Integer getTeacher_id(){
		return this.teacher_id;
	}
	public void setTeacher_id(Integer teacher_id){
		 this.teacher_id = teacher_id;
	}

	private Integer badge_id;
	@FiledName("badge_id")
	public Integer getBadge_id(){
		return this.badge_id;
	}
	public void setBadge_id(Integer badge_id){
		 this.badge_id = badge_id;
	}
}