package com.ycjcjy.gene.web.action.goodsordermain;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.GoodsOrderMain;
import com.ycjcjy.gene.service.GoodsOrderMainService;
import net.onebean.util.DateUtils;
import org.springframework.context.annotation.Description;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


/**
* @author szcc
* @description 商品订单主表 controller
* @date 2018-06-11 18:10:40
*/
@Controller
@RequestMapping("goodsordermain")
public class GoodsOrderMainController extends TlBaseController<GoodsOrderMain,GoodsOrderMainService> {

    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    public String preview(Model model,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("userId",user.getId());
        return getView("list");
    }



    @RequestMapping("/getNewOrder")
    @ResponseBody
    public Map<String,Object> getNewOrder( HttpServletRequest request,Integer flag){
        Map<String,Object> map = new HashMap<>();
        try{
            SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
            List<GoodsOrderMain> list = new ArrayList<>();
            if(("admin").equals(sysUser.getUser_type())){
                GoodsOrderMain m = new GoodsOrderMain();
                m.setFlag(flag);
                list = baseService.findNewGoodsOrderMain(m);
            }else{
                GoodsOrderMain m = new GoodsOrderMain();
                m.setFlag(flag);
                m.setCase_field_id(Integer.valueOf(sysUser.getField_id().toString()));
                list = baseService.findNewGoodsOrderMain(m);
            }
            if(list.size()>0){
                baseService.updateNewGoodsOrderMain(list);

                for(int i=0;i<list.size();i++){
                    list.get(i).setHaomiaoshu(String.valueOf(DateUtils.parse( list.get(i).getCreate_time(),"yyyy-MM-dd HH:mm:ss").getTime()));
                }



            }



            map.put("list",list);
            map.put("success",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",0);
        }
        return map;
    }
    @RequestMapping("/updateOrderState")
    @ResponseBody
    public Map<String,Object> updateOrderState( HttpServletRequest request,Integer orderId){
        Map<String,Object> map = new HashMap<>();
        try{
            GoodsOrderMain om =new GoodsOrderMain();
            om.setId(Long.valueOf(orderId));
            om.setDone_time(DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
            om.setOrder_state(1);
            baseService.updateOrder(om);
            map.put("success",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",0);
        }


        return map;
    }

    @RequestMapping("/updateOrderFlag")
    @ResponseBody
    public Map<String,Object> updateOrderFlag( HttpServletRequest request,Integer orderId){
        Map<String,Object> map = new HashMap<>();
        try{
            GoodsOrderMain om =new GoodsOrderMain();
            om.setId(Long.valueOf(orderId));
            baseService.updateOrderFlag(om);
            map.put("success",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",0);
        }


        return map;
    }


//    /**
//     * 收回过期体验券，返回案场钱
//     * @Scheduled(cron = "0 0/3 * * * ? ")
//     */
//
//    @Scheduled(cron = "* * * * * ? ")
//    public void orderStateTimer(){
//
//        ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
//        HttpServletRequest request = ((HttpServletRequest) RequestContextHolder.getRequestAttributes());
//        try{
//            SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
//            String lalal = sysUser.getUser_type();
//            System.out.println(lalal);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


}
