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
        User u = userManagementServiceImp.selectUserByUserName(ri.getUsername());
        Map<String,String> mapResult = new HashMap<String, String>();
        if(u==null){
            mapResult.put("result","rename");
        } else {
            Map<String,Object> mapCondition = new HashMap<String,Object>();
            mapCondition.put("registerInfo",ri);
            mapResult= userManagementServiceImp.register(mapCondition);
        }
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
    public String registerRole(String role,@RequestParam(value="functionList[]",required = false) int[] functionList){
        //判断角色名字是否唯一
        Role ro = userManagementServiceImp.selectRoleByRoleName(role);
        Map<String,String> resultMap = new HashMap<String, String>();
        if(ro == null){
            resultMap.put("result","rename");
        } else {
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
            int b = 0;
            if(functionList!=null){
                for(int i = 0;i < functionList.length;i++){
                    RolePrivilege rp = new RolePrivilege();
                    rp.setRoleid(r.getId());
                    rp.setAuthorithid(functionList[i]);
                    rolePrivilege.add(rp);
                }
                try {
                    b = userManagementServiceImp.saveRolePrivilege(rolePrivilege);
                } catch (Exception e) {
                    e.printStackTrace();
                    b = -1;
                }
            }
            //Map<String,String> resultMap = new HashMap<String, String>();
            if(a == -1 | b == -1){
                resultMap.put("result","failure");
            } else {
                resultMap.put("result","success");
            }
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
     * @param id
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateRole",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateRole(int id,String role,@RequestParam(value="functionList[]",required = false) int[] functionList){
        Map<String,Object> mapCondition = new HashMap<String,Object>();
        List<RolePrivilege> rolePrivilege = new ArrayList<RolePrivilege>();
        mapCondition.put("id",id);
        mapCondition.put("role",role);
        int updateRoleResult = 0;
        try {
            updateRoleResult = userManagementServiceImp.updateRoleById(mapCondition);
        } catch (Exception e) {
            e.printStackTrace();
            updateRoleResult =-1;
        }
        int deleteRolePrivilegeResult = 0;
        try {
            deleteRolePrivilegeResult = userManagementServiceImp.deleteRolePrivilege(id);
        } catch (Exception e) {
            e.printStackTrace();
            deleteRolePrivilegeResult =-1;
        }
        int b = 0;
        if(functionList!=null){
            for(int i = 0;i < functionList.length;i++){
                RolePrivilege rp = new RolePrivilege();
                rp.setRoleid(id);
                rp.setAuthorithid(functionList[i]);
                rolePrivilege.add(rp);
            }
            try {
                b = userManagementServiceImp.saveRolePrivilege(rolePrivilege);
            } catch (Exception e) {
                e.printStackTrace();
                b = -1;
            }
        }
        Map<String,String> resultMap = new HashMap<String, String>();
        if(updateRoleResult == -1 | b == -1 | deleteRolePrivilegeResult==-1){
            resultMap.put("result","failure");
        } else {
            resultMap.put("result","success");
        }
        String result = new Gson().toJson(resultMap);
        return result;
    }
    /**
     * 删除角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteRole",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteRole(int id){
        int roleDeleteResult = 0;
        int roleprivilegeDeleteResult = 0;
        try {
            roleDeleteResult = userManagementServiceImp.deleteRole(id);
        } catch (Exception e) {
            e.printStackTrace();
            roleDeleteResult = -1;
        }
        try {
            roleprivilegeDeleteResult = userManagementServiceImp.deleteRolePrivilege(id);
        } catch (Exception e) {
            e.printStackTrace();
            roleprivilegeDeleteResult =-1;
        }
        Map<String,String> resultMap = new HashMap<String, String>();
        if(roleDeleteResult == -1 | roleprivilegeDeleteResult == -1){
            resultMap.put("result","failure");
        } else {
            resultMap.put("result","success");
        }
        String result = new Gson().toJson(resultMap);
        return result;
    }
    /**
     * 角色名字唯一
     * @param roleName
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/booleanRoleName",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String booleanRoleName(String roleName){
        Role a = userManagementServiceImp.selectRoleByRoleName(roleName);
        Map<String,Object> mapResult = new HashMap<String,Object>();
        mapResult.put("result",a==null?"恭喜您，角色名字可用！":"该角色名字已被占用");
        String result = new Gson().toJson(mapResult);
        return result;
    }
    /**
     * 用户账号唯一
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/booleanUserName",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String booleanUserName(String userName){
        User a = userManagementServiceImp.selectUserByUserName(userName);
        Map<String,Object> mapResult = new HashMap<String,Object>();
        mapResult.put("result",a==null?"恭喜您，账户名可用！":"该账户名已被占用");
        String result = new Gson().toJson(mapResult);
        return result;
    }

}
