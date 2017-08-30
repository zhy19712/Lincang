package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/8/28.
 */
@Service
public class LoginServiceImp implements LoginServiceInf{
    @Autowired
    UserDao userDao;

    public User queryUserByUsername(String username,String password) {
        return userDao.queryUserByUsername(username,password);
    }
}
