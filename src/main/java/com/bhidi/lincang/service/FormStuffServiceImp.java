package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.F_Stuff;
import com.bhidi.lincang.dao.FormStuffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormStuffServiceImp implements FormStuffServiceInf {
    @Autowired
    FormStuffMapper formStuffMapper;

    @Override
    public Integer saveFormStuff(F_Stuff f_stuff) {
        return formStuffMapper.saveFormStuff(f_stuff);
    }

    @Override
    public Integer submittedFormStuff(F_Stuff f_stuff) {
        return formStuffMapper.submittedFormStuff(f_stuff);
    }

    @Override
    public F_Stuff queryStuffByOid(String oid) {
        return formStuffMapper.queryStuffByOid(oid);
    }

    @Override
    public int deleteStuffByOid(String oid) {
        return formStuffMapper.deleteStuffByOid(oid);
    }
}
