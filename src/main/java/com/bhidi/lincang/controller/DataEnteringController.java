package com.bhidi.lincang.controller;

import com.bhidi.lincang.service.DataEnteringServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataEnteringController {

    @Autowired
    DataEnteringServiceImp dataEnteringServiceImp;

    @ResponseBody
    @RequestMapping(value="DataEntering",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String DataEntering( ){
        return "";
    }
}
