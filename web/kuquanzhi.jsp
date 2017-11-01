<%--
  Created by IntelliJ IDEA.
  User: zhangchuan
  Date: 2017/10/20
  Time: 16:58
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
    <title>库区安置登记表</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/jedate.css">
    <link rel="stylesheet" href="css/lincang-yimin.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="shortcut icon" href="img/favicon.ico">
    <link rel="stylesheet" href="css/mybs.css">
    <link rel="stylesheet" href="css/app.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.jedate.js"></script>
    <script src="js/table.js"></script>
    <style>
        ::-webkit-input-placeholder { /* WebKit browsers */
            color:    red;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:    red;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    red;
        }
        :-ms-input-placeholder { /* Internet Explorer 10+ */
            color:    red;
        }
    </style>
</head>
<body>
<div style="margin-bottom: 0;height: 70px;" class="navbar navbar-default" role="navigation">

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
                    欢迎<span style="margin: 0 6px;">${user.name}</span><a href="logout.do" >注销</a>
                </c:if>
            </div>
        </div>
        <span id="status" style="display:none;width:0;height:0;">${user.level}</span>
        <span id="username" style="display:none;width:0;height:0;">${user.username}</span>
        <!-- user dropdown ends -->
    </div>
</div>
    <form action="" style="width: 96%;margin: 10px auto;">
        <table id="jqtable">
            <tbody>
            <tr>
                <td colspan="9" id="kind">库区安置登记表</td>
            </tr>
            <tr>
                <td rowspan="2" class="bgc">户主信息</td>
                <td class="bgc">所属水库</td>
                <td colspan="1"><input type="text" id="reservoir" required="required" placeholder="*此项为必填项"></td>
                <td class="bgc">户主姓名</td>
                <td colspan="2"><input type="text" id="householder" required="required" placeholder="*此项为必填项"></td>
                <td class="bgc">联系电话</td>
                <td colspan="2"><input type="text" id="number"></td>
            </tr>
            <tr>
                <td class="bgc">开户人姓名</td>
                <td><input type="text" id="bank-user"></td>
                <td class="bgc">开户行名称</td>
                <td colspan="2"><input type="text" id="bank-name"></td>
                <td class="bgc">银行卡号</td>
                <td colspan="2"><input type="text" id="bank-number"></td>
            </tr>
            <tr>
                <td rowspan="5" class="bgc">家庭信息</td>
                <td class="bgc">姓名</td>
                <td class="bgc" colspan="2">身份证号码</td>
                <td class="bgc">性别</td>
                <td class="bgc">民族</td>
                <td class="bgc">与户主关系</td>
                <td class="bgc">文化程度</td>
                <td class="bgc">职业</td>
            </tr>
            <tr id="home_people1">
                <td><input type="text" required="required" placeholder="*此项为必填项"></td>
                <td colspan="2"><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
            </tr>
            <tr id="home_people2">
                <td><input type="text"></td>
                <td colspan="2"><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
            </tr>
            <tr id="home_people3">
                <td><input type="text"></td>
                <td colspan="2"><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
            </tr>
            <tr id="home_people4">
                <td><input type="text" class="h_name"></td>
                <td colspan="2"><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td class="bgc" rowspan="2">所在地</td>
                <td class="bgc">州市</td>
                <td class="bgc">区县</td>
                <td class="bgc">乡镇</td>
                <td class="bgc">村</td>
                <td class="bgc">组</td>
                <td class="bgc" colspan="3">备注</td>
            </tr>
            <tr id="city">
                <td><input type="text" required="required" placeholder="*此项为必填项"></td>
                <td><input type="text" required="required" placeholder="*此项为必填项"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr>
                <td class="bgc" rowspan="3">住房情况</td>
                <td class="bgc">分类</td>
                <td class="bgc">宅基地面积</td>
                <td class="bgc">砖混结构</td>
                <td class="bgc">砖木结构</td>
                <td class="bgc">土木结构</td>
                <td class="bgc">木（竹）结构</td>
                <td class="bgc">简易房</td>
                <td class="bgc">备注</td>
            </tr>
            <tr id="main">
                <td class="bgc">主房</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
            </tr>
            <tr id="sub">
                <td class="bgc">附属房</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td class="bgc" rowspan="15">收入情况</td>
                <td class="bgc">分类</td>
                <td class="bgc">内容</td>
                <td class="bgc">数量</td>
                <td class="bgc">单价</td>
                <td class="bgc">小计</td>
                <td class="bgc" colspan="3">备注</td>
            </tr>
            <tr id="in_animal1">
                <td class="bgc" rowspan="8">养殖业收入</td>
                <td class="bgc">猪</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_animal2">
                <td class="bgc">牛</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_animal3">
                <td class="bgc">羊</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_animal4">
                <td class="bgc">鸡</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_animal5">
                <td class="bgc">鸭</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_animal6">
                <td class="bgc">渔业</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_animal7">
                <td class="bgc">乳业</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_animal8">
                <td class="bgc">其他</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_botany1">
                <td class="bgc" rowspan="4">种植业收入</td>
                <td class="bgc">粮食</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_botany2">
                <td class="bgc">蔬菜</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_botany3">
                <td class="bgc">水果</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_botany4">
                <td class="bgc">其他</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_other1">
                <td class="bgc" rowspan="2">其他收入</td>
                <td class="bgc">劳务酬劳</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="in_other2">
                <td class="bgc">房、耕地租赁</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr>
                <td class="bgc" rowspan="19">支出情况</td>
                <td class="bgc">分类</td>
                <td class="bgc">内容</td>
                <td class="bgc">数量</td>
                <td class="bgc">单价</td>
                <td class="bgc">小计</td>
                <td class="bgc" colspan="3">备注</td>
            </tr>
            <tr id="out_botany1">
                <td class="bgc" rowspan="6">种植业支出</td>
                <td class="bgc">籽种</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_botany2">
                <td class="bgc">化肥、农药</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_botany3">
                <td class="bgc">雇工</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_botany4">
                <td class="bgc">机耕支出</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_botany5">
                <td class="bgc">灌溉水电费</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_botany6">
                <td class="bgc">承租耕地</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_animal1">
                <td class="bgc" rowspan="4">养殖业支出</td>
                <td class="bgc">幼种</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_animal2">
                <td class="bgc">饲料</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_animal3">
                <td class="bgc">疫病防治</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_animal4">
                <td class="bgc">其他</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life1">
                <td class="bgc" rowspan="8">生活支出</td>
                <td class="bgc">主食</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life2">
                <td class="bgc">衣物</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life3">
                <td class="bgc">水、电费</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life4">
                <td class="bgc">通讯费</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life5">
                <td class="bgc">交通费</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life6">
                <td class="bgc">教育</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life7">
                <td class="bgc">医疗</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr id="out_life8">
                <td class="bgc">其他</td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td><input type="text"></td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr>
                <td class="bgc">其他信息</td>
                <td class="bgc">是否建立档案卡</td>
                <td>
                    <ul id="decide">
                        <li>
                            <input type="checkbox" id="yes"><label for="yes">是</label>
                        </li>
                        <li>
                            <input type="checkbox" id="no"><label for="no">否</label>
                        </li>
                    </ul>
                </td>
                <td class="bgc">致贫原因</td>
                <td colspan="5">
                    <ul id="reason">
                        <li>
                            <input type="checkbox" id="traffic" value="交通落后">
                            <label for="traffic">交通落后</label>
                        </li>
                        <li>
                            <input type="checkbox" id="money" value="缺技术、资金、土地、水">
                            <label for="money">缺技术、资金、土地、水</label>
                        </li>
                        <li>
                            <input type="checkbox" id="disaster" value="因学、残、灾">
                            <label for="disaster">因学、残、灾</label>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td class="bgc">被调查人签字</td>
                <td colspan="2"><input type="text" id="respondent"></td>
                <td class="bgc">调查人签字</td>
                <td colspan="2"><input type="text" id="inquirer" required="required" placeholder="*此项为必填项"></td>
                <td class="bgc">填表时间</td>
                <td colspan="2"><input type="text" id="time" readonly="readonly" required="required" placeholder="*此项为必填项"></td>
            </tr>
            </tbody>
        </table>
        <ul id="btn-container">
            <li><a class="hvr-rectangle-in button">提交</a></li>
            <li><a class="hvr-bounce-to-bottom button">放弃</a></li>
        </ul>
    </form>
</body>
</html>
