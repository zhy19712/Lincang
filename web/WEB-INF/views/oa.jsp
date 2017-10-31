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
    <link rel="stylesheet" href="../../css/oa.css">

    <link rel="stylesheet" href="../../css/media.css" media="print">

    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery-form.min.js"></script>
    <!-- The fav icon -->
    <link rel="shortcut icon" href="../../img/favicon.ico">
    <style>
        .mytable{
            width: 100%;
            font-size: 12px;
            border-top: 1px solid red;
            border-left: 1px solid red;
            line-height: 30px;
            text-align: center;
        }
        .mytable td{
            border-right: 1px solid red;
            border-bottom: 1px solid red;
        }
        .mytable input{
            width: 100%;
            padding: 0 5px;
            outline: none;
            border: none;
        }
        .mytable textarea{
            border: none;
            resize: none;
            outline: none;
        }
        .red{
            color: red;
        }
    </style>
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
        <a class="navbar-brand" href="/tohome.htm" style="width: 300px;"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
            <span style="font-size: 26px">临沧市移民开发局</span></a>
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

                        <li class="nav-header">我的表单</li>
                        <li><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span> 申请表单</span></a></li>

                        <li class="nav-header">我的事务</li>
                        <li><a href="#new2"><span class="notification red" id="nav_num"></span><i class="glyphicon glyphicon-tags"></i><span> 待处理事务</span></a></li>
                        <li><a href="#progress2"><i class="glyphicon glyphicon-refresh"></i><span> 已处理事务</span></a></li>
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
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 全部列表</h2>
                                        </div>


                                        <div class="box-content">
                                            <table id="NewTable_Stuff" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>拟稿单位</th>
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
                                            <table id="NewTable_Office" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>拟稿单位</th>
                                                    <th>当前状态</th>
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
                                                <a href="#">已办事务</a>
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
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>拟稿单位</th>
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

    <div class="modal fade" id="form_stuff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" id="close_stuff" data-dismiss="modal">×</button>
                    <h3 id="form-kind">填写表单</h3>
                </div>

                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm" enctype="multipart/form-data"  target="uploadFrame">
                    <div class="modal-body" style="font-size: 0;width: 100%;" id="print1">
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td class="red">拟稿单位</td>
                                <td colspan="3"><input type="text" name="dept"></td>
                                <td class="red">拟稿</td>
                                <td><input type="text" name="author"></td>
                                <td class="red">科室核稿</td>
                                <td><input type="text" name="reviewer"></td>
                            </tr>
                            <tr>
                                <td class="red">印刷</td>
                                <td colspan="3"><input type="text" name="print"></td>
                                <td class="red">校对</td>
                                <td><input type="text" name="revision"></td>
                                <td class="red">份数</td>
                                <td><input type="text" name="copy"></td>
                            </tr>
                            <tr>
                                <td class="red">上传附件</td>
                                <td colspan="9">
                                    <div id="filesUpload" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                        <a href="#" id="add_1" onclick="add_click_file(1)">添加附件</a>
                                        <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="red">主题词</td>
                                <td colspan="7"><input type="text" name="keyword"></td>
                            </tr>
                            <tr>
                                <td class="red">标题</td>
                                <td colspan="7"><input type="text" name="title"></td>
                            </tr>
                            <tr>
                                <td colspan="8"><textarea name="content" placeholder="内容" cols="30" rows="10" style="width: 99%;"></textarea></td>
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

    <div class="modal fade" id="select_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>选择处理人</h3>
                </div>


                <div class="modal-body" style="font-size: 0;width: 100%;">
                    <div class="bgc_info">
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td class="red">临沧(</td>
                                <td><input type="text" name="dept"></td>
                                <td style="padding: 0 15px;">)号</td>
                                <td class="red" style="padding: 0 5px;">日期</td>
                                <td><input type="text" name="author"></td>
                                <td class="red" style="padding: 0 5px;">缓级</td>
                                <td><input type="text" name="reviewer"></td>
                                <td class="red" style="padding: 0 5px;">密级</td>
                                <td><input type="text" name="reviewer"></td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <p>签发</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                                <td colspan="2">
                                    <p>审稿</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                                <td colspan="2">
                                    <p>会签</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9">
                                    <p>抄报</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="red" style="width: 79px">抄送</td>
                                <td colspan="8"><input type="text" name="keyword"></td>
                            </tr>
                            <tr>
                                <td class="red" style="width: 79px;border-bottom: none;">发</td>
                                <td colspan="8" style="border-bottom: none;"><input type="text" name="title"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="fawen_info">
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td class="red">拟稿单位</td>
                                <td colspan="3"><input type="text" name="dept"></td>
                                <td class="red">拟稿</td>
                                <td><input type="text" name="author"></td>
                                <td class="red">科室核稿</td>
                                <td><input type="text" name="reviewer"></td>
                            </tr>
                            <tr>
                                <td class="red">印刷</td>
                                <td colspan="3"><input type="text" name="print"></td>
                                <td class="red">校对</td>
                                <td><input type="text" name="revision"></td>
                                <td class="red">份数</td>
                                <td><input type="text" name="copy"></td>
                            </tr>
                            <tr>
                                <td class="red">下载</td>
                                <td colspan="9"></td>
                            </tr>
                            <tr>
                                <td class="red">主题词</td>
                                <td colspan="7"><input type="text" name="keyword"></td>
                            </tr>
                            <tr>
                                <td class="red">标题</td>
                                <td colspan="7"><input type="text" name="title"></td>
                            </tr>
                            <tr>
                                <td colspan="8"><textarea name="content" placeholder="内容" cols="30" rows="10" style="width: 99%;"></textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>


                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#"  class="btn btn-primary">提交</a>
                </div>


            </div>
        </div>
    </div>

    <%--<div class="modal fade" id="form_office" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"--%>
         <%--aria-hidden="true">--%>
        <%--<div class="modal-dialog">--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal">×</button>--%>
                    <%--<h3>填写表单</h3>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>临移（）</span>--%>
                            <%--<input id="input_office1" type="text">--%>

                        <%--</div>--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>日期</span>--%>
                            <%--<input id="input_office2" type="text">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>缓级</span>--%>
                            <%--<input id="input_office3" type="text">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>密级</span>--%>
                            <%--<input id="input_office4" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>签发</span>--%>
                            <%--<input id="input_office5" type="text">--%>

                        <%--</div>--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>审稿</span>--%>
                            <%--<input id="input_office6" type="text">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-6">--%>
                            <%--<span>会签</span>--%>
                            <%--<input id="input_office7" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<span>抄报：</span>--%>
                            <%--<input id="input_office8" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<span>抄送：</span>--%>
                            <%--<input id="input_office9" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<span>发：</span>--%>
                            <%--<input id="input_office10" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-5">--%>
                            <%--<span>拟稿单位</span>--%>
                            <%--<input id="input_office11" type="text">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>拟稿</span>--%>
                            <%--<input id="input_office12" type="text">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-4">--%>
                            <%--<span>科室核稿</span>--%>
                            <%--<input id="input_office13" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-5">--%>
                            <%--<span>印刷</span>--%>
                            <%--<input id="input_office14" type="text">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-3">--%>
                            <%--<span>校对</span>--%>
                            <%--<input id="input_office15" type="text">--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-4">--%>
                            <%--<span>份数</span>--%>
                            <%--<input id="input_office16" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<span>附件</span>--%>
                            <%--<input id="input_office17" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<span>主题词</span>--%>
                            <%--<input id="input_office18" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row myrow last">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<span>标题</span>--%>
                            <%--<input id="input_office19" type="text">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<textarea class="mytext" name="" id="input_office20" cols="30" rows="10" placeholder="内容"></textarea>--%>



                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                    <%--<a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>--%>
                    <%--<a href="#" class="btn btn-success" data-dismiss="modal">保存</a>--%>
                    <%--<a href="#" class="btn btn-primary" data-dismiss="modal">提交</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>


    <%--<div class="modal fade" id="flow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"--%>
         <%--aria-hidden="true">--%>

        <%--<div class="modal-dialog">--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal">×</button>--%>
                    <%--<h3>公文流转</h3>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>

                    <%--<div id="wrap">--%>
                        <%--<div>--%>
                            <%--<img src="../../img/员工.png" class="head head_pic1" alt="员工">--%>
                            <%--<p class="staff status1" >申请人</p>--%>
                            <%--<div class="details details1">--%>
                                <%--<p>姓名：<span>小吴</span></p>--%>
                                <%--<p>提交时间：<span>2017-06-18 16：30</span></p>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="hr hr1"></div>--%>

                        <%--<div>--%>
                            <%--<img src="../../img/中层.png" class="head head_pic2" alt="中层">--%>
                            <%--<p class="staff status2">办公室</p>--%>
                            <%--<div class="details details2">--%>
                                <%--<p>审核状态：<span>通过</span></p>--%>
                                <%--<p>审核人：<span>小明</span></p>--%>
                                <%--<p>审核时间：<span>2017-06-18 16：30</span></p>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="hr hr2"></div>--%>

                        <%--<div>--%>
                            <%--<img src="../../img/高层.png" class="head head_pic3" alt="高层">--%>
                            <%--<p class="staff status3">审批领导</p>--%>
                            <%--<div class="details details3">--%>
                                <%--<p>审核状态：<span>未审核</span></p>--%>
                                <%--<p>审批人：<span>小秋</span></p>--%>
                                <%--<p>审核时间：<span>2017-06-18 16：30</span></p>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    <%--</div>--%>

                <%--</div>--%>
                <%--</div>--%>

            <%--</div>--%>
        <%--</div>--%>
    </div>

    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; 临沧市移民局</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://www.bhidi.com">北京院</a></p>
    </footer>

</div><!--/.fluid-container-->



<script>

    var status=$("#status").text();

    function newForm() {
        $('#form_stuff input').val('');
        $('#form_stuff').modal('show');
//        $('#select_model').modal('show');
    }








    function detail_office(that) {
        $('#form_office').modal('show');
    }

    function flow(that){
        $('#flow').modal('show');
    }

    $("#status")
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
<script src="../../js/app.js"></script>
<script src="../../js/oa.js"></script>
</body>
</html>

