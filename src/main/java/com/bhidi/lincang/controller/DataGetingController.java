package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.DataGetingServiceImp;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class DataGetingController {

    @Autowired
    DataGetingServiceImp dataGetingServiceImp;

    @ResponseBody
    @RequestMapping(value="/dataGeting",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String dataGeting(String fid) {
        Bank bank = dataGetingServiceImp.getBankInfoByFid(fid);
        House house = dataGetingServiceImp.getHouseInfoByFid(fid);
        List<Income> incomeList = dataGetingServiceImp.getIncomeInfosByFid(fid);
        List<Outcome> outcomeList = dataGetingServiceImp.getOutcomeInfosByFid(fid);
        Move move = dataGetingServiceImp.getMoveInfoByFid(fid);
        List<People> peopleList = dataGetingServiceImp.getPeopleInfosByFid(fid);
        //返回结果的map
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("bank",bank!=null?bank:"");
        resultMap.put("house",house!=null?house:"");
        resultMap.put("income",incomeList);
        resultMap.put("outcome",outcomeList);
        resultMap.put("move",move!=null?move:"");
        resultMap.put("people",peopleList);
        System.out.print(resultMap);
        return new Gson().toJson(resultMap);
    }
    //导出数据，先做一个基本类
    @RequestMapping(value="/exportExcel",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String exportExcel(String fid) {
        //取出来对应的数据
       /* Bank bank = dataGetingServiceImp.getBankInfoByFid(fid);
        House house = dataGetingServiceImp.getHouseInfoByFid(fid);
        List<Income> incomeList = dataGetingServiceImp.getIncomeInfosByFid(fid);
        List<Outcome> outcomeList = dataGetingServiceImp.getOutcomeInfosByFid(fid);
        Move move = dataGetingServiceImp.getMoveInfoByFid(fid);
        List<People> peopleList = dataGetingServiceImp.getPeopleInfosByFid(fid);*/
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("移民搬迁登记表");
        //创建单元格
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString("学号"));
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell c1 = row1.createCell(1);
        c1.setCellValue(new HSSFRichTextString("姓名"));



        CellRangeAddress cra1=new CellRangeAddress(0, 0, 0, 10);
        /*CellRangeAddress cra2=new CellRangeAddress(0, 1, 1, 1);
        CellRangeAddress cra3=new CellRangeAddress(0, 1, 2, 2);
        CellRangeAddress cra4=new CellRangeAddress(0, 1, 3, 3);
        CellRangeAddress cra5=new CellRangeAddress(0, 0, 4, 6);
        CellRangeAddress cra6=new CellRangeAddress(0, 0, 7, 9);*/
        sheet.addMergedRegion(cra1);
       /* sheet.addMergedRegion(cra2);
        sheet.addMergedRegion(cra3);
        sheet.addMergedRegion(cra4);
        sheet.addMergedRegion(cra5);
        sheet.addMergedRegion(cra6);*/
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream("d:/student.xls");
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
