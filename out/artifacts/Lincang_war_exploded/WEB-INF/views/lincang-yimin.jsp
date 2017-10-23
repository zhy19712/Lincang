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
											<th>户主姓名</th>
											<th>所属水库</th>
											<th>所在地</th>
											<th>迁入地</th>
											<th>调查人</th>
											<th>填表时间</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</li>
						<li>
							tab1
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
				<button id="test">测试</button>
				<div id="allinfo_table_wrapper">
					<table id="allinfo_table" width="100%" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>分类</th>
								<th>户主姓名</th>
								<th>所属水库</th>
								<th>所在地</th>
								<th>迁入地</th>
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
		<div id="edit1">
			<form action="" style="width: 96%;margin: 10px auto;">
				<table id="jqtable">
					<tbody>
					<tr>
						<td colspan="9" id="kind">移民搬迁登记表</td>
					</tr>
					<tr>
						<td rowspan="2" class="bgc">户主信息</td>
						<td class="bgc">所属水库</td>
						<td colspan="1"><input type="text" id="reservoir"></td>
						<td class="bgc">安置点</td>
						<td><input type="text" id="place"></td>
						<td class="bgc">户主姓名</td>
						<td><input type="text" id="householder"></td>
						<td class="bgc">户主电话</td>
						<td><input type="text" id="number"></td>
					</tr>
					<tr>
						<td class="bgc">开户人姓名</td>
						<td><input type="text" id="bank-user"></td>
						<td class="bgc">开户行名称</td>
						<td colspan="1"><input type="text" id="bank-name"></td>
						<td class="bgc">银行卡号</td>
						<td colspan="3"><input type="text" id="bank-number"></td>
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
						<td><input type="text"></td>
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
						<td class="bgc" rowspan="3">搬迁信息</td>
						<td class="bgc">分类</td>
						<td class="bgc">州市</td>
						<td class="bgc">区县</td>
						<td class="bgc">乡镇</td>
						<td class="bgc">村</td>
						<td class="bgc">组</td>
						<td class="bgc" colspan="2">备注</td>
					</tr>
					<tr id="city1">
						<td class="bgc">迁入地</td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td colspan="2"><input type="text"></td>
					</tr>
					<tr id="city2">
						<td class="bgc">迁出地</td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td><input type="text"></td>
						<td colspan="2"><input type="text"></td>
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
						<td colspan="2"><input type="text" id="inquirer"></td>
						<td class="bgc">填表时间</td>
						<td colspan="2"><input type="text" id="time" readonly="readonly"></td>
					</tr>
					</tbody>
				</table>
				<ul id="btn-container">
					<li><a class="hvr-rectangle-in button">提交</a></li>
					<li><a class="hvr-bounce-to-bottom button">关闭</a></li>
				</ul>
			</form>
		</div>
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
</script>
</html>