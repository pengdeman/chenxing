package com.cx.chenxing.db.article.dao;

import com.cx.chenxing.db.article.entity.Article;
import com.cx.chenxing.model.article.param.ArticleQuery;
import com.cx.chenxing.utils.mybatisutils.BaseDao;
import java.util.List;

public interface ArticleDao extends BaseDao<Article, ArticleQuery,Integer> {
    void updateYdNum(List<Long> articleIds);

    List<Article> queryarticle(ArticleQuery articleQuery);
}
