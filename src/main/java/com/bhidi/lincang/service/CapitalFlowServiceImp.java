package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.dao.CapitalFlowMapper;
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
public class CapitalFlowServiceImp implements CapitalFlowServiceInf {
    @Autowired
    CapitalFlowMapper capitalFlowMapper;

    /*public int submitData(Map map){
        return capitalFlowMapper.submitData(map);
    }*/

    public CapitalFlow getCapitalDataById(String id) {
        return capitalFlowMapper.queryCapitalDataById(id);
    }

    public int setCatipalDataById(Map<String,Object> map){
        return capitalFlowMapper.updateCatipalDataById(map);
    }

    /**
     *获取capitalFlow的capitalFlowid
     * @return
     */
    public String getLastCapitalFlowId() {
        return capitalFlowMapper.selectLastCapitalFlowId();
    }

    public Map<String, Object> saveCapitalFlow(Map<String, Object> mapCondition) {
        CapitalFlow cf = (CapitalFlow)mapCondition.get("capitalFlow");
        MultipartFile[] files = (MultipartFile[])mapCondition.get("files");
        HttpServletRequest request = (HttpServletRequest)mapCondition.get("request");
        String str = "";
        List<String> fileUploadList =  multipleUpload(files,request);
        Date now = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf1.format(now);
        String lastCapitalFlowId = getLastCapitalFlowId();
        String capitalFlowId = "";
        if(lastCapitalFlowId==null || lastCapitalFlowId==""){
            capitalFlowId = today+"0001"+"";
        } else {
            if( today.equals(lastCapitalFlowId.substring(0,8)) ){
                BigDecimal bd = new BigDecimal(lastCapitalFlowId);
                capitalFlowId = bd.add(new BigDecimal(1)).toString();
            } else {
                capitalFlowId = today+"0001"+"";
            }
        }
        cf.setCapitalflowid(capitalFlowId);
        cf.setCreate_time(sdf.format(now));

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
        cf.setReport_attachment(str);
        int as = capitalFlowMapper.submitData(cf);
        mapResult.put("path",str);
        mapResult.put("capitalFlowId",capitalFlowId);
        mapResult.put("result","success");
        return mapResult;
    }

    public Map<String, Object> shiJuSubmit(Map<String, Object> mapCondition) {
        CapitalFlow cf = (CapitalFlow)mapCondition.get("capitalFlow");
        MultipartFile[] files = (MultipartFile[])mapCondition.get("files");
        HttpServletRequest request = (HttpServletRequest)mapCondition.get("request");
        String str = "";
        List<String> fileUploadList =  multipleUpload(files,request);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cf.setFinance_time(sdf.format(now));

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
        cf.setCaiwuattachment(str);
        int as = capitalFlowMapper.updateCapitalDataByCapitalFlow(cf);
        mapResult.put("path",str);
        //mapResult.put("capitalFlowId",capitalFlowId);
        mapResult.put("result","success");
        return mapResult;
    }

    public Map<String, Object> quxianSaveCapitalFlow(Map<String, Object> mapCondition) {
        CapitalFlow cf = (CapitalFlow)mapCondition.get("capitalFlow");
        MultipartFile[] files = (MultipartFile[])mapCondition.get("files");
        HttpServletRequest request = (HttpServletRequest)mapCondition.get("request");
        String str = "";
        List<String> fileUploadList =  multipleUpload(files,request);
        Date now = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf1.format(now);
        String lastCapitalFlowId = getLastCapitalFlowId();
        String capitalFlowId = "";
        if(lastCapitalFlowId==null || lastCapitalFlowId==""){
            capitalFlowId = today+"0001"+"";
        } else {
            if( today.equals(lastCapitalFlowId.substring(0,8)) ){
                BigDecimal bd = new BigDecimal(lastCapitalFlowId);
                capitalFlowId = bd.add(new BigDecimal(1)).toString();
            } else {
                capitalFlowId = today+"0001"+"";
            }
        }
        cf.setCapitalflowid(capitalFlowId);
        cf.setCreate_time(sdf.format(now));

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
        cf.setQuxianattachment(str);
        int as = capitalFlowMapper.submitData(cf);
        mapResult.put("path",str);
        mapResult.put("capitalFlowId",capitalFlowId);
        mapResult.put("result","success");
        return mapResult;
    }

    public int deleteCapitalFlow(String capitalflowid) {
        return capitalFlowMapper.deleteCapitalFlow(capitalflowid);
    }

    public int selectNumOfUnReadCapitalFlow(String name) {
        return capitalFlowMapper.selectNumOfUnReadCapitalFlow(name);
    }
    public List<DepartmentAndStaff> getDepartmentAndStaffs() {
        /*List<DepartmentAndStaffs> list = new ArrayList<DepartmentAndStaffs>();*/
        List<DepartmentAndStaff> departmentAndStaffList = capitalFlowMapper.selectDepartmentAndStaff();
        /*Set<String> departmentSet = new HashSet<String>();
        for(DepartmentAndStaff das:departmentAndStaffList){
            departmentSet.add(das.getDepartment());
        }
        for(String ds:departmentSet){
            DepartmentAndStaffs dass = new DepartmentAndStaffs();
            List<String> staffList = new ArrayList<String>();
            dass.setDepartment(ds);
            for(DepartmentAndStaff das:departmentAndStaffList){
                if(ds.equals(das.getDepartment())){
                    staffList.add(das.getName());
                }
            }
            dass.setStaffList(staffList);
            list.add(dass);
        }*/
        return departmentAndStaffList;
    }

    public int setQuXianReceiveMessage(List<QuXianReceiveMessage> list) {
        return capitalFlowMapper.setQuXianReceiveMessage(list);
    }

    public QuXianReceiveMessage getQuXianReceiveMessage(Map<String,Object> mapCondition) {
        return capitalFlowMapper.getQuXianReceiveMessage(mapCondition);
    }

    public CapitalFlow getNotice(String capitalflowid) {
        return capitalFlowMapper.getNotice(capitalflowid);
    }

    public int updateQuXianReceiveMessage(Map<String,Object> mapCondition) {
        return capitalFlowMapper.updateQuXianReceiveMessage(mapCondition);
    }

    public int updateCapitalFlow(Map<String,Object> mapCondition) {
        return capitalFlowMapper.updateCapitalFlow(mapCondition);
    }

    public int deleteMessage(String capitalflowid) {
        return capitalFlowMapper.deleteMessage(capitalflowid);
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
                String path = request.getSession().getServletContext().getRealPath("upload/capitalflow");
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
                pathpath = request.getSession().getServletContext().getRealPath("upload/capitalflow/"+fileNameSave);
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
