package com.ycjcjy.gene.web.action.sys;


import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.SysRole;
import com.ycjcjy.gene.model.SysRoleUser;
import com.ycjcjy.gene.service.SysPermissionRoleService;
import com.ycjcjy.gene.service.SysRoleService;
import com.ycjcjy.gene.service.SysRoleUserService;
import net.onebean.core.ConditionMap;
import net.onebean.core.ListPageQuery;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.core.form.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理
 * @author 0neBean
 */
@Controller
@RequestMapping("/sysrole")
public class SysRoleController extends TlBaseController<SysRole,SysRoleService> {

    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysPermissionRoleService sysPermissionRoleService;


    /**
     * 预览列表页面
     * @return view
     */
    @RequestMapping("preview")
    @Description(value = "预览列表页面")
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_PREVIEW')")
    public String preview() {
        return getView("list");
    }

    /**
     * 新增页面
     * @param model modelAndView
     * @param entity 实体
     * @return view
     */
    @RequestMapping("add")
    @Description(value = "新增页面")
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_ADD')")
    public String add(Model model,SysRole entity) {
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
        return getView("detail");
    }

    /**
     * 查看页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     */
    @RequestMapping("view/{id}")
    @Description(value = "查看页面")
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_VIEW')")
    public String view(Model model,@PathVariable("id")Object id){
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("view",true);
        return getView("detail");
    }

    /**
     * 编辑页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     */
    @RequestMapping("edit/{id}")
    @Description(value = "编辑页面")
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_EDIT')")
    public String edit(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        return getView("detail");
    }

    /**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<SysRole>
     */
    @RequestMapping("save")
    @Description(value = "保存")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_SAVE')")
    public PageResult<SysRole> save(SysRole entity, PageResult<SysRole> result) {
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }


    /**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<SysRole>
     */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_DELETE')")
    public PageResult<SysRole> delete(@PathVariable("id")Object id, PageResult<SysRole> result) {
        baseService.deleteById(id);
        Long roleId = Parse.toLong(id);
        sysRoleUserService.deleteByRoleId(roleId);
        sysPermissionRoleService.deteleByRoleId(roleId);
        result.setFlag(true);
        return result;
    }

    /**
     * 列表数据
     * @param sort 排序参数
     * @param page 分页参数
     * @param result 结果集
     * @param cond 表达式
     * @return PageResult<SysRole>
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_LIST')")
    public PageResult<SysRole> list (Sort sort, Pagination page, PageResult<SysRole> result
            , @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        dicCoverList(null,"dic@SF$is_lock","dic@SJQX$data_permission_level","date@creat_time$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    /**
     * 查找用户的所有角色
     * @param userId 用户ID
     * @param result 结果集
     * @return PageResult<SysRole>
     */
    @RequestMapping("findbyuid")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_FIND_BY_USERID')")
    public PageResult<SysRole> findRolesByUserId(@RequestParam("userId")Long userId, PageResult<SysRole> result){
        result.setData(baseService.findRolesByUserId(userId));
        return result;
    }

    /**
     * 根据角色名查找角色
     * @param name 角色名
     * @param page 分页信息
     * @param result 结果集
     * @return PageResult<SysRole>
     */
    @RequestMapping("findbyname")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_FIND_BY_NAME')")
    public PageResult<SysRole> findByName(@RequestParam("name")String name, Pagination page, PageResult<SysRole> result){
        ListPageQuery query = new ListPageQuery();
        ConditionMap map = new ConditionMap();
        Sort sort = new Sort();
        map.parseCondition(MessageFormat.format("ch_name@string@like${0}^is_lock@string@eq${1}",name,0));
        sort.setSort(Sort.DESC);
        sort.setOrderBy("id");
        query.setConditions(map);
        query.setPagination(page);
        query.setSort(sort);
        result.setPagination(page);
        result.setData(baseService.find(query));
        return result;
    }

    /**
     * 添加用户角色关联信息
     * @param userId 用户ID
     * @param result 结果集
     * @param roleIds 角色IDs 用','分割的字符串
     * @param page 分页信息
     * @return PageResult<SysRole>
     */
    @RequestMapping("addroleuser")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_ADD_ROLE_USER')")
    public PageResult<SysRole> addRoleUser(@RequestParam("userId")Long userId, PageResult<SysRole> result,
                                           @RequestParam("roleIds")String roleIds, Pagination page){
        String[] roleIdsArry = roleIds.split(",");
        for (String s : roleIdsArry) {
            SysRoleUser temp = new SysRoleUser(userId, Parse.toLong(s));
            sysRoleUserService.save(temp);
        }
        result.setFlag(true);
        return result;
    }

    /**
     * 删除用户角色关联信息
     * @param urIds 用户IDs 用','分割的字符串
     * @param result 结果集
     * @return PageResult<SysRole>
     */
    @RequestMapping("removeroleuser")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_ROLE_REMOVE_ROLE_USER')")
    public PageResult<SysRole> removeRoleUser(@RequestParam("urIds")String urIds, PageResult<SysRole> result){
        String[] urIdsArry = urIds.split(",");
        List <Long> ids = new ArrayList<>();
        for (String s : urIdsArry) {
            ids.add(Parse.toLong(s));
        }
        sysRoleUserService.deleteByIds(ids);
        result.setFlag(true);
        return result;
    }



}
