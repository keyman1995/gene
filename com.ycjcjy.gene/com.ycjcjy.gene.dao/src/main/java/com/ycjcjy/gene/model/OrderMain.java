package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("order_main")
public class OrderMain extends BaseModel{

	private Integer course_id;
	@FiledName("course_id")
	public Integer getCourse_id(){
		return this.course_id;
	}
	public void setCourse_id(Integer course_id){
		 this.course_id = course_id;
	}

	private Integer ticket_id;
	@FiledName("ticket_id")
	public Integer getTicket_id(){
		return this.ticket_id;
	}
	public void setTicket_id(Integer ticket_id){
		 this.ticket_id = ticket_id;
	}

	private Integer user_id;
	@FiledName("user_id")
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		 this.user_id = user_id;
	}

	private String order_no;
	@FiledName("order_no")
	public String getOrder_no(){
		return this.order_no;
	}
	public void setOrder_no(String order_no){
		 this.order_no = order_no;
	}

	private Integer course_num;
	@FiledName("course_num")
	public Integer getCourse_num(){
		return this.course_num;
	}
	public void setCourse_num(Integer course_num){
		 this.course_num = course_num;
	}

	private Integer course_type;
	@FiledName("course_type")
	public Integer getCourse_type(){
		return this.course_type;
	}
	public void setCourse_type(Integer course_type){
		 this.course_type = course_type;
	}

	private Double orgin_price;
	@FiledName("orgin_price")
	public Double getOrgin_price(){
		return this.orgin_price;
	}
	public void setOrgin_price(Double orgin_price){
		 this.orgin_price = orgin_price;
	}

	private Double actual_price;
	@FiledName("actual_price")
	public Double getActual_price(){
		return this.actual_price;
	}
	public void setActual_price(Double actual_price){
		 this.actual_price = actual_price;
	}

	private Double discount_price;
	@FiledName("discount_price")
	public Double getDiscount_price(){
		return this.discount_price;
	}
	public void setDiscount_price(Double discount_price){
		 this.discount_price = discount_price;
	}

	private String order_state;
	@FiledName("order_state")
	public String getOrder_state(){
		return this.order_state;
	}
	public void setOrder_state(String order_state){
		 this.order_state = order_state;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Timestamp pay_time;
	@FiledName("pay_time")
	public Timestamp getPay_time(){
		return this.pay_time;
	}
	public void setPay_time(Timestamp pay_time){
		 this.pay_time = pay_time;
	}

	private Timestamp close_time;
	@FiledName("close_time")
	public Timestamp getClose_time(){
		return this.close_time;
	}
	public void setClose_time(Timestamp close_time){
		 this.close_time = close_time;
	}

	private Timestamp done_time;
	@FiledName("done_time")
	public Timestamp getDone_time(){
		return this.done_time;
	}
	public void setDone_time(Timestamp done_time){
		 this.done_time = done_time;
	}

	private Integer order_type;
	@FiledName("order_type")
	public Integer getOrder_type(){
		return this.order_type;
	}
	public void setOrder_type(Integer order_type){
		 this.order_type = order_type;
	}

	private Integer card_id;
	@FiledName("card_id")
	public Integer getCard_id(){
		return this.card_id;
	}
	public void setCard_id(Integer card_id){
		 this.card_id = card_id;
	}

	private String coursename;
	private String username;
	private String tel;
	private String startTime;
	private String endTime;
	private String create_time_str;
	private String img_url;//课程图片
	private String coursestartline;//课程开始时间
	private String courseendline;//课程结束时间
	private String casefieldname;//案场名称
	private String coursetimeintro;//周几
	private String teacherName;//老师名称
	private String startTimeMD;//预计开班时间
	private Integer leftLesson;//剩余课时
	private String cardName;//体验卡名称
	@IgnoreColumn
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@IgnoreColumn
	public String getCasefieldname() {
		return casefieldname;
	}

	public void setCasefieldname(String casefieldname) {
		this.casefieldname = casefieldname;
	}
	@IgnoreColumn
	public String getCoursetimeintro() {
		return coursetimeintro;
	}

	public void setCoursetimeintro(String coursetimeintro) {
		this.coursetimeintro = coursetimeintro;
	}



	@IgnoreColumn
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	@IgnoreColumn
	public String getStartTimeMD() {
		return startTimeMD;
	}

	public void setStartTimeMD(String startTimeMD) {
		this.startTimeMD = startTimeMD;
	}

	@IgnoreColumn
	public Integer getLeftLesson() {
		return leftLesson;
	}

	public void setLeftLesson(Integer leftLesson) {
		this.leftLesson = leftLesson;
	}

	@IgnoreColumn
	public String getCoursestartline() {
		return coursestartline;
	}

	public void setCoursestartline(String coursestartline) {
		this.coursestartline = coursestartline;
	}
	@IgnoreColumn
	public String getCourseendline() {
		return courseendline;
	}

	public void setCourseendline(String courseendline) {
		this.courseendline = courseendline;
	}

	@IgnoreColumn
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@IgnoreColumn
	public String getCreate_time_str() {
		return create_time_str;
	}

	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}

	@IgnoreColumn
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@IgnoreColumn
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@IgnoreColumn
	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	@IgnoreColumn
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@IgnoreColumn
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	private String trade_no;
	@FiledName("trade_no")
	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	private Integer case_field_id;
	@FiledName("case_field_id")
	public Integer getCase_field_id() {
		return case_field_id;
	}

	public void setCase_field_id(Integer case_field_id) {
		this.case_field_id = case_field_id;
	}
}