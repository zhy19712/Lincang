<%--
  Created by IntelliJ IDEA.
  User: zhuangyf
  Date: 2017/8/30
  Time: 13:56
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

    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/home.css">
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
        <a class="navbar-brand" href="/tohome.htm"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
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
                    欢迎<span style="margin: 0 6px;">${user.name}</span>,<a href="logout.do" >注销</a>
                </c:if>
            </div>
        </div>
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
                    <ul class="nav nav-pills nav-stacked main-menu"  id="myTab">
                        <li class="nav-header">搬迁安置</li>
                        <li><a href="#people"><i class="glyphicon glyphicon-edit"></i><span> 移民搬迁</span></a></li>

                        <li class="nav-header">数据录入</li>
                        <li><a href="#import"><i class="glyphicon glyphicon-tags"></i><span> EXCEL导入</span></a></li>
                        <li><a href="#record"><i class="glyphicon glyphicon-tags"></i><span> 填写表单</span></a></li>
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
                                <div class="tab-pane active" id="people">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">移民搬迁安置</a>
                                            </li>
                                        </ul>
                                    </div>
                                    内容
                                </div>
                                <div class="tab-pane active" id="import">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">数据录入</a>
                                            </li>
                                            <li>
                                                <a href="#">EXCEL导入</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> EXCEL导入</h2>
                                        </div>
                                        <form action="/file/multipleUpload.do" method="post" enctype="multipart/form-data">
                                            <div id="filesUpload">
                                                <a href="#" id="add_1" onclick="add_click_file(1)"  >添加附件</a>
                                                <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
                                            </div>
                                            <input type="submit" value="提交"/>
                                        </form>
                                        <form action="/excel/multipleExcelUpLoadExcel.do" method="post" enctype="multipart/form-data">
                                            <div id="excelsUpload">
                                                <a href="#" id="excel_add" onclick="add_click_excel(1)"  >添加附件</a>
                                                <input style="display:none;" id="add_excel_1" type="file" name = "excels" onChange="addExcel(1)"/>
                                            </div>
                                            <input type="submit" value="提交"/>
                                        </form>
                                        <div>
                                            <c:if test="${error.size() > 0}">
                                                <c:forEach items="${error}" var="err">
                                                    <div>
                                                            ${err}:
                                                    </div>
                                                    <br>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                        <div>
                                            <c:if test="${error.size() == 0}">
                                                <sapn>${error}</sapn>
                                                <sapn>录入成功！</sapn>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane active" id="record">
                                    <div>
                                        <ul class="breadcrumb">
                                            <li>
                                                <a href="#">数据录入</a>
                                            </li>
                                            <li>
                                                <a href="#">填写表单</a>
                                            </li>
                                        </ul>
                                    </div>
                                    内容
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

    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; 临沧市移民局</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://www.bhidi.com">北京院</a></p>
    </footer>

    <script language="javascript">
        var fileIndex = 1;
        function add_click_file(index){
            $("#add_file_"+fileIndex).click();
        }
        function add(index) {
            /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
            var len = $("#add_file_" + (fileIndex) + "").val().split("\\").length;
            alert($("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1]);
            var num = $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1];
            $("#filesUpload").append('<span  id="add_file_span_' + (fileIndex) + '">' + $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1] + '</span>');
            $("#filesUpload").append('<a   id="add_file_a_' + (fileIndex) + '" href="javascript:del_file(' + fileIndex+ ')">删除</a>');
            $("#filesUpload").append('<input style="display:none;" id="add_file_' + (fileIndex + 1) + '" type="file" name = "files" onChange="add(' + (fileIndex + 1) + ')"/>');
            ++fileIndex;
        }
        function del_file(number) {
            var o=document.getElementById("filesupload");//获取父节点
            var int=document.getElementById("add_file_" + number+"");//获取需要删除的子节点
            var a=document.getElementById("add_file_a_" + number+"");//获取需要删除的子节点
            var span=document.getElementById("add_file_span_" + number+"");//获取需要删除的子节点
            o.removeChild(int)//从父节点o上面移除子节点a
            o.removeChild(a)
            o.removeChild(span)
        }



        var excelIndex = 1;
        function add_click_excel(index){
            $("#add_excel_"+excelIndex).click();
        }
        function addExcel(index) {
            /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
            var len = $("#add_excel_" + (excelIndex) + "").val().split("\\").length;
            alert($("#add_excel_" + (excelIndex) + "").val().split("\\")[len - 1]);
            var num = $("#add_excel_" + (excelIndex) + "").val().split("\\")[len - 1];
            $("#excelsUpload").append('<span  id="add_excel_span_' + (excelIndex) + '">' + $("#add_excel_" + (excelIndex) + "").val().split("\\")[len - 1] + '</span>');
            $("#excelsUpload").append('<a   id="add_excel_a_' + (excelIndex) + '" href="javascript:del_excel(' + excelIndex+ ')">删除</a>');
            $("#excelsUpload").append('<input style="display:none;" id="add_excel_' + (excelIndex + 1) + '" type="file" name = "excels" onChange="addExcel(' + (excelIndex + 1) + ')"/>');
            ++excelIndex;
        }
        function del_excel(number) {
            var o=document.getElementById("excelsUpload");//获取父节点
            var int=document.getElementById("add_excel_" + number+"");//获取需要删除的子节点
            var a=document.getElementById("add_excel_a_" + number+"");//获取需要删除的子节点
            var span=document.getElementById("add_excel_span_" + number+"");//获取需要删除的子节点
            o.removeChild(int)//从父节点o上面移除子节点a
            o.removeChild(a)
            o.removeChild(span)
        }
    </script>

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
<script src="../../js/app.js"></script>
</body>
</html>


