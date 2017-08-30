package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2017/8/24.
 */
@Repository
public class ExcelDao {
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /*
     *查询该fid对应的用户在数据库中是否存在
     */
    public String queryPeopleByFid(String fid){

        String sql = "select fid from people where fid = :fid ORDER BY id LIMIT 1";
        String fidResult = null;
        try {
            fidResult = namedJdbcTemplate.queryForObject(sql, Collections.singletonMap("fid", fid),String.class);
        } catch (EmptyResultDataAccessException e) {
            return "";
        }
        return fidResult;
    }
    /*
     *插入银行信息
     */
    public int saveBank(Bank bank){
        String sql = "insert into bank (fid,account_name,bank_name,account_number) values " +
                "(:fid,:account_name,:bank_name,:account_number)";
        return namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(bank));
    }

    /*
     * 批量插入用户情况
     */
    public int[] batchSavePeople(List<People> pl){
        SqlParameterSource[] s = new SqlParameterSource[pl.size()];
        for( int i = 0 ; i <  pl.size();i++ ){
            s[i] = new BeanPropertySqlParameterSource(pl.get(i) );
        }
        String sql = "insert into people(fid,reservoir,location,name,master,pid,gender,race,relation,education,profession,home_size,imm_num,interviewer,interviewee,created_at) " +
                "values(:fid,:reservoir,:location,:name,:master,:pid,:gender,:race,:relation,:education,:profession,:home_size,:imm_num,:interviewer,:interviewee,:created_at)";
        return namedJdbcTemplate.batchUpdate(sql,s);
    }

    /*
     *插入搬迁信息
     */
    public int saveMove(Move move){
        String sql = "insert into move (fid,from_city,from_district,from_town,from_village,from_group,from_remark,to_city,to_district,to_town,to_village,to_group,to_remark) values " +
                "(:fid,:from_city,:from_district,:from_town,:from_village,:from_group,:from_remark,:to_city,:to_district,:to_town,:to_village,:to_group,:to_remark)";
        return namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(move));
    }
    /*
     *插入住房信息
     */
    public int saveHouse(House house){
        String sql = "insert into house (fid,main_size,main_structure1,main_structure2,main_structure3,main_structure4,main_structure5,main_remark,sub_size,sub_structure1,sub_structure2,sub_structure3,sub_structure4,sub_structure5,sub_remark) values " +
                "(:fid,:main_size,:main_structure1,:main_structure2,:main_structure3,:main_structure4,:main_structure5,:main_remark,:sub_size,:sub_structure1,:sub_structure2,:sub_structure3,:sub_structure4,:sub_structure5,:sub_remark)";
        return namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(house));
    }


    /*
     * 批量插入收入情况
     */
    public int[] saveIncome(List<SqlParameterSource> listIncome){
        SqlParameterSource[] s = new SqlParameterSource[listIncome.size()];
        for( int i = 0 ; i <  listIncome.size();i++){
            s[i] = listIncome.get(i);
        }
        String sql = "insert into income (fid,income_source,income_cate,income_quantity,income_unit,income_sum,remark) values " +
                "(:fid,:income_source,:income_cate,:income_quantity,:income_unit,:income_sum,:remark)";

        return namedJdbcTemplate.batchUpdate(sql,s);
    }
    /*
     * 批量插入支出情况
     */
    public int[] saveOutcome(List<SqlParameterSource> listOutcome){
        SqlParameterSource[] s = new SqlParameterSource[listOutcome.size()];
        for( int i = 0 ; i <  listOutcome.size();i++){
            s[i] = listOutcome.get(i);
        }
        String sql = "insert into outcome (fid,outcome_source,outcome_cate,outcome_quantity,outcome_unit,outcome_sum,remark) values " +
                "(:fid,:outcome_source,:outcome_cate,:outcome_quantity,:outcome_unit,:outcome_sum,:remark)";

        return namedJdbcTemplate.batchUpdate(sql,s);
    }
}
