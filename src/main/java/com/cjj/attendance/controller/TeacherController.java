package com.cjj.attendance.controller;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.ResultKit;
import com.cjj.attendance.entity.Student;
import com.cjj.attendance.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/studentList")
    public ResultKit<List<Student>> studentList(@RequestParam int page ,
                                                @RequestParam int limit,
                                                String name , HttpSession session){
        String username = (String)session.getAttribute("username");
        PageInfo<Student> pageInfo = null;
//        System.out.println(username);
        String claz = teacherService.queryClaz(username);
        ResultKit<List<Student>> resultKit = new ResultKit<>();
        if(!StringUtils.isEmptyOrWhitespace(name)) {
            pageInfo = teacherService.searchStudent(page, limit, name, claz);
        } else {
            pageInfo = teacherService.queryClazStudent(page, limit, claz);
        }
//        System.out.println(pageInfo.getList());
        resultKit.setCode(0);
        resultKit.setMsg("");
        resultKit.setCount((int)pageInfo.getTotal());
        resultKit.setData(pageInfo.getList());
        return resultKit;
    }

    @GetMapping("/attendanceList")
    public ResultKit<List<Attendance>> attendanceList(@RequestParam int page ,
                                                      @RequestParam int limit ,
                                                      String name , HttpSession session){
        String username = (String)session.getAttribute("username");
        ResultKit<List<Attendance>> resultKit = new ResultKit<>();
        PageInfo<Attendance> pageInfo = null;
        String claz = teacherService.queryClaz(username);
//        System.out.println(username + page + limit + name + claz);
        if(name != null){
            pageInfo = teacherService.searchStudentAttendance(page , limit , name , claz);
        }else{
            pageInfo = teacherService.queryClazAttendance(page, limit, claz);
        }

        resultKit.setCode(0);
        resultKit.setMsg("");
        resultKit.setCount((int)pageInfo.getTotal());
        resultKit.setData(pageInfo.getList());
        return resultKit;
    }

    @PostMapping("/insertAttendance")
    @ResponseBody
    public ResultKit<Integer> insertAttendance(@RequestBody Attendance attendance , HttpSession session){
        String username = (String)session.getAttribute("username");
        String claz = teacherService.queryClaz(username);
        ResultKit<Integer> resultKit = new ResultKit<>();
        Integer result = teacherService.insertAttendance(attendance, claz);
        if(result != 0){
            resultKit.setCode(0);
            resultKit.setMsg("添加成功");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("添加失败");
        }
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }

    @PutMapping("/updateAttendance")
    @ResponseBody
    public ResultKit<Integer> updateAttendance(@RequestBody Attendance attendance , HttpSession session){
//        System.out.println(attendance);
        ResultKit<Integer> resultKit = new ResultKit<>();
        Integer result = teacherService.updateAbsenceType(attendance.getAbsenceType(), attendance.getAtdId());
        if(result != 0){
            resultKit.setCode(0);
            resultKit.setMsg("修改成功");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("修改失败，请稍后重试");
        }
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }
}
