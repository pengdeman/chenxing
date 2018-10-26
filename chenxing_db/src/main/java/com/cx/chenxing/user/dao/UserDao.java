package com.cx.chenxing.user.dao;

import com.cx.chenxing.mybatisutils.BaseDao;
import com.cx.chenxing.user.entity.User;
import com.cx.chenxing.user.param.UserQuery;
import com.cx.chenxing.user.result.UserBean;

import java.util.List;

public interface UserDao extends BaseDao<User,UserQuery,Integer> {
    UserBean findLoginUserByLoginNameAndPassword(UserQuery uquery);

    List<User> queryAccount(UserQuery uQuery);
}
