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
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
    <script src="js/fileinput.js"></script>
    <script type="text/javascript" src="layer/mobile/layer.js"></script>
    <script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

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
      .cxtext {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space:nowrap;
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
        <a class="navbar-brand" href="<%=basePath%>">辰星🌟</a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li><a href="<%=basePath%>">首页</a></li>
          <c:if test="${user == null}">
            <li><a href="javascript:alert('登录后才可查看！')">个人中心</a></li>
            <li><a href="javascript:alert('登录后才可查看！')">留言板</a></li>
            <li><a href="javascript:alert('登录后才可查看！')">个人档</a></li>
          </c:if>
          <c:if test="${user != null}">
            <li><a href="<%=basePath%>personalcenter/myselfindex">个人中心</a></li>
            <li><a href="javascript:alert('设计中！')">留言板</a></li>
            <li class="active"><a href="<%=basePath%>personalcenter/index">个人档</a></li>
          </c:if>
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

        <br>

        <div style="text-align: center;">
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#picmodifyModal">修改头像</button>
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#infomodifyModal">修改资料</button>
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
        基本资料
        <HR width="100%" color=#987cb9 SIZE=10 />
        <div class="panel panel-default">
          <table class="table">
            <tr>
              <td style=" white-space: nowrap;"><strong>昵称:</strong></td>
              <td>${user.userName }</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>性别:</strong></td>
              <td>${user.sex }</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>邮箱:</strong></td>
              <td>${user.mail }</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>年龄:</strong></td>
              <td>${user.age }</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>手机:</strong></td>
              <td>${user.phone }</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>生日:</strong></td>
              <td>
                <fmt:parseDate value="${user.birthday }"
                               pattern="yyyy-MM-dd HH:mm:ss" var="birthday"></fmt:parseDate>
                <fmt:formatDate value="${birthday}"
                                pattern="yyyy-MM-dd"></fmt:formatDate>
              </td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>坐标:</strong></td>
              <td>${user.adress }</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>登录时间:</strong></td>
              <td>
                <fmt:parseDate value="${user.loginTime }"
                               pattern="yyyy-MM-dd HH:mm:ss" var="loginTime"></fmt:parseDate>
                <fmt:formatDate value="${loginTime}"
                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
              </td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>签名:</strong></td>
              <td colspan="3">
                ${user.signature }
              </td>
            </tr>
          </table>
        </div>
        <br>
      </div>
    </div>
  </div>
  <br><br><br>
  <div class="row footer-bottom" style="background-color: #aaaaaa;">
    <br>
    <ul class="list-inline text-center">
      <li>Copyright &copy;2018. n 辰星集团 Software All Rights Reserved.</li>
    </ul>
    <br>
  </div>


  <!-- 修改头像模态框（Modal） -->
  <div class="modal fade" id="picmodifyModal" tabindex="-1" role="dialog" aria-labelledby="picmodifyModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="background-image: url('xingchen.jpg');background-repeat:no-repeat;background-size:100% 100%;-moz-background-size:100% 100%;">
          <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×
          </button>
          <h1 class="text-center" id="chenxingModalLabel" style="color: white">
            修改头像
          </h1>
        </div>
        <div class="modal-body">
          <form class="form-group" action="<%=basePath%>personalcenter/modifypic" id="picmodify-form_id" method="post" enctype="multipart/form-data">
            <div class="form-group">
              <label>选择头像</label>
              <input id="f_upload" type="file" class="file" name="picurl"/>
            </div>
            <div class="text-right">
              <button class="btn btn-primary" type="submit" onclick="picmodifysubform()">修改</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

  <!-- 修改个人信息模态框（Modal） -->
  <div class="modal fade" id="infomodifyModal" tabindex="-1" role="dialog" aria-labelledby="infomodifyModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="background-image: url('xingchen.jpg');background-repeat:no-repeat;background-size:100% 100%;-moz-background-size:100% 100%;">
          <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×
          </button>
          <h1 class="text-center" id="registerModalLabel" style="color: white">
            修改信息
          </h1>
        </div>
        <div class="modal-body">
          <form class="form-group" action="<%=basePath%>personalcenter/modifyinfo" id="infomodify-form_id" method="post">
            <div class="form-group">
              <label>昵称</label>
              <input class="form-control" type="text" name="user_name" value="${user.userName }">
            </div>
            <div class="form-group">
              <label>性别</label>
              <div class="input-group">
                <div class="input-group-btn">
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    选择性别
                    <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu">
                    <li><a href="javascript:getsex('男生')">男生</a></li>
                    <li><a href="javascript:getsex('女生')">女生</a></li>
                    <li><a href="javascript:getsex('保密')">保密</a></li>
                  </ul>
                </div><!-- /btn-group -->
                <input type="text" class="form-control" id="sex" name="sex" value="${user.sex }">
              </div><!-- /input-group -->
            </div>
            <div class="form-group">
              <label>年龄</label>
              <c:if test="${user.age != null}">
                <input class="form-control" type="text" name="age" value="${user.age}">
              </c:if>
              <c:if test="${user.age == null}">
                <input class="form-control" type="text" name="age" placeholder="今年多大了？">
              </c:if>
            </div>
            <div class="form-group">
              <label>手机</label>
              <c:if test="${user.phone != null}">
                <input class="form-control" type="text" name="phone" value="${user.phone}">
              </c:if>
              <c:if test="${user.phone == null}">
                <input class="form-control" type="text" name="phone" placeholder="留个联系方式？">
              </c:if>
            </div>
            <div class="form-group">
              <label>生日</label>
              <input type='text' class="form-control" id='datetime' name="birthday"  value="${user.birthday}"/>
            </div>
            <div class="form-group">
              <label>坐标</label>
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
                <input type="text" class="form-control" id="adress" name="adress" value="${user.adress }">
                <input type="hidden" name="lng" id="lng" value=""/>
                <input type="hidden" name="lat" id="lat" value=""/>
              </div><!-- /input-group -->
            </div>
            <div class="form-group">
              <label>签名</label>
              <c:if test="${user.signature != null}">
                <input class="form-control" type="text" name="signature" value="${user.phone}">
              </c:if>
              <c:if test="${user.signature == null}">
                <input class="form-control" type="text" name="signature" placeholder="分享下你的座右铭？">
              </c:if>
            </div>
            <div class="text-right">
              <button class="btn btn-primary" type="submit" onclick="infomodifysubform()">提交</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </form>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

  <script type="text/javascript">

      /**
       * 登出
       */
      function tuichu(){
          window.location.href="<%=basePath%>user/loginout";
      }

      /**
       * 获取后台message信息
       */
      $(function() {
          messge = '${messge}';
          if(messge != ''){
              alert(messge);
          }
          getlocattion();

          $("#datetime").datetimepicker({
              format: 'YYYY-MM-DD',
              locale: moment.locale('zh-cn')
          });

      });

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
      function getsex(mes){
          $("#sex").val(mes);
      }

      /**
       * 选择位置
       */
      function getlocal(mes){
          $("#adress").val(mes);
      }

      /**
       * 修改信息
       */
      function infomodifysubform(){
          if ($("input[name='user_name']").val().length == 0) {
              alert("请输入你的昵称。");
              return;
          }
          layer.open({
              type: 2
              ,content: '提交中...'
              ,time: 5
          });
          $("#infomodify-form_id").submit();
      }

      /**
       * 修改头像
       */
      function picmodifysubform() {
          if ($("input[name='picurl']").val().length == 0) {
              alert("请选择一个美丽图片。");
              return;
          }
          layer.open({
              type: 2
              ,content: '提交中...'
              ,time: 5
          });
          $("#picmodify-form_id").submit();
      }
  </script>

  </body>
</html>