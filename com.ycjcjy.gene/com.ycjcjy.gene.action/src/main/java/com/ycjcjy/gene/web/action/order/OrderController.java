package com.ycjcjy.gene.web.action.order;

import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.OrderMain;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.OrderMainService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by szc on 2018/5/9.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController<OrderMain,OrderMainService> {
    @RequestMapping("/orderList")
    @ResponseBody
    public PageResult<OrderMain> list (Sort sort, Pagination page, PageResult<OrderMain> result, OrderMain orderMain, @RequestParam(value = "conditionList",required = false) String cond, HttpServletRequest request){
        orderMain = reflectionModelFormConditionMapStr(cond,sort,page);
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        Integer count = 0;
        if(("admin").equals(sysUser.getUser_type())){
            count = baseService.getAllCount(orderMain);
            dataList = baseService.findAllOrderMain(orderMain);
        }else{
            orderMain.setCase_field_id(Integer.parseInt(String.valueOf(sysUser.getField_id())));
            count = baseService.getAllCount(orderMain);
            dataList = baseService.findAllOrderMain(orderMain);
        }
        dicCoverList(dataList,"date@create_time$");

        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);

        return result;
    }

    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    public String edit(Model model, @PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        return getView("detail");
    }

    @RequestMapping(value = "view/{id}")
    @Description(value = "编辑页面")
    public String view(Model model, @PathVariable("id")Object id) {

        OrderMain om = baseService.queryUserOrderDetailById(id.toString());
        model.addAttribute("entity",om);
        model.addAttribute("view",true);
        return getView("detail");
    }


}
