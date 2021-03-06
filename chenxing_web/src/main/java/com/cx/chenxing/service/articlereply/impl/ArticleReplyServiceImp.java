package com.cx.chenxing.service.articlereply.impl;

import com.cx.chenxing.db.articlereply.entity.ArticleReply;
import com.cx.chenxing.db.articlereply.manager.ArticleReplyManager;
import com.cx.chenxing.model.articlereply.param.ArticleReplyQuery;
import com.cx.chenxing.model.articlereply.result.ArticleReplyBean;
import com.cx.chenxing.service.articlereply.ArticleReplyService;
import com.cx.chenxing.utils.ModelUtil;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleReplyServiceImp implements ArticleReplyService {
    @Resource
    private ArticleReplyManager articleReplyManager;

    public int insert(ArticleReplyBean articleReplyBean) {
        ArticleReply articleReply = ModelUtil.copyObject(articleReplyBean, ArticleReply.class);
        int result = articleReplyManager.insert(articleReply);
        articleReplyBean.setId(articleReply.getId());
        return result;
    }

    /**
     * 根据实体类编辑所有值
     */
    public int update(ArticleReplyBean articleReplyBean) {
        ArticleReply articleReply = ModelUtil.copyObject(articleReplyBean, ArticleReply.class);
        return articleReplyManager.update(articleReply);
    }

    /**
     * 只编辑实体类内非空的值
     */
    public int updateSelective(ArticleReplyBean articleReplyBean) {
        ArticleReply articleReply = ModelUtil.copyObject(articleReplyBean, ArticleReply.class);
        return articleReplyManager.updateSelective(articleReply);
    }

    public int delete(long id) {
        return articleReplyManager.delete(id);
    }

    @Override
    public List<ArticleReplyBean> queryreply(ArticleReplyQuery articleReplyQuery) {
        return articleReplyManager.queryreply(articleReplyQuery);
    }

    @Override
    public void deleteByArticleId(long id) {
        articleReplyManager.deleteByArticleId(id);
    }

    public ArticleReplyBean selectByPrimaryKey(long id) {
        return ModelUtil.copyObject(articleReplyManager.selectByPrimaryKey(id), ArticleReplyBean.class);
    }
    
    public Page<ArticleReplyBean> query(ArticleReplyQuery articleReplyQuery) {
        return ModelUtil.copyPage(articleReplyManager.query(articleReplyQuery), ArticleReplyBean.class);
    }
}
