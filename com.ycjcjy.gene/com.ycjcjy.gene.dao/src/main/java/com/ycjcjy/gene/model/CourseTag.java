package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

/**
* @author chenjie
* @description 课程标签 model
* @date 2018-05-28 13:31:09
*/
@TableName("course_tag")
public class CourseTag extends BaseModel implements InterfaceBaseModel {


        private String name;
        @FiledName("name")
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }


}