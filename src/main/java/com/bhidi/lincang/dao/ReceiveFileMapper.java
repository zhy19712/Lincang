package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.ReceiveFile;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveFileMapper {
    int insert(ReceiveFile rfaa);
}
