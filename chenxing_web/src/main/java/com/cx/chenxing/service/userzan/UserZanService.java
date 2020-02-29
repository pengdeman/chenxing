package com.cx.chenxing.service.userzan;

import com.cx.chenxing.model.userzan.param.UserZanQuery;
import com.cx.chenxing.model.userzan.result.UserZanBean;
import com.cx.chenxing.utils.mybatisutils.Page;

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
