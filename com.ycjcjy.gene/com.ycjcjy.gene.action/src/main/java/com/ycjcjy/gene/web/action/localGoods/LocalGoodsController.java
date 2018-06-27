package com.ycjcjy.gene.web.action.localGoods;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.model.LocalGoods;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CardTicketMasterService;
import com.ycjcjy.gene.service.LocalGoodsService;

import com.ycjcjy.gene.service.LocalGoodsTypeService;
import com.ycjcjy.gene.service.SysCaseFieldService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("localgoodsfield")
public class LocalGoodsController extends TlBaseController<LocalGoods,LocalGoodsService> {
    @Autowired
    private CardTicketMasterService cardTicketMasterService;
    @Autowired
    private LocalGoodsTypeService localGoodsTypeService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;


    /**
     * 预览列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "previews")
    @Description(value = "预览列表页面")
    public String previews(Model model, HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("field_id",user.getField_id());
        return getView("list");
    }

    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "add")
    @Description(value = "新增页面")
    public String add(Model model,LocalGoods entity,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("field_id",user.getField_id());
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
    public String edit(Model model,@PathVariable("id")Object id,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("field_id",user.getField_id());
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        return getView("detail");
    }

    /**
     * 保存
     * @param model
     * @return
     */
    @RequestMapping(value = "save")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<LocalGoods> save(Model model, LocalGoods entity, PageResult<LocalGoods> result) {
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
    @RequestMapping(value = "deleteGoods/{id}")
    @Description(value = "删除")
    @ResponseBody
    public PageResult<LocalGoods> delete(Model model, @PathVariable("id")String id, PageResult<LocalGoods> result) {
        if (baseService.hasCard(Long.parseLong(id))>0){
            result.setMsg("商品下有卡，不能删除！");
            result.setFlag(false);
        }else {
            LocalGoods localGoods=baseService.findById(id);
            localGoods.setIs_show("0");
            baseService.update(localGoods);
            result.setFlag(true);
            result.setMsg("删除成功！");
        }
        return result;
    }


    @RequestMapping("list")
    @ResponseBody
    public PageResult<LocalGoods> list (Sort sort, Pagination page, PageResult<LocalGoods> result
            , @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        //dicCoverList(dataList,"dic@LOCAL_GOODS$type");
        List<LocalGoodsType> list = localGoodsTypeService.findAll();
        List<SysCaseField> list1 = sysCaseFieldService.findAll();
        for(int j =0;j<dataList.size();j++){
            for(int i=0; i<list.size();i++){
                if (dataList.get(j).getType().equals(list.get(i).getId().toString())){
                    dataList.get(j).setType(list.get(i).getType());
                }
            }
            for(int k =0;k<list1.size();k++){
                Long caseId = Long.parseLong(dataList.get(j).getCase_field_id());
                if (caseId == list1.get(k).getId()){
                    dataList.get(j).setCaseField(list1.get(k).getCasefieldname());
                }
            }
        }

        result.setData(dataList);
        result.setPagination(page);
        return result;
    }



    @RequestMapping(value = "cardSale/{cardId}")
    @ResponseBody
    public PageResult<LocalGoods> sale (Model model, PageResult<LocalGoods> result
            ,@PathVariable("cardId")String cardId){
        CardTicketMaster target= cardTicketMasterService.findById(cardId);
        if(null != target){
            Integer targetId =target.getTarget_id();
            LocalGoods entity = baseService.findById(targetId.toString());
            model.addAttribute("entity",entity);
            result.getData().add(entity);


        }
        return result;
    }



    


    @RequestMapping("allGoods")
    @ResponseBody
    public PageResult<LocalGoods> allGoods (Sort sort, Pagination page, PageResult<LocalGoods> result){
        result.setData(baseService.findAll());
        return result;
    }


}
