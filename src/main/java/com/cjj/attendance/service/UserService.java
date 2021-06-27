package com.cjj.attendance.service;

public interface UserService {

    boolean login(String username , String password);

    boolean teacherLogin(String teacherId , String password);

    boolean studentLogin(String stuId , String password);

    boolean signUp(String username , String password , String nickname);
}
