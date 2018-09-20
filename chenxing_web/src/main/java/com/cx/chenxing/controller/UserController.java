package com.cx.chenxing.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cx.chenxing.user.UserService;

//@Controller+@ResponseBody相当于@RestController
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
    private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/index")
	public String index() {
		//List list =  userService.findAllUser();
	    //return list.toString();
		return "/frontpages/index";
	}

}
