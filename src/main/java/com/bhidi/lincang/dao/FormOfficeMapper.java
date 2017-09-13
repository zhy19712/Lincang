package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.F_Stuff;
import org.springframework.stereotype.Repository;

@Repository
public interface FormOfficeMapper {
    Integer stuffToOffice(F_Stuff f_stuff);

    Integer updateFormOffice(F_Stuff f_stuff);
}
