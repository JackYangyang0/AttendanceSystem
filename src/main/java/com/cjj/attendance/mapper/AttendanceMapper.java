package com.cjj.attendance.mapper;

import com.cjj.attendance.entity.Attendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceMapper {
    /**
     * 查询所有考勤信息
     * @return
     */
    List<Attendance> queryAttendance();

    /**
     * 通过学生姓名查询考勤信息
     * @param stuName
     * @return
     */
    List<Attendance> searchAttendance(String stuName);

    /**
     * 通过考勤编号删除考勤记录
     * @param ids
     * @return
     */
    int deleteAttendance(String[] ids);

    /**
     * 添加
     * @param attendance
     * @return
     */
    int addAttendance(@Param("attendance") Attendance attendance);

    /**
     * 查询最近添加的考勤记录
     * @return
     */
    int queryAtdId();

    /**
     * 修改
     * @param attendance
     * @return
     */
    int updateAttendance(@Param("attendance") Attendance attendance);

}
