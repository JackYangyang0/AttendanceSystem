package com.cjj.attendance.mapper;

import com.cjj.attendance.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuAtdMapper {

    /**
     * 级联添加
     * @param stuId
     * @param atdId
     * @return
     */
    int addMsg(String stuId , int atdId);

    /**
     * 通过学生学号查询考勤编号
     * @param ids
     * @return
     */
    String[] queryMsgId(String[] ids);

    /**
     * 通过考勤号查询是否存在
     * @param ids
     * @return
     */
    String[] queryAtdId(String[] ids);

    /**
     * 通过学生学号查询考勤信息
     * @param stuId
     * @return
     */
    List<Attendance> queryStudentAttendance(String stuId);

    /**
     * 通过学生学号删除级联信息
     * @param ids
     * @return
     */
    int deleteMsg(String[] ids);

    /**
     * 通过考勤编号删除级联信息
     * @param ids
     * @return
     */
    int deleteMsgByAtdId(String[] ids);
}
