$(function () {

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
                    var html = "<input type='button' class='btn btn-primary btn-xs edit' style='margin-left: 5px;' value='查看'/>";
                    html += "<input type='button' class='btn btn-warning btn-xs edit' style='margin-left: 5px;' value='编辑'/>" ;
                    html += "<input type='button' class='btn btn-danger btn-xs edit' style='margin-left: 5px;' value='删除'/>" ;
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
            url: "/userManagementDataTableRole.do"
        },
        "order": [[0, 'asc']],
        "serverSide": true,
        "columns": [
            {"data": "id"},
            {"data": "rolename"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [2],
                "render" :  function(data,type,row) {
                    var html = "<input type='button' class='btn btn-primary btn-xs editRole' style='margin-left: 5px;' value='查看'/>";
                    html += "<input type='button' class='btn btn-warning btn-xs editRole' style='margin-left: 5px;' value='编辑'/>" ;
                    html += "<input type='button' class='btn btn-danger btn-xs editRole' style='margin-left: 5px;' value='删除'/>" ;
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

    //用户添加的正则
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

    //用户修改的正则
    var status4=true,status6=true;
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

    //树状复选框插件
    $("#tree_container").jstree({
        "plugins" : ["checkbox"]
    });
    $("#tree_container1").jstree({
        "plugins" : ["checkbox"]
    });

    //打开添加用户模态框
    $("#newForm").click(function () {
        status1=false;status2=false;status3=false;
        $('#form_add_users input').val('');
        $('#form_add_users').modal('show');
        //用户初始化 单位名称UnitName 部门Department
        initUND("form_add_users");
        //用户初始化 角色
        initRole("form_add_users");

        $("#usernamePrompt").text("以字母开头,可以字母和数字组合,长度在2个以上").css("color","black");
        $("#passPrompt").text("数字和字母组合,字符,长度在6~18之间").css("color","black");
        $("#namePrompt").text("必须为汉字").css("color","black");
    });
    //打开添加角色模态框
    $("#newRoleForm").click(function () {
        $('#form_add_Role input').val('');
//        树状复选框初始默认选中
        var instance = $('#tree_container').jstree(true);
        instance.deselect_all();
        var inArr=['1','2','3',"4","6","7","8","9","10","11"];
        instance.select_node(inArr);
        $('#form_add_Role').modal('show');
    });

    //用户添加按钮
    var flagUserAdd=true;
    $("#btn-primary").click(function () {
        if(status1 && status2 && status3){
            if(flagUserAdd){
                flagUserAdd=false;
                addUser()
            }
        }else {
            alert("请正确添加用户")
        }
    });
    //用户修改按钮
    var flagUserUpdate=true;
    $("#btn-update").click(function () {
        if(status4 && status6){
            if(flagUserUpdate){
                flagUserUpdate=false
                updateUser();
            }
        }else {
            alert("请正确修改用户")
        }
    });
    //角色添加按钮
    var flagRoleAdd=true;
    $("#roleBtnAdd").click(function () {
        var roleName= $("#roleName").val();
        if(roleName==""){
            alert("角色名不能为空！")
        }else {
            if(flagRoleAdd){
                addRole()
            }

        }
    });
    //角色修改按钮
    var flagRoleUpdate=true;
    $("#roleBtnUpdate").click(function () {
        var roleName1=$("#roleName1").val();
        if(roleName1==""){
            alert("角色不能为空")
        }else {
            if(flagRoleUpdate){

                updateRole();
            }

        }
    });

    //用户操作
    $("#NewTable_Admin").on("click",".edit",function () {
        initUND("form_update_users");
        initRole("form_update_users");
        status4=true;status6=true;
        $("#passPrompt1").text("数字和字母组合,字符,长度在6~18之间").css("color","black");
        $("#namePrompt1").text("必须为汉字").css("color","black");

        var id = $(this).parent("td").parent("tr").children("td:nth-child(1)").text(),
            kind = $(this).val(),
            url="/deleteRegisterInfoById.do";
        $("#user_id").text(id);

        if(kind=="查看"){
            $("#form-kind1").text("查看用户");
            $("#btn-update").hide();
            lookUser(id);
        }else if(kind=="编辑"){
            $("#form-kind1").text("编辑用户");
            $("#btn-update").show();
            lookUser(id);
        }else if(kind=="删除"){
            deldata(id,url);
        }
    });
    // 用户查看
    function lookUser(id) {
        $.ajax({
            url: "/getRegisterInfoById.do",
            type: "post",
            async: false,
            data: {"id":id},
            dataType: "json",
            success: function (data) {
                $("#username1").val(data.username);
                $("#pass1").val(data.pass);
                $("#form_update_users .role").val(data.role);
                $("#form_update_users .unit").val(data.unit);
                $("#name1").val(data.name);
                $("#phone1").val(data.phone);
                $("#form_update_users").modal('show');

                $.ajax({
                    url:"/getUnitAndDepartments.do",
                    dataType:"json",
                    type:"post",
                    data:"",
                    async:false,
                    success:function (val) {
                        var str="";
                        $.each(val,function (a,b) {
                            if(b.unit==data.unit){
                                $.each(b.departmentList,function (e,f) {
                                    str+="<option value="+f+">"+f+"</option>";
                                })
                            }
                        });
                        $("#form_update_users .department").html(str);
                        $("#form_update_users .department").val(data.department);
                    },error:(function(){
                        alert("系统出错")
                    })
                })

            }
        });
    }
    //用户修改
    function updateUser() {
        var username=$("#username1").val(),
            pass=$("#pass1").val(),
            role=$("#form_update_users .role").val(),
            unit=$("#form_update_users .unit").val(),
            name=$("#name1").val(),
            department=$("#form_update_users .department").val(),
            phone=$("#phone1").val(),
            id=$("#user_id").text();

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

        $.ajax({
            url:"/updateRegisterInfoById.do",
            dataType:"json",
            type:"post",
            data:datas,
            async:false,
            success:function (val) {
                flagUserUpdate=true;
                if(val.result =="success"){
                    table_refresh();
                    alert("修改成功");
                    $('#form_update_users').modal('hide');
                }else {
                    alert("修改失败");
                }
            },error:(function(){
                flagUserUpdate=true;
                alert("系统出错")
            })
        })
    }
    //用户增加
    function addUser() {
        var username=$("#username").val(),
            pass=$("#pass").val(),
            role=$("#form_add_users .role").val(),
            unit=$("#form_add_users .unit").val(),
            name=$("#name").val(),
            department=$("#form_add_users .department").val(),
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
                flagUserAdd= true;
                if(val.result =="success"){
                    table_refresh();
                    alert("添加成功");
                    $('#form_add_users').modal('hide');
                }else if(val.result =="rename"){
                    alert("该用户名已存在！");
                }else {
                    alert("添加失败");
                }
            },error:(function(){
                flagUserAdd= true;
                alert("系统出错")
            })
        })

    }

    //用户管理\角色管理 操作删除
    function deldata(id,url) {
        if(confirm("你确定要删除吗？")){
            $.ajax({
                url: url,
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
    //角色添加
    function addRole(){
        var dataArr=$("#tree_container").jstree().get_selected(true),
            roleName=$("#roleName").val(),
            idArr=[];

        $.each(dataArr,function (a,b) {
            if(parseInt(b.id)){
                idArr.push(parseInt(b.id));
            }
        });
        var datas={
            "role":roleName,
            "functionList":idArr
        };


        // 18/19/46
        var a= contains(idArr, 18);
        var b= contains(idArr, 19);
        var i= contains(idArr, 46);
        //26,27
        var c= contains(idArr, 26);
        var d= contains(idArr, 27);
        //41 ,42
        var e= contains(idArr, 41);
        var f= contains(idArr, 42);
        //13，45
        var g= contains(idArr, 13);
        var h= contains(idArr, 45);


        if(idArr.length==0){
            alert("功能至少选择一项")
        }else {
            if(g && h){
                alert("市资金申请全部列表查看、搜索功能和全部列表查看、搜索、删除功能不能同时选择！")
            }else {
                if(a && b && i){
                    alert("区县资金申请全部列表查看、搜索、删除功能和个人申请列表查看、搜索功能和全部列表查看、搜索功能")
                }
                if(a && i){
                    alert("区县资金申请全部列表查看、搜索、删除功能和全部列表查看、搜索功能")
                }else {
                    if(b && i){
                        alert("区县资金申请个人申请列表查看、搜索功能和全部列表查看、搜索功能")
                    }else {
                        if(a && b){
                            alert("区县资金申请全部列表和个人列表不能同时选择!")
                        }else {
                            if (c && d){
                                alert("发文模块，全部列表和个人列表不能同时选择!")
                            }else {
                                if(e && f){
                                    alert("非文件模块，全部列表和个人列表不能同时选择!")
                                }else {
                                    flagRoleAdd=false;


                                    $.ajax({
                                        url:"/registerRole.do",
                                        dataType:"json",
                                        type:"post",
                                        data:datas,
                                        async:false,
                                        success:function (val) {
                                            flagRoleAdd=true;
                                            if(val.result =="success"){
                                                table_refresh();
                                                alert("添加成功");
                                                $("#form_add_Role").modal('hide');
                                            }else {
                                                alert("添加失败");
                                            }
                                        },error:(function(){
                                            flagRoleAdd=true;
                                            alert("系统出错")
                                        })
                                    })
                                }
                            }
                        }
                    }
                }

            }

        }
    }
    //角色操作
    $("#NewTable_role").on("click",".editRole",function () {
        var id = $(this).parent("td").parent("tr").children("td:nth-child(1)").text(),
            kind = $(this).val(),
            url="/deleteRole.do";
        $("#role_id").text(id);

        if(kind=="查看"){
            $("#form_update_Role").modal('show');
            $("#Role-kind1").text("查看角色");
            $("#roleBtnUpdate").hide();
            lookRole(id)
        }else if(kind=="编辑"){
            $("#form_update_Role").modal('show');
            $("#Role-kind1").text("修改角色");
            $("#roleBtnUpdate").show();
            lookRole(id)
        }else if(kind=="删除"){
            deldata(id,url)
        }
    });
    //角色查看
    function lookRole(id) {
        $.ajax({
            url: "/selectRole.do",
            type: "post",
            async: false,
            data: {"id":id},
            dataType: "json",
            success: function (data) {
                var functionList=data.functionList;
                var newId=functionList.map(function (a) {
                    return a+"j"
                });
                var RoleIdArr = $('#tree_container1').jstree(true);
                RoleIdArr.deselect_all();  //functionList
                RoleIdArr.select_node(newId);

                $("#roleName1").val(data.rolename);
                $("#form_update_Role").modal('show');
            }
        });
    }
    //角色修改
    function updateRole() {
        var dataArr=$("#tree_container1").jstree().get_selected(true),
            roleName=$("#roleName1").val(),
            idArr=[],
            id=$("#role_id").text();

        $.each(dataArr,function (a,b) {
            if(parseInt(b.id)){
                idArr.push(parseInt(b.id));
            }
        });

        var datas={
            "role":roleName,
            "functionList":idArr,
            "id":id
        };
        // 18/19/46
        var a= contains(idArr, 18);
        var b= contains(idArr, 19);
        var i= contains(idArr, 46);
        //26,27
        var c= contains(idArr, 26);
        var d= contains(idArr, 27);
        //41 ,42
        var e= contains(idArr, 41);
        var f= contains(idArr, 42);
        //13，45
        var g= contains(idArr, 13);
        var h= contains(idArr, 45);


        if(idArr.length==0){
            alert("功能至少选择一项")
        }else {
            if(g && h){
                alert("市资金申请全部列表查看、搜索功能和全部列表查看、搜索、删除功能不能同时选择！")
            }else {
                if(a && b && i){
                    alert("区县资金申请全部列表查看、搜索、删除功能和个人申请列表查看、搜索功能和全部列表查看、搜索功能")
                }
                if(a && i){
                    alert("区县资金申请全部列表查看、搜索、删除功能和全部列表查看、搜索功能")
                }else {
                    if(b && i){
                        alert("区县资金申请个人申请列表查看、搜索功能和全部列表查看、搜索功能")
                    }else {
                        if(a && b){
                            alert("区县资金申请全部列表和个人列表不能同时选择!")
                        }else {
                            if (c && d){
                                alert("发文模块，全部列表和个人列表不能同时选择!")
                            }else {
                                if(e && f){
                                    alert("非文件模块，全部列表和个人列表不能同时选择!")
                                }else {
                                    flagRoleUpdate=false;
                                    $.ajax({
                                        url:"/updateRole.do",
                                        dataType:"json",
                                        type:"post",
                                        data:datas,
                                        async:false,
                                        success:function (val) {
                                            flagRoleUpdate=true;
                                            if(val.result =="success"){
                                                table_refresh();
                                                alert("修改成功");
                                                $("#form_update_Role").modal('hide');
                                            }else {
                                                alert("修改失败");
                                            }
                                        },error:(function(){
                                            flagRoleUpdate=true;
                                            alert("系统出错")
                                        })
                                    })
                                }
                            }
                        }
                    }
                }

            }

        }

    }
    //表格刷新
    function table_refresh() {
        all_table.ajax.url("/userManagementDataTableFirst.do").load();
        New_table.ajax.url("/userManagementDataTableRole.do").load();
    }
    //角色 表单复选框选中验证
    function contains(arr, obj) {
        var i = arr.length;
        while (i--) {
            if (arr[i] === obj) {
                return true;
            }
        }
        return false;
    }
    //用户初始化角色
    function initRole(that) {

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
                $("#"+that+" .role").html(str)
            },error:(function(){
                alert("系统出错")
            })
        })
    }
    //用户初始化 单位名称UnitName 部门Department
    function initUND(that) {

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
                $("#"+that+" .unit").html(str);

                $.each(val[0].departmentList,function (h,k) {
                    str1+="<option value="+k+">"+k+"</option>"
                });
                $(".department").html(str1);

                $("#"+that+" .unit").change(function () {
                    var sel=$("#"+that+" .unit").val();
                    var str2="";

                    $.each(val,function(c,d){
                        if(sel==d.unit){
                            $.each(d.departmentList,function (e,f) {
                                str2+="<option value="+f+">"+f+"</option>"
                            })
                        }
                    });
                    $("#"+that+" .department").html(str2);
                })
            },error:(function(){
                alert("系统出错")
            })
        })
    }

});







