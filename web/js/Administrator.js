var all_table = $('#NewTable_Admin').DataTable({
    ajax: {
        url: "/userManagementDataTableFirst.do"
    },
    "order": [[1, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "id"},
        {"data": "username"},
        {"data": "role"},
        {"data": "name"},
        {"data": "unit"},
        {"data": "department"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [6],
            "render" :  function(data,type,row) {
                var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='编辑'/>" ;
                html += "<input type='button' class='btn btn-danger btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='删除'/>" ;
                return html;
            }
        },
        {
            "searchable": false,
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

var New_table = $('#NewTable_role').DataTable({
    ajax: {
        url: "/sendFileDataTableFirst.do"
    },
    "order": [[2, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "sendfileid"},
        {"data": "role"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [2],
            "render" :  function(data,type,row) {
                var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
                html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='编辑'/>" ;
                html += "<input type='button' class='btn btn-danger btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='删除'/>" ;
                return html;
            }
        },
        {
            "searchable": false,
            "orderable": false,
            "targets": [0]
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


//增加的正则
var status1=false,status2=false,status3=false;
$("#name").keyup(function () {
    var name=$("#name").val();
    var pattern = /^[\u4e00-\u9fa5]*$/;
    if(name.length==0){
        status1=false;
        $("#namePrompt").css("color","black").text("必须为汉字");
    }else {
        if(!pattern.test(name)){
            status1=false;
            $("#namePrompt").css("color","red").text("必须为汉字");
        }else {
            status1=true;
            $("#namePrompt").text("")
        }
    }
});

$("#username").keyup(function () {
    var username=$("#username").val();
    var pattern = /^[a-zA-Z][a-zA-Z0-9]+$/;
    if(username.length==0){
        status2=false;
        $("#usernamePrompt").css("color","black").text("以字母开头,可以字母和数字组合,长度在2个以上");
    }else {
        if(!pattern.test(username)){
            status2=false;
            $("#usernamePrompt").css("color","red").text("以字母开头,可以字母和数字组合,长度在2个以上");
        }else {
            status2=true;
            $("#usernamePrompt").text("")
        }
    }
});

$("#pass").keyup(function () {
    var pass=$("#pass").val();
    var pattern = /\w{6,18}/;
    var p1=/^\d+$/;
    var p2=/^[a-zA-Z]+$/;

    if(pass.length==0){
        status3=false;
        $("#passPrompt").css("color","black").text("数字和字母,字符,长度在6~18之间");
    }else {
        if(!p1.test(pass) && !p2.test(pass) && pattern.test(pass)){
            status3=true;
            $("#passPrompt").text("")
        }else {
            status3=false;
            $("#passPrompt").css("color","red").text("数字和字母,字符,长度在6~18之间");
        }
    }
});

//修改的正则
var status4=true,status5=true,status6=true;
$("#name1").keyup(function () {
    var name=$("#name1").val();
    var pattern = /^[\u4e00-\u9fa5]*$/;
    if(name.length==0){
        status4=false;
        $("#namePrompt1").css("color","black").text("必须为汉字");
    }else {
        if(!pattern.test(name)){
            status4=false;
            $("#namePrompt1").css("color","red").text("必须为汉字");
        }else {
            status4=true;
            $("#namePrompt1").text("")
        }
    }
});

$("#username1").keyup(function () {
    var username=$("#username1").val();
    var pattern = /^[a-zA-Z][a-zA-Z0-9]+$/;
    if(username.length==0){
        status5=false;
        $("#usernamePrompt1").css("color","black").text("以字母开头,可以字母和数字组合,长度在2个以上");
    }else {
        if(!pattern.test(username)){
            status5=false;
            $("#usernamePrompt1").css("color","red").text("以字母开头,可以字母和数字组合");
        }else {
            status5=true;
            $("#usernamePrompt1").text("")
        }
    }
});

$("#pass1").keyup(function () {
    var pass=$("#pass1").val();
    var pattern = /\w{6,18}/;
    var p1=/^\d+$/;
    var p2=/^[a-zA-Z]+$/;

    if(pass.length==0){
        status6=false;
        $("#passPrompt1").css("color","black").text("数字和字母,字符,长度在6~18之间");
    }else {
        if(!p1.test(pass) && !p2.test(pass) && pattern.test(pass)){
            status6=true;
            $("#passPrompt1").text("")
        }else {
            status6=false;
            $("#passPrompt1").css("color","red").text("数字和字母,字符,长度在6~18之间");
        }
    }
});


    //初始化 单位名称UnitName 部门Department
    initUND();
    //动态角色
    initRole();

    //树状复选框插件
    $("#tree_container").jstree({
        "plugins" : ["checkbox"],
    });
    $("#tree_container").jstree().get_selected(true); //获取选中的
    $('#tree_container').jstree('deselect_all');//全部取消
    $('#tree_container').jstree('select_all');//全部选中

    //提交事件
    $("#btn-primary").click(function () {
        console.log(status1,status2,status3);
        if(status1 && status2 && status3){
            primaryClick()
        }else {
            alert("请正确添加用户")
        }
    });
    // 修改
    $("#btn-update").click(function () {
        if(status4 && status5 && status6){
            updata();
        }else {
            alert("请正确修改用户")
        }

    });
    //操作
    function edit(that) {
        var id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text(),
             kind = $(that).val();
            $("#data_id").text(id);

            if(kind=="查看"){
                $("#form-kind1").text("查看用户");
                $("#btn-update").hide();
                lookOver(id);
            }else if(kind=="编辑"){
                $("#form-kind1").text("编辑用户");
                $("#btn-update").show();
                lookOver(id);
            }else if(kind=="删除"){
                deldata(id)
            }
    }
    //修改
    function updata() {
        var username=$("#username1").val(),
            pass=$("#pass1").val(),
            role=$("#form_update .role").val(),
            unit=$("#form_update .unit").val(),
            name=$("#name1").val(),
            department=$("#form_update .department").val(),
            phone=$("#phone1").val(),
            id=$("#data_id").text();

        console.log(id);

        var datas={
            "username" : username,
            "pass" : pass,
            "role" : role,
            "unit" : unit,
            "department" : department,
            "name" : name,
            "phone" : phone,
            "id" :id
        };
        console.log(datas);
        $.ajax({
            url:"/updateRegisterInfoById.do",
            dataType:"json",
            type:"post",
            data:datas,
            async:false,
            success:function (val) {
                console.log(val);
                $('#form_update').modal('hide');
                if(val.result =="success"){
                    table_refresh();
                    alert("修改成功");
                }else {
                    alert("修改失败");
                }
            },error:(function(){
                alert("系统出错")
            })
        })
    }
    // 查看
    function lookOver(id) {
        $.ajax({
            url: "/getRegisterInfoById.do",
            type: "post",
            async: false,
            data: {"id":id},
            dataType: "json",
            success: function (data) {
                $("#username1").val(data.username);
                $("#pass1").val(data.pass);
                $("#form_update .role").val(data.role);
                $("#form_update .unit").val(data.unit);
                $("#name1").val(data.name);
                $("#form_update .department").val(data.department);
                $("#phone1").val(data.phone);
                $("#form_update").modal('show');
            }
        });
    }
    //删除
    function deldata(id) {
        if(confirm("你确定要删除吗？")){
            $.ajax({
                url: "/deleteRegisterInfoById.do",
                type: "post",
                async: false,
                data: {"id":id},
                dataType: "json",
                success: function (data) {
                    if(data){
                        table_refresh();
                        alert("删除成功！")
                    }else {
                        alert("删除失败！")
                    }
                },error:function () {
                    alert("系统出错！")
                }
            });
        }

    }
    //增加
    function primaryClick() {
        var username=$("#username").val(),
            pass=$("#pass").val(),
            role=$("#form_stuff .role").val(),
            unit=$("#form_stuff .unit").val(),
            name=$("#name").val(),
            department=$("#form_stuff .department").val(),
            phone=$("#phone").val();

        var datas={
            "username" : username,
            "pass" : pass,
            "role" : role,
            "unit" : unit,
            "department" : department,
            "name" : name,
            "phone" : phone
        };
           $.ajax({
               url:"/registerUser.do",
               dataType:"json",
               type:"post",
               data:datas,
               async:false,
               success:function (val) {
                   $('#form_stuff').modal('hide');
                   if(val.result =="success"){
                       table_refresh();
                       alert("提交成功");
                   }else {
                       alert("提交失败");
                   }
               },error:(function(){
                   alert("系统出错")
               })
           })

    }
    //表格刷新
    function table_refresh() {
        all_table.ajax.url("/userManagementDataTableFirst.do").load();
        // New_table.ajax.url("/sendFileDataTableSecond.do").load();
    }
    //初始化角色
    function initRole() {
        $.ajax({
            url:"getRoles.do",
            dataType:"json",
            type:"post",
            data:"",
            async:false,
            success:function (val) {
                var str="";
                $.each(val,function (a,b) {
                    str+="<option>"+b+"</option>"
                });
                $(".role").html(str)
            },error:(function(){
                alert("系统出错")
            })
        })
    }
    //初始化 单位名称UnitName 部门Department
    function initUND() {
        $.ajax({
            url:"/getUnitAndDepartments.do",
            dataType:"json",
            type:"post",
            data:"",
            async:false,
            success:function (val) {
                var str="",str1="";
                $.each(val,function (a,b) {
                    str+="<option value="+b.unit+">"+b.unit+"</option>";
                });
                $(".unit").html(str);

                $.each(val[0].departmentList,function (h,k) {
                    str1+="<option value="+k+">"+k+"</option>"
                });
                $(".department").html(str1);

                $(".unit").change(function () {
                    var sel=$(".unit").val();
                    var str2="";
                    $.each(val,function(c,d){
                        if(sel==d.unit){
                            $.each(d.departmentList,function (e,f) {
                                str2+="<option value="+f+">"+f+"</option>"
                            })
                        }
                    });
                    $(".department").html(str2);
                })
            },error:(function(){
                alert("系统出错")
            })
        })
    }












    






