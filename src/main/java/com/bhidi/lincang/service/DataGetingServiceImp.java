package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;
import com.bhidi.lincang.dao.DataGetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataGetingServiceImp implements DataGetingServiceInf{

    @Autowired
    DataGetingMapper dataGetingMapper;

    public Bank getBankInfoByFid(String fid) {
        return dataGetingMapper.selectBankInfoByFid(fid);
    }

    public House getHouseInfoByFid(String fid) {
        return dataGetingMapper.selectHouseInfoByFid(fid);
    }

    public List<Income> getIncomeInfosByFid(String fid) {
        return dataGetingMapper.selectIncomeInfoByFid(fid);
    }

    public List<Outcome> getOutcomeInfosByFid(String fid) {
        return dataGetingMapper.selectOutcomeInfosByFid(fid);
    }

    public Move getMoveInfoByFid(String fid) {
        return dataGetingMapper.selectMoveInfoByFid(fid);
    }

    public List<People> getPeopleInfosByFid(String fid) {
        return dataGetingMapper.selectPeopleInfosByFid(fid);
    }
}
