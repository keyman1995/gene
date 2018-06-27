package com.ycjcjy.gene.web.action.sys;

import com.ycjcjy.gene.core.TlBaseController;
import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.SysCaseFieldService;
import com.ycjcjy.gene.service.SysUserService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/syscasefield")
public class SysCaseFieldController extends TlBaseController<SysCaseField,SysCaseFieldService> {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysCaseFieldService sysCaseFieldService;



    @RequestMapping("/syscasefieldList")
    @ResponseBody
    public PageResult<SysCaseField> syscasefieldList (Sort sort, Pagination page, PageResult<SysCaseField> result,SysCaseField sysCaseField, @RequestParam(value = "conditionList",required = false) String cond,HttpServletRequest request){
//        initData(sort,page,cond);
        sysCaseField = reflectionModelFormConditionMapStr(cond,sort,page);
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        Integer count = 0;
        if(sysUser.getUser_type().equals("admin")){
            count = baseService.getAllCount(sysCaseField);
            dataList = baseService.findSysCaseFields(sysCaseField);
        }else{
            Integer id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            sysCaseField.setId(Long.parseLong(String.valueOf(id)));
            count = baseService.getAllCount(sysCaseField);
            dataList = baseService.findSysCaseFields(sysCaseField);
        }
        dicCoverList(null,"date@create_time$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }

    /**
     * 预览列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "preview0")
    @Description(value = "预览列表页面")
    public String preview0(Model model,HttpServletRequest request) {
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);

        if(sysUser.getUser_type().equals("admin")){
        }else{
            Integer case_field_id = Integer.parseInt(String.valueOf(sysUser.getField_id()));
            SysCaseField sysCaseField = sysCaseFieldService.findByFileldId(String.valueOf(case_field_id));
            String casefieldname = sysCaseField.getCasefieldname();
            model.addAttribute("casefieldname",casefieldname);
        }
        return getView("syscasefieldList");
    }

    @RequestMapping(value = "findByCaseFieldName/{casefieldname}")
    @Description(value = "按照案场名称查找")
    @ResponseBody
    public PageResult<SysCaseField> findByCaseFieldName(Model model, PageResult<SysCaseField> result,String casefieldname) {

        dataList.add(sysCaseFieldService.findByCaseFieldName(casefieldname));
        result.setData(dataList);
        return result;
    }
    @RequestMapping("/findAllCase")
    @ResponseBody
    public PageResult<SysCaseField> findAllCase(PageResult<SysCaseField> result){
        dataList = baseService.findAll();
        result.setData(dataList);
        return  result;
    }


    @RequestMapping(value = "savecasefield")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<SysCaseField> savecasefield(Model model, SysCaseField entity, PageResult<SysCaseField> result, HttpServletRequest request) {

        if (entity.getId() != null) {
            String lnglat =request.getParameter("lnglat");
            if(lnglat != null){
                String lng = lnglat.substring(0,lnglat.indexOf(","));
                String lat = lnglat.substring(lnglat.indexOf(",")+1);
                entity.setLng(lng);
                entity.setLat(lat);
                baseService.update(entity);
                result.setFlag(true);
            }else{
                result.setFlag(false);
                result.setMsg("案场在地图上的位置不明确，请在地图上抓取案场的位置！");
            }

        } else {
            SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
            String create_id = sysUser.getRealname();
            entity.setCreate_id(create_id);
            entity.setAvail(0.00);
            String lnglat =request.getParameter("lnglat");
            try{
                String lng = lnglat.substring(0,lnglat.indexOf(","));
                String lat = lnglat.substring(lnglat.indexOf(",")+1);
                entity.setLng(lng);
                entity.setLat(lat);
                baseService.save(entity);
                result.setFlag(true);
            }catch(Exception e){
                e.printStackTrace();
                result.setFlag(false);
                result.setMsg("案场在地图上的位置不明确，请在地图上抓取案场的位置！");
            }


        }
        result.getData().add(entity);
        return result;
    }

    /**
     * 删除
     * @param model
     * @return
     */
    @RequestMapping(value = "delete/{id}")
    @Description(value = "删除")
    @ResponseBody
    public PageResult<SysCaseField> delete(Model model,@PathVariable("id")Object id,PageResult<SysCaseField> result) {
        baseService.deleteById(id);

        result.setFlag(true);
        return result;
    }

    /**s
     * 编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}")
    @Description(value = "编辑页面")
    public String edit(Model model,@PathVariable("id")Object id) {
        model.addAttribute("entity",baseService.findById(id));
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
    public String add(Model model,SysCaseField entity) {
        model.addAttribute("add",true);
        model.addAttribute("entity",entity);
        return getView("detail");
    }

}
