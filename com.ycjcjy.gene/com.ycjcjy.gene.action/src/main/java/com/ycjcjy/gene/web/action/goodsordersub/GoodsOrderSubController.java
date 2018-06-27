package com.ycjcjy.gene.web.action.goodsordersub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.VO.ExportExcelVO;
import com.ycjcjy.gene.VO.MonthOrderVO;
import com.ycjcjy.gene.common.excel.ExportExcel;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.GoodsOrderSub;
import com.ycjcjy.gene.service.GoodsOrderSubService;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
* @author szcc
* @description 商品订单子订单 controller
* @date 2018-06-11 18:11:14
*/
@Controller
@RequestMapping("goodsordersub")
public class GoodsOrderSubController extends BaseController<GoodsOrderSub,GoodsOrderSubService> {
    @RequestMapping("/orderList")
    @ResponseBody
    public PageResult<GoodsOrderSub> list (Sort sort, Pagination page, PageResult<GoodsOrderSub> result, GoodsOrderSub goodsOrderSub, @RequestParam(value = "conditionList",required = false) String cond, HttpServletRequest request){
        goodsOrderSub = reflectionModelFormConditionMapStr(cond,sort,page);
        SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
        Integer count = 0;
        if(("admin").equals(sysUser.getUser_type())){
            count = baseService.getAllCount(goodsOrderSub);
            dataList = baseService.findAllGoodsOrderSub(goodsOrderSub);
        }else{
            goodsOrderSub.setCase_field_id(Integer.parseInt(String.valueOf(sysUser.getField_id())));
            count = baseService.getAllCount(goodsOrderSub);
            dataList = baseService.findAllGoodsOrderSub(goodsOrderSub);
        }
        dicCoverList(dataList,"date@create_time$");

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
    @RequestMapping(value = "previews")
    @Description(value = "预览列表页面")
    public String previews(Model model) {
        return getView("lists");
    }

    @RequestMapping("lists")
    @ResponseBody
    public PageResult<MonthOrderVO> lists (Sort sort, Pagination page, PageResult<MonthOrderVO> result, @RequestParam(value = "conditionList",required = false) String conditionStr,HttpServletRequest request){
        SysUser user =  SpringSecurityUtil.getCurrentLoginUser(request);
        int case_id = user.getField_id().intValue();
        List<MonthOrderVO> list = baseService.getMonthlyOrder(case_id);
        for(int i=0;i<list.size();i++){
            list.get(i).setOverused(list.get(i).getUsed()-list.get(i).getTotal());
            list.get(i).setLeftnum(list.get(i).getTotal()-list.get(i).getUsed());
        }
        result.setData(list);
        //page.init(baseService.getMonthlyOrderCount(case_id),page.getPageSize());
        result.setPagination(page);
        return result;
    }

    @RequestMapping("toExcel")
    public void toExcel (@RequestParam("monthTime")String monthTime, HttpServletRequest request, HttpServletResponse response){
        try {
            JSONObject obj = JSON.parseObject(monthTime);
            String thisMonth = obj.get("months").toString();
            SysUser sysUser = SpringSecurityUtil.getCurrentLoginUser(request);
            String month = new Character(thisMonth.charAt(6)).toString();
            List<ExportExcelVO> list = baseService.exportExcelList(sysUser.getField_id().intValue(),Integer.parseInt(month));
            //设置标题
            String[] columnName = new String[]{"归属案场","桌号","商品分类","商品名称","下单人","销售数量","商品单价","商品总价","订单状态","下单时间","订单号"};
            //设置数据
            List<Object[]> data = new ArrayList<>();
            int totalPrice = 0;
            for (ExportExcelVO card : list) {
                Object[] objs = new Object[]{card.getCasefieldname(),card.getTableInfo(),card.getGoodsType(),card.getGoodsName(),card.getSalername(),card.getGoods_num(),card.getPrice(),(card.getPrice()*Integer.parseInt(card.getGoods_num())),card.getOrder_state(),card.getCreate_str(),card.getOrder_no()};
                data.add(objs);
                totalPrice+=card.getPrice()*Integer.parseInt(card.getGoods_num());
            }
            Object[] objs1 = new Object[]{"总规定次数",obj.get("total"),"实际使用总数",obj.get("used"),"总价",totalPrice};
            data.add(objs1);
            //实例化工具类
            ExportExcel ex = new ExportExcel(thisMonth+"商品点单记录", columnName, data, request, response);
            //导出excel
            ex.export();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
