package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.FamilyInfoByName;
import com.bhidi.lincang.bean.Table_info;
import com.bhidi.lincang.system.DBConfig;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TableAddByName {
    @ResponseBody
    @RequestMapping(value="/TableAddByName",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String TableAddByName(HttpServletRequest request,String name) throws SQLException {
            ResultSet rs = null;
            Statement stmt = null;
            Connection conn = new DBConfig().getConn();
            String table = "PEOPLE";

            if( name != null ){
                try {
                    name = URLDecoder.decode(name,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }


            //获取请求次数
            String draw = "0";
            draw = request.getParameter("draw");
            //数据起始位置
            String start = request.getParameter("start");
            //数据长度
            String length = request.getParameter("length");

            //总记录数
            String recordsTotal = "0";

            //过滤后记录数
            String recordsFiltered = "";


            //定义列名
            String[] cols = {"FID","TABLE_TYPE","NAME", "RESERVOIR", "FROM_DISTRICT","INTERVIEWER","CREATED_AT"};
            String orderColumn = "0";
            orderColumn = request.getParameter("order[0][column]");
            orderColumn = cols[Integer.parseInt(orderColumn)];
            //获取排序方式 默认为asc
            String orderDir = "asc";
            orderDir = request.getParameter("order[0][dir]");

            //获取用户过滤框里的字符
            String searchValue = request.getParameter("search[value]");


            List<String> sArray = new ArrayList<String>();
            if (!searchValue.equals("")) {
                sArray.add(" FID like '%" + searchValue + "%'");
                sArray.add(" TABLE_TYPE like '%" + searchValue + "%'");
                sArray.add(" NAME like '%" + searchValue + "%'");
                sArray.add(" RESERVOIR like '%" + searchValue + "%'");
                sArray.add(" m.FROM_DISTRICT like '%" + searchValue + "%'");
                sArray.add(" INTERVIEWER like '%" + searchValue + "%'");
                sArray.add(" CREATED_AT like '%" + searchValue + "%'");
            }


            String individualSearch = "";
            if (sArray.size() == 1) {
                individualSearch = sArray.get(0);
            } else if (sArray.size() > 1) {
                for (int i = 0; i < sArray.size() - 1; i++) {
                    individualSearch += sArray.get(i) + " or ";
                }
                individualSearch += sArray.get(sArray.size() - 1);
            }

            List<Table_info> tasks = new ArrayList<Table_info>();
            if (conn != null) {
                String recordsFilteredSql = "SELECT count(p.NAME) as recordsFiltered FROM people p INNER JOIN move m ON p.`FID` = m.`FID` WHERE m.FROM_DISTRICT = '"+name+"' AND p.`MASTER` = '1'";
                stmt = conn.createStatement();
                //获取数据库总记录数
                String recordsTotalSql = "SELECT count(p.NAME) as recordsTotal FROM people p INNER JOIN move m ON p.`FID` = m.`FID` WHERE m.FROM_DISTRICT = '"+name+"' AND p.`MASTER` = '1'";
                rs = stmt.executeQuery(recordsTotalSql);
                while (rs.next()) {
                    recordsTotal = rs.getString("recordsTotal");
                }

                String searchSQL = "";
                String sql = "SELECT IFNULL(p.FID,'') as FID,IFNULL(p.TABLE_TYPE,'')as TABLE_TYPE,IFNULL(p.NAME,'')as NAME,IFNULL(p.RESERVOIR,'')as RESERVOIR,IFNULL(m.FROM_DISTRICT,'')as FROM_DISTRICT,IFNULL(p.INTERVIEWER,'')as INTERVIEWER,IFNULL(p.CREATED_AT,'')as CREATED_AT FROM people p INNER JOIN move m ON p.`FID` = m.`FID` WHERE m.FROM_DISTRICT = '"+ name +"' AND p.`MASTER` = '1'";
                if (individualSearch != "") {
                    searchSQL = " and " + "("+individualSearch+")";
                }
                sql += searchSQL;
                recordsFilteredSql += searchSQL;
                sql += " order by " + orderColumn + " " + orderDir;
                recordsFilteredSql += " order by " + orderColumn + " " + orderDir;
                sql += " limit " + start + ", " + length;


                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    tasks.add(new Table_info(
                            rs.getString("FID"),
                            rs.getString("TABLE_TYPE"),
                            rs.getString("NAME"),
                            rs.getString("RESERVOIR"),
                            rs.getString("FROM_DISTRICT"),
                            rs.getString("INTERVIEWER"),
                            rs.getString("CREATED_AT")));
                }

                if (searchValue != "") {
                    rs = stmt.executeQuery(recordsFilteredSql);
                    while (rs.next()) {
                        recordsFiltered = rs.getString("recordsFiltered");
                    }
                } else {
                    recordsFiltered = recordsTotal;
                }
            }


            Map<Object, Object> info = new HashMap<Object, Object>();
            info.put("data", tasks);
            info.put("recordsTotal", recordsTotal);
            info.put("recordsFiltered", recordsFiltered);
            info.put("draw", draw);
            String json = new Gson().toJson(info);
            rs.close();
            stmt.close();
            conn.close();
            return json;
    }

   /* @ResponseBody
    @RequestMapping(value="/sform_office.do",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String SubmittedForm(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "FORM_OFFICE";

        //获取请求次数
        String draw = "0";
        draw = request.getParameter("draw");
        //数据起始位置
        String start = request.getParameter("start");
        //数据长度
        String length = request.getParameter("length");

        //总记录数
        String recordsTotal = "0";

        //过滤后记录数
        String recordsFiltered = "";


        //定义列名
        String[] cols = {"OID","CREATED_AT", "DEPT", "AUTHOR", "TITLE"};
        String orderColumn = "0";
        orderColumn = request.getParameter("order[0][column]");
        orderColumn = cols[Integer.parseInt(orderColumn)];
        //获取排序方式 默认为asc
        String orderDir = "asc";
        orderDir = request.getParameter("order[0][dir]");

        //获取用户过滤框里的字符
        String searchValue = request.getParameter("search[value]");


        List<String> sArray = new ArrayList<String>();
        if (!searchValue.equals("")) {
            sArray.add(" OID like '%" + searchValue + "%'");
            sArray.add(" CREATED_AT like '%" + searchValue + "%'");
            sArray.add(" DEPT like '%" + searchValue + "%'");
            sArray.add(" AUTHOR like '%" + searchValue + "%'");
            sArray.add(" TITLE like '%" + searchValue + "%'");
        }


        String individualSearch = "";
        if (sArray.size() == 1) {
            individualSearch = sArray.get(0);
        } else if (sArray.size() > 1) {
            for (int i = 0; i < sArray.size() - 1; i++) {
                individualSearch += sArray.get(i) + " or ";
            }
            individualSearch += sArray.get(sArray.size() - 1);
        }

        List<Form_Office> tasks = new ArrayList<Form_Office>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where STATUS = 'SUBMITTED'";
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where STATUS = 'SUBMITTED'";
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }

            String searchSQL = "";
            String sql = "SELECT * FROM " + table + " where STATUS = 'SUBMITTED'";
            if (individualSearch != "") {
                searchSQL = " and " + "("+individualSearch+")";
            }
            sql += searchSQL;
            recordsFilteredSql += searchSQL;
            sql += " order by " + orderColumn + " " + orderDir;
            recordsFilteredSql += " order by " + orderColumn + " " + orderDir;
            sql += " limit " + start + ", " + length;


            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tasks.add(new Form_Office(
                        rs.getInt("OID"),
                        rs.getString("CREATED_AT"),
                        rs.getString("DEPT"),
                        rs.getString("AUTHOR"),
                        rs.getString("TITLE")));
            }

            if (searchValue != "") {
                rs = stmt.executeQuery(recordsFilteredSql);
                while (rs.next()) {
                    recordsFiltered = rs.getString("recordsFiltered");
                }
            } else {
                recordsFiltered = recordsTotal;
            }
        }


        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("data", tasks);
        info.put("recordsTotal", recordsTotal);
        info.put("recordsFiltered", recordsFiltered);
        info.put("draw", draw);
        String json = new Gson().toJson(info);
        rs.close();
        stmt.close();
        conn.close();
        return json;
    }*/

}