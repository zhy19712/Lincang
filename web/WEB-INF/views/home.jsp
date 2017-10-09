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
            <a class="navbar-brand" href="oa.jsp" style="width: 300px"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
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
                        欢迎${user.username}, <a href="logout.do" >注销</a>
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
                            <li class="nav-header">我的办公</li>
                            <li><a href="/oa.htm"><i class="glyphicon glyphicon-edit"></i><span> 发文管理</span></a></li>
                            <li><a href="./money.jsp"><i class="glyphicon glyphicon-file"></i><span> 资金申请</span></a></li>

                            <li class="nav-header">我的业务</li>
                            <li><a href="/yimin.htm"><i class="glyphicon glyphicon-tags"></i><span> 业务管理</span></a></li>
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
                                <img src="`../../images/timg.jpg" alt="" style="width: 100%;">
                                <div class="news-wrapper">
                                    <div class="header">
                                        <p class="title">通知公告</p>
                                        <a href="#">查看更多</a>
                                    </div>
                                    <ul class="news">
                                        <li>
                                            <a href="#">新闻1新闻1新闻1新闻1新闻1新闻1新闻1新闻1新闻1新闻1新闻1新闻1</a>
                                        </li>
                                        <li>
                                            <a href="#">新闻2</a>
                                        </li>
                                        <li>
                                            <a href="#">新闻3</a>
                                        </li>
                                        <li>
                                            <a href="#">新闻4</a>
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
                                            <a href="#">新闻1新闻1新闻1新闻1新闻1新闻1新闻1新闻1新闻1</a>
                                        </li>
                                        <li>
                                            <a href="#">新闻2</a>
                                        </li>
                                        <li>
                                            <a href="#">新闻3</a>
                                        </li>
                                        <li>
                                            <a href="#">新闻4</a>
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



</body>
</html>


