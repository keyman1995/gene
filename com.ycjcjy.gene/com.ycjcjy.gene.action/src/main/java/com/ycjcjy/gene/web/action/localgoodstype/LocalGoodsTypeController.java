package com.ycjcjy.gene.web.action.localgoodstype;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.LocalGoodsType;
import com.ycjcjy.gene.service.LocalGoodsTypeService;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
* @author Ken Hu
* @description 商品类型管理 controller
* @date 2018-06-11 15:43:08
*/
@Controller
@RequestMapping("localgoodstype")
public class LocalGoodsTypeController extends TlBaseController<LocalGoodsType,LocalGoodsTypeService> {

    /**
     * 预览列表页面
     * @return view
     */
    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    @PreAuthorize("hasPermission('$everyone','PERM_LOCAL_GOODS_TYPE_PREVIEW')")
    public String preview(Model model, HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("field_id",user.getField_id());
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
    @PreAuthorize("hasPermission('$everyone','PERM_LOCAL_GOODS_TYPE_ADD')")
    public String add(Model model, LocalGoodsType entity,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("field_id",user.getField_id());
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
    @PreAuthorize("hasPermission('$everyone','PERM_LOCAL_GOODS_TYPE_VIEW')")
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
    @PreAuthorize("hasPermission('$everyone','PERM_LOCAL_GOODS_TYPE_EDIT')")
    public String edit(Model model, @PathVariable("id") Object id,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("field_id",user.getField_id());
        model.addAttribute("entity", baseService.findById(id));
        model.addAttribute("edit", true);
        return getView("detail");
    }

    /**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<LocalGoodsType>
    */
    @RequestMapping(value = "save")
    @Description(value = "保存")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_LOCAL_GOODS_TYPE_SAVE')")
    public PageResult<LocalGoodsType> save(LocalGoodsType entity, PageResult<LocalGoodsType> result) {
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }

    /**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<LocalGoodsType>
    */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_LOCAL_GOODS_TYPE_DELETE')")
    public PageResult<LocalGoodsType> delete(@PathVariable("id") Object id, PageResult<LocalGoodsType> result,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        LocalGoodsType localGoodsType=baseService.findById(id);
        if (baseService.hasGoods(localGoodsType.getId().toString(),user.getField_id().toString())>0){
            result.setFlag(false);
            result.setMsg("种类下有商品，不能删除");
        }else {
            LocalGoodsType localGoodsType1=baseService.findById(id);
            localGoodsType.setIs_show("0");
            baseService.update(localGoodsType);
            result.setFlag(true);
        }
        return result;
    }

    /**
     * 列表数据
     * @param sort 排序参数
     * @param page 分页参数
     * @param result 结果集
     * @param cond 表达式
     * @return PageResult<LocalGoodsType>
    */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_LOCAL_GOODS_TYPE_LIST')")
    public PageResult<LocalGoodsType> list(Sort sort, Pagination page, PageResult<LocalGoodsType> result, @RequestParam(value = "conditionList", required = false) String cond) {
        initData(sort, page, cond);
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    @RequestMapping("allGoodsType")
    @ResponseBody
    public PageResult<LocalGoodsType> allGoodsType (Sort sort, Pagination page, PageResult<LocalGoodsType> result,HttpServletRequest request){
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        result.setData(baseService.findAllType(user.getField_id()));
        return result;
    }
}
