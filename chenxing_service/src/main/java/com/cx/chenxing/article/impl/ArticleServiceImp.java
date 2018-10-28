package com.cx.chenxing.article.impl;

import com.cx.chenxing.article.ArticleService;
import com.cx.chenxing.article.entity.Article;
import com.cx.chenxing.article.manager.ArticleManager;
import com.cx.chenxing.article.param.ArticleQuery;
import com.cx.chenxing.article.result.ArticleBean;
import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.utils.ModelUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ArticleServiceImp implements ArticleService {
    @Resource
    private ArticleManager articleManager;

    public int insert(ArticleBean articleBean) {
        Article article = ModelUtil.copyObject(articleBean, Article.class);
        int result = articleManager.insert(article);
        return result;
    }

    /**
     * 根据实体类编辑所有值
     */
    public int update(ArticleBean articleBean) {
        Article article = ModelUtil.copyObject(articleBean, Article.class);
        return articleManager.update(article);
    }

    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(ArticleBean articleBean) {
        Article article = ModelUtil.copyObject(articleBean, Article.class);
        return articleManager.updateSelective(article);
    }

    public int delete(long id) {
        return articleManager.delete(id);
    }

    public ArticleBean selectByPrimaryKey(long id) {
        return ModelUtil.copyObject(articleManager.selectByPrimaryKey(id), ArticleBean.class);
    }
    
    public Page<ArticleBean> query(ArticleQuery articleQuery) {
        return ModelUtil.copyPage(articleManager.query(articleQuery), ArticleBean.class);
    }
}
