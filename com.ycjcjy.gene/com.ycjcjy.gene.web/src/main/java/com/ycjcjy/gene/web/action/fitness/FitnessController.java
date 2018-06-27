package com.ycjcjy.gene.web.action.fitness;

import com.ycjcjy.gene.VO.CmsEnum;
import com.ycjcjy.gene.VO.CourseEnum;
import com.ycjcjy.gene.model.*;
import com.ycjcjy.gene.service.*;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import net.onebean.core.ConditionMap;
import net.onebean.core.Pagination;
import net.onebean.core.form.Parse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: 健身课程
 * creat_user: chenjie
 * creat_time: 2018/5/8
 **/

@Controller
@RequestMapping("fitness")
public class FitnessController {

    private static final Logger logger = LoggerFactory.getLogger(FitnessController.class);

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CmsPicService cmsPicService;
    @Autowired
    private CourseManagerService courseManagerService;
    @Autowired
    private TeacherImgService teacherImgService;
    @Autowired
    private TeacherCertificateService teacherCertificateService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private DicDictionaryService dicDictionaryService;

    /**
     *
     *  健身首页
     * @author chenjie
     * @date 2018/5/10 13:41
     * @param
     * @return com.ycjcjy.gene.web.action.system.util.ResponseBean
     */
    @RequestMapping("index")
    @ResponseBody
    public ResponseBean toIndex(String name){
        ResponseBean responseBean = new ResponseBean();
        Teacher teacher = new Teacher();
        CourseManagerVo courseManager = new CourseManagerVo();
        try{
            //查询最新的健身Cms

            CmsPic cmsPic = new CmsPic();
            cmsPic.setPlace(CmsEnum.CMS_JS.getCode());
            cmsPic.setSec_place(0);
            List<CmsPic> cmsPics = cmsPicService.findPicByPlace(cmsPic);
            if(!"".equals(name)&&name!=null){
                teacher.setName(name);
            }
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("cms",cmsPics);
            teacher.setType(0);
            teacher.setCan_star(1);
            //查教练
            List<Map<String,Object>> teachers = teacherService.findByParam(teacher);
           result.put("coaches",teachers);
           courseManager.setSpecialclass(String.valueOf(CourseEnum.COURSE_TT.getCode()));
           courseManager.setIsgreate("1");
           //查找所有精品团课信息
            List<CourseManagerVo> courses = courseManagerService.getCoursesForIndex(courseManager);
            result.put("courses",courses);
           responseBean.setSuccess(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     * 教练的详情
     * @param id
     * @return
     */
    @RequestMapping("getCoachId")
    @ResponseBody
    public ResponseBean toCoachDetail(String id){
        ResponseBean responseBean = new ResponseBean();
        CourseManagerVo courseManager = new CourseManagerVo();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            Teacher teacher = teacherService.findForWeb(id);
            result.put("teacher",teacher);
            responseBean.setSuccess(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }
    /**
     *
     *  私教照片
     * @author chenjie
     * @date 2018/5/10 13:41
     * @param
     * @return com.ycjcjy.gene.web.action.system.util.ResponseBean
     */
    @RequestMapping("getCoachByType")
    @ResponseBody
    public ResponseBean toPictures(String id,Integer type){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> result = new HashMap<String, Object>();
        CourseManagerVo courseManager = new CourseManagerVo();
        try{
            if("2".equals(String.valueOf(type))){//私教相册 证书
                ConditionMap map = new ConditionMap();
                map.parseCondition("teacher_id@long@eq$"+id);
                List<TeacherImg> teacherImgs = teacherImgService.find(null,map);
                List<TeacherCertificate> teacherBadges = teacherCertificateService.find(null,map);
                result.put("imgs",teacherImgs);
                result.put("badges",teacherBadges);
            }

           if("1".equals(String.valueOf(type))){//私教课程 和 所在案场
               Teacher teacher = teacherService.findForWeb(id);
               if(teacher.getCaseids()!=null && !"".equals(teacher.getCaseids())){
                   String[] ids = teacher.getCaseids().split(",");
                   List<SysCaseField> sysCaseFields = sysCaseFieldService.findByCaseids(ids);
                   teacher.setSysCaseFields(sysCaseFields);
               }
               result.put("teacher",teacher);
               courseManager.setTeacherids(id);
               courseManager.setSpecialclass(String.valueOf(CourseEnum.COURSE_SJ.getCode()));
               List<Map<String,Object>> cousers = courseManagerService.getCoursesForWeb(courseManager);
               result.put("courses",cousers);
           }
            responseBean.setSuccess(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     *
     *  查看评价
     *  target_id 课程id 或 老师Id
     *  type 0 对课程的评价 1 对老师的评价
     * @author chenjie
     * @date 2018/5/17 23:01
     * @param [evaluate]
     * @return com.ycjcjy.gene.web.action.system.util.ResponseBean
     */
    @RequestMapping("getAllComments")
    @ResponseBody
    public ResponseBean toComments(Evaluate evaluate){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            List<Evaluate> evaluates = evaluateService.getAllForWeb(evaluate);
            result.put("evaluates",evaluates);
            Integer count = evaluateService.getAllCount(evaluate);
            result.put("total",count);
            int totalPage = responseBean.init(count,evaluate.getBase_pageSize());
            result.put("totalPages",totalPage);
            responseBean.setSuccess(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }





    /**
     *
     *私教课详情 健身课程详情
     * @author chenjie
     * @date 2018/5/10 13:42
     * @param
     * @return com.ycjcjy.gene.web.action.system.util.ResponseBean
     */
    @RequestMapping("getCourseById")
    @ResponseBody
    public ResponseBean getFitnessById(Integer id){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> result = new HashMap<String, Object>();
        CourseManagerVo courseManagerVo = null;
        try{
            courseManagerVo = courseManagerService.getCourseMangerById(Parse.toLong(id));
            result.put("courseManagerVo",courseManagerVo);
            String val = dicDictionaryService.selectVal("SERVER_TEL");//客服电话
            courseManagerVo.setServerTel(val);
            result.put("tel",val);
            responseBean.setSuccess(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }



    /**
     *
     *   获取所有的健身团体课程
     * @author chenjie
     * @date 2018/5/9 17:40
     * @param
     * @return com.ycjcjy.gene.web.action.system.util.ResponseBean
     */
    @RequestMapping("getAllFitCourse")
    @ResponseBody
    public ResponseBean getAllFitCourse(CourseManagerVo courseManagerVo){
        ResponseBean responseBean = new ResponseBean();
        try{
            courseManagerVo.setCoursetype(CourseEnum.COURSE_JS.getCode());
            Map<String,Object> result = new HashMap<String, Object>();
            courseManagerVo.setSpecialclass(String.valueOf(CourseEnum.COURSE_TT.getCode()));
            List<Map<String,Object>> maps = courseManagerService.getCoursesForWeb(courseManagerVo);
            result.put("courses",maps);
            Integer count = courseManagerService.getAllCountForWeb(courseManagerVo);
            Integer totoalCount = responseBean.init(count,courseManagerVo.getBase_pageSize());
            result.put("total",totoalCount);
            result.put("totalPage",totoalCount);
            responseBean.setSuccess(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }

    /**
     *
     *   获取所有的教练
     * @author chenjie
     * @date 2018/5/10 15:02
     * @param [teacher]
     * @return com.ycjcjy.gene.web.action.system.util.ResponseBean
     */
    @RequestMapping("getAllCoach")
    @ResponseBody
    public ResponseBean getAllCoach(Teacher teacher){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            teacher.setType(0);
            //查教练
            List<Map<String,Object>> teachers = teacherService.findByParam(teacher);
            map.put("teachers",teachers);
            //查出总条数
            Integer count = teacherService.getAllTeacherCount(teacher);
            Integer totalPage = responseBean.init(count,teacher.getBase_pageSize());
            map.put("total",count);
            map.put("totalPage",totalPage);
            responseBean.setSuccess(map);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }




}
