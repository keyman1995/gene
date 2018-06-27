package com.ycjcjy.gene.web.action.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ycjcjy.gene.common.util.AliPayEntity;
import com.ycjcjy.gene.common.util.AlipayUtil;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.util.PropUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/5/12
 **/
@RequestMapping("test")
@Controller
public class PayTest {

    private static  String ALIPAYAPPID = PropUtil.getConfig("ALIPAYAPPID");
    private static  String PAY_URL=PropUtil.getConfig("PAY_URL");
    private static  String APP_PRIVATE_KEY = PropUtil.getConfig("APPLY_PRIVATE_KEY");
    private static  String ALIPAY_PUBLIC_KEY = PropUtil.getConfig("APPLY_PUBLIC_KEY");
    private static  String PRODUCT_CODE = PropUtil.getConfig("PRODUCT_CODE");
    private static String CHARSET="UTF-8";

    @RequestMapping("pay")
    public void toTestPay(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map<String,Object> result = new HashMap<String, Object>();
        String form ="";
        try{
            AliPayEntity entity = new AliPayEntity();
            entity.setSubject("咖啡体验预约课程");
            entity.setOut_trade_no("201805130001");
            entity.setTotal_amount("1000");
            //result =  AlipayUtil.toAlipay(entity);
             form = (String) result.get("body");
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();

    }

    @RequestMapping("payBySdk")
    public void doPost(HttpServletRequest request, HttpServletResponse httpResponse) throws AlipayApiException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", ALIPAYAPPID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        String out_trade_no = request.getParameter("out_trade_no");
        String total_amount = request.getParameter("total_amount");
        String subject = request.getParameter("subject");
        alipayRequest.setBizContent("{" +
                "out_trade_no:'2018112233'," +
                "total_amount:'88.88'," +
                "subject:'Iphone6 16G'," +
                "product_code:'QUICK_WAP_WAY'"+
                " }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


}
