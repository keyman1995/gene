package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.common.SmsUtil;
import com.ycjcjy.gene.dao.*;
import com.ycjcjy.gene.model.*;
import net.onebean.util.DateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.service.GoodsOrderMainService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author szcc
* @description 商品订单主表 serviceImpl
* @date 2018-06-11 18:10:40
*/
@Service
public class GoodsOrderMainServiceImpl extends BaseBiz<GoodsOrderMain, GoodsOrderMainDao> implements GoodsOrderMainService{

    @Autowired
    private GoodsOrderSubDao orderSubDao;
    @Autowired
    private CardTicketSalveDao cardTicketSalveDao;
    @Autowired
    private LocalGoodsDao localGoodsDao;
    @Autowired
    private GoodsOrderMainDao goodsOrderMainDao;
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<GoodsOrderMain> findNewGoodsOrderMain(GoodsOrderMain goodsOrderMain) {
        return baseDao.findNewGoodsOrderMain(goodsOrderMain);
    }

    @Override
    public int updateNewGoodsOrderMain(List<GoodsOrderMain> list) {
        return baseDao.updateNewGoodsOrderMain(list);
    }

    @Override
    public int updateOrder(GoodsOrderMain goodsOrderMain) {
        baseDao.updateOrder(goodsOrderMain);
        GoodsOrderSub sub = new GoodsOrderSub();
        sub.setPid(Integer.valueOf(goodsOrderMain.getId().toString()));
        return orderSubDao.updateOrder(sub);
    }

    @Override
    public int updateOrderFlag(GoodsOrderMain goodsOrderMain) {
        return baseDao.updateOrderFlag(goodsOrderMain);
    }

    @Override
    public Map<String,Object> addOrder(String json, int tableId,int caseId,int salerId,String remark) {
        Calendar calendar = Calendar.getInstance();
        int nowMonth = calendar.get(Calendar.MONTH )+1;
        Map<String,Object> map = new HashMap<>();
        map.put("success",0);
        map.put("order","");
        //是否有卡？
        int masterId = cardTicketSalveDao.getMaxMasterIdForGood(String.valueOf(caseId),String.valueOf(nowMonth));

        if(masterId==0){
            return map;
        }
        //通过,得到当月的salve
        int leftCount = cardTicketSalveDao.findLeftCountByMonth(caseId,nowMonth);

        //解析json
        JSONArray array = JSONArray.fromObject(json); //id count
        Integer[] idArr = new Integer[array.size()];
        Integer[] numArr = new Integer[array.size()];

        int salveSum = 0;

        for(int i=0;i<array.size();i++){
            idArr[i] = Integer.valueOf(array.getJSONObject(i).getString("goodId"));
            int num = Integer.valueOf(array.getJSONObject(i).getString("count"));
            salveSum += num;
            numArr[i] = num;
        }

        //对比是不是够，不够就加
        if(leftCount<salveSum){

            int addNum = salveSum - leftCount;
           if(leftCount>0){
               cardTicketSalveDao.costGoodsToSale(String.valueOf(salerId),String.valueOf(caseId),String.valueOf(leftCount),DateUtils.getNowyyyy_MM_dd_HH_mm_ss(),String.valueOf(nowMonth));
           }


            for(int i=0;i<addNum;i++){
                CardTicketSalve salve = new CardTicketSalve();
                salve.setState("4");
                salve.setTouser_time(new Date());
                salve.setPid(masterId);
                salve.setTosale_time(new Date());
                cardTicketSalveDao.add(salve);
            }

        }else{
            cardTicketSalveDao.costGoodsToSale(String.valueOf(salerId),String.valueOf(caseId),String.valueOf(salveSum),DateUtils.getNowyyyy_MM_dd_HH_mm_ss(),String.valueOf(nowMonth));
        }
        //加order和ordersub

        double sumPrice = 0d;
        List<GoodsOrderSub> subList = new ArrayList<>();


        String orderNo = createOrderNo();
        for(int i=0;i<idArr.length;i++){
            int id = idArr[i];
            int num = numArr[i];
            LocalGoods good = localGoodsDao.findById(String.valueOf(id));
            double subPrice = good.getPrice()*num;
            sumPrice+=subPrice;
            GoodsOrderSub sub = new GoodsOrderSub();

            sub.setCase_field_id(caseId);
            sub.setCreate_time(DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
            sub.setGoods_id(id);
            sub.setGoods_num(num);
            sub.setOrder_month(nowMonth);
            sub.setOrder_no(orderNo);
            sub.setOrder_state(0);
            sub.setSaler_id(salerId);
            sub.setTable_id(tableId);
            sub.setPrice(subPrice);

            subList.add(sub);
        }

        GoodsOrderMain main = new GoodsOrderMain();
        main.setOrder_no(orderNo);
        main.setOrder_state(0);
        main.setSaler_id(salerId);
        main.setTable_id(tableId);
        main.setPrice(sumPrice);
        main.setCreate_time(DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
        main.setRemark(remark);
        main.setCase_field_id(caseId);
        main.setFlag(0);
        goodsOrderMainDao.add(main);


        for(GoodsOrderSub sub:subList){
            sub.setPid(Integer.valueOf(main.getId().toString()));
            orderSubDao.add(sub);
        }


        //查询今日是第几单，已经购买了多少个商品，发送短信
        int num = goodsOrderMainDao.toDayGoodsNum(String.valueOf(caseId),main.getId().toString(),DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
        int no = goodsOrderMainDao.toDayOrderCount(String.valueOf(caseId),main.getId().toString(),DateUtils.getNowyyyy_MM_dd_HH_mm_ss())+1;

        //得到咖啡师，发短信
        List<SysUser> sysUserList = sysUserDao.findCoffeeMakerByCaseId(caseId);
        if(sysUserList!=null&&sysUserList.size()>0){
            for(SysUser u :sysUserList){
                SmsUtil.drinkOrder(u.getMobile(),no,num);
            }
        }


        GoodsOrderMain orderTui = goodsOrderMainDao.findNewGoodsOrderMainForTui(main);
        try{
            orderTui.setHaomiaoshu(String.valueOf(DateUtils.parse( orderTui.getCreate_time(),"yyyy-MM-dd HH:mm:ss").getTime()));
        }catch (Exception e){
            e.printStackTrace();
        }


        map.put("success",1);
        map.put("order",orderTui);
        return map;
    }

    public String createOrderNo(){
        SimpleDateFormat sdfNo = new SimpleDateFormat("yyyyMMdd");
        String orderNo = "";
        Date date = new Date();
        orderNo  = "g"+sdfNo.format(date)+System.currentTimeMillis();
        return orderNo;
    }
}