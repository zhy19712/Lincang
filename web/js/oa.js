



var fileIndex = 1;
function add_click_file(index){
    $("#add_file_"+fileIndex).click();
}

function add(index) {
    /*因为浏览器的设置问题直接用.val()方法取值的时候会取到C:\fakepath\。。所以在这里进行了剪切。*/
    var len = $("#add_file_" + (fileIndex) + "").val().split("\\").length;
    alert($("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1]);
    var num = $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1];
    $("#filesUpload").append('<span  id="add_file_span_' + (fileIndex) + '">' + $("#add_file_" + (fileIndex) + "").val().split("\\")[len - 1] + '</span>');
    $("#filesUpload").append('<a   id="add_file_a_' + (fileIndex) + '" href="javascript:del_file(' + fileIndex+ ')">删除</a>');
    $("#filesUpload").append('<input style="display:none;" id="add_file_' + (fileIndex + 1) + '" type="file" name = "files" onChange="add(' + (fileIndex + 1) + ')"/>');
    ++fileIndex;
}


function del_file(number) {
    var o=document.getElementById("filesUpload");//获取父节点
    var int=document.getElementById("add_file_" + number+"");//获取需要删除的子节点
    var a=document.getElementById("add_file_a_" + number+"");//获取需要删除的子节点
    var span=document.getElementById("add_file_span_" + number+"");//获取需要删除的子节点
    o.removeChild(int)//从父节点o上面移除子节点a
    o.removeChild(a)
    o.removeChild(span)
}







    //表单放弃
    $(".btn-danger").click(function () {

         $("#input1").val(" ");
         $("#input2").val(" ");
         $("#input3").val(" ");
         $("#input4").val(" ");
         $("#input5").val(" ");
         $("#input6").val(" ");
         $("#input7").val(" ");
         $("#input8").val(" ");
         $("#input9").val(" ");
         $("#input10").val(" ");
    });




    // 表单保存
    $(".btn-success").click(function () {
        var sent_at=  $("#input1").val();
        var author=  $("#input2").val();
        var dept=  $("#input3").val();
        var print=  $("#input4").val();
        var revision=  $("#input5").val();
        var copy=  $("#input6").val();
        var keywords=  $("#input8").val();
        var title=  $("#input9").val();
        var content=  $("#input10").val();
        var attachment= $("#filesUpload > span");
        var arrAttachment=[];
        for(var i=0;i<attachment.length;i++){
            arrAttachment.push(attachment.eq(i).text());
        }
        var datas= {
            "sent_at":sent_at,
            "author":author,
            "dept":dept,
            "print":print,
            "revision":revision,
            "copy":copy,
            "arrAttachment":arrAttachment,
            "keywords":keywords,
            "title":title,
            "content":content
        };
        console.log([datas])
        $.ajax({
            type: 'post',
            url: '/saveFormData.do',
            data: datas,
            dataType: 'json',
            async:false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {
                //location.reload();
            }
        });
        /*$("#Commit").submit();*/
    });




    // 表单提交
    $(".btn-primary").click(function () {
        var sent_at=  $("#input1").val();
        var author=  $("#input2").val();
        var dept=  $("#input3").val();
        var print=  $("#input4").val();
        var revision=  $("#input5").val();
        var copy=  $("#input6").val();
        var keywords=  $("#input8").val();
        var title=  $("#input9").val();
        var content=  $("#input10").val();
        var attachment= $("#filesUpload > span");
        var arrAttachment=[];
        for(var i=0;i<attachment.length;i++){
            arrAttachment.push(attachment.eq(i).text());
        }
        var datas= {
            "sent_at":sent_at,
            "author":author,
            "dept":dept,
            "print":print,
            "revision":revision,
            "copy":copy,
            "arrAttachment":arrAttachment,
            "keywords":keywords,
            "title":title,
            "content":content
        };
        console.log([datas])
        $.ajax({
            type: 'post',
            url: '/submitFormData.do',
            data: datas,
            dataType: 'json',
            async:false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {
                //location.reload();
            }
        });
        /*$("#Commit").submit();*/
    });












