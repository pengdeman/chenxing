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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@Controller+@ResponseBody相当于@RestController
@Controller
@RequestMapping("/")
public class UserController {
	
	@Resource
    private UserService userService;

	/**
	 * 系统首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "frontpages/index";
	}

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
		String nowtime = sdfs.format(new Date());
		String loginName = request.getParameter("username");
		String password = request.getParameter("password");
		UserBean loginUser = userService.findLoginUserByLoginNameAndPassword(loginName,MD5Util.MD5(password, ""));
		if(loginUser!=null && "1".equals(loginUser.getActivate())){
			String logintime = sdfs.format(loginUser.getLoginTime());
			if(!logintime.equals(nowtime)){
				loginUser.setAccountLevel(loginUser.getAccountLevel()+1);
			}
			loginUser.setLoginTime(new Date());
			userService.updateSelective(loginUser);

			request.getSession().setAttribute("user", loginUser);//得到当前用户的session，需要先创建一个
			model.addAttribute("user", loginUser);
			HttpSession session = request.getSession();
			session.setAttribute("login", loginUser);

			return "frontpages/index";
		}else if(loginUser!=null && "0".equals(loginUser.getActivate())){
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
		lg.setCreTime(new Date());
		lg.setLoginTime(new Date());
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
			se.doSendHtmlEmail("恭喜您！辰星上的家已经搭建完成啦！", "［辰星］<div>亲爱的辰星家人-"+userName+"，您好！恭喜您辰星账号已注册成功！<a href='"+url+"locationactivate?mail="+mail+"'>请点击</a>激活该账号！</div>", toUser, null);
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


}
