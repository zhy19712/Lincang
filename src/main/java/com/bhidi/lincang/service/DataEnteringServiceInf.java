package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;

import java.util.List;

public interface DataEnteringServiceInf {
    int savePerson(List<People> peopleList);

    int deletePerson(String fid);

    int saveMove(Move move);

    int deleteMove(String fid);

    int changeMove(Move move);

    int saveBank(Bank bank);

    int deleteBank(String fid);

    int changeBank(Bank bank);

    int saveHouse(House house);

    int deleteHouse(String fid);

    int changeHouse(House house);

    int saveIncome(List<Income> incomeList);

    int deleteIncome(String fid);

    int saveOutcome(List<Outcome> outcomeList);

    int deleteOutcome(String fid);

}
