package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.ReceiveFileServiceImp;
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
            rfa.setReveivereregisterpersonname( user.getUsername() );
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

    /**
     * 列表里的查看功能
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getReceiveFileInfoById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getReceiveFileInfoById( String id ){
        ReceiveFile rf = receiveFileServiceImp.getReceiveFileInfoById(id);
        String result = new Gson().toJson(rf);
        return result;
    }
    /**
     * 第二步，选择模板，和各个步骤的处理人
     */
    @ResponseBody
    @RequestMapping(value="/saveReceiveFileModelInfo",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String saveReceiveFileModelInfo(HttpSession session,String receivefileid,String modelname,
                                       String people_list,
                                       String text){
        JSONObject jsonobject = JSONObject.fromObject(people_list);
        People_List pl= (People_List)JSONObject.toBean(jsonobject,People_List.class);
        String modeltype = "";
        //根据modelname来判断给数据库中存储的名字
        if( modelname.equals("直接处理") ){
            modeltype = "model_zhijiechuli";
            //先将处理人信息和模板类型存储进receivefile表，在选择对应的表存储对应的信息
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            //判断两个科室的名字
            rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            rf.setDepartment1person(pl.getDepartment1());
            //rf.setDepartment2person(pl.getDepartment2());
            rf.setDepartment1persondelete(pl.getDepartment1());
            //rf.setDepartment2persondelete(pl.getDepartment2());
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete(pl.getBranch_leader());
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete(pl.getMain_leader());
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete(pl.getTransactor());
            //去更新receivefile表
            int er = receiveFileServiceImp.updateReceiveFile(rf);
            //接下来就是更新model_zhijiechuli表
            Model_Zhijiechuli meme = new Model_Zhijiechuli();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            Model_Zhijiechuli me= (Model_Zhijiechuli) JSONObject.toBean(object,Model_Zhijiechuli.class);
            meme.setFilename(me.getFilename());
            meme.setReceivefileid(receivefileid);
            meme.setReceivefilenum(me.getReceivefilenum());
            meme.setComefiledepartment(me.getComefiledepartment());
            meme.setComefilenum(me.getComefilenum());
            meme.setUrgency(me.getUrgency());
            meme.setSecret(me.getSecret());
            meme.setCopys(me.getCopys());
            meme.setCopys(me.getCopys());
            meme.setFiletitle(me.getFiletitle());
            meme.setSuggestion(me.getSuggestion());
            meme.setMainleader(pl.getMain_leader());
            meme.setBranchleader(pl.getBranch_leader());
            //meme.setDepartment(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //meme.setDepartmentperson(pl.getDepartment1());
            //meme.setDepartmentadvice(me.getDepartmentadvice());
            //meme.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            //meme.setDepartment2person(pl.getDepartment2());
            //meme.setDepartment2advice(me.getDepartment2advice());
            meme.setMainleaderinstruction(me.getMainleaderinstruction());
            meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            meme.setResult(me.getResult());
            meme.setImplementperson(pl.getTransactor());
            //去插入model_erkeshi表
            int mer = receiveFileServiceImp.insertModelZhijiechuli(meme);
        }
        if( modelname.equals("文件拟办单") ){
            modeltype = "model_wenjianniban";
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            //判断两个科室的名字
            //rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            //rf.setDepartment1person(pl.getDepartment1());
            //rf.setDepartment2person(pl.getDepartment2());
            //rf.setDepartment1persondelete(pl.getDepartment1());
            //rf.setDepartment2persondelete(pl.getDepartment2());
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete(pl.getBranch_leader());
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete(pl.getMain_leader());
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete(pl.getTransactor());
            //去更新receivefile表
            int er = receiveFileServiceImp.updateReceiveFile(rf);
            //接下来就是更新model_wenjianniban表
            Model_Wenjianniban meme = new Model_Wenjianniban();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            Model_Wenjianniban me= (Model_Wenjianniban) JSONObject.toBean(object,Model_Wenjianniban.class);
            meme.setFilename(me.getFilename());
            meme.setReceivefileid(receivefileid);
            meme.setDispatchfiledepartment(me.getDispatchfiledepartment());
            meme.setFilenum(me.getFilenum());
            meme.setReceivefileregisterid(me.getReceivefileregisterid());
            meme.setReceivefiledate(me.getReceivefiledate());
            meme.setFiletitle(me.getFiletitle());
            meme.setSuggestion(me.getSuggestion());
            meme.setMainleader(pl.getMain_leader());
            meme.setBranchleader(pl.getBranch_leader());
            //meme.setDepartment(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //meme.setDepartmentperson(pl.getDepartment1());
            //meme.setDepartmentadvice(me.getDepartmentadvice());
            //meme.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            //meme.setDepartment2person(pl.getDepartment2());
            //meme.setDepartment2advice(me.getDepartment2advice());
            meme.setMainleaderinstruction(me.getMainleaderinstruction());
            meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            meme.setResult(me.getResult());
            meme.setImplementperson(pl.getTransactor());
            //去插入model_erkeshi表
            int mer = receiveFileServiceImp.insertModelWenjiannibandan(meme);
        }
        if( modelname.equals("一科室提意见") ){
            modeltype = "model_yikeshi";
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            //判断两个科室的名字
            rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            rf.setDepartment1person(pl.getDepartment1());
            //rf.setDepartment2person(pl.getDepartment2());
            rf.setDepartment1persondelete(pl.getDepartment1());
            //rf.setDepartment2persondelete(pl.getDepartment2());
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete(pl.getBranch_leader());
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete(pl.getMain_leader());
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete(pl.getTransactor());
            //去更新receivefile表
            int er = receiveFileServiceImp.updateReceiveFile(rf);
            //接下来就是更新model_erkeshi表
            Model_Yikeshi meme = new Model_Yikeshi();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            Model_Yikeshi me= (Model_Yikeshi) JSONObject.toBean(object,Model_Yikeshi.class);
            meme.setFilename(me.getFilename());
            meme.setReceivefileid(receivefileid);
            meme.setReceivefilenum(me.getReceivefilenum());
            meme.setComefiledepartment(me.getComefiledepartment());
            meme.setComefilenum(me.getComefilenum());
            meme.setUrgency(me.getUrgency());
            meme.setSecret(me.getSecret());
            meme.setCopys(me.getCopys());
            meme.setCopys(me.getCopys());
            meme.setFiletitle(me.getFiletitle());
            meme.setSuggestion(me.getSuggestion());
            meme.setMainleader(pl.getMain_leader());
            meme.setBranchleader(pl.getBranch_leader());
            meme.setDepartment(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            meme.setDepartmentperson(pl.getDepartment1());
            meme.setDepartmentadvice(me.getDepartmentadvice());
            //meme.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            //meme.setDepartment2person(pl.getDepartment2());
            //meme.setDepartment2advice(me.getDepartment2advice());
            meme.setMainleaderinstruction(me.getMainleaderinstruction());
            meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            meme.setResult(me.getResult());
            meme.setImplementperson(pl.getTransactor());
            //去插入model_erkeshi表
            int mer = receiveFileServiceImp.insertModelYikeshi(meme);
        }
        if( modelname.equals("两科室提意见") ){
            modeltype = "model_erkeshi";
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            //判断两个科室的名字
            rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            rf.setDepartment1person(pl.getDepartment1());
            rf.setDepartment2person(pl.getDepartment2());
            rf.setDepartment1persondelete(pl.getDepartment1());
            rf.setDepartment2persondelete(pl.getDepartment2());
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete(pl.getBranch_leader());
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete(pl.getMain_leader());
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete(pl.getTransactor());
            //去更新receivefile表
            int er = receiveFileServiceImp.updateReceiveFile(rf);
            //接下来就是更新model_erkeshi表
            Model_Erkeshi meme = new Model_Erkeshi();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            Model_Erkeshi me= (Model_Erkeshi) JSONObject.toBean(object,Model_Erkeshi.class);
            meme.setFilename(me.getFilename());
            meme.setReceivefileid(receivefileid);
            meme.setReceivefilenum(me.getReceivefilenum());
            meme.setComefiledepartment(me.getComefiledepartment());
            meme.setComefilenum(me.getComefilenum());
            meme.setUrgency(me.getUrgency());
            meme.setSecret(me.getSecret());
            meme.setCopys(me.getCopys());
            meme.setCopys(me.getCopys());
            meme.setFiletitle(me.getFiletitle());
            meme.setSuggestion(me.getSuggestion());
            meme.setMainleader(pl.getMain_leader());
            meme.setBranchleader(pl.getBranch_leader());
            meme.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            meme.setDepartment1person(pl.getDepartment1());
            meme.setDepartment1advice(me.getDepartment1advice());
            meme.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            meme.setDepartment2person(pl.getDepartment2());
            meme.setDepartment2advice(me.getDepartment2advice());
            meme.setMainleaderinstruction(me.getMainleaderinstruction());
            meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            meme.setResult(me.getResult());
            meme.setImplementperson(pl.getTransactor());
            //去插入model_erkeshi表
            int mer = receiveFileServiceImp.insertModelErkeshi(meme);
        }
        //String result = new Gson().toJson(rf);
        return "";
    }
}
