<%--
  Created by IntelliJ IDEA.
  User: zhuangyf
  Date: 2017/8/17
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>临沧市移民局</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="临沧市移民局">

    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <link href="css/choose.min.css" rel="stylesheet">
    <link href="css/jquery.iphone.toggle.css" rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="css/mycss.css">


    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
</head>

<body>
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
        <a class="navbar-brand" href="index.jsp"> <img alt="Logo" src="img/logo20.png" class="hidden-xs"/>
            <span>临沧市移民开发局</span></a>
        <!-- logo ends -->

        <!-- user dropdown starts -->
        <div class="btn-group pull-right">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> 管理员</span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#">信息</a></li>
                <li class="divider"></li>
                <li><a href="login.html">注销</a></li>
            </ul>
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
                    <ul class="nav nav-pills nav-stacked main-menu" id="myTab">
                        <li><a href="#home"><i class="glyphicon glyphicon-home"></i><span> 首页</span></a></li>
                        <li class="nav-header">我的表单</li>
                        <li><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span> 提交表单</span></a></li>
                        <li><a href="#progress1"><i class="glyphicon glyphicon-refresh"></i><span> 在办表单</span></a></li>
                        <li><a href="#completed1"><i class="glyphicon glyphicon-check"></i><span> 已办表单</span></a></li>

                        <li class="nav-header">我的事务</li>
                        <li><a href="#new2"><i class="glyphicon glyphicon-tags"></i><span> 待办事务</span></a></li>
                        <li><a href="#progress2"><i class="glyphicon glyphicon-refresh"></i><span> 在办事务</span></a></li>
                        <li><a href="#completed2"><i class="glyphicon glyphicon-check"></i><span> 已归档事务</span></a></li>

                        <li class="nav-header">我的审批</li>
                        <li><a href="#new3"><i class="glyphicon glyphicon-tags"></i><span> 待审批表单</span></a></li>
                        <li><a href="#completed3"><i class="glyphicon glyphicon-check"></i><span> 审批记录</span></a></li>
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
                        <div class="box-inner homepage-box">
                            <div class="box-content">
                                <div id="myTabContent" class="tab-content">
                                    <div class="tab-pane active" id="home">
                                        <div>
                                            <ul class="breadcrumb">
                                                <li>
                                                    <a href="#">首页</a>
                                                </li>
                                            </ul>
                                        </div>
                                        home
                                    </div>
                                    <div class="tab-pane active" id="new1">
                                        <div>
                                            <ul class="breadcrumb">
                                                <li>
                                                    <a href="#">我的表单</a>
                                                </li>
                                                <li>
                                                    <a href="#">提交表单</a>
                                                </li>
                                            </ul>
                                        </div>

                                        <table id="originalForm" class="table table-striped table-bordered">
                                            <thead>
                                            <tr>
                                                <th>编号</th>
                                                <th>标题</th>
                                                <th>创建时间</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                        </table>
                                        <div class="row myrow">
                                            <div class="col-lg-5">
                                                <span>报稿单位</span>
                                                <input type="text">
                                            </div>
                                            <div class="col-lg-3">
                                                <span>报稿</span>
                                                <input type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <span>科室核稿</span>
                                                <input type="text">
                                            </div>
                                        </div>
                                        <div class="row myrow">
                                            <div class="col-lg-5">
                                                <span>印刷</span>
                                                <input type="text">
                                            </div>
                                            <div class="col-lg-3">
                                                <span>校对</span>
                                                <input type="text">
                                            </div>
                                            <div class="col-lg-4">
                                                <span>份数</span>
                                                <input type="text">
                                            </div>
                                        </div>
                                        <div class="row myrow">
                                            <div class="col-lg-12">
                                                <span>附件</span>
                                                <input type="text">
                                            </div>
                                        </div>
                                        <div class="row myrow">
                                            <div class="col-lg-12">
                                                <span>主题词</span>
                                                <input type="text">
                                            </div>
                                        </div>
                                        <div class="row myrow">
                                            <div class="col-lg-12">
                                                <p>标题</p>
                                                <textarea name="" id="" cols="30" rows="10"></textarea>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="tab-pane" id="progress1">
                                        <div>
                                            <ul class="breadcrumb">
                                                <li>
                                                    <a href="#">我的表单</a>
                                                </li>
                                                <li>
                                                    <a href="#">在办表单</a>
                                                </li>
                                            </ul>
                                        </div>
                                        progress1
                                    </div>
                                    <div class="tab-pane" id="completed1">
                                        completed1
                                    </div>
                                    <div class="tab-pane" id="new2">
                                        new2
                                    </div>
                                    <div class="tab-pane" id="progress2">
                                        progress2
                                    </div>
                                    <div class="tab-pane" id="completed2">
                                        completed2
                                    </div>
                                    <div class="tab-pane" id="new3">
                                        new3
                                    </div>
                                    <div class="tab-pane" id="completed3">
                                        completed3
                                    </div>
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

<!-- external javascript -->
<script src="js/bootstrap.min.js"></script>
<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- select or dropdown enhancer -->
<script src="js/chosen.jquery.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>

