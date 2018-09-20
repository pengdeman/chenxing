package com.cx.chenxing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.cx.chenxing.*.dao")
@ComponentScan(basePackages= {"com.cx.chenxing"})
public class ChenXingWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChenXingWebApplication.class, args);
	}
}
