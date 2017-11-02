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
            width: 50%;
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
                                                    <th>信息分类</th>
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
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 待办列表</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="NewTable_Office" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>信息分类</th>
                                                    <th>发起人</th>
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
                                    <td><input type="text" name="formsubmitperson"></td>
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
                    <h3>办公室处理</h3>
                </div>


                <div class="modal-body" style="font-size: 0;width: 100%;">
                    <div id="container" style="width: 100%;height: 160px">
                        <div class="step-body" id="myStep" style="width:80%;margin: 0 auto;">
                            <div class="step-header">
                                <ul>
                                    <li><p>信息提交</p></li>
                                    <li><p>办公室签收并处理</p></li>
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
                    <div class="con_info">
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
<script>

    // 全部列表datatables

    var all_table = $('#NewTable_Stuff').DataTable({
        ajax: {
            url: "/allNonFileManagementDataTable.do"
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
    $("#form_stuff .btn-primary").click(function () {
        var options  = {
            url:'/submitNonFileManagement.do',
            type:'post',
            success:function(data)
            {
                console.log(data);
                if(data.result == "success"){
                    alert("提交成功");
                    table_refresh();
                    acount();
                    $('#form_stuff').modal('hide');
                }else {
                    alert("提交失败")
                }
            },
            error:function () {
                alert("系统错误");
            }
        };
        $("#fileForm").ajaxSubmit(options);
    });

    //编辑查看按钮
    function edit(that) {
        var kind = $(that).val();
        var id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
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
            type: "post",
            dataType: "json",
            data: {nonfileid:id},
            success: function (data) {
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
                        console.log(str)
                    });
                }
            }
        })
    }
    //办公室提交
    $("#modle_handle .btn-primary").click(function () {
        var title = $("#modle_handle tr:nth-child(1) td:nth-child(2) input").val();
        var formsubmitperson = $("#modle_handle tr:nth-child(2) td:nth-child(2) input").val();
        var infokind = $("#modle_handle tr:nth-child(2) td:nth-child(4) input").val();
        var content = $("#modle_handle tr:nth-child(3) td:nth-child(1) textarea").val();
        var officecontent = $("#modle_handle tr:nth-child(5) td:nth-child(2) textarea").val();
        var text = new Object();
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
                    table_refresh();
                    acount();
                    $('#form_stuff').modal('hide');
                }else {
                    alert("提交失败");
                }
            }
        })
    })
</script>
</body>
</html>

