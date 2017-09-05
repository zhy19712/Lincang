package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.User;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/8/28.
 */
public interface LoginServiceInf {
    User queryUserByUsernameAndPass(String username,String password);
}
