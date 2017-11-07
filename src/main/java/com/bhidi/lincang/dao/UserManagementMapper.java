package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.RegisterInfo;
import com.bhidi.lincang.bean.UnitAndDepartment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserManagementMapper {
    List<String> selectRoles();

    List<UnitAndDepartment> selectUnitAndDepartment();

    int selectDept(RegisterInfo ri);

    int saveUser(RegisterInfo ri);

    int selectRoleid(RegisterInfo ri);

    int insertUserRole(Map<String, Object> mapConditionCondition);
}
