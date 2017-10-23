package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.People;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class DataEnteringController {

    @Autowired
    DataEnteringServiceImp dataEnteringServiceImp;

    @ResponseBody
    @RequestMapping(value="/dataEntering",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String dataEntering(HttpServletRequest request) {
        //这是前端传过来的数据
        Map<String,String[]> map = request.getParameterMap();
        //设置一个数量来接受前端写了几个用户
        int peopleNum = 0;
        for (String key : map.keySet()) {
            if( key.startsWith("data[home_infos]") ){
                peopleNum++;
            }
            for(String value : map.get(key)){
                System.out.println("key= "+ key + " and value= " + value );
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date =  new Date();
        //一个集合来收集用户信息
        List<People> peopleList = new ArrayList<People>();
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
                    //手机号码没有传递过来
                    people.setPhone("");
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
                //致贫原因有问题
                people.setPoor_reason("");
                people.setInterviewer( map.get("data[inquirer]")[0] );
                people.setInterviewee( map.get("data[respondent]")[0] );
                people.setCreated_at( map.get("data[time]")[0] );
                peopleList.add(people);
            }
        }



        System.out.println(peopleList.get(0));
        System.out.println(peopleList.get(1));
        return "";
    }
}
