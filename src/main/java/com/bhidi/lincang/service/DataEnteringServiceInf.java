package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;

import java.util.List;

public interface DataEnteringServiceInf {
    int savePerson(List<People> peopleList);

    int saveBank(Bank bank);

    int saveHouse(House house);

    int saveIncome(List<Income> incomeList);

    int saveOutcome(List<Outcome> outcomeList);

    int saveMove(Move move);

    int deletePerson(String fid);

    int changeMove(Move move);

    int changeBank(Bank bank);

    int changeHouse(House house);

    int deleteIncome(String fid);

    int deleteOutcome(String fid);
}
