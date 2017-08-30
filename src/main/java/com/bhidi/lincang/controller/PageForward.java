package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.User;
import com.google.gson.Gson;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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


    @RequestMapping(value = "/oa", method = RequestMethod.GET)
    public String to_oa(){
        return "oa";
    }

    @RequestMapping(value = "/yimin", method = RequestMethod.GET)
    public String to_yimin(){
        return "yimin";
    }
}
