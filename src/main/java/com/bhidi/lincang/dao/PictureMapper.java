package com.bhidi.lincang.dao;

import com.bhidi.lincang.bean.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper {
    List<PictureCounty> queryCountyInfo();

    List<PictureTown> queryTownInfo();

    List<PictureVillage> queryVillageInfo();

    int pictureSum();

    List<AnZhiKind> selectAnZhi();

    List<KuQuKind> selectKuQu();
}
