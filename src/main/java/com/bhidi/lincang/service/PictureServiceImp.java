package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.*;
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

    public List<AnZhiKind> getAnZhi() {
        return pictureMapper.selectAnZhi();
    }

    public List<KuQuKind> getKuQu() {
        return pictureMapper.selectKuQu();
    }
}
