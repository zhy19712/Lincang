package com.bhidi.lincang.service;

import com.bhidi.lincang.dao.CapitalFlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CapitalFlowServiceImp implements CapitalFlowServiceInf {
    @Autowired
    CapitalFlowMapper capitalFlowMapper;

    public int submitData(Map map){
        return capitalFlowMapper.submitData(map);
    }
}
