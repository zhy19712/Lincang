package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.Table_info;
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
public class TableAdd {
    @ResponseBody
    @RequestMapping(value="/FamilyInfoAdd",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String NewForm(HttpServletRequest request) throws SQLException {
            ResultSet rs = null;
            Statement stmt = null;
            Connection conn = new DBConfig().getConn();
            String table = "PEOPLE";
           /* if( fid != null ){
                try {
                    fid = URLDecoder.decode(fid ,"utf-8");
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
            String[] cols = {"FID","TABLE_TYPE","NAME", "RESERVOIR", "TO_DISTRICT","INTERVIEWER","CREATED_AT"};
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
                sArray.add(" b.account_name like '%" + searchValue + "%'");
                sArray.add(" b.bank_name like '%" + searchValue + "%'");
                sArray.add(" b.account_number like '%" + searchValue + "%'");

                sArray.add(" h.main_size like '%" + searchValue + "%'");
                sArray.add(" h.main_structure1 like '%" + searchValue + "%'");
                sArray.add(" h.main_structure2 like '%" + searchValue + "%'");
                sArray.add(" h.main_structure3 like '%" + searchValue + "%'");
                sArray.add(" h.main_structure4 like '%" + searchValue + "%'");
                sArray.add(" h.main_structure5 like '%" + searchValue + "%'");
                sArray.add(" h.main_remark like '%" + searchValue + "%'");
                sArray.add(" h.sub_size like '%" + searchValue + "%'");
                sArray.add(" h.sub_structure1 like '%" + searchValue + "%'");
                sArray.add(" h.sub_structure2 like '%" + searchValue + "%'");
                sArray.add(" h.sub_structure3 like '%" + searchValue + "%'");
                sArray.add(" h.sub_structure4 like '%" + searchValue + "%'");
                sArray.add(" h.sub_structure5 like '%" + searchValue + "%'");
                sArray.add(" h.sub_remark like '%" + searchValue + "%'");

                sArray.add(" i.INCOME_SOURCE like '%" + searchValue + "%'");
                sArray.add(" i.INCOME_CATE like '%" + searchValue + "%'");
                sArray.add(" i.INCOME_QUANTITY like '%" + searchValue + "%'");
                sArray.add(" i.INCOME_UNIT like '%" + searchValue + "%'");
                sArray.add(" i.INCOME_SUM like '%" + searchValue + "%'");
                sArray.add(" i.REMARK like '%" + searchValue + "%'");

                sArray.add(" o.OUTCOME_SOURCE like '%" + searchValue + "%'");
                sArray.add(" o.OUTCOME_CATE like '%" + searchValue + "%'");
                sArray.add(" o.OUTCOME_QUANTITY like '%" + searchValue + "%'");
                sArray.add(" o.OUTCOME_UNIT like '%" + searchValue + "%'");
                sArray.add(" o.OUTCOME_SUM like '%" + searchValue + "%'");
                sArray.add(" o.REMARK like '%" + searchValue + "%'");

                sArray.add(" m.FROM_CITY like '%" + searchValue + "%'");
                sArray.add(" m.FROM_DISTRICT like '%" + searchValue + "%'");
                sArray.add(" m.FROM_TOWN like '%" + searchValue + "%'");
                sArray.add(" m.FROM_VILLAGE like '%" + searchValue + "%'");
                sArray.add(" m.FROM_GROUP like '%" + searchValue + "%'");
                sArray.add(" m.FROM_REMARK like '%" + searchValue + "%'");
                sArray.add(" m.TO_CITY like '%" + searchValue + "%'");
                sArray.add(" m.TO_DISTRICT like '%" + searchValue + "%'");
                sArray.add(" m.TO_TOWN like '%" + searchValue + "%'");
                sArray.add(" m.TO_VILLAGE like '%" + searchValue + "%'");
                sArray.add(" m.TO_GROUP like '%" + searchValue + "%'");
                sArray.add(" m.TO_REMARK like '%" + searchValue + "%'");

                sArray.add(" p.RESERVOIR like '%" + searchValue + "%'");
                sArray.add(" p.LOCATION like '%" + searchValue + "%'");
                sArray.add(" p.NAME like '%" + searchValue + "%'");
                sArray.add(" p.MASTER like '%" + searchValue + "%'");
                sArray.add(" p.PID like '%" + searchValue + "%'");
                sArray.add(" p.GENDER like '%" + searchValue + "%'");
                sArray.add(" p.RACE like '%" + searchValue + "%'");
                sArray.add(" p.PHONE like '%" + searchValue + "%'");
                sArray.add(" p.RELATION like '%" + searchValue + "%'");
                sArray.add(" p.EDUCATION like '%" + searchValue + "%'");
                sArray.add(" p.PROFESSION like '%" + searchValue + "%'");
                sArray.add(" p.HOME_SIZE like '%" + searchValue + "%'");
                sArray.add(" p.IMM_NUM like '%" + searchValue + "%'");
                sArray.add(" p.PROP like '%" + searchValue + "%'");
                sArray.add(" p.POOR_REASON like '%" + searchValue + "%'");
                sArray.add(" p.INTERVIEWER like '%" + searchValue + "%'");
                sArray.add(" p.INTERVIEWEE like '%" + searchValue + "%'");
                sArray.add(" p.CREATED_AT like '%" + searchValue + "%'");
                sArray.add(" p.TABLE_TYPE like '%" + searchValue + "%'");

                /*sArray.add(" p.FID like '%" + searchValue + "%'");
                sArray.add(" p.TABLE_TYPE like '%" + searchValue + "%'");
                sArray.add(" p.NAME like '%" + searchValue + "%'");
                sArray.add(" p.RESERVOIR like '%" + searchValue + "%'");
                sArray.add(" m.TO_DISTRICT like '%" + searchValue + "%'");
                sArray.add(" p.INTERVIEWER like '%" + searchValue + "%'");
                sArray.add(" p.CREATED_AT like '%" + searchValue + "%'");*/
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
                String recordsFilteredSql = "select count(DISTINCT p.FID) as recordsFiltered from " + table+" p,move m,bank b,house h,income i,outcome o where p.fid = m.fid and p.fid = b.fid and p.fid = h.fid and p.fid = i.fid and p.fid = o.fid and p.MASTER = 1";
                stmt = conn.createStatement();
                //获取数据库总记录数
                String recordsTotalSql = "select count(DISTINCT p.FID) as recordsTotal from " + table+" p,move m,bank b,house h,income i,outcome o where p.fid = m.fid and p.fid = b.fid and p.fid = h.fid and p.fid = i.fid and p.fid = o.fid and p.MASTER = 1";
                rs = stmt.executeQuery(recordsTotalSql);
                while (rs.next()) {
                    recordsTotal = rs.getString("recordsTotal");
                }

                String searchSQL = "";
                String sql = "SELECT DISTINCT IFNULL(p.FID,'') as FID,IFNULL(p.TABLE_TYPE,'')as TABLE_TYPE,IFNULL(p.NAME,'')as NAME,IFNULL(p.RESERVOIR,'')as RESERVOIR,IFNULL(m.TO_DISTRICT,'')as TO_DISTRICT,IFNULL(p.INTERVIEWER,'')as INTERVIEWER,IFNULL(p.CREATED_AT,'')as CREATED_AT FROM " + table +" p,move m,bank b,house h,income i,outcome o where p.fid = m.fid and p.fid = b.fid and p.fid = h.fid and p.fid = i.fid and p.fid = o.fid and p.MASTER = 1";
                if (individualSearch != "") {
                    searchSQL = " and p.FID IN"+"("+ "SELECT p.fid FROM people p,move m,bank b,house h,income i,outcome o WHERE  p.fid = m.fid and p.fid = b.fid and p.fid = h.fid and p.fid = i.fid and p.fid = o.fid and" + "("+individualSearch+")"+")";
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
                                rs.getString("TO_DISTRICT"),
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
}
