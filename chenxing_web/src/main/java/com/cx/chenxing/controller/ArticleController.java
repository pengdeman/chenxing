package com.cx.chenxing.controller;

import com.cx.chenxing.model.article.result.ArticleBean;
import com.cx.chenxing.model.articlereply.param.ArticleReplyQuery;
import com.cx.chenxing.model.articlereply.result.ArticleReplyBean;
import com.cx.chenxing.model.user.result.UserBean;
import com.cx.chenxing.model.userzan.param.UserZanQuery;
import com.cx.chenxing.model.userzan.result.UserZanBean;
import com.cx.chenxing.service.article.ArticleService;
import com.cx.chenxing.service.articlereply.ArticleReplyService;
import com.cx.chenxing.service.user.UserService;
import com.cx.chenxing.service.userzan.UserZanService;
import com.cx.chenxing.utils.GetSysUrlUtil;
import com.cx.chenxing.utils.JsonUtil;
import com.cx.chenxing.utils.MailUtil;
import com.cx.chenxing.utils.NormalUtils;
import com.cx.chenxing.utils.mybatisutils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文章相关
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleReplyService articleReplyService;
    @Resource
    private UserZanService userZanService;
    @Resource
    private UserService userService;


    @RequestMapping("/submitarticle")
    public String submitArticle(HttpServletRequest request, RedirectAttributes attributes){
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
        articleBean.setZfnum(0);
        articleBean.setPlnum(0);
        articleBean.setYdnum(0);
        articleBean.setDznum(0);
        if("公开".equals(show)){
            articleBean.setShows("1");
        }else if("仅自己可见".equals(show)){
            articleBean.setShows("2");
        }
        if(user != null){
            articleBean.setCreUid(user.getId());
        }else{
            attributes.addAttribute("messge", "登录超时，请重新登陆！");
            return "redirect:/";
        }
        String[] types = {"JPG", "PNG", "JPEG", "GIF", "JEPG"};
        try {
            String[] newfileName = NormalUtils.syqpicdownUrl(reques, "picurl",articleBean.getPicurl());
            if (newfileName != null) {
                //判断图片类型
                if (!Arrays.asList(types).contains(newfileName[0].substring(newfileName[0].lastIndexOf(".")+1).toUpperCase())) {
                    attributes.addAttribute("messge", "照片格式只支持png、jpg、jpeg、gif！");
                    return "redirect:/";
                }
                articleBean.setPicurl(newfileName[1]);
                //articleBean.setPictruename(newfileName[0]);
            } else {
                attributes.addAttribute("messge", "发布信息失败，请上传您的照片！");
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        articleService.insert(articleBean);
        attributes.addAttribute("messge", "发布成功！");
        return "redirect:/";
    }

    /**
     * 查看文章详情
     * @return
     */
    @RequestMapping("/articledetail")
    public String articleDetail(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user != null){
            model.addAttribute("userId", user.getId());
        }else{
            model.addAttribute("userId", "");
        }
        String articleId = request.getParameter("id");
        //查询文章信息
        ArticleBean article = articleService.selectByPrimaryKey(Long.parseLong(articleId));
        if(user != null){
            UserZanQuery userZanQuery = new UserZanQuery();
            userZanQuery.setZanArticleId(article.getId());
            userZanQuery.setZanUid(user.getId());
            List<UserZanBean> uzan = userZanService.query(userZanQuery).getResult();
            if(uzan.size()>0){
                article.setIszan("1");
            }else{
                article.setIszan("0");
            }
        }
        //查询文章回复信息
        ArticleReplyQuery articleReplyQuery = new ArticleReplyQuery();
        articleReplyQuery.setArticleId(Long.parseLong(articleId));
        List<ArticleReplyBean> articleReplyList = articleReplyService.queryreply(articleReplyQuery);
        if(user != null){
            for(int i=0; i<articleReplyList.size(); i++){
                UserZanQuery userZanQuery = new UserZanQuery();
                userZanQuery.setZanArticleRid(articleReplyList.get(i).getId());
                userZanQuery.setZanUid(user.getId());
                List<UserZanBean> uzan = userZanService.query(userZanQuery).getResult();
                if(uzan.size()>0){
                    articleReplyList.get(i).setIszan("1");
                }else{
                    articleReplyList.get(i).setIszan("0");
                }
                UserBean replyUser = userService.selectByPrimaryKey(articleReplyList.get(i).getReplyUid());
                UserBean breplyUser = userService.selectByPrimaryKey(articleReplyList.get(i).getBreplyUid());
                if(Objects.nonNull(replyUser)){
                    articleReplyList.get(i).setReplyUname(replyUser.getUserName());
                    articleReplyList.get(i).setReplyImg(replyUser.getImg());
                }
                if(Objects.nonNull(breplyUser)){
                    articleReplyList.get(i).setBreplyUname(breplyUser.getUserName());
                    articleReplyList.get(i).setBreplyImg(breplyUser.getImg());
                }
            }
        }
        model.addAttribute("article", article);
        model.addAttribute("articleReplyList", articleReplyList);
        return "frontpages/articledetail";
    }

    /**
     * 异步提交回复
     * @param request
     * @param response
     * @param model
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/ajaxSaveReply")
    public String ajaxSaveReply(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String url = GetSysUrlUtil.geturl(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> m = new HashMap<>();
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user==null){
            m.put("success", 0);
        }else{
            String replyContent = request.getParameter("replyContents");//回复内容
            String articleId = request.getParameter("articleId");//文章id
            String breplyId = request.getParameter("breplyId");//被回复人id
            ArticleReplyBean articleReplyBean = new ArticleReplyBean();
            articleReplyBean.setArticleId(Long.parseLong(articleId));
            articleReplyBean.setReplyUid(user.getId());
            articleReplyBean.setBreplyUid(Long.parseLong(breplyId));
            articleReplyBean.setReplyTime(sdf.format(new Date()));
            articleReplyBean.setReplyComment(replyContent);
            articleReplyBean.setReplyPid(0L);//点赞数
            articleReplyService.insert(articleReplyBean);

            ArticleBean articleBean = articleService.selectByPrimaryKey(Long.parseLong(articleId));
            if(articleBean != null){
                Integer z = articleBean.getPlnum() + 1;
                articleBean.setPlnum(z);
                articleService.updateSelective(articleBean);
            }

            //发送邮件
            try {
                String mailHost = "smtp.163.com";//
                String sender_username = "penderman@163.com";
                String sender_password = "p920521";
                String[] toUser = new String[]{user.getMail()};
                MailUtil se = new MailUtil(mailHost, sender_username, sender_password, false);
                se.doSendHtmlEmail("辰星", "［辰星］ <div>您在辰星上收到了回复，请点击<a href='"+url+"/article/articledetail?id="+articleId+"'>www.pengyingjun.com</a>查看评论详情！</div>", toUser, null);
            } catch (Exception e) {
                model.addAttribute("messge", "邮箱格式异常，请检查邮箱准确性！");
            }
            m.put("success", 1);
            ArticleReplyBean ff = articleReplyService.selectByPrimaryKey(articleReplyBean.getId());
            UserBean replyUser = userService.selectByPrimaryKey(ff.getReplyUid());
            UserBean breplyUser = userService.selectByPrimaryKey(ff.getBreplyUid());
            ff.setReplyUname(replyUser.getUserName());
            ff.setReplyImg(replyUser.getImg());
            ff.setBreplyUname(breplyUser.getUserName());
            ff.setBreplyImg(breplyUser.getImg());
            m.put("articleReply", ff);
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(JsonUtil.toJson(m));
        pw.flush();
        return null;
    }

    /**
     * 删除文章
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/deletearticle")
    public String deletearticle(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        articleService.delete(Long.parseLong(id));
        articleReplyService.deleteByArticleId(Long.parseLong(id));
        return "redirect:/";
    }

    /**
     * 删除文章评论
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/deletearticlepl")
    public void deletearticlepl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> m = new HashMap<>();
        String id = request.getParameter("id");
        String articleId = request.getParameter("articleId");
        if(id.length() > 0 && articleId.length() > 0){
            ArticleBean articleBean = articleService.selectByPrimaryKey(Long.parseLong(articleId));
            articleBean.setPlnum(articleBean.getPlnum()-1);
            articleService.updateSelective(articleBean);
            articleReplyService.delete(Long.parseLong(id));
            m.put("flag", 1);//删除成功
        }else{
            m.put("flag", 2);//删除失败
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(JsonUtil.toJson(m));
        pw.flush();
    }

    /**
     * 评论点赞
     * @return
     * @throws IOException
     */
    @RequestMapping("/zans")
    public void zans(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> m = new HashMap<>();
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user == null){
            m.put("num", 20000000);
        }else{
            String id = request.getParameter("id");

            UserZanQuery userZanQuery = new UserZanQuery();
            userZanQuery.setZanUid(user.getId());
            userZanQuery.setZanArticleRid(Long.parseLong(id));
            Page<UserZanBean> uzan = userZanService.query(userZanQuery);
            if(uzan.getResult().size() > 0){
                m.put("num", 10000000);
            }else{
                UserZanBean userZanBean = new UserZanBean();
                userZanBean.setZanUid(user.getId());
                userZanBean.setZanArticleRid(Long.parseLong(id));
                userZanBean.setZanTime(new Date());
                userZanService.insert(userZanBean);

                ArticleReplyBean articleReplyBean = articleReplyService.selectByPrimaryKey(Integer.parseInt(id));
                articleReplyBean.setReplyPid(articleReplyBean.getReplyPid()+1);
                articleReplyService.updateSelective(articleReplyBean);
                m.put("num", articleReplyBean.getReplyPid());
            }
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(JsonUtil.toJson(m));
        pw.flush();
    }

    /**
     * 文章点赞
     * @return
     * @throws IOException
     */
    @RequestMapping("/zan")
    public void zan(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> m = new HashMap<>();
        HttpSession session = request.getSession();
        UserBean user= (UserBean) session.getAttribute("user");
        if(user == null){
            m.put("num", 20000000);
        }else{
            String id = request.getParameter("id");

            UserZanQuery userZanQuery = new UserZanQuery();
            userZanQuery.setZanUid(user.getId());
            userZanQuery.setZanArticleId(Long.parseLong(id));
            Page<UserZanBean> uzanlist = userZanService.query(userZanQuery);
            if(uzanlist.getResult().size() > 0){
                m.put("num", 10000000);
            }else{
                UserZanBean userZanBean = new UserZanBean();
                userZanBean.setZanUid(user.getId());
                userZanBean.setZanArticleId(Long.parseLong(id));
                userZanBean.setZanTime(new Date());
                userZanService.insert(userZanBean);

                ArticleBean articleBean = articleService.selectByPrimaryKey(Long.parseLong(id));
                articleBean.setDznum(articleBean.getDznum()+1);
                articleService.updateSelective(articleBean);
                m.put("num", articleBean.getDznum());
            }
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(JsonUtil.toJson(m));
        pw.flush();
    }

}
