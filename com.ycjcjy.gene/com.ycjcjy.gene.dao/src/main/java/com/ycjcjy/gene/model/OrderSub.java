package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("order_sub")
public class OrderSub extends BaseModel{
	private boolean flag;
	@IgnoreColumn
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private Integer order_id;
	@FiledName("order_id")
	public Integer getOrder_id(){
		return this.order_id;
	}
	public void setOrder_id(Integer order_id){
		 this.order_id = order_id;
	}

	private Integer case_field_id;
	@FiledName("case_field_id")
	public Integer getCase_field_id(){
		return this.case_field_id;
	}
	public void setCase_field_id(Integer case_field_id){
		 this.case_field_id = case_field_id;
	}

	private Timestamp appointment_start_time;
	@FiledName("appointment_start_time")
	public Timestamp getAppointment_start_time(){
		return this.appointment_start_time;
	}
	public void setAppointment_start_time(Timestamp appointment_start_time){
		 this.appointment_start_time = appointment_start_time;
	}

	private Timestamp appointment_end_time;
	@FiledName("appointment_end_time")
	public Timestamp getAppointment_end_time(){
		return this.appointment_end_time;
	}
	public void setAppointment_end_time(Timestamp appointment_end_time){
		 this.appointment_end_time = appointment_end_time;
	}

	private Integer course_sub_id;
	@FiledName("course_sub_id")
	public Integer getCourse_sub_id(){
		return this.course_sub_id;
	}
	public void setCourse_sub_id(Integer course_sub_id){
		 this.course_sub_id = course_sub_id;
	}

	private String order_state;
	@FiledName("order_state")
	public String getOrder_state(){
		return this.order_state;
	}
	public void setOrder_state(String order_state){
		 this.order_state = order_state;
	}

	private Integer is_delay;
	@FiledName("is_delay")
	public Integer getIs_delay(){
		return this.is_delay;
	}
	public void setIs_delay(Integer is_delay){
		 this.is_delay = is_delay;
	}

	private Timestamp delay_start_time;
	@FiledName("delay_start_time")
	public Timestamp getDelay_start_time(){
		return this.delay_start_time;
	}
	public void setDelay_start_time(Timestamp delay_start_time){
		 this.delay_start_time = delay_start_time;
	}

	private Timestamp delay_end_time;
	@FiledName("delay_end_time")
	public Timestamp getDelay_end_time(){
		return this.delay_end_time;
	}
	public void setDelay_end_time(Timestamp delay_end_time){
		 this.delay_end_time = delay_end_time;
	}

	private Double discount_price;
	@FiledName("discount_price")
	public Double getDiscount_price(){
		return this.discount_price;
	}
	public void setDiscount_price(Double discount_price){
		 this.discount_price = discount_price;
	}

	private Timestamp verification_time;
	@FiledName("verification_time")
	public Timestamp getVerification_time(){
		return this.verification_time;
	}
	public void setVerification_time(Timestamp verification_time){
		 this.verification_time = verification_time;
	}

	private Integer verification_case_field_id;
	@FiledName("verification_case_field_id")
	public Integer getVerification_case_field_id(){
		return this.verification_case_field_id;
	}
	public void setVerification_case_field_id(Integer verification_case_field_id){
		 this.verification_case_field_id = verification_case_field_id;
	}

	private Integer verification_user_id;
	@FiledName("verification_user_id")
	public Integer getVerification_user_id(){
		return this.verification_user_id;
	}
	public void setVerification_user_id(Integer verification_user_id){
		 this.verification_user_id = verification_user_id;
	}

	private Integer sijiao_index;
	@FiledName("sijiao_index")
	public Integer getSijiao_index() {
		return sijiao_index;
	}

	public void setSijiao_index(Integer sijiao_index) {
		this.sijiao_index = sijiao_index;
	}

	private String sub_time_str;
	@IgnoreColumn
	public String getSub_time_str() {
		return sub_time_str;
	}

	public void setSub_time_str(String sub_time_str) {
		this.sub_time_str = sub_time_str;
	}

	private Timestamp start_time;
	private Timestamp end_time;
	private String startStr;
	private String endStr;
	private String courseName;
	private String courseSubName;
	private Integer courseId;
	private String fieldName;
    @IgnoreColumn
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @IgnoreColumn
	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	@IgnoreColumn
	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	@IgnoreColumn
	public String getStartStr() {
		return startStr;
	}

	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}
	@IgnoreColumn
	public String getEndStr() {
		return endStr;
	}

	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}
	@IgnoreColumn
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@IgnoreColumn
	public String getCourseSubName() {
		return courseSubName;
	}

	public void setCourseSubName(String courseSubName) {
		this.courseSubName = courseSubName;
	}
	@IgnoreColumn
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	private Integer user_id;
	@IgnoreColumn
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		this.user_id = user_id;
	}

	private String username;
	@IgnoreColumn
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private String tel;
	@IgnoreColumn
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	private String coursename;
	@IgnoreColumn
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	private String subcousename;
	@IgnoreColumn
	public String getSubcousename() {
		return subcousename;
	}
	public void setSubcousename(String subcousename) {
		this.subcousename = subcousename;
	}
}