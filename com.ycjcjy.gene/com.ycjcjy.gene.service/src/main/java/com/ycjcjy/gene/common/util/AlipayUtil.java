package com.ycjcjy.gene.common.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.ycjcjy.gene.model.OrderMain;
import net.onebean.util.PropUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.alipay.api.AlipayConstants.APP_ID;
import static org.apache.catalina.manager.Constants.CHARSET;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/5/12
 **/
public class AlipayUtil {

    private static  String ALIPAYAPPID = PropUtil.getConfig("ALIPAYAPPID");
    private static  String PAY_URL=PropUtil.getConfig("PAY_URL");
    private static  String APP_PRIVATE_KEY = PropUtil.getConfig("APPLY_PRIVATE_KEY");
    private static  String ALIPAY_PUBLIC_KEY = PropUtil.getConfig("APPLY_PUBLIC_KEY");
    private static  String PRODUCT_CODE = PropUtil.getConfig("PRODUCT_CODE");
    private static String CHARSET="UTF-8";

 /*   public static void toPay() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(PAY_URL, ALIPAYAPPID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo("2018051211220699");
        model.setTotalAmount("88.88");
        model.setBody("Iphone6 16G test");
        model.setSubject("Iphone6 16G");
        model.setTerminalId("5");
        request.setBizModel(model);
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        System.out.println("----------------"+response.getBody());
        System.out.println("+================"+response.getQrCode());
    }*/

/*    public static Map<String,Object> toAlipay(AliPayEntity entity) throws AlipayApiException{
        Map<String,Object> result = new HashMap<String, Object>();
        AlipayClient alipayClient = new DefaultAlipayClient(PAY_URL, ALIPAYAPPID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(entity.getOrderTradeNo());
        model.setSubject(entity.getSubject());

        model.setTotalAmount(entity.getTotalAmount());
        request.setBizModel(model);
        request.setReturnUrl("www.baidu.com");
        request.setNotifyUrl("www.baidu.com");
        AlipayTradePrecreateResponse response = alipayClient.pageExecute(request);
        result.put("body",response.getBody());
        result.put("QrCode",response.getQrCode());
        return result;
    }*/
}
