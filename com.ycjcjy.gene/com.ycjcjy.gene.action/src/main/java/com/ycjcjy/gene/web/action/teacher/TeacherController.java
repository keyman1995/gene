package com.ycjcjy.gene.web.action.teacher;


import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.model.TeacherBadge;
import com.ycjcjy.gene.service.SysCaseFieldService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.Teacher;
import com.ycjcjy.gene.service.TeacherService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController<Teacher,TeacherService> {

    @Autowired
    public SysCaseFieldService sysCaseFieldService;

    @RequestMapping("teacherList")
    @ResponseBody
    public PageResult<Teacher> list (Sort sort, Pagination page, PageResult<Teacher> result, Teacher teacher,@RequestParam(value = "conditionList",required = false) String cond){

        teacher = reflectionModelFormConditionMapStr(cond,sort,page);
        Integer count = baseService.getAllCount(teacher);
        dataList = baseService.findAllTeacher(teacher);
        dicCoverList(dataList,"date@create_time$");

        List<SysCaseField> fieldList = sysCaseFieldService.findAll();

        Map<Long,String> map = new HashMap<>();

        for(SysCaseField s:fieldList){
            map.put(s.getId(),s.getCasefieldname());
        }


        for(int i=0;i<dataList.size();i++){
                 Teacher t = dataList.get(i);

                String caseList = t.getCaseids();
                String caseName = "";
                if(StringUtils.isNotBlank(caseList)){
                    String[] arr = caseList.split(",");
                    for(String cid:arr){
                        caseName += map.get(Long.valueOf(cid))+",";
                    }
                }

                if(StringUtils.isNotBlank(caseName)){
                    caseName = caseName.substring(0,caseName.length()-1);
                }

            dataList.get(i).setCaseStr(caseName);

        }

        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    /**
     * 查看页面
     * @param model
     * @return
     */
    @RequestMapping(value = "view/{id}")
    @Description(value = "查看页面")
    public String view(Model model, @PathVariable("id")Object id){
        model.addAttribute("entity",baseService.findById(id));

        List<SysCaseField> caseFields = sysCaseFieldService.findAll();
        model.addAttribute("caseFields",caseFields);

        model.addAttribute("view",true);
        return getView("detail");
    }


    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {        sb.append(list.get(i)).append(separator);    }
        return sb.toString().substring(0,sb.toString().length()-1);
    }


    /**
     * 编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    public String edit(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));

        List<SysCaseField> caseFields = sysCaseFieldService.findAll();
        model.addAttribute("caseFields",caseFields);


        model.addAttribute("edit",true);
        return getView("detail");
    }

    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "add")
    @Description(value = "新增页面")
    public String add(Model model,Teacher entity) {
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
        List<SysCaseField> caseFields = sysCaseFieldService.findAll();
        model.addAttribute("caseFields",caseFields);

        return getView("detail");
    }


}
