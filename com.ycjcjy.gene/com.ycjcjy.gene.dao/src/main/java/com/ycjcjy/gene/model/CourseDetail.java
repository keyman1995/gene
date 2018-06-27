package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@TableName("course_detail")
public class CourseDetail extends BaseModel{

	private Integer courseid;
	@FiledName("courseid")
	public Integer getCourseid(){
		return this.courseid;
	}
	public void setCourseid(Integer courseid){
		 this.courseid = courseid;
	}


	private List<String> img_urls;
	@IgnoreColumn
	public List<String> getImg_urls() {
		return img_urls;
	}
	public void setImg_urls(List<String> img_urls) {
		this.img_urls = img_urls;
	}

	private String img_url;
	@FiledName("img_url")
	public String getImg_url(){
		return this.img_url;
	}
	public void setImg_url(String img_url){
		 this.img_url = img_url;
	}

	private Integer sort;
	@FiledName("sort")
	public Integer getSort(){
		return this.sort;
	}
	public void setSort(Integer sort){
		 this.sort = sort;
	}

	private Timestamp createtime;
	@FiledName("createtime")
	public Timestamp getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(Timestamp createtime){
		 this.createtime = createtime;
	}

	private Long createby;
	@FiledName("createby")
	public Long getCreateby(){
		return this.createby;
	}
	public void setCreateby(Long createby){
		 this.createby = createby;
	}
}