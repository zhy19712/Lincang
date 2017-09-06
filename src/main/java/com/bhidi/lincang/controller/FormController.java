package com.bhidi.lincang.controller;

import com.bhidi.lincang.service.FormServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class FormController {

    @Autowired
    FormServiceImp formServiceImp;

    //保存的处理,保存但是不提交
    //保存是将信息仅仅进行保存，提交的话信息就在办公室那边看到了
    @RequestMapping(value="/saveFormData",method= RequestMethod.POST)
    public void saveFormData(){

    }
    //提交的处理
    @RequestMapping(value="/submitFormData",method= RequestMethod.POST)
    public void submitFormData(){

    }
}
