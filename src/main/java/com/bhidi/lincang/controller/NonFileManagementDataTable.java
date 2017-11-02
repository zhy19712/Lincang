package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.NonFileManagement;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.system.DBConfig;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NonFileManagementDataTable {
    @ResponseBody
    @RequestMapping(value="/allNonFileManagementDataTable",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String allNonFileManagementDataTable(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "nonfilemanagement";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        String name = "";
        List<String> roleList = new ArrayList<String>();
        if( user!=null ){
            name = user.getName();
            roleList = user.getRoleList();
        }

        String status = "";
        if( roleList.size() > 0 ) {
            if ("市局办公室".equals(roleList.get(0))) {
                status = "";
            } else {
                status = " AND submitperson = '"+name+"'";
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
        String[] cols = {"nonfileid","title", "infokind", "submitperson","status"};
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
            searchValue = searchValue.replaceAll("'","");
            sArray.add(" nonfileid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" infokind like '%" + searchValue + "%'");
            sArray.add(" submitperson like '%" + searchValue + "%'");
            sArray.add(" status like '%" + searchValue + "%'");
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

        List<NonFileManagement> tasks = new ArrayList<NonFileManagement>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1 = 1" +status;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where 1 = 1" +status;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }

            String searchSQL = "";
            String sql = "SELECT IFNULL(nonfileid,'')as nonfileid,IFNULL(title,'') as title,IFNULL(infokind,'') as infokind,IFNULL(submitperson,'') as submitperson,IFNULL(status,'') as status FROM " + table + " where 1 = 1"+status;
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
                tasks.add(new NonFileManagement (
                        rs.getString("nonfileid"),
                        rs.getString("title"),
                        rs.getString("infokind"),
                        rs.getString("submitperson"),
                        rs.getString("status")));
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
    /**
     * 待处理的事物（根据用户角色判断状态，再根据名字判断是否是这个人处理的东西）
     * @param request
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/pendingNonFileManagementDataTable",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String pendingNonFileManagementDataTable(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "nonfilemanagement";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        String name = "";
        List<String> roleList = new ArrayList<String>();
        if( user!=null ){
            name = user.getName();
            roleList = user.getRoleList();
        }
        //根据角色名字判断状态
        String status = "";
        if( roleList.size() > 0 ){
            if( "市局办公室".equals(roleList.get(0)) ){
                status = " AND status = '办公室签收并处理'";
            } else {
                status = "";
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
        String[] cols = {"nonfileid","title", "infokind", "submitperson","status"};
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
            searchValue = searchValue.replaceAll("'","");
            sArray.add(" nonfileid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" infokind like '%" + searchValue + "%'");
            sArray.add(" submitperson like '%" + searchValue + "%'");
            sArray.add(" status like '%" + searchValue + "%'");
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

        List<NonFileManagement> tasks = new ArrayList<NonFileManagement>();
        if (conn != null) {
            String recordsFilteredSql = "SELECT count(1) AS recordsFiltered FROM " + table + " WHERE 1=1 "+status;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "SELECT count(1) AS recordsTotal FROM " + table + " WHERE 1=1 "+status;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }

            String searchSQL = "";
            String sql = "SELECT IFNULL(nonfileid,'')as nonfileid,IFNULL(title,'') as title,IFNULL(infokind,'') as infokind,IFNULL(submitperson,'') as submitperson,IFNULL(status,'') as status FROM " + table + " where 1 = 1"+status;
            if (individualSearch != "") {
                searchSQL = " AND " + "("+individualSearch+")";
            }
            sql += searchSQL;
            recordsFilteredSql += searchSQL;
            sql += " order by " + orderColumn + " " + orderDir;
            recordsFilteredSql += " order by " + orderColumn + " " + orderDir;
            sql += " limit " + start + ", " + length;

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tasks.add(new NonFileManagement(
                        rs.getString("nonfileid"),
                        rs.getString("title"),
                        rs.getString("infokind"),
                        rs.getString("submitperson"),
                        rs.getString("status")));
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

    /**
     * 已处理的事物（根据用户角色判断状态，根据名字判断是否是这个人的处理的东西）
     * @param request
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/handledNonFileManagementDataTable",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String handledNonFileManagementDataTable(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "nonfilemanagement";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        String name = "";
        List<String> roleList = new ArrayList<String>();
        if( user!=null ){
            name = user.getName();
            roleList = user.getRoleList();
        }
        String status = "";
        if( roleList.size() > 0 ){
            status = " AND officeperson LIKE '%"+name+"%'";
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
        String[] cols = {"nonfileid","title", "infokind", "submitperson","status"};
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
            searchValue = searchValue.replaceAll("'","");
            sArray.add(" nonfileid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" infokind like '%" + searchValue + "%'");
            sArray.add(" submitperson like '%" + searchValue + "%'");
            sArray.add(" status like '%" + searchValue + "%'");
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

        List<NonFileManagement> tasks = new ArrayList<NonFileManagement>();
        if (conn != null) {
            String recordsFilteredSql = "SELECT count(1) AS recordsFiltered FROM " + table + " WHERE 1=1 "+status;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "SELECT count(1) AS recordsTotal FROM " + table + " WHERE  1=1 "+status;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }

            String searchSQL = "";
            String sql = "SELECT IFNULL(nonfileid,'')as nonfileid,IFNULL(title,'') as title,IFNULL(infokind,'') as infokind,IFNULL(submitperson,'') as submitperson,IFNULL(status,'') as status FROM " + table + " where 1 = 1"+status;
            if (individualSearch != "") {
                searchSQL = " AND " + "("+individualSearch+")";
            }
            sql += searchSQL;
            recordsFilteredSql += searchSQL;
            sql += " order by " + orderColumn + " " + orderDir;
            recordsFilteredSql += " order by " + orderColumn + " " + orderDir;
            sql += " limit " + start + ", " + length;

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tasks.add(new NonFileManagement(
                        rs.getString("nonfileid"),
                        rs.getString("title"),
                        rs.getString("infokind"),
                        rs.getString("submitperson"),
                        rs.getString("status")));
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
