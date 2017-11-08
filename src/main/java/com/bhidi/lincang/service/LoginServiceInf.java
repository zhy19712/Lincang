package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.Privilege;
import com.bhidi.lincang.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/28.
 */
public interface LoginServiceInf {
    User queryUserByUsernameAndPass(String username,String password);

    int getRoleid(String rolename);

    List<Integer> getFunction(int roleid);

    List<Privilege> getNotFunction(List<Integer> intList);
}
