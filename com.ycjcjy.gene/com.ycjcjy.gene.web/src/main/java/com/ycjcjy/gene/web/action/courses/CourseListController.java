package com.ycjcjy.gene.web.action.courses;

import com.ycjcjy.gene.common.CDateUtil;
import com.ycjcjy.gene.model.CourseManagerVo;
import com.ycjcjy.gene.model.SubCourseManager;
import com.ycjcjy.gene.service.CourseManagerService;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("courseList")
public class CourseListController {

    @Autowired
    private CourseManagerService courseManagerService;

    private static final Logger logger = LoggerFactory.getLogger(CourseListController.class);

    /**
     *课程列表页
     * @param courseManager
     *精选 查看更多时 specialcourse不传， 其他 查看更多时 specialcourse必传，列表页筛选条件看具体情况
     * 还得传base_currentPage 当前页 和base_pageSize 每页的数据条数
     * @return
     */
    @RequestMapping("/courseList")
    @ResponseBody
    public ResponseBean courseList(CourseManagerVo courseManager){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

        if(("1").equals(courseManager.getTimeRange())){
            courseManager.setTimeStart(new Date());
            courseManager.setTimeEnd(CDateUtil.getDayEnd());
        }else if(("2").equals(courseManager.getTimeRange())){
            courseManager.setTimeStart(new Date());
            courseManager.setTimeEnd(CDateUtil.getEndDayOfTomorrow());
        }else if(("3").equals(courseManager.getTimeRange())){
            courseManager.setTimeStart(new Date());
            courseManager.setTimeEnd(CDateUtil.getEndDayOfWeek());
        }else{
            courseManager.setTimeStart(new Date());
            courseManager.setTimeEnd(CDateUtil.getEndDayOfMonth());
        }

        try{
            List<Map<String,Object>> courses = courseManagerService.getCoursesForWeb(courseManager);
            Integer total = courseManagerService.getAllCountForWeb(courseManager);
            Integer totalPage = responseBean.init(total,courseManager.getBase_pageSize());
            data.put("totalPage",totalPage);
            data.put("total",total);

                data.put("courses",courses);
                responseBean.setSuccess(data,"请求成功");
//                for(CourseManager course : courses){
//                    Map<String,Object> content = new HashedMap();
//
//                    content.put("courseid",course.getId());
//                    content.put("pic",course.getImg_url());
//                    content.put("title",course.getCoursename());
//                    content.put("address","南京 "+course.getCasename()+"门店");
//                    content.put("type",DictionaryUtils.dic("SPECIAL_COURSE",course.getSpecialclass()));
//                    SimpleDateFormat format0 = new SimpleDateFormat("yyyy.MM.dd");
//                    SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
//                    if(course.getMinsubstartline() == null){
//                        String hour = format0.format(course.getCoursestartline())+"~"+format0.format(course.getCourseendline());
//                        content.put("time",hour);
//                    }else{
//                        String hour = format0.format(course.getMinsubstartline())+" "+course.getWeekday().replace("星期","周")+" "+format1.format(course.getMinsubstartline())+"-"+format1.format(course.getSubendline());
//                        content.put("time",hour);
//                    }
//                    if(course.getCourseprice() == null || course.getCourseprice() == 0){
//                        content.put("price","免费体验");
//                    }else{
//                        content.put("price",course.getCourseprice()+"元/人");
//                    }
//                    substance.add(content);
//
//                    responseBean.setSuccess(substance,"请求成功");
//                }



        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }

        return responseBean;
    }

    /**
     *课程详情页
     * @param courseid 选中课程的ID
     * @return 当hassubmanager为true时，需要查看返回数据中的subCourseTimeList结果集，为false时，则不需要
     */
    @RequestMapping("/courseDetail")
    @ResponseBody
    public ResponseBean courseDetail(Integer courseid){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

        SimpleDateFormat format0 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat format2 = new SimpleDateFormat("MM/dd");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try{
            CourseManagerVo course = courseManagerService.getCourseMangerById(Long.parseLong(String.valueOf(courseid)));
           course.setNowDate(new Date());
            if (course == null) {
                responseBean.setError();
            } else {
                    data.put("course",course);
//                    if (course == null) {
//                        responseBean.setError();
//                    } else {
//                        data.put("headimg", course.getImg_url());
//                        data.put("coursename", course.getCoursename());
//                        data.put("price", course.getCourseprice() + "元/课时");
//                        data.put("buycount", course.getBuycount() + "人已购买");
//                        data.put("time", format0.format(course.getCoursestartline()) + "--" + format0.format(course.getCourseendline()));
//                        data.put("icon", course.getIcon());
//                        data.put("lng", course.getLng());
//                        data.put("lat", course.getLat());
//                        data.put("casefieldname", course.getCasefieldname());
//                        data.put("address", course.getAddress());
//                        data.put("details", course.getCourseDetailList());
//                        data.put("teachername",course.getTeachername());
//                        data.put("teacherimg",course.getTeacherimg());
//                        data.put("teacherexperience",course.getTeacherexperience());
//                        responseBean.setSuccess(data, "请求成功");
//                    }

//                    if (course == null) {
//                        responseBean.setError();
//                    } else {
//                        data.put("headimg", course.getImg_url());
//                        data.put("coursename", course.getCoursename());
//                        data.put("price", course.getCourseprice() + "元/课时");
//                        data.put("buycount", course.getBuycount() + "人已购买");
//                        data.put("icon", course.getIcon());
//                        data.put("lng", course.getLng());
//                        data.put("lat", course.getLat());
//                        data.put("casefieldname", course.getCasefieldname());
//                        data.put("address", course.getAddress());
//                        data.put("details", course.getCourseDetailList());
                        List<SubCourseManager> subCourseManagers = course.getSubCourseTimeList();
                        SubCourseManager[] subCourseManagers1 = (SubCourseManager[])subCourseManagers.toArray(new SubCourseManager[0]);
                        List timeList = new ArrayList();
                        String titleTime = "";
                        String titleTime1 = "";
                        String titleTime2 = "";
                        if(subCourseManagers.size()!= 0){
                            for(int i =0;i<subCourseManagers1.length;i++){

                                timeList.add(format2.format(format3.parse(String.valueOf(subCourseManagers1[i].getSubstartline())))+" "+subCourseManagers1[i].getWeekday().replace("星期","周")+" "+format1.format(format3.parse(String.valueOf(subCourseManagers1[i].getSubstartline())))+"~"+format1.format(format3.parse(String.valueOf(subCourseManagers1[i].getSubendline()))));

                            }
                        }

                        data.put("timeList",timeList);
                        data.put("titleTime",format0.format(course.getCoursestartline())+"~"+format0.format(course.getCourseendline()));
//                        responseBean.setSuccess(data, "请求成功");
//                    }
                    responseBean.setSuccess(data,"请求成功");
                }



        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }



        return responseBean;
    }





}
