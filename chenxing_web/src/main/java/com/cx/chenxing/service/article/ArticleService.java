package com.cx.chenxing.service.article;

import com.cx.chenxing.model.article.param.ArticleQuery;
import com.cx.chenxing.model.article.result.ArticleBean;
import com.cx.chenxing.utils.mybatisutils.Page;
import java.util.List;

public interface ArticleService {
    public int insert(ArticleBean articleBean);

    public Page<ArticleBean> query(ArticleBean articleBean);

    /**
     * 根据实体类编辑所有值
     */
    public int update(ArticleBean articleBean);
    
    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(ArticleBean articleBean);
        
    public ArticleBean selectByPrimaryKey(long id);
        
    public int delete(long id);

    void updateYdNum(List<Long> articleIds);

    public Page<ArticleBean> queryarticle(ArticleQuery articleQuery);
}
