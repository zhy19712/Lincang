

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





$(function () {

    //树状复选框插件
    $("#tree_container").jstree({
        "plugins" : ["checkbox"],

    });
    $("#tree_container").jstree().get_selected(true); //获取选中的
    $('#tree_container').jstree('deselect_all');//全部取消
    $('#tree_container').jstree('select_all');//全部选中



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
                $("#unit").html(str);

                $.each(val[0].departmentList,function (h,k) {
                    str1+="<option value="+k+">"+k+"</option>"
                });
                $("#department").html(str1);

                 $("#unit").change(function () {
                     var sel=$("#unit").val();
                     var str2="";
                    $.each(val,function(c,d){
                        if(sel==d.unit){
                            $.each(d.departmentList,function (e,f) {
                                str2+="<option value="+f+">"+f+"</option>"
                            })
                        }
                    });
                     $("#department").html(str2);
                 })
            },error:(function(){
                alert("系统出错")
            })
        })
    }
    //初始化 单位名称UnitName 部门Department
    initUND();

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
                })
                $("#role").html(str)
            },error:(function(){
                alert("系统出错")
            })
        })
    }
    //动态角色
    initRole();



    //提交事件
    $("#btn-primary").click(function () {

        primaryClick()
    });

    function primaryClick() {
        var username=$("#username").val(),
            pass=$("#pass").val(),
            role=$("#role").val(),
            unit=$("#unit").val(),
            name=$("#name").val(),
            department=$("#department").val(),
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
                if(val.result =="success"){
                    table_refresh();
                    $('#form_stuff').modal('hide');
                    alert("提交成功");
                }else {
                    alert("提交失败");
                    $('#form_stuff').modal('hide');
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


    
    



})



