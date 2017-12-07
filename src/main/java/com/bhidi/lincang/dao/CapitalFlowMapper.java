package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.CapitalFlow;
import com.bhidi.lincang.bean.DepartmentAndStaff;
import com.bhidi.lincang.bean.QuXianReceiveMessage;
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

    int setQuXianReceiveMessage(List<QuXianReceiveMessage> list);

    QuXianReceiveMessage getQuXianReceiveMessage(Map<String,Object> mapCondition);

    CapitalFlow getNotice(String capitalflowid);

    int updateQuXianReceiveMessage(Map<String,Object> mapCondition);

    int updateCapitalFlow(Map<String, Object> mapCondition);

    int deleteMessage(String capitalflowid);
}
