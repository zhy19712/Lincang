<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/8/16
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
      <title>文件上传下载</title>
      <script type='text/javascript' src='<%=path %>/js/jquery.min.js'></script>
      <script language="javascript">
          var indexindex = 1;
          function add_click_file(index){
                  $("#add_file_"+indexindex).click();
          }
          function add(index) {
              var len = $("#add_file_" + (indexindex) + "").val().split("\\").length;
              alert($("#add_file_" + (indexindex) + "").val().split("\\")[len - 1]);
              var num = $("#add_file_" + (indexindex) + "").val().split("\\")[len - 1];
              $("#filesupload").append('<span  id="add_file_span_' + (indexindex) + '">' + $("#add_file_" + (indexindex) + "").val().split("\\")[len - 1] + '</span>');
              $("#filesupload").append('<a   id="add_file_a_' + (indexindex) + '" href="javascript:delFile(' + indexindex+ ')">删除</a>');
              $("#filesupload").append('<input style="display:none;" id="add_file_' + (indexindex + 1) + '" type="file" name = "files" onChange="add(' + (indexindex + 1) + ')"/>');
              ++indexindex;
          }
          function delFile(number) {
              var o=document.getElementById("filesupload");//获取父节点
              var int=document.getElementById("add_file_" + number+"");//获取需要删除的子节点
              var a=document.getElementById("add_file_a_" + number+"");//获取需要删除的子节点
              var span=document.getElementById("add_file_span_" + number+"");//获取需要删除的子节点
              o.removeChild(int)//从父节点o上面移除子节点a
              o.removeChild(a)
              o.removeChild(span)
          }
      </script>
  </head>
  <body>
      <form action="/file/multipleUpload.do" method="post" enctype="multipart/form-data">
          <div id="filesupload">
              <a href="#" id="add_1" onclick="add_click_file(1)"  >添加附件</a>
              <input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
          </div>
          <input type="submit" value="提交"/>
      </form>
      <form action="/excel/readExcel.do" method="post" enctype="multipart/form-data">
          <input type="submit" value="提交"/>
      </form>
  </body>
</html>
