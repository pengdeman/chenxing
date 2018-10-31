<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ga2vOgjKiex14wPRkTblnAHYIB2bWrTy"></script>
    <title>辰星🌟</title>

    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/fileinput.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="layer/mobile/need/layer.css" />
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
    <script src="js/locales/zh.js"></script>
    <script src="js/fileinput.js"></script>
    <script type="text/javascript" src="layer/mobile/layer.js"></script>
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
      .cxguanzhu {
        height: 30px;
        float: right;
      }
      .cxtouxiang {
        width: 60px;
        margin-left: -7px;
        border-radius: 50%;
      }
      .cxtime {
        font-size: 12px;
        color: #aaaaaa;
      }
      .cxtext {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space:nowrap;
      }
      .bodernone {
        border:none;
      }
    </style>
  </head>
  <body>
  <div class="jumbotron text-center" style="margin-bottom:0; background-image: url('xingchen.jpg');background-repeat:no-repeat;background-size:100% 100%;-moz-background-size:100% 100%;">
    <h5 style="color: white">从童年起，我便独自一人</h5>
    <h5 style="color: white">照顾着</h5>
    <h5 style="color: white">历代的星辰</h5>
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
          <li class="active"><a href="<%=basePath%>index">首页</a></li>
          <li><a href="#">个人中心</a></li>
          <li><a href="#">留言板</a></li>
          <li><a href="<%=basePath%>personalcenter/index">个人档</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <c:if test="${user == null}">
            <li><a href="#" data-toggle="modal" data-target="#loginModal"><span class="glyphicon glyphicon-user"></span>登录</a></li>
          </c:if>
          <c:if test="${user != null}">
            <li><a href="#" data-toggle="modal" data-target="" onclick="tuichu()"><span class="glyphicon glyphicon-log-in"></span> 登出</a></li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <h2></h2>
        <div class="fakeimg" style="background-image: url('pic/${user.img }');">
        </div>
        <div class="panel panel-default">
          <table class="table">
            <tr>
              <td colspan="4" style="text-align: center;">
                <span class="glyphicon glyphicon-user"></span>
                <!-- ♂表示男性，♀表示女性 -->
                <c:if test="${user == null}">
                  <strong>外星人游客</strong>&nbsp;&nbsp;
                </c:if>
                <c:if test="${user != null}">
                  <strong>${user.userName}</strong>&nbsp;&nbsp;
                </c:if>
                <c:if test="${user.sex == 1}">
                  <strong>♂</strong>
                </c:if>
                <c:if test="${user.sex == 2}">
                  <strong>♀</strong>
                </c:if>
              </td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>关注:</strong></td>
              <td>232</td>
              <td style=" white-space: nowrap;"><strong>粉丝:</strong></td>
              <td>428</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>访客:</strong></td>
              <td>192033</td>
              <td style=" white-space: nowrap;"><strong>坐标:</strong></td>
              <td>北京</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>签名:</strong></td>
              <td colspan="3">
                纸上得来终觉浅，觉知此时要躬行。
              </td>
            </tr>
          </table>
        </div>

        <div style="text-align: center;">
          <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#chenxingModal">辰星发布</button>
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
        <c:forEach items="${articleList}" var="item">
          <h2></h2>
          <div class="fakeimg" style="background-image: url('pic/${item.picurl }')"></div>
          <div class="panel panel-default">
            <div class="panel-body">
              <table class="table" frame="void">
                <tr style="height: 20px;">
                  <td rowspan="2" width="55px;">
                    <img src="touxiang.jpg" class="cxtouxiang">
                  </td>
                  <td>
                    <label style="margin-top: 5px;">${item.creUid }</label>&nbsp;&nbsp;<label title="登录365天，皇冠等级">👑</label>
                    <button type="button" class="btn btn-default cxguanzhu">➕关注</button>
                  </td>
                </tr>
                <tr>
                  <td class="cxtime">
                    <fmt:parseDate value="${item.creTime }"
                                   pattern="yyyy-MM-dd HH:mm:ss" var="creTime"></fmt:parseDate>
                    <fmt:formatDate value="${creTime}"
                                    pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate>
                  </td>
                </tr>
              </table>
              <div class="cxtext">
                  ${item.article }
              </div>
              <br>
              <div class="btn-group" style="margin-left: -15px;">
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-map-marker" style="font-size: 10px;">${item.location }</span>
                </button>
              </div>
              <br>
              <HR width="100%" color=#987cb9 SIZE=10 />
              <div class="btn-group" style="margin-left: -15px;">
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-eye-open"> ${item.ydnum }</span>
                </button>
              </div>
              <div class="btn-group" style="float: right; margin-right: -10px;">
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-share-alt"> ${item.zfnum }</span>
                </button>
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-edit"> ${item.plnum }</span>
                </button>
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-heart-empty"> ${item.dznum }</span>
                  <!-- <span class="glyphicon glyphicon-heart"></span> 已赞 -->
                </button>
              </div>
            </div>
          </div>
          <br>
        </c:forEach>
      </div>
    </div>
  </div>
  <br><br><br>
  <div class="row footer-bottom" style="background-color: #aaaaaa;">
    <br>
    <ul class="list-inline text-center">
      <li style="font-size: 10px;">Copyright &copy;2018. n 辰星集团 Software All Rights Reserved.</li>
    </ul>
    <br>
  </div>

  <!-- 注册模态框（Modal） -->
  <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="background-image: url('xingchen.jpg');background-repeat:no-repeat;background-size:100% 100%;-moz-background-size:100% 100%;">
          <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×
          </button>
          <h1 class="text-center" id="registerModalLabel" style="color: white">
            注册
          </h1>
        </div>
        <div class="modal-body">
          <form class="form-group" action="<%=basePath%>user/locationreg" id="regist-form_id" method="post">
            <div class="form-group">
              <label>昵称</label>
              <input class="form-control" type="text" name="signup_name" placeholder="给自己起一个帅气的名字吧">
            </div>
            <div class="form-group">
              <label>密码</label>
              <input class="form-control" type="password" name="signup_password" placeholder="至少6位字母或数字">
            </div>
            <div class="form-group">
              <label>邮箱</label>
              <input class="form-control" type="email" name="signup_email" placeholder="该邮箱将作为登录账号使用">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" type="submit" onclick="registsubform()">提交</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
            <a href="" data-toggle="modal" data-dismiss="modal" data-target="#loginModal">已有账号？点我登录</a>
          </form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->


  <!-- 登录模态框（Modal） -->
  <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="background-image: url('xingchen.jpg');background-repeat:no-repeat;background-size:100% 100%;-moz-background-size:100% 100%;">
          <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×
          </button>
          <h1 class="text-center" id="loginModalLabel" style="color: white">
            登录
          </h1>
        </div>
        <div class="modal-body">
          <form class="form-group" action="<%=basePath%>user/locationsign" id="signin-form_id" method="post">
            <div class="form-group">
              <label>用户名</label>
              <input class="form-control" type="text" name="username" placeholder="请输入用户名">
            </div>
            <div class="form-group">
              <label>密码</label>
              <input class="form-control" type="password" name="password" placeholder="请输入密码">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" type="submit" onclick="signinsubform()">登录</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
            <a href="" data-toggle="modal" data-dismiss="modal" data-target="#registerModal">还没有账号？点我注册</a>  |
            <a href="" data-toggle="modal" data-dismiss="modal" data-target="#passwardModal">忘记密码</a>
          </form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

  <!-- 忘记密码模态框（Modal） -->
  <div class="modal fade" id="passwardModal" tabindex="-1" role="dialog" aria-labelledby="passwardModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="background-image: url('xingchen.jpg');background-repeat:no-repeat;background-size:100% 100%;-moz-background-size:100% 100%;">
          <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×
          </button>
          <h1 class="text-center" id="passwardModalLabel" style="color: white">
            重置密码
          </h1>
        </div>
        <div class="modal-body">
            <div class="form-group">
              <label>邮箱账号</label>
              <div class="form-inline">
                <input class="form-control" type="text" name="usermail" placeholder="请输入邮箱账号" style="width: 82%;">
                <button class="btn btn-primary modifybutton" type="button" onclick="sendma()"  data-loading-text="已发送">发送验证码</button>
              </div>
            </div>
            <div class="form-group">
              <label>验证码</label>
              <input class="form-control" type="text" name="checkma" placeholder="请输入邮箱接收到的验证码">
            </div>
            <div class="form-group">
              <label>新密码</label>
              <input class="form-control" type="text" name="newpassword" placeholder="请输入您的新密码">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" type="submit" onclick="modifypassword()">确认</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
            <a href="" data-toggle="modal" data-dismiss="modal" data-target="#loginModal">返回登录</a>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

  <!-- 发布辰星模态框（Modal） -->
  <div class="modal fade" id="chenxingModal" tabindex="-1" role="dialog" aria-labelledby="chenxingModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="background-image: url('xingchen.jpg');background-repeat:no-repeat;background-size:100% 100%;-moz-background-size:100% 100%;">
          <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×
          </button>
          <h1 class="text-center" id="chenxingModalLabel" style="color: white">
            辰星发布
          </h1>
        </div>
        <div class="modal-body">
          <form class="form-group" action="<%=basePath%>article/submitarticle" id="article-form_id" method="post" enctype="multipart/form-data">
            <div class="form-group">
              <label>辰星密语</label>
              <textarea class="form-control" type="text" name="article" id="article" style="width: 99.5%; min-height: 150px;">

              </textarea>
            </div>
            <div class="form-group">
              <label>权限设置</label>
                <div class="input-group">
                  <div class="input-group-btn">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                      选择权限
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                      <li><a href="javascript:getquanxian('公开')">公开</a></li>
                      <li><a href="javascript:getquanxian('仅自己可见')">仅自己可见</a></li>
                    </ul>
                  </div><!-- /btn-group -->
                  <input type="text" class="form-control" id="quanxian" name="show" value="公开">
                </div><!-- /input-group -->
            </div>
            <div class="form-group">
              <label>定位地点</label>
              <div class="input-group">
                <div class="input-group-btn">
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    选择地点
                    <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu" id="localselect">
                    <li><a onclick="getlocal()">定位中☢...</a></li>
                  </ul>
                </div><!-- /btn-group -->
                <input type="text" class="form-control" id="local" name="location">
                <input type="hidden" name="lng" id="lng" value=""/>
                <input type="hidden" name="lat" id="lat" value=""/>
              </div><!-- /input-group -->
            </div>
            <div class="form-group">
              <label>辰星美图</label>
              <input id="f_upload" type="file" class="file" name="picurl"/>
            </div>
            <div class="text-right">
              <button class="btn btn-primary" type="submit" onclick="chenxingsubform()">提交</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

  <script type="text/javascript">

      /**
       * 获取后台message信息
       */
      $(function() {
          messge = '${messge}';
          if(messge != ''){
              alert(messge);
          }
          getlocattion()
      });

      /**
       * 辰星发布
       */
      function chenxingsubform() {
          if ($("#article").val().length == 0) {
              alert("请填写文字发出你的辰星密语吧。");
              return;
          }
          if ($("input[name='show']").val().length == 0) {
              alert("请选择一个你分享的权限。");
              return;
          }
          if ($("input[name='location']").val().length == 0) {
              alert("请选择一个地点再分享吧。");
              return;
          }
          if ($("input[name='picurl']").val().length == 0) {
              alert("请选择一个美丽图片。");
              return;
          }
          layer.open({
              type: 2
              ,content: '提交中...'
              ,time: 5
          });
          $("#article-form_id").submit();
      }

      /**
       * 获取定位
       */
      function getlocattion(){
          $("#sele1").html("");
          $("#sele1").html("<li><a onclick='getlocal("+""+")'>☞定位中☢...</a></li>");
          var geolocation = new BMap.Geolocation();
          var geoc = new BMap.Geocoder();
          geolocation.getCurrentPosition(function(r){
              if(this.getStatus() == BMAP_STATUS_SUCCESS){
                  var pt = new BMap.Point(r.point.lng, r.point.lat);
                  var dzs = "";
                  var dzss = "";
                  geoc.getLocation(pt, function(rs){
                      var addComp = rs.addressComponents;
                      $("#lng").val(r.point.lng);
                      $("#lat").val(r.point.lat);
                      dzs = addComp.province +".";
                      if(addComp.province != addComp.city){
                          dzs = dzs + addComp.city+ ".";
                      }
                      dzs = dzs + addComp.district +".";
                      dzss = dzs+addComp.street;
                      /* 				if(addComp.street != ""){
                                          dzs = dzs + addComp.street+"•";
                                      } */
                      //addComp.province +  +"•"+ addComp.streetNumber
                  });
                  $.ajax({
                      url:"https://api.map.baidu.com/place/v2/search?query=旅游景区&location="+r.point.lat+","+r.point.lng+"&radius=1000&page_size=20&output=json&ak=ga2vOgjKiex14wPRkTblnAHYIB2bWrTy",
                      type:"GET",
                      dataType: "JSONP",
                      error:function(request){
                          alert("获取失败！");
                      },
                      success:function(data){
                          var selelist = '<li><a onclick="getlocal('+'\''+dzss+'\''+')">☞'+dzss+'</a></li>';
                          if(status == 0){
                              $.each(data.results, function (i, item) {
                                  var locald = dzs+""+item.name;
                                      selelist = selelist + '<li><a onclick="getlocal('+'\''+locald+'\''+')">☞'+item.name+'</a></li>'
                              });
                          }else{
                              selelist = selelist + dzs;
                          }
                          $("#localselect").html(selelist);
                      }
                  });
              }
          },{enableHighAccuracy: true})
      }

      /**
       * 选择权限
       */
      function getquanxian(mes){
          $("#quanxian").val(mes);
      }

      /**
       * 选择位置
       */
      function getlocal(mes){
          $("#local").val(mes);
      }

      /**
       * 登出
       */
      function tuichu(){
          window.location.href="<%=basePath%>user/loginout";
      }

      /**
       * 发送验证码
       */
      function sendma() {
          if ($("input[name='usermail']").val().length == 0) {
              alert("请输入邮箱账号。");
              return;
          }
          var usermail = $("input[name='usermail']").val();
          $.ajax({
              cache:true,
              type:"POST",
              url:"<%=basePath%>user/sendcode",
              data:{usermail: usermail},
              async:false,
              error:function(request){
                  alert("发送失败！");
              },
              success:function(data){
                  var jsonObj=eval("("+data+")");
                  if(jsonObj.mes == 1){
                      alert("请填写正确的邮箱账号！");
                  }else if(jsonObj.mes == 2){
                      alert("该邮箱格式错误或该邮箱账号不存在！");
                  }else if(jsonObj.mes == 3){
                      $(".modifybutton").button('loading').delay(10000).queue(function() {
                          $(".modifybutton").button('reset');
                          $(".modifybutton").dequeue();
                      });
                      alert("该邮箱账号的验证码已发送，请去邮箱查看并输入到验证码一栏中！");
                  }else{
                      alert("发生了未知错误！");
                  }
              }
          });
      }

      /**
       * 确认修改密码
       */
      function modifypassword() {
          if ($("input[name='checkma']").val().length == 0) {
              alert("请输入验证码。");
              return;
          }
          if ($("input[name='newpassword']").val().length == 0) {
              alert("请输入新密码。");
              return;
          }
          if ($("input[name='usermail']").val().length == 0) {
              alert("请输入邮箱。");
              return;
          }
          var checkma = $("input[name='checkma']").val();
          var newpassword = $("input[name='newpassword']").val();
          var usermail = $("input[name='usermail']").val();

          $.ajax({
              cache:true,
              type:"POST",
              url:"<%=basePath%>user/modifypassword",
              data:{checkma: checkma, "newpassword":newpassword, "usermail":usermail},
              async:false,
              error:function(request){
                  alert("修改失败！");
              },
              success:function(data){
                  var jsonObj=eval("("+data+")");
                  if(jsonObj.flag == 1 || jsonObj.flag == 3){
                      alert("您输入的验证码不匹配！");
                  }else if(jsonObj.flag == 2){
                      alert("您输入的邮箱有误！");
                  }else if(jsonObj.flag == 4){
                      alert("密码修改成功！");
                  }
                  $('#passwardModal').modal('hide');
              }
          });
      }

      /**
       * 登录
       */
      function signinsubform() {
          if ($("input[name='username']").val().length == 0) {
              alert("请输入用户名。");
              return;
          }
          if ($("input[name='password']").val().length == 0) {
              alert("请输入密码。");
              return;
          }
          $("#signin-form_id").submit();
      }

      /**
       * 注册
       */
      function registsubform() {
          if ($("input[name='signup_name']").val().length == 0) {
              alert("请输入昵称。");
              return;
          }
          if ($("input[name='signup_email']").val().length == 0) {
              alert("请输入Email。");
              return;
          }
          if ($("input[name='signup_password']").val().length == 0) {
              alert("请输入密码。");
              return;
          }
          layer.open({
              type: 2
              ,content: '提交中...'
              ,time: 5
          });
          $("#signup-form_id").submit();
      }
  </script>
  </body>
</html>