package com.ycjcjy.gene.web.action.information;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.Information;
import com.ycjcjy.gene.service.InformationService;
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


/**
* @author yibin
* @description 首页资讯 controller
* @date 2018-05-28 10:04:00
*/
@Controller
@RequestMapping("information")
public class InformationController extends TlBaseController<Information,InformationService> {

    /**
     * 预览列表页面
     * @return view
     */
    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
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
    public String add(Model model, Information entity) {
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
    public String edit(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", baseService.findById(id));
        model.addAttribute("edit", true);
        return getView("detail");
    }

    /**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<Information>
    */
    @RequestMapping(value = "saveInfor")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<Information> saveInfor(Information entity, PageResult<Information> result) {
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }

    /**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<Information>
    */
    @RequestMapping(value = "deleteInfor/{id}")
    @Description(value = "删除")
    @ResponseBody
    public PageResult<Information> deleteInfor(@PathVariable("id") Object id, PageResult<Information> result) {
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
     * @return PageResult<Information>
    */
    @RequestMapping("list")
    @ResponseBody
    public PageResult<Information> list(Information information,Sort sort, Pagination page, PageResult<Information> result, @RequestParam(value = "conditionList", required = false) String cond) {

        information = reflectionModelFormConditionMapStr(cond,sort,page);

        Integer count = baseService.getAllCount(information);
        dataList = baseService.findAllInfor(information);
        dicCoverList(null, "date@create_time$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }
}
