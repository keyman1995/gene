package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("vip_card")
public class VipCard extends BaseModel{

	private String card_no;
	@FiledName("card_no")
	public String getCard_no(){
		return this.card_no;
	}
	public void setCard_no(String card_no){
		 this.card_no = card_no;
	}

	private Integer sale_id;
	@FiledName("sale_id")
	public Integer getSale_id(){
		return this.sale_id;
	}
	public void setSale_id(Integer sale_id){
		 this.sale_id = sale_id;
	}

	private Integer user_id;
	@FiledName("user_id")
	public Integer getUser_id(){
		return this.user_id;
	}
	public void setUser_id(Integer user_id){
		 this.user_id = user_id;
	}

	private Double price;

	@FiledName("price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 501 默认 502 已开卡 503 已激活
	 */
	private String state;
	@FiledName("state")
	public String getState(){
		return this.state;
	}
	public void setState(String state){
		 this.state = state;
	}

	private Timestamp create_time;
	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Timestamp open_time;
	@FiledName("open_time")
	public Timestamp getOpen_time(){
		return this.open_time;
	}
	public void setOpen_time(Timestamp open_time){
		 this.open_time = open_time;
	}

	private Timestamp valid_time;
	@FiledName("valid_time")
	public Timestamp getValid_time(){
		return this.valid_time;
	}
	public void setValid_time(Timestamp valid_time){
		 this.valid_time = valid_time;
	}

	private String create_time_str;
	private String open_time_str;
	private String valid_time_str;

	private Integer num;

	@IgnoreColumn
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@IgnoreColumn
	public String getCreate_time_str() {

		return create_time_str;
	}

	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	@IgnoreColumn
	public String getOpen_time_str() {
		return open_time_str;
	}

	public void setOpen_time_str(String open_time_str) {
		this.open_time_str = open_time_str;
	}
	@IgnoreColumn
	public String getValid_time_str() {
		return valid_time_str;
	}

	public void setValid_time_str(String valid_time_str) {
		this.valid_time_str = valid_time_str;
	}


	private String sale_phone;
	private String user_phone;
	@IgnoreColumn
	public String getSale_phone() {
		return sale_phone;
	}

	public void setSale_phone(String sale_phone) {
		this.sale_phone = sale_phone;
	}
	@IgnoreColumn
	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	private String casefieldname;
	private String salename;
	private String username;
	@IgnoreColumn
	public String getCasefieldname() {
		return casefieldname;
	}

	public void setCasefieldname(String casefieldname) {
		this.casefieldname = casefieldname;
	}
	@IgnoreColumn
	public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
	}
	@IgnoreColumn
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private Integer case_id;

	@FiledName("case_id")
	public Integer getCase_id() {
		return case_id;
	}

	public void setCase_id(Integer case_id) {
		this.case_id = case_id;
	}
}