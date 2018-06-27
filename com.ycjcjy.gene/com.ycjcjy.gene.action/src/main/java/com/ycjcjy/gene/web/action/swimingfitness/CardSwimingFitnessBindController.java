package com.ycjcjy.gene.web.action.swimingfitness;

import com.ycjcjy.gene.common.dictionary.DictionaryUtils;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CardSwimingFitness;
import com.ycjcjy.gene.model.CardSwimingFitnessBind;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CardSwimingFitnessBindService;
import com.ycjcjy.gene.service.CardSwimingFitnessService;
import com.ycjcjy.gene.service.SysCaseFieldService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.util.DateUtils;
import net.onebean.util.PropUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/swimingfitnessbind")
public class CardSwimingFitnessBindController extends BaseController<CardSwimingFitnessBind,CardSwimingFitnessBindService> {

    @Autowired
    private CardSwimingFitnessService cardSwimingFitnessService;

    @RequestMapping("list")
    @ResponseBody
    public PageResult<CardSwimingFitnessBind> list (Sort sort, Pagination page, PageResult<CardSwimingFitnessBind> result, @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        dicCoverList(null,"date@create_time$","date@start_crad_time$","date@end_crad_time$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "add")
    @Description(value = "新增页面")
    public String add(Model model,CardSwimingFitnessBind entity) {
        model.addAttribute("cardList",cardSwimingFitnessService.findAll());
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
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
        model.addAttribute("cardList",cardSwimingFitnessService.findAll());
        model.addAttribute("entity",baseService.findById(id));
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
        model.addAttribute("cardList",cardSwimingFitnessService.findAll());
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        return getView("detail");
    }

    /**
     * 保存
     * @param model
     * @return
     */
    @RequestMapping(value = "saves")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<CardSwimingFitnessBind> saves(Model model, CardSwimingFitnessBind entity, PageResult<CardSwimingFitnessBind> result,HttpServletRequest request) {
        SysUser create_user = SpringSecurityUtil.getCurrentLoginUser(request);
        result.getData().add(baseService.saves(entity,create_user));
        return result;
    }
}
