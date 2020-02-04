package com.cx.chenxing.controller;

import com.cx.chenxing.article.ArticleService;
import com.cx.chenxing.article.param.ArticleQuery;
import com.cx.chenxing.article.result.ArticleBean;
import com.cx.chenxing.mybatisutils.Page;
import com.cx.chenxing.user.UserService;
import com.cx.chenxing.user.result.UserBean;
import com.cx.chenxing.userzan.UserZanService;
import com.cx.chenxing.userzan.param.UserZanQuery;
import com.cx.chenxing.userzan.result.UserZanBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Resource
    private UserZanService userZanService;

    /**
     * 系统首页
     * @return
     */
    @RequestMapping("/")
    public String systemin(@ModelAttribute("messge") String message, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        ArticleQuery articleQuery = new ArticleQuery();
        List<ArticleBean> articleList = articleService.queryarticle(articleQuery).getResult();
        //更新阅读数------
        List<Long> articleIds = new ArrayList<>();
        articleList.stream().forEach(ac -> {
            articleIds.add(ac.getId());
            if(Objects.nonNull(user)){
                UserZanQuery userZanQuery = new UserZanQuery();
                userZanQuery.setZanArticleId(ac.getId());
                userZanQuery.setZanUid(user.getId());
                Page<UserZanBean> uzan = userZanService.query(userZanQuery);
                if(uzan.getResult().size() > 0){
                    ac.setIszan("1");
                }else{
                    ac.setIszan("0");
                }
            }
        });
        articleService.updateYdNum(articleIds);
        //更新阅读数------end
        model.addAttribute("articleList", articleList);
        model.addAttribute("messge", message);
        if(user != null){
            UserBean users = userService.selectByPrimaryKey(user.getId());
            model.addAttribute("user", users);
        }else{
            model.addAttribute("user", user);
        }
        return "frontpages/index";
    }
}
