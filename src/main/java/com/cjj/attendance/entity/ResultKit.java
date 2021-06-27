package com.cjj.attendance.entity;

import lombok.Data;

@Data
public class ResultKit<T> {

    private int code; // 响应码

    private String msg;  // 响应信息

    private Integer count;

    private T data; // 响应数据

}
