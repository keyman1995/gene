package com.ycjcjy.gene.VO;

import com.ycjcjy.gene.model.CourseType;
import net.onebean.core.BaseTree;

import java.util.List;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/14
 **/
public class CourseTypeTree extends BaseTree {

    public final static String TYPE_ITEM = "item";
    public final static String TYPE_FOLDER = "folder";

    private Long id;
    private Integer sort;
    private String type_img;
    private List<CourseTypeTree> childList;
    private List<CourseType> dataList;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType_img() {
        return type_img;
    }

    public void setType_img(String type_img) {
        this.type_img = type_img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CourseTypeTree> getChildList() {
        return childList;
    }

    public void setChildList(List<CourseTypeTree> childList) {
        this.childList = childList;
    }

    public List<CourseType> getDataList() {
        return dataList;
    }

    public void setDataList(List<CourseType> dataList) {
        this.dataList = dataList;
    }
}
