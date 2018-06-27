package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

import java.util.List;

/**
* @author Ken Hu
* @description 商品类型管理 model
* @date 2018-06-11 15:43:08
*/
@TableName("local_goods_type")
public class LocalGoodsType extends BaseModel implements InterfaceBaseModel {


        private String type;
        @FiledName("type")
        public String getType(){
            return this.type;
        }
        public void setType(String type){
            this.type = type;
        }
    private String is_show;
    @FiledName("is_show")
    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }
        private String code;
        @FiledName("code")
        public String getCode(){
            return this.code;
        }
        public void setCode(String code){
            this.code = code;
        }
        private Integer case_field_id;
    @FiledName("case_field_id")
    public Integer getCase_field_id() {
        return case_field_id;
    }

    public void setCase_field_id(Integer case_field_id) {
        this.case_field_id = case_field_id;
    }

    private List<LocalGoods> goodsList;
    @IgnoreColumn
    public List<LocalGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<LocalGoods> goodsList) {
        this.goodsList = goodsList;
    }
}