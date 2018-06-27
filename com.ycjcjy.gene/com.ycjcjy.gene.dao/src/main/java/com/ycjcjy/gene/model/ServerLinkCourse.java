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
* @description 专题课程 model
* @date 2018-05-30 19:59:09
*/
@TableName("server_link_course")
public class ServerLinkCourse extends BaseModel implements InterfaceBaseModel {


        private String title;
        @FiledName("title")
        public String getTitle(){
            return this.title;
        }
        public void setTitle(String title){
            this.title = title;
        }
        private Long serverid;
        @FiledName("serverid")
        public Long getServerid() {
        return serverid;
        }
        public void setServerid(Long serverid) {
        this.serverid = serverid;
        }

        private String courseids;
        @FiledName("courseids")
        public String getCourseids(){
            return this.courseids;
        }
        public void setCourseids(String courseids){
            this.courseids = courseids;
        }

        private Timestamp createtime;
        @FiledName("createtime")
        public Timestamp getCreatetime() {
                return createtime;
        }
        public void setCreatetime(Timestamp createtime) {
                this.createtime = createtime;
        }

        private Long createby;
        @FiledName("createby")
        public Long getCreateby() {
                return createby;
        }
        public void setCreateby(Long createby) {
                this.createby = createby;
        }

        private List<CourseManagerVo> courseManagerVos;
        @IgnoreColumn
        public List<CourseManagerVo> getCourseManagerVos() {
                return courseManagerVos;
        }
        public void setCourseManagerVos(List<CourseManagerVo> courseManagerVos) {
                this.courseManagerVos = courseManagerVos;
        }
}