package com.ycjcjy.gene.web.action.sendCardLog;


import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.SendCardLogService;
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

@Controller
@RequestMapping("/sendCardLog")
public class SendCardLogController extends BaseController<SendCardLog,SendCardLogService> {



    @RequestMapping("lists")
    @ResponseBody
    public PageResult<SendCardLog> customerList (Sort sort, Pagination page, PageResult<SendCardLog> result, SendCardLog sendCardLog , @RequestParam(value = "conditionList",required = false) String conditionStr, HttpServletRequest request){
        /*initData(sort,page,conditionStr);*/
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        if (!user.getUser_type().equals("admin")){
            sendCardLog.setCurrent_user_org_id(user.getOrg_id());
        }
        sendCardLog=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        result.setData(baseService.getList(sendCardLog));
        page.init(baseService.pageCount(sendCardLog),page.getPageSize());
        result.setPagination(page);
        return result;
    }

    @RequestMapping(value = "viewOne/{id}")
    @Description(value = "查看页面")
    public String view(Model model,@PathVariable("id")String id){
        model.addAttribute("entity",baseService.getOne(Integer.valueOf(id)));
        model.addAttribute("view",true);
        return getView("detail");
    }




}
