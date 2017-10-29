package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;

import java.util.Map;

public interface ReceiveFileServiceInf {
    Map<String,Object> save(Map<String, Object> mapCondition);

    ReceiveFile getReceiveFileInfoById(String receivefileid);

    String getDepartmentNameByName(String department1);

    int updateReceiveFile(ReceiveFile rf);

    int insertModelErkeshi(Model_Erkeshi meme);

    int insertModelYikeshi(Model_Yikeshi meme);

    int insertModelWenjiannibandan(Model_Wenjianniban meme);

    int insertModelZhijiechuli(Model_Zhijiechuli meme);
}
