package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.dao.ExcelDao;
import com.bhidi.lincang.dao.ExcelMapper;
import com.bhidi.lincang.system.DBUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2017/8/21.
 */
@Service
public class ExcelServiceImp implements ExcelServiceInf{
    @Autowired
    ExcelMapper excelMapper;
    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010,并存储。
     * @throws Exception
     */
    public String readService(File excelFile){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        //取出来文件的名字
        String excelName = excelFile.getName();
        System.out.println("ExcelServiceImp中的excel文件的名字："+excelName);
        //取出来文件的路径
        String serverPath = excelFile.getPath();
        System.out.println("服务器上文件的路径："+serverPath);
        String[] strs = serverPath.split("\\\\");
        //在这里将地址的斜杠改变一下
        serverPath = serverPath.replaceAll("\\\\","/");
        serverPath = serverPath.replaceFirst("/","//");
        System.out.println("要放到方法中去删除的地址："+serverPath);
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
        if(SheetCount == 0){
            return serverPath+"-"+excelName+"文件中无sheet！";
        }
        //在这里先判断是否fid为空，为空的话返回，就不插入了，不为空的话判断数据库中是否有关于这个fid的内容
        for( int s = 0; s< SheetCount; s++ ){
            Sheet sheet = workbook.getSheetAt(s);
            //获取有效行数
            int rowcount = sheet.getLastRowNum();

            if( rowcount == 0 ){
                return serverPath+"-"+excelName+"文件内容为空！";
            }
            //00获取移民编号
           /* Row row0 = sheet.getRow(0);
            Cell cell00 = row0.getCell(0);
            String fid = cell00.getStringCellValue();
            if( "".equals(fid) ){
                return serverPath+"-"+excelName+"文件的fid为："+fid+"其中的"+sheet.getSheetName()+"表编号不可以为空！";
            }
            System.out.println("判断阶段的："+fid);
            String fidResult = excelMapper.queryPeopleByFid(fid);
            if( fidResult != null ){
                return serverPath+"-"+excelName+"文件的fid为："+fid+"，数据库中已经存在你们家的信息，请前往修改页面进行修改。"+"   sheet名字："+sheet.getSheetName();
            }*/
        }
        //在这里遍历sheet的名字，是什么名字的时候，调用哪个方法
        String resultFirst = "";
        String resultSecond = "";
        for( int s = 0; s< SheetCount; s++ ){
            String sss = workbook.getSheetAt(s).getSheetName().trim();
            if( "移民搬迁登记表".equals(workbook.getSheetAt(s).getSheetName().trim() )){
                Sheet sheet = workbook.getSheetAt(s);
                resultFirst = first(sheet);
                if(!"录入成功！".equals(resultFirst)){
                    return excelName + resultFirst;
                }
            }
            if( "库区安置登记表".equals(workbook.getSheetAt(s).getSheetName().trim() )){
                Sheet sheet = workbook.getSheetAt(s);
                resultSecond = second(sheet);
                if(!"录入成功！".equals(resultSecond)){
                    return excelName + resultSecond;
                }
            }
        }
        return "录入成功！";
    }
    /*
     * 录入excel文件第一个sheet的方法
     */
    public String first(Sheet firstSheet){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date =  new Date();
        //00获取移民编号
        /*Row row0 = firstSheet.getRow(0);
        Cell cell00 = row0.getCell(0);
        String fid = cell00.getStringCellValue();*/
        /*if( "".equals(fid) ){
            return "表编号不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
        //根据查询数据库中是否存在此户的数据，如果有，就请他们去修改页面进行修改，否则可以继续
        /*int ifexit = DBUtils.queryFid("select fid from people where fid = "+'"'+fid+'"');
        if( ifexit == 1 ){
            return "数据库中已经存在你们家的信息，请前往修改页面进行修改。";
        }*/
       /* String fidRes = excelDao.queryPeopleByFid(fid);
        if( !"".equals(fidRes) ){
            return "数据库中已经存在你们家的信息，请前往修改页面进行修改。"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
        //取到表格的类型
        Row row0 = firstSheet.getRow(0);
        Cell cell00 = row0.getCell(0);
        String fid = "BQ"+sdf.format(date);
        String table_type = "移民搬迁登记表";
        //12获取所属水库
        Row row1 = firstSheet.getRow(1);
        Cell cell12 = row1.getCell(2);
        String reservoir = "";
        if(cell12!=null){
            cell12.setCellType(Cell.CELL_TYPE_STRING);
            reservoir = cell12.getStringCellValue();
        }

       /* if( "".equals(reservoir) ){
            return "所属水库不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
        /*System.out.println("所属水库："+reservoir);*/
        //14获取安置点
        Cell cell14 = row1.getCell(4);
        String location = "";
        if(cell14!=null){
            cell14.setCellType(Cell.CELL_TYPE_STRING);
            location = cell14.getStringCellValue();
        }

        /*if( "".equals(location) ){
            return "安置点不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
        System.out.println("安置点："+location);
        //16获取户主姓名，以此来将下边的人来进行身份的鉴别，如果这个单元格里边没有值，取出来的是“”；空串。
        Cell cell16 = row1.getCell(6);
        String masterName = "";
        if(cell16!=null){
            cell16.setCellType(Cell.CELL_TYPE_STRING);
            masterName = cell16.getStringCellValue();
        }

        /*if( "".equals(masterName) ){
            return "户主姓名不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
        System.out.println("户主姓名："+masterName);
        //18获取电话号码
        Cell cell18 = row1.getCell(8);
        String phone = "";
        if(cell18!=null){
            cell18.setCellType(Cell.CELL_TYPE_STRING);
            phone = cell18.getStringCellValue();
        }
        System.out.println("电话号码："+masterName);
        //取出银行卡信息
        //22获取开户人姓名
        Row row2 = firstSheet.getRow(2);
        Cell cell22 = row2.getCell(2);
        String account_name = "";
        if(cell22!=null){
            cell22.setCellType(Cell.CELL_TYPE_STRING);
            account_name = cell22.getStringCellValue();
        }
        System.out.println("开户人姓名："+account_name);
        //24获取开户行名称
        Cell cell24 = row2.getCell(4);
        String bank_name = "";
        if(cell24!=null){
            cell24.setCellType(Cell.CELL_TYPE_STRING);
            bank_name = cell24.getStringCellValue();
        }
        System.out.println("开户行名称："+bank_name);
        //26获取银行卡号
        Cell cell26 = row2.getCell(6);
        String account_number ="";
        if(cell26!=null){
            cell26.setCellType(Cell.CELL_TYPE_STRING);
            account_number = cell26.getStringCellValue();
        }
        System.out.println("银行卡号："+account_number);
        Bank bank = new Bank();
        bank.setFid(fid);
        bank.setAccount_name(account_name);
        bank.setBank_name(bank_name);
        bank.setAccount_number(account_number);
        //在这里应该添加事物的，先没有考虑如果插入不成功，会怎么办。
        Integer intResultOfBank = 0;
        if( bank != null ){
            try {
                intResultOfBank =  excelMapper.saveBank(bank);
            } catch (Exception e) {
                intResultOfBank = -1;
            }
        }



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
        List<People> pl = new ArrayList<People>();
        for (int i = 0; i < firstSheetMergeCount; i++) {
            CellRangeAddress range = firstSheet.getMergedRegion(i);
            firstSheetfirstColumn = range.getFirstColumn();
            firstSheetlastColumn = range.getLastColumn();
            firstSheetfirstRow = range.getFirstRow();
            firstSheetlastRow = range.getLastRow();
            if( firstSheetfirstRow == 3 & firstSheetfirstColumn == 0){
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
           /* if( ("".equals(cell.getStringCellValue())) ){
                return "姓名不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
            }*/
            //第一个单元格不是空的在去取这一行的数据。
            if( !("".equals(cell.getStringCellValue())) ){
                num++;
                //取出剩下的东西
                //姓名
                Cell cell1 = row.getCell(1);
                String name = "";
                if( cell1 != null ){
                    cell1.setCellType(Cell.CELL_TYPE_STRING);
                    name = cell1.getStringCellValue();
                }
                //身份证号
                Cell cell2 = row.getCell(2);
                String pid = "";
                if( cell2 != null ){
                    cell2.setCellType(Cell.CELL_TYPE_STRING);
                    pid = cell2.getStringCellValue();
                }

                /*if( "".equals(pid) ){
                    return "身份证号不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
                }*/
                //性别
                Cell cell4 = row.getCell(4);
                String gender = "";
                if( cell4 != null ){
                    cell4.setCellType(Cell.CELL_TYPE_STRING);
                    gender = cell4.getStringCellValue();
                }
                //民族
                Cell cell5 = row.getCell(5);
                String race ="";
                if( cell5 != null ){
                    cell5.setCellType(Cell.CELL_TYPE_STRING);
                    race = cell5.getStringCellValue();
                }
                //与户主关系
                Cell cell6 = row.getCell(6);
                String relation ="";
                if( cell6 != null ){
                    cell6.setCellType(Cell.CELL_TYPE_STRING);
                    relation = cell6.getStringCellValue();
                }
                //文化程度
                Cell cell7 = row.getCell(7);
                String education = "";
                if( cell7 != null ){
                    cell7.setCellType(Cell.CELL_TYPE_STRING);
                    education = cell7.getStringCellValue();
                }
                //现从事职业
                Cell cell8 = row.getCell(8);
                String profession ="";
                if( cell8 != null ){
                    cell8.setCellType(Cell.CELL_TYPE_STRING);
                    profession = cell8.getStringCellValue();
                }
                //赋值
                People peo = new People();
                peo.setFid(fid);
                peo.setTable_type(table_type);
                peo.setReservoir(reservoir);
                peo.setLocation(location);
                peo.setName(name);
                if( masterName.equals(name) ){
                    peo.setMaster(1);
                    peo.setPhone(phone);
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
                pl.add(peo);
            }
        }
        //取出来调查者和被调查者
        Row interview = firstSheet.getRow(firstSheetendRow + 42);
        //调查人
        Cell cellInterviewer = interview.getCell(4);
        String interviewer ="";
        if(cellInterviewer!=null){
            cellInterviewer.setCellType(Cell.CELL_TYPE_STRING);
            interviewer = cellInterviewer.getStringCellValue();
        }
        //被调查人
        Cell cellInterviewee = interview.getCell(1);
        String interviewee ="";
        if(cellInterviewee!=null){
            cellInterviewee.setCellType(Cell.CELL_TYPE_STRING);
            interviewee = cellInterviewee.getStringCellValue();
        }
        //填表时间
        Cell cellCreated_at = interview.getCell(7);
        String created_at ="";
        if(cellCreated_at!=null){
            cellCreated_at.setCellType(Cell.CELL_TYPE_STRING);
            created_at = cellCreated_at.getStringCellValue();
        }
        //取出来其他信息的行
        Row other = firstSheet.getRow(firstSheetendRow + 41);
        //是否建档立卡
        Cell propInterviewer = other.getCell(2);
        String prop ="";
        if(propInterviewer!=null){
            propInterviewer.setCellType(Cell.CELL_TYPE_STRING);
            prop = propInterviewer.getStringCellValue();
        }
        //贫困原因
        Cell poorInterviewer = other.getCell(4);
        String poor_reason ="";
        if(poorInterviewer!=null){
            poorInterviewer.setCellType(Cell.CELL_TYPE_STRING);
            poor_reason = poorInterviewer.getStringCellValue();
        }
        //在这里给集合中的每个用户添加属性
        for( People p: pl ){
            p.setHome_size(num);
            p.setImm_num(num);
            p.setProp("是".equals(prop)?1:0);
            p.setPoor_reason(poor_reason);
            p.setInterviewee(interviewee);
            p.setInterviewer(interviewer);
            p.setCreated_at(created_at);
        }
        //调用Dao层将用户的存储到数据库中去
        Integer intResultOfPeople = 0;
        if( pl.size() > 0 ){
            try {
                intResultOfPeople = excelMapper.batchSavePeople(pl);
            } catch (Exception e) {
                intResultOfPeople = -1;
            }
        }



        //开始取搬迁信息
        //迁出行
        Row rowLeave = firstSheet.getRow(firstSheetendRow + 2);
        //州市
        Cell cellLeave2 = rowLeave.getCell(2);
        String from_city ="";
        if( cellLeave2 != null ){
            cellLeave2.setCellType(Cell.CELL_TYPE_STRING);
            from_city = cellLeave2.getStringCellValue();
        }
        System.out.println(from_city);
        //区县
        Cell cellLeave3 = rowLeave.getCell(3);
        String from_district ="";
        if( cellLeave3 != null ){
            cellLeave3.setCellType(Cell.CELL_TYPE_STRING);
            from_district = cellLeave3.getStringCellValue();
        }
        System.out.println(from_district);
        //乡镇
        Cell cellLeave4 = rowLeave.getCell(4);
        String from_town ="";
        if( cellLeave4 != null ){
            cellLeave4.setCellType(Cell.CELL_TYPE_STRING);
            from_town = cellLeave4.getStringCellValue();
        }
        System.out.println(from_town);
        //村
        Cell cellLeave5 = rowLeave.getCell(5);
        String from_village ="";
        if( cellLeave5 != null ){
            cellLeave5.setCellType(Cell.CELL_TYPE_STRING);
            from_village = cellLeave5.getStringCellValue();
        }
        System.out.println(from_village);
        //组
        Cell cellLeave6 = rowLeave.getCell(6);
        String from_group ="";
        if( cellLeave6 != null ){
            cellLeave6.setCellType(Cell.CELL_TYPE_STRING);
            from_group = cellLeave6.getStringCellValue();
        }
        System.out.println(from_group);
        //迁出备注
        Cell cellLeave7 = rowLeave.getCell(7);
        String from_remark = "";
        if( cellLeave7 != null ){
            cellLeave7.setCellType(Cell.CELL_TYPE_STRING);
            from_remark = cellLeave7.getStringCellValue();
        }
        System.out.println(from_remark);
        //迁入行
        Row rowTo= firstSheet.getRow(firstSheetendRow + 3);
        //州市
        Cell cellTo2 = rowTo.getCell(2);
        String to_city ="";
        if( cellTo2 != null ){
            cellTo2.setCellType(Cell.CELL_TYPE_STRING);
            to_city = cellTo2.getStringCellValue();
        }
        System.out.println(to_city);
        //区县
        Cell cellTo3 = rowTo.getCell(3);
        String to_district ="";
        if( cellTo3 != null ){
            cellTo3.setCellType(Cell.CELL_TYPE_STRING);
            to_district = cellTo3.getStringCellValue();
        }
        System.out.println(to_district);
        //乡镇
        Cell cellTo4 = rowTo.getCell(4);
        String to_town ="";
        if( cellTo4 != null ){
            cellTo4.setCellType(Cell.CELL_TYPE_STRING);
            to_town = cellTo4.getStringCellValue();
        }
        System.out.println(to_town);
        //村
        Cell cellTo5 = rowTo.getCell(5);
        String to_village ="";
        if( cellTo5 != null ){
            cellTo5.setCellType(Cell.CELL_TYPE_STRING);
            to_village = cellTo5.getStringCellValue();
        }
        System.out.println(to_village);
        //组
        Cell cellTo6 = rowTo.getCell(6);
        String to_group = "";
        if( cellTo6 != null ){
            cellTo6.setCellType(Cell.CELL_TYPE_STRING);
            to_group = cellTo6.getStringCellValue();
        }
        System.out.println(to_group);
        //迁入备注
        Cell cellTo7 = rowTo.getCell(7);
        String to_remark ="";
        if( cellTo7 != null ){
            cellTo7.setCellType(Cell.CELL_TYPE_STRING);
            to_remark = cellTo7.getStringCellValue();
        }
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
        //调用Dao层的方法插入数据库
        Integer intResultOfMove = 0;
        if( move != null ){
            try {
                intResultOfMove = excelMapper.saveMove(move);
            } catch (Exception e) {
                intResultOfMove = -1;
            }
        }


        //开始取住房情况信息
        Row rowMainHouse = firstSheet.getRow(firstSheetendRow + 5);
        //主房面积
        Cell cellMainHouse2 = rowMainHouse.getCell(2);
        String main_size ="";
        if( cellMainHouse2 != null ){
            cellMainHouse2.setCellType(Cell.CELL_TYPE_STRING);
            main_size = cellMainHouse2.getStringCellValue();
        }
        System.out.println("主房面积："+main_size);
        //砖混结构
        Cell cellMainHouse3 = rowMainHouse.getCell(3);
        String main_structure1 ="";
        if( cellMainHouse3 != null ){
            cellMainHouse3.setCellType(Cell.CELL_TYPE_STRING);
            main_structure1 = cellMainHouse3.getStringCellValue();
        }
        System.out.println(main_structure1);
        //砖木结构
        Cell cellMainHouse4 = rowMainHouse.getCell(4);
        String main_structure2 ="";
        if( cellMainHouse4 != null ){
            cellMainHouse4.setCellType(Cell.CELL_TYPE_STRING);
            main_structure2 = cellMainHouse4.getStringCellValue();
        }
        System.out.println(main_structure2);
        //土木结构
        Cell cellMainHouse5 = rowMainHouse.getCell(5);
        String main_structure3 ="";
        if( cellMainHouse5 != null ){
            cellMainHouse5.setCellType(Cell.CELL_TYPE_STRING);
            main_structure3 = cellMainHouse5.getStringCellValue();
        }
        System.out.println(main_structure3);
        //木竹结构
        Cell cellMainHouse6 = rowMainHouse.getCell(6);
        String main_structure4 ="";
        if( cellMainHouse6 != null ){
            cellMainHouse6.setCellType(Cell.CELL_TYPE_STRING);
            main_structure4 = cellMainHouse6.getStringCellValue();
        }
        System.out.println(main_structure4);
        //简易房
        Cell cellMainHouse7 = rowMainHouse.getCell(7);
        String main_structure5 ="";
        if( cellMainHouse7 != null ){
            cellMainHouse7.setCellType(Cell.CELL_TYPE_STRING);
            main_structure5 = cellMainHouse7.getStringCellValue();
        }
        System.out.println(main_structure5);
        //主房备注
        Cell cellMainHouse8 = rowMainHouse.getCell(8);
        String main_remark = "";
        if( cellMainHouse8 != null ){
            cellMainHouse8.setCellType(Cell.CELL_TYPE_STRING);
            main_remark = cellMainHouse8.getStringCellValue();
        }
        System.out.println(main_remark);
        //附属房行
        Row rowSubHouse = firstSheet.getRow(firstSheetendRow + 6);
        //附属房面积
        Cell cellSubHouse2 = rowSubHouse.getCell(2);
        String sub_size ="";
        if( cellSubHouse2 != null ){
            cellSubHouse2.setCellType(Cell.CELL_TYPE_STRING);
            sub_size = cellSubHouse2.getStringCellValue();
        }
        System.out.println("附属房间面积："+sub_size);
        //砖混结构
        Cell cellSubHouse3 = rowSubHouse.getCell(3);
        String sub_structure1 = "";
        if( cellSubHouse3 != null ){
            cellSubHouse3.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure1 = cellSubHouse3.getStringCellValue();
        }
        System.out.println(sub_structure1);
        //砖木结构
        Cell cellSubHouse4 = rowSubHouse.getCell(4);
        String sub_structure2 ="";
        if( cellSubHouse4 != null ){
            cellSubHouse4.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure2 = cellSubHouse4.getStringCellValue();
        }
        System.out.println(sub_structure2);
        //土木结构
        Cell cellSubHouse5 = rowSubHouse.getCell(5);
        String sub_structure3 ="";
        if( cellSubHouse5 != null ){
            cellSubHouse5.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure3 = cellSubHouse5.getStringCellValue();
        }
        System.out.println(sub_structure3);
        //木竹结构
        Cell cellSubHouse6 = rowSubHouse.getCell(6);
        String sub_structure4 ="";
        if( cellSubHouse6 != null ){
            cellSubHouse6.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure4 = cellSubHouse6.getStringCellValue();
        }
        System.out.println(sub_structure4);
        //简易房
        Cell cellSubHouse7 = rowSubHouse.getCell(7);
        String sub_structure5 ="";
        if( cellSubHouse7 != null ){
            cellSubHouse7.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure5 = cellSubHouse7.getStringCellValue();
        }
        System.out.println(sub_structure5);
        //简易房
        Cell cellSubHouse8 = rowSubHouse.getCell(8);
        String sub_remark = "";
        if( cellSubHouse8 != null ){
            cellSubHouse8.setCellType(Cell.CELL_TYPE_STRING);
            sub_remark = cellSubHouse8.getStringCellValue();
        }
        System.out.println(sub_remark);
        //new一个对象出来，将这些数据注入
        House house = new House();
        house.setFid(fid);
        house.setMain_size("".equals(main_size)? "0.0":main_size);
        house.setMain_structure1(main_structure1);
        house.setMain_structure2(main_structure2);
        house.setMain_structure3(main_structure3);
        house.setMain_structure4(main_structure4);
        house.setMain_structure5(main_structure5);
        house.setMain_remark(main_remark);
        house.setSub_size("".equals(sub_size)? "0.0":sub_size);
        house.setSub_structure1(sub_structure1);
        house.setSub_structure2(sub_structure2);
        house.setSub_structure3(sub_structure3);
        house.setSub_structure4(sub_structure4);
        house.setSub_structure5(sub_structure5);
        house.setSub_remark(sub_remark);
        //调用Dao的方法将房屋信息插入数据库
        Integer intResultOfHouse = 0;
        if( house != null ){
            try {
                intResultOfHouse =  excelMapper.saveHouse(house);
            } catch (Exception e) {
                intResultOfHouse = -1;
            }
        }



        //取收入的数据
        List<Income> listIncome = new ArrayList<Income>();
        //收入分类之养殖业收入，一共8行内容，
        for(int incomenum = (firstSheetendRow + 8);incomenum< (firstSheetendRow + 16);incomenum++ ){
            Row rowIncome = firstSheet.getRow(incomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowIncomeType = firstSheet.getRow(firstSheetendRow + 8);
            Cell cellIncomeType1 = rowIncomeType.getCell(1);
            String income_source = "";
            if( cellIncomeType1 != null ){
                cellIncomeType1.setCellType(Cell.CELL_TYPE_STRING);
                income_source = cellIncomeType1.getStringCellValue();
            }
            System.out.println("收入分类："+income_source);
            //内容为猪
            Cell cellIncome2 = rowIncome.getCell(2);
            String income_cate ="";
            if( cellIncome2 != null ){
                cellIncome2.setCellType(Cell.CELL_TYPE_STRING);
                income_cate = cellIncome2.getStringCellValue();
            }
            System.out.println("收入内容："+income_cate);
            //猪的数量
            Cell cellIncome3 = rowIncome.getCell(3);
            String income_quantity = "";
            if( cellIncome3 != null ){
                cellIncome3.setCellType(Cell.CELL_TYPE_STRING);
                income_quantity = cellIncome3.getStringCellValue();
            }
            System.out.println("数量："+income_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(income_quantity)){
                break;
            }
            //猪的单价
            Cell cellIncome4 = rowIncome.getCell(4);
            String income_unit ="";
            if( cellIncome4 != null ){
                cellIncome4.setCellType(Cell.CELL_TYPE_STRING);
                income_unit = cellIncome4.getStringCellValue();
            }
            System.out.println("收入单价："+income_unit);
            //猪的小计
            Cell cellIncome5 = rowIncome.getCell(5);
            String income_sum = "";
            if( cellIncome5 != null ){
                cellIncome5.setCellType(Cell.CELL_TYPE_STRING);
                income_sum = cellIncome5.getStringCellValue();
            }
            System.out.println("收入小计："+income_sum);
            //备注
            Cell cellIncome6 = rowIncome.getCell(6);
            String remark = "";
            if( cellIncome6 != null ){
                cellIncome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellIncome6.getStringCellValue();
            }
            System.out.println("收入备注："+remark);
            //new一个对象出来，将数据存储进去
            Income income = new Income();
            income.setFid(fid);
            income.setIncome_source(income_source);
            income.setIncome_cate(income_cate);
            income.setIncome_quantity("".equals(income_quantity)?0:Integer.parseInt(income_quantity));
            income.setIncome_unit("".equals(income_unit)?0.0f:Float.parseFloat(income_unit));
            income.setIncome_sum("".equals(income_sum)?0.0f:Float.parseFloat(income_sum));
            income.setRemark(remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(income);*/
            listIncome.add(income);
        }
        System.out.println("集合添加了养殖业之后的长度："+listIncome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //种植业收入数据
        for(int incomenum = (firstSheetendRow + 16);incomenum< (firstSheetendRow + 20);incomenum++ ){
            Row rowIncome = firstSheet.getRow(incomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowIncomeType = firstSheet.getRow(firstSheetendRow + 16);
            Cell cellIncomeType1 = rowIncomeType.getCell(1);
            String income_source = "";
            if( cellIncomeType1 != null ){
                cellIncomeType1.setCellType(Cell.CELL_TYPE_STRING);
                income_source = cellIncomeType1.getStringCellValue();
            }
            System.out.println("收入分类："+income_source);
            //内容为猪
            Cell cellIncome2 = rowIncome.getCell(2);
            String income_cate = "";
            if( cellIncome2 != null ){
                cellIncome2.setCellType(Cell.CELL_TYPE_STRING);
                income_cate = cellIncome2.getStringCellValue();
            }
            System.out.println("收入内容："+income_cate);
            //猪的数量
            Cell cellIncome3 = rowIncome.getCell(3);
            String income_quantity = "";
            if( cellIncome3 != null ){
                cellIncome3.setCellType(Cell.CELL_TYPE_STRING);
                income_quantity = cellIncome3.getStringCellValue();
            }
            System.out.println("数量："+income_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(income_quantity)){
                break;
            }
            //猪的单价
            Cell cellIncome4 = rowIncome.getCell(4);
            String income_unit ="";
            if( cellIncome4 != null ){
                cellIncome4.setCellType(Cell.CELL_TYPE_STRING);
                income_unit = cellIncome4.getStringCellValue();
            }
            System.out.println("收入单价："+income_unit);
            //猪的小计
            Cell cellIncome5 = rowIncome.getCell(5);
            String income_sum = "";
            if( cellIncome5 != null ){
                cellIncome5.setCellType(Cell.CELL_TYPE_STRING);
                income_sum = cellIncome5.getStringCellValue();
            }
            System.out.println("收入小计："+income_sum);
            //备注
            Cell cellIncome6 = rowIncome.getCell(6);
            String remark = "";
            if( cellIncome6 != null ){
                cellIncome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellIncome6.getStringCellValue();
            }
            System.out.println("收入备注："+remark);
            //new一个对象出来，将数据存储进去
            Income income = new Income();
            income.setFid(fid);
            income.setIncome_source(income_source);
            income.setIncome_cate(income_cate);
            income.setIncome_quantity("".equals(income_quantity)?0:Integer.parseInt(income_quantity));
            income.setIncome_unit("".equals(income_unit)?0.0f:Float.parseFloat(income_unit));
            income.setIncome_sum("".equals(income_sum)?0.0f:Float.parseFloat(income_sum));
            income.setRemark(remark);
           /* SqlParameterSource paramSource = new BeanPropertySqlParameterSource(income);*/
            listIncome.add(income);
        }
        System.out.println("集合添加了种殖业之后的长度："+listIncome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //其他收入数据
        for(int incomenum = (firstSheetendRow + 20);incomenum< (firstSheetendRow + 22);incomenum++ ){
            Row rowIncome = firstSheet.getRow(incomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowIncomeType = firstSheet.getRow(firstSheetendRow + 20);
            Cell cellIncomeType1 = rowIncomeType.getCell(1);
            String income_source ="";
            if( cellIncomeType1 != null ){
                cellIncomeType1.setCellType(Cell.CELL_TYPE_STRING);
                income_source = cellIncomeType1.getStringCellValue();
            }
            System.out.println("收入分类："+income_source);
            //内容为猪
            Cell cellIncome2 = rowIncome.getCell(2);
            String income_cate ="";
            if( cellIncome2 != null ){
                cellIncome2.setCellType(Cell.CELL_TYPE_STRING);
                income_cate = cellIncome2.getStringCellValue();
            }
            System.out.println("收入内容："+income_cate);
            //猪的数量
            Cell cellIncome3 = rowIncome.getCell(3);
            String income_quantity = "";
            if( cellIncome3 != null ){
                cellIncome3.setCellType(Cell.CELL_TYPE_STRING);
                income_quantity = cellIncome3.getStringCellValue();
            }
            System.out.println("数量："+income_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(income_quantity)){
                break;
            }
            //猪的单价
            Cell cellIncome4 = rowIncome.getCell(4);
            String income_unit ="";
            if( cellIncome4 != null ){
                cellIncome4.setCellType(Cell.CELL_TYPE_STRING);
                income_unit = cellIncome4.getStringCellValue();
            }
            System.out.println("收入单价："+income_unit);
            //猪的小计
            Cell cellIncome5 = rowIncome.getCell(5);
            String income_sum = "";
            if( cellIncome5 != null ){
                cellIncome5.setCellType(Cell.CELL_TYPE_STRING);
                income_sum = cellIncome5.getStringCellValue();
            }
            System.out.println("收入小计："+income_sum);
            //备注
            Cell cellIncome6 = rowIncome.getCell(6);
            String remark = "";
            if( cellIncome6 != null ){
                cellIncome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellIncome6.getStringCellValue();
            }
            System.out.println("收入备注："+remark);
            //new一个对象出来，将数据存储进去
            Income income = new Income();
            income.setFid(fid);
            income.setIncome_source(income_source);
            income.setIncome_cate(income_cate);
            income.setIncome_quantity("".equals(income_quantity)?0:Integer.parseInt(income_quantity));
            income.setIncome_unit("".equals(income_unit)?0.0f:Float.parseFloat(income_unit));
            income.setIncome_sum("".equals(income_sum)?0.0f:Float.parseFloat(income_sum));
            income.setRemark(remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(income);*/
            listIncome.add(income);
        }
        System.out.println("集合添加了其他之后的长度："+listIncome.size());
        //调用Dao层的插入方法存储收入数据
        Integer intResultOfIncome = 0;
        if( listIncome.size() > 0 ){
            try {
                intResultOfIncome = excelMapper.batchSaveIncome(listIncome);
            } catch (Exception e) {
                intResultOfIncome = -1;
            }
        }



        //支出的数据的取
        List<Outcome> listOutcome = new ArrayList<Outcome>();
        //支出分类之种殖业支出，
        for(int outcomenum = (firstSheetendRow + 23);outcomenum< (firstSheetendRow + 29);outcomenum++ ){
            Row rowOutcome = firstSheet.getRow(outcomenum);
            //支出类别为种殖业的,类别要单独处理
            Row rowOutcomeType = firstSheet.getRow(firstSheetendRow + 23);
            Cell cellOutcomeType1 = rowOutcomeType.getCell(1);
            String outcome_source = "";
            if( cellOutcomeType1 != null ){
                cellOutcomeType1.setCellType(Cell.CELL_TYPE_STRING);
                outcome_source = cellOutcomeType1.getStringCellValue();
            }
            System.out.println("支出分类："+outcome_source);
            //内容
            Cell cellOutcome2 = rowOutcome.getCell(2);
            String outcome_cate ="";
            if( cellOutcome2 != null ){
                cellOutcome2.setCellType(Cell.CELL_TYPE_STRING);
                outcome_cate = cellOutcome2.getStringCellValue();
            }
            System.out.println("支出内容："+outcome_cate);
            //数量
            Cell cellOutcome3 = rowOutcome.getCell(3);
            String outcome_quantity ="";
            if( cellOutcome3 != null ){
                cellOutcome3.setCellType(Cell.CELL_TYPE_STRING);
                outcome_quantity = cellOutcome3.getStringCellValue();
            }
            System.out.println("数量："+outcome_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(outcome_quantity)){
                break;
            }
            //单价
            Cell cellOutcome4 = rowOutcome.getCell(4);
            String outcome_unit ="";
            if( cellOutcome4 != null ){
                cellOutcome4.setCellType(Cell.CELL_TYPE_STRING);
                outcome_unit = cellOutcome4.getStringCellValue();
            }
            System.out.println("支出单价："+outcome_unit);
            //小计
            Cell cellOutcome5 = rowOutcome.getCell(5);
            String outcome_sum ="";
            if( cellOutcome5 != null ){
                cellOutcome5.setCellType(Cell.CELL_TYPE_STRING);
                outcome_sum = cellOutcome5.getStringCellValue();
            }
            System.out.println("支出小计："+outcome_sum);
            //备注
            Cell cellOutcome6 = rowOutcome.getCell(6);
            String remark = "";
            if( cellOutcome6 != null ){
                cellOutcome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellOutcome6.getStringCellValue();
            }
            System.out.println("支出备注："+remark);
            //new一个支出对象出来，将数据存储进去
            Outcome outcome = new Outcome();
            outcome.setFid(fid);
            outcome.setOutcome_source(outcome_source);
            outcome.setOutcome_cate(outcome_cate);
            outcome.setOutcome_quantity("".equals(outcome_quantity)?0:Integer.parseInt(outcome_quantity));
            outcome.setOutcome_unit("".equals(outcome_unit)?0.0f:Float.parseFloat(outcome_unit));
            outcome.setOutcome_sum("".equals(outcome_sum)?0.0f:Float.parseFloat(outcome_sum));
            outcome.setRemark(remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(outcome);*/
            listOutcome.add(outcome);
        }
        System.out.println("集合添加了种殖业支出之后的长度："+listOutcome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //养植业支出数据
        for(int outcomenum = (firstSheetendRow + 29);outcomenum< (firstSheetendRow + 33);outcomenum++ ){
            Row rowOutcome = firstSheet.getRow(outcomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowOutcomeType = firstSheet.getRow(firstSheetendRow + 29);
            Cell cellOutcomeType1 = rowOutcomeType.getCell(1);
            String outcome_source = "";
            if( cellOutcomeType1 != null ){
                cellOutcomeType1.setCellType(Cell.CELL_TYPE_STRING);
                outcome_source = cellOutcomeType1.getStringCellValue();
            }
            System.out.println("支出分类："+outcome_source);
            //内容
            Cell cellOutcome2 = rowOutcome.getCell(2);
            String outcome_cate ="";
            if( cellOutcome2 != null ){
                cellOutcome2.setCellType(Cell.CELL_TYPE_STRING);
                outcome_cate = cellOutcome2.getStringCellValue();
            }
            System.out.println("支出内容："+outcome_cate);
            //数量
            Cell cellOutcome3 = rowOutcome.getCell(3);
            String outcome_quantity ="";
            if( cellOutcome3 != null ){
                cellOutcome3.setCellType(Cell.CELL_TYPE_STRING);
                outcome_quantity = cellOutcome3.getStringCellValue();
            }
            System.out.println("数量："+outcome_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(outcome_quantity)){
                break;
            }
            //单价
            Cell cellOutcome4 = rowOutcome.getCell(4);
            String outcome_unit = "";
            if( cellOutcome4 != null ){
                cellOutcome4.setCellType(Cell.CELL_TYPE_STRING);
                outcome_unit = cellOutcome4.getStringCellValue();
            }
            System.out.println("支出单价："+outcome_unit);
            //小计
            Cell cellOutcome5 = rowOutcome.getCell(5);
            String outcome_sum = "";
            if( cellOutcome5 != null ){
                cellOutcome5.setCellType(Cell.CELL_TYPE_STRING);
                outcome_sum = cellOutcome5.getStringCellValue();
            }
            System.out.println("支出小计："+outcome_sum);
            //备注
            Cell cellOutcome6 = rowOutcome.getCell(6);
            String remark ="";
            if( cellOutcome6 != null ){
                cellOutcome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellOutcome6.getStringCellValue();
            }
            System.out.println("支出备注："+remark);
            //new一个对象出来，将数据存储进去
            Outcome outcome = new Outcome();
            outcome.setFid(fid);
            outcome.setOutcome_source(outcome_source);
            outcome.setOutcome_cate(outcome_cate);
            outcome.setOutcome_quantity("".equals(outcome_quantity)?0:Integer.parseInt(outcome_quantity));
            outcome.setOutcome_unit("".equals(outcome_unit)?0.0f:Float.parseFloat(outcome_unit));
            outcome.setOutcome_sum("".equals(outcome_sum)?0.0f:Float.parseFloat(outcome_sum));
            outcome.setRemark(remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(outcome);*/
            listOutcome.add(outcome);
        }
        System.out.println("集合添加了养殖业支出之后的长度："+listOutcome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //生活支出数据
        for(int outcomenum = (firstSheetendRow + 33);outcomenum< (firstSheetendRow + 41);outcomenum++ ){
            Row rowOutcome = firstSheet.getRow(outcomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowOutcomeType = firstSheet.getRow(firstSheetendRow + 33);
            Cell cellOutcomeType1 = rowOutcomeType.getCell(1);
            String outcome_source ="";
            if( cellOutcomeType1 != null ){
                cellOutcomeType1.setCellType(Cell.CELL_TYPE_STRING);
                outcome_source = cellOutcomeType1.getStringCellValue();
            }
            System.out.println("支出分类："+outcome_source);
            //内容
            Cell cellOutcome2 = rowOutcome.getCell(2);
            String outcome_cate ="";
            if( cellOutcome2 != null ){
                cellOutcome2.setCellType(Cell.CELL_TYPE_STRING);
                outcome_cate = cellOutcome2.getStringCellValue();
            }
            System.out.println("支出内容："+outcome_cate);
            //数量
            Cell cellOutcome3 = rowOutcome.getCell(3);
            String outcome_quantity ="";
            if( cellOutcome3 != null ){
                cellOutcome3.setCellType(Cell.CELL_TYPE_STRING);
                outcome_quantity = cellOutcome3.getStringCellValue();
            }
            System.out.println("数量："+outcome_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(outcome_quantity)){
                break;
            }
            //单价
            Cell cellOutcome4 = rowOutcome.getCell(4);
            String outcome_unit = "";
            if( cellOutcome4 != null ){
                cellOutcome4.setCellType(Cell.CELL_TYPE_STRING);
                outcome_unit = cellOutcome4.getStringCellValue();
            }
            System.out.println("支出单价："+outcome_unit);
            //小计
            Cell cellOutcome5 = rowOutcome.getCell(5);
            String outcome_sum ="";
            if( cellOutcome5 != null ){
                cellOutcome5.setCellType(Cell.CELL_TYPE_STRING);
                outcome_sum = cellOutcome5.getStringCellValue();
            }
            System.out.println("支出小计："+outcome_sum);
            //备注
            Cell cellOutcome6 = rowOutcome.getCell(6);
            String remark ="";
            if( cellOutcome6 != null ){
                cellOutcome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellOutcome6.getStringCellValue();
            }
            System.out.println("支出备注："+remark);
            //new一个对象出来，将数据存储进去
            Outcome outcome = new Outcome();
            outcome.setFid(fid);
            outcome.setOutcome_source(outcome_source);
            outcome.setOutcome_cate(outcome_cate);
            outcome.setOutcome_quantity("".equals(outcome_quantity)?0:Integer.parseInt(outcome_quantity));
            outcome.setOutcome_unit("".equals(outcome_unit)?0.0f:Float.parseFloat(outcome_unit));
            outcome.setOutcome_sum("".equals(outcome_sum)?0.0f:Float.parseFloat(outcome_sum));
            outcome.setRemark(remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(outcome);*/
            listOutcome.add(outcome);
        }
        System.out.println("集合添加了生活支出之后的长度："+listOutcome.size());
        //调用Dao层的方法存储支出数据
        Integer intResultOfOutcome = 0;
        if( listOutcome.size() > 0 ){
            intResultOfOutcome = excelMapper.batchSaveOutcome(listOutcome);
        }
        //intResultOfPeople,intResultOfMove,intResultOfHouse,intResultOfIncome,intResultOfOutcome
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(intResultOfPeople);
        intList.add(intResultOfMove);
        intList.add(intResultOfHouse);
        intList.add(intResultOfIncome);
        intList.add(intResultOfOutcome);
        intList.add(intResultOfBank);
        String strResult = "";
        for( int r = 0;r < intList.size();r++){
            if( intList.get(r) == -1 ){
                strResult = "录入失败！";
            } else {
                strResult = "录入成功！";
            }
        }
        /*Map mapResult = new HashMap();
        mapResult.put("result",strResult);*/
        return strResult;
    }
    /*
     * 录入excel文件第二个sheet的方法
     */
    public String second(Sheet firstSheet){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date =  new Date();
        //取到表格的类型
        Row row0 = firstSheet.getRow(0);
        Cell cell00 = row0.getCell(0);
        String fid = "KQ"+sdf.format(date);
        String table_type = "库区安置登记表";
        //00获取移民编号
        /*Row row0 = firstSheet.getRow(0);
        Cell cell00 = row0.getCell(0);
        String fid = cell00.getStringCellValue();*/
        /*if( "".equals(fid) ){
            return "表编号不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
       /* System.out.println(fid);*/
        //根据查询数据库中是否存在此户的数据，如果有，就请他们去修改页面进行修改，否则可以继续
       /* int ifexit = DBUtils.queryFid("select fid from people where fid = "+'"'+fid+'"');
        if( ifexit == 1 ){
            return "数据库中已经存在你们家的信息，请前往修改页面进行修改。";
        }*/
       /*  String fidRes = excelDao.queryPeopleByFid(fid);
       if( !"".equals(fidRes) ){
            return "数据库中已经存在你们家的信息，请前往修改页面进行修改。"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
        //22获取所属水库
        Row row1 = firstSheet.getRow(1);
        Cell cell12 = row1.getCell(2);
        String reservoir ="";
        if(cell12!=null){
            cell12.setCellType(Cell.CELL_TYPE_STRING);
            reservoir = cell12.getStringCellValue();
        }
        System.out.println("所属水库："+reservoir);
        /*if( "".equals(reservoir) ){
            return "所属水库不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/

       /* //24获取安置点
        Cell cell24 = row2.getCell(4);
        if(cell24!=null){
            cell24.setCellType(Cell.CELL_TYPE_STRING);
        }
        String location = cell24.getStringCellValue();
        if( "".equals(location) ){
            return "安置点不可以为空！";
        }
        System.out.println("安置点："+location);*/
        //14获取户主姓名，以此来将下边的人来进行身份的鉴别，如果这个单元格里边没有值，取出来的是“”；空串。
        Cell cell14 = row1.getCell(4);
        String masterName ="";
        if(cell14!=null){
            cell14.setCellType(Cell.CELL_TYPE_STRING);
            masterName = cell14.getStringCellValue();
        }

        /*if( "".equals(masterName) ){
            return "户主姓名不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
        }*/
        System.out.println("户主姓名："+masterName);
        //17获取电话号码
        Cell cell17 = row1.getCell(7);
        String phone = "";
        if(cell17!=null){
            cell17.setCellType(Cell.CELL_TYPE_STRING);
            phone = cell17.getStringCellValue();
        }
        System.out.println("电话号码："+masterName);
        //取出银行卡信息
        //22获取开户人姓名
        Row row2 = firstSheet.getRow(2);
        Cell cell22 = row2.getCell(2);
        String account_name ="";
        if(cell22!=null){
            cell22.setCellType(Cell.CELL_TYPE_STRING);
            account_name = cell22.getStringCellValue();
        }
        System.out.println("开户人姓名："+account_name);
        //24获取开户行名称
        Cell cell24 = row2.getCell(4);
        String bank_name ="";
        if(cell24!=null){
            cell24.setCellType(Cell.CELL_TYPE_STRING);
            bank_name = cell24.getStringCellValue();
        }
        System.out.println("开户行名称："+bank_name);
        //27获取银行卡号
        Cell cell27 = row2.getCell(7);
        String account_number ="";
        if(cell27!=null){
            cell27.setCellType(Cell.CELL_TYPE_STRING);
            account_number = cell27.getStringCellValue();
        }
        System.out.println("银行卡号："+account_number);
        Bank bank = new Bank();
        bank.setFid(fid);
        bank.setAccount_name(account_name);
        bank.setBank_name(bank_name);
        bank.setAccount_number(account_number);
        //存储数据库
        Integer intResultOfBank = 0;
        if( bank != null ){
            try {
                intResultOfBank =  excelMapper.saveBank(bank);
            } catch (Exception e) {
                intResultOfBank = -1;
            }
        }


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
        List<People> pl = new ArrayList<People>();
        for (int i = 0; i < firstSheetMergeCount; i++) {
            CellRangeAddress range = firstSheet.getMergedRegion(i);
            firstSheetfirstColumn = range.getFirstColumn();
            firstSheetlastColumn = range.getLastColumn();
            firstSheetfirstRow = range.getFirstRow();
            firstSheetlastRow = range.getLastRow();
            if( firstSheetfirstRow == 3 & firstSheetfirstColumn == 0){
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
           /* if( ("".equals(cell.getStringCellValue())) ){
                return "姓名不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
            }*/
            //第一个单元格不是空的在去取这一行的数据。
            if( !("".equals(cell.getStringCellValue())) ){
                num++;
                //取出剩下的东西
                //姓名
                Cell cell1 = row.getCell(1);
                String name ="";
                if( cell1 != null ){
                    cell1.setCellType(Cell.CELL_TYPE_STRING);
                    name = cell1.getStringCellValue();
                }
                //身份证号
                Cell cell2 = row.getCell(2);
                String pid = "";
                if( cell2 != null ){
                    cell2.setCellType(Cell.CELL_TYPE_STRING);
                    pid = cell2.getStringCellValue();
                }
                /*if( "".equals(pid) ){
                    return "身份证号不可以为空！"+"   表编号："+fid+"   sheet名字："+firstSheet.getSheetName();
                }*/
                //性别
                Cell cell4 = row.getCell(4);
                String gender = "";
                if( cell4 != null ){
                    cell4.setCellType(Cell.CELL_TYPE_STRING);
                    gender = cell4.getStringCellValue();
                }
                //民族
                Cell cell5 = row.getCell(5);
                String race = "";
                if( cell5 != null ){
                    cell5.setCellType(Cell.CELL_TYPE_STRING);
                    race = cell5.getStringCellValue();
                }
                //与户主关系
                Cell cell6 = row.getCell(6);
                String relation = "";
                if( cell6 != null ){
                    cell6.setCellType(Cell.CELL_TYPE_STRING);
                    relation = cell6.getStringCellValue();
                }
                //文化程度
                Cell cell7 = row.getCell(7);
                String education = "";
                if( cell7 != null ){
                    cell7.setCellType(Cell.CELL_TYPE_STRING);
                    education = cell7.getStringCellValue();
                }
                //现从事职业
                Cell cell8 = row.getCell(8);
                String profession = "";
                if( cell8 != null ){
                    cell8.setCellType(Cell.CELL_TYPE_STRING);
                    profession = cell8.getStringCellValue();
                }
                //赋值
                People peo = new People();
                peo.setFid(fid);
                peo.setReservoir(reservoir);
                peo.setTable_type(table_type);
                peo.setName(name);
                if( masterName.equals(name) ){
                    peo.setMaster(1);
                    peo.setPhone(phone);
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
                pl.add(peo);
            }
        }
        //取出来调查者和被调查者
        Row interview = firstSheet.getRow(firstSheetendRow + 41);
        //调查人
        Cell cellInterviewer = interview.getCell(4);
        String interviewer ="";
        if(cellInterviewer!=null){
            cellInterviewer.setCellType(Cell.CELL_TYPE_STRING);
            interviewer = cellInterviewer.getStringCellValue();
        }
        //被调查人
        Cell cellInterviewee = interview.getCell(1);
        String interviewee = "";
        if(cellInterviewee!=null){
            cellInterviewee.setCellType(Cell.CELL_TYPE_STRING);
            interviewee = cellInterviewee.getStringCellValue();
        }
        //填表时间
        Cell cellCreate_at = interview.getCell(7);
        String create_at = "";
        if(cellCreate_at!=null){
            cellCreate_at.setCellType(Cell.CELL_TYPE_STRING);
            create_at = cellCreate_at.getStringCellValue();
        }
        //取出来其他
        Row other = firstSheet.getRow(firstSheetendRow + 40);
        //是否建档立卡
        Cell cellProp = other.getCell(2);
        String prop = "";
        if(cellProp!=null){
            cellProp.setCellType(Cell.CELL_TYPE_STRING);
            prop = cellProp.getStringCellValue();
        }
        //致贫原因
        Cell cellPoor = other.getCell(4);
        String poor_reason = "";
        if(cellPoor!=null){
            cellPoor.setCellType(Cell.CELL_TYPE_STRING);
            poor_reason = cellPoor.getStringCellValue();
        }



        //在这里给集合中的每个用户添加属性
        for( People p: pl ){
            p.setHome_size(num);
            p.setImm_num(num);
            p.setProp("是".equals(prop)?1:0);
            p.setPoor_reason(poor_reason);
            p.setInterviewee(interviewee);
            p.setInterviewer(interviewer);
            p.setCreated_at(create_at);
        }
        //调用Dao层方法将家庭成员信息的存储到数据库中去
        Integer intResultOfPeople = 0;
        if( pl.size() > 0 ){
            try {
                intResultOfPeople = excelMapper.batchSavePeople(pl);
            } catch (Exception e) {
                intResultOfPeople = -1;
            }
        }




        //开始取搬迁信息
        //所在地
        Row rowLeave = firstSheet.getRow(firstSheetendRow + 2);
        //州市
        Cell cellLeave2 = rowLeave.getCell(1);
        String from_city ="";
        if( cellLeave2 != null ){
            cellLeave2.setCellType(Cell.CELL_TYPE_STRING);
            from_city = cellLeave2.getStringCellValue();
        }
        System.out.println(from_city);
        //区县
        Cell cellLeave3 = rowLeave.getCell(2);
        String from_district = "";
        if( cellLeave3 != null ){
            cellLeave3.setCellType(Cell.CELL_TYPE_STRING);
            from_district = cellLeave3.getStringCellValue();
        }
        System.out.println(from_district);
        //乡镇
        Cell cellLeave4 = rowLeave.getCell(3);
        String from_town = "";
        if( cellLeave4 != null ){
            cellLeave4.setCellType(Cell.CELL_TYPE_STRING);
            from_town = cellLeave4.getStringCellValue();
        }
        System.out.println(from_town);
        //村
        Cell cellLeave5 = rowLeave.getCell(4);
        String from_village ="";
        if( cellLeave5 != null ){
            cellLeave5.setCellType(Cell.CELL_TYPE_STRING);
            from_village = cellLeave5.getStringCellValue();
        }
        System.out.println(from_village);
        //组
        Cell cellLeave6 = rowLeave.getCell(5);
        String from_group = "";
        if( cellLeave6 != null ){
            cellLeave6.setCellType(Cell.CELL_TYPE_STRING);
            from_group = cellLeave6.getStringCellValue();
        }
        System.out.println(from_group);
        //迁出备注
        Cell cellLeave7 = rowLeave.getCell(6);
        String from_remark ="";
        if( cellLeave7 != null ){
            cellLeave7.setCellType(Cell.CELL_TYPE_STRING);
            from_remark = cellLeave7.getStringCellValue();
        }
        System.out.println(from_remark);
        /*//迁入行
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
        System.out.println(to_remark);*/

        //new一个对象出来，将这些数据注入
        Move move = new Move();
        move.setFid(fid);
        move.setFrom_city(from_city);
        move.setFrom_district(from_district);
        move.setFrom_town(from_town);
        move.setFrom_village(from_village);
        move.setFrom_group(from_group);
        move.setFrom_remark(from_remark);
       /* move.setTo_city(to_city);
        move.setTo_district(to_district);
        move.setTo_town(to_town);
        move.setTo_village(to_village);
        move.setTo_group(to_group);
        move.setTo_remark(to_remark);*/
        //调用Dao层的方法插入数据库
        Integer intResultOfMove =0;
        if( move != null ){
            try {
                intResultOfMove = excelMapper.saveMove(move);
            } catch (Exception e) {
                intResultOfMove = -1;
            }
        }


        //开始取住房情况信息
        Row rowMainHouse = firstSheet.getRow(firstSheetendRow + 4);
        //主房面积
        Cell cellMainHouse2 = rowMainHouse.getCell(2);
        String main_size = "";
        if( cellMainHouse2 != null ){
            cellMainHouse2.setCellType(Cell.CELL_TYPE_STRING);
            main_size = cellMainHouse2.getStringCellValue();
        }
        System.out.println("主房面积："+main_size);
        //砖混结构
        Cell cellMainHouse3 = rowMainHouse.getCell(3);
        String main_structure1 = "";
        if( cellMainHouse3 != null ){
            cellMainHouse3.setCellType(Cell.CELL_TYPE_STRING);
            main_structure1 = cellMainHouse3.getStringCellValue();
        }
        System.out.println(main_structure1);
        //砖木结构
        Cell cellMainHouse4 = rowMainHouse.getCell(4);
        String main_structure2 = "";
        if( cellMainHouse4 != null ){
            cellMainHouse4.setCellType(Cell.CELL_TYPE_STRING);
            main_structure2 = cellMainHouse4.getStringCellValue();
        }
        System.out.println(main_structure2);
        //土木结构
        Cell cellMainHouse5 = rowMainHouse.getCell(5);
        String main_structure3 = "";
        if( cellMainHouse5 != null ){
            cellMainHouse5.setCellType(Cell.CELL_TYPE_STRING);
            main_structure3 = cellMainHouse5.getStringCellValue();
        }
        System.out.println(main_structure3);
        //木竹结构
        Cell cellMainHouse6 = rowMainHouse.getCell(6);
        String main_structure4 = "";
        if( cellMainHouse6 != null ){
            cellMainHouse6.setCellType(Cell.CELL_TYPE_STRING);
            main_structure4 = cellMainHouse6.getStringCellValue();
        }
        System.out.println(main_structure4);
        //简易房
        Cell cellMainHouse7 = rowMainHouse.getCell(7);
        String main_structure5 = "";
        if( cellMainHouse7 != null ){
            cellMainHouse7.setCellType(Cell.CELL_TYPE_STRING);
            main_structure5 = cellMainHouse7.getStringCellValue();
        }
        System.out.println(main_structure5);
        //主房备注
        Cell cellMainHouse8 = rowMainHouse.getCell(8);
        String main_remark = "";
        if( cellMainHouse8 != null ){
            cellMainHouse8.setCellType(Cell.CELL_TYPE_STRING);
            main_remark = cellMainHouse8.getStringCellValue();
        }
        System.out.println(main_remark);
        //附属房行
        Row rowSubHouse = firstSheet.getRow(firstSheetendRow + 5);
        //附属房面积
        Cell cellSubHouse2 = rowSubHouse.getCell(2);
        String sub_size = "";
        if( cellSubHouse2 != null ){
            cellSubHouse2.setCellType(Cell.CELL_TYPE_STRING);
            sub_size = cellSubHouse2.getStringCellValue();
        }
        System.out.println("附属房间面积："+sub_size);
        //砖混结构
        Cell cellSubHouse3 = rowSubHouse.getCell(3);
        String sub_structure1 ="";
        if( cellSubHouse3 != null ){
            cellSubHouse3.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure1 = cellSubHouse3.getStringCellValue();
        }
        System.out.println(sub_structure1);
        //砖木结构
        Cell cellSubHouse4 = rowSubHouse.getCell(4);
        String sub_structure2 = "";
        if( cellSubHouse4 != null ){
            cellSubHouse4.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure2 = cellSubHouse4.getStringCellValue();
        }
        System.out.println(sub_structure2);
        //土木结构
        Cell cellSubHouse5 = rowSubHouse.getCell(5);
        String sub_structure3 = "";
        if( cellSubHouse5 != null ){
            cellSubHouse5.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure3 = cellSubHouse5.getStringCellValue();
        }
        System.out.println(sub_structure3);
        //木竹结构
        Cell cellSubHouse6 = rowSubHouse.getCell(6);
        String sub_structure4 = "";
        if( cellSubHouse6 != null ){
            cellSubHouse6.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure4 = cellSubHouse6.getStringCellValue();
        }
        System.out.println(sub_structure4);
        //简易房
        Cell cellSubHouse7 = rowSubHouse.getCell(7);
        String sub_structure5 ="";
        if( cellSubHouse7 != null ){
            cellSubHouse7.setCellType(Cell.CELL_TYPE_STRING);
            sub_structure5 = cellSubHouse7.getStringCellValue();
        }
        System.out.println(sub_structure5);
        //附属房备注
        Cell cellSubHouse8 = rowSubHouse.getCell(8);
        String sub_remark = "";
        if( cellSubHouse8 != null ){
            cellSubHouse8.setCellType(Cell.CELL_TYPE_STRING);
            sub_remark = cellSubHouse8.getStringCellValue();
        }
        System.out.println(sub_remark);
        //new一个对象出来，将这些数据注入
        House house = new House();
        house.setFid(fid);
        house.setMain_size("".equals(main_size)?"0.0":main_size);
        house.setMain_structure1(main_structure1);
        house.setMain_structure2(main_structure2);
        house.setMain_structure3(main_structure3);
        house.setMain_structure4(main_structure4);
        house.setMain_structure5(main_structure5);
        house.setMain_remark(main_remark);
        house.setSub_size("".equals(sub_size)?"0.0":sub_size);
        house.setSub_structure1(sub_structure1);
        house.setSub_structure2(sub_structure2);
        house.setSub_structure3(sub_structure3);
        house.setSub_structure4(sub_structure4);
        house.setSub_structure5(sub_structure5);
        house.setSub_remark(sub_remark);
        //调用Dao的方法将房屋信息插入数据库
        Integer intResultOfHouse = 0;
        if( house != null ){
            try {
                intResultOfHouse =  excelMapper.saveHouse(house);
            } catch (Exception e) {
                intResultOfHouse = -1;
            }
        }

        //取收入的数据
        List<Income> listIncome = new ArrayList<Income>();
        //收入分类之养殖业收入，一共8行内容，
        for(int incomenum = (firstSheetendRow + 7);incomenum< (firstSheetendRow + 15);incomenum++ ){
            Row rowIncome = firstSheet.getRow(incomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowIncomeType = firstSheet.getRow(firstSheetendRow + 7);
            Cell cellIncomeType1 = rowIncomeType.getCell(1);
            String income_source = "";
            if( cellIncomeType1 != null ){
                cellIncomeType1.setCellType(Cell.CELL_TYPE_STRING);
                income_source = cellIncomeType1.getStringCellValue();
            }
            System.out.println("收入分类："+income_source);
            //内容为猪
            Cell cellIncome2 = rowIncome.getCell(2);
            String income_cate = "";
            if( cellIncome2 != null ){
                cellIncome2.setCellType(Cell.CELL_TYPE_STRING);
                income_cate = cellIncome2.getStringCellValue();
            }
            System.out.println("收入内容："+income_cate);
            //猪的数量
            Cell cellIncome3 = rowIncome.getCell(3);
            String income_quantity ="";
            if( cellIncome3 != null ){
                cellIncome3.setCellType(Cell.CELL_TYPE_STRING);
                income_quantity = cellIncome3.getStringCellValue();
            }
            System.out.println("数量："+income_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(income_quantity)){
                break;
            }
            //猪的单价
            Cell cellIncome4 = rowIncome.getCell(4);
            String income_unit ="";
            if( cellIncome4 != null ){
                cellIncome4.setCellType(Cell.CELL_TYPE_STRING);
                income_unit = cellIncome4.getStringCellValue();
            }
            System.out.println("收入单价："+income_unit);
            //猪的小计
            Cell cellIncome5 = rowIncome.getCell(5);
            String income_sum = "";
            if( cellIncome5 != null ){
                cellIncome5.setCellType(Cell.CELL_TYPE_STRING);
                income_sum = cellIncome5.getStringCellValue();
            }
            System.out.println("收入小计："+income_sum);
            //备注
            Cell cellIncome6 = rowIncome.getCell(6);
            String remark ="";
            if( cellIncome6 != null ){
                cellIncome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellIncome6.getStringCellValue();
            }
            System.out.println("收入备注："+remark);
            //new一个对象出来，将数据存储进去
            Income income = new Income();
            income.setFid(fid);
            income.setIncome_source(income_source);
            income.setIncome_cate(income_cate);
            income.setIncome_quantity("".equals(income_quantity)?0:Integer.parseInt(income_quantity));
            income.setIncome_unit("".equals(income_unit)?0.0f:Float.parseFloat(income_unit));
            income.setIncome_sum("".equals(income_sum)?0.0f:Float.parseFloat(income_sum));
            income.setRemark("".equals(remark)?"无":remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(income);*/
            listIncome.add(income);
        }
        System.out.println("集合添加了养殖业之后的长度："+listIncome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //种植业收入数据
        for(int incomenum = (firstSheetendRow + 15);incomenum< (firstSheetendRow + 19);incomenum++ ){
            Row rowIncome = firstSheet.getRow(incomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowIncomeType = firstSheet.getRow(firstSheetendRow + 15);
            Cell cellIncomeType1 = rowIncomeType.getCell(1);
            String income_source ="";
            if( cellIncomeType1 != null ){
                cellIncomeType1.setCellType(Cell.CELL_TYPE_STRING);
                income_source = cellIncomeType1.getStringCellValue();
            }
            System.out.println("收入分类："+income_source);
            //内容为猪
            Cell cellIncome2 = rowIncome.getCell(2);
            String income_cate ="";
            if( cellIncome2 != null ){
                cellIncome2.setCellType(Cell.CELL_TYPE_STRING);
                income_cate = cellIncome2.getStringCellValue();
            }
            System.out.println("收入内容："+income_cate);
            //猪的数量
            Cell cellIncome3 = rowIncome.getCell(3);
            String income_quantity ="";
            if( cellIncome3 != null ){
                cellIncome3.setCellType(Cell.CELL_TYPE_STRING);
                income_quantity = cellIncome3.getStringCellValue();
            }
            System.out.println("数量："+income_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(income_quantity)){
                break;
            }
            //猪的单价
            Cell cellIncome4 = rowIncome.getCell(4);
            String income_unit ="";
            if( cellIncome4 != null ){
                cellIncome4.setCellType(Cell.CELL_TYPE_STRING);
                income_unit = cellIncome4.getStringCellValue();
            }
            System.out.println("收入单价："+income_unit);
            //猪的小计
            Cell cellIncome5 = rowIncome.getCell(5);
            String income_sum ="";
            if( cellIncome5 != null ){
                cellIncome5.setCellType(Cell.CELL_TYPE_STRING);
                income_sum = cellIncome5.getStringCellValue();
            }
            System.out.println("收入小计："+income_sum);
            //备注
            Cell cellIncome6 = rowIncome.getCell(6);
            String remark ="";
            if( cellIncome6 != null ){
                cellIncome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellIncome6.getStringCellValue();
            }
            System.out.println("收入备注："+remark);
            //new一个对象出来，将数据存储进去
            Income income = new Income();
            income.setFid(fid);
            income.setIncome_source(income_source);
            income.setIncome_cate(income_cate);
            income.setIncome_quantity("".equals(income_quantity)?0:Integer.parseInt(income_quantity));
            income.setIncome_unit("".equals(income_unit)?0.0f:Float.parseFloat(income_unit));
            income.setIncome_sum("".equals(income_sum)?0.0f:Float.parseFloat(income_sum));
            income.setRemark("".equals(remark)?"无":remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(income);*/
            listIncome.add(income);
        }
        System.out.println("集合添加了种殖业之后的长度："+listIncome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //其他收入数据
        for(int incomenum = (firstSheetendRow + 19);incomenum< (firstSheetendRow + 21);incomenum++ ){
            Row rowIncome = firstSheet.getRow(incomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowIncomeType = firstSheet.getRow(firstSheetendRow + 19);
            Cell cellIncomeType1 = rowIncomeType.getCell(1);
            String income_source ="";
            if( cellIncomeType1 != null ){
                cellIncomeType1.setCellType(Cell.CELL_TYPE_STRING);
                income_source = cellIncomeType1.getStringCellValue();
            }
            System.out.println("收入分类："+income_source);
            //内容为猪
            Cell cellIncome2 = rowIncome.getCell(2);
            String income_cate ="";
            if( cellIncome2 != null ){
                cellIncome2.setCellType(Cell.CELL_TYPE_STRING);
                income_cate = cellIncome2.getStringCellValue();
            }
            System.out.println("收入内容："+income_cate);
            //猪的数量
            Cell cellIncome3 = rowIncome.getCell(3);
            String income_quantity ="";
            if( cellIncome3 != null ){
                cellIncome3.setCellType(Cell.CELL_TYPE_STRING);
                income_quantity = cellIncome3.getStringCellValue();
            }
            System.out.println("数量："+income_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(income_quantity)){
                break;
            }
            //猪的单价
            Cell cellIncome4 = rowIncome.getCell(4);
            String income_unit ="";
            if( cellIncome4 != null ){
                cellIncome4.setCellType(Cell.CELL_TYPE_STRING);
                income_unit = cellIncome4.getStringCellValue();
            }
            System.out.println("收入单价："+income_unit);
            //猪的小计
            Cell cellIncome5 = rowIncome.getCell(5);
            String income_sum ="";
            if( cellIncome5 != null ){
                cellIncome5.setCellType(Cell.CELL_TYPE_STRING);
                income_sum = cellIncome5.getStringCellValue();
            }
            System.out.println("收入小计："+income_sum);
            //备注
            Cell cellIncome6 = rowIncome.getCell(6);
            String remark ="";
            if( cellIncome6 != null ){
                cellIncome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellIncome6.getStringCellValue();
            }
            System.out.println("收入备注："+remark);
            //new一个对象出来，将数据存储进去
            Income income = new Income();
            income.setFid(fid);
            income.setIncome_source(income_source);
            income.setIncome_cate(income_cate);
            income.setIncome_quantity("".equals(income_quantity)?0:Integer.parseInt(income_quantity));
            income.setIncome_unit("".equals(income_unit)?0.0f:Float.parseFloat(income_unit));
            income.setIncome_sum("".equals(income_sum)?0.0f:Float.parseFloat(income_sum));
            income.setRemark("".equals(remark)?"无":remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(income);*/
            listIncome.add(income);
        }
        System.out.println("集合添加了其他之后的长度："+listIncome.size());
        //调用Dao层的插入方法存储收入数据
        Integer intResultOfIncome = 0;
        if( listIncome.size() > 0 ){
            try {
                intResultOfIncome = excelMapper.batchSaveIncome(listIncome);
            } catch (Exception e) {
                intResultOfIncome = -1;
            }
        }




        //支出的数据的取
        List<Outcome> listOutcome = new ArrayList<Outcome>();
        //支出分类之种殖业支出，
        for(int outcomenum = (firstSheetendRow + 22);outcomenum< (firstSheetendRow + 28);outcomenum++ ){
            Row rowOutcome = firstSheet.getRow(outcomenum);
            //支出类别为种殖业的,类别要单独处理
            Row rowOutcomeType = firstSheet.getRow(firstSheetendRow + 22);
            Cell cellOutcomeType1 = rowOutcomeType.getCell(1);
            String outcome_source ="";
            if( cellOutcomeType1 != null ){
                cellOutcomeType1.setCellType(Cell.CELL_TYPE_STRING);
                outcome_source = cellOutcomeType1.getStringCellValue();
            }
            System.out.println("支出分类："+outcome_source);
            //内容
            Cell cellOutcome2 = rowOutcome.getCell(2);
            String outcome_cate ="";
            if( cellOutcome2 != null ){
                cellOutcome2.setCellType(Cell.CELL_TYPE_STRING);
                outcome_cate = cellOutcome2.getStringCellValue();
            }
            System.out.println("支出内容："+outcome_cate);
            //数量
            Cell cellOutcome3 = rowOutcome.getCell(3);
            String outcome_quantity ="";
            if( cellOutcome3 != null ){
                cellOutcome3.setCellType(Cell.CELL_TYPE_STRING);
                outcome_quantity = cellOutcome3.getStringCellValue();
            }
            System.out.println("数量："+outcome_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(outcome_quantity)){
                break;
            }
            //单价
            Cell cellOutcome4 = rowOutcome.getCell(4);
            String outcome_unit ="";
            if( cellOutcome4 != null ){
                cellOutcome4.setCellType(Cell.CELL_TYPE_STRING);
                outcome_unit = cellOutcome4.getStringCellValue();
            }
            System.out.println("支出单价："+outcome_unit);
            //小计
            Cell cellOutcome5 = rowOutcome.getCell(5);
            String outcome_sum ="";
            if( cellOutcome5 != null ){
                cellOutcome5.setCellType(Cell.CELL_TYPE_STRING);
                outcome_sum = cellOutcome5.getStringCellValue();
            }
            System.out.println("支出小计："+outcome_sum);
            //备注
            Cell cellOutcome6 = rowOutcome.getCell(6);
            String remark = "";
            if( cellOutcome6 != null ){
                cellOutcome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellOutcome6.getStringCellValue();
            }
            System.out.println("支出备注："+remark);
            //new一个支出对象出来，将数据存储进去
            Outcome outcome = new Outcome();
            outcome.setFid(fid);
            outcome.setOutcome_source(outcome_source);
            outcome.setOutcome_cate(outcome_cate);
            outcome.setOutcome_quantity("".equals(outcome_quantity)?0:Integer.parseInt(outcome_quantity));
            outcome.setOutcome_unit("".equals(outcome_unit)?0.0f:Float.parseFloat(outcome_unit));
            outcome.setOutcome_sum("".equals(outcome_sum)?0.0f:Float.parseFloat(outcome_sum));
            outcome.setRemark(remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(outcome);*/
            listOutcome.add(outcome);
        }
        System.out.println("集合添加了种殖业支出之后的长度："+listOutcome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //养植业支出数据
        for(int outcomenum = (firstSheetendRow + 28);outcomenum< (firstSheetendRow + 32);outcomenum++ ){
            Row rowOutcome = firstSheet.getRow(outcomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowOutcomeType = firstSheet.getRow(firstSheetendRow + 28);
            Cell cellOutcomeType1 = rowOutcomeType.getCell(1);
            String outcome_source = "";
            if( cellOutcomeType1 != null ){
                cellOutcomeType1.setCellType(Cell.CELL_TYPE_STRING);
                outcome_source = cellOutcomeType1.getStringCellValue();
            }
            System.out.println("支出分类："+outcome_source);
            //内容
            Cell cellOutcome2 = rowOutcome.getCell(2);
            String outcome_cate = "";
            if( cellOutcome2 != null ){
                cellOutcome2.setCellType(Cell.CELL_TYPE_STRING);
                outcome_cate = cellOutcome2.getStringCellValue();
            }
            System.out.println("支出内容："+outcome_cate);
            //数量
            Cell cellOutcome3 = rowOutcome.getCell(3);
            String outcome_quantity ="";
            if( cellOutcome3 != null ){
                cellOutcome3.setCellType(Cell.CELL_TYPE_STRING);
                outcome_quantity = cellOutcome3.getStringCellValue();
            }
            System.out.println("数量："+outcome_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(outcome_quantity)){
                break;
            }
            //单价
            Cell cellOutcome4 = rowOutcome.getCell(4);
            String outcome_unit = "";
            if( cellOutcome4 != null ){
                cellOutcome4.setCellType(Cell.CELL_TYPE_STRING);
                outcome_unit = cellOutcome4.getStringCellValue();
            }
            System.out.println("支出单价："+outcome_unit);
            //小计
            Cell cellOutcome5 = rowOutcome.getCell(5);
            String outcome_sum ="";
            if( cellOutcome5 != null ){
                cellOutcome5.setCellType(Cell.CELL_TYPE_STRING);
                outcome_sum = cellOutcome5.getStringCellValue();
            }
            System.out.println("支出小计："+outcome_sum);
            //备注
            Cell cellOutcome6 = rowOutcome.getCell(6);
            String remark = "";
            if( cellOutcome6 != null ){
                cellOutcome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellOutcome6.getStringCellValue();
            }
            System.out.println("支出备注："+remark);
            //new一个对象出来，将数据存储进去
            Outcome outcome = new Outcome();
            outcome.setFid(fid);
            outcome.setOutcome_source(outcome_source);
            outcome.setOutcome_cate(outcome_cate);
            outcome.setOutcome_quantity("".equals(outcome_quantity)?0:Integer.parseInt(outcome_quantity));
            outcome.setOutcome_unit("".equals(outcome_unit)?0.0f:Float.parseFloat(outcome_unit));
            outcome.setOutcome_sum("".equals(outcome_sum)?0.0f:Float.parseFloat(outcome_sum));
            outcome.setRemark(remark);
           /* SqlParameterSource paramSource = new BeanPropertySqlParameterSource(outcome);*/
            listOutcome.add(outcome);
        }
        System.out.println("集合添加了养殖业支出之后的长度："+listOutcome.size());
        //在这里先不存储，继续去取下边的类别的数据
        //生活支出数据
        for(int outcomenum = (firstSheetendRow + 32);outcomenum< (firstSheetendRow + 40);outcomenum++ ){
            Row rowOutcome = firstSheet.getRow(outcomenum);
            //收入类别为养殖业的,类别要单独处理
            Row rowOutcomeType = firstSheet.getRow(firstSheetendRow + 32);
            Cell cellOutcomeType1 = rowOutcomeType.getCell(1);
            String outcome_source ="";
            if( cellOutcomeType1 != null ){
                cellOutcomeType1.setCellType(Cell.CELL_TYPE_STRING);
                outcome_source = cellOutcomeType1.getStringCellValue();
            }
            System.out.println("支出分类："+outcome_source);
            //内容
            Cell cellOutcome2 = rowOutcome.getCell(2);
            String outcome_cate ="";
            if( cellOutcome2 != null ){
                cellOutcome2.setCellType(Cell.CELL_TYPE_STRING);
                outcome_cate = cellOutcome2.getStringCellValue();
            }
            System.out.println("支出内容："+outcome_cate);
            //数量
            Cell cellOutcome3 = rowOutcome.getCell(3);
            String outcome_quantity ="";
            if( cellOutcome3 != null ){
                cellOutcome3.setCellType(Cell.CELL_TYPE_STRING);
                outcome_quantity = cellOutcome3.getStringCellValue();
            }
            System.out.println("数量："+outcome_quantity);
            //在这里进行判断当数量没有填写的时候结束循环
            if("".equals(outcome_quantity)){
                break;
            }
            //单价
            Cell cellOutcome4 = rowOutcome.getCell(4);
            String outcome_unit = "";
            if( cellOutcome4 != null ){
                cellOutcome4.setCellType(Cell.CELL_TYPE_STRING);
                outcome_unit = cellOutcome4.getStringCellValue();
            }
            System.out.println("支出单价："+outcome_unit);
            //小计
            Cell cellOutcome5 = rowOutcome.getCell(5);
            String outcome_sum = "";
            if( cellOutcome5 != null ){
                cellOutcome5.setCellType(Cell.CELL_TYPE_STRING);
                outcome_sum = cellOutcome5.getStringCellValue();
            }
            System.out.println("支出小计："+outcome_sum);
            //备注
            Cell cellOutcome6 = rowOutcome.getCell(6);
            String remark = "";
            if( cellOutcome6 != null ){
                cellOutcome6.setCellType(Cell.CELL_TYPE_STRING);
                remark = cellOutcome6.getStringCellValue();
            }
            System.out.println("支出备注："+remark);
            //new一个对象出来，将数据存储进去
            Outcome outcome = new Outcome();
            outcome.setFid(fid);
            outcome.setOutcome_source(outcome_source);
            outcome.setOutcome_cate(outcome_cate);
            outcome.setOutcome_quantity("".equals(outcome_quantity)?0:Integer.parseInt(outcome_quantity));
            outcome.setOutcome_unit("".equals(outcome_unit)?0.0f:Float.parseFloat(outcome_unit));
            outcome.setOutcome_sum("".equals(outcome_sum)?0.0f:Float.parseFloat(outcome_sum));
            outcome.setRemark(remark);
            /*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(outcome);*/
            listOutcome.add(outcome);
        }
        System.out.println("集合添加了生活支出之后的长度："+listOutcome.size());
        //调用Dao层的方法存储支出数据
        Integer intResultOfOutcome = 0;
        if( listOutcome.size() > 0 ){
            intResultOfOutcome = excelMapper.batchSaveOutcome(listOutcome);
        }
        //intResultOfPeople,intResultOfMove,intResultOfHouse,intResultOfIncome,intResultOfOutcome
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(intResultOfPeople);
        intList.add(intResultOfMove);
        intList.add(intResultOfHouse);
        intList.add(intResultOfIncome);
        intList.add(intResultOfOutcome);
        intList.add(intResultOfBank);
        String strResult = "";
        for( int r = 0;r < intList.size();r++){
            if( intList.get(r) == -1 ){
                strResult = "录入失败！";
            } else {
                strResult = "录入成功！";
            }
        }
        /*Map mapResult = new HashMap();
        mapResult.put("result",strResult);*/
        return strResult;
    }
    /*
     *判断一行是否为空的方法
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
