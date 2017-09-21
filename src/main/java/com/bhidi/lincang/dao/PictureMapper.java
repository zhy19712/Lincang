package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.PictureCounty;
import com.bhidi.lincang.bean.PictureTown;
import com.bhidi.lincang.bean.PictureVillage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper {
    List<PictureCounty> queryCountyInfo();

    List<PictureTown> queryTownInfo();

    List<PictureVillage> queryVillageInfo();
}
