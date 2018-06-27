package com.ycjcjy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@ComponentScan(
		basePackages = {"com.ycjcjy.gene.service",
				"com.ycjcjy.gene.common",
				"com.ycjcjy.gene.dao.mongo",
				"com.ycjcjy.gene.web.action.system.listener",
				"net.onebean.component"},
		includeFilters = {
				@ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION),
				@ComponentScan.Filter(value = Component.class, type = FilterType.ANNOTATION)
		})
@ComponentScan(
		basePackages = {"com.ycjcjy.gene.web"},
		includeFilters = {
				@ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
		})
@ImportResource(locations={"classpath*:META-INF/spring/*.xml"})
@SpringBootApplication
@EnableScheduling
@Configuration
public class GeneWebMain extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GeneWebMain.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GeneWebMain.class, args);
	}

//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer(){
//		return new EmbeddedServletContainerCustomizer() {
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer Container) {
//				Container.setSessionTimeout(36000);//单位为S
//				}
//       };
//	}
}


