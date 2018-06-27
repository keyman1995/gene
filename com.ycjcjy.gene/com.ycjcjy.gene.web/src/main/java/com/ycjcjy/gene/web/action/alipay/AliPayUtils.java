package com.ycjcjy.gene.web.action.alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ycjcjy.gene.common.util.AliPayEntity;
import com.ycjcjy.gene.service.OrderMainService;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.util.PropUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/5/13
 **/
@RequestMapping("pay")
@Controller
public class AliPayUtils {

    private static  String ALIPAYAPPID = PropUtil.getConfig("ALIPAYAPPID");
    private static  String PAY_URL=PropUtil.getConfig("PAY_URL");
    private static  String APP_PRIVATE_KEY = PropUtil.getConfig("APPLY_PRIVATE_KEY");
    private static  String ALIPAY_PUBLIC_KEY = PropUtil.getConfig("APPLY_PUBLIC_KEY");
    private static  String PRODUCT_CODE = PropUtil.getConfig("PRODUCT_CODE");
    private static  String RETURN_URL=PropUtil.getConfig("RETURN_URL");
    private static  String NOTIFY_URL = PropUtil.getConfig("NOTIFY_URL");
    private static String CHARSET="UTF-8";
    private static String FORMAT="json";
    private static String SING_TYPE="RSA2";
    private static Integer ALI_PAY_TYPE= 1;

    @Autowired
    private OrderMainService orderMainService;


    @RequestMapping("forAli")
    public void doPay(HttpServletRequest request, HttpServletResponse response){
        AlipayClient alipayClient = new DefaultAlipayClient(PAY_URL, ALIPAYAPPID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SING_TYPE); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        AliPayEntity payEntity = new AliPayEntity();
        alipayRequest.setReturnUrl(RETURN_URL);
        alipayRequest.setNotifyUrl(NOTIFY_URL);
        String form = "";
        PrintWriter writer;
        try {
            String out_trade_no = request.getParameter("out_trade_no");
            writer = response.getWriter();
            String total_amount = request.getParameter("total_amount");
            String subject = request.getParameter("subject");
            payEntity.setTotal_amount(total_amount);
            payEntity.setOut_trade_no(out_trade_no);
            payEntity.setSubject(subject);
            payEntity.setProduct_code(PRODUCT_CODE);
            Object  json = JSON.toJSON(payEntity);
            alipayRequest.setBizContent(String.valueOf(json));
            form = alipayClient.pageExecute(alipayRequest).getBody();
            writer.write(form);
            response.setContentType("text/html;charset="+CHARSET);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }



    /**
     *
     *  支付宝延签
     * @author chenjie
     * @date 2018/5/14 14:41
     * @param
     * @return void
     */
    @RequestMapping("result")
    public void doReturn(HttpServletRequest request,HttpServletResponse  response){
        try {

            Map<String,String> params = new HashMap<String,String>();
            Map requestParams = request.getParameterMap();
            ResponseBean responseBean = new ResponseBean();
            Map<String,Object> resultMap = new HashMap<String, Object>();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }
           boolean result =  AlipaySignature.rsaCheckV1(params,ALIPAY_PUBLIC_KEY,CHARSET,"RSA2");//支付宝延签
            if(result){//延签成功
                String out_trade_no = params.get("out_trade_no");
                String trade_no = params.get("trade_no");
                if(out_trade_no.startsWith("m")){//商品购买
                    //TODO 需要将交易订单号 不需要有用户对象 和前端 协商返回的url是多少
                    resultMap = orderMainService.paySuccess(ALI_PAY_TYPE,out_trade_no,Double.valueOf(params.get("")),null);
                }
                if(out_trade_no.startsWith("c")){//充值

                }

                return;
            }else{//延签失败

                return;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
