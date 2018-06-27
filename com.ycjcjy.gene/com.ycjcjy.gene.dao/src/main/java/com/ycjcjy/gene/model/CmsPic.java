package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("cms_pic")
public class CmsPic extends BaseModel{

	private String specialclass;
	@IgnoreColumn
	public String getSpecialclass() {
		return specialclass;
	}

	public void setSpecialclass(String specialclass) {
		this.specialclass = specialclass;
	}




	private String img_url;
	@FiledName("img_url")
	public String getImg_url(){
		return this.img_url;
	}
	public void setImg_url(String img_url){
		 this.img_url = img_url;
	}

	private Integer place;
	@FiledName("place")
	public Integer getPlace(){
		return this.place;
	}
	public void setPlace(Integer place){
		 this.place = place;
	}

	private String url;
	@FiledName("url")
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		 this.url = url;
	}

	private String status;
	@FiledName("status")
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		 this.status = status;
	}


	private String placeName;//位置中文

	@IgnoreColumn
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}


	public Integer sec_place;//页面上的位置  0 banner  1  下面卡片
	@FiledName("sec_place")
	public Integer getSec_place() {
		return sec_place;
	}

	public void setSec_place(Integer sec_place) {
		this.sec_place = sec_place;
	}

	public Integer jumpType;
	@FiledName("jump_type")
	public Integer getJumpType() {
		return jumpType;
	}
	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}

	public Integer course_id;
	@FiledName("course_id")
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		this.create_time = create_time;
	}

	private String title;
	@FiledName("title")
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}


}