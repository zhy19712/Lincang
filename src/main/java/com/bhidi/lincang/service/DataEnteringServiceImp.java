package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.People;
import com.bhidi.lincang.dao.DataEnteringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataEnteringServiceImp implements DataEnteringServiceInf {
    @Autowired
    DataEnteringMapper dataEnteringMapper;


    public int savePerson(List<People> peopleList) {
        return dataEnteringMapper.insertPeople(peopleList);
    }
}
