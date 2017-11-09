package com.bhidi.lincang.controller;

import com.bhidi.lincang.bean.Privilege;
import com.bhidi.lincang.bean.User;
import com.bhidi.lincang.service.LoginServiceImp;
import com.google.gson.Gson;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/login",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(String username, String password, String login_auto_login,HttpSession session, ModelMap map, HttpServletResponse response){
        User user = loginServiceImp.queryUserByUsernameAndPass(username,password);
        if( user != null  ){
            if(user.getRoleList()!=null & user.getRoleList().size()>0){
                //先求出来roleid
                int roleid = loginServiceImp.getRoleid(user.getRoleList().get(0));
                //根据roleid查出来功能
                List<Integer> intList = loginServiceImp.getFunction(roleid);
                //查出来不属于他的功能
                List<Privilege> privilegeList = new ArrayList<Privilege>();
                if( intList!=null & intList.size()!=0){
                    privilegeList = loginServiceImp.getNotFunction(intList);
                }
                user.setPermissionList(privilegeList);
            }
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
                Cookie cookie = new Cookie("cookie_user",jsonUser);
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            }
            return "home";
        } else {
            map.put("msg","用户名或者密码不正确！");
            return "login";
        }
    }
    /*
     *注销的业务逻辑
     */
    @RequestMapping("/logout")
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
        cookie.setMaxAge(0);
        /*cookie.setPath("/");*/
        response.addCookie(cookie);
        session.invalidate();
        return "login";
    }
}
