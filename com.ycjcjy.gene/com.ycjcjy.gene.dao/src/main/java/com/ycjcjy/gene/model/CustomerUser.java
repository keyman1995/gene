package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("customer_user")
public class CustomerUser extends BaseModel{

	private String username;
	@FiledName("username")
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		 this.username = username;
	}

	private String tel;
	@FiledName("tel")
	public String getTel(){
		return this.tel;
	}
	public void setTel(String tel){
		 this.tel = tel;
	}

	private String wc_nickname;
	@FiledName("wc_nickname")
	public String getWc_nickname(){
		return this.wc_nickname;
	}
	public void setWc_nickname(String wc_nickname){
		this.wc_nickname = wc_nickname;
	}

	private String ms_rank;
	@FiledName("ms_rank")
	public String getMs_rank(){
		return this.ms_rank;
	}
	public void setMs_rank(String ms_rank){
		this.ms_rank = ms_rank;
	}

	private String ms_point;
	@FiledName("ms_point")
	public String getMs_point(){
		return this.ms_point;
	}
	public void setMs_point(String ms_point){
		this.ms_point = ms_point;
	}

	private String open_id;
	@FiledName("open_id")
	public String getOpen_id(){
		return this.open_id;
	}
	public void setOpen_id(String open_id){
		 this.open_id = open_id;
	}

	private Double actual_balance;
	@FiledName("actual_balance")
	public Double getActual_balance(){
		return this.actual_balance;
	}
	public void setActual_balance(Double actual_balance){
		 this.actual_balance = actual_balance;
	}

	private Double gift_balance;
	@FiledName("gift_balance")
	public Double getGift_balance(){
		return this.gift_balance;
	}
	public void setGift_balance(Double gift_balance){
		 this.gift_balance = gift_balance;
	}

	private String icon;
	@FiledName("icon")
	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
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

	private Integer makeupId;
	@IgnoreColumn
	public Integer getMakeupId() {
		return makeupId;
	}

	public void setMakeupId(Integer makeupId) {
		this.makeupId = makeupId;
	}

	private String courseName;
	@IgnoreColumn
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	private String subCourseId;
	@IgnoreColumn
	public String getSubCourseId() {
		return subCourseId;
	}

	public void setSubCourseId(String subCourseId) {
		this.subCourseId = subCourseId;
	}
	private Timestamp makeupTime;
	@IgnoreColumn
	public Timestamp getMakeupTime() {
		return makeupTime;
	}

	public void setMakeupTime(Timestamp makeupTime) {
		this.makeupTime = makeupTime;
	}

	private Integer courseId;
	@IgnoreColumn
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	private Timestamp newCourseTime;
	@IgnoreColumn
	public Timestamp getNewCourseTime() {
		return newCourseTime;
	}

	public void setNewCourseTime(Timestamp newCourseTime) {
		this.newCourseTime = newCourseTime;
	}

	private Double allBalance;
	@IgnoreColumn
	public Double getAllBalance() {
		return allBalance;
	}

	public void setAllBalance(Double allBalance) {
		this.allBalance = allBalance;
	}

	private Integer user_type;
	@FiledName("user_type")
	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}



	private Integer sex;
	@FiledName("sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	private Integer age;
	private Integer area;
	@FiledName("age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@FiledName("area")
	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}
}