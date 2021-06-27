package com.cjj.attendance.utils;

/**
 *  返回响应代码工具类
 */
public enum ResultCode {
    SUCCESS(200),//成功
    WRONG_UP(-200),//帐号密码不存在
    FAIL(400),//失败
    TIME_OUT(402),//超时
    UNAUTHORIZED(401),//token未认证（签名错误）
    NOT_FOUND(404),//不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
