var mytable1,allinfo_table,county_name;
$(function(){
	//自适应宽高
	var height = $(window).height() - 66;
	if(height<730){
		height = 730;
	}
	var width = $(window).width() - 300;
	var show_height = height - 191;
	var tab_content_height = height - 105;
	$("#content").height(height);
	$("#data_input").height(height);
	// $("#container").width(width);
	var width1 = width - 100;
	$("#container1").width(width1);
	$("#container2").width(width1);
	$("#show").height(show_height);
	$("#tab_content li").height(tab_content_height);

    //所有信息展示表格
    allinfo_table = $('#allinfo_table').DataTable({
        ajax: {
            url: "/FamilyInfoAdd.do",
            async:false
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "TABLE_TYPE"},
            {"data": "FID"},
            {"data": "NAME"},
            {"data": "RESERVOIR"},
            {"data": "TO_DISTRICT"},
            {"data": "INTERVIEWER"},
            {"data": "CREATED_AT"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [7],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                    html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='编辑'/>" ;
                    html += "<input type='button' class='btn btn-danger btn-xs' style='margin-left: 5px;' onclick='delete1(this)' value='删除'/>" ;
                    return html;
                }
            }
        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        }
    });


    //区县列表信息
    mytable1 = $('#table1').DataTable({
        ajax: {
            url: "/TableAddByName.do?name=" + encodeURI(encodeURI("云县")),
            async:false
        },
        "order": [[1, 'desc']],
        "serverSide": true,
        "columns": [
            {"data": "TABLE_TYPE"},
            {"data": "FID"},
            {"data": "NAME"},
            {"data": "RESERVOIR"},
            {"data": "TO_DISTRICT"},
            {"data": "INTERVIEWER"},
            {"data": "CREATED_AT"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [7],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                    html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='编辑'/>" ;
                    return html;
                }
            }
        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        }
    });

    //获取功能
    var fun_list = [];
    $.ajax({
        url: "/getFunction.do",
        type: "post",
        async: false,
        dataType: "json",
        success:function (data) {
            console.log(data);
            $.each(data.function,function (i,n) {
                if(n.classification == "移民登记" || n.classification == "移民分析"){
                    fun_list.push(n);
                }
            })
        }
    });

    var f1 = [];
    var f2 = [];
    var f3 = [];
    var f4 = [];
    var f5 = [];
    var f6 = [];
    var f7 = [];
    var f8 = [];
    var f9 = [];
    var f10 = [];
    var f11 = [];
    console.log(fun_list)
    $.each(fun_list,function (i,n) {
        if(n.authdescription == "移民新建功能"){
            f1.push(n.authdescription)
        }else if(n.authdescription == "移民修改功能"){
            f2.push(n.authdescription)
        }else if(n.authdescription == "移民上传功能"){
            f3.push(n.authdescription)
        }else if(n.authdescription == "列表查看、搜索功能"){
            f4.push(n.authdescription)
        }else if(n.authdescription == "列表删除功能"){
            f5.push(n.authdescription)
        }else if(n.authdescription == "地图搜索功能"){
            f6.push(n.authdescription)
        }else if(n.authdescription == "地图查看功能"){
            f7.push(n.authdescription)
        }else if(n.authdescription == "地图统计功能（按照区县）"){
            f8.push(n.authdescription)
        }else if(n.authdescription == "移民信息查看"){
            f9.push(n.authdescription)
        }else if(n.authdescription == "统计分析查看"){
            f10.push(n.authdescription)
        }else if(n.authdescription == "区县搜索功能"){
            f11.push(n.authdescription)
        }
    })

    if(f1.length == 0){
        $("#excel>ul").css("display","none");
    }
    if(f2.length == 0){
        $("#allinfo_table tbody tr td:last-child input:nth-child(2)").css("display","none");
    }
    if(f3.length == 0){
        $("#excel>div").css("display","none");
        $("#excel>button").css("display","none");
    }
    if(f4.length == 0){
        $("#allinfo_table_wrapper").css("display","none");
    }
    if(f5.length == 0){
        $(".btn-danger").css("display","none");
    }
    if(f1.length == 0 && f2.length == 0 && f3.length == 0 && f4.length == 0 && f5.length == 0){
        $("#slide .nav>li:first-child").css("display","none");
        $("#slide .nav>li:nth-child(2)").click();
    }
    if(f6.length == 0){
        $("#sel_city").css("display","none");
    }
    if(f7.length == 0){
        $("#container").css("display","none");
        $("#back").css("display","none");
    }
    if(f8.length == 0){
        $("#show").css("display","none");
    }
    if(f9.length == 0){
        $("#tab_list>li:nth-child(1)").css("display","none");
        $("#tab_content>li:nth-child(1)").css("display","none");
        $("#tab_list>li:nth-child(2)").click();
    }
    if(f10.length == 0){
        $("#tab_list>li:nth-child(2)").css("display","none");
    }
    if(f11.length == 0){
        $("#table1_filter").css("display","none");
    }



	var ta_height = $(window).height() - 86
	$("#ta_sroll").height(ta_height);
	//滚动条插件
	$("#show").panel({iWheelStep:32});
	$("#ta_sroll").panel({iWheelStep:32});
	$("#tab_content li:first-child").panel({iWheelStep:32});




    
	//引入地图
    var map = new BMap.Map("container");          // 创建地图实例
	var point = new BMap.Point(100.085905,23.882529);  // 创建点坐标
	map.centerAndZoom(point, 10);   //初始化地图，设置地图中心坐标和地图级别
	map.enableScrollWheelZoom();   //启用鼠标滚轮缩放
	map.setMinZoom(10);   //设置缩放最小级别
	map.setMaxZoom(13);   //设置缩放最小级别
	var opts = {anchor:BMAP_ANCHOR_BOTTOM_RIGHT,type: BMAP_NAVIGATION_CONTROL_ZOOM}        //右下方

	map.addControl(new BMap.NavigationControl(opts));                //平移缩放控件 默认左上角

	map.addControl(new BMap.ScaleControl(opts));                     //比例尺控件地图 默认左下方

	map.setCurrentCity("云南");
	 
	

	//行政区域面积
	function getBoundary(name){       
	    var bdary = new BMap.Boundary();
	    bdary.get(name, function(rs){       //获取行政区域
	        map.clearOverlays();        //清除地图覆盖物       
	        var count = rs.boundaries.length; //行政区域的点有多少个
	      
	        for(var i = 0; i < count; i++){
	            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#00ae66",fillColor: "#d1e9dc",fillOpacity: 0.5}); //建立多边形覆盖物
	            map.addOverlay(ply);  //添加覆盖物
	            // map.setViewport(ply.getPath());    //调整视野         
	        }                
	    });   
	}
	//改变覆盖物显示
	function change_label(level){
		$.each($(".BMapLabel"),function(i,n){
			if(n.title != level){
                $(this).css("display","none");
			}else{
                $(this).css("display","block");
			}
		});
		
	}
    //添加侧边栏信息
    function slide(name,num,households) {
        var str = "";
        str += ""
            +  "<li class='about'>"
            +  "<a href='#' title='地区'>"
            +  "<div class='img'></div>"
            +  "<div class='info'>"
            +  "<h2 class='name' style='margin-bottom: 0px;margin-top: 0px;'>" + name + "</h2>"
            +  "<p class='text' style='margin-bottom: 0px;'>共有户数<span class='people'>" + households + "</span>户</p>"
            +  "<p class='text' style='margin-bottom: 0px;'>共有移民<span class='people'>" + num + "</span>人</p>"
            +  "</div>"
            +  "</a>"
            +  "</li>"
        $("#show ul").append(str);
    }

    //获取数据,添加覆盖物
    var all_info = [];
    var all_place = [];
    var mydata = [];
    $.ajax({
        url:"./picture.do",
        async: false,
        success: function (data) {
        	console.log(data);
        	mydata = data.result;
            $("#show ul").empty();
            $.each(data.result,function (i,n) {
                all_info.push(n);
                all_place.push(n.name);
                slide(n.name,n.num,n.households);
                if(n.name.length<8){
                    addLabel(n.name,n.level,n.name+"<br>共有移民"+n.num+"人");
                }else {
                    var name1 = n.name.substring(0,7);
                    var name2 = n.name.substring(7);
                    addLabel(n.name,n.level,name1+"<br>"+name2+"<br>共有移民"+n.num+"人");
                }
                $.each(n.listChild,function (i,n) {
                    all_info.push(n);
                    all_place.push(n.name);
                    if(n.name.length<8){
                        addLabel(n.name,n.level,n.name+"<br>共有移民"+n.num+"人");
                    }else {
                        var name1 = n.name.substring(0,7);
                        var name2 = n.name.substring(7);
                        addLabel(n.name,n.level,name1+"<br>"+name2+"<br>共有移民"+n.num+"人");
                    }
                    $.each(n.listChild,function (i,n) {
                        all_info.push(n);
                        all_place.push(n.name);
                        addLabel(n.name,n.level,n.name);
                    })
                })
            });
        }
    });

	//侧边栏所有县或该县下的镇级信息显示,
	function xian(name) {
        $("#show ul").empty();
		if(arguments.length == 1){
			$.each(mydata,function (i,n) {
                if (name == n.name){
                    $("#show ul").empty();
                    $.each(n.listChild,function (i,n) {
                        slide(n.name,n.num,n.households);
                    });
                    return ;
                }
            })
		}else {
			$.each(mydata,function (i,n) {
                slide(n.name,n.num,n.households);
            })
		}
    }

    //侧边栏所有村级或该县下的村级信息显示
	function zhen(name) {
        $("#show ul").empty();
		if (arguments.length == 1){
			$.each(mydata,function (i,n) {
				$.each(n.listChild,function (i,n) {
                    if (name == n.name){
                        $("#show ul").empty();
                        $.each(n.listChild,function (i,n) {
                            slide(n.name,n.num,n.households);
                        });
                        return ;
                    }
                })
            })
		}else {
			$.each(mydata,function (i,n) {
				$.each(n.listChild,function (i,n) {
					slide(n.name,n.num,n.households);
                })
            })
		}
    }

    //侧边栏所有村级或该村信息显示
	function cun(name) {
        $("#show ul").empty();
		if(arguments.length == 1){
            $.each(mydata,function (i,n) {
                $.each(n.listChild,function (i,n) {
                    $.each(n.listChild,function (i,n) {
                        if (name == n.name){
                            slide(n.name,n.num,n.households);

                            return ;
                        }
                    })
                });
            })
        }else {
            $.each(mydata,function (i,n) {
                $.each(n.listChild,function (i,n) {
                    $.each(n.listChild,function (i,n) {
						slide(n.name,n.num,n.households);
                    })
                });
            })
		}
    }

	//添加覆盖物
	function addLabel(name,level,string){ 
		// 创建地址解析器实例     
		var myGeo = new BMap.Geocoder();      
		// 将地址解析结果显示在地图上，并调整地图视野    
		myGeo.getPoint(name,function(point){      
	    	 if(point){	
				var opts = {position:point,offset:new BMap.Size(-40,-35)};    //定义位置及偏移量
				var label = new BMap.Label(string,opts);
				if(level != "村级单位"){
					label.setStyle({											
						color:"#fff",
						backgroundColor:"#69bf8a",
						borderColor:"transparent",
						width:"95px",
						height:"75px",
						borderRadius:"50%",
						lineHeight:"20px",
						textAlign:"center",
						paddingTop:"20px",
						cursor:"pointer",
					});
				}else{
					label.setStyle({											
						color:"#fff",
						backgroundColor:"transparent",
						borderColor:"transparent",
						height:"25px",
						minWidth:"50px",
						lineHeight:"20px",
						textAlign:"center",
						padding:"0 8px",
						cursor:"pointer",
						background:"url(../../img/c-green.png) no-repeat top left"
					});
				}					
				label.setTitle(level);
				label.setZIndex("999");
				map.addOverlay(label);
				label.disableMassClear();
				if(level != "县级单位"){
					label.hide();
				}
				label.addEventListener("click",function(){
					var la = this;
					var u = map.getZoom();
					if(u == 10 || u == 11){
						map.centerAndZoom(point,12);
						xian(name);
					}
					// else if(u == 12 || u == 13){
					// 	map.centerAndZoom(point,14);
					// 	setTimeout(function(){
					// 		$.each($(".BMapLabel"),function(i,n){
					// 			if(n.title == "镇级单位"){
					// 				$(this).css({"backgroundColor":"#69bf8a","backgroundImage":"none"});
					// 			}
					// 		});
					// 	},100);
                     //    zhen(name);
					// }else{
					// 	$(".c_info").css("visibility","hidden");
                     //    $.each(mydata,function (i,n) {
                     //        $.each(n.listChild,function (i,n) {
                     //            $.each(n.listChild,function (i,n) {
                     //                if (name == n.name){
                     //                    $("#show ul").empty();
                     //                    slide(n.name,n.num);
                     //                    la.setContent("<p id='c_name'>"+name+"</p><p class='c_info'>| "+ n.num +"人</p>");
                     //                    return ;
                     //                }
                     //            })
                     //        });
                     //    })
					// }
				});
				label.addEventListener("mouseover",function (){
					var la = this;
					var u = map.getZoom();
					if(u < 14){
						la.setStyle({backgroundColor:"#e14a4b"});
						getBoundary(name);
					}else if(u > 13){
						la.setStyle({background:"url(../../img/c-red.png) no-repeat top left"});
					}
				});
				label.addEventListener("mouseout",function(){
					var la = this;
					var u = map.getZoom();
					if(u < 14){
						map.clearOverlays();        //清除地图覆盖物 
						la.setStyle({backgroundColor:"#69bf8a"});    
					}else{
						la.setStyle({background:"url(../../img/c-green.png) no-repeat top left"});
					}
	        		
				});
	         }     
	      }, "云南省临沧市");
	}

	// 缩放地图
	map.addEventListener("zoomend",function(){
		var u = map.getZoom();
		if(u == 10 || u == 11){
            change_label("县级单位");
			xian();
		}else if(u == 12 || u == 13){
            change_label("镇级单位");
			zhen();
		}
		// else if(u > 13){
         //    change_label("村级单位");
		// 	cun();
		// }
	});


	//搜索功能
	var $$ = function (id) {

	    return "string" == typeof id ? document.getElementById(id) : id;

	};

	var Bind = function(object, fun) {

	    return function() {

	        return fun.apply(object, arguments);

	    }

	};

	function AutoComplete(obj,autoObj,arr){

	    this.obj=$$(obj);        //输入框

	    this.autoObj=$$(autoObj);//DIV的根节点

	    this.value_arr=arr;        //不要包含重复值

	    this.index=-1;          //当前选中的DIV的索引

	    this.search_value="";   //保存当前搜索的字符

	};

	AutoComplete.prototype={

	    //初始化DIV的位置

	    init: function(){

	        this.autoObj.style.left = this.obj.offsetLeft + "px";

	        this.autoObj.style.top  = this.obj.offsetTop + this.obj.offsetHeight + "px";

	        this.autoObj.style.width= this.obj.offsetWidth - 2 + "px";//减去边框的长度2px

	    },

	    //删除自动完成需要的所有DIV

	    deleteDIV: function(){

	        while(this.autoObj.hasChildNodes()){

	            this.autoObj.removeChild(this.autoObj.firstChild);

	        }

	        this.autoObj.className="auto_hidden";

	    },

	    //设置值

	    setValue: function(_this){

	        return function(){

	            _this.obj.value=this.seq;

	            _this.autoObj.className="auto_hidden";

	        }

	    },

	    //模拟鼠标移动至DIV时，DIV高亮

	    autoOnmouseover: function(_this,_div_index){

	        return function(){

	            _this.index=_div_index;

	            var length = _this.autoObj.children.length;

	            for(var j=0;j<length;j++){

	                if(j!=_this.index ){

	                    _this.autoObj.childNodes[j].className='auto_onmouseout';

	                }else{

	                    _this.autoObj.childNodes[j].className='auto_onmouseover';

	                }

	            }

	        }

	    },

	    //更改classname

	    changeClassname: function(length){

	        for(var i=0;i<length;i++){

	            if(i!=this.index ){

	                this.autoObj.childNodes[i].className='auto_onmouseout';

	            }else{

	                this.autoObj.childNodes[i].className='auto_onmouseover';

	                this.obj.value=this.autoObj.childNodes[i].seq;

	            }

	        }

	    }

	    ,

	    //响应键盘

	    pressKey: function(event){

	        var length = this.autoObj.children.length;

	        //光标键"↓"

	        if(event.keyCode==40){

	            ++this.index;

	            if(this.index>length){

	                this.index=0;

	            }else if(this.index==length){

	                this.obj.value=this.search_value;

	            }

	            this.changeClassname(length);

	        }

	        //光标键"↑"

	        else if(event.keyCode==38){

	            this.index--;

	            if(this.index<-1){

	                this.index=length - 1;

	            }else if(this.index==-1){

	                this.obj.value=this.search_value;

	            }

	            this.changeClassname(length);

	        }

	        //回车键

	        else if(event.keyCode==13){

	            this.autoObj.className="auto_hidden";

	            this.index=-1;
				search();

	        }else{

	            this.index=-1;

	        }

	    },

	    //程序入口

	    start: function(event){

	        if(event.keyCode!=13&&event.keyCode!=38&&event.keyCode!=40){

	            this.init();

	            this.deleteDIV();

	            this.search_value=this.obj.value;

	            var valueArr=this.value_arr;

	            valueArr.sort();

	            if(this.obj.value.replace(/(^\s*)|(\s*$$)/g,'')==""){ return; }//值为空，退出

	            try{ var reg = new RegExp("(" + this.obj.value + ")","i");}

	            catch (e){ return; }

	            var div_index=0;//记录创建的DIV的索引

	            for(var i=0;i<valueArr.length;i++){

	                if(reg.test(valueArr[i])){

	                    var div = document.createElement("div");

	                    div.className="auto_onmouseout";

	                    div.seq=valueArr[i];

	                    div.onclick=this.setValue(this);

	                    div.onmouseover=this.autoOnmouseover(this,div_index);

	                    div.innerHTML=valueArr[i].replace(reg,"<strong style='color:red;'>$1</strong>");//搜索到的字符粗体显示

	                    this.autoObj.appendChild(div);

	                    this.autoObj.className="auto_show";

	                    div_index++;

	                }

	            }

	        }

	        this.pressKey(event);

	        window.onresize=Bind(this,function(){this.init();});

	    }

	}
	var autoComplete=new AutoComplete('ipt','auto',all_place);


	$(".city_info").click(function () {
        $("#show ul").empty();
	    $.each(mydata,function (i,n) {
            slide(n.name,n.num,n.households);
        })
        map.centerAndZoom(point, 10);
    })



	//搜索
	function search(){
		var name = $("#ipt").val();
		var level;
		$.each(all_info,function(i,n){
			if(name == n.name){
				level = n.level;
                var myGeo = new BMap.Geocoder();
                myGeo.getPoint(name,function(point){
                    if(point){
                        if(level == "县级单位"){
                            map.centerAndZoom(point,10);
                            xian(name);
                        }else if(level == "镇级单位"){
                            map.centerAndZoom(point,12);
                            zhen(name);
                        }else if(level == "村级单位"){
                            map.centerAndZoom(point,14);
                            cun(name);
                        }else{
                            alert("您输入的名称有误,请重新输入");
                        }
                    }
                }, "云南省临沧市");
			}
		});

	}
	$("#btn").click(search);

	$("#ipt").keyup(function (e){
		autoComplete.start(e);
	});

	//tab切换
    var num = 0;
	$("#tab_list li").click(function () {
		var index = $(this).index();
		if(index != num){
		    num = index;
		    $("#tab_list li").css({"background-color": "#DCDCDC","color": "#595757"});
		    $("#tab_list li").eq(index).css({"background-color": "#f26d0b","color": "#fff"});
            $("#tab_content li").css("display","none");
            $("#tab_content li").eq(index).fadeIn();
        }
    })

	//返回地图按钮
	$("#back").click(function () {
		$("#show_info").css("display","none");
    });


	//获取总人数
	$.ajax({
		url: "/pictureSum.do",
		type: "post",
		dataType: "json",
		success: function (data) {
			$("#total_people").text(data)
        }
	})







    $("#show").on("click",".about",function () {
		county_name = $(this).find(".name").text();
		console.log(county_name);
        mytable1.ajax.url("/TableAddByName.do?name="+ encodeURI(encodeURI(county_name))).load();
		$("#show_info").css("display","block");
    });


	//数据录入表格单选
	$("#yes").click(function () {
		$("#no").prop("checked",false);
    })
    $("#no").click(function () {
        $("#yes").prop("checked",false);
    });


    //打印插件
    $("#jqprint").click(function () {
        jQuery('#jqtable').print();
    });







    //统计分析图表
	var datax_anzhi = [];
	var datay_anzhi = [];
	var datax_kuqu = [];
	var datay_kuqu = [];
	$.ajax({
		url: "/numOfKind.do",
		type: "post",
		async: false,
		dataType: "json",
		success: function (data) {
			$.each(data.anZhi,function (i,n) {
				datax_anzhi.push(n.anZhiName);
				datay_anzhi.push(n.numOfAnZhi);
            });
			$.each(data.kuQu,function (i,n) {
                datax_kuqu.push(n.kuQuName);
                datay_kuqu.push(n.numOfKuQu)
            })
        }
	})
	var echart1 = echarts.init(document.getElementById('container1'));
	var echart2 = echarts.init(document.getElementById('container2'));
    var option1 = {
        color: ['#3398DB'],
		title: {
        	text: "安置点统计表",
            left: "50%"
		},
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : datax_anzhi,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'移民数量',
                type:'bar',
                barWidth: '60%',
                data:datay_anzhi
            }
        ]
    };
    var option2 = {
        color: ['#3398DB'],
        title: {
            text: "所属水库登记表",
			left: "50%"
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : datax_kuqu,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'移民数量',
                type:'bar',
                barWidth: '60%',
                data:datay_kuqu
            }
        ]
    };
    echart1.setOption(option1);
    echart2.setOption(option2);


})