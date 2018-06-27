package com.ycjcjy.gene.VO;

import com.ycjcjy.gene.model.CardTicketSalve;

/**
 * Created by szc on 2018/5/10.
 */
public class TicketSalerVo {
    //销售方面
    private Integer totalNum;//总数
    private Integer leftNum;//剩余
    private Integer userUseNum;//用户已使用
    private Integer giveUserNum;//给用户的数量
    private Double price;
    private String userName;
    private String cardName;
    private String touserTime;
    private Integer cardCount;
    private Integer goodsCount;
    private String shorName;
    private String saleName;
    private Integer saleId;
    private Integer orderState;
    private Integer goodsNum;
    private Integer totalCardCount;
    private Integer totalGoodsCount;
    private Integer canSendCard;
    private Integer canSend;

    public Integer getCanSendCard() {
        return canSendCard;
    }

    public void setCanSendCard(Integer canSendCard) {
        this.canSendCard = canSendCard;
    }

    public Integer getCanSend() {
        return canSend;
    }

    public void setCanSend(Integer canSend) {
        this.canSend = canSend;
    }

    public Integer getTotalCardCount() {
        return totalCardCount;
    }

    public void setTotalCardCount(Integer totalCardCount) {
        this.totalCardCount = totalCardCount;
    }

    public Integer getTotalGoodsCount() {
        return totalGoodsCount;
    }

    public void setTotalGoodsCount(Integer totalGoodsCount) {
        this.totalGoodsCount = totalGoodsCount;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getGiveUserNum() {
        return giveUserNum;
    }

    public void setGiveUserNum(Integer giveUserNum) {
        this.giveUserNum = giveUserNum;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getCardCount() {
        return cardCount;
    }

    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getShorName() {
        return shorName;
    }

    public void setShorName(String shorName) {
        this.shorName = shorName;
    }



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getTouserTime() {
        return touserTime;
    }

    public void setTouserTime(String touserTime) {
        this.touserTime = touserTime;
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

    public Integer getUserUseNum() {
        return userUseNum;
    }

    public void setUserUseNum(Integer userUseNum) {
        this.userUseNum = userUseNum;
    }

}
