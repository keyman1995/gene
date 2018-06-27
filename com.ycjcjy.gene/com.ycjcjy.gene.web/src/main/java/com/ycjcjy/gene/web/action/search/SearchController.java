package com.ycjcjy.gene.web.action.search;

import com.ycjcjy.gene.common.util.CookUtils;
import com.ycjcjy.gene.model.CourseManagerVo;
import com.ycjcjy.gene.model.CourseType;
import com.ycjcjy.gene.model.HotSearchWord;
import com.ycjcjy.gene.service.CourseManagerService;
import com.ycjcjy.gene.service.CourseTypeService;
import com.ycjcjy.gene.service.HotSearchWordService;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private HotSearchWordService hotSearchWordService;

    @Autowired
    private CourseManagerService courseManagerService;

    @Autowired
    private CourseTypeService courseTypeService;

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping("/searchTags")
    @ResponseBody
    public ResponseBean searchTags(HttpServletRequest request,CourseManagerVo courseManager){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

        try{
            List<String> historyTags = CookUtils.getAll(request.getCookies(),String.valueOf(courseManager.getCoursetype()));
            data.put("historyTags",historyTags);


            List<HotSearchWord> hotTags = hotSearchWordService.findAllHotSearchWords();
            data.put("hotTags",hotTags);
            responseBean.setSuccess(data,"请求成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }


        return responseBean;
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseBean search(HttpServletRequest request, HttpServletResponse response, CourseManagerVo courseManager){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();
        try{
//            System.out.println("时间："+request.getSession().getMaxInactiveInterval());
            Cookie cookie = CookUtils.setCookie(request.getCookies(), String.valueOf(courseManager.getCoursetype()), courseManager.getCoursename());
            response.addCookie(cookie);
            responseBean.setSuccess(data,"搜索历史添加成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }
        return responseBean;
    }

    @RequestMapping("/courseTypes")
    @ResponseBody
    public ResponseBean courseTypes(){
        ResponseBean responseBean = new ResponseBean();
        Map<String,Object> data = new HashedMap();

        try{
            List<CourseType> courseTypes = courseTypeService.findByParentId(1);
            data.put("courseTypes",courseTypes);
            responseBean.setSuccess(data,"请求成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseBean.setError();
        }


        return responseBean;
    }

}
