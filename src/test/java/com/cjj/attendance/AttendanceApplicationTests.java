package com.cjj.attendance;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.Student;
import com.cjj.attendance.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AttendanceApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
    }

    @Test
    void testUpdate(){
        Student student = new Student("1" , null , 1 , 29 , "音乐1703");
        int i = studentService.updateStudent(student);
        System.out.println(i);
    }

    @Test
    void testQuery(){
        PageInfo<Attendance> pageInfo = studentService.queryStudentAttendance(1, 5, "5");
        List<Attendance> list = pageInfo.getList();
        for (Attendance attendance : list) {
            System.out.println(attendance);
        }
    }

}