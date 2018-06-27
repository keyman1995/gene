package com.ycjcjy.gene.web.action.card;

import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CardGoodsBasic;
import com.ycjcjy.gene.service.CardGoodsBasicService;
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

import java.util.List;


/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/10
 **/
@Controller
@RequestMapping("cardGoods")
public class CardGoodsController extends BaseController<CardGoodsBasic,CardGoodsBasicService>{



    @RequestMapping("goodsList")
    @ResponseBody
    public PageResult<CardGoodsBasic> list (Sort sort, Pagination page, PageResult<CardGoodsBasic> result,CardGoodsBasic cardGoodsBasic, @RequestParam(value = "conditionList",required = false) String conditionStr){
        cardGoodsBasic=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        List<CardGoodsBasic> list = baseService.findGoods(cardGoodsBasic);
        dicCoverList(list,"dic@LOCAL_GOODS$type");
        result.setData(dataList);
        page.init(baseService.pageCount(cardGoodsBasic),page.getPageSize());
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
    public PageResult<CardGoodsBasic> delete(Model model, @PathVariable("id")String id, PageResult<CardGoodsBasic> result) {
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






}
