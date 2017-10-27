package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.ReceiveFile;

import java.util.Map;

public interface ReceiveFileServiceInf {
    Map<String,Object> save(Map<String, Object> mapCondition);

    ReceiveFile getReceiveFileInfoById(String receivefileid);
}
