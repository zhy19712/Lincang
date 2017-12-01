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
        Bank bank = dataGetingServiceImp.getBankInfoByFid(fid);
        List<People> peopleList = dataGetingServiceImp.getPeopleInfosByFid(fid);
        /*House house = dataGetingServiceImp.getHouseInfoByFid(fid);
        List<Income> incomeList = dataGetingServiceImp.getIncomeInfosByFid(fid);
        List<Outcome> outcomeList = dataGetingServiceImp.getOutcomeInfosByFid(fid);
        Move move = dataGetingServiceImp.getMoveInfoByFid(fid);
        */
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 13);//设置字体大小
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("移民搬迁登记表");
        sheet.setColumnWidth(0, 3766);
        sheet.setColumnWidth(1, 3766);
        sheet.setColumnWidth(2, 3766);
        sheet.setColumnWidth(3, 3766);
        sheet.setColumnWidth(4, 3766);
        sheet.setColumnWidth(5, 3766);
        sheet.setColumnWidth(6, 3766);
        sheet.setColumnWidth(7, 3766);
        sheet.setColumnWidth(8, 3766);

        CellRangeAddress cra0=new CellRangeAddress(0, 0, 0, 8);
        sheet.addMergedRegion(cra0);

        //创建单元格
        HSSFRow row = sheet.createRow(0);
        HSSFCell c0 = row.createCell(0);
        c0.setCellValue(new HSSFRichTextString("移民搬迁登记表"));
        c0.setCellStyle(cellStyle);


        CellRangeAddress cra1=new CellRangeAddress(1, 2, 0, 0);
        sheet.addMergedRegion(cra1);
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell c10 = row1.createCell(0);
        c10.setCellValue(new HSSFRichTextString("户主信息"));
        c10.setCellStyle(cellStyle);
        HSSFCell c11 = row1.createCell(1);
        c11.setCellValue(new HSSFRichTextString("所属水库"));
        c11.setCellStyle(cellStyle);
        HSSFCell c12 = row1.createCell(2);
        String reservoir = "";
        if(peopleList !=null && peopleList.size()>0){
            reservoir = peopleList.get(0).getReservoir();
        }
        c12.setCellValue(new HSSFRichTextString(reservoir));
        c12.setCellStyle(cellStyle);
        HSSFCell c13 = row1.createCell(3);
        c13.setCellValue(new HSSFRichTextString("安置点"));
        c13.setCellStyle(cellStyle);
        HSSFCell c14 = row1.createCell(4);
        String location = "";
        if(peopleList !=null && peopleList.size()>0){
            location = peopleList.get(0).getLocation();
        }
        c14.setCellValue(new HSSFRichTextString(location));
        c14.setCellStyle(cellStyle);
        HSSFCell c15 = row1.createCell(5);
        c15.setCellValue(new HSSFRichTextString("户主姓名"));
        c15.setCellStyle(cellStyle);
        String masterName = "";
        HSSFCell c16 = row1.createCell(6);
        if(peopleList !=null && peopleList.size()>0){
            for(int i = 0; i < peopleList.size();i++){
                if(peopleList.get(i).getMaster() == 1){
                    masterName = peopleList.get(i).getName();
                }
            }
        }
        c16.setCellValue(new HSSFRichTextString(masterName));
        c16.setCellStyle(cellStyle);
        HSSFCell c17 = row1.createCell(7);
        c17.setCellValue(new HSSFRichTextString("联系电话"));
        c17.setCellStyle(cellStyle);
        HSSFCell c18 = row1.createCell(8);
        String phone = "";
        if(peopleList !=null && peopleList.size()>0){
            phone = peopleList.get(0).getPhone();
        }
        c18.setCellValue(new HSSFRichTextString(phone));
        c18.setCellStyle(cellStyle);

        CellRangeAddress cra2=new CellRangeAddress(2, 2, 6, 8);
        sheet.addMergedRegion(cra2);
        HSSFRow row2 = sheet.createRow(2);
        HSSFCell c21 = row2.createCell(1);
        c21.setCellValue(new HSSFRichTextString("开户人姓名"));
        c21.setCellStyle(cellStyle);
        HSSFCell c22 = row2.createCell(2);
        String accountNmae = "";
        if(bank != null){
            accountNmae = bank.getAccount_name();
        }
        c22.setCellValue(new HSSFRichTextString(accountNmae));
        c22.setCellStyle(cellStyle);
        HSSFCell c23 = row2.createCell(3);
        c23.setCellValue(new HSSFRichTextString("开户行名称"));
        c23.setCellStyle(cellStyle);
        HSSFCell c24 = row2.createCell(4);
        String bankName = "";
        if(bank != null){
            bankName = bank.getBank_name();
        }
        c24.setCellValue(new HSSFRichTextString(bankName));
        c24.setCellStyle(cellStyle);
        HSSFCell c25 = row2.createCell(5);
        c25.setCellValue(new HSSFRichTextString("银行卡号"));
        c25.setCellStyle(cellStyle);
        HSSFCell c26 = row2.createCell(6);
        String accountNumber = "";
        if(accountNumber != null){
            accountNumber = bank.getAccount_number();
        }
        c26.setCellValue(new HSSFRichTextString(accountNumber));
        c26.setCellStyle(cellStyle);


        //给合并的单元格加上边框
        setRegionStyle(sheet,cra0,cellStyle);
        setRegionStyle(sheet,cra1,cellStyle);
        setRegionStyle(sheet,cra2,cellStyle);

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
    public void setRegionStyle(HSSFSheet sheet, CellRangeAddress region,
                                      HSSFCellStyle cs) {

         for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {

            HSSFRow row = sheet.getRow(i);
            if (row == null)
                row = sheet.createRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);

            }
        }
    }
}
