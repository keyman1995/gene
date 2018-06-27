package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;

import java.sql.Timestamp;

@TableName("card_user_rela")
public class CardUserRela extends BaseModel{

	private Integer card_id;
	@FiledName("card_id")
	public Integer getCard_id(){
		return this.card_id;
	}
	public void setCard_id(Integer card_id){
		 this.card_id = card_id;
	}

	private Integer card_type;
	@FiledName("card_type")
	public Integer getCard_type(){
		return this.card_type;
	}
	public void setCard_type(Integer card_type){
		 this.card_type = card_type;
	}

	private Integer salve_id;
	@FiledName("salve_id")
	public Integer getSalve_id(){
		return this.salve_id;
	}
	public void setSalve_id(Integer salve_id){
		 this.salve_id = salve_id;
	}

	private Integer source;
	@FiledName("source")
	public Integer getSource(){
		return this.source;
	}
	public void setSource(Integer source){
		 this.source = source;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Timestamp start_time;
	@FiledName("start_time")
	public Timestamp getStart_time(){
		return this.start_time;
	}
	public void setStart_time(Timestamp start_time){
		 this.start_time = start_time;
	}

	private Timestamp end_time;
	@FiledName("end_time")
	public Timestamp getEnd_time(){
		return this.end_time;
	}
	public void setEnd_time(Timestamp end_time){
		 this.end_time = end_time;
	}

	private Integer user_id;
	@FiledName("user_id")
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		 this.user_id = user_id;
	}

	private Integer state;

	@FiledName("state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	private String startStr;
	private String endStr;

	private String name;
	private String remark;
	private Double price;
	private String gymType;
	private Integer cardcount;
	private Integer cardState;
	private String card_tag;
	private Integer courseId;
	@IgnoreColumn
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@IgnoreColumn
	public String getCard_tag() {
		return card_tag;
	}

	public void setCard_tag(String card_tag) {
		this.card_tag = card_tag;
	}

	@IgnoreColumn
	public Integer getCardState() {
		return cardState;
	}

	public void setCardState(Integer cardState) {
		this.cardState = cardState;
	}

	@IgnoreColumn
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@IgnoreColumn
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@IgnoreColumn
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	@IgnoreColumn
	public String getGymType() {
		return gymType;
	}

	public void setGymType(String gymType) {
		this.gymType = gymType;
	}
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
	public Integer getCardcount(){
		return this.cardcount;
	}
	public void setCardcount(Integer cardcount){
		this.cardcount = cardcount;
	}
}