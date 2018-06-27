package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("course_makeup_manager")
public class CourseMakeupManager extends BaseModel{

	private Integer user_id;
	@FiledName("user_id")
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		 this.user_id = user_id;
	}

	private Integer course_id;
	@FiledName("course_id")
	public Integer getCourse_id(){
		return this.course_id;
	}
	public void setCourse_id(Integer course_id){
		 this.course_id = course_id;
	}

	private Integer order_id;
	@FiledName("order_id")
	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	private Integer status;
	@FiledName("status")
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		 this.status = status;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Timestamp solve_time;
	@FiledName("solve_time")
	public Timestamp getSolve_time() {
		return solve_time;
	}

	public void setSolve_time(Timestamp solve_time) {
		this.solve_time = solve_time;
	}

	private Integer solver_id;
	@FiledName("solver_id")
	public Integer getSolver_id() {
		return solver_id;
	}

	public void setSolver_id(Integer solver_id) {
		this.solver_id = solver_id;
	}

	private Timestamp newCourseTime;
	@IgnoreColumn
	public Timestamp getNewCourseTime() {
		return newCourseTime;
	}

	public void setNewCourseTime(Timestamp newCourseTime) {
		this.newCourseTime = newCourseTime;
	}

	private String courseName;
	private String courseParentName;
	private Timestamp starttime;
	private Timestamp endtime;

	@IgnoreColumn
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@IgnoreColumn
	public String getCourseParentName() {
		return courseParentName;
	}

	public void setCourseParentName(String courseParentName) {
		this.courseParentName = courseParentName;
	}
	@IgnoreColumn
	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	@IgnoreColumn
	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
}