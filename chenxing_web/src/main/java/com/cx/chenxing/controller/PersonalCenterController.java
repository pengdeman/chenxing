package com.cx.chenxing.controller;

import com.cx.chenxing.model.article.param.ArticleQuery;
import com.cx.chenxing.model.article.result.ArticleBean;
import com.cx.chenxing.model.user.result.UserBean;
import com.cx.chenxing.service.article.ArticleService;
import com.cx.chenxing.service.user.UserService;
import com.cx.chenxing.utils.NormalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * 个人中心相关
 */
@Controller
@RequestMapping("/personalcenter")
public class PersonalCenterController {

    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;

    /**
     * 个人主页
     * @return
     */
    @RequestMapping("/myselfindex")
    public String myselfindex(HttpServletRequest request, Model model, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            UserBean users = userService.selectByPrimaryKey(user.getId());
            model.addAttribute("user", users);
            ArticleQuery articleQuery = new ArticleQuery();
            articleQuery.setCreUid(user.getId());
            articleQuery.setShows("1");
            articleQuery.setSortColumns("cre_time desc");
            List<ArticleBean> articleList = articleService.queryarticle(articleQuery).getResult();
            model.addAttribute("articleList", articleList);
        }else{
            attributes.addAttribute("messge", "登录超时，请重新登陆！");
            return "redirect:/index";
        }
        return "frontpages/myselfindex";
    }

    /**
     * 个人档案首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(@ModelAttribute("messge") String message, HttpServletRequest request,
                        Model model, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            UserBean users = userService.selectByPrimaryKey(user.getId());
            model.addAttribute("user", users);
            model.addAttribute("messge", message);
        }else{
            attributes.addAttribute("messge", "登录超时，请重新登陆！");
            return "redirect:/";
        }
        return "frontpages/personalcenter";
    }

    /**
     * 修改信息
     * @param request
     * @param attributes
     * @return
     */
    @RequestMapping("/modifyinfo")
    public String modifyinfo(HttpServletRequest request, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            UserBean u = userService.selectByPrimaryKey(user.getId());
            u.setUserName(request.getParameter("user_name"));
            u.setAdress(request.getParameter("adress")==""?null:request.getParameter("adress"));
            u.setSex(request.getParameter("sex")==""?null:request.getParameter("sex"));
            u.setPhone(request.getParameter("phone")==""?null:request.getParameter("phone"));
            u.setBirthday(request.getParameter("birthday")==""?null:request.getParameter("birthday"));
            u.setSignature(request.getParameter("signature")==""?null:request.getParameter("signature"));
            if(request.getParameter("age") != null && !"".equals(request.getParameter("age"))){
                u.setAge(Integer.parseInt(request.getParameter("age")));
            }else{
                u.setAge(null);
            }
            userService.update(u);
        }else{
            attributes.addAttribute("messge", "登录超时，请重新登陆！");
            return "redirect:/";
        }
        return "redirect:/personalcenter/index";
    }

    /**
     * 修改头像
     * @param request
     * @param attributes
     * @return
     */
    @RequestMapping("/modifypic")
    public String modifyPic(HttpServletRequest request, RedirectAttributes attributes){
        MultipartHttpServletRequest reques = (MultipartHttpServletRequest) request;
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            UserBean u = userService.selectByPrimaryKey(user.getId());
            String[] types = {"JPG", "PNG", "JPEG", "GIF", "JEPG"};
            try {
                String[] newfileName = NormalUtils.syqpicdownUrl(reques, "picurl",u.getImg());
                if (newfileName != null) {
                    //判断图片类型
                    if (!Arrays.asList(types).contains(newfileName[0].substring(newfileName[0].lastIndexOf(".")+1).toUpperCase())) {
                        attributes.addAttribute("messge", "照片格式只支持png、jpg、jpeg、gif！");
                        return "redirect:/personalcenter/index";
                    }
                    u.setImg(newfileName[1]);
                } else {
                    attributes.addAttribute("messge", "发布信息失败，请上传您的照片！");
                    return "redirect:/personalcenter/index";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            userService.updateSelective(u);
        }else{
            attributes.addAttribute("messge", "登录超时，请重新登陆！");
            return "redirect:/";
        }

        return "redirect:/personalcenter/index";
    }

}
