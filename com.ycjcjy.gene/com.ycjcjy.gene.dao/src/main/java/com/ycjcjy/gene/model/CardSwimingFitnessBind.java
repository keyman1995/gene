package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@TableName("card_swiming_fitness_bind")
public class CardSwimingFitnessBind extends BaseModel{

	private Integer sys_user_id;
	@FiledName("sys_user_id")
	public Integer getSys_user_id(){
		return this.sys_user_id;
	}
	public void setSys_user_id(Integer sys_user_id){
		 this.sys_user_id = sys_user_id;
	}

	private Integer customer_user_id;
	@FiledName("customer_user_id")
	public Integer getCustomer_user_id(){
		return this.customer_user_id;
	}
	public void setCustomer_user_id(Integer customer_user_id){
		 this.customer_user_id = customer_user_id;
	}

	private Long create_user_id;
	@FiledName("create_user_id")
	public Long getCreate_user_id(){
		return this.create_user_id;
	}
	public void setCreate_user_id(Long create_user_id){
		 this.create_user_id = create_user_id;
	}

	private String create_user_name;
	@FiledName("create_user_name")
	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	private Timestamp create_time;
	private String create_time_str;
	@IgnoreColumn
	public String getCreate_time_str() {
		return create_time_str;
	}

	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}

	@FiledName("create_time")
	public Timestamp getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Timestamp create_time){
		 this.create_time = create_time;
	}

	private Long card_id;
	@FiledName("card_id")
	public Long getCard_id() {
		return card_id;
	}

	public void setCard_id(Long card_id) {
		this.card_id = card_id;
	}

	private String card_name;
	private String sys_user_name;
	private String customer_user_name;
	@FiledName("sys_user_name")
	public String getSys_user_name() {
		return sys_user_name;
	}

	public void setSys_user_name(String sys_user_name) {
		this.sys_user_name = sys_user_name;
	}
	@FiledName("customer_user_name")
	public String getCustomer_user_name() {
		return customer_user_name;
	}

	public void setCustomer_user_name(String customer_user_name) {
		this.customer_user_name = customer_user_name;
	}
	@FiledName("card_name")
	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	private Integer card_num;
	@FiledName("card_num")
	public Integer getCard_num() {
		return card_num;
	}

	public void setCard_num(Integer card_num) {
		this.card_num = card_num;
	}

	private Timestamp end_crad_time;
	@FiledName("end_crad_time")
	public Timestamp getEnd_crad_time() {
		return end_crad_time;
	}

	public void setEnd_crad_time(Timestamp end_crad_time) {
		this.end_crad_time = end_crad_time;
	}

	private String end_crad_time_str;
	@IgnoreColumn
	public String getEnd_crad_time_str() {
		return end_crad_time_str;
	}

	public void setEnd_crad_time_str(String end_crad_time_str) {
		this.end_crad_time_str = end_crad_time_str;
	}

	private BigDecimal price;
	@FiledName("price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}