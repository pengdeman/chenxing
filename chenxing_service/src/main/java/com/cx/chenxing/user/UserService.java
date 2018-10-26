package com.cx.chenxing.user;

import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.user.entity.User;
import com.cx.chenxing.user.param.UserQuery;
import com.cx.chenxing.user.result.UserBean;

import java.util.List;

public interface UserService {
    public int insert(UserBean userBean);

    public Page<UserBean> query(UserBean userBean);

    /**
     * 根据实体类编辑所有值
     */
    public int update(UserBean userBean);
    
    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(UserBean userBean);
        
    public UserBean selectByPrimaryKey(long id);
        
    public int delete(long id);

    public UserBean findLoginUserByLoginNameAndPassword(String loginName, String password);

    List<User> queryAccount(UserQuery uQuery);
}
