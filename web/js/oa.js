

//打印

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
                html += "<input type='button' class='btn btn-warning btn-xs' style='margin-left: 5px;' onclick='delete1(this)' value='删除'/>" ;
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

var ycl_table = $('#SubmittedTable_Office').DataTable({
    ajax: {
        url: "/sendFileDataTableThird.do"
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

//表格刷新
function table_refresh() {
    all_table.ajax.url("/sendFileDataTableFirst.do").load();
    dcl_table.ajax.url("/sendFileDataTableSecond.do").load();
    ycl_table.ajax.url("/sendFileDataTableThird.do").load();
}

//待办事务的显示条数
function acount(){
    var info=dcl_table.page.info();
    $("#nav_num").text(info.recordsTotal)
}
setTimeout(acount,0);

//树形插件
$('#tree_container').jstree({
    "plugins" : ["checkbox"]
});

//日期插件
$("#time1").jeDate({
    format: "YYYY-MM-DD"
});
//选择处理人
$("#select_people li input").focus(function () {
    var text = $(this).siblings("span").text();
    $("#sel_model>p:first-child").text(text);
    $('#tree_container').jstree('deselect_all');
})
//处理人集合
$("#sel_people button").click(function () {
    var people_kind = $("#sel_model>p:first-child").text();
    var people_arr = [];
    $.each($("#tree_container").jstree().get_selected(true),function (i,n) {
        if(n.parent != "#"){
            console.log(n);
            people_arr.push(n.text);
        }
    })
    var people = people_arr.toString();
    if(people_kind == "领导签批人"){
        $("#lingdao").val(people);
    }else if(people_kind == "办理人"){
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
//删除功能
function delete1(that) {
    var sendfileid = $(that).parent("td").parent("tr").children("td:first-child").text();
    console.log(sendfileid);
    $.ajax({
        url: "/deleteSendFile.do",
        type: "post",
        dataType: "json",
        data: {sendfileid:sendfileid},
        success: function (data) {
            if(data.result == "success"){
                all_table.ajax.url("/sendFileDataTableFirst.do").load();
                alert("删除成功");
            }else {
                alert(data.result);
            }
        }
    })
}

//编辑查看按钮
var id,status;
function edit(that) {
    $("#select_model input").val("");
    $("#select_model textarea").val("");
    id = $(that).parent("td").parent("tr").children("td:nth-child(1)").text();
    status = $(that).parent("td").parent("tr").children("td:nth-child(5)").text();
    $(".btn-success").css("display","none");
    var kind = $(that).val();
    console.log(id,status);
    var mydata;
    $.ajax({
        url: "/getSendFileInfoBySendFileId.do",
        type: "post",
        async: false,
        data: {sendFileid:id},
        dataType: "json",
        success: function (data) {
            console.log(data);
            mydata = data;
            // $("#leader").text(data.approver);
            // $("#people").text(data.implementperson);
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
            $(".mytable tr:nth-child(12) td:nth-child(2) textarea").val(data.result);
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
    });
    $("#select_model .modal-header h3").text("发文管理-" + id);
    $("#select_model .btn-primary").text("提交");
    $('#select_model').modal('show');
    $("#select_model input").attr("readonly",true);
    $("#select_model textarea").attr("readonly",true);
    $(".user_1").empty();
    $(".user_2").empty();
    $(".user_3").empty();
    $(".user_4").empty();
    $(".user_5").empty();
    if(mydata.applicant != ""){
        var str = "";
        str +=  ""
            +   "<div>"
            +   "<p class='user'>"+ mydata.applicant +"</p>"
            +   "<p class='time'>"+ mydata.createdtime +"</p>"
            +   "</div>"
        $(".user_1").append(str).addClass("myactive");
    }
    if(mydata.officeprocessperson != ""){
        var str = "";
        str +=  ""
            +   "<div>"
            +   "<p class='user'>"+ mydata.officeprocessperson +"</p>"
            +   "<p class='time'>"+ mydata.officeprocesstime +"</p>"
            +   "</div>"
        $(".user_2").append(str).addClass("myactive");
    }
    if(mydata.approver != ""){
        var peoplearr1 = mydata.approver.split(",");
        var deletearr1 = mydata.approverdelete.split(",");
        var timearr1 = mydata.approvertime.split(",");
        $.each(peoplearr1,function (i,n) {
            var str = "";
            str +=  ""
                +   "<div>"
                +   "<p class='user'>"+ n +"</p>"
                +   "<p class='time'></p>"
                +   "</div>"
            $(".user_3").append(str);
        });
        $.each(deletearr1,function (i,n) {
            var name = n;
            var time = timearr1[i];
            $.each(peoplearr1,function (i,n) {
                if(name == n){
                    $(".user_3>div:nth-child("+(i+1)+")").addClass("myactive");
                    $(".user_3>div:nth-child("+(i+1)+")").children(".time").text(time);
                }
            })
        })
    }
    if(mydata.implementperson != ""){
        var peoplearr2 = mydata.implementperson.split(",");
        var deletearr2 = mydata.implementpersondelete.split(",");
        var timearr2 = mydata.implementpersontime.split(",");
        $.each(peoplearr2,function (i,n) {
            var str = "";
            str +=  ""
                +   "<div>"
                +   "<p class='user'>"+ n +"</p>"
                +   "<p class='time'></p>"
                +   "</div>"
            $(".user_4").append(str);
        });
        $.each(deletearr2,function (i,n) {
            var name = n;
            var time = timearr2[i];
            $.each(peoplearr2,function (i,n) {
                if(name == n){
                    $(".user_4>div:nth-child("+(i+1)+")").addClass("myactive");
                    $(".user_4>div:nth-child("+(i+1)+")").children(".time").text(time);
                }
            })
        })
    }
    if(mydata.confirmperson != ""){
        var str = "";
        str +=  ""
            +   "<div>"
            +   "<p class='user'>"+ mydata.confirmperson +"</p>"
            +   "<p class='time'>"+ mydata.confirmtime +"</p>"
            +   "</div>"
        $(".user_5").append(str).addClass("myactive");
    }

    if(status == "办公室审核处理"){
        $("#select_model input").attr("readonly",false);
        $("#select_model textarea").attr("readonly",false);
        $("#select_model tr:nth-child(2) td:nth-child(1) textarea").attr("readonly",true);
        $("#select_model tr:nth-child(2) td:nth-child(3) textarea").attr("readonly",true);
        $("#select_model tr:nth-child(12) td:nth-child(2) textarea").attr("readonly",true);
        if(kind == "查看"){
            $("#people_list").css("display","none");
            $("#sel_model").css("display","none");
        }else if(kind == "编辑"){
            $("#select_people input").val("");
            $('#tree_container').jstree('deselect_all');
            $("#people_list").css("display","none");
            $("#sel_model").css("display","block");
        }

        step.goStep(2);
    }else {
        $("#people_list").css("display","block");
        $("#sel_model").css("display","none");
        if(status == "签批"){
            step.goStep(3);
            $("#select_model tr:nth-child(2) td:nth-child(1) textarea").attr("readonly",false);
            $("#select_model tr:nth-child(2) td:nth-child(3) textarea").attr("readonly",false);
        }else if(status == "处理处置"){
            step.goStep(4);
            $("#select_model tr:nth-child(12) td:nth-child(2) textarea").attr("readonly",false);
        }else if(status == "办公室归档"){
            step.goStep(5);
            $("#select_model .btn-primary").text("确认归档");
        }else if(status == "结束"){
            step.goStep(6);
            $(".btn-success").css("display","inline-block");
            $("#myprint tr:nth-child(2) td:nth-child(2)").text(mydata.sn);
            $("#myprint tr:nth-child(2) td:nth-child(5)").text(mydata.date);
            $("#myprint tr:nth-child(2) td:nth-child(7)").text(mydata.urgency);
            $("#myprint tr:nth-child(2) td:nth-child(9)").text(mydata.secret);

            $("#myprint tr:nth-child(3) td:nth-child(1) p:nth-child(2)").text(mydata.qianfa);
            $("#myprint tr:nth-child(3) td:nth-child(2) p:nth-child(2)").text(mydata.shengao);
            $("#myprint tr:nth-child(3) td:nth-child(3) p:nth-child(2)").text(mydata.huiqian);

            $("#myprint tr:nth-child(4) td:nth-child(1) p:nth-child(2)").text(mydata.chaobao);

            $("#myprint tr:nth-child(5) td:nth-child(2)").text(mydata.chaosong);

            $("#myprint tr:nth-child(6) td:nth-child(2)").text(mydata.fa);
            $("#myprint tr:nth-child(7) td:nth-child(2)").text(mydata.dept);
            $("#myprint tr:nth-child(7) td:nth-child(4)").text(mydata.author);
            $("#myprint tr:nth-child(7) td:nth-child(6)").text(mydata.reviewer);

            $("#myprint tr:nth-child(8) td:nth-child(2)").text(mydata.print);
            $("#myprint tr:nth-child(8) td:nth-child(4)").text(mydata.revision);
            $("#myprint tr:nth-child(8) td:nth-child(6)").text(mydata.copy);

            $("#myprint tr:nth-child(9) td:nth-child(2)").text(mydata.keyword);
            $("#myprint tr:nth-child(10) td:nth-child(2)").text(mydata.title);
            $("#myprint tr:nth-child(11) td:nth-child(1)").text(mydata.content);
            $("#myprint tr:nth-child(12) td:nth-child(2)").text(mydata.result);
        }
    }
    if(kind == "查看"){
        $("#select_model .btn-primary").css("display","none");
    }else if(kind == "编辑"){
        $("#select_model .btn-primary").css("display","inline-block");
    }
}

//打印
$(".btn-success").click(function () {
        jQuery('#myprint').print();
});
//  新建表单 表单提交
var x_flag = true;
$("#form_stuff .btn-primary").click(function () {
    if(x_flag){
        x_flag = false;
        var options  = {
            url:'/submitSendFile.do',
            type:'post',
            success:function(data)
            {
                console.log(data);
                if(data.result == "success"){
                    table_refresh();
                    acount();
                    $('#form_stuff').modal('hide');
                    alert("提交成功");
                    x_flag = true;
                }else {
                    alert("提交失败");
                    x_flag = true;
                }
            },
            error:function () {
                alert("系统错误");
            }
        };
        $("#fileForm").ajaxSubmit(options);
    }
});

//办公室提交
var b_flag = true;
$("#select_model .btn-primary").click(function () {
    if(b_flag){
        if(status == "办公室审核处理"){
            var lingdao = $("#lingdao").val();
            var banli = $("#banli").val();
            if(!lingdao || !banli){
                alert("请选择处理人");
                return;
            }
        }
        b_flag = false;
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
        var result = $("#select_model tr:nth-child(12) td:nth-child(2) textarea").val();
        var text = new Object();
        text.approver = lingdao;
        text.implementperson = banli;
        text.sendfileid = id;
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
        text.result = result;
        console.log(lingdao,banli,text);
        var mytext = JSON.stringify(text);
        $.ajax({
            url: "/updateSendFileInfoBySendFileId.do",
            type: "post",
            data: {text:mytext},
            dataType: "json",
            success: function (data) {
                if(data.result == "success"){
                    table_refresh();
                    acount();
                    $("#select_model").modal("hide");
                    alert("提交成功");
                    b_flag = true;
                }else {
                    alert("提交失败");
                    b_flag = true;
                }
            }
        })
    }

})

