package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.F_Stuff;
import com.bhidi.lincang.dao.FormOfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class FormOfficeServiceImp implements FormOfficeServiceInf {
    @Autowired
    FormOfficeMapper formOfficeMapper;

    public Integer stuffToOffice(F_Stuff f_stuff) {
        return formOfficeMapper.stuffToOffice(f_stuff);
    }

    public Integer updateFormOffice(F_Stuff f_stuff) {
        return formOfficeMapper.updateFormOffice(f_stuff);
    }
}
