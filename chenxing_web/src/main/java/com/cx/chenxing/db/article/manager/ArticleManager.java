package com.cx.chenxing.db.article.manager;

import com.cx.chenxing.db.article.dao.ArticleDao;
import com.cx.chenxing.db.article.entity.Article;
import com.cx.chenxing.model.article.param.ArticleQuery;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleManager {

    @Resource
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
