package com.ycjcjy.gene.web.action.course;


import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CourseMakeupManager;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SubCourseManager;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CourseMakeupManagerService;
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
import javax.swing.*;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/courseMakeup")
public class CourseMakeupController extends BaseController<CourseMakeupManager,CourseMakeupManagerService> {

    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    public String preview(Model model) {
        return getView("list");
    }

    @RequestMapping(value = "previewCustomer/{id}")
    @Description(value = "预览列表页面")
    public String previewCustomer(Model model,@PathVariable("id")Integer id) {
        model.addAttribute("courseId",id);
        return getView("customerList");
    }





    @RequestMapping("solveCustomer/{makeupId}")
    public PageResult<CourseMakeupManager> solveCustomer (@PathVariable("makeupId")Integer makeupId, HttpServletRequest request, CustomerUser customerUser,PageResult<CourseMakeupManager> result){
        CourseMakeupManager courseMakeupManager = baseService.findById(makeupId.toString());
        Date date =new Date();
        Timestamp timestamp= new Timestamp(date.getTime());
        courseMakeupManager.setSolve_time(timestamp);
        courseMakeupManager.setStatus(1);
        long solve =SpringSecurityUtil.getCurrentLoginUser(request).getId();
        int solverId = (int)solve;
        courseMakeupManager.setSolver_id(solverId);
        courseMakeupManager.setNewCourseTime(customerUser.getNewCourseTime());
        baseService.update(courseMakeupManager);
        result.getData().add(courseMakeupManager);
        //添加订单（未开始）
        return result;

    }

    @RequestMapping(value = "edit/{userId}/{courseId}")
    @Description(value = "编辑页面")
    public String edit(Model model,@PathVariable("userId")Integer userId,@PathVariable("courseId")Integer courseId) {
        CustomerUser customerUser = baseService.findCustomerDetail(userId,courseId);
        model.addAttribute("entity",customerUser);
        model.addAttribute("courseId",courseId);
        model.addAttribute("edit",true);
        return getView("detail");
    }



}
