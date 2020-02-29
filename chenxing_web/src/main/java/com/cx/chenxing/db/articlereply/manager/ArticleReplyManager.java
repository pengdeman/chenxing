package com.cx.chenxing.db.articlereply.manager;

import com.cx.chenxing.db.articlereply.dao.ArticleReplyDao;
import com.cx.chenxing.db.articlereply.entity.ArticleReply;
import com.cx.chenxing.model.articlereply.param.ArticleReplyQuery;
import com.cx.chenxing.model.articlereply.result.ArticleReplyBean;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleReplyManager {

    @Resource
    private ArticleReplyDao articlereplyDao;

    @Transactional
    public int insert(ArticleReply articlereply) {
        return articlereplyDao.insert(articlereply);
    }

    /**
     * 根据实体类编辑所有值
     */
    @Transactional
    public int update(ArticleReply articlereply) {
        return articlereplyDao.updateByPrimaryKey(articlereply);
    }

    /**
     * 只编辑实体类内非空的值
     */
    @Transactional
    public int updateSelective(ArticleReply articlereply) {
        return articlereplyDao.updateByPrimaryKeySelective(articlereply);
    }
    
    @Transactional
    public ArticleReply selectByPrimaryKey(long id) {
        return articlereplyDao.selectByPrimaryKey(id);
    }
    
    @Transactional
    public int delete(long id) {
        return articlereplyDao.deleteByPrimaryKey(id);
    }

    public Page<ArticleReply> query(ArticleReplyQuery articlereplyQuery) {
        List<ArticleReply> list = articlereplyDao.query(articlereplyQuery);
        Page<ArticleReply> page = new Page<ArticleReply>(articlereplyQuery);
        page.setResult(list);
        return page;
    }

    public List<ArticleReplyBean> queryreply(ArticleReplyQuery articleReplyQuery) {
        return articlereplyDao.queryreply(articleReplyQuery);
    }

    public void deleteByArticleId(long id) {
        articlereplyDao.deleteByArticleId(id);
    }
}
