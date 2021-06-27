package com.cjj.attendance.service;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.Student;
import com.github.pagehelper.PageInfo;

public interface TeacherService {

    PageInfo<Student> queryClazStudent(int pageNum , int pageSize , String claz);

    PageInfo<Student> searchStudent(int pageNum , int pageSize , String stuName , String claz);

    String queryClaz(String teacherId);

    PageInfo<Attendance> queryClazAttendance(int pageNum , int pageSize , String claz);

    PageInfo<Attendance> searchStudentAttendance(int pageNum , int pageSize , String stuName , String claz);

    Integer insertAttendance(Attendance attendance , String claz);

    Integer updateAbsenceType(int absenceType , int atdId);
}
