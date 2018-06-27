package com.ycjcjy.gene.web.action.cms;


import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CmsPic;
import com.ycjcjy.gene.service.CmsPicService;
import com.ycjcjy.gene.service.CourseManagerService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cmsPic")
public class CmsPicController extends BaseController<CmsPic,CmsPicService> {


    @Autowired
    private CourseManagerService courseManagerService;

    @RequestMapping("picList")
    @ResponseBody
    public PageResult<CmsPic> list (CmsPic cmsPic, Sort sort, Pagination page, PageResult<CmsPic> result, @RequestParam(value = "conditionList",required = false) String cond){
        cmsPic = reflectionModelFormConditionMapStr(cond,sort,page);

        Integer count = baseService.getAllCount(cmsPic);
        dataList = baseService.findAllPic(cmsPic);
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public PageResult<CmsPic> save(Model model, CmsPic entity, PageResult<CmsPic> result) {
        if(entity.getId()==null){
            baseService.save(entity);
        }else{
            baseService.updateCmsPic(entity);
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
    public String add(Model model,CmsPic entity) {
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
        model.addAttribute("courses",courseManagerService.getAllForSel());
        return getView("detail");
    }

    /**
     * 查看页面
     * @param model
     * @return
     */
    @RequestMapping(value = "view/{id}")
    @Description(value = "查看页面")
    public String view(Model model,@PathVariable("id")Object id){
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("courses",courseManagerService.getAllForSel());
        model.addAttribute("view",true);
        return getView("detail");
    }

    /**
     * 编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    public String edit(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("courses",courseManagerService.getAllForSel());
        model.addAttribute("edit",true);
        return getView("detail");
    }



}
