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
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="../../css/mycss.css">
    <link rel="stylesheet" href="../../css/oa.css">
    <link rel="stylesheet" href="../../css/ui-choose.css">
    <link rel="stylesheet" href="../../css/jedate.css">
    <link rel="stylesheet" href="../../css/money.css">
    <style>
        .last{
            border-bottom: 1px solid red !important;
        }
    </style>


    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery-form.min.js"></script>
    <script src="../../js/ui-choose.js"></script>
    <script src="../../js/jquery.jedate.js"></script>
    <!-- The fav icon -->
    <link rel="shortcut icon" href="../../img/favicon.ico">
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
        <a class="navbar-brand" href="oa.jsp" style="width: 300px;"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
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
                    欢迎${user.username}<a href="logout.do" >注销</a>
                </c:if>
            </div>
        </div>
        <span id="status" style="display:none;width:0;height:0;">${user.level}</span>
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
                        <li id="m_apply1" class="nav-header">我的申请</li>
                        <li><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span>资金申请</span></a></li>
                        <li><a href="#progress1"><i class="glyphicon glyphicon-refresh"></i><span> 区县资金申请</span></a></li>

                        <li class="nav-header">我的事务</li>
                        <li><a href="#new2"><i class="glyphicon glyphicon-tags"></i><span> 财务科已到资金</span></a></li>
                        <li><a href="#progress2"><i class="glyphicon glyphicon-refresh"></i><span> 规划科资金到账</span></a></li>
                        <li><a href="#completed2"><i class="glyphicon glyphicon-check"></i><span> 区县接收资金申请通知</span></a></li>
                        <li><a href="#completed4"><i class="glyphicon glyphicon-check"></i><span> 财务转账页面</span></a></li>
                        <li><a href="#completed5"><i class="glyphicon glyphicon-check"></i><span> 区县收款页面</span></a></li>
                        <li><a href="#completed6"><i class="glyphicon glyphicon-check"></i><span> 资金去向明细</span></a></li>

                        <li class="nav-header">我的审批</li>
                        <li><a href="#new3"><i class="glyphicon glyphicon-tags"></i><span> 规划科批复</span></a></li>
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
                            <div id="myTabContent" class="tab-content">

                                <div class="tab-pane active" id="new1">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的申请</a>
                                            </li>
                                            <li>
                                                <a href="#">新建申请</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="填写新表单" class="well top-block"
                                               href="javascript:void(0)" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>

                                                <div>资金申请</div>

                                            </a>
                                        </div>

                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 待办列表</h2>
                                        </div>


                                        <div class="box-content">
                                            <table id="money_apply1" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>编号</th>
                                                        <th>创建时间</th>
                                                        <th>上报人</th>
                                                        <th>上报季度</th>
                                                        <th>当前状态</th>
                                                        <th>操作</th>
                                                    </tr>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="progress1">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的申请</a>
                                            </li>
                                            <li>
                                                <a href="#">新建申请</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="填写新表单" class="well top-block"
                                               href="javascript:void(0)" onclick="applyForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>

                                                <div>资金申请</div>

                                            </a>
                                        </div>

                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 在办列表</h2>
                                        </div>


                                        <div class="box-content">
                                            <table id="quxian1" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>时间</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="new2">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的事务</a>
                                            </li>
                                            <li>
                                                <a href="#">已到资金</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已到资金</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="guihua2" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>到账时间</th>
                                                    <th>项目名称</th>
                                                    <th>金额数量</th>
                                                    <th>申报人</th>
                                                    <th>申报时间</th>
                                                </tr>

                                                </thead>
                                            </table>
                                        </div>

                                        <p style="color: red;">下面内容为点击某条列表，出现的详细信息</p>
                                        <div class="money-info">
                                            <div class="m-container">
                                                <div class="m-row">
                                                    <p><span class="key">上报人：</span><span class="s-name">xxx</span></p>
                                                    <p><span class="key">上报时间：</span><span class="s-time">xxx</span></p>
                                                </div>
                                                <div class="m-row">
                                                    <p><span class="key">上报内容：</span><span class="s-con">xxx</span></p>
                                                    <p><span class="key">上报文件：</span><span class="s-fil">xxx</span></p>
                                                </div>
                                                <div class="m-row">
                                                    <p><span class="key">款项来源：</span><span class="d-name">xxx</span></p>
                                                    <p><span class="key">到款时间：</span><span class="d-time">xxx</span></p>
                                                </div>
                                                <div class="m-row">
                                                    <p><span class="key">到款金额：</span><span class="d-con">xxx</span></p>
                                                    <p><span class="key">附件：</span><span class="d-fil">xxx</span></p>
                                                </div>
                                            </div>
                                            <button type="button" class="btn btn-primary dropdown-toggle mybtn">提交，通知规划科</button>
                                        </div>
                                    </div>

                                </div>

                                <div class="tab-pane" id="progress2">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的事务</a>
                                            </li>
                                            <li>
                                                <a href="#">在办事务</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 在办列表</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="quxian2" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>上报人</th>
                                                        <th>上报时间</th>
                                                        <th>到账金额</th>
                                                        <th>到账时间</th>
                                                        <th>操作</th>
                                                    </tr>
                                                </thead>
                                            </table>
                                        </div>

                                        <div class="info-detail">
                                            <p style="color: red">下面内容为点击列表时展示内容</p>
                                            <div class="infos">
                                                <div class="apply">资金申请信息</div>
                                                <div class="money">到款信息</div>
                                            </div>
                                            <div class="notice">
                                                <div class="add">
                                                    <span>通知区县</span>
                                                    <ul class="ui-choose" multiple="multiple" id="uc_03">
                                                        <li>临翔区</li>
                                                        <li>凤庆县</li>
                                                        <li>永德县</li>
                                                        <li>镇康县</li>
                                                        <li>云县</li>
                                                        <li>沧源佤族自治县</li>
                                                        <li>耿马傣族佤族自治县</li>
                                                        <li>双江拉祜族佤族布朗族傣族自治县</li>
                                                    </ul>
                                                </div>
                                                <div class="text">
                                                    <span>通知内容</span><textarea name="" id="" cols="30"
                                                                               rows="10"></textarea>
                                                </div>
                                            </div>
                                            <button type="button" class="btn btn-primary dropdown-toggle mybtn">确认通知区县</button>
                                        </div>
                                    </div>


                                </div>
                                <div class="tab-pane" id="completed2">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的事务</a>
                                            </li>
                                            <li>
                                                <a href="#">已归档事务</a>
                                            </li>
                                        </ul>
                                    </div>


                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已归档事务</h2>

                                        </div>
                                        <div class="box-content">
                                            <%--<table id="caiwu2" class="display" width="100%" cellspacing="0">--%>
                                                <%--<thead>--%>
                                                <%--<tr>--%>
                                                    <%--<th>编号</th>--%>
                                                    <%--<th>发件人</th>--%>
                                                    <%--<th>时间</th>--%>
                                                    <%--<th>操作</th>--%>
                                                <%--</tr>--%>
                                                <%--</thead>--%>
                                            <%--</table>--%>
                                            <div class="x-know">
                                                <p><span class="key">发件人：</span><span class="name">xxx</span></p>
                                                <p><span class="key">通知内容: </span><span class="content">临沧为云南省地级市，地处云南省西南部，东邻普洱市，北连大理州，西接保山市，西南与缅甸交界，地处澜沧江与怒江之间，因濒临澜沧江而得名。市政府驻地距省会昆明598千米，是昆明通往缅甸仰光的陆上捷径，有3个国家口岸和17条通道。</span></p>
                                                <button type="button" class="btn btn-primary dropdown-toggle mybtn">已知晓</button>
                                            </div>
                                        </div>


                                    </div>



                                </div>
                                <div class="tab-pane" id="completed4">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的事务</a>
                                            </li>
                                            <li>
                                                <a href="#">已归档事务</a>
                                            </li>
                                        </ul>
                                    </div>


                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已归档事务</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="quxian3" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>转账人员</th>
                                                    <th>转账金额</th>
                                                    <th>时间</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                            <div class="c-off">
                                                <div class="info">
                                                    <div class="app">申请信息</div>
                                                    <div class="off">批复信息</div>
                                                </div>
                                                <p><span class="key">通知内容：</span><input type="text"></p>
                                                <p><span class="key">转款日期：</span><input type="text" id="myDate"></p>
                                                <p class="t-money"><span class="key">金额：</span><input type="text"></p>
                                                <button type="button" class="btn btn-primary dropdown-toggle mybtn">已处理</button>
                                            </div>
                                        </div>


                                    </div>



                                </div>
                                <div class="tab-pane" id="completed5">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的事务</a>
                                            </li>
                                            <li>
                                                <a href="#">已归档事务</a>
                                            </li>
                                        </ul>
                                    </div>


                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已归档事务</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="quxian4" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>发件人</th>
                                                    <th>时间</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                            <div class="y-money">
                                                <p><span class="key">发件人：</span><span class="name">财务科</span></p>
                                                <p><span class="key">收款内容：</span><span class="con">临沧为云南省地级市，地处云南省西南部，东邻普洱市，北连大理州，西接保山市，西南与缅甸交界，地处澜沧江与怒江之间，因濒临澜沧江而得名。市政府驻地距省会昆明598千米，是昆明通往缅甸仰光的陆上捷径，有3个国家口岸和17条通道。</span></p>
                                                <button type="button" class="btn btn-primary dropdown-toggle mybtn">确认</button>
                                            </div>
                                        </div>


                                    </div>



                                </div>
                                <div class="tab-pane" id="completed6">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的事务</a>
                                            </li>
                                            <li>
                                                <a href="#">已归档事务</a>
                                            </li>
                                        </ul>
                                    </div>


                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已归档事务</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="money" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>去向</th>
                                                    <th>金额</th>
                                                    <th>发放人</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>


                                    </div>



                                </div>
                                <div class="tab-pane" id="new3">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的审批</a>
                                            </li>
                                            <li>
                                                <a href="#">待审批表单</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 待审批表单</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="NewTable_Approval" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>接收时间</th>
                                                    <th>科室</th>
                                                    <th>报稿</th>
                                                    <th>标题</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                        <p style="color: red">下面为点击某个列表展示的内容</p>
                                        <div class="official">
                                            <p><span class="key">申请人：</span><span class="name">xxx</span></p>
                                            <p><span class="key">申请原因：</span><span class="reason">xxx</span></p>
                                            <div class="about">
                                                <span>批复意见：</span><textarea name="" cols="30"
                                                                            rows="10"></textarea>
                                            </div>
                                            <button type="button" id="yes" class="btn btn-success dropdown-toggle mybtn">同意</button>
                                            <button type="button" id="no" class="btn btn-danger dropdown-toggle mybtn">不同意</button>
                                        </div>

                                    </div>



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

    <div class="modal fade" id="money_apply_wdo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" id="close_stuff" data-dismiss="modal">×</button>
                    <h3>填写表单</h3>
                </div>
                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm" action="" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">

                    <div class="modal-body">
                        <input id="oId" type="text" name="id" style="display: none" ></input>
                        <input id="created_at" type="text" name="created_at" style="display: none" ></input>
                        <div class="row myrow">
                            <div class="col-sm-6">
                                <span>上报人</span>
                                <input id="input1" type="text" name="dept">
                            </div>
                            <div class="col-sm-6">
                                <span>上报季度</span>
                                <input id="input2" type="text" name="author">
                            </div>
                        </div>
                        <div class="row myrow">
                            <div class="col-sm-12">
                                <span>上传文件</span>
                                <div id="filesUpload" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" id="add_1" onclick="add_click_file(1)">添加附件</a>
                                    <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                </div>
                                <input type="button" id="Commit" style="display:none"/>
                            </div>
                        </div>
                        <textarea class="mytext" name="content" id="input3" cols="30" rows="10" placeholder="上报内容描述"></textarea>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>


            </div>
        </div>
    </div>

    <div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>详细信息</h3>
                </div>
                <iframe name="uploadFrame" style="display:none;"></iframe>
                <form action="" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">

                    <div class="modal-body">
                        <p>申请进度: <span>财务正在处理</span></p>
                        <div class="row myrow">
                            <div class="col-sm-6">
                                <span>上报人</span>
                                <input type="text" name="dept">
                            </div>
                            <div class="col-sm-6">
                                <span>上报时间</span>
                                <input type="text" name="author">
                            </div>
                        </div>
                        <div class="row myrow">
                            <div class="col-sm-12">
                                <span>文件</span>
                                <div style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" onclick="add_click_file(1)">附件</a>
                                    <input style="display:none;" type="file" name = "files" onChange="add(1)"/>
                                </div>
                                <input type="button" style="display:none"/>
                            </div>
                        </div>
                        <div class="row myrow"  style="border: 1px solid red;height: 50px;">
                            <div class="col-sm-12"></div>
                        </div>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                </div>


            </div>
        </div>
    </div>

    <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>详细信息</h3>
                </div>
                <iframe name="uploadFrame" style="display:none;"></iframe>
                <form action="" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">

                    <div class="modal-body">
                        <p>申请进度: <span>财务正在处理</span></p>
                        <div class="row myrow">
                            <div class="col-sm-6">
                                <span>上报人</span>
                                <input type="text" name="dept" readonly="true">
                            </div>
                            <div class="col-sm-6">
                                <span>上报时间</span>
                                <input type="text" name="author" readonly="true">
                            </div>
                        </div>
                        <div class="row myrow">
                            <div class="col-sm-12">
                                <span>文件</span>
                                <div style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" onclick="add_click_file(1)">下载</a>
                                    <input style="display:none;" type="file" name = "files" onChange="add(1)"/>
                                </div>
                                <input type="button" style="display:none"/>
                            </div>
                        </div>
                        <div class="row myrow"  style="height: 50px;border-left:1px solid red;border-right:1px solid red;">
                            <div class="col-sm-12"></div>
                        </div>
                        <div class="row myrow" id="caiwu1">
                            <div class="col-sm-6">
                                <span>款项来源</span>
                                <input type="text" name="dept">
                            </div>
                            <div class="col-sm-6">
                                <span>到款时间</span>
                                <input type="text" name="author">
                            </div>
                        </div>
                        <div class="row myrow last" id="caiwu2">
                            <div class="col-sm-6">
                                <span>到款金额</span>
                                <input type="text" name="dept">
                            </div>
                            <div class="col-sm-6">
                                <span>上传附件</span>
                                <input type="text" name="author">
                            </div>
                        </div>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="form_office" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>填写表单</h3>
                </div>
                <div class="modal-body">
                    <div class="row myrow">
                        <div class="col-lg-3">
                            <span>临移（）</span>
                            <input id="input_office1" type="text">

                        </div>
                        <div class="col-lg-3">
                            <span>日期</span>
                            <input id="input_office2" type="text">
                        </div>
                        <div class="col-lg-3">
                            <span>缓级</span>
                            <input id="input_office3" type="text">
                        </div>
                        <div class="col-lg-3">
                            <span>密级</span>
                            <input id="input_office4" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-3">
                            <span>签发</span>
                            <input id="input_office5" type="text">

                        </div>
                        <div class="col-lg-3">
                            <span>审稿</span>
                            <input id="input_office6" type="text">
                        </div>
                        <div class="col-lg-6">
                            <span>会签</span>
                            <input id="input_office7" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-12">
                            <span>抄报：</span>
                            <input id="input_office8" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-12">
                            <span>抄送：</span>
                            <input id="input_office9" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-12">
                            <span>发：</span>
                            <input id="input_office10" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-5">
                            <span>拟稿单位</span>
                            <input id="input_office11" type="text">
                        </div>
                        <div class="col-lg-3">
                            <span>拟稿</span>
                            <input id="input_office12" type="text">
                        </div>
                        <div class="col-lg-4">
                            <span>科室核稿</span>
                            <input id="input_office13" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-5">
                            <span>印刷</span>
                            <input id="input_office14" type="text">
                        </div>
                        <div class="col-lg-3">
                            <span>校对</span>
                            <input id="input_office15" type="text">
                        </div>
                        <div class="col-lg-4">
                            <span>份数</span>
                            <input id="input_office16" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-12">
                            <span>附件</span>
                            <input id="input_office17" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-lg-12">
                            <span>主题词</span>
                            <input id="input_office18" type="text">
                        </div>
                    </div>
                    <div class="row myrow last">
                        <div class="col-lg-12">
                            <span>标题</span>
                            <input id="input_office19" type="text">
                        </div>
                    </div>
                    <textarea class="mytext" name="" id="input_office20" cols="30" rows="10" placeholder="内容"></textarea>



                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-success" data-dismiss="modal">保存</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">提交</a>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="flow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>公文流转</h3>
                </div>
                <div class="modal-body">

                    <div id="wrap">
                        <div>
                            <img src="../../img/员工.png" class="head head_pic1" alt="员工">
                            <p class="staff status1" >申请人</p>
                            <div class="details details1">
                                <p>姓名：<span>小吴</span></p>
                                <p>提交时间：<span>2017-06-18 16：30</span></p>
                            </div>
                        </div>

                        <div class="hr hr1"></div>

                        <div>
                            <img src="../../img/中层.png" class="head head_pic2" alt="中层">
                            <p class="staff status2">办公室</p>
                            <div class="details details2">
                                <p>审核状态：<span>通过</span></p>
                                <p>审核人：<span>小明</span></p>
                                <p>审核时间：<span>2017-06-18 16：30</span></p>
                            </div>
                        </div>

                        <div class="hr hr2"></div>

                        <div>
                            <img src="../../img/高层.png" class="head head_pic3" alt="高层">
                            <p class="staff status3">审批领导</p>
                            <div class="details details3">
                                <p>审核状态：<span>未审核</span></p>
                                <p>审批人：<span>小秋</span></p>
                                <p>审核时间：<span>2017-06-18 16：30</span></p>
                            </div>
                        </div>

                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

<footer class="row">
    <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; 临沧市移民局</p>

    <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
            href="http://www.bhidi.com">北京院</a></p>
</footer>

</div><!--/.fluid-container-->



<script>
    $('.ui-choose').ui_choose();
    var uc_03 = $('#uc_03').data('ui-choose');

    $("#myDate").jeDate({
        format: "YYYY-MM-DD"
    });
    var status;
    ~function() {

        status = $("#status").text();

        if(status == 2){
            $("#new1>.row").css("display","none");

        }else if(status == 3){
            $("#m_apply1").css("display","none");

        }

    }();
    // #资金申请查看按钮
    function detail(that) {
        $("#detail").modal('show');
        var id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
        console.log(id);
        $.ajax({
            url: "/getCatipalDataById.do",
            type: "post",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                console.log(data);
            }
        })
        /*if(status == 1){

        }*/
    }
    function edit(that) {
        $("#edit").modal('show');
        var state = $(that).parent("td").parent("tr").children("td:nth-child(5)").text();
        console.log(state);
//        if(status == 1){
//
//        }
//        $.ajax({
//            url: '/queryStuffById.do',
//            type: 'post',
//            data: "id="+oid,
//            dataType: 'json',
//            async: false,
//            contentType: "application/x-www-form-urlencoded; charset=utf-8",
//            success: function (data) {
//                $("#input1").val(data.dept);
//                $("#input2").val(data.author);
//                $("#input3").val(data.reviewer);
//                $("#input4").val(data.print);
//                $("#input5").val(data.revision);
//                $("#input6").val(data.copy);
//                $("#input8").val(data.keyword);
//                $("#input9").val(data.title);
//                $("#input10").val(data.content);
//                $("#oId").text(data.id);
//                $("#created_at").text(data.created_at);
//                $('#form_stuff').modal('show');
//            }
//        });
    }

    function newForm() {
        $('#money_apply_wdo').modal('show');
    }

    function applyForm() {
        $('#form_apply').modal('show');
    }






    function detail_office(that) {
        $('#form_office').modal('show');
    }

    function flow(that){
        $('#flow').modal('show');
    }


</script>



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
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="../../js/money.js"></script>

</body>
</html>