package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.CapitalFlow;

import java.util.Map;

public interface CapitalFlowServiceInf {
    int submitData(Map map);

    CapitalFlow getDataById(String id);


    int setDataById(Map<String, String> map);
}
