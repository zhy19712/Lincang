package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.dao.UserManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserManagementServiceImp implements UserManagementServiceInf{
    @Autowired

    UserManagementMapper userManagementMapper;

    public List<String> getRoles() {
        return userManagementMapper.selectRoles();
    }

    public List<UnitAndDepartments> getUnitAndDepartments() {
        List<UnitAndDepartments> list = new ArrayList<UnitAndDepartments>();
        //先获得所有的UnitAndDepartment
        List<UnitAndDepartment> unitAndDepartmentList = userManagementMapper.selectUnitAndDepartment();
        //单位的set
        Set<String> unitSet = new HashSet<String>();
        for(UnitAndDepartment uad:unitAndDepartmentList){
            unitSet.add(uad.getUnit());
        }
        for(String unit:unitSet){
            UnitAndDepartments uads = new UnitAndDepartments();
            uads.setUnit(unit);
            List<String> departmentList = new ArrayList<String>();
            for(UnitAndDepartment uad:unitAndDepartmentList){
                if(unit.equals(uad.getUnit())){
                    departmentList.add(uad.getDepartment());
                }
            }
            uads.setDepartmentList(departmentList);
            list.add(uads);
        }
        return list;
    }

    public Map<String, String> register(Map<String, Object> mapCondition) {
        RegisterInfo ri = (RegisterInfo)mapCondition.get("registerInfo");
        //先根据单位和部门来得到user表中的dept应该填什么
        int dept = userManagementMapper.selectDept(ri);
        ri.setDepartment(dept+"");
        //把用户存进去吧id拿出来
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ri.setCreated_at(sdf.format(now));
        int userid = userManagementMapper.saveUser(ri);
        //查出来对应的角色的id
        int roleid = userManagementMapper.selectRoleid(ri);
        //将两个id插入user_role表
        Map<String,Object> mapConditionCondition = new HashMap<String,Object>();
        mapConditionCondition.put("userid",ri.getId());
        mapConditionCondition.put("roleid",roleid);
        int user_role_result = userManagementMapper.insertUserRole(mapConditionCondition);
        //返回的map
        Map<String,String> mapResult = new HashMap<String, String>();
        if(userid == -1 | user_role_result == -1){
            mapResult.put("result","failure");
        } else {
            mapResult.put("result","success");
        }
        return mapResult;
    }

    public RegisterInfo getRegisterInfoById(int id) {
        return userManagementMapper.selectRegisterInfoById(id);
    }

    public Map<String, String> update(Map<String, Object> mapCondition) {
        RegisterInfo ri = (RegisterInfo)mapCondition.get("registerInfo");
        //先根据单位和部门来得到user表中的dept应该填什么
        int dept = userManagementMapper.selectDept(ri);
        ri.setDepartment(dept+"");
        //把用户修改
        int userid = userManagementMapper.updateUser(ri);
        //查出来对应的角色的id
        int roleid = userManagementMapper.selectRoleid(ri);
        //将两个id修改user_role表
        Map<String,Object> mapConditionCondition = new HashMap<String,Object>();
        mapConditionCondition.put("userid",ri.getId());
        mapConditionCondition.put("roleid",roleid);
        int user_role_result = userManagementMapper.updateUserRole(mapConditionCondition);
        //返回的map
        Map<String,String> mapResult = new HashMap<String, String>();
        if(userid == -1 | user_role_result == -1){
            mapResult.put("result","failure");
        } else {
            mapResult.put("result","success");
        }
        return mapResult;
    }

    public int deleteRegisterInfoById(int id) {
        return userManagementMapper.deleteRegisterInfoById(id);
    }

    public int deleteRole(int id) {
        return userManagementMapper.deleteRole(id);
    }

    public Role selectRole(int id) {
        return userManagementMapper.selectRole(id);
    }

    public int saveRole(Role rri) {
        return userManagementMapper.insertRole(rri);
    }

    public int saveRolePrivilege(List<RolePrivilege> rolePrivilege) {
        return userManagementMapper.insertRolePrivilege(rolePrivilege);
    }

    public int deleteRolePrivilege(int id) {
        return userManagementMapper.deleteRolePrivilege(id);
    }

    public int updateRoleById(Map<String, Object> mapCondition) {
        return userManagementMapper.updateRoleById(mapCondition);
    }

    public Role selectRoleByRoleName(String roleName) {
        return userManagementMapper.selectRoleByRoleName(roleName);
    }

    public User selectUserByUserName(String userName) {
        return userManagementMapper.selectUserByUserName(userName);
    }


}
