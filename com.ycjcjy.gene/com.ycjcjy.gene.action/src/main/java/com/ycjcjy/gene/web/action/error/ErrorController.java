package com.ycjcjy.gene.web.action.error;

import com.ycjcjy.gene.web.action.login.LoginController;
import net.onebean.util.PropUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("error")
@Controller
public class ErrorController {

    @RequestMapping("403")
    public String page_403(){
        return "/error/403";
    }

    @RequestMapping("404")
    public String page_404(){
        return "/error/404";
    }

    @RequestMapping("500")
    public String page_500(){
        return "/error/500";
    }

    @RequestMapping("400")
    public String page_400(){
        return "/error/400";
    }

    @RequestMapping("401")
    public String page_401(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(LoginController.ERROR_SESSION_MSG_KEY);
        return "/error/401";
    }

    @RequestMapping("408")
    public String page_408(){
        return "/error/408";
    }

    @RequestMapping("518")
    public String page_518(){
        return "/error/518";
    }

    @RequestMapping("501")
    public String page_501(){
        return "/error/501";
    }

    @RequestMapping("browerlist")
    public String page_brower(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        if (!ua.contains("Trident")){
            return "redirect:/"+PropUtil.getConfig("")+"/sdsds";
        }
        return "/error/browerlist";
    }

}
