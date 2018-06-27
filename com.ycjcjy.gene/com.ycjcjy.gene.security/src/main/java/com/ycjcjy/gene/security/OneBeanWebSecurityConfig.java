package com.ycjcjy.gene.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * spring security
 * web安全配置器,boot版本基本配置
 * @author 0neBean
 */
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
public class OneBeanWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OneBeanAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private OneBeanFilterSecurityInterceptor myFilterSecurityInterceptor;
    @Autowired
    UserDetailsService customUserService;
    @Autowired
    OneBeanPermissionEvaluator permissionEvaluator;
    @Autowired
    private OneBeanPasswordEncoder oneBeanPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //user Details Service验证
        //指定spring 提供密码加密类 采用SHA-256(采用随机盐+秘钥+密码)加密方式  加密后密码长度80位 随机秘钥随每次启动程序生成
        auth.userDetailsService(customUserService).passwordEncoder(oneBeanPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        web.expressionHandler(handler);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        myAuthenticationFailureHandler.setDefaultFailureUrl("/login");

        String[] unSecuredUrls = { "/system_assets/**", "/assets/**","/websocket/**","/druid/**","/error/**"};
        http.authorizeRequests()
                .antMatchers(unSecuredUrls).permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/center")
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll() //登录页面用户任意访问
                .and()
                .headers().frameOptions().sameOrigin()//允许iframe嵌套本应用页面
                .and().rememberMe().and()
                .logout().permitAll(); //注销行为任意访问
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class).csrf().disable();
    }

}

