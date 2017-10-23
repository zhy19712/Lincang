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
    <title>收文登记</title>
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
        textarea{
            outline: none;
            border: none;
            resize: none;
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
    <p id="title">收文登记</p>
    <table>
        <tbody>
            <tr>
                <td>年度</td>
                <td><input type="text" name="year"></td>
                <td>保管期限</td>
                <td><select name="time_limit" id="">
                    <option value="1个月">1个月</option>
                    <option value="6个月">6个月</option>
                    <option value="1年">1年</option>
                    <option value="2年">2年</option>
                </select></td>
                <td>类别</td>
                <td colspan="2"><input type="text" name="kind"></td>
                <td>来文日期</td>
                <td colspan="2"><input type="text" name="document_time1"></td>
            </tr>
            <tr>
                <td>文件编号</td>
                <td colspan="2"><input type="text"></td>
                <td>登记号</td>
                <td><input type="text"></td>
                <td>全宗号</td>
                <td><input type="text"></td>
                <td>成文日期</td>
                <td colspan="2"><input type="text"></td>
            </tr>
            <tr>
                <td style="vertical-align: middle;">题名</td>
                <td colspan="9">
                    <textarea name="" cols="30" rows="10" style="width: 99%;outline: none;"></textarea>
                </td>
            </tr>
            <tr>
                <td>主题词</td>
                <td colspan="4"><input type="text"></td>
                <td>责任者</td>
                <td colspan="4"><input type="text"></td>
            </tr>
            <tr>
                <td>归档份数</td>
                <td><input type="text"></td>
                <td>页数</td>
                <td><input type="text"></td>
                <td>密级</td>
                <td><input type="text"></td>
                <td>机构问题</td>
                <td><input type="text"></td>
                <td>收件人</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>来文机关</td>
                <td colspan="5"><input type="text"></td>
                <td>附件页数</td>
                <td colspan="3"><input type="text"></td>
            </tr>
            <tr>
                <td>实体分类号</td>
                <td colspan="2"><input type="text"></td>
                <td>分发情况</td>
                <td colspan="3"><input type="text"></td>
                <td>旧全宗</td>
                <td colspan="2"><input type="text"></td>
            </tr>
            <tr>
                <td>归档情况</td>
                <td colspan="3"><input type="text"></td>
                <td>登记日期</td>
                <td colspan="2"><input type="text"></td>
                <td>传阅情况</td>
                <td colspan="2"><input type="text"></td>
            </tr>
            <tr>
                <td style="vertical-align: middle;">处理情况</td>
                <td colspan="9"><textarea name="" id="" cols="30" rows="10" style="width: 99%;"></textarea></td>
            </tr>
        </tbody>
    </table>
    <button type="submit">提交</button>
</form>
</body>
</html>
