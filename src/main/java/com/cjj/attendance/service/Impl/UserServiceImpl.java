package com.cjj.attendance.service.Impl;

import com.cjj.attendance.entity.Student;
import com.cjj.attendance.entity.Teacher;
import com.cjj.attendance.entity.User;
import com.cjj.attendance.mapper.UserMapper;
import com.cjj.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public boolean login(String username, String password) {
        User login = userMapper.login(username, password);
        if(login != null)return true;
        else return false;
    }

    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public boolean teacherLogin(String teacherId, String password) {
        Teacher teacher = userMapper.teacherLogin(teacherId, password);
        if(teacher != null){ return true; }
        return false;
    }

    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public boolean studentLogin(String stuId , String password){
        Student student = userMapper.studentLogin(stuId);
        StringBuilder stringBuilder = new StringBuilder();
        if(student != null){
            String reverse = stringBuilder.append(password).reverse().toString();
            if(student.getStuId().equals(reverse)){
//                System.out.println(student.getStuId() + reverse);
                return true;
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean signUp(String username, String password, String nickname) {
        int i = userMapper.signUp(username, password, nickname);
        if(i != 0){
            return true;
        }
        return false;
    }
}
