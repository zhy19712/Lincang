/**
 * Created by zhangchuan on 2017/10/23.
 */
$(function () {
    var url = window.location.search;
    url = decodeURIComponent(url);
    var reg = /[?&][^?&]+=[^?&]+/g;
    var arr = url.match(reg);
    var kind = arr[0].substring(1).split("=")[1];
    var id = arr[1].substring(1).split("=")[1];
    kind = decodeURI(decodeURI(kind));
    var id1 = decodeURI(decodeURI(id));
    $("#fid span").text(id1);
    var id2 = encodeURI(encodeURI(id1));
    console.log(kind,id1);
    $.ajax({
        url: "/dataGeting.do",
        type: "post",
        data: {fid:id1},
        dataType: "json",
        success: function (data) {
            console.log(data);
            //银行账户信息
             $("#bank-user").val(data.bank.account_name);
             $("#bank-number").val(data.bank.account_number);
             $("#bank-name").val(data.bank.account_number);
             //人员信息
             $.each(data.people,function (i,n) {
                 if(n.master == "户主"){
                     $("#reservoir").val(n.reservoir);
                     $("#householder").val(n.relation);
                     $("#number").val(n.phone);
                     $("#place").val(n.location);
                     if(n.prop == 1){
                         $("#yes").attr("checked",true);
                     }else {
                         $("#no").attr("checked",false);
                     }
                     var arr = n.poor_reason.split(",");
                     $.each(arr,function (i,n) {
                         if(n == "交通落后"){
                             $("#reason li:first-child input").attr("checked",true);
                         }else if(n == "缺技术、资金、土地、水"){
                             $("#reason li:nth-child(2) input").attr("checked",true);
                         }else if(n == "因学、残、灾"){
                             $("#reason li:nth-child(3) input").attr("checked",true);
                         }
                     });
                     $("#respondent").val(n.interviewee);
                     $("#inquirer").val(n.interviewer);
                     $("#time").val(n.created_at);
                 }
                if(i == 0){
                     $("#home_people1 td:first-child input").val(n.name);
                     $("#home_people1 td:nth-child(2) input").val(n.pid);
                     $("#home_people1 td:nth-child(3) input").val(n.gender);
                     $("#home_people1 td:nth-child(4) input").val(n.race);
                     $("#home_people1 td:nth-child(5) input").val(n.relation);
                     $("#home_people1 td:nth-child(6) input").val(n.education);
                     $("#home_people1 td:nth-child(7) input").val(n.profession);
                }else if(i == 1){
                    $("#home_people2 td:first-child input").val(n.name);
                    $("#home_people2 td:nth-child(2) input").val(n.pid);
                    $("#home_people2 td:nth-child(3) input").val(n.gender);
                    $("#home_people2 td:nth-child(4) input").val(n.race);
                    $("#home_people2 td:nth-child(5) input").val(n.relation);
                    $("#home_people2 td:nth-child(6) input").val(n.education);
                    $("#home_people2 td:nth-child(7) input").val(n.profession);
                }else if(i == 2){
                    $("#home_people3 td:first-child input").val(n.name);
                    $("#home_people3 td:nth-child(2) input").val(n.pid);
                    $("#home_people3 td:nth-child(3) input").val(n.gender);
                    $("#home_people3 td:nth-child(4) input").val(n.race);
                    $("#home_people3 td:nth-child(5) input").val(n.relation);
                    $("#home_people3 td:nth-child(6) input").val(n.education);
                    $("#home_people3 td:nth-child(7) input").val(n.profession);
                }else if(i == 3){
                    $("#home_people4 td:first-child input").val(n.name);
                    $("#home_people4 td:nth-child(2) input").val(n.pid);
                    $("#home_people4 td:nth-child(3) input").val(n.gender);
                    $("#home_people4 td:nth-child(4) input").val(n.race);
                    $("#home_people4 td:nth-child(5) input").val(n.relation);
                    $("#home_people4 td:nth-child(6) input").val(n.education);
                    $("#home_people4 td:nth-child(7) input").val(n.profession);
                }
             });

             //所在地
            $("#city td:nth-child(1) input").val(data.move.from_city);
            $("#city td:nth-child(2) input").val(data.move.from_disirict);
            $("#city td:nth-child(3) input").val(data.move.from_town);
            $("#city td:nth-child(4) input").val(data.move.from_village);
            $("#city td:nth-child(5) input").val(data.move.from_group);
            $("#city td:nth-child(6) input").val(data.move.from_remake);

            //迁入地
            $("#city1 td:nth-child(1) input").val(data.move.from_city);
            $("#city1 td:nth-child(2) input").val(data.move.from_disirict);
            $("#city1 td:nth-child(3) input").val(data.move.from_town);
            $("#city1 td:nth-child(4) input").val(data.move.from_village);
            $("#city1 td:nth-child(5) input").val(data.move.from_group);
            $("#city1 td:nth-child(6) input").val(data.move.from_remake);

            //迁出地
            $("#city2 td:nth-child(1) input").val(data.move.to_city);
            $("#city2 td:nth-child(2) input").val(data.move.to_disirict);
            $("#city2 td:nth-child(3) input").val(data.move.to_town);
            $("#city2 td:nth-child(4) input").val(data.move.to_village);
            $("#city2 td:nth-child(5) input").val(data.move.to_group);
            $("#city2 td:nth-child(6) input").val(data.move.to_remake);

            //房子信息
            $("#main td:nth-child(2) input").val(data.house.main_size);
            $("#main td:nth-child(3) input").val(data.house.main_structure1);
            $("#main td:nth-child(4) input").val(data.house.main_structure2);
            $("#main td:nth-child(5) input").val(data.house.main_structure3);
            $("#main td:nth-child(6) input").val(data.house.main_structure4);
            $("#main td:nth-child(7) input").val(data.house.main_structure5);
            $("#main td:nth-child(8) input").val(data.house.main_remark);

            $("#sub td:nth-child(2) input").val(data.house.sub_size);
            $("#sub td:nth-child(3) input").val(data.house.sub_structure1);
            $("#sub td:nth-child(4) input").val(data.house.sub_structure2);
            $("#sub td:nth-child(5) input").val(data.house.sub_structure3);
            $("#sub td:nth-child(6) input").val(data.house.sub_structure4);
            $("#sub td:nth-child(7) input").val(data.house.sub_structure5);
            $("#sub td:nth-child(8) input").val(data.house.sub_remark);


        }
    })
})
