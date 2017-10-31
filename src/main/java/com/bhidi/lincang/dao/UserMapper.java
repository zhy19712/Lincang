package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> selectDepartmentAndStaff();
}
