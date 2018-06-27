package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

/**
* @author Ken Hu
* @description 案场桌位管理 model
* @date 2018-06-11 15:35:49
*/
@TableName("sys_case_field_table")
public class SysCaseFieldTable extends BaseModel implements InterfaceBaseModel {
    private String is_show;
    @FiledName("is_show")
    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }


        private String num;
        @FiledName("num")
        public String getNum(){
            return this.num;
        }
        public void setNum(String num){
            this.num = num;
        }
        private String area;
        @FiledName("area")
        public String getArea(){
            return this.area;
        }
        public void setArea(String area){
            this.area = area;
        }
        private String case_field_id;
    @FiledName("case_field_id")
    public String getCase_field_id() {
        return case_field_id;
    }

    public void setCase_field_id(String case_field_id) {
        this.case_field_id = case_field_id;
    }

    private String areaName;
        @IgnoreColumn
        public String getAreaName() {
            return areaName;
        }
        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        private String tableInfo;
        @IgnoreColumn
        public String getTableInfo() {
            return tableInfo;
        }

        public void setTableInfo(String tableInfo) {
            this.tableInfo = tableInfo;
        }
        private String img_url;
    @IgnoreColumn
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}