package com.ycjcjy.gene.web.action.course;

import com.ycjcjy.gene.VO.CourseTypeTree;
import com.ycjcjy.gene.VO.OrgTree;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CourseType;
import com.ycjcjy.gene.model.SysOrganization;
import com.ycjcjy.gene.service.CourseTypeService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.form.Parse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/14
 **/

@Controller
@RequestMapping("coursetype")
public class CourseTypeController extends BaseController<CourseType,CourseTypeService> {

    @RequestMapping("alltypetree")
    @ResponseBody
    public PageResult<CourseTypeTree> allcoursetree(@RequestParam(value = "parentid",required = false) Long parentid, PageResult<CourseTypeTree> result){
        List<CourseType> list = baseService.findChildSync(parentid);
        result.setData(baseService.courseToCourseTypeTree(list,null));
        return result;
    }

    @RequestMapping("findByParam")
    @ResponseBody
    public PageResult<CourseType> allCourse(@RequestParam(value = "parentid",required = false) Long parentid,PageResult<CourseType> result){
        dataList = baseService.findChildSync(parentid);
        result.setData(dataList);
        return result;
    }






    @RequestMapping("coursetypetree")
    @ResponseBody
    public PageResult<CourseTypeTree> coursetypetree(Pagination page, PageResult<CourseTypeTree> result, @RequestParam(value = "parentid",required = false) Long parentid, @RequestParam(value = "self_id",required = false) Long self_id){
        List<CourseType> list = baseService.findChildSync(parentid);
        result.setData(baseService.courseToCourseTypeTree(list,self_id));
        result.setPagination(page);
        return result;
    }

    @RequestMapping("coursetypePlace")
    @ResponseBody
    public PageResult<CourseType> coursetypePlace(Pagination page, PageResult<CourseType> result){
        result.setData(baseService.findPicPlace());
        result.setPagination(page);
        return result;
    }
    @RequestMapping("addChild")
    public String addChild(Model model, @RequestParam("parentid")Long parentid,CourseType entity) {
        entity.setSort(baseService.findChildOrderNextNum(parentid));
        model.addAttribute("entity",entity);
        model.addAttribute("add",true);
        return getView("detail");
    }

    @RequestMapping(value = "delete/{id}")
    @ResponseBody
    public PageResult<CourseType> delete(Model model, @PathVariable("id")Object id, PageResult<CourseType> result) {
        baseService.deleteSelfAndChildById(Parse.toLong(id));
        result.setFlag(true);
        return result;
    }

    @RequestMapping("saveCourseType")
    @ResponseBody
    public PageResult<CourseType> saveCourseType( CourseType entity, PageResult<CourseType> result){

        List<String> unicodes = baseService.getAllUnicode(entity.getId());
        if(unicodes!=null && unicodes.size()!=0) {
            if (unicodes.contains(entity.getCode())) {
                result.setFlag(false);
                result.setMsg("编码重复");
                return result;
            }
        }
        if (entity.getId() != null && !"".equals(entity.getId())) {
            baseService.update(entity);
        } else {
            baseService.save(entity);
        }
        result.setFlag(true);
        return result;
    }



}
