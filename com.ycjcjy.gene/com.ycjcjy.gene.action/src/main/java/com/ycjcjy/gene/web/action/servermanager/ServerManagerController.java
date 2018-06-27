package com.ycjcjy.gene.web.action.servermanager;

import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.ServerLinkCourse;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CourseManagerService;
import com.ycjcjy.gene.service.ServerLinkCourseService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.ServerManager;
import com.ycjcjy.gene.service.ServerManagerService;
import net.onebean.util.DateUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
* @author chenjie
* @description 5A服务 controller
* @date 2018-05-28 09:26:17
*/
@Controller
@RequestMapping("servermanager")
public class ServerManagerController extends BaseController<ServerManager,ServerManagerService> {

    @Autowired
    private CourseManagerService courseManagerService;
    @Autowired
    private ServerLinkCourseService serverLinkCourseService;


    /**
     * 预览列表页面
     * @return view
     */
  /*  @RequestMapping(value = "preview")
    @Description(value = "预览列表页面")
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_PREVIEW')")
    public String preview(Model model) {
        return getView("list");
    }*/


    /**
     * 新增页面
     * @param model modelAndView
     * @param entity 实体
     * @return view
     */
 /*   @RequestMapping(value = "add")
    @Description(value = "新增页面")
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_ADD')")
    public String add(Model model, ServerManager entity) {
        model.addAttribute("add", true);
        model.addAttribute("entity", entity);
        return getView("detail");
    }*/

    /**
     * 查看页面
     * @param model modelAndView
     * @param id 主键
     * @return view
     */
    @RequestMapping(value = "view/{id}")
    @Description(value = "查看页面")
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_VIEW')")
    public String view(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", baseService.findById(id));
        List<Map<String,Object>> cousers = courseManagerService.getAllForSel();
        model.addAttribute("courses",cousers);
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
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_EDIT')")
    public String edit(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", baseService.findById(id));
        List<Map<String,Object>> cousers = courseManagerService.getAllForSel();
        model.addAttribute("courses",cousers);
        model.addAttribute("edit", true);
        return getView("detail");
    }

    /**
     * 保存
     * @param entity 实体
     * @param result 结果集
     * @return PageResult<ServerManager>
    */
    @RequestMapping(value = "saveServer")
    @Description(value = "保存")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_SAVE')")
    public PageResult<ServerManager> save(ServerManager entity, PageResult<ServerManager> result, HttpServletRequest request) {
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        entity.setCreateby(sysUser.getId());
        Date now = new Date();
        entity.setCreatetime(DateUtils.dateToTimeStamp(now));
        baseService.save(entity);
        result.getData().add(entity);
        return result;
    }

    @RequestMapping(value = "updateStatus")
    @Description(value = "更改状态")
    @ResponseBody
    public PageResult<ServerManager> updateispublic(Integer id, String status, PageResult<ServerManager> result){
        baseService.updateStatus(Long.valueOf(String.valueOf(id)),status);
        result.setFlag(true);
        return result;
    }




    @RequestMapping(value = "addlink/{id}")
    public String addlink(Model model, @PathVariable("id") Object id){
        model.addAttribute("serverid",id);
      return getView("linkcourselist");
    }

    @RequestMapping(value = "addlinkcourse")
    public String addlinkcourse(Model model, ServerLinkCourse entity) {
        model.addAttribute("add", true);
        List<Map<String,Object>> cousers = courseManagerService.getAllForSel();
        model.addAttribute("courses",cousers);
        model.addAttribute("entity", entity);
        return getView("linkcoursedetail");
    }

    @RequestMapping("listlink")
    @ResponseBody
    //@PreAuthorize("hasPermission('$everyone','PERM_SERVER_LIST')")
    public PageResult<ServerLinkCourse> listlinkCourse(Long serverId, PageResult<ServerLinkCourse> result) {
        List<ServerLinkCourse> serverLinkCourseList = serverLinkCourseService.getAllLinkCourses(serverId);
        /*result.setPagination(page);*/
        result.setData(serverLinkCourseList);
        return result;
    }

    @RequestMapping("editlinkcourse/{id}")
    @Description(value = "编辑页面")
    //@PreAuthorize("hasPermission('$everyone','PERM_SERVER_EDIT')")
    public String editLink(Model model, @PathVariable("id") Object id) {
        model.addAttribute("entity", serverLinkCourseService.findById(id));
        List<Map<String,Object>> cousers = courseManagerService.getAllForSel();
        model.addAttribute("courses",cousers);
        model.addAttribute("edit", true);
        return getView("linkcoursedetail");
    }

    @RequestMapping("savelink")
    @Description(value = "保存关联课程")
    @ResponseBody
    public PageResult<ServerLinkCourse> savelinkCourse(Model model, ServerLinkCourse entity, PageResult<ServerLinkCourse> result,HttpServletRequest request) {
        Date nowdate = new Date();
        entity.setCreatetime(DateUtils.dateToTimeStamp(nowdate));
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        entity.setCreateby(sysUser.getId());
        serverLinkCourseService.save(entity);
        result.getData().add(entity);
        return result;
    }

/*    @RequestMapping("deletelinkcourse")
    @ResponseBody
    public PageResult<ServerLinkCourse> delete(@PathVariable("id") Object id, PageResult<ServerLinkCourse> result) {
        serverLinkCourseService.deleteById(id);
        result.setFlag(true);
        return result;
    }*/









    /**
     * 根据ID删除
     * @param id 主键
     * @param result 结果集
     * @return PageResult<ServerManager>
    */
   /* @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_DELETE')")
    public PageResult<ServerManager> delete(@PathVariable("id") Object id, PageResult<ServerManager> result) {
        baseService.deleteById(id);
        result.setFlag(true);
        return result;
    }*/

    /**
     * 列表数据
     * @param sort 排序参数
     * @param page 分页参数
     * @param result 结果集
     * @param cond 表达式
     * @return PageResult<ServerManager>
    */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasPermission('$everyone','PERM_SERVER_LIST')")
    public PageResult<ServerManager> list(Sort sort, Pagination page, PageResult<ServerManager> result, @RequestParam(value = "conditionList", required = false) String cond) {
        initData(sort, page, cond);
        dicCoverList(dataList,"date@createtime$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }
}
