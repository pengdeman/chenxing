package com.cx.chenxing.articlereply.dao;

import com.cx.chenxing.articlereply.entity.ArticleReply;
import com.cx.chenxing.articlereply.param.ArticleReplyQuery;
import com.cx.chenxing.articlereply.result.ArticleReplyBean;
import com.cx.chenxing.mybatisutils.BaseDao;

import java.util.List;

public interface ArticleReplyDao extends BaseDao<ArticleReply,ArticleReplyQuery,Integer> {
    public List<ArticleReplyBean> queryreply(ArticleReplyQuery articleReplyQuery);
}
