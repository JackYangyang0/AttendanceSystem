package com.cjj.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private String teacherId;

    private String teacherName;

    private String claz;

    private String password;

}
