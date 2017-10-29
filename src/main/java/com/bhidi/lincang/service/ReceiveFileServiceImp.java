package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.dao.ReceiveFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.util.*;

@Service
public class ReceiveFileServiceImp implements ReceiveFileServiceInf{

    @Autowired
    ReceiveFileMapper receiveFileMapper;



    public ReceiveFile getReceiveFileInfoById(String receivefileid) {
        return receiveFileMapper.selectReceiveFileInfoById(receivefileid);
    }

    public String getDepartmentNameByName(String department1) {
        return receiveFileMapper.selectDepartmentNameByName(department1);
    }


    public int updateReceiveFile(ReceiveFile rf) {
        return receiveFileMapper.updateReceiveFile(rf);
    }


    public int insertModelErkeshi(Model_Erkeshi meme) {
        return receiveFileMapper.insertModelErkeshi(meme);
    }


    public int insertModelYikeshi(Model_Yikeshi meme) {
        return receiveFileMapper.insertModelYikeshi(meme);
    }


    public int insertModelWenjiannibandan(Model_Wenjianniban meme) {
        return receiveFileMapper.insertModelWenjiannibandan(meme);
    }


    public int insertModelZhijiechuli(Model_Zhijiechuli meme) {
        return receiveFileMapper.insertModelZhijiechuli(meme);
    }

    public Model_Zhijiechuli getModelZhijiechuliInfoById(String receivefileid) {
        return receiveFileMapper.getInfoFromZhijiechuli(receivefileid);
    }

    public Model_Wenjianniban getModelWenjiannibanInfoById(String receivefileid) {
        return receiveFileMapper.getInfoFromWenjianniban(receivefileid);
    }

    public Model_Yikeshi getModelYikeshiInfoById(String receivefileid) {
        return receiveFileMapper.getInfoFromYikeshi(receivefileid);
    }

    public Model_Erkeshi getModelErkeshiInfoById(String receivefileid) {
        return receiveFileMapper.getInfoFromErkeshi(receivefileid);
    }

    public Map<String,Object> save(Map<String, Object> mapCondition){
        ReceiveFile rfaa = (ReceiveFile)mapCondition.get("receiveFileAhead");
        MultipartFile[] files = (MultipartFile[])mapCondition.get("files");
        HttpServletRequest request = (HttpServletRequest)mapCondition.get("request");
        String str = "";
        List<String> fileUploadList =  multipleUpload(files,request);
        String receivefileid = new Date().getTime()+"";
        rfaa.setReceivefileid(receivefileid);

        for( int i = 0;i < fileUploadList.size();i++ ) {
            if (fileUploadList.get(i).contains("文件上传失败！") || fileUploadList.get(i).contains("文件为空！")) {
                for( int t = 0;t < fileUploadList.size();t++ ) {
                    //delete(request,fileUploadList.get(t).split("\\\\")[ (fileUploadList.get(t).split("\\\\").length)-1 ]);
                    System.out.println(fileUploadList.get(t));
                    delete(fileUploadList.get(t));
                }
            }
        }

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
        rfaa.setAttachmentpath(str);
        int as = receiveFileMapper.insert(rfaa);
        mapResult.put("path",str);
        mapResult.put("receivefileid",receivefileid);
        mapResult.put("result","success");
        return mapResult;
    }
    /**
     * 删除文件
     */
    /*public void delete(HttpServletRequest request,String fileName) {
        // 得到上传服务器的物理路径
        String fileUrl = request.getSession().getServletContext().getRealPath("upload//receivefile//" + fileName);
        File file = new File(fileUrl);
        file.delete();
    }*/
    public void delete(String path) {
        // 得到上传服务器的物理路径
        File file = new File(path);
        file.delete();
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
                pathpath = request.getSession().getServletContext().getRealPath("upload/receivefile/"+fileNameSave);
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

                }
            }
        }
        return resultFinal;
    }
}
