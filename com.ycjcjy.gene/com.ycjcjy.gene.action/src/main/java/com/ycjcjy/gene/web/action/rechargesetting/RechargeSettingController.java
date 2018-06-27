package com.ycjcjy.gene.web.action.rechargesetting;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.RechargeSetting;
import com.ycjcjy.gene.service.RechargeSettingService;
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

@Controller
@RequestMapping("/rechargesetting")
public class RechargeSettingController extends TlBaseController<RechargeSetting,RechargeSettingService> {

    @RequestMapping("list")
    @ResponseBody
    public PageResult<RechargeSetting> list (Sort sort, Pagination page, PageResult<RechargeSetting> result, @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        dicCoverList(null,"date@create_time$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    /**
     * 预览列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    public String preview(Model model) {
        return getView("list");
    }

    /**
     * 删除
     * @param model
     * @return
     */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    public PageResult<RechargeSetting> delete(Model model, @PathVariable("id")Object id, PageResult<RechargeSetting> result) {
        baseService.deleteById(id);
        result.setFlag(true);
        return result;
    }

    /**
     * 保存
     * @param model
     * @return
     */
    @RequestMapping(value = "save")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<RechargeSetting> save(Model model, RechargeSetting entity, PageResult<RechargeSetting> result) {
        if (entity.getId() != null) {
            baseService.update(entity);
        } else {
            baseService.save(entity);
        }
        result.getData().add(entity);
        return result;
    }

    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "add")
    @Description(value = "新增页面")
    public String add(Model model,RechargeSetting entity) {
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
        return getView("detail");
    }

    /**s
     * 编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    public String edit(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        return getView("detail");
    }


}
