package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.common.dictionary.DictionaryUtils;
import com.ycjcjy.gene.dao.CustomerUserDao;
import com.ycjcjy.gene.dao.SysCaseFieldDao;
import com.ycjcjy.gene.dao.UserRechargeDao;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.model.UserRecharge;
import com.ycjcjy.gene.service.UserRechargeService;
import net.onebean.core.BaseBiz;
import net.onebean.core.PageResult;
import net.onebean.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserRechargeServiceImpl extends BaseBiz<UserRecharge, UserRechargeDao> implements UserRechargeService{

    @Autowired
    private UserRechargeDao userRechargeDao;

    @Autowired
    private SysCaseFieldDao sysCaseFieldDao;

    @Autowired
    private CustomerUserDao customerUserDao;

    @Override
    public void add(UserRecharge entity) {
        userRechargeDao.add(entity);
    }

    @Override
    public UserRecharge findById(Integer id) {
        return userRechargeDao.findById(id);
    }

    @Override
    public void updateStatusById(String status, Long id) {
        userRechargeDao.updateStatusById(status,id);
    }

    @Override
    public UserRecharge findByUserRechargeId(Integer id) {
        return userRechargeDao.findByUserRechargeId(id);
    }

    @Override
    public void updateIdentifyingcodeById(String identifyingcode, Long id,Timestamp update_time) {
        userRechargeDao.updateIdentifyingcodeById(identifyingcode,id,update_time);
    }

    @Override
    public List<UserRecharge> findByType(String type) {
        return userRechargeDao.findByType(type);
    }

    @Override
    public Integer getAllCount(UserRecharge userRecharge) {
        return baseDao.getAllCount(userRecharge);
    }

    @Override
    public List<UserRecharge> findUserRecharges(UserRecharge userRecharge) {
        return baseDao.findUserRecharges(userRecharge);
    }

    @Override
    public PageResult<UserRecharge> savecasefieldrecharge(Model model, UserRecharge entity, PageResult<UserRecharge> result, HttpServletRequest request) {
        if (entity.getId()!=null){
            UserRecharge userRecharge = userRechargeDao.findByUserRechargeId(Integer.parseInt(String.valueOf(entity.getId())));
            if(userRecharge.getUpdate_time().getTime()+3600000 < System.currentTimeMillis()){
                result.setFlag(false);
                result.setMsg("验证码过期，请重新验证！");
            }else{
                if (userRecharge.getIdentifyingcode().equals(request.getParameter("identifyingcode"))&userRecharge.getStatus().equals("701")){
                    SysCaseField sysCaseField = sysCaseFieldDao.findById(String.valueOf(userRecharge.getCase_field_id()));
                    Double avail = sysCaseField.getAvail();
                    if(avail!=null){
                        avail = userRecharge.getActual();
                        sysCaseFieldDao.updateAvailById(avail,Long.parseLong(String.valueOf(sysCaseField.getId())));
                        userRechargeDao.updateStatusById("702",entity.getId());

                        result.setFlag(true);
                    }
                }else{
                    result.setFlag(false);
                    result.setMsg("验证码错误，请重新验证！");
                }
            }

        }else {
            String case_field_id = request.getParameter("case_field_id");
            if(case_field_id != null){


            }else{
                case_field_id = request.getParameter("casefieldid");
            }
            SysCaseField sysCaseField = sysCaseFieldDao.findById(case_field_id);
            String casefieldname = sysCaseField.getCasefieldname();
            String tel = request.getParameter("tel");
            Double actual = Double.parseDouble(request.getParameter("actual"));

            if(actual>0){

                entity.setType("712");
                entity.setCase_field_id(Integer.parseInt(String.valueOf(case_field_id)));
                entity.setCasefieldname(casefieldname);

                entity.setStatus("701");
                entity.setActual(actual);
                //生成订单号
                entity.setOrder_id("c"+new SimpleDateFormat("yyyyMMdd").format(new Date())+System.currentTimeMillis());

                String telephone = DictionaryUtils.dic("TELEPHONE_BACKSTAGE","721");

                //发送短信
                Map<String,Object> par = SmsUtil.sendRechargeCode(telephone);
                if(("OK").equals(String.valueOf(par.get("result")))){
                    result.setFlag(true);
                    Date nowDate = new Date();
                    String identifyingcode = par.get("code").toString();
                    entity.setIdentifyingcode(identifyingcode);
                    entity.setUpdate_time(DateUtils.dateToTimeStamp(nowDate));
                    userRechargeDao.add(entity);
                }else if(("isv.BUSINESS_LIMIT_CONTROL").equals(String.valueOf(par.get("result")))){
                    result.setMsg("验证码重新发送失败，请勿频繁发送验证码！");
                }else if(("isv.MOBILE_NUMBER_ILLEGAL").equals(String.valueOf(par.get("result")))){
                    result.setMsg("验证码重新发送失败，手机号码不正确！");
                }else if(("isv.OUT_OF_SERVICE").equals(String.valueOf(par.get("result")))){
                    result.setMsg("验证码重新发送失败，请联系管理员！");
                }else{
                    result.setFlag(false);
                    result.setMsg("验证码重新发送失败！");
                }

            }else{
                result.setFlag(false);
                result.setMsg("充值金额不正确，请重新确定充值信息！");
            }

        }
        result.getData().add(entity);
        return  result;
    }

    @Override
    public PageResult<UserRecharge> resend(Integer id,PageResult<UserRecharge> result) {
        try {
            UserRecharge userRecharge = userRechargeDao.findByUserRechargeId(id);
            SysCaseField sysCaseField = sysCaseFieldDao.findById(String.valueOf(userRecharge.getCase_field_id()));
            String casefieldname = sysCaseField.getCasefieldname();
            String status = userRecharge.getStatus();
            if(status.equals("701")){

                String telephone = DictionaryUtils.dic("TELEPHONE_BACKSTAGE","721");

                Map<String,Object> par = SmsUtil.sendRechargeCode(telephone);
                if(("OK").equals(String.valueOf(par.get("result")))){
                    result.setFlag(true);
                    result.setMsg("重新发送验证码成功！");
                    Date nowDate = new Date();
                    Timestamp update_time = DateUtils.dateToTimeStamp(nowDate);
                    String identifyingcode = par.get("code").toString();
                    userRechargeDao.updateIdentifyingcodeById(identifyingcode,Long.parseLong(String.valueOf(id)),update_time);
                }else if(("isv.BUSINESS_LIMIT_CONTROL").equals(String.valueOf(par.get("result")))){
                    result.setMsg("验证码重新发送失败，请勿频繁发送验证码！");
                }else if(("isv.MOBILE_NUMBER_ILLEGAL").equals(String.valueOf(par.get("result")))){
                    result.setMsg("验证码重新发送失败，手机号码不正确！");
                }else if(("isv.OUT_OF_SERVICE").equals(String.valueOf(par.get("result")))){
                    result.setMsg("验证码重新发送失败，请联系管理员！");
                }else{
                    result.setFlag(false);
                    result.setMsg("验证码重新发送失败！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setFlag(false);
            result.setMsg("重新发送验证码失败！");
        }



        return result;
    }

    @Override
    public Map<String, Object> cancel(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();

        UserRecharge userRecharge = userRechargeDao.findByUserRechargeId(id);
        String status = userRecharge.getStatus();
        if(status.equals("701")){
            userRechargeDao.updateStatusById("704",Long.parseLong(String.valueOf(id)));
        }

        result.put("success",1);
        result.put("msg","取消充值成功！");
        return result;
    }

    @Override
    public PageResult<UserRecharge> savebackstagerecharge(Model model, UserRecharge entity, PageResult<UserRecharge> result, HttpServletRequest request) {
        if (entity.getId()!=null){
            UserRecharge userRecharge = userRechargeDao.findByUserRechargeId(Integer.parseInt(String.valueOf(entity.getId())));
            if(userRecharge.getUpdate_time().getTime()+3600000 < System.currentTimeMillis()){
                result.setFlag(false);
                result.setMsg("验证码过期，请重新验证！");
            }else{
                if (userRecharge.getIdentifyingcode().equals(request.getParameter("identifyingcode"))&userRecharge.getStatus().equals("701")){
                    SysCaseField sysCaseField = sysCaseFieldDao.findById(String.valueOf(userRecharge.getCase_field_id()));
                    Double avail = sysCaseField.getAvail();
                    CustomerUser customerUser = customerUserDao.findById(String.valueOf(userRecharge.getUser_id()));
                    Double gift_balance = customerUser.getGift_balance();
                    if(avail!=null){
                        avail = -(userRecharge.getActual());
                        gift_balance += userRecharge.getActual();
                        sysCaseFieldDao.updateAvailById(avail,Long.parseLong(String.valueOf(sysCaseField.getId())));
                        customerUserDao.updateGiftBalanceById(gift_balance,customerUser.getId());
                        userRechargeDao.updateStatusById("702",entity.getId());

                        result.setFlag(true);
                    }

                }else{
                    result.setFlag(false);
                    result.setMsg("验证码错误，请重新验证！");
                }
            }

        }else {
            String case_field_id = request.getParameter("case_field_id");
            String tel = request.getParameter("tel");
            String mobile = request.getParameter("mobile");
            Double actual = Double.parseDouble(request.getParameter("actual"));

            if(case_field_id != null){

            }else{
                case_field_id = request.getParameter("casefieldid");
            }

            if(actual>0){
                CustomerUser customerUser = customerUserDao.findByTel(mobile);
                SysCaseField sysCaseField = sysCaseFieldDao.findById(case_field_id);
                String casefieldname = sysCaseField.getCasefieldname();
                if(customerUser != null){

                    entity.setType("713");
                    entity.setCase_field_id(Integer.parseInt(String.valueOf(case_field_id)));
                    entity.setCasefieldname(casefieldname);

                    entity.setUser_id(Integer.parseInt(String.valueOf(customerUser.getId())));
                    entity.setActual(actual);

                    //生成订单号
                    entity.setOrder_id("c"+new SimpleDateFormat("yyyyMMdd").format(new Date())+System.currentTimeMillis());

                    if(sysCaseField.getAvail() >= actual){
                        entity.setStatus("701");

                        //发送短信
                        Map<String,Object> par = SmsUtil.sendRechargeCode(tel);
                        if(("OK").equals(String.valueOf(par.get("result")))){
                            result.setFlag(true);
                            Date nowDate = new Date();
                            String identifyingcode = par.get("code").toString();
                            entity.setIdentifyingcode(identifyingcode);
                            entity.setUpdate_time(DateUtils.dateToTimeStamp(nowDate));
                            userRechargeDao.add(entity);
                        }else if(("isv.BUSINESS_LIMIT_CONTROL").equals(String.valueOf(par.get("result")))){
                            result.setMsg("验证码重新发送失败，请勿频繁发送验证码！");
                        }else if(("isv.MOBILE_NUMBER_ILLEGAL").equals(String.valueOf(par.get("result")))){
                            result.setMsg("验证码重新发送失败，手机号码不正确！");
                        }else if(("isv.OUT_OF_SERVICE").equals(String.valueOf(par.get("result")))){
                            result.setMsg("验证码重新发送失败，请联系管理员！");
                        }else{
                            result.setFlag(false);
                            result.setMsg("验证码重新发送失败！");
                        }
                    }else{
                        result.setFlag(false);
                        result.setMsg("充值失败，案场余额不足！");
                    }
                }else{
                    result.setFlag(false);
                    result.setMsg("充值用户不存在，请检查用户的联系电话是否证确！");
                }

            }else{
                result.setFlag(false);
                result.setMsg("充值金额不正确，请检查充值信息！");
            }




        }
        result.getData().add(entity);
        return  result;
    }

    @Override
    public Map<String, Object> resend1(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            UserRecharge userRecharge = userRechargeDao.findByUserRechargeId(id);
            String username = userRecharge.getUsername();
            Integer user_id = userRecharge.getUser_id();
            CustomerUser customerUser = customerUserDao.findById(String.valueOf(user_id));
            String mobile = customerUser.getTel();
            Integer case_field_id = userRecharge.getCase_field_id();
            SysCaseField sysCaseField = sysCaseFieldDao.findById(String.valueOf(case_field_id));
            String tel = sysCaseField.getTel();

            String status = userRecharge.getStatus();
            if (status.equals("701")) {
                //发送短信
                Map<String,Object> par = SmsUtil.sendRechargeCode(tel);

                if(("OK").equals(String.valueOf(par.get("result")))){
                    result.put("success",1);
                    result.put("msg","重新发送验证码成功！");
                    Date nowDate = new Date();
                    Timestamp update_time = DateUtils.dateToTimeStamp(nowDate);
                    String identifyingcode = par.get("code").toString();
                    userRechargeDao.updateIdentifyingcodeById(identifyingcode, Long.parseLong(String.valueOf(id)),update_time);
                }else{
                    result.put("success",0);
                    result.put("msg","重新发送验证码失败！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",0);
            result.put("msg","重新发送验证码失败");
        }

        return result;
    }

    /**
     * 生成充值订单，插入到充值表中
     * @param user_id 当前用户的ID
     * @param price 充值金额
     * @param gift_price 赠送金额
     * @return 返回的是这笔充值订单的信息，但订单的状态是审核中，表示充值还未成功
     */
    @Override
    public UserRecharge insertCustomeruserRecharge(Integer user_id,Double price,Double gift_price) {
        UserRecharge userRecharge = null;
        userRecharge.setUser_id(user_id);
        userRecharge.setActual(price);
        userRecharge.setGift(gift_price);
        userRecharge.setStatus("701");

        //生成订单号
        userRecharge.setOrder_id("c"+new SimpleDateFormat("yyyyMMdd").format(new Date())+System.currentTimeMillis());

        baseDao.insertCustomeruserRecharge(userRecharge);

        return userRecharge;
    }

    /**
     * 当支付成功之后，修改充值订单的状态为充值成功
     * @param userRecharge
     */
    @Override
    public void updateStatusByOrderId(UserRecharge userRecharge) {
        userRecharge.setStatus("702");
        customerUserDao.updateBalanceById(userRecharge.getActual(),userRecharge.getGift(),Long.parseLong(String.valueOf(userRecharge.getUser_id())));
        baseDao.updateStatusByOrderId(userRecharge);
    }


    /**
     * 生成验证码
     * @return
     */
    public static String initCode(){

        Random r = new Random();
        String identifyingcode = "";
        String chars = "0123456789";
        for (int i = 0; i < 6; i++) {
            identifyingcode += chars.charAt(r.nextInt(chars.length()));
        }
        return identifyingcode;
    }
}