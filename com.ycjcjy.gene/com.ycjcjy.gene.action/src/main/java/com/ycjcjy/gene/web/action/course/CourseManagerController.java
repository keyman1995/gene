package com.ycjcjy.gene.web.action.course;

import com.ycjcjy.gene.VO.CourseEnum;
import com.ycjcjy.gene.common.CDateUtil;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CourseLinkeTag;
import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.SubCourseManager;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.*;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.core.form.Parse;
import net.onebean.util.DateUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/11
 **/
@Controller
@RequestMapping("course")
public class CourseManagerController extends BaseController<CourseManager,CourseManagerService> {

    @Autowired
    private CourseManagerService courseManagerService;
    @Autowired
    private SubCourseManagerService subCourseManagerService;
    @Autowired
    private CardCourdeBasicService cardCourdeBasicService;
    @Autowired
    private CourseLinkeTagService courseLinkeTagService;
    @Autowired
    private CourseTagService courseTagService;

    @RequestMapping("listCourse")
    @ResponseBody
    public PageResult<CourseManager> list(Sort sort,Pagination page,PageResult<CourseManager> result,CourseManager courseManager,@RequestParam(value = "conditionList",required = false) String cond){
        courseManager = reflectionModelFormConditionMapStr(cond,sort,page);
        Integer count = baseService.getAllCount(courseManager);
        dataList = baseService.findCourses(courseManager);
        dicCoverList(dataList,"date@coursestartline$","date@createtime$","date@updatetime$","date@courseendline$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }

    @RequestMapping("viewCourse/{id}")
    public String viewCourse(Model model, @PathVariable("id")Object id){
        CourseManager courseManager = courseManagerService.findByCourseId(String.valueOf(id));
        if(!courseManager.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_JZ.getCode()))){//讲座课程需要展示时分秒
            courseManager.setCourseendline_str(DateUtils.format(courseManager.getCourseendline()));
            courseManager.setCoursestartline_str(DateUtils.format(courseManager.getCoursestartline()));
        }else{
            courseManager.setCourseendline_str(DateUtils.format(courseManager.getCourseendline(),"yyyy-MM-dd HH:mm:ss"));
            courseManager.setCoursestartline_str(DateUtils.format(courseManager.getCoursestartline(),"yyyy-MM-dd HH:mm:ss"));
        }
        model.addAttribute("entity",courseManager);
        model.addAttribute("tags",courseTagService.getAllTage(courseManager.getId()));
        model.addAttribute("view",true);
        return getView("detail");
    }

    @RequestMapping("addCourse")
    public String addCourse(Model model,CourseManager entity) {
        model.addAttribute("add",true);
        model.addAttribute("tags",courseTagService.findAll());
        model.addAttribute("entity",entity);
        return getView("detail");
    }

    @RequestMapping("editCourse/{id}")
    public String editCourse(Model model,@PathVariable("id")Object id) {
        model.addAttribute("add",true);
        CourseManager courseManager = courseManagerService.findByCourseId(String.valueOf(id));
        if(!courseManager.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_JZ.getCode()))){//讲座课程需要展示时分秒
            courseManager.setCourseendline_str(DateUtils.format(courseManager.getCourseendline()));
            courseManager.setCoursestartline_str(DateUtils.format(courseManager.getCoursestartline()));
        }else{
            courseManager.setCourseendline_str(DateUtils.format(courseManager.getCourseendline(),"yyyy-MM-dd HH:mm:ss"));
            courseManager.setCoursestartline_str(DateUtils.format(courseManager.getCoursestartline(),"yyyy-MM-dd HH:mm:ss"));
        }
        model.addAttribute("entity",courseManager);
        model.addAttribute("tags",courseTagService.findAll());
        return getView("detail");
    }

    @RequestMapping("copyCourse/{id}")
    public String copyCourse(Model model,@PathVariable("id")Object id) {
        model.addAttribute("copy",true);
        CourseManager courseManager = courseManagerService.findByCourseId(String.valueOf(id));
        courseManager.setCourseprice(null);
        courseManager.setCaseid(null);
        courseManager.setId(null);
        courseManager.setTeacherids(null);
        courseManager.setSubteacherids(null);
        courseManager.setCourseaddress(null);
        courseManager.setIspublic("0");
        model.addAttribute("entity",courseManager);
        model.addAttribute("tags",courseTagService.findAll());
        return getView("detailcopy");
    }




    @RequestMapping(value = "saveCourse")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<CourseManager> save(Model model, CourseManager entity, PageResult<CourseManager> result, HttpServletRequest request) {

        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        entity.setWeekday(CDateUtil.dayForWeek(entity.getCoursestartline()));
        if(entity.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_JZ.getCode()))){
            String startDate = DateUtils.format(entity.getCoursestartline(),"yyyy.MM.dd");
            String startTime = DateUtils.format(entity.getCoursestartline(),"HH:mm");
            String endTime = DateUtils.format(entity.getCourseendline(),"HH:mm");
            entity.setCoursetimeintro(startDate+" "+entity.getWeekday()+" "+startTime+"~"+endTime);
        }
        if(entity.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_YY.getCode()))){
            String date = DateUtils.format(entity.getCoursestartline(),"yyyy.MM.dd");
            entity.setCoursetimeintro(date+" "+entity.getWeekday());
            entity.setIsgoon("0");
        }

        Date nowDate = new Date();
        if (entity.getId() != null) {
            entity.setUpdateby(sysUser.getId());
            entity.setUpdatetime(DateUtils.dateToTimeStamp(nowDate));
            baseService.update(entity);
            updateSubCourse(entity,sysUser);
        } else {
            entity.setCreateby(sysUser.getId());
            entity.setCreatetime(DateUtils.dateToTimeStamp(nowDate));
            baseService.save(entity);
            addSubCourseForCourse(entity,sysUser);
        }
        String tags = entity.getCoursetags();
        if(tags!=null && !"".equals(tags)){
            List<CourseLinkeTag> courseLinkeTags = new ArrayList<CourseLinkeTag>();
            String[] courseTags = tags.split(",");
           courseLinkeTagService.deleteByCourseId(entity.getId());
            for(String tag : courseTags){
                CourseLinkeTag courseLinkeTag = new CourseLinkeTag();
                courseLinkeTag.setCourseid(String.valueOf(entity.getId()));
                courseLinkeTag.setTagid(tag);
                courseLinkeTags.add(courseLinkeTag);
            }
            courseLinkeTagService.saveBatch(courseLinkeTags);
        }
        result.getData().add(entity);
        return result;
    }

    public void addSubCourseForCourse(CourseManager entity,SysUser sysUser){
        if(entity.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_JZ.getCode()))){
            SubCourseManager subCourseManager = new SubCourseManager();
            subCourseManager.setPcourseid(Integer.valueOf(String.valueOf(entity.getId())));
            subCourseManager.setWeekday(entity.getWeekday());
            subCourseManager.setSubstartline(entity.getCoursestartline());
            subCourseManager.setSubendline(entity.getCourseendline());
            subCourseManager.setSubcousename(entity.getCoursename());
            subCourseManager.setCreateby(sysUser.getId());
            subCourseManager.setCreatetime(DateUtils.dateToTimeStamp(new Date()));
            subCourseManager.setSubDeatil(entity.getCourseintro());
            subCourseManagerService.save(subCourseManager);
        }
    }

    public void updateSubCourse(CourseManager entity,SysUser sysUser){
        if(entity.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_JZ.getCode()))){
            SubCourseManager subCourseManager = new SubCourseManager();
            subCourseManager.setPcourseid(Integer.valueOf(String.valueOf(entity.getId())));
            subCourseManager.setWeekday(entity.getWeekday());
            subCourseManager.setSubstartline(entity.getCoursestartline());
            subCourseManager.setSubendline(entity.getCourseendline());
            subCourseManager.setSubcousename(entity.getCoursename());
            subCourseManager.setCreateby(sysUser.getId());
            subCourseManager.setCreatetime(DateUtils.dateToTimeStamp(new Date()));
            subCourseManager.setSubDeatil(entity.getCourseintro());
            subCourseManagerService.updateBySub(subCourseManager);
        }
    }



    @RequestMapping("updateispublic")
    @ResponseBody
    public PageResult<CourseManager> updateispublic(Integer id,String ispublic,PageResult<CourseManager> result){
        baseService.updatePublic(Long.valueOf(String.valueOf(id)),ispublic);
        result.setFlag(true);
        return result;
    }

    @RequestMapping("deleteCourse/{id}")
    @ResponseBody
    public PageResult<CourseManager> deleteCourse(@PathVariable("id")Object id,PageResult<CourseManager> result){

        Integer isout = cardCourdeBasicService.isOutDate(Parse.toInt(id));
        if(isout!=0){
            result.setFlag(false);
            result.setMsg("删除失败,该课程绑定的体验卡还没有过期");
         return result;
        }
        CourseManager courseManager = courseManagerService.findById(String.valueOf(id));
        if(courseManager.getSpecialclass().equals(String.valueOf(CourseEnum.COURSE_JZ.getCode()))){
            courseManagerService.deleteByCourseId(Long.valueOf(String.valueOf(id)));
            subCourseManagerService.deleteByPid(Parse.toInt(id));
        }else{
            Date nowDate = new Date();
            int count = subCourseManagerService.getNowCounts(nowDate,Integer.valueOf(String.valueOf(id)));
            if(count>0){
                result.setFlag(false);
                result.setMsg("删除失败,在有效期内还有课程");
                return result;
            }
            courseManagerService.deleteByCourseId(Long.valueOf(String.valueOf(id)));
        }
        result.setFlag(true);
        return result;
    }

    @RequestMapping("allCourse")
    @ResponseBody
    public PageResult<CourseManager> allCourse(Sort sort,Pagination page,PageResult<CourseManager> result,CourseManager courseManager,@RequestParam(value = "conditionList",required = false) String cond){
        page.setPageSize(1000);
        courseManager = reflectionModelFormConditionMapStr(cond,sort,page);
        courseManager.setCoursestatus("0");
        courseManager.setIspublic("1");
        Integer count = baseService.findYouXiaoCount(courseManager);
        dataList = baseService.findYouXiaoCourse(courseManager);
        dicCoverList(dataList,"date@coursestartline$","date@createtime$","date@updatetime$","date@courseendline$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }

    @RequestMapping("courseForCms")
    @ResponseBody
    public Map<String,Object> courseForCms(){
        Map<String,Object> result = new HashedMap();
        List<Map<String, Object>> data = courseManagerService.getAllForSel();
        result.put("data",data);
        return result;
    }
}
