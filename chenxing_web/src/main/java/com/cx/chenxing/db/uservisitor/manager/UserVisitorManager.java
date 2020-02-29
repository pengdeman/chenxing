package com.cx.chenxing.db.uservisitor.manager;

import com.cx.chenxing.db.uservisitor.dao.UserVisitorDao;
import com.cx.chenxing.db.uservisitor.entity.UserVisitor;
import com.cx.chenxing.model.uservisitor.param.UserVisitorQuery;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserVisitorManager {

    @Resource
    private UserVisitorDao uservisitorDao;

    @Transactional
    public int insert(UserVisitor uservisitor) {
        return uservisitorDao.insert(uservisitor);
    }

    /**
     * 根据实体类编辑所有值
     */
    @Transactional
    public int update(UserVisitor uservisitor) {
        return uservisitorDao.updateByPrimaryKey(uservisitor);
    }

    /**
     * 只编辑实体类内非空的值
     */
    @Transactional
    public int updateSelective(UserVisitor uservisitor) {
        return uservisitorDao.updateByPrimaryKeySelective(uservisitor);
    }
    
    @Transactional
    public UserVisitor selectByPrimaryKey(long id) {
        return uservisitorDao.selectByPrimaryKey(id);
    }
    
    @Transactional
    public int delete(long id) {
        return uservisitorDao.deleteByPrimaryKey(id);
    }

    public Page<UserVisitor> query(UserVisitorQuery uservisitorQuery) {
        List<UserVisitor> list = uservisitorDao.query(uservisitorQuery);
        Page<UserVisitor> page = new Page<UserVisitor>(uservisitorQuery);
        page.setResult(list);
        return page;
    }
}
