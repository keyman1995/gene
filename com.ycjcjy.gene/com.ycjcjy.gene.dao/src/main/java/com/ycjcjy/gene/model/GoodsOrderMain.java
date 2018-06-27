package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

import java.util.List;

/**
* @author szcc
* @description 商品订单主表 model
* @date 2018-06-11 18:10:40
*/
@TableName("goods_order_main")
public class GoodsOrderMain extends BaseModel implements InterfaceBaseModel {


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
        private String done_time;
        @FiledName("done_time")
        public String getDone_time(){
            return this.done_time;
        }
        public void setDone_time(String done_time){
            this.done_time = done_time;
        }
        private Integer flag;
        @FiledName("flag")
        public Integer getFlag(){
            return this.flag;
        }
        public void setFlag(Integer flag){
            this.flag = flag;
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
        private String remark;
        @FiledName("remark")
        public String getRemark(){
            return this.remark;
        }
        public void setRemark(String remark){
            this.remark = remark;
        }

        private List<GoodsOrderSub> list;
    @IgnoreColumn
    public List<GoodsOrderSub> getList() {
        return list;
    }

    public void setList(List<GoodsOrderSub> list) {
        this.list = list;
    }

    public Integer table_id;
    @FiledName("table_id")
    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }

    public String tableInfo;
    @IgnoreColumn
    public String getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(String tableInfo) {
        this.tableInfo = tableInfo;
    }


    public String create_time_str;
    @IgnoreColumn
    public String getCreate_time_str() {
        return create_time_str;
    }

    public void setCreate_time_str(String create_time_str) {
        this.create_time_str = create_time_str;
    }

    public String img_url;
    @IgnoreColumn
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String realname;
    @IgnoreColumn
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String img_url_mono;
    @IgnoreColumn
    public String getImg_url_mono() {
        return img_url_mono;
    }

    public void setImg_url_mono(String img_url_mono) {
        this.img_url_mono = img_url_mono;
    }

    public String haomiaoshu;
    @IgnoreColumn
    public String getHaomiaoshu() {
        return haomiaoshu;
    }

    public void setHaomiaoshu(String haomiaoshu) {
        this.haomiaoshu = haomiaoshu;
    }
}