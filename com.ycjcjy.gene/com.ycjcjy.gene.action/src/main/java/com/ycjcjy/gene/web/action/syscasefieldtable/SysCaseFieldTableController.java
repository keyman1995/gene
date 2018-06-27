package com.ycjcjy.gene.web.action.syscasefieldtable;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.SysCaseFieldArea;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.SysCaseFieldAreaService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.SysCaseFieldTable;
import com.ycjcjy.gene.service.SysCaseFieldTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
* @author Ken Hu
* @description 案场桌位管理 controller
* @date 2018-06-11 15:35:49
*/
@Controller
@RequestMapping("syscasefieldtable")
public class SysCaseFieldTableController extends TlBaseController<SysCaseFieldTable,SysCaseFieldTableService> {
    @Autowired
    private SysCaseFieldAreaService sysCaseFieldAreaService;

    /**
     * 预览列表页面
     * @return view
     */
    @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    @PreAuthorize("hasPermission('$everyone','PERM_CASE_FIELD_TABLE_PREVIEW')")
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
    @PreAuthorize("hasPermission('$everyone','PERM_CASE_FIELD_TABLE_ADD')")
    public String add(Model model, SysCaseFieldTable entity,HttpServletRequest request) {
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
    @PreAuthorize("hasPermission('$everyone','PERM_CASE_FIELD_TABLE_VIEW')")
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
    @PreAuthorize("hasPermission('$everyone','PERM_CASE_FIELD_TABLE_EDIT')")
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
     * @return PageResult<SysCaseFieldTable>
    */
    @RequestMapping(value = "save")
    @Description(value = "保存")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_CASE_FIELD_TABLE_SAVE')")
    public PageResult<SysCaseFieldTable> save(SysCaseFieldTable entity, PageResult<SysCaseFieldTable> result) {
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }

    /**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<SysCaseFieldTable>
    */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_CASE_FIELD_TABLE_DELETE')")
    public PageResult<SysCaseFieldTable> delete(@PathVariable("id") Object id, PageResult<SysCaseFieldTable> result) {
        SysCaseFieldTable sysCaseFieldTable=baseService.findById(id);
        sysCaseFieldTable.setIs_show("0");
        baseService.update(sysCaseFieldTable);
        result.setFlag(true);
        return result;
    }

    /**
     * 列表数据
     * @param sort 排序参数
     * @param page 分页参数
     * @param result 结果集
     * @param cond 表达式
     * @return PageResult<SysCaseFieldTable>
    */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_CASE_FIELD_TABLE_LIST')")
    public PageResult<SysCaseFieldTable> list(Sort sort, Pagination page, PageResult<SysCaseFieldTable> result, @RequestParam(value = "conditionList", required = false) String cond) {
        initData(sort, page, cond);
        List<SysCaseFieldArea> list = sysCaseFieldAreaService.findAll();
        for (int i = 0; i<list.size();i++){
            for (int j = 0;j<dataList.size();j++){
                if (list.get(i).getId() == Long.parseLong(dataList.get(j).getArea())){
                    dataList.get(j).setAreaName(list.get(i).getArea_name());
                }
            }
        }
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }
}
