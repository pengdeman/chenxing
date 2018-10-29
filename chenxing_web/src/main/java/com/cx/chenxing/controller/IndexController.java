package com.cx.chenxing.controller;

import com.cx.chenxing.article.ArticleService;
import com.cx.chenxing.article.result.ArticleBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页相关
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private ArticleService articleService;

    /**
     * 系统首页
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        ArticleBean articleQuery = new ArticleBean();
        List<ArticleBean> articleList = articleService.query(articleQuery).getResult();
        model.addAttribute("articleList", articleList);
        return "frontpages/index";
    }
}
