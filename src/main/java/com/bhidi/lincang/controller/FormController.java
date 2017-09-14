package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.Attachment;
import com.bhidi.lincang.bean.F_Stuff;
import com.bhidi.lincang.service.AttachmentServiceImp;
import com.bhidi.lincang.service.FormOfficeServiceImp;
import com.bhidi.lincang.service.FormStuffServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 *  从申请者发出的请求都是在这个Controller进行处理
 */
@Controller
public class FormController {

    @Autowired
    FormStuffServiceImp formStuffServiceImp;

    @Autowired
    FormOfficeServiceImp formOfficeServiceImp;

    @Autowired
    AttachmentServiceImp attachmentServiceImp;

    //保存的处理,保存但是不提交
    //保存是将信息仅仅进行保存，提交的话信息就在办公室那边看到了
    @ResponseBody
    @RequestMapping(value="/saveFormData",method= RequestMethod.POST)
    public String saveFormData(HttpServletRequest request,
            @RequestParam(value="id",required=false)Integer id,
                               @RequestParam(value="created_at",required=false)String created_at,
                               @RequestParam(value="author",required=false)String author,
                               @RequestParam(value="dept",required=false)String dept,
                               @RequestParam(value="reviewer",required=false)String reviewer,
                               @RequestParam(value="keyword",required=false)String keyword,
                               @RequestParam(value="title",required=false)String title,
                               @RequestParam(value="content",required=false)String content,
                               @RequestParam(value="print",required=false)String print,
                               @RequestParam(value="revision",required=false)String revision,
                               @RequestParam(value="copy",required=false)String copy,
                               /* @RequestParam(value="arrAttachment[]",required=false) String[] arrAttachment*/
                               @RequestParam("files") MultipartFile[] files
                               ){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        F_Stuff f_stuff = new F_Stuff();
        f_stuff.setCreated_at( "".equals(created_at)? sdf.format(now):created_at );
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
        Integer integerResultOfFormStuff = null;
        //判断id的内容，来判断进行插入还是更新。
        if( id != null ){
            //更新操作
            f_stuff.setId(id);
            try {
                integerResultOfFormStuff = formStuffServiceImp.updateFormStuff(f_stuff);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //插入操作,这个应该返回id
            try {
                integerResultOfFormStuff = formStuffServiceImp.saveFormStuff(f_stuff);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //调用上传附件的方法，返回插入attachement表之后的id
        multipleUpload(files, request);
        //将表id和附件的ids插入attachement_form表
        return integerResultOfFormStuff.toString();
    }
    //提交的处理,这里还应该存储到office的表里去。
    @ResponseBody
    @RequestMapping(value="/submitFormData",method= RequestMethod.POST)
    public String submitFormData(@RequestParam(value="id",required=false)Integer id,
                                 @RequestParam(value="created_at",required=false)String created_at,
                                 @RequestParam(value="author",required=false)String author,
                                 @RequestParam(value="dept",required=false)String dept,
                                 @RequestParam(value="reviewer",required=false)String reviewer,
                                 @RequestParam(value="keyword",required=false)String keyword,
                                 @RequestParam(value="title",required=false)String title,
                                 @RequestParam(value="content",required=false)String content,
                                 @RequestParam(value="print",required=false)String print,
                                 @RequestParam(value="revision",required=false)String revision,
                                 @RequestParam(value="copy",required=false)String copy
                               /* @RequestParam(value="arrAttachment[]",required=false) String[] arrAttachment*/){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        F_Stuff f_stuff = new F_Stuff();
        f_stuff.setCreated_at( created_at == ""?sdf.format(now):created_at );
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
        Integer idResultOfFormStuff = null;
        if( id != null ){
            //更新操作，更新FORM_STUFF表
            f_stuff.setId(id);
            try {
                idResultOfFormStuff = formStuffServiceImp.updateFormStuff(f_stuff);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //插入操作，插入FORM_STUFF表
            //在这里返回插入数据的id
            try {
                idResultOfFormStuff = formStuffServiceImp.submittedFormStuff(f_stuff);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //在这里先判断插入FORM_STUFF成功没有，没成功直接返回0，成功在继续插入
        Integer integerResultOfFormOffice = 0;
        if( idResultOfFormStuff != 0 ){
            //执行插入FROM_OFFICE表格的操作，在这里需要将状态转变为new
            f_stuff.setStatus("NEW");
            try {
                integerResultOfFormOffice = formOfficeServiceImp.stuffToOffice(f_stuff);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "0";
        }
        return integerResultOfFormOffice.toString();
    }
    /*
     * 查看按钮，目前是传送回来ID
     */
    @ResponseBody
    @RequestMapping(value="/queryStuffById",method= RequestMethod.POST)
    public F_Stuff queryStuffByOid(int id){
        F_Stuff fStuff = null;
        try {
            fStuff = formStuffServiceImp.queryStuffById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fStuff;
    }
    /*
     * 删除按钮，根据id删除表单内容
     */
    @ResponseBody
    @RequestMapping(value="/deleteStuffById",method= RequestMethod.POST)
    public String delete(int id) {
        int cnt = 0;
        try {
            cnt = formStuffServiceImp.deleteStuffById(id);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return Integer.toString(cnt);
    }

    /**
     * 多表单提交功能
     * @param files
     * @param request
     * @return
     */
    public String multipleUpload(MultipartFile[] files, HttpServletRequest request) {
        //声明一个集合来收集每个文件上传的结果
        Set<String> resultSet = new HashSet<>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> resultFinal = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if( files != null && files.length > 0 ){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                if( !"".equals(file.getOriginalFilename()) ){
                    String resultSingle = singleUpload(file,request);
                    resultSet.add(resultSingle);
                }
                if( files.length == 1 && "".equals(file.getOriginalFilename()) ){
                    resultFinal = null;
                }

            }
        }
        //对单个文件的返回结果进行处理返回最终上传结果
        for( String s:resultSet ){
            if( !"ok!".equals(s) ){
                resultFinal.add(s);
            }
        }
        map.put("fileerror",resultFinal);
        return "yimin_temp";
    }
    /**
     * 单个文件上传功能
     * @param file
     * @return
     */
/*    @ResponseBody
    @RequestMapping(value="/singleUpload",method= RequestMethod.POST)*/
    public String singleUpload(MultipartFile file,HttpServletRequest request) {
        //文件名字
        String fileName =  file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                //文件存储路径
                String path = request.getSession().getServletContext().getRealPath("upload");
                File dir = new File(path, fileName);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                //MultipartFile自带的解析方法去转存文件
                file.transferTo(dir);
                //本机的IP
                //本地的主机
                InetAddress inetAddress = InetAddress.getLocalHost();
                //本机的ip
                String ipStr = inetAddress.getHostAddress();
                System.out.println("本机的IP = " + ipStr);
                //文件在服务器的位置
                String pathpath = request.getSession().getServletContext().getRealPath("upload\\"+fileName);
                System.out.println("文件在服务器上的位置为"+pathpath);
                //取到文件的大小，long
                Long fileSize = file.getSize();
                System.out.println("文件大小为"+fileSize);
                //调用方法存储attachment表格
                Attachment att = new Attachment();
                att.setName(fileName);
                att.setPath(pathpath);
                att.setSize(fileSize);
                int idResult = attachmentServiceImp.save(att);
                System.out.println(idResult);
            } catch (Exception e) {
                return fileName+"文件上传失败！";
            }
            return "ok!";
        } else{
            return fileName+"文件为空！";
        }

    }
}
