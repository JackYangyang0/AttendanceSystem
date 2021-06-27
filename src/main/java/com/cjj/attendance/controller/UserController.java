package com.cjj.attendance.controller;

import com.cjj.attendance.entity.ResultKit;
import com.cjj.attendance.service.UserService;
import com.cjj.attendance.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username")String username ,
                              @RequestParam("password")String password,
                              @RequestParam("root") String root,
                               HttpServletRequest request , HttpSession session){
        ModelAndView mav = new ModelAndView();
        boolean login = false;
//        System.out.println(username + password + root);
        if(!StringUtils.isEmptyOrWhitespace(username) && !StringUtils.isEmptyOrWhitespace(password)){
            if(root.equals("user")){
                login = userService.login(username, password);
                if(login){
                    session.setAttribute("username" , username);
                    mav.setViewName("index");
                }else {
                    mav.setViewName("login");
                }
            }else if(root.equals("teacher")){
                login = userService.teacherLogin(username, password);
                if(login){
                    session.setAttribute("username" , username);
                    mav.setViewName("teacherIndex");
                }else{
                    mav.setViewName("login");
                }
            }else{
                login = userService.studentLogin(username, password);
                if(login){
                    session.setAttribute("username" , username);
                    mav.setViewName("studentIndex");
                }else{
                    mav.setViewName("login");
                }
            }
        }else{
            mav.setViewName("login");
        }

        return mav;
    }

    @RequestMapping(value = "/signUp" , method = RequestMethod.POST)
    public String signUp(@RequestParam("username") String username,
                            @RequestParam("email") String email,
                            @RequestParam("password") String password , HttpSession session){
        boolean result = userService.signUp(username, password, email);

        return "login";
    }

}
