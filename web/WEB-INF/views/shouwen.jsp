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
    <link rel="stylesheet" href="../../css/jquery.step.css">
    <link rel="stylesheet" href="../../css/jedate.css">
    <link rel="stylesheet" href="../../js/themes/default/style.min.css">
    <link rel="stylesheet" href="../../css/money.css">
    <link rel="stylesheet" href="../../css/mycommon.css">
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
        #user_container1>div,#user_container>div{
            display: inline-block;
            vertical-align: middle;
            word-wrap: break-word;
            text-align: center;
            font-size: 14px;
            padding: 0 5px;
            width: 16.5%;
        }
        #shouwen_wdo form,#more,#more1,#model_info{
            width: 96%;
            margin: 0 auto;
        }
        .title{
            text-align: center;
            font-weight: 700;
            margin: 10px 0;
        }
        #shouwen_wdo table,#more table,#more1 table,#model_container table,#model_container_1 table{
            width: 100%;
            border-top: 1px solid #000;
            border-left: 1px solid #000;
        }
        table td{
            padding: 5px 0;
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
        }
        #shouwen_wdo table input,#more table input,#more1 table input{
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
            height: 100px;
            width: 100%;
        }
        #sel_model{
            width: 96%;
            margin: 0 auto;
            font-size: 0;
        }
        #sel_model>div{
            font-size: 14px;
        }
        #select_people{
            padding-left: 0;
            line-height: 20px;
        }
        li{
            list-style: none;
            margin-top: 5px;
        }
        #select_people li:nth-child(1),#select_people li:nth-child(2){
            display: none;
        }
        #select_people li span{
            display: inline-block;
            vertical-align: middle;
            width: 120px;
        }
        #model_container,#model_container_1{
            width: 96%;
            margin: 0 auto;
            position: relative;
            height: 740px;
            transition: all 0.5s;
        }
        #model_container>div,#model_container_1>div{
            position: absolute;
            top: 0;
            left: 0;
            width:100%;
            display: none;
        }
        #model_container>#model1,#model_container_1>#model1_1{
            display: block;
        }
        #model_container input,#model_container_1 input{
            width: 100%;
            outline:none;
            border: none;
            padding: 0 5px;
        }
        input{
            line-height: 16px !important;
        }
        .left{
            text-align: left;
        }
        #handle_people span{
            display: inline-block;
            vertical-align: middle;
            width: 150px;
        }
        #fawen td,#dcl_table td,#ycl_table td{
            border: none;
            border-top: 1px solid #ddd;
        }
        #more tr:nth-child(4) td:nth-child(2)>div,#more1 tr:nth-child(4) td:nth-child(2)>div{
            display: inline-block;
            vertical-align: top;
        }
        #more tr:nth-child(4) td:nth-child(2)>div button,#more1 tr:nth-child(4) td:nth-child(2)>div button{
            background: transparent;
            border: none;
            margin: 0;
            padding: 0;
        }
        #more tr:nth-child(4) td:nth-child(2)>div span,#more1 tr:nth-child(4) td:nth-child(2)>div span,#more tr:nth-child(4) td:nth-child(2)>div button,#more1 tr:nth-child(4) td:nth-child(2)>div button{
            color: #2fa4e7;
            margin: 0 5px;
        }
        #more tr:nth-child(4) td:nth-child(2)>div span:hover,#more1 tr:nth-child(4) td:nth-child(2)>div span:hover,#more tr:nth-child(4) td:nth-child(2)>div button:hover,#more1 tr:nth-child(4) td:nth-child(2)>div button:hover{
            text-decoration: underline;
        }
        #container,#container1{
            border-bottom: 1px solid #ccc;
        }
        .delete_wrapper{
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            z-index: 999;
            display: none;
        }
        .delete{
            position: absolute;
            top: 450px;
            left: 50%;
            transform: translate(-50%,-50%);
            width: 200px;
            line-height:20px;
            text-align: center;
            font-size: 14px;
            background-color: #fff;
        }
        #sel_people>p,#sel_people>ul,#sel_people>div{
            display: inline-block;
            vertical-align: top;
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
        .btn-success{
            display: none;
        }
    </style>


    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery-form.min.js"></script>
    <script src="../../js/ui-choose.js"></script>
    <script src="../../js/jquery.jedate.js"></script>
    <script src="../../js/jquery.step.js"></script>
    <!-- The fav icon -->
    <link rel="shortcut icon" href="../../img/favicon.ico">
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
        <a class="navbar-brand" href="/tohome.htm" style="width: 500px;"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
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
                    欢迎<span style="margin: 0 6px;">${user.name}</span><a href="logout.do" >注销</a>
                </c:if>
            </div>
        </div>
        <span id="status" style="display:none;width:0;height:0;">${user.level}</span>
        <span id="username" style="display:none;width:0;height:0;">${user.username}</span>
        <span id="roleList" style="display:none;width:0;height:0;">${user.roleList}</span>
        <span id="receivefileid" style="display:none;"></span>
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
                        <li id="m_apply1"><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span id="kind1">收文表单</span></a></li>

                        <li class="nav-header">我的事务</li>
                        <li id="dcl"><a href="#new2"><span class="notification red" id="nav_num"></span><i class="glyphicon glyphicon-tags"></i><span> 待处理事务</span></a></li>
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
                                                <a href="#">新建收文表单</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="新建收文表单" class="well top-block"
                                               href="#" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>
                                                <div id="kind2">新建收文表单</div>
                                            </a>
                                        </div>

                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 全部列表</h2>
                                        </div>


                                        <div class="box-content">
                                            <table id="fawen" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>收文编号</th>
                                                    <th>年度</th>
                                                    <th>类别</th>
                                                    <th>来文日期</th>
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
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 待办列表</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="dcl_table" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>收文编号</th>
                                                    <th>年度</th>
                                                    <th>类别</th>
                                                    <th>来文日期</th>
                                                    <th>标题</th>
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
                                                <a href="#">已办事务</a>
                                            </li>
                                        </ul>
                                    </div>


                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已办列表</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="ycl_table" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>收文编号</th>
                                                    <th>年度</th>
                                                    <th>类别</th>
                                                    <th>来文日期</th>
                                                    <th>标题</th>
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
                <form id = "fileForm"
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
                                    <textarea name="title" style="width: 99%;outline: none;height: 80px;"></textarea>
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

                                <td colspan="9"><textarea name="dealsituation" id="" style="width: 99%;"></textarea></td>


                            </tr>
                            </tbody>
                        </table>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>


            </div>
        </div>
    </div>

    <div class="modal fade" id="model_handle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3></h3>
                </div>
                <div id="container1" style="width: 100%;padding-bottom: 20px;">
                    <div class="step-body" id="myStep1" style="width:80%;margin: 0 auto;">
                        <div class="step-header">
                            <ul>
                                <li><p>办公室收文登记</p></li>
                                <li><p>办公室处理文件</p></li>
                                <li><p>签批</p></li>
                                <li><p>处理处置</p></li>
                                <li><p>办公室归档</p></li>
                                <li><p>结束</p></li>
                            </ul>
                        </div>
                    </div>
                    <div id="user_container1" style="width:80%;margin: 0 auto;">
                        <div class="user1_1"></div>
                        <div class="user1_2"></div>
                        <div class="user1_3"></div>
                        <div class="user1_4"></div>
                        <div class="user1_5"></div>
                        <div class="user1_6"></div>
                    </div>
                </div>
                <div id="more" style="height: 56px;overflow: hidden;transition: all 0.5s;border-top: 1px solid #ccc;">
                    <span class="style1">收文登记表</span><p id="first" style="margin-top:20px;margin-left:30px;font-size: 16px;cursor: pointer;line-height: 26px;display: inline-block;border: 1px solid #999;padding:0 10px;border-radius: 3px;color: #466faa;background: url(../../images/bg-2.png) repeat-x top left;">点击查看详细信息<span style="display: inline-block;width: 7px;height: 6px;background: url('../../images/arrow.png') no-repeat center center;background-size: 7px 6px;vertical-align: top;margin-top: 10px;margin-left: 14px;"></span></p>
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
                                <textarea name="timing" style="width: 99%;outline: none;height: 80px;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>下载附件</td>
                            <td colspan="9"></td>
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
                            <td colspan="9"><textarea name="chuli" style="width: 99%;"></textarea></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div id="model_container_1">
                    <div id="model1_1">
                        <p class="title"></p>
                        <table border-collapse="separate">
                            <tbody>
                            <tr>
                                <td>收文号</td>
                                <td><input type="text"></td>
                                <td>来文机关</td>
                                <td><input type="text"></td>
                                <td>来文号</td>
                                <td><input type="text"></td>
                                <td>缓急</td>
                                <td><input type="text"></td>
                                <td>密级</td>
                                <td><input type="text"></td>
                                <td>份数</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td>文件标题</td>
                                <td colspan="11"><input type="text" style="width: 100%"></td>
                            </tr>
                            <tr>
                                <td class="middle">拟办意见</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">分管领导批示</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">主要领导批示</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">办理结果</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;"></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="model2_1">
                        <p class="title"></p>
                        <table border-collapse="separate">
                            <tbody>
                            <tr>
                                <td>发文单位</td>
                                <td><input type="text"></td>
                                <td>文件字号</td>
                                <td><input type="text"></td>
                                <td>收文登记号</td>
                                <td><input type="text"></td>
                                <td>收文日期</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td class="middle">文件标题</td>
                                <td colspan="7"><textarea name="" style="height: 50px;"></textarea></td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">局领导批示:</p>
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">分管领导意见:</p>
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">拟办意见:</p>
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">办理结果:</p>
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="model3_1">
                        <p class="title"></p>
                        <table border-collapse="separate">
                            <tbody>
                            <tr>
                                <td>收文号</td>
                                <td><input type="text"></td>
                                <td>来文单位</td>
                                <td><input type="text"></td>
                                <td>来文号</td>
                                <td><input type="text"></td>
                                <td>缓急</td>
                                <td><input type="text"></td>
                                <td>密级</td>
                                <td><input type="text"></td>
                                <td>份数</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td class="middle">文件标题</td>
                                <td colspan="11"><textarea></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle" rowspan="3">
                                    拟办意见
                                </td>
                                <td colspan="5" rowspan="3">
                                    <textarea></textarea>
                                </td>
                                <td colspan="6">
                                    科室意见
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6"><input type="text" style="width: 100%;text-align:center;font-size: 14px;line-height: 20px;"></td>
                            </tr>
                            <tr>
                                <td colspan="6"><textarea></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle">主要领导批示意见</td>
                                <td colspan="11">
                                    <textarea></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">分管领导批示</td>
                                <td colspan="11">
                                    <textarea></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">办理结果</td>
                                <td colspan="11">
                                    <textarea></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="model4_1">
                        <p class="title"></p>
                        <table border-collapse="separate">
                            <tbody>
                            <tr>
                                <td>收文号</td>
                                <td><input type="text"></td>
                                <td>来文单位</td>
                                <td><input type="text"></td>
                                <td>来文号</td>
                                <td><input type="text"></td>
                                <td>缓急</td>
                                <td><input type="text"></td>
                                <td>密级</td>
                                <td><input type="text"></td>
                                <td>份数</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td class="middle">文件标题</td>
                                <td colspan="11"><textarea name=""></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle" rowspan="3">
                                    拟办意见
                                </td>
                                <td colspan="5" rowspan="3">
                                    <textarea></textarea>
                                </td>
                                <td colspan="6">
                                    科室意见
                                </td>
                            </tr>
                            <tr>
                                </td>
                                <td colspan="3"><input type="text" style="width: 100%;text-align:center;font-size: 14px;line-height: 20px;"></td>
                                <td colspan="3"><input type="text" style="width: 100%;text-align:center;font-size: 14px;line-height: 20px;"></td>
                            </tr>
                            <tr>
                                <td colspan="3"><textarea name=""></textarea></td>
                                <td colspan="3"><textarea name=""></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle">主要领导批示意见</td>
                                <td colspan="11">
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">分管领导批示</td>
                                <td colspan="11">
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">办理结果</td>
                                <td colspan="11">
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary">提交</a>
                    <a href="#" class="btn btn-success">打印</a>
                </div>
                <div class="delete_wrapper">
                    <div class="delete">
                        <p style="color: red;">确定删除此附件吗？</p>
                        <button class="cancel">取消</button>
                        <button class="confirm">确认</button>
                    </div>
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
                    <h3></h3>
                </div>
                <div id="container" style="width: 100%;padding-bottom: 20px;">
                    <div class="step-body" id="myStep" style="width:80%;margin: 0 auto;">
                        <div class="step-header">
                            <ul>
                                <li><p>办公室收文登记</p></li>
                                <li><p>办公室处理文件</p></li>
                                <li><p>签批</p></li>
                                <li><p>处理处置</p></li>
                                <li><p>办公室归档</p></li>
                                <li><p>结束</p></li>
                            </ul>
                        </div>
                    </div>
                    <div id="user_container" style="width:80%;margin: 0 auto;">
                        <div class="user_1"></div>
                        <div class="user_2"></div>
                        <div class="user_3"></div>
                        <div class="user_4"></div>
                        <div class="user_5"></div>
                        <div class="user_6"></div>
                    </div>
                </div>
                <div id="more1" style="height: 56px;overflow: hidden;transition: all 0.5s;">
                    <span class="style1">收文登记表</span><p id="first1" style="margin-top:20px;margin-left:34px;font-size: 16px;cursor: pointer;line-height: 26px;display: inline-block;border: 1px solid #999;padding:0 10px;border-radius: 3px;color: #466faa;background: url(../../images/bg-2.png) repeat-x top left;">点击查看详细信息<span style="display: inline-block;width: 7px;height: 6px;background: url('../../images/arrow.png') no-repeat center center;background-size: 7px 6px;vertical-align: top;margin-top: 10px;margin-left: 14px;"></span></p>
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
                                <textarea name="timing" style="width: 99%;outline: none;height: 80px;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>下载附件</td>
                            <td colspan="9"><a href="#"></a></td>
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
                            <td colspan="9"><textarea name="chuli" style="width: 99%;"></textarea></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div id="sel_model">
                    <div id="model">
                        <p class="style1">选择处理方式</p>
                        <select  class="form-control input-sm" name="" id="sel1" style="margin-left: 30px;display: inline-block;width: 171px;">
                            <option>直接处理</option>
                            <option>文件拟办单</option>
                            <option>一科室提意见</option>
                            <option>两科室提意见</option>
                        </select>
                    </div>
                    <div id="sel_people">
                        <p class="style1">选择处理人</p>
                        <p id="people_kind" style="display: none;"></p>
                        <ul id="select_people" style="margin-left: 30px;">
                            <li><span>科室1处理人</span><input type="text" id="keshi1"></li>
                            <li><span>科室2处理人</span><input type="text" id="keshi2"></li>
                            <li><span>分管领导处理人</span><input type="text" id="fenguan"></li>
                            <li><span>主管领导处理人</span><input type="text" id="zhuguan"></li>
                            <li><span>办理人</span><input type="text" id="banli"></li>
                        </ul>
                        <div id="tree_container_wrapper" style="margin-left: 30px;">
                            <div id="tree_container">
                                <ul>
                                    <li data-jstree='{"opened":false}'>办公室
                                        <ul>
                                            <li>穆志芳</li>
                                            <li>袁璐</li>
                                            <li>办公室测试账号</li>
                                        </ul>
                                    </li>
                                </ul>
                                <ul>
                                    <li data-jstree='{"opened":false}'>规划科
                                        <ul>
                                            <li>杨再培</li>
                                            <li>规划测试账号</li>
                                        </ul>
                                    </li>
                                </ul>
                                <ul>
                                    <li data-jstree='{"opened":false}'>财务科
                                        <ul>
                                            <li>财务测试账号</li>
                                        </ul>
                                    </li>
                                </ul>
                                <ul>
                                    <li data-jstree='{"opened":false}'>主管领导
                                        <ul>
                                            <li>主管领导测试账号</li>
                                        </ul>
                                    </li>
                                </ul>
                                <ul>
                                    <li data-jstree='{"opened":false}'>分管领导
                                        <ul>
                                            <li>分管领导测试账号</li>
                                        </ul>
                                    </li>
                                </ul>
                                <ul>
                                    <li data-jstree='{"opened":false}'>其他科室
                                        <ul>
                                            <li>其他用户测试账号</li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <button class="mybtn1">确认</button>
                        </div>
                    </div>
                </div>
                <div id="model_container">
                    <div id="model1">
                        <p class="title"><input type="text" style="width: 100%" value="临沧市移民局文件处理笺"></p>
                        <table>
                            <tbody>
                            <tr>
                                <td>收文号</td>
                                <td><input type="text"></td>
                                <td>来文机关</td>
                                <td><input type="text"></td>
                                <td>来文号</td>
                                <td><input type="text"></td>
                                <td>缓急</td>
                                <td><input type="text"></td>
                                <td>密级</td>
                                <td><input type="text"></td>
                                <td>份数</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td>文件标题</td>
                                <td colspan="11"><input type="text" style="width: 100%"></td>
                            </tr>
                            <tr>
                                <td class="middle">拟办意见</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">分管领导批示</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">主要领导批示</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">办理结果</td>
                                <td colspan="11">
                                    <textarea name="" style="width: 99%;" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="model2">
                        <p class="title"><input type="text" style="width: 100%" value="临沧市移民局文件处理笺"></p>
                        <table>
                            <tbody>
                            <tr>
                                <td>发文单位</td>
                                <td><input type="text"></td>
                                <td>文件字号</td>
                                <td><input type="text"></td>
                                <td>收文登记号</td>
                                <td><input type="text"></td>
                                <td>收文日期</td>
                                <td><input id="mytime" type="text" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td class="middle">文件标题</td>
                                <td colspan="7"><textarea name="" style="height: 50px;"></textarea></td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">局领导批示:</p>
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">分管领导意见:</p>
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">拟办意见:</p>
                                    <textarea name=""></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8">
                                    <p class="left">办理结果:</p>
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="model3">
                        <p class="title"><input type="text" style="width: 100%" value="临沧市移民局文件处理笺"></p>
                        <table>
                            <tbody>
                            <tr>
                                <td>收文号</td>
                                <td><input type="text"></td>
                                <td>来文单位</td>
                                <td><input type="text"></td>
                                <td>来文号</td>
                                <td><input type="text"></td>
                                <td>缓急</td>
                                <td><input type="text"></td>
                                <td>密级</td>
                                <td><input type="text"></td>
                                <td>份数</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td class="middle">文件标题</td>
                                <td colspan="11"><textarea name="" style="height: 50px"></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle" rowspan="3">
                                    拟办意见
                                </td>
                                <td colspan="5" style="border-bottom: none">
                                </td>
                                <td colspan="6">
                                    科室意见
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" style="border-bottom: none">
                                </td>
                                <td colspan="6"><input type="text" style="width: 110px;font-size: 16px;line-height: 20px;" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <textarea name=""></textarea>
                                </td>
                                <td colspan="6"><textarea name="" readonly="readonly"></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle">主要领导批示意见</td>
                                <td colspan="11">
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">分管领导批示</td>
                                <td colspan="11">
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">办理结果</td>
                                <td colspan="11">
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="model4">
                        <p class="title"><input type="text" style="width: 100%" value="临沧市移民局文件处理笺"></p>
                        <table>
                            <tbody>
                            <tr>
                                <td>收文号</td>
                                <td><input type="text"></td>
                                <td>来文单位</td>
                                <td><input type="text"></td>
                                <td>来文号</td>
                                <td><input type="text"></td>
                                <td>缓急</td>
                                <td><input type="text"></td>
                                <td>密级</td>
                                <td><input type="text"></td>
                                <td>份数</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td class="middle">文件标题</td>
                                <td colspan="11"><textarea name="" style="height: 50px"></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle" rowspan="3">
                                    拟办意见
                                </td>
                                <td colspan="5" style="border-bottom: none">
                                </td>
                                <td colspan="6">
                                    科室意见
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" style="border-bottom: none">
                                </td>
                                <td colspan="3"><input type="text" style="width: 110px;font-size: 16px;line-height: 20px;" readonly="readonly"></td>
                                <td colspan="3"><input type="text" style="width: 110px;font-size: 16px;line-height: 20px;" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <textarea name=""></textarea>
                                </td>
                                <td colspan="3"><textarea name="" readonly="readonly"></textarea></td>
                                <td colspan="3"><textarea name="" readonly="readonly"></textarea></td>
                            </tr>
                            <tr>
                                <td class="middle">主要领导批示意见</td>
                                <td colspan="11">
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">分管领导批示</td>
                                <td colspan="11">
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="middle">办理结果</td>
                                <td colspan="11">
                                    <textarea name="" readonly="readonly"></textarea>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>
                <div class="delete_wrapper">
                    <div class="delete">
                        <p style="color: red;">确定删除此附件吗？</p>
                        <button class="cancel">取消</button>
                        <button class="confirm">确认</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="print_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <table id="print1" style="border-top: 1px solid #000;border-left: 1px solid #000;" border-collapse="separate">
                    <tbody>
                    <tr>
                        <td colspan="12"></td>
                    </tr>
                    <tr>
                        <td>收文号</td>
                        <td></td>
                        <td>来文机关</td>
                        <td></td>
                        <td>来文号</td>
                        <td></td>
                        <td>缓急</td>
                        <td></td>
                        <td>密级</td>
                        <td></td>
                        <td>份数</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>文件标题</td>
                        <td colspan="11"></td>
                    </tr>
                    <tr>
                        <td class="middle">拟办意见</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    <tr>
                        <td class="middle">分管领导批示</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    <tr>
                        <td class="middle">主要领导批示</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    <tr>
                        <td class="middle">办理结果</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table id="print2" style="border-top: 1px solid #000;border-left: 1px solid #000;" border-collapse="separate">
                    <tbody>
                    <tr>
                        <td colspan="8"></td>
                    </tr>
                    <tr>
                        <td>发文单位</td>
                        <td></td>
                        <td>文件字号</td>
                        <td></td>
                        <td>收文登记号</td>
                        <td></td>
                        <td>收文日期</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="middle">文件标题</td>
                        <td colspan="7"></td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <p class="left">局领导批示:</p>
                            <p></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <p class="left">分管领导意见:</p>
                            <p></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <p class="left">拟办意见:</p>
                            <p></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <p class="left">办理结果:</p>
                            <p></p>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table id="print3" style="border-top: 1px solid #000;border-left: 1px solid #000;" border-collapse="separate">
                    <tbody>
                    <tr><td colspan="12"></td></tr>
                    <tr>
                        <td>收文号</td>
                        <td><input type="text"></td>
                        <td>来文单位</td>
                        <td><input type="text"></td>
                        <td>来文号</td>
                        <td><input type="text"></td>
                        <td>缓急</td>
                        <td><input type="text"></td>
                        <td>密级</td>
                        <td><input type="text"></td>
                        <td>份数</td>
                        <td><input type="text"></td>
                    </tr>
                    <tr>
                        <td class="middle">文件标题</td>
                        <td colspan="11"><textarea></textarea></td>
                    </tr>
                    <tr>
                        <td class="middle" rowspan="3">
                            拟办意见
                        </td>
                        <td colspan="5" rowspan="3">
                        </td>
                        <td colspan="6">
                            科室意见
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6"></td>
                    </tr>
                    <tr>
                        <td colspan="6"></td>
                    </tr>
                    <tr>
                        <td class="middle">主要领导批示意见</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    <tr>
                        <td class="middle">分管领导批示</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    <tr>
                        <td class="middle">办理结果</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table id="print4" style="border-top: 1px solid #000;border-left: 1px solid #000;" border-collapse="separate">
                    <tbody>
                    <tr>
                        <td colspan="12"></td>
                    </tr>
                    <tr>
                        <td>收文号</td>
                        <td></td>
                        <td>来文单位</td>
                        <td></td>
                        <td>来文号</td>
                        <td></td>
                        <td>缓急</td>
                        <td></td>
                        <td>密级</td>
                        <td><</td>
                        <td>份数</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="middle">文件标题</td>
                        <td colspan="11"></td>
                    </tr>
                    <tr>
                        <td class="middle" rowspan="3">
                            拟办意见
                        </td>
                        <td colspan="5" rowspan="3">
                        </td>
                        <td colspan="6">
                            科室意见
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"></td>
                        <td colspan="3"></td>
                    </tr>
                    <tr>
                        <td colspan="3"></td>
                        <td colspan="3"></td>
                    </tr>
                    <tr>
                        <td class="middle">主要领导批示意见</td>
                        <td colspan="11">
                        </td>
                    </tr>
                    <tr>
                        <td class="middle">分管领导批示</td>
                        <td colspan="11">

                        </td>
                    </tr>
                    <tr>
                        <td class="middle">办理结果</td>
                        <td colspan="11">

                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

</div>




<footer class="row">
    <p class="col-md-12 col-sm-12 col-xs-12 copyright" style="text-align: center;">&copy; 临沧市移民局 <span style="margin-left: 10px;">Powered by: </span><a
            href="http://www.bhidi.com">北京院</a></p>
</footer>
</div><!--/.fluid-container-->







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
<script src="../../js/jstree.js"></script>
<script src="../../js/jquery-form.min.js"></script>
<script src="../../js/jQuery.print.js"></script>
<script src="../../js/money.js"></script>
<script>
    //数组去重
    Array.prototype.unique3 = function(){
        var res = [];
        var json = {};
        for(var i = 0; i < this.length; i++){
            if(!json[this[i]]){
                res.push(this[i]);
                json[this[i]] = 1;
            }
        }
        return res;
    }
    //收文登记
    var shouwen = $('#fawen').DataTable({
        ajax: {
            url: "/receiveFileDataTable.do",
            async: false
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "receivefileid"},
            {"data": "year"},
            {"data": "type"},
            {"data": "cometime"},
            {"data": "title"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [6],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                    html += "<input type='button' class='btn btn-danger btn-xs' style='margin-left: 5px;' onclick='delete1(this)' value='删除'/>" ;
                    return html;
                }
            }
        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        }
    });

    //待处理
    var dcl_table = $('#dcl_table').DataTable({
        ajax: {
            url: "/receiveFileDataTableByNameAndStatus.do"
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "receivefileid"},
            {"data": "year"},
            {"data": "type"},
            {"data": "cometime"},
            {"data": "title"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [6],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                    html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='编辑'/>" ;
                    return html;
                }
            }
        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        }
    });

    //已处理
    var ycl_table = $('#ycl_table').DataTable({
        ajax: {
            url: "/receiveFileDataTableByNameAndStatusHave.do"
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "receivefileid"},
            {"data": "year"},
            {"data": "type"},
            {"data": "cometime"},
            {"data": "title"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [6],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                    html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='编辑'/>" ;
                    return html;
                }
            }
        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        }
    });

    //获取功能
    var fun_list = [];
    $.ajax({
        url: "/getFunction.do",
        type: "post",
        async: false,
        dataType: "json",
        success:function (data) {
            console.log(data);
            $.each(data.function,function (i,n) {
                if(n.classification == "收文管理"){
                    fun_list.push(n);
                }
            })
        }
    });
    var fun_list1 = [];
    $.each(fun_list,function (i,n) {
        if(n.subclassification == "我的表单"){
            fun_list1.push(n)
        }
    })
    var f1 = [];
    var f2 = [];
    $.each(fun_list1,function(i,n){
        if(n.authdescription == "文件登记功能"){
            f1.push(n);
        }else if(n.authdescription == "全部列表查看、搜索、删除功能"){
            f2.push(n);
        }
    })
    if(f1.length == 0){
        $("#new1>.row").css("display","none");
    }
    if(f2.length == 0){
        $("#new1>.box-inner").css("display","none");
    }
    if(f1.length == 0 && f2.length == 0){
        $("#myTab li:nth-child(1)").remove();
        $("#myTab li:nth-child(1)").remove();
        $("#new1").remove();
        $("#dcl").addClass("active");
        $("#new2").addClass("active");
    }

    //删除功能
    function delete1(that) {
        var receivefileid = $(that).parent("td").parent("tr").children("td:first-child").text();
        var modeltype;
        $.ajax({
            url: "/getReceiveFileAndModelInfo.do",
            async: false,
            type: "post",
            data: {receivefileid:receivefileid},
            dataType: "json",
            success: function (data) {
                console.log(data)
                modeltype = data.ReceiveFile.modeltype;
            }
        })
        console.log(receivefileid);
        if(confirm("你确定要删除吗？")){
            $.ajax({
                url: "/deleteReceiveFile.do",
                type: "post",
                dataType: "json",
                data: {receivefileid:receivefileid,modeltype:modeltype},
                success: function (data) {
                    if(data.result == "success"){
                        table_refresh();
                        setTimeout(acount,100);
                        alert("删除成功");
                    }else {
                        alert(data.result);
                    }
                }
            })
        }
    }


    //待办事务的显示条数
    function acount() {
        var info = dcl_table.page.info();
        $("#nav_num").text(info.recordsTotal)
    }
    setTimeout(acount,100);

    //表格刷新
    function table_refresh() {
        shouwen.ajax.url("/receiveFileDataTable.do").load();
        dcl_table.ajax.url("/receiveFileDataTableByNameAndStatus.do").load();
        ycl_table.ajax.url("/receiveFileDataTableByNameAndStatusHave.do").load();
    }

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
    $("#mytime").jeDate({
        format: "YYYY-MM-DD"
    });

    //树形插件
    var jstree = $('#tree_container').jstree({
        "plugins" : ["checkbox"]
    });
//    $('#tree_container').on('changed.jstree', function (e, data) {
//        console.log(data);
//        $('#tree_container').jstree(true).toggle_node(data.deselected);
//    })
    // 多文件上传
    var fileIndex = 1;
    function add_click_file(index){
        $("#add_file_"+fileIndex).click();
    }


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

    //收文登记提交
    var sflag = true;
    $("#shouwen_wdo .btn-primary").click(function () {
        if(sflag){
            var val1 = $("#fileForm tr:nth-child(1) td:nth-child(2) input").val();
            var val2 = $("#fileForm tr:nth-child(1) td:nth-child(6) input").val();
            var val3 = $("#fileForm tr:nth-child(1) td:nth-child(8) input").val();
            var val4 = $("#fileForm tr:nth-child(2) td:nth-child(2) input").val();
            var val5 = $("#fileForm tr:nth-child(2) td:nth-child(4) input").val();
            var val6 = $("#fileForm tr:nth-child(2) td:nth-child(8) input").val();
            var val7 = $("#fileForm tr:nth-child(3) td:nth-child(2) textarea").val();
            var val8 = $("#fileForm tr:nth-child(5) td:nth-child(2) input").val();
            if(!val1){
                alert("年度不能为空");
                return;
            }else if(!val2){
                alert("类别不能为空");
                return;
            }else if(!val3){
                alert("来文日期不能为空");
                return;
            }else if(!val4){
                alert("文件编号不能为空");
                return;
            }else if(!val5){
                alert("登记号不能为空");
                return;
            }else if(!val6){
                alert("成文日期不能为空");
                return;
            }else if(!val7){
                alert("题名不能为空");
                return;
            }else if(!val8){
                alert("主题词不能为空");
                return;
            }
            sflag = false;
            var options  = {
                url:'reveiceFileRegistration.do',
                type:'post',
                success:function(data)
                {
                    console.log(data);
                    if(data.result == "success"){
                        alert("提交成功");
                        sflag = true;
                        $('#shouwen_wdo').modal('hide');
                        $("#shouwen_wdo input").val("");
                        $("#shouwen_wdo textarea").val("");
                        table_refresh();
                        setTimeout(acount,100);
                    }else {
                        alert(data.result);
                        sflag = true;
                    }
                }
            };
            $("#fileForm").ajaxSubmit(options);
        }
    })

    //选择模版
    $("#sel1").change(function () {
        $("#model_container>div").css("display","none");
        $("#select_people li input").val("");
        $("#model_container input").val("");
        $("#model_container textarea").val("");
        $("#model_container .title input").val("临沧市移民局文件处理笺");
        if($(this).val() == "一科室提意见"){
            $("#select_people li:nth-child(1)").css("display","block");
            $("#select_people li:nth-child(2)").css("display","none");
            $("#model_container>#model3").css({"display":"block"});
        }else if($(this).val() == "两科室提意见"){
            $("#select_people li:nth-child(1)").css("display","block");
            $("#select_people li:nth-child(2)").css("display","block");
            $("#model_container>#model4").css({"display":"block"});
        }else {
            $("#select_people li:nth-child(1)").css("display","none");
            $("#select_people li:nth-child(2)").css("display","none");
            if($(this).val() == "直接处理"){
                $("#model_container>#model1").css({"display":"block"});
            }else if($(this).val() == "文件拟办单"){
                $("#model_container>#model2").css({"display":"block"});
            }
        }
    });
    //选择处理人
    $("#select_people li input").focus(function () {
        var text = $(this).siblings("span").text();
        $("#people_kind").text(text);
        $('#tree_container').jstree('deselect_all');
    })


    //处理人集合
    $("#sel_people button").click(function () {

        var people_arr = [];
        var arr = [];
        $.each($("#tree_container").jstree().get_selected(true),function (i,n) {
            if(n.parent != "#"){
                console.log(n);
                people_arr.push(n.text);
                arr.push(n.parent);
            }
        })
        var peole_kind = $("#people_kind").text();
        var people = people_arr.toString();

        if(peole_kind == "办公室处理人"){
            $("#bangongshi").val(people);
        }else if(peole_kind == "科室1处理人"){
            arr = arr.unique3();
            if(arr.length >1){
                alert("此处不能选择多个部门");
                return;
            }else {
                $("#keshi1").val(people);
            }
        }else if(peole_kind == "科室2处理人"){
            arr = arr.unique3();
            if(arr.length > 1){
                alert("此处不能选择多个部门");
                return;
            }else {
                $("#keshi2").val(people);
            }
        }else if(peole_kind == "分管领导处理人"){
            $("#fenguan").val(people);
        }else if(peole_kind == "主管领导处理人"){
            $("#zhuguan").val(people);
        }else if(peole_kind == "办理人"){
            $("#banli").val(people);
        }



    });

    var username;
    ~function() {

        username = $("#username").text();
        $("#user1").val(username);


    }();
    //查看登记信息按钮
    var flag = false;
    $("#first").click(function () {
        flag = !flag;
        if(flag == true){
            $("#more").height(760);
        }else {
            $("#more").height(56);
        }
    })
    var flag1 = false;
    $("#first1").click(function () {
        flag1 = !flag1;
        if(flag1 == true){
            $("#more1").height(760);
        }else {
            $("#more1").height(56);
        }
    })


    var file_arr = [];
    var del_file_arr = [];
    var mydata1;
    var mydata;
    var id;
    var state;

    //删除附件
    var del_path;
    var $that;
    function del(that) {
        $that = $(that);
        $(that).parent("form").parent("div").parent("td").parent("tr").parent("tbody").parent("table").parent("div").siblings(".delete_wrapper").css("display","block");
        del_path = $(that).siblings(".file_url").val();
    }
    $(".cancel").click(function () {
        $(this).parent(".delete").parent(".delete_wrapper").css("display","none");
    })
    $(".confirm").click(function () {
        console.log(del_path,id);
        var $this = $(this);
        $.ajax({
            url: "/pathDelete.do",
            type: "post",
            data: {receivefileid:id,path:del_path},
            dataType: "json",
            success: function(data){
                console.log(data);
                if(data.result == "success"){
                    $this.parent(".delete").parent(".delete_wrapper").css("display","none");
                    $that.parent("form").parent("div").remove();
                }
            }
        })
    })




//    function delete_file() {
//        var delete_file = [];
//        if(del_file_arr.length > 0){
//            $.each(del_file_arr,function (i,n) {
//                var index = n.lastIndexOf(".");
//                var name = n.substring(0,index);
//                $.each(file_arr,function (i,n) {
//                    var num = n.search(name);
//                    if(num > 0){
//                        delete_file.push(n);
//                    }
//                })
//            });
//        }
//        console.log(delete_file)
//        if(delete_file.length>0){
//            console.log(id,delete_file);
//            var mydelete = JSON.stringify(delete_file);
//            $.ajax({
//                url: "/pathDelete.do",
//                type: "post",
//                data: {receivefileid:id,path:mydelete},
//                dataType: "json",
//                success: function(data){
//                    console.log(data);
//                }
//            })
//        }
//    }

    function newForm() {

        $("#shouwen_wdo input").val("");
        $("#shouwen_wdo textarea").val("");
        $.each($("#filesUpload a"),function (i,n) {
            if(n.text != "添加附件"){
                n.remove()
            }
        })
        $("#filesUpload span").remove();
        $('#shouwen_wdo').modal('show');

    }
    //编辑查看按钮
    var model_name;
    function edit(that) {
        //查看发文登记信息
        $("#select_model input").val("");
        $("#select_model textarea").val("");
        $("#model_handle input").val("");
        $("#model_handle textarea").val("");
        $("#model_container .title input").val("临沧市移民局文件处理笺");
        $(".btn-success").css("display","none");
        var kind = $(that).val();
        state = $(that).parent("td").parent("tr").children("td:nth-child(6)").text();
        id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
        $("#select_model .modal-header h3").text("收文管理-" + id);
        $("#model_handle .modal-header h3").text("收文管理-" + id);
        if(state == "办公室归档"){
            $("#model_handle .btn-primary").text("确认归档");
        }else {
            $("#model_handle .btn-primary").text("提交");
        }
        $.ajax({
            url: "getReceiveFileInfoById.do",
            async: false,
            type: "post",
            dataType: "json",
            data: {id:id},
            success: function (data) {
                mydata = data;
                console.log(data);
                $("#more tr:nth-child(4) td:nth-child(2)").empty();
                $("#more1 tr:nth-child(4) td:nth-child(2)").empty();
                if(data.attachmentpath != ""){
                    file_arr = data.attachmentpath.split(",");
                    $.each(file_arr,function (i,n) {
                        var start = n.lastIndexOf("\\") + 1;
                        var end = n.lastIndexOf("-");
                        var filekind_index = n.lastIndexOf(".");
                        var str = n.substring(start,end);
                        var filekind = n.substring(filekind_index);
                        str = str + filekind;
                        var files = "";
                        files  += ""
                            + "<div style='display: inline-block;'>"
                            + "<iframe name='downloadFrame' style='display:none;'></iframe>"
                            + "<form action='/file/download.do' method='get' target='downloadFrame'>"
                            + "<span class='file_name' style='color: #000;'>"+str+"</span>"
                            + "<input class='file_url' style='display: none;' name='path' value="+ n +">"
//                            + "<span class='del' onclick='del(this)'>删除</span>"
                            + "<button type='submit'>下载</button>"
                            + "</form>"
                            + "</div>"
                        $("#more tr:nth-child(4) td:nth-child(2)").append(files);
                        $("#more1 tr:nth-child(4) td:nth-child(2)").append(files);
                        console.log(str)
                    });
                }
                $("#more tr:nth-child(1) td:nth-child(2) input").val(data.year);
                $("#more tr:nth-child(1) td:nth-child(4) input").val(data.savetime);
                $("#more tr:nth-child(1) td:nth-child(6) input").val(data.type);
                $("#more tr:nth-child(1) td:nth-child(8) input").val(data.cometime);
                $("#more tr:nth-child(2) td:nth-child(2) input").val(data.fileid);
                $("#more tr:nth-child(2) td:nth-child(4) input").val(data.registrationnum);
                $("#more tr:nth-child(2) td:nth-child(6) input").val(data.fileallid);
                $("#more tr:nth-child(2) td:nth-child(8) input").val(data.	writtentime);
                $("#more tr:nth-child(3) td:nth-child(2) textarea").val(data.title);


                $("#more tr:nth-child(5) td:nth-child(2) input").val(data.keyword);
                $("#more tr:nth-child(5) td:nth-child(4) input").val(data.responsibleperson);
                $("#more tr:nth-child(6) td:nth-child(2) input").val(data.archivecopies);
                $("#more tr:nth-child(6) td:nth-child(4) input").val(data.pagenum);
                $("#more tr:nth-child(6) td:nth-child(6) input").val(data.secret);
                $("#more tr:nth-child(6) td:nth-child(8) input").val(data.issues);
                $("#more tr:nth-child(6) td:nth-child(10) input").val(data.receiveperson);
                $("#more tr:nth-child(7) td:nth-child(2) input").val(data.comedepartment);
                $("#more tr:nth-child(7) td:nth-child(4) input").val(data.attachmentpagenum);
                $("#more tr:nth-child(8) td:nth-child(2) input").val(data.entitynum);
                $("#more tr:nth-child(8) td:nth-child(4) input").val(data.distributionsituation);
                $("#more tr:nth-child(8) td:nth-child(6) input").val(data.oldfond);
                $("#more tr:nth-child(9) td:nth-child(2) input").val(data.archivesituation);
                $("#more tr:nth-child(9) td:nth-child(4) input").val(data.registrationdate);
                $("#more tr:nth-child(9) td:nth-child(6) input").val(data.circulationsituation);
                $("#more tr:nth-child(10) td:nth-child(2) textarea").val(data.dealsituation);


                $("#more1 tr:nth-child(1) td:nth-child(2) input").val(data.year);
                $("#more1 tr:nth-child(1) td:nth-child(4) input").val(data.savetime);
                $("#more1 tr:nth-child(1) td:nth-child(6) input").val(data.type);
                $("#more1 tr:nth-child(1) td:nth-child(8) input").val(data.cometime);
                $("#more1 tr:nth-child(2) td:nth-child(2) input").val(data.fileid);
                $("#more1 tr:nth-child(2) td:nth-child(4) input").val(data.registrationnum);
                $("#more1 tr:nth-child(2) td:nth-child(6) input").val(data.fileallid);
                $("#more1 tr:nth-child(2) td:nth-child(8) input").val(data.writtentime);
                $("#more1 tr:nth-child(3) td:nth-child(2) textarea").val(data.title);


                $("#more1 tr:nth-child(5) td:nth-child(2) input").val(data.keyword);
                $("#more1 tr:nth-child(5) td:nth-child(4) input").val(data.responsibleperson);
                $("#more1 tr:nth-child(6) td:nth-child(2) input").val(data.archivecopies);
                $("#more1 tr:nth-child(6) td:nth-child(4) input").val(data.pagenum);
                $("#more1 tr:nth-child(6) td:nth-child(6) input").val(data.secret);
                $("#more1 tr:nth-child(6) td:nth-child(8) input").val(data.issues);
                $("#more1 tr:nth-child(6) td:nth-child(10) input").val(data.receiveperson);
                $("#more1 tr:nth-child(7) td:nth-child(2) input").val(data.comedepartment);
                $("#more1 tr:nth-child(7) td:nth-child(4) input").val(data.attachmentpagenum);
                $("#more1 tr:nth-child(8) td:nth-child(2) input").val(data.entitynum);
                $("#more1 tr:nth-child(8) td:nth-child(4) input").val(data.distributionsituation);
                $("#more1 tr:nth-child(8) td:nth-child(6) input").val(data.oldfond);
                $("#more1 tr:nth-child(9) td:nth-child(2) input").val(data.archivesituation);
                $("#more1 tr:nth-child(9) td:nth-child(4) input").val(data.registrationdate);
                $("#more1 tr:nth-child(9) td:nth-child(6) input").val(data.circulationsituation);
                $("#more1 tr:nth-child(10) td:nth-child(2) textarea").val(data.dealsituation);
            }
        });
        $("#receivefileid").text(id);
        $(".user_1").empty();
        $(".user1_1").empty();
        $(".user_2").empty();
        $(".user1_2").empty();
        $(".user1_3").empty();
        $(".user1_4").empty();
        $(".user1_5").empty();
        if(mydata.reveivereregisterpersonname != ""){
            var str = "";
            str +=  ""
                +   "<div>"
                +   "<p class='user'>"+ mydata.reveivereregisterpersonname +"</p>"
                +   "<p class='time'>"+ mydata.reveivereregistertime +"</p>"
                +   "</div>"
            $(".user1_1").append(str).addClass("myactive");
            $(".user_1").append(str).addClass("myactive");
        }
        if(mydata.modelchoicename != ""){
            var str = "";
            str +=  ""
                +   "<div>"
                +   "<p class='user'>"+ mydata.modelchoicename +"</p>"
                +   "<p class='time'>"+ mydata.modelchoicetime +"</p>"
                +   "</div>"
            $(".user1_2").append(str).addClass("myactive");
            $(".user_2").append(str).addClass("myactive");
        }
        if(mydata.department1person != ""){
            var peoplearr = mydata.department1person.split(",");
            var deletearr = mydata.department1persondelete.split(",");
            var timearr = mydata.department1time.split(",");
            $.each(peoplearr,function (i,n) {
                var str = "";
                str +=  ""
                    +   "<div>"
                    +   "<p class='user'>"+ n +"</p>"
                    +   "<p class='time'></p>"
                    +   "</div>"
                $(".user1_3").append(str);
            });
            $.each(deletearr,function (i,n) {
                var name = n;
                var time = timearr[i];
                $.each(peoplearr,function (i,n) {
                    if(name == n){
                        $(".user1_3>div:nth-child("+(i+1)+")").addClass("myactive");
                        $(".user1_3>div:nth-child("+(i+1)+")").children(".time").text(time);
                    }
                })
            })
        }
        if(mydata.department2person != ""){
            var peoplearr = mydata.department2person.split(",");
            var deletearr = mydata.department2persondelete.split(",");
            var timearr = mydata.department2time.split(",");
            var size = $(".user1_3>div").size() + 1;
            $.each(peoplearr,function (i,n) {
                var str = "";
                str +=  ""
                    +   "<div>"
                    +   "<p class='user'>"+ n +"</p>"
                    +   "<p class='time'></p>"
                    +   "</div>"
                $(".user1_3").append(str);
            });
            $.each(deletearr,function (i,n) {
                var name = n;
                var time = timearr[i];
                $.each(peoplearr,function (i,n) {
                    if(name == n){
                        $(".user1_3>div:nth-child("+(i+size)+")").addClass("myactive");
                        $(".user1_3>div:nth-child("+(i+size)+")").children(".time").text(time);
                    }
                })
            })
        }
        if(mydata.fenguanname != ""){
            var peoplearr = mydata.fenguanname.split(",");
            var deletearr = mydata.fenguannamedelete.split(",");
            var timearr = mydata.fenguantime.split(",");
            var size = $(".user1_3>div").size() + 1;
            $.each(peoplearr,function (i,n) {
                var str = "";
                str +=  ""
                    +   "<div>"
                    +   "<p class='user'>"+ n +"</p>"
                    +   "<p class='time'></p>"
                    +   "</div>"
                $(".user1_3").append(str);
            });
            $.each(deletearr,function (i,n) {
                var name = n;
                var time = timearr[i];
                $.each(peoplearr,function (i,n) {
                    if(name == n){
                        $(".user1_3>div:nth-child("+(i+size)+")").addClass("myactive");
                        $(".user1_3>div:nth-child("+(i+size)+")").children(".time").text(time);
                    }
                })
            })
        }
        if(mydata.zhuguanname != ""){
            var peoplearr = mydata.zhuguanname.split(",");
            var deletearr = mydata.zhuguannamedelete.split(",");
            var timearr = mydata.zhuguantime.split(",");
            var size = $(".user1_3>div").size() + 1;
            $.each(peoplearr,function (i,n) {
                var str = "";
                str +=  ""
                    +   "<div>"
                    +   "<p class='user'>"+ n +"</p>"
                    +   "<p class='time'></p>"
                    +   "</div>"
                $(".user1_3").append(str);
            });
            $.each(deletearr,function (i,n) {
                var name = n;
                var time = timearr[i];
                $.each(peoplearr,function (i,n) {
                    if(name == n){
                        $(".user1_3>div:nth-child("+(i+size)+")").addClass("myactive");
                        $(".user1_3>div:nth-child("+(i+size)+")").children(".time").text(time);
                    }
                })
            })
        }
        if(mydata.implementperson != ""){
            var peoplearr = mydata.implementperson.split(",");
            var deletearr = mydata.implementpersondelete.split(",");
            var timearr = mydata.implementtime.split(",");
            $.each(peoplearr,function (i,n) {
                var str = "";
                str +=  ""
                    +   "<div>"
                    +   "<p class='user'>"+ n +"</p>"
                    +   "<p class='time'></p>"
                    +   "</div>"
                $(".user1_4").append(str);
            });
            $.each(deletearr,function (i,n) {
                var name = n;
                var time = timearr[i];
                $.each(peoplearr,function (i,n) {
                    if(name == n){
                        $(".user1_4>div:nth-child("+(i+1)+")").addClass("myactive");
                        $(".user1_4>div:nth-child("+(i+1)+")").children(".time").text(time);
                    }
                })
            })
        }
        if(mydata.confirmperson != ""){
            var str = "";
            str +=  ""
                +   "<div>"
                +   "<p class='user'>"+ mydata.confirmperson +"</p>"
                +   "<p class='time'>"+ mydata.confirmtime +"</p>"
                +   "</div>"
            $(".user1_5").append(str).addClass("myactive");
        }
        if(state == "办公室处理文件"){
            if(kind == "查看"){
                $('#model_handle').modal('show');
                $('#model_handle .btn-primary').css('display','none');
                $("#model_info").css("display","none");
                $("#model_container_1").css("display","none");
            }else if(kind == "编辑"){
                $("#select_people input").val("");
                $("#more1 input").attr("readonly",true);
                $('#tree_container').jstree('deselect_all');
                $('#select_model').modal('show');
            }
            step1.goStep(2);
            step.goStep(2);
        }else {
            var fina_data;
            $.ajax({
                url:"/getReceiveFileAndModelInfo.do",
                async: false,
                type: "post",
                data: {receivefileid:id},
                dataType: "json",
                success: function(data){
                    console.log(data);
                    fina_data = data;
                    mydata1 = data.ReceiveFile;
                    model_name = data.ReceiveFile.modeltype;
                    $("#model_container_1>div").css("display","none");
                    if(model_name == "直接处理"){
                        $("#model1_1").css("display","block");
                        $("#model1_1>.title").text(data.model.filename);
                        $("#model1_1 tr:first-child td:nth-child(2) input").val(data.model.receivefilenum);
                        $("#model1_1 tr:first-child td:nth-child(4) input").val(data.model.comefiledepartment);
                        $("#model1_1 tr:first-child td:nth-child(6) input").val(data.model.comefilenum);
                        $("#model1_1 tr:first-child td:nth-child(8) input").val(data.model.urgency);
                        $("#model1_1 tr:first-child td:nth-child(10) input").val(data.model.secret);
                        $("#model1_1 tr:first-child td:nth-child(12) input").val(data.model.copys);
                        $("#model1_1 tr:nth-child(2) td:nth-child(2) input").val(data.model.filetitle);
                        $("#model1_1 tr:nth-child(3) td:nth-child(2) textarea").val(data.model.suggestion);
                        $("#model1_1 tr:nth-child(4) td:nth-child(2) textarea").val(data.model.branchleaderinstruction);
                        $("#model1_1 tr:nth-child(5) td:nth-child(2) textarea").val(data.model.mainleaderinstruction);
                        $("#model1_1 tr:nth-child(6) td:nth-child(2) textarea").val(data.model.result);
                    }else if(model_name == "文件拟办单"){
                        $("#model2_1").css("display","block");
                        $("#model2_1>.title").text(data.model.filename);
                        $("#model2_1 tr:first-child td:nth-child(2) input").val(data.model.dispatchfiledepartment);
                        $("#model2_1 tr:first-child td:nth-child(4) input").val(data.model.filenum);
                        $("#model2_1 tr:first-child td:nth-child(6) input").val(data.model.receivefileregisterid);
                        $("#model2_1 tr:first-child td:nth-child(8) input").val(data.model.receivefiledate);
                        $("#model2_1 tr:nth-child(2) td:nth-child(2) textarea").val(data.model.filetitle);
                        $("#model2_1 tr:nth-child(5) td textarea").val(data.model.suggestion);
                        $("#model2_1 tr:nth-child(3) td:nth-child(1) textarea").val(data.model.mainleaderinstruction);
                        $("#model2_1 tr:nth-child(4) td:nth-child(1) textarea").val(data.model.branchleaderinstruction);
                        $("#model2_1 tr:nth-child(6) td:nth-child(1) textarea").val(data.model.result);
                    }else if(model_name == "一科室提意见"){
                        $("#model3_1").css("display","block");
                        $("#model3_1>.title").text(data.model.filename);
                        $("#model3_1 tr:first-child td:nth-child(2) input").val(data.model.receivefilenum);
                        $("#model3_1 tr:first-child td:nth-child(4) input").val(data.model.comefiledepartment);
                        $("#model3_1 tr:first-child td:nth-child(6) input").val(data.model.comefilenum);
                        $("#model3_1 tr:first-child td:nth-child(8) input").val(data.model.urgency);
                        $("#model3_1 tr:first-child td:nth-child(10) input").val(data.model.secret);
                        $("#model3_1 tr:first-child td:nth-child(12) input").val(data.model.copys);
                        $("#model3_1 tr:nth-child(2) td:nth-child(2) textarea").val(data.model.filetitle);
                        $("#model3_1 tr:nth-child(3) td:nth-child(2) textarea").val(data.model.suggestion);
                        $("#model3_1 tr:nth-child(6) td:nth-child(2) textarea").val(data.model.mainleaderinstruction);
                        $("#model3_1 tr:nth-child(7) td:nth-child(2) textarea").val(data.model.branchleaderinstruction);
                        $("#model3_1 tr:nth-child(8) td:nth-child(2) textarea").val(data.model.result);
                        $("#model3_1 tr:nth-child(4) td:nth-child(1) input").val(data.model.department);
                        $("#model3_1 tr:nth-child(5) td:nth-child(1) textarea").val(data.model.departmentadvice);
                    }else if(model_name == "两科室提意见"){
                        $("#model4_1").css("display","block");
                        $("#model4_1>.title").text(data.model.filename);
                        $("#model4_1 tr:first-child td:nth-child(2) input").val(data.model.receivefilenum);
                        $("#model4_1 tr:first-child td:nth-child(4) input").val(data.model.comefiledepartment);
                        $("#model4_1 tr:first-child td:nth-child(6) input").val(data.model.comefilenum);
                        $("#model4_1 tr:first-child td:nth-child(8) input").val(data.model.urgency);
                        $("#model4_1 tr:first-child td:nth-child(10) input").val(data.model.secret);
                        $("#model4_1 tr:first-child td:nth-child(12) input").val(data.model.copys);
                        $("#model4_1 tr:nth-child(2) td:nth-child(2) textarea").val(data.model.filetitle);
                        $("#model4_1 tr:nth-child(3) td:nth-child(2) textarea").val(data.model.suggestion);
                        $("#model4_1 tr:nth-child(6) td:nth-child(2) textarea").val(data.model.mainleaderinstruction);
                        $("#model4_1 tr:nth-child(7) td:nth-child(2) textarea").val(data.model.branchleaderinstruction);
                        $("#model4_1 tr:nth-child(8) td:nth-child(2) textarea").val(data.model.result);
                        $("#model4_1 tr:nth-child(4) td:nth-child(1) input").val(data.model.department1name);
                        $("#model4_1 tr:nth-child(5) td:nth-child(1) textarea").val(data.model.department1advice);
                        $("#model4_1 tr:nth-child(4) td:nth-child(2) input").val(data.model.department2name);
                        $("#model4_1 tr:nth-child(5) td:nth-child(2) textarea").val(data.model.department2advice);
                    }
                }
            });
            $("#model_info").css("display","block");
            $("#model_container_1").css("display","block");
            $('#model_handle').modal('show');
            $("#handle_people li:first-child").css("display","none");
            $("#handle_people li:nth-child(2)").css("display","none");
            $("#model_handle input").attr("readonly",true);
            $("#model_handle textarea").attr("readonly",true);
            if(mydata1.status == "科室签批"){
                if(mydata1.modeltype == "一科室提意见"){
                    $("#model3_1 tr:nth-child(5) td:nth-child(1) textarea").attr("readonly",false);
                    $("#handle_people li:first-child").css("display","block");
                }else if(mydata1.modeltype == "两科室提意见"){
                    $("#model4_1 tr:nth-child(5) td:nth-child(1) textarea").attr("readonly",false);
                    $("#model4_1 tr:nth-child(5) td:nth-child(2) textarea").attr("readonly",false);
                    $("#handle_people li:first-child").css("display","block");
                    $("#handle_people li:nth-child(2)").css("display","block");
                }
                step1.goStep(3);
                step.goStep(3);
            }else if(mydata1.status == "分管领导签批"){
                $("#model1_1 tr:nth-child(4) td:nth-child(2) textarea").attr("readonly",false);
                $("#model2_1 tr:nth-child(4) td:nth-child(1) textarea").attr("readonly",false);
                $("#model3_1 tr:nth-child(7) td:nth-child(2) textarea").attr("readonly",false);
                $("#model4_1 tr:nth-child(7) td:nth-child(2) textarea").attr("readonly",false);
                step1.goStep(3);
                step.goStep(3);
            }else if(mydata1.status == "主管领导签批"){
                $("#model1_1 tr:nth-child(5) td:nth-child(2) textarea").attr("readonly",false);
                $("#model2_1 tr:nth-child(3) td:nth-child(1) textarea").attr("readonly",false);
                $("#model3_1 tr:nth-child(6) td:nth-child(2) textarea").attr("readonly",false);
                $("#model4_1 tr:nth-child(6) td:nth-child(2) textarea").attr("readonly",false);
                step1.goStep(3);
                step.goStep(3);
            }else if(mydata1.status == "处理处置"){
                $("#model1_1 tr:nth-child(6) td:nth-child(2) textarea").attr("readonly",false);
                $("#model2_1 tr:nth-child(6) td:nth-child(1) textarea").attr("readonly",false);
                $("#model3_1 tr:nth-child(8) td:nth-child(2) textarea").attr("readonly",false);
                $("#model4_1 tr:nth-child(8) td:nth-child(2) textarea").attr("readonly",false);
                step1.goStep(4);
                step.goStep(4);
            }else if(mydata1.status == "办公室归档"){
                step1.goStep(5);
                step.goStep(5);
                $("#model_handle .btn-success").css("display","inline-block");
            }else if(mydata1.status == "结束"){
                step1.goStep(6);
                step.goStep(6);
                $("#model_handle .btn-success").css("display","inline-block");
                if(model_name == "直接处理"){
                    $("#print1 tr:first-child td").text(fina_data.model.filename);
                    $("#print1 tr:nth-child(2) td:nth-child(2) input").text(fina_data.model.receivefilenum);
                    $("#print1 tr:nth-child(2) td:nth-child(4) input").text(fina_data.model.comefiledepartment);
                    $("#print1 tr:nth-child(2) td:nth-child(6) input").text(fina_data.model.comefilenum);
                    $("#print1 tr:nth-child(2) td:nth-child(8) input").text(fina_data.model.urgency);
                    $("#print1 tr:nth-child(2) td:nth-child(10) input").text(fina_data.model.secret);
                    $("#print1 tr:nth-child(2) td:nth-child(12) input").text(fina_data.model.copys);
                    $("#print1 tr:nth-child(3) td:nth-child(2)").text(fina_data.model.filetitle);
                    $("#print1 tr:nth-child(4) td:nth-child(2)").text(fina_data.model.suggestion);
                    $("#print1 tr:nth-child(5) td:nth-child(2)").text(fina_data.model.branchleaderinstruction);
                    $("#print1 tr:nth-child(6) td:nth-child(2)").text(fina_data.model.mainleaderinstruction);
                    $("#print1 tr:nth-child(7) td:nth-child(2)").text(fina_data.model.result);
                }else if(model_name == "文件拟办单"){
                    $("#print2 tr:first-child td").text(fina_data.model.filename);
                    $("#print2 tr:nth-child(2) td:nth-child(2)").text(fina_data.model.dispatchfiledepartment);
                    $("#print2 tr:nth-child(2) td:nth-child(4)").text(fina_data.model.filenum);
                    $("#print2 tr:nth-child(2) td:nth-child(6)").text(fina_data.model.receivefileregisterid);
                    $("#print2 tr:nth-child(2) td:nth-child(8)").text(fina_data.model.receivefiledate);
                    $("#print2 tr:nth-child(3) td:nth-child(2)").text(fina_data.model.filetitle);
                    $("#print2 tr:nth-child(6) td p:nth-child(2)").text(fina_data.model.suggestion);
                    $("#print2 tr:nth-child(4) td p:nth-child(2)").text(fina_data.model.mainleaderinstruction);
                    $("#print2 tr:nth-child(5) td p:nth-child(2)").text(fina_data.model.branchleaderinstruction);
                    $("#print2 tr:nth-child(7) td p:nth-child(2)").text(fina_data.model.result);
                }else if(model_name == "一科室提意见"){
                    $("#print3 tr:first-child td").text(fina_data.model.filename);
                    $("#print3 tr:nth-child(2) td:nth-child(2)").text(fina_data.model.receivefilenum);
                    $("#print3 tr:nth-child(2) td:nth-child(4)").text(fina_data.model.comefiledepartment);
                    $("#print3 tr:nth-child(2) td:nth-child(6)").text(fina_data.model.comefilenum);
                    $("#print3 tr:nth-child(2) td:nth-child(8)").text(fina_data.model.urgency);
                    $("#print3 tr:nth-child(2) td:nth-child(10)").text(fina_data.model.secret);
                    $("#print3 tr:nth-child(2) td:nth-child(12)").text(fina_data.model.copys);
                    $("#print3 tr:nth-child(3) td:nth-child(2)").text(fina_data.model.filetitle);
                    $("#print3 tr:nth-child(4) td:nth-child(2)").text(fina_data.model.suggestion);
                    $("#print3 tr:nth-child(7) td:nth-child(2)").text(fina_data.model.mainleaderinstruction);
                    $("#print3 tr:nth-child(8) td:nth-child(2)").text(fina_data.model.branchleaderinstruction);
                    $("#print3 tr:nth-child(9) td:nth-child(2)").text(fina_data.model.result);
                    $("#print3 tr:nth-child(5) td:nth-child(1)").text(fina_data.model.department);
                    $("#print3 tr:nth-child(6) td:nth-child(1)").text(fina_data.model.departmentadvice);
                }else if(model_name == "两科室提意见"){
                    $("#print4 tr:first-child td").text(fina_data.model.filename);
                    $("#print4 tr:nth-child(2) td:nth-child(2)").text(fina_data.model.receivefilenum);
                    $("#print4 tr:nth-child(2)  td:nth-child(4)").text(fina_data.model.comefiledepartment);
                    $("#print4 tr:nth-child(2)  td:nth-child(6)").text(fina_data.model.comefilenum);
                    $("#print4 tr:nth-child(2)  td:nth-child(8)").text(fina_data.model.urgency);
                    $("#print4 tr:nth-child(2)  td:nth-child(10)").text(fina_data.model.secret);
                    $("#print4 tr:nth-child(2)  td:nth-child(12)").text(fina_data.model.copys);
                    $("#print4 tr:nth-child(3) td:nth-child(2)").text(fina_data.model.filetitle);
                    $("#print4 tr:nth-child(4) td:nth-child(2)").text(fina_data.model.suggestion);
                    $("#print4 tr:nth-child(7) td:nth-child(2)").text(fina_data.model.mainleaderinstruction);
                    $("#print4 tr:nth-child(8) td:nth-child(2)").text(fina_data.model.branchleaderinstruction);
                    $("#print4 tr:nth-child(9) td:nth-child(2)").text(fina_data.model.result);
                    $("#print4 tr:nth-child(5) td:nth-child(1)").text(fina_data.model.department1name);
                    $("#print4 tr:nth-child(6) td:nth-child(1)").text(fina_data.model.department1advice);
                    $("#print4 tr:nth-child(5) td:nth-child(2)").text(fina_data.model.department2name);
                    $("#print4 tr:nth-child(6) td:nth-child(2)").text(fina_data.model.department2advice);
                }
            }
            if(kind == "查看"){
                $('#model_handle .btn-primary').css('display','none');
            }else if(kind == "编辑"){
                $('#model_handle .btn-primary').css('display','inline-block');
            }
        }

    }

    //打印功能
    $("#model_handle .btn-success").click(function () {
        if(model_name == "直接处理"){
            jQuery('#print1').print();
        }else if(model_name == "文件拟办单"){
            jQuery('#print2').print();
        }else if(model_name == "一科室提意见"){
            jQuery('#print3').print();
        }else if(model_name == "两科室提意见"){
            jQuery('#print4').print();
        }
    });

    //办公室选择模版及信息提交
    var b_flag = true;
    $("#select_model .btn-primary").click(function(){
        if(b_flag){
            var receivefileid = $("#receivefileid").text();
            var model = $("#sel1").val();
            var keshi1 = $("#keshi1").val();
            var keshi2 = $("#keshi2").val();
            var fenguan = $("#fenguan").val();
            var zhuguan = $("#zhuguan").val();
            var banli = $("#banli").val();
            var people_list = new Object();
            if(keshi1){
                people_list.department1 = keshi1;
            }
            if(keshi2){
                people_list.department2 = keshi2;
            }
            if(fenguan){
                people_list.branch_leader = fenguan;
            }
            if(zhuguan){
                people_list.main_leader = zhuguan;
            }
            if(banli){
                people_list.transactor = banli;
            }
            var text = new Object();
            if(model == "直接处理"){
                if(!fenguan || !zhuguan || !banli){
                    alert("请选择处理人");
                    return;
                }
                text.filename = $("#model1 .title input").val();
                text.receivefilenum = $("#model1 tr:first-child td:nth-child(2) input").val();
                text.comefiledepartment = $("#model1 tr:first-child td:nth-child(4) input").val();
                text.comefilenum = $("#model1 tr:first-child td:nth-child(6) input").val();
                text.urgency = $("#model1 tr:first-child td:nth-child(8) input").val();
                text.secret = $("#model1 tr:first-child td:nth-child(10) input").val();
                text.copys = $("#model1 tr:first-child td:nth-child(12) input").val();
                text.filetitle = $("#model1 tr:nth-child(2) td:nth-child(2) input").val();
                text.suggestion = $("#model1 tr:nth-child(3) td:nth-child(2) textarea").val();
                text.branchleaderinstruction = $("#model1 tr:nth-child(4) td:nth-child(2) textarea").val();
                text.mainleaderinstruction = $("#model1 tr:nth-child(5) td:nth-child(2) textarea").val();
                text.result = $("#model1 tr:nth-child(6) td:nth-child(2) textarea").val();
                if(!text.receivefilenum){
                    alert("收文号不能为空");
                    return;
                }else if(!text.comefiledepartment){
                    alert("来文机关不能为空");
                    return;
                }else if(!text.comefilenum){
                    alert("来文号不能为空");
                    return;
                }else if(!text.filetitle){
                    alert("文件标题不能为空");
                    return;
                }else if(!text.suggestion){
                    alert("拟办意见不能为空");
                    return;
                }
            }else if(model == "文件拟办单"){
                if(!fenguan || !zhuguan || !banli){
                    alert("请选择处理人");
                    return;
                }
                text.filename = $("#model2 .title input").val();
                text.dispatchfiledepartment = $("#model2 tr:first-child td:nth-child(2) input").val();
                text.filenum = $("#model2 tr:first-child td:nth-child(4) input").val();
                text.receivefileregisterid = $("#model2 tr:first-child td:nth-child(6) input").val();
                text.receivefiledate = $("#model2 tr:first-child td:nth-child(8) input").val();
                text.filetitle = $("#model2 tr:nth-child(2) td:nth-child(2) textarea").val();
                text.suggestion = $("#model2 tr:nth-child(5) td textarea").val();
                text.mainleaderinstruction = $("#model2 tr:nth-child(3) td:nth-child(2) textarea").val();
                text.branchleaderinstruction = $("#model2 tr:nth-child(4) td:nth-child(2) textarea").val();
                text.result = $("#model2 tr:nth-child(6) td:nth-child(2) textarea").val();
                if(!text.dispatchfiledepartment){
                    alert("发文单位不能为空");
                    return;
                }else if(!text.filenum){
                    alert("文件字号不能为空");
                    return;
                }else if(!text.receivefileregisterid){
                    alert("收文登记号不能为空");
                    return;
                }else if(!text.receivefiledate){
                    alert("收文日期不能为空");
                    return;
                }else if(!text.filetitle){
                    alert("文件标题不能为空");
                    return;
                }else if(!text.suggestion){
                    alert("拟办意见不能为空");
                    return;
                }
            }else if(model == "一科室提意见"){
                if(!keshi1 || !fenguan || !zhuguan || !banli){
                    alert("请选择处理人");
                    return;
                }
                text.filename = $("#model3 .title input").val();
                text.receivefilenum = $("#model3 tr:first-child td:nth-child(2) input").val();
                text.comefiledepartment = $("#model3 tr:first-child td:nth-child(4) input").val();
                text.comefilenum = $("#model3 tr:first-child td:nth-child(6) input").val();
                text.urgency = $("#model3 tr:first-child td:nth-child(8) input").val();
                text.secret = $("#model3 tr:first-child td:nth-child(10) input").val();
                text.copys = $("#model3 tr:first-child td:nth-child(12) input").val();
                text.filetitle = $("#model3 tr:nth-child(2) td:nth-child(2) textarea").val();
                text.suggestion = $("#model3 tr:nth-child(5) td:nth-child(1) textarea").val();
                text.mainleaderinstruction = $("#model3 tr:nth-child(6) td:nth-child(2) textarea").val();
                text.branchleaderinstruction = $("#model3 tr:nth-child(7) td:nth-child(2) textarea").val();
                text.result = $("#model3 tr:nth-child(8) td:nth-child(2) textarea").val();
                text.department = $("#model3 tr:nth-child(4) td:nth-child(1) input").val();
                text.departmentadvice = $("#model3 tr:nth-child(5) td:nth-child(1) textarea").val();
                if(!text.receivefilenum){
                    alert("收文号不能为空");
                    return;
                }else if(!text.comefiledepartment){
                    alert("收文单位不能为空");
                    return;
                }else if(!text.comefilenum){
                    alert("来文号不能为空");
                    return;
                }else if(!text.filetitle){
                    alert("文件标题不能为空");
                    return;
                }else if(!text.suggestion){
                    alert("拟办意见不能为空");
                    return;
                }
            }else if(model == "两科室提意见"){
                if(!keshi2 || !keshi1 || !fenguan || !zhuguan || !banli){
                    alert("请选择处理人");
                    return;
                }
                text.filename = $("#model4 .title input").val();
                text.receivefilenum = $("#model4 tr:first-child td:nth-child(2) input").val();
                text.comefiledepartment = $("#model4 tr:first-child td:nth-child(4) input").val();
                text.comefilenum = $("#model4 tr:first-child td:nth-child(6) input").val();
                text.urgency = $("#model4 tr:first-child td:nth-child(8) input").val();
                text.secret = $("#model4 tr:first-child td:nth-child(10) input").val();
                text.copys = $("#model4 tr:first-child td:nth-child(12) input").val();
                text.filetitle = $("#model4 tr:nth-child(2) td:nth-child(2) textarea").val();
                text.suggestion = $("#model4 tr:nth-child(5) td:nth-child(1) textarea").val();
                text.mainleaderinstruction = $("#model4 tr:nth-child(6) td:nth-child(2) textarea").val();
                text.branchleaderinstruction = $("#model4 tr:nth-child(7) td:nth-child(2) textarea").val();
                text.result = $("#model4 tr:nth-child(8) td:nth-child(2) textarea").val();
                text.department1name = $("#model4 tr:nth-child(4) td:nth-child(1) input").val();
                text.department1advice = $("#model4 tr:nth-child(5) td:nth-child(1) textarea").val();
                text.department2name = $("#model4 tr:nth-child(4) td:nth-child(2) input").val();
                text.department2advice = $("#model4 tr:nth-child(5) td:nth-child(2) textarea").val();
                if(!text.receivefilenum){
                    alert("收文号不能为空");
                    return;
                }else if(!text.comefiledepartment){
                    alert("收文单位不能为空");
                    return;
                }else if(!text.comefilenum){
                    alert("来文号不能为空");
                    return;
                }else if(!text.filetitle){
                    alert("文件标题不能为空");
                    return;
                }else if(!text.suggestion){
                    alert("拟办意见不能为空");
                    return;
                }
            }
            console.log(text);
            b_flag = false;
            people_list = JSON.stringify(people_list);
            text = JSON.stringify(text);
            console.log(people_list);
            console.log(text);
            console.log(receivefileid);
            $.ajax({
                url: "/saveReceiveFileModelInfo.do",
                type: "post",
                data: {modelname:model,people_list:people_list,text:text,receivefileid:receivefileid},
                dataType:"json",
                success: function (data) {
                    console.log(data);
                    if(data.result == "success"){
                        $('#select_model').modal('hide');
                        table_refresh();
                        setTimeout(acount,100);
                        alert("提交成功");
                        b_flag = true;
                    }else {
                        alert("提交失败");
                        b_flag = true;
                    }
                }
            })
        }
    })

    //提交
    var t_flag = true;
    $("#model_handle .btn-primary").click(function () {
        if(t_flag){
            if(state == "办公室归档"){
                console.log(id);
                $.ajax({
                    url: "/toConfirm.do",
                    type: "post",
                    data: {receivefileid:id},
                    dataType: "json",
                    success: function(data){
                        if(data.result == "success"){
                            alert("归档成功");
                            $('#model_handle').modal('hide');
                            $("#model_handle input").val("");
                            $("#model_handle textarea").val("");
                            $("#model_handle .title").val("临沧市移民局文件处理笺");
                            table_refresh();
                            setTimeout(acount,100);
                        }
                    }
                })
            }else {
                var text = new Object();
                text.departmentadvice = "";
                text.department1advice = "";
                text.department2advice = "";
                text.branchleaderinstruction = "";
                text.mainleaderinstruction = "";
                text.result = "";
                if(mydata1.status == "科室签批"){
                    if(mydata1.modeltype == "一科室提意见"){
                        text.departmentadvice = $("#model3_1 tr:nth-child(5) td:nth-child(1) textarea").val();
                        if(!text.departmentadvice){
                            alert("科室意见不能为空");
                            return;
                        }
                    }else if(mydata1.modeltype == "两科室提意见"){
                        text.department1advice = $("#model4_1 tr:nth-child(5) td:nth-child(1) textarea").val();
                        text.department2advice = $("#model4_1 tr:nth-child(5) td:nth-child(2) textarea").val();
                        if(!text.department1advice && !text.department2advice){
                            alert("科室意见不能为空");
                            return;
                        }
                    }
                }else if(mydata1.status == "分管领导签批"){
                    if(mydata1.modeltype == "直接处理"){
                        text.branchleaderinstruction = $("#model1_1 tr:nth-child(4) td:nth-child(2) textarea").val();
                    }else if(mydata1.modeltype == "文件拟办单"){
                        text.branchleaderinstruction = $("#model2_1 tr:nth-child(4) td:nth-child(1) textarea").val();
                    }else if(mydata1.modeltype == "一科室提意见"){
                        text.branchleaderinstruction = $("#model3_1 tr:nth-child(7) td:nth-child(2) textarea").val();
                    }else if(mydata1.modeltype == "两科室提意见"){
                        text.branchleaderinstruction = $("#model4_1 tr:nth-child(7) td:nth-child(2) textarea").val();
                    }
                    if(!text.branchleaderinstruction){
                        alert("分管领导签批不能为空");
                        return;
                    }
                }else if(mydata1.status == "主管领导签批"){
                    if(mydata1.modeltype == "直接处理"){
                        text.mainleaderinstruction = $("#model1_1 tr:nth-child(5) td:nth-child(2) textarea").val();
                    }else if(mydata1.modeltype == "文件拟办单"){
                        text.mainleaderinstruction = $("#model2_1 tr:nth-child(3) td:nth-child(1) textarea").val();
                    }else if(mydata1.modeltype == "一科室提意见"){
                        text.mainleaderinstruction = $("#model3_1 tr:nth-child(6) td:nth-child(2) textarea").val();
                    }else if(mydata1.modeltype == "两科室提意见"){
                        text.mainleaderinstruction = $("#model4_1 tr:nth-child(6) td:nth-child(2) textarea").val();
                    }
                    if(!text.mainleaderinstruction){
                        alert("主管领导签批不能为空");
                        return;
                    }
                }else if(mydata1.status == "处理处置"){
                    if(mydata1.modeltype == "直接处理"){
                        text.result = $("#model1_1 tr:nth-child(6) td:nth-child(2) textarea").val();
                    }else if(mydata1.modeltype == "文件拟办单"){
                        text.result = $("#model2_1 tr:nth-child(6) td:nth-child(1) textarea").val();
                    }else if(mydata1.modeltype == "一科室提意见"){
                        text.result = $("#model3_1 tr:nth-child(8) td:nth-child(2) textarea").val();
                    }else if(mydata1.modeltype == "两科室提意见"){
                        text.result = $("#model4_1 tr:nth-child(8) td:nth-child(2) textarea").val();
                    }
                    if(!text.result){
                        alert("处理处置不能为空");
                        return;
                    }

                }
                console.log(text,mydata1);
                var text2 = JSON.stringify(text);
                var mydata2 = JSON.stringify(mydata1);
                t_flag = false;
                $.ajax({
                    url: "/updateReceiveFileAndModelInfo.do",
                    type: "post",
                    data: {text:text2,receivedata:mydata2},
                    dataType: "json",
                    success: function (data) {
                        if(data.result == "success"){
                            $('#model_handle').modal('hide');
                            $("#model_handle input").val("");
                            $("#model_handle textarea").val("");
                            $("#model_handle .title").val("临沧市移民局文件处理笺");
                            table_refresh();
                            setTimeout(acount,100);
                            alert("提交成功");
                            t_flag = true;
                        }else {
                            alert("提交失败");
                            t_flag = true;
                        }
                    }
                })
            }
        }

    })







</script>
</body>
</html>