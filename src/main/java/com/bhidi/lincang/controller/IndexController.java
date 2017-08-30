package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.User;
import com.google.gson.Gson;
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
public class IndexController {
    @RequestMapping(value = "/to_indexTest", method = RequestMethod.GET)
    public String to_index(@CookieValue(value="cookie_user",required=false)String cookie_user,HttpSession session,HttpServletRequest req){
/*        if( cookie_user != null ){
            System.out.println("cookie中去出来的"+cookie_user);
            try {
                cookie_user = URLDecoder.decode(cookie_user,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            User userFromJson = gson.fromJson(cookie_user,User.class);
            System.out.println("转"+userFromJson.getUsername());
            session.setAttribute("user",userFromJson);
        }*/
        Cookie[] cookies = req.getCookies();
        User user= null;
        if( cookies != null ){
            for(Cookie c :cookies){
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
                }
            }
        }
        return "indexTest";
    }
}
