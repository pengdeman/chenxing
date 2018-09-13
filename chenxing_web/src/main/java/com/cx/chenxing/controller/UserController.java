package com.cx.chenxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller+@ResponseBody相当于@RestController
@Controller
@RequestMapping(value="/")
public class UserController {
	
	@ResponseBody
	@RequestMapping(value="/")
	public String index() {
		System.out.println("进入方法！！！！！");
		return "Hello World";
	}

}
