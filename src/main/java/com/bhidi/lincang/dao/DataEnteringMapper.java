package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataEnteringMapper {
    int insertPeople(List peopleList);

    int insertBank(Bank bank);

    int insertHouse(House house);

    int insertIncome(List<Income> incomeList);

    int insertOutcome(List<Outcome> outcomeList);

    int insertMove(Move move);
}
