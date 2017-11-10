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
     * 市局提交申请按钮
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/submitDataOfCapital",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String submitDataOfCapital(HttpServletRequest request, HttpSession session, CapitalFlow cf, @RequestParam("files") MultipartFile[] files) {
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
     * 区县提交申请按钮
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/quxianSubmitDataOfCapital",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String quxianSubmitDataOfCapital(HttpServletRequest request, HttpSession session, CapitalFlow cf, @RequestParam("files") MultipartFile[] files) {
        //获取当前用户
        User user = (User)session.getAttribute("user");
        //取出来当前用户的姓名
        if( user!=null ){
            cf.setGuihuakeshenqingperson( user.getName() );
            cf.setShenqingrendept( user.getDept() );
        }
        cf.setStatus("市局规划科批复");
        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("capitalFlow",cf);
        mapCondition.put("files",files);
        mapCondition.put("request",request);
        //在这里把条件给到文件进行存储，然后把文件的url存储
        Map<String,Object> map = capitalFlowServiceImp.quxianSaveCapitalFlow(mapCondition);
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
     * 区县提交的申请---规划科批复---点击了编辑之后的提交按钮
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/quxianGuiHuaSetDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String quxianGuiHuaSetDataById(String id,HttpServletRequest request,
                              @RequestParam(value="replytext",required=false) String replytext){
        User user = (User)request.getSession().getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String finance_time = format.format(now);
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("replytext",replytext==null?"":replytext);
        map.put("guihuapifuren",user.getName());
        map.put("guihuapifutime",format.format(now));
        map.put("status","市局财务科处置办理");
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
     * 区县提交的申请---财务科处置办理---点击了编辑之后的提交按钮
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/quxianCaiWuSetDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String quxianCaiWuSetDataById(String id,HttpServletRequest request,
                                          @RequestParam(value="dealtext",required=false) String dealtext){
        User user = (User)request.getSession().getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("dealtext",dealtext==null?"":dealtext);
        map.put("caiwuzhuanzhangren",user.getName());
        map.put("caiwuzhuangzhangtime",format.format(now));
        map.put("status","区县资金流向记录");
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
     * 区县提交的申请---保存按钮
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/quxianSaveSetDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String quxianSaveSetDataById(String id,HttpServletRequest request,
                                         @RequestParam(value="capitalflowinstruction",required=false) String capitalflowinstruction){
        User user = (User)request.getSession().getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("capitalflowinstruction",capitalflowinstruction==null?"":capitalflowinstruction);
        CapitalFlow cap = capitalFlowServiceImp.getCapitalDataById(id);
        if(cap.getQuxianbaocunren()!=null & cap.getQuxianbaocunren().length()>0){
            map.put("quxianbaocunren",cap.getQuxianbaocunren()+","+user.getName());
        } else {
            map.put("quxianbaocunren",user.getName());
        }
        if(cap.getQuxianbaocuntime()!=null & cap.getQuxianbaocuntime().length()>0){
            map.put("quxianbaocuntime",cap.getQuxianbaocuntime()+","+format.format(now));
        } else {
            map.put("quxianbaocuntime",format.format(now));
        }
        map.put("status","区县资金流向记录");
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
     * 区县提交的申请---提交按钮
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/quxianSubmitSetDataById",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String quxianSubmitSetDataById(String id,HttpServletRequest request,
                                        @RequestParam(value="capitalflowinstruction",required=false) String capitalflowinstruction){
        User user = (User)request.getSession().getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Map<String,Object> map = new HashMap();
        map.put("id",id);
        map.put("capitalflowinstruction",capitalflowinstruction==null?"":capitalflowinstruction);
        map.put("quxiantijiaoren",user.getName());
        map.put("quxiantijiaotime",format.format(now));
        map.put("status","结束");
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
    public String setToAreaDataById(@RequestParam(value="id",required=false) String id,
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
