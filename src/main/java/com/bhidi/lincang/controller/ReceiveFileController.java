package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.ReceiveFileServiceImp;
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
import java.util.*;

@Controller
public class ReceiveFileController {

    @Autowired
    ReceiveFileServiceImp receiveFileServiceImp;

    /**
     * 第一个流程的收文登记
     * @param request
     * @param session
     * @param rfa
     * @param files
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/reveiceFileRegistration",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String reveiceFileRegistration(HttpServletRequest request, HttpSession session, ReceiveFile rfa, @RequestParam("files") MultipartFile[] files){
        //获取当前用户
        User user = (User)session.getAttribute("user");
        //取出来当前用户的账号名存储进ReceiveFileAhead
        if( user!=null ){
            rfa.setReveivereregisterpersonaccount( user.getUsername() );
        }
        rfa.setStatus("办公室处理文件");

        Map<String,Object> mapCondition = new HashMap();
        mapCondition.put("receiveFileAhead",rfa);
        mapCondition.put("files",files);
        mapCondition.put("request",request);
        //在这里把条件给到文件进行存储，然后把文件的url存储
        Map<String,Object> map = receiveFileServiceImp.save(mapCondition);
        String result = new Gson().toJson(map);
        return result;
    }
    @ResponseBody
    @RequestMapping(value="/getReceiveFileInfoById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getReceiveFileInfoById( String id ){
        ReceiveFile rf = receiveFileServiceImp.getReceiveFileInfoById(id);
        String result = new Gson().toJson(rf);
        return result;
    }
}
