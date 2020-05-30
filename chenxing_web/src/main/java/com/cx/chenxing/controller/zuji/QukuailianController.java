package com.cx.chenxing.controller.zuji;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class QukuailianController {
	public static Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
	
	@RequestMapping("/qkl")
	public String btc(HttpServletRequest request){
		return "qukuailian/index";
	}
	
	@RequestMapping("/lmd")
	public String lmd(HttpServletRequest request){
		return "peopleshow/lmd";
	}
	
	@RequestMapping("/pjj")
	public String pjj(HttpServletRequest request){
		return "peopleshow/pjj";
	}
	
	@RequestMapping("/pjr")
	public String pjr(HttpServletRequest request){
		return "peopleshow/pjr";
	}
}
