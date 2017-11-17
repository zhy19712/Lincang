package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.DataEnteringServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DataEnteringController {

    @Autowired
    DataEnteringServiceImp dataEnteringServiceImp;

    @ResponseBody
    @RequestMapping(value="/dataEntering",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String dataEntering(HttpServletRequest request) {
        //条件的map
        //Map<String,Object> mapTiaojian = new HashMap<String,Object>();
        //这是前端传过来的数据
        Map<String,String[]> map = request.getParameterMap();
        //设置一个数量来接受前端写了几个用户
        int peopleNum = 0;
        //move的信息
        int moveNum = 0;
        //house的数组
        int houseNum = 0;
        //bank的数组
        int bankNum = 0;
        //致贫原因的数组
        int prNum = 0;
        //收入的数组
        int incomeNum = 0;
        //支出的数组
        int outcomeNum = 0;
        for (String key : map.keySet()) {
            if( key.startsWith("data[home_infos]") ){
                peopleNum++;
            }
            if( key.startsWith("data[location]") | key.startsWith("data[move]") ){
                moveNum++;
            }
            if( key.startsWith("data[house]") ){
                houseNum++;
            }
            if( key.startsWith("data[bank") ){
                bankNum++;
            }
            if( key.startsWith("data[poor_reason]") ){
                prNum++;
            }
            if( key.startsWith("data[money_info]") ){
                incomeNum++;
            }
            if( key.startsWith("data[money_outcome]") ){
                outcomeNum++;
            }
            for(String value : map.get(key)){
                System.out.println("key= "+ key + " and value= " + value );
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date =  new Date();
        //一个集合来收集用户信息
        List<People> peopleList = new ArrayList<People>();
        Set<People> peopleSet = new HashSet();
        String type =   map.get("data[kind]")[0];

        for( int p = 0 ; p < (peopleNum/7);p++ ){
            People people = new People();
            if( "库区安置登记表".equals( type ) ){
                people.setFid( "KQ"+sdf.format(date) );
                people.setTable_type("库区安置登记表");
            }
            if( "移民搬迁登记表".equals( type ) ){
                people.setFid( "BQ"+sdf.format(date) );
                people.setTable_type("移民搬迁登记表");
                people.setLocation( map.containsKey("data[place]")?map.get("data[place]")[0]:"" );
            }
            people.setReservoir( map.get("data[reservoir]")[0] );
            if( map.get("data[home_infos]["+p+"][name]")[0].equals(map.get("data[householder]")[0]) ) {
                people.setMaster(1);
                people.setPhone( map.containsKey("data[tel_number]")?map.get("data[tel_number]")[0]:"");
            } else {
                people.setMaster(0);
            }
            people.setName( map.containsKey("data[home_infos]["+p+"][name]")?map.get("data[home_infos]["+p+"][name]")[0]:"" );
            people.setPid( map.containsKey("data[home_infos]["+p+"][id]")?map.get("data[home_infos]["+p+"][id]")[0]:"" );
            people.setGender( map.containsKey("data[home_infos]["+p+"][sex]")?map.get("data[home_infos]["+p+"][sex]")[0]:"" );
            people.setRace( map.containsKey("data[home_infos]["+p+"][nation]")?map.get("data[home_infos]["+p+"][nation]")[0]:"" );
            people.setRelation( map.containsKey("data[home_infos]["+p+"][relation]")?map.get("data[home_infos]["+p+"][relation]")[0]:"" );
            people.setEducation( map.containsKey("data[home_infos]["+p+"][cultural]")?map.get("data[home_infos]["+p+"][cultural]")[0]:"" );
            people.setProfession( map.containsKey("data[home_infos]["+p+"][profession]")?map.get("data[home_infos]["+p+"][profession]")[0]:"");
            people.setHome_size( peopleNum/7 );
            people.setImm_num( peopleNum/7 );
            people.setProp( map.containsKey("data[prop]")? ( "是".equals(map.get("data[prop]")[0])?1:0 ):0 );
            String poor_reason = "";
            for( int pr = 0;pr < prNum;pr++ ){
                if( pr < (prNum - 1) ){
                    //data[poor_reason][0][reason]
                    poor_reason += (map.get("data[poor_reason]["+pr+"][reason]")[0] + ",");
                }
                if( pr == (prNum - 1) ){
                    poor_reason += map.get("data[poor_reason]["+pr+"][reason]")[0];
                }
            }
            people.setPoor_reason( poor_reason );
            people.setInterviewer( map.get("data[inquirer]")[0] );
            people.setInterviewee( map.get("data[respondent]")[0] );
            people.setCreated_at( map.get("data[time]")[0] );
            peopleSet.add(people);
        }



        for (People ple : peopleSet) {
            peopleList.add(ple);
        }
        //存储用户返回值
        int rePeople = 0;
        if (peopleList.size() > 0){
            try {
                rePeople = dataEnteringServiceImp.savePerson(peopleList);
            } catch (Exception e) {
                rePeople = -1;
            }
        }
        //move信息
        Move move = new Move();
        if ( moveNum > 0 ) {
            if ("库区安置登记表".equals(type)) {
                move.setFid("KQ" + sdf.format(date));
                move.setTo_city( map.containsKey("data[location][city]")?map.get("data[location][city]")[0]:"" );
                move.setTo_district( map.containsKey("data[location][county]")?map.get("data[location][county]")[0]:"" );
                move.setTo_town( map.containsKey("data[location][town]")?map.get("data[location][town]")[0]:"" );
                move.setTo_village( map.containsKey("data[location][village]")?map.get("data[location][village]")[0]:"" );
                move.setTo_group( map.containsKey("data[location][group]")?map.get("data[location][group]")[0]:"" );
                move.setTo_remark( map.containsKey("data[location][remark]")?map.get("data[location][remark]")[0]:"");
            }
            if ("移民搬迁登记表".equals(type)) {
                move.setFid("BQ" + sdf.format(date));
                move.setTo_city( map.containsKey("data[move][to_city]")?map.get("data[move][to_city]")[0]:"" );
                move.setTo_district( map.containsKey("data[move][to_disirict]")? map.get("data[move][to_disirict]")[0]:"" );
                move.setTo_town( map.containsKey("data[move][to_town]")?map.get("data[move][to_town]")[0]:"" );
                move.setTo_village( map.containsKey("data[move][to_village]")?map.get("data[move][to_village]")[0]:"" );
                move.setTo_group( map.containsKey("data[move][to_group]")?map.get("data[move][to_group]")[0]:"" );
                move.setTo_remark( map.containsKey("data[move][to_remake]")?map.get("data[move][to_remake]")[0]:"" );

                move.setFrom_city( map.containsKey("data[move][from_city]")?map.get("data[move][from_city]")[0]:"" );
                move.setFrom_district( map.containsKey("data[move][from_disirict]")? map.get("data[move][from_disirict]")[0]:"" );
                move.setFrom_town( map.containsKey("data[move][from_town]")?map.get("data[move][from_town]")[0]:"" );
                move.setFrom_village( map.containsKey("data[move][from_village]")?map.get("data[move][from_village]")[0]:"" );
                move.setFrom_group( map.containsKey("data[move][from_group]")?map.get("data[move][from_group]")[0]:"" );
                move.setFrom_remark( map.containsKey("data[move][from_remake]")?map.get("data[move][from_remake]")[0]:"" );
            }

        }

        //存储move返回值
        int reMove= 0;
        if (move.getFid() != null){
            try {
                reMove = dataEnteringServiceImp.saveMove(move);
            } catch (Exception e) {
                reMove = -1;
            }
        }

        //银行信息
        Bank bank = new Bank();
        if( bankNum > 0 ){
            if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                bank.setFid( "KQ"+sdf.format(date) );
            }
            if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                bank.setFid( "BQ"+sdf.format(date) );
            }
            bank.setAccount_name( map.containsKey("data[bank_user]")?map.get("data[bank_user]")[0]:"" );
            bank.setBank_name( map.containsKey("data[bank_name]")?map.get("data[bank_name]")[0]:"" );
            bank.setAccount_number( map.containsKey("data[bank_number]")?map.get("data[bank_number]")[0]:"" );
        }
        //存储银行返回值
        int reBank= 0;
        if (bank.getFid() != null){
            try {
                reBank = dataEnteringServiceImp.saveBank(bank);
            } catch (Exception e) {
                reBank = -1;
            }
        }
        //House信息
        House house = new House();

        if( houseNum > 0 ){
            if( "库区安置登记表".equals( type ) ){
                house.setFid( "KQ"+sdf.format(date) );
            }
            if( "移民搬迁登记表".equals( type ) ){
                house.setFid( "BQ"+sdf.format(date) );
            }

            house.setMain_size( map.containsKey("data[house][main_arear]")?map.get("data[house][main_arear]")[0]:"" );
            house.setMain_structure1( map.containsKey("data[house][main_structure1]")?map.get("data[house][main_structure1]")[0]:"" );
            house.setMain_structure2( map.containsKey("data[house][main_structure2]")?map.get("data[house][main_structure2]")[0]:"");
            house.setMain_structure3( map.containsKey("data[house][main_structure3]")?map.get("data[house][main_structure3]")[0]:"" );
            house.setMain_structure4( map.containsKey("data[house][main_structure4]")?map.get("data[house][main_structure4]")[0]:"" );
            house.setMain_structure5( map.containsKey("data[house][main_easy]")?map.get("data[house][main_easy]")[0]:"" );
            house.setMain_remark( map.containsKey("data[house][main_remark]")?map.get("data[house][main_remark]")[0]:"" );


            house.setSub_size( map.containsKey("data[house][sub_arear]")?map.get("data[house][sub_arear]")[0]:"");
            house.setSub_structure1( map.containsKey("data[house][sub_structure1]")?map.get("data[house][sub_structure1]")[0]:"" );
            house.setSub_structure2( map.containsKey("data[house][sub_structure2]")?map.get("data[house][sub_structure2]")[0]:"" );
            house.setSub_structure3( map.containsKey("data[house][sub_structure3]")?map.get("data[house][sub_structure3]")[0]:"" );
            house.setSub_structure4( map.containsKey("data[house][sub_structure4]")?map.get("data[house][sub_structure4]")[0]:"" );
            house.setSub_structure5( map.containsKey("data[house][sub_easy]")?map.get("data[house][sub_easy]")[0]:"" );
            house.setSub_remark( map.containsKey("data[house][sub_remark]")?map.get("data[house][sub_remark]")[0]:"" );

        }

        //存储house返回值
        int reHouse= 0;
        if (house.getFid() != null){
            try {
                reHouse = dataEnteringServiceImp.saveHouse(house);
            } catch (Exception e) {
                reHouse = -1;
            }
        }
        //一个集合来收集income信息
        List<Income> incomeList = new ArrayList<Income>();
        Set<Income> incomeSet = new HashSet();
        for( int p = 0 ; p < (incomeNum/6);p++ ){
            Income income = new Income();
            if( "库区安置登记表".equals( type ) ){
                income.setFid( "KQ"+sdf.format(date) );
            }
            if( "移民搬迁登记表".equals( type ) ){
                income.setFid( "BQ"+sdf.format(date) );
            }
            income.setIncome_source( map.containsKey("data[money_info]["+p+"][kind]")?map.get("data[money_info]["+p+"][kind]")[0]:"" );
            income.setIncome_cate( map.containsKey("data[money_info]["+p+"][content]")?map.get("data[money_info]["+p+"][content]")[0]:"" );
            income.setIncome_quantity( map.containsKey("data[money_info]["+p+"][count]")?( map.get("data[money_info]["+p+"][count]")[0].equals("")?"0":map.get("data[money_info]["+p+"][count]")[0] ):"0"  );
            income.setIncome_unit( map.containsKey("data[money_info]["+p+"][price]")?( map.get("data[money_info]["+p+"][price]")[0].equals("")?"0":map.get("data[money_info]["+p+"][price]")[0] ):"0.0"  );
            income.setIncome_sum( map.containsKey("data[money_info]["+p+"][total]")?( map.get("data[money_info]["+p+"][total]")[0].equals("")?"0":map.get("data[money_info]["+p+"][total]")[0] ):"0.0"  );
            income.setRemark( map.containsKey("data[money_info]["+p+"][remark]")?map.get("data[money_info]["+p+"][remark]")[0]:"" );
            incomeSet.add(income);
        }
        for(Income income : incomeSet){
            incomeList.add(income);
        }
        //income存储
        int reIncome= 0;
        if (incomeList.size() > 0){
            try {
                reIncome = dataEnteringServiceImp.saveIncome(incomeList);
            } catch (Exception e) {
                reIncome = -1;
            }
        }
        //一个集合来收集outcome信息
        List<Outcome> outcomeList = new ArrayList<Outcome>();
        Set<Outcome> outcomeSet = new HashSet();
        for( int p = 0 ; p < (outcomeNum/6);p++ ){
            Outcome outcome = new Outcome();
            if( "库区安置登记表".equals( type ) ){
                outcome.setFid( "KQ"+sdf.format(date) );
            }
            if( "移民搬迁登记表".equals( type ) ){
                outcome.setFid( "BQ"+sdf.format(date) );
            }
            outcome.setOutcome_source( map.containsKey("data[money_outcome]["+p+"][kind]")?map.get("data[money_outcome]["+p+"][kind]")[0]:"" );
            outcome.setOutcome_cate( map.containsKey("data[money_outcome]["+p+"][content]")?map.get("data[money_outcome]["+p+"][content]")[0]:"" );
            outcome.setOutcome_quantity( map.containsKey("data[money_outcome]["+p+"][count]")?( map.get("data[money_outcome]["+p+"][count]")[0].equals("")?"0":map.get("data[money_outcome]["+p+"][count]")[0] ):""  );
            outcome.setOutcome_unit( map.containsKey("data[money_outcome]["+p+"][price]")?( map.get("data[money_outcome]["+p+"][price]")[0].equals("")?"0.0":map.get("data[money_outcome]["+p+"][price]")[0] ):"0.0"  );
            outcome.setOutcome_sum( map.containsKey("data[money_outcome]["+p+"][total]")?( map.get("data[money_outcome]["+p+"][total]")[0].equals("")?"0.0":map.get("data[money_outcome]["+p+"][total]")[0] ):"0.0"  );
            outcome.setRemark( map.containsKey("data[money_outcome]["+p+"][remark]")?map.get("data[money_outcome]["+p+"][remark]")[0]:"" );
            outcomeSet.add(outcome);
        }

        for(Outcome outcome : outcomeSet){
            outcomeList.add(outcome);
        }
        //outcome存储
        int reOutcome= 0;
        if (outcomeList.size() > 0){
            try {
                reOutcome = dataEnteringServiceImp.saveOutcome(outcomeList);
            } catch (Exception e) {
                reOutcome = -1;
            }
        }
        //rePeople,reMove,reBank,reRouse,reIncome,reOutcome
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(rePeople);
        intList.add(reMove);
        intList.add(reBank);
        intList.add(reHouse);
        intList.add(reIncome);
        intList.add(reOutcome);
        String strResult = "";
        for( int r = 0;r < intList.size();r++){
            if( intList.get(r) == -1 ){
                strResult = "failure";
            } else {
                strResult = "success";
            }
        }
        Map mapResult = new HashMap();
        mapResult.put("result",strResult);
        return new Gson().toJson(mapResult);
    }

    /**
     * 输入整个excel表数据的的编辑按钮之后的保存
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/dataEditSaving",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String dataEditSaving(HttpServletRequest request) {
        //条件的map
        //Map<String,Object> mapTiaojian = new HashMap<String,Object>();
        //这是前端传过来的数据
        Map<String,String[]> map = request.getParameterMap();
        //设置一个数量来接受前端写了几个用户
        int peopleNum = 0;
        //move的信息
        int moveNum = 0;
        //house的数组
        int houseNum = 0;
        //bank的数组
        int bankNum = 0;
        //致贫原因的数组
        int prNum = 0;
        //收入的数组
        int incomeNum = 0;
        //支出的数组
        int outcomeNum = 0;
        for (String key : map.keySet()) {
            if( key.startsWith("data[home_infos]") ){
                peopleNum++;
            }
            if( key.startsWith("data[location]") | key.startsWith("data[move]") ){
                moveNum++;
            }
            if( key.startsWith("data[house]") ){
                houseNum++;
            }
            if( key.startsWith("data[bank") ){
                bankNum++;
            }
            if( key.startsWith("data[poor_reason]") ){
                prNum++;
            }
            if( key.startsWith("data[money_info]") ){
                incomeNum++;
            }
            if( key.startsWith("data[money_outcome]") ){
                outcomeNum++;
            }
            for(String value : map.get(key)){
                System.out.println("key= "+ key + " and value= " + value );
            }
        }
        //一个集合来收集用户信息
        List<People> peopleList = new ArrayList<People>();
        Set<People> peopleSet = new HashSet();
        String type =   map.get("data[kind]")[0];
        String fid =   map.get("data[fid]")[0];
        for( int p = 0 ; p < (peopleNum/7);p++ ){
            People people = new People();
            if( "库区安置登记表".equals( type ) ){
                people.setFid( fid );
                people.setTable_type("库区安置登记表");
            }
            if( "移民搬迁登记表".equals( type ) ){
                people.setFid( fid );
                people.setTable_type("移民搬迁登记表");
                people.setLocation( map.containsKey("data[place]")?map.get("data[place]")[0]:"" );
            }
            people.setReservoir( map.get("data[reservoir]")[0] );
            if( map.get("data[home_infos]["+p+"][name]")[0].equals(map.get("data[householder]")[0]) ) {
                people.setMaster(1);
                people.setPhone( map.containsKey("data[tel_number]")?map.get("data[tel_number]")[0]:"");
            } else {
                people.setMaster(0);
            }
            people.setName( map.containsKey("data[home_infos]["+p+"][name]")?map.get("data[home_infos]["+p+"][name]")[0]:"" );
            people.setPid( map.containsKey("data[home_infos]["+p+"][id]")?map.get("data[home_infos]["+p+"][id]")[0]:"" );
            people.setGender( map.containsKey("data[home_infos]["+p+"][sex]")?map.get("data[home_infos]["+p+"][sex]")[0]:"" );
            people.setRace( map.containsKey("data[home_infos]["+p+"][nation]")?map.get("data[home_infos]["+p+"][nation]")[0]:"" );
            people.setRelation( map.containsKey("data[home_infos]["+p+"][relation]")?map.get("data[home_infos]["+p+"][relation]")[0]:"" );
            people.setEducation( map.containsKey("data[home_infos]["+p+"][cultural]")?map.get("data[home_infos]["+p+"][cultural]")[0]:"" );
            people.setProfession( map.containsKey("data[home_infos]["+p+"][profession]")?map.get("data[home_infos]["+p+"][profession]")[0]:"");
            people.setHome_size( peopleNum/7 );
            people.setImm_num( peopleNum/7 );
            people.setProp( map.containsKey("data[prop]")? ( "是".equals(map.get("data[prop]")[0])?1:0 ):0 );
            String poor_reason = "";
            for( int pr = 0;pr < prNum;pr++ ){
                if( pr < (prNum - 1) ){
                    //data[poor_reason][0][reason]
                    poor_reason += (map.get("data[poor_reason]["+pr+"][reason]")[0] + ",");
                }
                if( pr == (prNum - 1) ){
                    poor_reason += map.get("data[poor_reason]["+pr+"][reason]")[0];
                }
            }
            people.setPoor_reason( poor_reason );
            people.setInterviewer( map.get("data[inquirer]")[0] );
            people.setInterviewee( map.get("data[respondent]")[0] );
            people.setCreated_at( map.get("data[time]")[0] );
            peopleSet.add(people);
        }

        for (People ple : peopleSet) {
            peopleList.add(ple);
        }
        //存储用户返回值
        int rePeople = 0;
        if (peopleList.size() > 0){
            try {
                int deleteNum = dataEnteringServiceImp.deletePerson(fid);
                rePeople = dataEnteringServiceImp.savePerson(peopleList);
            } catch (Exception e) {
                rePeople = -1;
            }
        }
        //move信息
        Move move = new Move();
        if ( moveNum > 0 ) {
            if ("库区安置登记表".equals(type)) {
                move.setFid(fid);
                move.setTo_city( map.containsKey("data[location][city]")?map.get("data[location][city]")[0]:"" );
                move.setTo_district( map.containsKey("data[location][county]")?map.get("data[location][county]")[0]:"" );
                move.setTo_town( map.containsKey("data[location][town]")?map.get("data[location][town]")[0]:"" );
                move.setTo_village( map.containsKey("data[location][village]")?map.get("data[location][village]")[0]:"" );
                move.setTo_group( map.containsKey("data[location][group]")?map.get("data[location][group]")[0]:"" );
                move.setTo_remark( map.containsKey("data[location][remark]")?map.get("data[location][remark]")[0]:"");
            }
            if ("移民搬迁登记表".equals(type)) {
                move.setFid(fid);
                move.setTo_city( map.containsKey("data[move][to_city]")?map.get("data[move][to_city]")[0]:"" );
                move.setTo_district( map.containsKey("data[move][to_disirict]")? map.get("data[move][to_disirict]")[0]:"" );
                move.setTo_town( map.containsKey("data[move][to_town]")?map.get("data[move][to_town]")[0]:"" );
                move.setTo_village( map.containsKey("data[move][to_village]")?map.get("data[move][to_village]")[0]:"" );
                move.setTo_group( map.containsKey("data[move][to_group]")?map.get("data[move][to_group]")[0]:"" );
                move.setTo_remark( map.containsKey("data[move][to_remake]")?map.get("data[move][to_remake]")[0]:"" );

                move.setFrom_city( map.containsKey("data[move][from_city]")?map.get("data[move][from_city]")[0]:"" );
                move.setFrom_district( map.containsKey("data[move][from_disirict]")? map.get("data[move][from_disirict]")[0]:"" );
                move.setFrom_town( map.containsKey("data[move][from_town]")?map.get("data[move][from_town]")[0]:"" );
                move.setFrom_village( map.containsKey("data[move][from_village]")?map.get("data[move][from_village]")[0]:"" );
                move.setFrom_group( map.containsKey("data[move][from_group]")?map.get("data[move][from_group]")[0]:"" );
                move.setFrom_remark( map.containsKey("data[move][from_remake]")?map.get("data[move][from_remake]")[0]:"" );
            }

        }

        //存储move返回值
        int reMove= 0;
        if (move.getFid() != null){
            try {
                int deleteNum = dataEnteringServiceImp.deleteMove(fid);
                reMove = dataEnteringServiceImp.saveMove(move);
            } catch (Exception e) {
                reMove = -1;
            }
        }

        //银行信息
        Bank bank = new Bank();
        if( bankNum > 0 ){
            if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                bank.setFid(fid);
            }
            if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                bank.setFid(fid);
            }
            bank.setAccount_name( map.containsKey("data[bank_user]")?map.get("data[bank_user]")[0]:"" );
            bank.setBank_name( map.containsKey("data[bank_name]")?map.get("data[bank_name]")[0]:"" );
            bank.setAccount_number( map.containsKey("data[bank_number]")?map.get("data[bank_number]")[0]:"" );
        }
        //存储银行返回值
        int reBank= 0;
        if (bank.getFid() != null){
            try {
                int deleteNum = dataEnteringServiceImp.deleteBank(fid);
                reBank = dataEnteringServiceImp.saveBank(bank);
            } catch (Exception e) {
                reBank = -1;
            }
        }
        //House信息
        House house = new House();

        if( houseNum > 0 ){
            if( "库区安置登记表".equals( type ) ){
                house.setFid(fid);
            }
            if( "移民搬迁登记表".equals( type ) ){
                house.setFid(fid);
            }

            house.setMain_size( map.containsKey("data[house][main_arear]")?map.get("data[house][main_arear]")[0]:"" );
            house.setMain_structure1( map.containsKey("data[house][main_structure1]")?map.get("data[house][main_structure1]")[0]:"" );
            house.setMain_structure2( map.containsKey("data[house][main_structure2]")?map.get("data[house][main_structure2]")[0]:"");
            house.setMain_structure3( map.containsKey("data[house][main_structure3]")?map.get("data[house][main_structure3]")[0]:"" );
            house.setMain_structure4( map.containsKey("data[house][main_structure4]")?map.get("data[house][main_structure4]")[0]:"" );
            house.setMain_structure5( map.containsKey("data[house][main_easy]")?map.get("data[house][main_easy]")[0]:"" );
            house.setMain_remark( map.containsKey("data[house][main_remark]")?map.get("data[house][main_remark]")[0]:"" );


            house.setSub_size( map.containsKey("data[house][sub_arear]")?map.get("data[house][sub_arear]")[0]:"");
            house.setSub_structure1( map.containsKey("data[house][sub_structure1]")?map.get("data[house][sub_structure1]")[0]:"" );
            house.setSub_structure2( map.containsKey("data[house][sub_structure2]")?map.get("data[house][sub_structure2]")[0]:"" );
            house.setSub_structure3( map.containsKey("data[house][sub_structure3]")?map.get("data[house][sub_structure3]")[0]:"" );
            house.setSub_structure4( map.containsKey("data[house][sub_structure4]")?map.get("data[house][sub_structure4]")[0]:"" );
            house.setSub_structure5( map.containsKey("data[house][sub_easy]")?map.get("data[house][sub_easy]")[0]:"" );
            house.setSub_remark( map.containsKey("data[house][sub_remark]")?map.get("data[house][sub_remark]")[0]:"" );

        }

        //存储house返回值
        int reHouse= 0;
        if (house.getFid() != null){
            try {
                int deleteNum = dataEnteringServiceImp.deleteHouse(fid);
                reHouse = dataEnteringServiceImp.saveHouse(house);
            } catch (Exception e) {
                reHouse = -1;
            }
        }
        //一个集合来收集income信息
        List<Income> incomeList = new ArrayList<Income>();
        Set<Income> incomeSet = new HashSet();
        for( int p = 0 ; p < (incomeNum/6);p++ ){
            Income income = new Income();
            if( "库区安置登记表".equals( type ) ){
                income.setFid( fid );
            }
            if( "移民搬迁登记表".equals( type ) ){
                income.setFid( fid );
            }
            income.setIncome_source( map.containsKey("data[money_info]["+p+"][kind]")?map.get("data[money_info]["+p+"][kind]")[0]:"" );
            income.setIncome_cate( map.containsKey("data[money_info]["+p+"][content]")?map.get("data[money_info]["+p+"][content]")[0]:"" );
            income.setIncome_quantity( map.containsKey("data[money_info]["+p+"][count]")?( map.get("data[money_info]["+p+"][count]")[0].equals("")?"0":map.get("data[money_info]["+p+"][count]")[0] ):"0"  );
            income.setIncome_unit( map.containsKey("data[money_info]["+p+"][price]")?( map.get("data[money_info]["+p+"][price]")[0].equals("")?"0":map.get("data[money_info]["+p+"][price]")[0] ):"0.0"  );
            income.setIncome_sum( map.containsKey("data[money_info]["+p+"][total]")?( map.get("data[money_info]["+p+"][total]")[0].equals("")?"0":map.get("data[money_info]["+p+"][total]")[0] ):"0.0"  );
            income.setRemark( map.containsKey("data[money_info]["+p+"][remark]")?map.get("data[money_info]["+p+"][remark]")[0]:"" );
            incomeSet.add(income);
        }
        for(Income income : incomeSet){
            incomeList.add(income);
        }
        //income存储
        int reIncome= 0;
        if (incomeList.size() > 0){
            try {
                int deleteIncomeNum = dataEnteringServiceImp.deleteIncome(fid);
                reIncome = dataEnteringServiceImp.saveIncome(incomeList);
            } catch (Exception e) {
                reIncome = -1;
            }
        }
        //一个集合来收集outcome信息
        List<Outcome> outcomeList = new ArrayList<Outcome>();
        Set<Outcome> outcomeSet = new HashSet();
        for( int p = 0 ; p < (outcomeNum/6);p++ ){
            Outcome outcome = new Outcome();
            if( "库区安置登记表".equals( type ) ){
                outcome.setFid( fid );
            }
            if( "移民搬迁登记表".equals( type ) ){
                outcome.setFid( fid );
            }
            outcome.setOutcome_source( map.containsKey("data[money_outcome]["+p+"][kind]")?map.get("data[money_outcome]["+p+"][kind]")[0]:"" );
            outcome.setOutcome_cate( map.containsKey("data[money_outcome]["+p+"][content]")?map.get("data[money_outcome]["+p+"][content]")[0]:"" );
            outcome.setOutcome_quantity( map.containsKey("data[money_outcome]["+p+"][count]")?( map.get("data[money_outcome]["+p+"][count]")[0].equals("")?"0":map.get("data[money_outcome]["+p+"][count]")[0] ):""  );
            outcome.setOutcome_unit( map.containsKey("data[money_outcome]["+p+"][price]")?( map.get("data[money_outcome]["+p+"][price]")[0].equals("")?"0.0":map.get("data[money_outcome]["+p+"][price]")[0] ):"0.0"  );
            outcome.setOutcome_sum( map.containsKey("data[money_outcome]["+p+"][total]")?( map.get("data[money_outcome]["+p+"][total]")[0].equals("")?"0.0":map.get("data[money_outcome]["+p+"][total]")[0] ):"0.0"  );
            outcome.setRemark( map.containsKey("data[money_outcome]["+p+"][remark]")?map.get("data[money_outcome]["+p+"][remark]")[0]:"" );
            outcomeSet.add(outcome);
        }

        for(Outcome outcome : outcomeSet){
            outcomeList.add(outcome);
        }
        //outcome存储
        int reOutcome= 0;
        if (outcomeList.size() > 0){
            try {
                int deleteOutcomeNum = dataEnteringServiceImp.deleteOutcome(fid);
                reOutcome = dataEnteringServiceImp.saveOutcome(outcomeList);
            } catch (Exception e) {
                reOutcome = -1;
            }
        }
        //rePeople,reMove,reBank,reRouse,reIncome,reOutcome
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(rePeople);
        intList.add(reMove);
        intList.add(reBank);
        intList.add(reHouse);
        intList.add(reIncome);
        intList.add(reOutcome);
        String strResult = "";
        for( int r = 0;r < intList.size();r++){
            if( intList.get(r) == -1 ){
                strResult = "failure";
            } else {
                strResult = "success";
            }
        }
        Map mapResult = new HashMap();
        mapResult.put("result",strResult);
        return new Gson().toJson(mapResult);
    }
}
