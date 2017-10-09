package com.bhidi.lincang.service;

import com.bhidi.lincang.dao.DataEnteringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataEnteringServiceImp implements DataEnteringServiceInf {
    @Autowired
    DataEnteringMapper dataEnteringMapper;

}
