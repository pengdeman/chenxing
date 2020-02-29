package com.cx.chenxing.service.uservisitor;

import com.cx.chenxing.model.uservisitor.result.UserVisitorBean;
import com.cx.chenxing.utils.mybatisutils.Page;

public interface UserVisitorService {
    public int insert(UserVisitorBean userVisitorBean);

    public Page<UserVisitorBean> query(UserVisitorBean userVisitorBean);

    /**
     * 根据实体类编辑所有值
     */
    public int update(UserVisitorBean userVisitorBean);
    
    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(UserVisitorBean userVisitorBean);
        
    public UserVisitorBean selectByPrimaryKey(long id);
        
    public int delete(long id);
}
