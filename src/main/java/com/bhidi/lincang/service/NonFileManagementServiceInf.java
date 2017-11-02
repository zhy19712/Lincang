package com.bhidi.lincang.service;

import java.util.Map;

public interface NonFileManagementServiceInf {
    Map<String,Object> submit(Map<String, Object> mapCondition);

    int updateNonFileManagement(Map<String, Object> mapCondition);
}
