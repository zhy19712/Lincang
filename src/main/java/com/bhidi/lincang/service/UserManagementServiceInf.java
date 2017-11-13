package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;

import java.util.List;
import java.util.Map;

public interface UserManagementServiceInf {
    List<String> getRoles();

    List<UnitAndDepartments> getUnitAndDepartments();

    Map<String,String> register(Map<String, Object> mapCondition);

    RegisterInfo getRegisterInfoById(int id);

    Map<String,String> update(Map<String, Object> mapCondition);

    int deleteRegisterInfoById(int id);

    int deleteRole(int id);

    Role selectRole(int id);

    int saveRole(Role rri);

    int saveRolePrivilege(List<RolePrivilege> rolePrivilege);

    int deleteRolePrivilege(int id);

    int updateRoleById(Map<String, Object> mapCondition);


}
