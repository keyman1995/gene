package com.ycjcjy.gene.web.action.customeruserrecharge;

import com.ycjcjy.gene.common.util.WebSorketSessionIdUtil;
import com.ycjcjy.gene.model.CustomerSpendLog;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.RechargeSetting;
import com.ycjcjy.gene.service.CustomerSpendLogService;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.service.RechargeSettingService;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customerUserRecharge")
public class CustomerUserRechargeController {

    @Autowired
    private CustomerUserService customerUserService;

    @Autowired
    private RechargeSettingService rechargeSettingService;

    @Autowired
    private CustomerSpendLogService customerSpendLogService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerUserRechargeController.class);

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResponseBean getUserInfo(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

        try{
            HttpSession session = request.getSession();
            CustomerUser customerUser = (CustomerUser) session.getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            if(customerUser == null){
                responseBean.setError(1,"用户登录失效，请重新登录");
            }else{
                data.put("icon",customerUser.getIcon());
                data.put("user_id",customerUser.getId());
                data.put("username",customerUser.getWc_nickname());
                data.put("tel",customerUser.getTel());
                data.put("sorketId", WebSorketSessionIdUtil.getSessionId(1,Integer.valueOf(customerUser.getId().toString())));
                responseBean.setSuccess(data,"请求成功");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }


//        }

        return responseBean;
    }

    /**
     * 获取用户的实际余额和赠送余额
     * @param request
     * @return
     */
    @RequestMapping("/balanceList")
    @ResponseBody
    public ResponseBean balanceList(HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

//        Long user_id = Long.valueOf(String.valueOf(getUserInfo(request,id).get("user_id")));
//        Long user_id = Long.valueOf(String.valueOf(id));
        try{
            Map<String,Object> user = (Map<String,Object>)getUserInfo(request).getData();

            if(user == null){
                responseBean.setError(1,"用户登录失效，请重新登录");
            }else{
                Long user_id = Long.valueOf(String.valueOf(user.get("user_id")));
                Double actual_balance = customerUserService.findActual_balanceById(user_id);
                Double gift_balance = customerUserService.findGift_balanceById(user_id);
                DecimalFormat df = new DecimalFormat("###############0.00");
                String temp = df.format(actual_balance);
                String temp1 = df.format(gift_balance);
                data.put("actual_balance",temp);
                data.put("gift_balance",temp1);
                responseBean.setSuccess(data,"请求成功");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }



        return responseBean;
    }

    /**
     * 获取充值赠送配置
     * @return
     */
    @RequestMapping("/rechargeSettingList")
    @ResponseBody
    public ResponseBean rechargeSettingList(){
        ResponseBean responseBean = new ResponseBean();
        try{
            List<RechargeSetting> lists = rechargeSettingService.findAllSetting();
            if(lists.size() == 0){
                responseBean.setError();
            }else{
                responseBean.setSuccess(lists,"请求成功");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }

        return responseBean;
    }

    @RequestMapping("/billList")
    @ResponseBody
    public ResponseBean billList(HttpServletRequest request,CustomerSpendLog customerSpendLog){

        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        List records = new ArrayList();

        DecimalFormat df = new DecimalFormat("###############0.00");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Map<String,Object> user = (Map<String,Object>)getUserInfo(request).getData();
            if(user == null){
                responseBean.setError(1,"用户登录失效，请重新登录");
            }else{
                customerSpendLog.setCustomer_id(Integer.parseInt(String.valueOf(user.get("user_id"))));
                List<CustomerSpendLog> customerSpendLogs = customerSpendLogService.findByUserId(customerSpendLog);
                if(customerSpendLogs.size() != 0){
                    for(CustomerSpendLog customerSpendLog1 : customerSpendLogs){
                        Map<String,Object> record = new HashedMap();
                        record.put("time",sdf.format(customerSpendLog1.getCreate_time()));
                        record.put("address",customerSpendLog1.getCasefield_name());
                        record.put("goodsName",customerSpendLog1.getGoods_name());
                        record.put("price",df.format(customerSpendLog1.getPrice()));
                        records.add(record);
                    }
                }

                Integer total = customerSpendLogService.getAllCountByCustomerId(customerSpendLog);
                Integer totalPage = responseBean.init(total,customerSpendLog.getBase_pageSize());
                data.put("totalPage",totalPage);
                data.put("total",total);
                data.put("records",records);
                responseBean.setSuccess(data,"请求成功");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }



        return responseBean;

    }
}
