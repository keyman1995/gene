package com.ycjcjy.gene.web.action.teacher;


import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.Teacher;
import com.ycjcjy.gene.model.TeacherImg;
import com.ycjcjy.gene.service.TeacherImgService;
import com.ycjcjy.gene.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacherImg")
public class TeacherImgController extends BaseController<TeacherImg,TeacherImgService> {

    @RequestMapping("list")
    @ResponseBody
    public PageResult<TeacherImg> list (Sort sort, Pagination page, PageResult<TeacherImg> result, @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        dicCoverList(null,"date@create_time$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }




}
