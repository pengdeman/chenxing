package com.cx.chenxing.articlereply;

import com.cx.chenxing.articlereply.result.ArticleReplyBean;
import com.cx.chenxing.mybatisutils.Page;

public interface ArticleReplyService {
    public int insert(ArticleReplyBean articleReplyBean);

    public Page<ArticleReplyBean> query(ArticleReplyBean articleReplyBean);

    /**
     * 根据实体类编辑所有值
     */
    public int update(ArticleReplyBean articleReplyBean);
    
    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(ArticleReplyBean articleReplyBean);
        
    public ArticleReplyBean selectByPrimaryKey(long id);
        
    public int delete(long id);
}
