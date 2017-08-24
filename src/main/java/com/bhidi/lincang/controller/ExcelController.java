package com.bhidi.lincang.controller;

import com.bhidi.lincang.service.ExcelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * Created by admin on 2017/8/18.
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private ExcelServiceImp excelServiceImp;

    @ResponseBody
    @RequestMapping(value="/readExcel",method= RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String readExcel(){
        File excelFile = null;
        try{
            // 创建需要读取的文件对象
            excelFile = new File("C://Users/admin/Desktop/移民搬迁管理登记表终结版.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!excelFile.exists()){
            return "文件不存在";
        }
        if(!(excelFile.isFile() && (excelFile.getName().endsWith("xls") || excelFile.getName().endsWith("xlsx")))){
            return "文件不是Excel";
        }
        //调用service层的业务逻辑处理，进行Excel文件的读取。
        String sign = excelServiceImp.readService(excelFile);
        return sign;
    }
}
