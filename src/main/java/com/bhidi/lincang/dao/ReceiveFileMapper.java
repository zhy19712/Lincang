package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveFileMapper {
    int insert(ReceiveFile rfaa);

    ReceiveFile selectReceiveFileInfoById(String receivefileid);

    String selectDepartmentNameByName(String department1);

    int updateReceiveFile(ReceiveFile rf);

    int insertModelErkeshi(Model_Erkeshi meme);

    int insertModelYikeshi(Model_Yikeshi meme);

    int insertModelWenjiannibandan(Model_Wenjianniban meme);

    int insertModelZhijiechuli(Model_Zhijiechuli meme);
}
