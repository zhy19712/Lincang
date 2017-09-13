package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.F_Stuff;
import org.springframework.stereotype.Repository;
@Repository
public interface FormStuffMapper {

    Integer saveFormStuff(F_Stuff f_stuff);

    Integer submittedFormStuff(F_Stuff f_stuff);

    F_Stuff queryStuffById(int id);

    int deleteStuffById(int id);

    Integer updateFormStuff(F_Stuff f_stuff);
}
