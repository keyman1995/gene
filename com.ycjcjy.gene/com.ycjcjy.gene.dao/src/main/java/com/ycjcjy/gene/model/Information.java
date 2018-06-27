package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

import java.sql.Timestamp;

/**
* @author yibin
* @description 首页资讯 model
* @date 2018-05-28 10:04:00
*/
@TableName("information")
public class Information extends BaseModel implements InterfaceBaseModel {


        private String title;
        @FiledName("title")
        public String getTitle(){
            return this.title;
        }
        public void setTitle(String title){
            this.title = title;
        }

        private String url;
        @FiledName("url")
        public String getUrl(){
            return this.url;
        }
        public void setUrl(String url){
            this.url = url;
        }

        private Timestamp create_time;
        @FiledName("create_time")
        public Timestamp getCreate_time(){
            return this.create_time;
        }
        public void setCreate_time(Timestamp create_time){
            this.create_time = create_time;
        }

        private String create_time_str;
        @IgnoreColumn
        public String getCreate_time_str() {
            return create_time_str;
        }
        public void setCreate_time_str(String create_time_str) {
        this.create_time_str = create_time_str;
    }


}