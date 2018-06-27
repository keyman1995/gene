package com.ycjcjy.gene.web.action.user;

import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.model.Teacher;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.service.SysUserService;
import com.ycjcjy.gene.service.TeacherService;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.component.aliyun.AliyunSMSUtils;
import net.onebean.component.aliyun.AliyunSmsSendResult;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

/**
 * Created by szc on 2018/4/24.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CustomerUserService customerUserService;

    @RequestMapping("/sendSms")
    @ResponseBody
    public Map<String,Object> sendSms(String tel, HttpServletRequest request){
        Map<String,Object> map = new HashedMap();
        //判断是否为用户
        SysUser user = sysUserService.findUserByPhone(tel);
        map.put("result","200");
        map.put("msg","请求成功");
        boolean flag = true;
        if(null != user){

            if(user.getUser_type().equals("manager")){
                map.put("type",3);
            }else if(user.getUser_type().equals("sale")){
                map.put("type",2);
            }else{
                flag = false;
            }
        }else{
            Teacher teacher = teacherService.findByTel(tel);

            if(teacher!=null){
                map.put("type",teacher.getType());
            }else{
                flag = false;
            }
        }

        if(flag){
            Map<String,Object> param =  SmsUtil.sendValidCode(tel);

            if(!param.get("result").equals("OK")){
                map.put("result","400");
                map.put("msg","验证码发送失败");
                return map;
            }
            customerUserService.addPinCode(param);
        }else{
            map.put("result","400");
            map.put("msg","您不是该系统用户");
        }
        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String tel,String code, HttpServletRequest request){
        Map<String,Object> map = new HashedMap();
        try {


            map.put("tel", tel);
            Map<String, Object> a = customerUserService.getPinCode(map);
           if (a == null || a.get("code") == null || !code.equals(String.valueOf(a.get("code")))) {
                map.put("result", "400");
                map.put("msg", "验证码错误");
                return map;
            }

            CustomerUser customerUser = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            //判断是否为用户
            //更新用户手机号和openid
            SysUser user = sysUserService.findUserByPhone(tel);
            map.put("result", "200");
            map.put("msg", "请求成功");
            String openId = request.getSession().getAttribute(BaseConstans.SESSION_OPENID.getValue()).toString();
            int fff = 1;
            if (null != user) {

                if (user.getUser_type().equals("manager")) {
                    map.put("type", 3);
                    map.put("user", user);


                    user.setOpen_id(openId);
                    sysUserService.update(user);
                    fff = 0;
                } else if (user.getUser_type().equals("sale")) {
                    map.put("type", 2);
                    map.put("user", user);
                    user.setOpen_id(openId);
                    sysUserService.update(user);
                    //request.getSession().setAttribute(BaseConstans.SESSION_WXUSER.getValue(),user);
                    fff = 0;
                } else {
                    map.put("result", "400");
                    map.put("msg", "用户不允许登录");

                }
                if(customerUser!=null){
                    customerUser.setTel(tel);
                    customerUser.setUser_type(Integer.valueOf(map.get("type").toString()));
                    customerUserService.update(customerUser);
                }
            }

            Teacher teacher = teacherService.findByTel(tel);

            if (teacher != null) {

                map.put("type", teacher.getType());
                map.put("user", teacher);
                teacher.setOpen_id(openId);
                teacherService.update(teacher);
                //request.getSession().setAttribute(BaseConstans.SESSION_WXUSER.getValue(),teacher);
                if(customerUser!=null){
                    customerUser.setTel(tel);
                    customerUserService.update(customerUser);
                }

            } else if(fff==1){
                map.put("result", "400");
                map.put("msg", "手机号不存在");
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "400");
            map.put("msg", "系统错误");
        }
        return map;
    }

    @RequestMapping("/validIdentity")
    @ResponseBody
    public ResponseBean validIdentity(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();


        try{
            String openId = request.getSession().getAttribute(BaseConstans.SESSION_OPENID.getValue()).toString();
            //CustomerUser customerUser = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            Teacher teacher = teacherService.findByOpenId(openId);
            SysUser sysUser = sysUserService.findByOpenId(openId);

            if(teacher!=null){
                data.put("user", teacher);
                data.put("type", teacher.getType());
            }else if(sysUser!=null){
                if (sysUser.getUser_type().equals("manager")) {
                    data.put("type", 3);


                } else if (sysUser.getUser_type().equals("sale")) {
                    data.put("type", 2);


                }
                data.put("user",sysUser);
            }else{

                responseBean.setError(1,"需要手机号注册");
            }

            responseBean.setData(data);

        }catch (Exception e){
            e.printStackTrace();
            responseBean.setError("手机号不存在");
        }


        return responseBean;
    }

}
