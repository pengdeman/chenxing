package com.cx.chenxing.controller;

import com.cx.chenxing.article.ArticleService;
import com.cx.chenxing.article.result.ArticleBean;
import com.cx.chenxing.user.entity.User;
import com.cx.chenxing.user.result.UserBean;
import com.cx.chenxing.utils.NormalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 文章相关
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("/submitarticle")
    public String submitArticle(HttpServletRequest request, Model model){
        MultipartHttpServletRequest reques = (MultipartHttpServletRequest) request;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String article = request.getParameter("article");//文字
        String location = request.getParameter("location");//位置
        String lng = request.getParameter("lng");//经度
        String lat = request.getParameter("lat");//纬度
        String show = request.getParameter("show");//是否可见 1公开  2仅自己可见
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        ArticleBean articleBean = new ArticleBean();
        articleBean.setArticle(article);
        articleBean.setCreTime(sdf.format(new Date()));
        articleBean.setLng(lng);
        articleBean.setLat(lat);
        articleBean.setLocation(location);
        if("公开".equals(show)){
            articleBean.setShows("1");
        }else if("仅自己可见".equals(show)){
            articleBean.setShows("2");
        }
        if(user != null){
            articleBean.setCreUid(user.getId());
        }else{
            model.addAttribute("messge", "登录超时，请重新登陆！");
            return "frontpages/index";
        }
        String[] types = {"JPG", "PNG", "JPEG", "GIF", "JEPG"};
        try {
            String[] newfileName = NormalUtils.syqpicdownUrl(reques, "picurl",articleBean.getPicurl());
             if (newfileName != null) {
                //判断图片类型
                if (!Arrays.asList(types).contains(newfileName[0].substring(newfileName[0].lastIndexOf(".")+1).toUpperCase())) {
                    model.addAttribute("messge", "照片格式只支持png、jpg、jpeg、gif！");
                    return "frontpages/index";
                }
                articleBean.setPicurl(newfileName[1]);
                //articleBean.setPictruename(newfileName[0]);
            } else {
                model.addAttribute("messge", "发布信息失败，请上传您的照片！");
                return "frontpages/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        articleService.insert(articleBean);
        model.addAttribute("messge", "发布成功！");
        return "frontpages/index";
    }

}
