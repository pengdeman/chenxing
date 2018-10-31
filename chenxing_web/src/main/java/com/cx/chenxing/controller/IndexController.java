package com.cx.chenxing.controller;

import com.cx.chenxing.article.ArticleService;
import com.cx.chenxing.article.result.ArticleBean;
import com.cx.chenxing.user.result.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String index(HttpServletRequest request, Model model) {
        ArticleBean articleQuery = new ArticleBean();
        articleQuery.setShows("1");
        articleQuery.setSortColumns("cre_time desc");
        List<ArticleBean> articleList = articleService.query(articleQuery).getResult();
        model.addAttribute("articleList", articleList);
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            model.addAttribute("user", user);
        }else{
            model.addAttribute("user", "");
        }
        return "frontpages/index";
    }
}
