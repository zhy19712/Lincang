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
    <link href="css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <link href="css/chosen.min.css" rel="stylesheet">
    <link href="css/jquery.iphone.toggle.css" rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="css/mycss.css">
    <link rel="stylesheet" href="css/oa.css">
    <link rel="stylesheet" href="css/ui-choose.css">
    <link rel="stylesheet" href="css/jquery.step.css">
    <link rel="stylesheet" href="css/jedate.css">
    <link rel="stylesheet" href="css/money.css">
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
        #user_container1>div,#user_container2>div{
            display: inline-block;
            vertical-align: middle;
            word-wrap: break-word;
            text-align: center;
            font-size: 20px;
            width: 33%;
        }
        #user_container2>div{
            width: 20%;
        }
        #money_apply_wdo form,#more{
            width: 96%;
            margin: 0 auto;
        }
        #title{
            text-align: center;
            font-weight: 700;
            margin: 10px 0;
        }
        #money_apply_wdo table,#more table{
            width: 100%;
            border-top: 1px solid #000;
            border-left: 1px solid #000;
        }
        #money_apply_wdo table td,#more table td{
            padding: 5px 0;
            text-align: center;
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
        }
        #money_apply_wdo table input,#more table input{
            width: 100%;
            outline: none;
            border: none;
            padding: 0 5px;
        }
        #money_apply_wdo table select,#more table select{
            width: 94%;
            margin-left: 3%;
        }
        textarea{
            outline:none;
        }
    </style>


    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-form.min.js"></script>
    <script src="js/ui-choose.js"></script>
    <script src="js/jquery.jedate.js"></script>
    <script src="js/jquery.step.js"></script>
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
        <a class="navbar-brand" href="oa.jsp" style="width: 300px;"> <img alt="Logo" src="../../img/logo20.png" class="hidden-xs"/>
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
                        <li id="header1" class="nav-header">我的收文</li>
                        <li id="m_apply1"><a href="#new1"><i class="glyphicon glyphicon-edit"></i><span id="kind1">收文登记</span></a></li>

                        <li class="nav-header">我的待办</li>
                        <li id="dcl"><a href="#new2"><i class="glyphicon glyphicon-tags"></i><span> 待处理事务</span></a></li>
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
                                                <a href="#">新建登记</a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2 col-sm-3 col-xs-6">
                                            <a data-toggle="tooltip" title="新建收文登记表" class="well top-block"
                                               href="#" onclick="newForm()">
                                                <i class="glyphicon glyphicon-pencil blue"></i>
                                                <div id="kind2">新建收文登记表</div>
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
                                                    <th>年度</th>
                                                    <th>类别</th>
                                                    <th>来文日期</th>
                                                    <th>文件编号</th>
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
                                            <h2><i class="glyphicon glyphicon-info-sign"></i> 已到资金</h2>

                                        </div>
                                        <div class="box-content">
                                            <table id="dcl_table" class="display" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>发起人</th>
                                                    <th>发起人类型</th>
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
                                                    <th>标题</th>
                                                    <th>创建时间</th>
                                                    <th>发起人</th>
                                                    <th>发起人类型</th>
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

    <div class="modal fade" id="money_apply_wdo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">



                <div class="modal-header">
                    <button type="button" class="close" id="close_stuff" data-dismiss="modal">×</button>
                    <h3>收文登记</h3>
                </div>
                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                <form id = "fileForm" action="" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">

                    <div class="modal-body">
                        <p id="title">收文登记</p>
                        <table>
                            <tbody>
                            <tr>
                                <td>年度</td>
                                <td><input type="text" name="niandu"></td>
                                <td>保管期限</td>
                                <td><select name="qixian">
                                    <option value="1个月">1个月</option>
                                    <option value="6个月">6个月</option>
                                    <option value="1年">1年</option>
                                    <option value="2年">2年</option>
                                </select></td>
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
                                    <textarea name="timing" cols="30" rows="10" style="width: 99%;outline: none;height: 80px;"></textarea>
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
                                <td colspan="9"><textarea name="chuli" id="" cols="30" rows="10" style="width: 99%;"></textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">放弃</a>
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
                    <h3>详细信息</h3>
                </div>
                <div id="container" style="width: 100%;height: 160px">
                    <div class="step-body" id="myStep" style="width:80%;margin: 0 auto;">
                        <div class="step-header">
                            <ul>
                                <li><p>规划科已上报</p></li>
                                <li><p>财务处理中</p></li>
                                <li><p>规划科已通知区县</p></li>
                            </ul>
                        </div>
                    </div>
                    <div id="user_container1" style="width:80%;margin: 0 auto;margin-top: 85px;">
                        <div class="user1_1"></div>
                        <div class="user1_2"></div>
                        <div class="user1_3"></div>
                    </div>
                </div>
                <div id="more" style="height: 30px;overflow: hidden;transition: all 0.5s;">
                    <p id="first" style="margin-left: 24px;font-size: 16px;cursor: pointer;line-height: 30px;">查看收文登记详细信息</p>
                    <table>
                        <tbody>
                        <tr>
                            <td>年度</td>
                            <td><input type="text" name="niandu"></td>
                            <td>保管期限</td>
                            <td><select name="qixian">
                                <option value="1个月">1个月</option>
                                <option value="6个月">6个月</option>
                                <option value="1年">1年</option>
                                <option value="2年">2年</option>
                            </select></td>
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
                                <textarea name="timing" cols="30" rows="10" style="width: 99%;outline: none;height: 80px;"></textarea>
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
                            <td colspan="9"><textarea name="chuli" id="" cols="30" rows="10" style="width: 99%;"></textarea></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <iframe name="uploadFrame" style="display:none;"></iframe>
                <form action="" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">


                    <div class="modal-body">
                        <div class="row myrow">
                            <div class="col-sm-12">
                                <span>标题</span>
                                <input type="text" id="report_title_edit" readonly="true">
                            </div>
                        </div>
                        <div class="row myrow">
                            <div class="col-sm-6">
                                <span>上报人</span>
                                <input type="text" id="report_person_edit" readonly="true">
                            </div>
                            <div class="col-sm-6">
                                <span>上报时间</span>
                                <input type="text" id="report_quarter_edit" readonly="true">
                            </div>
                        </div>
                        <div class="row myrow">
                            <div class="col-sm-12">
                                <span>文件</span>
                                <div style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" onclick="add_click_file(1)">下载</a>
                                    <input style="display:none;" type="file" name = "files" onChange="add(1)"/>
                                </div>
                                <input type="button" style="display:none"/>
                            </div>
                        </div>
                        <div class="row myrow" id="caiwu">
                            <div class="col-sm-12" id="report_text_edit"></div>
                        </div>
                        <div class="row myrow" id="caiwu1">
                            <div class="col-sm-6">
                                <span>款项来源</span>
                                <input type="text" id="money_source" readonly="true">
                            </div>
                            <div class="col-sm-6">
                                <span>到款时间</span>
                                <input type="text" name="author" id="arrival_time" readonly="readonly">
                            </div>
                        </div>
                        <div class="row myrow" id="caiwu2">
                            <div class="col-sm-6">
                                <span>到款金额</span>
                                <input type="text" id="amount" readonly="true">
                            </div>
                            <div class="col-sm-6">
                                <span>上传附件</span>
                                <input type="text" name="author">
                            </div>
                        </div>
                        <div class="row myrow last" id="guihuake">
                            <div class="col-sm-12">
                                <div class="notice">
                                    <div class="add">
                                        <span>通知区县</span>
                                        <ul class="ui-choose" multiple="multiple" id="uc_03" style="width: 92%;">
                                            <li>临翔区</li>
                                            <li>凤庆县</li>
                                            <li>永德县</li>
                                            <li>镇康县</li>
                                            <li>云县</li>
                                            <li>沧源佤族自治县</li>
                                            <li>耿马傣族佤族自治县</li>
                                            <li>双江拉祜族佤族布朗族傣族自治县</li>
                                        </ul>
                                    </div>
                                    <div class="text">
                                        <span>通知内容</span><textarea name="" id="notice_content" cols="30" rows="10" style="outline: none;border: 1px solid #000"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row myrow" id="guihuake_show1">
                            <div class="col-sm-12">
                                <div class="county_infos">
                                    <span>已通知区县:</span>
                                    <p></p>
                                </div>
                            </div>
                        </div>
                        <div class="row myrow last" id="guihuake_show2">
                            <div class="col-sm-12">
                                <div class="not_content">
                                    <span>通知内容:</span>
                                    <p></p>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary">提交</a>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="edit2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" data-backdrop="static">

        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>详细信息</h3>
                </div>
                <div id="container2" style="width: 100%;height: 160px">
                    <div class="step-body" id="myStep2" style="width:80%;margin: 0 auto;">
                        <div class="step-header">
                            <ul>
                                <li><p>区县已申请</p></li>
                                <li><p>市局规划科批复中</p></li>
                                <li><p>市局财务科转账中</p></li>
                                <li><p>区县资金流向录入</p></li>
                                <li><p>区县资金流向明细</p></li>
                            </ul>
                        </div>
                    </div>
                    <div id="user_container2" style="width:80%;margin: 0 auto;margin-top: 85px;">
                        <div class="user2_1"></div>
                        <div class="user2_2"></div>
                        <div class="user2_3"></div>
                        <div class="user2_4"></div>
                        <div class="user2_5"></div>
                    </div>
                </div>
                <iframe name="uploadFrame" style="display:none;"></iframe>
                <form action="" method="post"
                      enctype="multipart/form-data"  target="uploadFrame">


                    <div class="modal-body">
                        <div class="row myrow">
                            <div class="col-sm-12">
                                <span>标题</span>
                                <input type="text" id="apply_title_edit" readonly="true">
                            </div>
                        </div>
                        <div class="row myrow">
                            <div class="col-sm-6">
                                <span>申请人</span>
                                <input type="text" id="apply_person_edit" readonly="true">
                            </div>
                            <div class="col-sm-6">
                                <span>申请原因</span>
                                <input type="text" id="apply_reason_edit" readonly="true">
                            </div>
                        </div>
                        <div class="row myrow" id="apply1">
                            <div class="col-sm-12">
                                <span>文件</span>
                                <div style="width:80%;display: inline-block; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;vertical-align: bottom;">
                                    <a href="#" onclick="add_click_file(1)">下载</a>
                                    <input style="display:none;" type="file" name = "files" onChange="add(1)"/>
                                </div>
                                <input type="button" style="display:none"/>
                            </div>
                        </div>
                        <div class="row myrow" id="pifu">
                            <div class="col-sm-12" style="padding-top: 10px">
                                <span style="display: inline-block;vertical-align: top;border: none;">批复意见:</span><textarea name="" id="pifu_content" cols="30" rows="10" style="width:90%;outline: none;border: 1px solid #000"></textarea>
                            </div>
                        </div>
                        <div class="row myrow" id="chuli">
                            <div class="col-sm-12" style="padding-top: 10px">
                                <span style="display: inline-block;vertical-align: top;border: none;">处理内容:</span><textarea name="" id="chuli_content" cols="30" rows="10" style="width:90%;outline: none;border: 1px solid #000"></textarea>
                            </div>
                        </div>
                        <div class="row myrow" id="luru">
                            <div class="col-sm-12" style="padding-top: 10px">
                                <span style="display: inline-block;vertical-align: top;border: none;">资金流向:</span><textarea name="" id="luru_content" cols="30" rows="10" style="width:90%;outline: none;border: 1px solid #000"></textarea>
                            </div>
                        </div>
                    </div>

                </form>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-success">保存</a>
                    <a href="#" class="btn btn-primary">提交</a>
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

<script src="js/money.js"></script>
<script src="js/readmore.js"></script>
<script>
    $('.ui-choose').ui_choose();
    var uc_03 = $('#uc_03').data('ui-choose');

    $("#myDate").jeDate({
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
    //收文登记
    $("#money_apply_wdo .btn-primary").click(function () {
        $("#fileForm").submit();
    })

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
            {"data": "title"},
            {"data": "create_time"},
            {"data": "report_person"},
            {"data": "initiatorclass"},
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
            {"data": "title"},
            {"data": "create_time"},
            {"data": "report_person"},
            {"data": "initiatorclass"},
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
    var status;
    ~function() {

        status = $("#status").text();

        if(status == 2){
            $("#header1").remove();
            $("#m_apply1").remove();
            $("#new1").remove();
            $("#dcl").addClass("active");
            $("#new2").addClass("active");
            sta1 = "市局财务科处理中,市局财务科转账中";
            sta2 = "已通知区县,市局规划科处理中,区县资金流向录入";
            dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
            ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();

        }else if(status == 3){
            $("#night").addClass("last");
            $("#kind1").text("资金申请");
            $("#kind2").text("资金申请");
            money_apply1.ajax.url("/capitalFlowForm.do?userstatus=3").load();
            sta1 = "区县资金流向录入";
            sta2 = "区县资金流向明细";
            dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
            ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
        }else if(status == 1){
            $("#night").removeClass("last");
            sta1 = "市局规划科处理中,市局规划科批复中";
            sta2 = "已通知区县,市局财务科转账中";
            console.log(sta1);
            $("#kind1").text("市局资金计划上报");
            $("#kind2").text("市局资金计划上报");
            money_apply1.ajax.url("/capitalFlowForm.do?userstatus=1").load();
            dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
            ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
        }

    }();




    // 清空数据
    function mywipeData() {
        $("#edit input").val("");

        $("#notice_content").val("");
    }

    function edit2(that) {
        $("#edit2").modal('show');
        var kind = $(that).val();
        var id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
        $.ajax({
            url: "/getCatipalDataById.do",
            type: "post",
            async: false,
            data: {id:id},
            dataType: "json",
            success: function (data) {
                var data = data.result;
                $("#apply_title_edit").val(data.title);
                $("#apply_person_edit").val(data.report_person);
                $("#apply_reason_edit").val(data.report_reason);
                $("#apply1").addClass("last");
                if(data.status == "市局规划科批复中"){
                    step2.goStep(1);
                    $(".user2_1").text(data.quxianshenqingren);
                }else if(data.status == "市局财务科转账中"){
                    step2.goStep(3);
                    $(".user2_1").text(data.quxianshenqingren);
                    $(".user2_2").text(data.guihuapifuren);
                }else if(data.status == "区县资金流向录入"){
                    step2.goStep(4);
                    $(".user2_1").text(data.quxianshenqingren);
                    $(".user2_2").text(data.guihuapifuren);
                    $(".user2_3").text(data.caiwuzhuanzhangren);
                    $(".user2_4").text(data.quxianbaocunren);
                }else if(data.status == "区县资金流向明细"){
                    step2.goStep(5);
                    console.log(data.quxianshenqingren,data.guihuapifuren,data.caiwuzhuanzhangren,data.quxianbaocunren,data.quxiantijiaoren)
                    $(".user2_1").text(data.quxianshenqingren);
                    $(".user2_2").text(data.guihuapifuren);
                    $(".user2_3").text(data.caiwuzhuanzhangren);
                    $(".user2_4").text(data.quxianbaocunren);
                    $(".user2_5").text(data.quxiantijiaoren);
                }
            }
        });
    }

    function edit(that) {
        if( status != 3){
            $("#edit2 .btn-success").css("display","none");
            var kind = $(that).val();
            var id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
            $("#edit .btn-primary").css("display","none");
            $.ajax({
                url: "/getCatipalDataById.do",
                type: "post",
                async: false,
                data: {id:id},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    var data = data.result;
                    if(data.initiatorclass == 1){
                        $("#edit").modal('show');
                        $("#report_title_edit").val(data.title);
                        $("#report_person_edit").val(data.report_person);
                        $("#report_quarter_edit").val(data.report_quarter);
                        $("#report_text_edit").text(data.report_text);
                        $("#money_source").val(data.money_source);
                        $("#arrival_time").val(data.arrival_time);
                        $("#amount").val(data.amount);
                        $("#guihuake_show1 .county_infos p").text(data.areaname);
                        $("#guihuake_show2 .not_content p").text(data.text);
                        if(data.status == "市局财务科处理中"){
                            step.goStep(1);
                            $(".user1_1").text(data.guihuakeshenqing);
                        }else if(data.status == "市局规划科处理中"){
                            step.goStep(2);
                            $(".user1_1").text(data.guihuakeshenqing);
                            $(".user1_2").text(data.caiwuchuliren);
                        }else if(data.status == "已通知区县"){
                            step.goStep(3);
                            $(".user1_1").text(data.guihuakeshenqing);
                            $(".user1_2").text(data.caiwuchuliren);
                            $(".user1_3").text(data.guihuachuliren);
                        }
                    }else if(data.initiatorclass == 3){
                        edit2(that);
                        var kind = $(that).val();
                        var state = $(that).parent("td").parent("tr").children("td:nth-child(6)").text();
                        console.log(status);
                        if(kind == "查看"){
                            $("#edit2 .btn-primary").css("display","none");
                            if(status == 1){
                                if(state == "市局规划科批复中"){
                                    $("#pifu").css("display","none");
                                }else if(state == "市局财务科转账中"){
                                    $("#pifu").css("display","block");
                                    $("#apply1").removeClass("last");
                                    $("#pifu").addClass("last");
                                    $("#pifu_content").val(data.replytext).attr("readonly",true);
                                }
                            }else if(status == 2){
                                if(state == "市局财务科转账中"){
                                    $("#pifu").css("display","block");
                                    $("#apply1").removeClass("last");
                                    $("#pifu").addClass("last");
                                    $("#chuli").css("display","none");
                                    $("#pifu_content").val(data.replytext).attr("readonly",true);
                                }else if(state == "区县资金流向录入"){
                                    $("#pifu").css("display","block");
                                    $("#apply1").removeClass("last");
                                    $("#pifu").removeClass("last");
                                    $("#chuli").css("display","block").addClass("last");
                                    $("#pifu_content").val(data.replytext).attr("readonly",true);
                                    $("#chuli_content").val(data.dealtext).attr("readonly",true);
                                }
                            }else if(status == 3){

                            }
                        }else if(kind == "编辑"){
                            $("#edit2 .btn-primary").css("display","inline-block");
                            if(status == 1){
                                if(state == "市局规划科批复中"){
                                    var username = $("#username").text();
                                    $("#pifu").css("display","block");
                                    $("#apply1").removeClass("last");
                                    $("#pifu").addClass("last");
                                    $("#edit2 .btn-primary").click(function () {
                                        var text = $("#pifu_content").val();
                                        if(text == ""){
                                            alert("批复内容不能为空");
                                            return;
                                        }else {
                                            //规划科批复
                                            $.ajax({
                                                url: "/setToAreaDataById.do",
                                                type: "post",
                                                dataType: "json",
                                                data:{id:id,replytext:text,username:username},
                                                success:function (data) {
                                                    console.log(data);
                                                    $("#edit2").modal("hide");
                                                    sta1 = "市局规划科处理中,市局规划科批复中";
                                                    sta2 = "已通知区县,市局财务科转账中";
                                                    dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
                                                    ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
                                                    $("#pifu_content").val("");
                                                }
                                            })
                                        }
                                    })
                                }
                            }else if(status == 2){
                                if(state == "市局财务科转账中"){
                                    var username = $("#username").text();
                                    $("#pifu").css("display","block");
                                    $("#apply1").removeClass("last");
                                    $("#pifu").removeClass("last");
                                    $("#chuli").css("display","block").addClass("last");
                                    $("#pifu_content").val(data.replytext).attr("readonly",true);
                                    $("#edit2 .btn-primary").click(function () {
                                        var text = $("#chuli_content").val();
                                        if(text == ""){
                                            alert("处理内容不能为空");
                                            return;
                                        }else {
                                            //财务科处理
                                            console.log(id,text);
                                            $.ajax({
                                                url: "/setDataById.do",
                                                type: "post",
                                                dataType: "json",
                                                data:{id:id,dealtext:text,username:username},
                                                success:function (data) {
                                                    console.log(data);
                                                    $("#edit2").modal("hide");
                                                    sta1 = "市局财务科处理中,市局财务科转账中";
                                                    sta2 = "已通知区县,市局规划科处理中,区县资金流向录入";
                                                    dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
                                                    ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
                                                    $("#chuli_content").val("");
                                                }
                                            })
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            });
            var state = $(that).parent("td").parent("tr").children("td:nth-child(6)").text();
            if( status == 2 ){
                if(kind == "查看"){
                    $("#guihuake_show1").css("display","none");
                    $("#guihuake_show2").css("display","none");
                    $("#guihuake").css("display","none");
                    if(state == "市局财务科处理中"){
                        $("#caiwu2").css("display","none");
                        $("#caiwu1").css("display","none");
                        $("#caiwu").addClass("last");
                    }else if(state == "市局规划科处理中"){
                        $("#caiwu").removeClass("last");
                        $("#caiwu2").css("display","block").addClass("last");
                        $("#caiwu1").css("display","block");
                        $("#money_source").attr("readonly",true);
                        $("#amount").attr("readonly",true);
                    }else if(state == "已通知区县"){
                        $("#caiwu").removeClass("last");
                        $("#caiwu2").css("display","block").removeClass("last");
                        $("#caiwu1").css("display","block");
                        $("#money_source").attr("readonly",true);
                        $("#amount").attr("readonly",true);
                        $("#guihuake_show1").css("display","block");
                        $("#guihuake_show2").css("display","block");
                    }
                }else if(kind == "编辑"){
                    var username = $("#username").text();

                    $("#caiwu").removeClass("last");
                    $("#caiwu1").css("display","block");
                    $("#caiwu2").css("display","block");
                    $("#guihuake_show1").css("display","none");
                    $("#guihuake_show2").css("display","none");
                    $("#guihuake").css("display","none");
                    $("#caiwu2").addClass("last");
                    if(state == "市局财务科处理中"){
                        $("#edit .btn-primary").css("display","inline-block");
                        $("#edit .btn-primary").text("通知规划科");
                        $("#money_source").attr("readonly",false);
                        $("#amount").attr("readonly",false);
                        $("#edit .btn-primary").click(function () {
                            var money_source = $("#money_source").val();
                            var arrival_time = $("#arrival_time").val();
                            var amount = $("#amount").val();
                            if(money_source == ""){
                                alert("款项来源不能为空")
                            }else if(arrival_time == ""){
                                alert("到款时间不能为空")
                            }else if(amount == ""){
                                alert("到款金额不能为空")
                            }else {
                                console.log(money_source,arrival_time,amount)
                                //财务科提交
                                $.ajax({
                                    url: "/setDataById.do",
                                    type: "post",
                                    async: false,
                                    data: {
                                        id :id,
                                        money_source:money_source,
                                        arrival_time:arrival_time,
                                        amount:amount,
                                        username: username
                                    },
                                    dataType: "json",
                                    success: function (data) {
                                        $("#edit").modal("hide");
                                        sta1 = "市局财务科处理中";
                                        sta2 = "市局规划科处理中,已通知区县";
                                        dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
                                        ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
                                        mywipeData();
                                    }
                                })
                            }
                        })
                    }else if(state == "市局规划科处理中"){
                        $("#caiwu").removeClass("last");
                        $("#money_source").attr("readonly",true);
                        $("#amount").attr("readonly",true);
                    }else if(state == "已通知区县"){
                        $("#caiwu2").css("display","block").removeClass("last");
                        $("#caiwu1").css("display","block");
                        $("#money_source").attr("readonly",true);
                        $("#amount").attr("readonly",true);
                        $("#guihuake_show1").css("display","block");
                        $("#guihuake_show2").css("display","block");
                    }

                }
            }else if(status == 1){
                $("#money_source").attr("readonly",true);
                $("#amount").attr("readonly",true);
                if(kind == "查看" ){
                    if (state == "市局财务科处理中"){
                        $("#caiwu").addClass("last");
                        $("#caiwu1").css("display","none");
                        $("#caiwu2").css("display","none");
                        $("#guihuake").css("display","none");
                        $("#guihuake_show1").css("display","none");
                        $("#guihuake_show2").css("display","none");
                    }else if(state == "市局规划科处理中"){
                        $("#caiwu").removeClass("last");
                        $("#caiwu1").css("display","block");
                        $("#caiwu2").css("display","block").addClass("last");
                        $("#guihuake").css("display","none");
                        $("#guihuake_show1").css("display","none");
                        $("#guihuake_show2").css("display","none");
                    }else if(state == "已通知区县"){
                        $("#caiwu").removeClass("last");
                        $("#caiwu2").css("display","block").removeClass("last");
                        $("#caiwu1").css("display","block");
                        $("#guihuake").css("display","none");
                        $("#guihuake_show1").css("display","block");
                        $("#guihuake_show2").css("display","block").addClass("last");
                    }
                }else if(kind == "编辑"){
                    if(state == "市局财务科处理中"){
                        $("#caiwu").addClass("last");
                        $("#caiwu1").css("display","none");
                        $("#caiwu2").css("display","none");
                        $("#guihuake").css("display","none");
                        $("#guihuake_show1").css("display","none");
                        $("#guihuake_show2").css("display","none");
                    }else if(state == "市局规划科处理中"){
                        var username = $("#username").text();
                        $("#caiwu").removeClass("last");
                        $("#caiwu1").css("display","block");
                        $("#caiwu2").css("display","block").removeClass("last");
                        $("#guihuake_show1").css("display","none");
                        $("#guihuake_show2").css("display","none");
                        $("#guihuake").css("display","block");
                        $("#edit .btn-primary").css("display","inline-block");
                        $("#edit .btn-primary").text("通知区县");
                        $("#edit .btn-primary").click(function () {
                            var arr = [];
                            $.each($(".selected"),function (i,n) {
                                arr.push(n.innerText);
                            });
                            var text = $("#notice_content").val();
                            if(arr.length == 0){
                                alert("请选取区县")
                            }else if(text == ""){
                                alert("请输入通知内容")
                            }else{
                                //规划科提交
                                $.ajax({
                                    url: "/setToAreaDataById.do",
                                    type: "post",
                                    async: false,
                                    data: {id:id,areanames:arr,text:text,username:username},
                                    dataType: "json",
                                    success: function (data) {
                                        console.log(data);
                                        $("#edit").modal("hide");
                                        money_apply1.ajax.url("/capitalFlowForm.do?userstatus=1").load();
                                        sta1 = "市局规划科处理中";
                                        sta2 = "已通知区县";
                                        dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
                                        ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
                                        mywipeData();
                                        $("#uc_03 li").removeClass("selected");
                                    }
                                });
                            }
                        })
                    }else if(state == "已通知区县"){
                        $("#caiwu").removeClass("last");
                        $("#caiwu1").css("display","block");
                        $("#caiwu2").css("display","block").removeClass("last");
                        $("#guihuake").css("display","none");
                        $("#guihuake_show1").css("display","block");
                        $("#guihuake_show2").css("display","block").addClass("last");
                    }

                }
            }
        }else if(status == 3){
            edit2(that);
            var kind = $(that).val();
            var id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
            var state = $(that).parent("td").parent("tr").children("td:nth-child(6)").text();
            $.ajax({
                url: "/getCatipalDataById.do",
                type: "post",
                async: false,
                data: {id:id},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    var data = data.result;
                    if(kind == "查看"){
                        $("#edit2 .btn-success").css("display","none");
                        $("#edit2 .btn-primary").css("display","none");
                        $("#pifu").css("display","block");
                        $("#apply1").removeClass("last");
                        $("#pifu").removeClass("last");
                        $("#chuli").css("display","block").removeClass("last");
                        $("#pifu_content").val(data.replytext).attr("readonly",true);
                        $("#chuli_content").val(data.dealtext).attr("readonly",true);
                        $("#luru_content").val(data.capitalflowinstruction).attr("readonly",true);
                        $("#luru").css("display","block").addClass("last");
                    }else if(kind == "编辑"){
                        if(state == "区县资金流向录入"){
                            $("#edit2 .btn-success").css("display","inline-block");
                            $("#edit2 .btn-primary").css("display","inline-block");
                            $("#pifu").css("display","block");
                            $("#apply1").removeClass("last");
                            $("#pifu").removeClass("last");
                            $("#chuli").css("display","block").removeClass("last");
                            $("#pifu_content").val(data.replytext).attr("readonly",true);
                            $("#chuli_content").val(data.dealtext).attr("readonly",true);
                            $("#luru_content").val(data.capitalflowinstruction).attr("readonly",false);
                            $("#luru").css("display","block").addClass("last");
                            $("#edit2 .btn-primary").click(function () {
                                var username = $("#username").text();
                                var text = $("#luru_content").val();
                                if(text == ""){
                                    alert("录入内容不能为空");
                                    return;
                                }else {
                                    //区县提交
                                    console.log(id,text);
                                    $.ajax({
                                        url: "/countySubmitDataById.do",
                                        type: "post",
                                        dataType: "json",
                                        data:{id:id,capitalflowinstruction:text,username:username},
                                        success:function (data) {
                                            console.log(data);
                                            $("#edit2").modal("hide");
                                            money_apply1.ajax.url("/capitalFlowForm.do?userstatus=3").load();
                                            sta1 = "区县资金流向录入";
                                            sta2 = "区县资金流向明细";
                                            dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
                                            ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
                                            $("#luru_content").val("");
                                        }
                                    })
                                }
                            });
                            //区县保存
                            $("#edit2 .btn-success").click(function () {
                                var text = $("#luru_content").val();
                                var username = $("#username").text();
                                var str = data.quxianbaocunren;
                                if(str != ""){
                                    username = username +","+ str;
                                }
                                console.log(data.quxianbaocunren);
                                if(text == ""){
                                    alert("录入内容不能为空");
                                    return;
                                }else {
                                    console.log(id,text);
                                    $.ajax({
                                        url: "/countySaveDataById.do",
                                        type: "post",
                                        dataType: "json",
                                        data:{id:id,capitalflowinstruction:text,username:username},
                                        success:function (data) {
                                            console.log(data);
                                            $("#edit2").modal("hide");
                                            sta1 = "区县资金流向录入";
                                            sta2 = "区县资金流向明细";
                                            dcl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta1))).load();
                                            ycl_table.ajax.url("/pendingCapitalFlow.do?capitalstatus=" + encodeURI(encodeURI(sta2))).load();
                                            $("#luru_content").val("");
                                        }
                                    })
                                }
                            });
                        }else if(state == "区县资金明细"){
                            $("#edit2 .btn-success").css("display","none");
                            $("#edit2 .btn-primary").css("display","none");
                            $("#pifu").css("display","block");
                            $("#apply1").removeClass("last");
                            $("#pifu").removeClass("last");
                            $("#chuli").css("display","block").removeClass("last");
                            $("#pifu_content").val(data.replytext).attr("readonly",true);
                            $("#chuli_content").val(data.dealtext).attr("readonly",true);
                            $("#luru_content").val(data.capitalflowinstruction).attr("readonly",true);
                            $("#luru").css("display","block").addClass("last");
                        }
                    }
                }
            });


        }
    }

    function newForm() {

//        $('#money_apply_wdo').modal('show');
          $('#select_model').modal('show');
    }
    var flag = false;
    $("#first").click(function () {
        flag = !flag;
        if(flag == true){
            $("#more").height(760);
        }else {
            $("#more").height(30);
        }
    })

    function applyForm() {
        $('#money_apply_wdo').modal('show');
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