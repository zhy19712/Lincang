package com.bhidi.lincang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @ResponseBody
    @RequestMapping(value="/loginlogin",method= RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public void login(HttpServletRequest request){
        String TT = request.getParameter("username");
        System.out.println(TT);
    }
}