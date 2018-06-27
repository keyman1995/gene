package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

import java.sql.Timestamp;
import java.util.List;

/**
* @author chenjie
* @description 5A服务 model
* @date 2018-05-28 09:26:17
*/
@TableName("server_manager")
public class ServerManager extends BaseModel implements InterfaceBaseModel {


        private String name;
        @FiledName("name")
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        private String title;
        @FiledName("title")
        public String getTitle(){
            return this.title;
        }
        public void setTitle(String title){
            this.title = title;
        }

      /*  private String courseids;
        @FiledName("courseids")
        public String getCourseids(){
            return this.courseids;
        }
        public void setCourseids(String courseids){
            this.courseids = courseids;
        }
        private String courseidstw;
        @FiledName("courseidstw")
        public String getCourseidstw() {
        return courseidstw;
    }
        public void setCourseidstw(String courseidstw) {
        this.courseidstw = courseidstw;
    }
        private String courseidsth;
        @FiledName("courseidsth")
        public String getCourseidsth() {
        return courseidsth;
    }
        public void setCourseidsth(String courseidsth) {
        this.courseidsth = courseidsth;
    }*/

        private Timestamp createtime;
        @FiledName("createtime")
        public Timestamp getCreatetime(){
            return this.createtime;
        }
        public void setCreatetime(Timestamp createtime){
            this.createtime = createtime;
        }
        private Long createby;
        @FiledName("createby")
        public Long getCreateby(){
            return this.createby;
        }
        public void setCreateby(Long createby){
            this.createby = createby;
        }
        private String status;
        @FiledName("status")
        public String getStatus(){
            return this.status;
        }
        public void setStatus(String status){
            this.status = status;
        }
        private String img_url;
        @FiledName("img_url")
        public String getImg_url() {
        return img_url;
        }
        public void setImg_url(String img_url) {
        this.img_url = img_url;
        }
        private String detail_img;
        @FiledName("detail_img")
         public String getDetail_img() {
        return detail_img;
    }
         public void setDetail_img(String detail_img) {
        this.detail_img = detail_img;
    }
        private String serverdetail;
        @FiledName("serverdetail")
        public String getServerdetail() {
        return serverdetail;
    }
        public void setServerdetail(String serverdetail) {
        this.serverdetail = serverdetail;
    }
        private Integer type;
        @FiledName("type")
        public Integer getType() {
            return type;
        }
        public void setType(Integer type) {
            this.type = type;
        }

        private String createtime_str;
        @IgnoreColumn
        public String getCreatetime_str() {
        return createtime_str;
        }
        public void setCreatetime_str(String createtime_str) {
        this.createtime_str = createtime_str;
        }


        private List<ServerLinkCourse> serverLinkCourses;
        @IgnoreColumn
        public List<ServerLinkCourse> getServerLinkCourses() {
        return serverLinkCourses;
        }
        public void setServerLinkCourses(List<ServerLinkCourse> serverLinkCourses) {
        this.serverLinkCourses = serverLinkCourses;
        }
}