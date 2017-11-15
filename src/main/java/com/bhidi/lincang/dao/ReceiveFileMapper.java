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

    Model_Zhijiechuli getInfoFromZhijiechuli(String receivefileid);

    Model_Wenjianniban getInfoFromWenjianniban(String receivefileid);

    Model_Yikeshi getInfoFromYikeshi(String receivefileid);

    Model_Erkeshi getInfoFromErkeshi(String receivefileid);

    int updateModelZhijiechuli(Model_Zhijiechuli meme);

    int updateModelWenjiannibandan(Model_Wenjianniban meme);

    int updateModelYikeshi(Model_Yikeshi meme);

    int updateModelErkeshi(Model_Erkeshi meme);

    String selectLastReceiveFileId();

    int deleteReceiveFile(String receivefileid);

    int deleteModelZhiJieChuLi(String receivefileid);

    int deleteModelWenJianNiBan(String receivefileid);

    int deleteModelYiKeShi(String receivefileid);

    int deleteModelErKeShi(String receivefileid);
}
