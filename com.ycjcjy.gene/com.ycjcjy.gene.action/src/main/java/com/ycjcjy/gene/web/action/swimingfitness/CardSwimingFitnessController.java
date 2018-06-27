package com.ycjcjy.gene.web.action.swimingfitness;

import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CardSwimingFitness;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.service.CardSwimingFitnessService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.util.DateUtils;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/swimingfitness")
public class CardSwimingFitnessController extends BaseController<CardSwimingFitness,CardSwimingFitnessService> {

    @RequestMapping("list")
    @ResponseBody
    public PageResult<CardSwimingFitness> list (Sort sort, Pagination page, PageResult<CardSwimingFitness> result, @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        dicCoverList(null,"dic@JSKYXQ$time_scope","dic@JSKLX$type","date@create_time$","date@start_crad_time$","date@end_crad_time$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    @RequestMapping("findbyid")
    @ResponseBody
    public PageResult<CardSwimingFitness> findById(@RequestParam("id")Object id, PageResult<CardSwimingFitness> result){
        List<CardSwimingFitness> list = new ArrayList<>();
        CardSwimingFitness entiy = baseService.findById(id);
        list.add(entiy);
        result.setData(list);
        return result;
    }

}
