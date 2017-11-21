<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/16
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>异常页</title>
  </head>
  <body>
      <div>
          服务器异常，请稍等一会在访问！
          <br/>
          <span style="margin: 0 6px;">${exception}</span>
      </div>
  </body>
</html>
