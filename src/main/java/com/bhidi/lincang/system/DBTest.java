package com.bhidi.lincang.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhy19712 on 01/08/2017.
 */
public class DBTest {

    static String sql = null;
    static ResultSet ret = null;
    static Statement stmt = null;




    public static void main(String[] args) {
        sql = "select * from FORM_ORIGIN";//SQL语句
        Connection conn = new DBConfig().getConn();

        try {
            stmt = conn.createStatement();

            ret = stmt.executeQuery(sql);//执行语句，得到结果集
            while (ret.next()) {
                String TITLE = ret.getString(8);
                String CREATED_AT = ret.getString(2);
                String OID = ret.getString(1);
                System.out.println(TITLE + "\t" + CREATED_AT + "\t" + OID);
            }//显示数据
            ret.close();
            conn.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


