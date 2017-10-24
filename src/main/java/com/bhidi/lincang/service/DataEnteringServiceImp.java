package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.dao.DataEnteringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataEnteringServiceImp implements DataEnteringServiceInf {
    @Autowired
    DataEnteringMapper dataEnteringMapper;


    public int savePerson(List<People> peopleList) {
        return dataEnteringMapper.insertPeople(peopleList);
    }

    public int saveBank(Bank bank) {
        return dataEnteringMapper.insertBank(bank);
    }

    public int saveHouse(House house) {
        return dataEnteringMapper.insertHouse(house);
    }

    public int saveIncome(List<Income> incomeList) {
        return dataEnteringMapper.insertIncome(incomeList);
    }

    public int saveOutcome(List<Outcome> outcomeList) {
        return dataEnteringMapper.insertOutcome(outcomeList);
    }

    public int saveMove(Move move) {
        return dataEnteringMapper.insertMove(move);
    }
}
