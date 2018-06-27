package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

import java.util.List;

/**
* @author Ken Hu
* @description 案场区域管理 model
* @date 2018-06-11 15:26:53
*/
@TableName("sys_case_field_area")
public class SysCaseFieldArea extends BaseModel implements InterfaceBaseModel {
    private String is_show;
    @FiledName("is_show")
    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }


        private String area_name;
        @FiledName("area_name")
        public String getArea_name(){
            return this.area_name;
        }
        public void setArea_name(String area_name){
            this.area_name = area_name;
        }
        private String case_field_id;
        @FiledName("case_field_id")
        public String getCase_field_id(){
            return this.case_field_id;
        }
        public void setCase_field_id(String case_field_id){
            this.case_field_id = case_field_id;
        }
        private String img_url;
        @FiledName("img_url")
        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
        private String caseFiled;

        private String img_url_mono;
    @FiledName("img_url_mono")
    public String getImg_url_mono() {
        return img_url_mono;
    }

    public void setImg_url_mono(String img_url_mono) {
        this.img_url_mono = img_url_mono;
    }

    @IgnoreColumn
    public String getCaseFiled() {
        return caseFiled;
    }

    public void setCaseFiled(String caseFiled) {
        this.caseFiled = caseFiled;
    }

    public List<SysCaseFieldTable> tableList;
    @IgnoreColumn
    public List<SysCaseFieldTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<SysCaseFieldTable> tableList) {
        this.tableList = tableList;
    }
}