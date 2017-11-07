package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.RegisterInfo;
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
            String[] cols = {"username","role","name","unit","department"};
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
                sArray.add(" username like '%" + searchValue + "%'");
                sArray.add(" role like '%" + searchValue + "%'");
                sArray.add(" name like '%" + searchValue + "%'");
                sArray.add(" unit like '%" + searchValue + "%'");
                sArray.add(" department like '%" + searchValue + "%'");
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
                String recordsFilteredSql = "select count(1) as recordsFiltered from " + table;
                stmt = conn.createStatement();
                //获取数据库总记录数
                String recordsTotalSql = "select count(1) as recordsTotal from " + table;
                rs = stmt.executeQuery(recordsTotalSql);
                while (rs.next()) {
                    recordsTotal = rs.getString("recordsTotal");
                }

                String searchSQL = "";
                String sql = "SELECT IFNULL(u.username,'')AS username,IFNULL(r.rolename,'') AS role,IFNULL(u.name,'') AS name,IFNULL(ud.unit,'') AS unit, IFNULL(ud.department,'') AS department FROM user u,user_role ur,role r,unitanddepartment ud WHERE u.ID = ur.userid AND ur.roleid = r.id AND u.DEPT=ud.id";
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
}
