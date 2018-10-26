package com.cx.chenxing.utils;

import javax.servlet.http.HttpServletRequest;

public class GetBaseUrl {
    public static String geturl(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath =request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return basePath;
    }
}
