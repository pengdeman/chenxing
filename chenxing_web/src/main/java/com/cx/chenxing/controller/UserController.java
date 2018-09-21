package com.cx.chenxing.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cx.chenxing.user.UserService;

//@Controller+@ResponseBody相当于@RestController
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
    private UserService userService;
	
	@RequestMapping(value="/index")
	public String index() {
		//List list =  userService.findAllUser();
	    //return list.toString();
		return "frontpages/index";
	}

}
