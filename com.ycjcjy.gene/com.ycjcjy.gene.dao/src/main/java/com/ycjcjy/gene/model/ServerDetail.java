package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import net.onebean.core.model.InterfaceBaseModel;

/**
* @author chenjie
* @description 5A服务详情 model
* @date 2018-05-28 11:10:47
*/
@TableName("server_detail")
public class ServerDetail extends BaseModel implements InterfaceBaseModel {


        private String img_url;
        @FiledName("img_url")
        public String getImg_url(){
            return this.img_url;
        }
        public void setImg_url(String img_url){
            this.img_url = img_url;
        }
        private Integer sort;
        @FiledName("sort")
        public Integer getSort(){
            return this.sort;
        }
        public void setSort(Integer sort){
            this.sort = sort;
        }
        private Long serverid;
        @FiledName("serverid")
        public Long getServerid() {
        return serverid;
        }

         public void setServerid(Long serverid) {
        this.serverid = serverid;
        }
}