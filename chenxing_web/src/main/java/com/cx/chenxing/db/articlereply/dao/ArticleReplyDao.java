package com.cx.chenxing.db.articlereply.dao;

import com.cx.chenxing.db.articlereply.entity.ArticleReply;
import com.cx.chenxing.model.articlereply.param.ArticleReplyQuery;
import com.cx.chenxing.model.articlereply.result.ArticleReplyBean;
import com.cx.chenxing.utils.mybatisutils.BaseDao;
import java.util.List;

public interface ArticleReplyDao extends BaseDao<ArticleReply, ArticleReplyQuery,Integer> {
    public List<ArticleReplyBean> queryreply(ArticleReplyQuery articleReplyQuery);

    void deleteByArticleId(long id);
}
