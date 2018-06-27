package com.ycjcjy.order;

import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.dao.GoodsOrderMainDao;
import com.ycjcjy.gene.model.GoodsOrderMain;
import com.ycjcjy.gene.service.GoodsOrderMainService;
import com.ycjcjy.spring.GeneMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by szc on 2018/6/11.
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(GoodsOrderMainController.class)
@SpringBootTest(classes =GeneMain.class)
@Transactional
public class GoodsOrderMainTest {
    @Autowired
    private GoodsOrderMainDao goodsOrderMainDao;


    @Test
    public void testMve(){

        try {
            GoodsOrderMain main = new GoodsOrderMain();
            main.setId(Long.valueOf(45));
            GoodsOrderMain orderTui = goodsOrderMainDao.findNewGoodsOrderMainForTui(main);
            JSONObject json = new JSONObject();
            JSONObject json2 = new JSONObject();
            json.put("order",orderTui);
            json2.put("code",1);
            json2.put("mmm",orderTui);
            System.out.println("----------------json:"+json2.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
