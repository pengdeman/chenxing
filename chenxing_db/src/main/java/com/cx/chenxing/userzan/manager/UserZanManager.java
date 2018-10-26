package com.cx.chenxing.userzan.manager;

import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.userzan.dao.UserZanDao;
import com.cx.chenxing.userzan.entity.UserZan;
import com.cx.chenxing.userzan.param.UserZanQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserZanManager {
    @Autowired
    private UserZanDao userzanDao;

    @Transactional
    public int insert(UserZan userzan) {
        return userzanDao.insert(userzan);
    }

    /**
     * 根据实体类编辑所有值
     */
    @Transactional
    public int update(UserZan userzan) {
        return userzanDao.updateByPrimaryKey(userzan);
    }

    /**
     * 只编辑实体类内非空的值
     */
    @Transactional
    public int updateSelective(UserZan userzan) {
        return userzanDao.updateByPrimaryKeySelective(userzan);
    }
    
    @Transactional
    public UserZan selectByPrimaryKey(long id) {
        return userzanDao.selectByPrimaryKey(id);
    }
    
    @Transactional
    public int delete(long id) {
        return userzanDao.deleteByPrimaryKey(id);
    }

    public Page<UserZan> query(UserZanQuery userzanQuery) {
        List<UserZan> list = userzanDao.query(userzanQuery);
        Page<UserZan> page = new Page<UserZan>(userzanQuery);
        page.setResult(list);
        return page;
    }
}
