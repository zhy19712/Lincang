package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.NonFileManagement;
import org.springframework.stereotype.Repository;

@Repository
public interface NonFileManagementMapper {
    int insert(NonFileManagement nfm);

    NonFileManagement selectNonFileManagementInfoByNonFileId(String nonfileid);

    int updateNonFileManagement(NonFileManagement nfm);


}
