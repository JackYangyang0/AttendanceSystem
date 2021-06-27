package com.cjj.attendance.service;
import com.cjj.attendance.entity.Attendance;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AttendanceService {

    PageInfo<Attendance> queryAttendance(int pageNum , int pageSize);

    PageInfo<Attendance> searchAttendance(int pageNum , int pageSize , String stuName);

    int deleteAttendance(String[] atdId);

    int addAttendance(Attendance attendance);

    int updateAttendance(Attendance attendance);
}
