package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.House;
import com.bhidi.lincang.bean.Move;
import com.bhidi.lincang.bean.People;
import com.bhidi.lincang.bean.PeopleList;
import com.bhidi.lincang.dao.ExcelDao;
import com.bhidi.lincang.system.DBUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Created by admin on 2017/8/21.
 */
@Service
public class ExcelServiceImp implements ExcelServiceInf{
    @Autowired
    ExcelDao excelDao;
    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     * @throws Exception
     */

    public String readService(File excelFile){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        //读取步骤
        Workbook workbook = null;
        try{
            // 文件流
            FileInputStream is = new FileInputStream(excelFile);
            //根据输入流生成对应版本的工作簿workbook。
            workbook = WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Sheet的数量
        int SheetCount = workbook.getNumberOfSheets();
        System.out.println("Sheet的数量"+SheetCount);
        //取第一个sheet，在这里默认第一个sheet就是移民搬迁登记表。
        Sheet firstSheet = workbook.getSheetAt(0);
        String firstSheetName = firstSheet.getSheetName();
        System.out.println("第一个sheet的名字："+firstSheetName);
        //00获取移民编号
        Row row0 = firstSheet.getRow(0);
        Cell cell00 = row0.getCell(0);
        String fid = cell00.getStringCellValue();
        if( "".equals(fid) ){
            return "表编号不可以为空！";
        }
        System.out.println(fid);
        //根据查询数据库中是否存在此户的数据，如果有，就请他们去修改页面进行修改，否则可以继续
       /* int ifexit = DBUtils.queryFid("select fid from people where fid = "+'"'+fid+'"');
        if( ifexit == 1 ){
            return "数据库中已经存在你们家的信息，请前往修改页面进行修改。";
        }*/
        //22获取所属水库
        Row row2 = firstSheet.getRow(2);
        Cell cell22 = row2.getCell(2);
        if(cell22!=null){
            cell22.setCellType(Cell.CELL_TYPE_STRING);
        }
        String reservoir = cell22.getStringCellValue();
        if( "".equals(reservoir) ){
            return "所属水库不可以为空！";
        }
        System.out.println("所属水库："+reservoir);
        //24获取安置点
        Cell cell24 = row2.getCell(4);
        if(cell24!=null){
            cell24.setCellType(Cell.CELL_TYPE_STRING);
        }
        String location = cell24.getStringCellValue();
        if( "".equals(location) ){
            return "安置点不可以为空！";
        }
        System.out.println("安置点："+location);
        //27获取户主姓名，以此来将下边的人来进行身份的鉴别，如果这个单元格里边没有值，取出来的是“”；空串。
        Cell cell27 = row2.getCell(7);
        if(cell27!=null){
            cell27.setCellType(Cell.CELL_TYPE_STRING);
        }
        String masterName = cell27.getStringCellValue();
        if( "".equals(masterName) ){
            return "户主姓名不可以为空！";
        }
        System.out.println("户主姓名："+masterName);

        //第五行开始取个人信息,每行的名字取出来都要和前边户主的名字进行比较，进而来判断人的身份。
        //在这里我们要查出来合并单元格的多少来判断一共有几行是用来写家庭成员信息的。4-8
        int firstSheetbeginRow = 0;
        int firstSheetendRow = 0;
        int firstSheetfirstRow = 0;
        int firstSheetlastRow = 0;
        int firstSheetfirstColumn = 0;
        int firstSheetlastColumn = 0;
        int num = 0;
        int firstSheetMergeCount = firstSheet.getNumMergedRegions();
        //人的集合
        PeopleList pl = new PeopleList();
        for (int i = 0; i < firstSheetMergeCount; i++) {
            CellRangeAddress range = firstSheet.getMergedRegion(i);
            firstSheetfirstColumn = range.getFirstColumn();
            firstSheetlastColumn = range.getLastColumn();
            firstSheetfirstRow = range.getFirstRow();
            firstSheetlastRow = range.getLastRow();
            if( firstSheetfirstRow == 4 & firstSheetfirstColumn == 0){
                System.out.println("第一列："+firstSheetfirstColumn+"；最后一列："+firstSheetlastColumn+"；第一行："+firstSheetfirstRow+"；最后一行："+firstSheetlastRow);
                firstSheetbeginRow = firstSheetfirstRow;
                firstSheetendRow = firstSheetlastRow;
                break;
            }
        }
        //遍历行
        for( int j = firstSheetbeginRow+1 ; j <=  firstSheetendRow;j++){
            Row row = firstSheet.getRow(j);
            if( isRowEmpty(row) ){
                break;
            }
            Cell cell =row.getCell(1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if( ("".equals(cell.getStringCellValue())) ){
                return "姓名不可以为空！";
            }
            //第一个单元格不是空的在去取这一行的数据。
            if( !("".equals(cell.getStringCellValue())) ){
                num++;
                //取出剩下的东西
                //姓名
                Cell cell1 = row.getCell(1);
                cell1.setCellType(Cell.CELL_TYPE_STRING);
                String name = cell1.getStringCellValue();
                //身份证号
                Cell cell2 = row.getCell(2);
                cell2.setCellType(Cell.CELL_TYPE_STRING);
                String pid = cell2.getStringCellValue();
                if( "".equals(pid) ){
                    return "身份证号不可以为空！";
                }
                //性别
                Cell cell4 = row.getCell(4);
                cell4.setCellType(Cell.CELL_TYPE_STRING);
                String gender = cell4.getStringCellValue();
                //民族
                Cell cell5 = row.getCell(5);
                cell5.setCellType(Cell.CELL_TYPE_STRING);
                String race = cell5.getStringCellValue();
                //与户主关系
                Cell cell6 = row.getCell(6);
                cell6.setCellType(Cell.CELL_TYPE_STRING);
                String relation = cell6.getStringCellValue();
                //文化程度
                Cell cell7 = row.getCell(7);
                cell7.setCellType(Cell.CELL_TYPE_STRING);
                String education = cell7.getStringCellValue();
                //现从事职业
                Cell cell8 = row.getCell(8);
                cell8.setCellType(Cell.CELL_TYPE_STRING);
                String profession = cell8.getStringCellValue();

                //赋值
                People peo = new People();
                peo.setFid(fid);
                peo.setReservoir(reservoir);
                peo.setLocation(location);
                peo.setName(name);
                if( masterName.equals(name) ){
                    peo.setMaster(1);
                }else{
                    peo.setMaster(0);
                }
                peo.setPid(pid);
                peo.setGender(gender);
                peo.setRace(race);
                peo.setRelation(relation);
                peo.setEducation(education);
                peo.setProfession(profession);
                //将用户都放到集合中去，户人口和移民人口在外边处理
                pl.getPeopleList().add(peo);
            }
        }
        //取出来调查者和被调查者
        Row interview = firstSheet.getRow(firstSheetendRow + 42);
        //调查人
        Cell cellInterviewer = interview.getCell(4);
        if(cellInterviewer!=null){
            cellInterviewer.setCellType(Cell.CELL_TYPE_STRING);
        }
        String interviewer = cellInterviewer.getStringCellValue();
        //调查人
        Cell cellInterviewee = interview.getCell(1);
        if(cellInterviewee!=null){
            cellInterviewee.setCellType(Cell.CELL_TYPE_STRING);
        }
        String interviewee = cellInterviewee.getStringCellValue();


        //在这里给集合中的每个用户添加属性
        for( People p: pl.getPeopleList()){
            p.setHome_size(num);
            p.setImm_num(num);
            p.setInterviewee(interviewee);
            p.setInterviewer(interviewer);
        }
        //调用sql将用户的存储到数据库中去
       /* DBUtils.batchInsert(pl.getPeopleList());*/



        //开始取搬迁信息
        //迁出行
        Row rowLeave = firstSheet.getRow(firstSheetendRow + 2);
        //州市
        Cell cellLeave2 = rowLeave.getCell(2);
        if( cellLeave2 != null ){
            cellLeave2.setCellType(Cell.CELL_TYPE_STRING);
        }
        String from_city = cellLeave2.getStringCellValue();
        System.out.println(from_city);
        //区县
        Cell cellLeave3 = rowLeave.getCell(3);
        if( cellLeave3 != null ){
            cellLeave3.setCellType(Cell.CELL_TYPE_STRING);
        }
        String from_district = cellLeave3.getStringCellValue();
        System.out.println(from_district);
        //乡镇
        Cell cellLeave4 = rowLeave.getCell(4);
        if( cellLeave4 != null ){
            cellLeave4.setCellType(Cell.CELL_TYPE_STRING);
        }
        String from_town = cellLeave4.getStringCellValue();
        System.out.println(from_town);
        //村
        Cell cellLeave5 = rowLeave.getCell(5);
        if( cellLeave5 != null ){
            cellLeave5.setCellType(Cell.CELL_TYPE_STRING);
        }
        String from_village = cellLeave5.getStringCellValue();
        System.out.println(from_village);
        //组
        Cell cellLeave6 = rowLeave.getCell(6);
        if( cellLeave6 != null ){
            cellLeave6.setCellType(Cell.CELL_TYPE_STRING);
        }
        String from_group = cellLeave6.getStringCellValue();
        System.out.println(from_group);
        //迁出备注
        Cell cellLeave7 = rowLeave.getCell(7);
        if( cellLeave7 != null ){
            cellLeave7.setCellType(Cell.CELL_TYPE_STRING);
        }
        String from_remark = cellLeave7.getStringCellValue();
        System.out.println(from_remark);
        //迁入行
        Row rowTo= firstSheet.getRow(firstSheetendRow + 3);
        //州市
        Cell cellTo2 = rowTo.getCell(2);
        if( cellTo2 != null ){
            cellTo2.setCellType(Cell.CELL_TYPE_STRING);
        }
        String to_city = cellTo2.getStringCellValue();
        System.out.println(to_city);
        //区县
        Cell cellTo3 = rowTo.getCell(3);
        if( cellTo3 != null ){
            cellTo3.setCellType(Cell.CELL_TYPE_STRING);
        }
        String to_district = cellTo3.getStringCellValue();
        System.out.println(to_district);
        //乡镇
        Cell cellTo4 = rowTo.getCell(4);
        if( cellTo4 != null ){
            cellTo4.setCellType(Cell.CELL_TYPE_STRING);
        }
        String to_town = cellTo4.getStringCellValue();
        System.out.println(to_town);
        //村
        Cell cellTo5 = rowTo.getCell(5);
        if( cellTo5 != null ){
            cellTo5.setCellType(Cell.CELL_TYPE_STRING);
        }
        String to_village = cellTo5.getStringCellValue();
        System.out.println(to_village);
        //组
        Cell cellTo6 = rowTo.getCell(6);
        if( cellTo6 != null ){
            cellTo6.setCellType(Cell.CELL_TYPE_STRING);
        }
        String to_group = cellTo6.getStringCellValue();
        System.out.println(to_group);
        //迁入备注
        Cell cellTo7 = rowTo.getCell(7);
        if( cellTo7 != null ){
            cellTo7.setCellType(Cell.CELL_TYPE_STRING);
        }
        String to_remark = cellTo7.getStringCellValue();
        System.out.println(to_remark);

        //new一个对象出来，将这些数据注入
        Move move = new Move();
        move.setFid(fid);
        move.setFrom_city(from_city);
        move.setFrom_district(from_district);
        move.setFrom_town(from_town);
        move.setFrom_village(from_village);
        move.setFrom_group(from_group);
        move.setFrom_remark(from_remark);
        move.setTo_city(to_city);
        move.setTo_district(to_district);
        move.setTo_town(to_town);
        move.setTo_village(to_village);
        move.setTo_group(to_group);
        move.setTo_remark(to_remark);
        //调用jadbcTemplate的插入方法
       /* int i = excelDao.save(move);
        System.out.println("迁入迁出信息录入了："+i+"条；");*/

        //开始取住房情况信息
        Row rowMainHouse = firstSheet.getRow(firstSheetendRow + 5);
        //主房面积
        Cell cellMainHouse2 = rowMainHouse.getCell(2);
        if( cellMainHouse2 != null ){
            cellMainHouse2.setCellType(Cell.CELL_TYPE_STRING);
        }
        String main_size = cellMainHouse2.getStringCellValue();
        System.out.println("主房面积："+main_size);
        //砖混结构
        Cell cellMainHouse3 = rowMainHouse.getCell(3);
        if( cellMainHouse3 != null ){
            cellMainHouse3.setCellType(Cell.CELL_TYPE_STRING);
        }
        String main_structure1 = cellMainHouse3.getStringCellValue();
        System.out.println(main_structure1);
        //砖木结构
        Cell cellMainHouse4 = rowMainHouse.getCell(4);
        if( cellMainHouse4 != null ){
            cellMainHouse4.setCellType(Cell.CELL_TYPE_STRING);
        }
        String main_structure2 = cellMainHouse4.getStringCellValue();
        System.out.println(main_structure2);
        //土木结构
        Cell cellMainHouse5 = rowMainHouse.getCell(5);
        if( cellMainHouse5 != null ){
            cellMainHouse5.setCellType(Cell.CELL_TYPE_STRING);
        }
        String main_structure3 = cellMainHouse5.getStringCellValue();
        System.out.println(main_structure3);
        //木竹结构
        Cell cellMainHouse6 = rowMainHouse.getCell(6);
        if( cellMainHouse6 != null ){
            cellMainHouse6.setCellType(Cell.CELL_TYPE_STRING);
        }
        String main_structure4 = cellMainHouse6.getStringCellValue();
        System.out.println(main_structure4);
        //简易房
        Cell cellMainHouse7 = rowMainHouse.getCell(7);
        if( cellMainHouse7 != null ){
            cellMainHouse7.setCellType(Cell.CELL_TYPE_STRING);
        }
        String main_structure5 = cellMainHouse7.getStringCellValue();
        System.out.println(main_structure5);
        //主房备注
        Cell cellMainHouse8 = rowMainHouse.getCell(8);
        if( cellMainHouse8 != null ){
            cellMainHouse8.setCellType(Cell.CELL_TYPE_STRING);
        }
        String main_remark = cellMainHouse8.getStringCellValue();
        System.out.println(main_remark);
        //附属房行
        Row rowSubHouse = firstSheet.getRow(firstSheetendRow + 6);
        //附属房面积
        Cell cellSubHouse2 = rowSubHouse.getCell(2);
        if( cellSubHouse2 != null ){
            cellSubHouse2.setCellType(Cell.CELL_TYPE_STRING);
        }
        String sub_size = cellSubHouse2.getStringCellValue();
        System.out.println("附属房间面积："+sub_size);
        //砖混结构
        Cell cellSubHouse3 = rowSubHouse.getCell(3);
        if( cellSubHouse3 != null ){
            cellSubHouse3.setCellType(Cell.CELL_TYPE_STRING);
        }
        String sub_structure1 = cellSubHouse3.getStringCellValue();
        System.out.println(sub_structure1);
        //砖木结构
        Cell cellSubHouse4 = rowSubHouse.getCell(4);
        if( cellSubHouse4 != null ){
            cellSubHouse4.setCellType(Cell.CELL_TYPE_STRING);
        }
        String sub_structure2 = cellSubHouse4.getStringCellValue();
        System.out.println(sub_structure2);
        //土木结构
        Cell cellSubHouse5 = rowSubHouse.getCell(5);
        if( cellSubHouse5 != null ){
            cellSubHouse5.setCellType(Cell.CELL_TYPE_STRING);
        }
        String sub_structure3 = cellSubHouse5.getStringCellValue();
        System.out.println(sub_structure3);
        //木竹结构
        Cell cellSubHouse6 = rowSubHouse.getCell(6);
        if( cellSubHouse6 != null ){
            cellSubHouse6.setCellType(Cell.CELL_TYPE_STRING);
        }
        String sub_structure4 = cellSubHouse6.getStringCellValue();
        System.out.println(sub_structure4);
        //简易房
        Cell cellSubHouse7 = rowSubHouse.getCell(7);
        if( cellSubHouse7 != null ){
            cellSubHouse7.setCellType(Cell.CELL_TYPE_STRING);
        }
        String sub_structure5 = cellSubHouse7.getStringCellValue();
        System.out.println(sub_structure5);
        //简易房
        Cell cellSubHouse8 = rowSubHouse.getCell(8);
        if( cellSubHouse8 != null ){
            cellSubHouse8.setCellType(Cell.CELL_TYPE_STRING);
        }
        String sub_remark = cellSubHouse8.getStringCellValue();
        System.out.println(sub_remark);
        //new一个对象出来，将这些数据注入
        House house = new House();
        house.setFid(fid);
        house.setMain_size(Float.parseFloat(main_size));
        house.setMain_structure1(main_structure1);
        house.setMain_structure2(main_structure2);
        house.setMain_structure3(main_structure3);
        house.setMain_structure4(main_structure4);
        house.setMain_structure5(main_structure5);
        house.setMain_remark(main_remark);
        house.setSub_size(Float.parseFloat(sub_size));
        house.setSub_structure1(sub_structure1);
        house.setSub_structure2(sub_structure2);
        house.setSub_structure3(sub_structure3);
        house.setSub_structure4(sub_structure4);
        house.setSub_structure5(sub_structure5);
        house.setSub_remark(sub_remark);
        //调用jadbcTemplate的插入方法
        int j = excelDao.saveHouse(house);
        System.out.println("房子信息录入了："+j+"条！");


        //取收入的数据









        return "ok";
    }
    /*
     *判断一行是否为空
     */
    public boolean isRowEmpty(Row row) {
        int aa = row.getFirstCellNum();
        int bb = row.getLastCellNum();
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }
}
