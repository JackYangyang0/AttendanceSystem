package com.cjj.attendance.mapper;

import com.cjj.attendance.entity.Student;
import com.cjj.attendance.entity.Teacher;
import com.cjj.attendance.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User login(String username , String password);

    Teacher teacherLogin(String teacherId , String password);

    Student studentLogin(String stuId);

    int signUp(String username , String password , String nickname);
}
