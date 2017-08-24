package com.bhidi.lincang.system;


import com.bhidi.lincang.bean.People;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by zhy19712 on 04/08/2017.
 */
public class DBUtils {

    public DBUtils(){}

    /**
     * 单个插入
     * @param sql
     */
    public static void insert(String sql){
        Connection conn = new DBConfig().getConn();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 批量插入
     * @param
     */
    public static void batchInsert(List<People> listPeople){
        Connection conn = new DBConfig().getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into people(fid,reservoir,location,`name`,master,pid,gender,race,relation,education,profession,home_size,imm_num,interviewer,interviewee,created_at) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            for (People peo:listPeople) {
                ps.setObject(1, peo.getFid());
                ps.setObject(2, peo.getReservoir());
                ps.setObject(3, peo.getLocation());
                ps.setObject(4, peo.getName());
                ps.setObject(5, peo.getMaster());
                ps.setObject(6, peo.getPid());
                ps.setObject(7, peo.getGender());
                ps.setObject(8, peo.getRace());
                ps.setObject(9, peo.getRelation());
                ps.setObject(10, peo.getEducation());
                ps.setObject(11, peo.getProfession());
                ps.setObject(12, peo.getHome_size());
                ps.setObject(13, peo.getImm_num());
                ps.setObject(14, peo.getInterviewer());
                ps.setObject(15, peo.getInterviewee());
                ps.setObject(16, new Date());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据fid来判断是否进行插入操作
     * @param sql
     */
    public static int queryFid(String sql) {
        Connection conn = new DBConfig().getConn();
        Statement stmt = null;
        ResultSet set = null;
        String fid = "";
        try {
            stmt = conn.createStatement();
            set = stmt.executeQuery(sql);
            while( set.next() ){
                fid = set.getString("fid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if( "".equals(fid) ){
            return 0;
        } else {
            return 1;
        }
    }
}

