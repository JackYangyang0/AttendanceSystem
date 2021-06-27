package com.cjj.attendance.interceptor;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *  程序异常处理
 *  跳转到错误界面
 */

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    ModelAndView exceptionHandler(Throwable e, Model model){
        model.addAttribute("message" , "程序有错误啦，请稍后再试！");
        return new ModelAndView("error");
    }

}
