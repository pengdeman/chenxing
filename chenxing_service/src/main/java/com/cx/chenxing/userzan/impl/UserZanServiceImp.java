package com.cx.chenxing.userzan.impl;

import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.userzan.UserZanService;
import com.cx.chenxing.userzan.entity.UserZan;
import com.cx.chenxing.userzan.manager.UserZanManager;
import com.cx.chenxing.userzan.param.UserZanQuery;
import com.cx.chenxing.userzan.result.UserZanBean;
import com.cx.chenxing.utils.ModelUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserZanServiceImp implements UserZanService {
    @Resource
    private UserZanManager userZanManager;

    public int insert(UserZanBean userZanBean) {
        UserZan userZan = ModelUtil.copyObject(userZanBean, UserZan.class);
        int result = userZanManager.insert(userZan);
        return result;
    }

    /**
     * 根据实体类编辑所有值
     */
    public int update(UserZanBean userZanBean) {
        UserZan userZan = ModelUtil.copyObject(userZanBean, UserZan.class);
        return userZanManager.update(userZan);
    }

    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(UserZanBean userZanBean) {
        UserZan userZan = ModelUtil.copyObject(userZanBean, UserZan.class);
        return userZanManager.updateSelective(userZan);
    }

    public int delete(long id) {
        return userZanManager.delete(id);
    }

    public UserZanBean selectByPrimaryKey(long id) {
        return ModelUtil.copyObject(userZanManager.selectByPrimaryKey(id), UserZanBean.class);
    }
    
    public Page<UserZanBean> query(UserZanBean userZanBean) {
        UserZanQuery userZanQuery = ModelUtil.copyObject(userZanBean, UserZanQuery.class);
        return ModelUtil.copyPage(userZanManager.query(userZanQuery), UserZanBean.class);
    }
}
