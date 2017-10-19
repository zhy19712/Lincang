package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.CapitalFlow;
import com.bhidi.lincang.system.DBConfig;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class CapitalApplyFormController {
    /**
     * 资金申请的datatables
     * @param request
     * @param userstatus
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @RequestMapping(value="/capitalFlowForm",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String SubmittedForm(HttpServletRequest request,
                                @RequestParam(value="userstatus",required=false)String userstatus) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        String table = "CAPITALFLOW";
        String initiatorclass = "";
        if( "1".equals(userstatus) || "2".equals(userstatus) ){
            initiatorclass = "1";
        } else {
            initiatorclass = userstatus;
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
        String[] cols = {"id", "title","create_time", "report_person","initiatorclass","status"};
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
            sArray.add(" id like '%" + searchValue + "%'");
            sArray.add(" title like '%" + searchValue + "%'");
            sArray.add(" create_time like '%" + searchValue + "%'");
            sArray.add(" report_person like '%" + searchValue + "%'");
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
            String recordsFilteredSql = "select count(1) as recordsFiltered from " + table + " where initiatorclass ="+initiatorclass;
            stmt = conn.createStatement();
            //获取数据库总记录数
            String recordsTotalSql = "select count(1) as recordsTotal from " + table + " where initiatorclass ="+initiatorclass;
            rs = stmt.executeQuery(recordsTotalSql);
            while (rs.next()) {
                recordsTotal = rs.getString("recordsTotal");
            }
            String searchSQL = "";
            String sql = "SELECT IFNULL(id,'')as id,IFNULL(create_time,'')as create_time,IFNULL(report_person,'')as report_person,IFNULL(initiatorclass,'')as initiatorclass,IFNULL(title,'')as title,IFNULL(status,'')as status FROM " + table + " where initiatorclass ="+initiatorclass;
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
                tasks.add(new CapitalFlow(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("create_time"),
                        rs.getString("report_person"),
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
}
