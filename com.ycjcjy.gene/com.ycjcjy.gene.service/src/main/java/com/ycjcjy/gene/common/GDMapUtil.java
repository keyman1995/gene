package com.ycjcjy.gene.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 高德地图api类
 */
public class GDMapUtil {

    static String ak = "b104a2a68204f7c375520a0c9345345d";
    static String encoding = "UTF-8";

    /**
     * 根据搜索名，城市名，得到经纬度
     * @param name
     * @param cityName
     * @return
     */
    public static Map<String,String> getLocationByName(String name,String cityName){
        Map<String,String> map = new HashMap<>();
        String url = "";
        try{
            url = "http://restapi.amap.com/v3/place/text?keywords="+name+"&city="+cityName+"&key="+ak;
        }catch (Exception e){
            e.printStackTrace();
        }

        String sr=HttpClient4Util.getResponseAsMap4Get(url,encoding);
        JSONObject json = JSONObject.parseObject(sr);
        if(json.getInteger("status")==1){
            JSONArray list = json.getJSONArray("pois");
            JSONObject item = list.getJSONObject(0);

            String location = item.getString("location");
            String lng = location.split(",")[0];
            String lat = location.split(",")[1];
            map.put("lng",lng);
            map.put("lat",lat);
        }

        return map;
    }




    public static void main(String[] args) {
        //获取小区经纬度,传入小区名，城市名
        Map<String,String> map = GDMapUtil.getLocationByName("楚翘城","南京");


        System.out.println();
        System.out.println();
        System.out.println();


    }



}
