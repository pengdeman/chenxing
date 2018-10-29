package com.cx.chenxing.user.manager;

import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.user.dao.UserDao;
import com.cx.chenxing.user.entity.User;
import com.cx.chenxing.user.param.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserManager {

    @Autowired
    private UserDao userDao;

    @Transactional
    public int insert(User user) {
        return userDao.insert(user);
    }

    /**
     * 根据实体类编辑所有值
     */
    @Transactional
    public int update(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    /**
     * 只编辑实体类内非空的值
     */
    @Transactional
    public int updateSelective(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }
    
    @Transactional
    public User selectByPrimaryKey(long id) {
        return userDao.selectByPrimaryKey(id);
    }
    
    @Transactional
    public int delete(long id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public Page<User> query(UserQuery userQuery) {
        List<User> list = userDao.query(userQuery);
        Page<User> page = new Page<User>(userQuery);
        page.setResult(list);
        return page;
    }

    public List<User> queryAccount(UserQuery uQuery) {
        return this.userDao.queryAccount(uQuery);
    }
}
