package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.People;
import com.bhidi.lincang.bean.PeopleMore;
import com.bhidi.lincang.service.FamilyInfoServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FamilyInfoController {
    @Autowired
    FamilyInfoServiceImp familyInfoServiceImp;

    /**
     * 根据传递过来的县级别的单位名称显示家庭基本信息
     * @param name
     */
    @ResponseBody
    @RequestMapping(value="BasicInfoOfFamilyss",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String BasicInfoOfFamily(String name){
        List<People> result = new ArrayList<People>();
        result = familyInfoServiceImp.BasicInfoOfFamily(name);
        Gson gson = new Gson();
        String resultStr = gson.toJson(result);
        return resultStr;
    }
    /**
     * 根据传递过来的FID显示家庭成员信息
     * @param fid
     */
    @ResponseBody
    @RequestMapping(value="FamilyInfoByFidss",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String FamilyInfoByFid(String fid){
        List<People> result = new ArrayList<People>();
        result = familyInfoServiceImp.queryFamilyInfoByFid(fid);
        Gson gson = new Gson();
        String resultStr = gson.toJson(result);
        return resultStr;
    }
    /**
     * 根据传递过来的FID显示家庭详细信息
     * @param fid
     */
    @ResponseBody
    @RequestMapping(value="FamilyDetailByFid",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String FamilyDetailByFid(String fid){
        PeopleMore result = familyInfoServiceImp.queryFamilyDetailByFid(fid);
        Gson gson = new Gson();
        String resultStr = gson.toJson(result);
        return resultStr;
    }

}
