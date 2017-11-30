<%--
  Created by IntelliJ IDEA.
  User: zhangchuan
  Date: 2017/11/30
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>临沧市移民局</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="临沧市移民局">

    <!-- The styles -->
    <link href="../../css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="../../css/app.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">

    <!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <%--<script src="../../js/app.js"></script>--%>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <!-- The fav icon -->
    <link rel="shortcut icon" href="../../img/favicon.ico">
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
        #msg_table_wrapper{
            width: 94%;
            margin: 20px auto;
        }
    </style>
</head>
<body>
    <div id="bg">
    </div>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation" style="height: 60px;">

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
                        欢迎${user.name}<a href="logout.do" >注销</a>
                    </c:if>
                </div>
            </div>
            <!-- user dropdown ends -->
        </div>
    </div>
    <div id="table_wrapper" style="width: 94%;margin: 0 auto;background-color:#fff;position: relative;z-index: 999;">
        <table id="msg_table" class="display" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>序号</th>
                <th>发起人</th>
                <th>发起时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <button id="btn" style="font-size: 20px;position: relative;">按钮</button>
    <div class="modal fade" id="msg_table_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3></h3>
                </div>
                <div class="modal-body">
                    <table>
                        <tbody>
                            <tr>
                                <td>发起人</td>
                                <td></td>
                                <td>发起时间</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>通知内容</td>
                                <td colspan="3"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                </div>

            </div>
        </div>
    </div>
</body>
<script>
    var msg_table = $('#msg_table').DataTable({
        ajax: {
            url: "/quxianMessage.do",
            async:false
        },
        "order": [[3, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "capitalflowid"},
            {"data": "guihuachuliren"},
            {"data": "guihuakechulitime"},
            {"data": "status"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [4],
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

    function edit(that) {
        var id = $(that).parent("td").parent("tr").children("td:first-child").text();
        var status = $(that).parent("td").parent("tr").children("td:nth-child(4)").text();
        $.ajax({
            url: "",
            type: "post",
            data: {capitalflowid:id,status:status},
            dataType: "json",
            success: function (data) {
                console.log(data);
            }
        })
    }
    $("#btn").click(function () {
        $("#msg_table_modal").modal("show");
    })
</script>
</html>
