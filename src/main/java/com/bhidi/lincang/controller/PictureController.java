package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.PictureCounty;
import com.bhidi.lincang.bean.PictureFull;
import com.bhidi.lincang.bean.PictureTown;
import com.bhidi.lincang.bean.PictureVillage;
import com.bhidi.lincang.service.PictureServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PictureController {

    @Autowired
    PictureServiceImp pictureServiceImp;
    /**
     * 处理前台地图上边的数据
     */
    @ResponseBody
    @RequestMapping(value="/picture",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String pictureInfo(ModelMap map){
        List<PictureFull> listPictureResult = new ArrayList<PictureFull>();
        //查询县级别的数据
        List<PictureCounty> listPictureCounty = pictureServiceImp.queryCountyInfo();
        for(PictureCounty p: listPictureCounty){
            p.setLevel("县级单位！");
        }
        //查询镇级别的数据
        List<PictureTown> listPictureTown = pictureServiceImp.queryTownInfo();
        for(PictureTown p: listPictureTown){
            p.setLevel("镇级单位！");
        }
        //查询村级别的数据
        List<PictureVillage> listPictureVillage = pictureServiceImp.queryVillageInfo();
        for(PictureVillage p: listPictureVillage){
            p.setLevel("村级单位！");
        }
        if(listPictureCounty.size() > 0){
            //遍历县级别的数据
            for(int i = 0; i < listPictureCounty.size();i++){
                //建立一个对象来存储一个整体的数据
                PictureFull pictureFull = new PictureFull();
                pictureFull.setName(listPictureCounty.get(i).getCountyName());
                pictureFull.setLevel(listPictureCounty.get(i).getLevel());
                pictureFull.setNum(listPictureCounty.get(i).getNum());

                List<PictureFull> listTown = new ArrayList<PictureFull>();
                for(int j = 0; j < listPictureTown.size();j++){
                    if( listPictureCounty.get(i).getCountyName().equals( listPictureTown.get(j).getCountyName() ) ){
                        //设置一个镇级别数据的集合来装县对应的镇级别的所有数据
                        PictureFull pictureFullTowm = new PictureFull();
                        pictureFullTowm.setName(listPictureTown.get(j).getTownName());
                        pictureFullTowm.setLevel(listPictureTown.get(j).getLevel());
                        pictureFullTowm.setNum(listPictureTown.get(j).getNum());
                        List<PictureFull> listVillage = new ArrayList<PictureFull>();
                        for(int k = 0; k < listPictureVillage.size();k++){
                            if( listPictureTown.get(j).getTownName().equals( listPictureVillage.get(k).getTownName() ) ){
                                PictureFull pictureFullVillage = new PictureFull();
                                pictureFullVillage.setName(listPictureVillage.get(k).getVillageName());
                                pictureFullVillage.setLevel(listPictureVillage.get(k).getLevel());
                                pictureFullVillage.setNum(listPictureVillage.get(k).getNum());
                                listVillage.add(pictureFullVillage);
                            }

                        }
                        pictureFullTowm.setListChild(listVillage);
                        listTown.add(pictureFullTowm);
                    }
                }
                //这个集合的属性是需要去遍历镇级别的数据
                pictureFull.setListChild(listTown);
                //将对象添加进集合
                listPictureResult.add(pictureFull);
                map.put("result", listPictureResult);
            }
        } else {
            map.put("result", "数据库中没有信息！");
        }
        Gson gson = new Gson();
        String result = gson.toJson(map);
        System.out.println(result);
        return result;
    }
}
