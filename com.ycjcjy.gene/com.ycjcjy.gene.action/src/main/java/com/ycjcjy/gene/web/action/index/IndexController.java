package com.ycjcjy.gene.web.action.index;

import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.service.OrderMainService;
import com.ycjcjy.gene.service.OrderSubService;
import com.ycjcjy.gene.service.SubCourseManagerService;
import net.onebean.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by szc on 2018/5/30.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private OrderMainService orderMainService;
    @Autowired
    private OrderSubService orderSubService;
    @Autowired
    private CustomerUserService customerUserService;

    @Autowired
    private SubCourseManagerService subCourseManagerService;

    /**
     * 图表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "showTu")
    @ResponseBody
    public Map<String,Object> showTu(Integer dateType,String caseId,HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        String startTime = "";
        String endTime = "";

        try{
            //dateType  0 昨天 1 本周  2 本月

            SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
            if(("admin").equals(sysUser.getUser_type())) {
                result = orderMainService.queryIndexForTu(dateType, startTime, endTime, caseId);
            }


        }catch (Exception e){
            e.printStackTrace();
        }




        return result;
    }

    /**
     * 上部数据
     *
     * @param
     * @return
     */
    @RequestMapping(value = "showData")
    @ResponseBody
    public Map<String,Object> showData(HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        SysUser currentUser = SpringSecurityUtil.getCurrentLoginUser(request);
        try{
            String caseId = "";
            if(!currentUser.getUser_type().equals("admin")){
                caseId = currentUser.getField_id().toString();
            }

            int mxd = orderMainService.findShareLessonByMonth(caseId);//月下单
            int mpk = subCourseManagerService.findCountByMonth(caseId);//月排课
            int mqd = orderSubService.findCheckInByMonth(caseId);//月签到

            Map<String,Object> map1 = subCourseManagerService.findCountByDay(caseId);
            Map<String,Object> map2 = orderMainService.findTotalMoney(caseId);
            Map<String,Object> map3 = customerUserService.getCountByDay();
            Map<String,Object> map4 = orderSubService.findLessonDoneByNow(caseId);

            result.put("mxd",mxd);
            result.put("mpk",mpk);
            result.put("mqd",mqd);
            result.put("map1",map1);
            result.put("map2",map2);
            result.put("map3",map3);
            result.put("map4",map4);



        }catch (Exception e){
            e.printStackTrace();
        }




        return result;
    }


    @RequestMapping(value = "showTable")
    @ResponseBody
    public Map<String,Object> showTable(Integer dateType,String caseId,HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        List data = new ArrayList();
        try {
            SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
            if(("admin").equals(sysUser.getUser_type())) {
                data = subCourseManagerService.findAllNum(dateType, caseId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.put("data",data);
        return result;
    }

}
