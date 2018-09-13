package com.cx.chenxing.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cx.chenxing.user.IUserService;
import com.cx.chenxing.user.manager.UserManager;

public class UserServiceImpl implements IUserService{

	@Autowired
	private UserManager userManager;
	
	@Override
	public List findAllUser() {
		return userManager.findAllUser();
	}

}
