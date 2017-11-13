package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.service.LoginServiceImp;
import com.bhidi.lincang.service.UserManagementServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagementController {
    @Autowired
    UserManagementServiceImp userManagementServiceImp;
    @Autowired
    LoginServiceImp loginServiceImp;
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
    /**
     * 查看
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getRegisterInfoById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getRegisterInfoById(int id){
        RegisterInfo ri = userManagementServiceImp.getRegisterInfoById(id);
        String result = new Gson().toJson(ri);
        return result;
    }
    /**
     * 编辑之后的提交
     * @param ri
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateRegisterInfoById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateRegisterInfoById(RegisterInfo ri){
        Map<String,Object> mapCondition = new HashMap<String,Object>();
        mapCondition.put("registerInfo",ri);
        Map<String,String> mapResult= userManagementServiceImp.update(mapCondition);
        String result = new Gson().toJson(mapResult);
        return result;
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteRegisterInfoById",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteRegisterInfoById(int id){
        int a = userManagementServiceImp.deleteRegisterInfoById(id);
        String result = new Gson().toJson(a);
        return result;
    }
    /**
     * 注册新角色
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/registerRole",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String registerRole(String role,@RequestParam(value="functionList[]") int[] functionList){
        Role r = new Role();
        r.setRolename(role);
        List<RolePrivilege> rolePrivilege = new ArrayList<RolePrivilege>();
        //先存储角色，获得id
        int a = 0;
        try {
            a = userManagementServiceImp.saveRole(r);
        } catch (Exception e) {
            e.printStackTrace();
            a = -1;
        }
        for(int i = 0;i < functionList.length;i++){
            RolePrivilege rp = new RolePrivilege();
            rp.setRoleid(r.getId());
            rp.setAuthorithid(functionList[i]);
            rolePrivilege.add(rp);
        }
        int b = 0;
        try {
            b = userManagementServiceImp.saveRolePrivilege(rolePrivilege);
        } catch (Exception e) {
            e.printStackTrace();
            b = -1;
        }

        Map<String,String> resultMap = new HashMap<String, String>();
        if(a == -1 | b == -1){
            resultMap.put("result","failure");
        } else {
            resultMap.put("result","success");
        }
        String result = new Gson().toJson(resultMap);
        return result;
    }
    /**
     * 查看角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectRole",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String selectRole(int id){
        Role a = userManagementServiceImp.selectRole(id);
        //查到这个角色对应的功能
        List<Integer> intList = loginServiceImp.getFunction(a.getId());
        Map<String,Object> mapResult = new HashMap<String,Object>();
        mapResult.put("rolename",a==null?"":a.getRolename());
        mapResult.put("functionList",intList);
        String result = new Gson().toJson(mapResult);
        return result;
    }

    /**
     * 编辑角色
     * @param rri
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateRole",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateRole(RegisterRoleInfo rri){
        System.out.println("1");
        /*int a = userManagementServiceImp.deleteRole(id);
        String result = new Gson().toJson(a);
        return result;*/
        return "";
    }
    /**
     * 删除角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteRole",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteRole(int id){
        int a = userManagementServiceImp.deleteRole(id);
        String result = new Gson().toJson(a);
        return result;
    }
}
