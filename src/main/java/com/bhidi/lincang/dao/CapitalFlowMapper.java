package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.CapitalFlow;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CapitalFlowMapper {
    int submitData(CapitalFlow cf);

    CapitalFlow queryCatipalDataById(String id);

    int updateCatipalDataById(Map<String, String> map);

    String selectLastCapitalFlowId();
}
