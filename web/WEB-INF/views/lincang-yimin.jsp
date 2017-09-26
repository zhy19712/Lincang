<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="../../css/reset.css">
	<link rel="stylesheet" href="../../css/lincang-yimin.css">
	<link rel="stylesheet" href="../../css/search.css">
	<link rel="stylesheet" href="../../css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="../../css/style.css">
	<script src="../../js/jquery.min.js"></script>
	<script src="../../js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=LTqCYH5OsxDXuPREC7tYCrgRGjVtaLQw"></script>
	<script src="../../js/zUI.js"></script>
	<script src="../../js/lincang-yimin.js"></script>
</head>
<body>
	<div id="header">
		<img src="../../img/logo.png" alt="" class="logo">
		<div class="search">
			<div class="search-text">
	            <input type="text" placeholder="请输入地区名称">
	            <button><i></i></button>
	        </div>
	        <ul class="search-condition">
	            <li>
	                <span>迁出时间</span>
	                <i></i>
	                <ul class="none">
	                </ul>
	            </li>
	            <li>
	                <span>迁出人数</span>
	                <i></i>
	                <ul class="none">
	                </ul>
	            </li>
	            <li>
	                <span>迁出地点</span>
	                <i></i>
	                <ul class="none">
	                </ul>
	            </li>
	            <li>
	                <span>更多</span>
	                <i></i>
	            </li>
	            <li>
	                <i></i>
	                <span>清除全部条件</span>
	            </li>
	        </ul>
		</div>
	</div>
	<div id="content">
		<div id="container-wrapper">
			<div id="container"></div>
			<div id="show_info">
				<div id="back">返回地图</div>
				<div id="tab">
					<ul id="tab_list">
						<li>统计分析</li>
						<li>移民信息</li>
					</ul>
					<ul id="tab_content">
						<li>
							tab1
						</li>
						<li>
							<div id="table-wrapper">
								<table id="table1" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>FID</th>
											<th>户主姓名</th>
											<th>建档立卡</th>
											<th>家庭人数</th>
											<th>移民人数</th>
											<th>所属水库</th>
										</tr>
									</thead>
								</table>
							</div>
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
						<p>移民信息</p>
					</li>
					<li>
						<i></i>
						<p>数据录入</p>
					</li>
					<li>
						<i></i>
						<p>统计分析</p>
					</li>
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
				<div class="show">
					<ul>
					</ul>
				</div>
			</div>
		</div>
		<div id="data_input">
			<div id="ta_sroll">
				<div id="ta_wrapper">
					<table>
						<tbody>
						<tr>
							<td colspan="9">库区登记表</td>
						</tr>
						<tr>
							<td rowspan="2" class="bgc">户主信息</td>
							<td class="bgc">所属水库</td>
							<td colspan="4"><input type="text"></td>
							<td class="bgc">户主姓名</td>
							<td colspan="2"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">开户人姓名</td>
							<td><input type="text"></td>
							<td class="bgc">开户行名称</td>
							<td colspan="2"><input type="text"></td>
							<td class="bgc">银行卡号</td>
							<td colspan="2"><input type="text"></td>
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
						<tr>
							<td><input type="text"></td>
							<td colspan="2"><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
						</tr>
						<tr>
							<td><input type="text"></td>
							<td colspan="2"><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
						</tr>
						<tr>
							<td><input type="text"></td>
							<td colspan="2"><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
						</tr>
						<tr>
							<td><input type="text"></td>
							<td colspan="2"><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc" rowspan="2">所在地</td>
							<td class="bgc">州市</td>
							<td class="bgc">区县</td>
							<td class="bgc">乡镇</td>
							<td class="bgc">村</td>
							<td class="bgc">组</td>
							<td class="bgc" colspan="3">备注</td>
						</tr>
						<tr>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
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
						<tr>
							<td class="bgc">主房</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
						</tr>
						<tr>
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
						<tr>
							<td class="bgc" rowspan="8">养殖业收入</td>
							<td class="bgc">猪</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">牛</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">羊</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">鸡</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">鸭</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">渔业</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">乳业</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">其他</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc" rowspan="4">种植业收入</td>
							<td class="bgc">粮食</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">蔬菜</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">水果</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">其他</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc" rowspan="2">其他收入</td>
							<td class="bgc">劳务酬劳</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
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
						<tr>
							<td class="bgc" rowspan="6">种植业支出</td>
							<td class="bgc">籽种</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">化肥、农药</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">雇工</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">机耕支出</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">灌溉水电费</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">承租耕地</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc" rowspan="4">养殖业支出</td>
							<td class="bgc">幼种</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">饲料</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">疫病防治</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">其他</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc" rowspan="8">生活支出</td>
							<td class="bgc">主食</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">衣物</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">水、电费</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">通讯费</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">交通费</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">教育</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
							<td class="bgc">医疗</td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td><input type="text"></td>
							<td colspan="3"><input type="text"></td>
						</tr>
						<tr>
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
										<input type="checkbox" id="traffic">
										<label for="traffic">交通落后</label>
									</li>
									<li>
										<input type="checkbox" id="money">
										<label for="money">缺技术、资金、土地、水</label>
									</li>
									<li>
										<input type="checkbox" id="disaster">
										<label for="disaster">因学、残、灾</label>
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<td class="bgc">被调查人签字</td>
							<td colspan="2"><input type="text"></td>
							<td class="bgc">调查人签字</td>
							<td colspan="2"><input type="text"></td>
							<td class="bgc">填表时间</td>
							<td colspan="2"><input type="text"></td>
						</tr>
						</tbody>
					</table>
					<ul id="btn-container">
						<li><a class="hvr-rectangle-in button">提交</a></li>
						<li><a class="hvr-sweep-to-right button">保存</a></li>
						<li><a class="hvr-bounce-to-bottom button">放弃</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="data_analysis">统计分析</div>
	</div>
</body>
</html>