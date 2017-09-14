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

//表单放弃
$(".btn-danger").click(function () {
        wipeData()
});

//表单关闭 x
$("#close_stuff").click(function(){
    wipeData()
});

// 表单保存
$(".btn-success").click(function () {
    var id=$("#oId").text();
    alert(id);
    var created_at=$("#created_at").text();
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
  /*  var arrAttachment=[];
    for(var i=0;i<attachment.length;i++){
        arrAttachment.push(attachment.eq(i).text());
    }*/
    var datas= {
        "id":id,
        "created_at":created_at,
        "dept":dept,
        "author":author,
        "reviewer":reviewer,
        "print":print,
        "revision":revision,
        "copy":copy,
        "keyword":keyword,
        "title":title,
        "content":content
    };

    if(dept == ""){
        alert("拟稿单位不能为空")
    }else if(author == ""){
        alert("拟稿不能为空")
    }else if(title == ""){
        alert("标题不能为空")
    }else if(content == ""){
        alert("内容不能为空")
    }else{
        /*$.ajax({
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



            }
        });*/
        $("#fileForm").ajaxSubmit({
            /*url: '/file/multipleUpload.do',*/
            url: '/saveFormData.do',
            type: "post",
            enctype: 'multipart/form-data',
            dataType:'json',
            success: function (data) {
                wipeData();
            },
            error: function (data) {
            }
        });
    }
   /* $("#fileForm").submit();*/

});

// 表单提交
$(".btn-primary").click(function () {
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
                console.log(dept);
                //location.reload();
                alert("ok");
                wipeData();
            }
        });
       /* $("#fileForm").submit();*/
    }
});

// #NewTable_Stuff 表格操作，查看按钮
function detail(that) {
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
        success: function (data) {
            console.log(data);
            $(that).parents("tr").remove();
              alert("删除成功")
        }
    })
}










