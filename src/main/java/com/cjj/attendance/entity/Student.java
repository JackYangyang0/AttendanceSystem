package com.cjj.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String stuId;

    private String stuName;

    private int gender;

    private int age;

    private String claz;

}
