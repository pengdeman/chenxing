package com.cx.chenxing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages= {"com.cx"})
public class ChenXingWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChenXingWebApplication.class, args);
	}
}
