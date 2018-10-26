<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>账号激活成功！</title>
	<script src="js/baiducount.js"></script>
  </head>
  
  <body style="background-image: url('activeback.gif'); background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;">
    <div style="margin-top: 10%; text-align: center;">
    	<div>
    		<label style="color: white; font-size: 32px;">激活成功！</label>
    	</div>
        <br>
    	<div>
    		<label style="color: white">尊敬的用户，您的账号已激活，请<a href="<%=basePath%>index" style="color: red">点击登录</a>！</label>
    	</div>
    </div>
  </body>
</html>
