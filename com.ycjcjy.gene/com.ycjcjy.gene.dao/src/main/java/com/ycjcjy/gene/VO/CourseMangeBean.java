package com.ycjcjy.gene.VO;

import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.SubCourseManager;

import java.util.List;
import java.util.Map;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/16
 **/
public class CourseMangeBean {

    private List<Map<String,Object>> courseManagers;

    private List<SubCourseManager> subCourseManagers;

    public List<Map<String, Object>> getCourseManagers() {
        return courseManagers;
    }

    public void setCourseManagers(List<Map<String, Object>> courseManagers) {
        this.courseManagers = courseManagers;
    }

    public List<SubCourseManager> getSubCourseManagers() {
        return subCourseManagers;
    }

    public void setSubCourseManagers(List<SubCourseManager> subCourseManagers) {
        this.subCourseManagers = subCourseManagers;
    }
}
