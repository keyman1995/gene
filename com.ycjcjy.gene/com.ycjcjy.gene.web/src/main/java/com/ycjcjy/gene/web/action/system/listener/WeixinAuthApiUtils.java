package com.ycjcjy.gene.web.action.system.listener;

import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.ycjcjy.gene.web.action.system.util.AppConfig;


/**
 * 微信服务获取公共类
 * @author 0neBean
 */
public class WeixinAuthApiUtils {

    //微信配置实例
    private ApiConfig apiConfig;
    //API方法实例
    private OauthAPI oauthAPI;
    //单例锁
    private static Integer LOCK = 0;

    private static WeixinAuthApiUtils self;

    //单例构造器
    public static WeixinAuthApiUtils getInstance(){
        if (null == self)
            self = new WeixinAuthApiUtils();
        return self;
    }

    //获取单列的oauthAPI
    public OauthAPI getOauthAPI()
    {
        if (null == oauthAPI){

            getConfigInstance();

            synchronized (LOCK)
            {
                oauthAPI = new OauthAPI(apiConfig);
            }

        }
        return oauthAPI;
    }


    //获取单例的微信配置文件类
    public ApiConfig getConfigInstance()
    {
        synchronized (LOCK)
        {
            if (apiConfig == null)
            {
                apiConfig = new ApiConfig(AppConfig.getValue("APPID"),AppConfig.getValue("SECRET"), true);
            }
            return apiConfig;
        }
    }
}
