

//打印
// $("#btn-print").click(function () {
//     jQuery('#fileForm').print();
// })

// 全部列表datatables

var all_table = $('#NewTable_Stuff').DataTable({
    ajax: {
        url: "/sendFileDataTableFirst.do"
    },
    "order": [[2, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "sendfileid"},
        {"data": "title"},
        {"data": "createdtime"},
        {"data": "dept"},
        {"data": "status"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [5],
            "render" :  function(data,type,row) {
                var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
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


//待办列表的datatables
var dcl_table = $('#NewTable_Office').DataTable({
    ajax: {
        url: "/sendFileDataTableSecond.do",
        async:false
    },
    "order": [[1, 'asc']],
    "serverSide": true,
    "columns": [
        {"data": "sendfileid"},
        {"data": "title"},
        {"data": "createdtime"},
        {"data": "dept"},
        {"data": "status"},
        {"data": null}
    ],
    "columnDefs": [
        {
            "searchable": false,
            "orderable": false,
            "targets": [5],
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


// 已办表单的datatables

// var ycl_table = $('#SubmittedTable_Office').DataTable({
//     ajax: {
//         url: "/sform_stuff.do"
//     },
//     "order": [[2, 'asc']],
//     "serverSide": true,
//     "columns": [
//         {"data": "sendfileid"},
        // {"data": "title"},
        // {"data": "createdtime"},
        // {"data": "dept"},
        // {"data": "status"},
//         {"data": null}
//     ],
//     "columnDefs": [
//         {
//             "searchable": false,
//             "orderable": false,
//             "targets": [5],
//             "render" :  function(data,type,row) {
//                 var html = "<input type='button' class='btn btn-primary btn-xs' style='margin-left: 5px;' onclick='edit(this)' value='查看'/>";
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
setTimeout(function () {
    var info=dcl_table.page.info();
    $("#nav_num").text(info.recordsTotal)
},0);

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

//编辑查看按钮
var id,status;
function edit(that) {
    id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
    status = $(that).parent("td").parent("tr").children("td:nth-child(5)").text();
    console.log(id,status);
    $('#select_model').modal('show');
    $.ajax({
        url: "/getSendFileInfoBySendFileId.do",
        type: "post",
        data: {sendFileid:id},
        dataType: "json",
        success: function (data) {
            console.log(data);
            $(".mytable tr:nth-child(1) td:nth-child(2) input").val(data.sn);
            $(".mytable tr:nth-child(1) td:nth-child(5) input").val(data.date);
            $(".mytable tr:nth-child(1) td:nth-child(7) input").val(data.urgency);
            $(".mytable tr:nth-child(1) td:nth-child(9) input").val(data.secret);
            $(".mytable tr:nth-child(2) td:nth-child(1) textarea").val(data.qianfa);
            $(".mytable tr:nth-child(2) td:nth-child(2) textarea").val(data.shengao);
            $(".mytable tr:nth-child(2) td:nth-child(3) textarea").val(data.huiqian);
            $(".mytable tr:nth-child(3) td:nth-child(1) textarea").val(data.chaobao);
            $(".mytable tr:nth-child(4) td:nth-child(2) input").val(data.chaosong);
            $(".mytable tr:nth-child(5) td:nth-child(2) input").val(data.fa);
            $(".mytable tr:nth-child(6) td:nth-child(2) input").val(data.dept);
            $(".mytable tr:nth-child(6) td:nth-child(4) input").val(data.author);
            $(".mytable tr:nth-child(6) td:nth-child(6) input").val(data.reviewer);
            $(".mytable tr:nth-child(7) td:nth-child(2) input").val(data.print);
            $(".mytable tr:nth-child(7) td:nth-child(4) input").val(data.revision);
            $(".mytable tr:nth-child(7) td:nth-child(6) input").val(data.copy);
            $(".mytable tr:nth-child(9) td:nth-child(2) input").val(data.keyword);
            $(".mytable tr:nth-child(10) td:nth-child(2) input").val(data.title);
            $(".mytable tr:nth-child(11) td:nth-child(1) textarea").val(data.content);
            $(".mytable tr:nth-child(8) td:nth-child(2)").empty();
            var file_arr = data.attachmentpath.split(",");
            if(data.attachmentpath != ""){
                var file_arr = data.attachmentpath.split(",");
                $.each(file_arr,function (i,n) {
                    var start = n.lastIndexOf("\\") + 1;
                    var end = n.lastIndexOf("-");
                    var filekind_index = n.lastIndexOf(".");
                    var str = n.substring(start,end);
                    var filekind = n.substring(filekind_index);
                    str = str + filekind;
                    var files = "";
                    files  += ""
                        + "<div class='download_wrapper' style='display: inline-block;margin: 0 5px;'>"
                        + "<iframe name='downloadFrame' style='display:none;'></iframe>"
                        + "<form action='/file/download.do' method='get' target='downloadFrame'>"
                        + "<span class='file_name' style='color: #000;'>"+str+"</span>"
                        + "<input class='file_url' style='display: none;' name='path' value="+ n +">"
                        + "<button type='submit'>下载</button>"
                        + "</form>"
                        + "</div>"
                    $(".mytable tr:nth-child(8) td:nth-child(2)").append(files);
                    console.log(str)
                });
            }
        }
    })
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
                alert("提交失败")
            }
        },
        error:function () {
            alert("系统错误");
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
    text.approver = lingdao;
    text.implementperson = banli;
    text.id = id;
    text.status = status;
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
    var mytext = JSON.toString(text);
    // $.ajax({
    //     url: "",
    //     type: "post",
    //     data: {text:mytext},
    //     dataType: "json",
    //     success: function (data) {
    //         console.log(data);
    //     }
    // })
})

