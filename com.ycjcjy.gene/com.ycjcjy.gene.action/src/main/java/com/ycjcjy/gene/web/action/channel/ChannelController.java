package com.ycjcjy.gene.web.action.channel;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.Channel;
import com.ycjcjy.gene.service.ChannelService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.springframework.context.annotation.Description;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
* @author Ken Hu
* @description 渠道管理 controller
* @date 2018-05-28 09:55:09
*/
@Controller
@RequestMapping("channel")
public class ChannelController extends TlBaseController<Channel,ChannelService> {

    /**
     * 预览列表页面
     * @return view
     */
    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    @PreAuthorize("hasPermission('$everyone','PERM_CHANNEL_LIST_PREVIEW')")
    public String preview(Model model) {
        return getView("list");
    }


    /**
     * 新增页面
     * @param model modelAndView
     * @param entity 实体
     * @return view
     */
    @RequestMapping(value = "add")
    @Description(value = "新增页面")
    @PreAuthorize("hasPermission('$everyone','PERM_CHANNEL_LIST_ADD')")
    public String add(Model model, Channel entity) {
        model.addAttribute("add", true);
        model.addAttribute("entity", entity);
        return getView("detail");
    }

    /**
     * 查看页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     */
    @RequestMapping(value = "view/{id}")
    @Description(value = "查看页面")
    @PreAuthorize("hasPermission('$everyone','PERM_CHANNEL_LIST_VIEW')")
    public String view(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", baseService.findById(id));
        model.addAttribute("view", true);
        return getView("detail");
    }

    /**
     * 编辑页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     */
    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    @PreAuthorize("hasPermission('$everyone','PERM_CHANNEL_LIST_EDIT')")
    public String edit(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", baseService.findById(id));
        model.addAttribute("edit", true);
        return getView("detail");
    }

    /**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<Channel>
    */
    @RequestMapping(value = "save")
    @Description(value = "保存")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_CHANNEL_LIST_SAVE')")
    public PageResult<Channel> save(Channel entity, PageResult<Channel> result) {
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }

    /**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<Channel>
    */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_CHANNEL_LIST_DELETE')")
    public PageResult<Channel> delete(@PathVariable("id") Object id, PageResult<Channel> result) {
        baseService.deleteById(id);
        result.setFlag(true);
        return result;
    }

    /**
     * 列表数据
     * @param sort 排序参数
     * @param page 分页参数
     * @param result 结果集
     * @param cond 表达式
     * @return PageResult<Channel>
    */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_CHANNEL_LIST_LIST')")
    public PageResult<Channel> list(Sort sort, Pagination page, PageResult<Channel> result, @RequestParam(value = "conditionList", required = false) String cond) {
        initData(sort, page, cond);
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    @RequestMapping("findAll")
    @ResponseBody
    public PageResult<Channel> allGoods (Sort sort, Pagination page, PageResult<Channel> result){
        result.setData(baseService.findAll());
        return result;
    }
}
