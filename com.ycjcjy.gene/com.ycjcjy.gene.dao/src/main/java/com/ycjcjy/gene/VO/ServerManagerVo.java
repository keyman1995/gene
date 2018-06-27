package com.ycjcjy.gene.VO;

import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.ServerManager;
import net.onebean.core.extend.IgnoreColumn;

import java.util.List;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/5/28
 **/
public class ServerManagerVo extends ServerManager {

    private List<CourseManager> firCourse;
    @IgnoreColumn
    public List<CourseManager> getFirCourse() {
        return firCourse;
    }
    public void setFirCourse(List<CourseManager> firCourse) {
        this.firCourse = firCourse;
    }

    private List<CourseManager> secCourse;
    @IgnoreColumn
    public List<CourseManager> getSecCourse() {
        return secCourse;
    }
    public void setSecCourse(List<CourseManager> secCourse) {
        this.secCourse = secCourse;
    }

    private List<CourseManager> thrCourse;
    @IgnoreColumn
    public List<CourseManager> getThrCourse() {
        return thrCourse;
    }

    public void setThrCourse(List<CourseManager> thrCourse) {
        this.thrCourse = thrCourse;
    }
}
