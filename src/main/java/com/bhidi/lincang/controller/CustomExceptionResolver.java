package com.bhidi.lincang.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//全局异常处理器
@ControllerAdvice
public class CustomExceptionResolver {

    /*//系统抛出的异常
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        //handler就是处理器适配器要执行的Handler对象(只有method)
        //解析出异常类型。
        //如果该异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示。
        String message = "";
        if(ex instanceof MaxUploadSizeExceededException){
            message = "文件总大小不应该超出200M！";
            request.getSession().setAttribute("fileuploadexception",message);
        }else{
            message = "未知错误";
        }

        ModelAndView modelAndView=new ModelAndView();

        //将错误信息传到页面
        modelAndView.addObject("message",message);

        //指向到错误界面
        modelAndView.setViewName("error");

        return modelAndView;
    }*/
    @ResponseBody
    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    public String uploadFile(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","上传文件不允许超出200MB！");
        String result = new Gson().toJson(map);
        return result;
    }
    @ExceptionHandler(value = {Exception.class})
    public ModelAndView exce(Exception e){
        e.printStackTrace();
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception",e);
        return mav;
    }
}
