$(function(){

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


        $.ajax({
            type: "post",
            url: "update.do",
            data: "draftUnit=" + draftUnit + "draft=" + draft + "draftSectionOffice=" + draftSectionOffice + "Printing=" + Printing+
            "proofread=" + proofread+"Printing=" + Printing+"Copies=" + Copies+"Attachment=" + Attachment+
            "descriptor=" + descriptor+"title=" + title+"Content=" + Content,
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












});