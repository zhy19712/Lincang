package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

/**
 * Created by admin on 2017/8/28.
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;


    /*
     *查询用户信息
     */
    public User queryUserByUsername(String username,String password){
        String sql="select * from user where username= ? and pass = ?";
        Object[] params = new Object[] { username,password };
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, params, new RowMapper<User>() {
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setUid(rs.getString("uid"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("pass"));
                    user.setName(rs.getString("name"));
                    user.setDept(rs.getString("dept"));
                    user.setPhone1(rs.getString("phone1"));
                    user.setPhone2(rs.getString("phone2"));
                    user.setLevel(rs.getInt("level"));
                    user.setCreated_at(rs.getString("created_at"));
                    return user;
                }
            });
        } catch (DataAccessException e) {
            System.out.println("数据库没有该用户！");
        }
        return user;
    }
}
