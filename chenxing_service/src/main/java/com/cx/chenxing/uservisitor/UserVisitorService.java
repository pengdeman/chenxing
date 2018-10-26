/*
 * Powered By [meinong]
 * Web Site: http://www.meinong.cn
 * Since 2015 - 2018
 */

package com.cx.chenxing.uservisitor;

import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.uservisitor.result.UserVisitorBean;

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
