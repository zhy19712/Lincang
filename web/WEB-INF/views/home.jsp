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
    <link rel="stylesheet" href="../../css/mycommon.css">

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
            width: 49%;
            padding: 32px;
            box-sizing: border-box;
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


        .baner_box{position:relative;width:100%;overflow:hidden;}
        .baner_box .baner{overflow:hidden;z-index:0;margin:0 auto 0;position:absolute}
        .baner_box .baner li{float:left;z-index:0;text-align:center;position:relative;overflow:hidden}
        .baner_box .baner li img{width:100%;height:100%}
        .baner_box a{display:inline-block; position:absolute; top: 50%;transform: translateY(-50%);z-index:10;cursor:pointer;text-align:center;font-size:32px}
        .baner_box .left{display:none;width:30px;height:52px;line-height:52px;color:#fff;left:10px;background:rgba(0,0,0,.7)}
        .baner_box .right{display:none;width:30px;height:52px;line-height:52px;color:#fff;right:10px;background:rgba(0,0,0,.7)}
        .baner_box ol{position:absolute; left: 50%;transform: translateX(-50%);display:table;bottom:10px;z-index:10}
        .baner_box ol li{list-style:none;float:left;width:12px;height:12px;margin-left:5px;margin-right:5px;border-radius:50%;background:#ccc;cursor:pointer}
        .baner_box ol li.red{background:red}


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
    </style>
</head>

<body>

<div id="bg">
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
            <a class="navbar-brand" href="/tohome.htm" style="width: 500px"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
                <span style="font-size: 26px;font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif";>临沧市移民局数字化管理平台</span></a>
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
    <%--<div id="mycontainer" style="width: 1200px;margin: 0 auto;">--%>
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
                                <%--<li><a href="/zhunianbuchang.htm"><i class="glyphicon glyphicon-file"></i><span> 逐年补偿2</span></a></li>--%>

                                <li class="nav-header">协同办公</li>
                                <li><a href="/oa.htm"><i class="glyphicon glyphicon-edit"></i><span> 发文管理</span></a></li>
                                <li><a href="/shouwen.jsp"><i class="glyphicon glyphicon-edit"></i><span> 收文管理</span></a></li>
                                <li><a href="/tofeiwenjianguanli.htm"><i class="glyphicon glyphicon-edit"></i><span> 非文件管理</span></a></li>
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

                                    <div class="baner_box">
                                        <ul class="baner">
                                            <li><img src="../../images/1.jpg" /></li>
                                            <li><img src="../../images/2.jpg" /></li>
                                            <li><img src="../../images/3.jpg" /></li>
                                            <li><img src="../../images/4.jpg" /></li>
                                        </ul>
                                        <ol></ol>
                                        <a class="left"><</a>
                                        <a class="right">></a>
                                    </div>

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



        </div><!--/.fluid-container-->
    <%--</div>--%>
    <footer class="row">
        <p class="col-md-12 col-sm-12 col-xs-12 copyright" style="text-align: center;">&copy; 临沧市移民局 <span style="margin-left: 10px;">Powered by: </span><a
                href="http://www.bhidi.com">北京院</a></p>
    </footer>



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


    $(function() {
        var speed = 500; //animate time
        var setsed = 2000; //setInterval time
        var gao=$(window).height();
        var width = $(".baner_box").width(); //每次滚动距离
        var len = $(".baner li").length;
        var twidth = parseInt(width * (len + 1)); //baner总宽度设置
        var set;

        $(window).resize(function () {
            gao=$(window).height();
            width = $(".baner_box").width();
            $(".baner_box").css("height",gao/2);
            twidth = parseInt(width * (len + 1)); //baner总宽度设置
            //baner总宽度赋值
            $(".baner").width(twidth);
            $(".baner li").width(width); //可以动态选择像li中添加宽度，根据父元素盒子的宽度
        });

        //baner_box的高度为浏览器高度的一半
        $(".baner_box").css("height",gao/2);
        //baner总宽度赋值
        $(".baner").width(twidth);
        $(".baner li").width(width); //可以动态选择像li中添加宽度，根据父元素盒子的宽度
        //根据图片的index数，加载原点
        var olh = '<li></li>';
        var str="";
        for (var i = 0; i < len; i++) {
            str+=olh
        }
        $("ol").html(str);
        $("ol").find('li').first().addClass('red');

        //防止连续点击
        var _timer = {};
        function delay_till_last(id, fn, wait) {
            if (_timer[id]) {
                window.clearTimeout(_timer[id]);
                delete _timer[id];
            }

            return _timer[id] = window.setTimeout(function() {
                fn();
                delete _timer[id];
            }, wait);
        }

        //左滚动
        $(".left").on('click',
            function() {
                delay_till_last(1, function() {//注意 id 是唯一的，防止连续点击
                    var pleft = parseInt($(".baner").css("left")) + width + 'px';
                    var cpleft = '-' + parseInt((len-1) * width) + 'px';
                    var cleft = parseInt($(".baner").css("left"));
                    var cindex = parseInt( - (cleft / width));
                    if (cleft == 0) {
                        $(".baner").stop().animate({
                                left: cpleft
                            },
                            speed);
                    } else {
                        $(".baner").stop().animate({
                                left: pleft
                            },
                            speed);
                    }

                    if (cindex == 0) {
                        $("ol li").eq(len-1).addClass('red').siblings('li').removeClass('red');
                    } else {
                        $("ol li").eq(cindex - 1).addClass('red').siblings('li').removeClass('red');
                    }
                }, 500);
            });
        //右滚动
        $(".right").on('click',
            function() {
                delay_till_last(2, function() {//注意 id 是唯一的，防止连续点击
                    var nleft = parseInt($(".baner").css("left")) - width + 'px';
                    var npleft = parseInt( - width * (len-1));
                    var cleft = parseInt($(".baner").css("left"));
                    var cindex = parseInt( - (cleft / width));

                    if (cleft <= npleft) {
                        $(".baner").stop().animate({
                                left: 0
                            },
                            speed);
                    } else {
                        $(".baner").stop().animate({
                                left: nleft
                            },
                            speed);
                    }
                    if (cindex == (len-1)) {
                        $("ol li").eq(0).addClass('red').siblings('li').removeClass('red');
                    } else {
                        $("ol li").eq(cindex + 1).addClass('red').siblings('li').removeClass('red');
                    }

                },500);
            });
        //当前状态
        $("ol li").on('click',
            function() {
                var colindex = $(this).index();
                var collert = '-' + colindex * width;
                $(".baner").stop().animate({
                        left: collert
                    },
                    speed);
                $(this).addClass('red').siblings('li').removeClass('red');
            });
        //定时器
        function seTime() {
            set = setInterval(function() {
                    $(".right").click();
                },
                setsed);
        }
        //清除定时器
        $(".baner_box").hover(function() {
                clearInterval(set);
                $(".left").css("display","block");
                $(".right").css("display","block");
            },
            function() {
                seTime();
                $(".left").css("display","none");
                $(".right").css("display","none");
            });
        seTime();
    });
</script>

</body>
</html>


