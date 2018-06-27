package com.ycjcjy.gene.web.action.vipCard;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ycjcjy.gene.common.excel.ExportExcel;
import com.ycjcjy.gene.common.util.NumberArithmeticUtils;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.LocalGoods;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.model.VipCard;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.service.SysUserService;
import com.ycjcjy.gene.service.VipCardService;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/vipCard")
public class VipCardController extends BaseController<VipCard,VipCardService> {

    @Autowired
    public SysUserService sysUserService;
    @Autowired
    public CustomerUserService customerUserService;

    @RequestMapping("vipCardList")
    @ResponseBody
    public PageResult<VipCard> list (VipCard vipCard,Sort sort, Pagination page, PageResult<VipCard> result, @RequestParam(value = "conditionList",required = false) String cond){
        vipCard = reflectionModelFormConditionMapStr(cond,sort,page);

        Integer count = baseService.getAllCount(vipCard);
        dataList = baseService.findAllCard(vipCard);



        dicCoverList(dataList,"dic@VIP_CARD_STATE$state","date@create_time$","date@open_time$","date@valid_time$");
        result.setData(dataList);
        page.init(count,page.getPageSize());
        result.setPagination(page);
        result.setData(dataList);
        result.setPagination(page);
        return result;
    }

    @RequestMapping("toExcel")
    public void toExcel (Sort sort, Pagination page, PageResult<VipCard> result, @RequestParam(value = "conditionList",required = false) String cond ,HttpServletRequest request,HttpServletResponse response){
        try {
            page.setCurrentPage(1);
            page.setPageSize(50000);
            VipCard vipCard = reflectionModelFormConditionMapStr(cond, sort, page);
            vipCard.setState("501");
            dataList = baseService.findAllCard(vipCard);


            dicCoverList(dataList, "dic@VIP_CARD_STATE$state", "date@create_time$", "date@open_time$", "date@valid_time$");
            result.setData(dataList);

            //设置标题

            String[] columnName = new String[]{"卡号", "价格"};
            //设置数据
            List<Object[]> data = new ArrayList<>();

            for (VipCard card : dataList) {
                Object[] objs = new Object[]{card.getCard_no(), card.getPrice()};
                data.add(objs);
            }

            //实例化工具类
            ExportExcel ex = new ExportExcel("VIPCard", columnName, data, request, response);

            //导出excel
            ex.export();
        } catch (Exception e) {
                e.printStackTrace();
        }

    }

    /**
     * 保存
     * @param model
     * @return
     */
    @RequestMapping(value = "save")
    @Description(value = "保存")
    @ResponseBody
    public PageResult<VipCard> save(Model model, VipCard entity, PageResult<VipCard> result) {
        if (entity.getId() != null) {
            baseService.update(entity);
        } else {


            int num = entity.getNum();
            int i=0;
            Timestamp ts = new Timestamp(new Date().getTime());
            List<VipCard> list = new ArrayList<>();
            while (i<num){
                i++;
                VipCard card = new VipCard();
                //卡号生成
                card.setCard_no(initCardNo());
                card.setCreate_time(ts);
                card.setPrice(entity.getPrice());
                card.setState("501");
                list.add(card);
            }
            baseService.saveBatch(list);

        }
        result.getData().add(entity);
        return result;
    }

    public static String initCardNo(){

        StringBuffer cardNo = new StringBuffer("88");
        Long Temp=Math.round(Math.random()*899+100);
        Long Temp2=Math.round(Math.random()*899+100);

        Calendar calendar = Calendar.getInstance();
        cardNo.append(Temp)
                .append("8").append(Temp2)
                .append(String.valueOf(calendar.getTime().getTime()).substring(5))
                .append(Math.round(Math.random()*89+10));


        return cardNo.toString();
    }


    @RequestMapping("/getByCardNo")
    @ResponseBody
    public Map<String,Object> getByCardNo( String cardNo){
        Map<String,Object> result = new HashMap<>();
        result.put("success",0);
        try{

            VipCard vipCard =  baseService.getByCardNo(cardNo);
            if(vipCard==null){
                result.put("success",2);
                return result;
            }
            result.put("vipCard",vipCard);
            if(!vipCard.getState().equals("501")){
                SysUser user =  sysUserService.findById(String.valueOf(vipCard.getSale_id()));
                result.put("saler",user);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",1);
        }

        return result;
    }

    @RequestMapping("/kaika")
    @ResponseBody
    public Map<String,Object> kaika( String cardNo,Integer saleId){
        Map<String,Object> result = new HashMap<>();
        VipCard vipCard =  baseService.getByCardNo(cardNo);
        result.put("success",0);
        if(vipCard.getState().equals("501")){
            SysUser saler =  sysUserService.findById(String.valueOf(saleId));
            vipCard.setState("502");
            vipCard.setSale_id(Integer.valueOf(saler.getId().toString()));
            Timestamp time = new Timestamp(new Date().getTime());
            vipCard.setOpen_time(time);
            vipCard.setCase_id(Integer.valueOf(saler.getField_id().toString()));
            baseService.update(vipCard);
        }else{
            result.put("success",1);
            result.put("msg","卡已经被开卡");
        }
        return result;
    }

    @RequestMapping("/jihuo")
    @ResponseBody
    public Map<String,Object> jihuo( String cardNo,Integer userId){
        Map<String,Object> result = new HashMap<>();
        VipCard vipCard =  baseService.getByCardNo(cardNo);
        result.put("success",0);
        if(vipCard.getState().equals("502")){
            SysUser saler =  sysUserService.findById(String.valueOf(vipCard.getSale_id()));
            CustomerUser user =  customerUserService.findById(String.valueOf(userId));
            user.setGift_balance(user.getGift_balance()+vipCard.getPrice());
            customerUserService.update(user);
            vipCard.setState("503");
            vipCard.setUser_id(Integer.valueOf(user.getId().toString()));
            Timestamp time = new Timestamp(new Date().getTime());
            vipCard.setValid_time(time);
            baseService.update(vipCard);

        }else{
            result.put("success",1);
            result.put("msg","卡已经被激活");
        }
        return result;
    }

}
