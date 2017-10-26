package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.ReceiveFileAhead;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ReceiveFileController {

    @ResponseBody
    @RequestMapping(value="/reveiceFileRegistration",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String reveiceFileRegistration(HttpSession session, ReceiveFileAhead rfa){
        //获取当前用户
        /*User user = (com.bhidi.lincang.bean.User)session.getAttribute("user");
*/
        System.out.println(rfa.getYear());
        return "";
    }

}
