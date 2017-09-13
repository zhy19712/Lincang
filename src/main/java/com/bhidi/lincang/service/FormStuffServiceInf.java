package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.F_Stuff;

public interface FormStuffServiceInf {
    Integer saveFormStuff(F_Stuff f_stuff);

    Integer submittedFormStuff(F_Stuff f_stuff);

    F_Stuff queryStuffById(int id);

    int deleteStuffById(int id);

    Integer updateFormStuff(F_Stuff f_stuff);
}
