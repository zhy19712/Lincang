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
    <title>两科室意见</title>
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
            border: none;
            resize: none;
            width: 99%;
        }
        .left{
            text-align: left;
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
            <td>来文单位</td>
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
            <td class="middle">文件标题</td>
            <td colspan="11"><textarea name="" cols="30" rows="10"></textarea></td>
        </tr>
        <tr>
            <td class="middle" rowspan="3">
                拟办意见
            </td>
            <td colspan="5" class="botttom_none">
            </td>
            <td colspan="6">
                科室意见
            </td>
        </tr>
        <tr>
            <td colspan="5" class="botttom_none">
            </td>
            <td colspan="3"><input type="text" style="width: 110px;font-size: 16px;line-height: 20px;">科</td>
            <td colspan="3"><input type="text" style="width: 110px;font-size: 16px;line-height: 20px;">科</td>
        </tr>
        <tr>
            <td colspan="5">
                <textarea name="" cols="30" rows="10"></textarea>
            </td>
            <td colspan="3"><textarea name="" cols="30" rows="10"></textarea></td>
            <td colspan="3"><textarea name="" cols="30" rows="10"></textarea></td>
        </tr>
        <tr>
            <td class="middle">主要领导批示意见</td>
            <td colspan="11">
                <textarea name="" cols="30" rows="10"></textarea>
            </td>
        </tr>
        <tr>
            <td class="middle">分管领导批示</td>
            <td colspan="11">
                <textarea name="" cols="30" rows="10"></textarea>
            </td>
        </tr>
        <tr>
            <td class="middle">办理结果</td>
            <td colspan="11">
                <textarea name="" cols="30" rows="10"></textarea>
            </td>
        </tr>
        </tbody>
    </table>
    <button type="submit">提交</button>
</form>
</body>
</html>

