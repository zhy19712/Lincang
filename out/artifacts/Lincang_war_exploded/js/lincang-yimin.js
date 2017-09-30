$(function(){
	//自适应宽高
	var height = $(window).height() - 66;
	var width = $(window).width() - 360;
	var show_height = $(window).height() - 247;
	var tab_content_height = $(window).height() - 171;
	$("#content").height(height);
	$("#container").width(width);
	$(".show").height(show_height);
	$("#tab_content li").height(tab_content_height);

	var ta_height = $(window).height() - 86
	$("#ta_sroll").height(ta_height);
	//滚动条插件
	$(".show").panel({iWheelStep:32});
	$("#ta_sroll").panel({iWheelStep:32});
	$("#tab_content li").panel({iWheelStep:32});

	//省市县三级联动插件
	// $("#sel_city").citySelect({
 //        prov: "河南",
 //        nodata: "none"
 //    });



    
	//引入地图
    var map = new BMap.Map("container");          // 创建地图实例
	var point = new BMap.Point(100.085905,23.882529);  // 创建点坐标  
	map.centerAndZoom(point, 10);   //初始化地图，设置地图中心坐标和地图级别
	map.enableScrollWheelZoom();   //启用鼠标滚轮缩放
	map.setMinZoom(10);   //设置缩放最小级别
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
    function slide(name,num) {
        var str = "";
        str += ""
            +  "<li class='about'>"
            +  "<a href='#' title='地区'>"
            +  "<div class='img'></div>"
            +  "<div class='info'>"
            +  "<h2 class='name'>" + name + "</h2>"
            +  "<p class='text'>共有移民<span class='people'>" + num + "</span>人</p>"
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
            $.each(data.result,function (i,n) {
                all_info.push(n);
                all_place.push(n.name);
                slide(n.name,n.num);
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
		if(arguments.length == 1){
			$.each(mydata,function (i,n) {
                if (name == n.name){
                    $(".show ul").empty();
                    $.each(n.listChild,function (i,n) {
                        slide(n.name,n.num);
                    });
                    return ;
                }
            })
		}else {
            $(".show ul").empty();
			$.each(mydata,function (i,n) {
                slide(n.name,n.num);
            })
		}
    }

    //侧边栏所有村级或该县下的村级信息显示
	function zhen(name) {
		if (arguments.length == 1){
			$.each(mydata,function (i,n) {
				$.each(n.listChild,function (i,n) {
                    if (name == n.name){
                        $(".show ul").empty();
                        $.each(n.listChild,function (i,n) {
                            slide(n.name,n.num);
                        });
                        return ;
                    }
                })
            })
		}else {
            $(".show ul").empty();
			$.each(mydata,function (i,n) {
				$.each(n.listChild,function (i,n) {
					slide(n.name,n.num);
                })
            })
		}
    }

    //侧边栏所有村级或该村信息显示
	function cun(name) {
		if(arguments.length == 1){
            $.each(mydata,function (i,n) {
                $.each(n.listChild,function (i,n) {
                    $.each(n.listChild,function (i,n) {
                        if (name == n.name){
                            $(".show ul").empty();
                            slide(n.name,n.num);

                            return ;
                        }
                    })
                });
            })
        }else {
            $(".show ul").empty();
            $.each(mydata,function (i,n) {
                $.each(n.listChild,function (i,n) {
                    $.each(n.listChild,function (i,n) {
						slide(n.name,n.num);
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
					}else if(u == 12 || u == 13){
						map.centerAndZoom(point,14);
						setTimeout(function(){
							$.each($(".BMapLabel"),function(i,n){
								if(n.title == "镇级单位"){
									$(this).css({"backgroundColor":"#69bf8a","backgroundImage":"none"});
								}
							});
						},100);
                        zhen(name);
					}else{
						$(".c_info").css("visibility","hidden");
                        $.each(mydata,function (i,n) {
                            $.each(n.listChild,function (i,n) {
                                $.each(n.listChild,function (i,n) {
                                    if (name == n.name){
                                        $(".show ul").empty();
                                        slide(n.name,n.num);
                                        la.setContent("<p id='c_name'>"+name+"</p><p class='c_info'>| "+ n.num +"人</p>");
                                        return ;
                                    }
                                })
                            });
                        })
					}
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

	//缩放地图
	map.addEventListener("zoomend",function(){
		var u = map.getZoom();
		if(u == 10 || u == 11){
            change_label("县级单位");
			xian();
		}else if(u == 12 || u == 13){
            change_label("镇级单位");
			zhen();
		}else if(u > 13){
            change_label("村级单位");
			cun();
		}
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
    })

	//侧边栏切换
	var slide_index = 0;
	$(".nav").children("li").click(function () {
		var index = $(this).index();
		if(index != slide_index){
			slide_index = index;
			$(".nav li").css("background-color","#000");
			$(this).css("background-color","#3c96e6");
			if(slide_index == 0){
                $("#data_input").css("display","none");
                $("#data_analysis").css("display","none");
                $("#slide").children(".right").css("visibility","visible");
                $("#container").css("visibility","visible");
			}else if (slide_index == 1){
                $("#container").css("visibility","hidden");
                $("#slide").children(".right").css("visibility","hidden");
                $("#data_analysis").css("display","none");
                $("#data_input").css("display","block");
			}else if (slide_index == 2){
                $("#container").css("visibility","hidden");
                $("#slide").children(".right").css("visibility","hidden");
                $("#data_input").css("display","none");
				$("#data_analysis").css("display","block");
			}
		}
    })


	//区县列表信息
	var mytable1 = $('#table1').DataTable({
        ajax: {
            url: "./BasicInfoOfFamilyByName.do"
        },
        "order": [[1, 'asc']],// dt默认是第一列升序排列 这里第一列为序号列，所以设置为不排序，并把默认的排序列设置到后面
        "serverSide": true,
        "columns": [
            {data: "FID"},
            {data: "NAME"},
            {data: "PROP"},
            {data: "HOME_SIZE"},
            {data: "IMM_NUM"},
            {data: "RESERVOIR"}
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
    var mytable2 = $('#table2').DataTable({
        ajax: {
            url: "./FamilyInfoByFid.do"
        },
        "order": [[1, 'asc']],// dt默认是第一列升序排列 这里第一列为序号列，所以设置为不排序，并把默认的排序列设置到后面
        "serverSide": true,
        "columns": [
            {data: "NAME"},
            {data: "GENDER"},
            {data: "RACE"},
            {data: "PHONE"}
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

    $("#show").off("click",".about").on("click",".about",function () {
		var name = $(this).find(".name").text();
		mytable1.ajax.url("./BasicInfoOfFamilyByName.do?name=" + name).load();
		$("#show_info").css("display","block");
    });

	$("#table1 tbody").on("click","tr",function () {
		var fid = $(this).children("td:first-child").text();
        $("#table2_wrapper").css("display","block");
        mytable2.ajax.url("./FamilyInfoByFid.do?fid=" + fid).load();
		$("#family").css("display","block");
		$.ajax({
			url: "./FamilyDetailByFid.do",
			data: {fid:fid},
			dataType: "json",
			success: function (data) {
				console.log(data);
				var index1 = data.inArea.indexOf(",");
				var index2 = data.mainStructure.indexOf(",");
				var index3 = data.outArea.indexOf(",");
				var index4 = data.subStructure.indexOf(",");
				var inArea = data.inArea.substring(0,index1);
				var mainStructure = data.mainStructure.substring(0,index2);
				var outArea = data.outArea.substring(0,index3);
				var subStructure = data.subStructure.substring(0,index4);
				if(data.prop == 0){
					var prop = "是";
				}else {
					var prop = "否";
				}
				if(!data.poor_reason){
					var reason = "无"
				}else{
					var reason = data.poor_reason;
				}
				$(".f_name").text(data.name);
				$(".f_id").text(data.pid);
				$(".f_people").text(data.home_size);
				$(".f_imm").text(data.imm_num);
                $(".f_prop").text(prop);
                $(".f_out").text(outArea);
                $(".f_in").text(inArea);
                $(".f_reason").text(reason);
                $(".f_mainhouse").text(mainStructure);
                $(".f_mainarear").text(data.mainSize);
                $(".f_bank").text(data.bank_name);
                $(".f_number").text(data.account_number);
                $(".f_work").text(data.income_source);
                $(".f_money").text(data.income_sum);
                $(".f_reservoir").text(data.reservoir);
                $(".f_subhouse").text(subStructure);
                $(".f_subarear").text(outArea);
            }
		})
    })

	//数据录入表格单选
	$("#yes").click(function () {
		$("#no").prop("checked",false);
    })
    $("#no").click(function () {
        $("#yes").prop("checked",false);
    })
})