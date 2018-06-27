package com.ycjcjy.gene.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.common.dictionary.DictionaryUtils;
import net.onebean.core.ConditionMap;
import net.onebean.core.IBaseBiz;
import net.onebean.core.ListPageQuery;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import net.onebean.core.model.BaseIncrementIdModel;
import net.onebean.util.CollectionUtil;
import net.onebean.util.DateUtils;
import net.onebean.util.ReflectionUtils;
import net.onebean.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

public abstract  class TlBaseController<M extends  BaseIncrementIdModel,S extends IBaseBiz<M>> {

    /**
     *
     */
    protected List<M> dataList;
    /**
     * dao原型属性
     */
    protected S baseService;

    /**
     * 根据K泛型自动装载BaseDao
     *
     * @param baseService
     */
    @Autowired
    public final void setBaseDao(S baseService) {
        this.baseService = baseService;
    }


    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @param suffixName
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected String getView(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }

        return getViewPrefix() + suffixName;
    }

    /**
     * 获取完整
     * @return
     */
    protected String getViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            Class<M> entityClass = ReflectionUtils.findParameterizedType(getClass(), 2);
            currentViewPrefix = entityClass.getSimpleName().toLowerCase();
        }

        return currentViewPrefix;
    }


    /**
     * 给数据list包装字典
     * @param dataListParam
     * @param args
     * @see 'SF$is_lock' 参数前半段为字典的code后半段是字段名用$隔开
     */
    protected void dicCoverList(List<M> dataListParam,String...args){
        try {
            if(CollectionUtil.isNotEmpty(dataListParam)){
                dataList = dataListParam;
            }
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(dataList));
            array.stream().map(o -> {
                JSONObject temp = (JSONObject)o;
                for (String s : args) {
                    String type = s.substring(0,s.indexOf("@"));
                    String code = s.substring(s.indexOf("@")+1,s.indexOf("$"));
                    String filed = s.substring(s.indexOf("$")+1,s.length());
                    switch(type)
                    {
                        case "date":
                            if (StringUtils.isNotEmpty(filed)) {
                                temp.put(code+"_str", DateUtils.format(temp.getDate(code),filed));
                            } else {
                                temp.put(code+"_str", DateUtils.format(temp.getDate(code),DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
                            }
                            break;
                        case "dic":

                            temp.put(filed, DictionaryUtils.dic(code,temp.getString(filed)));
                            break;
                        default:
                            break;
                    }
                }
                return o;
            }).collect(Collectors.toList());
            Class clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            dataList = JSON.parseArray(JSON.toJSONString(array), ReflectionUtils.findParameterizedType(clazz,0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给数据tree包装字典
     * @param childListKey
     * @param dataListParam
     * @param args
     * @return
     */
    protected List<M> dicCoverTree(String childListKey,List<M> dataListParam,String...args){
        try {
            Class clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(dataListParam));
            JSONArray resultJsonArray = new JSONArray();
            JSONObject tempJsonObject;
            for (String s : args) {
                String type = s.substring(0,s.indexOf("@"));
                String filed = s.substring(s.indexOf("@")+1,s.indexOf("$"));
                String code = s.substring(s.indexOf("$")+1,s.length());
                for (int i = 0; i < jsonArray.size(); i++) {
                    tempJsonObject = jsonArray.getJSONObject(i);
                    switch(type)
                    {
                        case "date":
                            if (StringUtils.isNotEmpty(code)) {
                                tempJsonObject.put(filed+"_str", DateUtils.format(tempJsonObject.getDate(filed),code));
                            } else {
                                tempJsonObject.put(filed+"_str", DateUtils.format(tempJsonObject.getDate(filed),DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
                            }
                            break;
                        case "dic":

                            tempJsonObject.put(code, DictionaryUtils.dic(filed,tempJsonObject.getString(code)));
                            break;
                        default:
                            break;
                    }
                    if(CollectionUtil.isNotEmpty(tempJsonObject.getJSONArray(childListKey))){
                        JSONArray childList = tempJsonObject.getJSONArray(childListKey);
                        List<M> tempList = JSON.parseArray(JSON.toJSONString(childList), ReflectionUtils.findParameterizedType(clazz,0));
                        tempJsonObject.put(childListKey,JSONArray.parseArray(JSON.toJSONString(dicCoverTree(childListKey,tempList,args))));
                    }
                    resultJsonArray.add(tempJsonObject);
                }

            }

            dataListParam = JSON.parseArray(JSON.toJSONString(resultJsonArray), ReflectionUtils.findParameterizedType(clazz,0));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataListParam;
    }


    /**
     * 装载数据
     * @param sort
     * @param page
     * @param conditionStr
     */
    protected void initData(Sort sort, Pagination page, String conditionStr){
        ListPageQuery query = new ListPageQuery();
        ConditionMap map = new ConditionMap();
        map.parseCondition(conditionStr);
        query.setConditions(map);
        query.setPagination(page);
        query.setSort(sort);
        dataList = baseService.find(query);
    }

    /**
     * 将前台的查询参数通过反射获取class 反序列化成M model
     * @param conditionStr
     * @param sort 可为null
     * @param page 可为null
     * @return
     */
    protected M reflectionModelFormConditionMapStr(String conditionStr,Sort sort,Pagination page){
        try {
            ConditionMap map = new ConditionMap();
            map.parseCondition(conditionStr);
            Class clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            JSONObject jsonObject = new JSONObject();
            for (String key : map.keySet()) {
                String field = key.substring(0,key.indexOf("@"));
                String value = key.substring(key.indexOf("$")+1,key.length());
                if (StringUtils.isNotEmpty(value)){
                    jsonObject.put(field,value);
                }
            }
            if(null != sort){
                jsonObject.put("base_sort",sort.getSort());
                jsonObject.put("base_orderBy",sort.getOrderBy());
            }
            if(null != page){
                jsonObject.put("base_pageSize",page.getPageSize());
                jsonObject.put("base_currentPage",page.getCurrentPage());
            }
            return  JSON.parseObject(jsonObject.toJSONString(),ReflectionUtils.findParameterizedType(clazz,0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
