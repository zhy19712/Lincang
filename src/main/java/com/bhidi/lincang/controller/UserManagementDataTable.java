package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.RegisterInfo;
import com.bhidi.lincang.bean.Role;
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
public class UserManagementDataTable {
    @ResponseBody
    @RequestMapping(value="/userManagementDataTableFirst",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String userManagementDataTableFirst(HttpServletRequest request,String name) throws SQLException {
            ResultSet rs = null;
            Statement stmt = null;
            Connection conn = new DBConfig().getConn();
            String table = "user";

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
            String[] cols = {"id","username","role","name","unit","department"};
            String orderColumn = "0";
            orderColumn = request.getParameter("order[0][column]");
            orderColumn = cols[Integer.parseInt(orderColumn)];
            if("id".equals(orderColumn)){
                orderColumn = "u.id";
            }
            if("username".equals(orderColumn)){
                orderColumn = "u.username";
            }
            if("role".equals(orderColumn)){
                orderColumn = "r.rolename";
            }
            if("name".equals(orderColumn)){
                orderColumn = "u.name";
            }
            if("unit".equals(orderColumn)){
                orderColumn = "ud.unit";
            }
            if("department".equals(orderColumn)){
                orderColumn = "ud.department";
            }
            //获取排序方式 默认为asc
            String orderDir = "asc";
            orderDir = request.getParameter("order[0][dir]");
            //获取用户过滤框里的字符
            String searchValue = request.getParameter("search[value]");

            List<String> sArray = new ArrayList<String>();
            if (!searchValue.equals("")) {
                searchValue = searchValue.replaceAll("'","");
                sArray.add(" u.id like '%" + searchValue + "%'");
                sArray.add(" u.username like '%" + searchValue + "%'");
                sArray.add(" u.name like '%" + searchValue + "%'");
                sArray.add(" r.rolename like '%" + searchValue + "%'");
                sArray.add(" ud.unit like '%" + searchValue + "%'");
                sArray.add(" ud.department like '%" + searchValue + "%'");
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

            List<RegisterInfo> tasks = new ArrayList<RegisterInfo>();
            if (conn != null) {
                String recordsFilteredSql = "select count(u.id) as recordsFiltered from user u,user_role ur,role r,unitanddepartment ud WHERE  u.ID = ur.userid AND ur.roleid = r.id AND u.DEPT = ud.id";
                stmt = conn.createStatement();
                //获取数据库总记录数
                String recordsTotalSql = "select count(u.id) as recordsTotal from user u,user_role ur,role r,unitanddepartment ud WHERE u.ID = ur.userid AND ur.roleid = r.id AND u.DEPT = ud.id";
                rs = stmt.executeQuery(recordsTotalSql);
                while (rs.next()) {
                    recordsTotal = rs.getString("recordsTotal");
                }

                String searchSQL = "";
                String sql = "SELECT IFNULL(u.id,0)AS id,IFNULL(u.username,'')AS username,IFNULL(r.rolename,'') AS role,IFNULL(u.name,'') AS name,IFNULL(ud.unit,'') AS unit, IFNULL(ud.department,'') AS department FROM user u,user_role ur,role r,unitanddepartment ud WHERE u.ID = ur.userid AND ur.roleid = r.id AND u.DEPT = ud.id";
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
                    tasks.add(new RegisterInfo (
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("role"),
                            rs.getString("name"),
                            rs.getString("unit"),
                            rs.getString("department")));
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
    @ResponseBody
    @RequestMapping(value="/userManagementDataTableRole",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String userManagementDataTableRole(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "role";
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
        String[] cols = {"id","rolename"};
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
            sArray.add(" id like '%" + searchValue + "%'");
            sArray.add(" rolename like '%" + searchValue + "%'");
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

        List<Role> tasks = new ArrayList<Role>();
        if (conn != null) {
            String recordsFilteredSql = "SELECT count(1) AS recordsFiltered FROM " + table + " WHERE 1=1 ";
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "SELECT count(1) AS recordsTotal FROM " + table + " WHERE 1=1 ";
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }

            String searchSQL = "";
            String sql = "SELECT id,IFNULL(rolename,'') AS rolename FROM " + table + " WHERE 1=1 ";
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
                tasks.add(new Role(
                        rs.getInt("id"),
                        rs.getString("rolename")));
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
