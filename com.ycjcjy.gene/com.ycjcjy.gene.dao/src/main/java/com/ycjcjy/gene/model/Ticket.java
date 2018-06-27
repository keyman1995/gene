package com.ycjcjy.gene.model;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

@TableName("goods_ticket")
public class Ticket extends BaseModel{


	private String startline_str;
	private String deadline_str;

	@IgnoreColumn
	public String getStartline_str() {
		return startline_str;
	}

	public void setStartline_str(String startline_str) {
		this.startline_str = startline_str;
	}

	@IgnoreColumn
	public String getDeadline_str() {
		return deadline_str;
	}

	public void setDeadline_str(String deadline_str) {
		this.deadline_str = deadline_str;
	}

	private String ticketname;
	@FiledName("ticketname")
	public String getTicketname(){
		return this.ticketname;
	}
	public void setTicketname(String ticketname){
		 this.ticketname = ticketname;
	}

	private String tickettype;
	@FiledName("tickettype")
	public String getTickettype(){
		return this.tickettype;
	}
	public void setTickettype(String tickettype){
		 this.tickettype = tickettype;
	}

	private Double ticketcondition;
	@FiledName("ticketcondition")
	public Double getTicketcondition(){
		return this.ticketcondition;
	}
	public void setTicketcondition(Double ticketcondition){
		 this.ticketcondition = ticketcondition;
	}

	private Double ticketmoney;
	@FiledName("ticketmoney")
	public Double getTicketmoney(){
		return this.ticketmoney;
	}
	public void setTicketmoney(Double ticketmoney){
		 this.ticketmoney = ticketmoney;
	}

	private Timestamp startline;
	@FiledName("startline")
	public Timestamp getStartline(){
		return this.startline;
	}
	public void setStartline(Timestamp startline){
		 this.startline = startline;
	}

	private Timestamp deadline;
	@FiledName("deadline")
	public Timestamp getDeadline(){
		return this.deadline;
	}
	public void setDeadline(Timestamp deadline){
		 this.deadline = deadline;
	}

	private String ticketdetail;
	@FiledName("ticketdetail")
	public String getTicketdetail(){
		return this.ticketdetail;
	}
	public void setTicketdetail(String ticketdetail){
		 this.ticketdetail = ticketdetail;
	}

	private Integer ticketcount;
	@FiledName("ticketcount")
	public Integer getTicketcount(){
		return this.ticketcount;
	}
	public void setTicketcount(Integer ticketcount){
		 this.ticketcount = ticketcount;
	}

	private Integer createuser;
	@FiledName("createuser")
	public Integer getCreateuser(){
		return this.createuser;
	}
	public void setCreateuser(Integer createuser){
		 this.createuser = createuser;
	}

	private Integer updateuser;
	@FiledName("updateuser")
	public Integer getUpdateuser(){
		return this.updateuser;
	}
	public void setUpdateuser(Integer updateuser){
		 this.updateuser = updateuser;
	}

	private Timestamp createtime;
	@FiledName("createtime")
	public Timestamp getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(Timestamp createtime){
		 this.createtime = createtime;
	}

	private Timestamp updatetime;
	@FiledName("updatetime")
	public Timestamp getUpdatetime(){
		return this.updatetime;
	}
	public void setUpdatetime(Timestamp updatetime){
		 this.updatetime = updatetime;
	}
}