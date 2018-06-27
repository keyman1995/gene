package com.ycjcjy.gene.VO;

/**
 * @version V1.0
 * @class_name: com.ycjcjy.gene.VO
 * @param: $METHOD_PARAM$
 * @describe: TODO
 * @creat_user: MAT
 * @creat_time: 2018/5/16 10:12
 **/
public class CoachClassListVO {
    private Integer id;
    private String coursename;
    private Double coursetime;
    private Integer leftLeeson;
    private Integer courseId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getLeftLeeson() {
        return leftLeeson;
    }

    public void setLeftLeeson(Integer leftLeeson) {
        this.leftLeeson = leftLeeson;
    }

    public Double getCoursetime() {
        return coursetime;
    }

    public void setCoursetime(Double coursetime) {
        this.coursetime = coursetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}
