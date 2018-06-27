package com.ycjcjy.gene.common;

import net.onebean.component.aliyun.AliyunSMSUtils;
import net.onebean.component.aliyun.AliyunSmsSendResult;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;

/**
 * Created by szc on 2018/4/24.
 */
public class SmsUtil {

    private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);
    private static String smsSignature = "城的空间";

    public static Map<String,Object> sendValidCode(String tel){
//        String smsTemplate = "SMS_133968633";
        String smsTemplate = "SMS_136398960";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        String code = getSix();
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"num\":\""+code+"\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        par.put("msg",aliyunSmsSendResult.getMsg());
//        logger.info(aliyunSmsSendResult.getCode()+"     "+aliyunSmsSendResult.getMsg()+"     "+aliyunSmsSendResult.getFlag());
        if("OK".equals(aliyunSmsSendResult.getCode())){

            par.put("tel",tel);
            par.put("code",code);

            return par;
        }else{
            return par;
        }
    }

    /**
     * 下单成功
     * @param tel
     * @param content
     * @return
     */
    public static boolean sendOrderSuccess(String tel,String content){
        String smsTemplate="SMS_134320645";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"name\":\""+content+"\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 上课提醒
     * @param tel
     * @param name
     * @param time
     * @return
     */
    public static boolean sendGoToClass(String tel,String name,String time){
        String smsTemplate="SMS_134315651";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"name\":\""+name+"\",\"time\":\"" + time + "\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 旷课提醒
     * @param tel
     * @return
     */
    public static boolean sendKuangke(String tel){
        String smsTemplate="SMS_134320657";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 教练预约成功
     * @param tel
     * @param place
     * @param time
     * @return
     */
    public static boolean sendCoachSuccess(String tel,String place,String time){
        String smsTemplate="SMS_134315653";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"place\":\""+place+"\",\"time\":\"" + time + "\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 优惠券过期
     * @param tel
     * @param num
     * @return
     */
    public static boolean sendTicketGuoQi(String tel,String num){
        String smsTemplate="SMS_134315654";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"num\":\""+num+"\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 会员卡过期（游泳健身卡）
     * @param tel
     * @param card
     * @param time
     * @return
     */
    public static boolean sendCardGuoQi(String tel,String card,String time){
        String smsTemplate="SMS_134310665";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"card\":\""+card+"\",\"time\":\"" + time + "\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 发送案场充值验证码
     * @param tel
     * @return
     */
    public static Map<String,Object> sendRechargeCode(String tel){
        String smsTemplate = "SMS_133968052";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        String code = getSix();
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"num\":\""+code+"\"}");
//        logger.info(aliyunSmsSendResult.getMsg());
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){

            par.put("tel",tel);
            par.put("code",code);

            return par;
        }else{
            return par;
        }
    }

    /**
     * 卡券赠送成功发短信
     * @param tel
     * @param name
     * @param ticket
     * @return
     */
    public static boolean sendTicketSuccess(String tel,String name,String ticket){
        String smsTemplate = "SMS_135801167";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",
                ""+telephone+"","{\"name\":\""+name+"\",\"ticket\":\"" + ticket + "\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 饮品使用量
     * @param tel
     * @return
     */
    public static boolean drinkUsePercent(String tel,String time,int percent,int num,int total){
        String smsTemplate="SMS_137665282";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"",
                "{\"percent\":\""+percent+"\",\"time\":\"" + time + "\",\"num\":\"" + num + "\",\"total\":\"("+total+"次)\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 饮品下单提醒
     * @param tel
     * @return
     */
    public static boolean drinkOrder(String tel,int no,int num){
        String smsTemplate="SMS_137411052";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"no\":\""+no+"\",\"num\":\"" + num + "\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 关闭点单功能
     * @param tel
     * @return
     */
    public static boolean closeOrderGongNeng(String tel,String name){
        String smsTemplate="SMS_137666032";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"name\":\""+name+"\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 开启点单功能
     * @param tel
     * @return
     */
    public static boolean openOrderGongNeng(String tel,String name){
        String smsTemplate="SMS_137671002";
        Map<String,Object> par = new HashedMap();
        String telephone = tel;
        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms(smsSignature,""+smsTemplate+"",""+telephone+"","{\"name\":\""+name+"\"}");
        par.put("result",aliyunSmsSendResult.getCode());
        if("OK".equals(aliyunSmsSendResult.getCode())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 产生随机的六位数
     * @return
     */
    public static String getSix(){
        Random rad=new Random();

        String result  = rad.nextInt(1000000) +"";

        if(result.length()!=6){
            return getSix();
        }
        return result;
    }
}
