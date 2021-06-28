package com.cjj.attendance.service.Impl;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.DateNum;
import com.cjj.attendance.entity.Student;
import com.cjj.attendance.mapper.AttendanceMapper;
import com.cjj.attendance.mapper.StuAtdMapper;
import com.cjj.attendance.mapper.StudentMapper;
import com.cjj.attendance.service.AttendanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StuAtdMapper stuAtdMapper;

    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Attendance> queryAttendance(int pageNum , int pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        List<Attendance> attendances = attendanceMapper.queryAttendance();
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        return pageInfo;
    }

    /**
     * 通过学会姓名查询考勤记录
     * @param pageNum
     * @param pageSize
     * @param stuName
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public PageInfo<Attendance> searchAttendance(int pageNum , int pageSize , String stuName){
        PageHelper.startPage(pageNum , pageSize);
        List<Attendance> attendances = attendanceMapper.searchAttendance(stuName);
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        return pageInfo;
    }

    /**
     *  级联删除考勤记录
     * @param atdId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteAttendance(String[] atdId) {
        stuAtdMapper.deleteMsgByAtdId(atdId);
        int i = attendanceMapper.deleteAttendance(atdId);
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addAttendance(Attendance attendance) {
        Student student = studentMapper.queryOneStudent(attendance.getStuName());
        int result = 0;
        if(student!=null) {
            result = attendanceMapper.addAttendance(attendance);
            int atdId = attendanceMapper.queryAtdId();
            stuAtdMapper.addMsg(student.getStuId(), atdId);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateAttendance(Attendance attendance) {
        int i = attendanceMapper.updateAttendance(attendance);
        return i;
    }

    /**
     * 获取每个月有多少缺勤的
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public List<DateNum> getDateNum() {
        List<DateNum> dateNum = attendanceMapper.getDateNum();
        return dateNum;
    }

    /**
     * 获取本班级有多少缺勤的记录
     * @param claz
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public List<DateNum> getClazDateNum(String claz) {
        List<DateNum> clazDateNum = attendanceMapper.getClazDateNum(claz);
        return clazDateNum;
    }

    /**
     * 查询学生自身的缺勤记录数量
     * @param stuId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
    @Override
    public List<DateNum> getMyDateNum(String stuId) {
        List<DateNum> myDateNum = attendanceMapper.getMyDateNum(stuId);
        return myDateNum;
    }


}
