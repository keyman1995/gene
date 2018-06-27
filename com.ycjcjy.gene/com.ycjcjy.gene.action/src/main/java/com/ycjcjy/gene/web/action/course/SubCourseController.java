package com.ycjcjy.gene.web.action.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.VO.CourseMangeBean;
import com.ycjcjy.gene.common.CDateUtil;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CourseManager;
import com.ycjcjy.gene.model.OrderSub;
import com.ycjcjy.gene.model.SubCourseManager;
import com.ycjcjy.gene.service.CourseMakeupManagerService;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CourseManagerService;
import com.ycjcjy.gene.service.OrderSubService;
import com.ycjcjy.gene.service.SubCourseManagerService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.util.DateUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/16
 **/
@Controller
@RequestMapping("coursesub")
public class SubCourseController extends BaseController<SubCourseManager,SubCourseManagerService> {


    private static final Logger logger = LoggerFactory.getLogger(SubCourseController.class);

    private static final String WEEK_CIRCLE = "week";
    private static final String MON_CIRCLE = "month";

    @Autowired
    private CourseManagerService courseManagerService;
    @Autowired
    private CourseMakeupManagerService courseMakeupManagerService;
    @Autowired
    private OrderSubService orderSubService;

    @RequestMapping("listsubCourse")
    public String listAllSubCourse(Model model,HttpServletRequest request) throws ParseException {
        Date date = new Date();
        String id = request.getParameter("courseid");
        if(id!=null && !"".equals(id)){
            model.addAttribute("courseid",id);
        }
        String nowDate = request.getParameter("nowDate");
        if(nowDate!=null && !"".equals(nowDate)){
            date = DateUtils.parse(nowDate,"yyyy-MM-dd");
        }
        String dateFor = DateUtils.format(date,"yyyy-MM-dd");
        model.addAttribute("date",dateFor);
        return  getView("subcourse");
    }

    @RequestMapping("findAll")
    @ResponseBody
    public CourseMangeBean showSubCourse(@RequestParam(value = "paramDate")String date) throws ParseException {
        CourseMangeBean courseManageBean = new CourseMangeBean();
        JSONObject jsonObject = JSONObject.parseObject(date);
        Date paramDate = DateUtils.parse(String.valueOf(jsonObject.get("nowDate")),"yyyy-MM");
        Long couseid = null;
        if(String.valueOf(jsonObject.get("courseid"))!=null && !"".equals(String.valueOf(jsonObject.get("courseid")))){
            couseid = Long.valueOf(String.valueOf(jsonObject.get("courseid")));
        }
        courseManageBean.setSubCourseManagers(baseService.getAllSubCourses(paramDate,couseid));
        return courseManageBean;
    }

    @RequestMapping("findByCouserid")
    @ResponseBody
    public CourseManager findByCouserid(String id){
      CourseManager courseManager = courseManagerService.findByCourseId(id);
      return courseManager;
    }

    @RequestMapping("findByIsDate")
    @ResponseBody
    public PageResult<CourseManager> findByParam(PageResult<CourseManager> result,Pagination page,CourseManager courseManager) throws ParseException {
        courseManager.setBase_pageSize(5);
        if(page.getCurrentPage()!=1){
            courseManager.setBase_currentPage(page.getCurrentPage());
        }
        List<CourseManager> courses = courseManagerService.findCourses(courseManager);
        Integer count = courseManagerService.getAllCount(courseManager);
        page.setPageSize(5);
        page.init(count,page.getPageSize());
        result.setData(courses);
        result.setPagination(page);
        return result;
    }

    @RequestMapping("saveCourse")
    @ResponseBody
    public Map<String,Object> saveCourse(@RequestParam(value = "courseDetail")String courseDetail, HttpServletRequest request){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("result",false);
        JSONObject jsonObject = JSONObject.parseObject(courseDetail);
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        try {
            String weekDay = DateUtils.dayForWeek(String.valueOf(jsonObject.get("startTime")));
            String[] courseids =  String.valueOf(jsonObject.get("courseids")).split(",");
            String[] coursecounts =  String.valueOf(jsonObject.get("coursecounts")).split(",");
            String subName = String.valueOf(jsonObject.get("subName"));
            String subDeatil = String.valueOf(jsonObject.get("subDeatil"));
            //子课列表
            List<SubCourseManager> subCourseManagers = new ArrayList<SubCourseManager>();
            for(int i=0;i<courseids.length;i++){
                SubCourseManager subCourseManager = new SubCourseManager();
                   int exitCount = baseService.getSubCounts(Integer.valueOf(courseids[i]));
                   if(coursecounts[i]!=null && !"".equals(coursecounts[i])){
                       if(Integer.valueOf(coursecounts[i])<=exitCount){
                           return result;
                       }
                }
                subCourseManager.setPcourseid(Integer.valueOf(courseids[i]));
                subCourseManager.setSubDeatil(subDeatil);
                subCourseManager.setSubcousename(subName);
                subCourseManager.setCreateby(sysUser.getId());
                subCourseManager.setWeekday(weekDay);
                subCourseManager.setSubstartline(DateUtils.stringToTimeStamp(String.valueOf(jsonObject.get("startTime"))));
                subCourseManager.setSubendline(DateUtils.stringToTimeStamp(String.valueOf(jsonObject.get("endTime"))));
                subCourseManager.setCreatetime(DateUtils.dateToTimeStamp(new Date()));
                subCourseManagers.add(subCourseManager);
            }
            baseService.saveBatch(subCourseManagers);
            result.put("result",true);
            return result;
        } catch (Exception e) {
            result.put("result",false);
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    @RequestMapping("lists")
    @ResponseBody
    public PageResult<SubCourseManager> customerList (Sort sort, Pagination page, PageResult<SubCourseManager> result,SubCourseManager subCourseManager ,@RequestParam(value = "conditionList",required = false) String conditionStr){
        subCourseManager=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        result.setData(courseMakeupManagerService.findMakeupCourseList(subCourseManager));
        page.init(courseMakeupManagerService.pageCount(subCourseManager),page.getPageSize());
        result.setPagination(page);
        return result;
    }

    @RequestMapping("findSubById")
    @ResponseBody
    public Map<String,Object> findSubById(@RequestParam(value = "id")Integer id) throws ParseException {
        Map<String,Object> result = new HashMap<String, Object>();
        result = baseService.findBySubId(id);
        Date d = DateUtils.parse(String.valueOf(result.get("substartline")),"yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        String starStr = DateUtils.format(calendar.getTime(),"yyyy-MM-dd HH:mm:ss");
        result.put("starline",starStr);
        Date e = DateUtils.parse(String.valueOf(result.get("subendline")),"yyyy-MM-dd HH:mm:ss");
        Timestamp end = DateUtils.dateToTimeStamp(e);
        String endStr = DateUtils.getDateStrByTimestampNextDay(end);
        result.put("endline",endStr);
        return result;
    }

    @RequestMapping("deleteById")
    @ResponseBody
    public Map<String,Object> deleteById(@RequestParam(value = "id")Integer id){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            baseService.deleteBySubId(id);
            result.put("result",true);
            return result;
        }catch (Exception e){
            result.put("result",false);
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    @RequestMapping("editById")
    @ResponseBody
    public Map<String,Object> editById(@RequestParam (value = "subCourseManager")String subCourseManager,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("flag",false);
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        try{
            JSONObject jsonObject = JSONObject.parseObject(subCourseManager);
            Long id = Long.valueOf(String.valueOf(jsonObject.get("id")));
            String startLine = String.valueOf(jsonObject.get("substartline"));
            String endLine = String.valueOf(jsonObject.get("subendline"));
            String subDeatil = String.valueOf(jsonObject.get("subDeatil"));
            String subcousename = String.valueOf(jsonObject.get("subcousename"));
            SubCourseManager sub = new SubCourseManager();
            sub.setId(id);
            sub.setSubDeatil(subDeatil);
            sub.setSubcousename(subcousename);
            sub.setSubstartline(DateUtils.stringToTimeStamp(startLine));
            sub.setSubendline(DateUtils.stringToTimeStamp(endLine));
            sub.setCreateby(sysUser.getId());
            sub.setCreatetime(DateUtils.dateToTimeStamp(new Date()));
            baseService.updateBySub(sub);
            result.put("flag",true);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result.put("flag",false);
        }
        return result;
    }

    @RequestMapping(value = "delay/{id}")
    @Description(value = "预览列表页面")
    public String delay(Model model,@PathVariable("id")String id) {
        model.addAttribute("courseId",id);
        return getView("delaylist");
    }

    @RequestMapping(value = "delayDetail/{id}")
    @Description(value = "延时上课页面")
    public String delayDetail(Model model,@PathVariable("id")String id) {
        model.addAttribute("entity",baseService.findById(id));
        model.addAttribute("edit",true);
        return getView("delayDetail");
    }

    @RequestMapping(value = "delayThis")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<SubCourseManager> delayThis(Model model, SubCourseManager entity, PageResult<SubCourseManager> result) {
        try{
            List<OrderSub> orderSubList= orderSubService.findListBySubId(entity.getId().intValue());
            for (int i = 0;i<orderSubList.size();i++){
                orderSubList.get(i).setIs_delay(1);
                orderSubList.get(i).setDelay_start_time(entity.getSubstartline());
                orderSubList.get(i).setDelay_end_time(entity.getSubendline());
                orderSubService.update(orderSubList.get(i));
            }
            result.setMsg("推迟成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("推迟失败");
        }

        return result;
    }

    @RequestMapping("delaylist")
    @ResponseBody
    public PageResult<SubCourseManager> list (Sort sort, Pagination page,String courseId,SubCourseManager subCourseManager ,PageResult<SubCourseManager> result, @RequestParam(value = "conditionList",required = false) String conditionStr){
        subCourseManager=reflectionModelFormConditionMapStr(conditionStr,sort,page);
        subCourseManager.setPcourseid(Integer.parseInt(courseId));
        result.setData(baseService.delayList(subCourseManager));
        page.init(baseService.delayListCount(subCourseManager),page.getPageSize());
        result.setPagination(page);
        return result;
    }















}
