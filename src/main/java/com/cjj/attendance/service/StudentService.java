package com.cjj.attendance.service;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.Student;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {

    PageInfo<Student> studentList(int pageNum , int pageSize);

    PageInfo<Student> searchStudent(String stuName , int pageNum , int pageSize);

    Student queryOneById(String stuId);

    int deleteStudent(String[] ids);

    int insertStudent(Student student);

    int updateStudent(Student student);

    PageInfo<Attendance> queryStudentAttendance(int page , int limit , String stuId);

    int deleteMsg(String[] ids);

    PageInfo<Attendance> myAttendanceList(int page , int limit , String stuId);

}
