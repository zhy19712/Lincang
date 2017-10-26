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
    <link href="css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <link href="css/chosen.min.css" rel="stylesheet">
    <link href="css/jquery.iphone.toggle.css" rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="css/mycss.css">
    <link rel="stylesheet" href="css/oa.css">
    <link rel="stylesheet" href="css/ui-choose.css">
    <link rel="stylesheet" href="css/jquery.step.css">
    <link rel="stylesheet" href="css/jedate.css">
    <link rel="stylesheet" href="js/themes/default/style.min.css">
    <link rel="stylesheet" href="css/money.css">
    <style>
        .last{
            border-bottom: 1px solid #000 !important;
        }
        #new3 tbody tr td:last-child input:last-child{
            display: none;
        }
        #user_container1,#user_container2{
            font-size: 0;
        }
        #user_container1>div,#user_container2>div{
            display: inline-block;
            vertical-align: middle;
            word-wrap: break-word;
            text-align: center;
            font-size: 20px;
            width: 33%;
        }
        #user_container2>div{
            width: 20%;
        }
        #shouwen_wdo form,#more{
            width: 96%;
            margin: 0 auto;
        }
        .title{
            text-align: center;
            font-weight: 700;
            margin: 10px 0;
        }
        #shouwen_wdo table,#more table{
            width: 100%;
            border-top: 1px solid #000;
            border-left: 1px solid #000;
        }
        #shouwen_wdo table td,#more table td{
            padding: 5px 0;
            text-align: center;
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
        }
        #shouwen_wdo table input,#more table input{
            width: 100%;
            outline: none;
            border: none;
            padding: 0 5px;
            text-align: center;
        }
        #shouwen_wdo table select,#more table select{
            width: 94%;
            margin-left: 3%;
        }
        textarea{
            outline:none;
            border: none;
            resize: none;
        }
        #sel_model{
            width: 96%;
            margin: 0 auto;
            font-size: 0;
            /*position: relative;*/
        }
        #sel_model>div{
            display: inline-block;
            vertical-align: top;
            width: 50%;
            font-size: 14px;
        }
        #select_people{
            padding-left: 0;
            line-height: 20px;
        }
        #select_people li{
            list-style: none;
            margin-top: 5px;
        }
        #select_people li:nth-child(2),#select_people li:nth-child(3){
            display: none;
        }
        #select_people li span{
            display: inline-block;
            vertical-align: middle;
            width: 150px;
        }
        #model_container{
            width: 96%;
            margin: 0 auto;
            position: relative;
            height: 500px;
        }
        #model_container>div{
            position: absolute;
            top: 0;
            left: 0;
        }
    </style>


    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-form.min.js"></script>
    <script src="js/ui-choose.js"></script>
    <script src="js/jquery.jedate.js"></script>
    <script src="js/jquery.step.js"></script>
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
        <span id="username" style="display:none;width:0;height:0;">${user.username}</span>
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
                        <li id="header1" class="nav-header">我的收文</li>
                        <li id="m_apply1"><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span id="kind1">收文登记</span></a></li>

                        <li class="nav-header">我的待办</li>
                        <li id="dcl"><a href="#new2"><i class="glyphicon glyphicon-tags"></i><span> 待处理事务</span></a></li>
                        <li id="ycl"><a href="#new3"><i class="glyphicon glyphicon-refresh"></i><span> 已处理事务</span></a></li>
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
                                                <a href="#">我的收文</a>
                                            </li>
                                            <li>
                                                <a href="#">新建登记</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="新建收文登记表" class="well top-block"
                                               href="#" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>
                                                <div id="kind2">新建收文登记表</div>
                                            </a>
                                        </div>

                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 全部列表</h2>
                                        </div>


                                        <div class="box-content">
                                            <table id="money_apply1" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>年度</th>
                                                    <th>类别</th>
                                                    <th>来文日期</th>
                                                    <th>文件编号</th>
                                                    <th>标题</th>
                                                    <th>当前状态</th>
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
                                                <a href="#">待办事务</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已到资金</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="dcl_table" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>发起人</th>
                                                    <th>发起人类型</th>
                                                    <th>当前状态</th>
                                                    <th>操作</th>
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
                                                <a href="#">我的事务</a>
                                            </li>
                                            <li>
                                                <a href="#">已处理事务</a>
                                            </li>
                                        </ul>
                                    </div>


                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已处理事务</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="ycl_table" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>发起人</th>
                                                    <th>发起人类型</th>
                                                    <th>当前状态</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                            </table>
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

    <div class="modal fade" id="shouwen_wdo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" id="close_stuff" data-dismiss="modal">×</button>
                    <h3>收文登记</h3>
                </div>
                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm" action="/reveiceFileRegistration.do" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">

                    <div class="modal-body">
                        <p class="title">收文登记</p>
                        <input id="user1" type="text" name="user" value="" style="display: none;">
                        <table>
                            <tbody>
                            <tr>
                                <td>年度</td>
                                <td><input type="text" name="year"></td>
                                <td>保管期限</td>
                                <td><select name="savetime">
                                    <option value="1个月">1个月</option>
                                    <option value="6个月">6个月</option>
                                    <option value="1年">1年</option>
                                    <option value="2年">2年</option>
                                </select></td>
                                <td>类别</td>
                                <td colspan="2"><input type="text" name="type"></td>
                                <td>来文日期</td>
                                <td colspan="2"><input type="text" id="time1" readonly="readonly" name="cometime"></td>
                            </tr>
                            <tr>
                                <td>文件编号</td>
                                <td><input type="text" name="fileid"></td>
                                <td>登记号</td>
                                <td><input type="text" name="registrationnum"></td>
                                <td>全宗号</td>
                                <td colspan="2"><input type="text" name="fileallid"></td>
                                <td>成文日期</td>
                                <td colspan="2"><input type="text" id="time2" readonly="readonly" name="writtentime"></td>
                            </tr>
                            <tr>
                                <td style="vertical-align: middle;">题名</td>
                                <td colspan="9">
                                    <textarea name="title" cols="30" rows="10" style="width: 99%;outline: none;height: 80px;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>上传附件</td>
                                <td colspan="9"><div id="filesUpload" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" id="add_1" onclick="add_click_file(1)">添加附件</a>
                                    <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                </div></td>
                            </tr>
                            <tr>
                                <td>主题词</td>
                                <td colspan="5"><input type="text" name="keyword"></td>
                                <td>责任者</td>
                                <td colspan="3"><input type="text" name="responsibleperson"></td>
                            </tr>
                            <tr>
                                <td>归档份数</td>
                                <td><input type="text" name="archivecopies"></td>
                                <td>页数</td>
                                <td><input type="text" name="pagenum"></td>
                                <td>密级</td>
                                <td><input type="text" name="secret"></td>
                                <td>机构问题</td>
                                <td><input type="text" name="issues"></td>
                                <td>收件人</td>
                                <td><input type="text" name="receiveperson"></td>
                            </tr>
                            <tr>
                                <td>来文机关</td>
                                <td colspan="5"><input type="text" name="comedepartment"></td>
                                <td>附件页数</td>
                                <td colspan="3"><input type="text" name="attachmentpagenum"></td>
                            </tr>
                            <tr>
                                <td>实体分类号</td>
                                <td><input type="text" name="entitynum"></td>
                                <td>分发情况</td>
                                <td colspan="3"><input type="text" name="distributionsituation"></td>
                                <td>旧全宗</td>
                                <td colspan="3"><input type="text" name="oldfond"></td>
                            </tr>
                            <tr>
                                <td>归档情况</td>
                                <td><input type="text" name="archivesituation"></td>
                                <td>登记日期</td>
                                <td colspan="3"><input type="text" id="time3" readonly="readonly" name="registrationdate"></td>
                                <td>传阅情况</td>
                                <td colspan="3"><input type="text" name="circulationsituation"></td>
                            </tr>
                            <tr>
                                <td style="vertical-align: middle;">处理情况</td>
                                <td colspan="9"><textarea name="dealsituation" id="" cols="30" rows="10" style="width: 99%;"></textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>


            </div>
        </div>
    </div>

    <div class="modal fade" id="select_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>选择模版及处理人</h3>
                </div>
                <div id="container" style="width: 100%;height: 160px">
                    <div class="step-body" id="myStep" style="width:80%;margin: 0 auto;">
                        <div class="step-header">
                            <ul>
                                <li><p>规划科已上报</p></li>
                                <li><p>财务处理中</p></li>
                                <li><p>规划科已通知区县</p></li>
                            </ul>
                        </div>
                    </div>
                    <div id="user_container1" style="width:80%;margin: 0 auto;margin-top: 85px;">
                        <div class="user1_1"></div>
                        <div class="user1_2"></div>
                        <div class="user1_3"></div>
                    </div>
                </div>
                <div id="more" style="height: 30px;overflow: hidden;transition: all 0.5s;">
                    <p id="first" style="margin-left: 24px;font-size: 16px;cursor: pointer;line-height: 30px;">查看收文登记详细信息</p>
                    <table>
                        <tbody>
                        <tr>
                            <td>年度</td>
                            <td><input type="text" name="niandu"></td>
                            <td>保管期限</td>
                            <td>1个月</td>
                            <td>类别</td>
                            <td colspan="2"><input type="text" name="leibie"></td>
                            <td>来文日期</td>
                            <td colspan="2"><input type="text" name="laiwenriqi"></td>
                        </tr>
                        <tr>
                            <td>文件编号</td>
                            <td><input type="text" name="wenjianhao"></td>
                            <td>登记号</td>
                            <td><input type="text" name="dengjihao"></td>
                            <td>全宗号</td>
                            <td colspan="2"><input type="text" name="quanzonghao"></td>
                            <td>成文日期</td>
                            <td colspan="2"><input type="text" name="chengwenriqi"></td>
                        </tr>
                        <tr>
                            <td style="vertical-align: middle;">题名</td>
                            <td colspan="9">
                                <textarea name="timing" cols="30" rows="10" style="width: 99%;outline: none;height: 80px;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>下载附件</td>
                            <td colspan="9"><div id="filesUpload_1" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                <a href="#" id="add_1_1" onclick="add_click_file(1)">下载附件</a>
                                <input style="display:none;" id="add_file_1_1" type="file" name = "files" onChange="add(1)"/>
                            </div></td>
                        </tr>
                        <tr>
                            <td>主题词</td>
                            <td colspan="5"><input type="text" name="zhuti"></td>
                            <td>责任者</td>
                            <td colspan="3"><input type="text" name="zeren"></td>
                        </tr>
                        <tr>
                            <td>归档份数</td>
                            <td><input type="text" name="guidangyeshu"></td>
                            <td>页数</td>
                            <td><input type="text" name="yeshu"></td>
                            <td>密级</td>
                            <td><input type="text" name="miji"></td>
                            <td>机构问题</td>
                            <td><input type="text" name="jigou"></td>
                            <td>收件人</td>
                            <td><input type="text" name="shoujianren"></td>
                        </tr>
                        <tr>
                            <td>来文机关</td>
                            <td colspan="5"><input type="text" name="laiwenjiguan"></td>
                            <td>附件页数</td>
                            <td colspan="3"><input type="text" name="fujianyeshu"></td>
                        </tr>
                        <tr>
                            <td>实体分类号</td>
                            <td><input type="text" name="shitihao"></td>
                            <td>分发情况</td>
                            <td colspan="3"><input type="text" name="fenfa"></td>
                            <td>旧全宗</td>
                            <td colspan="3"><input type="text" name="jiuquanzong"></td>
                        </tr>
                        <tr>
                            <td>归档情况</td>
                            <td><input type="text" name="guidangqingkuang"></td>
                            <td>登记日期</td>
                            <td colspan="3"><input type="text" name="dengjiriqi"></td>
                            <td>传阅情况</td>
                            <td colspan="3"><input type="text" name="chuanyue"></td>
                        </tr>
                        <tr>
                            <td style="vertical-align: middle;">处理情况</td>
                            <td colspan="9"><textarea name="chuli" cols="30" rows="10" style="width: 99%;"></textarea></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div id="sel_model">
                    <div id="model">
                        <p>选择模版</p>
                        <select name="" id="sel1">
                            <option>直接处理</option>
                            <option>文件拟办单</option>
                            <option>一科室提意见</option>
                            <option>两科室提意见</option>
                        </select>
                        <ul id="select_people">
                            <li><span>选择办公室处理人</span><input type="text" id="bangongshi"></li>
                            <li><span>选择科室1处理人</span><input type="text" id="keshi1"></li>
                            <li><span>选择科室2处理人</span><input type="text" id="keshi2"></li>
                            <li><span>选择分管领导处理人</span><input type="text" id="fenguan"></li>
                            <li><span>选择主管领导处理人</span><input type="text" id="zhuguan"></li>
                            <li><span>选择办理人</span><input type="text" id="banli"></li>
                            <li><span>选择归档人</span><input type="text" readonly="readonly" value="办公室1,办公室2" id="guidang"></li>
                        </ul>
                    </div>
                    <div id="sel_people">
                        <p>选择办公室处理人</p>
                        <div id="tree_container">
                            <ul>
                                <li data-jstree='{"opened":true}'>办公室
                                    <ul>
                                        <li>办公室1</li>
                                        <li>办公室2</li>
                                    </ul>
                                </li>
                            </ul>
                            <ul>
                                <li data-jstree='{"opened":true}'>科室1
                                    <ul>
                                        <li>科室1_1</li>
                                        <li>科室1_2</li>
                                    </ul>
                                </li>
                            </ul>
                            <ul>
                                <li data-jstree='{"opened":true}'>科室2
                                    <ul>
                                        <li>科室2_1</li>
                                        <li>科室2_2</li>
                                    </ul>
                                </li>
                            </ul>
                            <ul>
                                <li data-jstree='{"opened":true}'>分管领导
                                    <ul>
                                        <li>分管领导1</li>
                                        <li>分管领导2</li>
                                    </ul>
                                </li>
                            </ul>
                            <ul>
                                <li data-jstree='{"opened":true}'>主管领导
                                    <ul>
                                        <li>主管领导1</li>
                                        <li>主管领导2</li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <button>确认</button>
                    </div>
                </div>
                <div id="model_container">
                    <%--<div id="model1">--%>
                        <%--<p class="title"><input type="text" value="临沧市移民局文件处理笺"></p>--%>
                        <%--<table>--%>
                            <%--<tbody>--%>
                            <%--<tr>--%>
                                <%--<td>收文号</td>--%>
                                <%--<td><input type="text"></td>--%>
                                <%--<td>来文机关</td>--%>
                                <%--<td><input type="text"></td>--%>
                                <%--<td>来文号</td>--%>
                                <%--<td><input type="text"></td>--%>
                                <%--<td>缓急</td>--%>
                                <%--<td><input type="text"></td>--%>
                                <%--<td>密级</td>--%>
                                <%--<td><input type="text"></td>--%>
                                <%--<td>份数</td>--%>
                                <%--<td><input type="text"></td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td>文件标题</td>--%>
                                <%--<td colspan="11"><input type="text"></td>--%>
                            <%--</tr>--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td rowspan="4" colspan="1">拟办意见</td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="11" class="botttom_none"><p style="height: 20px;line-height: 20px;">呈：<input type="text" style="width:70px;line-height: 20px;font-size:16px;text-align: center;">阅知</p></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="11" class="botttom_none"><input type="text">阅知</td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="11" class="botttom_none"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="11">局办公室<br>xxx年xx月xx日</td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--<tr>--%>
                                <%--<td class="middle">拟办意见</td>--%>
                                <%--<td colspan="11">--%>
                                    <%--<textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td class="middle">主要领导批示</td>--%>
                                <%--<td colspan="11">--%>
                                    <%--<textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td class="middle">办理结果</td>--%>
                                <%--<td colspan="11">--%>
                                    <%--<textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td class="middle">分管领导批示</td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="11">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td rowspan="3" class="middle">办理结果</td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="5" rowspan="3"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<td colspan="2"><input type="text"></td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                            <%--</tbody>--%>
                        <%--</table>--%>
                    <%--</div>--%>
                    <%--<div id="model2"></div>--%>
                    <%--<div id="model3"></div>--%>
                    <%--<div id="model4"></div>--%>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="edit2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>详细信息</h3>
                </div>
                <div id="container2" style="width: 100%;height: 160px">
                    <div class="step-body" id="myStep2" style="width:80%;margin: 0 auto;">
                        <div class="step-header">
                            <ul>
                                <li><p>区县已申请</p></li>
                                <li><p>市局规划科批复中</p></li>
                                <li><p>市局财务科转账中</p></li>
                                <li><p>区县资金流向录入</p></li>
                                <li><p>区县资金流向明细</p></li>
                            </ul>
                        </div>
                    </div>
                    <div id="user_container2" style="width:80%;margin: 0 auto;margin-top: 85px;">
                        <div class="user2_1"></div>
                        <div class="user2_2"></div>
                        <div class="user2_3"></div>
                        <div class="user2_4"></div>
                        <div class="user2_5"></div>
                    </div>
                </div>
                <iframe name="uploadFrame" style="display:none;"></iframe>
                <form action="" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">


                    <div class="modal-body">
                        <div class="row myrow">
                            <div class="col-sm-12">
                                <span>标题</span>
                                <input type="text" id="apply_title_edit" readonly="true">
                            </div>
                        </div>
                        <div class="row myrow">
                            <div class="col-sm-6">
                                <span>申请人</span>
                                <input type="text" id="apply_person_edit" readonly="true">
                            </div>
                            <div class="col-sm-6">
                                <span>申请原因</span>
                                <input type="text" id="apply_reason_edit" readonly="true">
                            </div>
                        </div>
                        <div class="row myrow" id="apply1">
                            <div class="col-sm-12">
                                <span>文件</span>
                                <div style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" onclick="add_click_file(1)">下载</a>
                                    <input style="display:none;" type="file" name = "files" onChange="add(1)"/>
                                </div>
                                <input type="button" style="display:none"/>
                            </div>
                        </div>
                        <div class="row myrow" id="pifu">
                            <div class="col-sm-12" style="padding-top: 10px">
                                <span style="display: inline-block;vertical-align: top;border: none;">批复意见:</span><textarea name="" id="pifu_content" cols="30" rows="10" style="width:90%;outline: none;border: 1px solid #000"></textarea>
                            </div>
                        </div>
                        <div class="row myrow" id="chuli">
                            <div class="col-sm-12" style="padding-top: 10px">
                                <span style="display: inline-block;vertical-align: top;border: none;">处理内容:</span><textarea name="" id="chuli_content" cols="30" rows="10" style="width:90%;outline: none;border: 1px solid #000"></textarea>
                            </div>
                        </div>
                        <div class="row myrow" id="luru">
                            <div class="col-sm-12" style="padding-top: 10px">
                                <span style="display: inline-block;vertical-align: top;border: none;">资金流向:</span><textarea name="" id="luru_content" cols="30" rows="10" style="width:90%;outline: none;border: 1px solid #000"></textarea>
                            </div>
                        </div>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-success">保存</a>
                    <a href="#" class="btn btn-primary">提交</a>
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
<script src="js/jstree.js"></script>
<script src="js/money.js"></script>
<script>

    //日期插件
    $("#time1").jeDate({
        format: "YYYY-MM-DD"
    });
    $("#time2").jeDate({
        format: "YYYY-MM-DD"
    });
    $("#time3").jeDate({
        format: "YYYY-MM-DD"
    });

    //树形插件
    $('#tree_container').jstree({
        "plugins" : ["checkbox"]
    });
    // 多文件上传
    var fileIndex = 1;
    function add_click_file(index){
        $("#add_file_"+fileIndex).click();
    }

//    $("#tree_container>ul>li").attr({"aria-expanded":false,"class":"jstree-node jstree-closed"});


    function add(index) {
        /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
        var len = $("#add_file_" + (fileIndex) + "").val().split("\\").length;
        var num = $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1];
        $("#filesUpload").append('<span  id="add_file_span_' + (fileIndex) + '"  class="add_file">' + $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1] + '</span>');
        $("#filesUpload").append('<a   id="add_file_a_' + (fileIndex) + '"  class="add_file" href="javascript:del_file(' + fileIndex+ ')">删除</a>');
        $("#filesUpload").append('<input style="display:none;" id="add_file_' + (fileIndex + 1) + '" type="file" name = "files" onChange="add(' + (fileIndex + 1) + ')"/>');
        ++fileIndex;
    }

    function del_file(number) {
        var o=document.getElementById("filesUpload");//获取父节点
        var int=document.getElementById("add_file_" + number+"");//获取需要删除的子节点
        var a=document.getElementById("add_file_a_" + number+"");//获取需要删除的子节点
        var span=document.getElementById("add_file_span_" + number+"");//获取需要删除的子节点
        o.removeChild(int); //从父节点o上面移除子节点a
        o.removeChild(a);
        o.removeChild(span)
    }
    //收文登记
    $("#shouwen_wdo .btn-primary").click(function () {
        alert(123)
        $("#fileForm").submit();
    })

    //选择模版
    $("#sel1").change(function () {
        if($(this).val() == "一科室提意见"){
            $("#select_people li:nth-child(2)").css("display","block");
            $("#select_people li:nth-child(3)").css("display","none");
        }else if($(this).val() == "两科室提意见"){
            $("#select_people li:nth-child(2)").css("display","block");
            $("#select_people li:nth-child(3)").css("display","block");
        }else {
            $("#select_people li:nth-child(2)").css("display","none");
            $("#select_people li:nth-child(3)").css("display","none");
        }
    });
    //选择处理人
    $("#select_people li input").focus(function () {
        var text = $(this).siblings("span").text();
        $("#sel_people>p").text(text);
    })

    //处理人集合
    var people_arr = [];
    $("#sel_people button").click(function () {
        people_arr = []
        $.each($("#tree_container .jstree-leaf"),function (i,n) {
            if($(n).attr("aria-selected") == "true"){
                people_arr.push($(n).text())
            }
        })
        var people = people_arr.toString();
        var peole_kind = $("#sel_people>p").text();
        if(peole_kind == "选择办公室处理人"){
            $("#bangongshi").val(people);
        }else if(peole_kind == "选择科室1处理人"){
            $("#keshi1").val(people);
        }else if(peole_kind == "选择科室2处理人"){
            $("#keshi2").val(people);
        }else if(peole_kind == "选择分管领导处理人"){
            $("#fenguan").val(people);
        }else if(peole_kind == "选择主管领导处理人"){
            $("#zhuguan").val(people);
        }else if(peole_kind == "选择办理人"){
            $("#banli").val(people);
        }
    })

    var status,username;
    ~function() {

        status = $("#status").text();
        username = $("#username").text();
        $("#user1").val(username);


    }();




    // 清空数据
    function mywipeData() {
        $("#edit input").val("");

        $("#notice_content").val("");
    }



    function newForm() {

        $('#shouwen_wdo').modal('show');
        console.log($("#user1").val());
//          $('#select_model').modal('show');
    }
    var flag = false;
    $("#first").click(function () {
        flag = !flag;
        if(flag == true){
            $("#more").height(760);
        }else {
            $("#more").height(30);
        }
    })






</script>
</body>
</html>