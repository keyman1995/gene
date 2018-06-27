package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

/**
* @author Ken Hu
* @description 渠道管理 model
* @date 2018-05-28 09:55:09
*/
@TableName("channel")
public class Channel extends BaseModel implements InterfaceBaseModel {


        private String name;
        @FiledName("name")
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        private String code;
        @FiledName("code")
        public String getCode(){
            return this.code;
        }
        public void setCode(String code){
            this.code = code;
        }


}