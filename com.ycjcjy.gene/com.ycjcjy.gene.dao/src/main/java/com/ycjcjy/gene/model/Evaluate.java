package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@TableName("evaluate")
public class Evaluate extends BaseModel{

	private String avatar_img;
	@IgnoreColumn
	public String getAvatar_img() {
		return avatar_img;
	}
	public void setAvatar_img(String avatar_img) {
		this.avatar_img = avatar_img;
	}

	private String createtime_str;
	@IgnoreColumn
	public String getCreatetime_str() {
		return createtime_str;
	}

	public void setCreatetime_str(String createtime_str) {
		this.createtime_str = createtime_str;
	}

	private Integer order_id;
	@FiledName("order_id")
	public Integer getOrder_id(){
		return this.order_id;
	}
	public void setOrder_id(Integer order_id){
		 this.order_id = order_id;
	}

	private Integer target_id;
	@FiledName("target_id")
	public Integer getTarget_id(){
		return this.target_id;
	}
	public void setTarget_id(Integer target_id){
		 this.target_id = target_id;
	}

	private Integer customer_id;
	@FiledName("customer_id")
	public Integer getCustomer_id(){
		return this.customer_id;
	}
	public void setCustomer_id(Integer customer_id){
		 this.customer_id = customer_id;
	}

	private String type;
	@FiledName("type")
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		 this.type = type;
	}

	private String score;
	@FiledName("score")
	public String getScore(){
		return this.score;
	}
	public void setScore(String score){
		 this.score = score;
	}

	private String content;
	@FiledName("content")
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		 this.content = content;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}


	private String teacherName;
	private String courseName;
	private String customerName;
	private String createTimeStr;
	@IgnoreColumn
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@IgnoreColumn
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	@IgnoreColumn
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@IgnoreColumn
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	private List<EvaluateImg> evaluateImgList;
	@IgnoreColumn
	public List<EvaluateImg> getEvaluateImgList() {
		return evaluateImgList;
	}
	public void setEvaluateImgList(List<EvaluateImg> evaluateImgList) {
		this.evaluateImgList = evaluateImgList;
	}
}