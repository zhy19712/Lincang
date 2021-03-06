package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.SendFile;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.dao.SendFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SendFileServiceImp implements SendFileServiceInf{
    @Autowired
    SendFileMapper sendFileMapper;

    public SendFile getSendFileInfoBySendFileId(String sendFileid) {
        return sendFileMapper.selectSendFileInfoBySendFileId(sendFileid);
    }


    public int updateSendFile(Map<String,Object> mapCondition) {
        SendFile sf = (SendFile)mapCondition.get("sendFile");
        User user = (User)mapCondition.get("user");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //查找数据库中对应的id的数据
        SendFile storeHouse = getSendFileInfoBySendFileId(sf.getSendfileid());
        String approver = storeHouse.getApprover();
        String implementperson = storeHouse.getImplementperson();
        if( "办公室审核处理".equals(storeHouse.getStatus()) ){
            //说明这次的处理人是办公室的人
            sf.setOfficeprocesstime(sdf.format(now));
            sf.setOfficeprocessperson(user.getName());
            sf.setStatus("签批");
        }
        if( "签批".equals(storeHouse.getStatus()) & storeHouse.getApprover().contains(user.getName()) ){
            sf.setApprover(approver);
            sf.setImplementperson(implementperson);
            //说明这次的处理人是签批人，这里有可能有两个
            if(storeHouse.getApproverdelete().equals("")){
                sf.setApproverdelete(user.getName());
                sf.setApprovertime(sdf.format(now));
            } else {
                sf.setApproverdelete(storeHouse.getApproverdelete()+","+user.getName());
                sf.setApprovertime(storeHouse.getApprovertime()+","+sdf.format(now));
            }
            if(storeHouse.getApprover().split(",").length== sf.getApproverdelete().split(",").length){
                sf.setStatus("处理处置");
            } else {
                sf.setStatus("签批");
            }
        }
        if( "处理处置".equals(storeHouse.getStatus()) & storeHouse.getImplementperson().contains(user.getName()) ){
            sf.setApprover(approver);
            sf.setImplementperson(implementperson);
            //说明这次调用这个方法的是处理人，有可能有两个
            if(storeHouse.getImplementpersondelete().equals("")){
                sf.setImplementpersondelete(user.getName());
                sf.setImplementpersontime(sdf.format(now));
            } else {
                sf.setImplementpersondelete(storeHouse.getImplementpersondelete()+","+user.getName());
                sf.setImplementpersontime(storeHouse.getImplementpersontime()+","+sdf.format(now));
            }
            if(storeHouse.getImplementperson().split(",").length== sf.getImplementpersondelete().split(",").length){
                sf.setStatus("办公室归档");
            } else {
                sf.setStatus("处理处置");
            }
        }
        if("办公室归档".equals(storeHouse.getStatus())){
            sf.setStatus("结束");
            sf.setConfirmperson(user.getName());
            sf.setConfirmtime(sdf.format(now));
            sf.setApprover(approver);
            sf.setImplementperson(implementperson);
        }
        return sendFileMapper.updateSendFile(sf);
    }

    public int deleteSendFile(String sendfileid) {
        return sendFileMapper.deleteSendFile(sendfileid);
    }

    /**
     *获取sendfile的sendfileid
     * @return
     */
    public String getLastSendFileId() {
        return sendFileMapper.selectLastSendFileId();
    }

    public Map<String,Object> save(Map<String, Object> mapCondition){
        SendFile sfa = (SendFile)mapCondition.get("sendFile");
        MultipartFile[] files = (MultipartFile[])mapCondition.get("files");
        HttpServletRequest request = (HttpServletRequest)mapCondition.get("request");
        String str = "";
        List<String> fileUploadList =  multipleUpload(files,request);
        Date now = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf1.format(now);
        String lastSendFileId = getLastSendFileId();
        String sendFileid = "";
        if(lastSendFileId==null || lastSendFileId==""){
            sendFileid = today+"0001"+"";
        } else {
            if( today.equals(lastSendFileId.substring(0,8)) ){
                BigDecimal bd = new BigDecimal(lastSendFileId);
                sendFileid = bd.add(new BigDecimal(1)).toString();
            } else {
                sendFileid = today+"0001"+"";
            }
        }
        sfa.setSendfileid(sendFileid);
        sfa.setCreatedtime(sdf.format(now));

        for( int i = 0;i < fileUploadList.size();i++ ) {
            if (fileUploadList.get(i).contains("文件上传失败！") || fileUploadList.get(i).contains("文件为空！")) {
                for( int t = 0;t < fileUploadList.size();t++ ) {
                    delete(fileUploadList.get(t));
                }
            }
        }
        //结果的map
        Map<String,Object> mapResult = new HashMap();
        for( int i = 0;i < fileUploadList.size();i++ ){
            if( fileUploadList.get(i).contains("文件上传失败！")||fileUploadList.get(i).contains("文件为空！") ){
                mapResult.put("path","");
                mapResult.put("sendfileid","");
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
        sfa.setAttachmentpath(str);
        int as = sendFileMapper.insert(sfa);
        mapResult.put("path",str);
        mapResult.put("sendfileid",sendFileid);
        mapResult.put("result","success");
        return mapResult;
    }
    /**
     * 删除文件
     */
    public void delete(String path) {
        // 得到上传服务器的物理路径
        File file = new File(path);
        file.delete();
    }

    public String singleUpload(MultipartFile file, HttpServletRequest request) {
        String pathpath = "";
        //文件名字
        String fileName =  file.getOriginalFilename();
        fileName = fileName.replaceAll(" ","");
        int last = fileName.lastIndexOf(".");

        //处理过的文件名字
        String fileNameSave = fileName.substring(0,last) + "-" + new Date().getTime() + "."+fileName.substring(last+1);
        if (!file.isEmpty()) {
            try {
                //文件存储路径
                String path = request.getSession().getServletContext().getRealPath("upload/sendfile");
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
                pathpath = request.getSession().getServletContext().getRealPath("upload/sendfile/"+fileNameSave);
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
