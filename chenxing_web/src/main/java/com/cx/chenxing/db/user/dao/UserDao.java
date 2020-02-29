package com.cx.chenxing.db.user.dao;

import com.cx.chenxing.db.user.entity.User;
import com.cx.chenxing.model.user.param.UserQuery;
import com.cx.chenxing.utils.mybatisutils.BaseDao;
import java.util.List;

public interface UserDao extends BaseDao<User, UserQuery,Integer> {

    List<User> queryAccount(UserQuery uQuery);
}
