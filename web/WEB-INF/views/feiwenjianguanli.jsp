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
    <!-- The fav icon -->
    <link rel="shortcut icon" href="../../img/favicon.ico">
    <style>
        .mytable{
            width: 100%;
            font-size: 12px;
            border-top: 1px solid #000;
            border-left: 1px solid #000;
            line-height: 30px;
            text-align: center;
        }
        .mytable td{
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
            padding: 0 3px;
        }
        .mytable input{
            width: 100%;
            padding: 0 5px;
            line-height: 16px;
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
        .red{
            color: red;
        }
        input{
            line-height: 16px !important;
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
            width: 33.3%;
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
            font-size: 20px;
            width: 33.3%;
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
        #container,#container1{
            border-bottom: 1px solid #ccc;
        }
    </style>
</head>
<div id="bg">
</div>
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
                    欢迎<span id="name" style="margin: 0 6px;">${user.name}</span><a href="logout.do" >注销</a>
                </c:if>
            </div>
        </div>
        <span id="status" style="display:none;width:0;height:0;">${user.level}</span>
        <span id="roleList" style="display:none;width:0;height:0;">${user.roleList}</span>
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
                        <li><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span>非文件表单</span></a></li>

                        <li id="header2" class="nav-header">我的事务</li>
                        <li id="dcl2"><a href="#new2"><span class="notification red" id="nav_num"></span><i class="glyphicon glyphicon-tags"></i><span> 待处理事务</span></a></li>
                        <li id="ycl2"><a href="#progress2"><i class="glyphicon glyphicon-refresh"></i><span> 已处理事务</span></a></li>
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
                                                <a href="#">新建非文件表单</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="新建非文件表单" class="well top-block"
                                               href="javascript:void(0)" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>

                                                <div>新建非文件表单</div>

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
                                                    <th>信息分类</th>
                                                    <th>提交人</th>
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
                                                    <th>信息分类</th>
                                                    <th>提交人</th>
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
                                                    <th>信息分类</th>
                                                    <th>提交人</th>
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
                                    <td>标题</td>
                                    <td colspan="3"><input type="text" name="title"></td>
                                </tr>
                                <tr>
                                    <td>提交人</td>
                                    <td><input id="name1" readonly="readonly" type="text" name="formsubmitperson"></td>
                                    <td>信息分类</td>
                                    <td><select name="infokind">
                                        <option value="计划总结类">计划总结类</option>
                                        <option value="汇报交流类">汇报交流类</option>
                                        <option value="简报信息类">简报信息类</option>
                                        <option value="灾情报告类">灾情报告类</option>
                                        <option value="维稳安全类">维稳安全类</option>
                                    </select></td>
                                </tr>
                                <tr>
                                    <td colspan="4"><textarea name="content" placeholder="内容" cols="30" rows="10" style="width: 99%;"></textarea></td>
                                </tr>
                                <tr>
                                    <td>上传附件</td>
                                    <td colspan="3">
                                        <div id="filesUpload" style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                            <a href="#" id="add_1" onclick="add_click_file(1)">添加附件</a>
                                            <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                        </div>
                                    </td>
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

    <div class="modal fade" id="modle_handle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3></h3>
                </div>


                <div class="modal-body" style="font-size: 0;width: 100%;">
                    <div id="container" style="width: 100%;height: 160px">
                        <div class="step-body" id="myStep" style="width:80%;margin: 0 auto;">
                            <div class="step-header">
                                <ul>
                                    <li><p>信息提交</p></li>
                                    <li><p>办公室处理处置</p></li>
                                    <li><p>结束</p></li>
                                </ul>
                            </div>
                        </div>
                        <div id="user_container" style="width:80%;margin: 0 auto;margin-top: 85px;">
                            <div class="user_1"></div>
                            <div class="user_2"></div>
                            <div class="user_3"></div>
                        </div>
                    </div>
                    <div class="con_info" style="margin-top: 20px;">
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td>标题</td>
                                <td colspan="3"><input type="text" name="title"></td>
                            </tr>
                            <tr>
                                <td>提交人</td>
                                <td><input type="text" name="formsubmitperson"></td>
                                <td>信息分类</td>
                                <td><input type="text"></td>
                            </tr>
                            <tr>
                                <td colspan="4"><textarea name="content" placeholder="内容" cols="30" rows="10" style="width: 99%;"></textarea></td>
                            </tr>
                            <tr>
                                <td>下载附件</td>
                                <td colspan="3">
                                </td>
                            </tr>
                            <tr>
                                <td>办公室处理</td>
                                <td colspan="3"><textarea cols="30" rows="10"></textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>


                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#"  class="btn btn-primary">签收</a>
                    <a href="#"  class="btn btn-success">采用</a>
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

    var status=$("#status").text();
    var name=$("#name").text();

    function newForm() {
        $('#form_stuff input').val('');
        $('#form_stuff textarea').val('');
        $.each($("#filesUpload a"),function (i,n) {
            if(n.text != "添加附件"){
                n.remove()
            }
        })
        $("#filesUpload span").remove();
        $("#name1").val(name);
        $('#form_stuff').modal('show');
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
<script>

    // 全部列表datatables

    var all_table = $('#NewTable_Stuff').DataTable({
        ajax: {
            url: "/allNonFileManagementDataTable.do",
            async: false
        },
        "order": [[2, 'asc']],
        "serverSide": true,
        "columns": [
            {"data": "nonfileid"},
            {"data": "title"},
            {"data": "infokind"},
            {"data": "submitperson"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [5],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                    html += "<input type='button' class='btn btn-danger btn-xs' style='margin-left: 5px;' onclick='delete1(this)' value='删除'/>" ;
                    return html;
                }
            },
            {
                "searchable": false,
                "orderable": false,
                "targets": [0]
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


    //待办列表的datatables
    var dcl_table = $('#NewTable_Office').DataTable({
        ajax: {
            url: "/pendingNonFileManagementDataTable.do",
            async:false
        },
        "order": [[1, 'asc']],
        "serverSide": true,
        "columns": [
            {"data": "nonfileid"},
            {"data": "title"},
            {"data": "infokind"},
            {"data": "submitperson"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [5],
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


    // 已办表单的datatables

    var ycl_table = $('#SubmittedTable_Office').DataTable({
        ajax: {
            url: "/handledNonFileManagementDataTable.do"
        },
        "order": [[2, 'asc']],
        "serverSide": true,
        "columns": [
            {"data": "nonfileid"},
            {"data": "title"},
            {"data": "infokind"},
            {"data": "submitperson"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [5],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                    return html;
                }
            },
            {
                "searchable": false,
                "orderable": false,
                "targets": [0]
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
                if(n.classification == "非文件管理"){
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
    var f3 = [];
    $.each(fun_list1,function(i,n){
        if(n.authdescription == "文件提交"){
            f1.push(n);
        }else if(n.authdescription == "全部列表查看、搜索、删除功能"){
            f2.push(n);
        }else if(n.authdescription == "个人申请列表查看、搜索功能"){
            f3.push(n);
        }
    })
    if(f1.length == 0){
        $("#new1>.row").css("display","none");
    }

    if(f2.length == 0 && f3.length == 0){
        $("#new1>.box-inner").css("display","none");
    }else if(f2.length == 0 && f3.length == 1){
        console.log(123)
        $(".btn-danger").css("display","none");
    }
    if(f1.length == 0 && f2.length == 0 && f3.length == 0){
        $("#myTab li:nth-child(1)").remove();
        $("#myTab li:nth-child(1)").remove();
        $("#new1").remove();
        $("#dcl").addClass("active");
        $("#new2").addClass("active");
    }

    //删除功能
    function delete1(that) {
        var nonfileid = $(that).parent("td").parent("tr").children("td:first-child").text();
        console.log(nonfileid);
        if(confirm("你确定要删除吗？")){
            $.ajax({
                url: "/deleteNonFile.do",
                type: "post",
                dataType: "json",
                data: {nonfileid:nonfileid},
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

    //表格刷新
    function table_refresh() {
        all_table.ajax.url("/allNonFileManagementDataTable.do").load();
        dcl_table.ajax.url("/pendingNonFileManagementDataTable.do").load();
        ycl_table.ajax.url("/handledNonFileManagementDataTable.do").load();
    }

    //待办事务的显示条数
    function acount(){
        var info=dcl_table.page.info();
        $("#nav_num").text(info.recordsTotal)
    }
    setTimeout(acount,0);

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

    //  新建表单 表单提交
    var xflag = true;
    $("#form_stuff .btn-primary").click(function () {
        if(xflag){
            xflag = false;
            var options  = {
                url:'/submitNonFileManagement.do',
                type:'post',
                success:function(data)
                {
                    console.log(data);
                    if(data.result == "success"){
                        alert("提交成功");
                        xflag = true;
                        table_refresh();
                        acount();
                        $('#form_stuff').modal('hide');
                    }else {
                        alert(data.result);
                        xflag = true;
                    }
                },
                error:function () {
                    alert("系统错误");
                    xflag = true;
                }
            };
            $("#fileForm").ajaxSubmit(options);
        }

    });

    //编辑查看按钮
    var id;
    function edit(that) {
        $("#modle_handle input").val("");
        $("#modle_handle textarea").val("");
        var mydata;
        var kind = $(that).val();
        id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
        var state = $(that).parent("td").parent("tr").children("td:nth-child(5)").text();
        $('#modle_handle').modal('show');
        if(kind == "查看"){
            $("#modle_handle .btn-primary").css("display","none");
            $("#modle_handle .btn-success").css("display","none");
        }else if(kind == "编辑"){
                $("#modle_handle .btn-primary").css("display","inline-block");
                $("#modle_handle .btn-success").css("display","inline-block");
        }
        console.log(kind,id);
        $.ajax({
            url: "/getNonFileManagementInfoByNonFileId.do",
            async: false,
            type: "post",
            dataType: "json",
            data: {nonfileid:id},
            success: function (data) {
                mydata = data;
                console.log(data)
                $("#modle_handle tr:nth-child(1) td:nth-child(2) input").val(data.title);
                $("#modle_handle tr:nth-child(2) td:nth-child(2) input").val(data.formsubmitperson);
                $("#modle_handle tr:nth-child(2) td:nth-child(4) input").val(data.infokind);
                $("#modle_handle tr:nth-child(3) td:nth-child(1) textarea").val(data.content);
                $("#modle_handle tr:nth-child(5) td:nth-child(2) textarea").val(data.officecontent);
                $("#modle_handle tr:nth-child(4) td:nth-child(2)").empty();
                var file_arr = data.attachmentpath.split(",");
                if(data.attachmentpath != ""){
                    var file_arr = data.attachmentpath.split(",");
                    $.each(file_arr,function (i,n) {
                        var start = n.lastIndexOf("\\") + 1;
                        var end = n.lastIndexOf("-");
                        var filekind_index = n.lastIndexOf(".");
                        var str = n.substring(start,end);
                        var filekind = n.substring(filekind_index);
                        str = str + filekind;
                        var files = "";
                        files  += ""
                            + "<div class='download_wrapper' style='display: inline-block;margin: 0 5px;'>"
                            + "<iframe name='downloadFrame' style='display:none;'></iframe>"
                            + "<form action='/file/download.do' method='get' target='downloadFrame'>"
                            + "<span class='file_name' style='color: #000;'>"+str+"</span>"
                            + "<input class='file_url' style='display: none;' name='path' value="+ n +">"
                            + "<button type='submit'>下载</button>"
                            + "</form>"
                            + "</div>"
                        $("#modle_handle tr:nth-child(4) td:nth-child(2)").append(files);
                    });
                }
            }
        })
        $(".user_1").empty();
        $(".user_2").empty();
        if(mydata.submitperson != ""){
            var str = "";
            str +=  ""
                +   "<div>"
                +   "<p class='user'>"+ mydata.submitperson +"</p>"
                +   "<p class='time'>"+ mydata.submittime +"</p>"
                +   "</div>"
            $(".user_1").append(str).addClass("myactive");
        }
        if(mydata.officeperson != ""){
            var str = "";
            str +=  ""
                +   "<div>"
                +   "<p class='user'>"+ mydata.officeperson +"</p>"
                +   "<p class='time'>"+ mydata.officetime +"</p>"
                +   "</div>"
            $(".user_2").append(str).addClass("myactive");
        }
        $("#modle_handle .modal-header h3").text("非文件管理-" + id);
        if(state == "办公室签收并处理"){
            step.goStep(2);
        }else if(state == "签收" || state == "采用" ){
            step.goStep(3);
            $("#myStep li:nth-child(3) p").text(mydata.status);
        }
    }
    //办公室提交
    var bflag1 = true;
    $("#modle_handle .btn-primary").click(function () {
        if(bflag1){
            bflag1 = false;
            var title = $("#modle_handle tr:nth-child(1) td:nth-child(2) input").val();
            var formsubmitperson = $("#modle_handle tr:nth-child(2) td:nth-child(2) input").val();
            var infokind = $("#modle_handle tr:nth-child(2) td:nth-child(4) input").val();
            var content = $("#modle_handle tr:nth-child(3) td:nth-child(1) textarea").val();
            var officecontent = $("#modle_handle tr:nth-child(5) td:nth-child(2) textarea").val();
            var text = new Object();
            text.nonfileid = id;
            text.title = title;
            text.formsubmitperson = formsubmitperson;
            text.infokind = infokind;
            text.content = content;
            text.officecontent = officecontent;
            text.status = "签收";
            console.log(text);
            var mytext = JSON.stringify(text)
            $.ajax({
                url: "/updateNonFileManagementInfo.do",
                type: "post",
                data: {text:mytext},
                success: function (data) {
                    if(data.result == "success"){
                        alert("提交成功");
                        bflag1 = true;
                        table_refresh();
                        acount();
                        $('#modle_handle').modal('hide');
                    }else {
                        alert("提交失败");
                        bflag1 = true;
                    }
                }
            })
        }
    })
    var bflag2 = true;
    $("#modle_handle .btn-success").click(function () {
        if(bflag2){
            bflag2 = false
            var title = $("#modle_handle tr:nth-child(1) td:nth-child(2) input").val();
            var formsubmitperson = $("#modle_handle tr:nth-child(2) td:nth-child(2) input").val();
            var infokind = $("#modle_handle tr:nth-child(2) td:nth-child(4) input").val();
            var content = $("#modle_handle tr:nth-child(3) td:nth-child(1) textarea").val();
            var officecontent = $("#modle_handle tr:nth-child(5) td:nth-child(2) textarea").val();
            var text = new Object();
            text.nonfileid = id;
            text.title = title;
            text.formsubmitperson = formsubmitperson;
            text.infokind = infokind;
            text.content = content;
            text.officecontent = officecontent;
            text.status = "采用";
            console.log(text);
            var mytext = JSON.stringify(text)
            $.ajax({
                url: "/updateNonFileManagementInfo.do",
                type: "post",
                data: {text:mytext},
                success: function (data) {
                    if(data.result == "success"){
                        alert("提交成功");
                        bflag2 = true;
                        table_refresh();
                        acount();
                        $('#modle_handle').modal('hide');
                    }else {
                        alert("提交失败");
                        bflag2 = true;
                    }
                }
            })
        }

    })
</script>
</body>
</html>

