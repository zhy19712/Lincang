package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.SendFile;

import java.util.Map;

public interface SendFileServiceInf {
    SendFile getSendFileInfoBySendFileId(String sendFileid);

    int updateSendFile(Map<String,Object> mapCondition);
}
