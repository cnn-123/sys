package com.example.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 陈莉
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.sys.demo.mapper")
public class SysApplication extends SpringBootServletInitializer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		//使用web.xml运行应用程序，指向SysApplication，最后启动springboot
		return builder.sources(SysApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SysApplication.class, args);
	}

}
