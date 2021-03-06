package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.CapitalFlow;
import com.bhidi.lincang.bean.Privilege;
import com.bhidi.lincang.bean.QuXianReceiveMessage;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.system.DBConfig;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class CapitalFlowDataTable {
    /**
     * 全部列表的datatables
     * @param request
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/capitalFlowAll",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String SubmittedForm(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "capitalflow";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        List<Integer> functionList = (List<Integer>)request.getSession().getAttribute("function");
        String name = "";
        String dept = "";
        String str = "";
        if( user!=null ){
            name = user.getName();
            dept = user.getDept();
        }
        /*if(dept.equals("临沧市移民局规划科")){
            str = "";
        } else {
            str = " AND shenqingrendept = '"+dept+"' ";
        }*/
        //根据功能名字判断状态
        if( functionList!=null && functionList.size() > 0 ){
            if(!functionList.contains(45) & !functionList.contains(13) & !functionList.contains(18) & !functionList.contains(46) & !functionList.contains(19)){
                str = " AND 1=0";
            }
            if( (functionList.contains(13) || functionList.contains(45)) & !functionList.contains(18) & !functionList.contains(46) & !functionList.contains(19)){
                str = " AND initiatorclass = '市局资金计划上报'";
            }
            if(!functionList.contains(45) & !functionList.contains(13) & (functionList.contains(18)||functionList.contains(46)) & !functionList.contains(19)){
                str = " AND initiatorclass = '区县资金申请'";
            }
            if(!functionList.contains(45) & !functionList.contains(13) & !functionList.contains(18) & !functionList.contains(46) & functionList.contains(19)){
                str = " AND initiatorclass = '区县资金申请' AND guihuakeshenqingperson = '"+name+"'";
            }
            if((functionList.contains(13) || functionList.contains(45)) & (functionList.contains(18)||functionList.contains(46)) & !functionList.contains(19)){
                str = "";
            }
            if((functionList.contains(13) || functionList.contains(45)) & !functionList.contains(18) & !functionList.contains(46) & functionList.contains(19)){
                str = " AND (initiatorclass = '市局资金计划上报' OR (initiatorclass = '区县资金申请' AND guihuakeshenqingperson = '"+name+"')"+")";
            }
            if(!functionList.contains(45) & !functionList.contains(13) & (functionList.contains(18)||functionList.contains(46)) & functionList.contains(19)){
                str = " AND initiatorclass = '区县资金申请'";
            }
            if((functionList.contains(13) || functionList.contains(45)) & (functionList.contains(18)||functionList.contains(46)) & functionList.contains(19)){
                str = "";
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
        String[] cols = {"capitalflowid", "title","create_time", "guihuakeshenqingperson","initiatorclass","status"};
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
            sArray.add(" capitalflowid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" create_time like '%" + searchValue + "%'");
            sArray.add(" guihuakeshenqingperson like '%" + searchValue + "%'");
            sArray.add(" initiatorclass like '%" + searchValue + "%'");
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
        List<CapitalFlow> tasks = new ArrayList<CapitalFlow>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1=1"+str;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where 1=1"+str;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }
            String searchSQL = "";
            String sql = "SELECT IFNULL(capitalflowid,'')as capitalflowid,IFNULL(create_time,'')as create_time,IFNULL(guihuakeshenqingperson,'')as guihuakeshenqingperson,IFNULL(initiatorclass,'')as initiatorclass,IFNULL(title,'')as title,IFNULL(status,'')as status FROM " + table + " where 1=1"+str;
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
                tasks.add(new CapitalFlow(rs.getString("capitalflowid"),
                        rs.getString("title"),
                        rs.getString("create_time"),
                        rs.getString("guihuakeshenqingperson"),
                        rs.getString("initiatorclass"),
                        rs.getString("status")
                ));
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
     * 待处理的datatables
     * @param request
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/capitalFlowWait",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String capitalFlowWait(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "capitalflow";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        List<Integer> functionList = (List<Integer>)request.getSession().getAttribute("function");
        String name = "";
        List<String> roleList = new ArrayList<String>();
        String dept = "";
        if( user!=null ){
            name = user.getName();
            roleList = user.getRoleList();
            dept =user.getDept();
        }
        //根据功能名字判断状态
        String str = "";
        if( functionList!=null && functionList.size() > 0 ){
            if(!functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND 1=0";
            }
            if(functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND status = '市局财务科办理'";
            }
            if(!functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND status = '市局规划科通知区县'";
            }
            if(!functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND status = '市局规划科批复'";
            }
            if(!functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND status = '市局财务科处置办理'";
            }
            if(!functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'";
            }
            if(functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县')";
            }
            if(functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科批复')";
            }
            if(functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局财务科处置办理')";
            }
            if(functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"') )";
            }
            if(!functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局规划科通知区县' OR status = '市局规划科批复')";
            }
            if(!functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局规划科通知区县' OR status = '市局财务科处置办理')";
            }
            if(!functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局规划科通知区县' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(!functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局规划科批复' OR status = '市局财务科处置办理')";
            }
            if(!functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局规划科批复' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(!functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县' OR status = '市局规划科批复')";
            }
            if(functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县' OR status = '市局财务科处置办理')";
            }
            if(functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科批复' OR status = '市局财务科处置办理')";
            }
            if(functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科批复' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(functionList.contains(14) & !functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(!functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局规划科通知区县' OR status = '市局规划科批复' OR status = '市局财务科处置办理')";
            }
            if(!functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局规划科通知区县' OR status = '市局规划科批复' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(!functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局规划科通知区县' OR status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(!functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局规划科批复' OR status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& !functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县' OR status = '市局规划科批复' OR status = '市局财务科处置办理')";
            }
            if(functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& !functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县' OR status = '市局规划科批复' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(functionList.contains(14) & functionList.contains(15) & !functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县' OR status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(functionList.contains(14) & !functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科批复' OR status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(!functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局规划科通知区县' OR status = '市局规划科批复' OR status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
            }
            if(functionList.contains(14) & functionList.contains(15) & functionList.contains(20)& functionList.contains(21)& functionList.contains(23)){
                str = " AND (status = '市局财务科办理' OR status = '市局规划科通知区县' OR status = '市局规划科批复' OR status = '市局财务科处置办理' OR (status = '区县资金流向记录' AND shenqingrendept = '"+dept+"'))";
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
        String[] cols = {"capitalflowid", "title","create_time", "guihuakeshenqingperson","initiatorclass","status"};
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
            sArray.add(" capitalflowid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" create_time like '%" + searchValue + "%'");
            sArray.add(" guihuakeshenqingperson like '%" + searchValue + "%'");
            sArray.add(" initiatorclass like '%" + searchValue + "%'");
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
        List<CapitalFlow> tasks = new ArrayList<CapitalFlow>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1=1"+str;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where 1=1"+str;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }
            String searchSQL = "";
            String sql = "SELECT IFNULL(capitalflowid,'')as capitalflowid,IFNULL(create_time,'')as create_time,IFNULL(guihuakeshenqingperson,'')as guihuakeshenqingperson,IFNULL(initiatorclass,'')as initiatorclass,IFNULL(title,'')as title,IFNULL(status,'')as status FROM " + table + " where 1=1"+str;
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
                tasks.add(new CapitalFlow(rs.getString("capitalflowid"),
                        rs.getString("title"),
                        rs.getString("create_time"),
                        rs.getString("guihuakeshenqingperson"),
                        rs.getString("initiatorclass"),
                        rs.getString("status")
                ));
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
     * 已处理的datatables
     * @param request
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/capitalFlowDealed",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String capitalFlowDealed(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "capitalflow";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        //List<Integer> functionList = (List<Integer>)request.getSession().getAttribute("function");
        String name = "";
        String str = "";
        if( user!=null ){
            name = user.getName();
            str = " AND (caiwuchuliren = '"+name+"' OR guihuachuliren = '"+name+"'OR guihuapifuren = '"+name+"' OR caiwuzhuanzhangren = '"+name+"'OR quxianbaocunren LIKE '%"+name+"%'OR quxiantijiaoren = '"+name+"')";
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
        String[] cols = {"capitalflowid", "title","create_time", "guihuakeshenqingperson","initiatorclass","status"};
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
            sArray.add(" capitalflowid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" create_time like '%" + searchValue + "%'");
            sArray.add(" guihuakeshenqingperson like '%" + searchValue + "%'");
            sArray.add(" initiatorclass like '%" + searchValue + "%'");
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
        List<CapitalFlow> tasks = new ArrayList<CapitalFlow>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1=1"+str;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where 1=1"+str;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }
            String searchSQL = "";
            String sql = "SELECT IFNULL(capitalflowid,'')as capitalflowid,IFNULL(create_time,'')as create_time,IFNULL(guihuakeshenqingperson,'')as guihuakeshenqingperson,IFNULL(initiatorclass,'')as initiatorclass,IFNULL(title,'')as title,IFNULL(status,'')as status FROM " + table + " where 1=1"+str;
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
                tasks.add(new CapitalFlow(rs.getString("capitalflowid"),
                        rs.getString("title"),
                        rs.getString("create_time"),
                        rs.getString("guihuakeshenqingperson"),
                        rs.getString("initiatorclass"),
                        rs.getString("status")
                ));
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
     * 区县查看各自的信息的表格
     * @param request
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/quxianMessage",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String quxianMessage(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "quxianreceivemessage";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        //List<Integer> functionList = (List<Integer>)request.getSession().getAttribute("function");
        String name = "";
        String str = "";
        if( user!=null ){
            name = user.getName();
            str = " AND (quxianpeople = '"+name+"')";
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
        String[] cols = {"capitalflowid","guihuachuliren","guihuakechulitime","status"};
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
            sArray.add(" capitalflowid like '%" + searchValue + "%'");
            sArray.add(" guihuachuliren like '%" + searchValue + "%'");
            sArray.add(" guihuakechulitime like '%" + searchValue + "%'");
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
        List<QuXianReceiveMessage> tasks = new ArrayList<QuXianReceiveMessage>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1=1"+str;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where 1=1"+str;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }
            String searchSQL = "";
            String sql = "SELECT IFNULL(capitalflowid,'')as capitalflowid,IFNULL(guihuachuliren,'')as guihuachuliren,IFNULL(guihuakechulitime,'')as guihuakechulitime,IFNULL(status,'')as status FROM " + table + " where 1=1"+str;
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
                tasks.add(new QuXianReceiveMessage(rs.getString("capitalflowid"),
                        rs.getString("guihuachuliren"),
                        rs.getString("guihuakechulitime"),
                        rs.getString("status")
                ));
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
