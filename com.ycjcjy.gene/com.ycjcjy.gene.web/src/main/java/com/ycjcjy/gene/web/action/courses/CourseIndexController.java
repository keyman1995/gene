package com.ycjcjy.gene.web.action.courses;

import com.ycjcjy.gene.VO.CmsEnum;
import com.ycjcjy.gene.VO.CourseEnum;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.*;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

@Controller
@RequestMapping("courseIndex")
public class CourseIndexController {

    @Autowired
    private CmsPicService cmsPicService;

    @Autowired
    private CourseManagerService courseManagerService;

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ServerManagerService serverManagerService;

    @Autowired
    private InformationService informationService;

    private static final Logger logger = LoggerFactory.getLogger(CourseIndexController.class);

    @RequestMapping("toIndex")
    @ResponseBody
    public ResponseBean toIndex(){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            List<CourseType> courseTypes = courseTypeService.findByParentId(CourseEnum.COURSE_GX.getCode());//共享教室
            CourseManagerVo courseManagerVo = new CourseManagerVo();

            result.put("courseType",courseTypes);
            CmsPic  cmsPic = new CmsPic();
            cmsPic.setPlace(CmsEnum.CMS_INDEX.getCode());
            cmsPic.setStatus("0");
            cmsPic.setSec_place(0);
            List<CmsPic> banner = cmsPicService.findPicByPlace(cmsPic);
            result.put("banner",banner);
            cmsPic.setSec_place(1);
            List<CmsPic> entry = cmsPicService.findPicByPlace(cmsPic);
            result.put("entry",entry);
            List<ServerManager> serverManagers = serverManagerService.getAllManager();
            result.put("servers",serverManagers);
         /*   courseManagerVo.setIsgreate("1");
            courseManagerVo.setCoursetype(CourseEnum.COURSE_GX.getCode());
            List<CourseManagerVo> courseManger = courseManagerService.getCoursesForIndex(courseManagerVo);
            result.put("courseManges",courseManger);*/
            List<Information> information = informationService.findInforByTime();
            result.put("information",information);
            responseBean.setSuccess(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 课程首页上的选项卡
     * @return 返回的是选项卡及其ID，精选可写死
     */
    @RequestMapping("/headlineIndex")
    @ResponseBody
    public ResponseBean headlineIndex(){
        ResponseBean responseBean = new ResponseBean();
        List data = new ArrayList();

        try{
            List<CourseType> lists = courseTypeService.findByParentId(2);
//            Map<String,Object> headline = new HashedMap();
            if(lists.size() == 0){
                responseBean.setError();
            }else{
                for(CourseType list : lists){
                    Map<String,Object> tabs = new HashedMap();
                    tabs.put("id",list.getId());
                    tabs.put("name",list.getName());
                    tabs.put("type",list.getCode());
                    data.add(tabs);
                }
            }
            if(data.size() == 0){
                responseBean.setError();
            }else{
                responseBean.setSuccess(data,"请求成功");
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }

        return responseBean;
    }


    /**
     * 课程首页的内容
     * @param cmsPic
     * 访问课程首页时传place=2002，当访问到其他tab时，place为该tab的ID
     * @return banner 页面上部的轮播图   entry 页面下部的图片列表
     */
    @RequestMapping("/courseIndex")
    @ResponseBody
    public ResponseBean courseIndex(CmsPic cmsPic){
        ResponseBean responseBean = new ResponseBean();
        CourseManagerVo courseManager = new CourseManagerVo();
        Map<String,Object> data = new HashedMap();
        Map<String,Object> selection1= new HashedMap();
        Map<String,Object> selection2 = new HashedMap();

        try{
            cmsPic.setSec_place(0);
            List<CmsPic> banner = cmsPicService.findPicByPlace(cmsPic);
            cmsPic.setSec_place(1);
            List<CmsPic>entry = cmsPicService.findPicByPlace(cmsPic);

            courseManager.setCoursetype(2);



            if(cmsPic.getPlace() != 2002){
                courseManager.setSpecialcourse(cmsPic.getPlace());
                CourseType courseType = courseTypeService.findById(String.valueOf(cmsPic.getPlace()));
                List<CourseManagerVo> courses = courseManagerService.getCoursesForIndex(courseManager);

                        selection1.put("title",courseType.getType());
                        selection1.put("banner",banner);
                        selection1.put("courses",courses);
                        selection2.put("title",courseType.getNewstype());
                        selection2.put("entry",entry);

//                        for(Map<String,Object> course : courses){
//                            Map<String,Object> content = new HashedMap();
//                            if(place.equals(course.get("specialclass"))){
//                                content.put("courseid",course.get("id"));
//                                content.put("pic",course.get("img_url"));
//                                content.put("title",course.get("coursename"));
//                                content.put("address","南京 "+course.get("casefieldname")+"门店");
//                                content.put("tags",course.get("coursetags"));
//                                SimpleDateFormat format0 = new SimpleDateFormat("yyyy.MM.dd");
//                                SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
//
//                                String time = format0.format(course.get("coursestartline"))+" "+String.valueOf(course.get("weekday")).replace("星期","周")+" "+String.valueOf(course.get("coursetimeintro"));
//                                content.put("time",time);
//
//                                if(course.get("courseprice") == null || Double.parseDouble(String.valueOf(course.get("courseprice"))) == 0){
//                                    content.put("price","免费体验");
//                                }else{
//                                    content.put("price",course.get("courseprice")+"元/课时");
//                                }
//                                substance.add(content);
//                            }
//                        }




            }else{
                courseManager.setIsgreate("1");
                List<CourseManagerVo> courses = courseManagerService.getCoursesForIndex(courseManager);

                    selection1.put("title","精选课程");
                    selection1.put("banner",banner);
                    selection1.put("courses",courses);
                    selection2.put("title","最新资讯");
                    selection2.put("entry",entry);
//                    for(Map<String,Object> course : courses){
//                        Map<String,Object> content = new HashedMap();
//
//                        content.put("courseid",course.get("id"));
//                        content.put("pic",course.get("img_url"));
//                        content.put("title",course.get("coursename"));
//                        content.put("address","南京 "+course.get("casefieldname")+"门店");
//                        content.put("type",DictionaryUtils.dic("SPECIAL_COURSE",String.valueOf(course.get("specialclass"))));
//                        SimpleDateFormat format0 = new SimpleDateFormat("yyyy.MM.dd");
//                        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
//
//                        String time = format0.format(course.get("coursestartline"))+" "+String.valueOf(course.get("weekday")).replace("星期","周")+" "+String.valueOf(course.get("coursetimeintro"));
//                        content.put("time",time);
//
//                        if(course.get("courseprice") == null || Double.parseDouble(String.valueOf(course.get("courseprice"))) == 0){
//                            content.put("price","免费体验");
//                        }else{
//                            content.put("price",course.get("courseprice")+"元/课时");
//                        }
//                            substance.add(content);
//                    }

            }
            data.put("selection1",selection1);
            data.put("selection2",selection2);
            responseBean.setSuccess(data,"请求成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }



        return responseBean;
    }


}
