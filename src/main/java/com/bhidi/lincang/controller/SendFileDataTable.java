package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.SendFile;
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
public class SendFileDataTable {
    @ResponseBody
    @RequestMapping(value="/sendFileDataTableFirst",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String sendFileDataTableFirst(HttpServletRequest request) throws SQLException {
            ResultSet rs = null;
            Statement stmt = null;
            Connection conn = new DBConfig().getConn();
            String table = "sendfile";
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
                    status = " AND applicant = '"+name+"'";
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
            String[] cols = {"sendfileid","title", "createdtime", "dept","status"};
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
                sArray.add(" sendfileid like '%" + searchValue + "%'");
                sArray.add(" title like '%" + searchValue + "%'");
                sArray.add(" createdtime like '%" + searchValue + "%'");
                sArray.add(" dept like '%" + searchValue + "%'");
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

            List<SendFile> tasks = new ArrayList<SendFile>();
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
                String sql = "SELECT IFNULL(sendfileid,'') AS sendfileid,IFNULL(title,'') AS title,IFNULL(createdtime,'') AS createdtime,IFNULL(dept,'') AS dept,IFNULL(status,'') AS status FROM " + table + " WHERE 1=1 "+status;
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
                    tasks.add(new SendFile(
                            rs.getString("sendfileid"),
                            rs.getString("title"),
                            rs.getString("createdtime"),
                            rs.getString("dept"),
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
    @RequestMapping(value="/sendFileDataTableSecond",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String sendFileDataTableSecond(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "sendfile";
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
                status = " AND ( (status = '办公室审核处理') OR (status = '办公室归档') OR ( status = '签批' AND approver LIKE '%"+name+"%' AND approverdelete NOT LIKE '%"+name+"%' ) OR ( status = '处理处置' AND implementperson LIKE '%"+name+"%' AND implementpersondelete NOT LIKE '%"+name+"%' ) )";
            } else {
                status = " AND ( (approver LIKE '%"+name+"%' AND approverdelete NOT LIKE '%"+name+"%') OR (implementperson LIKE '%"+name+"%' AND implementpersondelete NOT LIKE '%"+name+"%' )  )";
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
        String[] cols = {"sendfileid","title", "createdtime", "dept","status"};
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
            sArray.add(" sendfileid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" createdtime like '%" + searchValue + "%'");
            sArray.add(" dept like '%" + searchValue + "%'");
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

        List<SendFile> tasks = new ArrayList<SendFile>();
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
            String sql = "SELECT IFNULL(sendfileid,'') AS sendfileid,IFNULL(title,'') AS title,IFNULL(createdtime,'') AS createdtime,IFNULL(dept,'') AS dept,IFNULL(status,'') AS status FROM " + table + " WHERE 1=1 "+status;
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
                tasks.add(new SendFile(
                        rs.getString("sendfileid"),
                        rs.getString("title"),
                        rs.getString("createdtime"),
                        rs.getString("dept"),
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
    @RequestMapping(value="/sendFileDataTableThird",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String sendFileDataTableThird(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "sendfile";
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
            status = " AND ( (officeprocessperson LIKE '%"+name+"%') OR (approver LIKE '%"+name+"%' AND approverdelete LIKE '%"+name+"%') OR (implementperson LIKE '%"+name+"%' AND implementpersondelete LIKE '%"+name+"%') OR (confirmperson LIKE '%"+name+"%') )";
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
        String[] cols = {"sendfileid","title", "createdtime", "dept","status"};
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
            sArray.add(" sendfileid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" createdtime like '%" + searchValue + "%'");
            sArray.add(" dept like '%" + searchValue + "%'");
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

        List<SendFile> tasks = new ArrayList<SendFile>();
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
            String sql = "SELECT IFNULL(sendfileid,'') AS sendfileid,IFNULL(title,'') AS title,IFNULL(createdtime,'') AS createdtime,IFNULL(dept,'') AS dept,IFNULL(status,'') AS status FROM " + table + " WHERE 1=1 "+status;
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
                tasks.add(new SendFile(
                        rs.getString("sendfileid"),
                        rs.getString("title"),
                        rs.getString("createdtime"),
                        rs.getString("dept"),
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
