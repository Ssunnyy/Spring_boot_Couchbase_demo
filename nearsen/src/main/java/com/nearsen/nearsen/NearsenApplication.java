package com.nearsen.nearsen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NearsenApplication  {

//	extends SpringBootServletInitializer
////	extends SpringBootServletInitializer
//	// 打包war 需要添加的方法
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
//		return applicationBuilder.sources(NearsenApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(NearsenApplication.class, args);
	}
}
