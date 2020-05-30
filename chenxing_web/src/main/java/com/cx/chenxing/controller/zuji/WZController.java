package com.cx.chenxing.controller.zuji;

import com.cx.chenxing.db.zj.entity.User;
import com.cx.chenxing.db.zj.entity.Wz;
import com.cx.chenxing.service.zj.IWzService;
import com.cx.chenxing.utils.zj.GetLatAndLngByBaiduUtil;
import com.cx.chenxing.utils.zj.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller  
@RequestMapping("/wz") 
public class WZController {
	
	@Resource  
    private IWzService wzService;
	
    @RequestMapping("/getwz")
    public String getwz(HttpServletRequest request, HttpServletResponse response, Model model){ 
    	
        return "wz/index";  
    }
    
    @RequestMapping("/getfjr")
    public String getfjr(HttpServletRequest request, HttpServletResponse response, Model model){ 
    	
        return "wz/fjr";  
    }
    
    @RequestMapping("/wzinsert")
    public String  wzinsert(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{ 
    	HttpSession session = request.getSession();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	User user= (User)session.getAttribute("login");
    	String lng = request.getParameter("lng");//经度
    	String lat = request.getParameter("lat");//纬度
    	String name = request.getParameter("name");//纬度
    	if(user != null && user.getMail().equals(name)) {
    		return null;  
    	}else {
    		String wz = GetLatAndLngByBaiduUtil.getCityFromLngAndlat(lat, lng);
    		Wz wzb = new Wz();
    		wzb.setAdr(wz);
    		wzb.setLng(lng);
    		wzb.setLat(lat);
    		wzb.setCreTime(sdf.format(new Date()));
    		wzb.setName(name);
    		if(wz.length() > 0) {
    			wzService.insert(wzb);
    		}
    		
    		Map<String, Object> dateMap = new HashMap<String, Object>();
    		dateMap.put("wz", wz);
    		response.setCharacterEncoding("utf-8");
    		PrintWriter pw = response.getWriter();
    		pw.print(JsonUtil.toJson(dateMap).toString());
    		pw.flush();
    		return null;  
    	}
    }
    
    @RequestMapping("/getwzlist")
    public String getwzlist(HttpServletRequest request, HttpServletResponse response, Model model){ 
    	List<Wz> wzs = wzService.wzList();
    	model.addAttribute("ls", wzs);
        return "wz/list";  
    }
    
   
}
