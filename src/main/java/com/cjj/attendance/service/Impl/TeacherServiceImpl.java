package com.cjj.attendance.service.Impl;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.Student;
import com.cjj.attendance.mapper.AttendanceMapper;
import com.cjj.attendance.mapper.StuAtdMapper;
import com.cjj.attendance.mapper.TeacherMapper;
import com.cjj.attendance.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StuAtdMapper stuAtdMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     * 查询班级内的学生
     * @param pageNum
     * @param pageSize
     * @param claz
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Student> queryClazStudent(int pageNum , int pageSize ,String claz) {
        PageHelper.startPage(pageNum , pageSize);
        List<Student> students = teacherMapper.queryClazStudent(claz);
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        return pageInfo;
    }

    /**
     * 通过姓名查询学生
     * @param pageNum
     * @param pageSize
     * @param stuName
     * @param claz
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Student> searchStudent(int pageNum, int pageSize, String stuName, String claz) {
        PageHelper.startPage(pageNum , pageSize);
        List<Student> students = teacherMapper.searchStudent(stuName, claz);
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        return pageInfo;
    }

    /**
     * 查询老师所带的班级
     * @param teacherId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public String queryClaz(String teacherId) {
        String claz = teacherMapper.queryClaz(teacherId);
        return claz;
    }

    /**
     * 查询班级内的考勤记录
     * @param pageNum
     * @param pageSize
     * @param claz
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Attendance> queryClazAttendance(int pageNum, int pageSize, String claz) {
        PageHelper.startPage(pageNum , pageSize);
        List<Attendance> attendances = teacherMapper.queryClazAttendance(claz);
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        return pageInfo;
    }

    /**
     * 通过学生姓名查询本班该学生的考勤记录
     * @param pageNum
     * @param pageSize
     * @param stuName
     * @param claz
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Attendance> searchStudentAttendance(int pageNum, int pageSize, String stuName, String claz) {
        PageHelper.startPage(pageNum , pageSize);
        List<Attendance> attendances = teacherMapper.searchStudentAttendance(stuName, claz);
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        return pageInfo;
    }

    /**
     * 添加考勤信息
     * @param attendance
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertAttendance(Attendance attendance , String claz) {
        List<Student> students = teacherMapper.searchStudent(attendance.getStuName(), claz);
        int result = 0;
        if(students.size() != 0){
            result = teacherMapper.insertStudentAttendance(attendance);
            int atdId = attendanceMapper.queryAtdId();
            stuAtdMapper.addMsg(students.get(0).getStuId() , atdId);
//            System.out.println(students.get(0));
        }
        return result;
    }

    /**
     * 老师只能修改缺勤的类型
     * @param absenceType
     * @param atdId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateAbsenceType(int absenceType, int atdId) {
        int result = 0;
        result = teacherMapper.updateAbsenceType(absenceType, atdId);
        return result;
    }
}
