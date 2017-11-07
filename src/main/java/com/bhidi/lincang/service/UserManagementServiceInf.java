package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.RegisterInfo;
import com.bhidi.lincang.bean.UnitAndDepartments;

import java.util.List;
import java.util.Map;

public interface UserManagementServiceInf {
    List<String> getRoles();

    List<UnitAndDepartments> getUnitAndDepartments();

    Map<String,String> register(Map<String, Object> mapCondition);

    RegisterInfo getRegisterInfoById(int id);

    Map<String,String> update(Map<String, Object> mapCondition);
}
