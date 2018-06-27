package com.ycjcjy.gene.web.action.customerUser;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.service.CourseMakeupManagerService;
import com.ycjcjy.gene.service.CustomerUserService;
import net.onebean.core.Condition;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/customeruser")
public class CustomerUserController extends TlBaseController<CustomerUser,CustomerUserService> {
    @Autowired
    private CourseMakeupManagerService makeupManagerService;

    @RequestMapping("customeruserlist")
    @ResponseBody
    public PageResult<CustomerUser> customeruserlist(Sort sort, Pagination page, PageResult<CustomerUser> result, CustomerUser customerUser,
                                                     @RequestParam(value = "conditionList", required = false) String cond) {
//        initData(sort,page,cond);
        customerUser = reflectionModelFormConditionMapStr(cond, sort, page);
        Integer count = baseService.getAllCount(customerUser);
        dataList = baseService.findCustomerUsers(customerUser);
        dicCoverList(null, "date@create_time$");
        result.setData(dataList);
        page.init(count, page.getPageSize());
        result.setPagination(page);
        return result;
    }

    /**
     * 预览前台用户列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "preview")
    @Description(value = "预览前台用户列表页面")
    public String preview(Model model) {
        return getView("customeruserlist");
    }

    @RequestMapping("customerLists/{courseId}")
    @ResponseBody
    public PageResult<CustomerUser> customerList (Sort sort, Pagination page, PageResult<CustomerUser> result,CustomerUser customerUser ,@RequestParam(value = "conditionList",required = false) String conditionStr, @PathVariable("courseId")String courseId){
        /*initData(sort,page,conditionStr);*/
        customerUser=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        customerUser.setCourseId(Integer.parseInt(courseId));
        result.setData(makeupManagerService.findMakeupCustomer(customerUser));
        result.setPagination(page);
        return result;
    }

    /**
     * 查找客户用户
     * @param param
     * @param page
     * @param result
     * @return
     */
    @RequestMapping("findcususerbystr")
    @ResponseBody
    public PageResult<CustomerUser> findCusUserByStr(@RequestParam("param")String param,Pagination page,PageResult<CustomerUser> result){
        Condition condition;
        if (StringUtils.isNumeric(param)){
            condition = Condition.parseCondition("tel@string@eq");
        }else{
            condition = Condition.parseCondition("username@string@like");
        }
        condition.setValue(param);
        result.setData(baseService.find(null,condition));
        return result;
    }

    @RequestMapping("getById/{id}")
    @ResponseBody
    public CustomerUser customerList (@PathVariable("id")String id){
        CustomerUser user = baseService.findById(id);
        if(user != null){
            user.setAllBalance(user.getActual_balance()+user.getGift_balance());
        }
        return user;
    }

    @RequestMapping(value = "findUserByTel")
    @ResponseBody
    public Map<String,Object> findUserByTel(String tel){
        Map<String,Object> result = new HashMap<>();
        CustomerUser user = baseService.findByTel(tel);
        result.put("user",user);
        result.put("isexist",1);
        if(user==null){
            result.put("isexist",0);
        }
        return  result;
    }
}