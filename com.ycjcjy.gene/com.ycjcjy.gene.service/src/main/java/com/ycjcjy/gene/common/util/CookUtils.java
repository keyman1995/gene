package com.ycjcjy.gene.common.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/5/12
 **/
public class CookUtils {

    /**
     *
     *  获取所有的Cookies
     * @author chenjie
     * @date 2018/5/12 16:26
     * @param [cookies, type]
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getAll(Cookie[] cookies, String type) {
        List<String> result = new ArrayList<>();
        Map<String, Cookie> cookieMap = toCookieMap(cookies);
        Cookie cookie = cookieMap.get(type);
        if (cookie != null) {
            String value = cookie.getValue();
            String[] values = value.split("#");
            for (int i = 0; i < values.length; i++) {
                result.add(values[i]);
            }
        }
        return result;
    }
    /**
     *
     *   设置cookies的值
     * @author chenjie
     * @date 2018/5/12 16:26
     * @param [cookies, type, value]
     * @return javax.servlet.http.Cookie
     */
    public static Cookie setCookie(Cookie[] cookies, String type, String value) {

        Map<String, Cookie> cookieMap = toCookieMap(cookies);
        Cookie cookie = cookieMap.get(type);
        if (cookie != null && StringUtils.hasText(cookie.getValue())
                && !(cookie.getValue().startsWith(value) || cookie.getValue().contains("#"+value))){
            //cookie值中“#”出现的次数
            int times = appearTimes(cookie.getValue(), "#");
            //10个值有9个“#”，小于9直接保存到cookie
            if(times < 9){
                cookie.setValue(value+"#"+cookie.getValue());
                cookie.setMaxAge(60*60*24*30);
                cookie.setPath("/");
            }else {
                String oldValue = cookie.getValue();
                //截取最新的9个值
                String newValue = oldValue.substring(0, oldValue.lastIndexOf("#"));
                cookie.setValue(value + "#" + newValue);
                cookie.setMaxAge(60*60*24*30);
                cookie.setPath("/");
            }
        }else if (cookie == null || !StringUtils.hasText(cookie.getValue())){
            cookie = new Cookie(type, value);
            cookie.setMaxAge(60*60*24*30);
            cookie.setPath("/");
        }
        return cookie;
    }
    /**
     *
     *  删除cookies
     * @author chenjie
     * @date 2018/5/12 16:26
     * @param [cookies, type]
     * @return javax.servlet.http.Cookie
     */
    public static Cookie delCookie(Cookie[] cookies, String type){
        Map<String, Cookie> cookieMap = toCookieMap(cookies);
        Cookie cookie = cookieMap.get(type);
        if (cookie != null){
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
        }
        return cookie;
    }

    public static Map<String, Cookie> toCookieMap(Cookie[] cookies) {
        Map<String, Cookie> result = new HashMap<>();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                result.put(cookie.getName(), cookie);
            }
        }
        return result;
    }

    public static int appearTimes(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }
}
