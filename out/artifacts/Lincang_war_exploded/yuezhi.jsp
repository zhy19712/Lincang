<%--
  Created by IntelliJ IDEA.
  User: zhangchuan
  Date: 2017/10/23
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>阅知</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/jedate.css">
    <link rel="stylesheet" href="css/lincang-yimin.css">
    <style>
        form{
            width: 96%;
            margin: 0 auto;
        }
        #title{
            text-align: center;
            font-weight: 700;
            margin: 10px 0;
        }
        #title input{
            width: 100%;
            text-align: center;
            outline: none;
            border: none;
            font-weight: 700;
            font-size: 20px;
        }
        table{
            width: 100%;
            border-top: 1px solid #000;
            border-left: 1px solid #000;
        }
        table td{
            padding: 5px 0;
            text-align: center;
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
        }
        table input{
            width: 100%;
            outline: none;
            border: none;
            padding: 0 5px;
        }
        table select{
            width: 94%;
            margin-left: 3%;
        }
        button{
            float: right;
            width: 100px;
            line-height: 30px;
            margin-top: 20px;
        }
        .middle{
            vertical-align: middle;
        }
        .botttom_none{
            border-bottom: none;
        }
        textarea{
            outline: none;
        }
    </style>
</head>
<body>
<div id="header">
    <img src="img/logo.png" alt="" class="logo">
    <div class="search" style="font-size:16px;">
        <c:if test="${user==null}">
            <a href="/toLogin.htm" target="_blank">请登录</a>
        </c:if>
        <c:if test="${user!=null}">
            欢迎${user.username}, <a href="logout.do" >注销</a>
        </c:if>
    </div>
    <span id="username" style="display:none;width:0;height:0;">${user.username}</span>
</div>
<form action="">
    <p id="title"><input type="text" value="临沧市移民局文件处理笺"></p>
    <table>
        <tbody>
            <tr>
                <td>收文号</td>
                <td><input type="text"></td>
                <td>来文机关</td>
                <td><input type="text"></td>
                <td>来文号</td>
                <td><input type="text"></td>
                <td>缓急</td>
                <td><input type="text"></td>
                <td>密级</td>
                <td><input type="text"></td>
                <td>份数</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>文件标题</td>
                <td colspan="11"><input type="text"></td>
            </tr>
            <%--<tr>--%>
                <%--<td rowspan="4" colspan="1">拟办意见</td>--%>
                <%--<td colspan="11" class="botttom_none"><p style="height: 20px;line-height: 20px;">呈：<input type="text" style="width:70px;line-height: 20px;font-size:16px;text-align: center;">阅知</p></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="11" class="botttom_none"><input type="text">阅知</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="11" class="botttom_none"><input type="text"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="11">局办公室<br>xxx年xx月xx日</td>--%>
            <%--</tr>--%>
            <tr>
                <td class="middle">拟办意见</td>
                <td colspan="11">
                    <textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>
                </td>
            </tr>
            <tr>
                <td class="middle">主要领导批示</td>
                <td colspan="11">
                    <textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>
                </td>
            </tr>
            <tr>
                <td class="middle">办理结果</td>
                <td colspan="11">
                    <textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td class="middle">分管领导批示</td>--%>
                <%--<td colspan="11">--%>
                    <%--<textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td rowspan="3" class="middle">办理结果</td>--%>
                <%--<td colspan="5" rowspan="3"><input type="text"></td>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
                <%--<td colspan="2"><input type="text"></td>--%>
            <%--</tr>--%>
        </tbody>
    </table>
    <button type="submit">提交</button>
</form>
</body>
</html>
