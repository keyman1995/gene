package com.ycjcjy.gene.VO;

import java.sql.Timestamp;

/**
 * @version V1.0
 * @class_name: com.ycjcjy.gene.VO
 * @param: $METHOD_PARAM$
 * @describe: TODO
 * @creat_user: MAT
 * @creat_time: 2018/6/12 17:47
 **/
public class ExportExcelVO {
    private String order_no;
    private String goodsType;
    private String goodsName;
    private String goods_num;
    private String tableInfo;
    private String create_str;
    private String salername;
    private String casefieldname;
    private String order_state;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(String tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getCreate_str() {
        return create_str;
    }

    public void setCreate_str(String create_str) {
        this.create_str = create_str;
    }

    public String getSalername() {
        return salername;
    }

    public void setSalername(String salername) {
        this.salername = salername;
    }

    public String getCasefieldname() {
        return casefieldname;
    }

    public void setCasefieldname(String casefieldname) {
        this.casefieldname = casefieldname;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }
}
