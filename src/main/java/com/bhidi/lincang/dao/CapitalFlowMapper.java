package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.CapitalFlow;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CapitalFlowMapper {
    int submitData(CapitalFlow cf);

    CapitalFlow queryCapitalDataById(String id);

    int updateCatipalDataById(Map<String,Object> map);

    String selectLastCapitalFlowId();

    int updateCapitalDataByCapitalFlow(CapitalFlow cf);
}
