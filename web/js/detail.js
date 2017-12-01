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
    var table_name = arr[2].substring(1).split("=")[1];
    kind = decodeURI(decodeURI(kind));
    var id1 = decodeURI(decodeURI(id));
    $("#fid span").text(id1);
    var id2 = encodeURI(encodeURI(id1));
    console.log(kind,id1);
    console.log(table_name);


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
             $("#bank-name").val(data.bank.bank_name);
             //人员信息
            var mypeople = [];
            for(var i = (data.people.length-1);i >= 0;i--){
                mypeople.push(data.people[i])
            }
            console.log(mypeople);
             $.each(mypeople,function (i,n) {
                 if(n.master == 1){
                     $("#reservoir").val(n.reservoir);
                     $("#householder").val(n.name);
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
            $("#city td:nth-child(1) input").val(data.move.to_city);
            $("#city td:nth-child(2) input").val(data.move.to_district);
            $("#city td:nth-child(3) input").val(data.move.to_town);
            $("#city td:nth-child(4) input").val(data.move.to_village);
            $("#city td:nth-child(5) input").val(data.move.to_group);
            $("#city td:nth-child(6) input").val(data.move.to_remake);

            //迁入地
            $("#city1 td:nth-child(2) input").val(data.move.to_city);
            $("#city1 td:nth-child(3) input").val(data.move.to_district);
            $("#city1 td:nth-child(4) input").val(data.move.to_town);
            $("#city1 td:nth-child(5) input").val(data.move.to_village);
            $("#city1 td:nth-child(6) input").val(data.move.to_group);
            $("#city1 td:nth-child(7) input").val(data.move.to_remake);

            //迁出地
            $("#city2 td:nth-child(2) input").val(data.move.from_city);
            $("#city2 td:nth-child(3) input").val(data.move.from_district);
            $("#city2 td:nth-child(4) input").val(data.move.from_town);
            $("#city2 td:nth-child(5) input").val(data.move.from_village);
            $("#city2 td:nth-child(6) input").val(data.move.from_group);
            $("#city2 td:nth-child(7) input").val(data.move.from_remake);

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
    });

    //数据录入表格单选
    $("#yes").click(function () {
        $("#no").prop("checked",false);
    })
    $("#no").click(function () {
        $("#yes").prop("checked",false);
    });
    //日期插件
    $("#time").jeDate({
        format: "YYYY-MM-DD"
    });

    //表单提交
    var flag = true;
    $("#btn-container .btn-primary").click(function () {
        if(flag){
            var kind = $("#kind").text();
            var username = $("#username").text();
            var data = new Object();
            var reservoir = $("#reservoir").val();
            var place = $("#place").val();
            var householder = $("#householder").val();
            var bank_user = $("#bank-user").val();
            var bank_number = $("#bank-number").val();
            var bank_name = $("#bank-name").val();
            var number = $("#number").val();
            if(place){
                data.place = place;
            }
            data.kind = kind;
            data.reservoir = reservoir;
            data.householder = householder;
            data.bank_user = bank_user;
            data.bank_number = bank_number;
            data.bank_name = bank_name;
            data.tel_number = number;
            data.fid = id1;

            //家庭成员信息
            var home_infos = [];
            function home_info(name,id,sex,nation,relation,cultural,profession) {
                this.name = name;
                this.id = id;
                this.sex = sex;
                this.nation = nation;
                this.relation = relation;
                this.cultural = cultural;
                this.profession = profession;
            }
            function val(index1,index2) {
                var value = $("#home_people" + index1 + " td:nth-child(" + index2 + ") input").val()
                return value;
            }
            if(val(1,1) || val(1,2) || val(1,3) || val(1,4) ||val(1,5) || val(1,6) || val(1,7)){
                if(!val(1,1)){
                    alert("姓名不能为空");
                    return;
                }else {
                    var h1 = new home_info(val(1,1),val(1,2),val(1,3),val(1,4),val(1,5),val(1,6),val(1,7));
                    home_infos.push(h1);
                }
            }

            if(val(2,1) || val(2,2) || val(2,3) || val(2,4) ||val(2,5) || val(2,6) || val(2,7)){
                if(!val(2,1)){
                    alert("姓名不能为空");
                    return;
                }else {
                    var h2 = new home_info(val(2,1),val(2,2),val(2,3),val(2,4),val(2,5),val(2,6),val(2,7));
                    home_infos.push(h2);
                }
            }

            if(val(3,1) || val(3,2) || val(3,3) || val(3,4) ||val(3,5) || val(3,6) || val(3,7)){
                if(!val(3,1)){
                    alert("姓名不能为空");
                    return;
                }else {
                    var h3 = new home_info(val(3,1),val(3,2),val(3,3),val(3,4),val(3,5),val(3,6),val(3,7));
                    home_infos.push(h3);
                }
            }

            if(val(4,1) || val(4,2) || val(4,3) || val(4,4) ||val(4,5) || val(4,6) || val(4,7)){
                if(!val(4,1)){
                    alert("姓名不能为空");
                    return;
                }else {
                    var h4 = new home_info(val(4,1),val(4,2),val(4,3),val(4,4),val(4,5),val(4,6),val(4,7));
                    home_infos.push(h4);
                }
            }
            if(h1 || h2 || h3 || h4){
                data.home_infos = home_infos;
            }

            //城市信息
            var location = new Object();
            if($("#city td:nth-child(1) input").val()){
                location.city = $("#city td:nth-child(1) input").val();
            }
            if($("#city td:nth-child(2) input").val()){
                location.county = $("#city td:nth-child(2) input").val();
            }
            if($("#city td:nth-child(3) input").val()){
                location.town = $("#city td:nth-child(3) input").val();
            }
            if($("#city td:nth-child(4) input").val()){
                location.village = $("#city td:nth-child(4) input").val();
            }
            if($("#city td:nth-child(5) input").val()){
                location.group = $("#city td:nth-child(5) input").val();
            }
            if($("#city td:nth-child(6) input").val()){
                location.remark = $("#city td:nth-child(6) input").val();
            }
            if(location.city || location.county || location.town || location.village || location.group || location.remark){
                data.location = location;
            }

            //迁出地，迁入地
            var move = new Object();
            if($("#city1 td:nth-child(2) input").val()){
                move.to_city = $("#city1 td:nth-child(2) input").val();
            }
            if($("#city1 td:nth-child(3) input").val()){
                move.to_disirict = $("#city1 td:nth-child(3) input").val();
            }
            if($("#city1 td:nth-child(4) input").val()){
                move.to_town = $("#city1 td:nth-child(4) input").val();
            }
            if($("#city1 td:nth-child(5) input").val()){
                move.to_village = $("#city1 td:nth-child(5) input").val();
            }
            if($("#city1 td:nth-child(6) input").val()){
                move.to_group = $("#city1 td:nth-child(6) input").val();
            }
            if($("#city1 td:nth-child(7) input").val()){
                move.to_remake = $("#city1 td:nth-child(7) input").val();
            }
            if($("#city2 td:nth-child(2) input").val()){
                move.from_city = $("#city2 td:nth-child(2) input").val();
            }
            if($("#city2 td:nth-child(3) input").val()){
                move.from_disirict = $("#city2 td:nth-child(3) input").val();
            }
            if($("#city2 td:nth-child(4) input").val()){
                move.from_town = $("#city2 td:nth-child(4) input").val();
            }
            if($("#city2 td:nth-child(5) input").val()){
                move.from_village = $("#city2 td:nth-child(5) input").val();
            }
            if($("#city2 td:nth-child(6) input").val()){
                move.from_group = $("#city2 td:nth-child(6) input").val();
            }
            if($("#city2 td:nth-child(7) input").val()){
                move.from_remake = $("#city2 td:nth-child(7) input").val();
            }
            if(move.from_city || move.from_disirict || move.from_town || move.from_village || move.from_group || move.from_remake || move.to_city || move.to_disirict || move.to_town || move.to_village || move.to_group || move.to_remake){
                data.move = move;
            }
            //房子信息
            var house = new Object();
            if($("#main td:nth-child(2) input").val()){
                house.main_arear = $("#main td:nth-child(2) input").val();
            }
            if($("#main td:nth-child(3) input").val()){
                house.main_structure1 = $("#main td:nth-child(3) input").val();
            }
            if($("#main td:nth-child(4) input").val()){
                house.main_structure2 = $("#main td:nth-child(4) input").val();
            }
            if($("#main td:nth-child(5) input").val()){
                house.main_structure3 = $("#main td:nth-child(5) input").val();
            }
            if($("#main td:nth-child(6) input").val()){
                house.main_structure4 = $("#main td:nth-child(6) input").val();
            }
            if($("#main td:nth-child(7) input").val()){
                house.main_easy = $("#main td:nth-child(7) input").val();
            }
            if($("#main td:nth-child(8) input").val()){
                house.main_remark = $("#main td:nth-child(8) input").val();
            }
            if($("#sub td:nth-child(2) input").val()){
                house.sub_arear = $("#sub td:nth-child(2) input").val();
            }
            if($("#sub td:nth-child(3) input").val()){
                house.sub_structure1 = $("#sub td:nth-child(3) input").val();
            }
            if($("#sub td:nth-child(4) input").val()){
                house.sub_structure2 = $("#sub td:nth-child(4) input").val();
            }
            if($("#sub td:nth-child(5) input").val()){
                house.sub_structure3 = $("#sub td:nth-child(5) input").val();
            }
            if($("#sub td:nth-child(6) input").val()){
                house.sub_structure4 = $("#sub td:nth-child(6) input").val();
            }
            if($("#sub td:nth-child(7) input").val()){
                house.sub_easy = $("#sub td:nth-child(7) input").val();
            }
            if($("#sub td:nth-child(8) input").val()){
                house.sub_remark = $("#sub td:nth-child(8) input").val();
            }
            if(house.main_arear || house.main_structure1 || house.main_structure2 || house.main_structure3 || house.main_structure4 || house.main_easy || house.main_remark || house.sub_arear || house.sub_arear || house.sub_structure1 || house.sub_structure2 || house.sub_structure3 || house.sub_structure4 || house.sub_easy || house.sub_remark){

                data.house = house;
            }

            //收入与支出信息
            var money_info = [];
            function money(kind,content,count,price,total,remark) {
                this.kind = kind;
                this.content = content;
                this.count = count;
                this.price = price;
                this.total = total;
                this.remark = remark;
            }
            function money_val(kind,index1,index2) {
                var val = $("#" + kind + index1 + " td:nth-child(" + index2 + ") input").val();
                return val;
            }
            function money_content(kind,index1,index2) {
                var content = $("#" + kind + index1 + " td:nth-child(" + index2 + ")").text();
                return content;
            }

            if(money_val("in_animal",1,3) || money_val("in_animal",1,4) || money_val("in_animal",1,5) || money_val("in_animal",1,6)){
                console.log(money_content("in_animal",1,2),money_val("in_animal",1,3),money_val("in_animal",1,4),money_val("in_animal",1,5),money_val("in_animal",1,6));
                var money1 = new money("养殖业收入",money_content("in_animal",1,2),money_val("in_animal",1,3),money_val("in_animal",1,4),money_val("in_animal",1,5),money_val("in_animal",1,6));
                money_info.push(money1);
            }
            if(money_val("in_animal",2,2) || money_val("in_animal",2,3) || money_val("in_animal",2,4) || money_val("in_animal",2,5)){
                var money2 = new money("养殖业收入",money_content("in_animal",2,1),money_val("in_animal",2,2),money_val("in_animal",2,3),money_val("in_animal",2,4),money_val("in_animal",2,5));
                money_info.push(money2);
            }
            if(money_val("in_animal",3,2) || money_val("in_animal",3,3) || money_val("in_animal",3,4) || money_val("in_animal",3,5)){
                var money3 = new money("养殖业收入",money_content("in_animal",3,1),money_val("in_animal",3,2),money_val("in_animal",3,3),money_val("in_animal",3,4),money_val("in_animal",3,5));
                money_info.push(money3);
            }
            if(money_val("in_animal",4,2) || money_val("in_animal",4,3) || money_val("in_animal",4,4) || money_val("in_animal",4,5)){
                var money4 = new money("养殖业收入",money_content("in_animal",4,1),money_val("in_animal",4,2),money_val("in_animal",4,3),money_val("in_animal",4,4),money_val("in_animal",4,5));
                money_info.push(money4);
            }
            if(money_val("in_animal",5,2) || money_val("in_animal",5,3) || money_val("in_animal",5,4) || money_val("in_animal",5,5)){
                var money5 = new money("养殖业收入",money_content("in_animal",5,1),money_val("in_animal",5,2),money_val("in_animal",5,3),money_val("in_animal",5,4),money_val("in_animal",5,5));
                money_info.push(money5);
            }
            if(money_val("in_animal",6,2) || money_val("in_animal",6,3) || money_val("in_animal",6,4) || money_val("in_animal",6,5)){
                var money6 = new money("养殖业收入",money_content("in_animal",6,1),money_val("in_animal",6,2),money_val("in_animal",6,3),money_val("in_animal",6,4),money_val("in_animal",6,5));
                money_info.push(money6);
            }
            if(money_val("in_animal",7,2) || money_val("in_animal",7,3) || money_val("in_animal",7,4) || money_val("in_animal",7,5)){
                var money7 = new money("养殖业收入",money_content("in_animal",7,1),money_val("in_animal",7,2),money_val("in_animal",7,3),money_val("in_animal",7,4),money_val("in_animal",7,5));
                money_info.push(money7);
            }
            if(money_val("in_animal",8,2) || money_val("in_animal",8,3) || money_val("in_animal",8,4) || money_val("in_animal",8,5)){
                var money8 = new money("养殖业收入",money_content("in_animal",8,1),money_val("in_animal",8,2),money_val("in_animal",8,3),money_val("in_animal",8,4),money_val("in_animal",8,5));
                money_info.push(money8);
            }

            if(money_val("in_botany",1,3) || money_val("in_botany",1,4) || money_val("in_botany",1,5) || money_val("in_botany",1,6)){
                var money9 = new money("种植业收入",money_content("in_botany",1,2),money_val("in_botany",1,3),money_val("in_botany",1,4),money_val("in_botany",1,5),money_val("in_botany",1,6));
                money_info.push(money9);
            }
            if(money_val("in_botany",2,2) || money_val("in_botany",2,3) || money_val("in_botany",2,4) || money_val("in_botany",2,5)){
                var money10 = new money("种植业收入",money_content("in_botany",2,1),money_val("in_botany",2,2),money_val("in_botany",2,3),money_val("in_botany",2,4),money_val("in_botany",2,5));
                money_info.push(money10);
            }
            if(money_val("in_botany",3,2) || money_val("in_botany",3,3) || money_val("in_botany",3,4) || money_val("in_botany",3,5)){
                var money11 = new money("种植业收入",money_content("in_botany",3,1),money_val("in_botany",3,2),money_val("in_botany",3,3),money_val("in_botany",3,4),money_val("in_botany",3,5));
                money_info.push(money11);
            }
            if(money_val("in_botany",4,2) || money_val("in_botany",4,3) || money_val("in_botany",4,4) || money_val("in_botany",4,5)){
                var money12 = new money("种植业收入",money_content("in_botany",4,1),money_val("in_botany",4,2),money_val("in_botany",4,3),money_val("in_botany",4,4),money_val("in_botany",4,5));
                money_info.push(money12);
            }
            if(money_val("in_other",1,3) || money_val("in_other",1,4) || money_val("in_other",1,5) || money_val("in_other",1,6)){
                var money13 = new money("其他收入",money_content("in_other",1,2),money_val("in_other",1,3),money_val("in_other",1,4),money_val("in_other",1,5),money_val("in_other",1,6));
                money_info.push(money13);
            }
            if(money_val("in_other",2,2) || money_val("in_other",2,3) || money_val("in_other",2,4) || money_val("in_other",2,5)){
                var money14 = new money("其他收入",money_content("in_other",2,1),money_val("in_other",2,2),money_val("in_other",2,3),money_val("in_other",2,4),money_val("in_other",2,5));
                money_info.push(money14);
            }

            var money_outcome = [];
            if(money_val("out_botany",1,3) || money_val("out_botany",1,4) || money_val("out_botany",1,5) || money_val("out_botany",1,6)){
                var money15 = new money("种植业支出",money_content("out_botany",1,2),money_val("out_botany",1,3),money_val("out_botany",1,4),money_val("out_botany",1,5),money_val("out_botany",1,6));
                money_outcome.push(money15);
            }
            if(money_val("out_botany",2,2) || money_val("out_botany",2,3) || money_val("out_botany",2,4) || money_val("out_botany",2,5)){
                var money16 = new money("种植业支出",money_content("out_botany",2,1),money_val("out_botany",2,2),money_val("out_botany",2,3),money_val("out_botany",2,4),money_val("out_botany",2,5));
                money_outcome.push(money16);
            }
            if(money_val("out_botany",3,2) || money_val("out_botany",3,3) || money_val("out_botany",3,4) || money_val("out_botany",3,5)){
                var money17 = new money("种植业支出",money_content("out_botany",3,1),money_val("out_botany",3,2),money_val("out_botany",3,3),money_val("out_botany",3,4),money_val("out_botany",3,5));
                money_outcome.push(money17);
            }
            if(money_val("out_botany",4,2) || money_val("out_botany",4,3) || money_val("out_botany",4,4) || money_val("out_botany",4,5)){
                var money18 = new money("种植业支出",money_content("out_botany",4,1),money_val("out_botany",4,2),money_val("out_botany",4,3),money_val("out_botany",4,4),money_val("out_botany",4,5));
                money_outcome.push(money18);
            }
            if(money_val("out_botany",5,2) || money_val("out_botany",5,3) || money_val("out_botany",5,4) || money_val("out_botany",5,5)){
                var money19 = new money("种植业支出",money_content("out_botany",5,1),money_val("out_botany",5,2),money_val("out_botany",5,3),money_val("out_botany",5,4),money_val("out_botany",5,5));
                money_outcome.push(money19);
            }
            if(money_val("out_botany",6,2) || money_val("out_botany",6,3) || money_val("out_botany",6,4) || money_val("out_botany",6,5)){
                var money20 = new money("种植业支出",money_content("out_botany",6,1),money_val("out_botany",6,2),money_val("out_botany",6,3),money_val("out_botany",6,4),money_val("out_botany",6,5));
                money_outcome.push(money20);
            }


            if(money_val("out_animal",1,3) || money_val("out_animal",1,4) || money_val("out_animal",1,5) || money_val("out_animal",1,6)){
                var money21 = new money("养殖业支出",money_content("out_animal",1,2),money_val("out_animal",1,3),money_val("out_animal",1,4),money_val("out_animal",1,5),money_val("out_animal",1,6));
                money_outcome.push(money21);
            }
            if(money_val("out_animal",2,2) || money_val("out_animal",2,3) || money_val("out_animal",2,4) || money_val("out_animal",2,5)){
                var money22 = new money("养殖业支出",money_content("out_animal",2,1),money_val("out_animal",2,2),money_val("out_animal",2,3),money_val("out_animal",2,4),money_val("out_animal",2,5));
                money_outcome.push(money22);
            }
            if(money_val("out_animal",3,2) || money_val("out_animal",3,3) || money_val("out_animal",3,4) || money_val("out_animal",3,5)){
                var money23 = new money("养殖业支出",money_content("out_animal",3,1),money_val("out_animal",3,2),money_val("out_animal",3,3),money_val("out_animal",3,4),money_val("out_animal",3,5));
                money_outcome.push(money23);
            }
            if(money_val("out_animal",4,2) || money_val("out_animal",4,3) || money_val("out_animal",4,4) || money_val("out_animal",4,5)){
                var money24 = new money("养殖业支出",money_content("out_animal",4,1),money_val("out_animal",4,2),money_val("out_animal",4,3),money_val("out_animal",4,4),money_val("out_animal",4,5));
                money_outcome.push(money24);
            }


            if(money_val("out_life",1,3) || money_val("out_life",1,4) || money_val("out_life",1,5) || money_val("out_life",1,6)){
                var money25 = new money("生活支出",money_content("out_life",1,2),money_val("out_life",1,3),money_val("out_life",1,4),money_val("out_life",1,5),money_val("out_life",1,6));
                money_outcome.push(money25);
            }
            if(money_val("out_life",2,2) || money_val("out_life",2,3) || money_val("out_life",2,4) || money_val("out_life",2,5)){
                var money26 = new money("生活支出",money_content("out_life",2,1),money_val("out_life",2,2),money_val("out_life",2,3),money_val("out_life",2,4),money_val("out_life",2,5));
                money_outcome.push(money26);
            }
            if(money_val("out_life",3,2) || money_val("out_life",3,3) || money_val("out_life",3,4) || money_val("out_life",3,5)){
                var money27 = new money("生活支出",money_content("out_life",3,1),money_val("out_life",3,2),money_val("out_life",3,3),money_val("out_life",3,4),money_val("out_life",3,5));
                money_outcome.push(money27);
            }
            if(money_val("out_life",4,2) || money_val("out_life",4,3) || money_val("out_life",4,4) || money_val("out_life",4,5)){
                var money28 = new money("生活支出",money_content("out_life",4,1),money_val("out_life",4,2),money_val("out_life",4,3),money_val("out_life",4,4),money_val("out_life",4,5));
                money_outcome.push(money28);
            }
            if(money_val("out_life",5,2) || money_val("out_life",5,3) || money_val("out_life",5,4) || money_val("out_life",5,5)){
                var money29 = new money("生活支出",money_content("out_life",5,1),money_val("out_life",5,2),money_val("out_life",5,3),money_val("out_life",5,4),money_val("out_life",5,5));
                money_outcome.push(money29);
            }
            if(money_val("out_life",6,2) || money_val("out_life",6,3) || money_val("out_life",6,4) || money_val("out_life",6,5)){
                var money30 = new money("生活支出",money_content("out_life",6,1),money_val("out_life",6,2),money_val("out_life",6,3),money_val("out_life",6,4),money_val("out_life",6,5));
                money_outcome.push(money30);
            }
            if(money_val("out_life",7,2) || money_val("out_life",7,3) || money_val("out_life",7,4) || money_val("out_life",7,5)){
                var money31 = new money("生活支出",money_content("out_life",7,1),money_val("out_life",7,2),money_val("out_life",7,3),money_val("out_life",7,4),money_val("out_life",7,5));
                money_outcome.push(money31);
            }
            if(money_val("out_life",8,2) || money_val("out_life",8,3) || money_val("out_life",8,4) || money_val("out_life",8,5)){
                var money32 = new money("生活支出",money_content("out_life",8,1),money_val("out_life",8,2),money_val("out_life",8,3),money_val("out_life",8,4),money_val("out_life",8,5));
                money_outcome.push(money32);
            }



            if(money1 || money2 || money3 || money4 || money5 || money6 || money7 || money8 || money9 || money10 || money11 || money12 || money13 || money14){

                data.money_info = money_info;
            }
            if(money15 || money16 || money17 || money18 || money19 || money20 || money21 || money22 || money23 || money24 || money25 || money26 || money27 || money28 || money29 || money30 || money31 || money32 ){

                data.money_outcome = money_outcome;
            }

            var prop = "是";
            if(!$("#yes").prop("checked")){
                prop = "否";
            }
            data.prop = prop;

            var poor_reason = [];
            var x = 0;
            var reason1 = new Object();
            var reason2 = new Object();
            var reason3 = new Object();
            $.each($("#reason input[type='checkbox']:checked"),function () {
                x += 1;
                if(x == 1){
                    reason1.reason = $(this).val();
                    poor_reason.push(reason1);
                }else if(x == 2){
                    reason2.reason = $(this).val();
                    poor_reason.push(reason2);
                }else if(x == 3){
                    reason3.reason = $(this).val();
                    poor_reason.push(reason3);
                }

            })
            if(poor_reason.length > 0){
                data.poor_reason = poor_reason;
            }

            var respondent = $("#respondent").val();
            var inquirer = $("#inquirer").val();
            var time = $("#time").val();

            data.respondent = respondent;
            data.inquirer = inquirer;
            data.time = time;
            data.username = username;
            if(!data.reservoir || !data.householder || !data.inquirer || !data.time){
                alert("请输入必填项");
                return;
            }
            if(kind == "库区安置登记表"){
                if(!$("#city td:nth-child(1) input").val() || !$("#city td:nth-child(2) input").val() || !$("#home_people1 td:first-child input").val()){
                    alert("请输入所在地");
                    return;
                }
            }else if(kind == "移民搬迁登记表"){
                if(!$("#city1 td:nth-child(2) input").val() || !$("#city1 td:nth-child(3) input").val() || !$("#home_people1 td:first-child input").val()){
                    alert("请输入迁入地");
                    return;
                }
            }
            var h_flag = [];
            $.each(home_infos,function (i,n) {
                if(n.name == householder){
                    h_flag.push(n.name);
                }
            })
            if(h_flag.length == 0){
                alert("家庭信息中需包含户主信息");
                return;
            }else if(h_flag.length > 1){
                alert("家庭信息中不能有重复姓名");
                return;
            }
            console.log(data);
            flag = false;
            $.ajax({
                url: "/dataEditSaving.do",
                type: "post",
                data: {data:data},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    flag = true;
                    alert("提交成功");
                    window.open("/lincang-yimin.htm?name=table","_self");
                    $("#jqtable input").val("");
                }
            });
        }
    })

    //打印插件
    $("#jqprint").click(function () {
        jQuery('#jqtable').print();
    });
    $(".btn-danger").click(function () {
        if(table_name == "allinfo_table"){
            window.open("/lincang-yimin.htm?name=table","_self");
        }else if(table_name == "table1"){
            window.open("/lincang-yimin.htm","_self");
        }
    })
    //导出excle
    // var $exportLink = document.getElementById('btn-container');
    //
    // $exportLink.addEventListener('click', function(e){
    //
    //     e.preventDefault();
    //
    //     if(e.target.nodeName === "A"){
    //
    //         tableExport('jqtable', '测试测试', e.target.getAttribute('data-type'));
    //
    //     }
    //
    // }, false);

    //导出Excel
    // JavaScript Document
    //功能:导出多个表格到EXCEL或者ET
    //调用方法：toExcel('要导出的表格ID,以|分隔多个表格','输出到excel中的工作薄名称','导出的方式，0为不带格式，1为带格式','要导出的列数')
    // var idTmr = "";
    // function Cleanup() {
    //     window.clearInterval(idTmr);
    //     CollectGarbage();
    // }
    //
    // function toExcel(tableId,sheetname,method,cols){
    //     if(!confirm("确认导出数据到EXCEL?")){return false;}
    //     var tables=tableId.split("|");
    //     for(var n=0;n<tables.length;n++){
    //         if(!document.getElementById(tables[n])){
    //             alert("表格"+tables[n]+"不存在,请检查是否有数据输出");
    //             return false;
    //         }
    //     }
    //     try{
    //         var oXL = new ActiveXObject("excel.Application");
    //     }catch(e1){
    //         try{
    //             var oXL = new ActiveXObject("et.Application");
    //         }catch(e2){
    //             alert(e2.description+"\n\n\n要使用EXCEL对象，您必须安装Excel电子表格软件\n或者,需要安装Kingsoft ET软件\n\n同时浏览器须使用“ActiveX 控件”，您的浏览器须允许执行控件。");
    //             return;
    //         }
    //     }
    //
    //     try {
    //         var m=1;
    //         oXL.Visible = true;
    //         oXL.ScreenUpdating=false;
    //         //oXL.Calculation=-4135;
    //         var oWB = oXL.Workbooks.Add;
    //         var oSheet = oWB.ActiveSheet;
    //         var xlsheet = oWB.Worksheets(1);
    //         for(var i=oWB.Worksheets.count;i>1;i--){	//删除多余工作表
    //             oWB.Worksheets(i).Delete();
    //         }
    //         for(var n=0;n<tables.length;n++){
    //             var elTable = document.getElementById(tables[n]);
    //             var oRangeRef = document.body.createTextRange();
    //             oRangeRef.moveToElementText(elTable);
    //             oRangeRef.execCommand("Copy");
    //             oSheet.cells(m,1).select;
    //             oSheet.Paste();	//此方式为直接粘贴，带格式
    //             if (method == 0) {
    //                 oSheet.cells.ClearFormats;
    //                 //以下删除因表头分拆后产生的空行,一般表头不会超过5行,此处检查5行数据
    //                 for(var delrow=1;delrow<5;delrow++){
    //                     var isBlank=true;
    //                     for(var col=1;col<=elTable.rows[0].cells.length;col++){
    //                         if(oSheet.cells(m+1,col).value!="" && oSheet.cells(m+1,col).value!=undefined){
    //                             isBlank=false;
    //                             break;
    //                         }
    //                     }
    //                     if(isBlank){
    //                         oSheet.Rows(m+1).Delete;
    //                     }
    //                 }
    //             }
    //             m+=elTable.rows.length;
    //         }
    //         //oSheet.Cells.NumberFormatLocal = "@";//格式化数字时使用
    //         n=oSheet.Shapes.count;
    //         for(var i=1;i<=n;i++){
    //             oSheet.Shapes.Item(1).Delete();		//因为每次删除都会使总数减少,所以删除n次第一个对象,也可以倒过来从大到小删除
    //         }
    //         oXL.Selection.CurrentRegion.Select;			//选择当前区域
    //         oXL.Selection.Interior.Pattern = 0;			//设置底色为空
    //         oXL.Selection.Borders.LineStyle = 1;		//设置单元格边框为实线
    //         oXL.Selection.ColumnWidth = 5;				//设置列宽
    //         oXL.Selection.RowHeight = 16;				//行高
    //
    //         oXL.Selection.Columns.AutoFit;				//列宽自动适应
    //         //xlsheet.Columns("A:Z").AutoFit;			//列宽自动适应
    //         xlsheet.Rows("1:"+m).AutoFit;				//自动行高
    //         xlsheet.Name=sheetname;
    //         oSheet = null;
    //         oWB = null;
    //         appExcel = null;
    //         //oXL.Calculation=-4105;
    //         oXL.ScreenUpdating=true;
    //         idTmr = window.setInterval("Cleanup();",1); 	//释放Excel进程，回收内存空间，避免产生多个不会自己终止的Excel进程
    //     }catch (e) {
    //         idTmr = window.setInterval("Cleanup();",1);
    //         alert(e.description);
    //     }
    // }
    //
    // $(".btn-info").click(function () {
    //     toExcel("jqtable","test1",1,9)
    // })


    //导出Excel
    // var idTmr;
    // function  getExplorer() {
    //     var explorer = window.navigator.userAgent ;
    //     //ie
    //     if (explorer.indexOf("MSIE") >= 0) {
    //         return 'ie';
    //     }
    //     //firefox
    //     else if (explorer.indexOf("Firefox") >= 0) {
    //         return 'Firefox';
    //     }
    //     //Chrome
    //     else if(explorer.indexOf("Chrome") >= 0){
    //         return 'Chrome';
    //     }
    //     //Opera
    //     else if(explorer.indexOf("Opera") >= 0){
    //         return 'Opera';
    //     }
    //     //Safari
    //     else if(explorer.indexOf("Safari") >= 0){
    //         return 'Safari';
    //     }
    // }
    // function method5(tableid) {
    //     if(getExplorer()=='ie')
    //     {
    //         var curTbl = document.getElementById(tableid);
    //         var oXL = new ActiveXObject("Excel.Application");
    //         var oWB = oXL.Workbooks.Add();
    //         var xlsheet = oWB.Worksheets(1);
    //         var sel = document.body.createTextRange();
    //         sel.moveToElementText(curTbl);
    //         sel.select();
    //         sel.execCommand("Copy");
    //         xlsheet.Paste();
    //         oXL.Visible = true;
    //
    //         try {
    //             var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
    //         } catch (e) {
    //             print("Nested catch caught " + e);
    //         } finally {
    //             oWB.SaveAs(fname);
    //             oWB.Close(savechanges = false);
    //             oXL.Quit();
    //             oXL = null;
    //             idTmr = window.setInterval("Cleanup();", 1);
    //         }
    //
    //     }
    //     else
    //     {
    //         tableToExcel(tableid)
    //     }
    // }
    // function Cleanup() {
    //     window.clearInterval(idTmr);
    //     CollectGarbage();
    // }
    // var tableToExcel = (function() {
    //     var uri = 'data:application/vnd.ms-excel;base64,',
    //         template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',
    //         base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
    //         format = function(s, c) {
    //             return s.replace(/{(\w+)}/g,
    //                 function(m, p) { return c[p]; }) }
    //     return function(table, name) {
    //         if (!table.nodeType) table = document.getElementById(table)
    //         var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    //         window.location.href = uri + base64(format(template, ctx))
    //     }
    // })()
    // $(".btn-info").click(function () {
    //     method5("jqtable2");
    // })

    // $(document).ready(function () {
    //     $(".btn-info").click(function () {
    //         console.log("excel");
    //         $("#jqtable2").table2excel({
    //             exclude  : ".noExl", //过滤位置的 css 类名
    //             filename : "你想说啥" + new Date().getTime() + ".xls", //文件名称
    //             name: "Excel Document Name.xlsx",
    //             exclude_img: true,
    //             exclude_links: true,
    //             exclude_inputs: true
    //
    //         });
    //     });
    // });


})
