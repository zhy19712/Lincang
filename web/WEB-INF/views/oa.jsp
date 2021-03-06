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
    <link rel="stylesheet" href="../../css/jquery.step.css">
    <link rel="stylesheet" href="../../js/themes/default/style.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="../../css/jedate.css">
    <link rel="stylesheet" href="../../css/mycss.css">
    <link rel="stylesheet" href="../../css/oa.css">

    <link rel="stylesheet" href="../../css/media.css" media="print">
    <link rel="stylesheet" href="../../css/mycommon.css">

    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery-form.min.js"></script>
    <script src="../../js/jquery.jedate.js"></script>
    <script src="../../js/jquery.step.js"></script>
    <script src="../../js/jstree.js"></script>
    <script src="../../js/jQuery.print.js"></script>
    <script src="../../js/Blob.js"></script>
    <script src="../../js/FileSaver.js"></script>
    <script src="../../js/jquery.wordexport.js"></script>

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
            width: 99%;
            height: 100px;
        }
        .reds{
            color: red !important;
        }
        .text_left{
            text-align: left;
        }
        p{
            margin:0;
        }
        #sel_model{
            width: 96%;
            margin: 0 auto;
            font-size: 0;
        }
        #sel_model>div{
            display: inline-block;
            vertical-align: top;
            margin-left: 30px;
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
        #select_people li span{
            display: inline-block;
            vertical-align: middle;
            width: 150px;
        }
        #user_container1>div,#user_container>div{
            display: inline-block;
            vertical-align: middle;
            word-wrap: break-word;
            text-align: center;
            width: 16.5%;
        }
        .download_wrapper button{
            background: transparent;
            border: none;
            margin: 0;
            padding: 0;
            color: #2fa4e7;
        }
        .download_wrapper button:hover{
            text-decoration: underline;
        }
        input{
            line-height: 16px !important;
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
        #container{
            border-bottom: 1px solid #ccc;
        }
        .btn-success{
            display: none;
        }
        #myprint td{
            border-right: 1px solid red;
            border-bottom: 1px solid red;
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
                        <li><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span> 发文表单</span></a></li>

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

                                <div class="tab-pane active" id="new1">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">我的表单</a>
                                            </li>
                                            <li>
                                                <a href="#">新建发文表单</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="新建发文表单" class="well top-block"
                                               href="javascript:void(0)" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>

                                                <div>新建发文表单</div>

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
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已办列表</h2>

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
                                    <div id="filesUpload" style="width:80%;display: inline-block;vertical-align: bottom;">
                                        <a href="#" id="add_1" onclick="add_click_file(1)">添加附件</a>
                                        <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                    </div>
                                </td>
                            </tr>
                            <tr style="display: none;">
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
                    <div id="container" style="width: 100%;padding-bottom: 20px;">
                        <div class="step-body" id="myStep" style="width:80%;margin: 0 auto;">
                            <div class="step-header">
                                <ul>
                                    <li><p>发文起草</p></li>
                                    <li><p>办公室审核处理</p></li>
                                    <li><p>签批</p></li>
                                    <li><p>处理处置</p></li>
                                    <li><p>办公室归档</p></li>
                                    <li><p>结束</p></li>
                                </ul>
                            </div>
                        </div>
                        <div id="user_container" style="width:80%;margin: 0 auto;margin-top: 85px;">
                            <div class="user_1"></div>
                            <div class="user_2"></div>
                            <div class="user_3"></div>
                            <div class="user_4"></div>
                            <div class="user_5"></div>
                            <div class="user_6"></div>
                        </div>
                    </div>
                    <%--<div id="people_list" style="font-size: 12px;margin-left: 20px;line-height: 30px;">--%>
                        <%--<p><span>签批领导:</span><span id="leader" style="margin-left: 10px;"></span></p>--%>
                        <%--<p><span>办理人员:</span><span id="people" style="margin-left: 10px;"></span></p>--%>
                    <%--</div>--%>
                    <div id="sel_model" style="margin-top: 20px;">
                        <p style="display: none;">领导签批人</p>
                        <p id="text" class="style1" style="margin-top: 3px;">选择处理人</p>
                        <div id="model">
                            <ul id="select_people">
                                <li><span style="width: 120px;">领导签批人</span><input type="text" id="lingdao"></li>
                                <li><span style="width: 120px;">办理人</span><input type="text" id="banli"></li>
                            </ul>
                        </div>
                        <div id="sel_people">
                            <div id="tree_container"></div>
                            <button class="mybtn1">确认</button>
                        </div>
                    </div>
                    <div class="fawen_info" style="margin-top: 20px;">
                        <p class="red" style="font-size: 18px;text-align: center;line-height: 40px;">临沧市移民局发文稿纸</p>
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td class="red">文号</td>
                                <td colspan="2"><input type="text" name="dept"></td>
                                <td class="red">日期</td>
                                <td><input type="text" name="author" id="time1" readonly="readonly"></td>
                                <td class="red">缓级</td>
                                <td><input type="text" name="reviewer"></td>
                                <td class="red">密级</td>
                                <td><input type="text" name="reviewer"></td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <p class="red text-left">签发</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                                <td colspan="2">
                                    <p class="red text-left">审稿</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                                <td colspan="2">
                                    <p class="red text-left">会签</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9">
                                    <p class="red text-left">抄报</p>
                                    <textarea name="" cols="30" rows="10"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="red">抄送</td>
                                <td colspan="8"><input type="text" name="keyword"></td>
                            </tr>
                            <tr>
                                <td class="red">发</td>
                                <td colspan="8"><input type="text" name="title"></td>
                            </tr>
                            <tr>
                                <td class="red">拟稿单位</td>
                                <td colspan="4"><input type="text" name="dept"></td>
                                <td class="red">拟稿</td>
                                <td><input type="text" name="author"></td>
                                <td class="red">科室核稿</td>
                                <td><input type="text" name="reviewer"></td>
                            </tr>
                            <tr>
                                <td class="red">印刷</td>
                                <td colspan="4"><input type="text" name="print"></td>
                                <td class="red">校对</td>
                                <td><input type="text" name="revision"></td>
                                <td class="red">份数</td>
                                <td><input type="text" name="copy"></td>
                            </tr>
                            <tr>
                                <td class="red">下载</td>
                                <td colspan="8"></td>
                            </tr>
                            <tr style="display: none;">
                                <td class="red">主题词</td>
                                <td colspan="8"><input type="text" name="keyword"></td>
                            </tr>
                            <tr>
                                <td class="red">标题</td>
                                <td colspan="8"><input type="text" name="title"></td>
                            </tr>
                            <tr>
                                <td colspan="9"><textarea name="content" placeholder="内容" cols="30" rows="10" style="width: 99%;"></textarea></td>
                            </tr>
                            <tr>
                                <td class="red">办理结果</td>
                                <td colspan="8"><textarea name="content" cols="30" rows="10" style="width: 99%;"></textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>


                <div class="modal-footer" id="export">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <%--<a href="#"  class="btn btn-info">导出word</a>--%>
                    <a href="#"  class="btn btn-success">打印</a>
                    <a href="#"  class="btn btn-primary">提交</a>
                </div>


            </div>
        </div>
    </div>
    </div>

    <div class="modal fade" id="print_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-body" style="font-size: 0;width: 100%;">
                    <div class="fawen_info" style="margin-top: 20px;">
                        <table id="myprint" style="width:100%;border-top: 1px solid red;border-left: 1px solid red;">
                            <tbody>
                            <tr>
                                <td colspan="9" class="reds">临沧市移民局发文稿纸</td>
                            </tr>
                            <tr>
                                <td class="reds">文号</td>
                                <td colspan="2"></td>
                                <td class="reds">日期</td>
                                <td class="text-left"></td>
                                <td class="reds">缓级</td>
                                <td class="text-left"></td>
                                <td class="reds">密级</td>
                                <td class="text-left"></td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <p class="reds" style="text-align: left">签发</p>
                                    <p class="text-left" style="min-height: 100px"></p>
                                </td>
                                <td colspan="2">
                                    <p class="reds" style="text-align: left">审稿</p>
                                    <p class="text-left" style="min-height: 100px"></p>
                                </td>
                                <td colspan="2">
                                    <p class="reds" style="text-align: left">会签</p>
                                    <p class="text-left" style="min-height: 100px"></p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9">
                                    <p class="reds text-left">抄报</p>
                                    <p class="text-left" style="min-height: 100px"></p>
                                </td>
                            </tr>
                            <tr>
                                <td class="reds">抄送</td>
                                <td class="text-left" colspan="8"></td>
                            </tr>
                            <tr>
                                <td class="reds">发</td>
                                <td class="text-left" colspan="8"></td>
                            </tr>
                            <tr>
                                <td class="reds">拟稿单位</td>
                                <td class="text-left" colspan="4"></td>
                                <td class="reds">拟稿</td>
                                <td class="text-left"></td>
                                <td class="reds">科室核稿</td>
                                <td class="text-left"></td>
                            </tr>
                            <tr>
                                <td class="reds" style="color: red ">印刷</td>
                                <td class="text-left" colspan="4"></td>
                                <td class="reds">校对</td>
                                <td class="text-left"></td>
                                <td class="reds">份数</td>
                                <td class="text-left"></td>
                            </tr>
                            <tr style="display: none;">
                                <td class="reds">主题词</td>
                                <td class="text-left" colspan="8"></td>
                            </tr>
                            <tr>
                                <td class="reds">标题</td>
                                <td class="text-left" colspan="8"></td>
                            </tr>
                            <tr>
                                <td class="text-left" colspan="9">
                                    <p style="min-height: 100px"></p>
                                </td>
                            </tr>
                            <tr>
                                <td style="display: inline-block;position: relative; min-height:100px;">
                                    <p class="reds" style="width:100%;position: absolute;top:50%;left:50%;transform: translate(-50%,-50%)">办理结果</p>
                                </td>
                                <td class="text-left"  colspan="8" ></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
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



<script>

    //动态树结构显示
    function initTree() {
        var jsonarray=[];
        $.ajax({
            type: "post",
            url:"getDepartmentAndStaffs.do",
            dataType:"json",
            async: false,
            success:function(result) {
                for(var i=0 ; i<result.length; i++){
                    var arr=[];
                    for(var j=0;j<result[i].staffList.length;j++){
                        var rootArr={"text": result[i].staffList[j]};
                        arr.push(rootArr)
                    }
                    var arrays = {
                        "text":result[i].department,
                        "children":arr
                    };
                    jsonarray.push(arrays);
                }
            }
        });
        var arrs=[];
        for(var x=0;x<jsonarray.length;x++){
            if(jsonarray[x].text=="局长"){
                arrs.push(jsonarray[x]);
            }
        }
        for(var x=0;x<jsonarray.length;x++){
            if(jsonarray[x].text=="副局长"){
                arrs.push(jsonarray[x]);
            }
        }
        for(var x=0;x<jsonarray.length;x++){
            if(jsonarray[x].text!="副局长" && jsonarray[x].text!="局长"){
                arrs.push(jsonarray[x]);
            }
        }

        $("#tree_container").jstree({
                "plugins" : ["checkbox"],
                "core":{"data" :arrs}
            }
        )
    }
    initTree();

    var status=$("#status").text();

    function newForm() {
        $('#form_stuff input').val('');
        $('#form_stuff textarea').val('');
        $.each($("#filesUpload a"),function (i,n) {
            if(n.text != "添加附件"){
                n.remove()
            }
        })
        $("#filesUpload span").remove();
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
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="../../js/app.js"></script>
<script src="../../js/oa.js"></script>
</body>
</html>

