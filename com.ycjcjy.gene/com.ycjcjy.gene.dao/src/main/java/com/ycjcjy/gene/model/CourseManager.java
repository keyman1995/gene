package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;
import java.util.Date;

@TableName("course_manager")
public class CourseManager extends BaseModel{

	private String coursestartline_str;
	private String courseendline_str;
	private String casename;
	private String coursetypename;
	private String coursespecialname;


	private String weekday;
	@FiledName("weekday")
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	private String coursetags;
	@FiledName("coursetags")
	public String getCoursetags() {
		return coursetags;
	}
	public void setCoursetags(String coursetags) {
		this.coursetags = coursetags;
	}

	private String coursetimeintro;
	@FiledName("coursetimeintro")
	public String getCoursetimeintro() {
		return coursetimeintro;
	}
	public void setCoursetimeintro(String coursetimeintro) {
		this.coursetimeintro = coursetimeintro;
	}

	private String ispublic;
	@FiledName("ispublic")
	public String getIspublic() {
		return ispublic;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}

	private String circlename;
	@IgnoreColumn
	public String getCirclename() {
		return circlename;
	}
	public void setCirclename(String circlename) {
		this.circlename = circlename;
	}

	private Integer weekcount;
	@IgnoreColumn
	public Integer getWeekcount() {
		return weekcount;
	}
	public void setWeekcount(Integer weekcount) {
		this.weekcount = weekcount;
	}

	private  Integer monthcount;
	@IgnoreColumn
	public Integer getMonthcount() {
		return monthcount;
	}
	public void setMonthcount(Integer monthcount) {
		this.monthcount = monthcount;
	}

	private Date weekStart;
	@IgnoreColumn
	public Date getWeekStart() {
		return weekStart;
	}
	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}

	private Date weekEnd;
	@IgnoreColumn
	public Date getWeekEnd() {
		return weekEnd;
	}
	public void setWeekEnd(Date weekEnd) {
		this.weekEnd = weekEnd;
	}

	private Date monthSart;
	@IgnoreColumn
	public Date getMonthSart() {
		return monthSart;
	}
	public void setMonthSart(Date monthSart) {
		this.monthSart = monthSart;
	}

	private Date monthEnd;
	@IgnoreColumn
	public Date getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(Date monthEnd) {
		this.monthEnd = monthEnd;
	}

	private Integer conditionParam;
	@IgnoreColumn
	public Integer getConditionParam() {
		return conditionParam;
	}
	public void setConditionParam(Integer conditionParam) {
		this.conditionParam = conditionParam;
	}

	@IgnoreColumn
	public String getCoursespecialname() {
		return coursespecialname;
	}
	public void setCoursespecialname(String coursespecialname) {
		this.coursespecialname = coursespecialname;
	}

	@IgnoreColumn
	public String getCoursetypename() {
		return coursetypename;
	}
	public void setCoursetypename(String coursetypename) {
		this.coursetypename = coursetypename;
	}

	@IgnoreColumn
	public String getCasename() {
		return casename;
	}
	public void setCasename(String casename) {
		this.casename = casename;
	}

	@IgnoreColumn
	public String getCoursestartline_str() {
		return coursestartline_str;
	}

	public void setCoursestartline_str(String coursestartline_str) {
		this.coursestartline_str = coursestartline_str;
	}
	@IgnoreColumn
	public String getCourseendline_str() {
		return courseendline_str;
	}

	public void setCourseendline_str(String courseendline_str) {
		this.courseendline_str = courseendline_str;
	}

	private Double coursetime;
	@FiledName("coursetime")
	public Double getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(Double coursetime) {
		this.coursetime = coursetime;
	}

	private Integer coursecount;
	@FiledName("coursecount")
	public Integer getCoursecount() {
		return coursecount;
	}
	public void setCoursecount(Integer coursecount) {
		this.coursecount = coursecount;
	}

	private String courseintro;
	@FiledName("courseintro")
	public String getCourseintro() {
		return courseintro;
	}
	public void setCourseintro(String courseintro) {
		this.courseintro = courseintro;
	}

	private String coursecircle;
	@FiledName("coursecircle")
	public String getCoursecircle() {
		return coursecircle;
	}
	public void setCoursecircle(String coursecircle) {
		this.coursecircle = coursecircle;
	}

	private String isdiscount;
	@FiledName("isdiscount")
	public String getIsdiscount() {
		return isdiscount;
	}
	public void setIsdiscount(String isdiscount) {
		this.isdiscount = isdiscount;
	}

	private String isexperice;
	@FiledName("isexperice")
	public String getIsexperice() {
		return isexperice;
	}
	public void setIsexperice(String isexperice) {
		this.isexperice = isexperice;
	}

	private Double expericeprice;
	@FiledName("expericeprice")
	public Double getExpericeprice() {
		return expericeprice;
	}
	public void setExpericeprice(Double expericeprice) {
		this.expericeprice = expericeprice;
	}

	private String img_url;
	@FiledName("img_url")
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	private String isgreate;
	@FiledName("isgreate")
	public String getIsgreate() {
		return isgreate;
	}
	public void setIsgreate(String isgreate) {
		this.isgreate = isgreate;
	}

	private String fitness_url;
	@FiledName("fitness_url")
	public String getFitness_url() {
		return fitness_url;
	}
	public void setFitness_url(String fitness_url) {
		this.fitness_url = fitness_url;
	}

	private String isdate;
	@FiledName("isdate")
	public String getIsdate() {
		return isdate;
	}
	public void setIsdate(String isdate) {
		this.isdate = isdate;
	}

	private String isleave;
	@FiledName("isleave")
	public String getIsleave() {
		return isleave;
	}
	public void setIsleave(String isleave) {
		this.isleave = isleave;
	}

	private Double leavetime;
	@FiledName("leavetime")
	public Double getLeavetime() {
		return leavetime;
	}
	public void setLeavetime(Double leavetime) {
		this.leavetime = leavetime;
	}

	private String courseaddress;
	@FiledName("courseaddress")
	public String getCourseaddress() {
		return courseaddress;
	}
	public void setCourseaddress(String courseaddress) {
		this.courseaddress = courseaddress;
	}

	private Double discountprice;
	@FiledName("discountprice")
	public Double getDiscountprice() {
		return discountprice;
	}
	public void setDiscountprice(Double discountprice) {
		this.discountprice = discountprice;
	}

	private String coursename;
	@FiledName("coursename")
	public String getCoursename(){
		return this.coursename;
	}
	public void setCoursename(String coursename){
		 this.coursename = coursename;
	}

	private Integer coursetype;
	@FiledName("coursetype")
	public Integer getCoursetype() {
		return coursetype;
	}
	public void setCoursetype(Integer coursetype) {
		this.coursetype = coursetype;
	}

	private Integer specialcourse;
	@FiledName("specialcourse")
	public Integer getSpecialcourse() {
		return specialcourse;
	}
	public void setSpecialcourse(Integer specialcourse) {
		this.specialcourse = specialcourse;
	}

	private String specialclass;
	@FiledName("specialclass")
	public String getSpecialclass() {
		return specialclass;
	}
	public void setSpecialclass(String specialclass) {
		this.specialclass = specialclass;
	}

	private String teacherids;
	@FiledName("teacherids")
	public String getTeacherids() {
		return teacherids;
	}
	public void setTeacherids(String teacherids) {
		this.teacherids = teacherids;
	}

	private String subteacherids;
	@FiledName("subteacherids")
	public String getSubteacherids() {
		return subteacherids;
	}
	public void setSubteacherids(String subteacherids) {
		this.subteacherids = subteacherids;
	}

	private Integer caseid;
	@FiledName("caseid")
	public Integer getCaseid(){
		return this.caseid;
	}
	public void setCaseid(Integer caseid){
		this.caseid = caseid;
	}

	private Double courseprice;
	@FiledName("courseprice")
	public Double getCourseprice(){
		return this.courseprice;
	}
	public void setCourseprice(Double courseprice){
		 this.courseprice = courseprice;
	}

	private Integer coursenum;
	@FiledName("coursenum")
	public Integer getCoursenum(){
		return this.coursenum;
	}
	public void setCoursenum(Integer coursenum){
		 this.coursenum = coursenum;
	}

	private Integer minnum;
	@FiledName("minnum")
	public Integer getMinnum() {
		return minnum;
	}
	public void setMinnum(Integer minnum) {
		this.minnum = minnum;
	}

	private Integer delaytime;
	@FiledName("delaytime")
	public Integer getDelaytime() {
		return delaytime;
	}
	public void setDelaytime(Integer delaytime) {
		this.delaytime = delaytime;
	}

	private String isgoon;
	@FiledName("isgoon")
	public String getIsgoon() {
		return isgoon;
	}
	public void setIsgoon(String isgoon) {
		this.isgoon = isgoon;
	}

	private Double courseleave;
	@FiledName("courseleave")
	public Double getCourseleave(){
		return this.courseleave;
	}
	public void setCourseleave(Double courseleave){
		 this.courseleave = courseleave;
	}

	private Timestamp coursestartline;
	@FiledName("coursestartline")
	public Timestamp getCoursestartline(){
		return this.coursestartline;
	}
	public void setCoursestartline(Timestamp coursestartline){
		 this.coursestartline = coursestartline;
	}

	private Timestamp courseendline;
	@FiledName("courseendline")
	public Timestamp getCourseendline(){
		return this.courseendline;
	}
	public void setCourseendline(Timestamp courseendline){
		 this.courseendline = courseendline;
	}

	private Long createby;
	@FiledName("createby")
	public Long getCreateby(){
		return this.createby;
	}
	public void setCreateby(Long createby){
		 this.createby = createby;
	}

	private Long updateby;
	@FiledName("updateby")
	public Long getUpdateby(){
		return this.updateby;
	}
	public void setUpdateby(Long updateby){
		 this.updateby = updateby;
	}

	private Timestamp createtime;
	@FiledName("createtime")
	public Timestamp getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(Timestamp createtime){
		 this.createtime = createtime;
	}

	private String createtime_str;
	@IgnoreColumn
	public String getCreatetime_str() {
		return createtime_str;
	}
	public void setCreatetime_str(String createtime_str) {
		this.createtime_str = createtime_str;
	}

	private String updatetime_str;
	@IgnoreColumn
	public String getUpdatetime_str() {
		return updatetime_str;
	}
	public void setUpdatetime_str(String updatetime_str) {
		this.updatetime_str = updatetime_str;
	}

	private Timestamp updatetime;
	@FiledName("updatetime")
	public Timestamp getUpdatetime(){
		return this.updatetime;
	}
	public void setUpdatetime(Timestamp updatetime){
		 this.updatetime = updatetime;
	}

	private String coursestatus;
	@FiledName("coursestatus")
	public String getCoursestatus(){
		return this.coursestatus;
	}
	public void setCoursestatus(String coursestatus){
		if(coursestatus==null){
			coursestatus="1";
		}
		 this.coursestatus = coursestatus;
	}

	private String weekdays;
	private String coarchName;
	private String coarchUrl;
	private String coarchServiceTime;
	private String coarchId;
	private String startline;
	private String casefieldname;
	@IgnoreColumn
	public String getCoarchId() {
		return coarchId;
	}

	public void setCoarchId(String coarchId) {
		this.coarchId = coarchId;
	}

	@IgnoreColumn
	public String getCoarchUrl() {
		return coarchUrl;
	}

	public void setCoarchUrl(String coarchUrl) {
		this.coarchUrl = coarchUrl;
	}
	@IgnoreColumn
	public String getCoarchServiceTime() {
		return coarchServiceTime;
	}

	public void setCoarchServiceTime(String coarchServiceTime) {
		this.coarchServiceTime = coarchServiceTime;
	}

	@IgnoreColumn
	public String getCasefieldname() {
		return casefieldname;
	}

	public void setCasefieldname(String casefieldname) {
		this.casefieldname = casefieldname;
	}

	@IgnoreColumn
	public String getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}
	@IgnoreColumn
	public String getCoarchName() {
		return coarchName;
	}

	public void setCoarchName(String coarchName) {
		this.coarchName = coarchName;
	}
	@IgnoreColumn
	public String getStartline() {
		return startline;
	}

	public void setStartline(String startline) {
		this.startline = startline;
	}

	private Date minsubstartline;
	@IgnoreColumn
	public Date getMinsubstartline(){
		return this.minsubstartline;
	}
	public void setMinsubstartline(Date minsubstartline){
		this.minsubstartline = minsubstartline;
	}

	private Date subendline;
	@IgnoreColumn
	public Date getSubendline(){
		return this.subendline;
	}
	public void setSubendline(Date subendline){
		this.subendline = subendline;
	}

}