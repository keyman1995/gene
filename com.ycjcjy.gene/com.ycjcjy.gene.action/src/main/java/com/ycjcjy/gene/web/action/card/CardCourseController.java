package com.ycjcjy.gene.web.action.card;

import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CardCourdeBasic;
import com.ycjcjy.gene.service.CardCourdeBasicService;
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




/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/10
 **/
@Controller
@RequestMapping("cardCourse")
public class CardCourseController extends BaseController<CardCourdeBasic,CardCourdeBasicService>{

    @Autowired
    private CourseManagerService courseManagerService;

    @RequestMapping("CourseList")
    @ResponseBody
    public PageResult<CardCourdeBasic> list (Sort sort, Pagination page, PageResult<CardCourdeBasic> result, CardCourdeBasic cardCourdeBasic, @RequestParam(value = "conditionList",required = false) String conditionStr){
        cardCourdeBasic=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        result.setData(baseService.findCourse(cardCourdeBasic));
        page.init(baseService.pageCount(cardCourdeBasic),page.getPageSize());
        result.setPagination(page);
        return result;
    }

    /**
     * 删除
     * @param model
     * @return
     */
    @RequestMapping(value = "deleteCard/{id}")
    @Description(value = "删除")
    @ResponseBody
    public PageResult<CardCourdeBasic> delete(Model model, @PathVariable("id")String id, PageResult<CardCourdeBasic> result) {
        if (baseService.hasTicket(Integer.parseInt(id))>0){
            result.setMsg("卡下有劵，不能删除！");
            result.setFlag(false);
        }else {
            baseService.deleteById(id);
            result.setFlag(true);
            result.setMsg("删除成功！");
        }
        return result;
    }


    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    public String edit(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.getById(Long.valueOf(String.valueOf(id))));
        model.addAttribute("courses",courseManagerService.getAllForSel());
        model.addAttribute("edit",true);
        return getView("detail");
    }

    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "add")
    @Description(value = "新增页面")
    public String add(Model model,CardCourdeBasic entity) {
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
        model.addAttribute("view",true);
        model.addAttribute("courses",courseManagerService.getAllForSel());
        return getView("detail");
    }




}
