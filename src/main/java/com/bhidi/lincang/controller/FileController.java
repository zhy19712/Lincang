package com.bhidi.lincang.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @ResponseBody
    @RequestMapping(value="/multipleUpload",method= RequestMethod.POST)
    public String multipleUpload(@RequestParam("files") MultipartFile[] files,HttpServletRequest request, ModelMap map,String test) {
        //声明一个集合来收集每个文件上传的结果
        Set<String> resultSet = new HashSet<String>();

        List<String> resultFinal = new ArrayList<String>();
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
     * @throws Exception
     */
    //@RequestMapping("/down")
   /* public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{
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
    }*/
    @RequestMapping("/download")
    public ResponseEntity<byte[]> downloadImg(HttpSession session,String path) throws IOException{
        //=============造响应体=============
        //1、创建一个ResponseEntity对象。这个对象里面既有响应头还有响应体；
        ServletContext servletContext = session.getServletContext();
        //1、获取到图片的流，直接交给浏览器；ServletContext.可以从当前项目下获取资源
        //2、获取到图片的流;
        System.out.println(path);
        path = path.replaceAll("\\\\","/");
        System.out.println(path);
        /*path = path.replaceFirst("/","//");
        System.out.println(path);*/
        File file = new File(path);

        InputStream is = new FileInputStream(file);
        //InputStream is = servletContext.getResourceAsStream(path);
        //创建一个和流一样多的数组
        byte[] body = new byte[is.available()];
        //3、将流的数据放在数组里面
        is.read(body);
        is.close();

        //==============造响应头================
        HttpHeaders headers = new HttpHeaders();
        //文件下载的响应头
        //按照以前乱码的解决方式；

        //文件名乱码解决
        String filename = path.split("/")[ path.split("/").length - 1 ];
        String filenameSecond = filename.split("\\.")[filename.split("\\.").length-1];//docx
        int as = filename.lastIndexOf("-");
        String filenameFirst = filename.substring(0,as);
        filename = filenameFirst + "." + filenameSecond;
        filename = new String(filename.getBytes("GBK"),"ISO8859-1");

        headers.add("Content-Disposition", "attachment; filename="+filename);


        //第一个参数代表给浏览器的响应数据（响应体）
        //第二个参数代表当前响应的响应头（定制响应头）MultiValueMap
        //第三个参数代表当前响应状态码（statusCode）HttpStatus
        ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

        return re;
    }

}