package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@TableName("card_courde_basic")
public class CardCourdeBasic extends BaseModel{

	private Long course_id;
	@FiledName("course_id")
	public Long getCourse_id(){
		return this.course_id;
	}
	public void setCourse_id(Long course_id){
		 this.course_id = course_id;
	}

	private String name;
	@FiledName("name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		 this.name = name;
	}

	public Double price;
	public Timestamp start_time;
	public Timestamp end_time;
	public String remarks;
	@FiledName("price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
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
	@FiledName("remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	private String courseintro;
	private String img_url;
	private String courseaddress;
	private String coursename;
	private Double courseprice;

	@IgnoreColumn
	public String getCourseintro() {
		return courseintro;
	}

	public void setCourseintro(String courseintro) {
		this.courseintro = courseintro;
	}
	@IgnoreColumn
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	@IgnoreColumn
	public String getCourseaddress() {
		return courseaddress;
	}

	public void setCourseaddress(String courseaddress) {
		this.courseaddress = courseaddress;
	}
	@IgnoreColumn
	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	@IgnoreColumn
	public Double getCourseprice() {
		return courseprice;
	}

	public void setCourseprice(Double courseprice) {
		this.courseprice = courseprice;
	}




	private String startStr;
	private String endStr;
	@IgnoreColumn
	public String getEndStr() {
		return endStr;
	}

	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}

	@IgnoreColumn
	public String getStartStr() {
		return startStr;
	}

	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}
}
