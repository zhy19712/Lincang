<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>临沧市移民局</title>
	<link rel="stylesheet" href="../../css/mybs.css">
	<link href="../../css/app.css" rel="stylesheet">
	<link rel="stylesheet" href="../../css/reset.css">
	<link rel="stylesheet" href="../../css/lincang-yimin.css">
	<link rel="stylesheet" href="../../css/search.css">
	<link rel="stylesheet" href="../../css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="../../css/jedate.css">
	<link rel="stylesheet" href="../../css/style.css">
	<link rel="shortcut icon" href="../../img/favicon.ico">
	<link rel="stylesheet" href="../../css/mybs.css">
	<link rel="stylesheet" href="../../css/app.css">

	<script src="../../js/jquery.min.js"></script>
	<script src="../../js/jquery.cookie.js"></script>
	<script src="../../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=LTqCYH5OsxDXuPREC7tYCrgRGjVtaLQw"></script>
	<script src="../../js/zUI.js"></script>
	<script src="../../js/jquery.jedate.js"></script>
	<script src="../../js/echarts.common.min.js"></script>
	<script src="../../js/jQuery.print.js"></script>
	<script src="../../js/jquery-form.min.js"></script>
	<script src="../../js/lincang-yimin.js"></script>

</head>
<body>
	<div style="margin-bottom: 0;" class="navbar navbar-default" role="navigation">

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
						欢迎${user.username}<a href="logout.do" >注销</a>
					</c:if>
				</div>
			</div>
			<span id="status" style="display:none;width:0;height:0;">${user.level}</span>
			<span id="username" style="display:none;width:0;height:0;">${user.username}</span>
			<!-- user dropdown ends -->
		</div>
	</div>
	<div id="content">
		<div id="container-wrapper">
			<div id="container"></div>
			<div id="show_info">
				<div id="back">返回地图</div>
				<div id="tab">
					<ul id="tab_list">
						<li>移民信息</li>
						<li>统计分析</li>
					</ul>
					<ul id="tab_content">
						<li>
							<div id="table-wrapper">
								<table id="table1" width="100%" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>分类</th>
											<th>编号</th>
											<th>户主姓名</th>
											<th>所属水库</th>
											<th>所在地(迁入地)</th>
											<th>调查人</th>
											<th>填表时间</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</li>
						<li>
							<div id="container1"></div>
							<div id="container2"></div>
							<%--<div id="div1" style="width: 300px;height: 100px;background-color:red;"></div>--%>
							<%--<div id="div2" style="width: 300px;height: 100px;background-color:blue;"></div>--%>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div id="slide">
			<div class="left">
				<ul class="nav">
					<li>
						<i></i>
						<p>移民分析</p>
					</li>
					<li>
						<i></i>
						<p>移民登记</p>
					</li>
					<%--<li>--%>
						<%--<i></i>--%>
						<%--<p>统计分析</p>--%>
					<%--</li>--%>
				</ul>
			</div>
			<div class="right">
				<p class="title">为您找到<span id="total_people"></span>移民</p>
				<div id="sel_city">
					<!-- <select class="prov"></select> 
                    <select class="city" disabled="disabled"></select>
                    <select class="dist" disabled="disabled"></select> -->
                    <div align="center" style="width: 230px;float: left;margin-top: 10px;">
				        <input type="text" style="width: 217px;padding: 0 2px;height: 28px;outline:none;border-radius: 3px 0 0 3px;" placeholder="请输入地区名称" id="ipt">

				    </div>

				    <div class="auto_hidden" id="auto"></div>
				    <div id="btn" style="border: none;background-color: #990101;color: white;padding: 8px;float: left;cursor: pointer;border-radius: 0px 3px 3px 0px;transition: all 0.2s ease-out 0s;margin-top: 10px;margin-left: -3px;">搜索</div>
				</div>
				<div class="city_info">
					<div class="icon">
						<div class="circle">
							<div></div>
						</div>
						<div class="triangle"></div>
					</div>
					<div class="info">
						<h2 class="name">临沧市</h2>
						<p class="count">共6个区域</p>
					</div>
				</div>
				<div id="show">
					<ul>
					</ul>
				</div>
			</div>
		</div>
		<div id="data_input">
            <div id="new_table">
				<iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                <form style="margin-top: 10px;" id="excel" target="uploadFrame" enctype="multipart/form-data">
                    <ul style="display: inline-block;">
						<li><a data-toggle="tooltip" title="新建库区登记表" class="well top-block"
							   href="../../kuquanzhi.jsp" style="padding: 16px 0;border-radius: 6px;">
							<i class="glyphicon glyphicon-pencil blue"></i>

							<div>新建库区登记表</div>

						</a>
						</li>
						<li><a data-toggle="tooltip" title="新建移民搬迁登记表" class="well top-block"
							   href="../../yiminbanqian.jsp" style="padding: 16px 0;border-radius: 6px;">
							<i class="glyphicon glyphicon-pencil blue"></i>

							<div>新建移民搬迁登记表</div>

						</a>
						</li>
                    </ul>
					<div id="filesUpload" style="display:inline-block;line-height:16px;text-overflow:ellipsis; white-space:nowrap; overflow:hidden;">
						<a href="#" id="add_1" onclick="add_click_file(1)">导入Excel</a>
						<input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
					</div>
					<button id="submit" type="submit" style="vertical-align: middle;margin-bottom: 10px;">确认导入</button>
				</form>
				<div id="allinfo_table_wrapper">
					<table id="allinfo_table" width="100%" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>分类</th>
								<th>编号</th>
								<th>户主姓名</th>
								<th>所属水库</th>
								<th>所在地(迁入地)</th>
								<th>调查人</th>
								<th>填表时间</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
            </div>
		</div>
		<div id="data_analysis">统计分析</div>
	</div>
</body>
<script>
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

    //导入Excel回调
	$("#submit").click(function () {
        var options  = {
            url:'/excel/multipleExcelUpLoadExcel.do',
            type:'post',
            success:function(data)
            {
                console.log(data);
                var data = JSON.parse(data);
                if(data.result == "success"){
                    alert("提交成功");
                }else {
                    alert("请导入正确格式的Excel文件");
                }
            }
        };
        $("#excel").ajaxSubmit(options);
    })
    //查看或编辑
    function edit(that) {
        var kind = $(that).val();
        kind = encodeURI(encodeURI(kind));
        var table_kind = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
        var id = $(that).parent("td").parent("tr").children("td:nth-child(2)").text();
        id = encodeURI(encodeURI(id));
        if(table_kind == "库区安置登记表"){
            window.open("/anzhi_detail.jsp?kind=" + kind + "&id=" + id);
        }else if(table_kind == "移民搬迁登记表"){
            window.open("/banqian_detail.jsp?kind=" + kind + "&id=" + id);
		}
    }
</script>
</html>