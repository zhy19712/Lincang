package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.CapitalFlow;
import com.bhidi.lincang.service.CapitalFlowServiceImp;
import com.google.gson.Gson;
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
        map.put("status","市局财务科处理中");
        int result = capitalFlowServiceImp.submitData(map);
        return result+"";
    }

    @ResponseBody
    @RequestMapping(value="/getCatipalDataById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getDataById(String id) {
        CapitalFlow cap = capitalFlowServiceImp.getCatipalDataById(id);
        Map<String,CapitalFlow> mapResult = new HashMap();
        mapResult.put("result",cap);
        String resStr =  new Gson().toJson(mapResult);
        return resStr;
    }

    /**
     * 财务科点击了编辑之后的提交按钮
     * @param id
     * @param money_source
     * @param arrival_time
     * @param amount
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/setDataById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String setDataById(String id,
                              String money_source,String arrival_time,String amount) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String finance_time = format.format(now);
        Map<String,String> map = new HashMap();
        map.put("id",id);
        /*map.put("create_time",create_time);
        map.put("report_person",report_person == null?"":report_person);
        map.put("report_quarter",report_quarter == null?"":report_quarter);
        map.put("report_text",report_person == null?"":report_text);*/
        map.put("money_source",money_source);
        map.put("arrival_time",arrival_time);
        map.put("amount",amount);
        map.put("finance_time",finance_time);
        map.put("status","市局规划科处理中");
        int result = capitalFlowServiceImp.setCatipalDataById(map);
        return result+"";
    }

    /**
     * 规划科点击编辑之后的提交
     * @param id
     * @param areas
     * @param text
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/setToAreaDataById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String setDataById(String id,String[] areas,String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String toarea_time = format.format(now);
        Map<String,String> map = new HashMap();
        StringBuffer sb = new StringBuffer();
        for( int i = 0;i < areas.length;i++ ){
            if(i < (areas.length - 1)){
                sb.append(areas[i]+",");
            } else {
                sb.append(areas[i]);
            }
        }
        map.put("id",id);
        map.put("toarea_time",toarea_time);
        map.put("areaname",sb.toString());
        map.put("text",text);
        int result = capitalFlowServiceImp.setCatipalDataById(map);
        return result+"";
    }
}
