package com.ycjcjy.gene.web.action.serverlinkcourse;

import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.ServerLinkCourse;
import com.ycjcjy.gene.service.ServerLinkCourseService;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
* @author chenjie
* @description 专题课程 controller
* @date 2018-05-30 19:59:09
*/
@Controller
@RequestMapping("serverlinkcourse")
public class ServerLinkCourseController extends BaseController<ServerLinkCourse,ServerLinkCourseService> {

  /*  *//**
     * 预览列表页面
     * @return view
     *//*
    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LINK_PREVIEW')")
    public String preview(Model model) {
        return getView("list");
    }


    *//**
     * 新增页面
     * @param model modelAndView
     * @param entity 实体
     * @return view
     *//*
    @RequestMapping(value = "add")
    @Description(value = "新增页面")
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LINK_ADD')")
    public String add(Model model, ServerLinkCourse entity) {
        model.addAttribute("add", true);
        model.addAttribute("entity", entity);
        return getView("detail");
    }

    *//**
     * 查看页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     *//*
    @RequestMapping(value = "view/{id}")
    @Description(value = "查看页面")
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LINK_VIEW')")
    public String view(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", baseService.findById(id));
        model.addAttribute("view", true);
        return getView("detail");
    }

    *//**
     * 编辑页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     *//*
    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LINK_EDIT')")
    public String edit(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", baseService.findById(id));
        model.addAttribute("edit", true);
        return getView("detail");
    }

    *//**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<ServerLinkCourse>
    *//*
    @RequestMapping(value = "save")
    @Description(value = "保存")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LINK_SAVE')")
    public PageResult<ServerLinkCourse> save(ServerLinkCourse entity, PageResult<ServerLinkCourse> result) {
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }

    *//**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<ServerLinkCourse>
    *//*
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LINK_DELETE')")
    public PageResult<ServerLinkCourse> delete(@PathVariable("id") Object id, PageResult<ServerLinkCourse> result) {
        baseService.deleteById(id);
        result.setFlag(true);
        return result;
    }

    *//**
     * 列表数据
     * @param sort 排序参数
     * @param page 分页参数
     * @param result 结果集
     * @param cond 表达式
     * @return PageResult<ServerLinkCourse>
    */
 /*   @RequestMapping("listlink")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LINK_LIST')")
    public PageResult<ServerLinkCourse> list(Sort sort, Pagination page,String serverId, PageResult<ServerLinkCourse> result, @RequestParam(value = "conditionList", required = false) String cond) {
        initData(sort, page, cond);
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }*/
}
