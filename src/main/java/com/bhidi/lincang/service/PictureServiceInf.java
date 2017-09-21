package com.bhidi.lincang.service;

import com.bhidi.lincang.bean.PictureCounty;
import com.bhidi.lincang.bean.PictureTown;
import com.bhidi.lincang.bean.PictureVillage;

import java.util.List;

public interface PictureServiceInf {
    List<PictureCounty> queryCountyInfo();

    List<PictureTown> queryTownInfo();

    List<PictureVillage> queryVillageInfo();
}
