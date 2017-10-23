package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.Bank;
import com.bhidi.lincang.bean.House;
import com.bhidi.lincang.bean.Income;
import com.bhidi.lincang.bean.People;

import java.util.List;

public interface DataEnteringServiceInf {
    int savePerson(List<People> peopleList);

    int saveBank(Bank bank);

    int saveHouse(House house);

    int saveIncome(List<Income> incomeList);
}
