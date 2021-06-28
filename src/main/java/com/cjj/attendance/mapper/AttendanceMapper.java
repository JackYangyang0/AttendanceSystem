package com.cjj.attendance.mapper;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.DateNum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询每月有多少缺勤记录
     * @return
     */
    List<DateNum> getDateNum();

    /**
     * 查询本班级每月有多少缺勤记录
     * @param claz
     * @return
     */
    List<DateNum> getClazDateNum(String claz);

    /**
     * 查询学生自己有多少缺勤记录
     * @param stuId
     * @return
     */
    List<DateNum> getMyDateNum(String stuId);

}
