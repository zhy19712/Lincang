package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.Privilege;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/28.
 */
@Service
public class LoginServiceImp implements LoginServiceInf{

    @Autowired
    LoginMapper loginMapper;

    public User queryUserByUsernameAndPass(String username,String password) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("username",username);
        map.put("password",password);
        return loginMapper.queryUserByUsername(map);
    }

    public int getRoleid(String rolename) {
        return loginMapper.selectRoleid(rolename);
    }

    public List<Integer> getFunction(int roleid) {
        return loginMapper.selectFunction(roleid);
    }

    public List<Privilege> getNotFunction(List<Integer> intList) {
        return loginMapper.selectNotFunction(intList);
    }

}