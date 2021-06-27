package com.cjj.attendance.service.Impl;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.Student;
import com.cjj.attendance.mapper.AttendanceMapper;
import com.cjj.attendance.mapper.StuAtdMapper;
import com.cjj.attendance.mapper.StudentMapper;
import com.cjj.attendance.service.AttendanceService;
import com.cjj.attendance.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private StuAtdMapper stuAtdMapper;

    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Student> studentList(int pageNum , int pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        List<Student> students = studentMapper.queryStudentList();
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        return pageInfo;
    }

    /**
     * 通过学生姓名来查询
     * @param stuName
     * @param pageNum
     * @param pageSize
     * @return
     */

    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Student> searchStudent(String stuName , int pageNum , int pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        List<Student> students = studentMapper.searchStudent(stuName);
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        return pageInfo;
    }

    /**
     * 通过学生id来查询学生
     * @param stuId
     * @return
     */

    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public Student queryOneById(String stuId){
        Student student = studentMapper.queryOneById(stuId);
        return student;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteStudent(String[] ids) {
        int i = studentMapper.deleteStudent(ids);
        return i;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertStudent(Student student) {
        int i = studentMapper.insertStudent(student);
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateStudent(Student student) {
        int i = studentMapper.updateStudent(student);
        return i;
    }

    /**
     *  查询单个学生的所有考勤记录
     * @param page
     * @param limit
     * @param stuId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Attendance> queryStudentAttendance(int page , int limit , String stuId){
        PageHelper.startPage(page , limit);
        List<Attendance> attendances = stuAtdMapper.queryStudentAttendance(stuId);
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        return pageInfo;
    }

    /**
     * 级联删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteMsg(String[] ids) {
        String[] strings = stuAtdMapper.queryMsgId(ids);
        int i = 0;
//        System.out.println(strings.length);
        if(strings.length != 0) {
            stuAtdMapper.deleteMsg(ids);
            i = attendanceMapper.deleteAttendance(strings);
        }
        return i;
    }

    /**
     * 学生查看自身考勤信息
     * @param page
     * @param limit
     * @param stuId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Attendance> myAttendanceList(int page, int limit, String stuId) {
        PageHelper.startPage(page , limit);
        List<Attendance> myAttendance = studentMapper.getMyAttendance(stuId);
        PageInfo<Attendance> pageInfo = new PageInfo<>(myAttendance);
        return pageInfo;
    }

}
