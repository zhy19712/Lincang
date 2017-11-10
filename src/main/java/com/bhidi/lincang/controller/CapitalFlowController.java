package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.CapitalFlow;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.service.CapitalFlowServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/submitDataOfCapital",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String submitDataOfCapital(HttpServletRequest request, HttpSession session, CapitalFlow cf, @RequestParam("files") MultipartFile[] files) {
        //在提交的时候，userstatus就是数据库中的发起人类别，其他的时候就不是了。
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String create_time = format.format(now);
        Map<String,String> map = new HashMap();
        map.put("create_time",create_time);
        map.put("report_person",report_person == null?"":report_person);
        map.put("report_reason",report_reason == null?"":report_reason);
        map.put("report_quarter",report_quarter == null?"":report_quarter);
        map.put("report_text",report_person == null?"":report_text);
        map.put("title",title == null?"":title);
        //规划科申请人
        map.put("guihuakeshenqing",username == null?"":username);
        //区县申请人
        map.put("quxianshenqingren",username == null?"":username);
        map.put("initiatorclass",userstatus == null?"":userstatus);
        if( "3".equals(userstatus) ){
            map.put("status","市局规划科批复中");

        } else {
            map.put("status","市局财务科处理中");
        }
        int result = capitalFlowServiceImp.submitData(map);
        return result+"";*/
        //获取当前用户
        User user = (User)session.getAttribute("user");
        //取出来当前用户的姓名
        if( user!=null ){
            cf.setGuihuakeshenqingperson( user.getName() );
        }
        cf.setStatus("市局财务科办理");
        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("capitalFlow",cf);
        mapCondition.put("files",files);
        mapCondition.put("request",request);
        //在这里把条件给到文件进行存储，然后把文件的url存储
        Map<String,Object> map = capitalFlowServiceImp.saveCapitalFlow(mapCondition);
        String result = new Gson().toJson(map);
        return result;
    }
    /**
     * 查看按钮
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getCapitalDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String getCapitalDataById(String id) {
        CapitalFlow cap = capitalFlowServiceImp.getCapitalDataById(id);
        Map<String,CapitalFlow> mapResult = new HashMap();
        mapResult.put("result",cap);
        String resStr =  new Gson().toJson(mapResult);
        return resStr;
    }
    /**
     * 市局提交的申请---财务科编辑的提交
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/shiJuSubmit",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String shiJuSubmit(HttpServletRequest request, HttpSession session, CapitalFlow cf, @RequestParam("files") MultipartFile[] files) {
        //获取当前用户
        User user = (User)session.getAttribute("user");
        //取出来当前用户的姓名
        if( user!=null ){
            cf.setCaiwuchuliren( user.getName() );
        }
        cf.setStatus("市局规划科通知区县");
        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("capitalFlow",cf);
        mapCondition.put("files",files);
        mapCondition.put("request",request);
        //在这里把条件给到文件进行存储，然后把文件的url存储
        Map<String,Object> map = capitalFlowServiceImp.shiJuSubmit(mapCondition);
        String result = new Gson().toJson(map);
        return result;
    }


    /**
     * 区县提交的申请---财务科点击了编辑之后的提交按钮
     * @param id
     * @param money_source
     * @param arrival_time
     * @param amount
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/setDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String setDataById(String id,String money_source,String arrival_time,String amount,
                              @RequestParam(value="dealtext",required=false) String dealtext,
                              @RequestParam(value="username",required=false) String username){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String finance_time = format.format(now);
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        if( money_source != null ){
            map.put("money_source",money_source);
            map.put("arrival_time",arrival_time);
            map.put("amount",amount);
            map.put("finance_time",finance_time);
            map.put("status","市局规划科处理中");
            map.put("caiwuchuliren",username);
        }
        if( dealtext != null ){
            map.put("dealtext",dealtext);
            map.put("caiwuzhuanzhangren",username);
            map.put("status","区县资金流向录入");

        }
        int i =0;
        try {
            i = capitalFlowServiceImp.setCatipalDataById(map);
        } catch (Exception e) {
            i = -1;
        }
        Map<String,Object> mapResult = new HashMap<String,Object>();
        if( i==-1){
            mapResult.put("result","failure");
        } else {
            mapResult.put("result","success");
        }
        String result = new Gson().toJson(mapResult);
        return result;
    }

    /**
     * 规划科点击编辑之后的提交
     * @param id
     * @param text
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/setToAreaDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String setToAreaDataById(@RequestParam(value="id",required=false) int id,
                              @RequestParam(value="areaname",required=false) String areaname,
                              @RequestParam(value="text",required=false) String text,

                              @RequestParam(value="replytext",required=false) String replytext,
                              HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = (User)request.getSession().getAttribute("user");
        Date now = new Date();
        //String toarea_time = format.format(now);
        Map<String,Object> map = new HashMap();
        /*StringBuffer sb = new StringBuffer();
        if( areas != null ){
            for( int i = 0;i < areas.length;i++ ){
                if(i < (areas.length - 1)){
                    sb.append(areas[i]+",");
                } else {
                    sb.append(areas[i]);
                }
            }
        }*/
        map.put("id",id);
        map.put("guihuakechulitime",format.format(now));
        map.put("areaname",areaname==null?"":areaname);
        map.put("text",text==null?"":text);
        map.put("status","结束");
        if( user!=null ){
            map.put("guihuachuliren",user.getName());
        }


        if( replytext != null ){
            map.put("replytext",replytext);
            map.put("status","市局财务科转账中");
            //map.put("guihuapifuren",username);
        }
        int i =0;
        try {
            i = capitalFlowServiceImp.setCatipalDataById(map);
        } catch (Exception e) {
            i = -1;
        }
        Map<String,Object> mapResult = new HashMap<String,Object>();
        if( i==-1){
            mapResult.put("result","failure");
        } else {
            mapResult.put("result","success");
        }
        String result = new Gson().toJson(mapResult);
        return result;
    }

    /**
     * 区县点击了编辑之后的保存按钮
     * @param id
     * @param capitalflowinstruction
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/countySaveDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String countySaveDataById( String id,String capitalflowinstruction,
                                      @RequestParam(value="username",required=false)String username){
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("quxianbaocunren",username);
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
    public String countySubmitDataById( String id,String capitalflowinstruction,
                                        @RequestParam(value="username",required=false)String username){
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("quxiantijiaoren",username);
        if( capitalflowinstruction != null ){
            map.put("capitalflowinstruction",capitalflowinstruction);
            map.put("status","区县资金流向明细");
        }
        int result = capitalFlowServiceImp.setCatipalDataById(map);
        return result+"";
    }
}
