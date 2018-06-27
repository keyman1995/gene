package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("card_goods_basic")
public class CardGoodsBasic extends BaseModel{

	private String name;
	@FiledName("name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		 this.name = name;
	}

	private Long goods_id;
	@FiledName("goods_id")
	public Long getGoods_id(){
		return this.goods_id;
	}
	public void setGoods_id(Long goods_id){
		 this.goods_id = goods_id;
	}

	public Double price;
	public Timestamp start_time;
	public Timestamp end_time;
	public String remarks;
	@FiledName("price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	@FiledName("start_time")
	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	@FiledName("end_time")
	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	@FiledName("remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	private String type;
	private String img_url;
	private String goods_name;
	private String startStr;
	private String endStr;

	@IgnoreColumn
    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }
    @IgnoreColumn
    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    @IgnoreColumn
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@IgnoreColumn
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	@IgnoreColumn
	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
}