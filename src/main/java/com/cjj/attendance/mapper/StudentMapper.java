package com.cjj.attendance.mapper;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    List<Student> queryStudentList();

    List<Student> searchStudent(String stuName);

    Student queryOneStudent(String stuName);

    Student queryOneById(String stuId);

    int deleteStudent(String[] ids);

    int insertStudent(@Param("student") Student student);

    int updateStudent(@Param("student") Student student);

    List<Attendance> getMyAttendance(String stuId);
}
