package com.ycjcjy.gene.web.action.userRecharge;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.model.UserRecharge;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.*;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userrecharge")
public class UserRechargeController extends TlBaseController<UserRecharge,UserRechargeService> {

    @Autowired
    private UserRechargeService userRechargeService;

    @Autowired
    private SysCaseFieldService sysCaseFieldService;

    @Autowired
    private CustomerUserService customerUserService;

    @Autowired
    private DicDictionaryService dicDictionaryService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("userrecharge")
    @ResponseBody
    public PageResult<UserRecharge> userrecharge (Sort sort, Pagination page, PageResult<UserRecharge> result,UserRecharge userRecharge,
                               @RequestParam(value = "conditionList",required = false) String cond,HttpServletRequest request){
//        initData(sort,page,cond);
        userRecharge = reflectionModelFormConditionMapStr(cond,sort,page);
        userRecharge.setType("711");
//        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
//        Integer count = 0;
//        if(sysUser.getUser_type().equals("admin")){
//            count = baseService.getAllCount(userRecharge);
//            dataList = baseService.findUserRecharges(userRecharge);
//        }else{
//            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
//            userRecharge.setCase_field_id(case_field_id);
//            count = baseService.getAllCount(userRecharge);
//            dataList = baseService.findUserRecharges(userRecharge);
//        }
        Integer count = baseService.getAllCount(userRecharge);
        dataList = baseService.findUserRecharges(userRecharge);
        dicCoverList(null,"dic@RECHARGE_STATUS$status","dic@RECHARGE_TYPE$type","date@create_time$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }

    /**
     * 预览用户充值列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "preview0")
    @Description(value = "预览用户充值列表页面")
    public String preview0(Model model) {
        return getView("userrecharge");
    }



    @RequestMapping("casefieldrecharge")
    @ResponseBody
    public PageResult<UserRecharge> casefieldrecharge (Sort sort, Pagination page, PageResult<UserRecharge> result,UserRecharge userRecharge,
                                                  @RequestParam(value = "conditionList",required = false) String cond,HttpServletRequest request){
//        initData(sort,page,cond);
        userRecharge = reflectionModelFormConditionMapStr(cond,sort,page);
        userRecharge.setType("712");
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        Integer count = 0;
        if(sysUser.getUser_type().equals("admin")){
            count = baseService.getAllCount(userRecharge);
            dataList = baseService.findUserRecharges(userRecharge);
        }else{
            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            userRecharge.setCase_field_id(case_field_id);
            count = baseService.getAllCount(userRecharge);
            dataList = baseService.findUserRecharges(userRecharge);
        }
        dicCoverList(null,"dic@RECHARGE_TYPE$type","date@create_time$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }

    /**
     * 预览案场充值列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "preview1")
    @Description(value = "预览案场充值列表页面")
    public String preview1(Model model,HttpServletRequest request) {
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);

        if(sysUser.getUser_type().equals("admin")){
        }else{
            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            SysCaseField sysCaseField = sysCaseFieldService.findByFileldId(String.valueOf(case_field_id));
            Double avail = sysCaseField.getAvail();
            DecimalFormat df = new DecimalFormat("###############0.00");
            String temp = df.format(avail);
            String casefieldname = sysCaseField.getCasefieldname();
            model.addAttribute("avail",temp);
            model.addAttribute("casefieldname",casefieldname);
        }

        return getView("casefieldrecharge");
    }



    @RequestMapping("backstagerecharge")
    @ResponseBody
    public PageResult<UserRecharge> backstagerecharge (Sort sort, Pagination page, PageResult<UserRecharge> result,UserRecharge userRecharge,
                                                  @RequestParam(value = "conditionList",required = false) String cond,HttpServletRequest request){
//        initData(sort,page,cond);
        userRecharge = reflectionModelFormConditionMapStr(cond,sort,page);
        userRecharge.setType("713");
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        Integer count = 0;
        if(sysUser.getUser_type().equals("admin")){
            count = baseService.getAllCount(userRecharge);
            dataList = baseService.findUserRecharges(userRecharge);
        }else{
            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            userRecharge.setCase_field_id(case_field_id);
            count = baseService.getAllCount(userRecharge);
            dataList = baseService.findUserRecharges(userRecharge);
        }
        dicCoverList(null,"dic@RECHARGE_TYPE$type","date@create_time$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }

    /**
     * 预览后台充值列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "preview2")
    @Description(value = "预览后台充值列表页面")
    public String preview2(Model model,HttpServletRequest request) {
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);

        if(sysUser.getUser_type().equals("admin")){
        }else{
            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            SysCaseField sysCaseField = sysCaseFieldService.findByFileldId(String.valueOf(case_field_id));
            Double avail = sysCaseField.getAvail();
            DecimalFormat df = new DecimalFormat("###############0.00");
            String temp = df.format(avail);
            String casefieldname = sysCaseField.getCasefieldname();
            model.addAttribute("avail",temp);
            model.addAttribute("casefieldname",casefieldname);
        }
        return getView("backstagerecharge");
    }

//    /**
//     * 用户充值页面
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "usertouser")
//    @Description(value = "用户充值页面")
//    public String usertouser(Model model, UserRecharge entity) {
//        model.addAttribute("usertouser",true);
//        model.addAttribute("entity",entity);
//        return getView("recharge2");
//    }

    /**
     * 案场充值页面
     * @param model
     * @return
     */
    @RequestMapping(value = "casefieldtocasefield")
    @Description(value = "案场充值页面")
    public String casefieldtocasefield(Model model, UserRecharge entity,HttpServletRequest request) {
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        if(sysUser.getUser_type().equals("admin")){

        }else{
            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            SysCaseField sysCaseField = sysCaseFieldService.findByFileldId(String.valueOf(case_field_id));
            Double avail = sysCaseField.getAvail();
            DecimalFormat df = new DecimalFormat("###############0.00");
            String temp = df.format(avail);
            String casefieldname = sysCaseField.getCasefieldname();
            model.addAttribute("avail",temp);
            model.addAttribute("casefieldname",casefieldname);
            model.addAttribute("case_field_id",case_field_id);
        }


        model.addAttribute("casefieldtocasefield",true);
        model.addAttribute("entity",entity);
        return getView("casefieldrechargedetail");
    }

    /**
     * 案场充值
     * @param model
     * @return
     */
    @RequestMapping(value = "savecasefieldrecharge")
    @Description(value = "案场的充值")
    @ResponseBody
    public PageResult<UserRecharge> savecasefieldrecharge(Model model, UserRecharge entity, PageResult<UserRecharge> result, HttpServletRequest request) {

        result = userRechargeService.savecasefieldrecharge(model,entity,result,request);
//        if (entity.getId()!=null){
//            UserRecharge userRecharge = userRechargeService.findByUserRechargeId(Integer.parseInt(String.valueOf(entity.getId())));
//            if (userRecharge.getIdentifyingcode().equals(request.getParameter("identifyingcode"))&userRecharge.getStatus().equals("701")){
//                SysCaseField sysCaseField = sysCaseFieldService.findById(String.valueOf(userRecharge.getCase_field_id()));
//                Double avail = sysCaseField.getAvail();
//                if(avail!=null){
//                    avail = userRecharge.getActual();
//                    sysCaseFieldService.updateAvailById(avail,Long.parseLong(String.valueOf(sysCaseField.getId())));
//                    userRechargeService.updateStatusById("702",entity.getId());
//
//                    result.setFlag(true);
//                }
//            }else{
//                result.setFlag(false);
//                result.setMsg("验证码错误，请重新验证！");
//            }
//        }else {
//            String case_field_id = request.getParameter("case_field_id");
//            if(case_field_id != null){
//
//
//            }else{
//                case_field_id = request.getParameter("casefieldid");
//            }
//            SysCaseField sysCaseField = sysCaseFieldService.findById(case_field_id);
//            String casefieldname = sysCaseField.getCasefieldname();
//            String tel = request.getParameter("tel");
//            Double actual = Double.parseDouble(request.getParameter("actual"));
//
//            if(actual>0){
//                //生成验证码
//                String identifyingcode = initCode();
//
//                entity.setType("712");
//                entity.setCase_field_id(Integer.parseInt(String.valueOf(case_field_id)));
//                entity.setCasefieldname(casefieldname);
//
//                entity.setStatus("701");
//                entity.setActual(actual);
//                //生成订单号
//                entity.setOrder_id("c"+new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date()));
//                entity.setIdentifyingcode(identifyingcode);
//
//
//                String telephone = DictionaryUtils.dic("TELEPHONE_BACKSTAGE","721");
//                String smsTemplate = DictionaryUtils.dic("SMSTEMPLATE","722");
//
//                //发送短信
//                AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms("城的空间",""+smsTemplate+"",""+telephone+"","{\"num\":\""+identifyingcode+"\"}");
//                if("OK".equals(aliyunSmsSendResult.getCode())){
//                    result.setFlag(true);
//                    userRechargeService.add(entity);
//                }else{
//                    result.setFlag(false);
//                    result.setMsg("验证码发送失败，请重新尝试！");
//                }
//
//            }else{
//                result.setFlag(false);
//                result.setMsg("充值金额不正确，请重新确定充值信息！");
//            }
//
//        }
//        result.getData().add(entity);
        return  result;
    }

    /**
     * 案场充值重新发送验证码
     * @param
     * @return
     */
    @RequestMapping(value = "resend")
    @Description(value = "重新发送")
    @ResponseBody
    public PageResult<UserRecharge> resend(Integer id,PageResult<UserRecharge> result) {
        result = userRechargeService.resend(id,result);
//        try {
//            UserRecharge userRecharge = userRechargeService.findByUserRechargeId(id);
//            SysCaseField sysCaseField = sysCaseFieldService.findById(String.valueOf(userRecharge.getCase_field_id()));
//            String casefieldname = sysCaseField.getCasefieldname();
//            String status = userRecharge.getStatus();
//            if(status.equals("701")){
//                //生成验证码
//                String identifyingcode = initCode();
//
//
//
//                String telephone = DictionaryUtils.dic("TELEPHONE_BACKSTAGE","721");
//                String smsTemplate = DictionaryUtils.dic("SMSTEMPLATE","722");
//                //发送短信
//                AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms("城的空间",""+smsTemplate+"",""+telephone+"","{\"num\":\""+identifyingcode+"\"}");
//
//                if("OK".equals(aliyunSmsSendResult.getCode())){
//                    result.put("success",1);
//                    result.put("msg","重新发送验证码成功！");
//                    userRechargeService.updateIdentifyingcodeById(identifyingcode,Long.parseLong(String.valueOf(id)));
//                }else{
//                    result.put("success",0);
//                    result.put("msg","重新发送验证码失败！");
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            result.put("success",0);
//            result.put("msg","重新发送验证码失败！");
//        }
//
//
//
         return result;
    }

    /**
     * 取消充值
     * @param
     * @return
     */
    @RequestMapping(value = "cancel")
    @Description(value = "取消充值")
    @ResponseBody
    public Map<String,Object> cancel(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();
        result = userRechargeService.cancel(id);
//        UserRecharge userRecharge = userRechargeService.findByUserRechargeId(id);
//        String status = userRecharge.getStatus();
//        if(status.equals("701")){
//            userRechargeService.updateStatusById("704",Long.parseLong(String.valueOf(id)));
//        }
//
//        result.put("success",1);
//        result.put("msg","取消充值成功！");
        return result;
    }

    /**
     * 案场充值验证页面
     * @param model
     * @return
     */
    @RequestMapping(value = "validate/{id}")
    @Description(value = "验证页面")
    public String validate(Model model,@PathVariable("id")Object id) {
        UserRecharge entity = userRechargeService.findByUserRechargeId(Integer.parseInt(String.valueOf(id)));
        Double actual = entity.getActual();
        DecimalFormat df = new DecimalFormat("###############0.00");
        String temp = df.format(actual);

        model.addAttribute("actual",temp);
        model.addAttribute("entity",entity);
        model.addAttribute("validate",true);
        return getView("casefieldrechargedetail");
    }

    /**
     * 后台充值验证页面
     * @param model
     * @return
     */
    @RequestMapping(value = "validate1/{id}")
    @Description(value = "验证页面")
    public String validate1(Model model,@PathVariable("id")Object id) {
        UserRecharge entity = userRechargeService.findByUserRechargeId(Integer.parseInt(String.valueOf(id)));
        Double actual = entity.getActual();
        DecimalFormat df = new DecimalFormat("###############0.00");
        String temp = df.format(actual);

        model.addAttribute("actual",temp);
        model.addAttribute("entity",entity);
        model.addAttribute("validate1",true);
        return getView("backstagerechargedetail");
    }

    /**
     * 后台充值页面
     * @param model
     * @return
     */
    @RequestMapping(value = "casefieldtouser")
    @Description(value = "后台充值页面")
    public String casefieldtouser(Model model, UserRecharge entity,HttpServletRequest request) {
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        if(sysUser.getUser_type().equals("admin")){

        }else{
            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            SysCaseField sysCaseField = sysCaseFieldService.findByFileldId(String.valueOf(case_field_id));
            String avail = sysCaseField.getAvail().toString();
            String casefieldname = sysCaseField.getCasefieldname();
            model.addAttribute("avail",avail);
            model.addAttribute("casefieldname",casefieldname);
            model.addAttribute("case_field_id",case_field_id);
        }
        model.addAttribute("casefieldtouser",true);
        model.addAttribute("entity",entity);
        return getView("backstagerechargedetail");
    }

    /**
     * 后台充值
     * @param model
     * @return
     */
    @RequestMapping(value = "savebackstagerecharge")
    @Description(value = "后台充值")
    @ResponseBody
    public PageResult<UserRecharge> savebackstagerecharge(Model model, UserRecharge entity, PageResult<UserRecharge> result, HttpServletRequest request) {
        result = userRechargeService.savebackstagerecharge(model,entity,result,request);
//        if (entity.getId()!=null){
//            UserRecharge userRecharge = userRechargeService.findByUserRechargeId(Integer.parseInt(String.valueOf(entity.getId())));
//            if (userRecharge.getIdentifyingcode().equals(request.getParameter("identifyingcode"))&userRecharge.getStatus().equals("701")){
//                SysCaseField sysCaseField = sysCaseFieldService.findById(String.valueOf(userRecharge.getCase_field_id()));
//                Double avail = sysCaseField.getAvail();
//                CustomerUser customerUser = customerUserService.findById(String.valueOf(userRecharge.getUser_id()));
//                Double gift_balance = customerUser.getGift_balance();
//                if(avail!=null){
//                    avail = -(userRecharge.getActual());
//                    gift_balance += userRecharge.getActual();
//                    sysCaseFieldService.updateAvailById(avail,Long.parseLong(String.valueOf(sysCaseField.getId())));
//                    customerUserService.updateGiftBalanceById(gift_balance,customerUser.getId());
//                    userRechargeService.updateStatusById("702",entity.getId());
//
//                    result.setFlag(true);
//                }
//
//            }else{
//                result.setFlag(false);
//                result.setMsg("验证码错误，请重新验证！");
//            }
//        }else {
//            String case_field_id = request.getParameter("case_field_id");
//            String tel = request.getParameter("tel");
//            String mobile = request.getParameter("mobile");
//            Double actual = Double.parseDouble(request.getParameter("actual"));
//
//            if(case_field_id != null){
//
//            }else{
//                case_field_id = request.getParameter("casefieldid");
//            }
//
//            if(actual>0){
//                CustomerUser customerUser = customerUserService.findByTel(mobile);
//                SysCaseField sysCaseField = sysCaseFieldService.findById(case_field_id);
//                String casefieldname = sysCaseField.getCasefieldname();
//                if(customerUser != null){
//                    //生成验证码
//                    String identifyingcode = initCode();
//
//                    entity.setType("713");
//                    entity.setCase_field_id(Integer.parseInt(String.valueOf(case_field_id)));
//                    entity.setCasefieldname(casefieldname);
//
//                    entity.setUser_id(Integer.parseInt(String.valueOf(customerUser.getId())));
//                    entity.setActual(actual);
//
//                    //生成订单号
//                    entity.setOrder_id("c"+new SimpleDateFormat("yyyyMMdd").format(new Date())+System.currentTimeMillis());
//                    entity.setIdentifyingcode(identifyingcode);
//
//                    if(sysCaseField.getAvail() >= actual){
//                        entity.setStatus("701");
//
//                        String smsTemplate = DictionaryUtils.dic("SMSTEMPLATE","722");
//
//                        //发送短信
//                        AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms("城的空间",""+smsTemplate+"",""+tel+"","{\"num\":\""+identifyingcode+"\"}");
//                        if("OK".equals(aliyunSmsSendResult.getCode())){
//                            result.setFlag(true);
//                            userRechargeService.add(entity);
//                        }else{
//                            result.setFlag(false);
//                            result.setMsg("验证码发送失败，请重新尝试！");
//                        }
//                    }else{
//                        result.setFlag(false);
//                        result.setMsg("充值失败，案场余额不足！");
//                    }
//                }else{
//                    result.setFlag(false);
//                    result.setMsg("充值用户不存在，请检查用户的联系电话是否证确！");
//                }
//
//            }else{
//                result.setFlag(false);
//                result.setMsg("充值金额不正确，请检查充值信息！");
//            }
//
//
//
//
//        }
//        result.getData().add(entity);
        return  result;
    }

    /**
     * 后台充值重新发送验证码
     * @param
     * @return
     */
    @RequestMapping(value = "resend1")
    @Description(value = "重新发送")
    @ResponseBody
    public Map<String,Object> resend1(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();
        result = userRechargeService.resend1(id);
//        try {
//            UserRecharge userRecharge = userRechargeService.findByUserRechargeId(id);
//            String username = userRecharge.getUsername();
//            Integer user_id = userRecharge.getUser_id();
//            CustomerUser customerUser = customerUserService.findById(String.valueOf(user_id));
//            String mobile = customerUser.getTel();
//            Integer case_field_id = userRecharge.getCase_field_id();
//            SysCaseField sysCaseField = sysCaseFieldService.findById(String.valueOf(case_field_id));
//            String tel = sysCaseField.getTel();
//
//            String status = userRecharge.getStatus();
//            if (status.equals("701")) {
//                //生成验证码
//                String identifyingcode = initCode();
//
//                String smsTemplate = DictionaryUtils.dic("SMSTEMPLATE","722");
//                //发送短信
//                AliyunSmsSendResult aliyunSmsSendResult = AliyunSMSUtils.sendSms("城的空间", ""+smsTemplate+"", ""+tel+"", "{\"num\":\"" + identifyingcode + "\"}");
//
//                if("OK".equals(aliyunSmsSendResult.getCode())){
//                    result.put("success",1);
//                    result.put("msg","重新发送验证码成功！");
//                    userRechargeService.updateIdentifyingcodeById(identifyingcode, Long.parseLong(String.valueOf(id)));
//                }else{
//                    result.put("success",0);
//                    result.put("msg","重新发送验证码失败！");
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            result.put("success",0);
//            result.put("msg","重新发送验证码失败");
//        }

        return result;
    }




}
