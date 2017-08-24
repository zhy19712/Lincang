package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.House;
import com.bhidi.lincang.bean.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/8/24.
 */
@Repository
public class ExcelDao {
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    /*
     *具名参数的单次插入
     */
    public int save(Move move){
        String sql = "insert into move (fid,from_city,from_district,from_town,from_village,from_group,from_remark,to_city,to_district,to_town,to_village,to_group,to_remark) values " +
                "(:fid,:from_city,:from_district,:from_town,:from_village,:from_group,:from_remark,:to_city,:to_district,:to_town,:to_village,:to_group,:to_remark)";
        return namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(move));
    }
    public int saveHouse(House house){
        String sql = "insert into house (fid,main_size,main_structure1,main_structure2,main_structure3,main_structure4,main_structure5,main_remark,sub_size,sub_structure1,sub_structure2,sub_structure3,sub_structure4,sub_structure5,sub_remark) values " +
                "(:fid,:main_size,:main_structure1,:main_structure2,:main_structure3,:main_structure4,:main_structure5,:main_remark,:sub_size,:sub_structure1,:sub_structure2,:sub_structure3,:sub_structure4,:sub_structure5,:sub_remark)";
        return namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(house));
    }
}
