package com.ycjcjy.gene.web.action.system.listener;


import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.model.Teacher;
import com.ycjcjy.gene.service.CustomerUserService;
import com.ycjcjy.gene.service.SysUserService;
import com.ycjcjy.gene.service.TeacherService;
import com.ycjcjy.gene.web.action.system.util.AppConfig;
import com.ycjcjy.gene.web.action.system.util.BaseConstans;
import com.ycjcjy.gene.web.action.system.util.EmojiUtils;
import net.onebean.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 功能：微信open_id过滤器
 * @author Administrator
 */
@Service
@WebFilter(filterName="baseSessionFilter",urlPatterns="/*")
public class WxSessionFilter implements Filter
{
    private static final Log log = LogFactory.getLog(WxSessionFilter.class);

    private CustomerUserService userService;
    private TeacherService teacherService;
    private SysUserService sysUserService;


    private WeixinAuthApiUtils weixinAuthApiUtils;

    private static String excludedUrlUnBussniss []= {"/fitness","/websocket","/courseIndex","/search","/courseList","/findAllPre","/search"};//过滤放行的无业务URL
    private static String staticUrl []= {".txt",".css",".js",".png","ico"};
    private static String excludedUrlBussniss []= {};

    private static Boolean is_test = new Boolean( AppConfig.getValue("is_test"));
    private static String test_openid = AppConfig.getValue("test_openid");



    private static WebApplicationContext webApplicationContext;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {





        HttpServletRequest hrequest = (HttpServletRequest)request;
        HttpServletResponse hresponse = (HttpServletResponse)response;

        Cookie cookie = new Cookie(cookieName, "o5EGQ0-mvLQ_p6s9sNGFzdTgSmKM");
        cookie.setMaxAge(0);// 设置为30min
        cookie.setPath("/");
        System.out.println("已删除===============");
        hresponse.addCookie(cookie);

        //过滤放行的无业务URL
        for (String s:excludedUrlUnBussniss) {
            if (hrequest.getServletPath().contains(s)) {
                chain.doFilter(hrequest, hresponse);
                return;
            }
        }
        //放行静态资源
        for (String s:staticUrl) {
			if (hrequest.getServletPath().endsWith(s)) {
				chain.doFilter(hrequest, hresponse);
				return;
			}
		}
        
        if (null == webApplicationContext)
        {
            webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(hrequest.getSession().getServletContext());
        }

        userService = (CustomerUserService) webApplicationContext.getBean("customerUserServiceImpl");
        teacherService = (TeacherService) webApplicationContext.getBean("teacherServiceImpl");
        sysUserService = (SysUserService) webApplicationContext.getBean("sysUserServiceImpl");

        //防止init方法初始化失败 ,对微信工具类防空初始化
        weixinAuthApiUtils = (null == weixinAuthApiUtils)?weixinAuthApiUtils.getInstance():weixinAuthApiUtils;


        String url = hrequest.getServletPath();

        log.info("___________----进入过滤器--------____________");



            String openId=null;
            String access_token;
            if (is_test) {//从session中直接获取openid
                openId = test_openid;
            }else {

                Object o = hrequest.getSession().getAttribute(BaseConstans.SESSION_OPENID.getValue());

                if(o==null){
                    Cookie cok = getCookie(hrequest,cookieName);
                    log.info("-----------------getCookie object---------"+cok);
                    if(cok!=null){
                        o = cok.getValue();
                        log.info("-----------------yuanlai jiu you ooo---------"+o);
                        if(o!=null){
                            hrequest.getSession().setAttribute(BaseConstans.SESSION_OPENID.getValue(),o.toString());
                            openId=o.toString();
                        }
                    }

                }else{
                    openId=o.toString();
                }




                log.info("___________----11111--------____________");
                if( openId== null) {
                    log.info("___________----2222--------____________");




                    if (StringUtils.isBlank(hrequest.getParameter("wxcode"))) {//从session中直接获取code
                        log.info("___________----code missing--------____________");

                        String requestType = hrequest.getHeader("X-Requested-With");

                        //if (StringUtils.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")) {

                            hresponse.setCharacterEncoding("UTF-8");
                            hresponse.setContentType("application/json; charset=utf-8");
                            PrintWriter out = null;
                            try {
                               /* JSONObject res = new JSONObject();
                                res.put("systemLocked","true");
                                out = response.getWriter();
                                out.append(res.toString());*/

                                log.info("___________----hresponse.sendError(401)--------____________");
                                hresponse.setStatus(401);

                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                hresponse.setStatus(401);
                                return;
                            }

                        //}



                    }else{
                        //获取授权信息，取open_id
                        log.info("-----------------获取授权信息1---------");

//                        OauthAPI api = weixinAuthApiUtils.getOauthAPI();//.getToken(hrequest.getParameter("wxcode"));
//                        api.getOauthPageUrl("", OauthScope.SNSAPI_USERINFO,"ellt")
                        OauthGetTokenResponse codeResponse = weixinAuthApiUtils.getOauthAPI().getToken(hrequest.getParameter("wxcode"));
                        openId=codeResponse.getOpenid();
                        access_token = codeResponse.getRefreshToken();
                        log.info("-----------------获取到openId111---------"+openId);
                        log.info("-----------------获取到access_token111---------"+access_token);
                    }
                }else{
                    openId = hrequest.getSession().getAttribute(BaseConstans.SESSION_OPENID.getValue()).toString();
                }
            }


            //查用户，再放进去

//        Object o = hrequest.getSession().getAttribute(BaseConstans.SESSION_OPENID.getValue());
//        if(o!=null){
//            log.info("-----------------yuanlai jiu you ooo---------");
//            chain.doFilter(hrequest, hresponse);
//            return;
//        }




                CustomerUser wxUser = new CustomerUser();
                CustomerUser wu = userService.getUserByOpenid(openId);
                log.info("-----------------我去查用户了---------"+openId );

                CustomerUser wxUserInfo;
                if(wu==null) {
                    log.info("-----------------我进来了，我的wu没有值---------" );
                    //获取用户信息
                    wxUser.setOpen_id(openId);
                    wxUser.setAllBalance(0d);
                    wxUser.setActual_balance(0d);
                    wxUser.setGift_balance(0d);
                    wxUser.setUser_type(4);
                    log.info("-----------------获取到openId222---------" + openId);


                    UserAPI ua = new UserAPI(weixinAuthApiUtils.getConfigInstance());
                    log.info("-----------------ua---------" + ua.toString());
                    GetUserInfoResponse gi = ua.getUserInfo(openId);

                    if (null != gi) {
                        wxUser.setIcon(gi.getHeadimgurl());
                        wxUser.setUsername(EmojiUtils.filterName(gi.getNickname()));
                        wxUser.setWc_nickname(EmojiUtils.filterName(gi.getNickname()));
                    }

                    CustomerUser wu222 = userService.getUserByOpenid(openId);
                    if(wu222==null) {
                        userService.save(wxUser);
                    }
                    wxUserInfo = userService.getUserByOpenid(openId);
                }else {

                    if(StringUtils.isBlank(wu.getIcon())){
                        log.info("-----------------有记录但是没有头像昵称---------" );
                        UserAPI ua = new UserAPI(weixinAuthApiUtils.getConfigInstance());
                        log.info("-----------------ua---------" + ua.toString());
                        GetUserInfoResponse gi = ua.getUserInfo(openId);
                        if (null != gi) {
                            wu.setIcon(gi.getHeadimgurl());
                            //wu.setUsername(EmojiUtils.filterName(gi.getNickname()));
                            wu.setWc_nickname(EmojiUtils.filterName(gi.getNickname()));
                        }
                        userService.update(wu);


                    }

                    wxUserInfo = wu;
                }


                //根据openid得到用户身份
                Teacher teacher = teacherService.findByOpenId(openId);
                SysUser sysUser = sysUserService.findByOpenId(openId);

                //将当前用户信息放入到session当中去
                hrequest.getSession().setAttribute(BaseConstans.SESSION_WXUSER.getValue(), wxUserInfo);
                hrequest.getSession().setAttribute(BaseConstans.SESSION_SYSUSER.getValue(), sysUser);
                hrequest.getSession().setAttribute(BaseConstans.SESSION_TEACHER.getValue(), teacher);

                log.info("___________Oauth2获取到当前用户,放入session___________________");

                //将当前用户的open_id放入到session当中去
                hrequest.getSession().setAttribute(BaseConstans.SESSION_OPENID.getValue(), openId);

                log.info("___________Oauth2获取到当前用户 openid,放入session : "
                        + hrequest.getSession().getAttribute(BaseConstans.SESSION_OPENID.getValue()));


                addCookie(hresponse,cookieName,openId);

                log.info("-----------------拦截器结束了---------" );


            
            //过滤放行的业务URL
           /* for (String s:excludedUrlBussniss) {
    			if (hrequest.getServletPath().contains(s)) {
    				chain.doFilter(hrequest, hresponse);
                    return;
    			}
    		}*/
//            CustomerUser wxUserSession = (CustomerUser) hrequest.getSession().getAttribute(BaseConstans.SESSION_WXUSER.getValue());
//            log.info("_______________________________从session获取微信用户判断是否绑定_________________wxUserSession.getCustomerId()____________________"+wxUserSession.getId());
//            log.info("_______________________________从session获取微信用户判断是否绑定_________________wxUserSession.toString()____________________"+wxUserSession.toString());
            //log.info("_______________________________userService.validateCustomerId(wxUserSession.getCustomerId())________________"+userService.validateCustomerId(wxUserSession.getCustomerId()));


            chain.doFilter(hrequest, hresponse);
            
            



    }


    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        weixinAuthApiUtils = weixinAuthApiUtils.getInstance();
    }


    private static final String cookieName = "opck";

    public static void addCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null)
            response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        log.info("-----------------getCookie---------"+cookieName);
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookieName == null || cookieName.equals(""))
            return null;

        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName))
                return (Cookie) c;
        }
        return null;
    }

    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(60 * 60 * 24 * 365);// 设置为30min
        cookie.setPath("/");
        System.out.println("已添加===============");
        response.addCookie(cookie);
    }

}