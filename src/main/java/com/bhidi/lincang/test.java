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

        List<String> list = new ArrayList<String>();
        System.out.println(list.get(0));
    }
}
