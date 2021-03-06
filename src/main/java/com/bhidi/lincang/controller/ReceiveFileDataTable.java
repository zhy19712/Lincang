package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.ReceiveFile;
import com.bhidi.lincang.bean.User;
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
public class ReceiveFileDataTable {
    @ResponseBody
    @RequestMapping(value="/receiveFileDataTable",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String receiveFileDataTable(HttpServletRequest request,String name) throws SQLException {
            ResultSet rs = null;
            Statement stmt = null;
            Connection conn = new DBConfig().getConn();
            String table = "receivefile";
            List<Integer> functionList = (List<Integer>)request.getSession().getAttribute("function");
            if( name != null ){
                try {
                    name = URLDecoder.decode(name,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            String status = "";
            if( functionList!=null && functionList.size() > 0 ){
                if(functionList.contains(34)){
                    status = " AND 1=1";
                } else {
                    status = " AND 1=0";
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
            String[] cols = {"receivefileid","year","type", "cometime",  "title","status"};
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
                sArray.add(" year like '%" + searchValue + "%'");
                sArray.add(" type like '%" + searchValue + "%'");
                sArray.add(" cometime like '%" + searchValue + "%'");
                sArray.add(" receivefileid like '%" + searchValue + "%'");
                sArray.add(" title like '%" + searchValue + "%'");
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

            List<ReceiveFile> tasks = new ArrayList<ReceiveFile>();
            if (conn != null) {
                String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1 = 1 "+status;
                stmt = conn.createStatement();
                //获取数据库总记录数
                String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where 1 = 1 "+status;
                rs = stmt.executeQuery(recordsTotalSql);
                while (rs.next()) {
                    recordsTotal = rs.getString("recordsTotal");
                }

                String searchSQL = "";
                String sql = "SELECT IFNULL(year,'')as year,IFNULL(type,'') as type,IFNULL(cometime,'') as cometime,IFNULL(receivefileid,'') as receivefileid,IFNULL(title,'') as title,(CASE WHEN STATUS='科室签批' OR STATUS='分管领导签批' OR STATUS = '主管领导签批' THEN '签批' ELSE STATUS END) as status FROM " + table + " where 1 = 1 " +status;
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
                    tasks.add(new ReceiveFile (
                            rs.getString("year"),
                            rs.getString("type"),
                            rs.getString("cometime"),
                            rs.getString("receivefileid"),
                            rs.getString("title"),
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
     * 待处理的事物（根据用户角色判断状态，根据名字判断是否是这个人的处理的东西）
     * @param request
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/receiveFileDataTableByNameAndStatus",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String receiveFileDataTableByNameAndStatus(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "receivefile";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        List<Integer> functionList = (List<Integer>)request.getSession().getAttribute("function");
        String name = "";
        List<String> roleList = new ArrayList<String>();
        if( user!=null ){
            name = user.getName();
            roleList = user.getRoleList();
        }
        //根据角色名字判断状态
        String status = "";
        if( functionList!=null && functionList.size() > 0 ){
            /*if(functionList.contains(39)){*/
                if(!functionList.contains(35) & !functionList.contains(36) & !functionList.contains(37)& !functionList.contains(38)){
                    status = " AND 1=0";
                }
                if(functionList.contains(35) & !functionList.contains(36) & !functionList.contains(37)& !functionList.contains(38)){
                    status = " AND status = '办公室处理文件'";
                }
                if(!functionList.contains(35) & functionList.contains(36) & !functionList.contains(37)& !functionList.contains(38)){
                    status = " AND ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%'))";
                }
                if(!functionList.contains(35) & !functionList.contains(36) & functionList.contains(37)& !functionList.contains(38)){
                    status = " AND status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%' ";
                }
                if(!functionList.contains(35) & !functionList.contains(36) & !functionList.contains(37)& functionList.contains(38)){
                    status = " AND status = '办公室归档'";
                }
                if(functionList.contains(35) & functionList.contains(36) & !functionList.contains(37)& !functionList.contains(38)){
                    status = " AND (status = '办公室处理文件' OR ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%')))";
                }
                if(functionList.contains(35) & !functionList.contains(36) & functionList.contains(37)& !functionList.contains(38)){
                    status = " AND (status = '办公室处理文件' OR (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%' ))";
                }
                if(functionList.contains(35) & !functionList.contains(36) & !functionList.contains(37)& functionList.contains(38)){
                    status = " AND (status = '办公室处理文件' OR status = '办公室归档')";
                }
                if(!functionList.contains(35) & functionList.contains(36) & functionList.contains(37)& !functionList.contains(38)){
                    status = " AND ((status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%') OR ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%')) )";
                }
                if(!functionList.contains(35) & functionList.contains(36) & !functionList.contains(37)& functionList.contains(38)){
                    status = " AND ((status = '办公室归档') OR ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%')) )";
                }
                if(!functionList.contains(35) & !functionList.contains(36) & functionList.contains(37) & functionList.contains(38)){
                    status = " AND ((status = '办公室归档') OR (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%' ) )";
                }
                if(functionList.contains(35) & functionList.contains(36) & functionList.contains(37)& !functionList.contains(38)){
                    status = " AND (status = '办公室处理文件' OR (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%') OR ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%')))";
                }
                if(functionList.contains(35) & functionList.contains(36) & !functionList.contains(37)& functionList.contains(38)){
                    status = " AND (status = '办公室处理文件' OR (status = '办公室归档') OR ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%')))";
                }
                if(functionList.contains(35) & !functionList.contains(36) & functionList.contains(37) & functionList.contains(38)){
                    status = " AND (status = '办公室处理文件' OR (status = '办公室归档') OR (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%' ) )";
                }
                if(!functionList.contains(35) & functionList.contains(36) & functionList.contains(37)& functionList.contains(38)){
                    status = " AND ((status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%') OR (status = '办公室归档') OR ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%')))";
                }
                if(functionList.contains(35) & functionList.contains(36) & functionList.contains(37)& functionList.contains(38)){
                    status = " AND (status = '办公室处理文件' OR (status = '办公室归档') OR (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%') OR ( (status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%'))) OR  (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%')))";
                }
            /*} else {
                status = " AND 1=0";
            }*/
        }
        /*if( "市局办公室管理角色".equals(roleList.get(0)) ){
            status = " and ( (status = '办公室处理文件' or status = '办公室归档') or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%' ) or ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%')) )  or (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%'))";
        }
        if( !"市局办公室管理角色".equals(roleList.get(0)) & !"市局分管领导角色".equals(roleList.get(0)) & !"市局主管领导角色".equals(roleList.get(0))){
            status = " and ( ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%')) ) or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%') or (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%') )";
            //status = "and ( status = '科室签批')";
        }
        if( "市局分管领导角色".equals(roleList.get(0)) ){
            status = " and ( ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%')) ) or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%') or (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%') )";
        }
        if( "市局主管领导角色".equals(roleList.get(0)) ){
            status = " and ( ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete not like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete not like '%"+name+"%')) ) or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete not like '%"+name+"%') or (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete not like '%"+name+"%' ) or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete not like '%"+name+"%') )";
        }*/
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
        String[] cols = {"receivefileid","year","type", "cometime",  "title","status"};
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
            sArray.add(" year like '%" + searchValue + "%'");
            sArray.add(" type like '%" + searchValue + "%'");
            sArray.add(" cometime like '%" + searchValue + "%'");
            sArray.add(" receivefileid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
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

        List<ReceiveFile> tasks = new ArrayList<ReceiveFile>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1=1 "+status;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where 1=1 "+status;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }

            String searchSQL = "";
            String sql = "SELECT IFNULL(year,'')as year,IFNULL(type,'') as type,IFNULL(cometime,'') as cometime,IFNULL(receivefileid,'') as receivefileid,IFNULL(title,'') as title,(CASE WHEN STATUS='科室签批' OR STATUS='分管领导签批' OR STATUS = '主管领导签批' THEN '签批' ELSE STATUS END) as status FROM " + table + " where 1=1 "+status;
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
                tasks.add(new ReceiveFile (
                        rs.getString("year"),
                        rs.getString("type"),
                        rs.getString("cometime"),
                        rs.getString("receivefileid"),
                        rs.getString("title"),
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
    @RequestMapping(value="/receiveFileDataTableByNameAndStatusHave",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String receiveFileDataTableByNameAndStatusHave(HttpServletRequest request) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "receivefile";
        //获取到当前用户
        User user = (User)request.getSession().getAttribute("user");
        List<Integer> functionList = (List<Integer>)request.getSession().getAttribute("function");
        String name = "";
        List<String> roleList = new ArrayList<String>();
        if( user!=null ){
            name = user.getName();
            roleList = user.getRoleList();
        }
        //根据角色名字判断状态
        String status = "";
        if( functionList!=null && functionList.size() > 0 ){
            if(functionList.contains(39)||functionList.contains(38)||functionList.contains(37)||functionList.contains(36)||functionList.contains(35)){
                status = " AND ( modelchoicename LIKE '%"+name+"%' OR (department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') OR (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%') OR (fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%') OR (zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%') OR (confirmperson LIKE '%"+name+"%') OR (implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%'))";
            } else {
                status = " AND 1=0";
            }
        }
        /*if( roleList.size() > 0 ){*/
            /*if( "市局办公室".equals(roleList.get(0)) ){
                status = " and ( (status != '办公室处理文件' and status != '办公室归档' and status != '处理处置') or (implementpersondelete like '%"+name+"%' ) )";
            }
            if( !"市局办公室".equals(roleList.get(0)) & !"分管领导".equals(roleList.get(0)) & !"主管领导".equals(roleList.get(0))){
                //status = " and modeltype != 'model_zhijeichuli' and modeltype != 'model_wenjianniban' and (status != '办公室处理文件') and ((status != '科室一签批' and department1persondelete not like '%"+name+"%') and (status != '科室二签批' and department2persondelete not like '%"+name+"%'))";
                status = " and ( ((status != '办公室处理文件' or status != '科室签批' and status != '处理处置') and (department1persondelete like '%"+name+"%'or department2persondelete like '%"+name+"%')) or (implementpersondelete like '%"+name+"%'))";
                //status = "and modeltype != 'model_zhijeichuli' and modeltype != 'model_wenjianniban' and(status != '办公室处理文件' and status != '科室签批')";
            }
            if( "分管领导".equals(roleList.get(0)) ){
                //status = " and (status != '办公室处理文件' and status != '科室一签批'and status != '科室二签批' and status != '分管领导签批') and fenguannamedelete not like '%"+name+"%'";
                status = " and ( (status != '办公室处理文件' and status != '科室签批' and status != '处理处置' and fenguannamedelete like '%"+name+"%') or (implementpersondelete like '%"+name+"%') )";
            }
            if( "主管领导".equals(roleList.get(0)) ){
                //status = " and (status != '办公室处理文件' and status != '科室一签批'and status != '科室二签批' and status != '分管领导签批' and status != '主管领导签批') and zhuguannamedelete not like '%"+name+"%'";
                status = " and ( (status != '办公室处理文件' and status != '科室签批' and status != '处理处置' and status != '分管领导签批' and zhuguannamedelete like '%"+name+"%') or (implementpersondelete like '%"+name+"%') )";
            }*/
            /*if( "市局办公室管理角色".equals(roleList.get(0)) ){
                status = " and ( (status != '办公室处理文件'and modelchoicename like  '%"+name+"%')or ( status = '结束' and confirmperson like '%"+name+"%') or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%' )or (status = '办公室归档' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%' )or (status = '结束' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%' ) or ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '分管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '主管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '处理处置' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '办公室归档' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '结束' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or (status = '主管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '处理处置' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '办公室归档' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '结束' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%') or (status = '处理处置' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '办公室归档' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '结束' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')  )";
            }
            if( !"市局办公室管理角色".equals(roleList.get(0)) & !"市局分管领导角色".equals(roleList.get(0)) & !"市局主管领导角色".equals(roleList.get(0))){
                status = " and ( ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '分管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '主管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '处理处置' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '办公室归档' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '结束' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%') or (status = '办公室归档' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%') or (status = '结束' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%')or (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' ) or (status = '主管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '处理处置' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '办公室归档' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '结束' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )  or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '处理处置' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '办公室归档' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '结束' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%') )";
                //status = "and ( status = '科室签批')";
            }
            if( "市局分管领导角色".equals(roleList.get(0)) ){
                status = " and ( ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '分管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '主管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '处理处置' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '办公室归档' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '结束' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%') or (status = '办公室归档' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%') or (status = '结束' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%')or (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' ) or (status = '主管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '处理处置' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '办公室归档' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '结束' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )  or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '处理处置' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '办公室归档' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '结束' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%') )";
            }
            if( "市局主管领导角色".equals(roleList.get(0)) ){
                status = " and ( ( status = '科室签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '分管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '主管领导签批' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '处理处置' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or ( status = '办公室归档' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) ) or ( status = '结束' and ((department1person like '%"+name+"%'and department1persondelete like '%"+name+"%') or (department2person like '%"+name+"%'and department2persondelete like '%"+name+"%')) )or (status = '处理处置' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%') or (status = '办公室归档' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%') or (status = '结束' and implementperson like '%"+name+"%' and implementpersondelete like '%"+name+"%')or (status = '分管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' ) or (status = '主管领导签批' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '处理处置' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '办公室归档' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )or (status = '结束' and fenguanname like '%"+name+"%' and fenguannamedelete like '%"+name+"%' )  or (status = '主管领导签批' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '处理处置' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '办公室归档' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%')or (status = '结束' and zhuguanname like '%"+name+"%' and zhuguannamedelete like '%"+name+"%') )";
            }
        }*/
        /*if( name != null ){
            try {
                name = URLDecoder.decode(name,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }*/
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
        String[] cols = {"receivefileid","year","type", "cometime",  "title","status"};
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
            sArray.add(" year like '%" + searchValue + "%'");
            sArray.add(" type like '%" + searchValue + "%'");
            sArray.add(" cometime like '%" + searchValue + "%'");
            sArray.add(" receivefileid like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
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

        List<ReceiveFile> tasks = new ArrayList<ReceiveFile>();
        if (conn != null) {
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where 1=1 "+status;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where  1=1 "+status;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }

            String searchSQL = "";
            String sql = "SELECT IFNULL(year,'')as year,IFNULL(type,'') as type,IFNULL(cometime,'') as cometime,IFNULL(receivefileid,'') as receivefileid,IFNULL(title,'') as title,(CASE WHEN STATUS='科室签批' OR STATUS='分管领导签批' OR STATUS = '主管领导签批' THEN '签批' ELSE STATUS END) as status FROM " + table + " where  1=1 "+status;
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
                tasks.add(new ReceiveFile (
                        rs.getString("year"),
                        rs.getString("type"),
                        rs.getString("cometime"),
                        rs.getString("receivefileid"),
                        rs.getString("title"),
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
