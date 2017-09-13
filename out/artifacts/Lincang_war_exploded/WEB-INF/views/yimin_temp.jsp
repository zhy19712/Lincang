<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/16
  Time: 14:22
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
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>首页</title>

  </head>
  <body>
      <h2>临沧市移民项目</h2>
      <c:if test="${user==null}">
          <a href="/toLogin.htm" target="_blank">请登录</a>
      </c:if>
      <c:if test="${user!=null}">
          欢迎${user.username},<a href="logout.do" >注销</a>
      </c:if>


      <form action="/file/multipleUpload.do" method="post" enctype="multipart/form-data">

          <div id="filesUpload">
              <a href="#" id="add_1" onclick="add_click_file(1)"  >添加附件</a>
              <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
          </div>
          test:<input type="text" name = "test"/>
          <input type="submit" value="提交"/>
      </form>


      <div>
          <c:if test="${fileerror.size() > 0}">
          <c:forEach items="${fileerror}" var="err">
              <div>
                      ${err}
              </div>
              <br>
          </c:forEach>
      </c:if>
      </div>
      <div>
          <c:if test="${fileerror.size() == 0}">
              <sapn>上传成功！</sapn>
          </c:if>
      </div>





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
                          ${err}
                  </div>
                  <br>
              </c:forEach>
          </c:if>
      </div>
      <div>
          <c:if test="${error.size() == 0}">
              <sapn>录入成功！</sapn>
          </c:if>
      </div>
      <iframe id="test" name="test" style="display:none;"></iframe>

      <form action="/queryStuffByOid.do" method="post" target="test">
          <input type="text" name = "oid"/>
          <input type="submit" value="查看按钮测试"/>
      </form>
      <form action="/deleteStuffByOid.do" method="post" target="test">
          <input type="text" name = "oid"/>
          <input type="submit" value="删除按钮测试"/>
      </form>
  </body>




  <script type='text/javascript' src='<%=path %>/js/jquery.min.js'></script>
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
          var o=document.getElementById("filesUpload");//获取父节点
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


</html>
