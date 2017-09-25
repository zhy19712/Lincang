package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.People;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyInfoMapper {
    List<People> BasicInfoOfFamily(String name);

    List<People> queryFamilyInfoByFid(String fid);
}
