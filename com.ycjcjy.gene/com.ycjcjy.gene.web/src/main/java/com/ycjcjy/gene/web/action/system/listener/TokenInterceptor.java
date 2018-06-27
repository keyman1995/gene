package com.ycjcjy.gene.web.action.system.listener;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

/**
 * @version V1.0
 * @class_name: TokenInterceptor
 * @param:
 * @describe: 重复提交机制拦截器（目前不支持多标签打开的页面重复提交）
 * @creat_user: wf
 * @creat_time: 2017/8/2 13:38
 **/
     public class TokenInterceptor extends HandlerInterceptorAdapter {
        private static final Logger LOG = Logger.getLogger(RepeatToken.class);
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (handler instanceof HandlerMethod) {

                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Headers","X-Requested-With, Content-Type, Authorization");
                response.setHeader("Access-Control-Allow-Methods","GET, POST");

                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                RepeatToken annotation = method.getAnnotation(RepeatToken.class);
                if (annotation != null) {
                    boolean needSaveSession = annotation.apply();
                    if (needSaveSession) {

                        String uuid= UUID.randomUUID().toString();

                        request.getSession(true).setAttribute("token", uuid.replaceAll("-", ""));
                    }
                    boolean needRemoveSession = annotation.valid();
                    if (needRemoveSession) {
                        if (isRepeatSubmit(request)) {
                            /*LOG.info("please don't repeat submit,url:"+ request.getServletPath());
                            //servletResponse.sendRedirect(servletRequest.getContextPath() + "/ulogin.htm");

                            request.getRequestDispatcher("/system/order/toRepeatWarn").forward(request, response);//转发
                            return false;*/


                            String requestType = request.getHeader("X-Requested-With");
                            if(StringUtils.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")){
                                response.setHeader("sessionstatus", "timeout");
                                response.sendError(518, "session timeout.");

                            }else {
                                // 跳转到提示页面
                                request.getRequestDispatcher("/system/order/toRepeatWarn").forward(request, response);
                                return false;
                            }

                        }
                        request.getSession(true).removeAttribute("token");
                    }
                }
                return true;
            } else {
                return super.preHandle(request, response, handler);
            }
        }

        private boolean isRepeatSubmit(HttpServletRequest request) {
            String serverToken = (String) request.getSession(true).getAttribute("token");
            if (serverToken == null) {
                return true;
            }
            String clientToken = request.getParameter("token");
            if (clientToken == null) {
                return true;
            }
            if (!serverToken.equals(clientToken)) {
                return true;
            }
            return false;
        }

    private void writeMessageUtf8(HttpServletResponse response, Map<String , Object> json) throws IOException
    {
        try
        {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSON.toJSONString(json));
        }
        finally
        {
            response.getWriter().close();
        }
    }


}
