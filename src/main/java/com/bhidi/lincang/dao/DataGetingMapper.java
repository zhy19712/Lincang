package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataGetingMapper {

    Bank selectBankInfoByFid(String fid);

    House selectHouseInfoByFid(String fid);

    List<Income> selectIncomeInfoByFid(String fid);

    List<Outcome> selectOutcomeInfosByFid(String fid);

    Move selectMoveInfoByFid(String fid);

    List<People> selectPeopleInfosByFid(String fid);
}
