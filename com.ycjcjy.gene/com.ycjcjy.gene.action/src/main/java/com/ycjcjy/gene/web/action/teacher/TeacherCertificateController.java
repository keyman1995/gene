package com.ycjcjy.gene.web.action.teacher;


import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.TeacherCertificate;
import com.ycjcjy.gene.service.TeacherCertificateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacherCertificate")
public class TeacherCertificateController extends BaseController<TeacherCertificate,TeacherCertificateService> {
    
    @RequestMapping("list")
    @ResponseBody
    public PageResult<TeacherCertificate> list (Sort sort, Pagination page, PageResult<TeacherCertificate> result, @RequestParam(value = "conditionList",required = false) String cond){
        initData(sort,page,cond);
        dicCoverList(dataList,"date@create_time$");
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

}
