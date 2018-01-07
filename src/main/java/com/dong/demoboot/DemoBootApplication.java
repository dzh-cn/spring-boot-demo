package com.dong.demoboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:beanConfig/applicationBeans.xml")
@SpringBootApplication
public class DemoBootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoBootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoBootApplication.class);
	}
}
