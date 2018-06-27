package com.ycjcjy.gene.VO;

import java.util.List;

/**
 * Created by szc on 2018/5/10.
 */
public class TicketCardVo {
    //卡片信息
    private Integer totalNum;//总数
    private Integer leftNum;//剩余
    private Integer giveSaleNum;//已分配给销售
    private Integer giveUserNum;//已分配给用户
    private Integer userUseNum;//用户已使用
    private String cardName;
    private Integer isGuoQi;
    private Integer isWeiDao;
    private String cardStartTime;
    private String cardEndTime;
    private String remark;
    private Double price;
    private Integer masterId;
    private Integer to_type;
    private Integer channel_id;
    private String vedio_src;
    private String content;
    private String caseFieldName;
    private String caseFieldLocation;
    private String channelScript;
    private String videoImg;
    private String ticket_type;
    private Integer target_id;

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }

    public String getChannelScript() {
        return channelScript;
    }

    public void setChannelScript(String channelScript) {
        this.channelScript = channelScript;
    }

    public String getCaseFieldName() {
        return caseFieldName;
    }

    public void setCaseFieldName(String caseFieldName) {
        this.caseFieldName = caseFieldName;
    }

    public String getCaseFieldLocation() {
        return caseFieldLocation;
    }

    public void setCaseFieldLocation(String caseFieldLocation) {
        this.caseFieldLocation = caseFieldLocation;
    }

    public Integer getTo_type() {
        return to_type;
    }

    public void setTo_type(Integer to_type) {
        this.to_type = to_type;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public String getVedio_src() {
        return vedio_src;
    }

    public void setVedio_src(String vedio_src) {
        this.vedio_src = vedio_src;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsWeiDao() {
        return isWeiDao;
    }

    public void setIsWeiDao(Integer isWeiDao) {
        this.isWeiDao = isWeiDao;
    }

    public Integer getGiveUserNum() {
        return giveUserNum;
    }

    public void setGiveUserNum(Integer giveUserNum) {
        this.giveUserNum = giveUserNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getIsGuoQi() {
        return isGuoQi;
    }

    public void setIsGuoQi(Integer isGuoQi) {
        this.isGuoQi = isGuoQi;
    }

    public String getCardStartTime() {
        return cardStartTime;
    }

    public void setCardStartTime(String cardStartTime) {
        this.cardStartTime = cardStartTime;
    }

    public String getCardEndTime() {
        return cardEndTime;
    }

    public void setCardEndTime(String cardEndTime) {
        this.cardEndTime = cardEndTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public Integer getGiveSaleNum() {
        return giveSaleNum;
    }

    public void setGiveSaleNum(Integer giveSaleNum) {
        this.giveSaleNum = giveSaleNum;
    }

    public Integer getUserUseNum() {
        return userUseNum;
    }

    public void setUserUseNum(Integer userUseNum) {
        this.userUseNum = userUseNum;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
    }
}
