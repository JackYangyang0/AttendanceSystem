package com.cjj.attendance.mapper;

import com.cjj.attendance.entity.Attendance;
import com.cjj.attendance.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {

    /**
     * 查询老师负责班级的学生
     * @param claz
     * @return
     */
    List<Student> queryClazStudent(String claz);

    /**
     * 通过学生姓名查询该班级的学生
     * @param stuName
     * @param claz
     * @return
     */
    List<Student> searchStudent(String stuName , String claz);

    /**
     * 查询老师所负责的班级
     * @param teacherId
     * @return
     */
    String queryClaz(String teacherId);

    /**
     * 查询本班的考勤信息
     * @param claz
     * @return
     */
    List<Attendance> queryClazAttendance(String claz);

    /**
     *
     * @param stuName
     * @param claz
     * @return
     */
    List<Attendance> searchStudentAttendance(String stuName , String claz);

    Integer insertStudentAttendance(@Param("attendance") Attendance attendance);

    Integer updateAbsenceType(int absenceType , int atdId);
}
