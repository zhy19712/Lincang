package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.DepartmentAndStaff;
import com.bhidi.lincang.bean.DepartmentAndStaffs;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserServiceInf{
    @Autowired
    UserMapper userMapper;

    public List<DepartmentAndStaffs> getDepartmentAndStaffs() {
        List<DepartmentAndStaffs> list = new ArrayList<DepartmentAndStaffs>();
        List<DepartmentAndStaff> departmentAndStaffList = userMapper.selectDepartmentAndStaff();
        Set<String> departmentSet = new HashSet<String>();
        for(DepartmentAndStaff das:departmentAndStaffList){
            departmentSet.add(das.getDepartment());
        }
        for(String ds:departmentSet){
            DepartmentAndStaffs dass = new DepartmentAndStaffs();
            List<String> staffList = new ArrayList<String>();
            dass.setDepartment(ds);
            for(DepartmentAndStaff das:departmentAndStaffList){
                if(ds.equals(das.getDepartment())){
                    staffList.add(das.getName());
                }
            }
            dass.setStaffList(staffList);
            list.add(dass);
        }
        return list;
    }
}
