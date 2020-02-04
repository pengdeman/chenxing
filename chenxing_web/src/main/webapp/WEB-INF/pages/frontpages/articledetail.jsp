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
        width: 70px;
        height: 70px;
        margin-left: -7px;
        border-radius: 50%;
      }
      .cxhftouxiang {
        width: 40px;
        height: 40px;
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
        <a class="navbar-brand" href="<%=basePath%>index">辰星🌟</a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li><a href="<%=basePath%>index">首页</a></li>
          <c:if test="${user == null}">
            <li><a href="javascript:alert('登录后才可查看！')">个人中心</a></li>
            <li><a href="javascript:alert('登录后才可查看！')">留言板</a></li>
            <li><a href="javascript:alert('登录后才可查看！')">个人档</a></li>
          </c:if>
          <c:if test="${user != null}">
            <li><a href="<%=basePath%>personalcenter/myselfindex">个人中心</a></li>
            <li><a href="javascript:alert('设计中！')">留言板</a></li>
            <li><a href="<%=basePath%>personalcenter/index">个人档</a></li>
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
        <c:if test="${user == null }">
          <div class="fakeimg" style="background-image: url('wxr.png');">
        </c:if>
        <c:if test="${user != null }">
          <div class="fakeimg" style="background-image: url('pic/${user.img }');">
        </c:if>
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
                  <c:if test="${user.sex eq '男生'}">
                      <strong style="color: dodgerblue">♂</strong>
                  </c:if>
                  <c:if test="${user.sex eq '女生'}">
                      <strong style="color: deeppink">♀</strong>
                  </c:if>
                  <c:if test="${user.sex eq '保密'}">
                      <strong style="color: lawngreen">♀/♂</strong>
                  </c:if>
              </td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>关注:</strong></td>
              <td>0</td>
              <td style=" white-space: nowrap;"><strong>粉丝:</strong></td>
              <td>0</td>
            </tr>
            <tr>
              <td style=" white-space: nowrap;"><strong>访客:</strong></td>
              <td>0</td>
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
          <div class="fakeimg" style="background-image: url('pic/${article.picurl }')"></div>
          <div class="panel panel-default">
            <div class="panel-body">
              <table class="table" frame="void">
                <tr style="height: 20px;">
                  <td rowspan="2" width="55px;">
                    <img src="pic/${article.img }" class="cxtouxiang">
                  </td>
                  <td>
                    <label style="margin-top: 5px;">${article.userName }</label>&nbsp;&nbsp;<label title="登录365天，皇冠等级">👑</label>
                    <c:if test="${article.creUid == user.id}">
                      <button type="button" class="btn btn-default cxguanzhu" onclick="deletearticle(${article.id })">删除</button>
                    </c:if>
                  </td>
                </tr>
                <tr>
                  <td class="cxtime">
                    <fmt:parseDate value="${article.creTime }"
                                   pattern="yyyy-MM-dd HH:mm:ss" var="creTime"></fmt:parseDate>
                    <fmt:formatDate value="${creTime}"
                                    pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate>
                  </td>
                </tr>
              </table>
              <div class="cxtext">
                  ${article.article }
              </div>
              <br>
              <div class="btn-group" style="margin-left: -15px;">
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-map-marker" style="font-size: 10px;">${article.location }</span>
                </button>
              </div>
              <br>
              <HR width="100%" color=#987cb9 SIZE=10 />
              <div class="btn-group" style="margin-left: -15px;">
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-eye-open"> ${article.ydnum }</span>
                </button>
              </div>
              <div class="btn-group" style="float: right; margin-right: -10px;">
                <button type="button" class="btn btn-default bodernone">
                  <span class="glyphicon glyphicon-share-alt"> ${article.zfnum }</span>
                </button>
                <button type="button" class="btn btn-default bodernone" data-toggle="modal" data-target="#pingModal" onclick="fz(${article.id }, ${article.creUid })">
                  <span class="glyphicon glyphicon-edit"> ${article.plnum }</span>
                </button>
                <button id="zanid" type="button" class="btn btn-default bodernone" onclick="zan(${article.id })">
                  <c:if test="${article.iszan == 1 }">
                    <span style="color: red;" class="glyphicon glyphicon-heart"> ${article.dznum }</span>
                  </c:if>
                  <c:if test="${article.iszan != 1 }">
                    <span class="glyphicon glyphicon-heart-empty"> ${article.dznum }</span>
                  </c:if>
                </button>
              </div>
            </div>
          </div>
        <div id="plzj"><!-- 评论最外层 用于追加评论 -->
        <c:forEach items="${articleReplyList}" var="items" varStatus="vas">
        <!-- 回复区域开始 -->
        <div class="panel panel-default" id="deleteid${items.id }">
          <div class="panel-body">
            <table>
              <tr style="height: 10px;">
                <td rowspan="2" width="30px;">
                  <img src="pic/${items.replyImg }" class="cxhftouxiang">
                </td>
              </tr>
              <tr>
                <td style="font-size: 12px;">
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <a href="">${items.replyUname}</a>
                  <label style="color:#aaaaaa;">回复了</label>
                  <a href="">${items.breplyUname}</a>
                </td>
                <c:if test="${items.replyUid == user.id}">
                  <button type="button" class="btn btn-default cxguanzhu" onclick="deletearticlepl(${items.id }, ${items.articleId })">删除</button>
                </c:if>
              </tr>
            </table>
            <br>
            <div class="cxtext">
              ${items.replyComment }
            </div>
            <br>
            <HR width="100%" color=#987cb9 SIZE=5 />
            <div style="margin-top: -5px; font-size: 12px; color: #aaaaaa;">
              回复时间：
              <fmt:parseDate value="${items.replyTime }"
                             pattern="yyyy-MM-dd HH:mm:ss" var="creTime"></fmt:parseDate>
              <fmt:formatDate value="${creTime}"
                              pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </div>
            <div class="btn-group" style="float: right; margin-right: -10px; margin-top: -25px;">
              <button type="button" class="btn btn-default bodernone" data-toggle="modal" data-target="#pingModal" onclick="fz(${article.id }, ${items.replyUid })">
                <span class="glyphicon glyphicon-edit"> *</span>
              </button>
              <button id="paid${items.id }" type="button" class="btn btn-default bodernone" onclick="pzan(${items.id })">
                <c:if test="${items.iszan == 1 }">
                  <span style="color: red;" class="glyphicon glyphicon-heart"> ${items.replyPid }</span>
                </c:if>
                <c:if test="${items.iszan != 1 }">
                  <span class="glyphicon glyphicon-heart-empty"> ${items.replyPid }</span>
                </c:if>
              </button>
            </div>
          </div>
        </div>
        <!-- 回复区域结束 -->
        </c:forEach>
        </div><!-- 评论最外层 用于追加评论 -->
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

    <!-- 评论模态框（Modal） -->
    <div class="modal fade" id="pingModal" tabindex="-1" role="dialog" aria-labelledby="pingModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            请在下面输入您的评论：
          </div>
          <div class="modal-body" id="moadlimg">
            <textarea rows="7" style="width: 100%;" name="replyContents" id="replyContents"></textarea>
            <input type="hidden" value="" name="articleId" id="articleId">
            <input type="hidden" value="" name="breplyId" id="breplyId">
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="submitreply()">提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal -->
    </div>
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

  <script type="text/javascript">

  /**
    * 文章赞
    */
      function zan(id){
          var zanid = "zanid";
          $.ajax({
              cache:true,
              type:"POST",
              url:"<%=basePath%>/article/zan",
              data:{id: id},
              async:false,
              error:function(request){
                  alert("Connection error");
              },
              success:function(data){
                  var jsonObj=eval("("+data+")");
                  if(jsonObj.num == 10000000){
                      alert("您已点过赞了！");
                  }else if(jsonObj.num == 20000000){
                      alert("请您先登录再点赞！");
                  }else{
                      document.getElementById(zanid).innerHTML = "<span style=\"color: red;\" class=\"glyphicon glyphicon-heart\"> "+jsonObj.num+"</span>";
                      alert("点赞成功！");
                  }
              }
          });
      }

    /**
     * 评论赞
     */
      function pzan(id){
          var paid = "paid"+id;
          $.ajax({
              cache:true,
              type:"POST",
              url:"<%=basePath%>/article/zans",
              data:{id: id},
              async:false,
              error:function(request){
                  alert("Connection error");
              },
              success:function(data){
                  var jsonObj=eval("("+data+")");
                  if(jsonObj.num == 10000000){
                      alert("您已点过赞了！");
                  }else if(jsonObj.num == 20000000){
                      alert("请您先登录再点赞！");
                  }else{
                      document.getElementById(paid).innerHTML = "<span  style=\"color: red;\" class=\"glyphicon glyphicon-heart\"> "+jsonObj.num+"</span>";
                      alert("点赞成功！");
                  }
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
     * 异步提交评论
     */
      function submitreply(){
          $('pingModal').modal('hide');
          layer.open({
              type: 2
              ,content: '正在提交...'
              ,time: 6
          });
          var articleId = $("#articleId").val();
          var breplyId = $("#breplyId").val();
          var replyContents = $("#replyContents").val();
          $.ajax({
              cache:true,
              type:"POST",
              url:"<%=basePath%>/article/ajaxSaveReply",
              data:{articleId: articleId, breplyId: breplyId, replyContents: replyContents},
              async:false,
              error:function(request){
                  alert("提交失败！");
              },
              success:function(data){
                  var jsonObj=eval("("+data+")");
                  if(jsonObj.success == 0){
                      window.location.href="<%=basePath%>index";
                  }else if(jsonObj.success == 1){
                      var comment = "";
                      comment += "<div class='panel panel-default' id='deleteid"+jsonObj.articleReply.id+"'><div class='panel-body'><table><tr style='height: 10px;'><td rowspan='2' width='30px;'><img src='pic/"+jsonObj.articleReply.replyImg+"' class='cxhftouxiang'></td></tr><tr><td style='font-size: 12px;'>&nbsp;&nbsp;&nbsp;&nbsp;";
                      comment += "<a href=''>"+jsonObj.articleReply.replyUname+"</a><label style='color:#aaaaaa;'>回复了</label><a href=''>"+jsonObj.articleReply.breplyUname+"</a></td><button type='button' class='btn btn-default cxguanzhu' onclick='deletearticlepl("+jsonObj.articleReply.id+", "+jsonObj.articleReply.articleId+")'>删除</button></tr></table><br>";
                      comment += "<div class='cxtext'>"+jsonObj.articleReply.replyComment+"</div><br><HR width='100%' color=#987cb9 SIZE=5 /><div style='margin-top: -5px; font-size: 12px; color: #aaaaaa;'>回复时间：";

                      var t = jsonObj.articleReply.replyTime.substring(0,19);
                      t = t.replace('-', '年');
                      t = t.replace('-', '月');
                      var t2 = t;
                      t2 = t2.substring(10,19);
                      t = t.substring(0,10);
                      t = t+"日"+t2;

                      comment += t+"</div><div class='btn-group' style='float: right; margin-right: -10px; margin-top: -25px;'><button type='button' class='btn btn-default bodernone'><span class='glyphicon glyphicon-edit' data-toggle='modal' data-target='#pingModal' onclick='fz("+jsonObj.articleReply.articleId+", "+jsonObj.articleReply.replyUid+")'> *</span></button><button id='paid"+jsonObj.articleReply.id+"' type='button' class='btn btn-default bodernone' onclick='pzan("+jsonObj.articleReply.id+")'>";
                      comment += "<c:if test='${jsonObj.articleReply.iszan == 1 }'><span style='color: red;' class='glyphicon glyphicon-heart'> "+jsonObj.articleReply.replyPid+"</span></c:if><c:if test='${jsonObj.articleReply.iszan != 1 }'><span class='glyphicon glyphicon-heart-empty'> "+jsonObj.articleReply.replyPid+"</span></c:if></button></div></div></div>";

                      $("#plzj").append(comment);
                      alert("回复成功！");
                  }
              }
          });
      }







     /**
      * 评论模态框附值
      */
      function fz(articleId, breplyId){
          $("#articleId").val(articleId);
          $("#breplyId").val(breplyId);
      }

      /**
       * 获取后台message信息
       */
      $(function() {
          messge = '${messge}';
          if(messge != '') {
              alert(messge);
          }
      });

      /**
       * 登出
       */
      function tuichu(){
          window.location.href="<%=basePath%>user/loginout";
      }

      /**
       * 删除文章
       */
      function deletearticle(id){
          if(confirm("真的要删除这条分享吗?")) {
              window.location.href="<%=basePath%>article/deletearticle?id="+id;
          }
      }

      /**
       * 删除评论
       */
      function deletearticlepl(id, articleId){
          if(confirm("真的要删除这条评论吗?")) {
              $.ajax({
                  cache:true,
                  type:"POST",
                  url:"<%=basePath%>article/deletearticlepl",
                  data:{id: id, articleId: articleId},
                  async:false,
                  error:function(request){
                      alert("删除失败！");
                  },
                  success:function(data){
                      var jsonObj=eval("("+data+")");
                      if(jsonObj.flag == 1 ){
                          $("#deleteid"+id).hide();
                          alert("删除成功！");
                      }else if(jsonObj.flag == 2){
                          alert("删除失败！");
                      }
                  }
              });
          }
      }

  </script>
  </body>
</html>