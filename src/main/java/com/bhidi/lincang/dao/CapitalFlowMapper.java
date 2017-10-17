package com.bhidi.lincang.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CapitalFlowMapper {
    int submitData(Map map);
}
