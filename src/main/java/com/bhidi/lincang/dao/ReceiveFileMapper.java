package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.ReceiveFileAhead;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveFileMapper {
    int insert(ReceiveFileAhead rfaa);
}
