<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/28
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${msg}
    </div>
    <div>
        <form action ="/login.do" method = "get">
            账号：<input id = "account" type="text" name="username">
            <br/>
            密码：<input id = "password" type="password" name="password">
            <br/>
            <span>自动登陆</span>
            <input type="checkbox" value="1" name="login_auto_login" />
            <br/>
            <input type="submit" value="提交"/>
        </form>
    </div>
</body>
</html>
