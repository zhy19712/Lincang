package com.bhidi.lincang.controller;

import com.bhidi.lincang.service.CapitalFlowServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CapitalFlowController {
    @Autowired
    CapitalFlowServiceImp capitalFlowServiceImp;


    @ResponseBody
    @RequestMapping(value="/submitDataOfCapital",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String submitDataOfCapital(String report_person,String report_quarter,String report_text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String create_time = format.format(now);
        Map<String,String> map = new HashMap();
        map.put("create_time",create_time);
        map.put("report_person",report_person == null?"":report_person);
        map.put("report_quarter",report_quarter == null?"":report_quarter);
        map.put("report_text",report_person == null?"":report_text);
        map.put("status","申请中");
        int result = capitalFlowServiceImp.submitData(map);
        return result+"";
    }
}
