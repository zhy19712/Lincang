package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataEnteringMapper {
    int insertPeople(List peopleList);

    int deletePeople(String fid);

    int insertMove(Move move);

    int deleteMove(String fid);

    int updateMove(Move move);

    int insertBank(Bank bank);

    int updateBank(Bank bank);

    int deleteBank(String fid);

    int insertHouse(House house);

    int updateHouse(House house);

    int deleteHouse(String fid);

    int insertIncome(List<Income> incomeList);

    int deleteIncome(String fid);

    int insertOutcome(List<Outcome> outcomeList);

    int deleteOutcome(String fid);

}
