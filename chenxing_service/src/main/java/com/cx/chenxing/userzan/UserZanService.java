package com.cx.chenxing.userzan;

import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.userzan.param.UserZanQuery;
import com.cx.chenxing.userzan.result.UserZanBean;

public interface UserZanService {
    public int insert(UserZanBean userZanBean);

    public Page<UserZanBean> query(UserZanQuery userZanQuery);

    /**
     * 根据实体类编辑所有值
     */
    public int update(UserZanBean userZanBean);
    
    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(UserZanBean userZanBean);
        
    public UserZanBean selectByPrimaryKey(long id);
        
    public int delete(long id);
}
