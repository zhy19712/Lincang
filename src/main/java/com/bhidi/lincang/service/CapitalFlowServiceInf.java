package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.CapitalFlow;

import java.util.Map;

public interface CapitalFlowServiceInf {
    /*int submitData(Map map);*/

    CapitalFlow getCatipalDataById(String id);


    int setCatipalDataById(Map<String, String> map);

    Map<String,Object> saveCapitalFlow(Map<String, Object> mapCondition);
}
