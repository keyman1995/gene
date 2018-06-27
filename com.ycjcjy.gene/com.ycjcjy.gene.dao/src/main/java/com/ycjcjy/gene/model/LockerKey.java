package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("locker_key")
public class LockerKey extends BaseModel{

	private Long lock_num;
	@FiledName("lock_num")
	public Long getLock_num(){
		return this.lock_num;
	}
	public void setLock_num(Long lock_num){
		 this.lock_num = lock_num;
	}

	private Long case_field_id;
	@FiledName("case_field_id")
	public Long getCase_field_id(){
		return this.case_field_id;
	}
	public void setCase_field_id(Long case_field_id){
		 this.case_field_id = case_field_id;
	}

	private Long current_user_id;
	@FiledName("current_user_id")
	public Long getCurrent_user_id(){
		return this.current_user_id;
	}
	public void setCurrent_user_id(Long current_user_id){
		 this.current_user_id = current_user_id;
	}

	private String status;
	@FiledName("status")
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		 this.status = status;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private String current_user_name;
	@FiledName("current_user_name")
	public String getCurrent_user_name() {
		return current_user_name;
	}

	public void setCurrent_user_name(String current_user_name) {
		this.current_user_name = current_user_name;
	}

	private Long startNum;
	@IgnoreColumn
	public Long getStartNum() {
		return this.startNum;
	}
	public void setStartNum(Long startNum){
		this.startNum=startNum;
	}
	private Long endNum;
	@IgnoreColumn
	public Long getEndNum() {
		return this.endNum;
	}
	public void setEndNum(Long endNum){
		this.endNum=endNum;
	}


}