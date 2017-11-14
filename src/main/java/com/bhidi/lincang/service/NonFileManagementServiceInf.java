package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.NonFileManagement;

import java.util.Map;

public interface NonFileManagementServiceInf {
    Map<String,Object> submit(Map<String, Object> mapCondition);

    int updateNonFileManagement(Map<String, Object> mapCondition);

    NonFileManagement getNonFileManagementInfoByNonFileId(String nonfileid);

    int deleteNonFile(String nonfileid);
}
