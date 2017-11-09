package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.SendFile;
import org.springframework.stereotype.Repository;

@Repository
public interface SendFileMapper {
    int insert(SendFile sfa);

    SendFile selectSendFileInfoBySendFileId(String sendFileid);

    int updateSendFile(SendFile sf);

    String selectLastSendFileId();
}
