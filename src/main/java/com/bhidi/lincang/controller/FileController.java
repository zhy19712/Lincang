package com.bhidi.lincang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.net.InetAddress.getLocalHost;

/**
 * Created by admin on 2017/8/16.
 */
@Controller
@RequestMapping("file")
public class FileController {
    /**
     * 单个文件上传功能
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/singleUpload",method= RequestMethod.POST)
    public String singleUpload(MultipartFile file,HttpServletRequest request) {
        //文件名字
        String fileName =  file.getOriginalFilename();;
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
            } catch (Exception e) {
                return fileName+"文件上传失败！";
            }
            return "ok!";
        } else{
                return fileName+"文件为空！";
        }

    }
    /**
     * 多个文件上传功能
     * @param files
     * @return
     */
    @RequestMapping(value="/multipleUpload",method= RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String multipleUpload(@RequestParam("files") MultipartFile[] files,HttpServletRequest request, ModelMap map) {
        //声明一个集合来收集每个文件上传的结果
        Set<String> resultSet = new HashSet<>();

        List<String> resultFinal = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if(files!=null&&files.length>0){
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
     * 服务器上的文件删除功能
     * @param request
     * @param fileName
     * (static/upload/...)
     * @return
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, String fileName) {
        // 得到上传服务器的物理路径
        String fileUrl = request.getSession().getServletContext().getRealPath("upload" + fileName);
        File file = new File(fileUrl);
        file.delete();
    }
    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/down")
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //模拟文件，myfile.txt为需要下载的文件
        String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = "下载文件.txt";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }
}