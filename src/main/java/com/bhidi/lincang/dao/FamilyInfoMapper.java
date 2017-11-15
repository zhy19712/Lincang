package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.People;
import com.bhidi.lincang.bean.PeopleMore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyInfoMapper {
    List<People> BasicInfoOfFamily(String name);

    List<People> queryFamilyInfoByFid(String fid);

    PeopleMore queryFamilyDetailByFid(String fid);

    int deletePeople(String fid);
}
