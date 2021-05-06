package com.example.common;

import lombok.Data;

@Data
public class CommonResult {
    private int code;
    private String message;
    //可以改个名容易混淆
    private Object data;

    public static CommonResult succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static CommonResult succ(String mess) {
        return succ(200, mess, null);
    }


    //    可以直接 Result.
    public static CommonResult succ(int code, String msg, Object data) {
        CommonResult r = new CommonResult();
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static CommonResult fail(Object data) {
        return fail(400, "fail", data);
    }


    public static CommonResult fail(String message) {
        return fail(400, message, null);
    }


    //    可以直接 Result.
    public static CommonResult fail(int code, String msg, Object data) {
        CommonResult r = new CommonResult();
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }
}
