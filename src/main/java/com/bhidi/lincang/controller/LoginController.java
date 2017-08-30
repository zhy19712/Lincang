package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.service.LoginServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by admin on 2017/8/28.
 */
@Controller
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;

    /*
     *跳转进入登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    /*
     *登录的业务逻辑
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String username, String password, String login_auto_login,HttpSession session, ModelMap map, HttpServletResponse response){
        User user = loginServiceImp.queryUserByUsername(username,password);
        if( user != null  ){
            session.setAttribute("user",user);
            if (login_auto_login != null && !login_auto_login.equals("")) {
                //如果前台点击了自动登录，就放在cookie里边
                Gson gson = new Gson();
                String jsonUser = gson.toJson(user);
                try {
                    jsonUser = URLEncoder.encode(jsonUser,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                /*System.out.println(jsonUser);*/
                Cookie cookie = new Cookie("cookie_user",jsonUser);
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            }
            return "indexTest";
        } else {
            map.put("msg","用户名或者密码不正确！");
            return "login";
        }
    }
    /*
     *注销的业务逻辑
     */
    @RequestMapping("/cancel")
    public String cancel(HttpSession session,HttpServletResponse response){
        String s = "消除！";
        Gson gson = new Gson();
        String sss = gson.toJson(s);
        try {
            sss = URLEncoder.encode(sss,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie=new Cookie("cookie_user",sss);
        cookie.setMaxAge(0); //立即删除型
        /*cookie.setPath("/");*///项目所有目录均有效，这句很关键，否则不敢保证删除
        response.addCookie(cookie); //重新写入，将覆盖之前的
        session.invalidate();
        return "indexTest";
    }
}
