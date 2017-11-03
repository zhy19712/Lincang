<%--
  Created by IntelliJ IDEA.
  User: zhuangyf
  Date: 2017/8/30
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>临沧市移民局</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="临沧市移民局">

    <!-- The styles -->
    <link href="../../css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="../../css/app.css" rel="stylesheet">
    <link href="../../css/chosen.min.css" rel="stylesheet">
    <link href="../../css/jquery.iphone.toggle.css" rel='stylesheet'>

<%--<link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/home.css">--%>
    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>

    <!-- The fav icon -->
    <link rel="shortcut icon" href="../../img/favicon.ico">
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .news-wrapper{
            display: inline-block;
            vertical-align: middle;
            width: 400px;
            margin: 32px;
        }
        .news-wrapper .header{
            height: 30px;
            line-height: 30px;
        }
        .news-wrapper .header .title{
            float: left;
            margin: 0;
            width: 98px;
            height: 30px;
            text-align: center;
            color: #fff;
            background: url(../../images/li.png) no-repeat top left;
        }
        .news-wrapper .header a{
            float: right;
        }
        .news li{
            list-style: none;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            word-break: keep-all;
        }
        .news li::before{
            content: "·";
            color: #2fa4e7;
            padding-right: 10px;
            font-size: 20px;
        }
        .news li a{
            color: #000000;
        }
        #lb{
            position: relative;
            height: 400px;
            width: 100%;
            overflow: hidden;
        }
        #lb img{
            float: left;
            height: 400px;
            width: 100%;
        }
        #circle{
            position: absolute;
            width: 140px;
            height: 50px;
            left: 50%;
            margin-left: -80px;
            bottom: 10px;
        }
        #circle span{
            display: inline-block;
            width: 20px;
            height: 20px;
            margin-left: 10px;
            border-radius: 50%;
            background-color: transparent;
            border: 2px solid #fff;
            cursor: pointer;
        }
        #circle span:first-child{
            background-color: #2fa4e7;
        }
    </style>
</head>

<body>

<div id="mycontainer" style="width: 1200px;margin: 0 auto;">
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <!-- 小屏幕时的导航按键 starts -->
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- 小屏幕时的导航按键 ends -->
            <!-- logo starts -->
            <a class="navbar-brand" href="/tohome.htm" style="width: 300px"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
                <span style="font-size: 26px;">临沧市移民开发局</span></a>
            <!-- logo ends -->

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <div class="btn btn-default">
                    <i class="glyphicon glyphicon-user"></i>
                    <c:if test="${user==null}">
                        <a href="/toLogin.htm" target="_blank">请登录</a>
                    </c:if>
                    <c:if test="${user!=null}">
                        欢迎<span style="margin: 0 6px;">${user.name}</span>, <a href="logout.do" >注销</a>
                    </c:if>
                </div>
            </div>
            <!-- user dropdown ends -->
        </div>


    </div>
    <!-- topbar ends -->
    <div class="ch-container">
        <div class="row">

            <!-- left menu starts -->
            <div class="col-sm-2 col-lg-2">
                <div class="sidebar-nav">
                    <div class="nav-canvas">
                        <div class="nav-sm nav nav-stacked">
                        </div>
                        <ul class="nav nav-pills nav-stacked main-menu">
                            <li class="nav-header">移民搬迁</li>
                            <li><a href="/yimin.htm"><i class="glyphicon glyphicon-tags"></i><span> 移民管理</span></a></li>
                            <%--<li><a href="/money.htm"><i class="glyphicon glyphicon-file"></i><span> 逐年补偿</span></a></li>--%>

                            <%--<li class="nav-header">协同办公</li>--%>
                            <%--<li><a href="/oa.htm"><i class="glyphicon glyphicon-edit"></i><span> 发文管理</span></a></li>--%>
                            <%--<li><a href="/shouwen.jsp"><i class="glyphicon glyphicon-edit"></i><span> 收文管理</span></a></li>--%>
                            <%--<li><a href="/tofeiwenjianguanli.htm"><i class="glyphicon glyphicon-edit"></i><span> 非文件管理</span></a></li>--%>
                        </ul>
                    </div>
                </div>
            </div>
            <!--/span-->
            <!-- left menu ends -->

            <div id="content" class="col-lg-10 col-sm-10">
                <!-- content starts -->
                <div class=" row">
                    <div class="col-lg-12">
                        <div class="box-inner ">
                            <div class="box-content">
                                <ul id="lb">
                                    <img src="../../images/lb1.jpg" alt="">
                                    <img src="../../images/lb2.jpg" alt="">
                                    <img src="../../images/lb3.jpg" alt="">
                                    <img src="../../images/lb4.jpg" alt="">
                                    <div id="circle">
                                        <span></span>
                                        <span></span>
                                        <span></span>
                                        <span></span>
                                    </div>
                                </ul>
                                <div class="news-wrapper">
                                    <div class="header">
                                        <p class="title">通知公告</p>
                                        <a href="#">查看更多</a>
                                    </div>
                                    <ul class="news">
                                        <li>
                                            <a href="#">云南临沧佤族织锦展吸引八方游客</a>
                                        </li>
                                        <li>
                                            <a href="#">从芯仙茗堂云顶筑巢自驾至临沧冰岛村 风尘仆仆只愿寻一味清凉自在</a>
                                        </li>
                                        <li>
                                            <a href="#">云南临沧沧源县新米节接待游客163946人次</a>
                                        </li>
                                        <li>
                                            <a href="#">武警临沧支队:“魔鬼周”极限训练 锻造反恐精武</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="news-wrapper">
                                    <div class="header">
                                        <p class="title">最新消息</p>
                                        <a href="#">查看更多</a>
                                    </div>
                                    <ul class="news">
                                        <li>
                                            <a href="#">临沧荣获“全国森林旅游示范市”称号</a>
                                        </li>
                                        <li>
                                            <a href="#">临沧4天缴获114公斤冰毒 两批毒贩已落网</a>
                                        </li>
                                        <li>
                                            <a href="#">临沧供电检企联合开展职务犯罪警示教育</a>
                                        </li>
                                        <li>
                                            <a href="#">阿里巴巴太极禅苑文化驿栈在云南临沧揭牌</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">

                </div>
                <!-- content ends -->
            </div>
        </div>


        <hr>

        <footer class="row">
            <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; 临沧市移民局</p>

            <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                    href="http://www.bhidi.com">北京院</a></p>
        </footer>

    </div><!--/.fluid-container-->
</div>



<!-- external javascript -->
<script src="../../js/bootstrap.min.js"></script>
<!-- library for cookie management -->
<script src="../../js/jquery.cookie.js"></script>
<!-- select or dropdown enhancer -->
<script src="../../js/chosen.jquery.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="../../js/jquery.iphone.toggle.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="../../js/jquery.history.js"></script>
<script src="../../js/app.js"></script>
<script>
    var x = 1;
    function lb() {
        x += 1 ;
        $("#lb>img").css("display","none");
        $("#circle>span").css("background","transparent");
        if(x==1){
            $("#lb>img:first-child").fadeIn();
            $("#circle>span:first-child").css("background","#2fa4e7");
        }else if(x==2){
            $("#lb>img:nth-child(2)").fadeIn();
            $("#circle>span:nth-child(2)").css("background","#2fa4e7");
        }else if(x==3){
            $("#lb>img:nth-child(3)").fadeIn();
            $("#circle>span:nth-child(3)").css("background","#2fa4e7");
        }else if(x==4){
            $("#lb>img:nth-child(4)").fadeIn();
            $("#circle>span:nth-child(4)").css("background","#2fa4e7");
            x = 0;
        }else if(x==5){
            $("#lb>img:first-child").fadeIn();
            $("#circle>span:first-child").css("background","#2fa4e7");
            x == 1;
        }
    }
    var set1 = setInterval(lb,3000);
    $("#lb").hover(function () {
        clearInterval(set1)
    },function () {
        set1 = setInterval(lb,3000);
    });
    $("#circle>span").click(function () {
        var index = $(this).index() + 1;
        x = index;
        $("#lb>img").css("display","none");
        $("#circle>span").css("background","transparent");
        $("#lb>img:nth-child("+ index +")").fadeIn();
        $("#circle>span:nth-child("+ index +")").css("background","#2fa4e7");
    })
</script>

</body>
</html>


