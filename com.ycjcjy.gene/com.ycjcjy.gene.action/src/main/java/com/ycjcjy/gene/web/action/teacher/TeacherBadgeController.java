package com.ycjcjy.gene.web.action.teacher;


import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.TeacherBadge;
import com.ycjcjy.gene.service.TeacherBadgeService;
import net.onebean.core.ConditionMap;
import net.onebean.core.ListPageQuery;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacherBadge")
public class TeacherBadgeController extends BaseController<TeacherBadge,TeacherBadgeService> {

    @RequestMapping("findbyname")
    @ResponseBody
        public PageResult<TeacherBadge> findByName(@RequestParam("name")String name,Pagination page,PageResult<TeacherBadge> result){
        ListPageQuery query = new ListPageQuery();
        ConditionMap map = new ConditionMap();
        Sort sort = new Sort();
        map.parseCondition(MessageFormat.format("name@string@like${0}",name));
        sort.setSort(Sort.DESC);
        sort.setOrderBy("id");
        query.setConditions(map);
        page.setPageSize(15);
        query.setPagination(page);
        query.setSort(sort);
        result.setPagination(page);
        result.setData(baseService.find(query));
        return result;
    }
    @RequestMapping("findByTeacherId")
    @ResponseBody
    public Map<String,Object> findByTeacherId(@RequestParam("teacherId")int teacherId){

        Map<String,Object> map = new HashMap<>();
        map.put("success",0);
        try {
            List<TeacherBadge> list = baseService.selectbadgeByTeacherId(teacherId);
            map.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",1);
        }

        return map;


    }
}
