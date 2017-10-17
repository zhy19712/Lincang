package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.CapitalFlow;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CapitalFlowMapper {
    int submitData(Map map);

    CapitalFlow queryCatipalDataById(String id);

    int updateCatipalDataById(Map<String, String> map);
}
