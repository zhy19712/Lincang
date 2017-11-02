package com.bhidi.lincang.service;

import com.bhidi.lincang.dao.UserManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementServiceImp implements UserManagementServiceInf{
    @Autowired
    UserManagementMapper userManagementMapper;

    public List<String> getDepartment() {
        return userManagementMapper.selectDepartment();
    }
}
