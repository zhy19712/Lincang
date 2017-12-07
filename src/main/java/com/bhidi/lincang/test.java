package com.bhidi.lincang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        /*System.out.println("阿斯蒂芬"+"KQ"+sdf.format(date));
        System.out.println(0/7);*/

        /*List<String> list = new ArrayList<String>();
        System.out.println(list.get(0));*/
        /*String s = "G:\\git\\nihao\\最终---最终表";
        System.out.println(s.split("-").length);*/
        String str ="BQ20171207113644录入成功！";
        System.out.println(str.substring(0,16));
    }
}
