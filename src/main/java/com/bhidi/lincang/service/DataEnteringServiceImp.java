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

    public int deletePerson(String fid) {
        return dataEnteringMapper.deletePeople(fid);
    }

    public int saveMove(Move move) {
        return dataEnteringMapper.insertMove(move);
    }

    public int changeMove(Move move) {
        return dataEnteringMapper.updateMove(move);
    }

    public int deleteMove(String fid) {
        return dataEnteringMapper.deleteMove(fid);
    }

    public int saveBank(Bank bank) {
        return dataEnteringMapper.insertBank(bank);
    }

    public int changeBank(Bank bank) {
        return dataEnteringMapper.updateBank(bank);
    }

    public int deleteBank(String fid) {
        return dataEnteringMapper.deleteBank(fid);
    }

    public int saveHouse(House house) {
        return dataEnteringMapper.insertHouse(house);
    }

    public int changeHouse(House house) {
        return dataEnteringMapper.updateHouse(house);
    }

    public int deleteHouse(String fid) {
        return dataEnteringMapper.deleteHouse(fid);
    }

    public int saveIncome(List<Income> incomeList) {
        return dataEnteringMapper.insertIncome(incomeList);
    }

    public int deleteIncome(String fid) {
        return dataEnteringMapper.deleteIncome(fid);
    }

    public int saveOutcome(List<Outcome> outcomeList) {
        return dataEnteringMapper.insertOutcome(outcomeList);
    }

    public int deleteOutcome(String fid) {
        return dataEnteringMapper.deleteOutcome(fid);
    }
}
