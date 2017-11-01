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

    public List<PictureCounty> queryCountyInfo() {
        return pictureMapper.queryCountyInfo();
    }

    public List<PictureTown> queryTownInfo() {
        return pictureMapper.queryTownInfo();
    }

    public List<PictureVillage> queryVillageInfo() {
        return pictureMapper.queryVillageInfo();
    }

    public int pictureSum() {
        return pictureMapper.pictureSum();
    }
}
