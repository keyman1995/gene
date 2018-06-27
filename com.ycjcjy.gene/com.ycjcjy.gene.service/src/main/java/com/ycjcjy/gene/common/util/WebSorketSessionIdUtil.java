package com.ycjcjy.gene.common.util;

/**
 * websorketsessionId生成、获取
 * Created by szc on 2018/5/12.
 */
public class WebSorketSessionIdUtil {


    public static String getSessionId(int type,int userId){
        String sessionId = "";
        if(type==0){
            sessionId = "SYS_USER_"+userId;
        }else if(type==1){
            sessionId = "CUSTOMER_USER_"+userId;
        }else if(type==2){
            sessionId = "TEACHER_"+userId;
        }
        return sessionId;
    }

}
