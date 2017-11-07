package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.RegisterInfo;
import com.bhidi.lincang.bean.UnitAndDepartments;
import com.bhidi.lincang.bean.UserManagement;
import com.bhidi.lincang.service.UserManagementServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagementController {
    @Autowired
    UserManagementServiceImp userManagementServiceImp;
    /**
     * 获取所有的角色名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getRoles",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getRoles(){
        //查询出所有的用户和相对应的角色
        List<String> roleList=  userManagementServiceImp.getRoles();
        String result = new Gson().toJson(roleList);
        return result;
    }
    /**
     * 获取所有的单位名称和对应的部门的名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getUnitAndDepartments",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getUnits(){
        List<UnitAndDepartments> unitAndDepartmentsList=  userManagementServiceImp.getUnitAndDepartments();
        String result = new Gson().toJson(unitAndDepartmentsList);
        return result;
    }

    /**
     * 注册新用户的提交
     * @param ri
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/registerUser",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String registerUser(RegisterInfo ri){
        Map<String,Object> mapCondition = new HashMap<String,Object>();
        mapCondition.put("registerInfo",ri);
        Map<String,String> mapResult= userManagementServiceImp.register(mapCondition);

        String result = new Gson().toJson(mapResult);
        return result;
    }

}
