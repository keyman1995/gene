package com.ycjcjy.gene.VO;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/5/15
 **/
public enum CmsEnum {


    CMS_INDEX(2000,"首页"),
    CMS_SJ(0,"私教"),
    CMS_JS(2001,"健身"),
    CMS_GX(2002,"课程");

    private Integer code;
    private String value;

    CmsEnum(Integer code, String value) {
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
