package com.ycjcjy.gene.model;

import net.onebean.core.extend.IgnoreColumn;

import java.util.Date;
import java.util.List;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/11
 **/
public class CourseManagerVo extends CourseManager {

    private String title;
    @IgnoreColumn
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Integer buycount;
    @IgnoreColumn
    public Integer getBuycount() {
        return buycount;
    }
    public void setBuycount(Integer buycount) {
        this.buycount = buycount;
    }

    private String teachername;
    @IgnoreColumn
    public String getTeachername() {
        return teachername;
    }
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    private String teacherimg;
    @IgnoreColumn
    public String getTeacherimg() {
        return teacherimg;
    }

    public void setTeacherimg(String teacherimg) {
        this.teacherimg = teacherimg;
    }

    private String teacherexperience;
    @IgnoreColumn
    public String getTeacherexperience() {
        return teacherexperience;
    }
    public void setTeacherexperience(String teacherexperience) {
        this.teacherexperience = teacherexperience;
    }

    private List<SubCourseManager> subCourseTimeList;
    @IgnoreColumn
    public List<SubCourseManager> getSubCourseTimeList() {
        return subCourseTimeList;
    }
    public void setSubCourseTimeList(List<SubCourseManager> subCourseTimeList) {
        this.subCourseTimeList = subCourseTimeList;
    }

    private List<CourseDetail> courseDetailList;
    @IgnoreColumn
    public List<CourseDetail> getCourseDetailList() {
        return courseDetailList;
    }
    public void setCourseDetailList(List<CourseDetail> courseDetailList) {
        this.courseDetailList = courseDetailList;
    }


    private String address;
    @IgnoreColumn
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    private String lng;
    @IgnoreColumn
    public String getLng() {
        return lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }

    private String lat;
    @IgnoreColumn
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    private String icon;
    @IgnoreColumn
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    private List<Evaluate> evaluates;
    @IgnoreColumn
    public List<Evaluate> getEvaluates() {
        return evaluates;
    }
    public void setEvaluates(List<Evaluate> evaluates) {
        this.evaluates = evaluates;
    }

    private String timeRange;
    @IgnoreColumn
    public String getTimeRange() {
        return timeRange;
    }
    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    private Date timeStart;
    @IgnoreColumn
    public Date getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    private Date timeEnd;
    @IgnoreColumn
    public Date getTimeEnd() {
        return timeEnd;
    }
    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    private String serverTel;
    @IgnoreColumn
    public String getServerTel() {
        return serverTel;
    }
    public void setServerTel(String serverTel) {
        this.serverTel = serverTel;
    }

    private List<CourseTag> tagList;
    @IgnoreColumn
    public List<CourseTag> getTagList() {
        return tagList;
    }
    public void setTagList(List<CourseTag> tagList) {
        this.tagList = tagList;
    }

    private String dic;
    @IgnoreColumn
    public String getDic() {
        return dic;
    }
    public void setDic(String dic) {
        this.dic = dic;
    }

    private Date nowDate;

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }
}
