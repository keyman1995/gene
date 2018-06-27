package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("evaluate_img")
public class EvaluateImg extends BaseModel{

	private String img_url;
	@FiledName("img_url")
	public String getImg_url(){
		return this.img_url;
	}
	public void setImg_url(String img_url){
		 this.img_url = img_url;
	}

	private Integer e_id;
	@FiledName("e_id")
	public Integer getE_id(){
		return this.e_id;
	}
	public void setE_id(Integer e_id){
		 this.e_id = e_id;
	}
}