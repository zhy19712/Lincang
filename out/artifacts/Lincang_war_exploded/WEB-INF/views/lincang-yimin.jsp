<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="../../css/mybs.css">
	<link href="../../css/app.css" rel="stylesheet">
	<link rel="stylesheet" href="../../css/reset.css">
	<link rel="stylesheet" href="../../css/lincang-yimin.css">
	<link rel="stylesheet" href="../../css/search.css">
	<link rel="stylesheet" href="../../css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="../../css/jedate.css">
	<link rel="stylesheet" href="../../css/style.css">

	<script src="../../js/jquery.min.js"></script>
	<script src="../../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=LTqCYH5OsxDXuPREC7tYCrgRGjVtaLQw"></script>
	<script src="../../js/zUI.js"></script>
	<script src="../../js/jquery.jedate.js"></script>
	<script src="../../js/echarts.common.min.js"></script>
	<script src="../../js/jQuery.print.js"></script>
	<script src="../../js/lincang-yimin.js"></script>
</head>
<body>
	<div id="header">
		<img src="../../img/logo.png" alt="" class="logo">
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
				<p class="title">为您找到<span>41364</span>移民</p>
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
                <form action="" target="uploadFrame">
                    <ul>
						<li><a data-toggle="tooltip" title="新建库区安置登记表" class="well top-block"
							   href="../../kuquanzhi.jsp" style="padding: 16px 0;border-radius: 6px;">
							<i class="glyphicon glyphicon-pencil blue"></i>

							<div>新建库区安置登记表</div>

						</a>
						</li>
						<li><a data-toggle="tooltip" title="新建移民搬迁登记表" class="well top-block"
							   href="../../yiminbanqian.jsp" style="padding: 16px 0;border-radius: 6px;">
							<i class="glyphicon glyphicon-pencil blue"></i>

							<div>新建移民搬迁登记表</div>

						</a>
						</li>
						<div id="filesUpload" style="width:80%;text-overflow:ellipsis; white-space:nowrap; overflow:hidden;">
							<a href="#" id="add_1" onclick="add_click_file(1)">上传库区安置登记表</a>
							<input style="display:none;" id="add_file_1" type="file" name = "files" onChange="add(1)"/>
						</div>
                    </ul>
					<button type="submit">确认上传</button>
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
    //查看或编辑
    function edit(that) {
        var kind = $(that).val();
        window.open("http://www.jb51.net")
    }
</script>
</html>