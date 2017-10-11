//待办表单的datatable
var newForm_stuff = $('#NewTable_Stuff').DataTable({
    ajax: {
        url: "/nform_stuff.do",
    },
    "order": [[2, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "ID"},
        {"data": "TITLE"},
        {"data": "CREATED_AT"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [3],
            "render" :  function(data,type,row) {
                var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='detail(this)' value='查看'/>"
                html += "<input type='button' class='btn btn-danger btn-xs' style='margin-left: 5px;' onclick='deleteOrder(this)' value='删除'/>"
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

//再办表单的datatable
var submittedForm_stuff = $('#SubmittedTable_Stuff').DataTable({
    ajax: {
        url: "/sform_stuff.do"
    },
    "order": [[2, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "ID"},
        {"data": "TITLE"},
        {"data": "CREATED_AT"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [3],
            "render" :  function(data,type,row) {
                var html = "<input type='button' class='btn btn-info btn-xs' style='margin-left: 5px;' onclick='flow(this)' value='流程'/>" +
                    "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' disabled='disabled' onclick='' value='确认'/>";
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

//已办表单的datatable

var comForm_stuff = $('#OverTable_Stuff').DataTable({
    ajax: {
        url: "/archivedtable_stuff.do",
    },
    "order": [[2, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "ID"},
        {"data": "TITLE"},
        {"data": "CREATED_AT"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [3],
            "render" :  function(data,type,row) {
                var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='comdetail(this)' value='查看'/>"
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

//已归档事务的datatable

var comForm_office = $('#OverTable_Office').DataTable({
    ajax: {
        url: "/archivedtable_office.do",
    },
    "order": [[2, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "OID"},
        {"data": "CREATED_AT"},
        {"data": "DEPT"},
        {"data": "AUTHOR"},
        {"data": "TITLE"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [5],
            "render" :  function(data,type,row) {
                var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='comdetail(this)' value='查看'/>"
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

//审批记录的datatable

// var comForm_stuff = $('#OverTable_Approval').DataTable({
//     ajax: {
//         url: "/nform_stuff.do",
//     },
//     "order": [[2, 'asc']],
//     "serverSide": true,
//     "columns": [
//         {"data": "ID"},
//         {"data": "ID"},
//         {"data": "TITLE"},
//         {"data": "CREATED_AT"},
//         {"data": "CREATED_AT"},
//         {"data": null}
//     ],
//     "columnDefs": [
//         {
//             "searchable": false,
//             "orderable": false,
//             "targets": [5],
//             "render" :  function(data,type,row) {
//                 var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='comdetail(this)' value='查看'/>"
//                 return html;
//             }
//         },
//         {
//             "searchable": false,
//             "orderable": false,
//             "targets": [0]
//         }
//     ],
//     "language": {
//         "lengthMenu": "每页_MENU_ 条记录",
//         "zeroRecords": "没有找到记录",
//         "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
//         "infoEmpty": "无记录",
//         "search": "搜索：",
//         "infoFiltered": "(从 _MAX_ 条记录过滤)",
//         "paginate": {
//             "previous": "上一页",
//             "next": "下一页"
//         }
//     }
// });

// 多文件上传
var fileIndex = 1;
function add_click_file(index){
    $("#add_file_"+fileIndex).click();
}

function add(index) {
    /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
    var len = $("#add_file_" + (fileIndex) + "").val().split("\\").length;
    alert($("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1]);
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

// 清空数据
function wipeData() {
    $("#input1").val("");
    $("#input2").val("");
    $("#input3").val("");
    $("#input4").val("");
    $("#input5").val("");
    $("#input6").val("");
    $("#input8").val("");
    $("#input9").val("");
    $("#input10").val("");
    $(".add_file").remove();
    $("#oId").text("");
    $("#created_at").text("");
}

//新建表单 表单放弃
$("#form_stuff .btn-danger").click(function () {
        wipeData()
});

//新建表单 表单关闭 x
$("#close_stuff").click(function(){
    wipeData()
});

// 新建表单 表单保存
$("#form_stuff .btn-success").click(function () {
    var dept=  $("#input1").val();
    var author=  $("#input2").val();
    var reviewer=  $("#input3").val();
    var print=  $("#input4").val();
    var revision=  $("#input5").val();
    var copy=  $("#input6").val();
    var keyword=  $("#input8").val();
    var title=  $("#input9").val();
    var content=  $("#input10").val();
    var attachment= $("#filesUpload > span");
    var arrAttachment=[];
    var id=$("#oId").text();
    var created_at=$("#created_at").text();
    for(var i=0;i<attachment.length;i++){
        arrAttachment.push(attachment.eq(i).text());
    }
    var datas= {
        "dept":dept,
        "author":author,
        "reviewer":reviewer,
        "print":print,
        "revision":revision,
        "copy":copy,
        "keyword":keyword,
        "title":title,
        "content":content,
        "id":id,
        "created_at":created_at
    };

    if(dept == ""){
        alert("拟稿单位不能为空");
    }else if(author == ""){
        alert("拟稿不能为空");
    }else if(title == ""){
        alert("标题不能为空");
    }else if(content == ""){
        alert("内容不能为空");
    }else{
        $.ajax({
            url: '/saveFormData.do',
            type: 'post',
            data: datas,
            dataType: 'json',
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                if(data){
                    alert("保存成功");
                }else {
                    alert("保存失败");
                }
                $("#form_stuff").modal("hide");
                wipeData();
            },
            error:function () {
                alert("系统错误");
            }
        });
    }
   /* $("#fileForm").submit();*/
   /*$("#fileForm").ajaxSubmit({
       url: '/file/multipleUpload.do',
       type: "post",
       enctype: 'multipart/form-data',
       dataType:'json',
       success: function (data)
       {
       },
       error: function (data)
       {
       }
   })*/
});

//  新建表单 表单提交
$("#form_stuff .btn-primary").click(function () {
    var dept=  $("#input1").val();
    var author=  $("#input2").val();
    var reviewer=  $("#input3").val();
    var print=  $("#input4").val();
    var revision=  $("#input5").val();
    var copy=  $("#input6").val();
    var keyword=  $("#input8").val();
    var title=  $("#input9").val();
    var content=  $("#input10").val();
    var attachment= $("#filesUpload > span");
    var arrAttachment=[];
    var id=$("#oId").text();
    var created_at=$("#created_at").text();
    for(var i=0;i<attachment.length;i++){
        arrAttachment.push(attachment.eq(i).text());
    }
    var datas= {
        "dept":dept,
        "author":author,
        "reviewer":reviewer,
        "print":print,
        "revision":revision,
        "copy":copy,
        /*"arrAttachment":arrAttachment,*/
        "keyword":keyword,
        "title":title,
        "content":content,
        "id":id,
        "created_at":created_at
    };
    if(dept == ""){
        alert("拟稿单位不能为空")
    }else if(author == ""){
        alert("拟稿不能为空")
    }else if(title == ""){
        alert("标题不能为空")
    }else if(content == ""){
        alert("内容不能为空")
    }else {
        $.ajax({
            type: 'post',
            url: '/submitFormData.do',
            data: datas,
            dataType: 'json',
            async:false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {

                if(result){
                    alert("提交成功");
                    newForm_stuff.ajax.url("/nform_stuff.do").load();
                    submittedForm_stuff.ajax.url("/sform_stuff.do").load();
                }else {
                    alert("提交失败");
                }
                $("#form_stuff").modal("hide");
                wipeData();
            },
            error:function () {
                alert("系统错误");
            }
        });
       /* $("#fileForm").submit();*/
    }
});

// #NewTable_Stuff 表格操作，查看按钮
function detail(that) {
    $("#form-kind").text("表单详情");
    $("#app1").css("display","none");
    $("#app2").css("display","none");
    $("#btn-save").css("display","none");
    $("#btn-submit").css("display","none");
    $("#btn-up").text("关闭").css("display","inline-block");
    var oid = $(that).parents("tr").children("td:nth-child(1)").text();
    var title = $(that).parents("tr").children("td:nth-child(2)").text();
    var time = $(that).parents("tr").children("td:nth-child(3)").text();
    $.ajax({
        url: '/queryStuffById.do',
        type: 'post',
        data: "id="+oid,
        dataType: 'json',
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (data) {
            console.log(data);
            $("#input1").val(data.dept);
            $("#input2").val(data.author);
            $("#input3").val(data.reviewer);
            $("#input4").val(data.print);
            $("#input5").val(data.revision);
            $("#input6").val(data.copy);
            $("#input8").val(data.keyword);
            $("#input9").val(data.title);
            $("#input10").val(data.content);
            $("#oId").text(data.id);
            $("#created_at").text(data.created_at);
            $('#form_stuff').modal('show');
        }
    })
}

//删除按钮
function deleteOrder(that) {
    var oid = $(that).parents("tr").children("td:nth-child(1)").text();
    $.ajax({
        url: '/deleteStuffById.do',
        type: 'post',
        data: "id="+oid,
        dataType: 'json',
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (val) {
            if(val){
                newForm_stuff.ajax.url("/nform_stuff.do").load();
                alert("删除成功")
            }else {
                alert("删除失败")
            }
        },
        error:function () {
            alert("系统错误");
        }
    })
}

// #comForm_stuff 表格操作，查看按钮
function comdetail(that) {
    $("#form-kind").text("表单详情");
    $("#app1").css("display","block");
    $("#app2").css("display","block");
    $("#btn-save").css("display","none");
    $("#btn-submit").css("display","none");
    $("#btn-up").text("关闭").css("display","inline-block");
    var oid = $(that).parents("tr").children("td:nth-child(1)").text();
    var title = $(that).parents("tr").children("td:nth-child(2)").text();
    var time = $(that).parents("tr").children("td:nth-child(3)").text();
    $.ajax({
        url: '/queryStuffById.do',
        type: 'post',
        data: "id="+oid,
        dataType: 'json',
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (data) {
            console.log(data);
            $("#input1").val(data.dept);
            $("#input2").val(data.author);
            $("#input3").val(data.reviewer);
            $("#input4").val(data.print);
            $("#input5").val(data.revision);
            $("#input6").val(data.copy);
            $("#input8").val(data.keyword);
            $("#input9").val(data.title);
            $("#input10").val(data.content);
            $("#oId").text(data.id);
            $("#created_at").text(data.created_at);
            $('#form_stuff').modal('show');
        }
    })
}


//待办事务  编辑按钮
function detail_office(that){

}
