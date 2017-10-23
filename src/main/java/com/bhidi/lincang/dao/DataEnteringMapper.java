package com.bhidi.lincang.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataEnteringMapper {
    int insertPeople(List peopleList);
}
