package com.bhidi.lincang;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("阿斯蒂芬"+"KQ"+sdf.format(date));
        System.out.println(0/7);

        System.out.println("1科室提意见.docx".split("\\.").length);
    }
}
