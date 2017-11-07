package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.DepartmentAndStaffs;
import com.bhidi.lincang.service.UserServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @ResponseBody
    @RequestMapping(value="/loginlogin",method= RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public void login(HttpServletRequest request){
        String TT = request.getParameter("username");
        System.out.println(TT);
    }

    /**
     *这个方法是收文管理部分的那个用户树，按照部门取出来用户的名字
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getDepartmentAndStaffs",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getDepartmentAndStaff(){
        List<DepartmentAndStaffs> resList=  userServiceImp.getDepartmentAndStaffs();
        String result = new Gson().toJson(resList);
        return result;
    }
}
