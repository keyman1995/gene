package com.ycjcjy.gene.web.action.cardTicket;


import com.ycjcjy.gene.common.util.WebSorketSessionIdUtil;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.CardCourdeBasicService;
import com.ycjcjy.gene.service.CardTicketSalveService;
import com.ycjcjy.gene.service.CardUserRelaService;
import com.ycjcjy.gene.service.CourseManagerService;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.util.DateUtils;
import net.onebean.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by szc on 2018/5/7.
 */
@Controller
@RequestMapping("/cardTicket")
public class CaidTicketController {

    @Autowired
    private CardUserRelaService cardUserRelaService;
    @Autowired
    private CardCourdeBasicService cardCourdeBasicService;
    @Autowired
    private CourseManagerService courseManagerService;

    @RequestMapping("/getAllCardTicket")
    @ResponseBody
    public ResponseBean getAllUserCardTicket(HttpServletRequest request, String state,String card_type,String base_currentPage, String base_pageSize){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

        try {
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            String base_offset = "0";
            if(StringUtils.isBlank(base_pageSize)){
                base_pageSize = "10";
            }

            if(StringUtils.isNotBlank(base_currentPage)){
                base_offset = String.valueOf((Integer.valueOf(base_currentPage)-1)*Integer.valueOf(base_pageSize));
            }
            List<CardUserRela> list = cardUserRelaService.getAllCard(user.getId().toString(),state,card_type,base_offset,base_pageSize);
            int total = cardUserRelaService.getCount(user.getId().toString(),state,card_type);
            int totalPage = responseBean.init(total,Integer.valueOf(base_pageSize));
            data.put("list",list);
            data.put("total",total);
            data.put("totalPage",totalPage);
            data.put("base_currentPage",base_currentPage);
            data.put("base_pageSize",base_pageSize);
            responseBean.setSuccess(data);

        }catch (Exception e){
            responseBean.setError("系统错误");
        }


        return responseBean;
    }

    @RequestMapping("/getCardDetail")
    @ResponseBody
    public ResponseBean getCardDetail (String  id, HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
            CustomerUser user = (CustomerUser)request.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
            CardUserRela cardUserRela = cardUserRelaService.getCardDetail(id);
            data.put("sorketId", WebSorketSessionIdUtil.getSessionId(1,Integer.valueOf(user.getId().toString())));
            data.put("cardUserRela",cardUserRela);

            responseBean.setSuccess(data,"请求成功");

        }catch (Exception e){
            responseBean.setError("系统错误");
        }
        return responseBean;

    }





}
