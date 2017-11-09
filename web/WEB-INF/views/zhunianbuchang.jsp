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
    <link rel="stylesheet" href="../../css/money.css">
    <link rel="stylesheet" href="../../css/mycommon.css">
    <style>
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
        .mytable{
            width: 100%;
            border-top: 1px solid #000;
            border-left: 1px solid #000;
        }
        .mytable td{
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
            line-height: 30px;
            padding: 0 5px;
        }
        .mytable input{
            outline: none;
            border: none;
            width: 100%;
            padding: 0 5px;
        }
        .mytable textarea{
            outline: none;
            border: none;
            width: 100%;
            height:120px;
            padding: 0 5px;
            resize: none;
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
        <a class="navbar-brand" href="/tohome.htm" style="width: 300px;"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
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
                        <li id="header1" class="nav-header">我的申请</li>
                        <li id="m_apply1"><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span id="kind1">市局资金计划上报</span></a></li>

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

                                                <div id="kind2">市局资金计划上报</div>

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
                                                    <th>编号</th>
                                                    <th>流程类型</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>发起人</th>
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
                                                    <th>流程类型</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>发起人</th>
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
                                                    <th>流程类型</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>发起人</th>
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

    <div class="modal fade" id="money_apply_wdo1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" id="close_stuff" data-dismiss="modal">×</button>
                    <h3>填写表单</h3>
                </div>
                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm1" action="" method="post" enctype="multipart/form-data"  target="uploadFrame">
                    <div class="modal-body">
                        <table class="mytable ghapply">
                            <tr>
                                <td>标题</td>
                                <td colspan="3"><input type="text"></td>
                            </tr>
                            <tr>
                                <td>上报人</td>
                                <td><input type="text"></td>
                                <td>上报时间</td>
                                <td><input type="text" id="time1" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td>上报文件</td>
                                <td colspan="3"><div id="filesUpload" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" id="add_1" onclick="add_click_file(1)">添加附件</a>
                                    <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                </div></td>
                            </tr>
                            <tr>
                                <td colspan="4"><textarea name="" placeholder="上报内容"></textarea></td>
                            </tr>
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

    <div class="modal fade" id="caiwu_handle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" id="close_stuff1" data-dismiss="modal">×</button>
                    <h3></h3>
                </div>
                    <div class="modal-body">
                        <table class="mytable ghapply">
                            <tr>
                                <td>标题</td>
                                <td colspan="3"><input type="text"></td>
                            </tr>
                            <tr>
                                <td>上报人</td>
                                <td><input type="text"></td>
                                <td>上报时间</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td>下载</td>
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td colspan="4"><textarea></textarea></td>
                            </tr>
                            <iframe id="uploadFrame2" name="uploadFrame" style="display:none;"></iframe>
                            <form id = "fileForm2" action="" method="post" enctype="multipart/form-data"  target="uploadFrame2">
                                <table class="mytable" style="border-top: none;">
                                        <tr>
                                            <td>款项来源</td>
                                            <td><input type="text"></td>
                                            <td>到款时间</td>
                                            <td><input type="text" id="time2" readonly="readonly"></td>
                                        </tr>
                                        <tr>
                                            <td>到款金额</td>
                                            <td><input type="text"></td>
                                            <td>上传附件</td>
                                            <td><div id="myfilesUpload" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                                <a href="#" id="myadd_1" onclick="myadd_click_file(1)">添加附件</a>
                                                <input style="display:none;" id="myadd_file_1" type="file" name = "files" onChange="myadd(1)"/>
                                            </div></td>
                                        </tr>
                                    </table>
                            </form>
                        </table>
                    </div>

                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="guihua_handle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3></h3>
                </div>
                <div class="modal-body">
                    <table class="mytable ghapply">
                        <tr>
                            <td>标题</td>
                            <td colspan="3"><input type="text"></td>
                        </tr>
                        <tr>
                            <td>上报人</td>
                            <td><input type="text"></td>
                            <td>上报时间</td>
                            <td><input type="text"></td>
                        </tr>
                        <tr>
                            <td>下载附件</td>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td colspan="4"><textarea></textarea></td>
                        </tr>
                        <tr>
                            <td>款项来源</td>
                            <td><input type="text"></td>
                            <td style="width: 80px;">到款时间</td>
                            <td><input type="text"></td>
                        </tr>
                        <tr>
                            <td>到款金额</td>
                            <td><input type="text"></td>
                            <td>下载附件</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>通知区县</td>
                            <td colspan="3">
                                <ul class="ui-choose" multiple="multiple" id="uc_03" style="margin: 10px;">
                                    <li>临翔区</li>
                                    <li>凤庆县</li>
                                    <li>永德县</li>
                                    <li>镇康县</li>
                                    <li>云县</li>
                                    <li>沧源佤族自治县</li>
                                    <li>耿马傣族佤族自治县</li>
                                    <li>双江拉祜族佤族布朗族傣族自治县</li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><textarea placeholder="通知内容"></textarea></td>
                        </tr>
                    </table>
                </div>

                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="final_handle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3></h3>
                </div>
                <div class="modal-body">
                    <table class="mytable ghapply">
                        <tr>
                            <td>标题</td>
                            <td colspan="3"><input type="text"></td>
                        </tr>
                        <tr>
                            <td>上报人</td>
                            <td><input type="text"></td>
                            <td>上报时间</td>
                            <td><input type="text"></td>
                        </tr>
                        <tr>
                            <td>下载附件</td>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td colspan="4"><textarea></textarea></td>
                        </tr>
                        <tr>
                            <td>款项来源</td>
                            <td><input type="text"></td>
                            <td style="width: 80px;">到款时间</td>
                            <td><input type="text"></td>
                        </tr>
                        <tr>
                            <td>到款金额</td>
                            <td><input type="text"></td>
                            <td>下载附件</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>通知区县</td>
                            <td colspan="3">
                                <input type="text">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><textarea></textarea></td>
                        </tr>
                    </table>
                </div>

                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="money_apply_wdo2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>填写表单</h3>
                </div>
                <iframe id="uploadFrame3" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm3" action="" method="post" enctype="multipart/form-data"  target="uploadFrame3">
                    <div class="modal-body">
                        <table class="mytable ghapply">
                            <tr>
                                <td>标题</td>
                                <td colspan="3"><input type="text"></td>
                            </tr>
                            <tr>
                                <td>申请人</td>
                                <td><input type="text"></td>
                                <td>申请原因</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td>上传附件</td>
                                <td colspan="3"><div id="my1filesUpload" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" id="my1add_1" onclick="my1add_click_file(1)">添加附件</a>
                                    <input style="display:none;" id="my1add_file_1" type="file" name = "files" onChange="my1add(1)"/>
                                </div></td>
                            </tr>
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

    <div class="modal fade" id="final_handle2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>填写表单</h3>
                </div>
                <div class="modal-body">
                    <table class="mytable ghapply">
                        <tr>
                            <td>标题</td>
                            <td colspan="3"><input type="text"></td>
                        </tr>
                        <tr>
                            <td>申请人</td>
                            <td><input type="text"></td>
                            <td>申请原因</td>
                            <td><input type="text"></td>
                        </tr>
                        <tr>
                            <td>上传附件</td>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td>批复意见</td>
                            <td colspan="3"><textarea></textarea></td>
                        </tr>
                        <tr>
                            <td>处理内容</td>
                            <td colspan="3"><textarea></textarea></td>
                        </tr>
                        <tr>
                            <td>资金流向</td>
                            <td colspan="3"><textarea></textarea></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>

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
<script>
    //checkbox美化
    $('.ui-choose').ui_choose();
    var uc_03 = $('#uc_03').data('ui-choose');
    //日期插件

    $("#time1").jeDate({
        format: "YYYY-MM-DD"
    });
    $("#arrival_time").jeDate({
        format: "YYYY-MM-DD"
    });

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

    // 多文件上传
    var myfileIndex = 1;
    function myadd_click_file(index){
        $("#myadd_file_"+myfileIndex).click();
    }

    function myadd(index) {
        /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
        var len = $("#myadd_file_" + (myfileIndex) + "").val().split("\\").length;
        var num = $("#myadd_file_" + (myfileIndex) + "").val().split("\\")[len - 1];
        $("#myfilesUpload").append('<span  id="myadd_file_span_' + (myfileIndex) + '"  class="add_file">' + $("#myadd_file_" + (myfileIndex) + "").val().split("\\")[len - 1] + '</span>');
        $("#myfilesUpload").append('<a   id="myadd_file_a_' + (myfileIndex) + '"  class="add_file" href="javascript:mydel_file(' + myfileIndex+ ')">删除</a>');
        $("#myfilesUpload").append('<input style="display:none;" id="myadd_file_' + (myfileIndex + 1) + '" type="file" name = "files" onChange="myadd(' + (myfileIndex + 1) + ')"/>');
        ++myfileIndex;
    }

    function mydel_file(number) {
        var o=document.getElementById("myfilesUpload");//获取父节点
        var int=document.getElementById("myadd_file_" + number+"");//获取需要删除的子节点
        var a=document.getElementById("myadd_file_a_" + number+"");//获取需要删除的子节点
        var span=document.getElementById("myadd_file_span_" + number+"");//获取需要删除的子节点
        o.removeChild(int); //从父节点o上面移除子节点a
        o.removeChild(a);
        o.removeChild(span)
    }

    // 多文件上传
    var my1fileIndex = 1;
    function my1add_click_file(index){
        $("#my1add_file_"+my1fileIndex).click();
    }

    function my1add(index) {
        /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
        var len = $("#my1add_file_" + (my1fileIndex) + "").val().split("\\").length;
        var num = $("#my1add_file_" + (my1fileIndex) + "").val().split("\\")[len - 1];
        $("#my1filesUpload").append('<span  id="my1add_file_span_' + (my1fileIndex) + '"  class="add_file">' + $("#my1add_file_" + (my1fileIndex) + "").val().split("\\")[len - 1] + '</span>');
        $("#my1filesUpload").append('<a   id="my1add_file_a_' + (my1fileIndex) + '"  class="add_file" href="javascript:my1del_file(' + my1fileIndex+ ')">删除</a>');
        $("#my1filesUpload").append('<input style="display:none;" id="my1add_file_' + (my1fileIndex + 1) + '" type="file" name = "files" onChange="my1add(' + (my1fileIndex + 1) + ')"/>');
        ++my1fileIndex;
    }

    function my1del_file(number) {
        var o=document.getElementById("my1filesUpload");//获取父节点
        var int=document.getElementById("my1add_file_" + number+"");//获取需要删除的子节点
        var a=document.getElementById("my1add_file_a_" + number+"");//获取需要删除的子节点
        var span=document.getElementById("my1add_file_span_" + number+"");//获取需要删除的子节点
        o.removeChild(int); //从父节点o上面移除子节点a
        o.removeChild(a);
        o.removeChild(span)
    }



    //资金申请
    var money_apply1 = $('#money_apply1').DataTable({
        ajax: {
            url: "/capitalFlowForm.do?userstatus=1",
            async:false
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "id"},
            {"data": "initiatorclass"},
            {"data": "title"},
            {"data": "create_time"},
            {"data": "report_person"},
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
    var sta1 = "市局规划科处理中";
    var dcl_table = $('#dcl_table').DataTable({
        ajax: {
            url: "/pendingCapitalFlow.do?capitalstatus="+ encodeURI(encodeURI(sta1)),
            async:false
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "id"},
            {"data": "initiatorclass"},
            {"data": "title"},
            {"data": "create_time"},
            {"data": "report_person"},
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
    var sta2 = "已通知区县";
    var ycl_table = $('#ycl_table').DataTable({
        ajax: {
            url: "/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2)),
            async:false
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "id"},
            {"data": "initiatorclass"},
            {"data": "title"},
            {"data": "create_time"},
            {"data": "report_person"},
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

    //待办事务的显示条数
    function acount() {
        var info = dcl_table.page.info();
        $("#nav_num").text(info.recordsTotal)
    }
    setTimeout(acount,100);

    //表格刷新
    //    function table_refresh() {
    //        money_apply1.ajax.url("/receiveFileDataTable.do").load();
    //        dcl_table.ajax.url("/receiveFileDataTableByNameAndStatus.do").load();
    //        ycl_table.ajax.url("/receiveFileDataTableByNameAndStatusHave.do").load();
    //    }
    var status;
    ~function() {

        status = $("#status").text();

    }();





    //上报提交
    $("#money_apply_wdo1 .btn-primary").click(function () {
//        var options  = {
//            url:'reveiceFileRegistration.do',
//            type:'post',
//            success:function(data)
//            {
//                console.log(data);
//                if(data.result == "success"){
//                    alert("提交成功");
//                    $('#money_apply_wdo1').modal('hide');
//                    $("#money_apply_wdo1 input").val("");
//                    $("#money_apply_wdo1 textarea").val("");
//                }else {
//                    alert(data.result);
//                }
//            }
//        };
//        $("#fileForm1").ajaxSubmit(options);
    });



    function newForm() {
        $('#money_apply_wdo1 input').val("");
        $('#money_apply_wdo1 textarea').val("");
        $.each($("#filesUpload a"),function (i,n) {
            if(n.text != "添加附件"){
                n.remove()
            }
        })
        $("#filesUpload span").remove();
        $('#money_apply_wdo1').modal('show');
//        $('#caiwu_handle').modal('show');
//        $('#guihua_handle').modal('show');
//        $('#final_handle').modal('show');
//        $('#money_apply_wdo2').modal('show');
//        $('#final_handle2').modal('show');
    }





    function detail_office(that) {
        $('#form_office').modal('show');
    }

    function flow(that){
        $('#flow').modal('show');
    }


</script>
</body>
</html>