package com.ycjcjy.spring.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import net.onebean.core.form.Parse;
import net.onebean.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * druid数据库连接池配置
 * @author 0neBean
 */
@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 配置监控服务器
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
        servletRegistrationBean.addInitParameter("allow", PropUtil.getConfig("com.alibaba.druid.ip.allow"));
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        servletRegistrationBean.addInitParameter("deny", PropUtil.getConfig("com.alibaba.druid.ip.deny"));
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", PropUtil.getConfig("com.alibaba.druid.username"));
        servletRegistrationBean.addInitParameter("loginPassword", PropUtil.getConfig("com.alibaba.druid.password"));
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        //慢sql日志
        servletRegistrationBean.addInitParameter("logSlowSql", PropUtil.getConfig("com.alibaba.druid.logSlowSql"));
        return servletRegistrationBean;
    }

    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }


    /**
     * 数据库连接池
     * @return 返回数据库连接池实例
     */
    @Bean(name = "dataSource")
    public DruidDataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(PropUtil.getConfig("spring.datasource.url"));
        datasource.setUsername(PropUtil.getConfig("spring.datasource.username"));
        datasource.setPassword(PropUtil.getConfig("spring.datasource.password"));
        datasource.setDriverClassName(PropUtil.getConfig("spring.datasource.driver-class-name"));
        //初始化大小，最小，最大
        datasource.setInitialSize(Parse.toInt(PropUtil.getConfig("spring.datasource.initialSize")));
        datasource.setMinIdle(Parse.toInt(PropUtil.getConfig("spring.datasource.minIdle")));
        datasource.setMaxActive(Parse.toInt(PropUtil.getConfig("spring.datasource.maxActive")));
        //配置获取连接等待超时的时间
        datasource.setMaxWait(Parse.toLong(PropUtil.getConfig("spring.datasource.maxWait")));
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        datasource.setTimeBetweenEvictionRunsMillis(Parse.toLong(PropUtil.getConfig("spring.datasource.timeBetweenEvictionRunsMillis")));
        //配置一个连接在池中最小生存的时间，单位是毫秒
        datasource.setMinEvictableIdleTimeMillis(Parse.toLong(PropUtil.getConfig("spring.datasource.minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(PropUtil.getConfig("spring.datasource.validationQuery"));
        datasource.setTestWhileIdle(Parse.toBoolean(PropUtil.getConfig("spring.datasource.testWhileIdle")));
        datasource.setTestOnBorrow(Parse.toBoolean(PropUtil.getConfig("spring.datasource.testOnBorrow")));
        datasource.setTestOnReturn(Parse.toBoolean(PropUtil.getConfig("spring.datasource.testOnReturn")));
        //打开PSCache，并且指定每个连接上PSCache的大小
        datasource.setPoolPreparedStatements(Parse.toBoolean(PropUtil.getConfig("spring.datasource.poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Parse.toInt(PropUtil.getConfig("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
        try {
            //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
            datasource.setFilters(PropUtil.getConfig("spring.datasource.filters"));
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

}
