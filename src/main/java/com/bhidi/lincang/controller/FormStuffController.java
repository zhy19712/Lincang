package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.F_Stuff;
import com.bhidi.lincang.service.FormOfficeServiceImp;
import com.bhidi.lincang.service.FormStuffServiceImp;
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

/*
 *  从申请者发出的请求都是在这个Controller进行处理
 */
@Controller
public class FormStuffController {

    @Autowired
    FormStuffServiceImp formStuffServiceImp;

    @Autowired
    FormOfficeServiceImp formOfficeServiceImp;

    //保存的处理,保存但是不提交
    //保存是将信息仅仅进行保存，提交的话信息就在办公室那边看到了
    @ResponseBody
    @RequestMapping(value="/saveFormData",method= RequestMethod.POST)
    public String saveFormData(@RequestParam(value="dept",required=false)String dept,
                             @RequestParam(value="author",required=false)String author,
                             @RequestParam(value="reviewer",required=false)String reviewer,
                             @RequestParam(value="print",required=false)String print,
                             @RequestParam(value="revision",required=false)String revision,
                             @RequestParam(value="copy",required=false)String copy,
                           /*  @RequestParam(value="arrAttachment[]",required=false) String[] arrAttachment,*/
                             @RequestParam(value="keyword",required=false)String keyword,
                             @RequestParam(value="title",required=false)String title,
                             @RequestParam(value="content",required=false)String content){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String oid =  String.valueOf( now.getTime() ).substring(1,10);
        F_Stuff f_stuff = new F_Stuff();
        f_stuff.setOid(oid);
        f_stuff.setCreated_at( sdf.format(now) );
        f_stuff.setSent_at( "" );
        f_stuff.setReviewer(reviewer);
        f_stuff.setAuthor(author);
        f_stuff.setDept(dept);
        f_stuff.setKeyword(keyword);
        f_stuff.setTitle(title);
        f_stuff.setContent(content);
        f_stuff.setPrint(print);
        f_stuff.setRevision(revision);
        f_stuff.setCopy(copy);
        f_stuff.setStatus("NEW");
        //在这里需要设置返回值的，要让用户知道上传或者说是这些东西完事没有。
        Integer integerResultOfFormStuff = formStuffServiceImp.saveFormStuff(f_stuff);
        return integerResultOfFormStuff.toString();
    }
    //提交的处理,这里还应该存储到事物的表里去。
    @ResponseBody
    @RequestMapping(value="/submitFormData",method= RequestMethod.POST)
    public String submitFormData(@RequestParam(value="dept",required=false)String dept,
                               @RequestParam(value="author",required=false)String author,
                               @RequestParam(value="reviewer",required=false)String reviewer,
                               @RequestParam(value="print",required=false)String print,
                               @RequestParam(value="revision",required=false)String revision,
                               @RequestParam(value="copy",required=false)String copy,
                               /*@RequestParam(value="arrAttachment[]",required=false) String[] arrAttachment,*/
                               @RequestParam(value="keyword",required=false)String keyword,
                               @RequestParam(value="title",required=false)String title,
                               @RequestParam(value="content",required=false)String content){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String oid =  String.valueOf( now.getTime() ).substring(1,10);
        F_Stuff f_stuff = new F_Stuff();
        f_stuff.setOid(oid);
        f_stuff.setCreated_at( sdf.format(now) );
        f_stuff.setSent_at( sdf.format(now) );
        f_stuff.setReviewer(reviewer);
        f_stuff.setAuthor(author);
        f_stuff.setDept(dept);
        f_stuff.setKeyword(keyword);
        f_stuff.setTitle(title);
        f_stuff.setContent(content);
        f_stuff.setPrint(print);
        f_stuff.setRevision(revision);
        f_stuff.setCopy(copy);
        f_stuff.setStatus("SUBMITTED");
        //在这里需要设置返回值的，要让用户知道上传或者说是这些东西完事没有。
        Integer integerResultOfFormStuff = formStuffServiceImp.submittedFormStuff(f_stuff);
        //在这里先判断插入FORM_STUFF成功没有，没成功直接返回0，成功在继续插入
        Integer integerResultOfFormOffice = 0;
        if( integerResultOfFormStuff != 0 ){
            //执行插入FROM_OFFICE表格的操作，在这里需要将状态转变为new
            f_stuff.setStatus("NEW");
            integerResultOfFormOffice = formOfficeServiceImp.stuffToOffice(f_stuff);
        } else {
            return "0";
        }
        return integerResultOfFormOffice.toString();
    }
    /*
     * 查看按钮，目前是传送回来OID
     */
    @ResponseBody
    @RequestMapping(value="/queryStuffByOid",method= RequestMethod.POST)
    public F_Stuff queryStuffByOid(String oid){
        F_Stuff fStuff = formStuffServiceImp.queryStuffByOid(oid);
        return fStuff;
    }
    @ResponseBody
    @RequestMapping(value="/deleteStuffByOid")
    public String delete( String oid ) {
        int cnt = 0;
        try {
            cnt = formStuffServiceImp.deleteStuffByOid(oid);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return Integer.toString(cnt);
    }
}
