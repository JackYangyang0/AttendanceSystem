package com.cjj.attendance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    private int atdId;

    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date absenceDate;

    private String lesson;

    private String lessonName;

    private String stuName;

    private int absenceType;

}
