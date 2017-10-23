package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.DataEnteringServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
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
        //house的数组
        int houseNum = 0;
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
            if( key.startsWith("data[house]") ){
                houseNum++;
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
        for (String key : map.keySet()) {
            for( int p = 0 ; p < (peopleNum/7);p++ ){
                People people = new People();
                if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                    people.setFid( "KQ"+sdf.format(date) );
                }
                if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                    people.setFid( "BQ"+sdf.format(date) );
                    people.setLocation( map.get("data[place]")[0] );
                }
                people.setReservoir( map.get("data[reservoir]")[0] );
                if( map.get("data[home_infos]["+p+"][name]")[0].equals(map.get("data[householder]")[0]) ) {
                    people.setMaster(1);
                    people.setPhone( map.get("data[tel_number]")[0] );
                } else {
                    people.setMaster(0);
                }
                people.setName( map.get("data[home_infos]["+p+"][name]")[0] );
                people.setPid( map.get("data[home_infos]["+p+"][id]")[0] );
                people.setGender( map.get("data[home_infos]["+p+"][sex]")[0] );
                people.setRace( map.get("data[home_infos]["+p+"][nation]")[0] );
                people.setRelation( map.get("data[home_infos]["+p+"][relation]")[0] );
                people.setEducation( map.get("data[home_infos]["+p+"][cultural]")[0] );
                people.setProfession( map.get("data[home_infos]["+p+"][profession]")[0] );
                people.setHome_size( peopleNum/7 );
                people.setImm_num( peopleNum/7 );
                people.setProp( (map.get("data[prop]")[0]).equals("是")?1:0 );
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
        }
        for (People ple : peopleSet) {
            peopleList.add(ple);
        }
        //存储用户返回值
        int rePeople = 0;
        if (peopleList.size() > 0){
            rePeople = dataEnteringServiceImp.savePerson(peopleList);
        }
        //move信息
        Move move = new Move();
        for (String key : map.keySet()) {
            if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                move.setFid( "KQ"+sdf.format(date) );
            }
            if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                move.setFid( "BQ"+sdf.format(date) );
            }
        }

        //银行信息
        Bank bank = new Bank();
        for (String key : map.keySet()) {
            if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                bank.setFid( "KQ"+sdf.format(date) );
            }
            if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                bank.setFid( "BQ"+sdf.format(date) );
            }
            bank.setAccount_name( map.get("data[bank_user]")[0] );
            bank.setBank_name( map.get("data[bank_name]")[0] );
            bank.setAccount_number( map.get("data[bank_number]")[0] );
        }
        //存储银行返回值
        int reBank= 0;
        if (bank.getFid() != null){
            reBank = dataEnteringServiceImp.saveBank(bank);
        }
        //House信息
        House house = new House();
        for (String key : map.keySet()) {
            if( houseNum > 0 ){
                if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                    house.setFid( "KQ"+sdf.format(date) );
                }
                if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                    house.setFid( "BQ"+sdf.format(date) );
                }
                house.setMain_size( map.get("data[house][main_arear]")[0] );
                house.setMain_structure1( map.get("data[house][main_structure1]")[0] );
                house.setMain_structure2( map.get("data[house][main_structure2]")[0] );
                house.setMain_structure3( map.get("data[house][main_structure3]")[0] );
                house.setMain_structure4( map.get("data[house][main_structure4]")[0] );
                house.setMain_structure5( map.get("data[house][main_easy]")[0] );
                house.setMain_remark( map.get("data[house][main_remark]")[0] );
                house.setSub_size( map.get("data[house][sub_arear]")[0]);
                house.setSub_structure1( map.get("data[house][sub_structure1]")[0] );
                house.setSub_structure2( map.get("data[house][sub_structure2]")[0] );
                house.setSub_structure3( map.get("data[house][sub_structure3]")[0] );
                house.setSub_structure4( map.get("data[house][sub_structure4]")[0] );
                house.setSub_structure5( map.get("data[house][sub_easy]")[0] );
                house.setSub_remark( map.get("data[house][sub_remark]")[0] );
            }
        }
        //存储house返回值
        int reRouse= 0;
        if (house.getFid() != null){
            reRouse = dataEnteringServiceImp.saveHouse(house);
        }
        //一个集合来收集income信息
        List<Income> incomeList = new ArrayList<Income>();
        Set<Income> incomeSet = new HashSet();
        for (String key : map.keySet()) {
            for( int p = 0 ; p < (incomeNum/6);p++ ){
                Income income = new Income();
                if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                    income.setFid( "KQ"+sdf.format(date) );
                }
                if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                    income.setFid( "BQ"+sdf.format(date) );
                }
                income.setIncome_source( map.get("data[money_info]["+p+"][kind]")[0] );
                income.setIncome_cate( map.get("data[money_info]["+p+"][content]")[0] );
                income.setIncome_quantity( Integer.parseInt(map.get("data[money_info]["+p+"][count]")[0]) );
                income.setIncome_unit( Float.parseFloat(map.get("data[money_info]["+p+"][price]")[0]) );
                income.setIncome_sum( Float.parseFloat(map.get("data[money_info]["+p+"][total]")[0]) );
                income.setRemark( map.get("data[money_info]["+p+"][remark]")[0] );
                incomeSet.add(income);
            }
        }
        for(Income income : incomeSet){
            incomeList.add(income);
        }
        //income存储
        int reIncome= 0;
        if (incomeList.size() > 0){
            reIncome = dataEnteringServiceImp.saveIncome(incomeList);
        }
        //一个集合来收集outcome信息
        List<Outcome> outcomeList = new ArrayList<Outcome>();
        Set<Outcome> outcomeSet = new HashSet();
        for (String key : map.keySet()) {
            for( int p = 0 ; p < (outcomeNum/6);p++ ){
                Outcome outcome = new Outcome();
                if( "库区安置登记表".equals( map.get("data[kind]")[0] ) ){
                    outcome.setFid( "KQ"+sdf.format(date) );
                }
                if( "移民搬迁登记表".equals( map.get("data[kind]")[0] ) ){
                    outcome.setFid( "BQ"+sdf.format(date) );
                }
                outcome.setOutcome_source( map.get("data[money_outcome]["+p+"][kind]")[0] );
                outcome.setOutcome_cate( map.get("data[money_outcome]["+p+"][content]")[0] );
                outcome.setOutcome_quantity( Integer.parseInt(map.get("data[money_outcome]["+p+"][count]")[0]) );
                outcome.setOutcome_unit( Float.parseFloat(map.get("data[money_outcome]["+p+"][price]")[0]) );
                outcome.setOutcome_sum( Float.parseFloat(map.get("data[money_outcome]["+p+"][total]")[0]) );
                outcome.setRemark( map.get("data[money_outcome]["+p+"][remark]")[0] );
                outcomeSet.add(outcome);
            }
        }
        for(Outcome outcome : outcomeSet){
            outcomeList.add(outcome);
        }
        //outcome存储
        int reOutcome= 0;
        if (outcomeList.size() > 0){
            reOutcome = dataEnteringServiceImp.saveOutcome(outcomeList);
        }
        return "";
    }
}
