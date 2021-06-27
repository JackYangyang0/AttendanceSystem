package com.cjj.attendance.controller;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.ResultKit;
import com.cjj.attendance.entity.Student;
import com.cjj.attendance.service.AttendanceService;
import com.cjj.attendance.service.StudentService;
import com.cjj.attendance.utils.JSON;
import com.cjj.attendance.utils.ResultCode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceService attendanceService;

    /*
        查询学生，将查询到的学生返回到页面
     */
    @GetMapping("/list")
    public ResultKit<List<Student>> studentList(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "5") Integer limit,
                                                String name,
                                                HttpServletRequest request){
        PageInfo<Student> students = null;
        // 判断是否是查询操作
        if(!StringUtils.isEmptyOrWhitespace(name)) {
            students = studentService.searchStudent(name, page , limit);
        } else {
            students =  studentService.studentList(page, limit);
        }
//        System.out.println(students);
        ResultKit<List<Student>> resultKit = new ResultKit<>();
        resultKit.setCode(0);
        resultKit.setMsg("");
        resultKit.setCount((int)students.getTotal());
        resultKit.setData(students.getList());
        return resultKit;
    }

    @DeleteMapping("/delete")
    public ResultKit<Integer> deleteStudent(@RequestParam String ids, HttpServletRequest request){
        ResultKit<Integer> resultKit = new ResultKit<>();
        String[] id = ids.split(",");
        studentService.deleteMsg(id);
        int result = studentService.deleteStudent(id);
        resultKit.setCode(0);
        resultKit.setMsg("");
        resultKit.setCount(result);
        return resultKit;
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResultKit<Integer> insertStudent(@RequestBody Student student , HttpServletRequest request){
//        System.out.println(student);
        ResultKit<Integer> resultKit = new ResultKit<>();
        int result = studentService.insertStudent(student);
        if(result != 0){
            resultKit.setCode(0);
            resultKit.setMsg("");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("添加失败！");
        }
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }

    @PutMapping("/update")
    @ResponseBody
    public ResultKit<Integer> updateStudent(@RequestBody Student student , HttpServletRequest request){
//        System.out.println(student);
        ResultKit<Integer> resultKit = new ResultKit<>();
        int result = studentService.updateStudent(student);
        if(result != 0){
            resultKit.setCode(0);
            resultKit.setMsg("");
        }else{
            resultKit.setCode(-1);
            resultKit.setMsg("修改失败！");
        }
        resultKit.setCount(result);
        resultKit.setData(result);
        return resultKit;
    }

    @GetMapping("/detailMsg")
    public ResultKit<List<Attendance>> detailMsg(@RequestParam int page ,
                                                 @RequestParam int limit ,
                                                 String name , HttpServletRequest request){
        ResultKit<List<Attendance>> resultKit = new ResultKit<>();
        if(name == null){
            resultKit.setCode(-1);
            resultKit.setMsg("请输入要查询学生的学号进行查找");
        }else{
            PageInfo<Attendance> pageInfo = studentService.queryStudentAttendance(page, limit, name);
            resultKit.setCode(0);
            resultKit.setMsg("");
            resultKit.setCount((int)pageInfo.getTotal());
            resultKit.setData(pageInfo.getList());
        }
        return resultKit;
    }

    @GetMapping("/detailStudent")
    public ResultKit<List<Student>> detailStudent(String name , HttpServletRequest request){
        ResultKit<List<Student>> resultKit = new ResultKit<>();
        List<Student> list = null;
        if(name == null){
            resultKit.setCode(-1);
            resultKit.setMsg("请输入要查询学生的学号进行查找");
        }else{
            list = new ArrayList<Student>();
            Student student = studentService.queryOneById(name);
            list.add(student);
            if(student == null){
                resultKit.setCode(-1);
                resultKit.setMsg("查无此人，请确认学号是否正确");
                resultKit.setCount(0);
            }else{
                resultKit.setCode(0);
                resultKit.setMsg("");
                resultKit.setCount(1);
            }
            resultKit.setData(list);
        }
        return resultKit;
    }

    @GetMapping("/myAttendanceList")
    public ResultKit<List<Attendance>> myAttendanceList(@RequestParam int page ,
                                                  @RequestParam int limit ,
                                                  HttpSession session){
        String stuId = (String)session.getAttribute("username");
        ResultKit<List<Attendance>> resultKit = new ResultKit<>();
        PageInfo<Attendance> pageInfo = studentService.myAttendanceList(page, limit , stuId);
        if(pageInfo.getTotal() == 0){
            resultKit.setMsg("恭喜！您没有缺勤记录");
        }else {
            resultKit.setMsg("");
        }
        resultKit.setCode(0);
        resultKit.setCount((int)pageInfo.getTotal());
        resultKit.setData(pageInfo.getList());
        return resultKit;
    }

}
