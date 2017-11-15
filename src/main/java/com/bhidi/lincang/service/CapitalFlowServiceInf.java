package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.CapitalFlow;

import java.util.Map;

public interface CapitalFlowServiceInf {
    /*int submitData(Map map);*/

    CapitalFlow getCapitalDataById(String id);


    int setCatipalDataById(Map<String,Object> map);

    Map<String,Object> saveCapitalFlow(Map<String, Object> mapCondition);

    Map<String,Object> shiJuSubmit(Map<String, Object> mapCondition);

    Map<String,Object> quxianSaveCapitalFlow(Map<String, Object> mapCondition);

    int deleteCapitalFlow(String capitalflowid);
}
