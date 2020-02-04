package com.cx.chenxing.controller;

import com.cx.chenxing.article.ArticleService;
import com.cx.chenxing.article.param.ArticleQuery;
import com.cx.chenxing.article.result.ArticleBean;
import com.cx.chenxing.user.UserService;
import com.cx.chenxing.user.result.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页相关
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;

    /**
     * 系统首页
     * @return
     */
    @RequestMapping("/index")
    public String index(@ModelAttribute("messge") String message, HttpServletRequest request, Model model) {
        ArticleQuery articleQuery = new ArticleQuery();
        List<ArticleBean> articleList = articleService.queryarticle(articleQuery).getResult();
        //更新阅读数------
        List<Long> articleIds = new ArrayList<>();
        articleList.stream().forEach(ac -> {
            articleIds.add(ac.getId());
        });
        articleService.updateYdNum(articleIds);
        //更新阅读数------end
        model.addAttribute("articleList", articleList);
        model.addAttribute("messge", message);
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            UserBean users = userService.selectByPrimaryKey(user.getId());
            model.addAttribute("user", users);
        }else{
            model.addAttribute("user", user);
        }
        return "frontpages/index";
    }
}
