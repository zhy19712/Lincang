package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.DataEnteringServiceImp;
import com.bhidi.lincang.service.DataGetingServiceImp;
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
public class DataGetingController {

    @Autowired
    DataGetingServiceImp dataGetingServiceImp;

    @ResponseBody
    @RequestMapping(value="/dataGeting",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String dataGeting(String fid) {
        Bank bank = dataGetingServiceImp.getBankInfoByFid(fid);
        House house = dataGetingServiceImp.getHouseInfoByFid(fid);
        List<Income> incomeList = dataGetingServiceImp.getIncomeInfosByFid(fid);
        List<Outcome> outcomeList = dataGetingServiceImp.getOutcomeInfosByFid(fid);
        Move move = dataGetingServiceImp.getMoveInfoByFid(fid);
        List<People> peopleList = dataGetingServiceImp.getPeopleInfosByFid(fid);
        //返回结果的map
        Map resultMap = new HashMap();
        resultMap.put("bank",bank);
        resultMap.put("house",house);
        resultMap.put("income",incomeList);
        resultMap.put("outcome",outcomeList);
        resultMap.put("move",move);
        resultMap.put("people",peopleList);
        return new Gson().toJson(resultMap);
    }
}
