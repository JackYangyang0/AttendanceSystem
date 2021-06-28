package com.cjj.attendance.service;
import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.DateNum;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface AttendanceService {

    PageInfo<Attendance> queryAttendance(int pageNum , int pageSize);

    PageInfo<Attendance> searchAttendance(int pageNum , int pageSize , String stuName);

    int deleteAttendance(String[] atdId);

    int addAttendance(Attendance attendance);

    int updateAttendance(Attendance attendance);

    List<DateNum> getDateNum();

    List<DateNum> getClazDateNum(String claz);

    List<DateNum> getMyDateNum(String stuId);
}
