package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.OriginalFrom;
import com.bhidi.lincang.system.DBConfig;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WaitingTable_Stuff {
    @ResponseBody
    @RequestMapping(value = "/oform", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String FetchData(@RequestParam(value = "draw", required = false) String draw,
                            @RequestParam(value = "start", required = false) String start,
                            @RequestParam(value = "length", required = false) String length,
                            @RequestParam(value = "order[0][column]", required = false) String orderColumn,
                            @RequestParam(value = "order[0][dir]", required = false) String orderDir,
                            @RequestParam(value = "search[value]", required = false) String searchValue) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "FORM_ORIGIN";

        //总记录数
        String recordsTotal = "0";

        //过滤后记录数
        String recordsFiltered = "";


        //定义列名
        String[] cols = {"OID", "TITLE", "CREATED_AT"};

        //获取用户过滤框里的字符

        List<String> sArray = new ArrayList<String>();
        if (!searchValue.equals("")) {
            sArray.add(" OID like '%" + searchValue + "%'");
            sArray.add(" TITLE like '%" + searchValue + "%'");
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

        List<OriginalFrom> tasks = new ArrayList<OriginalFrom>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }


            String searchSQL = "";
            String sql = "SELECT * FROM " + table;
            if (individualSearch != "") {
                searchSQL = " where " + individualSearch;
            }
            sql += searchSQL;
            recordsFilteredSql += searchSQL;
            sql += " order by " + orderColumn + " " + orderDir;
            recordsFilteredSql += " order by " + orderColumn + " " + orderDir;
            sql += " limit " + start + ", " + length;


            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tasks.add(new OriginalFrom(rs.getString("OID"),
                        rs.getString("TITLE"),
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
}
