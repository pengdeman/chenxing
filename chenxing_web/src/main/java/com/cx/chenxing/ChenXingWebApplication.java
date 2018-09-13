package com.cx.chenxing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.cx")
@ComponentScan(basePackages= {"com.cx"})
public class ChenXingWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChenXingWebApplication.class, args);
	}
}
