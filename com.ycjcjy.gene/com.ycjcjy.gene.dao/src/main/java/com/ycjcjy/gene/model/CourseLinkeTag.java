package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

/**
* @author chenjie
* @description 课程标签关联表 model
* @date 2018-05-28 16:59:16
*/
@TableName("course_linke_tag")
public class CourseLinkeTag extends BaseModel implements InterfaceBaseModel {


        private String courseid;
        @FiledName("courseid")
        public String getCourseid(){
            return this.courseid;
        }
        public void setCourseid(String courseid){
            this.courseid = courseid;
        }
        private String tagid;
        @FiledName("tagid")
        public String getTagid(){
            return this.tagid;
        }
        public void setTagid(String tagid){
            this.tagid = tagid;
        }


}