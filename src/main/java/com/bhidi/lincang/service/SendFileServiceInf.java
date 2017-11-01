package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.SendFile;

public interface SendFileServiceInf {
    SendFile getSendFileInfoBySendFileId(String sendFileid);

    int updateSendFile(SendFile sf);
}
