package com.ycjcjy.gene.web.action.user;

import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private CustomerUserService customerUserService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/sendSms")
    @ResponseBody
    public ResponseBean bindingTel(String tel) {
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            //发送短信
            Map<String, Object> par = SmsUtil.sendValidCode(tel);
            if (("OK").equals(String.valueOf(par.get("result")))) {
                responseBean.setSuccess(data, "发送验证码成功");
                customerUserService.addPinCode(par);
            } else {
                responseBean.setError("发送验证码失败");
                logger.error(String.valueOf(par.get("msg")));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }

        return responseBean;
    }

    @RequestMapping("/validate")
    @ResponseBody
    public ResponseBean validate(String tel, String code, HttpServletRequest request) {
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("tel", tel);
            Map<String, Object> par = customerUserService.getPinCode(map);
            if (par != null) {
                if ((code).equals(par.get("code").toString())) {
                    String openId = request.getSession().getAttribute(BaseConstans.SESSION_OPENID.getValue()).toString();
                    customerUserService.updateTelByOpenId(tel, openId);
                    responseBean.setSuccess(data, "绑定手机号成功");
                } else {
                    responseBean.setError("验证码错误");
                }
            } else {
                responseBean.setError("验证码过期，请重新获取验证码");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }


        return responseBean;
    }

}