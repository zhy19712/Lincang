package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.CapitalFlow;
import com.bhidi.lincang.service.CapitalFlowServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CapitalFlowController {
    @Autowired
    CapitalFlowServiceImp capitalFlowServiceImp;

    /**
     * 提交申请按钮
     * @param report_person
     * @param report_reason
     * @param report_quarter
     * @param report_text
     * @param userstatus
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/submitDataOfCapital",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String submitDataOfCapital(@RequestParam(value="report_person",required=false)String report_person,
                                      @RequestParam(value="report_reason",required=false)String report_reason,
                                      @RequestParam(value="report_quarter",required=false)String report_quarter,
                                      @RequestParam(value="report_text",required=false)String report_text,
                                      @RequestParam(value="userstatus",required=false)String userstatus) {
        //在提交的时候，userstatus就是数据库中的发起人类别，其他的时候就不是了。
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String create_time = format.format(now);
        Map<String,String> map = new HashMap();
        map.put("create_time",create_time);
        map.put("report_person",report_person == null?"":report_person);
        map.put("report_reason",report_reason == null?"":report_reason);
        map.put("report_quarter",report_quarter == null?"":report_quarter);
        map.put("report_text",report_person == null?"":report_text);
        map.put("initiatorclass",userstatus == null?"":userstatus);
        if( "3".equals(userstatus) ){
            map.put("status","市局规划科批复中");
        } else {
            map.put("status","市局财务科处理中");
        }
        int result = capitalFlowServiceImp.submitData(map);
        return result+"";
    }

    /**
     * 查看按钮
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getCatipalDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
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
    @RequestMapping(value="/setDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String setDataById(String id,String money_source,String arrival_time,String amount,
                              @RequestParam(value="dealtext",required=false) String dealtext ){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String finance_time = format.format(now);
        Map<String,String> map = new HashMap();
        map.put("id",id);
        if( money_source != null ){
            map.put("money_source",money_source);
            map.put("arrival_time",arrival_time);
            map.put("amount",amount);
            map.put("finance_time",finance_time);
            map.put("status","市局规划科处理中");
        }
        if( dealtext != null ){
            map.put("dealtext",dealtext);
            map.put("status","区县资金流向录入");
        }

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
    @RequestMapping(value="/setToAreaDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String setDataById(String id,
                              @RequestParam(value="areanames[]",required=false) String[] areas,
                              @RequestParam(value="replytext",required=false) String replytext,
                              @RequestParam(value="text",required=false) String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String toarea_time = format.format(now);
        Map<String,String> map = new HashMap();
        StringBuffer sb = new StringBuffer();
        if( areas != null ){
            for( int i = 0;i < areas.length;i++ ){
                if(i < (areas.length - 1)){
                    sb.append(areas[i]+",");
                } else {
                    sb.append(areas[i]);
                }
            }
        }
        map.put("id",id);
        if( areas != null ){
            map.put("toarea_time",toarea_time);
            map.put("areaname",sb.toString());
            map.put("text",text);
            map.put("status","已通知区县");
        }
        if( replytext != null ){
            map.put("replytext",replytext);
            map.put("status","市局财务科转账中");
        }
        int result = capitalFlowServiceImp.setCatipalDataById(map);
        return result+"";
    }

    /**
     * 区县点击了编辑之后的保存按钮
     * @param id
     * @param capitalflowinstruction
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/countySaveDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String countySaveDataById( String id,String capitalflowinstruction ){
        Map<String,String> map = new HashMap();
        map.put("id",id);
        if( capitalflowinstruction != null ){
            map.put("capitalflowinstruction",capitalflowinstruction);
        }
        int result = capitalFlowServiceImp.setCatipalDataById(map);
        return result+"";
    }
    /**
     * 区县点击了编辑之后的提交按钮
     * @param id
     * @param capitalflowinstruction
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/countySubmitDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String countySubmitDataById( String id,String capitalflowinstruction ){
        Map<String,String> map = new HashMap();
        map.put("id",id);
        if( capitalflowinstruction != null ){
            map.put("capitalflowinstruction",capitalflowinstruction);
            map.put("status","区县资金流向明细");
        }
        int result = capitalFlowServiceImp.setCatipalDataById(map);
        return result+"";
    }
}
