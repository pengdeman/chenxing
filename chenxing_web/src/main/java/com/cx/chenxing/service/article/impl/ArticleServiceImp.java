package com.cx.chenxing.service.article.impl;

import com.cx.chenxing.db.article.entity.Article;
import com.cx.chenxing.db.article.manager.ArticleManager;
import com.cx.chenxing.model.article.param.ArticleQuery;
import com.cx.chenxing.model.article.result.ArticleBean;
import com.cx.chenxing.service.article.ArticleService;
import com.cx.chenxing.utils.ModelUtil;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public void updateYdNum(List<Long> articleIds) {
        articleManager.updateYdNum(articleIds);
    }

    @Override
    public Page<ArticleBean> queryarticle(ArticleQuery articleQuery) {
        return ModelUtil.copyPage(articleManager.queryarticle(articleQuery), ArticleBean.class);
    }

    public ArticleBean selectByPrimaryKey(long id) {
        return ModelUtil.copyObject(articleManager.selectByPrimaryKey(id), ArticleBean.class);
    }
    
    public Page<ArticleBean> query(ArticleBean articleBean) {
        ArticleQuery articleQuery = ModelUtil.copyObject(articleBean, ArticleQuery.class);
        return ModelUtil.copyPage(articleManager.query(articleQuery), ArticleBean.class);
    }
}
