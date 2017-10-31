package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.DepartmentAndStaff;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserServiceInf{
    @Autowired
    UserMapper userMapper;

    public List<DepartmentAndStaff> getDepartmentAndStaff() {
        List<User> userList = userMapper.selectDepartmentAndStaff();
        Set<String> departmentSet = new HashSet<String>();
        List<String> departmentList = new ArrayList<String>();
        List<DepartmentAndStaff> result = new ArrayList<DepartmentAndStaff>();
        for( int i = 0 ; i < userList.size(); i++){
            departmentSet.add(userList.get(i).getRoleList().get(0));
        }
        for (String str : departmentSet) {
            departmentList.add(str);
        }
        for( int d = 0;d < departmentList.size();d++){
            DepartmentAndStaff das = new DepartmentAndStaff();
            das.setDepartmentName(departmentList.get(d));
            List<String> staffList = new ArrayList<String>();
            for( int s=0;s < userList.size();s++){
                if( departmentList.get(d).equals( userList.get(s).getRoleList().get(0) ) ){
                    staffList.add(userList.get(s).getName());
                }
            }
            das.setStaffList(staffList);
            result.add(das);
        }
        return result;
    }
}
