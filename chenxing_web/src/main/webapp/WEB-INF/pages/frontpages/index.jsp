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
        <a class="navbar-brand" href="#">辰星🌟</a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">主页</a></li>
          <li><a href="#">足迹</a></li>
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
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="cxtext">
              北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，
              是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，
              建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、
              保存最为完整的木质结构古建筑之一。北京故宫于明成祖永乐四年（1406年）开始建设，
              以南京故宫为蓝本营建，到永乐十八年（1420年）建成。它是一座长方形城池，
              南北长961米，东西宽753米，四面围有高10米的城墙，城外有宽52米的护城河。
              紫禁城内的建筑分为外朝和内廷两部分。外朝的中心为太和殿、中和殿、保和殿，
              统称三大殿，是国家举行大典礼的地方。内廷的中心是乾清宫、交泰殿、坤宁宫，
              统称后三宫，是皇帝和皇后居住的正宫。
            </div>
            <br>
            <div class="btn-group" style="margin-left: -15px;">
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-map-marker" style="font-size: 10px;">北京市朝阳区新华金融大厦</span>
              </button>
            </div>
            <br>
            <HR width="80%" color=#987cb9 SIZE=10 />
            <div class="btn-group" style="margin-left: -15px;">
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-eye-open">9822</span>
              </button>
            </div>
            <div class="btn-group" style="float: right; margin-right: -10px;">
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-share-alt">155</span>
              </button>
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-edit">25</span>
              </button>
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-heart-empty">15</span>
                <!-- <span class="glyphicon glyphicon-heart"></span> 已赞 -->
              </button>
            </div>
          </div>
        </div>
        <br>
        <h2></h2>
        <div class="fakeimg" style="background-image: url('gz.jpg')"></div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="cxtext">
              广州塔（英语：Canton Tower）又称广州新电视塔，昵称小蛮腰。位于广州市海珠区（
              艺洲岛）赤岗塔附近，距离珠江南岸125米，与珠江新城、花城广场、海心沙岛隔江相望。
              广州塔塔身主体高454米，天线桅杆高146米，总高度600米。是中国第一高塔，
              世界第二高塔，仅次于东京晴空塔，是国家AAAA级旅游景区。广州塔塔身168米–334.4米
              处设有“蜘蛛侠栈道”，是世界最高最长的空中漫步云梯。塔身422.8米处设有旋转餐厅，
              是世界最高的旋转餐厅。塔身顶部450~454米处设有摩天轮，是世界最高摩天轮。
              天线桅杆455米~485米处设有“极速云霄”速降游乐项目，是世界最高的垂直速降游乐项目。
              天线桅杆488米处设有户外摄影观景平台，是世界最高的户外观景平台，超越了迪拜哈利法塔的442米室外
              观景平台，以及加拿大国家电视塔447米的“天空之盖”的高度
            </div>
            <br>
            <div class="btn-group" style="margin-left: -15px;">
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-map-marker" style="font-size: 10px;">黑龙江省桦南县林业大院3号楼</span>
              </button>
            </div>
            <br>
            <HR width="80%" color=#987cb9 SIZE=10 />
            <div class="btn-group" style="margin-left: -15px;">
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-eye-open">4222</span>
              </button>
            </div>
            <div class="btn-group" style="float: right;">
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-share-alt">92</span>
              </button>
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-edit">65</span>
              </button>
              <button type="button" class="btn btn-default" style="border:none">
                <span class="glyphicon glyphicon-heart-empty">23</span>
              </button>
            </div>
          </div>
        </div>
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