package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.SendFile;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.service.SendFileServiceImp;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SendFileController {
    @Autowired
    SendFileServiceImp sendFileServiceImp;
    /**
     * 发文申请的提交按钮
     * @param request
     * @param session
     * @param sf
     * @param files
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/submitSendFile",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String submitSendFile(HttpServletRequest request, HttpSession session, SendFile sf, @RequestParam("files") MultipartFile[] files){
        //获取当前用户
        User user = (User)session.getAttribute("user");
        //取出来当前用户的姓名
        if( user!=null ){
            sf.setApplicant( user.getName() );
        }
        sf.setStatus("办公室审核处理");
        sf.setApproverdelete("");
        sf.setImplementpersondelete("");

        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("sendFile",sf);
        mapCondition.put("files",files);
        mapCondition.put("request",request);
        //在这里把条件给到文件进行存储，然后把文件的url存储
        Map<String,Object> map = sendFileServiceImp.save(mapCondition);
        String result = new Gson().toJson(map);
        return result;
    }
    /**
     * 列表里的查看功能
     * @param sendFileid
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getSendFileInfoBySendFileId",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getSendFileInfoBySendFileId( String sendFileid ){
        SendFile rf = sendFileServiceImp.getSendFileInfoBySendFileId(sendFileid);
        String result = new Gson().toJson(rf);
        return result;
    }
    /**
     * 办公室的处理之后的提交按钮
     * @param text
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateSendFileInfoBySendFileId",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateSendFileInfoBySendFileId( String text,HttpSession session ){
        JSONObject jsonobject = JSONObject.fromObject(text);
        SendFile sf= (SendFile)JSONObject.toBean(jsonobject,SendFile.class);
        User user = (User)session.getAttribute("user");
        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("sendFile",sf);
        mapCondition.put("user",user);
        int er = 0;
        try {
            er = sendFileServiceImp.updateSendFile(mapCondition);
        } catch (Exception e) {
            er = -1;
        }
        Map<String,String> map = new HashMap<String,String>();
        if( er == -1){
            map.put("result","failure");
        } else {
            map.put("result","success");
        }
        String result = new Gson().toJson(map);
        return result;
    }
}
