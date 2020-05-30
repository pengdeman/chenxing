package com.cx.chenxing.controller.zuji;

import com.cx.chenxing.db.zj.entity.*;
import com.cx.chenxing.service.zj.*;
import com.cx.chenxing.utils.MailUtil;
import com.cx.chenxing.utils.zj.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class LocationController {
	public static Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    @Resource  
    private IUserService userService;
    @Resource  
    private IFootprintService footprintService;
    @Resource  
    private IFootprintReplyService footprintReplyService;
    @Resource  
    private IVisitorService visitorService;
    @Resource  
    private IUzanService uzanService;
    @Resource  
    private IWzService wzService;
    
    
    @RequestMapping("/btc")
	public String btc(HttpServletRequest request){
		return "xnb/xnbindex";
	}
    
    @RequestMapping("/querybtc")
    public String querybtc(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
    	String url = "https://www.binance.com/api/v1/ticker/allPrices";
        String json = loadJSON(url);
    	Map<String, Object> m = new HashMap<>();
    	m.put("json", json);
		response.setCharacterEncoding("UTF-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(m).toString());
    	pw.flush();
        return null;
    }
    
    //股票查询－－－－－－－－－－－start－－－－－－－－－－
    @RequestMapping("/jjuummpp")
	public String jjuummpp(HttpServletRequest request){
		return "location/jujuetixing";
	}
    
    /**
     * 股票助手
     * @param request
     * @return
     */
    @RequestMapping("/shares")
	public String shares(HttpServletRequest request){
		return "gupiao/jump";
	}

    @RequestMapping("/share")
	public String share(HttpServletRequest request){
		return "gupiao/share";
	}
    
    @RequestMapping("/sharess")
	public String sharess(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
    	String codes = request.getParameter("code");
    	if (codes.startsWith("00")) {
    		codes = "sz"+codes;
    	}else {
    		codes = "sh"+codes;
    	}
    	String url = "http://hq.sinajs.cn/list="+codes;
        String json = loadJSON(url);
    	Map<String, Object> m = new HashMap<>();
    	m.put("json", json);
		response.setCharacterEncoding("UTF-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(m).toString());
    	pw.flush();
        return null; 
	}
    
    public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"GBK"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
    /**
     * 网站入口
     * @return
     */
	@RequestMapping("/location")
	public String indexhtml(HttpServletRequest request){
		HttpSession session = request.getSession();
        session.removeAttribute("login");
		return "location/locationlogin";
	}
	
	/**
	 * 全球定位
	 * @param request
	 * @return
	 */
	@RequestMapping("/globallocation")
	public String globallocation(HttpServletRequest request, HttpServletResponse response, Model model){
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user == null){
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
    	Wz lg = new Wz();
    	lg.setName(user.getMail());
    	List<Wz> wzs = wzService.wzListbymail(lg);
    	model.addAttribute("ls", wzs);
    	model.addAttribute("mail", user.getMail());
		return "location/globallocation";
	}
	
	@RequestMapping("/getfjr")
    public String getfjr(HttpServletRequest request, HttpServletResponse response, Model model){ 
    	model.addAttribute("mail", request.getParameter("mail"));
        return "location/fjr";  
    }
	
	/**
	 * 足迹圈删除某条足迹记录
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/deletezj")
	public String deletezj(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user == null){
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
		String id = request.getParameter("id");
		footprintService.deleteByPrimaryKey(Integer.parseInt(id));
		List<FootprintReply> replyList = footprintReplyService.replyList(Integer.parseInt(id));
		for(int i=0; i<replyList.size(); i++){
			footprintReplyService.deleteByPrimaryKey(replyList.get(i).getReplyId());
		}
		//return "redirect:indexs";
		Map<String, Object> m = new HashMap<>();
    	m.put("mess", "删除成功！");
    	response.setCharacterEncoding("utf-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(m).toString());
    	pw.flush();
        return null;
	}
	
	/**
	 * 个人主页删除足迹记录
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deletepoint")
	public String deletepoint(HttpServletRequest request, Model model) throws IOException{
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user == null){
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
		String id = request.getParameter("id");
		footprintService.deleteByPrimaryKey(Integer.parseInt(id));
		List<FootprintReply> replyList = footprintReplyService.replyList(Integer.parseInt(id));
		for(int i=0; i<replyList.size(); i++){
			footprintReplyService.deleteByPrimaryKey(replyList.get(i).getReplyId());
		}
		return "redirect:indexs";
	}
	
	/**
	 * 点赞
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/zan")
	public String zan(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> m = new HashMap<>();
		HttpSession session = request.getSession();
		User user= (User)session.getAttribute("login");
		if(user == null){
			m.put("num", 20000000);
		}else{
			String id = request.getParameter("id");

			Uzan uzs = new Uzan();
			uzs.setZanUid(user.getId());
			uzs.setFootprintId(Integer.parseInt(id));
			List<Uzan> uzanlist = uzanService.queryzanlist(uzs);

			if(uzanlist.size() > 0){
				m.put("num", 10000000);
			}else{
				Uzan uz = new Uzan();
				uz.setZanUid(user.getId());
				uz.setFootprintId(Integer.parseInt(id));
				uz.setZanTime(sf.format(new Date()));
				uzanService.insert(uz);

				Footprint f = footprintService.getFootprintById(Integer.parseInt(id));
				f.setClickNum(f.getClickNum()+1);
				footprintService.updateSelective(f);
				m.put("num", f.getClickNum());
			}
		}

		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(JsonUtil.toJson(m).toString());
		pw.flush();
		return null;
	}
	/**
	 * 评论点赞
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/zans")
	public String zans(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> m = new HashMap<>();
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user == null){
    		m.put("num", 20000000);
		}else{
			String id = request.getParameter("id");
			
			Uzan uzs = new Uzan();
			uzs.setZanUid(user.getId());
			uzs.setFootprintRid(Integer.parseInt(id));
			List<Uzan> uzanlist = uzanService.queryzanlist(uzs);
			
			if(uzanlist.size() > 0){
				m.put("num", 10000000);
			}else{
				
				Uzan uz = new Uzan();
				uz.setZanUid(user.getId());
				uz.setFootprintRid(Integer.parseInt(id));
				uz.setZanTime(sf.format(new Date()));
				uzanService.insert(uz);
				
				FootprintReply footprintReply = footprintReplyService.selectByPrimaryKey(Integer.parseInt(id));
				footprintReply.setReplyParentId(footprintReply.getReplyParentId()+1);
				footprintReplyService.updateSelective(footprintReply);
				m.put("num", footprintReply.getReplyParentId());
			}
		}
		
		response.setCharacterEncoding("utf-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(m).toString());
    	pw.flush();
        return null; 
	}
    /**
     * 新增足迹入口
     * @return
     */
	@RequestMapping("/jumpnew")
	public String jumpnew(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user != null){
    		return "location/locationinfo";
		}else{
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
	}
	
	/**
	 * 足迹圈入口
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/jumppeople")
	public String jumppeople(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user != null){
    		model.addAttribute("userId", user.getId());
		}else{
			model.addAttribute("userId", "");
//			model.addAttribute("messge", "登录超时，请重新登陆！");
//			return "location/locationlogin";
		}
    	Footprint f = new Footprint();
    	f.setBeginNum(0);
    	f.setEndNum(10);
		List<Footprint> flist = footprintService.fList(f);
		if(user != null){
			for(int i=0; i<flist.size(); i++){
				Uzan uz = new Uzan();
				uz.setFootprintId(flist.get(i).getId());
				uz.setZanUid(user.getId());
				List<Uzan> uzan = uzanService.queryzanlist(uz);
				if(uzan.size()>0){
					flist.get(i).setIszan("1");
				}else{
					flist.get(i).setIszan("0");
				}
			}
		}
		model.addAttribute("flist", flist);
    	return "location/locationshowlist";
	}
	
	/**
	 * 获取某个足迹详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotolocationdetails")
	public String gotolocationdetails(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user != null){
    		model.addAttribute("userId", user.getId());
		}else{
			model.addAttribute("userId", "");
		}
    	String footprintId = request.getParameter("footprintId");
		Footprint f = footprintService.selectByPrimaryKey(Integer.parseInt(footprintId));
		if(user != null){
			Uzan uz = new Uzan();
			uz.setFootprintId(f.getId());
			uz.setZanUid(user.getId());
			List<Uzan> uzan = uzanService.queryzanlist(uz);
			if(uzan.size()>0){
				f.setIszan("1");
			}else{
				f.setIszan("0");
			}
		}
		List<FootprintReply> footprints = footprintReplyService.replyList(Integer.parseInt(footprintId));//查询一级评论
		if(user != null){
			for(int i=0; i<footprints.size(); i++){
				Uzan uz = new Uzan();
				uz.setFootprintRid(footprints.get(i).getReplyId());
				uz.setZanUid(user.getId());
				List<Uzan> uzan = uzanService.queryzanlist(uz);
				if(uzan.size()>0){
					footprints.get(i).setIszan("1");
				}else{
					footprints.get(i).setIszan("0");
				}
			}
		}
		model.addAttribute("footprintId", footprintId);
		model.addAttribute("f", f);
		model.addAttribute("footprints", footprints);
		return "location/locationdetails";
	}
	
	/**
	 * 异步加载大家足迹列表
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getpeople")
	public String getpeople(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user != null){
    		model.addAttribute("userId", user.getId());
		}else{
			model.addAttribute("userId", "");
		}
    	Integer beginNum = Integer.parseInt(request.getParameter("beginNum"));
    	Integer endNum = Integer.parseInt(request.getParameter("endNum"));
    	Footprint f = new Footprint();
    	f.setBeginNum(beginNum);
    	f.setEndNum(endNum);
		List<Footprint> flist = footprintService.fList(f);
		if(user != null){
			for(int i=0; i<flist.size(); i++){
				Uzan uz = new Uzan();
				uz.setFootprintId(flist.get(i).getId());
				uz.setZanUid(user.getId());
				List<Uzan> uzan = uzanService.queryzanlist(uz);
				if(uzan.size()>0){
					flist.get(i).setIszan("1");
				}else{
					flist.get(i).setIszan("0");
				}
			}
		}
		response.setCharacterEncoding("utf-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(flist).toString());
    	pw.flush();
        return null;
	}
	
	/**
	 * 更新转发
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	 @RequestMapping("/updatezf")
	    public String updatezf(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{ 
		 	Map<String, Object> m = new HashMap<>();	
		 	HttpSession session = request.getSession();
	    	User user= (User)session.getAttribute("login");
	    	if(user == null){
				m.put("messge", 1);
				response.setCharacterEncoding("utf-8");
		    	PrintWriter pw = response.getWriter();
		    	pw.print(JsonUtil.toJson(m).toString());
		    	pw.flush();
				return null;
			}
	    	String id = request.getParameter("id");
	    	//先更新评论条数
	    	Footprint footprint = footprintService.selectByPrimaryKey(Integer.parseInt(id));
	    	Integer z = 0;
	    	if(footprint != null){
	    		z = footprint.getZfNum() + 1;
	    		footprint.setZfNum(z);
	    		footprintService.updateSelective(footprint);
	    	}
	    	m.put("num", z);
	    	response.setCharacterEncoding("utf-8");
	    	PrintWriter pw = response.getWriter();
	    	pw.print(JsonUtil.toJson(m).toString());
	    	pw.flush();
	        return null;
	    } 
	
	/**
	 * 删除评论
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	 @RequestMapping("/deletefoot")
	    public String deletefoot(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{ 
	    	HttpSession session = request.getSession();
	    	User user= (User)session.getAttribute("login");
	    	if(user == null){
				model.addAttribute("messge", "登录超时，请重新登陆！");
				return "location/locationlogin";
			}
	    	String id = request.getParameter("id");
	    	//先更新评论条数
	    	FootprintReply footprintReply = footprintReplyService.selectByPrimaryKey(Integer.parseInt(id));
	    	Footprint footprint = footprintService.selectByPrimaryKey(footprintReply.getFootprintId());
	    	if(footprint != null){
	    		Integer z = footprint.getSayNum() - 1;
	    		footprint.setSayNum(z);
	    		footprintService.updateSelective(footprint);
	    	}
	    	//再删除评论
	    	footprintReplyService.deleteByPrimaryKey(Integer.parseInt(id));
//	    	return "redirect:/jumppeople";
	    	Map<String, Object> m = new HashMap<>();
	    	m.put("mess", "删除成功！");
	    	response.setCharacterEncoding("utf-8");
	    	PrintWriter pw = response.getWriter();
	    	pw.print(JsonUtil.toJson(m).toString());
	    	pw.flush();
	        return null;
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
    public String ajaxSaveReply(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException{ 
    	String url = getUrl.geturl(request);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Map<String, Object> m = new HashMap<>();
    	HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user==null){
    		m.put("success", 0);
    	}else{
    		String replyContent = request.getParameter("replyContents");//回复内容
    		String footprintId = request.getParameter("footprintId");//文章id
    		String breplyUserId = request.getParameter("breplyUserId");//被回复人id
    		System.out.println(replyContent+"-"+footprintId+"-"+breplyUserId);
    		FootprintReply footprintReply = new FootprintReply();
    		footprintReply.setFootprintId(Integer.parseInt(footprintId));//分享
    		footprintReply.setReplyUserId(user.getId());//
    		footprintReply.setReplyUserName(user.getUserName());//
    		footprintReply.setBreplyUserId(Integer.parseInt(breplyUserId));//
    		footprintReply.setReplyTime(sdf.format(new Date()));
    		footprintReply.setReplyContent(replyContent);
    		footprintReply.setReplyParentId(0);
    		User usr = userService.getUserById(Integer.parseInt(breplyUserId));
    		footprintReply.setBreplyUserName(usr.getUserName());
    		footprintReplyService.insert(footprintReply);
    		
    		Footprint footprint = footprintService.selectByPrimaryKey(Integer.parseInt(footprintId));
    		if(footprint != null){
    			Integer z = footprint.getSayNum() + 1;
    			footprint.setSayNum(z);
    			footprintService.updateSelective(footprint);
    		}
    		//发送邮件
    		try {
    			String mailHost = "smtp.163.com";//
    			String sender_username = "penderman@163.com";
    			String sender_password = "p920521";
    			String[] toUser = new String[]{usr.getMail()};
    			MailUtil se = new MailUtil(mailHost, sender_username, sender_password, false);
    			se.doSendHtmlEmail("足迹", "［足迹网］ <div>您在足迹上收到了回复，请点击<a href='"+url+"gotolocationdetails?footprintId="+footprintId+"'>www.pengyingjun.com</a>查看评论详情！</div>", toUser, null);
    		} catch (Exception e) {
    			model.addAttribute("messge", "邮箱格式异常，请检查邮箱准确性！");
    		}
    		m.put("success", 1);
    		FootprintReply ff = footprintReplyService.selectByPrimaryKey(footprintReply.getReplyId());
    		m.put("footprintReply", ff);
    	}
		response.setCharacterEncoding("utf-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(m).toString());
    	pw.flush();
        return null; 
    }  
    
    
	/**
	 * 发表地理位置信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/locationinfo")
	public String locationinfo(HttpServletRequest request, Model model){
		MultipartHttpServletRequest reques = (MultipartHttpServletRequest) request;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String comment = request.getParameter("comment");//文字
		String adr = request.getParameter("adr");//位置
		String lng = request.getParameter("lng");//经度
		String lat = request.getParameter("lat");//纬度
		String selname = request.getParameter("selname");//是否可见 1大家  2自己
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
		Footprint footprint = new Footprint();
		footprint.setClickNum(0);
		footprint.setComment(comment);
		footprint.setLng(lng);
		footprint.setLat(lat);
		footprint.setLocation(adr);
		footprint.setReadNum(Integer.parseInt(selname));
		footprint.setSayNum(0);
		footprint.setZfNum(0);
		if(user != null){
			footprint.setUserId(user.getId());
			footprint.setCreName(user.getUserName());
		}else{
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
		footprint.setCreTime(sdf.format(new Date()));
		String[] types = {"JPG", "PNG", "JPEG", "GIF", "JEPG"};
		try {
			String[] newfileName = NormalUtils.syqpicdownUrl(reques, "pic",footprint.getPictruename());
			if (newfileName != null) {
				//判断图片类型
		        if (!Arrays.asList(types).contains(newfileName[0].substring(newfileName[0].lastIndexOf(".")+1).toUpperCase())) {
		        	model.addAttribute("messge", "照片格式只支持png、jpg、jpeg、gif！");
					return "location/index";
		        }
		        footprint.setPicurl(newfileName[1]);
		        footprint.setPictruename(newfileName[0]);
			} else {
				model.addAttribute("messge", "发布信息失败，请上传您的照片！");
				return "location/locationinfo";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		footprintService.insert(footprint);
		return "redirect:jumppeople";
	}
	
	/**
	 * 系统首页
	 * @return
	 */
	@RequestMapping("/indexs")
	public String indexs(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user != null){
    		model.addAttribute("userId", user.getId());
    		model.addAttribute("mail", user.getMail());
    		model.addAttribute("picUrl", user.getPicurl());
    		model.addAttribute("userName", user.getUserName());
    		model.addAttribute("loginIp", user.getLoginIp());
    		model.addAttribute("loginAdr", user.getLoginadr());
    		model.addAttribute("level", user.getAge());
    		model.addAttribute("levelimg", getLevel(user.getAge()));
    		
    		Visitor v1 = new Visitor();
    		v1.setBvisitorId(user.getId());
    		//查询该账号被访问次数
    		List<Visitor> vs = visitorService.queryListNumByBvisitorId(v1);
    		//查询有哪些用户
    		List<Visitor> vls = visitorService.queryListByBvisitorId(v1);
    		StringBuffer sb = new StringBuffer();
    		for(int i=0; i<vls.size(); i++){
    			sb.append("["+vls.get(i).getVisitorName()+"]");
    			if(i != vls.size()-1){
    				sb.append("、");
    			}
    			if(i == 10){
    				break;
    			}
    		}
    		if(vls.size() == 0){
    			sb.append("暂无访客！");
    		}else{
    			sb.append("等"+vs.size()+"人访问了您的主页... ...");
    		}
    		model.addAttribute("sbinfo", sb);
    		return "location/index";
		}else{
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
	}
	
	/**
	 * 访问其他个人用户首页
	 * @return
	 */
	@RequestMapping("/somebodyindexs")
	public String somebodyindexs(HttpServletRequest request, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HttpSession session = request.getSession();
    	User user= (User)session.getAttribute("login");
    	if(user != null){
    		String userId = request.getParameter("userId");//用户id
    		User u = userService.getUserById(Integer.parseInt(userId));
    		model.addAttribute("userId", userId);
    		model.addAttribute("mail", u.getMail());
    		model.addAttribute("picUrl", u.getPicurl());
    		model.addAttribute("userName", u.getUserName());
    		model.addAttribute("loginIp", u.getLoginIp());
    		model.addAttribute("loginAdr", u.getLoginadr());
    		model.addAttribute("level", u.getAge());
    		model.addAttribute("levelimg", getLevel(u.getAge()));
    		
    		//插入访问记录表
    		if(user.getId() != u.getId()){
    			Visitor v = new Visitor();
    			v.setVisitorAdr(user.getLoginadr());
    			v.setVisitorId(user.getId());
    			v.setVisitorName(user.getUserName());
    			v.setVisitorTime(sdf.format(new Date()));
    			v.setBvisitorId(u.getId());
    			v.setBvisitorName(u.getUserName());
    			visitorService.insert(v);
    		}
    		
    		Visitor v1 = new Visitor();
    		v1.setBvisitorId(u.getId());
    		//查询该账号被访问次数
    		List<Visitor> vs = visitorService.queryListNumByBvisitorId(v1);
    		//查询有哪些用户
    		List<Visitor> vls = visitorService.queryListByBvisitorId(v1);
    		StringBuffer sb = new StringBuffer();
    		for(int i=0; i<vls.size(); i++){
    			sb.append("["+vls.get(i).getVisitorName()+"]");
    			if(i != vls.size()-1){
    				sb.append("、");
    			}
    			if(i == 10){
    				break;
    			}
    		}
    		if(vls.size() == 0){
    			sb.append("暂无访客！");
    		}else{
    			sb.append("等"+vs.size()+"人访问了他的主页... ...");
    		}
    		model.addAttribute("sbinfo", sb);
    		
    		return "location/somebodyindex";
		}else{
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
	}
	
	/**
	 * 注册页面跳转
	 * @return
	 */
	@RequestMapping("/tosignup")
	public String tosignup(){
		
		return "location/locationsignup";
	}
	
    /**
     * 发送重置密码链接至邮箱
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/sendresetlink")
	public String sendresetlink(HttpServletRequest request, Model model){
    	String url = getUrl.geturl(request);
		String mail = request.getParameter("mail");//邮箱
		if(!checkEmail(mail)){
			model.addAttribute("messge", "邮箱格式有问题，请重新输入！");
			return "location/locationlogin";
		}
		//发送邮件
		try {
	        String mailHost = "smtp.163.com";//
	        String sender_username = "penderman@163.com";
	        String sender_password = "p920521";
			String[] toUser = new String[]{mail};
			MailUtil se = new MailUtil(mailHost, sender_username, sender_password, false);
			se.doSendHtmlEmail("足迹", "［足迹网］<div>亲爱的足迹网用户，您好！<a href='"+url+"locationresetpass?mail="+mail+"'>请点击</a>重置密码！</div>", toUser, null);
			model.addAttribute("messge", "邮件已发送！请登录注册邮箱重置密码！");
		} catch (Exception e) {
			model.addAttribute("messge", "邮箱格式异常，请检查邮箱准确性！");
		}
		return "location/locationlogin";
	}
    
    
    /**
     * 跳转重置密码页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/locationresetpass")
    public String locationresetpass(HttpServletRequest request,Model model){  
        String mail = request.getParameter("mail");
        List<User> user = userService.findName("", mail);
        if(user.size()>0){
        	model.addAttribute("user", user.get(0)); 
        }else{
        	model.addAttribute("messge", "该邮箱不存在！");
        	return "locationlogin";
        }
        return "location/locationresetpass"; 
    }
    
    /**
     * 修改密码
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/locationresetpassword")
    public String locationresetpassword(HttpServletRequest request,Model model){  
        String mail = request.getParameter("mail");
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        if(pass1 != null && pass2 != null && !"".equals(pass1) && !"".equals(pass2)){
        	if(!pass1.equals(pass2)){
        		model.addAttribute("messge", "两次密码输入不一致！");
            	return "location/locationresetpass";
        	}else{
        		 List<User> user = userService.findName("", mail);
        		 if(user.size()>0){
        			User user2 = user.get(0);
        			user2.setPassword(MD5Util.MD5(pass1, ""));
        			userService.updateSelective(user2);
        			model.addAttribute("messge", "密码重置成功！");
        	     }else{
        	        model.addAttribute("messge", "该邮箱不存在！");
        	        return "location/locationresetpass";
        	     }
        	}
        }else{
        	model.addAttribute("messge", "请填写完整在确认修改！");
        	return "location/locationresetpass";
        }
        return "location/locationlogin"; 
    }
    
    /**
     * 用户登录
     * @param request
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/locationsign")
    public String locationsign(HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException{  
    	SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
    	String nowtime = sdfs.format(new Date());
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String loginName = request.getParameter("username");
		String password = request.getParameter("password");
//		String wz = new String(request.getParameter("wz").getBytes("ISO8859-1"),"UTF-8");
		String wz = new String(request.getParameter("wz").getBytes("iso-8859-1"), "utf-8"); 
		LOGGER.info(wz+"--------------------------------");
		//String wz = request.getParameter("wz");
		User loginUser = userService.findLoginUserByLoginNameAndPassword(loginName,MD5Util.MD5(password, ""));
		if(loginUser!=null && "1".equals(loginUser.getIsActivate())){
			String logintime = loginUser.getLoginDate().substring(0, 10);
			if(!logintime.equals(nowtime)){
				loginUser.setAge(loginUser.getAge()+1);
			}
			loginUser.setLoginIp(GetIpUtil.getIpAddr(request));
			loginUser.setLoginDate(sdf.format(new Date()));
			if(wz != null && !"".equals(wz)){
				loginUser.setLoginadr(wz);
			}
			userService.updateSelective(loginUser);//更新登录人ip
			
			//=======================================================
			//创建两个Cookie对象
			Cookie nameCookie = new Cookie("username", loginName);
			//设置Cookie的有效期为3天
			nameCookie.setMaxAge(60 * 60 * 24 * 3);
			Cookie pwdCookie = new Cookie("password", password);
			pwdCookie.setMaxAge(60 * 60 * 24 * 3);
			response.addCookie(nameCookie);
			response.addCookie(pwdCookie);
			//=======================================================
			
			request.getSession().setAttribute("user", loginUser);//得到当前用户的session，需要先创建一个
			model.addAttribute("user", loginUser);
			HttpSession session = request.getSession();
	        session.setAttribute("login", loginUser);
	        
			return "redirect:indexs";
		}else if(loginUser!=null && "0".equals(loginUser.getIsActivate())){
			model.addAttribute("messge", "您的账号还未激活，请激活后登录！");
			return "location/locationlogin";
		}else{
			model.addAttribute("messge", "密码或登录名错误，请检查后重新输入！");
			return "location/locationlogin";
		}
    } 
    
    /**
     * 获取用户地址
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/getpoint")
    public String getpoint(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{  
    	String userid = request.getParameter("userid");
    	/**
    	 * 查询登录用户地址列表
    	 */
    	List<Footprint> footprintlist = new ArrayList<>();
    	Footprint fp = new Footprint();
    	if(userid != null && !"".equals(userid)){
    		fp.setUserId(Integer.parseInt(userid));
    		footprintlist = footprintService.footprintList(fp);
    	}
    	response.setCharacterEncoding("utf-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(footprintlist).toString());
    	pw.flush();
        return null;
    }
    
    /**
     * 获取用户公开地址
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/getpublicpoint")
    public String getpublicpoint(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{  
    	String userid = request.getParameter("userid");
    	/**
    	 * 查询登录用户地址列表
    	 */
    	List<Footprint> footprintlist = new ArrayList<>();
    	Footprint fp = new Footprint();
    	if(userid != null && !"".equals(userid)){
    		fp.setUserId(Integer.parseInt(userid));
    		footprintlist = footprintService.publicfootprintList(fp);
    	}
    	response.setCharacterEncoding("utf-8");
    	PrintWriter pw = response.getWriter();
    	pw.print(JsonUtil.toJson(footprintlist).toString());
    	pw.flush();
        return null;
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
		String url = getUrl.geturl(request);
		MultipartHttpServletRequest reques = (MultipartHttpServletRequest) request;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userName = request.getParameter("signup_name");//昵称
		String mail = request.getParameter("signup_email");//邮箱
		String password = request.getParameter("signup_password");//密码
		boolean isNum = userName.matches("[0-9]+");//+表示1个搜索或多个（如"3"或"225"），*表示0个或多个（[0-9]*）（如""或"1"或"22"），?表示0个或1个([0-9]?)(如""或"7")
		if(isNum){
			model.addAttribute("messge", "您的大名不能都为数字！");
			return "location/locationlogin";
		}
		List<User> ls = userService.findName(userName, mail);
		if(ls.size()>0){
			model.addAttribute("messge", "昵称或邮箱已存在，请重新输入！");
			return "location/locationlogin";
		}
		if(!checkEmail(mail)){
			model.addAttribute("messge", "邮箱格式有问题，请重新输入！");
			return "location/locationlogin";
		}
		User lg = new User();
		lg.setUserName(userName);
		lg.setMail(mail);
		lg.setLoginName("足迹网用户");
		lg.setPassword(MD5Util.MD5(password, ""));
		lg.setCreDate(sdf.format(new Date()));
		lg.setLoginIp(GetIpUtil.getIpAddr(request));
		lg.setLoginDate(sdf.format(new Date()));
		lg.setIsActivate("0");
		lg.setAge(0);//登录天数
		String[] types = {"JPG", "PNG", "JPEG", "GIF", "JEPG"};
		try {
			String[] newfileName = NormalUtils.syqpicdownUrl(reques, "pic",lg.getPictruename());
			if (newfileName != null) {
				//判断图片类型
		        if (!Arrays.asList(types).contains(newfileName[0].substring(newfileName[0].lastIndexOf(".")+1).toUpperCase())) {
		        	model.addAttribute("messge", "头像照片格式只支持png、jpg！");
					return "location/locationlogin";
		        }
				lg.setPicurl(newfileName[1]);
				lg.setPictruename(newfileName[0]);
			} else {
				model.addAttribute("messge", "请上传您的头像！");
				return "location/locationlogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		userService.insert(lg);
		//发送邮件
		try {
	        String mailHost = "smtp.163.com";//
	        String sender_username = "penderman@163.com";
	        String sender_password = "p920521";
			String[] toUser = new String[]{mail};
			MailUtil se = new MailUtil(mailHost, sender_username, sender_password, false);
			se.doSendHtmlEmail("您好，非常感谢您注册足迹网！", "［足迹网］<div>亲爱的足迹网-"+userName+"-用户，您好！恭喜您足迹网账号已注册成功！<a href='"+url+"locationactivate?mail="+mail+"'>请点击</a>激活该账号！</div>", toUser, null);
			model.addAttribute("messge", "注册成功！请登录注册邮箱激活账号！(注：如未收到邮件，可能被识别为垃圾邮件了，请到邮箱垃圾箱中查看，并设置为这不是垃圾邮件！)");
		} catch (Exception e) {
			model.addAttribute("messge", "邮箱格式异常，请检查邮箱准确性！");
		}
		return "location/locationlogin";
	}
	
	/**
	 * 跳转修改个人信息页面
	 * @return
	 */
	@RequestMapping("/jumpmodifyinfo")
	public String jumpmodifyinfo(HttpServletRequest request, Model model){
		String userId = request.getParameter("userId");//昵称
		User user = userService.getUserById(Integer.parseInt(userId));
		if(user != null){
    		model.addAttribute("userId", user.getId());
    		model.addAttribute("mail", user.getMail());
    		model.addAttribute("picUrl", user.getPicurl());
    		model.addAttribute("userName", user.getUserName());
    		model.addAttribute("loginIp", user.getLoginIp());
    		model.addAttribute("loginAdr", user.getLoginadr());
    		model.addAttribute("level", user.getAge());
    		model.addAttribute("levelimg", getLevel(user.getAge()));
    		return "location/modifyinfo";
		}else{
			model.addAttribute("messge", "登录超时，请重新登陆！");
			return "location/locationlogin";
		}
	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyinfo")
	@Transactional(rollbackFor = Exception.class)
	public String modifyinfo(HttpServletRequest request, Model model){
		MultipartHttpServletRequest reques = (MultipartHttpServletRequest) request;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userName = request.getParameter("signup_name");//昵称
		String userId = request.getParameter("userId");//昵称
		if(userId == null && "".equals(userId)){
			model.addAttribute("messge", "用户标识丢失！");
			model.addAttribute("userName", userName);
			return "location/modifyinfo";
		}
		boolean isNum = userName.matches("[0-9]+");//+表示1个搜索或多个（如"3"或"225"），*表示0个或多个（[0-9]*）（如""或"1"或"22"），?表示0个或1个([0-9]?)(如""或"7")
		if(isNum){
			model.addAttribute("messge", "您的大名不能都为数字！");
			model.addAttribute("userName", userName);
			return "location/modifyinfo";
		}
		User lg = new User();
		lg.setUserName(userName);
		lg.setId(Integer.parseInt(userId));
		lg.setLoginIp(GetIpUtil.getIpAddr(request));
		lg.setLoginDate(sdf.format(new Date()));
		String[] types = {"JPG", "PNG", "JPEG", "GIF", "JEPG"};
		try {
			String[] newfileName = NormalUtils.syqpicdownUrl(reques, "pic",lg.getPictruename());
			if (newfileName != null) {
				//判断图片类型
		        if (!Arrays.asList(types).contains(newfileName[0].substring(newfileName[0].lastIndexOf(".")+1).toUpperCase())) {
		        	model.addAttribute("messge", "头像照片格式只支持png、jpg、jpeg！");
		        	model.addAttribute("userName", userName);
					return "location/modifyinfo";
		        }
				lg.setPicurl(newfileName[1]);
				lg.setPictruename(newfileName[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		userService.updateSelective(lg);
		HttpSession session = request.getSession();
		User u = userService.getUserById(Integer.parseInt(userId));
        session.setAttribute("login", u);
		return "redirect:indexs";
	}
	
	/**
	 * 激活账号
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping("/locationactivate")
    public String locationactivate(HttpServletRequest request,Model model){  
        String mail = request.getParameter("mail");
        List<User> user = userService.findName("", mail);
        if(user.size()>0){
        	user.get(0).setIsActivate("1");
        	userService.updateSelective(user.get(0));
        }
        return "location/activesuccess"; 
    }
//------------------------------------------------------------------------------------------------------------------------    
    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
    
    
 // ======================等级计算系统===================
    public String getLevel(Integer days) {
    	String level = "";
    	Integer king = days/448;//448天为一个皇冠
    	if(king == 0){
    		
    	}else{
    		for (int i = 0; i < king; i++) {
    			level += "♔ ";
			}
    		days = days - (king * 448);
    	}
    	Integer sun = days/112;//112天为一个太阳
    	if(sun == 0){
    		
    	}else{
    		for (int i = 0; i < sun; i++) {
    			level += "☀ ";
			}
    		days = days - (sun * 112);
    	}
    	Integer moon = days/28;//28天为一个月亮 
    	if(moon == 0){
    		
    	}else{
    		for (int i = 0; i < moon; i++) {
    			level += "☾ ";
			}
    		days = days - (moon * 28);
    	}    	
    	Integer star = days/7;//7天为一个星
    	if(star == 0 && level.length() == 0){
    		days = 7-days;
    		level += days+"天后升级到✭";
    	}else{
    		for (int i = 0; i < star; i++) {
    			level += "✭ ";
			}
    		days = days - (star * 28);
    	}
		return level;
	}
 // ======================等级计算系统===================  
    
    
//－－－－－－－－－－－－－－－－－－－邮件发送系统－－－－－－－－－－－－－－－－－－－－－
    @RequestMapping("/zjmail")
    public String zjmail(HttpServletRequest request,Model model){  
       List<User> ulist = userService.findAll();
       model.addAttribute("ulist", ulist);
       return "mail/mail"; 
    }
    
    @RequestMapping("/sendmail")
    public String sendmail(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{  
       String maillist = request.getParameter("maillist");
       String mailtext = request.getParameter("mailtext");
       String mailtitle = request.getParameter("mailtitle");
       String[] mail = maillist.split(",");
       for(int i=0; i<mail.length; i++){
    	 //发送邮件
   		try {
   	        String mailHost = "smtp.163.com";//
   	        String sender_username = "penderman@163.com";
   	        String sender_password = "p920521";
   			String[] toUser = new String[]{mail[i]};
   			MailUtil se = new MailUtil(mailHost, sender_username, sender_password, false);
   			se.doSendHtmlEmail("[足迹网]  "+mailtitle, mailtext, toUser, null);
   		} catch (Exception e) {
   			LOGGER.info(mail[i]+"用户邮件发送失败！");
   		}
       }
       Map<String, Object> m = new HashMap<>();
   	   m.put("json", "发送邮件成功！");
       response.setCharacterEncoding("utf-8");
   	   PrintWriter pw = response.getWriter();
   	   pw.print(JsonUtil.toJson(m).toString());
   	   pw.flush();
       return null;
    }
    
//－－－－－－－－－－－－－－－－－－－邮件发送系统－－－－－－－－－－－－－－－－－－－－－    
}
