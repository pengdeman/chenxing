package com.cx.chenxing.utils.zj;

import javax.servlet.http.HttpServletRequest;

public class getUrl {
    public static String geturl(HttpServletRequest request){
    	String path = request.getContextPath();
        String basePath =request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
    }
}
