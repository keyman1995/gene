package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@TableName("course_type")
public class CourseType extends BaseModel{


	private List<CourseType> childList;
	@IgnoreColumn
	public List<CourseType> getChildList() {
		return childList;
	}
	public void setChildList(List<CourseType> childList) {
		this.childList = childList;
	}

	private Integer sort;
	@FiledName("sort")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	private String name;
	@FiledName("name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		 this.name = name;
	}

	private String type;
	@FiledName("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String newstype;
	@FiledName("newstype")
	public String getNewstype() {
		return newstype;
	}

	public void setNewstype(String newstype) {
		this.newstype = newstype;
	}

	private String code;
	@FiledName("code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	private String isteacher;
	@FiledName("isteacher")
	public String getIsteacher() {
		return isteacher;
	}
	public void setIsteacher(String isteacher) {
		this.isteacher = isteacher;
	}

	private String parentids;
	@FiledName("parentids")
	public String getParentids() {
		return parentids;
	}
	public void setParentids(String parentids) {
		this.parentids = parentids;
	}

	private Long parentid;
	@FiledName("parentid")
	public Long getParentid() {
		return parentid;
	}
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	private String is_delete;
	@FiledName("is_delete")
	public String getIs_delete() {
		is_delete = (null == is_delete)?"0":is_delete;
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	private String is_root;
	@FiledName("is_root")
	public String getIs_root() {
		is_root = (null == is_root)?"0":is_root;
		return is_root;
	}
	public void setIs_root(String is_root) {
		this.is_root = is_root;
	}

	private String type_img;
	@FiledName("type_img")
	public String getType_img() {
		return type_img;
	}
	public void setType_img(String type_img) {
		this.type_img = type_img;
	}

	private Integer createby;
	@FiledName("createby")
	public Integer getCreateby(){
		return this.createby;
	}
	public void setCreateby(Integer createby){
		 this.createby = createby;
	}

	private Timestamp createtime;
	@FiledName("createtime")
	public Timestamp getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(Timestamp createtime){
		 this.createtime = createtime;
	}
}