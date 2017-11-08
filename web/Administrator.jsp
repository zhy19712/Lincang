<%--
  Created by IntelliJ IDEA.
  User: TR
  Date: 2017/11/7
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <link rel="stylesheet" href="css/jquery.step.css">
    <link rel="stylesheet" href="js/themes/default/style.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="css/jedate.css">
    <link rel="stylesheet" href="css/mycss.css">
    <link rel="stylesheet" href="../../css/themes/default/style.css">
    <link rel="stylesheet" href="css/oa.css">

    <link rel="stylesheet" href="css/media.css" media="print">

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-form.min.js"></script>
    <script src="js/jquery.jedate.js"></script>
    <script src="js/jquery.step.js"></script>
    <script src="js/jstree.js"></script>
    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    <style>
        .mytable{
            width: 100%;
            font-size: 12px;
            border-top: 1px solid black;
            border-left: 1px solid black;
            line-height: 30px;
            text-align: center;
        }
        .mytable td{
            border-right: 1px solid black;
            border-bottom: 1px solid black;
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
        .prompt{
            float:left;
            width:50%;
            white-space:nowrap;
            overflow:hidden;
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
        <a class="navbar-brand" href="/tohome.htm" style="width: 300px;"> <img alt="Logo" src="img/logo20.png" class="hidden-xs"/>
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

                        <li class="nav-header">用户管理</li>
                        <li><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span> 添加用户</span></a></li>

                        <li class="nav-header">角色管理</li>
                        <li><a href="#new2"><i class="glyphicon glyphicon-tags"></i><span> 添加角色</span></a></li>
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
                                                <a href="#">用户管理</a>
                                            </li>
                                            <li>
                                                <a href="#">添加用户</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="填写新表单" class="well top-block"
                                               href="javascript:void(0)" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>

                                                <div>添加用户</div>

                                            </a>
                                        </div>

                                    </div>
                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 全部列表</h2>
                                        </div>


                                        <div class="box-content">
                                            <table id="NewTable_Admin" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>用户名</th>
                                                    <th>角色</th>
                                                    <th>姓名</th>
                                                    <th>单位名称</th>
                                                    <th>部门</th>
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
                                                <a href="#">角色管理</a>
                                            </li>
                                            <li>
                                                <a href="#">添加角色</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="填写新角色" class="well top-block"
                                               href="javascript:void(0)" onclick="newRoleForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>

                                                <div>添加角色</div>

                                            </a>
                                        </div>

                                    </div>

                                    <div class="box-inner">
                                        <div class="box-header well">
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 角色列表</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="NewTable_role" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>角色</th>
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
                    <h3 id="form-kind">添加用户</h3>
                </div>

                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm" enctype="multipart/form-data"  target="uploadFrame">
                    <div class="modal-body" style="font-size: 0;width: 100%;" id="print1">
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td>用户名</td>
                                <td colspan="3">
                                    <input type="text" name="username" id="username" class="pull-left" style="width :50%;">
                                    <span id="usernamePrompt" class="text-left prompt" >以字母开头,可以字母和数字组合,长度在2个以上</span>
                                </td>
                            </tr>
                            <tr>
                                <td>密码</td>
                                <td>
                                    <input type="text" name="pass" id="pass" class="pull-left" style="width :50%;">
                                    <span id="passPrompt" class="text-left prompt" >数字和字母组合,字符,长度在6~18之间</span>
                                </td>
                            </tr>
                            <tr>
                                <td>角色</td>
                                <td colspan="7">
                                    <div class="col-sm-4">
                                        <select class="form-control input-sm role" name="role"  >
                                            <option>==请选择===</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>单位名称</td>
                                <td>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <select class="form-control input-sm unit" name="unit"  >
                                                <option>==请选择===</option>
                                            </select>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>部门</td>
                                <td>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <select class="form-control input-sm department" name="department" >
                                                <option>==请选择===</option>
                                            </select>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>姓名</td>
                                <td><input type="text" name="name" id="name" class="pull-left" style="width :50%;">
                                    <span id="namePrompt" class="text-left prompt">必须为汉字</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="black">联系电话</td>
                                <td><input type="text" name="phone" id="phone"></td>
                            </tr>


                            </tbody>
                        </table>
                    </div>
                </form>

                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary" id="btn-primary">提交</a>
                </div>


            </div>
        </div>
    </div>

    <div class="modal fade" id="form_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" id="close_update" data-dismiss="modal">×</button>
                    <h3 id="form-kind1">编辑用户</h3>
                </div>
                <span id="data_id" style="display: none"></span>

                <iframe id="uploadFrame1" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm1" enctype="multipart/form-data"  target="uploadFrame">
                    <div class="modal-body" style="font-size: 0;width: 100%;" id="print11">
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td>用户名</td>
                                <td colspan="3">

                                    <input type="text" name="username" id="username1" class="pull-left" style="width :50%;">
                                    <span id="usernamePrompt1" class="text-left prompt" >以字母开头,可以字母和数字组合,长度在2个以上</span>
                                </td>
                            </tr>
                            <tr>
                                <td>密码</td>
                                <td>
                                    <input type="text" name="pass" id="pass1" class="pull-left" style="width :50%;">
                                    <span id="passPrompt1" class="text-left prompt" >数字和字母组合,字符,长度在6~18之间</span>
                                </td>
                            </tr>
                            <tr>
                                <td>角色</td>
                                <td colspan="7">
                                    <div class="col-sm-4">
                                        <select class="form-control input-sm role" name="role" >
                                            <option>==请选择===</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>单位名称</td>
                                <td>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <select class="form-control input-sm unit" name="unit"  >
                                                <option>==请选择===</option>
                                            </select>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>部门</td>
                                <td>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <select class="form-control input-sm department" name="department">
                                                <option>==请选择===</option>
                                            </select>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>姓名</td>
                                <td>
                                    <input type="text" name="name" id="name1" class="pull-left" style="width :50%;">
                                    <span id="namePrompt1" class="text-left prompt">必须为汉字</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="black">联系电话</td>
                                <td><input type="text" name="phone" id="phone1"></td>
                            </tr>


                            </tbody>
                        </table>
                    </div>
                </form>

                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary" id="btn-update">修改</a>
                </div>


            </div>
        </div>
    </div>


    <div class="modal fade" id="form_Role" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" id="close_Role" data-dismiss="modal">×</button>
                    <h3 id="Role-kind">添加角色</h3>
                </div>

                <form id = "fileRole" enctype="multipart/form-data"  target="uploadFrame">
                    <div class="modal-body" style="font-size: 0;width: 100%;" id="print2">
                        <table class="mytable">
                            <tbody>
                            <tr>
                                <td>角色</td>
                                <td colspan="3"><input type="text" name="username" id="roleName"></td>
                            </tr>

                            <tr>
                                <td>功能</td>
                                <td>

                                    <div id="tree_container" align="left">
                                        <ul>
                                            <li>移民搬迁模块
                                                <ul>
                                                    <li>移民管理
                                                        <ul>
                                                            <li>移民登记
                                                                <ul>
                                                                    <li id="1">移民新建功能</li>
                                                                    <li id="2">移民修改功能</li>
                                                                    <li id="3">移民上传功能</li>
                                                                    <li id="4">列表查看，搜索功能</li>
                                                                    <li id="5">列表删除功能</li>
                                                                </ul>
                                                            </li>
                                                            <li>移民分析
                                                                <ul>
                                                                    <li id="6">地图搜索功能</li>
                                                                    <li id="7">地图查看功能</li>
                                                                    <li id="8">地图统计功能(按照区县)</li>
                                                                    <li id="9">移民信息查看</li>
                                                                    <li id="10">统计分析查看</li>
                                                                    <li id="11">区县搜索功能</li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li>资金申请模块
                                                <ul>
                                                    <li>市资金申请管理
                                                        <ul>
                                                            <li>我的表单
                                                                <ul>
                                                                    <li id="12">市局规划科资金申请上报功能</li>
                                                                    <li id="13">全部列表查看、搜索、删除功能</li>
                                                                </ul>
                                                            </li>
                                                            <li>待处理/已处理事务
                                                                <ul>
                                                                    <li id="14">市局财务处理功能</li>
                                                                    <li id="15">市局规划科处理功能</li>
                                                                    <li id="16">列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li>区县资金申请管理
                                                        <ul>
                                                            <li>我的申请
                                                                <ul>
                                                                    <li id="17">区县发起申请功能</li>
                                                                    <li id="18">全部列表查看、搜索、删除功能</li>
                                                                    <li id="19">个人申请列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                            <li>待处理/已处理事务
                                                                <ul>
                                                                    <li id="20">市局规划科资金批复功能</li>
                                                                    <li id="21">市局财务科处理功能</li>
                                                                    <li id="22">区县接收到账功能</li>
                                                                    <li id="23">区县记账功能</li>
                                                                    <li id="24">列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li>协同办公模块
                                                <ul>
                                                    <li>发文管理
                                                        <ul>
                                                            <li>我的表单
                                                                <ul>
                                                                    <li id="25">起草文件功能</li>
                                                                    <li id="26">全部列表查看、搜索、删除功能</li>
                                                                    <li id="27">个人申请列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                            <li>待处理/已处理事务
                                                                <ul>
                                                                    <li id="28">审核处理</li>
                                                                    <li id="29">领导签批功能</li>
                                                                    <li id="30">处理处置功能</li>
                                                                    <li id="31">文件归档功能</li>
                                                                    <li id="32">列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li>收文管理
                                                        <ul>
                                                            <li>我的表单
                                                                <ul>
                                                                    <li id="33">文件登记功能</li>
                                                                    <li id="34">全部列表查看、搜索、删除功能</li>
                                                                </ul>
                                                            </li>
                                                            <li>待处理/已处理事务
                                                                <ul>
                                                                    <li id="35">文件处理功能</li>
                                                                    <li id="36">文件签批功能</li>
                                                                    <li id="37">文件处理处置功能</li>
                                                                    <li id="38">办公室归档功能</li>
                                                                    <li id="39">列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li>非文件管理
                                                        <ul>
                                                            <li>我的表单
                                                                <ul>
                                                                    <li id="40">文件提交</li>
                                                                    <li id="41">全部列表查看、搜索、删除功能</li>
                                                                    <li id="42">个人申请列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                            <li>待处理/已处理事务
                                                                <ul>
                                                                    <li id="43">文件接收及签批</li>
                                                                    <li id="44">列表查看、搜索功能</li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>

                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </form>

                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary" id="roleBtnAdd">提交</a>
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




<script>

    function newForm() {
        $('#form_stuff input').val('');
        $('#form_stuff').modal('show');
    }

    function newRoleForm() {
        $('#form_Role input').val('');
        $('#form_Role').modal('show');
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
<script src="js/app.js"></script>
<script src="js/Administrator.js"></script>
</body>
</html>


