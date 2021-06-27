package com.cjj.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
public class ViewController {

    @GetMapping("student")
    public String student(){
        return "student";
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("attendance")
    public String attendance(){
        return "attendance";
    }

    @GetMapping("studentDetail")
    public String studentDetail(){ return "studentDetail"; }

    @GetMapping("clazStudent")
    public String clazStudent(){ return "clazStudent"; }

    @GetMapping("clazAttendance")
    public String clazAttendance(){ return "clazAttendance"; }

    @GetMapping("myAttendance")
    public String myAttendance(){
        return "myAttendance";
    }

}
