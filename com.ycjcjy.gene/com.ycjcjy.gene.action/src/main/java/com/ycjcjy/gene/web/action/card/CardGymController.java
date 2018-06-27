package com.ycjcjy.gene.web.action.card;

import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CardGymBasic;
import com.ycjcjy.gene.model.CardTicketMaster;
import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CardGymBasicService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/10
 **/
@Controller
@RequestMapping("cardGym")
public class CardGymController extends BaseController<CardGymBasic,CardGymBasicService> {


    @RequestMapping("gymList")
    @ResponseBody
    public PageResult<CardGymBasic> list (Sort sort, Pagination page, PageResult<CardGymBasic> result,CardGymBasic cardGymBasic, @RequestParam(value = "conditionList",required = false) String conditionStr){
        cardGymBasic=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        List<CardGymBasic> list = baseService.findList(cardGymBasic);
        dicCoverList(list,"dic@JSKYXQ$card_gym_type");
        result.setData(dataList);
        page.init(baseService.getCount(cardGymBasic),page.getPageSize());
        result.setPagination(page);
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
    @Override
    public PageResult<CardGymBasic> save(Model model, CardGymBasic entity, PageResult<CardGymBasic> result) {
        entity.setCreate_time(new Date());
        if (entity.getId() != null) {
            baseService.update(entity);
        } else {
            baseService.save(entity);
        }
        result.getData().add(entity);
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
    public PageResult<CardGymBasic> delete(Model model, @PathVariable("id")String id, PageResult<CardGymBasic> result) {
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
