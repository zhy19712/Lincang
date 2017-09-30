package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.People;
import com.bhidi.lincang.bean.PeopleMore;

import java.util.List;

public interface FamilyInfoServiceInf {
    List<People> BasicInfoOfFamily(String name);

    List<People> queryFamilyInfoByFid(String fid);

    PeopleMore queryFamilyDetailByFid(String fid);
}
