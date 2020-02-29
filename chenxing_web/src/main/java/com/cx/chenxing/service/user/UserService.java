package com.cx.chenxing.service.user;

import com.cx.chenxing.db.user.entity.User;
import com.cx.chenxing.model.user.param.UserQuery;
import com.cx.chenxing.model.user.result.UserBean;
import com.cx.chenxing.utils.mybatisutils.Page;
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

    List<User> queryAccount(UserQuery uQuery);
}
