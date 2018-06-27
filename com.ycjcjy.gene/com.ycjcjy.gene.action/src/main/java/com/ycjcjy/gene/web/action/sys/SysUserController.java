package com.ycjcjy.gene.web.action.sys;


import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.OneBeanPasswordEncoder;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.SysCaseFieldService;
import com.ycjcjy.gene.service.SysRoleUserService;
import com.ycjcjy.gene.service.SysUserService;
import net.onebean.core.Condition;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sysuser")
public class SysUserController extends TlBaseController<SysUser,SysUserService> {
    @Autowired
    private OneBeanPasswordEncoder oneBeanPasswordEncoder;
    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;


    /**
     * 编辑页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     */
    @RequestMapping("edit/{id}")
    @Description(value = "编辑页面")
    @PreAuthorize("hasPermission('$everyone','PERM_USER_EDIT')")
    public String edit(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        model.addAttribute("caseFieldList",sysCaseFieldService.findAll());
        return getView("detail");
    }

    /**
     * 查看页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     *
     */
    @RequestMapping("view/{id}")
    @Description(value = "查看页面")
    @PreAuthorize("hasPermission('$everyone','PERM_USER_VIEW')")
    public String view(Model model,@PathVariable("id")Object id){
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("view",true);
        model.addAttribute("caseFieldList",sysCaseFieldService.findAll());
        return getView("detail");
    }

    /**
     * 新增页面
     * @param model modelAndView
     * @param entity 实体
     * @return view
     */
    @RequestMapping("add")
    @Description(value = "新增页面")
    @PreAuthorize("hasPermission('$everyone','PERM_USER_ADD')")
    public String add(Model model,SysUser entity) {
        entity.setCan_send_card(1);
        entity.setCan_send(1);
        entity.setMsg_state(0);
        entity.setId(Long.valueOf(0));
        entity.setIs_field_contact("0");
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
        model.addAttribute("caseFieldList",sysCaseFieldService.findAll());
        return getView("detail");
    }

    /**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<SysUser>
     */
    @RequestMapping(value = "save")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_USER_SAVE')")
    public PageResult<SysUser> save(SysUser entity, PageResult<SysUser> result) {
        if(entity.getId()==0){
            entity.setId(null);
        }

        if (null != entity.getPassword() && entity.getPassword().length() != 80){
            //页面限制用户密码长度为30,加密过得密码长度为80 未加密就加个密吧!
            entity.setPassword(oneBeanPasswordEncoder.encode(entity.getPassword()));
        }
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }

    /**
     * 预览列表页面
     * @return view
     */
    @RequestMapping("preview")
    @Description(value = "预览列表页面")
    @PreAuthorize("hasPermission('$everyone','PERM_USER_PREVIEW')")
    public String preview() {
        return getView("list");
    }


    /**
     * 列表数据
     * @param sort 排序参数
     * @param page 分页参数
     * @param result 结果集
     * @param cond 表达式
     * @return PageResult<SysUser>
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_USER_LIST')")
    public PageResult<SysUser> list (Sort sort, Pagination page, PageResult<SysUser> result,@RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        dicCoverList(dataList,"dic@SF$is_lock","dic@YHLX$user_type","date@creat_time$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    /**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<SysUser>
     */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_USER_DELETE')")
    public PageResult<SysUser> delete(Model model, @PathVariable("id")Object id, PageResult<SysUser> result) {
        baseService.deleteById(id);
        sysRoleUserService.deleteByUserId(Parse.toLong(id));
        result.setFlag(true);
        return result;
    }

    /**
     * 重置密码
     * @param id 主键
     * @param result 结果集
     * @return PageResult<SysUser>
     */
    @RequestMapping(value = "resetpassword/{id}")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_USER_RESET_PASSWORD')")
    public PageResult<SysUser> resetpassword(@PathVariable("id")Object id, PageResult<SysUser> result) {
        SysUser entity =  baseService.findById(id);
        entity.setPassword(oneBeanPasswordEncoder.encode("123456"));
        baseService.update(entity);
        result.setFlag(true);
        return result;
    }

    /**
     * 账户设置
     * @param request HttpServletRequest
     * @param model modelAndView
     * @return view
     */
    @RequestMapping(value = "setting")
    @PreAuthorize("isAuthenticated()")
    public String setting(HttpServletRequest request,Model model) {
        SysUser entity = SpringSecurityUtil.getCurrentLoginUser(request);
        model.addAttribute("entity",entity);
        model.addAttribute("edit",true);
        return getView("setting");
    }

    /**
     * 设置密码
     * @param request HttpServletRequest
     * @param model modelAndView
     * @return view
     */
    @RequestMapping(value = "setpassword")
    @PreAuthorize("isAuthenticated()")
    public String setpassword(HttpServletRequest request,Model model) {
        model.addAttribute("entity",SpringSecurityUtil.getCurrentLoginUser(request));
        return getView("setpassword");
    }

    /**
     * 根据用户'orgid'查找用户
     * @param orgId 机构ID
     * @param result 结果集
     * @return view
     */
    @RequestMapping(value = "finduserbyorgid")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_USER_FIND_BY_ORGID')")
    public PageResult<SysUser> findUserByOrgId(@RequestParam("orgId")String orgId,PageResult<SysUser> result){
        Condition param =  Condition.parseCondition("org_id@string@eq");
        param.setValue(orgId);
        result.setData(baseService.find(null,param));
        return  result;
    }

    @RequestMapping(value = "findUserByTel")
    @ResponseBody
    public Map<String,Object> findUserByTel(String tel){
        Map<String,Object> result = new HashMap<>();
        SysUser user = baseService.findUserByPhone(tel);
        result.put("user",user);
        result.put("isexist",1);
        if(user==null){
            result.put("isexist",0);
        }
        return  result;
    }

    @RequestMapping(value = "findUserByTelForSysUser")
    @ResponseBody
    public Map<String,Object> findUserByTelForSysUser(String tel,Integer id){
        Map<String,Object> result = new HashMap<>();
        SysUser user = baseService.findUserByPhone(tel);
        if(user!=null&&user.getId().toString().equals(id.toString())){
            user=null;
        }
        result.put("user",user);
        result.put("isexist",1);
        if(user==null){
            result.put("isexist",0);
        }
        return  result;
    }

    /**
     * 编辑页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     */
    @RequestMapping("edit1/{id}")
    @Description(value = "编辑页面")
    public String edit1(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        model.addAttribute("caseFieldList",sysCaseFieldService.findAll());
        return getView("detail");
    }

    /**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<SysUser>
     */
    @RequestMapping(value = "save1")
    @ResponseBody
    public PageResult<SysUser> save1(SysUser entity, PageResult<SysUser> result) {
        if (null != entity.getPassword() && entity.getPassword().length() != 80){
            //页面限制用户密码长度为30,加密过得密码长度为80 未加密就加个密吧!
            entity.setPassword(oneBeanPasswordEncoder.encode(entity.getPassword()));
        }
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }
}
