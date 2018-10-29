package com.cx.chenxing.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cx.chenxing.user.entity.User;
import com.cx.chenxing.user.param.UserQuery;
import com.cx.chenxing.user.result.UserBean;
import com.cx.chenxing.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cx.chenxing.user.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户相关
 */
//@Controller+@ResponseBody相当于@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/locationsign")
    public String locationsign(HttpServletRequest request, HttpServletResponse response, Model model){
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = sdfs.format(new Date());
        String loginName = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean userBean = new UserBean();
        userBean.setPassword(MD5Util.MD5(password, ""));
        userBean.setAccount(loginName);
        List<UserBean> ulist = userService.query(userBean).getResult();
        if(ulist.size() > 0 && "1".equals(ulist.get(0).getActivate())){
            UserBean loginUser = ulist.get(0);
            String logintime = sdfs.format(loginUser.getLoginTime());
            if(!logintime.equals(nowtime)){
                loginUser.setAccountLevel(loginUser.getAccountLevel()+1);
            }
            loginUser.setLoginTime(sdf.format(new Date()));
            userService.updateSelective(loginUser);
            HttpSession session = request.getSession();//得到当前用户的session，需要先创建一个
            session.setAttribute("user", loginUser);

            return "frontpages/index";
        }else if(ulist.size() > 0 && "0".equals(ulist.get(0).getActivate())){
            model.addAttribute("messge", "您的账号还未激活，请激活后登录！");
            return "frontpages/index";
        }else{
            model.addAttribute("messge", "密码或登录名错误，请检查后重新输入！");
            return "frontpages/index";
        }
    }


    /**
     * 用户注册
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/locationreg")
    @Transactional(rollbackFor = Exception.class)
    public String locationreg(HttpServletRequest request, Model model){
        String url = GetBaseUrl.geturl(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String userName = request.getParameter("signup_name");//昵称
        String mail = request.getParameter("signup_email");//邮箱
        String password = request.getParameter("signup_password");//密码
        boolean isNum = userName.matches("[0-9]+");//+表示1个搜索或多个（如"3"或"225"），*表示0个或多个（[0-9]*）（如""或"1"或"22"），?表示0个或1个([0-9]?)(如""或"7")
        if(isNum){
            model.addAttribute("messge", "您的大名不能都为数字！");
            return "location/locationlogin";
        }
        UserQuery uQuery = new UserQuery();
        uQuery.setAccount(mail);
        uQuery.setMail(userName);
        List<User> ls = userService.queryAccount(uQuery);
        if(ls.size()>0){
            model.addAttribute("messge", "昵称或邮箱已存在，请重新输入！");
            return "frontpages/index";
        }
        if(!CheckMail.checkEmail(mail)){
            model.addAttribute("messge", "邮箱格式有问题，请重新输入！");
            return "frontpages/index";
        }
        UserBean lg = new UserBean();
        lg.setUserName(userName);
        lg.setMail(mail);
        lg.setAccount(mail);
        lg.setPassword(MD5Util.MD5(password, ""));
        String d = sdf.format(new Date());
        lg.setCreTime(d);
        lg.setLoginTime(d);
        lg.setActivate("0");
        lg.setAccountLevel(1);
        userService.insert(lg);
        //发送邮件
        try {
            String mailHost = "smtp.163.com";
            String sender_username = "penderman@163.com";
            String sender_password = "p920521";
            String[] toUser = new String[]{mail};
            MailUtil se = new MailUtil(mailHost, sender_username, sender_password, false);
            se.doSendHtmlEmail("恭喜您！辰星上的家已经搭建完成啦！", "［辰星］<div>亲爱的辰星家人-"+userName+"，您好！恭喜您辰星账号已注册成功！<a href='"+url+"user/locationactivate?mail="+mail+"'>请点击</a>激活该账号！</div>", toUser, null);
            model.addAttribute("messge", "注册成功！请登录注册邮箱激活账号！(注：如未收到邮件，可能被识别为垃圾邮件了，请到邮箱垃圾箱中查看，并设置为这不是垃圾邮件！)");
        } catch (Exception e) {
            model.addAttribute("messge", "邮箱格式异常，请检查邮箱准确性！");
        }
        return "frontpages/index";
    }


    /**
     * 激活账号
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/locationactivate")
    public String locationactivate(HttpServletRequest request,Model model) throws Exception {
        String mail = request.getParameter("mail");
        UserQuery uQuery = new UserQuery();
        uQuery.setAccount(mail);
        List<User> user = userService.queryAccount(uQuery);
        if(user.size()>0){
            List<UserBean> ub = ModelUtil.copyList(user, UserBean.class);
            ub.get(0).setActivate("1");
            userService.updateSelective(ub.get(0));
        }
        return "frontpages/activesuccess";
    }


    /**
     * 发送重置密码的邮箱验证码
     * @param request
     * @param response
     */
    @RequestMapping("/sendcode")
    public void sendCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> m = new HashMap<>();
        String usermail = request.getParameter("usermail");
        if(usermail.length() < 0){
            m.put("mes",1);
        }else{
            UserBean ub = new UserBean();
            ub.setAccount(usermail);
            List<UserBean> ulist = userService.query(ub).getResult();
            if(ulist.size() == 0){
                m.put("mes",2);
            }else{
                //生成随机码
                String mailcode = (int)((Math.random()*9+1)*1000)+"";
                //发送邮件
                try {
                    String mailHost = "smtp.163.com";
                    String sender_username = "penderman@163.com";
                    String sender_password = "p920521";
                    String[] toUser = new String[]{usermail};
                    MailUtil se = new MailUtil(mailHost, sender_username, sender_password, false);
                    se.doSendHtmlEmail("您好！收到了辰星家园的来信！", "［辰星］<div>亲爱的辰星家人，您好！您的验证码为：【"+mailcode+"】！</div>", toUser, null);
                    m.put("mes",3);
                } catch (Exception e) {
                    m.put("mes",4);
                }
                HttpSession session = request.getSession();
                session.setAttribute("mailcode", mailcode);
            }
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(JsonUtil.toJson(m).toString());
        pw.flush();
    }

    /**
     * 修改密码
     * @param request
     * @param response
     */
    @RequestMapping("/modifypassword")
    public void modifyPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> m = new HashMap<>();
        String usermail = request.getParameter("usermail");
        String checkma = request.getParameter("checkma");
        String newpassword = request.getParameter("newpassword");
        HttpSession session = request.getSession();
        String mailcode = (String)session.getAttribute("mailcode");
        if(mailcode.length()<0){
            m.put("flag", 1);
        }else if(mailcode.equals(checkma)){
            UserBean ub = new UserBean();
            ub.setAccount(usermail);
            List<UserBean> ulist = userService.query(ub).getResult();
            if(ulist.size() < 0){
                m.put("flag", 2);
            }else{
                UserBean u = new UserBean();
                u.setPassword(MD5Util.MD5(newpassword, ""));
                userService.updateSelective(u);
                m.put("flag", 4);
            }
        }else{
            m.put("flag", 3);
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(JsonUtil.toJson(m).toString());
        pw.flush();
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "frontpages/index";
    }

}