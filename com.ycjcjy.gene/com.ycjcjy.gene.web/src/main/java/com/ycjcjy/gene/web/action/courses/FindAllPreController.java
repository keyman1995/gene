package com.ycjcjy.gene.web.action.courses;

import com.ycjcjy.gene.model.CourseType;
import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.service.CourseTypeService;
import com.ycjcjy.gene.service.SysCaseFieldService;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("findAllPre")
public class FindAllPreController {

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private SysCaseFieldService sysCaseFieldService;

    private static final Logger logger = LoggerFactory.getLogger(FindAllPreController.class);

    @RequestMapping("/findAllPre")
    @ResponseBody
    public ResponseBean findAllPre(Integer coursetype){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        SysCaseField sysCaseField = null;

        try{
            List<CourseType> lists = courseTypeService.findByParentId(coursetype);

            List<SysCaseField> entries = sysCaseFieldService.findSysCaseFields(sysCaseField);

            List specialcourses = new ArrayList();
            List caseFields = new ArrayList();
            if(lists.size() == 0){
                responseBean.setError();
            }else{
                for(CourseType list : lists){
                    Map<String,Object> specialcourse = new HashedMap();
                    specialcourse.put("id",list.getId());
                    specialcourse.put("name",list.getName());
                    specialcourses.add(specialcourse);
                }
            }
            if(entries.size() == 0){
                responseBean.setError();
            }else{
                for(SysCaseField entry : entries){
                    Map<String,Object> caseField = new HashedMap();
                    caseField.put("caseid",entry.getId());
                    caseField.put("casefieldname",entry.getCasefieldname());
                    caseFields.add(caseField);
                }
            }
            Map<String,Object> hour1 = new HashedMap();
            Map<String,Object> hour2 = new HashedMap();
            Map<String,Object> hour3 = new HashedMap();
            Map<String,Object> hour4 = new HashedMap();
            List hours = new ArrayList();
            hour1.put("name","今天");
            hour1.put("id",1);
            hours.add(hour1);
            hour2.put("name","近两天");
            hour2.put("id",2);
            hours.add(hour2);
            hour3.put("name","本周");
            hour3.put("id",3);
            hours.add(hour3);
            hour4.put("name","本月");
            hour4.put("id",4);
            hours.add(hour4);
            data.put("hours",hours);
            data.put("specialcourses",specialcourses);
            data.put("caseFields",caseFields);
            responseBean.setSuccess(data,"请求成功");

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }

        return responseBean;
    }
}
