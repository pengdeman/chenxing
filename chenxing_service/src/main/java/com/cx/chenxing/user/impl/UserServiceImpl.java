package com.cx.chenxing.user.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cx.chenxing.user.UserService;
import com.cx.chenxing.user.manager.UserManager;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserManager userManager;
	
	@Override
	public List findAllUser() {
		return userManager.findAllUser();
	}

}
