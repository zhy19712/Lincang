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
    if(kind == "查看"){
        $("#btn-container .hvr-rectangle-in").css("display","none");
    }else if(kind == "编辑"){
        $("#btn-container .hvr-rectangle-in").css("display","inline-block");
    }
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
                 if(n.master == 1){
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
            $("#city td:nth-child(2) input").val(data.move.from_district);
            $("#city td:nth-child(3) input").val(data.move.from_town);
            $("#city td:nth-child(4) input").val(data.move.from_village);
            $("#city td:nth-child(5) input").val(data.move.from_group);
            $("#city td:nth-child(6) input").val(data.move.from_remake);

            //迁入地
            $("#city1 td:nth-child(2) input").val(data.move.from_city);
            $("#city1 td:nth-child(3) input").val(data.move.from_district);
            $("#city1 td:nth-child(4) input").val(data.move.from_town);
            $("#city1 td:nth-child(5) input").val(data.move.from_village);
            $("#city1 td:nth-child(6) input").val(data.move.from_group);
            $("#city1 td:nth-child(7) input").val(data.move.from_remake);

            //迁出地
            $("#city2 td:nth-child(2) input").val(data.move.to_city);
            $("#city2 td:nth-child(3) input").val(data.move.to_district);
            $("#city2 td:nth-child(4) input").val(data.move.to_town);
            $("#city2 td:nth-child(5) input").val(data.move.to_village);
            $("#city2 td:nth-child(6) input").val(data.move.to_group);
            $("#city2 td:nth-child(7) input").val(data.move.to_remake);

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

            //收入信息
            function money_in(kind,data) {
                $("#"+ kind +" td:nth-child(2) input").val(data.income_quantity);
                $("#"+ kind +" td:nth-child(3) input").val(data.income_unit);
                $("#"+ kind +" td:nth-child(4) input").val(data.income_sum);
                $("#"+ kind +" td:nth-child(5) input").val(data.remake);
            }
            $.each(data.income,function (i,n) {
                if(n.income_source == "养殖业收入"){
                    if(n.income_cate == "猪"){
                        $("#in_animal1 td:nth-child(3) input").val(n.income_quantity);
                        $("#in_animal1 td:nth-child(4) input").val(n.income_unit);
                        $("#in_animal1 td:nth-child(5) input").val(n.income_sum);
                        $("#in_animal1 td:nth-child(6) input").val(n.remake);
                    }else if(n.income_cate == "牛"){
                        money_in("in_animal2",n);
                    }else if(n.income_cate == "羊"){
                        money_in("in_animal3",n);
                    }else if(n.income_cate == "鸡"){
                        money_in("in_animal4",n);
                    }else if(n.income_cate == "鸭"){
                        money_in("in_animal5",n);
                    }else if(n.income_cate == "渔业"){
                        money_in("in_animal6",n);
                    }else if(n.income_cate == "乳业"){
                        money_in("in_animal7",n);
                    }else if(n.income_cate == "其他"){
                        money_in("in_animal8",n);
                    }
                }else if(n.income_source == "种植业收入"){
                    if(n.income_cate == "粮食"){
                        $("#in_botany1 td:nth-child(3) input").val(n.income_quantity);
                        $("#in_botany1 td:nth-child(4) input").val(n.income_unit);
                        $("#in_botany1 td:nth-child(5) input").val(n.income_sum);
                        $("#in_botany1 td:nth-child(6) input").val(n.remake);
                    }else if(n.income_cate == "蔬菜"){
                        money_in("in_botany2",n);
                    }else if(n.income_cate == "水果"){
                        money_in("in_botany3",n);
                    }else if(n.income_cate == "其他"){
                        money_in("in_botany4",n);
                    }
                }else if(n.income_source == "其他收入"){
                    if(n.income_cate == "劳务酬劳"){
                        $("#in_other1 td:nth-child(3) input").val(n.income_quantity);
                        $("#in_other1 td:nth-child(4) input").val(n.income_unit);
                        $("#in_other1 td:nth-child(5) input").val(n.income_sum);
                        $("#in_other1 td:nth-child(6) input").val(n.remake);
                    }else if(n.income_cate == "房、耕地租赁"){
                        money_in("in_other2",n);
                    }
                }
            })

            //支出信息
            function money_out(kind,data) {
                $("#"+ kind +" td:nth-child(2) input").val(data.outcome_quantity);
                $("#"+ kind +" td:nth-child(3) input").val(data.outcome_unit);
                $("#"+ kind +" td:nth-child(4) input").val(data.outcome_sum);
                $("#"+ kind +" td:nth-child(5) input").val(data.remake);
            }
            $.each(data.outcome,function (i,n) {
                if(n.outcome_source == "种植业支出"){
                    if(n.outcome_cate == "籽种"){
                        $("#out_botany1 td:nth-child(3) input").val(n.outcome_quantity);
                        $("#out_botany1 td:nth-child(4) input").val(n.outcome_unit);
                        $("#out_botany1 td:nth-child(5) input").val(n.outcome_sum);
                        $("#out_botany1 td:nth-child(6) input").val(n.remake);
                    }else if(n.outcome_cate == "化肥、农药"){
                        money_out("out_botany2",n);
                    }else if(n.outcome_cate == "雇工"){
                        money_out("out_botany3",n);
                    }else if(n.outcome_cate == "机耕支出"){
                        money_out("out_botany4",n);
                    }else if(n.outcome_cate == "灌溉水电费"){
                        money_out("out_botany5",n);
                    }else if(n.outcome_cate == "承租耕地"){
                        money_out("out_botany6",n);
                    }
                }else if(n.outcome_source == "养殖业支出"){
                    if(n.outcome_cate == "幼种"){
                        $("#out_animal1 td:nth-child(3) input").val(n.outcome_quantity);
                        $("#out_animal1 td:nth-child(4) input").val(n.outcome_unit);
                        $("#out_animal1 td:nth-child(5) input").val(n.outcome_sum);
                        $("#out_animal1 td:nth-child(6) input").val(n.remake);
                    }else if(n.outcome_cate == "饲料"){
                        money_out("out_animal2",n);
                    }else if(n.outcome_cate == "疫病防治"){
                        money_out("out_animal3",n);
                    }else if(n.outcome_cate == "其他"){
                        money_out("out_animal4",n);
                    }
                }else if(n.outcome_source == "生活支出"){
                    if(n.outcome_cate == "主食"){
                        $("#out_life1 td:nth-child(3) input").val(n.outcome_quantity);
                        $("#out_life1 td:nth-child(4) input").val(n.outcome_unit);
                        $("#out_life1 td:nth-child(5) input").val(n.outcome_sum);
                        $("#out_life1 td:nth-child(6) input").val(n.remake);
                    }else if(n.outcome_cate == "衣物"){
                        money_out("out_life2",n);
                    }else if(n.outcome_cate == "水、电费"){
                        money_out("out_life3",n);
                    }else if(n.outcome_cate == "通讯费"){
                        money_out("out_life4",n);
                    }else if(n.outcome_cate == "交通费"){
                        money_out("out_life5",n);
                    }else if(n.outcome_cate == "教育"){
                        money_out("out_life6",n);
                    }else if(n.outcome_cate == "医疗"){
                        money_out("out_life7",n);
                    }else if(n.outcome_cate == "其他"){
                        money_out("out_life8",n);
                    }
                }
            })
        }
    })
})
