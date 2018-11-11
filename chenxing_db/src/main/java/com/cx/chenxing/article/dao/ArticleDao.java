package com.cx.chenxing.article.dao;

import com.cx.chenxing.article.entity.Article;
import com.cx.chenxing.article.param.ArticleQuery;
import com.cx.chenxing.mybatisutils.BaseDao;

import java.util.List;

public interface ArticleDao extends BaseDao<Article,ArticleQuery,Integer> {
    void updateYdNum(List<Long> articleIds);

    List<Article> queryarticle(ArticleQuery articleQuery);
}
