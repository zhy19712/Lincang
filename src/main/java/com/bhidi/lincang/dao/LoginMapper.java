package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface LoginMapper {
    User queryUserByUsername(Map<String,String> map);
}
