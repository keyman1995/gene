package com.ycjcjy.gene.web.action.evaluate;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.Evaluate;
import com.ycjcjy.gene.service.EvaluateImgService;
import com.ycjcjy.gene.service.EvaluateService;
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
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController<Evaluate,EvaluateService> {
    @Autowired
    private EvaluateImgService evaluateImgService;

    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    public String preview(Model model) {
        return getView("list");
    }

    @RequestMapping(value = "addEva")
    @Description(value = "新增页面")
    public String add(Model model,Evaluate entity) {
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
        return getView("detail");
    }

    /**
     * 查看页面
     * @param model
     * @return
     */
    @RequestMapping(value = "viewEva/{id}")
    @Description(value = "查看页面")
    public String view(Model model,@PathVariable("id")Integer id){
        model.addAttribute("entity",baseService.getDetail(id));
        model.addAttribute("view",true);
        return getView("detail");
    }

    /**
     * 编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value = "editEva/{id}")
    @Description(value = "编辑页面")
    public String edit(Model model,@PathVariable("id")Integer id) {
        model.addAttribute("entity",baseService.getDetail(id));
        model.addAttribute("edit",true);
        return getView("detail");
    }

    @RequestMapping("/lists")
    @ResponseBody
    public PageResult<Evaluate> list (Sort sort, Pagination page, PageResult<Evaluate> result,Evaluate evaluate, @RequestParam(value = "conditionList",required = false) String conditionStr){
        evaluate= reflectionModelFormConditionMapStr(conditionStr,sort,page);
        result.setData(baseService.getAllEvaluate(evaluate));
        page.init(baseService.pageCount(evaluate),page.getPageSize());
        result.setPagination(page);
        return result;
    }




}
