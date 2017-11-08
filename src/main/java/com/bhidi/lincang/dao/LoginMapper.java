package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.Privilege;
import com.bhidi.lincang.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoginMapper {
    User queryUserByUsername(Map<String,String> map);

    int selectRoleid(String rolename);

    List<Integer> selectFunction(int roleid);

    List<Privilege> selectNotFunction(List<Integer> intList);
}
