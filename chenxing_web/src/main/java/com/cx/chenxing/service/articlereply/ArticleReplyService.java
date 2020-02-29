package com.cx.chenxing.service.articlereply;

import com.cx.chenxing.model.articlereply.param.ArticleReplyQuery;
import com.cx.chenxing.model.articlereply.result.ArticleReplyBean;
import com.cx.chenxing.utils.mybatisutils.Page;

import java.util.List;

public interface ArticleReplyService {
    public int insert(ArticleReplyBean articleReplyBean);

    public Page<ArticleReplyBean> query(ArticleReplyQuery articleReplyQuery);

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

    public List<ArticleReplyBean> queryreply(ArticleReplyQuery articleReplyQuery);

    void deleteByArticleId(long parseLong);
}
