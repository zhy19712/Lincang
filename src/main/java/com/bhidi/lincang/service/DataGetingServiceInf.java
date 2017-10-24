package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;

import java.util.List;

public interface DataGetingServiceInf {
    Bank getBankInfoByFid(String fid);

    House getHouseInfoByFid(String fid);

    List<Income> getIncomeInfosByFid(String fid);

    List<Outcome> getOutcomeInfosByFid(String fid);

    Move getMoveInfoByFid(String fid);

    List<People> getPeopleInfosByFid(String fid);
}
