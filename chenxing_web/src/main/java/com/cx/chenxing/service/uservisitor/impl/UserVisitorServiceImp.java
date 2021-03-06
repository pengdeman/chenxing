package com.cx.chenxing.service.uservisitor.impl;

import com.cx.chenxing.db.uservisitor.entity.UserVisitor;
import com.cx.chenxing.db.uservisitor.manager.UserVisitorManager;
import com.cx.chenxing.model.uservisitor.param.UserVisitorQuery;
import com.cx.chenxing.model.uservisitor.result.UserVisitorBean;
import com.cx.chenxing.service.uservisitor.UserVisitorService;
import com.cx.chenxing.utils.ModelUtil;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserVisitorServiceImp implements UserVisitorService {
    @Resource
    private UserVisitorManager userVisitorManager;

    public int insert(UserVisitorBean userVisitorBean) {
        UserVisitor userVisitor = ModelUtil.copyObject(userVisitorBean, UserVisitor.class);
        int result = userVisitorManager.insert(userVisitor);
        return result;
    }

    /**
     * 根据实体类编辑所有值
     */
    public int update(UserVisitorBean userVisitorBean) {
        UserVisitor userVisitor = ModelUtil.copyObject(userVisitorBean, UserVisitor.class);
        return userVisitorManager.update(userVisitor);
    }

    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(UserVisitorBean userVisitorBean) {
        UserVisitor userVisitor = ModelUtil.copyObject(userVisitorBean, UserVisitor.class);
        return userVisitorManager.updateSelective(userVisitor);
    }

    public int delete(long id) {
        return userVisitorManager.delete(id);
    }

    public UserVisitorBean selectByPrimaryKey(long id) {
        return ModelUtil.copyObject(userVisitorManager.selectByPrimaryKey(id), UserVisitorBean.class);
    }
    
    public Page<UserVisitorBean> query(UserVisitorBean userVisitorBean) {
        UserVisitorQuery userVisitorQuery = ModelUtil.copyObject(userVisitorBean, UserVisitorQuery.class);
        return ModelUtil.copyPage(userVisitorManager.query(userVisitorQuery), UserVisitorBean.class);
    }
}