package com.ycjcjy.gene.web.action.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CourseDetail;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CourseDetailService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/14
 **/
@RequestMapping("coursedetail")
@Controller
public class CourseDetailController extends BaseController<CourseDetail,CourseDetailService>{

  @RequestMapping("list")
  @ResponseBody
   public PageResult<CourseDetail> list(Sort sort,Pagination page,PageResult<CourseDetail> result, @RequestParam(value = "conditionList",required = false) String cond){

      initData(sort,page,cond);
      result.setData(dataList);
      result.setPagination(page);
      return result;
  }
  @RequestMapping("editDetail")
  @ResponseBody
  public CourseDetail editDetail(String id){
      CourseDetail courseDetail = null;
       if(id!=null && !"".equals(id)){
            courseDetail = baseService.findById(id);
       }
       return courseDetail;
  }


  @RequestMapping("batchSave")
  @ResponseBody
  public Map<String,Object> batchSave(@RequestParam(value = "value")String value, HttpServletRequest request){
      Map<String,Object> result = new HashMap<String, Object>();
      result.put("flag",true);
      result.put("msg","上传成功");
      List<CourseDetail> courseDetailList = new ArrayList<CourseDetail>();
      JSONObject jsonObject = JSON.parseObject(value);
      SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
      String courseId = jsonObject.getString("courseid");
      String sort = jsonObject.getString("sort");
      JSONArray img_url = jsonObject.getJSONArray("imgs");

          for(int i=0;i<img_url.size();i++){
              CourseDetail courseDetail = new CourseDetail();
              String img = (String)img_url.get(i);
              courseDetail.setCourseid(Integer.valueOf(courseId));
              courseDetail.setImg_url(img);
              if(sort==null || "".equals(sort)){
                  courseDetail.setSort(i);
              }else{
                  courseDetail.setSort(Integer.valueOf(sort));
              }
              courseDetail.setCreateby(sysUser.getId());
              courseDetail.setCreatetime(DateUtils.dateToTimeStamp(new Date()));
              courseDetailList.add(courseDetail);
          }
          baseService.saveBatch(courseDetailList);

      return result;
  }








}
