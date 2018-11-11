package com.cx.chenxing.article.manager;

import com.cx.chenxing.article.dao.ArticleDao;
import com.cx.chenxing.article.entity.Article;
import com.cx.chenxing.article.param.ArticleQuery;
import com.cx.chenxing.article.result.ArticleBean;
import com.cx.chenxing.mybatisutils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ArticleManager {

    @Autowired
    private ArticleDao articleDao;

    @Transactional
    public int insert(Article article) {
        return articleDao.insert(article);
    }

    /**
     * 根据实体类编辑所有值
     */
    @Transactional
    public int update(Article article) {
        return articleDao.updateByPrimaryKey(article);
    }

    /**
     * 只编辑实体类内非空的值
     */
    @Transactional
    public int updateSelective(Article article) {
        return articleDao.updateByPrimaryKeySelective(article);
    }
    
    @Transactional
    public Article selectByPrimaryKey(long id) {
        return articleDao.selectByPrimaryKey(id);
    }
    
    @Transactional
    public int delete(long id) {
        return articleDao.deleteByPrimaryKey(id);
    }

    public Page<Article> query(ArticleQuery articleQuery) {
        List<Article> list = articleDao.query(articleQuery);
        Page<Article> page = new Page<Article>(articleQuery);
        page.setResult(list);
        return page;
    }

    public void updateYdNum(List<Long> articleIds) {
        articleDao.updateYdNum(articleIds);
    }

    public Page<Article> queryarticle(ArticleQuery articleQuery) {
        List<Article> list = articleDao.queryarticle(articleQuery);
        Page<Article> page = new Page<Article>(articleQuery);
        page.setResult(list);
        return page;
    }
}
