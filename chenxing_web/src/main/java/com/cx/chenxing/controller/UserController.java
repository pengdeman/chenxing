package com.cx.chenxing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller+@ResponseBody相当于@RestController
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@ResponseBody
	@RequestMapping(value="/index")
	public String index() {
		return "Hello User";
	}

}
