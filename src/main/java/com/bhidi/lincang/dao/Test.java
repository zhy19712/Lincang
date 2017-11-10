package com.bhidi.lincang.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/23.
 */
public class Test {
    private static ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    public static void main(String[] args)throws Exception{
/*        System.out.println(ioc);
        DataSource bean = (DataSource) ioc.getBean("dataSource");
        Connection connection = bean.getConnection();
        System.out.println(connection);
        connection.close();*/
        /*List<String> list = new ArrayList<String>();
        if( list == null ){
            System.out.println("null");
        }
        System.out.println("".equals(null));*/
/*
        String path= "E:\\GitRepository\\Lincang\\out\\artifacts\\Lincang_war_exploded\\upload\\receivefile\\1-1509117536062.txt";
        File file =  new File(path);
        file.delete();
        System.out.println(file.getName());
        System.out.println(file.length());
*/

System.out.println("asd".length());
    }

}
