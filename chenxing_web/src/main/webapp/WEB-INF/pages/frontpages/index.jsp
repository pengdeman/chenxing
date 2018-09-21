<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>辰星🌟</title>

    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
      .fakeimg {
        height: 270px;
        background: #aaa;
        background-repeat:no-repeat;
        background-size:100% 100%;
        -moz-background-size:100% 100%;
      }
      @media screen and (max-width:500px){
        .hideline { display:none; }
      }
    </style>
  </head>
  <body>
  <div class="jumbotron text-center" style="margin-bottom:0; background-image: url('xingchen.jpg');">
    <h1 style="color: white">辰星</h1>
    <p style="color: white; font-size: small">那灿烂银河中的每一颗辰星，在浩瀚的夜空中，是如此的渺小，但当流淌过我的眼帘，却深深地耀住了我的心房。虽是渺小，但却努力地闪出如宝石般的光芒来装点着夜空、依偎着银河、告别着平凡。</p>
  </div>

  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">辰星🌟</a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">主页</a></li>
          <li><a href="#">足迹</a></li>
          <li><a href="#">相册</a></li>
          <li><a href="#">留言板</a></li>
          <li><a href="#">个人中心</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
          <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <h2></h2>
        <div class="fakeimg" style="background-image: url('touxiang.jpg');">
        </div>
        <div class="panel panel-default">
          <table class="table">
            <tr>
              <td><strong>关注:</strong></td>
              <td>232</td>
              <td><strong>粉丝:</strong></td>
              <td>428</td>
            </tr>
            <tr>
              <td><strong>昵称:</strong></td>
              <td>英峻侠</td>
              <td><strong>坐标:</strong></td>
              <td>北京</td>
            </tr>
          </table>
        </div>
        <br>
        <div class="hideline"><!-- 该div用于屏幕宽度小于500px时隐藏友情链接 -->
        <h3>友情链接</h3>
        <p>感谢以下各站点对本站的大力支持。</p>
        <ul class="nav nav-pills nav-stacked">
          <li class="active"><a href="http://www.pengyingjun.com" target="_blank">足迹网</a></li>
          <li><a href="https://weibo.com/2422587714" target="_blank">彭英峻新浪微博</a></li>
          <li><a href="https://changba.com/s/xnDL3ZAc22ISVZ2LK7onXw?&code=RkvQSz26klqKNaC51ggM9dP1JcfCTyTVFpVM2tr0OMjWk-5TC-l20GCJDS9DMk0QTtWCtpL4MwzOdmNwhs9BKUH1p8jdII1mGasf9lwd2VmZ9M7S7UsVOGqZhq23Rf47EJuxRcd7yu7iiUZP5tmn1w&source=qzone" target="_blank">彭英峻唱吧主站</a></li>
        </ul>
        </div>
        <hr class="hidden-sm hidden-md hidden-lg">
      </div>
      <div class="col-sm-8">
        <h2></h2>
        <div class="fakeimg" style="background-image: url('bj.jpg')"></div>
        <p>一些文本..</p>
        <p>菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！</p>
        <br>
        <h2></h2>
        <div class="fakeimg" style="background-image: url('gz.jpg')"></div>
        <p>一些文本..</p>
        <p>菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！</p>
      </div>
    </div>
  </div>
  <br><br><br>
  <div class="row footer-bottom" style="background-color: #aaaaaa;">
    <br>
    <ul class="list-inline text-center">
      <li>Copyright &copy;2018. n 辰星公司 Software All Rights Reserved.</li>
    </ul>
    <br>
  </div>
  </body>
</html>