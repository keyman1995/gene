package com.ycjcjy.gene.model;
import net.onebean.core.Pagination;
import net.onebean.core.extend.FiledName;
import net.onebean.core.extend.IgnoreColumn;
import net.onebean.core.extend.TableName;
import net.onebean.core.model.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@TableName("card_ticket_master")
public class CardTicketMaster extends BaseModel{


	private String ticket_type;//类别  0课程 1商品 2游泳健身卡
	private Integer org_id;
	private Double price;
	private Integer target_id;
	private Timestamp create_time;
	private Timestamp update_time;
	private Integer create_by;
	private Integer update_by;
	private String type_str;
    @IgnoreColumn
    public String getType_str() {
        return type_str;
    }

    public void setType_str(String type_str) {
        this.type_str = type_str;
    }

    @FiledName("ticket_type")
	public String getTicket_type() {
		return ticket_type;
	}

	public void setTicket_type(String ticket_type) {
		this.ticket_type = ticket_type;
	}

	@FiledName("org_id")
	public Integer getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}

	@FiledName("price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@FiledName("target_id")
	public Integer getTarget_id() {
		return target_id;
	}

	public void setTarget_id(Integer target_id) {
		this.target_id = target_id;
	}

	@FiledName("create_time")
	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	@FiledName("update_time")
	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	@FiledName("create_by")
	public Integer getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}

	@FiledName("update_by")
	public Integer getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(Integer update_by) {
		this.update_by = update_by;
	}

	private String cardName;
	private Double cardPrice;
	private String cardRemarks;
	private String endTimeStr;
	private Timestamp cardEndTime;
	private Integer zs;
	private Integer kf;
	private Integer zg;
	private Integer xs;
	private Integer yf;
	private Integer yy;
	private Integer gq;
	private String fieldName;
	private Integer count;
	private Boolean flag;
    @IgnoreColumn
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @FiledName("count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @IgnoreColumn
    public String getCardRemarks() {
        return cardRemarks;
    }

    public void setCardRemarks(String cardRemarks) {
        this.cardRemarks = cardRemarks;
    }

    @IgnoreColumn
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @IgnoreColumn
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    @IgnoreColumn
    public Double getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(Double cardPrice) {
        this.cardPrice = cardPrice;
    }
    @IgnoreColumn
    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
    @IgnoreColumn
    public Timestamp getCardEndTime() {
        return cardEndTime;
    }

    public void setCardEndTime(Timestamp cardEndTime) {
        this.cardEndTime = cardEndTime;
    }
    @IgnoreColumn
    public Integer getZs() {
        return zs;
    }

    public void setZs(Integer zs) {
        this.zs = zs;
    }
    @IgnoreColumn
    public Integer getKf() {
        return kf;
    }

    public void setKf(Integer kf) {
        this.kf = kf;
    }
    @IgnoreColumn
    public Integer getZg() {
        return zg;
    }

    public void setZg(Integer zg) {
        this.zg = zg;
    }
    @IgnoreColumn
    public Integer getXs() {
        return xs;
    }

    public void setXs(Integer xs) {
        this.xs = xs;
    }
    @IgnoreColumn
    public Integer getYf() {
        return yf;
    }

    public void setYf(Integer yf) {
        this.yf = yf;
    }
    @IgnoreColumn
    public Integer getYy() {
        return yy;
    }

    public void setYy(Integer yy) {
        this.yy = yy;
    }
    @IgnoreColumn
    public Integer getGq() {
        return gq;
    }

    public void setGq(Integer gq) {
        this.gq = gq;
    }

    /**
     * 提交到的ID组合
     */
    private String ids;
    @IgnoreColumn
    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }


    /**
     * 每组的数量
     */
    private String num;
    @IgnoreColumn
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    /**
     * 案场信息
     */

    private SysCaseField sysCaseField;
    @IgnoreColumn
    public SysCaseField getSysCaseField() {
        return sysCaseField;
    }
    public void setSysCaseField(SysCaseField sysCaseField) {
        this.sysCaseField = sysCaseField;
    }

    private String prices;
    @IgnoreColumn
    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    private String ticketTypes;
    @IgnoreColumn
    public String getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(String ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    /**渠道商系列**/

    private Integer to_type;
    private Integer channel_id;
    private String vedio_src;
    private String content;
    private String channel_script;
    private String video_img;
    @FiledName("video_img")
    public String getVideo_img() {
        return video_img;
    }

    public void setVideo_img(String video_img) {
        this.video_img = video_img;
    }

    @FiledName("channel_script")
    public String getChannel_script() {
        return channel_script;
    }

    public void setChannel_script(String channel_script) {
        this.channel_script = channel_script;
    }

    private String channel_name;
    @IgnoreColumn
    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    @FiledName("to_type")
    public Integer getTo_type() {
        return to_type;
    }

    public void setTo_type(Integer to_type) {
        this.to_type = to_type;
    }
    @FiledName("channel_id")
    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }
    @FiledName("vedio_src")
    public String getVedio_src() {
        return vedio_src;
    }

    public void setVedio_src(String vedio_src) {
        this.vedio_src = vedio_src;
    }
    @FiledName("content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
