package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.People;
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
    @RequestMapping(value="BasicInfoOfFamily",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String BasicInfoOfFamily(String name){
        List<People> result = new ArrayList<People>();
        result = familyInfoServiceImp.BasicInfoOfFamily(name);
        Gson gson = new Gson();
        String resultStr = gson.toJson(result);
        return resultStr;
    }
}
