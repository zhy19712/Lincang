

//打印
// $("#btn-print").click(function () {
//     jQuery('#fileForm').print();
// })

// 全部列表datatables

// var newForm_stuff = $('#NewTable_Stuff').DataTable({
//     ajax: {
//         url: "/nform_stuff.do"
//     },
//     "order": [[2, 'asc']],
//     "serverSide": true,
//     "columns": [
//         {"data": "ID"},
//         {"data": "TITLE"},
//         {"data": "CREATED_AT"},
//         {"data": null}
//     ],
//     "columnDefs": [
//         {
//             "searchable": false,
//             "orderable": false,
//             "targets": [3],
//             "render" :  function(data,type,row) {
//                 var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='detail(this)' value='查看'/>";
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


//待办列表的datatables
// var newForm_office = $('#NewTable_Office').DataTable({
//     ajax: {
//         url: "/nform_office.do",
//         async:false
//     },
//     "order": [[1, 'asc']],
//     "serverSide": true,
//     "columns": [
//         {"data": "OID"},
//         {"data": "CREATED_AT"},
//         {"data": "DEPT"},
//         {"data": "AUTHOR"},
//         {"data": "TITLE"},
//         {"data": null}
//     ],
//     "columnDefs": [
//         {
//             "searchable": false,
//             "orderable": false,
//             "targets": [5],
//             "render" :  function(data,type,row) {
//                 var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' value='查看'/>";
//                 html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;'  value='编辑'/>" ;
//                 return html;
//             }
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


// 已办表单的datatables

// var SubmittedTable_Office = $('#SubmittedTable_Office').DataTable({
//     ajax: {
//         url: "/sform_stuff.do"
//     },
//     "order": [[2, 'asc']],
//     "serverSide": true,
//     "columns": [
//         {"data": "ID"},
//         {"data": "TITLE"},
//         {"data": "CREATED_AT"},
//         {"data": null}
//     ],
//     "columnDefs": [
//         {
//             "searchable": false,
//             "orderable": false,
//             "targets": [3],
//             "render" :  function(data,type,row) {
//                 var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='detail(this)' value='编辑'/>"
//                 html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='flow(this)' value='流程'/>" ;
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


//待办事务的显示条数
// setTimeout(function () {
//     var info=newForm_office.page.info();
//     $("#nav_num").text(info.recordsTotal)
// },0);


//树形插件
$('#tree_container').jstree({
    "plugins" : ["checkbox"]
});

//选择处理人
$("#select_people li input").focus(function () {
    var text = $(this).siblings("span").text();
    $("#sel_people>p").text(text);
})
//处理人集合
var people_arr = [];
$("#sel_people button").click(function () {
    people_arr = [];
    var peole_kind = $("#sel_people>p").text();
    $.each($("#tree_container .jstree-leaf"),function (i,n) {
        if($(n).attr("aria-selected") == "true"){
            people_arr.push($(n).text())
        }
    })
    var people = people_arr.toString();
    if(peole_kind == "选择领导签批人"){
        $("#lingdao").val(people);
    }else if(peole_kind == "选择办理人"){
        $("#banli").val(people);
    }
});


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


//  新建表单 表单提交
$("#form_stuff .btn-primary").click(function () {
    var options  = {
        url:'/submitSendFile.do',
        type:'post',
        success:function(data)
        {
            console.log(data);
            if(data.result == "success"){
                alert("提交成功");
                $('#form_stuff').modal('hide');
            }else {
                alert(data.result);
            }
        }
    };
    $("#fileForm").ajaxSubmit(options);
});

//办公室提交
$("#select_model .btn-primary").click(function () {
    var lingdao = $("#lingdao").val();
    var banli = $("#banli").val();
    var sn = $("#select_model tr:nth-child(1) td:nth-child(2) input").val();
    var date = $("#select_model tr:nth-child(1) td:nth-child(5) input").val();
    var urgency = $("#select_model tr:nth-child(1) td:nth-child(7) input").val();
    var secret = $("#select_model tr:nth-child(1) td:nth-child(9) input").val();
    var qianfa = $("#select_model tr:nth-child(2) td:nth-child(1) textarea").val();
    var shengao = $("#select_model tr:nth-child(2) td:nth-child(2) textarea").val();
    var huiqian = $("#select_model tr:nth-child(2) td:nth-child(3) textarea").val();
    var chaobao = $("#select_model tr:nth-child(3) td:nth-child(1) textarea").val();
    var chaosong = $("#select_model tr:nth-child(4) td:nth-child(2) input").val();
    var fa = $("#select_model tr:nth-child(5) td:nth-child(2) input").val();
    var dept = $("#select_model tr:nth-child(6) td:nth-child(2) input").val();
    var author = $("#select_model tr:nth-child(6) td:nth-child(4) input").val();
    var reviewer = $("#select_model tr:nth-child(6) td:nth-child(6) input").val();
    var print = $("#select_model tr:nth-child(7) td:nth-child(2) input").val();
    var revision = $("#select_model tr:nth-child(7) td:nth-child(4) input").val();
    var copy = $("#select_model tr:nth-child(7) td:nth-child(6) input").val();
    var keyword = $("#select_model tr:nth-child(9) td:nth-child(2) input").val();
    var title = $("#select_model tr:nth-child(10) td:nth-child(2) input").val();
    var content = $("#select_model tr:nth-child(11) td:nth-child(1) textarea").val();
    var text = new Object();
    text.sn = sn;
    text.date = date;
    text.urgency = urgency;
    text.secret = secret;
    text.qianfa = qianfa;
    text.shengao = shengao;
    text.huiqian = huiqian;
    text.chaobao = chaobao;
    text.chaosong = chaosong;
    text.fa = fa;
    text.dept = dept;
    text.author = author;
    text.reviewer = reviewer;
    text.print = print;
    text.revision = revision;
    text.copy = copy;
    text.keyword = keyword;
    text.title = title;
    text.content = content;
    console.log(lingdao,banli,text);
})

