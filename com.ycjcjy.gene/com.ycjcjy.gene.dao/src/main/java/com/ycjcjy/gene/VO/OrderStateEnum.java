package com.ycjcjy.gene.VO;

/**
 * Created by szc on 2018/5/8.
 */
public enum OrderStateEnum {
    //  0 待支付 1 已支付 2 已关闭 3已完成

    ORDERMAIN_DZF("0","待支付"),
    ORDERMAIN_YZF("1","已支付"),
    ORDERMAIN_YWC("2","已完成"),
    ORDERMAIN_YGB("3","已关闭"),

    //0 已生成 1 已完成 2 预约中 3 进行中 4 已取消
    ORDERSUB_YSC("0","已生成"),
    ORDERSUB_YWC("1","已完成"),
    ORDERSUB_YYZ("2","预约中"),
    ORDERSUB_JXZ("3","进行中"),
    ORDERSUB_YQX("4","已取消");

    private String code;

    private String value;

    OrderStateEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
