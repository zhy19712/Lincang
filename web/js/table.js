/**
 * Created by zhangchuan on 2017/10/20.
 */
$(function () {
    //清空表格
    $("#jqtable input").val("");
    $("#yes").val("是");
    $("#no").val("否");
    $("#traffic").val("交通落后");
    $("#money").val("缺技术、资金、土地、水");
    $("#disaster").val("因学、残、灾");
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
    $("#btn-container li:first-child").click(function () {
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
        if(!reservoir || !householder || !bank_user || !bank_name || !bank_number){
            alert("没有填写必填项");
            return;
        }
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
            move.from_city = $("#city1 td:nth-child(2) input").val();
        }
        if($("#city1 td:nth-child(3) input").val()){
            move.from_disirict = $("#city1 td:nth-child(3) input").val();
        }
        if($("#city1 td:nth-child(4) input").val()){
            move.from_town = $("#city1 td:nth-child(4) input").val();
        }
        if($("#city1 td:nth-child(5) input").val()){
            move.from_village = $("#city1 td:nth-child(5) input").val();
        }
        if($("#city1 td:nth-child(6) input").val()){
            move.from_group = $("#city1 td:nth-child(6) input").val();
        }
        if($("#city1 td:nth-child(7) input").val()){
            move.from_remake = $("#city1 td:nth-child(7) input").val();
        }
        if($("#city2 td:nth-child(2) input").val()){
            move.to_city = $("#city2 td:nth-child(2) input").val();
        }
        if($("#city2 td:nth-child(3) input").val()){
            move.to_disirict = $("#city2 td:nth-child(3) input").val();
        }
        if($("#city2 td:nth-child(4) input").val()){
            move.to_town = $("#city2 td:nth-child(4) input").val();
        }
        if($("#city2 td:nth-child(5) input").val()){
            move.to_village = $("#city2 td:nth-child(5) input").val();
        }
        if($("#city2 td:nth-child(6) input").val()){
            move.to_group = $("#city2 td:nth-child(6) input").val();
        }
        if($("#city2 td:nth-child(7) input").val()){
            move.to_remake = $("#city2 td:nth-child(7) input").val();
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

        // var money2 = new money("养殖业收入",money_content("in_animal",2,1),money_val("in_animal",2,2),money_val("in_animal",2,3),money_val("in_animal",2,4),money_val("in_animal",2,5));
        // var money3 = new money("养殖业收入",money_content("in_animal",3,1),money_val("in_animal",3,2),money_val("in_animal",3,3),money_val("in_animal",3,4),money_val("in_animal",3,5));
        // var money4 = new money("养殖业收入",money_content("in_animal",4,1),money_val("in_animal",4,2),money_val("in_animal",4,3),money_val("in_animal",4,4),money_val("in_animal",4,5));
        // var money5 = new money("养殖业收入",money_content("in_animal",5,1),money_val("in_animal",5,2),money_val("in_animal",5,3),money_val("in_animal",5,4),money_val("in_animal",5,5));
        // var money6 = new money("养殖业收入",money_content("in_animal",6,1),money_val("in_animal",6,2),money_val("in_animal",6,3),money_val("in_animal",6,4),money_val("in_animal",6,5));
        // var money7 = new money("养殖业收入",money_content("in_animal",7,1),money_val("in_animal",7,2),money_val("in_animal",7,3),money_val("in_animal",7,4),money_val("in_animal",7,5));
        // var money8 = new money("养殖业收入",money_content("in_animal",8,1),money_val("in_animal",8,2),money_val("in_animal",8,3),money_val("in_animal",8,4),money_val("in_animal",8,5));

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

        // var money9 = new money("种植业收入",money_content("in_botany",1,2),money_val("in_botany",1,3),money_val("in_botany",1,4),money_val("in_botany",1,5),money_val("in_botany",1,6));
        // var money10 = new money("种植业收入",money_content("in_botany",2,1),money_val("in_botany",2,2),money_val("in_botany",2,3),money_val("in_botany",2,4),money_val("in_botany",2,5));
        // var money11 = new money("种植业收入",money_content("in_botany",3,1),money_val("in_botany",3,2),money_val("in_botany",3,3),money_val("in_botany",3,4),money_val("in_botany",3,5));
        // var money12 = new money("种植业收入",money_content("in_botany",4,1),money_val("in_botany",4,2),money_val("in_botany",4,3),money_val("in_botany",4,4),money_val("in_botany",4,5));
        // var money13 = new money("其他收入",money_content("in_other",1,2),money_val("in_other",1,3),money_val("in_other",1,4),money_val("in_other",1,5),money_val("in_other",1,6));
        // var money14 = new money("其他收入",money_content("in_other",2,1),money_val("in_other",2,2),money_val("in_other",2,3),money_val("in_other",2,4),money_val("in_other",2,5));
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

        // var money15 = new money("种植业支出",money_content("out_botany",1,2),money_val("out_botany",1,3),money_val("out_botany",1,4),money_val("out_botany",1,5),money_val("out_botany",1,6));
        // var money16 = new money("种植业支出",money_content("out_botany",2,1),money_val("out_botany",2,2),money_val("out_botany",2,3),money_val("out_botany",2,4),money_val("out_botany",2,5));
        // var money17 = new money("种植业支出",money_content("out_botany",3,1),money_val("out_botany",3,2),money_val("out_botany",3,3),money_val("out_botany",3,4),money_val("out_botany",3,5));
        // var money18 = new money("种植业支出",money_content("out_botany",4,1),money_val("out_botany",4,2),money_val("out_botany",4,3),money_val("out_botany",4,4),money_val("out_botany",4,5));
        // var money19 = new money("种植业支出",money_content("out_botany",5,1),money_val("out_botany",5,2),money_val("out_botany",5,3),money_val("out_botany",5,4),money_val("out_botany",5,5));
        // var money20 = new money("种植业支出",money_content("out_botany",6,1),money_val("out_botany",6,2),money_val("out_botany",6,3),money_val("out_botany",6,4),money_val("out_botany",6,5));

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

        // var money21 = new money("养殖业支出",money_content("out_animal",1,2),money_val("out_animal",1,3),money_val("out_animal",1,4),money_val("out_animal",1,5),money_val("out_animal",1,6));
        // var money22 = new money("养殖业支出",money_content("out_animal",2,1),money_val("out_animal",2,2),money_val("out_animal",2,3),money_val("out_animal",2,4),money_val("out_animal",2,5));
        // var money23 = new money("养殖业支出",money_content("out_animal",3,1),money_val("out_animal",3,2),money_val("out_animal",3,3),money_val("out_animal",3,4),money_val("out_animal",3,5));
        // var money24 = new money("养殖业支出",money_content("out_animal",4,1),money_val("out_animal",4,2),money_val("out_animal",4,3),money_val("out_animal",4,4),money_val("out_animal",4,5));

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

        // var money25 = new money("生活支出",money_content("out_life",1,2),money_val("out_life",1,3),money_val("out_life",1,4),money_val("out_life",1,5),money_val("out_life",1,6));
        // var money26 = new money("生活支出",money_content("out_life",2,1),money_val("out_life",2,2),money_val("out_life",2,3),money_val("out_life",2,4),money_val("out_life",2,5));
        // var money27 = new money("生活支出",money_content("out_life",3,1),money_val("out_life",3,2),money_val("out_life",3,3),money_val("out_life",3,4),money_val("out_life",3,5));
        // var money28 = new money("生活支出",money_content("out_life",4,1),money_val("out_life",4,2),money_val("out_life",4,3),money_val("out_life",4,4),money_val("out_life",4,5));
        // var money29 = new money("生活支出",money_content("out_life",5,1),money_val("out_life",5,2),money_val("out_life",5,3),money_val("out_life",5,4),money_val("out_life",5,5));
        // var money30 = new money("生活支出",money_content("out_life",6,1),money_val("out_life",6,2),money_val("out_life",6,3),money_val("out_life",6,4),money_val("out_life",6,5));
        // var money31 = new money("生活支出",money_content("out_life",7,1),money_val("out_life",7,2),money_val("out_life",7,3),money_val("out_life",7,4),money_val("out_life",7,5));
        // var money32 = new money("生活支出",money_content("out_life",8,1),money_val("out_life",8,2),money_val("out_life",8,3),money_val("out_life",8,4),money_val("out_life",8,5));

        // money_info.push(money1,money2,money3,money4,money5,money6,money7,money8,money9,money10,money11,money12,money13,money14,money15,money16,money17,money18,money19,money20,money21,money22,money23,money24,money25,money26,money27,money28,money29,money30,money31,money32);

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

        if(!respondent || !inquirer || !time){
            alert("请将必填项输入完整");
            return;
        }
        data.respondent = respondent;
        data.inquirer = inquirer;
        data.time = time;
        data.username = username;
        console.log(data);
        $.ajax({
            url: "/dataEntering.do",
            type: "post",
            data: {data:data},
            dataType: "json",
            success: function (data) {
                console.log(data);
                alert("提交成功");
                $("#jqtable input").val("");
            }
        });
    })

})


