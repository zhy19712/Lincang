package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.DataGetingServiceImp;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
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
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("学生表");
        //创建单元格
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString("学号"));
        HSSFCell c1 = row.createCell(1);
        c1.setCellValue(new HSSFRichTextString("姓名"));
        HSSFCell c2 = row.createCell(2);
        c2.setCellValue(new HSSFRichTextString("性别"));
        HSSFCell c3 = row.createCell(3);
        c3.setCellValue(new HSSFRichTextString("年龄"));
        HSSFCell c4 = row.createCell(4);
        c4.setCellValue(new HSSFRichTextString("2015年分数"));
        HSSFCell c5 = row.createCell(7);
        c5.setCellValue(new HSSFRichTextString("2014年分数"));
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell c6 = row1.createCell(4);
        c6.setCellValue(new HSSFRichTextString("语文"));
        HSSFCell c7 = row1.createCell(5);
        c7.setCellValue(new HSSFRichTextString("数学"));
        HSSFCell c8 = row1.createCell(6);
        c8.setCellValue(new HSSFRichTextString("外语"));
        HSSFCell c9 = row1.createCell(7);
        c9.setCellValue(new HSSFRichTextString("语文"));
        HSSFCell c10 = row1.createCell(8);
        c10.setCellValue(new HSSFRichTextString("数学"));
        HSSFCell c11 = row1.createCell(9);
        c11.setCellValue(new HSSFRichTextString("外语"));


        CellRangeAddress cra1=new CellRangeAddress(0, 1, 0, 0);
        CellRangeAddress cra2=new CellRangeAddress(0, 1, 1, 1);
        CellRangeAddress cra3=new CellRangeAddress(0, 1, 2, 2);
        CellRangeAddress cra4=new CellRangeAddress(0, 1, 3, 3);
        CellRangeAddress cra5=new CellRangeAddress(0, 0, 4, 6);
        CellRangeAddress cra6=new CellRangeAddress(0, 0, 7, 9);
        /*Region region1 = new Region(0, (short)0, 1, (short)0);
        Region region2 = new Region(0, (short)1, 1, (short)1);
        Region region3 = new Region(0, (short)2, 1, (short)2);
        Region region4 = new Region(0, (short)3, 1, (short)3);
        Region region5 = new Region(0, (short)4, 0, (short)6);
        Region region6 = new Region(0, (short)7, 0, (short)9);*/
        sheet.addMergedRegion(cra1);
        sheet.addMergedRegion(cra2);
        sheet.addMergedRegion(cra3);
        sheet.addMergedRegion(cra4);
        sheet.addMergedRegion(cra5);
        sheet.addMergedRegion(cra6);

        try {
            FileOutputStream stream = new FileOutputStream("d:/student.xls");
            workbook.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
