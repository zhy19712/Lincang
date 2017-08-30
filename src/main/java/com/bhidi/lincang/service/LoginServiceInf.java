package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.User;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/8/28.
 */
@Service
public interface LoginServiceInf {
    User queryUserByUsername(String username,String password);
}
