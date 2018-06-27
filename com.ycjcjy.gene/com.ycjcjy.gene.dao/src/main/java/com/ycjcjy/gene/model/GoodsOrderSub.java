package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

/**
* @author szcc
* @description 商品订单子订单 model
* @date 2018-06-11 18:11:14
*/
@TableName("goods_order_sub")
public class GoodsOrderSub extends BaseModel implements InterfaceBaseModel {


        private String order_no;
        @FiledName("order_no")
        public String getOrder_no(){
            return this.order_no;
        }
        public void setOrder_no(String order_no){
            this.order_no = order_no;
        }
        private Integer order_state;
        @FiledName("order_state")
        public Integer getOrder_state(){
            return this.order_state;
        }
        public void setOrder_state(Integer order_state){
            this.order_state = order_state;
        }
        private String create_time;
        @FiledName("create_time")
        public String getCreate_time(){
            return this.create_time;
        }
        public void setCreate_time(String create_time){
            this.create_time = create_time;
        }
        private Double price;
        @FiledName("price")
        public Double getPrice(){
            return this.price;
        }
        public void setPrice(Double price){
            this.price = price;
        }
        private Integer saler_id;
        @FiledName("saler_id")
        public Integer getSaler_id(){
            return this.saler_id;
        }
        public void setSaler_id(Integer saler_id){
            this.saler_id = saler_id;
        }
        private Integer case_field_id;
        @FiledName("case_field_id")
        public Integer getCase_field_id(){
            return this.case_field_id;
        }
        public void setCase_field_id(Integer case_field_id){
            this.case_field_id = case_field_id;
        }
        private Integer goods_id;
        @FiledName("goods_id")
        public Integer getGoods_id(){
            return this.goods_id;
        }
        public void setGoods_id(Integer goods_id){
            this.goods_id = goods_id;
        }
        private Integer goods_num;
        @FiledName("goods_num")
        public Integer getGoods_num(){
            return this.goods_num;
        }
        public void setGoods_num(Integer goods_num){
            this.goods_num = goods_num;
        }


    private Integer pid;
    @FiledName("pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    private String goodsName;
    @IgnoreColumn
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer table_id;
    @FiledName("table_id")
    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }

    public Integer order_month;
    @FiledName("order_month")
    public Integer getOrder_month() {
        return order_month;
    }

    public void setOrder_month(Integer order_month) {
        this.order_month = order_month;
    }

    public String create_time_str;
    @IgnoreColumn
    public String getCreate_time_str() {
        return create_time_str;
    }

    public void setCreate_time_str(String create_time_str) {
        this.create_time_str = create_time_str;
    }

    public String salername;
    public String salertel;
    public String casefieldname;
    public String tableInfo;
    @IgnoreColumn
    public String getSalername() {
        return salername;
    }

    public void setSalername(String salername) {
        this.salername = salername;
    }
    @IgnoreColumn
    public String getSalertel() {
        return salertel;
    }

    public void setSalertel(String salertel) {
        this.salertel = salertel;
    }
    @IgnoreColumn
    public String getCasefieldname() {
        return casefieldname;
    }

    public void setCasefieldname(String casefieldname) {
        this.casefieldname = casefieldname;
    }

    @IgnoreColumn
    public String getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(String tableInfo) {
        this.tableInfo = tableInfo;
    }
}