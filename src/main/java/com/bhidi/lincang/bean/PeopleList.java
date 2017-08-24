package com.bhidi.lincang.bean;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/21.
 */
public class PeopleList {
    private List<People> peopleList;

    public PeopleList() {
        this.peopleList = new ArrayList<People>();
    }

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> peopleList) {

        this.peopleList = peopleList;
    }
}
