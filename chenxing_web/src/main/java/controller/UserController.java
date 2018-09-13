package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller+@ResponseBody相当于@RestController
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/userinfo")
	@ResponseBody
	public String index() {
		return "Hello World";
	}

}
