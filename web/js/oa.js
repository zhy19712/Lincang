



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

        $("#Commit").click();


        var draftUnit=  $("#input1").val();
        var draft=  $("#input2").val();
        var draftSectionOffice=  $("#input3").val();
        var Printing=  $("#input4").val();
        var proofread=  $("#input5").val();
        var Copies=  $("#input6").val();
        var descriptor=  $("#input8").val();
        var title=  $("#input9").val();
        var Content=  $("#input10").val();


        var Attachment= $("#filesUpload > span");
        var arrAttachment=[];

        for(var i=0;i<Attachment.length;i++){
            arrAttachment.push(Attachment.eq(i).text());
        }

        var datas= {
            "draftUnit":draftUnit,
            "draft":draft,
            "draftSectionOffice":draftSectionOffice,
            "Printing":Printing,
            "proofread":proofread,
            "Copies":Copies,
            "descriptor":descriptor,
            "title":title,
            "Content":Content,
            "arrAttachment":arrAttachment
        };

        console.log([datas])







        $.ajax({
            type: "post",
            url: "update.do",
            data:[datas],
            // "draftUnit=" + draftUnit + "draft=" + draft + "draftSectionOffice=" + draftSectionOffice + "Printing=" + Printing+
            // "proofread=" + proofread+"Printing=" + Printing+"Copies=" + Copies+"Attachment=" + Attachment+
            // "descriptor=" + descriptor+"title=" + title+"Content=" + Content,
            dataType: 'json',
            async:false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {
                //location.reload();
            }
        });
    });




    // 表单提交
    $(".btn-primary").click(function () {

        var draftUnit=  $("#input1").val();
        var draft=  $("#input2").val();
        var draftSectionOffice=  $("#input3").val();
        var Printing=  $("#input4").val();
        var proofread=  $("#input5").val();
        var Copies=  $("#input6").val();
        var Attachment=  $("#input7").val();
        var descriptor=  $("#input8").val();
        var title=  $("#input9").val();
        var Content=  $("#input10").val();

        console.log(draftUnit)


        // $.ajax({
        //     type: "post",
        //     url: "update.do",
        //     data: "draftUnit=" + draftUnit + "draft=" + draft + "draftSectionOffice=" + draftSectionOffice + "Printing=" + Printing+
        //     "proofread=" + proofread+"Printing=" + Printing+"Copies=" + Copies+"Attachment=" + Attachment+
        //     "descriptor=" + descriptor+"title=" + title+"Content=" + Content,
        //     dataType: 'json',
        //     async:false,
        //     contentType: "application/x-www-form-urlencoded; charset=utf-8",
        //     success: function (result) {
        //         //location.reload();
        //     }
        // });
    });












