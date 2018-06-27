package com.ycjcjy.gene.web.action.verification;

import com.ycjcjy.gene.common.util.NumberArithmeticUtils;
import com.ycjcjy.gene.common.util.WebSorketSessionIdUtil;
import com.ycjcjy.gene.common.webSocket.WebSocketController;
import com.ycjcjy.gene.model.CardTicketMaster;
import com.ycjcjy.gene.model.CustomerSpendLog;
import com.ycjcjy.gene.model.LocalGoods;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.*;
import net.onebean.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by szc on 2018/4/22.
 */
@Controller
@RequestMapping("/verification")
public class VerificationController {

    @Autowired
    public LocalGoodsService localGoodsService;

    @Autowired
    public CardTicketMasterService cardTicketMasterService;

    @Autowired
    public CustomerUserService customerUserService;
    @Autowired
    public CustomerSpendLogService customerSpendLogService;
    @Autowired
    public SysUserService sysUserService;
    @Autowired
    public CardGoodsBasicService cardGoodsBasicService;

    @RequestMapping("sale")
    public String sale (Model model){

        return "/localgoodsfield/sale";
    }

    @RequestMapping("/searchGoods")
    @ResponseBody
    public Map<String,Object> searchGoods(Integer type,Integer id){
        Map<String,Object> result = new HashMap<>();
        List<LocalGoods> list = new ArrayList<>();
        result.put("success",0);
        try{
            //1001 用户 1002 订单  1003 商品 1005课程     1007 VIP卡
            if(type ==1001){
                list =  localGoodsService.findAll();
            }else if(type==1003){
                CardTicketMaster ticket = cardTicketMasterService.findById(id.toString());
                list.add(localGoodsService.findById(cardGoodsBasicService.findById(ticket.getTarget_id().toString()).getGoods_id().toString()));
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",1);
        }

        result.put("list",list);
        return result;
    }

    @RequestMapping("/calPrice")
    @ResponseBody
    public Map<String,Object> calPrice(String ids,String nums){
        Map<String,Object> result = new HashMap<>();
        double price = 0d;
        if(StringUtils.isNotBlank(ids)&&StringUtils.isNotBlank(nums)){
            String[] idArr = ids.split(",");
            String[] numArr = nums.split(",");
            for(int i=0;i<idArr.length;i++){
                LocalGoods goods =  localGoodsService.findById(idArr[i]);
                price = NumberArithmeticUtils.add(price,NumberArithmeticUtils.mul(goods.getPrice(),Double.valueOf(numArr[i])).doubleValue()).doubleValue();
            }
        }

        result.put("price",price);

        return result;
    }

    @RequestMapping("/goCutMoney")
    @ResponseBody
    public Map<String,Object> goCutMoney(String ids,String nums,Integer type,Integer id,Integer saleId,Integer num,Integer ticketId,HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        result.put("success",0);
        result.put("msg","成功");
        try{
            double price = 0d;
            if(StringUtils.isNotBlank(ids)&&StringUtils.isNotBlank(nums)){
                String[] idArr = ids.split(",");
                String[] numArr = nums.split(",");
                for(int i=0;i<idArr.length;i++){
                    LocalGoods goods =  localGoodsService.findById(idArr[i]);
                    price = NumberArithmeticUtils.add(price,NumberArithmeticUtils.mul(goods.getPrice(),Double.valueOf(numArr[i])).doubleValue()).doubleValue();
                }
            }
            //扣钱
            if(type ==1001){
                int code = customerUserService.cutMoney(price,Long.valueOf(id));
                if(code ==-1){
                    result.put("success",1);
                    result.put("msg","用户余额不足");
                    WebSocketController.sendMessageToUser("0","核销失败", WebSorketSessionIdUtil.getSessionId(1,id));
                }else{
                //消费成功、记录消费记录
                    CustomerSpendLog log = new CustomerSpendLog();
                    SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
                    log.setCasefield_id(Integer.valueOf(sysUser.getField_id().toString()));
                    log.setCustomer_id(id);
                    log.setGoods_id(ids);
                    log.setQuantity(nums);
                    Timestamp ts = new Timestamp(new Date().getTime());
                    log.setCreate_time(ts);
                    log.setPrice(price);
                    log.setType(0);
                    customerSpendLogService.save(log);
                    result.put("success",1);
                    result.put("msg","购买成功！");
                    WebSocketController.sendMessageToUser("1","成功", WebSorketSessionIdUtil.getSessionId(1,id));
                }
            }else if(type==1003){
                int haveNum = cardTicketMasterService.saleTicketCount(saleId,ticketId);

                if(haveNum<num){
                    result.put("success",1);
                    result.put("msg","商品体验券次数不足，不可消费");
                    WebSocketController.sendMessageToUser("0","核销失败", WebSorketSessionIdUtil.getSessionId(0,saleId));
                }else{
                    cardTicketMasterService.updateSaleGoodsTicketState(saleId.toString(),ticketId.toString(),num.toString(), DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
                    result.put("success",0);
                    result.put("msg","购买成功！");
                    WebSocketController.sendMessageToUser("1","成功", WebSorketSessionIdUtil.getSessionId(0,saleId));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",1);
            result.put("msg","核销失败");
        }
        return result;
    }


}
