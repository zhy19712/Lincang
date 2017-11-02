package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.UserManagement;
import com.bhidi.lincang.service.UserManagementServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserManagementController {
    @Autowired
    UserManagementServiceImp userManagementServiceImp;
    /**
     * 获取所有的部门
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getDepartment",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getDepartment(){
        //查询出所有的用户和相对应的角色
        List<String> departmentList=  userManagementServiceImp.getDepartment();
        String result = new Gson().toJson(departmentList);
        return result;
    }
    @ResponseBody
    @RequestMapping(value="/getDepartment",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String setUserInfo(UserManagement um){
        return "";
    }
}
