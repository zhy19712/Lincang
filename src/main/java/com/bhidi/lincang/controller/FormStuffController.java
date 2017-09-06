package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.F_Stuff;
import com.bhidi.lincang.service.FormStuffServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FormStuffController {

    @Autowired
    FormStuffServiceImp formStuffServiceImp;

    //保存的处理,保存但是不提交
    //保存是将信息仅仅进行保存，提交的话信息就在办公室那边看到了
    @RequestMapping(value="/saveFormData",method= RequestMethod.POST)
    public void saveFormData(@RequestParam(value="sent_at",required=false)String sent_at,
                             @RequestParam(value="author",required=false)String author,
                             @RequestParam(value="dept",required=false)String dept,
                             @RequestParam(value="print",required=false)String print,
                             @RequestParam(value="revision",required=false)String revision,
                             @RequestParam(value="copy",required=false)String copy,
                             @RequestParam(value="arrAttachment[]",required=false) String[] arrAttachment,
                             @RequestParam(value="keywords",required=false)String keywords,
                             @RequestParam(value="title",required=false)String title,
                             @RequestParam(value="content",required=false)String content){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String oid =  String.valueOf( now.getTime() ).substring(1,10);
        F_Stuff f_stuff = new F_Stuff();
        f_stuff.setOid(oid);
        f_stuff.setCreated_at( sdf.format(now) );
        f_stuff.setSent_at(sent_at);
        f_stuff.setAuthor(author);
        f_stuff.setDept(dept);
        String reviewer = "";
        if( arrAttachment != null ){
            if( arrAttachment.length > 0 ){
                for( int i = 0; i < (arrAttachment.length-1) ; i++){
                    reviewer += arrAttachment[i];
                    reviewer += ",";
                }
                reviewer += arrAttachment[arrAttachment.length-1];
            }
        }
        f_stuff.setReviewer(reviewer);
        f_stuff.setKeywords(keywords);
        f_stuff.setTitle(title);
        f_stuff.setContent(content);
        f_stuff.setPrint(print);
        f_stuff.setRevision(revision);
        f_stuff.setCopy(copy == ""?0:Integer.parseInt(copy));
        f_stuff.setStatus("NEW");
        //在这里需要设置返回值的，要让用户知道上传或者说是这些东西完事没有。
        Integer integerResultOfFormStuff = formStuffServiceImp.saveFormStuff(f_stuff);
        System.out.println(integerResultOfFormStuff);
    }
    //提交的处理,这里还应该存储到事物的表里去。
    @RequestMapping(value="/submitFormData",method= RequestMethod.POST)
    public void submitFormData(@RequestParam(value="sent_at",required=false)String sent_at,
                               @RequestParam(value="author",required=false)String author,
                               @RequestParam(value="dept",required=false)String dept,
                               @RequestParam(value="print",required=false)String print,
                               @RequestParam(value="revision",required=false)String revision,
                               @RequestParam(value="copy",required=false)String copy,
                               @RequestParam(value="arrAttachment[]",required=false) String[] arrAttachment,
                               @RequestParam(value="keywords",required=false)String keywords,
                               @RequestParam(value="title",required=false)String title,
                               @RequestParam(value="content",required=false)String content){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String oid =  String.valueOf( now.getTime() ).substring(1,10);
        F_Stuff f_stuff = new F_Stuff();
        f_stuff.setOid(oid);
        f_stuff.setCreated_at( sdf.format(now) );
        f_stuff.setSent_at(sent_at);
        f_stuff.setAuthor(author);
        f_stuff.setDept(dept);
        String subReviewer = "";
        if( arrAttachment != null ){
            if( arrAttachment.length > 0 ){
                for( int i = 0; i < (arrAttachment.length-1) ; i++){
                    subReviewer += arrAttachment[i];
                    subReviewer += ",";
                }
                subReviewer += arrAttachment[arrAttachment.length-1];
            }
        }
        f_stuff.setReviewer(subReviewer);
        f_stuff.setKeywords(keywords);
        f_stuff.setTitle(title);
        f_stuff.setContent(content);
        f_stuff.setPrint(print);
        f_stuff.setRevision(revision);
        f_stuff.setCopy(copy == ""?0:Integer.parseInt(copy));
        f_stuff.setStatus("SUBMITTED");
        //在这里需要设置返回值的，要让用户知道上传或者说是这些东西完事没有。
        Integer integerResultOfFormStuff = formStuffServiceImp.ssubmittedFormStuff(f_stuff);
        System.out.println(integerResultOfFormStuff);
    }
}
