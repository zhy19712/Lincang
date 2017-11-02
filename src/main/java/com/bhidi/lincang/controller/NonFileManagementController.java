package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.NonFileManagement;
import com.bhidi.lincang.bean.ReceiveFile;
import com.bhidi.lincang.bean.SendFile;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.service.NonFileManagementServiceImp;
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
public class NonFileManagementController {
    @Autowired
    NonFileManagementServiceImp nonFileManagementServiceImp;

    /**
     * 第一步提交
     * @param request
     * @param session
     * @param nfm
     * @param files
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/submitNonFileManagement",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String submitNonFileManagement(HttpServletRequest request, HttpSession session, NonFileManagement nfm, @RequestParam("files") MultipartFile[] files){
        //获取当前用户
        User user = (User)session.getAttribute("user");
        //取出来当前用户的账号名存储进NonFileManagement
        if( user!=null ){
            nfm.setSubmitperson( user.getName() );
        }
        nfm.setStatus("办公室签收并处理");

        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("nonfilemanagement",nfm);
        mapCondition.put("files",files);
        mapCondition.put("request",request);
        //在这里把条件给到文件进行存储，然后把文件的url存储
        Map<String,Object> map = nonFileManagementServiceImp.submit(mapCondition);
        String result = new Gson().toJson(map);
        return result;
    }
    /**
     * 列表里的查看功能
     * @param nonfileid
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getNonFileManagementInfoByNonFileId",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getNonFileManagementInfoByNonFileId( String nonfileid ){
        NonFileManagement nfm = nonFileManagementServiceImp.getNonFileManagementInfoByNonFileId(nonfileid);
        String result = new Gson().toJson(nfm);
        return result;
    }
    /**
     * 办公室的处理之后的提交按钮
     * @param text
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateNonFileManagementInfo",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateNonFileManagementInfo( String text,HttpSession session ){
        JSONObject jsonobject = JSONObject.fromObject(text);
        NonFileManagement nfm= (NonFileManagement)JSONObject.toBean(jsonobject,NonFileManagement.class);
        User user = (User)session.getAttribute("user");
        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("nonfilemanagement",nfm);
        mapCondition.put("user",user);
        int er = 0;
        try {
            er = nonFileManagementServiceImp.updateNonFileManagement(mapCondition);
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
