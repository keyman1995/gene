package com.ycjcjy.gene.common.filter;

import com.alibaba.fastjson.JSON;
import net.onebean.core.BreadCrumbs;
import net.onebean.util.PropUtil;
import net.onebean.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 过滤器
 * @author 0neBean
 */
@Service
@WebFilter(filterName="onebeanFilter",urlPatterns="/*")
public class DoOnceFilter implements Filter {

    private static String[] excludedUrlUnBussniss = {"/error/browerlist"};

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;


        try {
            excludedUrlUnBussnissHandler(request,response,chain);
        } catch (IOException e) {
            return;
        }

        try {
            ieExceptionHandler(request,response);
        } catch (Exception e) {
            return;
        }

        breadCrumbsHandler(request);


        //执行操作后必须doFilter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 过滤放行的无业务URL
     * @param chain chain
     * @param request req
     * @param response resp
     * @throws IOException 抛出异常
     * @throws ServletException 抛出异常
     */
    private void excludedUrlUnBussnissHandler(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws IOException, ServletException{
        for (String s:excludedUrlUnBussniss) {
            if (request.getServletPath().contains(s)) {
                chain.doFilter(request, response);
                throw  new IOException();
            }
        }
    }


    /**
     * 不支持IE
     * @param request req
     * @param response resp
     * @throws IOException 抛出异常
     */
    private void ieExceptionHandler(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String ua = request.getHeader("User-Agent");
        if (ua.contains("Trident")){
            response.sendRedirect(PropUtil.getConfig("server.context-path")+"/error/browerlist");
            throw  new IOException();
        }
    }

    /**
     * 处理面包屑
     * @param request httpServletRequest
     */
    private void breadCrumbsHandler(HttpServletRequest request){
        if (StringUtils.isNotEmpty(request.getParameter("breadCrumbsStr"))){
            /*面包屑*/
            String breadCrumbsStr = request.getParameter("breadCrumbsStr");
            String uri = (request.getRequestURI().endsWith("/"))?request.getRequestURI():request.getRequestURI()+"/";
            Map<String,Object> isRepeat = new HashMap<>();
            List<BreadCrumbs> breadCrumbsList = JSON.parseArray(breadCrumbsStr.replace("\\",""),BreadCrumbs.class);
            List<BreadCrumbs> breadCrumbsList_result = new ArrayList<>();
            for (int i = 0; i < breadCrumbsList.size(); i++) {
                //selectedCut 为手动点击面包屑 截断点击后的面包屑
                boolean selectedCut = uri.equals(breadCrumbsList.get(i).getBreadCrumbsUrl()) && i != breadCrumbsList.size()-1;
                if(isRepeat.get(breadCrumbsList.get(i).getBreadCrumbsUrl()) != null){
                    for (BreadCrumbs bb : breadCrumbsList_result) {
                        boolean repeat = bb.getBreadCrumbsUrl().equals(breadCrumbsList.get(i).getBreadCrumbsUrl());
                        if (!repeat){
                            //如果发现重复 遍历面包屑 在重复的地方截断
                            breadCrumbsList_result = new ArrayList<>();
                            breadCrumbsList_result.add(bb);
                        }
                    }
                    break;
                }else{
                    breadCrumbsList_result.add(breadCrumbsList.get(i));
                    isRepeat.put(breadCrumbsList.get(i).getBreadCrumbsUrl(),new byte[0]);
                    if (selectedCut){
                        break;
                    }
                }
            }
            request.setAttribute("breadCrumbs",breadCrumbsList_result);
        }
    }




}
