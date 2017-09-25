<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="../../css/reset.css">
	<link rel="stylesheet" href="../../css/lincang-yimin.css">
	<!-- <link rel="stylesheet" type="text/css" href="http://www.sucaihuo.com/jquery/css/common.css" /> -->
	<link rel="stylesheet" href="../../css/search.css">
	<script src="../../js/jquery.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=LTqCYH5OsxDXuPREC7tYCrgRGjVtaLQw"></script>
	<!-- <script src="js/jquery.cityselect.js"></script> -->
	<!-- <script src="js/search.js"></script> -->
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
						<li>
							<a href="#" title="地区">
								<div class="img"></div>
								<div class="info">
									<h2 class="name">隆阳区</h2>
									<p class="text">共有移民<span class="family">160</span>户，<span class="people">540</span>人</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#" title="地区">
								<div class="img"></div>
								<div class="info">
									<h2 class="name">隆阳区</h2>
									<p class="text">共有移民<span class="family">160</span>户，<span class="people">540</span>人</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#" title="地区">
								<div class="img"></div>
								<div class="info">
									<h2 class="name">隆阳区</h2>
									<p class="text">共有移民<span class="family">160</span>户，<span class="people">540</span>人</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#" title="地区">
								<div class="img"></div>
								<div class="info">
									<h2 class="name">隆阳区</h2>
									<p class="text">共有移民<span class="family">160</span>户，<span class="people">540</span>人</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#" title="地区">
								<div class="img"></div>
								<div class="info">
									<h2 class="name">隆阳区</h2>
									<p class="text">共有移民<span class="family">160</span>户，<span class="people">540</span>人</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#" title="地区">
								<div class="img"></div>
								<div class="info">
									<h2 class="name">隆阳区</h2>
									<p class="text">共有移民<span class="family">160</span>户，<span class="people">540</span>人</p>
								</div>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>