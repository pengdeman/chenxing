package com.cx.chenxing.service.user.impl;

import com.cx.chenxing.db.user.entity.User;
import com.cx.chenxing.db.user.manager.UserManager;
import com.cx.chenxing.model.user.param.UserQuery;
import com.cx.chenxing.model.user.result.UserBean;
import com.cx.chenxing.service.user.UserService;
import com.cx.chenxing.utils.ModelUtil;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserManager userManager;

    public int insert(UserBean userBean) {
        User user = ModelUtil.copyObject(userBean, User.class);
        int result = userManager.insert(user);
        return result;
    }

    /**
     * 根据实体类编辑所有值
     */
    public int update(UserBean userBean) {
        User user = ModelUtil.copyObject(userBean, User.class);
        return userManager.update(user);
    }

    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(UserBean userBean) {
        User user = ModelUtil.copyObject(userBean, User.class);
        return userManager.updateSelective(user);
    }

    public int delete(long id) {
        return userManager.delete(id);
    }

    @Override
    public List<User> queryAccount(UserQuery uQuery) {
        return userManager.queryAccount(uQuery);
    }

    public UserBean selectByPrimaryKey(long id) {
        return ModelUtil.copyObject(userManager.selectByPrimaryKey(id), UserBean.class);
    }
    
    public Page<UserBean> query(UserBean userBean) {
        UserQuery userQuery = ModelUtil.copyObject(userBean, UserQuery.class);
        return ModelUtil.copyPage(userManager.query(userQuery), UserBean.class);
    }
}
