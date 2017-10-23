package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.Bank;
import com.bhidi.lincang.bean.House;
import com.bhidi.lincang.bean.Income;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataEnteringMapper {
    int insertPeople(List peopleList);

    int insertBank(Bank bank);

    int insertHouse(House house);

    int insertIncome(List<Income> incomeList);
}
