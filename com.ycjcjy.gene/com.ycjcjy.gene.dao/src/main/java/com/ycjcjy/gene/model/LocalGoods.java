package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("local_goods")
public class LocalGoods extends BaseModel{

	private String goods_name;
	@FiledName("goods_name")
	public String getGoods_name(){
		return this.goods_name;
	}
	public void setGoods_name(String goods_name){
		 this.goods_name = goods_name;
	}

	private String img_url;
	@FiledName("img_url")
	public String getImg_url(){
		return this.img_url;
	}
	public void setImg_url(String img_url){
		 this.img_url = img_url;
	}

	private String type;
	@FiledName("type")
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		 this.type = type;
	}

	private String case_field_id;
	@FiledName("case_field_id")
	public String getCase_field_id() {
		return case_field_id;
	}

	public void setCase_field_id(String case_field_id) {
		this.case_field_id = case_field_id;
	}

	private Double price;
	@FiledName("price")
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price){
		 this.price = price;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Timestamp update_time;
	@FiledName("update_time")
	public Timestamp getUpdate_time(){
		return this.update_time;
	}
	public void setUpdate_time(Timestamp update_time){
		 this.update_time = update_time;
	}

	private String QRcode;
	@IgnoreColumn
	public String getQRcode(){ return this.QRcode;}
	public void setQRcode(String qRcode){this.QRcode=qRcode;}

	private String caseField;
    @IgnoreColumn
    public String getCaseField() {
        return caseField;
    }

    public void setCaseField(String caseField) {
        this.caseField = caseField;
    }

    private String is_show;
    @FiledName("is_show")
    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }
}