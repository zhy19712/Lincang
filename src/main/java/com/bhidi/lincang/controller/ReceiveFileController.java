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
import java.text.SimpleDateFormat;
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
        //取出来当前用户的账号名存储进ReceiveFile
        if( user!=null ){
            rfa.setReveivereregisterpersonname( user.getName() );
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
        int er =0;
        int mer =0;
        String modeltype = "";
        //根据modelname来判断给数据库中存储的名字
        if( modelname.equals("直接处理") ){
            modeltype = "model_zhijiechuli";
            //先将处理人信息和模板类型存储进receivefile表，在选择对应的表存储对应的信息
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("分管领导签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rf.setModelchoicetime(sdf.format(now));
            //判断两个科室的名字
            //rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            //rf.setDepartment1person(pl.getDepartment1());
            //rf.setDepartment2person(pl.getDepartment2());
            //rf.setDepartment1persondelete(pl.getDepartment1());
            //rf.setDepartment2persondelete(pl.getDepartment2());
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete("");
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete("");
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete("");
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                er = -1;
            }
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
            try {
                mer = receiveFileServiceImp.insertModelZhijiechuli(meme);
            } catch (Exception e) {
                mer = -1;
            }
        }
        if( modelname.equals("文件拟办单") ){
            modeltype = "model_wenjianniban";
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("分管领导签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rf.setModelchoicetime(sdf.format(now));
            //判断两个科室的名字
            //rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            //rf.setDepartment1person(pl.getDepartment1());
            //rf.setDepartment2person(pl.getDepartment2());
            //rf.setDepartment1persondelete(pl.getDepartment1());
            //rf.setDepartment2persondelete(pl.getDepartment2());
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete("");
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete("");
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete("");
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                er = -1;
            }
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
            try {
                mer = receiveFileServiceImp.insertModelWenjiannibandan(meme);
            } catch (Exception e) {
                mer = -1;
            }
        }
        if( modelname.equals("一科室提意见") ){
            modeltype = "model_yikeshi";
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("科室签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rf.setModelchoicetime(sdf.format(now));
            //判断两个科室的名字
            rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            //rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            rf.setDepartment1person(pl.getDepartment1());
            //rf.setDepartment2person(pl.getDepartment2());
            rf.setDepartment1persondelete("");
            //rf.setDepartment2persondelete(pl.getDepartment2());
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete("");
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete("");
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete("");
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                er = -1;
            }
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
            try {
                mer = receiveFileServiceImp.insertModelYikeshi(meme);
            } catch (Exception e) {
                mer = -1;
            }
        }
        if( modelname.equals("两科室提意见") ){
            modeltype = "model_erkeshi";
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileid);
            rf.setStatus("科室签批");
            rf.setModeltype(modeltype);
            rf.setModelchoicename(((User)session.getAttribute("user")).getName());
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rf.setModelchoicetime(sdf.format(now));
            //判断两个科室的名字
            rf.setDepartment1name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment1()!=null?pl.getDepartment1().split(",")[0]:""));
            rf.setDepartment2name(receiveFileServiceImp.getDepartmentNameByName(pl.getDepartment2()!=null?pl.getDepartment2().split(",")[0]:""));
            rf.setDepartment1person(pl.getDepartment1());
            rf.setDepartment2person(pl.getDepartment2());
            rf.setDepartment1persondelete("");
            rf.setDepartment2persondelete("");
            rf.setFenguanname(pl.getBranch_leader());
            rf.setFenguannamedelete("");
            rf.setZhuguanname(pl.getMain_leader());
            rf.setZhuguannamedelete("");
            rf.setImplementperson(pl.getTransactor());
            rf.setImplementpersondelete("");
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                er = -1;
            }
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
            try {
                mer = receiveFileServiceImp.insertModelErkeshi(meme);
            } catch (Exception e) {
                mer = -1;
            }
        }
        Map<String,String> map = new HashMap<String,String>();
        if( mer == -1 || er == -1){
            map.put("result","failure");
        } else {
            map.put("result","success");
        }
        String result = new Gson().toJson(map);
        return result;
    }
    /**
     * 模板选择完成之后的查看
     */
    @ResponseBody
    @RequestMapping(value="/getReceiveFileAndModelInfo",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getReceiveFileAndModelInfo(String receivefileid){
        ReceiveFile rf = receiveFileServiceImp.getReceiveFileInfoById(receivefileid);
        String modeltype = rf.getModeltype();
        Map<String,Object> mapResult = new HashMap<String,Object>();
        if(modeltype.equals("model_zhijiechuli")){
            rf.setModeltype("直接处理");
            Model_Zhijiechuli mz = receiveFileServiceImp.getModelZhijiechuliInfoById(receivefileid);
            mapResult.put("model",mz!=null?mz:"");
        }
        if(modeltype.equals("model_wenjianniban")){
            rf.setModeltype("文件拟办单");
            Model_Wenjianniban mw = receiveFileServiceImp.getModelWenjiannibanInfoById(receivefileid);
            mapResult.put("model",mw!=null?mw:"");
        }
        if(modeltype.equals("model_yikeshi")){
            rf.setModeltype("一科室提意见");
            Model_Yikeshi my = receiveFileServiceImp.getModelYikeshiInfoById(receivefileid);
            mapResult.put("model",my!=null?my:"");
        }
        if(modeltype.equals("model_erkeshi")){
            rf.setModeltype("两科室提意见");
            Model_Erkeshi me = receiveFileServiceImp.getModelErkeshiInfoById(receivefileid);
            mapResult.put("model",me!=null?me:"");
        }
        mapResult.put("ReceiveFile",rf!=null?rf:"");
        String result = new Gson().toJson(mapResult);
        return result;
    }
    /**
     * 第四步，模板选择之后点击编辑之后的提交
     */
    @ResponseBody
    @RequestMapping(value="/updateReceiveFileAndModelInfo",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateReceiveFileAndModelInfo(HttpSession session,String receivedata,String text){
        JSONObject objectrf = JSONObject.fromObject(receivedata);
        ReceiveFile receivefileinfo = (ReceiveFile) JSONObject.toBean(objectrf,ReceiveFile.class);
        //取到数据库中的数据
        ReceiveFile rfRource = receiveFileServiceImp.getReceiveFileInfoById(receivefileinfo.getReceivefileid());
        //取到当前用户的角色
        User user = (User)session.getAttribute("user");
        String name = user.getName();
        String role  = user.getRoleList().get(0);
        String dept = user.getDept();
        int er =0;
        int mer =0;
        String modeltype = "";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //根据modelname来判断给数据库中存储的名字
        if( receivefileinfo.getModeltype().equals("直接处理") ){
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileinfo.getReceivefileid());
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                String fenguanname = receivefileinfo.getFenguanname();
                String fenguannamedelete = receivefileinfo.getFenguannamedelete();
                if(fenguannamedelete.equals("")){
                    rf.setFenguannamedelete(user.getName());
                    rf.setFenguantime(sdf.format(now));
                } else {
                    rf.setFenguannamedelete(fenguannamedelete+","+user.getName());
                    rf.setFenguantime(rfRource.getFenguantime()+","+sdf.format(now));
                }
                if( fenguanname.split(",").length == (rf.getFenguannamedelete().split(",").length) ){
                    rf.setStatus("主管领导签批");
                } else {
                    rf.setStatus("分管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                String zhuguanname = receivefileinfo.getZhuguanname();
                String zhuguannamedelete = receivefileinfo.getZhuguannamedelete();
                if(zhuguannamedelete.equals("")){
                    rf.setZhuguannamedelete(user.getName());
                    rf.setZhuguantime(sdf.format(now));
                } else {
                    rf.setZhuguannamedelete(zhuguannamedelete +","+user.getName());
                    rf.setZhuguantime(rfRource.getZhuguantime()+","+sdf.format(now));
                }

                if(zhuguanname.split(",").length == ( rf.getZhuguannamedelete().split(",").length) ){
                    rf.setStatus("处理处置");
                } else {
                    rf.setStatus("主管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                String implementperson = receivefileinfo.getImplementperson();
                String implementpersondelete = receivefileinfo.getImplementpersondelete();
                if( implementpersondelete.equals("") ){
                    rf.setImplementpersondelete(user.getName());
                    rf.setImplementtime(sdf.format(now));
                } else {
                    rf.setImplementpersondelete(implementpersondelete+","+user.getName());
                    rf.setImplementtime(rfRource.getImplementtime()+","+sdf.format(now));
                }
                if(implementperson.split(",").length == (rf.getImplementpersondelete().split(",").length)){
                    rf.setStatus("办公室归档");
                } else {
                    rf.setStatus("处理处置");
                }
            }
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                e.printStackTrace();
                er = -1;
            }
            //接下来就是更新model_zhijiechuli表
            Model_Zhijiechuli meme = new Model_Zhijiechuli();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            ModelText me= (ModelText) JSONObject.toBean(object,ModelText.class);
            meme.setReceivefileid(receivefileinfo.getReceivefileid());
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                meme.setMainleaderinstruction(me.getMainleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                meme.setResult(me.getResult());
            }
            //去修改model_zhijiechuli表
            try {
                mer = receiveFileServiceImp.updateModelZhijiechuli(meme);
            } catch (Exception e) {
                e.printStackTrace();
                mer = -1;
            }
        }




        if( receivefileinfo.getModeltype().equals("文件拟办单") ){
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileinfo.getReceivefileid());
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                String fenguanname = receivefileinfo.getFenguanname();
                String fenguannamedelete = receivefileinfo.getFenguannamedelete();
                if(fenguannamedelete.equals("")){
                    rf.setFenguannamedelete(user.getName());
                    rf.setFenguantime(sdf.format(now));
                } else {
                    rf.setFenguannamedelete(fenguannamedelete+","+user.getName());
                    rf.setFenguantime(rfRource.getFenguantime()+","+sdf.format(now));
                }
                if( fenguanname.split(",").length == rf.getFenguannamedelete().split(",").length ){
                    rf.setStatus("主管领导签批");
                } else {
                    rf.setStatus("分管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                String zhuguanname = receivefileinfo.getZhuguanname();
                String zhuguannamedelete = receivefileinfo.getZhuguannamedelete();
                if(zhuguannamedelete.equals("")){
                    rf.setZhuguannamedelete(user.getName());
                    rf.setZhuguantime(sdf.format(now));
                } else {
                    rf.setZhuguannamedelete(zhuguannamedelete +","+user.getName());
                    rf.setZhuguantime(rfRource.getZhuguantime()+","+sdf.format(now));
                }

                if(zhuguanname.split(",").length ==  rf.getZhuguannamedelete().split(",").length ){
                    rf.setStatus("处理处置");
                } else {
                    rf.setStatus("主管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                String implementperson = receivefileinfo.getImplementperson();
                String implementpersondelete = receivefileinfo.getImplementpersondelete();
                if( implementpersondelete.equals("") ){
                    rf.setImplementpersondelete(user.getName());
                    rf.setImplementtime(sdf.format(now));
                } else {
                    rf.setImplementpersondelete(implementpersondelete+","+user.getName());
                    rf.setImplementtime(rfRource.getImplementtime()+","+sdf.format(now));
                }
                if(implementperson.split(",").length == rf.getImplementpersondelete().split(",").length){
                    rf.setStatus("办公室归档");
                } else {
                    rf.setStatus("处理处置");
                }
            }
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                e.printStackTrace();
                er = -1;
            }
            //接下来就是更新model_wenjianniban表
            Model_Wenjianniban meme = new Model_Wenjianniban();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            ModelText me= (ModelText) JSONObject.toBean(object,ModelText.class);
            meme.setReceivefileid(receivefileinfo.getReceivefileid());
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                meme.setMainleaderinstruction(me.getMainleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                meme.setResult(me.getResult());
            }
            //去修改model_erkeshi表
            try {
                mer = receiveFileServiceImp.updateModelWenjiannibandan(meme);
            } catch (Exception e) {
                e.printStackTrace();
                mer = -1;
            }
        }




        if( receivefileinfo.getModeltype().equals("一科室提意见") ){
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileinfo.getReceivefileid());
            if(receivefileinfo.getStatus().equals("科室签批")){
                if(receivefileinfo.getDepartment1persondelete().equals("")){
                    rf.setDepartment1persondelete(user.getName());
                    rf.setDepartment1time(sdf.format(now));
                } else {
                    rf.setDepartment1persondelete(receivefileinfo.getDepartment1persondelete()+","+user.getName());
                    rf.setDepartment1time(rfRource.getDepartment1time()+","+sdf.format(now));
                }
                if( receivefileinfo.getDepartment1person().split(",").length == rf.getDepartment1persondelete().split(",").length ){
                    rf.setStatus("分管领导签批");
                } else {
                    rf.setStatus("科室签批");
                }
            }
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                String fenguanname = receivefileinfo.getFenguanname();
                String fenguannamedelete = receivefileinfo.getFenguannamedelete();
                if(fenguannamedelete.equals("")){
                    rf.setFenguannamedelete(user.getName());
                    rf.setFenguantime(sdf.format(now));
                } else {
                    rf.setFenguannamedelete(fenguannamedelete+","+user.getName());
                    rf.setFenguantime(rfRource.getFenguantime()+","+sdf.format(now));
                }
                if( fenguanname.split(",").length == rf.getFenguannamedelete().split(",").length ){
                    rf.setStatus("主管领导签批");
                } else {
                    rf.setStatus("分管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                String zhuguanname = receivefileinfo.getZhuguanname();
                String zhuguannamedelete = receivefileinfo.getZhuguannamedelete();
                if(zhuguannamedelete.equals("")){
                    rf.setZhuguannamedelete(user.getName());
                    rf.setZhuguantime(sdf.format(now));
                } else {
                    rf.setZhuguannamedelete(zhuguannamedelete +","+user.getName());
                    rf.setZhuguantime(rfRource.getZhuguantime()+","+sdf.format(now));
                }
                if(zhuguanname.split(",").length == rf.getZhuguannamedelete().split(",").length ){
                    rf.setStatus("处理处置");
                } else {
                    rf.setStatus("主管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                String implementperson = receivefileinfo.getImplementperson();
                String implementpersondelete = receivefileinfo.getImplementpersondelete();
                if( implementpersondelete.equals("") ){
                    rf.setImplementpersondelete(user.getName());
                    rf.setImplementtime(sdf.format(now));
                } else {
                    rf.setImplementpersondelete(implementpersondelete+","+user.getName());
                    rf.setImplementtime(rfRource.getImplementtime()+","+sdf.format(now));
                }
                if(implementperson.split(",").length == rf.getImplementpersondelete().split(",").length){
                    rf.setStatus("办公室归档");
                } else {
                    rf.setStatus("处理处置");
                }
            }
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                e.printStackTrace();
                er = -1;
            }
            //接下来就是更新model_yikeshi表
            Model_Yikeshi meme = new Model_Yikeshi();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            ModelText me= (ModelText) JSONObject.toBean(object,ModelText.class);
            meme.setReceivefileid(receivefileinfo.getReceivefileid());
            if(receivefileinfo.getStatus().equals("科室签批") & dept.equals(receivefileinfo.getDepartment1name())){
                meme.setDepartmentadvice(me.getDepartmentadvice());
            }
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                meme.setMainleaderinstruction(me.getMainleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                meme.setResult(me.getResult());
            }
            //去修改model_yikeshi表
            try {
                mer = receiveFileServiceImp.updateModelYikeshi(meme);
            } catch (Exception e) {
                e.printStackTrace();
                mer = -1;
            }
        }




        if( receivefileinfo.getModeltype().equals("两科室提意见") ){
            ReceiveFile rf = new ReceiveFile();
            rf.setReceivefileid(receivefileinfo.getReceivefileid());
            String person1Rource = receivefileinfo.getDepartment1person();
            String person1RourceDelete = receivefileinfo.getDepartment1persondelete();
            String person2Rource = receivefileinfo.getDepartment2person();
            String person2RourceDelete = receivefileinfo.getDepartment2persondelete();
            if(receivefileinfo.getStatus().equals("科室签批")){
                if( dept.equals(receivefileinfo.getDepartment1name()) & person1Rource.contains(name) & !person1RourceDelete.contains(name) ){
                    rf.setDepartment2persondelete(receivefileinfo.getDepartment2persondelete());
                    if(receivefileinfo.getDepartment1persondelete().equals("")){
                        rf.setDepartment1persondelete(user.getName());
                        rf.setDepartment1time(sdf.format(now));
                    }else {
                        rf.setDepartment1persondelete(receivefileinfo.getDepartment1persondelete()+","+user.getName());
                        rf.setDepartment1time(rfRource.getDepartment1time()+","+sdf.format(now));
                    }
                    int len1 = receivefileinfo.getDepartment1person().split(",").length;
                    int len2 = rf.getDepartment1persondelete().equals("")?0:rf.getDepartment1persondelete().split(",").length;
                    int len3 = receivefileinfo.getDepartment2person().split(",").length;
                    int len4 = receivefileinfo.getDepartment2persondelete().equals("")?0:receivefileinfo.getDepartment2persondelete().split(",").length;
                    if( ( len1==len2 ) & ( len3==len4 ) ){
                        rf.setStatus("分管领导签批");
                    } else {
                        rf.setStatus("科室签批");
                    }
                } else  {
                    rf.setDepartment1persondelete(receivefileinfo.getDepartment1persondelete());
                    if(receivefileinfo.getDepartment2persondelete().equals("")){
                        rf.setDepartment2persondelete(user.getName());
                        rf.setDepartment2time(sdf.format(now));
                    } else {
                        rf.setDepartment2persondelete(receivefileinfo.getDepartment2persondelete()+","+user.getName());
                        rf.setDepartment2time(rfRource.getDepartment1time()+","+sdf.format(now));
                    }
                    int len1 = receivefileinfo.getDepartment1person().split(",").length;
                    int len2 = receivefileinfo.getDepartment1persondelete().equals("")?0:receivefileinfo.getDepartment1persondelete().split(",").length;
                    int len3 = receivefileinfo.getDepartment2person().split(",").length;
                    int len4 = rf.getDepartment2persondelete().equals("")?0:rf.getDepartment2persondelete().split(",").length;
                    if( (len1 == len2) & ( len3== len4) ){
                        rf.setStatus("分管领导签批");
                    } else {
                        rf.setStatus("科室签批");
                    }
                }

            }
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                String fenguanname = receivefileinfo.getFenguanname();
                String fenguannamedelete = receivefileinfo.getFenguannamedelete();
                if(fenguannamedelete.equals("")){
                    rf.setFenguannamedelete(user.getName());
                    rf.setFenguantime(sdf.format(now));
                } else {
                    rf.setFenguannamedelete(fenguannamedelete+","+user.getName());
                    rf.setFenguantime(rfRource.getFenguantime()+","+sdf.format(now));
                }
                if( fenguanname.split(",").length == rf.getFenguannamedelete().split(",").length ){
                    rf.setStatus("主管领导签批");
                } else {
                    rf.setStatus("分管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                String zhuguanname = receivefileinfo.getZhuguanname();
                String zhuguannamedelete = receivefileinfo.getZhuguannamedelete();
                if(zhuguannamedelete.equals("")){
                    rf.setZhuguannamedelete(user.getName());
                    rf.setZhuguantime(sdf.format(now));
                } else {
                    rf.setZhuguannamedelete(zhuguannamedelete +","+user.getName());
                    rf.setZhuguantime(rfRource.getZhuguantime()+","+sdf.format(now));
                }

                if(zhuguanname.split(",").length ==  rf.getZhuguannamedelete().split(",").length ){
                    rf.setStatus("处理处置");
                } else {
                    rf.setStatus("主管领导签批");
                }
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                String implementperson = receivefileinfo.getImplementperson();
                String implementpersondelete = receivefileinfo.getImplementpersondelete();
                if( implementpersondelete.equals("") ){
                    rf.setImplementpersondelete(user.getName());
                    rf.setImplementtime(sdf.format(now));
                } else {
                    rf.setImplementpersondelete(implementpersondelete+","+user.getName());
                    rf.setImplementtime(rfRource.getImplementtime()+","+sdf.format(now));
                }
                if(implementperson.split(",").length == rf.getImplementpersondelete().split(",").length){
                    rf.setStatus("办公室归档");
                } else {
                    rf.setStatus("处理处置");
                }
            }
            //去更新receivefile表
            try {
                er = receiveFileServiceImp.updateReceiveFile(rf);
            } catch (Exception e) {
                e.printStackTrace();
                er = -1;
            }
            //接下来就是更新model_erkeshi表
            Model_Erkeshi meme = new Model_Erkeshi();
            //把text中的内容取出来
            JSONObject object = JSONObject.fromObject(text);
            ModelText me= (ModelText) JSONObject.toBean(object,ModelText.class);
            meme.setReceivefileid(receivefileinfo.getReceivefileid());
            if(receivefileinfo.getStatus().equals("科室签批") & dept.equals(receivefileinfo.getDepartment1name()) & person1Rource.contains(name) & !person1RourceDelete.contains(name) ){
                meme.setDepartment1advice(me.getDepartment1advice());
            }
            if(receivefileinfo.getStatus().equals("科室签批") & dept.equals(receivefileinfo.getDepartment2name()) & person2Rource.contains(name) & !person2RourceDelete.contains(name) ){
                meme.setDepartment2advice(me.getDepartment2advice());
            }
            if(receivefileinfo.getStatus().equals("分管领导签批")){
                meme.setBranchleaderinstruction(me.getBranchleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("主管领导签批")){
                meme.setMainleaderinstruction(me.getMainleaderinstruction());
            }
            if(receivefileinfo.getStatus().equals("处理处置") & receivefileinfo.getImplementperson().contains(user.getName())){
                meme.setResult(me.getResult());
            }
            //去更新model_erkeshi表
            try {
                mer = receiveFileServiceImp.updateModelErkeshi(meme);
            } catch (Exception e) {
                e.printStackTrace();
                mer = -1;
            }
        }
        Map<String,String> map = new HashMap<String,String>();
        if( mer == -1 || er == -1){
            map.put("result","failure");
        } else {
            map.put("result","success");
        }
        String result = new Gson().toJson(map);
        return result;
    }

    /**
     * 附件删除按钮
     * @param receivefileid
     * @param path
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/pathDelete",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String pathDelete(HttpSession session,String receivefileid,String path){
        //先把receivefileid对应的attachmentpath查出来
        User user = (User)session.getAttribute("user");
        ReceiveFile rf = receiveFileServiceImp.getReceiveFileInfoById(receivefileid);
        String attachmentpath = rf.getAttachmentpath();
        String[] paths = attachmentpath.split(",");
        //path = path.replaceAll("\\\\\\\\","\\\\");
        List<String> pathList = new ArrayList<String>();
        for( int i = 0; i < paths.length; i++ ){
            if( !paths[i].equals(path) ){
                pathList.add(paths[i]);
            }
        }
        String attachmentpathfinal = "";
        for( int t = 0; t < pathList.size(); t++  ){
            if( t <  (pathList.size()-1) ){
                attachmentpathfinal = pathList.get(t)+ ",";
            } else {
                attachmentpathfinal = pathList.get(t);
            }
        }
        ReceiveFile newrf = new ReceiveFile();
        newrf.setReceivefileid( rf.getReceivefileid() );
        newrf.setAttachmentpath( attachmentpathfinal );
        if( rf.getAttachmentdeleteperson().equals("") ){
            newrf.setAttachmentdeleteperson(user.getName()+"-"+path);
        } else {
            newrf.setAttachmentdeleteperson(rf.getAttachmentdeleteperson() +"," +user.getName()+"-"+path);
        }

        int re = 0;
        try {
            re = receiveFileServiceImp.updateReceiveFile(newrf);
        } catch (Exception e) {
            re = -1;
        }
        Map<String,String> map = new HashMap<String,String>();
        if( re == -1 ){
            map.put("result","failure");
        } else {
            map.put("result","success");
        }
        String result = new Gson().toJson(map);
        return result;
    }

    /**
     * 确认归档按钮
     * @param receivefileid
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/toConfirm",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String toConfirm(String receivefileid,HttpSession session){
        User user = (User)session.getAttribute("user");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ReceiveFile rf = new ReceiveFile();
        rf.setReceivefileid(receivefileid);
        rf.setStatus("结束");
        rf.setConfirmperson( user.getName() );
        rf.setConfirmtime(sdf.format(now));
        int re = 0;
        try {
            re = receiveFileServiceImp.updateReceiveFile(rf);
        } catch (Exception e) {
            re = -1;
        }
        Map<String,String> map = new HashMap<String,String>();
        if( re == -1 ){
            map.put("result","failure");
        } else {
            map.put("result","success");
        }
        String result = new Gson().toJson(map);
        return result;
    }
    /**
     * 删除按钮
     * @param receivefileid
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteReceiveFile",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteReceiveFile(String receivefileid,String modeltype){
        int deleteResult = 0;
        try {
            deleteResult = receiveFileServiceImp.deleteReceiveFile(receivefileid);
        } catch (Exception e) {
            e.printStackTrace();
            deleteResult =-1;
        }
        int deleteModel = 0;
        if("".equals(modeltype)){
            deleteModel = 0;
        } else if("model_zhijiechuli".equals(modeltype)){
            deleteModel = receiveFileServiceImp.deleteModelZhiJieChuLi(receivefileid);
        } else if("model_wenjianniban".equals(modeltype)){
            deleteModel = receiveFileServiceImp.deleteModelWenJianNiBan(receivefileid);
        } else if("model_yikeshi".equals(modeltype)){
            deleteModel = receiveFileServiceImp.deleteModelYiKeShi(receivefileid);
        } else if("model_erkeshi".equals(modeltype)){
            deleteModel = receiveFileServiceImp.deleteModelErKeShi(receivefileid);
        }



        Map<String,String> map = new HashMap<String,String>();
        if( deleteResult == -1 | deleteModel==-1){
            map.put("result","failure");
        } else {
            map.put("result","success");
        }
        String result = new Gson().toJson(map);
        return result;
    }
}
