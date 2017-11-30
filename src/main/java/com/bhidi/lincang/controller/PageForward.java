package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.User;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by admin on 2017/8/28.
 */

@Controller
public class PageForward {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String to_home(HttpSession session,HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        User user = null;
        if( cookies != null) {
            for (Cookie c : cookies) {
                String str = "";
                if (c.getName() != null && c.getName().equals("cookie_user")) {
                    str = c.getValue();
                }
                if (!str.equals("")) {
                    try {
                        str = URLDecoder.decode(str, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();
                    user = gson.fromJson(str, User.class);
                    session.setAttribute("user", user);
                    return "home";
                }
            }
        }
        return "login";
    }

    @RequestMapping(value = "/tohome", method = RequestMethod.GET)
    public String to_home(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "home";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/lincang-yimin", method = RequestMethod.GET)
    public String to_yimin(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "lincang-yimin";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/oa", method = RequestMethod.GET)
    public String to_oa(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "oa";
        } else {
            return "login";
        }
    }


    @RequestMapping(value = "/shouwen", method = RequestMethod.GET)
    public String to_money(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "shouwen";
        } else {
            return "login";
        }
        /*return "yimin_temp";*/
    }

    @RequestMapping(value = "/tofeiwenjianguanli", method = RequestMethod.GET)
    public String tofeiwenjianguanli(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "feiwenjianguanli";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/zhunianbuchang", method = RequestMethod.GET)
    public String tozhunianbuchang(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "zhunianbuchang";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/yiimindengji", method = RequestMethod.GET)
    public String lincangyimin(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "yiimindengji";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/kuquanzhi", method = RequestMethod.GET)
    public String kuquanzhi(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "kuquanzhi";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/yiminbanqian", method = RequestMethod.GET)
    public String yiminbanqian(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "yiminbanqian";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/anzhi_detail", method = RequestMethod.GET)
    public String anzhi_detail(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "anzhi_detail";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/banqian_detail", method = RequestMethod.GET)
    public String banqian_detail(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "banqian_detail";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public String msg(HttpSession session){
        User user = (User)session.getAttribute("user");
        if( user != null ){
            return "msg";
        } else {
            return "login";
        }
    }
}
