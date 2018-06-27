package com.ycjcjy.gene.web.action.share;

import com.alibaba.fastjson.JSONObject;
import com.github.sd4324530.fastweixin.api.JsAPI;
import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;
import com.ycjcjy.gene.web.action.system.listener.WeixinAuthApiUtils;
import com.ycjcjy.gene.web.action.system.util.AppConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("share")
public class ShareController {

    /**
     * js初始化
     * @param url
     * @return
     */
    @RequestMapping(value = "initWxJsConfig")
    @ResponseBody
    public String initWxJsConfig(String url)
    {
        JsAPI jsapi = new JsAPI(WeixinAuthApiUtils.getInstance().getConfigInstance());
        long timestame = System.currentTimeMillis() / 1000;
        String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
        GetSignatureResponse res = jsapi.getSignature(nonceStr, timestame, url);

        JSONObject obj = new JSONObject();
        obj.put("noncestr", res.getNoncestr());
        obj.put("signature", res.getSignature());
        obj.put("timestamp", res.getTimestamp());
        obj.put("appId", AppConfig.getValue("APPID"));
        return obj.toString();
    }

}
