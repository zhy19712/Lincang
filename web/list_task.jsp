<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.bhidi.lincang.bean.FamilyInfoByName" %>
<%@ page import="com.bhidi.lincang.system.DBConfig" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map"%>
<%@ page import="com.bhidi.lincang.bean.FamilyInfoByName" %>
<%--
  Created by IntelliJ IDEA.
  User: zhy19712
  Date: 2017/8/2
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    ResultSet rs = null;
    Statement stmt = null;
    Connection conn = new DBConfig().getConn();
    String table = "PEOPLE";

    //获取参数
    String name1 = "";
    String name = "";
    name1 = request.getParameter("name");
    name = java.net.URLDecoder.decode(name1,"utf-8");
    if( "null".equals(name) ){
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("data", null);
        info.put("recordsTotal", 0);
        info.put("recordsFiltered", "");
        info.put("draw", "0");
        String json = new Gson().toJson(info);
        out.write(json);
    }else {
        System.out.println(name);
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
    String[] cols = {"FID","NAME", "PROP", "HOME_SIZE", "IMM_NUM","RESERVOIR"};
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
        sArray.add(" p.FID like '%" + searchValue + "%'");
        sArray.add(" NAME like '%" + searchValue + "%'");
        sArray.add(" PROP like '%" + searchValue + "%'");
        sArray.add(" HOME_SIZE like '%" + searchValue + "%'");
        sArray.add(" IMM_NUM like '%" + searchValue + "%'");
        sArray.add(" RESERVOIR like '%" + searchValue + "%'");
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

    List<FamilyInfoByName> tasks = new ArrayList<FamilyInfoByName>();
    if (conn != null) {
                /*String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where STATUS = 'NEW'";*/
        String recordsFilteredSql = "SELECT count(p.NAME) as recordsFiltered FROM people p INNER JOIN move m ON p.`FID` = m.`FID` WHERE m.FROM_DISTRICT = '"+name+"' AND p.`MASTER` = '1'";
        stmt = conn.createStatement();
        //获取数据库总记录数
                /*String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where STATUS = 'NEW'";*/
        String recordsTotalSql = "SELECT count(p.NAME) as recordsTotal FROM people p INNER JOIN move m ON p.`FID` = m.`FID` WHERE m.FROM_DISTRICT = '"+name+"' AND p.`MASTER` = '1'";
        rs = stmt.executeQuery(recordsTotalSql);
        while (rs.next()) {
            recordsTotal = rs.getString("recordsTotal");
        }

        String searchSQL = "";
                /*String sql = "SELECT * FROM " + table + " where STATUS = 'NEW'";*/
        String sql = "SELECT IFNULL(p.FID,'')as FID,IFNULL(p.NAME,'')as NAME,IFNULL(p.PROP,'')as PROP,IFNULL(p.HOME_SIZE,'')as HOME_SIZE,IFNULL(p.IMM_NUM,'')as IMM_NUM,IFNULL(p.RESERVOIR,'')as RESERVOIR FROM people p INNER JOIN move m ON p.`FID` = m.`FID` WHERE m.FROM_DISTRICT = '"+ name +"' AND p.`MASTER` = '1'";
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
            tasks.add(new FamilyInfoByName (
                    rs.getString("FID"),
                    rs.getString("NAME"),
                    rs.getInt("PROP"),
                    rs.getInt("HOME_SIZE"),
                    rs.getInt("IMM_NUM"),
                    rs.getString("RESERVOIR")));
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
    out.write(json);
%>