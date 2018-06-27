package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@TableName("course_sub_manager")
public class SubCourseManager extends BaseModel{


	private List<CourseManager> courseManagers;


	private Date date;
	@IgnoreColumn
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private String substartline_str;
	private String subendline_str;
	private String startStr;
	private String endStr;
	private String startStrb;
	private String endStrb;
	@IgnoreColumn
	public String getStartStrb() {
		return startStrb;
	}

	public void setStartStrb(String startStrb) {
		this.startStrb = startStrb;
	}
	@IgnoreColumn
	public String getEndStrb() {
		return endStrb;
	}

	public void setEndStrb(String endStrb) {
		this.endStrb = endStrb;
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
    public String getSubstartline_str() {
        return substartline_str;
    }

    public void setSubstartline_str(String substartline_str) {
        this.substartline_str = substartline_str;
    }
    @IgnoreColumn
    public String getSubendline_str() {
        return subendline_str;
    }

    public void setSubendline_str(String subendline_str) {
        this.subendline_str = subendline_str;
    }

    private String weekday;
	@FiledName("weekday")
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	@IgnoreColumn
	public List<CourseManager> getCourseManagers() {
		return courseManagers;
	}
	public void setCourseManagers(List<CourseManager> courseManagers) {
		this.courseManagers = courseManagers;
	}

	private Integer pcourseid;
	@FiledName("pcourseid")
	public Integer getPcourseid(){
		return this.pcourseid;
	}
	public void setPcourseid(Integer pcourseid){
		 this.pcourseid = pcourseid;
	}

	private String subcousename;
	@FiledName("subcousename")
	public String getSubcousename(){
		return this.subcousename;
	}
	public void setSubcousename(String subcousename){
		 this.subcousename = subcousename;
	}

	private Timestamp substartline;
	@FiledName("substartline")
	public Timestamp getSubstartline(){
		return this.substartline;
	}
	public void setSubstartline(Timestamp substartline){
		 this.substartline = substartline;
	}

	private Timestamp subendline;
	@FiledName("subendline")
	public Timestamp getSubendline(){
		return this.subendline;
	}
	public void setSubendline(Timestamp subendline){
		 this.subendline = subendline;
	}

	private Long createby;
	@FiledName("createby")
	public Long getCreateby(){
		return this.createby;
	}
	public void setCreateby(Long createby){
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

	private String subDeatil;
	@FiledName("subDeatil")
	public String getSubDeatil() {
		return subDeatil;
	}
	public void setSubDeatil(String subDeatil) {
		this.subDeatil = subDeatil;
	}

	private Integer subminnum;
	@FiledName("subminnum")
	public Integer getSubminnum(){
		return this.subminnum;
	}
	public void setSubminnum(Integer subminnum){
		 this.subminnum = subminnum;
	}

	private Integer makeupCount;
	@IgnoreColumn
	public Integer getMakeupCount() {
		return makeupCount;
	}

	public void setMakeupCount(Integer makeupCount) {
		this.makeupCount = makeupCount;
	}
	private String pName;
	@IgnoreColumn
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	private  String fieldName;
	@IgnoreColumn
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	private String start_time;
	private String end_time;
	@IgnoreColumn
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	@IgnoreColumn
	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	private  String caseId;
	@IgnoreColumn
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
}