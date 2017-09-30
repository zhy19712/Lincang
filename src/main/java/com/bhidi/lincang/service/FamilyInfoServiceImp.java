package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.People;
import com.bhidi.lincang.bean.PeopleMore;
import com.bhidi.lincang.dao.FamilyInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyInfoServiceImp implements FamilyInfoServiceInf{
    @Autowired
    FamilyInfoMapper familyInfoMapper;
    public List<People> BasicInfoOfFamily(String name) {
        return familyInfoMapper.BasicInfoOfFamily(name);
    }

    public List<People> queryFamilyInfoByFid(String fid) {
        return familyInfoMapper.queryFamilyInfoByFid(fid);
    }

    public PeopleMore queryFamilyDetailByFid(String fid) {
        return familyInfoMapper.queryFamilyDetailByFid(fid);
    }
}
