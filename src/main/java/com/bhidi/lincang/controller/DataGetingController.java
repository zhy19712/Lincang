package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.DataGetingServiceImp;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
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

    @RequestMapping(value="/exportExcel",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResponseEntity<byte[]> exportExcel(String fid){
        if(fid.contains("KQ")){
            return exportKuQuExcel(fid);
        } else {
            return exportBanQianExcel(fid);
        }
    }


    //导出移民搬迁表数据
    @RequestMapping(value="/exportBanQianExcel",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResponseEntity<byte[]> exportBanQianExcel(String fid) {
        //取出来对应的数据
        Bank bank = dataGetingServiceImp.getBankInfoByFid(fid);
        List<People> peopleList = dataGetingServiceImp.getPeopleInfosByFid(fid);
        Move move = dataGetingServiceImp.getMoveInfoByFid(fid);
        House house = dataGetingServiceImp.getHouseInfoByFid(fid);
        List<Income> incomeList = dataGetingServiceImp.getIncomeInfosByFid(fid);
        List<Outcome> outcomeList = dataGetingServiceImp.getOutcomeInfosByFid(fid);
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
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        HSSFFont font1 = workbook.createFont();
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short) 13);//设置字体大小
        cellStyle1.setFont(font1);
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
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
        //c0.setCellStyle(cellStyle1);


        CellRangeAddress cra1=new CellRangeAddress(1, 2, 0, 0);
        sheet.addMergedRegion(cra1);
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell c10 = row1.createCell(0);
        c10.setCellValue(new HSSFRichTextString("户主信息"));
        //c10.setCellStyle(cellStyle1);
        HSSFCell c11 = row1.createCell(1);
        c11.setCellValue(new HSSFRichTextString("所属水库"));
        c11.setCellStyle(cellStyle1);
        HSSFCell c12 = row1.createCell(2);
        String reservoir = "";
        if(peopleList !=null && peopleList.size()>0){
            reservoir = peopleList.get(0).getReservoir();
        }
        c12.setCellValue(new HSSFRichTextString(reservoir));
        c12.setCellStyle(cellStyle);
        HSSFCell c13 = row1.createCell(3);
        c13.setCellValue(new HSSFRichTextString("安置点"));
        c13.setCellStyle(cellStyle1);
        HSSFCell c14 = row1.createCell(4);
        String location = "";
        if(peopleList !=null && peopleList.size()>0){
            location = peopleList.get(0).getLocation();
        }
        c14.setCellValue(new HSSFRichTextString(location));
        c14.setCellStyle(cellStyle);
        HSSFCell c15 = row1.createCell(5);
        c15.setCellValue(new HSSFRichTextString("户主姓名"));
        c15.setCellStyle(cellStyle1);
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
        c17.setCellStyle(cellStyle1);
        HSSFCell c18 = row1.createCell(8);
        String phone = "";
        if(peopleList !=null && peopleList.size()>0){
            for(int i = 0; i < peopleList.size();i++){
                if(peopleList.get(i).getMaster() == 1){
                    phone = peopleList.get(i).getPhone();
                }
            }
        }
        c18.setCellValue(new HSSFRichTextString(phone));
        c18.setCellStyle(cellStyle);

        CellRangeAddress cra2=new CellRangeAddress(2, 2, 6, 8);
        sheet.addMergedRegion(cra2);
        HSSFRow row2 = sheet.createRow(2);
        HSSFCell c21 = row2.createCell(1);
        c21.setCellValue(new HSSFRichTextString("开户人姓名"));
        c21.setCellStyle(cellStyle1);
        HSSFCell c22 = row2.createCell(2);
        String accountNmae = "";
        if(bank != null){
            accountNmae = bank.getAccount_name();
        }
        c22.setCellValue(new HSSFRichTextString(accountNmae));
        c22.setCellStyle(cellStyle);
        HSSFCell c23 = row2.createCell(3);
        c23.setCellValue(new HSSFRichTextString("开户行名称"));
        c23.setCellStyle(cellStyle1);
        HSSFCell c24 = row2.createCell(4);
        String bankName = "";
        if(bank != null){
            bankName = bank.getBank_name();
        }
        c24.setCellValue(new HSSFRichTextString(bankName));
        c24.setCellStyle(cellStyle);
        HSSFCell c25 = row2.createCell(5);
        c25.setCellValue(new HSSFRichTextString("银行卡号"));
        c25.setCellStyle(cellStyle1);
        HSSFCell c26 = row2.createCell(6);
        String accountNumber = "";
        if(accountNumber != null){
            accountNumber = bank.getAccount_number();
        }
        c26.setCellValue(new HSSFRichTextString(accountNumber));
        c26.setCellStyle(cellStyle);
        //家庭信息部分
        int homeSize = 4;
        if(peopleList !=null && peopleList.size()>0){
            homeSize = peopleList.size();
        }
        CellRangeAddress cra3=new CellRangeAddress(3, 3+homeSize, 0, 0);
        sheet.addMergedRegion(cra3);
        HSSFRow row3 = sheet.createRow(3);
        HSSFCell c30 = row3.createCell(0);
        c30.setCellValue(new HSSFRichTextString("家庭信息"));
        HSSFCell c31 = row3.createCell(1);
        c31.setCellValue(new HSSFRichTextString("姓名"));
        c31.setCellStyle(cellStyle1);
        CellRangeAddress cra4=new CellRangeAddress(3, 3, 2, 3);
        sheet.addMergedRegion(cra4);
        HSSFCell c32 = row3.createCell(2);
        c32.setCellValue(new HSSFRichTextString("身份证号码"));
        HSSFCell c34 = row3.createCell(4);
        c34.setCellValue(new HSSFRichTextString("性别"));
        c34.setCellStyle(cellStyle1);
        HSSFCell c35 = row3.createCell(5);
        c35.setCellValue(new HSSFRichTextString("民族"));
        c35.setCellStyle(cellStyle1);
        HSSFCell c36 = row3.createCell(6);
        c36.setCellValue(new HSSFRichTextString("与户主关系"));
        c36.setCellStyle(cellStyle1);
        HSSFCell c37 = row3.createCell(7);
        c37.setCellValue(new HSSFRichTextString("文化程度"));
        c37.setCellStyle(cellStyle1);
        HSSFCell c38 = row3.createCell(8);
        c38.setCellValue(new HSSFRichTextString("职业"));
        c38.setCellStyle(cellStyle1);
        //开始循环
        for(int i = 4; i < 4+peopleList.size();i++){
            CellRangeAddress cra5=new CellRangeAddress(i, i, 2, 3);
            sheet.addMergedRegion(cra5);
            HSSFRow row4 = sheet.createRow(i);
            HSSFCell c41 = row4.createCell(1);
            c41.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getName()));
            c41.setCellStyle(cellStyle);
            HSSFCell c42 = row4.createCell(2);
            c42.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getPid()));
            c42.setCellStyle(cellStyle);
            HSSFCell c44 = row4.createCell(4);
            c44.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getGender()));
            c44.setCellStyle(cellStyle);
            HSSFCell c45 = row4.createCell(5);
            c45.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getRace()));
            c45.setCellStyle(cellStyle);
            HSSFCell c46 = row4.createCell(6);
            c46.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getRelation()));
            c46.setCellStyle(cellStyle);
            HSSFCell c47 = row4.createCell(7);
            c47.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getEducation()));
            c47.setCellStyle(cellStyle);
            HSSFCell c48 = row4.createCell(8);
            c48.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getProfession()));
            c48.setCellStyle(cellStyle);
            setRegionStyle(sheet,cra5,cellStyle);
        }
        //搬迁部分
        int familyLastRow = 3+homeSize;
        CellRangeAddress cra6=new CellRangeAddress(familyLastRow+1, familyLastRow+3, 0, 0);
        sheet.addMergedRegion(cra6);
        HSSFRow row8 = sheet.createRow(familyLastRow+1);
        HSSFCell c80 = row8.createCell(0);
        c80.setCellValue(new HSSFRichTextString("搬迁信息"));
        HSSFCell c81 = row8.createCell(1);
        c81.setCellValue(new HSSFRichTextString("分类"));
        c81.setCellStyle(cellStyle1);
        HSSFCell c82 = row8.createCell(2);
        c82.setCellValue(new HSSFRichTextString("州市"));
        c82.setCellStyle(cellStyle1);
        HSSFCell c83 = row8.createCell(3);
        c83.setCellValue(new HSSFRichTextString("区县"));
        c83.setCellStyle(cellStyle1);
        HSSFCell c84 = row8.createCell(4);
        c84.setCellValue(new HSSFRichTextString("乡镇"));
        c84.setCellStyle(cellStyle1);
        HSSFCell c85 = row8.createCell(5);
        c85.setCellValue(new HSSFRichTextString("村"));
        c85.setCellStyle(cellStyle1);
        HSSFCell c86 = row8.createCell(6);
        c86.setCellValue(new HSSFRichTextString("组"));
        c86.setCellStyle(cellStyle1);
        CellRangeAddress cra7=new CellRangeAddress(familyLastRow+1, familyLastRow+1, 7, 8);
        sheet.addMergedRegion(cra7);
        HSSFCell c87 = row8.createCell(7);
        c87.setCellValue(new HSSFRichTextString("备注"));
        HSSFRow row9 = sheet.createRow(familyLastRow+2);
        HSSFCell c91 = row9.createCell(1);
        c91.setCellValue(new HSSFRichTextString("迁出地"));
        c91.setCellStyle(cellStyle1);
        HSSFCell c92 = row9.createCell(2);
        c92.setCellValue(new HSSFRichTextString(move.getFrom_city()));
        c92.setCellStyle(cellStyle);
        HSSFCell c93 = row9.createCell(3);
        c93.setCellValue(new HSSFRichTextString(move.getFrom_district()));
        c93.setCellStyle(cellStyle);
        HSSFCell c94 = row9.createCell(4);
        c94.setCellValue(new HSSFRichTextString(move.getFrom_town()));
        c94.setCellStyle(cellStyle);
        HSSFCell c95 = row9.createCell(5);
        c95.setCellValue(new HSSFRichTextString(move.getFrom_village()));
        c95.setCellStyle(cellStyle);
        HSSFCell c96 = row9.createCell(6);
        c96.setCellValue(new HSSFRichTextString(move.getFrom_group()));
        c96.setCellStyle(cellStyle);
        CellRangeAddress cra8=new CellRangeAddress(familyLastRow+2, familyLastRow+2, 7, 8);
        sheet.addMergedRegion(cra8);
        HSSFCell c97 = row9.createCell(7);
        c97.setCellValue(new HSSFRichTextString(move.getFrom_remark()));
        HSSFRow row10 = sheet.createRow(familyLastRow+3);
        HSSFCell c101 = row10.createCell(1);
        c101.setCellValue(new HSSFRichTextString("迁入地"));
        c101.setCellStyle(cellStyle1);
        HSSFCell c102 = row10.createCell(2);
        c102.setCellValue(new HSSFRichTextString(move.getTo_city()));
        c102.setCellStyle(cellStyle);
        HSSFCell c103 = row10.createCell(3);
        c103.setCellValue(new HSSFRichTextString(move.getTo_district()));
        c103.setCellStyle(cellStyle);
        HSSFCell c104 = row10.createCell(4);
        c104.setCellValue(new HSSFRichTextString(move.getTo_town()));
        c104.setCellStyle(cellStyle);
        HSSFCell c105 = row10.createCell(5);
        c105.setCellValue(new HSSFRichTextString(move.getTo_village()));
        c105.setCellStyle(cellStyle);
        HSSFCell c106 = row10.createCell(6);
        c106.setCellValue(new HSSFRichTextString(move.getTo_group()));
        c106.setCellStyle(cellStyle);
        CellRangeAddress cra9=new CellRangeAddress(familyLastRow+3, familyLastRow+3, 7, 8);
        sheet.addMergedRegion(cra9);
        HSSFCell c107 = row10.createCell(7);
        c107.setCellValue(new HSSFRichTextString(move.getTo_remark()));
        //住房部分
        int houseRowNum = familyLastRow+4;
        CellRangeAddress cra10=new CellRangeAddress(houseRowNum, houseRowNum+2, 0, 0);
        sheet.addMergedRegion(cra10);
        HSSFRow rowHouse = sheet.createRow(houseRowNum);
        HSSFCell cHouse0 = rowHouse.createCell(0);
        cHouse0.setCellValue(new HSSFRichTextString("住房情况"));
        HSSFCell cHouse1 = rowHouse.createCell(1);
        cHouse1.setCellValue(new HSSFRichTextString("分类"));
        cHouse1.setCellStyle(cellStyle1);
        HSSFCell cHouse2 = rowHouse.createCell(2);
        cHouse2.setCellValue(new HSSFRichTextString("宅基地面积"));
        cHouse2.setCellStyle(cellStyle1);
        HSSFCell cHouse3 = rowHouse.createCell(3);
        cHouse3.setCellValue(new HSSFRichTextString("砖混结构"));
        cHouse3.setCellStyle(cellStyle1);
        HSSFCell cHouse4 = rowHouse.createCell(4);
        cHouse4.setCellValue(new HSSFRichTextString("砖木结构"));
        cHouse4.setCellStyle(cellStyle1);
        HSSFCell cHouse5 = rowHouse.createCell(5);
        cHouse5.setCellValue(new HSSFRichTextString("土木结构"));
        cHouse5.setCellStyle(cellStyle1);
        HSSFCell cHouse6 = rowHouse.createCell(6);
        cHouse6.setCellValue(new HSSFRichTextString("木（竹）结构"));
        cHouse6.setCellStyle(cellStyle1);
        HSSFCell cHouse7 = rowHouse.createCell(7);
        cHouse7.setCellValue(new HSSFRichTextString("简易房"));
        cHouse7.setCellStyle(cellStyle1);
        HSSFCell cHouse8 = rowHouse.createCell(8);
        cHouse8.setCellValue(new HSSFRichTextString("备注"));
        cHouse8.setCellStyle(cellStyle1);
        HSSFRow rowHouse1 = sheet.createRow(houseRowNum+1);
        HSSFCell cHouse11 = rowHouse1.createCell(1);
        cHouse11.setCellValue(new HSSFRichTextString("主房"));
        cHouse11.setCellStyle(cellStyle1);
        HSSFCell cHouse12 = rowHouse1.createCell(2);
        cHouse12.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_size()));
        cHouse12.setCellStyle(cellStyle);
        HSSFCell cHouse13 = rowHouse1.createCell(3);
        cHouse13.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure1()));
        cHouse13.setCellStyle(cellStyle);
        HSSFCell cHouse14 = rowHouse1.createCell(4);
        cHouse14.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure2()));
        cHouse14.setCellStyle(cellStyle);
        HSSFCell cHouse15 = rowHouse1.createCell(5);
        cHouse15.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure3()));
        cHouse15.setCellStyle(cellStyle);
        HSSFCell cHouse16 = rowHouse1.createCell(6);
        cHouse16.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure4()));
        cHouse16.setCellStyle(cellStyle);
        HSSFCell cHouse17 = rowHouse1.createCell(7);
        cHouse17.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure5()));
        cHouse17.setCellStyle(cellStyle);
        HSSFCell cHouse18 = rowHouse1.createCell(8);
        cHouse18.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_remark()));
        cHouse18.setCellStyle(cellStyle);
        HSSFRow rowHouse2 = sheet.createRow(houseRowNum+2);
        HSSFCell cHouse21 = rowHouse2.createCell(1);
        cHouse21.setCellValue(new HSSFRichTextString("附属房"));
        cHouse21.setCellStyle(cellStyle1);
        HSSFCell cHouse22 = rowHouse2.createCell(2);
        cHouse22.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_size()));
        cHouse22.setCellStyle(cellStyle);
        HSSFCell cHouse23 = rowHouse2.createCell(3);
        cHouse23.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure1()));
        cHouse23.setCellStyle(cellStyle);
        HSSFCell cHouse24 = rowHouse2.createCell(4);
        cHouse24.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure2()));
        cHouse24.setCellStyle(cellStyle);
        HSSFCell cHouse25 = rowHouse2.createCell(5);
        cHouse25.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure3()));
        cHouse25.setCellStyle(cellStyle);
        HSSFCell cHouse26 = rowHouse2.createCell(6);
        cHouse26.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure4()));
        cHouse26.setCellStyle(cellStyle);
        HSSFCell cHouse27 = rowHouse2.createCell(7);
        cHouse27.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure5()));
        cHouse27.setCellStyle(cellStyle);
        HSSFCell cHouse28 = rowHouse2.createCell(8);
        cHouse28.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_remark()));
        cHouse28.setCellStyle(cellStyle);
        //收入情况部分
        int incomeRowNum = houseRowNum+3;
        CellRangeAddress cra11=new CellRangeAddress(incomeRowNum, incomeRowNum+14, 0, 0);
        sheet.addMergedRegion(cra11);
        HSSFRow rowIncome0 = sheet.createRow(incomeRowNum);
        HSSFCell cIncome0 = rowIncome0.createCell(0);
        cIncome0.setCellValue(new HSSFRichTextString("收入情况"));
        HSSFCell cIncome01 = rowIncome0.createCell(1);
        cIncome01.setCellValue(new HSSFRichTextString("分类"));
        cIncome01.setCellStyle(cellStyle1);
        HSSFCell cIncome02 = rowIncome0.createCell(2);
        cIncome02.setCellValue(new HSSFRichTextString("内容"));
        cIncome02.setCellStyle(cellStyle1);
        HSSFCell cIncome03 = rowIncome0.createCell(3);
        cIncome03.setCellValue(new HSSFRichTextString("数量"));
        cIncome03.setCellStyle(cellStyle1);
        HSSFCell cIncome04 = rowIncome0.createCell(4);
        cIncome04.setCellValue(new HSSFRichTextString("单价"));
        cIncome04.setCellStyle(cellStyle1);
        HSSFCell cIncome05 = rowIncome0.createCell(5);
        cIncome05.setCellValue(new HSSFRichTextString("小计"));
        cIncome05.setCellStyle(cellStyle1);
        CellRangeAddress cra12=new CellRangeAddress(incomeRowNum, incomeRowNum, 6, 8);
        sheet.addMergedRegion(cra12);
        HSSFCell cIncome06 = rowIncome0.createCell(6);
        cIncome06.setCellValue(new HSSFRichTextString("备注"));

        Income zhu = null;
        Income niu = null;
        Income yang = null;
        Income ji = null;
        Income ya = null;
        Income yuye = null;
        Income ruye = null;
        Income yangzhiother = null;
        Income liangshi = null;
        Income shucai = null;
        Income linguo = null;
        Income zhongzhiother = null;
        Income laowuchoulao = null;
        Income fangwuzulin = null;
        if(incomeList !=null || incomeList.size()>0){
            for(int i=0;i<incomeList.size();i++){
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "猪".equals(incomeList.get(i).getIncome_cate())){
                    zhu = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "牛".equals(incomeList.get(i).getIncome_cate())){
                    niu = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "羊".equals(incomeList.get(i).getIncome_cate())){
                    yang = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "鸡".equals(incomeList.get(i).getIncome_cate())){
                    ji = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "鸭".equals(incomeList.get(i).getIncome_cate())){
                    ya = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "渔业".equals(incomeList.get(i).getIncome_cate())){
                    yuye = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "乳业".equals(incomeList.get(i).getIncome_cate())){
                    ruye = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "其他".equals(incomeList.get(i).getIncome_cate())){
                    yangzhiother = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "粮食".equals(incomeList.get(i).getIncome_cate())){
                    liangshi = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "蔬菜".equals(incomeList.get(i).getIncome_cate())){
                    shucai = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "林果".equals(incomeList.get(i).getIncome_cate())){
                    linguo = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "其他".equals(incomeList.get(i).getIncome_cate())){
                    zhongzhiother = incomeList.get(i);
                }
                if("其他收入".equals(incomeList.get(i).getIncome_source()) & "劳务酬劳".equals(incomeList.get(i).getIncome_cate())){
                    laowuchoulao = incomeList.get(i);
                }
                if("其他收入".equals(incomeList.get(i).getIncome_source()) & "房、耕地租赁".equals(incomeList.get(i).getIncome_cate())){
                    fangwuzulin = incomeList.get(i);
                }
            }
        }
        HSSFRow rowIncome1 = sheet.createRow(incomeRowNum+1);
        CellRangeAddress cra13=new CellRangeAddress(incomeRowNum+1, incomeRowNum+8, 1, 1);
        sheet.addMergedRegion(cra13);
        HSSFCell cIncome11 = rowIncome1.createCell(1);
        cIncome11.setCellValue(new HSSFRichTextString("养殖业收入"));
        HSSFCell cIncome12 = rowIncome1.createCell(2);
        cIncome12.setCellValue(new HSSFRichTextString("猪"));
        cIncome12.setCellStyle(cellStyle1);
        HSSFCell cIncome13 = rowIncome1.createCell(3);
        cIncome13.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getIncome_quantity()));
        cIncome13.setCellStyle(cellStyle);
        HSSFCell cIncome14 = rowIncome1.createCell(4);
        cIncome14.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getIncome_unit()));
        cIncome14.setCellStyle(cellStyle);
        HSSFCell cIncome15 = rowIncome1.createCell(5);
        cIncome15.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getIncome_sum()));
        cIncome15.setCellStyle(cellStyle);
        CellRangeAddress cra14=new CellRangeAddress(incomeRowNum+1, incomeRowNum+1, 6, 8);
        sheet.addMergedRegion(cra14);
        HSSFCell cIncome16 = rowIncome1.createCell(6);
        cIncome16.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getRemark()));
        cIncome16.setCellStyle(cellStyle);
        //牛
        HSSFRow rowIncome2 = sheet.createRow(incomeRowNum+2);
        HSSFCell cIncome22 = rowIncome2.createCell(2);
        cIncome22.setCellValue(new HSSFRichTextString("牛"));
        cIncome22.setCellStyle(cellStyle1);
        HSSFCell cIncome23 = rowIncome2.createCell(3);
        cIncome23.setCellValue(new HSSFRichTextString(niu == null?"":niu.getIncome_quantity()));
        cIncome23.setCellStyle(cellStyle);
        HSSFCell cIncome24 = rowIncome2.createCell(4);
        cIncome24.setCellValue(new HSSFRichTextString(niu == null?"":niu.getIncome_unit()));
        cIncome24.setCellStyle(cellStyle);
        HSSFCell cIncome25 = rowIncome2.createCell(5);
        cIncome25.setCellValue(new HSSFRichTextString(niu == null?"":niu.getIncome_sum()));
        cIncome25.setCellStyle(cellStyle);
        CellRangeAddress cra15=new CellRangeAddress(incomeRowNum+2, incomeRowNum+2, 6, 8);
        sheet.addMergedRegion(cra15);
        HSSFCell cIncome26 = rowIncome2.createCell(6);
        cIncome26.setCellValue(new HSSFRichTextString(niu == null?"":niu.getRemark()));
        cIncome26.setCellStyle(cellStyle);
        //羊
        HSSFRow rowIncome3 = sheet.createRow(incomeRowNum+3);
        HSSFCell cIncome32 = rowIncome3.createCell(2);
        cIncome32.setCellValue(new HSSFRichTextString("羊"));
        cIncome32.setCellStyle(cellStyle1);
        HSSFCell cIncome33 = rowIncome3.createCell(3);
        cIncome33.setCellValue(new HSSFRichTextString(yang == null?"":yang.getIncome_quantity()));
        cIncome33.setCellStyle(cellStyle);
        HSSFCell cIncome34 = rowIncome3.createCell(4);
        cIncome34.setCellValue(new HSSFRichTextString(yang == null?"":yang.getIncome_unit()));
        cIncome34.setCellStyle(cellStyle);
        HSSFCell cIncome35 = rowIncome3.createCell(5);
        cIncome35.setCellValue(new HSSFRichTextString(yang == null?"":yang.getIncome_sum()));
        cIncome35.setCellStyle(cellStyle);
        CellRangeAddress cra16=new CellRangeAddress(incomeRowNum+3, incomeRowNum+3, 6, 8);
        sheet.addMergedRegion(cra16);
        HSSFCell cIncome36 = rowIncome3.createCell(6);
        cIncome36.setCellValue(new HSSFRichTextString(yang == null?"":yang.getRemark()));
        cIncome36.setCellStyle(cellStyle);
        //鸡
        HSSFRow rowIncome4 = sheet.createRow(incomeRowNum+4);
        HSSFCell cIncome42 = rowIncome4.createCell(2);
        cIncome42.setCellValue(new HSSFRichTextString("鸡"));
        cIncome42.setCellStyle(cellStyle1);
        HSSFCell cIncome43 = rowIncome4.createCell(3);
        cIncome43.setCellValue(new HSSFRichTextString(ji == null?"":ji.getIncome_quantity()));
        cIncome43.setCellStyle(cellStyle);
        HSSFCell cIncome44 = rowIncome4.createCell(4);
        cIncome44.setCellValue(new HSSFRichTextString(ji == null?"":ji.getIncome_unit()));
        cIncome44.setCellStyle(cellStyle);
        HSSFCell cIncome45 = rowIncome4.createCell(5);
        cIncome45.setCellValue(new HSSFRichTextString(ji == null?"":ji.getIncome_sum()));
        cIncome45.setCellStyle(cellStyle);
        CellRangeAddress cra17=new CellRangeAddress(incomeRowNum+4, incomeRowNum+4, 6, 8);
        sheet.addMergedRegion(cra17);
        HSSFCell cIncome46 = rowIncome4.createCell(6);
        cIncome46.setCellValue(new HSSFRichTextString(ji == null?"":ji.getRemark()));
        cIncome46.setCellStyle(cellStyle);
        //鸭
        HSSFRow rowIncome5 = sheet.createRow(incomeRowNum+5);
        HSSFCell cIncome52 = rowIncome5.createCell(2);
        cIncome52.setCellValue(new HSSFRichTextString("鸭"));
        cIncome52.setCellStyle(cellStyle1);
        HSSFCell cIncome53 = rowIncome5.createCell(3);
        cIncome53.setCellValue(new HSSFRichTextString(ya == null?"":ya.getIncome_quantity()));
        cIncome53.setCellStyle(cellStyle);
        HSSFCell cIncome54 = rowIncome5.createCell(4);
        cIncome54.setCellValue(new HSSFRichTextString(ya == null?"":ya.getIncome_unit()));
        cIncome54.setCellStyle(cellStyle);
        HSSFCell cIncome55 = rowIncome5.createCell(5);
        cIncome55.setCellValue(new HSSFRichTextString(ya == null?"":ya.getIncome_sum()));
        cIncome55.setCellStyle(cellStyle);
        CellRangeAddress cra18=new CellRangeAddress(incomeRowNum+5, incomeRowNum+5, 6, 8);
        sheet.addMergedRegion(cra18);
        HSSFCell cIncome56 = rowIncome5.createCell(6);
        cIncome56.setCellValue(new HSSFRichTextString(ya == null?"":ya.getRemark()));
        cIncome56.setCellStyle(cellStyle);
        //渔业
        HSSFRow rowIncome6 = sheet.createRow(incomeRowNum+6);
        HSSFCell cIncome62 = rowIncome6.createCell(2);
        cIncome62.setCellValue(new HSSFRichTextString("渔业"));
        cIncome62.setCellStyle(cellStyle1);
        HSSFCell cIncome63 = rowIncome6.createCell(3);
        cIncome63.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getIncome_quantity()));
        cIncome63.setCellStyle(cellStyle);
        HSSFCell cIncome64 = rowIncome6.createCell(4);
        cIncome64.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getIncome_unit()));
        cIncome64.setCellStyle(cellStyle);
        HSSFCell cIncome65 = rowIncome6.createCell(5);
        cIncome65.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getIncome_sum()));
        cIncome65.setCellStyle(cellStyle);
        CellRangeAddress cra19=new CellRangeAddress(incomeRowNum+6, incomeRowNum+6, 6, 8);
        sheet.addMergedRegion(cra19);
        HSSFCell cIncome66 = rowIncome6.createCell(6);
        cIncome66.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getRemark()));
        cIncome66.setCellStyle(cellStyle);
        //乳业
        HSSFRow rowIncome7 = sheet.createRow(incomeRowNum+7);
        HSSFCell cIncome72 = rowIncome7.createCell(2);
        cIncome72.setCellValue(new HSSFRichTextString("乳业"));
        cIncome72.setCellStyle(cellStyle1);
        HSSFCell cIncome73 = rowIncome7.createCell(3);
        cIncome73.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getIncome_quantity()));
        cIncome73.setCellStyle(cellStyle);
        HSSFCell cIncome74 = rowIncome7.createCell(4);
        cIncome74.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getIncome_unit()));
        cIncome74.setCellStyle(cellStyle);
        HSSFCell cIncome75 = rowIncome7.createCell(5);
        cIncome75.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getIncome_sum()));
        cIncome75.setCellStyle(cellStyle);
        CellRangeAddress cra20=new CellRangeAddress(incomeRowNum+7, incomeRowNum+7, 6, 8);
        sheet.addMergedRegion(cra20);
        HSSFCell cIncome76 = rowIncome7.createCell(6);
        cIncome76.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getRemark()));
        cIncome76.setCellStyle(cellStyle);
        //养殖其他
        HSSFRow rowIncome8 = sheet.createRow(incomeRowNum+8);
        HSSFCell cIncome82 = rowIncome8.createCell(2);
        cIncome82.setCellValue(new HSSFRichTextString("其他"));
        cIncome82.setCellStyle(cellStyle1);
        HSSFCell cIncome83 = rowIncome8.createCell(3);
        cIncome83.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getIncome_quantity()));
        cIncome83.setCellStyle(cellStyle);
        HSSFCell cIncome84 = rowIncome8.createCell(4);
        cIncome84.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getIncome_unit()));
        cIncome84.setCellStyle(cellStyle);
        HSSFCell cIncome85 = rowIncome8.createCell(5);
        cIncome85.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getIncome_sum()));
        cIncome85.setCellStyle(cellStyle);
        CellRangeAddress cra21=new CellRangeAddress(incomeRowNum+8, incomeRowNum+8, 6, 8);
        sheet.addMergedRegion(cra21);
        HSSFCell cIncome86 = rowIncome8.createCell(6);
        cIncome86.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getRemark()));
        cIncome86.setCellStyle(cellStyle);
        //种植业
        //粮食
        HSSFRow rowIncome9 = sheet.createRow(incomeRowNum+9);
        CellRangeAddress cra22=new CellRangeAddress(incomeRowNum+9, incomeRowNum+12, 1, 1);
        sheet.addMergedRegion(cra22);
        HSSFCell cIncome91 = rowIncome9.createCell(1);
        cIncome91.setCellValue(new HSSFRichTextString("种殖业收入"));
        HSSFCell cIncome92 = rowIncome9.createCell(2);
        cIncome92.setCellValue(new HSSFRichTextString("粮食"));
        cIncome92.setCellStyle(cellStyle1);
        HSSFCell cIncome93 = rowIncome9.createCell(3);
        cIncome93.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getIncome_quantity()));
        cIncome93.setCellStyle(cellStyle);
        HSSFCell cIncome94 = rowIncome9.createCell(4);
        cIncome94.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getIncome_unit()));
        cIncome94.setCellStyle(cellStyle);
        HSSFCell cIncome95 = rowIncome9.createCell(5);
        cIncome95.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getIncome_sum()));
        cIncome95.setCellStyle(cellStyle);
        CellRangeAddress cra23=new CellRangeAddress(incomeRowNum+9, incomeRowNum+9, 6, 8);
        sheet.addMergedRegion(cra23);
        HSSFCell cIncome96 = rowIncome9.createCell(6);
        cIncome96.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getRemark()));
        cIncome96.setCellStyle(cellStyle);
        //蔬菜
        HSSFRow rowIncome10 = sheet.createRow(incomeRowNum+10);
        HSSFCell cIncome102 = rowIncome10.createCell(2);
        cIncome102.setCellValue(new HSSFRichTextString("蔬菜"));
        cIncome102.setCellStyle(cellStyle1);
        HSSFCell cIncome103 = rowIncome10.createCell(3);
        cIncome103.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getIncome_quantity()));
        cIncome103.setCellStyle(cellStyle);
        HSSFCell cIncome104 = rowIncome10.createCell(4);
        cIncome104.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getIncome_unit()));
        cIncome104.setCellStyle(cellStyle);
        HSSFCell cIncome105 = rowIncome10.createCell(5);
        cIncome105.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getIncome_sum()));
        cIncome105.setCellStyle(cellStyle);
        CellRangeAddress cra24=new CellRangeAddress(incomeRowNum+10, incomeRowNum+10, 6, 8);
        sheet.addMergedRegion(cra24);
        HSSFCell cIncome106 = rowIncome10.createCell(6);
        cIncome106.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getRemark()));
        cIncome106.setCellStyle(cellStyle);
        //林果
        HSSFRow rowIncome11 = sheet.createRow(incomeRowNum+11);
        HSSFCell cIncome112 = rowIncome11.createCell(2);
        cIncome112.setCellValue(new HSSFRichTextString("林果"));
        cIncome112.setCellStyle(cellStyle1);
        HSSFCell cIncome113 = rowIncome11.createCell(3);
        cIncome113.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getIncome_quantity()));
        cIncome113.setCellStyle(cellStyle);
        HSSFCell cIncome114 = rowIncome11.createCell(4);
        cIncome114.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getIncome_unit()));
        cIncome114.setCellStyle(cellStyle);
        HSSFCell cIncome115 = rowIncome11.createCell(5);
        cIncome115.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getIncome_sum()));
        cIncome115.setCellStyle(cellStyle);
        CellRangeAddress cra25=new CellRangeAddress(incomeRowNum+11, incomeRowNum+11, 6, 8);
        sheet.addMergedRegion(cra25);
        HSSFCell cIncome116 = rowIncome11.createCell(6);
        cIncome116.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getRemark()));
        cIncome116.setCellStyle(cellStyle);
        //其他
        HSSFRow rowIncome12 = sheet.createRow(incomeRowNum+12);
        HSSFCell cIncome122 = rowIncome12.createCell(2);
        cIncome122.setCellValue(new HSSFRichTextString("其他"));
        cIncome122.setCellStyle(cellStyle1);
        HSSFCell cIncome123 = rowIncome12.createCell(3);
        cIncome123.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getIncome_quantity()));
        cIncome123.setCellStyle(cellStyle);
        HSSFCell cIncome124 = rowIncome12.createCell(4);
        cIncome124.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getIncome_unit()));
        cIncome124.setCellStyle(cellStyle);
        HSSFCell cIncome125 = rowIncome12.createCell(5);
        cIncome125.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getIncome_sum()));
        cIncome125.setCellStyle(cellStyle);
        CellRangeAddress cra26=new CellRangeAddress(incomeRowNum+12, incomeRowNum+12, 6, 8);
        sheet.addMergedRegion(cra26);
        HSSFCell cIncome126 = rowIncome12.createCell(6);
        cIncome126.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getRemark()));
        cIncome126.setCellStyle(cellStyle);
        //其他收入
        //劳务酬劳
        HSSFRow rowIncome13 = sheet.createRow(incomeRowNum+13);
        CellRangeAddress cra27=new CellRangeAddress(incomeRowNum+13, incomeRowNum+14, 1, 1);
        sheet.addMergedRegion(cra27);
        HSSFCell cIncome131 = rowIncome13.createCell(1);
        cIncome131.setCellValue(new HSSFRichTextString("其他收入"));
        HSSFCell cIncome132 = rowIncome13.createCell(2);
        cIncome132.setCellValue(new HSSFRichTextString("劳务酬劳"));
        cIncome132.setCellStyle(cellStyle1);
        HSSFCell cIncome133 = rowIncome13.createCell(3);
        cIncome133.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getIncome_quantity()));
        cIncome133.setCellStyle(cellStyle);
        HSSFCell cIncome134 = rowIncome13.createCell(4);
        cIncome134.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getIncome_unit()));
        cIncome134.setCellStyle(cellStyle);
        HSSFCell cIncome135 = rowIncome13.createCell(5);
        cIncome135.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getIncome_sum()));
        cIncome135.setCellStyle(cellStyle);
        CellRangeAddress cra28=new CellRangeAddress(incomeRowNum+13, incomeRowNum+13, 6, 8);
        sheet.addMergedRegion(cra28);
        HSSFCell cIncome136 = rowIncome13.createCell(6);
        cIncome136.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getRemark()));
        cIncome136.setCellStyle(cellStyle);
        //房、耕地租赁
        HSSFRow rowIncome14 = sheet.createRow(incomeRowNum+14);
        HSSFCell cIncome142 = rowIncome14.createCell(2);
        cIncome142.setCellValue(new HSSFRichTextString("房、耕地租赁"));
        cIncome142.setCellStyle(cellStyle1);
        HSSFCell cIncome143 = rowIncome14.createCell(3);
        cIncome143.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getIncome_quantity()));
        cIncome143.setCellStyle(cellStyle);
        HSSFCell cIncome144 = rowIncome14.createCell(4);
        cIncome144.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getIncome_unit()));
        cIncome144.setCellStyle(cellStyle);
        HSSFCell cIncome145 = rowIncome14.createCell(5);
        cIncome145.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getIncome_sum()));
        cIncome145.setCellStyle(cellStyle);
        CellRangeAddress cra29=new CellRangeAddress(incomeRowNum+14, incomeRowNum+14, 6, 8);
        sheet.addMergedRegion(cra29);
        HSSFCell cIncome146 = rowIncome13.createCell(6);
        cIncome146.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getRemark()));
        cIncome146.setCellStyle(cellStyle);
        //支出情况部分
        int outcomeRowNum = incomeRowNum+15;
        CellRangeAddress cra30=new CellRangeAddress(outcomeRowNum, outcomeRowNum+18, 0, 0);
        sheet.addMergedRegion(cra30);
        HSSFRow rowOutcome0 = sheet.createRow(outcomeRowNum);
        HSSFCell cOutcome00 = rowOutcome0.createCell(0);
        cOutcome00.setCellValue(new HSSFRichTextString("支出情况"));
        HSSFCell cOutcome01 = rowOutcome0.createCell(1);
        cOutcome01.setCellValue(new HSSFRichTextString("分类"));
        cOutcome01.setCellStyle(cellStyle1);
        HSSFCell cOutcome02 = rowOutcome0.createCell(2);
        cOutcome02.setCellValue(new HSSFRichTextString("内容"));
        cOutcome02.setCellStyle(cellStyle1);
        HSSFCell cOutcome03 = rowOutcome0.createCell(3);
        cOutcome03.setCellValue(new HSSFRichTextString("数量"));
        cOutcome03.setCellStyle(cellStyle1);
        HSSFCell cOutcome04 = rowOutcome0.createCell(4);
        cOutcome04.setCellValue(new HSSFRichTextString("单价"));
        cOutcome04.setCellStyle(cellStyle1);
        HSSFCell cOutcome05 = rowOutcome0.createCell(5);
        cOutcome05.setCellValue(new HSSFRichTextString("小计"));
        cOutcome05.setCellStyle(cellStyle1);
        CellRangeAddress cra31=new CellRangeAddress(outcomeRowNum, outcomeRowNum, 6, 8);
        sheet.addMergedRegion(cra31);
        HSSFCell cOutcome06 = rowOutcome0.createCell(6);
        cOutcome06.setCellValue(new HSSFRichTextString("备注"));
        Outcome zizhong = null;
        Outcome huafei = null;
        Outcome gugong = null;
        Outcome jigengzhichu = null;
        Outcome guangaishuidianfei = null;
        Outcome chengzugengdi = null;
        Outcome youzhong = null;
        Outcome siliao = null;
        Outcome jibingfangzhi = null;
        Outcome yangzhizhichuother = null;
        Outcome zhushi = null;
        Outcome yiwu = null;
        Outcome shuidianfei = null;
        Outcome tongxunfei = null;
        Outcome jiaotongfei = null;
        Outcome jiaoyu = null;
        Outcome yiliao = null;
        Outcome shenghuozhichuother = null;
        if(outcomeList !=null || outcomeList.size()>0) {
            for (int i = 0; i < outcomeList.size(); i++) {
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "籽种".equals(outcomeList.get(i).getOutcome_cate())) {
                    zizhong = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "化肥、农药".equals(outcomeList.get(i).getOutcome_cate())) {
                    huafei = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "雇工".equals(outcomeList.get(i).getOutcome_cate())) {
                    gugong = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "机耕支出".equals(outcomeList.get(i).getOutcome_cate())) {
                    jigengzhichu = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "灌溉水电费".equals(outcomeList.get(i).getOutcome_cate())) {
                    guangaishuidianfei = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "承租耕地".equals(outcomeList.get(i).getOutcome_cate())) {
                    chengzugengdi = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "幼种".equals(outcomeList.get(i).getOutcome_cate())) {
                    youzhong = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "饲料".equals(outcomeList.get(i).getOutcome_cate())) {
                    siliao = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "疾病防治".equals(outcomeList.get(i).getOutcome_cate())) {
                    jibingfangzhi = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "其他".equals(outcomeList.get(i).getOutcome_cate())) {
                    yangzhizhichuother = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "主食".equals(outcomeList.get(i).getOutcome_cate())) {
                    zhushi = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "衣物".equals(outcomeList.get(i).getOutcome_cate())) {
                    yiwu = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "水、电费".equals(outcomeList.get(i).getOutcome_cate())) {
                    shuidianfei = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "通讯费".equals(outcomeList.get(i).getOutcome_cate())) {
                    tongxunfei = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "交通费".equals(outcomeList.get(i).getOutcome_cate())) {
                    jiaotongfei = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "教育".equals(outcomeList.get(i).getOutcome_cate())) {
                    jiaoyu = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "医疗".equals(outcomeList.get(i).getOutcome_cate())) {
                    yiliao = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "其他".equals(outcomeList.get(i).getOutcome_cate())) {
                    shenghuozhichuother = outcomeList.get(i);
                }
            }
        }
        //种植业支出
        //籽种
        HSSFRow rowOutcome1 = sheet.createRow(outcomeRowNum+1);
        CellRangeAddress cra32=new CellRangeAddress(outcomeRowNum+1, outcomeRowNum+6, 1, 1);
        sheet.addMergedRegion(cra32);
        HSSFCell cOutcome11 = rowOutcome1.createCell(1);
        cOutcome11.setCellValue(new HSSFRichTextString("种殖业支出"));
        HSSFCell cOutcome12 = rowOutcome1.createCell(2);
        cOutcome12.setCellValue(new HSSFRichTextString("籽种"));
        cOutcome12.setCellStyle(cellStyle1);
        HSSFCell cOutcome13 = rowOutcome1.createCell(3);
        cOutcome13.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getOutcome_quantity()));
        cOutcome13.setCellStyle(cellStyle);
        HSSFCell cOutcome14 = rowOutcome1.createCell(4);
        cOutcome14.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getOutcome_unit()));
        cOutcome14.setCellStyle(cellStyle);
        HSSFCell cOutcome15 = rowOutcome1.createCell(5);
        cOutcome15.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getOutcome_sum()));
        cOutcome15.setCellStyle(cellStyle);
        CellRangeAddress cra33=new CellRangeAddress(outcomeRowNum+1, outcomeRowNum+1, 6, 8);
        sheet.addMergedRegion(cra33);
        HSSFCell cOutcome16 = rowOutcome1.createCell(6);
        cOutcome16.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getRemark()));
        cOutcome16.setCellStyle(cellStyle);
        //化肥
        HSSFRow rowOutcome2 = sheet.createRow(outcomeRowNum+2);
        HSSFCell cOutcome22 = rowOutcome2.createCell(2);
        cOutcome22.setCellValue(new HSSFRichTextString("化肥、农药"));
        cOutcome22.setCellStyle(cellStyle1);
        HSSFCell cOutcome23 = rowOutcome2.createCell(3);
        cOutcome23.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getOutcome_quantity()));
        cOutcome23.setCellStyle(cellStyle);
        HSSFCell cOutcome24 = rowOutcome2.createCell(4);
        cOutcome24.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getOutcome_unit()));
        cOutcome24.setCellStyle(cellStyle);
        HSSFCell cOutcome25 = rowOutcome2.createCell(5);
        cOutcome25.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getOutcome_sum()));
        cOutcome25.setCellStyle(cellStyle);
        CellRangeAddress cra34=new CellRangeAddress(outcomeRowNum+2, outcomeRowNum+2, 6, 8);
        sheet.addMergedRegion(cra34);
        HSSFCell cOutcome26 = rowOutcome2.createCell(6);
        cOutcome26.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getRemark()));
        cOutcome26.setCellStyle(cellStyle);
        //雇工
        HSSFRow rowOutcome3 = sheet.createRow(outcomeRowNum+3);
        HSSFCell cOutcome32 = rowOutcome3.createCell(2);
        cOutcome32.setCellValue(new HSSFRichTextString("雇工"));
        cOutcome32.setCellStyle(cellStyle1);
        HSSFCell cOutcome33 = rowOutcome3.createCell(3);
        cOutcome33.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getOutcome_quantity()));
        cOutcome33.setCellStyle(cellStyle);
        HSSFCell cOutcome34 = rowOutcome3.createCell(4);
        cOutcome34.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getOutcome_unit()));
        cOutcome34.setCellStyle(cellStyle);
        HSSFCell cOutcome35 = rowOutcome3.createCell(5);
        cOutcome35.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getOutcome_sum()));
        cOutcome35.setCellStyle(cellStyle);
        CellRangeAddress cra35=new CellRangeAddress(outcomeRowNum+3, outcomeRowNum+3, 6, 8);
        sheet.addMergedRegion(cra35);
        HSSFCell cOutcome36 = rowOutcome3.createCell(6);
        cOutcome36.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getRemark()));
        cOutcome36.setCellStyle(cellStyle);
        //机耕支出
        HSSFRow rowOutcome4 = sheet.createRow(outcomeRowNum+4);
        HSSFCell cOutcome42 = rowOutcome4.createCell(2);
        cOutcome42.setCellValue(new HSSFRichTextString("机耕支出"));
        cOutcome42.setCellStyle(cellStyle1);
        HSSFCell cOutcome43 = rowOutcome4.createCell(3);
        cOutcome43.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getOutcome_quantity()));
        cOutcome43.setCellStyle(cellStyle);
        HSSFCell cOutcome44 = rowOutcome4.createCell(4);
        cOutcome44.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getOutcome_unit()));
        cOutcome44.setCellStyle(cellStyle);
        HSSFCell cOutcome45 = rowOutcome4.createCell(5);
        cOutcome45.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getOutcome_sum()));
        cOutcome45.setCellStyle(cellStyle);
        CellRangeAddress cra36=new CellRangeAddress(outcomeRowNum+4, outcomeRowNum+4, 6, 8);
        sheet.addMergedRegion(cra36);
        HSSFCell cOutcome46 = rowOutcome4.createCell(6);
        cOutcome46.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getRemark()));
        cOutcome46.setCellStyle(cellStyle);
        //灌溉水电费
        HSSFRow rowOutcome5 = sheet.createRow(outcomeRowNum+5);
        HSSFCell cOutcome52 = rowOutcome5.createCell(2);
        cOutcome52.setCellValue(new HSSFRichTextString("灌溉水电费"));
        cOutcome52.setCellStyle(cellStyle1);
        HSSFCell cOutcome53 = rowOutcome5.createCell(3);
        cOutcome53.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getOutcome_quantity()));
        cOutcome53.setCellStyle(cellStyle);
        HSSFCell cOutcome54 = rowOutcome5.createCell(4);
        cOutcome54.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getOutcome_unit()));
        cOutcome54.setCellStyle(cellStyle);
        HSSFCell cOutcome55 = rowOutcome5.createCell(5);
        cOutcome55.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getOutcome_sum()));
        cOutcome55.setCellStyle(cellStyle);
        CellRangeAddress cra37=new CellRangeAddress(outcomeRowNum+5, outcomeRowNum+5, 6, 8);
        sheet.addMergedRegion(cra37);
        HSSFCell cOutcome56 = rowOutcome5.createCell(6);
        cOutcome56.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getRemark()));
        cOutcome56.setCellStyle(cellStyle);
        //承租耕地
        HSSFRow rowOutcome6 = sheet.createRow(outcomeRowNum+6);
        HSSFCell cOutcome62 = rowOutcome6.createCell(2);
        cOutcome62.setCellValue(new HSSFRichTextString("承租耕地"));
        cOutcome62.setCellStyle(cellStyle1);
        HSSFCell cOutcome63 = rowOutcome6.createCell(3);
        cOutcome63.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getOutcome_quantity()));
        cOutcome63.setCellStyle(cellStyle);
        HSSFCell cOutcome64 = rowOutcome6.createCell(4);
        cOutcome64.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getOutcome_unit()));
        cOutcome64.setCellStyle(cellStyle);
        HSSFCell cOutcome65 = rowOutcome6.createCell(5);
        cOutcome65.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getOutcome_sum()));
        cOutcome65.setCellStyle(cellStyle);
        CellRangeAddress cra38=new CellRangeAddress(outcomeRowNum+6, outcomeRowNum+6, 6, 8);
        sheet.addMergedRegion(cra38);
        HSSFCell cOutcome66 = rowOutcome6.createCell(6);
        cOutcome66.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getRemark()));
        cOutcome66.setCellStyle(cellStyle);
        //养植业支出
        //幼种
        HSSFRow rowOutcome7 = sheet.createRow(outcomeRowNum+7);
        CellRangeAddress cra39=new CellRangeAddress(outcomeRowNum+7, outcomeRowNum+10, 1, 1);
        sheet.addMergedRegion(cra39);
        HSSFCell cOutcome71 = rowOutcome7.createCell(1);
        cOutcome71.setCellValue(new HSSFRichTextString("养殖业支出"));
        HSSFCell cOutcome72 = rowOutcome7.createCell(2);
        cOutcome72.setCellValue(new HSSFRichTextString("幼种"));
        cOutcome72.setCellStyle(cellStyle1);
        HSSFCell cOutcome73 = rowOutcome7.createCell(3);
        cOutcome73.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getOutcome_quantity()));
        cOutcome73.setCellStyle(cellStyle);
        HSSFCell cOutcome74 = rowOutcome7.createCell(4);
        cOutcome74.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getOutcome_unit()));
        cOutcome74.setCellStyle(cellStyle);
        HSSFCell cOutcome75 = rowOutcome7.createCell(5);
        cOutcome75.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getOutcome_sum()));
        cOutcome75.setCellStyle(cellStyle);
        CellRangeAddress cra40=new CellRangeAddress(outcomeRowNum+7, outcomeRowNum+7, 6, 8);
        sheet.addMergedRegion(cra40);
        HSSFCell cOutcome76 = rowOutcome7.createCell(6);
        cOutcome76.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getRemark()));
        cOutcome76.setCellStyle(cellStyle);
        //饲料
        HSSFRow rowOutcome8 = sheet.createRow(outcomeRowNum+8);
        HSSFCell cOutcome82 = rowOutcome8.createCell(2);
        cOutcome82.setCellValue(new HSSFRichTextString("饲料"));
        cOutcome82.setCellStyle(cellStyle1);
        HSSFCell cOutcome83 = rowOutcome8.createCell(3);
        cOutcome83.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getOutcome_quantity()));
        cOutcome83.setCellStyle(cellStyle);
        HSSFCell cOutcome84 = rowOutcome8.createCell(4);
        cOutcome84.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getOutcome_unit()));
        cOutcome84.setCellStyle(cellStyle);
        HSSFCell cOutcome85 = rowOutcome8.createCell(5);
        cOutcome85.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getOutcome_sum()));
        cOutcome85.setCellStyle(cellStyle);
        CellRangeAddress cra41=new CellRangeAddress(outcomeRowNum+8, outcomeRowNum+8, 6, 8);
        sheet.addMergedRegion(cra41);
        HSSFCell cOutcome86 = rowOutcome8.createCell(6);
        cOutcome86.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getRemark()));
        cOutcome86.setCellStyle(cellStyle);
        //疫病防治
        HSSFRow rowOutcome9 = sheet.createRow(outcomeRowNum+9);
        HSSFCell cOutcome92 = rowOutcome9.createCell(2);
        cOutcome92.setCellValue(new HSSFRichTextString("疫病防治"));
        cOutcome92.setCellStyle(cellStyle1);
        HSSFCell cOutcome93 = rowOutcome9.createCell(3);
        cOutcome93.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getOutcome_quantity()));
        cOutcome93.setCellStyle(cellStyle);
        HSSFCell cOutcome94 = rowOutcome9.createCell(4);
        cOutcome94.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getOutcome_unit()));
        cOutcome94.setCellStyle(cellStyle);
        HSSFCell cOutcome95 = rowOutcome9.createCell(5);
        cOutcome95.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getOutcome_sum()));
        cOutcome95.setCellStyle(cellStyle);
        CellRangeAddress cra42=new CellRangeAddress(outcomeRowNum+9, outcomeRowNum+9, 6, 8);
        sheet.addMergedRegion(cra42);
        HSSFCell cOutcome96 = rowOutcome9.createCell(6);
        cOutcome96.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getRemark()));
        cOutcome96.setCellStyle(cellStyle);
        //其他
        HSSFRow rowOutcome10 = sheet.createRow(outcomeRowNum+10);
        HSSFCell cOutcome102 = rowOutcome10.createCell(2);
        cOutcome102.setCellValue(new HSSFRichTextString("其他"));
        cOutcome102.setCellStyle(cellStyle1);
        HSSFCell cOutcome103 = rowOutcome10.createCell(3);
        cOutcome103.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getOutcome_quantity()));
        cOutcome103.setCellStyle(cellStyle);
        HSSFCell cOutcome104 = rowOutcome10.createCell(4);
        cOutcome104.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getOutcome_unit()));
        cOutcome104.setCellStyle(cellStyle);
        HSSFCell cOutcome105 = rowOutcome10.createCell(5);
        cOutcome105.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getOutcome_sum()));
        cOutcome105.setCellStyle(cellStyle);
        CellRangeAddress cra43=new CellRangeAddress(outcomeRowNum+10, outcomeRowNum+10, 6, 8);
        sheet.addMergedRegion(cra43);
        HSSFCell cOutcome106 = rowOutcome10.createCell(6);
        cOutcome106.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getRemark()));
        cOutcome106.setCellStyle(cellStyle);
        //生活支出
        //主食
        HSSFRow rowOutcome11 = sheet.createRow(outcomeRowNum+11);
        CellRangeAddress cra44=new CellRangeAddress(outcomeRowNum+11, outcomeRowNum+18, 1, 1);
        sheet.addMergedRegion(cra44);
        HSSFCell cOutcome111 = rowOutcome11.createCell(1);
        cOutcome111.setCellValue(new HSSFRichTextString("生活支出"));
        HSSFCell cOutcome112 = rowOutcome11.createCell(2);
        cOutcome112.setCellValue(new HSSFRichTextString("主食"));
        cOutcome112.setCellStyle(cellStyle1);
        HSSFCell cOutcome113 = rowOutcome11.createCell(3);
        cOutcome113.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getOutcome_quantity()));
        cOutcome113.setCellStyle(cellStyle);
        HSSFCell cOutcome114 = rowOutcome11.createCell(4);
        cOutcome114.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getOutcome_unit()));
        cOutcome114.setCellStyle(cellStyle);
        HSSFCell cOutcome115 = rowOutcome11.createCell(5);
        cOutcome115.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getOutcome_sum()));
        cOutcome115.setCellStyle(cellStyle);
        CellRangeAddress cra45=new CellRangeAddress(outcomeRowNum+11, outcomeRowNum+11, 6, 8);
        sheet.addMergedRegion(cra45);
        HSSFCell cOutcome116 = rowOutcome11.createCell(6);
        cOutcome116.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getRemark()));
        cOutcome116.setCellStyle(cellStyle);
        //衣物
        HSSFRow rowOutcome12 = sheet.createRow(outcomeRowNum+12);
        HSSFCell cOutcome122 = rowOutcome12.createCell(2);
        cOutcome122.setCellValue(new HSSFRichTextString("衣物"));
        cOutcome122.setCellStyle(cellStyle1);
        HSSFCell cOutcome123 = rowOutcome12.createCell(3);
        cOutcome123.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getOutcome_quantity()));
        cOutcome123.setCellStyle(cellStyle);
        HSSFCell cOutcome124 = rowOutcome12.createCell(4);
        cOutcome124.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getOutcome_unit()));
        cOutcome124.setCellStyle(cellStyle);
        HSSFCell cOutcome125 = rowOutcome12.createCell(5);
        cOutcome125.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getOutcome_sum()));
        cOutcome125.setCellStyle(cellStyle);
        CellRangeAddress cra46=new CellRangeAddress(outcomeRowNum+12, outcomeRowNum+12, 6, 8);
        sheet.addMergedRegion(cra46);
        HSSFCell cOutcome126 = rowOutcome12.createCell(6);
        cOutcome126.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getRemark()));
        cOutcome126.setCellStyle(cellStyle);
        //水、电费
        HSSFRow rowOutcome13 = sheet.createRow(outcomeRowNum+13);
        HSSFCell cOutcome132 = rowOutcome13.createCell(2);
        cOutcome132.setCellValue(new HSSFRichTextString("水、电费"));
        cOutcome132.setCellStyle(cellStyle1);
        HSSFCell cOutcome133 = rowOutcome13.createCell(3);
        cOutcome133.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getOutcome_quantity()));
        cOutcome133.setCellStyle(cellStyle);
        HSSFCell cOutcome134 = rowOutcome13.createCell(4);
        cOutcome134.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getOutcome_unit()));
        cOutcome134.setCellStyle(cellStyle);
        HSSFCell cOutcome135 = rowOutcome13.createCell(5);
        cOutcome135.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getOutcome_sum()));
        cOutcome135.setCellStyle(cellStyle);
        CellRangeAddress cra47=new CellRangeAddress(outcomeRowNum+13, outcomeRowNum+13, 6, 8);
        sheet.addMergedRegion(cra47);
        HSSFCell cOutcome136 = rowOutcome13.createCell(6);
        cOutcome136.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getRemark()));
        cOutcome136.setCellStyle(cellStyle);
        //通讯费
        HSSFRow rowOutcome14 = sheet.createRow(outcomeRowNum+14);
        HSSFCell cOutcome142 = rowOutcome14.createCell(2);
        cOutcome142.setCellValue(new HSSFRichTextString("通讯费"));
        cOutcome142.setCellStyle(cellStyle1);
        HSSFCell cOutcome143 = rowOutcome14.createCell(3);
        cOutcome143.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getOutcome_quantity()));
        cOutcome143.setCellStyle(cellStyle);
        HSSFCell cOutcome144 = rowOutcome14.createCell(4);
        cOutcome144.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getOutcome_unit()));
        cOutcome144.setCellStyle(cellStyle);
        HSSFCell cOutcome145 = rowOutcome14.createCell(5);
        cOutcome145.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getOutcome_sum()));
        cOutcome145.setCellStyle(cellStyle);
        CellRangeAddress cra48=new CellRangeAddress(outcomeRowNum+14, outcomeRowNum+14, 6, 8);
        sheet.addMergedRegion(cra48);
        HSSFCell cOutcome146 = rowOutcome14.createCell(6);
        cOutcome146.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getRemark()));
        cOutcome146.setCellStyle(cellStyle);
        //交通费
        HSSFRow rowOutcome15 = sheet.createRow(outcomeRowNum+15);
        HSSFCell cOutcome152 = rowOutcome15.createCell(2);
        cOutcome152.setCellValue(new HSSFRichTextString("交通费"));
        cOutcome152.setCellStyle(cellStyle1);
        HSSFCell cOutcome153 = rowOutcome15.createCell(3);
        cOutcome153.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getOutcome_quantity()));
        cOutcome153.setCellStyle(cellStyle);
        HSSFCell cOutcome154 = rowOutcome15.createCell(4);
        cOutcome154.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getOutcome_unit()));
        cOutcome154.setCellStyle(cellStyle);
        HSSFCell cOutcome155 = rowOutcome15.createCell(5);
        cOutcome155.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getOutcome_sum()));
        cOutcome155.setCellStyle(cellStyle);
        CellRangeAddress cra49=new CellRangeAddress(outcomeRowNum+15, outcomeRowNum+15, 6, 8);
        sheet.addMergedRegion(cra49);
        HSSFCell cOutcome156 = rowOutcome15.createCell(6);
        cOutcome156.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getRemark()));
        cOutcome156.setCellStyle(cellStyle);
        //教育
        HSSFRow rowOutcome16 = sheet.createRow(outcomeRowNum+16);
        HSSFCell cOutcome162 = rowOutcome16.createCell(2);
        cOutcome162.setCellValue(new HSSFRichTextString("教育"));
        cOutcome162.setCellStyle(cellStyle1);
        HSSFCell cOutcome163 = rowOutcome16.createCell(3);
        cOutcome163.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getOutcome_quantity()));
        cOutcome163.setCellStyle(cellStyle);
        HSSFCell cOutcome164 = rowOutcome16.createCell(4);
        cOutcome164.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getOutcome_unit()));
        cOutcome164.setCellStyle(cellStyle);
        HSSFCell cOutcome165 = rowOutcome16.createCell(5);
        cOutcome165.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getOutcome_sum()));
        cOutcome165.setCellStyle(cellStyle);
        CellRangeAddress cra50=new CellRangeAddress(outcomeRowNum+16, outcomeRowNum+16, 6, 8);
        sheet.addMergedRegion(cra50);
        HSSFCell cOutcome166 = rowOutcome16.createCell(6);
        cOutcome166.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getRemark()));
        cOutcome166.setCellStyle(cellStyle);
        //医疗
        HSSFRow rowOutcome17 = sheet.createRow(outcomeRowNum+17);
        HSSFCell cOutcome172 = rowOutcome17.createCell(2);
        cOutcome172.setCellValue(new HSSFRichTextString("医疗"));
        cOutcome172.setCellStyle(cellStyle1);
        HSSFCell cOutcome173 = rowOutcome17.createCell(3);
        cOutcome173.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getOutcome_quantity()));
        cOutcome173.setCellStyle(cellStyle);
        HSSFCell cOutcome174 = rowOutcome17.createCell(4);
        cOutcome174.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getOutcome_unit()));
        cOutcome174.setCellStyle(cellStyle);
        HSSFCell cOutcome175 = rowOutcome17.createCell(5);
        cOutcome175.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getOutcome_sum()));
        cOutcome175.setCellStyle(cellStyle);
        CellRangeAddress cra51=new CellRangeAddress(outcomeRowNum+17, outcomeRowNum+17, 6, 8);
        sheet.addMergedRegion(cra51);
        HSSFCell cOutcome176 = rowOutcome17.createCell(6);
        cOutcome176.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getRemark()));
        cOutcome176.setCellStyle(cellStyle);
        //其他
        HSSFRow rowOutcome18 = sheet.createRow(outcomeRowNum+18);
        HSSFCell cOutcome182 = rowOutcome18.createCell(2);
        cOutcome182.setCellValue(new HSSFRichTextString("其他"));
        cOutcome182.setCellStyle(cellStyle1);
        HSSFCell cOutcome183 = rowOutcome18.createCell(3);
        cOutcome183.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getOutcome_quantity()));
        cOutcome183.setCellStyle(cellStyle);
        HSSFCell cOutcome184 = rowOutcome18.createCell(4);
        cOutcome184.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getOutcome_unit()));
        cOutcome184.setCellStyle(cellStyle);
        HSSFCell cOutcome185 = rowOutcome18.createCell(5);
        cOutcome185.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getOutcome_sum()));
        cOutcome185.setCellStyle(cellStyle);
        CellRangeAddress cra52=new CellRangeAddress(outcomeRowNum+18, outcomeRowNum+18, 6, 8);
        sheet.addMergedRegion(cra52);
        HSSFCell cOutcome186 = rowOutcome18.createCell(6);
        cOutcome186.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getRemark()));
        cOutcome186.setCellStyle(cellStyle);
        //其他信息部分
        HSSFRow rowOutcome19 = sheet.createRow(outcomeRowNum+19);
        CellRangeAddress cra53=new CellRangeAddress(outcomeRowNum+19, outcomeRowNum+19, 4, 8);
        sheet.addMergedRegion(cra53);
        HSSFCell cOutcome190 = rowOutcome19.createCell(0);
        cOutcome190.setCellValue(new HSSFRichTextString("其他信息"));
        cOutcome190.setCellStyle(cellStyle1);
        HSSFCell cOutcome191 = rowOutcome19.createCell(1);
        cOutcome191.setCellValue(new HSSFRichTextString("是否建档立卡"));
        cOutcome191.setCellStyle(cellStyle1);
        HSSFCell cOutcome192 = rowOutcome19.createCell(2);
        String prop="";
        String poorReason = "";
        String interviewee = "";
        String interviewer = "";
        String createTime = "";
        if(peopleList !=null && peopleList.size()>0){
            for(int i = 0; i < peopleList.size();i++){
                if(peopleList.get(i).getMaster() == 1){
                    prop = peopleList.get(i).getProp() == 1?"是":"否";
                    poorReason = peopleList.get(i).getPoor_reason();
                    interviewee = peopleList.get(i).getInterviewee();
                    interviewer = peopleList.get(i).getInterviewer();
                    createTime = peopleList.get(i).getCreated_at();
                }
            }
        }
        cOutcome192.setCellValue(new HSSFRichTextString(prop));
        cOutcome192.setCellStyle(cellStyle);
        HSSFCell cOutcome193 = rowOutcome19.createCell(3);
        cOutcome193.setCellValue(new HSSFRichTextString("致贫原因"));
        cOutcome193.setCellStyle(cellStyle1);
        HSSFCell cOutcome194 = rowOutcome19.createCell(4);
        cOutcome194.setCellValue(new HSSFRichTextString(poorReason));
        cOutcome194.setCellStyle(cellStyle);
        //其他信息部分
        HSSFRow rowOutcome20 = sheet.createRow(outcomeRowNum+20);
        HSSFCell cOutcome200 = rowOutcome20.createCell(0);
        cOutcome200.setCellValue(new HSSFRichTextString("被调查人签字"));
        cOutcome200.setCellStyle(cellStyle1);
        CellRangeAddress cra54=new CellRangeAddress(outcomeRowNum+20, outcomeRowNum+20, 1, 2);
        sheet.addMergedRegion(cra54);
        HSSFCell cOutcome201 = rowOutcome20.createCell(1);
        cOutcome201.setCellValue(new HSSFRichTextString(interviewee));
        HSSFCell cOutcome203 = rowOutcome20.createCell(3);
        cOutcome203.setCellValue(new HSSFRichTextString("调查人签字"));
        cOutcome203.setCellStyle(cellStyle1);
        CellRangeAddress cra55=new CellRangeAddress(outcomeRowNum+20, outcomeRowNum+20, 4, 5);
        sheet.addMergedRegion(cra55);
        HSSFCell cOutcome204 = rowOutcome20.createCell(4);
        cOutcome204.setCellValue(new HSSFRichTextString(interviewer));
        HSSFCell cOutcome206 = rowOutcome20.createCell(6);
        cOutcome206.setCellValue(new HSSFRichTextString("填表时间"));
        cOutcome206.setCellStyle(cellStyle1);
        CellRangeAddress cra56=new CellRangeAddress(outcomeRowNum+20, outcomeRowNum+20, 7, 8);
        sheet.addMergedRegion(cra56);
        HSSFCell cOutcome207 = rowOutcome20.createCell(7);
        cOutcome207.setCellValue(new HSSFRichTextString(createTime));







        //给合并的单元格加上边框
        setRegionStyle(sheet,cra0,cellStyle1);
        setRegionStyle(sheet,cra1,cellStyle1);
        setRegionStyle(sheet,cra2,cellStyle);
        setRegionStyle(sheet,cra3,cellStyle1);
        setRegionStyle(sheet,cra4,cellStyle1);
        setRegionStyle(sheet,cra6,cellStyle1);
        setRegionStyle(sheet,cra7,cellStyle1);
        setRegionStyle(sheet,cra8,cellStyle);
        setRegionStyle(sheet,cra9,cellStyle);
        setRegionStyle(sheet,cra10,cellStyle1);
        setRegionStyle(sheet,cra11,cellStyle1);
        setRegionStyle(sheet,cra12,cellStyle1);
        setRegionStyle(sheet,cra13,cellStyle1);
        setRegionStyle(sheet,cra14,cellStyle);
        setRegionStyle(sheet,cra15,cellStyle);
        setRegionStyle(sheet,cra16,cellStyle);
        setRegionStyle(sheet,cra17,cellStyle);
        setRegionStyle(sheet,cra18,cellStyle);
        setRegionStyle(sheet,cra19,cellStyle);
        setRegionStyle(sheet,cra20,cellStyle);
        setRegionStyle(sheet,cra21,cellStyle);
        setRegionStyle(sheet,cra22,cellStyle1);
        setRegionStyle(sheet,cra23,cellStyle);
        setRegionStyle(sheet,cra24,cellStyle);
        setRegionStyle(sheet,cra25,cellStyle);
        setRegionStyle(sheet,cra26,cellStyle);
        setRegionStyle(sheet,cra27,cellStyle1);
        setRegionStyle(sheet,cra28,cellStyle);
        setRegionStyle(sheet,cra29,cellStyle);
        setRegionStyle(sheet,cra30,cellStyle1);
        setRegionStyle(sheet,cra31,cellStyle1);
        setRegionStyle(sheet,cra32,cellStyle1);
        setRegionStyle(sheet,cra33,cellStyle);
        setRegionStyle(sheet,cra34,cellStyle);
        setRegionStyle(sheet,cra35,cellStyle);
        setRegionStyle(sheet,cra36,cellStyle);
        setRegionStyle(sheet,cra37,cellStyle);
        setRegionStyle(sheet,cra38,cellStyle);
        setRegionStyle(sheet,cra39,cellStyle1);
        setRegionStyle(sheet,cra40,cellStyle);
        setRegionStyle(sheet,cra41,cellStyle);
        setRegionStyle(sheet,cra42,cellStyle);
        setRegionStyle(sheet,cra43,cellStyle);
        setRegionStyle(sheet,cra44,cellStyle1);
        setRegionStyle(sheet,cra45,cellStyle);
        setRegionStyle(sheet,cra46,cellStyle);
        setRegionStyle(sheet,cra47,cellStyle);
        setRegionStyle(sheet,cra48,cellStyle);
        setRegionStyle(sheet,cra49,cellStyle);
        setRegionStyle(sheet,cra50,cellStyle);
        setRegionStyle(sheet,cra51,cellStyle);
        setRegionStyle(sheet,cra52,cellStyle);
        setRegionStyle(sheet,cra53,cellStyle);
        setRegionStyle(sheet,cra54,cellStyle);
        setRegionStyle(sheet,cra55,cellStyle);
        setRegionStyle(sheet,cra56,cellStyle);
        //输出文件
        /*FileOutputStream stream = null;
        try {
            stream = new FileOutputStream("d:/student.xls");
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] body = new byte[0];
        try {
            workbook.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            //=============造响应体=============
            //InputStream is = servletContext.getResourceAsStream(path);
            //创建一个和流一样多的数组
            body = new byte[is.available()];
            //3、将流的数据放在数组里面
            is.read(body);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //==============造响应头================
        HttpHeaders headers = new HttpHeaders();
        //文件下载的响应头
        //按照以前乱码的解决方式；

        //文件名乱码解决
        String filename = fid+".xls";
        try {
            filename = new String(filename.getBytes("GBK"),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        headers.add("Content-Disposition", "attachment; filename="+filename);


        //第一个参数代表给浏览器的响应数据（响应体）
        //第二个参数代表当前响应的响应头（定制响应头）MultiValueMap
        //第三个参数代表当前响应状态码（statusCode）HttpStatus
        ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

        return re;

    }
    //导出库区安置表数据
    @RequestMapping("/exportKuQiExcel")
    public ResponseEntity<byte[]> exportKuQuExcel(String fid) {
        //取出来对应的数据
        Bank bank = dataGetingServiceImp.getBankInfoByFid(fid);
        List<People> peopleList = dataGetingServiceImp.getPeopleInfosByFid(fid);
        Move move = dataGetingServiceImp.getMoveInfoByFid(fid);
        House house = dataGetingServiceImp.getHouseInfoByFid(fid);
        List<Income> incomeList = dataGetingServiceImp.getIncomeInfosByFid(fid);
        List<Outcome> outcomeList = dataGetingServiceImp.getOutcomeInfosByFid(fid);
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
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        HSSFFont font1 = workbook.createFont();
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short) 13);//设置字体大小
        cellStyle1.setFont(font1);
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("库区安置登记表");
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
        c0.setCellValue(new HSSFRichTextString("库区安置登记表"));
        //c0.setCellStyle(cellStyle1);


        CellRangeAddress cra1=new CellRangeAddress(1, 2, 0, 0);
        sheet.addMergedRegion(cra1);
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell c10 = row1.createCell(0);
        c10.setCellValue(new HSSFRichTextString("户主信息"));
        //c10.setCellStyle(cellStyle1);
        HSSFCell c11 = row1.createCell(1);
        c11.setCellValue(new HSSFRichTextString("所属水库"));
        c11.setCellStyle(cellStyle1);
        HSSFCell c12 = row1.createCell(2);
        String reservoir = "";
        if(peopleList !=null && peopleList.size()>0){
            reservoir = peopleList.get(0).getReservoir();
        }
        c12.setCellValue(new HSSFRichTextString(reservoir));
        c12.setCellStyle(cellStyle);
        HSSFCell c13 = row1.createCell(3);
        c13.setCellValue(new HSSFRichTextString("户主姓名"));
        c13.setCellStyle(cellStyle1);
        HSSFCell c14 = row1.createCell(4);
        String masterName = "";
        if(peopleList !=null && peopleList.size()>0){
            for(int i = 0; i < peopleList.size();i++){
                if(peopleList.get(i).getMaster() == 1){
                    masterName = peopleList.get(i).getName();
                }
            }
        }
        c14.setCellValue(new HSSFRichTextString(masterName));
        c14.setCellStyle(cellStyle);
        CellRangeAddress cra60=new CellRangeAddress(1, 1, 4, 5);
        sheet.addMergedRegion(cra60);
        HSSFCell c16 = row1.createCell(6);
        c16.setCellValue(new HSSFRichTextString("联系电话"));
        String phone = "";
        if(peopleList !=null && peopleList.size()>0){
            for(int i = 0; i < peopleList.size();i++){
                if(peopleList.get(i).getMaster() == 1){
                    phone = peopleList.get(i).getPhone();
                }
            }
        }
        c16.setCellStyle(cellStyle);
        HSSFCell c17 = row1.createCell(7);
        c17.setCellValue(new HSSFRichTextString(phone));
        c17.setCellStyle(cellStyle1);
        CellRangeAddress cra61=new CellRangeAddress(1, 1, 7, 8);
        sheet.addMergedRegion(cra61);

        CellRangeAddress cra2=new CellRangeAddress(2, 2, 4, 5);
        sheet.addMergedRegion(cra2);
        HSSFRow row2 = sheet.createRow(2);
        HSSFCell c21 = row2.createCell(1);
        c21.setCellValue(new HSSFRichTextString("开户人姓名"));
        c21.setCellStyle(cellStyle1);
        HSSFCell c22 = row2.createCell(2);
        String accountNmae = "";
        if(bank != null){
            accountNmae = bank.getAccount_name();
        }
        c22.setCellValue(new HSSFRichTextString(accountNmae));
        c22.setCellStyle(cellStyle);
        HSSFCell c23 = row2.createCell(3);
        c23.setCellValue(new HSSFRichTextString("开户行名称"));
        c23.setCellStyle(cellStyle1);
        HSSFCell c24 = row2.createCell(4);
        String bankName = "";
        if(bank != null){
            bankName = bank.getBank_name();
        }
        c24.setCellValue(new HSSFRichTextString(bankName));
        c24.setCellStyle(cellStyle);
        HSSFCell c26 = row2.createCell(6);
        c26.setCellValue(new HSSFRichTextString("银行卡号"));
        c26.setCellStyle(cellStyle1);
        CellRangeAddress cra62=new CellRangeAddress(2, 2, 7, 8);
        sheet.addMergedRegion(cra62);
        HSSFCell c27 = row2.createCell(7);
        String accountNumber = "";
        if(accountNumber != null){
            accountNumber = bank.getAccount_number();
        }
        c27.setCellValue(new HSSFRichTextString(accountNumber));
        c27.setCellStyle(cellStyle);
        //家庭信息部分
        int homeSize = 4;
        if(peopleList !=null && peopleList.size()>0){
            homeSize = peopleList.size();
        }
        CellRangeAddress cra3=new CellRangeAddress(3, 3+homeSize, 0, 0);
        sheet.addMergedRegion(cra3);
        HSSFRow row3 = sheet.createRow(3);
        HSSFCell c30 = row3.createCell(0);
        c30.setCellValue(new HSSFRichTextString("家庭信息"));
        HSSFCell c31 = row3.createCell(1);
        c31.setCellValue(new HSSFRichTextString("姓名"));
        c31.setCellStyle(cellStyle1);
        CellRangeAddress cra4=new CellRangeAddress(3, 3, 2, 3);
        sheet.addMergedRegion(cra4);
        HSSFCell c32 = row3.createCell(2);
        c32.setCellValue(new HSSFRichTextString("身份证号码"));
        HSSFCell c34 = row3.createCell(4);
        c34.setCellValue(new HSSFRichTextString("性别"));
        c34.setCellStyle(cellStyle1);
        HSSFCell c35 = row3.createCell(5);
        c35.setCellValue(new HSSFRichTextString("民族"));
        c35.setCellStyle(cellStyle1);
        HSSFCell c36 = row3.createCell(6);
        c36.setCellValue(new HSSFRichTextString("与户主关系"));
        c36.setCellStyle(cellStyle1);
        HSSFCell c37 = row3.createCell(7);
        c37.setCellValue(new HSSFRichTextString("文化程度"));
        c37.setCellStyle(cellStyle1);
        HSSFCell c38 = row3.createCell(8);
        c38.setCellValue(new HSSFRichTextString("职业"));
        c38.setCellStyle(cellStyle1);
        //开始循环
        for(int i = 4; i < 4+peopleList.size();i++){
            CellRangeAddress cra5=new CellRangeAddress(i, i, 2, 3);
            sheet.addMergedRegion(cra5);
            HSSFRow row4 = sheet.createRow(i);
            HSSFCell c41 = row4.createCell(1);
            c41.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getName()));
            c41.setCellStyle(cellStyle);
            HSSFCell c42 = row4.createCell(2);
            c42.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getPid()));
            c42.setCellStyle(cellStyle);
            HSSFCell c44 = row4.createCell(4);
            c44.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getGender()));
            c44.setCellStyle(cellStyle);
            HSSFCell c45 = row4.createCell(5);
            c45.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getRace()));
            c45.setCellStyle(cellStyle);
            HSSFCell c46 = row4.createCell(6);
            c46.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getRelation()));
            c46.setCellStyle(cellStyle);
            HSSFCell c47 = row4.createCell(7);
            c47.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getEducation()));
            c47.setCellStyle(cellStyle);
            HSSFCell c48 = row4.createCell(8);
            c48.setCellValue(new HSSFRichTextString(peopleList.get(i-4).getProfession()));
            c48.setCellStyle(cellStyle);
            setRegionStyle(sheet,cra5,cellStyle);
        }
        //搬迁部分
        int familyLastRow = 3+homeSize;
        CellRangeAddress cra6=new CellRangeAddress(familyLastRow+1, familyLastRow+2, 0, 0);
        sheet.addMergedRegion(cra6);
        HSSFRow row8 = sheet.createRow(familyLastRow+1);
        HSSFCell c80 = row8.createCell(0);
        c80.setCellValue(new HSSFRichTextString("所在地"));
        HSSFCell c81 = row8.createCell(1);
        c81.setCellValue(new HSSFRichTextString("州市"));
        c81.setCellStyle(cellStyle1);
        HSSFCell c82 = row8.createCell(2);
        c82.setCellValue(new HSSFRichTextString("区县"));
        c82.setCellStyle(cellStyle1);
        HSSFCell c83 = row8.createCell(3);
        c83.setCellValue(new HSSFRichTextString("乡镇"));
        c83.setCellStyle(cellStyle1);
        HSSFCell c84 = row8.createCell(4);
        c84.setCellValue(new HSSFRichTextString("村"));
        c84.setCellStyle(cellStyle1);
        HSSFCell c85 = row8.createCell(5);
        c85.setCellValue(new HSSFRichTextString("组"));
        c85.setCellStyle(cellStyle1);
        HSSFCell c86 = row8.createCell(6);
        c86.setCellValue(new HSSFRichTextString("备注"));
        CellRangeAddress cra7=new CellRangeAddress(familyLastRow+1, familyLastRow+1, 6, 8);
        sheet.addMergedRegion(cra7);

        HSSFRow row10 = sheet.createRow(familyLastRow+2);
        HSSFCell c101 = row10.createCell(1);
        c101.setCellValue(new HSSFRichTextString(move.getTo_city()));
        c101.setCellStyle(cellStyle);
        HSSFCell c102 = row10.createCell(2);
        c102.setCellValue(new HSSFRichTextString(move.getTo_district()));
        c102.setCellStyle(cellStyle);
        HSSFCell c103 = row10.createCell(3);
        c103.setCellValue(new HSSFRichTextString(move.getTo_town()));
        c103.setCellStyle(cellStyle);
        HSSFCell c104 = row10.createCell(4);
        c104.setCellValue(new HSSFRichTextString(move.getTo_village()));
        c104.setCellStyle(cellStyle);
        HSSFCell c105 = row10.createCell(5);
        c105.setCellValue(new HSSFRichTextString(move.getTo_group()));
        c105.setCellStyle(cellStyle);
        HSSFCell c106 = row10.createCell(6);
        c106.setCellValue(new HSSFRichTextString(move.getTo_remark()));
        c106.setCellStyle(cellStyle);
        CellRangeAddress cra9=new CellRangeAddress(familyLastRow+2, familyLastRow+2, 6, 8);
        sheet.addMergedRegion(cra9);
        //住房部分
        int houseRowNum = familyLastRow+3;
        CellRangeAddress cra10=new CellRangeAddress(houseRowNum, houseRowNum+2, 0, 0);
        sheet.addMergedRegion(cra10);
        HSSFRow rowHouse = sheet.createRow(houseRowNum);
        HSSFCell cHouse0 = rowHouse.createCell(0);
        cHouse0.setCellValue(new HSSFRichTextString("住房情况"));
        HSSFCell cHouse1 = rowHouse.createCell(1);
        cHouse1.setCellValue(new HSSFRichTextString("分类"));
        cHouse1.setCellStyle(cellStyle1);
        HSSFCell cHouse2 = rowHouse.createCell(2);
        cHouse2.setCellValue(new HSSFRichTextString("宅基地面积"));
        cHouse2.setCellStyle(cellStyle1);
        HSSFCell cHouse3 = rowHouse.createCell(3);
        cHouse3.setCellValue(new HSSFRichTextString("砖混结构"));
        cHouse3.setCellStyle(cellStyle1);
        HSSFCell cHouse4 = rowHouse.createCell(4);
        cHouse4.setCellValue(new HSSFRichTextString("砖木结构"));
        cHouse4.setCellStyle(cellStyle1);
        HSSFCell cHouse5 = rowHouse.createCell(5);
        cHouse5.setCellValue(new HSSFRichTextString("土木结构"));
        cHouse5.setCellStyle(cellStyle1);
        HSSFCell cHouse6 = rowHouse.createCell(6);
        cHouse6.setCellValue(new HSSFRichTextString("木（竹）结构"));
        cHouse6.setCellStyle(cellStyle1);
        HSSFCell cHouse7 = rowHouse.createCell(7);
        cHouse7.setCellValue(new HSSFRichTextString("简易房"));
        cHouse7.setCellStyle(cellStyle1);
        HSSFCell cHouse8 = rowHouse.createCell(8);
        cHouse8.setCellValue(new HSSFRichTextString("备注"));
        cHouse8.setCellStyle(cellStyle1);
        HSSFRow rowHouse1 = sheet.createRow(houseRowNum+1);
        HSSFCell cHouse11 = rowHouse1.createCell(1);
        cHouse11.setCellValue(new HSSFRichTextString("主房"));
        cHouse11.setCellStyle(cellStyle1);
        HSSFCell cHouse12 = rowHouse1.createCell(2);
        cHouse12.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_size()));
        cHouse12.setCellStyle(cellStyle);
        HSSFCell cHouse13 = rowHouse1.createCell(3);
        cHouse13.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure1()));
        cHouse13.setCellStyle(cellStyle);
        HSSFCell cHouse14 = rowHouse1.createCell(4);
        cHouse14.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure2()));
        cHouse14.setCellStyle(cellStyle);
        HSSFCell cHouse15 = rowHouse1.createCell(5);
        cHouse15.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure3()));
        cHouse15.setCellStyle(cellStyle);
        HSSFCell cHouse16 = rowHouse1.createCell(6);
        cHouse16.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure4()));
        cHouse16.setCellStyle(cellStyle);
        HSSFCell cHouse17 = rowHouse1.createCell(7);
        cHouse17.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_structure5()));
        cHouse17.setCellStyle(cellStyle);
        HSSFCell cHouse18 = rowHouse1.createCell(8);
        cHouse18.setCellValue(new HSSFRichTextString(house==null?"":house.getMain_remark()));
        cHouse18.setCellStyle(cellStyle);
        HSSFRow rowHouse2 = sheet.createRow(houseRowNum+2);
        HSSFCell cHouse21 = rowHouse2.createCell(1);
        cHouse21.setCellValue(new HSSFRichTextString("附属房"));
        cHouse21.setCellStyle(cellStyle1);
        HSSFCell cHouse22 = rowHouse2.createCell(2);
        cHouse22.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_size()));
        cHouse22.setCellStyle(cellStyle);
        HSSFCell cHouse23 = rowHouse2.createCell(3);
        cHouse23.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure1()));
        cHouse23.setCellStyle(cellStyle);
        HSSFCell cHouse24 = rowHouse2.createCell(4);
        cHouse24.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure2()));
        cHouse24.setCellStyle(cellStyle);
        HSSFCell cHouse25 = rowHouse2.createCell(5);
        cHouse25.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure3()));
        cHouse25.setCellStyle(cellStyle);
        HSSFCell cHouse26 = rowHouse2.createCell(6);
        cHouse26.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure4()));
        cHouse26.setCellStyle(cellStyle);
        HSSFCell cHouse27 = rowHouse2.createCell(7);
        cHouse27.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_structure5()));
        cHouse27.setCellStyle(cellStyle);
        HSSFCell cHouse28 = rowHouse2.createCell(8);
        cHouse28.setCellValue(new HSSFRichTextString(house==null?"":house.getSub_remark()));
        cHouse28.setCellStyle(cellStyle);
        //收入情况部分
        int incomeRowNum = houseRowNum+3;
        CellRangeAddress cra11=new CellRangeAddress(incomeRowNum, incomeRowNum+14, 0, 0);
        sheet.addMergedRegion(cra11);
        HSSFRow rowIncome0 = sheet.createRow(incomeRowNum);
        HSSFCell cIncome0 = rowIncome0.createCell(0);
        cIncome0.setCellValue(new HSSFRichTextString("收入情况"));
        HSSFCell cIncome01 = rowIncome0.createCell(1);
        cIncome01.setCellValue(new HSSFRichTextString("分类"));
        cIncome01.setCellStyle(cellStyle1);
        HSSFCell cIncome02 = rowIncome0.createCell(2);
        cIncome02.setCellValue(new HSSFRichTextString("内容"));
        cIncome02.setCellStyle(cellStyle1);
        HSSFCell cIncome03 = rowIncome0.createCell(3);
        cIncome03.setCellValue(new HSSFRichTextString("数量"));
        cIncome03.setCellStyle(cellStyle1);
        HSSFCell cIncome04 = rowIncome0.createCell(4);
        cIncome04.setCellValue(new HSSFRichTextString("单价"));
        cIncome04.setCellStyle(cellStyle1);
        HSSFCell cIncome05 = rowIncome0.createCell(5);
        cIncome05.setCellValue(new HSSFRichTextString("小计"));
        cIncome05.setCellStyle(cellStyle1);
        CellRangeAddress cra12=new CellRangeAddress(incomeRowNum, incomeRowNum, 6, 8);
        sheet.addMergedRegion(cra12);
        HSSFCell cIncome06 = rowIncome0.createCell(6);
        cIncome06.setCellValue(new HSSFRichTextString("备注"));

        Income zhu = null;
        Income niu = null;
        Income yang = null;
        Income ji = null;
        Income ya = null;
        Income yuye = null;
        Income ruye = null;
        Income yangzhiother = null;
        Income liangshi = null;
        Income shucai = null;
        Income linguo = null;
        Income zhongzhiother = null;
        Income laowuchoulao = null;
        Income fangwuzulin = null;
        if(incomeList !=null || incomeList.size()>0){
            for(int i=0;i<incomeList.size();i++){
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "猪".equals(incomeList.get(i).getIncome_cate())){
                    zhu = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "牛".equals(incomeList.get(i).getIncome_cate())){
                    niu = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "羊".equals(incomeList.get(i).getIncome_cate())){
                    yang = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "鸡".equals(incomeList.get(i).getIncome_cate())){
                    ji = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "鸭".equals(incomeList.get(i).getIncome_cate())){
                    ya = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "渔业".equals(incomeList.get(i).getIncome_cate())){
                    yuye = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "乳业".equals(incomeList.get(i).getIncome_cate())){
                    ruye = incomeList.get(i);
                }
                if("养殖业收入".equals(incomeList.get(i).getIncome_source()) & "其他".equals(incomeList.get(i).getIncome_cate())){
                    yangzhiother = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "粮食".equals(incomeList.get(i).getIncome_cate())){
                    liangshi = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "蔬菜".equals(incomeList.get(i).getIncome_cate())){
                    shucai = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "林果".equals(incomeList.get(i).getIncome_cate())){
                    linguo = incomeList.get(i);
                }
                if("种植业收入".equals(incomeList.get(i).getIncome_source()) & "其他".equals(incomeList.get(i).getIncome_cate())){
                    zhongzhiother = incomeList.get(i);
                }
                if("其他收入".equals(incomeList.get(i).getIncome_source()) & "劳务酬劳".equals(incomeList.get(i).getIncome_cate())){
                    laowuchoulao = incomeList.get(i);
                }
                if("其他收入".equals(incomeList.get(i).getIncome_source()) & "房、耕地租赁".equals(incomeList.get(i).getIncome_cate())){
                    fangwuzulin = incomeList.get(i);
                }
            }
        }
        HSSFRow rowIncome1 = sheet.createRow(incomeRowNum+1);
        CellRangeAddress cra13=new CellRangeAddress(incomeRowNum+1, incomeRowNum+8, 1, 1);
        sheet.addMergedRegion(cra13);
        HSSFCell cIncome11 = rowIncome1.createCell(1);
        cIncome11.setCellValue(new HSSFRichTextString("养殖业收入"));
        HSSFCell cIncome12 = rowIncome1.createCell(2);
        cIncome12.setCellValue(new HSSFRichTextString("猪"));
        cIncome12.setCellStyle(cellStyle1);
        HSSFCell cIncome13 = rowIncome1.createCell(3);
        cIncome13.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getIncome_quantity()));
        cIncome13.setCellStyle(cellStyle);
        HSSFCell cIncome14 = rowIncome1.createCell(4);
        cIncome14.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getIncome_unit()));
        cIncome14.setCellStyle(cellStyle);
        HSSFCell cIncome15 = rowIncome1.createCell(5);
        cIncome15.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getIncome_sum()));
        cIncome15.setCellStyle(cellStyle);
        CellRangeAddress cra14=new CellRangeAddress(incomeRowNum+1, incomeRowNum+1, 6, 8);
        sheet.addMergedRegion(cra14);
        HSSFCell cIncome16 = rowIncome1.createCell(6);
        cIncome16.setCellValue(new HSSFRichTextString(zhu == null?"":zhu.getRemark()));
        cIncome16.setCellStyle(cellStyle);
        //牛
        HSSFRow rowIncome2 = sheet.createRow(incomeRowNum+2);
        HSSFCell cIncome22 = rowIncome2.createCell(2);
        cIncome22.setCellValue(new HSSFRichTextString("牛"));
        cIncome22.setCellStyle(cellStyle1);
        HSSFCell cIncome23 = rowIncome2.createCell(3);
        cIncome23.setCellValue(new HSSFRichTextString(niu == null?"":niu.getIncome_quantity()));
        cIncome23.setCellStyle(cellStyle);
        HSSFCell cIncome24 = rowIncome2.createCell(4);
        cIncome24.setCellValue(new HSSFRichTextString(niu == null?"":niu.getIncome_unit()));
        cIncome24.setCellStyle(cellStyle);
        HSSFCell cIncome25 = rowIncome2.createCell(5);
        cIncome25.setCellValue(new HSSFRichTextString(niu == null?"":niu.getIncome_sum()));
        cIncome25.setCellStyle(cellStyle);
        CellRangeAddress cra15=new CellRangeAddress(incomeRowNum+2, incomeRowNum+2, 6, 8);
        sheet.addMergedRegion(cra15);
        HSSFCell cIncome26 = rowIncome2.createCell(6);
        cIncome26.setCellValue(new HSSFRichTextString(niu == null?"":niu.getRemark()));
        cIncome26.setCellStyle(cellStyle);
        //羊
        HSSFRow rowIncome3 = sheet.createRow(incomeRowNum+3);
        HSSFCell cIncome32 = rowIncome3.createCell(2);
        cIncome32.setCellValue(new HSSFRichTextString("羊"));
        cIncome32.setCellStyle(cellStyle1);
        HSSFCell cIncome33 = rowIncome3.createCell(3);
        cIncome33.setCellValue(new HSSFRichTextString(yang == null?"":yang.getIncome_quantity()));
        cIncome33.setCellStyle(cellStyle);
        HSSFCell cIncome34 = rowIncome3.createCell(4);
        cIncome34.setCellValue(new HSSFRichTextString(yang == null?"":yang.getIncome_unit()));
        cIncome34.setCellStyle(cellStyle);
        HSSFCell cIncome35 = rowIncome3.createCell(5);
        cIncome35.setCellValue(new HSSFRichTextString(yang == null?"":yang.getIncome_sum()));
        cIncome35.setCellStyle(cellStyle);
        CellRangeAddress cra16=new CellRangeAddress(incomeRowNum+3, incomeRowNum+3, 6, 8);
        sheet.addMergedRegion(cra16);
        HSSFCell cIncome36 = rowIncome3.createCell(6);
        cIncome36.setCellValue(new HSSFRichTextString(yang == null?"":yang.getRemark()));
        cIncome36.setCellStyle(cellStyle);
        //鸡
        HSSFRow rowIncome4 = sheet.createRow(incomeRowNum+4);
        HSSFCell cIncome42 = rowIncome4.createCell(2);
        cIncome42.setCellValue(new HSSFRichTextString("鸡"));
        cIncome42.setCellStyle(cellStyle1);
        HSSFCell cIncome43 = rowIncome4.createCell(3);
        cIncome43.setCellValue(new HSSFRichTextString(ji == null?"":ji.getIncome_quantity()));
        cIncome43.setCellStyle(cellStyle);
        HSSFCell cIncome44 = rowIncome4.createCell(4);
        cIncome44.setCellValue(new HSSFRichTextString(ji == null?"":ji.getIncome_unit()));
        cIncome44.setCellStyle(cellStyle);
        HSSFCell cIncome45 = rowIncome4.createCell(5);
        cIncome45.setCellValue(new HSSFRichTextString(ji == null?"":ji.getIncome_sum()));
        cIncome45.setCellStyle(cellStyle);
        CellRangeAddress cra17=new CellRangeAddress(incomeRowNum+4, incomeRowNum+4, 6, 8);
        sheet.addMergedRegion(cra17);
        HSSFCell cIncome46 = rowIncome4.createCell(6);
        cIncome46.setCellValue(new HSSFRichTextString(ji == null?"":ji.getRemark()));
        cIncome46.setCellStyle(cellStyle);
        //鸭
        HSSFRow rowIncome5 = sheet.createRow(incomeRowNum+5);
        HSSFCell cIncome52 = rowIncome5.createCell(2);
        cIncome52.setCellValue(new HSSFRichTextString("鸭"));
        cIncome52.setCellStyle(cellStyle1);
        HSSFCell cIncome53 = rowIncome5.createCell(3);
        cIncome53.setCellValue(new HSSFRichTextString(ya == null?"":ya.getIncome_quantity()));
        cIncome53.setCellStyle(cellStyle);
        HSSFCell cIncome54 = rowIncome5.createCell(4);
        cIncome54.setCellValue(new HSSFRichTextString(ya == null?"":ya.getIncome_unit()));
        cIncome54.setCellStyle(cellStyle);
        HSSFCell cIncome55 = rowIncome5.createCell(5);
        cIncome55.setCellValue(new HSSFRichTextString(ya == null?"":ya.getIncome_sum()));
        cIncome55.setCellStyle(cellStyle);
        CellRangeAddress cra18=new CellRangeAddress(incomeRowNum+5, incomeRowNum+5, 6, 8);
        sheet.addMergedRegion(cra18);
        HSSFCell cIncome56 = rowIncome5.createCell(6);
        cIncome56.setCellValue(new HSSFRichTextString(ya == null?"":ya.getRemark()));
        cIncome56.setCellStyle(cellStyle);
        //渔业
        HSSFRow rowIncome6 = sheet.createRow(incomeRowNum+6);
        HSSFCell cIncome62 = rowIncome6.createCell(2);
        cIncome62.setCellValue(new HSSFRichTextString("渔业"));
        cIncome62.setCellStyle(cellStyle1);
        HSSFCell cIncome63 = rowIncome6.createCell(3);
        cIncome63.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getIncome_quantity()));
        cIncome63.setCellStyle(cellStyle);
        HSSFCell cIncome64 = rowIncome6.createCell(4);
        cIncome64.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getIncome_unit()));
        cIncome64.setCellStyle(cellStyle);
        HSSFCell cIncome65 = rowIncome6.createCell(5);
        cIncome65.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getIncome_sum()));
        cIncome65.setCellStyle(cellStyle);
        CellRangeAddress cra19=new CellRangeAddress(incomeRowNum+6, incomeRowNum+6, 6, 8);
        sheet.addMergedRegion(cra19);
        HSSFCell cIncome66 = rowIncome6.createCell(6);
        cIncome66.setCellValue(new HSSFRichTextString(yuye == null?"":yuye.getRemark()));
        cIncome66.setCellStyle(cellStyle);
        //乳业
        HSSFRow rowIncome7 = sheet.createRow(incomeRowNum+7);
        HSSFCell cIncome72 = rowIncome7.createCell(2);
        cIncome72.setCellValue(new HSSFRichTextString("乳业"));
        cIncome72.setCellStyle(cellStyle1);
        HSSFCell cIncome73 = rowIncome7.createCell(3);
        cIncome73.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getIncome_quantity()));
        cIncome73.setCellStyle(cellStyle);
        HSSFCell cIncome74 = rowIncome7.createCell(4);
        cIncome74.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getIncome_unit()));
        cIncome74.setCellStyle(cellStyle);
        HSSFCell cIncome75 = rowIncome7.createCell(5);
        cIncome75.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getIncome_sum()));
        cIncome75.setCellStyle(cellStyle);
        CellRangeAddress cra20=new CellRangeAddress(incomeRowNum+7, incomeRowNum+7, 6, 8);
        sheet.addMergedRegion(cra20);
        HSSFCell cIncome76 = rowIncome7.createCell(6);
        cIncome76.setCellValue(new HSSFRichTextString(ruye == null?"":ruye.getRemark()));
        cIncome76.setCellStyle(cellStyle);
        //养殖其他
        HSSFRow rowIncome8 = sheet.createRow(incomeRowNum+8);
        HSSFCell cIncome82 = rowIncome8.createCell(2);
        cIncome82.setCellValue(new HSSFRichTextString("其他"));
        cIncome82.setCellStyle(cellStyle1);
        HSSFCell cIncome83 = rowIncome8.createCell(3);
        cIncome83.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getIncome_quantity()));
        cIncome83.setCellStyle(cellStyle);
        HSSFCell cIncome84 = rowIncome8.createCell(4);
        cIncome84.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getIncome_unit()));
        cIncome84.setCellStyle(cellStyle);
        HSSFCell cIncome85 = rowIncome8.createCell(5);
        cIncome85.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getIncome_sum()));
        cIncome85.setCellStyle(cellStyle);
        CellRangeAddress cra21=new CellRangeAddress(incomeRowNum+8, incomeRowNum+8, 6, 8);
        sheet.addMergedRegion(cra21);
        HSSFCell cIncome86 = rowIncome8.createCell(6);
        cIncome86.setCellValue(new HSSFRichTextString(yangzhiother == null?"":yangzhiother.getRemark()));
        cIncome86.setCellStyle(cellStyle);
        //种植业
        //粮食
        HSSFRow rowIncome9 = sheet.createRow(incomeRowNum+9);
        CellRangeAddress cra22=new CellRangeAddress(incomeRowNum+9, incomeRowNum+12, 1, 1);
        sheet.addMergedRegion(cra22);
        HSSFCell cIncome91 = rowIncome9.createCell(1);
        cIncome91.setCellValue(new HSSFRichTextString("种殖业收入"));
        HSSFCell cIncome92 = rowIncome9.createCell(2);
        cIncome92.setCellValue(new HSSFRichTextString("粮食"));
        cIncome92.setCellStyle(cellStyle1);
        HSSFCell cIncome93 = rowIncome9.createCell(3);
        cIncome93.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getIncome_quantity()));
        cIncome93.setCellStyle(cellStyle);
        HSSFCell cIncome94 = rowIncome9.createCell(4);
        cIncome94.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getIncome_unit()));
        cIncome94.setCellStyle(cellStyle);
        HSSFCell cIncome95 = rowIncome9.createCell(5);
        cIncome95.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getIncome_sum()));
        cIncome95.setCellStyle(cellStyle);
        CellRangeAddress cra23=new CellRangeAddress(incomeRowNum+9, incomeRowNum+9, 6, 8);
        sheet.addMergedRegion(cra23);
        HSSFCell cIncome96 = rowIncome9.createCell(6);
        cIncome96.setCellValue(new HSSFRichTextString(liangshi == null?"":liangshi.getRemark()));
        cIncome96.setCellStyle(cellStyle);
        //蔬菜
        HSSFRow rowIncome10 = sheet.createRow(incomeRowNum+10);
        HSSFCell cIncome102 = rowIncome10.createCell(2);
        cIncome102.setCellValue(new HSSFRichTextString("蔬菜"));
        cIncome102.setCellStyle(cellStyle1);
        HSSFCell cIncome103 = rowIncome10.createCell(3);
        cIncome103.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getIncome_quantity()));
        cIncome103.setCellStyle(cellStyle);
        HSSFCell cIncome104 = rowIncome10.createCell(4);
        cIncome104.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getIncome_unit()));
        cIncome104.setCellStyle(cellStyle);
        HSSFCell cIncome105 = rowIncome10.createCell(5);
        cIncome105.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getIncome_sum()));
        cIncome105.setCellStyle(cellStyle);
        CellRangeAddress cra24=new CellRangeAddress(incomeRowNum+10, incomeRowNum+10, 6, 8);
        sheet.addMergedRegion(cra24);
        HSSFCell cIncome106 = rowIncome10.createCell(6);
        cIncome106.setCellValue(new HSSFRichTextString(shucai == null?"":shucai.getRemark()));
        cIncome106.setCellStyle(cellStyle);
        //林果
        HSSFRow rowIncome11 = sheet.createRow(incomeRowNum+11);
        HSSFCell cIncome112 = rowIncome11.createCell(2);
        cIncome112.setCellValue(new HSSFRichTextString("林果"));
        cIncome112.setCellStyle(cellStyle1);
        HSSFCell cIncome113 = rowIncome11.createCell(3);
        cIncome113.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getIncome_quantity()));
        cIncome113.setCellStyle(cellStyle);
        HSSFCell cIncome114 = rowIncome11.createCell(4);
        cIncome114.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getIncome_unit()));
        cIncome114.setCellStyle(cellStyle);
        HSSFCell cIncome115 = rowIncome11.createCell(5);
        cIncome115.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getIncome_sum()));
        cIncome115.setCellStyle(cellStyle);
        CellRangeAddress cra25=new CellRangeAddress(incomeRowNum+11, incomeRowNum+11, 6, 8);
        sheet.addMergedRegion(cra25);
        HSSFCell cIncome116 = rowIncome11.createCell(6);
        cIncome116.setCellValue(new HSSFRichTextString(linguo == null?"":linguo.getRemark()));
        cIncome116.setCellStyle(cellStyle);
        //其他
        HSSFRow rowIncome12 = sheet.createRow(incomeRowNum+12);
        HSSFCell cIncome122 = rowIncome12.createCell(2);
        cIncome122.setCellValue(new HSSFRichTextString("其他"));
        cIncome122.setCellStyle(cellStyle1);
        HSSFCell cIncome123 = rowIncome12.createCell(3);
        cIncome123.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getIncome_quantity()));
        cIncome123.setCellStyle(cellStyle);
        HSSFCell cIncome124 = rowIncome12.createCell(4);
        cIncome124.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getIncome_unit()));
        cIncome124.setCellStyle(cellStyle);
        HSSFCell cIncome125 = rowIncome12.createCell(5);
        cIncome125.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getIncome_sum()));
        cIncome125.setCellStyle(cellStyle);
        CellRangeAddress cra26=new CellRangeAddress(incomeRowNum+12, incomeRowNum+12, 6, 8);
        sheet.addMergedRegion(cra26);
        HSSFCell cIncome126 = rowIncome12.createCell(6);
        cIncome126.setCellValue(new HSSFRichTextString(zhongzhiother == null?"":zhongzhiother.getRemark()));
        cIncome126.setCellStyle(cellStyle);
        //其他收入
        //劳务酬劳
        HSSFRow rowIncome13 = sheet.createRow(incomeRowNum+13);
        CellRangeAddress cra27=new CellRangeAddress(incomeRowNum+13, incomeRowNum+14, 1, 1);
        sheet.addMergedRegion(cra27);
        HSSFCell cIncome131 = rowIncome13.createCell(1);
        cIncome131.setCellValue(new HSSFRichTextString("其他收入"));
        HSSFCell cIncome132 = rowIncome13.createCell(2);
        cIncome132.setCellValue(new HSSFRichTextString("劳务酬劳"));
        cIncome132.setCellStyle(cellStyle1);
        HSSFCell cIncome133 = rowIncome13.createCell(3);
        cIncome133.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getIncome_quantity()));
        cIncome133.setCellStyle(cellStyle);
        HSSFCell cIncome134 = rowIncome13.createCell(4);
        cIncome134.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getIncome_unit()));
        cIncome134.setCellStyle(cellStyle);
        HSSFCell cIncome135 = rowIncome13.createCell(5);
        cIncome135.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getIncome_sum()));
        cIncome135.setCellStyle(cellStyle);
        CellRangeAddress cra28=new CellRangeAddress(incomeRowNum+13, incomeRowNum+13, 6, 8);
        sheet.addMergedRegion(cra28);
        HSSFCell cIncome136 = rowIncome13.createCell(6);
        cIncome136.setCellValue(new HSSFRichTextString(laowuchoulao == null?"":laowuchoulao.getRemark()));
        cIncome136.setCellStyle(cellStyle);
        //房、耕地租赁
        HSSFRow rowIncome14 = sheet.createRow(incomeRowNum+14);
        HSSFCell cIncome142 = rowIncome14.createCell(2);
        cIncome142.setCellValue(new HSSFRichTextString("房、耕地租赁"));
        cIncome142.setCellStyle(cellStyle1);
        HSSFCell cIncome143 = rowIncome14.createCell(3);
        cIncome143.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getIncome_quantity()));
        cIncome143.setCellStyle(cellStyle);
        HSSFCell cIncome144 = rowIncome14.createCell(4);
        cIncome144.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getIncome_unit()));
        cIncome144.setCellStyle(cellStyle);
        HSSFCell cIncome145 = rowIncome14.createCell(5);
        cIncome145.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getIncome_sum()));
        cIncome145.setCellStyle(cellStyle);
        CellRangeAddress cra29=new CellRangeAddress(incomeRowNum+14, incomeRowNum+14, 6, 8);
        sheet.addMergedRegion(cra29);
        HSSFCell cIncome146 = rowIncome13.createCell(6);
        cIncome146.setCellValue(new HSSFRichTextString(fangwuzulin == null?"":fangwuzulin.getRemark()));
        cIncome146.setCellStyle(cellStyle);
        //支出情况部分
        int outcomeRowNum = incomeRowNum+15;
        CellRangeAddress cra30=new CellRangeAddress(outcomeRowNum, outcomeRowNum+18, 0, 0);
        sheet.addMergedRegion(cra30);
        HSSFRow rowOutcome0 = sheet.createRow(outcomeRowNum);
        HSSFCell cOutcome00 = rowOutcome0.createCell(0);
        cOutcome00.setCellValue(new HSSFRichTextString("支出情况"));
        HSSFCell cOutcome01 = rowOutcome0.createCell(1);
        cOutcome01.setCellValue(new HSSFRichTextString("分类"));
        cOutcome01.setCellStyle(cellStyle1);
        HSSFCell cOutcome02 = rowOutcome0.createCell(2);
        cOutcome02.setCellValue(new HSSFRichTextString("内容"));
        cOutcome02.setCellStyle(cellStyle1);
        HSSFCell cOutcome03 = rowOutcome0.createCell(3);
        cOutcome03.setCellValue(new HSSFRichTextString("数量"));
        cOutcome03.setCellStyle(cellStyle1);
        HSSFCell cOutcome04 = rowOutcome0.createCell(4);
        cOutcome04.setCellValue(new HSSFRichTextString("单价"));
        cOutcome04.setCellStyle(cellStyle1);
        HSSFCell cOutcome05 = rowOutcome0.createCell(5);
        cOutcome05.setCellValue(new HSSFRichTextString("小计"));
        cOutcome05.setCellStyle(cellStyle1);
        CellRangeAddress cra31=new CellRangeAddress(outcomeRowNum, outcomeRowNum, 6, 8);
        sheet.addMergedRegion(cra31);
        HSSFCell cOutcome06 = rowOutcome0.createCell(6);
        cOutcome06.setCellValue(new HSSFRichTextString("备注"));
        Outcome zizhong = null;
        Outcome huafei = null;
        Outcome gugong = null;
        Outcome jigengzhichu = null;
        Outcome guangaishuidianfei = null;
        Outcome chengzugengdi = null;
        Outcome youzhong = null;
        Outcome siliao = null;
        Outcome jibingfangzhi = null;
        Outcome yangzhizhichuother = null;
        Outcome zhushi = null;
        Outcome yiwu = null;
        Outcome shuidianfei = null;
        Outcome tongxunfei = null;
        Outcome jiaotongfei = null;
        Outcome jiaoyu = null;
        Outcome yiliao = null;
        Outcome shenghuozhichuother = null;
        if(outcomeList !=null || outcomeList.size()>0) {
            for (int i = 0; i < outcomeList.size(); i++) {
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "籽种".equals(outcomeList.get(i).getOutcome_cate())) {
                    zizhong = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "化肥、农药".equals(outcomeList.get(i).getOutcome_cate())) {
                    huafei = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "雇工".equals(outcomeList.get(i).getOutcome_cate())) {
                    gugong = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "机耕支出".equals(outcomeList.get(i).getOutcome_cate())) {
                    jigengzhichu = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "灌溉水电费".equals(outcomeList.get(i).getOutcome_cate())) {
                    guangaishuidianfei = outcomeList.get(i);
                }
                if ("种植业支出".equals(outcomeList.get(i).getOutcome_source()) & "承租耕地".equals(outcomeList.get(i).getOutcome_cate())) {
                    chengzugengdi = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "幼种".equals(outcomeList.get(i).getOutcome_cate())) {
                    youzhong = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "饲料".equals(outcomeList.get(i).getOutcome_cate())) {
                    siliao = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "疾病防治".equals(outcomeList.get(i).getOutcome_cate())) {
                    jibingfangzhi = outcomeList.get(i);
                }
                if ("养殖业支出".equals(outcomeList.get(i).getOutcome_source()) & "其他".equals(outcomeList.get(i).getOutcome_cate())) {
                    yangzhizhichuother = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "主食".equals(outcomeList.get(i).getOutcome_cate())) {
                    zhushi = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "衣物".equals(outcomeList.get(i).getOutcome_cate())) {
                    yiwu = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "水、电费".equals(outcomeList.get(i).getOutcome_cate())) {
                    shuidianfei = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "通讯费".equals(outcomeList.get(i).getOutcome_cate())) {
                    tongxunfei = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "交通费".equals(outcomeList.get(i).getOutcome_cate())) {
                    jiaotongfei = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "教育".equals(outcomeList.get(i).getOutcome_cate())) {
                    jiaoyu = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "医疗".equals(outcomeList.get(i).getOutcome_cate())) {
                    yiliao = outcomeList.get(i);
                }
                if ("生活支出".equals(outcomeList.get(i).getOutcome_source()) & "其他".equals(outcomeList.get(i).getOutcome_cate())) {
                    shenghuozhichuother = outcomeList.get(i);
                }
            }
        }
        //种植业支出
        //籽种
        HSSFRow rowOutcome1 = sheet.createRow(outcomeRowNum+1);
        CellRangeAddress cra32=new CellRangeAddress(outcomeRowNum+1, outcomeRowNum+6, 1, 1);
        sheet.addMergedRegion(cra32);
        HSSFCell cOutcome11 = rowOutcome1.createCell(1);
        cOutcome11.setCellValue(new HSSFRichTextString("种殖业支出"));
        HSSFCell cOutcome12 = rowOutcome1.createCell(2);
        cOutcome12.setCellValue(new HSSFRichTextString("籽种"));
        cOutcome12.setCellStyle(cellStyle1);
        HSSFCell cOutcome13 = rowOutcome1.createCell(3);
        cOutcome13.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getOutcome_quantity()));
        cOutcome13.setCellStyle(cellStyle);
        HSSFCell cOutcome14 = rowOutcome1.createCell(4);
        cOutcome14.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getOutcome_unit()));
        cOutcome14.setCellStyle(cellStyle);
        HSSFCell cOutcome15 = rowOutcome1.createCell(5);
        cOutcome15.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getOutcome_sum()));
        cOutcome15.setCellStyle(cellStyle);
        CellRangeAddress cra33=new CellRangeAddress(outcomeRowNum+1, outcomeRowNum+1, 6, 8);
        sheet.addMergedRegion(cra33);
        HSSFCell cOutcome16 = rowOutcome1.createCell(6);
        cOutcome16.setCellValue(new HSSFRichTextString(zizhong == null?"":zizhong.getRemark()));
        cOutcome16.setCellStyle(cellStyle);
        //化肥
        HSSFRow rowOutcome2 = sheet.createRow(outcomeRowNum+2);
        HSSFCell cOutcome22 = rowOutcome2.createCell(2);
        cOutcome22.setCellValue(new HSSFRichTextString("化肥、农药"));
        cOutcome22.setCellStyle(cellStyle1);
        HSSFCell cOutcome23 = rowOutcome2.createCell(3);
        cOutcome23.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getOutcome_quantity()));
        cOutcome23.setCellStyle(cellStyle);
        HSSFCell cOutcome24 = rowOutcome2.createCell(4);
        cOutcome24.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getOutcome_unit()));
        cOutcome24.setCellStyle(cellStyle);
        HSSFCell cOutcome25 = rowOutcome2.createCell(5);
        cOutcome25.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getOutcome_sum()));
        cOutcome25.setCellStyle(cellStyle);
        CellRangeAddress cra34=new CellRangeAddress(outcomeRowNum+2, outcomeRowNum+2, 6, 8);
        sheet.addMergedRegion(cra34);
        HSSFCell cOutcome26 = rowOutcome2.createCell(6);
        cOutcome26.setCellValue(new HSSFRichTextString(huafei == null?"":huafei.getRemark()));
        cOutcome26.setCellStyle(cellStyle);
        //雇工
        HSSFRow rowOutcome3 = sheet.createRow(outcomeRowNum+3);
        HSSFCell cOutcome32 = rowOutcome3.createCell(2);
        cOutcome32.setCellValue(new HSSFRichTextString("雇工"));
        cOutcome32.setCellStyle(cellStyle1);
        HSSFCell cOutcome33 = rowOutcome3.createCell(3);
        cOutcome33.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getOutcome_quantity()));
        cOutcome33.setCellStyle(cellStyle);
        HSSFCell cOutcome34 = rowOutcome3.createCell(4);
        cOutcome34.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getOutcome_unit()));
        cOutcome34.setCellStyle(cellStyle);
        HSSFCell cOutcome35 = rowOutcome3.createCell(5);
        cOutcome35.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getOutcome_sum()));
        cOutcome35.setCellStyle(cellStyle);
        CellRangeAddress cra35=new CellRangeAddress(outcomeRowNum+3, outcomeRowNum+3, 6, 8);
        sheet.addMergedRegion(cra35);
        HSSFCell cOutcome36 = rowOutcome3.createCell(6);
        cOutcome36.setCellValue(new HSSFRichTextString(gugong == null?"":gugong.getRemark()));
        cOutcome36.setCellStyle(cellStyle);
        //机耕支出
        HSSFRow rowOutcome4 = sheet.createRow(outcomeRowNum+4);
        HSSFCell cOutcome42 = rowOutcome4.createCell(2);
        cOutcome42.setCellValue(new HSSFRichTextString("机耕支出"));
        cOutcome42.setCellStyle(cellStyle1);
        HSSFCell cOutcome43 = rowOutcome4.createCell(3);
        cOutcome43.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getOutcome_quantity()));
        cOutcome43.setCellStyle(cellStyle);
        HSSFCell cOutcome44 = rowOutcome4.createCell(4);
        cOutcome44.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getOutcome_unit()));
        cOutcome44.setCellStyle(cellStyle);
        HSSFCell cOutcome45 = rowOutcome4.createCell(5);
        cOutcome45.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getOutcome_sum()));
        cOutcome45.setCellStyle(cellStyle);
        CellRangeAddress cra36=new CellRangeAddress(outcomeRowNum+4, outcomeRowNum+4, 6, 8);
        sheet.addMergedRegion(cra36);
        HSSFCell cOutcome46 = rowOutcome4.createCell(6);
        cOutcome46.setCellValue(new HSSFRichTextString(jigengzhichu == null?"":jigengzhichu.getRemark()));
        cOutcome46.setCellStyle(cellStyle);
        //灌溉水电费
        HSSFRow rowOutcome5 = sheet.createRow(outcomeRowNum+5);
        HSSFCell cOutcome52 = rowOutcome5.createCell(2);
        cOutcome52.setCellValue(new HSSFRichTextString("灌溉水电费"));
        cOutcome52.setCellStyle(cellStyle1);
        HSSFCell cOutcome53 = rowOutcome5.createCell(3);
        cOutcome53.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getOutcome_quantity()));
        cOutcome53.setCellStyle(cellStyle);
        HSSFCell cOutcome54 = rowOutcome5.createCell(4);
        cOutcome54.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getOutcome_unit()));
        cOutcome54.setCellStyle(cellStyle);
        HSSFCell cOutcome55 = rowOutcome5.createCell(5);
        cOutcome55.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getOutcome_sum()));
        cOutcome55.setCellStyle(cellStyle);
        CellRangeAddress cra37=new CellRangeAddress(outcomeRowNum+5, outcomeRowNum+5, 6, 8);
        sheet.addMergedRegion(cra37);
        HSSFCell cOutcome56 = rowOutcome5.createCell(6);
        cOutcome56.setCellValue(new HSSFRichTextString(guangaishuidianfei == null?"":guangaishuidianfei.getRemark()));
        cOutcome56.setCellStyle(cellStyle);
        //承租耕地
        HSSFRow rowOutcome6 = sheet.createRow(outcomeRowNum+6);
        HSSFCell cOutcome62 = rowOutcome6.createCell(2);
        cOutcome62.setCellValue(new HSSFRichTextString("承租耕地"));
        cOutcome62.setCellStyle(cellStyle1);
        HSSFCell cOutcome63 = rowOutcome6.createCell(3);
        cOutcome63.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getOutcome_quantity()));
        cOutcome63.setCellStyle(cellStyle);
        HSSFCell cOutcome64 = rowOutcome6.createCell(4);
        cOutcome64.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getOutcome_unit()));
        cOutcome64.setCellStyle(cellStyle);
        HSSFCell cOutcome65 = rowOutcome6.createCell(5);
        cOutcome65.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getOutcome_sum()));
        cOutcome65.setCellStyle(cellStyle);
        CellRangeAddress cra38=new CellRangeAddress(outcomeRowNum+6, outcomeRowNum+6, 6, 8);
        sheet.addMergedRegion(cra38);
        HSSFCell cOutcome66 = rowOutcome6.createCell(6);
        cOutcome66.setCellValue(new HSSFRichTextString(chengzugengdi == null?"":chengzugengdi.getRemark()));
        cOutcome66.setCellStyle(cellStyle);
        //养植业支出
        //幼种
        HSSFRow rowOutcome7 = sheet.createRow(outcomeRowNum+7);
        CellRangeAddress cra39=new CellRangeAddress(outcomeRowNum+7, outcomeRowNum+10, 1, 1);
        sheet.addMergedRegion(cra39);
        HSSFCell cOutcome71 = rowOutcome7.createCell(1);
        cOutcome71.setCellValue(new HSSFRichTextString("养殖业支出"));
        HSSFCell cOutcome72 = rowOutcome7.createCell(2);
        cOutcome72.setCellValue(new HSSFRichTextString("幼种"));
        cOutcome72.setCellStyle(cellStyle1);
        HSSFCell cOutcome73 = rowOutcome7.createCell(3);
        cOutcome73.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getOutcome_quantity()));
        cOutcome73.setCellStyle(cellStyle);
        HSSFCell cOutcome74 = rowOutcome7.createCell(4);
        cOutcome74.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getOutcome_unit()));
        cOutcome74.setCellStyle(cellStyle);
        HSSFCell cOutcome75 = rowOutcome7.createCell(5);
        cOutcome75.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getOutcome_sum()));
        cOutcome75.setCellStyle(cellStyle);
        CellRangeAddress cra40=new CellRangeAddress(outcomeRowNum+7, outcomeRowNum+7, 6, 8);
        sheet.addMergedRegion(cra40);
        HSSFCell cOutcome76 = rowOutcome7.createCell(6);
        cOutcome76.setCellValue(new HSSFRichTextString(youzhong == null?"":youzhong.getRemark()));
        cOutcome76.setCellStyle(cellStyle);
        //饲料
        HSSFRow rowOutcome8 = sheet.createRow(outcomeRowNum+8);
        HSSFCell cOutcome82 = rowOutcome8.createCell(2);
        cOutcome82.setCellValue(new HSSFRichTextString("饲料"));
        cOutcome82.setCellStyle(cellStyle1);
        HSSFCell cOutcome83 = rowOutcome8.createCell(3);
        cOutcome83.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getOutcome_quantity()));
        cOutcome83.setCellStyle(cellStyle);
        HSSFCell cOutcome84 = rowOutcome8.createCell(4);
        cOutcome84.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getOutcome_unit()));
        cOutcome84.setCellStyle(cellStyle);
        HSSFCell cOutcome85 = rowOutcome8.createCell(5);
        cOutcome85.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getOutcome_sum()));
        cOutcome85.setCellStyle(cellStyle);
        CellRangeAddress cra41=new CellRangeAddress(outcomeRowNum+8, outcomeRowNum+8, 6, 8);
        sheet.addMergedRegion(cra41);
        HSSFCell cOutcome86 = rowOutcome8.createCell(6);
        cOutcome86.setCellValue(new HSSFRichTextString(siliao == null?"":siliao.getRemark()));
        cOutcome86.setCellStyle(cellStyle);
        //疫病防治
        HSSFRow rowOutcome9 = sheet.createRow(outcomeRowNum+9);
        HSSFCell cOutcome92 = rowOutcome9.createCell(2);
        cOutcome92.setCellValue(new HSSFRichTextString("疫病防治"));
        cOutcome92.setCellStyle(cellStyle1);
        HSSFCell cOutcome93 = rowOutcome9.createCell(3);
        cOutcome93.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getOutcome_quantity()));
        cOutcome93.setCellStyle(cellStyle);
        HSSFCell cOutcome94 = rowOutcome9.createCell(4);
        cOutcome94.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getOutcome_unit()));
        cOutcome94.setCellStyle(cellStyle);
        HSSFCell cOutcome95 = rowOutcome9.createCell(5);
        cOutcome95.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getOutcome_sum()));
        cOutcome95.setCellStyle(cellStyle);
        CellRangeAddress cra42=new CellRangeAddress(outcomeRowNum+9, outcomeRowNum+9, 6, 8);
        sheet.addMergedRegion(cra42);
        HSSFCell cOutcome96 = rowOutcome9.createCell(6);
        cOutcome96.setCellValue(new HSSFRichTextString(jibingfangzhi == null?"":jibingfangzhi.getRemark()));
        cOutcome96.setCellStyle(cellStyle);
        //其他
        HSSFRow rowOutcome10 = sheet.createRow(outcomeRowNum+10);
        HSSFCell cOutcome102 = rowOutcome10.createCell(2);
        cOutcome102.setCellValue(new HSSFRichTextString("其他"));
        cOutcome102.setCellStyle(cellStyle1);
        HSSFCell cOutcome103 = rowOutcome10.createCell(3);
        cOutcome103.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getOutcome_quantity()));
        cOutcome103.setCellStyle(cellStyle);
        HSSFCell cOutcome104 = rowOutcome10.createCell(4);
        cOutcome104.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getOutcome_unit()));
        cOutcome104.setCellStyle(cellStyle);
        HSSFCell cOutcome105 = rowOutcome10.createCell(5);
        cOutcome105.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getOutcome_sum()));
        cOutcome105.setCellStyle(cellStyle);
        CellRangeAddress cra43=new CellRangeAddress(outcomeRowNum+10, outcomeRowNum+10, 6, 8);
        sheet.addMergedRegion(cra43);
        HSSFCell cOutcome106 = rowOutcome10.createCell(6);
        cOutcome106.setCellValue(new HSSFRichTextString(yangzhizhichuother == null?"":yangzhizhichuother.getRemark()));
        cOutcome106.setCellStyle(cellStyle);
        //生活支出
        //主食
        HSSFRow rowOutcome11 = sheet.createRow(outcomeRowNum+11);
        CellRangeAddress cra44=new CellRangeAddress(outcomeRowNum+11, outcomeRowNum+18, 1, 1);
        sheet.addMergedRegion(cra44);
        HSSFCell cOutcome111 = rowOutcome11.createCell(1);
        cOutcome111.setCellValue(new HSSFRichTextString("生活支出"));
        HSSFCell cOutcome112 = rowOutcome11.createCell(2);
        cOutcome112.setCellValue(new HSSFRichTextString("主食"));
        cOutcome112.setCellStyle(cellStyle1);
        HSSFCell cOutcome113 = rowOutcome11.createCell(3);
        cOutcome113.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getOutcome_quantity()));
        cOutcome113.setCellStyle(cellStyle);
        HSSFCell cOutcome114 = rowOutcome11.createCell(4);
        cOutcome114.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getOutcome_unit()));
        cOutcome114.setCellStyle(cellStyle);
        HSSFCell cOutcome115 = rowOutcome11.createCell(5);
        cOutcome115.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getOutcome_sum()));
        cOutcome115.setCellStyle(cellStyle);
        CellRangeAddress cra45=new CellRangeAddress(outcomeRowNum+11, outcomeRowNum+11, 6, 8);
        sheet.addMergedRegion(cra45);
        HSSFCell cOutcome116 = rowOutcome11.createCell(6);
        cOutcome116.setCellValue(new HSSFRichTextString(zhushi == null?"":zhushi.getRemark()));
        cOutcome116.setCellStyle(cellStyle);
        //衣物
        HSSFRow rowOutcome12 = sheet.createRow(outcomeRowNum+12);
        HSSFCell cOutcome122 = rowOutcome12.createCell(2);
        cOutcome122.setCellValue(new HSSFRichTextString("衣物"));
        cOutcome122.setCellStyle(cellStyle1);
        HSSFCell cOutcome123 = rowOutcome12.createCell(3);
        cOutcome123.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getOutcome_quantity()));
        cOutcome123.setCellStyle(cellStyle);
        HSSFCell cOutcome124 = rowOutcome12.createCell(4);
        cOutcome124.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getOutcome_unit()));
        cOutcome124.setCellStyle(cellStyle);
        HSSFCell cOutcome125 = rowOutcome12.createCell(5);
        cOutcome125.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getOutcome_sum()));
        cOutcome125.setCellStyle(cellStyle);
        CellRangeAddress cra46=new CellRangeAddress(outcomeRowNum+12, outcomeRowNum+12, 6, 8);
        sheet.addMergedRegion(cra46);
        HSSFCell cOutcome126 = rowOutcome12.createCell(6);
        cOutcome126.setCellValue(new HSSFRichTextString(yiwu == null?"":yiwu.getRemark()));
        cOutcome126.setCellStyle(cellStyle);
        //水、电费
        HSSFRow rowOutcome13 = sheet.createRow(outcomeRowNum+13);
        HSSFCell cOutcome132 = rowOutcome13.createCell(2);
        cOutcome132.setCellValue(new HSSFRichTextString("水、电费"));
        cOutcome132.setCellStyle(cellStyle1);
        HSSFCell cOutcome133 = rowOutcome13.createCell(3);
        cOutcome133.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getOutcome_quantity()));
        cOutcome133.setCellStyle(cellStyle);
        HSSFCell cOutcome134 = rowOutcome13.createCell(4);
        cOutcome134.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getOutcome_unit()));
        cOutcome134.setCellStyle(cellStyle);
        HSSFCell cOutcome135 = rowOutcome13.createCell(5);
        cOutcome135.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getOutcome_sum()));
        cOutcome135.setCellStyle(cellStyle);
        CellRangeAddress cra47=new CellRangeAddress(outcomeRowNum+13, outcomeRowNum+13, 6, 8);
        sheet.addMergedRegion(cra47);
        HSSFCell cOutcome136 = rowOutcome13.createCell(6);
        cOutcome136.setCellValue(new HSSFRichTextString(shuidianfei == null?"":shuidianfei.getRemark()));
        cOutcome136.setCellStyle(cellStyle);
        //通讯费
        HSSFRow rowOutcome14 = sheet.createRow(outcomeRowNum+14);
        HSSFCell cOutcome142 = rowOutcome14.createCell(2);
        cOutcome142.setCellValue(new HSSFRichTextString("通讯费"));
        cOutcome142.setCellStyle(cellStyle1);
        HSSFCell cOutcome143 = rowOutcome14.createCell(3);
        cOutcome143.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getOutcome_quantity()));
        cOutcome143.setCellStyle(cellStyle);
        HSSFCell cOutcome144 = rowOutcome14.createCell(4);
        cOutcome144.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getOutcome_unit()));
        cOutcome144.setCellStyle(cellStyle);
        HSSFCell cOutcome145 = rowOutcome14.createCell(5);
        cOutcome145.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getOutcome_sum()));
        cOutcome145.setCellStyle(cellStyle);
        CellRangeAddress cra48=new CellRangeAddress(outcomeRowNum+14, outcomeRowNum+14, 6, 8);
        sheet.addMergedRegion(cra48);
        HSSFCell cOutcome146 = rowOutcome14.createCell(6);
        cOutcome146.setCellValue(new HSSFRichTextString(tongxunfei == null?"":tongxunfei.getRemark()));
        cOutcome146.setCellStyle(cellStyle);
        //交通费
        HSSFRow rowOutcome15 = sheet.createRow(outcomeRowNum+15);
        HSSFCell cOutcome152 = rowOutcome15.createCell(2);
        cOutcome152.setCellValue(new HSSFRichTextString("交通费"));
        cOutcome152.setCellStyle(cellStyle1);
        HSSFCell cOutcome153 = rowOutcome15.createCell(3);
        cOutcome153.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getOutcome_quantity()));
        cOutcome153.setCellStyle(cellStyle);
        HSSFCell cOutcome154 = rowOutcome15.createCell(4);
        cOutcome154.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getOutcome_unit()));
        cOutcome154.setCellStyle(cellStyle);
        HSSFCell cOutcome155 = rowOutcome15.createCell(5);
        cOutcome155.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getOutcome_sum()));
        cOutcome155.setCellStyle(cellStyle);
        CellRangeAddress cra49=new CellRangeAddress(outcomeRowNum+15, outcomeRowNum+15, 6, 8);
        sheet.addMergedRegion(cra49);
        HSSFCell cOutcome156 = rowOutcome15.createCell(6);
        cOutcome156.setCellValue(new HSSFRichTextString(jiaotongfei == null?"":jiaotongfei.getRemark()));
        cOutcome156.setCellStyle(cellStyle);
        //教育
        HSSFRow rowOutcome16 = sheet.createRow(outcomeRowNum+16);
        HSSFCell cOutcome162 = rowOutcome16.createCell(2);
        cOutcome162.setCellValue(new HSSFRichTextString("教育"));
        cOutcome162.setCellStyle(cellStyle1);
        HSSFCell cOutcome163 = rowOutcome16.createCell(3);
        cOutcome163.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getOutcome_quantity()));
        cOutcome163.setCellStyle(cellStyle);
        HSSFCell cOutcome164 = rowOutcome16.createCell(4);
        cOutcome164.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getOutcome_unit()));
        cOutcome164.setCellStyle(cellStyle);
        HSSFCell cOutcome165 = rowOutcome16.createCell(5);
        cOutcome165.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getOutcome_sum()));
        cOutcome165.setCellStyle(cellStyle);
        CellRangeAddress cra50=new CellRangeAddress(outcomeRowNum+16, outcomeRowNum+16, 6, 8);
        sheet.addMergedRegion(cra50);
        HSSFCell cOutcome166 = rowOutcome16.createCell(6);
        cOutcome166.setCellValue(new HSSFRichTextString(jiaoyu == null?"":jiaoyu.getRemark()));
        cOutcome166.setCellStyle(cellStyle);
        //医疗
        HSSFRow rowOutcome17 = sheet.createRow(outcomeRowNum+17);
        HSSFCell cOutcome172 = rowOutcome17.createCell(2);
        cOutcome172.setCellValue(new HSSFRichTextString("医疗"));
        cOutcome172.setCellStyle(cellStyle1);
        HSSFCell cOutcome173 = rowOutcome17.createCell(3);
        cOutcome173.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getOutcome_quantity()));
        cOutcome173.setCellStyle(cellStyle);
        HSSFCell cOutcome174 = rowOutcome17.createCell(4);
        cOutcome174.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getOutcome_unit()));
        cOutcome174.setCellStyle(cellStyle);
        HSSFCell cOutcome175 = rowOutcome17.createCell(5);
        cOutcome175.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getOutcome_sum()));
        cOutcome175.setCellStyle(cellStyle);
        CellRangeAddress cra51=new CellRangeAddress(outcomeRowNum+17, outcomeRowNum+17, 6, 8);
        sheet.addMergedRegion(cra51);
        HSSFCell cOutcome176 = rowOutcome17.createCell(6);
        cOutcome176.setCellValue(new HSSFRichTextString(yiliao == null?"":yiliao.getRemark()));
        cOutcome176.setCellStyle(cellStyle);
        //其他
        HSSFRow rowOutcome18 = sheet.createRow(outcomeRowNum+18);
        HSSFCell cOutcome182 = rowOutcome18.createCell(2);
        cOutcome182.setCellValue(new HSSFRichTextString("其他"));
        cOutcome182.setCellStyle(cellStyle1);
        HSSFCell cOutcome183 = rowOutcome18.createCell(3);
        cOutcome183.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getOutcome_quantity()));
        cOutcome183.setCellStyle(cellStyle);
        HSSFCell cOutcome184 = rowOutcome18.createCell(4);
        cOutcome184.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getOutcome_unit()));
        cOutcome184.setCellStyle(cellStyle);
        HSSFCell cOutcome185 = rowOutcome18.createCell(5);
        cOutcome185.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getOutcome_sum()));
        cOutcome185.setCellStyle(cellStyle);
        CellRangeAddress cra52=new CellRangeAddress(outcomeRowNum+18, outcomeRowNum+18, 6, 8);
        sheet.addMergedRegion(cra52);
        HSSFCell cOutcome186 = rowOutcome18.createCell(6);
        cOutcome186.setCellValue(new HSSFRichTextString(shenghuozhichuother == null?"":shenghuozhichuother.getRemark()));
        cOutcome186.setCellStyle(cellStyle);
        //其他信息部分
        HSSFRow rowOutcome19 = sheet.createRow(outcomeRowNum+19);
        CellRangeAddress cra53=new CellRangeAddress(outcomeRowNum+19, outcomeRowNum+19, 4, 8);
        sheet.addMergedRegion(cra53);
        HSSFCell cOutcome190 = rowOutcome19.createCell(0);
        cOutcome190.setCellValue(new HSSFRichTextString("其他信息"));
        cOutcome190.setCellStyle(cellStyle1);
        HSSFCell cOutcome191 = rowOutcome19.createCell(1);
        cOutcome191.setCellValue(new HSSFRichTextString("是否建档立卡"));
        cOutcome191.setCellStyle(cellStyle1);
        HSSFCell cOutcome192 = rowOutcome19.createCell(2);
        String prop="";
        String poorReason = "";
        String interviewee = "";
        String interviewer = "";
        String createTime = "";
        if(peopleList !=null && peopleList.size()>0){
            for(int i = 0; i < peopleList.size();i++){
                if(peopleList.get(i).getMaster() == 1){
                    prop = peopleList.get(i).getProp() == 1?"是":"否";
                    poorReason = peopleList.get(i).getPoor_reason();
                    interviewee = peopleList.get(i).getInterviewee();
                    interviewer = peopleList.get(i).getInterviewer();
                    createTime = peopleList.get(i).getCreated_at();
                }
            }
        }
        cOutcome192.setCellValue(new HSSFRichTextString(prop));
        cOutcome192.setCellStyle(cellStyle);
        HSSFCell cOutcome193 = rowOutcome19.createCell(3);
        cOutcome193.setCellValue(new HSSFRichTextString("致贫原因"));
        cOutcome193.setCellStyle(cellStyle1);
        HSSFCell cOutcome194 = rowOutcome19.createCell(4);
        cOutcome194.setCellValue(new HSSFRichTextString(poorReason));
        cOutcome194.setCellStyle(cellStyle);
        //其他信息部分
        HSSFRow rowOutcome20 = sheet.createRow(outcomeRowNum+20);
        HSSFCell cOutcome200 = rowOutcome20.createCell(0);
        cOutcome200.setCellValue(new HSSFRichTextString("被调查人签字"));
        cOutcome200.setCellStyle(cellStyle1);
        CellRangeAddress cra54=new CellRangeAddress(outcomeRowNum+20, outcomeRowNum+20, 1, 2);
        sheet.addMergedRegion(cra54);
        HSSFCell cOutcome201 = rowOutcome20.createCell(1);
        cOutcome201.setCellValue(new HSSFRichTextString(interviewee));
        HSSFCell cOutcome203 = rowOutcome20.createCell(3);
        cOutcome203.setCellValue(new HSSFRichTextString("调查人签字"));
        cOutcome203.setCellStyle(cellStyle1);
        CellRangeAddress cra55=new CellRangeAddress(outcomeRowNum+20, outcomeRowNum+20, 4, 5);
        sheet.addMergedRegion(cra55);
        HSSFCell cOutcome204 = rowOutcome20.createCell(4);
        cOutcome204.setCellValue(new HSSFRichTextString(interviewer));
        HSSFCell cOutcome206 = rowOutcome20.createCell(6);
        cOutcome206.setCellValue(new HSSFRichTextString("填表时间"));
        cOutcome206.setCellStyle(cellStyle1);
        CellRangeAddress cra56=new CellRangeAddress(outcomeRowNum+20, outcomeRowNum+20, 7, 8);
        sheet.addMergedRegion(cra56);
        HSSFCell cOutcome207 = rowOutcome20.createCell(7);
        cOutcome207.setCellValue(new HSSFRichTextString(createTime));







        //给合并的单元格加上边框
        setRegionStyle(sheet,cra0,cellStyle1);
        setRegionStyle(sheet,cra1,cellStyle1);
        setRegionStyle(sheet,cra2,cellStyle);
        setRegionStyle(sheet,cra3,cellStyle1);
        setRegionStyle(sheet,cra4,cellStyle1);
        setRegionStyle(sheet,cra6,cellStyle1);
        setRegionStyle(sheet,cra7,cellStyle1);
        /*setRegionStyle(sheet,cra8,cellStyle);*/
        setRegionStyle(sheet,cra9,cellStyle);
        setRegionStyle(sheet,cra10,cellStyle1);
        setRegionStyle(sheet,cra11,cellStyle1);
        setRegionStyle(sheet,cra12,cellStyle1);
        setRegionStyle(sheet,cra13,cellStyle1);
        setRegionStyle(sheet,cra14,cellStyle);
        setRegionStyle(sheet,cra15,cellStyle);
        setRegionStyle(sheet,cra16,cellStyle);
        setRegionStyle(sheet,cra17,cellStyle);
        setRegionStyle(sheet,cra18,cellStyle);
        setRegionStyle(sheet,cra19,cellStyle);
        setRegionStyle(sheet,cra20,cellStyle);
        setRegionStyle(sheet,cra21,cellStyle);
        setRegionStyle(sheet,cra22,cellStyle1);
        setRegionStyle(sheet,cra23,cellStyle);
        setRegionStyle(sheet,cra24,cellStyle);
        setRegionStyle(sheet,cra25,cellStyle);
        setRegionStyle(sheet,cra26,cellStyle);
        setRegionStyle(sheet,cra27,cellStyle1);
        setRegionStyle(sheet,cra28,cellStyle);
        setRegionStyle(sheet,cra29,cellStyle);
        setRegionStyle(sheet,cra30,cellStyle1);
        setRegionStyle(sheet,cra31,cellStyle1);
        setRegionStyle(sheet,cra32,cellStyle1);
        setRegionStyle(sheet,cra33,cellStyle);
        setRegionStyle(sheet,cra34,cellStyle);
        setRegionStyle(sheet,cra35,cellStyle);
        setRegionStyle(sheet,cra36,cellStyle);
        setRegionStyle(sheet,cra37,cellStyle);
        setRegionStyle(sheet,cra38,cellStyle);
        setRegionStyle(sheet,cra39,cellStyle1);
        setRegionStyle(sheet,cra40,cellStyle);
        setRegionStyle(sheet,cra41,cellStyle);
        setRegionStyle(sheet,cra42,cellStyle);
        setRegionStyle(sheet,cra43,cellStyle);
        setRegionStyle(sheet,cra44,cellStyle1);
        setRegionStyle(sheet,cra45,cellStyle);
        setRegionStyle(sheet,cra46,cellStyle);
        setRegionStyle(sheet,cra47,cellStyle);
        setRegionStyle(sheet,cra48,cellStyle);
        setRegionStyle(sheet,cra49,cellStyle);
        setRegionStyle(sheet,cra50,cellStyle);
        setRegionStyle(sheet,cra51,cellStyle);
        setRegionStyle(sheet,cra52,cellStyle);
        setRegionStyle(sheet,cra53,cellStyle);
        setRegionStyle(sheet,cra54,cellStyle);
        setRegionStyle(sheet,cra55,cellStyle);
        setRegionStyle(sheet,cra56,cellStyle);
        setRegionStyle(sheet,cra60,cellStyle);
        setRegionStyle(sheet,cra61,cellStyle);
        setRegionStyle(sheet,cra62,cellStyle);
        //输出文件
       /* FileOutputStream stream = null;
        try {
            stream = new FileOutputStream("d:/student.xls");
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] body = new byte[0];
        try {
            workbook.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            //=============造响应体=============
            //InputStream is = servletContext.getResourceAsStream(path);
            //创建一个和流一样多的数组
            body = new byte[is.available()];
            //3、将流的数据放在数组里面
            is.read(body);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //==============造响应头================
        HttpHeaders headers = new HttpHeaders();
        //文件下载的响应头
        //按照以前乱码的解决方式；

        //文件名乱码解决
        String filename = fid+".xls";
        try {
            filename = new String(filename.getBytes("GBK"),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        headers.add("Content-Disposition", "attachment; filename="+filename);


        //第一个参数代表给浏览器的响应数据（响应体）
        //第二个参数代表当前响应的响应头（定制响应头）MultiValueMap
        //第三个参数代表当前响应状态码（statusCode）HttpStatus
        ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

        return re;
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
    public void setRegionStyle1(HSSFSheet sheet, CellRangeAddress region,
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
