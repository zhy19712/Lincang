<%--
  Created by IntelliJ IDEA.
  User: zhuangyf
  Date: 2017/8/17
  Time: 9:21
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
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="../../css/mycss.css">


    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>
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
        <a class="navbar-brand" href="oa.jsp"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
            <span>临沧市移民开发局</span></a>
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
                <span id="status" style="overflow: hidden;width:0;height:0;">${user.level}</span>
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
                        <li><a href="#home"><i class="glyphicon glyphicon-home"></i><span> 首页</span></a></li>

                        <li class="nav-header">我的表单</li>
                        <li><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span> 待办表单</span></a></li>
                        <li><a href="#progress1"><i class="glyphicon glyphicon-refresh"></i><span> 在办表单</span></a></li>
                        <li><a href="#completed1"><i class="glyphicon glyphicon-check"></i><span> 已办表单</span></a></li>

                        <li class="nav-header">我的事务</li>
                        <li><a href="#new2"><span class="notification red">12</span><i class="glyphicon glyphicon-tags"></i><span> 待办事务</span></a></li>
                        <li><a href="#progress2"><i class="glyphicon glyphicon-refresh"></i><span> 在办事务</span></a></li>
                        <li><a href="#completed2"><i class="glyphicon glyphicon-check"></i><span> 已归档事务</span></a></li>

                        <li class="nav-header">我的审批</li>
                        <li><a href="#new3"><i class="glyphicon glyphicon-tags"></i><span> 待审批表单</span><span class="notification red">3</span></a></li>
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
                    <div class="box-inner ">
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
                                                <a href="#">待办表单</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="填写新表单" class="well top-block"
                                               href="javascript:void(0)" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>

                                                <div>新建表单</div>

                                            </a>
                                        </div>

                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 待办列表</h2>
                                        </div>


                                        <div class="box-content">
                                            <table id="NewTable_Stuff" class="display" width="100%" cellspacing="0">
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
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 在办列表</h2>

                                        </div>


                                        <div class="box-content">
                                            <table id="SubmittedTable_Stuff" class="display" width="100%" cellspacing="0">
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
                                <div class="tab-pane" id="completed1">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的表单</a>
                                            </li>
                                            <li>
                                                <a href="#">已办表单</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 以办列表</h2>

                                        </div>


                                        <div class="box-content">
                                            <table id="OverTable_Stuff" class="display" width="100%" cellspacing="0">
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
                                                <a href="#">待办事务</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 待办列表</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="NewTable_Office" class="display" width="100%" cellspacing="0">
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
                                            <table id="SubmittedTable_Office" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>提交时间</th>
                                                    <th>缓级</th>
                                                    <th>报稿</th>
                                                    <th>标题</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                            </table>
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
                                            <table id="OverTable_Office" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>提交时间</th>
                                                    <th>缓级</th>
                                                    <th>报稿</th>
                                                    <th>标题</th>
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


                                    </div>



                                </div>
                                <div class="tab-pane" id="completed3">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的审批</a>
                                            </li>
                                            <li>
                                                <a href="#">审批记录</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 审批记录</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="OverTable_Approval" class="display" width="100%" cellspacing="0">
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




    <div class="modal fade" id="form_stuff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>填写表单</h3>
                </div>
                <div class="modal-body">
                    <div class="row myrow">
                        <div class="col-sm-5">
                            <span>拟稿单位</span>
                            <input id="input1" type="text">
                        </div>
                        <div class="col-sm-3">
                            <span>拟稿</span>
                            <input id="input2" type="text">
                        </div>
                        <div class="col-sm-4">
                            <span>科室核稿</span>
                            <input id="input3" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-sm-5">
                            <span>印刷</span>
                            <input id="input4" type="text">
                        </div>
                        <div class="col-sm-3">
                            <span>校对</span>
                            <input id="input5" type="text">
                        </div>
                        <div class="col-sm-4">
                            <span>份数</span>
                            <input id="input6" type="text">
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-sm-12">
                            <span>附件</span>

                            <form action="/file/multipleUpload.do" method="post"  enctype="multipart/form-data" style="width:80%;display: inline-block;text-overflow:ellipsis; white-space:nowrap; overflow:hidden;">

                                <div id="filesUpload">
                                    <a href="#" id="add_1" onclick="add_click_file(1)">添加附件</a>
                                    <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                </div>
                                <input type="button" id="Commit" style="display:none"/>
                            </form>

                            <%--<input id="input7" type="text" name="文件">--%>
                        </div>
                    </div>
                    <div class="row myrow">
                        <div class="col-sm-12">
                            <span>主题词</span>
                            <input id="input8" type="text">
                        </div>
                    </div>
                    <div class="row myrow last">
                        <div class="col-sm-12">
                            <span>标题</span>
                            <input id="input9" type="text">
                        </div>
                    </div>
                    <textarea class="mytext" name="" id="input10" cols="30" rows="10" placeholder="内容"></textarea>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-success" data-dismiss="modal">保存</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">提交</a>
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
                <canvas id="myCanvas" width="100%"></canvas>
                </div>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
                    <a href="#" class="btn btn-success" data-dismiss="modal">保存</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">提交</a>
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

    ~function() {

        var status=$("#status").text();

        if(status == 2){
            $("#myTab > li:gt(7)").remove();

        }else if(status == 3){
            $("#myTab > li:gt(4)").remove();

        }

    }();





    function newForm() {
        $('#form_stuff').modal('show');
    }




    function detail(that) {
        var oid = $(that).parents("tr").children("td:nth-child(1)").text();
        var title = $(that).parents("tr").children("td:nth-child(2)").text();
        var time = $(that).parents("tr").children("td:nth-child(3)").text();
        alert(oid);
        //   that.preventDefault();
        $('#form_stuff').modal('show');
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
<script type="text/javascript" language="javascript"
        src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="../../js/app.js"></script>
<script src="../../js/flowchart.js"></script>

<script src="../../js/oa.js"></script>
</body>
</html>

