package com.bhidi.lincang.controller;

import com.bhidi.lincang.service.ExcelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.util.*;

/**
 * Created by admin on 2017/8/18.
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private ExcelServiceImp excelServiceImp;
    /*
     * 读取excel文件的方法
     */
   /* @ResponseBody
    @RequestMapping(value="/readExcel",method= RequestMethod.POST,produces = "text/html;charset=UTF-8")*/
    public List<String> readExcel(List<String> pathList){
        //返回值的集合
        List<String> resultList = new ArrayList<>();

        //遍历地址的集合生成相对的File实例
        for( String path: pathList){
            String[] strs = path.split("\\\\");
            //文件名字
            String excelName = strs[strs.length - 1];
            //在这里将地址的斜杠改变一下

            path = path.replaceAll("\\\\","/");
            path = path.replaceFirst("/","//");

            File excelFile = null;
            try {
                excelFile = new File(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!excelFile.exists()){
                resultList.add(excelName+"文件不存在!");
            }
            if(!(excelFile.isFile() && (excelFile.getName().endsWith("xls") || excelFile.getName().endsWith("xlsx")))){
                resultList.add(excelName+"文件不是Excel格式!");
            }
            String resultService = excelServiceImp.readService(excelFile);
            resultList.add(resultService);
        }
        return resultList;



        /*File excelFile = null;
        File excelFile1 = null;
        File excelFile2= null;
        try{
            // 创建需要读取的文件对象
            excelFile = new File("C://Users/admin/Desktop/移民搬迁管理登记表终结版.xlsx");
            excelFile1 = new File("C://Users/admin/Desktop/tt.xlsx");
            excelFile2 = new File("C://Users/admin/Desktop/aaa.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!excelFile.exists()){
            return "文件不存在!";
        }
        if(!(excelFile.isFile() && (excelFile.getName().endsWith("xls") || excelFile.getName().endsWith("xlsx")))){
            return "文件不是Excel!";
        }
        List<File> excelList = new ArrayList<File>();
        excelList.add(excelFile);
        excelList.add(excelFile1);
        excelList.add(excelFile2);
        Set<String> set = new HashSet();
        //调用service层的业务逻辑处理，进行Excel文件的读取。
        for( int i = 0 ; i <  excelList.size();i++){
            String sign = null;
            try {
                sign = excelServiceImp.readService(excelList.get(i));
            } catch (Exception e) {
                sign = e.getMessage();
            }
            set.add(sign);
        }
        if( set.size() == 1 ){
            String result = "";
            for( String str : set){
                result = str;
            }
            return result;
        }
        else{
            String result = "";
            for( String str : set){
                if(!"录入成功！".equals(str)){
                    result = result + str + "/t";
                }
            }
            return result;
        }*/
    }
    /*
     * 上传多个excel文件的方法
     */
    @RequestMapping(value="/multipleExcelUpLoadExcel",method= RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String multipleExcelUpLoadExcel(@RequestParam("excels") MultipartFile[] excels, HttpServletRequest request, ModelMap map){
        //建立两个返回值集合
        List<String> resultList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        // /判断excel数组不能为空并且长度大于0
        if( excels!=null && excels.length > 0 ){
            //循环获取file数组中得文件
            for( int i = 0;i < excels.length;i++ ){
                MultipartFile excel = excels[i];
                if( !excel.isEmpty() ){
                    //保存文件
                    String sign = singleExcelUpload(excel,request);
                    if( sign.endsWith("!") ){
                        errorList.add(sign);
                    } else {
                        resultList.add(sign);
                    }
                } else {
                    String excelName = excel.getOriginalFilename();
                    if( !"".endsWith(excelName) ){
                        errorList.add(excelName+"为空！");
                    }
                }
            }
        }
        List<String> resultRead = readExcel(resultList);
        for( String s :resultRead){
            if( !"录入成功！".equals(s) ){
                errorList.add(s);
                //调用删除服务器上文件的方法！
                delete(s);
            }
        }
        map.put("error",errorList);
        return "indexTest";
    }
    /*
     * 单个excel文件的上传
     */
    @RequestMapping(value="/singleExcelUpload",method= RequestMethod.POST)
    @ResponseBody
    public String singleExcelUpload(MultipartFile excel,HttpServletRequest request) {
        String pathpath = "";
        try {
            //文件存储路径
            String path = request.getSession().getServletContext().getRealPath("upload/excel");
            //文件名字
            String fileName = excel.getOriginalFilename();
            File dir = new File(path, fileName);
            if ( !dir.exists() ) {
                //创建此抽象路径指定的目录，包括所有必须但不存在的父目录。
                dir.mkdirs();
            }
            //MultipartFile自带的解析方法去转存文件,将文件存储到目录中去。
            excel.transferTo(dir);
            //本机的IP
            //本地的主机
            InetAddress inetAddress = InetAddress.getLocalHost();
            //本机的ip
            String ipStr = inetAddress.getHostAddress();
            System.out.println("本机的IP = " + ipStr);

            //文件在服务器的位置
            pathpath = request.getSession().getServletContext().getRealPath("upload\\excel\\"+fileName);
            System.out.println("文件在服务器上的位置为"+pathpath);
            //取到文件的大小，long
            Long fileSize = excel.getSize();
            System.out.println("文件大小为"+fileSize);
        } catch (Exception e) {
            return pathpath+"上传失败！";
        }
        return pathpath;
    }
    /**
     * 服务器上的文件删除功能
     * @param fileUrl
     * (static/upload/...)
     * @return
     */
    @RequestMapping("/delete")
    public void delete(String fileUrl) {
        File file = new File(fileUrl);
        file.delete();
    }
}