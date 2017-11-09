<%--
  Created by IntelliJ IDEA.
  User: zhuangyf
  Date: 2017/8/30
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>临沧市移民局</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="临沧市移民局">

    <!-- The styles -->
    <link href="../../css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="../../css/app.css" rel="stylesheet">
    <link href='../../css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='../../css/animate.min.css' rel='stylesheet'>
    <style>
        input:-webkit-autofill {
            -webkit-box-shadow: 0 0 0px 1000px white inset;
            border: 1px solid #CCC!important;
        }
        #bg{
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: 100%;
            background: url(../../images/bg-1.png) no-repeat left top;
            background-size: 100% 100%;
        }
        #bg div {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 1080px;
            background-color: rgba(0, 0, 0, 0.25);
        }
        footer{
            color: #fff !important;
            margin-top: 20px !important;
            position: absolute !important;
            bottom: 0 !important;
            width: 100% !important;
        }
    </style>
    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>

</head>

<body>
<div id="bg">
    <div></div>
</div>
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
        <a class="navbar-brand" href="" style="width: 500px;"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
            <span style="font-size: 26px;font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif";>临沧市移民局数字化管理平台</span></a>
        <!-- logo ends -->

    </div>
</div>
<div class="ch-container">
    <div class="row">


        <div class="row" style="position: absolute;top: 50%;margin-top: -180px;">

            <div class="well col-md-5 center login-box">
                <h4>用户登录</h4>
                <div class="alert alert-info">
                    <c:if test="${msg==null}">
                        用户名密码登录
                    </c:if>
                    <c:if test="${msg!=null}">
                        ${msg}
                    </c:if>
                </div>
                <form class="form-horizontal" action="login.do" method="post">
                    <fieldset>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                            <input type="text" name="username" class="form-control" placeholder="用户名">
                        </div>
                        <div class="clearfix"></div>
                        <br>

                        <div class="input-group input-group-lg">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                            <input type="password" name="password" class="form-control" placeholder="密码">
                        </div>
                        <div class="clearfix"></div>

                        <div class="input-prepend">
                            <label class="remember" for="remember"><input type="checkbox" name = "login_auto_login" id="remember" value="1"> 记住我</label>
                        </div>
                        <div class="clearfix"></div>

                        <p class="center col-md-5">
                            <input type="submit"  value="登录" class="btn btn-primary"/>
                        </p>
                    </fieldset>
                </form>
            </div>
            <!--/span-->
        </div><!--/row-->
    </div><!--/fluid-row-->

</div><!--/.fluid-container-->
<footer class="row" style="color: #fff;margin-top: 50px;">
    <p class="col-md-12 col-sm-12 col-xs-12 copyright" style="text-align: center;">&copy; 临沧市移民局 <span style="margin-left: 10px;">Powered by: </span><a
            href="http://www.bhidi.com">北京院</a></p>
</footer>



<script src="../../js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="../../js/jquery.cookie.js"></script>
<!-- for iOS style toggle switch -->
<script src="../../js/jquery.iphone.toggle.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="../../js/jquery.history.js"></script>
<script src="../../js/app.js"></script>



</body>
</html>
