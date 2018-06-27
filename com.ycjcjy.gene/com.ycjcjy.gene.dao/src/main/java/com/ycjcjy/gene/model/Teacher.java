package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@TableName("teacher")
public class Teacher extends BaseModel{

	private String name;
	@FiledName("name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		 this.name = name;
	}

	private List<SysCaseField> sysCaseFields;
	@IgnoreColumn
	public List<SysCaseField> getSysCaseFields() {
		return sysCaseFields;
	}
	public void setSysCaseFields(List<SysCaseField> sysCaseFields) {
		this.sysCaseFields = sysCaseFields;
	}

	private Integer sex;
	@FiledName("sex")
	public Integer getSex(){
		return this.sex;
	}
	public void setSex(Integer sex){
		 this.sex = sex;
	}

	private String experience;
	@FiledName("experience")
	public String getExperience(){
		return this.experience;
	}
	public void setExperience(String experience){
		 this.experience = experience;
	}

	private String goodat;
	@FiledName("goodat")
	public String getGoodat(){
		return this.goodat;
	}
	public void setGoodat(String goodat){
		 this.goodat = goodat;
	}

	private String caseids;
	@FiledName("caseids")
	public String getCaseids(){
		return this.caseids;
	}
	public void setCaseids(String caseids){
		 this.caseids = caseids;
	}

	private String service_info;
	@FiledName("service_info")
	public String getService_info(){
		return this.service_info;
	}
	public void setService_info(String service_info){
		 this.service_info = service_info;
	}

	private Double price;
	@FiledName("price")
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price){
		 this.price = price;
	}

	private Integer limit_buy;
	@FiledName("limit_buy")
	public Integer getLimit_buy(){
		return this.limit_buy;
	}
	public void setLimit_buy(Integer limit_buy){
		 this.limit_buy = limit_buy;
	}

	private Integer can_star;
	@FiledName("can_star")
	public Integer getCan_star(){
		return this.can_star;
	}
	public void setCan_star(Integer can_star){
		 this.can_star = can_star;
	}

	private String tags;
	@FiledName("tags")
	public String getTags(){
		return this.tags;
	}
	public void setTags(String tags){
		 this.tags = tags;
	}

	private String title;
	@FiledName("title")
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		 this.title = title;
	}

	private String img;
	@FiledName("img")
	public String getImg(){
		return this.img;
	}
	public void setImg(String img){
		 this.img = img;
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

	private String caseStr;

	@IgnoreColumn
	public String getCaseStr() {
		return caseStr;
	}

	public void setCaseStr(String caseStr) {
		this.caseStr = caseStr;
	}

	private Integer create_by;
	@FiledName("create_by")
	public Integer getCreate_by(){
		return this.create_by;
	}
	public void setCreate_by(Integer create_by){
		 this.create_by = create_by;
	}

	private Timestamp update_time;
	@FiledName("update_time")
	public Timestamp getUpdate_time(){
		return this.update_time;
	}
	public void setUpdate_time(Timestamp update_time){
		 this.update_time = update_time;
	}

	private Integer update_by;
	@FiledName("update_by")
	public Integer getUpdate_by(){
		return this.update_by;
	}
	public void setUpdate_by(Integer update_by){
		 this.update_by = update_by;
	}

	private Integer type;
	@FiledName("type")
	public Integer getType(){
		return this.type;
	}
	public void setType(Integer type){
		 this.type = type;
	}

	private Integer can_tiyan;
	@FiledName("can_tiyan")
	public Integer getCan_tiyan(){
		return this.can_tiyan;
	}
	public void setCan_tiyan(Integer can_tiyan){
		 this.can_tiyan = can_tiyan;
	}


	private String phone;
	@FiledName("phone")
	public String getPhone(){
		return this.phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}



	private String badgeIds;
	@IgnoreColumn
	public String getBadgeIds() {
		return badgeIds;
	}

	public void setBadgeIds(String badgeIds) {
		this.badgeIds = badgeIds;
	}

	private String open_id;
	@FiledName("open_id")
	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	private Integer teache_num;

	@FiledName("teache_num")
	public Integer getTeache_num() {
		return teache_num;
	}

	public void setTeache_num(Integer teache_num) {
		this.teache_num = teache_num;
	}

	private Integer evaluate_point;
	@FiledName("evaluate_point")
	public Integer getEvaluate_point() {
		return evaluate_point;
	}

	public void setEvaluate_point(Integer evaluate_point) {
		this.evaluate_point = evaluate_point;
	}
}