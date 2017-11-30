package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.CapitalFlow;
import com.bhidi.lincang.bean.DepartmentAndStaff;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CapitalFlowMapper {
    int submitData(CapitalFlow cf);

    CapitalFlow queryCapitalDataById(String id);

    int updateCatipalDataById(Map<String,Object> map);

    String selectLastCapitalFlowId();

    int updateCapitalDataByCapitalFlow(CapitalFlow cf);

    int deleteCapitalFlow(String capitalflowid);

    int selectNumOfUnReadCapitalFlow(String name);

    List<DepartmentAndStaff> selectDepartmentAndStaff();
}
