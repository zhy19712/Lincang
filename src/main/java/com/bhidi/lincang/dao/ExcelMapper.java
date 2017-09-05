package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelMapper {
     /*
     *查询该fid对应的用户在数据库中是否存在
     */
    String queryPeopleByFid(String fid);
    /*
     * 插入银行信息
     */
    Integer saveBank(Bank bank);
    /*
     * 批量插入家庭人口情况
     */
    Integer batchSavePeople(List<People> peopleList);
    /*
     * 插入搬迁信息
     */
    Integer saveMove(Move move);
    /*
     * 插入住房信息
     */
    Integer saveHouse(House house);
    /*
     * 批量插入收入情况
     */
    Integer batchSaveIncome(List<Income> listIncome);
    /*
     * 批量插入支出情况
     */
    Integer batchSaveOutcome(List<Outcome> listOutcome);



















}
