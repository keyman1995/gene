package com.ycjcjy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;

@ComponentScan(
		basePackages = {"com.ycjcjy.gene.service",
				"com.ycjcjy.gene.common",
				"com.ycjcjy.gene.security",
				"com.ycjcjy.gene.dao.mongo",
				"net.onebean.component"},
		includeFilters = {
				@ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION),
				@ComponentScan.Filter(value = Component.class, type = FilterType.ANNOTATION),
				@ComponentScan.Filter(value = ServerEndpoint.class, type = FilterType.ANNOTATION)
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
public class GeneMain extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GeneMain.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GeneMain.class, args);
	}

}


