package com.ycjcjy.gene.VO;

/**
 * Created by szc on 2018/5/8.
 */
public enum CourseEnum {
    // 800讲座 801预约课程 802系列课程 803 私教课程 804团体课程

    COURSE_GX(2,"共享教室"),
    COURSE_JS(3,"健身"),
    COURSE_JZ(800,"讲座课程"),
    COURSE_YY(801,"预约课程"),
    COURSE_XL(802,"系列课程"),
    COURSE_SJ(803,"私教课程"),
    COURSE_TT(804,"团体课程");

    private Integer code;

    private String value;

    CourseEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
