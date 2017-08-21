package com.bhidi.lincang.system;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhy19712 on 04/08/2017.
 */
public class DBUtils {

    public DBUtils(){}

    public static void Insert(String sql){
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int Query(String sql){
        Statement stmt = null;
        Connection conn = new DBConfig().getConn();
        ResultSet set = null;
        try {
            stmt = conn.createStatement();
            set = stmt.executeQuery(sql);

            conn.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int i = 0;

        try{
            i = set.getInt("id");
        }catch(Exception e){
            e.printStackTrace();
        }
        if(set ==null){
            return 0;
        }else {
            return i;
        }
    }
}

