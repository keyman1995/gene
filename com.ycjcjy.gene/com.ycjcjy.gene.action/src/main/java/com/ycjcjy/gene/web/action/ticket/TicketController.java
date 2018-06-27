package com.ycjcjy.gene.web.action.ticket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CardTicketMaster;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import com.ycjcjy.gene.service.CardTicketMasterService;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.service.SysCaseFieldService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/4/10
 **/
@Controller
@RequestMapping("ticket")
public class TicketController extends BaseController<CardTicketMaster,CardTicketMasterService>{

    @Autowired
    private CardTicketMasterService cardTicketMasterService;
    @Autowired
    private SysCaseFieldService sysCaseFieldService;
    @Autowired
    private CustomerUserService customerUserService;


    /**
     * 预览列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "view")
    @Description(value = "预览列表页面")
    public String view(Model model,HttpServletRequest request) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        SysCaseField sysCaseField = sysCaseFieldService.findByFileldId(String.valueOf(user.getField_id()));
        model.addAttribute("avail",sysCaseField.getAvail());
        return getView("list");
    }



    @RequestMapping("ticketList")
    @ResponseBody
    public PageResult<CardTicketMaster> ticketList(HttpServletRequest request,Sort sort, Pagination page, PageResult<CardTicketMaster> result,CardTicketMaster cardTicketMaster, @RequestParam(value = "conditionList",required = false) String cond){
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        cardTicketMaster =  reflectionModelFormConditionMapStr(cond,sort,page);
        if(("admin").equals(user.getUser_type())){

        }else{
            //如果不是平台进来 放入案场ID
            if(!"1".equals(user.getOrg_id())){
                Long fileld = user.getField_id();
                cardTicketMaster.setOrg_id(Integer.valueOf(fileld.toString()));
            }
        }
        List<CardTicketMaster>list = baseService.findList(cardTicketMaster);
        for (int i =0; i<list.size();i++){
            if (null == list.get(i).getCardEndTime()){
                list.get(i).setFlag(true);
            }else {
                if(list.get(i).getCardEndTime().before(new Timestamp(System.currentTimeMillis()))){
                    list.get(i).setFlag(false);
                }else {
                    list.get(i).setFlag(true);
                }
            }
        }
        result.setData(list);
        Integer count  = baseService.getCount(cardTicketMaster);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        return result;
    }

    @RequestMapping("saveTicket")
    @ResponseBody
    public PageResult<CardTicketMaster> saveTicket(Model model, CardTicketMaster entity, PageResult<CardTicketMaster> result, HttpServletRequest request) {

        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        //创建人
        entity.setCreate_by(Integer.valueOf(user.getId().toString()));
        cardTicketMasterService.saveTicket(entity);
        return result;
    }

    /**
     * 策划购买
     */
    @RequestMapping("purchase")
    @ResponseBody
    public Map<String, Object> purchase(Model model, HttpServletRequest request, CardTicketMaster cardTicketMaster) {
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        String flag;
        try {
             flag =  cardTicketMasterService.purchase(cardTicketMaster,user.getField_id());
        } catch (Exception e) {
            flag =   e.getMessage();
        }
        Map<String,Object> map = new HashedMap();
        map.put("msg",flag);

        return map;
    }

    @RequestMapping("cardList")
    @ResponseBody
    public List<Map<String,Object>> cardList(HttpServletRequest request,String seachType){
        List<Map<String,Object>> list = cardTicketMasterService.cardList(seachType);

        return list;
    }



    @RequestMapping("findByTel")
    @ResponseBody
    public Map<String,Object> findByTel(String tel, HttpServletRequest request){
        Map<String,Object> map = new HashedMap();
        //判断是否为用户
        CustomerUser customerUser = customerUserService.findByTel(tel);
        if(null == customerUser){
            map.put("msg","401");
        }else{
            map.put("data",customerUser);
            map.put("msg","200");
        }
        return map;
    }

    @RequestMapping(value = "viewCard/{ticket_id}")
    @Description(value = "预览列表页面")
    public String preview(Model model,@PathVariable("ticket_id")Integer ticket_id)
    {   model.addAttribute("ticket_id",ticket_id);
        return getView("cardBind");
    }

    @RequestMapping(value = "editit")
    @Description(value = "编辑页面")
    public String edit(Model model,@RequestParam("nimabi") String nimabi) {
        JSONObject obj = JSON.parseObject(nimabi);
        CardTicketMaster entity = baseService.findById(obj.get("id"));
        if (entity.getTicket_type().equals("0")){
            entity.setType_str("课程");
        }else if(entity.getTicket_type().equals("1")){
            entity.setType_str("商品");
        }else if(entity.getTicket_type().equals("2")){
            entity.setType_str("游泳健身卡");
        }
        model.addAttribute("field",obj.get("field"));
        model.addAttribute("card",obj.get("card"));
        model.addAttribute("zs",obj.get("zs"));
        model.addAttribute("channel",obj.get("channel"));
        model.addAttribute("entity",entity);
        model.addAttribute("edit",true);
        return getView("details");
    }

    @RequestMapping("updateTicket")
    @ResponseBody
    public PageResult<CardTicketMaster> updateTicket(Model model, CardTicketMaster entity, PageResult<CardTicketMaster> result, HttpServletRequest request) {
        CardTicketMaster cardTicketMaster = cardTicketMasterService.findById(entity.getId().toString());
        cardTicketMaster.setVedio_src(entity.getVedio_src());
        cardTicketMaster.setVideo_img(entity.getVideo_img());
        cardTicketMaster.setContent(entity.getContent());
        cardTicketMaster.setChannel_script(entity.getChannel_script());
        cardTicketMasterService.update(cardTicketMaster);
        return result;
    }




}
