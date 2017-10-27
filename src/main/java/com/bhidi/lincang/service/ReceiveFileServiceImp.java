package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.ReceiveFileAhead;
import com.bhidi.lincang.dao.ReceiveFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.util.*;

@Service
public class ReceiveFileServiceImp implements ReceiveFileServiceInf{

    @Autowired
    ReceiveFileMapper receiveFileMapper;

    public Map<String,Object> save(Map<String, Object> mapCondition){
        ReceiveFileAhead rfaa = (ReceiveFileAhead)mapCondition.get("receiveFileAhead");
        MultipartFile[] files = (MultipartFile[])mapCondition.get("files");
        HttpServletRequest request = (HttpServletRequest)mapCondition.get("request");
        String str = "";
        List<String> fileUploadList =  multipleUpload(files,request);
        String receivefileid = new Date().getTime()+"";
        rfaa.setReceivefileid(receivefileid);

        //结果的map
        Map<String,Object> mapResult = new HashMap();
        for( int i = 0;i < fileUploadList.size();i++ ){
            if( fileUploadList.get(i).contains("文件上传失败！")||fileUploadList.get(i).contains("文件为空！") ){
                mapResult.put("path","");
                mapResult.put("receivefileid","");
                mapResult.put("result",fileUploadList.get(i));
                return mapResult;
            } else {
                if( i < (fileUploadList.size() -1) ){
                    str += fileUploadList.get(i) + ",";
                } else {
                    str += fileUploadList.get(i);
                }
            }
        }
        int as = receiveFileMapper.insert(rfaa);
        mapResult.put("path",str);
        mapResult.put("receivefileid",receivefileid);
        mapResult.put("result","success");
        return mapResult;
    }



    public String singleUpload(MultipartFile file, HttpServletRequest request) {
        String pathpath = "";
        //文件名字
        String fileName =  file.getOriginalFilename();
        System.out.println(fileName);
        System.out.println(fileName.split("\\.").length);

        //处理过的文件名字
        String fileNameSave = fileName.split("\\.")[0] + "-" + new Date().getTime() + "."+fileName.split("\\.")[1];
        if (!file.isEmpty()) {
            try {
                //文件存储路径
                String path = request.getSession().getServletContext().getRealPath("upload/receivefile");
                File dir = new File(path, fileNameSave);
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
                //System.out.println("本机的IP = " + ipStr);
                //文件在服务器的位置
                pathpath = request.getSession().getServletContext().getRealPath("upload\\"+fileNameSave);
                //System.out.println("文件在服务器上的位置为"+pathpath);
                //取到文件的大小，long
                Long fileSize = file.getSize();
                //System.out.println("文件大小为"+fileSize);
            } catch (Exception e) {
                return fileName+"文件上传失败！";
            }
            return pathpath;
        } else{
            return fileName+"文件为空！";
        }
    }
    /**
     * 多个文件上传功能
     * @param files
     * @return
     */
    public List<String> multipleUpload(MultipartFile[] files, HttpServletRequest request) {
/*        //
        Map mapResult = new HashMap()*/;
        //声明一个集合来收集每个文件上传的结果
        /*Set<String> resultSet = new HashSet<String>();*/

        List<String> resultFinal = new ArrayList<String>();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                //保存文件
                if (!"".equals(file.getOriginalFilename())) {
                    String resultSingle = singleUpload(file, request);
                    resultFinal.add(resultSingle);
                }
                if (files.length == 1 && "".equals(file.getOriginalFilename())) {
                    /*resultFinal = null;*/
                }
            }
        }
        //对单个文件的返回结果进行处理返回最终上传结果
      /*  for( String s:resultSet ){
            if( !"ok!".equals(s) ){
                resultFinal.add(s);
            }
        }*/
        /*mapResult.put("result",resultFinal);*/
        return resultFinal;
    }
}
