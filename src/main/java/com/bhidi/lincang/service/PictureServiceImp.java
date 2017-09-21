package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.PictureCounty;
import com.bhidi.lincang.bean.PictureTown;
import com.bhidi.lincang.bean.PictureVillage;
import com.bhidi.lincang.dao.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImp implements PictureServiceInf {
    @Autowired
    PictureMapper pictureMapper;

    @Override
    public List<PictureCounty> queryCountyInfo() {
        return pictureMapper.queryCountyInfo();
    }

    @Override
    public List<PictureTown> queryTownInfo() {
        return pictureMapper.queryTownInfo();
    }

    @Override
    public List<PictureVillage> queryVillageInfo() {
        return pictureMapper.queryVillageInfo();
    }
}
