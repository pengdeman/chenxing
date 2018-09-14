package com.cx.chenxing.user.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.chenxing.user.dao.UserDao;

@Service
public class UserManager {

	@Autowired
	private UserDao userDao;
	
	public List findAllUser() {
		return userDao.findAllUser();
	}

}
